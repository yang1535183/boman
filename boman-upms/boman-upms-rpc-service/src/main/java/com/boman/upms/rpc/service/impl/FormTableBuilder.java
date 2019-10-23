package com.boman.upms.rpc.service.impl;

import com.boman.upms.rpc.api.BomanFormService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.util.Map;

/**
 * 表单数据表生成
 */
public class FormTableBuilder {
    private String tablePrefix = "wf_form_";
    private String tableName = "";

    private BomanFormService bomanFormService;

    public FormTableBuilder(BomanFormService bomanFormService, String tableName){
        this.bomanFormService = bomanFormService;
        if(StringUtils.isEmpty(tableName)){
            this.tableName = tablePrefix + System.currentTimeMillis() + RandomUtils.nextInt(2);
        }else {
            this.tableName = tableName;
        }
    }

    /**
     * 删除表格
     */
    public boolean dropTable() {
        bomanFormService.executeSqlBatch("drop table if exists " + tableName);
        return true;
    }

    /**
     * 创建表格
     */
    public boolean createTable(Map<String, String> columnMapping) {
        if (this.dropTable()) {
            //生成-更新数据表
            if (!columnMapping.isEmpty()) {
                String sql = "create table " + tableName + " ( `id` int(11) NOT NULL AUTO_INCREMENT,";
                for (String key : columnMapping.keySet()) {
                    String tag = columnMapping.get(key);
                    if (tag.equals("textarea"))
                        sql += "`" + key + "`" + " text,";
                    else
                        sql += "`" + key + "`" + " varchar(100),";
                }
                sql += "PRIMARY KEY (`id`) )";
                //创建表
                bomanFormService.executeSqlBatch(sql);
            }
        }
        return true;
    }


    public String getTableName() {
        // tableName
        return tableName;
    }
}
