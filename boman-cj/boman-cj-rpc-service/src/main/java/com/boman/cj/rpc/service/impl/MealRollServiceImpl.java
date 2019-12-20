/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MealRollServiceImpl
 * Author:   Administrator
 * Date:     2019/10/12 10:59
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.boman.cj.rpc.service.impl;

import com.boman.cj.dao.model.MealRoll;
import com.boman.cj.dao.mapper.MealRollMapper;
import com.boman.cj.rpc.api.MealRollService;
import com.boman.common.db.DataSourceEnum;
import com.boman.common.db.DynamicDataSource;
import com.boman.upms.dao.model.UpmsUser;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author yw
 * @create 2019/10/12
 * @since 1.0.0
 */
@Service
@Transactional
public class MealRollServiceImpl implements MealRollService {

    @Autowired
    private MealRollMapper mealRollMapper;

    @Override
    public List<MealRoll> selectByMealRollForOffsetPage(MealRoll mealRoll, int offset, int limit) {
        List<MealRoll> list = mealRollMapper.selectByMealRollForOffsetPage(mealRoll);
        return list;
    }

    @Override
    public long countByMealRoll(MealRoll mealRoll) {
        DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
        long count = mealRollMapper.countByMealRoll(mealRoll);
        return count;
    }

    @Override
    public MealRoll createMealRoll(MealRoll mealRoll) {
        mealRollMapper.insert(mealRoll);
        return mealRoll;
    }

    @Override
    public void executeSqlBatch(String sql) {

    }

    @Override
    public long saveForm(MealRoll mealRoll) {

        return  mealRollMapper.updateByPrimaryKeySelective(mealRoll);
    }

    @Override
    public MealRoll selectByPrimaryKey(String id) {

        return mealRollMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(String id) {

        return mealRollMapper.deleteByPrimaryKey(id);
    }

    @Override
    public String selectHtmlRuntime(String id, String tid, String start, String name) {
        return null;
    }

    @Override
    public JSONObject submit(String data) {
        return null;
    }

    /**
     * 通过用户获取用户所拥有的餐券
     * @param user
     */
    @Override
    public List<MealRoll> getByUserForMealRoll(UpmsUser user){
        List<MealRoll> list = mealRollMapper.getByUserForMealRoll(user);
        return  list;
    }

    @Override
    public List<Map<Object, Object>> userOrg(UpmsUser upmsUser) {
        return mealRollMapper.userOrg(upmsUser);
    }

    @Override
    public List<MealRoll> getSpecialMealRoll(Map<Object, Object> params) {
        return mealRollMapper.getSpecialMealRoll(params);
    }
}
