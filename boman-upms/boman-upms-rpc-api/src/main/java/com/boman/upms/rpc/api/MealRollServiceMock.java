/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: MealRoolServiceMock
 * Author:   Administrator
 * Date:     2019/10/14 11:55
 * Description: 餐卷降级
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.boman.upms.rpc.api;

import com.boman.upms.dao.model.MealRoll;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈餐卷降级〉
 *
 * @author yw
 * @create 2019/10/14
 * @since 1.0.0
 */
public class MealRollServiceMock implements MealRollService{
    @Override
    public List<MealRoll> selectByMealRollForOffsetPage(MealRoll mealRoll, int offset, int limit) {
        return null;
    }

    @Override
    public long countByMealRoll(MealRoll mealRoll) {
        return 0;
    }

    @Override
    public MealRoll createMealRoll(MealRoll mealRoll) {
        return null;
    }

    @Override
    public void executeSqlBatch(String sql) {

    }

    @Override
    public long saveForm(MealRoll mealRoll) {
        return 0;
    }

    @Override
    public MealRoll selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
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
