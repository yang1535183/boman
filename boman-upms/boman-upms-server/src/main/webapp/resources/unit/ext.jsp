<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="list-group">
    <li class="list-group-item">
        <a href="javascript:;"> <span>宏控件</span> <i class="fd-view-drag ti-move pull-right"></i></a>
        <div class="view hide">
            <span fd-view="macros">{宏控件}</span>
        </div>
    </li>
    <li class="list-group-item">
        <a href="javascript:;"> <span>流程变量</span> <i class="fd-view-drag ti-move pull-right"></i></a>
        <div class="view hide">
            <span class="disabled" fd-view="variable">{流程变量}</span>
        </div>
    </li>
    <li class="list-group-item">
        <a href="javascript:;"> <span>列表控件</span> <i class="fd-view-drag ti-move pull-right"></i></a>
        <div class="view hide">
            <span class="form-control" fd-view="list">{列表控件}</span>
        </div>
    </li>
    <li class="list-group-item">
        <a href="javascript:;"> <span>提示控件</span> <i class="fd-view-drag ti-move pull-right"></i></a>
        <div class="view hide">
            <div class="alert alert-warning mt5" fd-view="alert">这是提示信息！！！</div>
        </div>
    </li>
</ul>