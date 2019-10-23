package com.boman.upms.rpc.service.impl;

import com.boman.common.db.DataSourceEnum;
import com.boman.common.db.DynamicDataSource;
import com.boman.upms.common.builder.FormBuilder;
import com.boman.upms.common.builder.FormRuntime;
import com.boman.upms.dao.mapper.BomanFormMapper;
import com.boman.upms.dao.model.BomanForm;
import com.boman.upms.rpc.api.BomanFormService;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONObject;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BomanFormService接口实现
 */
@Service
@Transactional
public class BomanFormServiceImpl implements BomanFormService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BomanFormServiceImpl.class);

    @Autowired
    private BomanFormMapper bomanFormMapper;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    /**
     * 查询表单列表
     */
    @Override
    public List<BomanForm> selectByBomanFormForOffsetPage(BomanForm bomanForm, int offset, int limit) {
        DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
        PageHelper.offsetPage(offset, limit, false);
        List<BomanForm> list = bomanFormMapper.selectByBomanFormForOffsetPage(bomanForm);
        return list;
    }

    /**
     *  查询表单总数
     * @param bomanForm
     */
    @Override
    public long countByBomanForm(BomanForm bomanForm) {
        DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
        long count = bomanFormMapper.countByBomanForm(bomanForm);
        return count;
    }

    /**
     *  执行原生的sql
     * @param sql
     */
    @Override
    public void executeSqlBatch(String sql) {
        DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
        bomanFormMapper.executeSqlBatch(sql);
    }

    /**
     * 保存表单
     */
    @Override
    public long saveForm(BomanForm bomanForm) {
        FormBuilder formBuilder = new FormBuilder(bomanForm.getSource());
        if(formBuilder.isEmpty()){
            throw new RuntimeException();
        }
        if(StringUtils.isEmpty(bomanForm.getName())){
            bomanForm.setName("新建表单");
        }
        bomanForm.setTip(formBuilder.getFormTip());
        String content = formBuilder.render();
        FormTableBuilder formTableBuilder = new FormTableBuilder(this, bomanForm.getTableName());
        formTableBuilder.createTable(formBuilder.getColumnMapping());
        bomanForm.setTableName(formTableBuilder.getTableName());
        bomanForm.setContent(content);
        if(bomanForm.getId() != null){
            bomanFormMapper.updateForm(bomanForm);
            return bomanForm.getId();
        }
        bomanFormMapper.saveForm(bomanForm);
        return bomanForm.getId();
    }

    /**
     * 根据主键查询实体类
     * @param id
     */
    @Override
    public BomanForm selectByPrimaryKey(String id) {
        return bomanFormMapper.selectByPrimaryKey(Integer.parseInt(id));
    }

    @Override
    public void deleteByPrimaryKey(String id) {
        bomanFormMapper.deleteByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 获取可运行的表单html
     * @param id
     * @param tid
     * @param start
     */
    @Override
    public String selectHtmlRuntime(String id, String tid, String start, String name) {
        if(StringUtils.isNotBlank(id)){
            BomanForm bomanForm = this.selectByPrimaryKey(id);
            FormRuntime formRuntime = new FormRuntime(bomanForm.getContent(), name);
            formRuntime.addParam("tid", tid)
                    .addParam("start", start != null)
                    .addParam("formAction", "/manage/form/submit")
                    .addParam("formId", bomanForm.getId());
            if(StringUtils.isNotBlank(tid)){
                Task task = taskService.createTaskQuery().taskId(tid).singleResult();
                if(task != null){
                    formRuntime.setVariableMap(taskService.getVariables(task.getId()));
                }
            }
            return formRuntime.html();
        }
        return null;
    }

    /**
     * 表单数据提交
     * @param data
     */
    @Override
    public JSONObject submit(String data) {
        JSONObject jData = JSONObject.fromObject(data);
        jData = jData.getJSONObject("master");
        JSONObject obj = new JSONObject();
        try {
            BomanForm bomanForm = this.selectByPrimaryKey(jData.getString("_formId"));
            if(bomanForm != null){
                //处理流程相关
                Map<String, Object> variables = new HashMap<>();
                if(StringUtils.isNotEmpty(bomanForm.getTableName())){
                    //遍历参数，组装sql语句
                    String insertSql = "insert into " + bomanForm.getTableName() + "(";
                    String insertSqlValue = " values(";
                    for (Object name : jData.keySet()) {
                        if ("_formId".equals(name) || "_tid".equals(name) || "_start".equals(name))
                            continue;
                        insertSql += "" + name + ",";
                        insertSqlValue += "'" + jData.get(name) + "',";
                        variables.put(name + "", jData.get(name));
                    }
                    insertSql = insertSql.substring(0, insertSql.length()-1);
                    insertSqlValue = insertSqlValue.substring(0, insertSqlValue.length()-1);
                    insertSql += ")" + insertSqlValue + ")";
                    this.executeSqlBatch(insertSql);
                }
                String tid = jData.getString("_tid");
                if(StringUtils.isNotEmpty(tid)){
                    if(jData.getBoolean("_start")){
                        runtimeService.startProcessInstanceById(tid, variables);
                    }else {
                        Task task = taskService.createTaskQuery().taskId(tid).singleResult();
                        if(task == null){
                            obj.element("success", false);
                            obj.element("message", "任务已结束或流程不存在！");
                            return obj;
                        }
                        taskService.complete(tid, variables);
                    }
                }

            }
            obj.element("success", true);
            if(StringUtils.isNotEmpty(bomanForm.getTip())){
                obj.element("tip", bomanForm.getTip());
            }
            return obj;
        }catch (Exception e){
            e.printStackTrace();
            obj.element("success", false);
            obj.element("message", e.toString());
            return obj;
        }
    }
}
