package com.boman.upms.dao.mapper;

import com.boman.upms.dao.model.BomanForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BomanFormMapper {

    /**
     * 查询表单列表
     * @param bomanForm
     */
    public List<BomanForm> selectByBomanFormForOffsetPage(BomanForm bomanForm);

    /**
     * 查询表单总数
     * @param bomanForm
     */
    public long countByBomanForm(BomanForm bomanForm);

    /**
     *  执行原生的sql
     * @param sql
     */
    public void executeSqlBatch(@Param("sql") String sql);

    public long saveForm(BomanForm bomanForm);

    /**
     * selectByPrimaryKey
     * @param id
     */
    public BomanForm selectByPrimaryKey(Integer id);

    /**
     * 删除记录
     */
    public void deleteByPrimaryKey(Integer id);

    /**
     *  更新记录
     * @param bomanForm
     */
    public void updateForm(BomanForm bomanForm);
}
