package com.boman.upms.rpc.api;

import com.boman.upms.dao.model.MealRoll;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by jys on 2019/10/11
 */
public interface MealRollService {

    /**
     * 查询餐卷列表
     * @param mealRoll
     * @param offset
     * @param limit
     */
    public List<MealRoll> selectByMealRollForOffsetPage(MealRoll mealRoll, int offset, int limit);

    /**
     *  餐卷总数
     * @param mealRoll
     */
    public long countByMealRoll(MealRoll mealRoll);

    /**
     *  新增餐卷
     * @param mealRoll
     */
    public MealRoll createMealRoll(MealRoll mealRoll);

    /**
     *  MyBatis 执行原生sql语句
     * @param sql
     */
    public void executeSqlBatch(String sql);

    /**
     * 保存表单
     * @param mealRoll
     */
    public long saveForm(MealRoll mealRoll);

    /**
     * 根据主键查询实体类
     * @param id
     */
    public MealRoll selectByPrimaryKey(String id);

    /**
     *  删除记录
     * @param id
     */
    public int deleteByPrimaryKey(String id);

    /**
     * 获取可运行的表单html
     * @param id
     * @param tid
     * @param start
     */
    public String selectHtmlRuntime(String id, String tid, String start, String name);

    /**
     * 表单数据提交
     * @param data
     */
    public JSONObject submit(String data);
}
