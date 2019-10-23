package com.boman.upms.server.controller.activiti;

import com.boman.upms.rpc.api.ActivitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "service")
public class ModelEditorJsonRestResource {

    @Autowired
    private ActivitiService activitiService;

    /**
     * 获取activity 编辑需要的json
     * @param modelId
     * @return
     */
    @RequestMapping(value = "/model/{modelId}/json", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getEditorJson(@PathVariable String modelId){
        String objectNode = activitiService.getEditorJson(modelId);
        return objectNode;
    }
}
