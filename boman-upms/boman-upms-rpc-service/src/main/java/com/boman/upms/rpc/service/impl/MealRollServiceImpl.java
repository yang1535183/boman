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
package com.boman.upms.rpc.service.impl;

import com.boman.common.db.DataSourceEnum;
import com.boman.common.db.DynamicDataSource;
import com.boman.upms.dao.mapper.MealRollMapper;
import com.boman.upms.dao.model.MealRoll;
import com.boman.upms.rpc.api.MealRollService;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
        PageHelper.offsetPage(offset, limit, false);
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
}
