<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="list-group" style="background-color: gainsboro;">
    <li class="list-group-item">
        <a href="javascript:;"> <span>标签+文本框</span> <i class="fd-view-drag ti-move pull-right"></i></a>
        <div class="view hide">
            <table fd-move fd-view="label_input" id="v_label_input" style="width:100%">
                <tr>
                    <td fd-view="table_td" style="min-width:5%;width:8%"><span contenteditable="true" fd-view="label" fd-label>标签：</span></td>
                    <td fd-view="table_td"><input type="text" class="form-control" fd-view="input" field=""/></td>
                </tr>
            </table>
        </div>
    </li>
    <li class="list-group-item">
        <a href="javascript:;"> <span>标签+密码框</span> <i class="fd-view-drag ti-move pull-right"></i></a>
        <div class="view hide">
            <table fd-move fd-view="label_password" id="v_label_password" style="width:100%">
                <tr>
                    <td fd-view="table_td" style="min-width:5%;width:8%"><span contenteditable="true" fd-view="label" fd-label>标签：</span></td>
                    <td fd-view="table_td"><input type="text" class="form-control" fd-view="password" field=""/></td>
                </tr>
            </table>
        </div>
    </li>
    <li class="list-group-item">
        <a href="javascript:;"> <span>标签+多行文本</span> <i class="fd-view-drag ti-move pull-right"></i></a>
        <div class="view hide">
            <table fd-move fd-view="label_textarea" id="v_label_textarea" style="width:100%">
                <tr>
                    <td fd-view="table_td" style="min-width:5%;width:8%"><span contenteditable="true" fd-view="label" fd-label>标签：</span></td>
                    <td fd-view="table_td"><textarea class="form-control" fd-view="textarea" field=""></textarea></td>
                </tr>
            </table>
        </div>
    </li>
    <li class="list-group-item">
        <a href="javascript:;"> <span>标签+单选下拉</span> <i class="fd-view-drag ti-move pull-right"></i></a>
        <div class="view hide">
            <table fd-move fd-view="label_textarea" id="v_label_textarea" style="width:100%">
                <tr>
                    <td fd-view="table_td" style="min-width:5%;width:8%"><span contenteditable="true" fd-view="label" fd-label>标签：</span></td>
                    <td fd-view="table_td">
                        <select class="form-control" fd-view="select" field="">
                            <option>选项一</option>
                            <option>选项二</option>
                        </select>
                    </td>
                </tr>
            </table>
        </div>
    </li>
    <li class="list-group-item">
        <a href="javascript:;"> <span>单选按钮组</span> <i class="fd-view-drag ti-move pull-right"></i></a>
        <div class="view hide">
            <div fd-move fd-view="radio_group" id="v_label_input">
                #foreach($!i in [1..3])
                <label class="radio-inline">
                    <input type="radio" name="radio1" fd-view="radio" id="v_radio" field="">
                    <span fd-view="label" contenteditable="true">名称</span>
                </label>
                #end
            </div>
        </div>
    </li>
    <li class="list-group-item">
        <a href="javascript:;"> <span>复选按钮组</span> <i class="fd-view-drag ti-move pull-right"></i></a>
        <div class="view hide">
            <div fd-move fd-view="checkbox_group" id="v_checkbox_input">
                #foreach($!i in [1..3])
                <label class="checkbox-inline">
                    <input type="checkbox" name="checkbox1" fd-view="checkbox" id="v_checkbox" field="">
                    <span fd-view="label" contenteditable="true">名称</span>
                </label>
                #end
            </div>
        </div>
    </li>
    <li class="list-group-item">
        <a href="javascript:;"> <span>日期区间</span> <i class="fd-view-drag ti-move pull-right"></i></a>
        <div class="view hide">
            <div fd-view="datepickerrange" id="v_datepickerrange">
                <input id="start" type="text" fd-view="datepicker" class="form-control inline datepicker" style="width:50%" data-date-format="yyyy-mm-dd"> -
                <input id="end" type="text" fd-view="datepicker" class="form-control inline datepicker" style="width:50%" data-date-format="yyyy-mm-dd">
            </div>
        </div>
    </li>
    <li class="list-group-item">
        <a href="javascript:;"> <span>金额区间</span> <i class="fd-view-drag ti-move pull-right"></i></a>
        <div class="view hide">
            <div fd-view="moneyrange" id="v_moneyrange">
                <input id="start" type="text" fd-view="input" class="form-control inline" style="width:40%;text-align:right;" placeholder="￥" rules="money" data="{'rules':['money'],'css_align':'right'}" field=""> -
                <input id="end" type="text" fd-view="input" class="form-control inline" style="width:40%;text-align:right;" placeholder="￥" rules="money" data="{'rules':['money'],'css_align':'right'}" field="">
            </div>
        </div>
    </li>
    <li class="list-group-item">
        <a href="javascript:;"> <span>动态下拉表格</span> <i class="fd-view-drag ti-move pull-right"></i></a>
        <div class="view hide">
            <div fd-view="dynamictable" id="v_dynamictable" class="input-group inline">
                <input type="text" class="form-control inline" readonly="readonly">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button">选择</button>
                </span>
            </div>
        </div>
    </li>
</ul>