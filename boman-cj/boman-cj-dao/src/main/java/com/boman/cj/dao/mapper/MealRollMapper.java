package com.boman.cj.dao.mapper;



import com.boman.cj.dao.model.MealRoll;
import com.boman.upms.dao.model.UpmsUser;

import java.util.List;
import java.util.Map;

public interface MealRollMapper {

    /**
     * 查询表单列表
     * @param mealRoll
     */
    public List<MealRoll> selectByMealRollForOffsetPage(MealRoll mealRoll);

    /**
     * 查询表单总数
     * @param mealRoll
     */
    public long countByMealRoll(MealRoll mealRoll);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cj_meal_roll
     *
     * @mbg.generated Fri Oct 11 17:10:00 CST 2019
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cj_meal_roll
     *
     * @mbg.generated Fri Oct 11 17:10:00 CST 2019
     */
    int insert(MealRoll record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cj_meal_roll
     *
     * @mbg.generated Fri Oct 11 17:10:00 CST 2019
     */
    int insertSelective(MealRoll record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cj_meal_roll
     *
     * @mbg.generated Fri Oct 11 17:10:00 CST 2019
     */
    MealRoll selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cj_meal_roll
     *
     * @mbg.generated Fri Oct 11 17:10:00 CST 2019
     */
    int updateByPrimaryKeySelective(MealRoll record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cj_meal_roll
     *
     * @mbg.generated Fri Oct 11 17:10:00 CST 2019
     */
    int updateByPrimaryKey(MealRoll record);

    List<MealRoll> getByUserForMealRoll(UpmsUser user);

    public List<Map<Object, Object>> userOrg(UpmsUser upmsUser);

    List<MealRoll> getSpecialMealRoll(Map<Object, Object> params);
}