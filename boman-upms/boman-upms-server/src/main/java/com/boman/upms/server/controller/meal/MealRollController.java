/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: mealRollAdd
 * Author:   Administrator
 * Date:     2019/10/11 16:03
 * Description: 餐卷管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.boman.upms.server.controller.meal;


import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.boman.common.util.ShiroUtils;
import com.boman.common.validator.LengthValidator;
import com.boman.common.validator.NotNullValidator;
import com.boman.upms.common.constant.UpmsResult;
import com.boman.upms.common.constant.UpmsResultConstant;
import com.boman.upms.dao.model.MealRoll;
import com.boman.upms.dao.model.UpmsUser;
import com.boman.upms.rpc.api.MealRollService;
import com.boman.upms.rpc.api.UpmsOrganizationService;
import com.boman.upms.rpc.api.UpmsUserOrganizationService;
import com.boman.upms.rpc.api.UpmsUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 〈餐卷管理〉
 *
 * @author yw
 * @create 2019/10/11
 * @since 1.0.0
 */

@Controller
@RequestMapping("/meal/mealRoll")
public class MealRollController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MealRollController.class);

    @Autowired
    private MealRollService mealRollService;

    @Autowired
    private UpmsUserService upmsUserService;

    @Autowired
    private UpmsOrganizationService upmsOrganizationService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        // 表单首页
        return "/meal/form/index.jsp";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
                       @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
                       @RequestParam(required = false, defaultValue = "", value = "search") String faceValue,
                       @RequestParam(required = false, value = "sort") String sort,
                       @RequestParam(required = false, value = "order") String order) {
        MealRoll mealRoll = new MealRoll();
        mealRoll.setFaceValue(faceValue);
        mealRoll.setOrder(order);
        mealRoll.setSort(sort);

        // 当前用户
        String loginName = ShiroUtils.getLoginName();
        UpmsUser upmsUser = upmsUserService.getUserByLoginName(loginName);
        mealRoll.setCreateBy(upmsUser);

        List<MealRoll> rows = mealRollService.selectByMealRollForOffsetPage(mealRoll, offset, limit);
        long total = mealRollService.countByMealRoll(mealRoll);
        Map<String, Object> result = new HashMap<>();
        result.put("rows", rows);
        result.put("total", total);
        return result;
    }

    @ApiOperation(value = "新增餐卷")
//    @RequiresPermissions("upms:user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(String id, String opt, ModelMap modelMap) {
        MealRoll mealRoll = mealRollService.selectByPrimaryKey(id);
        modelMap.put("mealRoll", mealRoll);
        modelMap.put("opt", opt);
        return "/meal/form/create.jsp";
    }

    @ApiOperation(value = "新增餐卷")
//    @RequiresPermissions("upms:user:create")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(MealRoll mealRoll) {
        ComplexResult result = FluentValidator.checkAll()
                .on(mealRoll.getFaceValue(), new LengthValidator(1, 20, "面值"))
                .on(mealRoll.getFaceValue(), new NotNullValidator("面值"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }

        // 当前用户
        String loginName = ShiroUtils.getLoginName();
        UpmsUser upmsUser = upmsUserService.getUserByLoginName(loginName);

        long time = System.currentTimeMillis();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.printf(mealRoll.toString());

        if (mealRoll.getId() != null && mealRoll.getId().toString() != "") {
            mealRollService.saveForm(mealRoll);
            LOGGER.info("修改餐卷，主键：id={}", mealRoll.getId());
        } else {

            //增加默认字段
            mealRoll.setCreateBy(upmsUser); // 创建者
            mealRoll.setCreateDate(new Date()); //创建时间
            mealRoll.setUpdateBy(upmsUser);     // 更新者
            mealRoll.setUpdateDate(new Date()); // 更新时间
            mealRoll.setDelFlag("0");   //是否删除（0:未删除 1:已删除）
            mealRoll.setReceive("0");//是否领用（0:未领用 1:已领用）
            mealRoll.setSpecial("0"); // 是否专用卷（0:否 1:是）
            String receiverUserIds[] = mealRoll.getReceiverUserIds().split(",");
            for (int i=0;i<receiverUserIds.length;i++) {
                UpmsUser user = new UpmsUser();
                user.setUserId(Integer.parseInt(receiverUserIds[i]));
                mealRoll.setReceiver(user);
                mealRoll = mealRollService.createMealRoll(mealRoll);
                if (null == mealRoll) {
                    return new UpmsResult(UpmsResultConstant.FAILED, "操作失败！");
                }
                LOGGER.info("新增餐卷，主键：id={}", mealRoll.getId());
            }
        }
        return new UpmsResult(UpmsResultConstant.SUCCESS, 1);
    }

    @ApiOperation(value = "删除餐卷")
//    @RequiresPermissions("upms:user:delete")
    @RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("ids") String ids) {
        int count = 0;
        count = mealRollService.deleteByPrimaryKey(ids);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @ApiOperation(value = "修改餐卷")
//    @RequiresPermissions("upms:user:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, ModelMap modelMap) {
        MealRoll mealRoll = mealRollService.selectByPrimaryKey(String.valueOf(id));
        modelMap.put("mealRoll", mealRoll);
        return "/meal/form/update.jsp";
    }

    @ApiOperation(value = "修改餐卷")
//    @RequiresPermissions("upms:user:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable("id") int id, MealRoll mealRoll) {
        ComplexResult result = FluentValidator.checkAll()
                .on(mealRoll.getFaceValue(), new LengthValidator(1, 20, "面值"))
                .on(mealRoll.getFaceValue(), new NotNullValidator("面值"))
                .doValidate()
                .result(ResultCollectors.toComplex());
        if (!result.isSuccess()) {
            return new UpmsResult(UpmsResultConstant.INVALID_LENGTH, result.getErrors());
        }
        Long count = mealRollService.saveForm(mealRoll);
        return new UpmsResult(UpmsResultConstant.SUCCESS, count);
    }

    @RequestMapping(value = "getMealRollById")
    @ResponseBody
    public MealRoll getMealRollById(String id) {
        return mealRollService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "用户选择")
    @RequestMapping(value = "chooseUser")
    public String chooseUser(HttpServletRequest request) {
        return "/meal/form/chooseUser.jsp";
    }

    @ApiOperation(value = "用户树数据")
    @RequestMapping(value = "userList")
    @ResponseBody
    public Object userList(HttpServletRequest request) {
        String loginName = ShiroUtils.getLoginName();
        UpmsUser loginUser = upmsUserService.getUserByLoginName(loginName);
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<Object,Object>> userMap = upmsUserService.userOrg(loginUser);
        // 默认根节点
        Map<Object,Object> map2 = new HashMap<Object, Object>();
        map2.put("id","0");
        map2.put("pid","-1");
        map2.put("username","用户");
        userMap.add(map2);
        map.put("userlist", userMap);
        return map;
    }
}
