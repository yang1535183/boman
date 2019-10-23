package com.boman.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 启动解压bomanAdmin-x.x.x.jar到resources目录
 */
public class BomanAdminUtil implements InitializingBean, ServletContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(BomanAdminUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        LOGGER.info("===== 开始解压zheng-admin =====");
        String version = PropertiesFileUtil.getInstance("boman-admin-client").get("zheng.admin.version");
        LOGGER.info("zheng-admin.jar 版本: {}", version);
        String jarPath = servletContext.getRealPath("/WEB-INF/lib/boman-admin-" + version + ".jar");
        LOGGER.info("zheng-admin.jar 包路径: {}", jarPath);
        String resources = servletContext.getRealPath("/") + "/resources/zheng-admin";
        LOGGER.info("zheng-admin.jar 解压到: {}", resources);
        JarUtil.decompress(jarPath, resources);
        LOGGER.info("===== 解压zheng-admin完成 =====");
    }
}
