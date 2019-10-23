package com.boman.upms.rpc.api;

import com.boman.upms.dao.model.BomanForm;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * 表单接口
 */
public interface BomanFormService {

    /**
     * 查询表单列表
     * @param bomanForm
     * @param offset
     * @param limit
     */
    public List<BomanForm> selectByBomanFormForOffsetPage(BomanForm bomanForm, int offset, int limit);

    /**
     *  表单总数
     * @param bomanForm
     */
    public long countByBomanForm(BomanForm bomanForm);

    /**
     *  MyBatis 执行原生sql语句
     * @param sql
     */
    public void executeSqlBatch(String sql);

    /**
     * 保存表单
     * @param bomanForm
     */
    public long saveForm(BomanForm bomanForm);

    /**
     * 根据主键查询实体类
     * @param id
     */
    public BomanForm selectByPrimaryKey(String id);

    /**
     *  删除记录
     * @param id
     */
    public void deleteByPrimaryKey(String id);

    /**
     * 获取可运行的表单html
     * @param id
     * @param tid
     * @param start
     */
    public String selectHtmlRuntime(String id, String tid, String start, String name);

    /**
     * 表单数据提交
     * @param data
     */
    public JSONObject submit(String data);
}
