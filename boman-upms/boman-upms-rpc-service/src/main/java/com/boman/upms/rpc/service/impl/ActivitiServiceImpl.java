package com.boman.upms.rpc.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boman.upms.rpc.api.ActivitiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.*;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ActivitiService接口实现
 */
@Service
@Transactional
public class ActivitiServiceImpl implements ActivitiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivitiServiceImpl.class);

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 获取activity 编辑需要的json
     * @param modelId
     * @return
     */
    @Override
    public String getEditorJson(String modelId) {
        ObjectNode objectNode = null;

        Model model = repositoryService.getModel(modelId);
        if(model != null){
            try {
                if(StringUtils.isNotEmpty(model.getMetaInfo())){
                    objectNode = (ObjectNode) new ObjectMapper().readTree(model.getMetaInfo());
                }else {
                    objectNode = new ObjectMapper().createObjectNode();
                    objectNode.put("name", model.getName());
                }
                objectNode.put("modelId", model.getId());
                ObjectNode editorJsonNode = (ObjectNode) new ObjectMapper().readTree(new String(repositoryService.getModelEditorSource(model.getId()), "utf-8"));
                objectNode.set("model", editorJsonNode);
            } catch (Exception e){
                LOGGER.error("Error creating model JSON", e);
                throw new ActivitiException("Error creating model JSON", e);
            }
        }
        //objectNode.toString();
        return objectNode.toString();
    }

    /**
     * 保存编辑的工作流
     * @param modelId
     * @param jsonObject
     */
    @Override
    public void saveModel(String modelId, JSONObject jsonObject) {
        try {
            String name= (String) jsonObject.get("name");
            String description= (String) jsonObject.get("description");
            String json_xml= (String) jsonObject.get("json_xml");
            String svg_xml= (String) jsonObject.get("svg_xml");

            Model model = repositoryService.getModel(modelId);
            JSONObject object=new JSONObject();
            object.put("name", name);
            object.put("description", description);
            model.setMetaInfo(object.toString());
            model.setName(name);
            repositoryService.saveModel(model);

            repositoryService.addModelEditorSource(model.getId(), json_xml.getBytes("utf-8"));

            InputStream svgStream = new ByteArrayInputStream(svg_xml.getBytes("utf-8"));
            TranscoderInput input = new TranscoderInput(svgStream);

            PNGTranscoder transcoder = new PNGTranscoder();

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(outStream);

            transcoder.transcode(input, output);
            final byte[] result = outStream.toByteArray();
            repositoryService.addModelEditorSourceExtra(model.getId(), result);

            outStream.close();

        } catch (Exception e){
            LOGGER.error("Error saving model", e);
            e.printStackTrace();
        }
    }

    /**
     * 获取工作流列表
     * @param offset
     * @param limit
     * @param search
     */
    @Override
    public Object modelList(int offset, int limit, String search) {
        Map<String, Object> result = new HashMap<>();
        ModelQuery query = repositoryService.createModelQuery();
        List<org.activiti.engine.repository.Model> modelList = query.listPage(offset, limit);
        long count = query.count();
        result.put("rows", modelList);
        result.put("total", count);
        return result;
    }

    /**
     * 新建流程
     * @param name
     * @param key
     * @param descript
     */
    @Override
    public String goActiviti(String name, String key, String descript, String loginName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Model modelData = repositoryService.newModel();
            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put("modelId", key);
            modelObjectNode.put("name", name);
            modelObjectNode.put("revision", 1);
            modelObjectNode.put("description", descript == null ? "" : descript.toString());
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(name);
            modelData.setKey(key == null ? "" : key.toString());
            modelData.setCategory("");
            repositoryService.saveModel(modelData);

            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("resourceId", modelData.getId());
            //设置参数
            ObjectNode prop = objectMapper.createObjectNode();
            prop.put("process_id", key);
            prop.put("name", name);
            prop.put("documentation", descript);
            prop.put("process_namespace", "");
            prop.put("process_author", loginName);
            prop.put("process_version", 1);
            editorNode.set("properties", prop);

            //设置stencil
            ObjectNode stencil = objectMapper.createObjectNode();
            stencil.put("id", "BPMNDiagram");
            editorNode.set("stencil", stencil);
            //设置stencilset
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("url", "stencilsets/bpmn2.0/bpmn2.0.json");
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.set("stencilset", stencilSetNode);

            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
            return modelData.getId();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除流程
     * @param id
     */
    @Override
    public void deleteActiviti(String id) {
        repositoryService.deleteModel(id);
    }

    /**
     * 部署流程
     * @param id
     */
    @Override
    public void deployActiviti(String id) {
        try {
            Model model = repositoryService.getModel(id);
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(id));
            BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            String processName = model.getName() + ".bpmn20.xml";

            DeploymentBuilder builder = repositoryService.createDeployment();
            builder.name(model.getName());
            builder.addBpmnModel(processName, bpmnModel);
            builder.category(model.getCategory());
            Deployment deployment = builder.deploy();

            model.setDeploymentId(deployment.getId());
            repositoryService.saveModel(model);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 取消部署流程
     * @param id
     */
    @Override
    public void undeployActiviti(String id) {
        try {
            repositoryService.deleteDeployment(id, true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取已部署流程数据
     * @param offset
     * @param limit
     * @param search
     */
    @Override
    public Object selectApplylist(int offset, int limit, String search) {
        Map<String, Object> result = new HashMap<>();
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        long count = query.count();
        List<ProcessDefinition> list = query.listPage(offset, limit);
        JSONArray array = new JSONArray();
        for(ProcessDefinition processDefinition : list){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", processDefinition.getId());
            jsonObject.put("name", processDefinition.getName());
            jsonObject.put("key", processDefinition.getKey());
            jsonObject.put("descript", processDefinition.getDescription());
            array.add(jsonObject);
        }
        result.put("rows", array);
        result.put("total", count);
        return result;
    }
}
