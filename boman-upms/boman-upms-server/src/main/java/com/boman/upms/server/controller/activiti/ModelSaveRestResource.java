package com.boman.upms.server.controller.activiti;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boman.upms.rpc.api.ActivitiService;
import org.activiti.engine.ActivitiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 保存编辑的工作流
 */
@Controller
@RequestMapping("/service")
public class ModelSaveRestResource {

    @Autowired
    private ActivitiService activitiService;

    /**
     * 保存编辑的工作流
     * @param modelId
     * @param name
     * @param description
     * @param json_xml
     * @param svg_xml
     * @param request
     * @param response
     */
    @PostMapping(value = "/model/{modelId}/save")
    @ResponseStatus(value = HttpStatus.OK)
    public void saveModel(@PathVariable String modelId, String name, String description
            , String json_xml, String svg_xml, HttpServletRequest request, HttpServletResponse response){

        Map<String, String[]> map = request.getParameterMap();
        JSONObject jsonObject = new JSONObject();
        for(Map.Entry<String,String[]> entry : map.entrySet()){
            String data=entry.getKey()+"="+(entry.getValue()[0]);
            jsonObject= JSON.parseObject(data);
        }
        try {
            activitiService.saveModel(modelId,jsonObject);
        }catch (Exception e){
            e.printStackTrace();
            throw new ActivitiException("Error saving model", e);
        }
    }

}
