package com.boman.upms.server.controller.activiti;

import com.boman.common.util.ShiroUtils;
import com.boman.upms.common.constant.UpmsResult;
import com.boman.upms.common.constant.UpmsResultConstant;
import com.boman.upms.rpc.api.ActivitiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@Api(value = "流程管理", description = "流程管理")
@RequestMapping("/manage/activiti")
public class BomanActivitiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BomanActivitiController.class);

    @Autowired
    private ActivitiService activitiService;

    @ApiOperation(value = "流程管理首页")
    @RequiresPermissions("upms:activiti:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        // activiti index
        return "/manage/activiti/index.jsp";
    }

    @ApiOperation(value = "流程列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
                       @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
                       @RequestParam(required = false, defaultValue = "", value = "search") String search,
                       @RequestParam(required = false, value = "sort") String sort,
                       @RequestParam(required = false, value = "order") String order) {
        Object object = activitiService.modelList(offset, limit, search);
        return object;
    }

    /**
     * 添加流程到Activiti中
     * @return
     */
    @RequestMapping(value = "/add/do", method = RequestMethod.POST)
    @ResponseBody
    public Object doAdd(String name, String key, String descript){
        String modelId = activitiService.goActiviti(name, key, descript, ShiroUtils.getLoginName());
        if(StringUtils.isEmpty(modelId)){
            return new UpmsResult(UpmsResultConstant.FAILED, "操作失败");
        }
        return new UpmsResult(UpmsResultConstant.SUCCESS, modelId);
    }

    @ApiOperation(value = "新建流程")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        // 新建流程
        return "/manage/activiti/create.jsp";
    }

    @ApiOperation(value = "删除流程")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("id") String id){
        try {
            activitiService.deleteActiviti(id);
        }catch (Exception e){
            return new UpmsResult(UpmsResultConstant.FAILED, "操作失败");
        }
        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }

    @ApiOperation(value = "部署流程")
    @RequestMapping(value = "/deploy/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object deploy(HttpServletRequest request, @PathVariable("id") String id){
        try {
            activitiService.deployActiviti(id);
        }catch (Exception e){
            return new UpmsResult(UpmsResultConstant.FAILED, "操作失败");
        }
        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }

    @ApiOperation(value = "取消部署流程")
    @RequestMapping(value = "/undeploy/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object undeploy(@PathVariable("id") String id){
        try {
            activitiService.undeployActiviti(id);
        }catch (Exception e){
            return new UpmsResult(UpmsResultConstant.FAILED, "操作失败");
        }
        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }

}
