package com.boman.upms.server.controller.activiti;

import com.boman.upms.common.constant.UpmsResult;
import com.boman.upms.common.constant.UpmsResultConstant;
import com.boman.upms.rpc.api.ActivitiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(value = "已部署流程管理", description = "已部署流程管理")
@RequestMapping("/manage/activiti/apply")
public class BomanActivitiApplyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BomanActivitiApplyController.class);

    @Autowired
    private ActivitiService activitiService;

    @ApiOperation(value = "已部署流程首页")
    @RequiresPermissions("upms:apply:read")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        // activiti apply index
        return "/manage/activiti/apply/index.jsp";
    }

    /**
     * 获取已部署流程数据
     * @return
     */
    @ApiOperation(value = "已部署流程列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
                       @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
                       @RequestParam(required = false, defaultValue = "", value = "search") String search,
                       @RequestParam(required = false, value = "sort") String sort,
                       @RequestParam(required = false, value = "order") String order){
        Object object = activitiService.selectApplylist(offset, limit, search);
        return object;
    }

    public Object start(){
        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }


}
