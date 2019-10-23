<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="form-group" fd-property="datalist">
    <label>列表数据</label>
    <button class="btn btn-info form-control">数据设置</button>
</div>
<div class="form-group" fd-property="remote">
    <label>远程数据</label>
    <textarea name="remote" class="form-control" placeholder="输入数据接口地址"></textarea>
</div>
<div class="form-group hide" fd-property="table_rows">
    <label>行数</label> <input type="number" name="controlTableRows" class="form-control" placeholder="数字"/>
</div>
<div class="form-group hide" fd-property="table_cols">
    <label>列数</label> <input type="number" name="controlTableCols" class="form-control" placeholder="数字"/>
</div>
<div class="form-group hide" fd-property="table_header_show">
    <label>表头显示</label>
    <select class="form-control" name="controlRequired">
        <option value="true">是</option>
        <option value="false">否</option>
    </select>
</div>
<div class="form-group hide" fd-property="editable">
    <label>可编辑</label>
    <select class="form-control" name="controlRequired">
        <option value="true">是</option>
        <option value="false">否</option>
    </select>
</div>
<div class="form-group" fd-property="macros">
    <label>宏类型</label> <select class="form-control" name="controlMacros">
    <optgroup label="日期宏控件">
        <option value="dt_yyyy-MM-dd HH:mm">当前日期+时间[1992-12-01 12:00]</option>
        <option value="dt_yyyy-MM-dd HH:mm:ss">当前日期+时间[1992-12-01 12:00:00]</option>
        <option value="dt_yyyy-MM-dd">当前日期[1997-01-01]</option>
        <option value="dt_yyyy年MM月dd日">当前日期[1997年01月01日]</option>
        <option value="dt_yyyy年MM月">当前日期[1997年01月]</option>
        <option value="dt_MM月dd日">当前日期[01月01日]</option>
        <option value="dt_yyyy">当前年份[1992]</option>
        <option value="dt_yyyy年">当前年份[1992年]</option>
        <option value="dt_HH:mm">当前时间[12:00]</option>
        <option value="dt_HH:mm:ss">当前时间[12:00:00]</option>
        <option value="dt_week">当前星期[星期一]</option>
    </optgroup>
    <optgroup label="常用参数">
        <option value="user_id">当前用户ID</option>
        <option value="user_realname">当前用户姓名</option>
        <option value="user_unitname">当前用户部门名称</option>
    </optgroup>
</select>
</div>
<div class="form-group" fd-property="script">
    <!-- 未完成 -->
    <label>脚本</label>
    <textarea name="script" class="form-control" placeholder="要执行的脚本"></textarea>
</div>
<div class="form-group hide" fd-property="datasource">
    <label>数据源</label>
    <select class="form-control" name="controlRequired">
        <option value="">无</option>
        <%--#foreach($!obj in $!dsList)
        <option value="$!obj.id">$!obj.name</option>
        #end--%>
    </select>
</div>