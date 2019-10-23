package com.boman.upms.server.controller.manage;

import com.boman.common.util.ShiroUtils;
import com.boman.upms.common.constant.DateKit;
import com.boman.upms.common.constant.HtmlUtils;
import com.boman.upms.common.constant.UpmsResult;
import com.boman.upms.common.constant.UpmsResultConstant;
import com.boman.upms.dao.model.BomanForm;
import com.boman.upms.rpc.api.BomanFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "表单管理", description = "表单管理")
@RequestMapping("/manage/form")
public class BomanFormController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BomanFormController.class);

    @Autowired
    private BomanFormService bomanFormService;

    @ApiOperation(value = "表单首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        // 表单首页
        return "/manage/form/index.jsp";
    }

    @ApiOperation(value = "表单列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
                       @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
                       @RequestParam(required = false, defaultValue = "", value = "search") String search,
                       @RequestParam(required = false, value = "sort") String sort,
                       @RequestParam(required = false, value = "order") String order){
        BomanForm bomanForm = new BomanForm();
        bomanForm.setName(search);
        bomanForm.setOrder(order);
        bomanForm.setSort(sort);
        List<BomanForm> rows = bomanFormService.selectByBomanFormForOffsetPage(bomanForm, offset, limit);
        long total = bomanFormService.countByBomanForm(bomanForm);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "表单设计器")
    @RequestMapping(value = "/design", method = RequestMethod.GET)
    public String design(){
        // 可视化表单设计器
        return "/manage/form/design.jsp";
    }

    @ApiOperation(value = "表单保存")
    @RequestMapping(value = "/design/save", method = {RequestMethod.POST})
    @ResponseBody
    public JSONObject save(String name, String html, String id){
        JSONObject obj = new JSONObject();
        try {
            if(StringUtils.isEmpty(id) && StringUtils.isEmpty(name)){
                obj.element("success", false);
                obj.element("message", "表单信息不完整，保存失败");
                return obj;
            }
            BomanForm bomanForm = new BomanForm();
            if(!StringUtils.isEmpty(id)){
                bomanForm = bomanFormService.selectByPrimaryKey(id);
                if(bomanForm  == null){
                    obj.element("success", false);
                    obj.element("message", "表单不存在，请检查是否已被删除");
                    return obj;
                }
            }else {
                bomanForm.setName(name);
                bomanForm.setVersion("1.0");
            }
            bomanForm.setSource(html);
            bomanForm.setContent(HtmlUtils.parseHTML(html));
            bomanForm.setCreateTime(DateKit.now());
            bomanForm.setCreateUser(ShiroUtils.getLoginName());
            long bId = bomanFormService.saveForm(bomanForm);
            obj.element("id", bId);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            obj.element("success", false);
            obj.element("message", "保存失败");
            return obj;
        }
        obj.element("success", true);
        return obj;
    }

    @ApiOperation(value = "表单删除")
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public Object delete(@PathVariable String id){
        try {
            BomanForm bomanForm = bomanFormService.selectByPrimaryKey(id);
            if(bomanForm != null){
                String table = bomanForm.getTableName();
                bomanFormService.deleteByPrimaryKey(id);
                if(!StringUtils.isEmpty(table)){
                    String sql = "drop table if exists " + table;
                    bomanFormService.executeSqlBatch(sql);
                }
            }
            return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return new UpmsResult(UpmsResultConstant.FAILED, "操作失败");
        }
    }

    @ApiOperation(value = "表单编辑")
    @RequestMapping(value = "/design/edit/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String edit(Model model, @PathVariable String id){
        BomanForm bomanForm  = bomanFormService.selectByPrimaryKey(id);
        model.addAttribute("form", bomanForm);
        return "/manage/form/design.jsp";
    }

    /**
     * 预览数据库中的表单
     * @param model
     * @param id
     * @param tid
     * @param start
     */
    @RequestMapping(value = "/design/preview/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String preview_id(Model model, @PathVariable String id, String tid, String start){
        BomanForm bomanForm = null;
        if(StringUtils.isNotBlank(id)){
            bomanForm = bomanFormService.selectByPrimaryKey(id);
            String html = bomanFormService.selectHtmlRuntime(id, tid, start, ShiroUtils.getLoginName());

            model.addAttribute("html", html);
            model.addAttribute("form", bomanForm);
        }
        return "/manage/form/preview.jsp";
    }

    /**
     * 表单数据提交
     * @param data
     */
    @RequestMapping(value = "/submit", method = {RequestMethod.POST})
    @ResponseBody
    public JSONObject submit(String data) {
        JSONObject jsonObject = bomanFormService.submit(data);
        return jsonObject;
    }
}
