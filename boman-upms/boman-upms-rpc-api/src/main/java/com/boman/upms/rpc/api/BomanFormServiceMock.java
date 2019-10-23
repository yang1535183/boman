package com.boman.upms.rpc.api;

import com.boman.upms.dao.model.BomanForm;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 降级实现BomanFormService接口
 */
public class BomanFormServiceMock implements BomanFormService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BomanFormServiceMock.class);

    @Override
    public List<BomanForm> selectByBomanFormForOffsetPage(BomanForm bomanForm, int offset, int limit) {
        LOGGER.info("BomanFormServiceMock ==> selectByBomanFormForOffsetPage");
        return null;
    }

    @Override
    public long countByBomanForm(BomanForm bomanForm) {
        LOGGER.info("BomanFormServiceMock ==> countByBomanForm");
        return 0;
    }

    @Override
    public void executeSqlBatch(String sql) {
        LOGGER.info("BomanFormServiceMock ==> executeSqlBatch");
    }

    @Override
    public long saveForm(BomanForm bomanForm) {
        LOGGER.info("BomanFormServiceMock ==> saveForm");
        return 0;
    }

    @Override
    public BomanForm selectByPrimaryKey(String id) {
        LOGGER.info("BomanFormServiceMock ==> selectByPrimaryKey");
        return null;
    }

    @Override
    public void deleteByPrimaryKey(String id) {
        LOGGER.info("BomanFormServiceMock ==> deleteByPrimaryKey");
    }

    @Override
    public String selectHtmlRuntime(String id, String tid, String start, String name) {
        LOGGER.info("BomanFormServiceMock ==> selectHtmlRuntime");
        return "";
    }

    @Override
    public JSONObject submit(String data) {
        LOGGER.info("BomanFormServiceMock ==> submit");
        return null;
    }
}
