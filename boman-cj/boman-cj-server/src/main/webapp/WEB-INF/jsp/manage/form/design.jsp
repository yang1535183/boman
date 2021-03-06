<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html class="no-js" lang="zh_CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
    <title>可视化表单设计器</title>
    <link href="/resources/zheng-admin/images/logo_favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="/resources/form/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/form/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/form/css/themify-icons.css">
    <link rel="stylesheet" href="/resources/form/css/simple-line-icons.css">
    <link rel="stylesheet" href="/resources/form/css/animate.min.css">
    <link rel="stylesheet" href="/resources/form/css/skins/palette.css">
    <link rel="stylesheet" href="/resources/form/toastr/toastr.min.css">
    <link rel="stylesheet" href="/resources/form/css/main.css">
    <link rel="stylesheet" href="/resources/form/js/main.css">
    <link rel="stylesheet" href="/resources/form/css/colorpicker.css">
    <link rel="stylesheet" href="/resources/form/select2/select2.css">
</head>
<body>
<div class="app layout">
    <input value="${form.id}" hidden id="formId">
    <section class="layout">
        <!-- 内容主体 -->
        <section class="main-content bg-white">
            <header class="header navbar">
                <div class="btn-group navbar-btn">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-table"></i> 表格操作 <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="javascript:;" onclick="fd.design.table.merge()"><i class="fa fa-columns"></i> 合并单元格</a></li>
                        <li><a href="javascript:;" onclick="fd.design.table.insertRow('up')"><i class="fa fa-th"></i> 上方增加一行</a></li>
                        <li><a href="javascript:;" onclick="fd.design.table.insertRow('down')"><i class="fa fa-th"></i> 下方增加一行</a></li>
                        <li><a href="javascript:;" onclick="fd.design.table.insertCol('left')"><i class="ti-layout-column2"></i> 左侧增加一列</a></li>
                        <li><a href="javascript:;" onclick="fd.design.table.insertCol('right')"><i class="ti-layout-column2"></i> 右侧增加一列</a></li>
                    </ul>
                </div>
                <div class="btn-group navbar-btn">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-retweet"></i> 切换模式 <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li id="btnEditMode"><a href="javascript:;" onclick="fd.design.editMode('#editMode')"><i class="fa fa-code"></i> 源码编辑模式</a></li>
                        <li id="btnEditScript" class="hide"><a href="javascript:;" onclick="fd.design.editScript('#editScript')"><i class="fa fa-bug"></i> 脚本编辑模式</a></li>
                        <!-- 未完成 -->
                        <li id="btnEnableDrag"><a href="javascript:;" onclick="fd.design.enabledrag('#btnEnableDrag','#btnDisableDrag')"><i class="fa fa-arrows"></i> 开启控件移动</a></li>
                        <li id="btnDisableDrag" class="hide"><a href="javascript:;" onclick="fd.design.disabledrag('#btnEnableDrag','#btnDisableDrag')"><i class="fa fa-arrows"></i>
                            关闭控件移动</a></li>
                    </ul>
                </div>
                <div class="btn-group navbar-btn">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-arrows"></i> 移动布局 <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="javascript:;" onclick="fd.design.move('up')"><i class="fa fa-arrow-circle-up"></i> 向上(左)移动</a></li>
                        <li><a href="javascript:;" onclick="fd.design.move('down')"><i class="fa fa-arrow-circle-down"></i> 向下(右)移动</a></li>
                    </ul>
                </div>
                <div class="pull-right">
                    <button type="button" class="btn btn-default navbar-btn" onclick="fd.design.remove()">
                        <i class="fa fa-trash"></i> 删除选中
                    </button>
                    <button type="button" class="btn btn-default navbar-btn" onclick="fd.design.save()">
                        <i class="fa fa-save"></i> 保存
                    </button>
                    <div class="btn-group navbar-btn">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            更多 <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu pull-right">
                            <li><a href="javascript:;" onclick="fd.design.redo()"><i class="fa fa-rotate-left"></i> 重做</a></li>
                            <!--<li><a href="javascript:;" onclick="fd.design.preview()"><i class="fa fa-eye"></i> 预览</a></li>-->
                            <li><a href="javascript:;" onclick="fd.design.saveAndPreview()"><i class="fa fa-eye"></i> 保存并预览</a></li>
                        </ul>
                    </div>
                    <button id="btnShowProp" type="button" class="btn btn-default navbar-btn hide" onclick="fd.design.showProperties(this, '#btnHideProp', '#asidePanel')">
                        <i class="ti-angle-double-left"></i> 属性面板
                    </button>
                    <button id="btnHideProp" type="button" class="btn btn-default navbar-btn" onclick="fd.design.hideProperties(this, '#btnShowProp', '#asidePanel')">
                        属性面板 <i class="ti-angle-double-right"></i>
                    </button>
                </div>
            </header>
            <div class="content-wrap">
                <div class="wrapper bg-default" style="padding-left: 0px;padding-right: 0px;">
                    <div class="row">
                        <div class="col-lg-2">
                            <div class="panel">
                                <div class="panel-heading"><i class="fa fa-th-large"></i> 控件面板</div>
                                <div class="panel-body" style="min-height: 630px;">
                                    <section class="panel bg-none">
                                        <div class="panel-group" id="accordionControl">
                                            <div class="panel bordered">
                                                <div class="panel-heading"><a data-toggle="collapse" data-parent="#accordionControl" href="#panelLayout" aria-expanded="false" class="collapsed"><i
                                                        class="fa fa-trello"></i> 布局控件 </a></div>
                                                <div id="panelLayout" class="panel-collapse collapse" aria-expanded="false">
                                                    <div class="panel-body">
                                                        <%--#parse("bi/form/control/layout.html")--%>
                                                        <jsp:include page="/resources/unit/layout.jsp"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel bordered">
                                                <div class="panel-heading"><a data-toggle="collapse" data-parent="#accordionControl" href="#panelBase" aria-expanded="false" class="collapsed"><i
                                                        class="fa fa-trello"></i> 基本控件 </a></div>
                                                <div id="panelBase" class="panel-collapse collapse" aria-expanded="false">
                                                    <div class="panel-body">
                                                        <%--#parse("bi/form/control/base.html")--%>
                                                        <jsp:include page="/resources/unit/base.jsp"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel bordered">
                                                <div class="panel-heading"><a data-toggle="collapse" data-parent="#accordionControl" href="#panelGroup" aria-expanded="false" class="collapsed"><i
                                                        class="fa fa-trello"></i> 组合控件 </a></div>
                                                <div id="panelGroup" class="panel-collapse collapse" aria-expanded="false">
                                                    <div class="panel-body">
                                                        <%--#parse("bi/form/control/group.html")--%>
                                                        <jsp:include page="/resources/unit/group.jsp"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel bordered">
                                                <div class="panel-heading"><a data-toggle="collapse" data-parent="#accordionControl" href="#panelExt" aria-expanded="false" class="collapsed"><i
                                                        class="fa fa-trello"></i> 扩展控件 </a></div>
                                                <div id="panelExt" class="panel-collapse collapse" aria-expanded="false">
                                                    <div class="panel-body">
                                                        <%--#parse("bi/form/control/ext.html")--%>
                                                        <jsp:include page="/resources/unit/ext.jsp"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-8">
                            <div class="panel">
                                <div class="panel-heading"><i class="fa fa-code"></i> 表单设计区域</div>
                                <div class="panel-body">
                                    <%--#if($!form) $!form.source #else
                                    <div class="containers fd-view-drag-in">
                                        <div class="hide" id="script"></div>
                                    </div>
                                    #end--%>
                                    <c:choose>
                                        <c:when test="${form != null}">
                                            ${form.source}
                                        </c:when>
                                        <c:otherwise>
                                            <div class="containers fd-view-drag-in">
                                                <div class="hide" id="script"></div>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>

                                    <form id="previewForm" action="$!basePath/bi/form/design/preview" method="post" target="_blank">
                                        <textarea name="html" class="hide"></textarea>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-2" id="asidePanel">
                            <div class="panel">
                                <div class="panel-heading"><i class="fa fa-magic"></i> 属性面板</div>
                                <div class="panel-body" style="min-height: 630px;">
                                    <div class="nav-tabs-custom bg-white no-shadow">
                                        <ul class="nav nav-tabs nav-tabs nav-justified" role="tablist" id="propertyTab">
                                            <li class="active"><a href="#pro_content1" data-toggle="tab">表单属性</a></li>
                                            <li><a href="#pro_content2" data-toggle="tab">控件属性</a></li>
                                        </ul>
                                        <div class="tab-content no-p">
                                            <div class="tab-pane fade active in p15" id="pro_content1">
                                                <%--#parse("bi/form/property/form.html")--%>
                                                <jsp:include page="/resources/unit/form.jsp"/>
                                            </div>
                                            <div class="tab-pane fade pt15 pl5 pr5" id="pro_content2">
                                                <div class="properties">
                                                    <div class="panel-group" id="accordionProperty">
                                                        <div class="panel">
                                                            <div class="panel-heading">
                                                                <a data-toggle="collapse" data-parent="#accordionProperty" href="#c1" class="collapsed"><i class="ti-menu-alt"></i> 常规</a>
                                                            </div>
                                                            <div id="c1" class="panel-collapse collapse">
                                                                <div class="panel-body bg-default">
                                                                    <%--#parse("bi/form/property/general.html")--%>
                                                                    <jsp:include page="/resources/unit/general.jsp"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="panel">
                                                            <div class="panel-heading">
                                                                <a data-toggle="collapse" data-parent="#accordionProperty" href="#c2"><i class="ti-layout-slider"></i> 文本/密码</a>
                                                            </div>
                                                            <div id="c2" class="panel-collapse collapse">
                                                                <div class="panel-body bg-default">
                                                                    <%--#parse("bi/form/property/text.html")--%>
                                                                    <jsp:include page="/resources/unit/text.jsp"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="panel">
                                                            <div class="panel-heading">
                                                                <a data-toggle="collapse" data-parent="#accordionProperty" href="#c3"><i class="ti-check-box"></i> 单选/复选框</a>
                                                            </div>
                                                            <div id="c3" class="panel-collapse collapse">
                                                                <div class="panel-body bg-default">
                                                                    <%--#parse("bi/form/property/checkbox.html")--%>
                                                                    <jsp:include page="/resources/unit/checkbox.jsp"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="panel">
                                                            <div class="panel-heading">
                                                                <a data-toggle="collapse" data-parent="#accordionProperty" href="#c4"><i class="ti-palette"></i> 样式</a>
                                                            </div>
                                                            <div id="c4" class="panel-collapse collapse">
                                                                <div class="panel-body bg-default">
                                                                    <%--#parse("bi/form/property/style.html")--%>
                                                                    <jsp:include page="/resources/unit/style.jsp"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="panel">
                                                            <div class="panel-heading">
                                                                <a data-toggle="collapse" data-parent="#accordionProperty" href="#c5"><i class="ti-info-alt"></i> CSS/辅助功能</a>
                                                            </div>
                                                            <div id="c5" class="panel-collapse collapse">
                                                                <div class="panel-body bg-default">
                                                                    <%--#parse("bi/form/property/css.html")--%>
                                                                    <jsp:include page="/resources/unit/css.jsp"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="panel">
                                                            <div class="panel-heading">
                                                                <a data-toggle="collapse" data-parent="#accordionProperty" href="#c6"><i class="ti-lock"></i> 验证规则</a>
                                                            </div>
                                                            <div id="c6" class="panel-collapse collapse">
                                                                <div class="panel-body bg-default">
                                                                    <%--#parse("bi/form/property/validator.html")--%>
                                                                    <jsp:include page="/resources/unit/validator.jsp"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="panel">
                                                            <div class="panel-heading">
                                                                <a data-toggle="collapse" data-parent="#accordionProperty" href="#c7"><i class="ti-more"></i> 其他</a>
                                                            </div>
                                                            <div id="c7" class="panel-collapse collapse">
                                                                <div class="panel-body bg-default">
                                                                    <%--#parse("bi/form/property/other.html")--%>
                                                                    <jsp:include page="/resources/unit/other.jsp"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 hide">
                            <div class="alert alert-warning m5">
                                按住Ctrl键可多选，按照Alt键选择整个控件
                            </div>
                        </div>
                    </div>
                </div>
                <a class="exit-offscreen"></a>
            </div>
        </section>
    </section>
</div>
<jsp:include page="/resources/unit/list.jsp"/>
<jsp:include page="/resources/unit/editMode.jsp"/>
<jsp:include page="/resources/unit/editScript.jsp"/>
    <script type="text/javascript" src="/resources/form/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="/resources/form/js/jquery.slimscroll.min.js"></script>
    <script type="text/javascript" src="/resources/form/js/modernizr.js"></script>
    <script type="text/javascript" src="/resources/form/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="/resources/form/js/vue.min.js"></script>
    <script type="text/javascript" src="/resources/form/toastr/toastr.min.js"></script>
    <script type="text/javascript" src="/resources/form/js/jquery.dragg.min.js"></script>
    <script type="text/javascript" src="/resources/form/js/mod_codebeautify.js"></script>
    <script type="text/javascript" src="/resources/form/js/bootstrap-colorpicker.js"></script>
    <script type="text/javascript" src="/resources/form/select2/select2.min.js"></script>

    <script type="text/javascript" src="/resources/form/js/formdesign.js"></script>
    <script type="text/javascript" src="/resources/form/js/formdesign.config.js"></script>
    <script type="text/javascript" src="/resources/form/js/formdesign.design.js"></script>

    <script type="text/javascript" src="/resources/form/js/main.js"></script>
    <script type="text/javascript" src="/resources/form/view/theme.js"></script>
    <script type="text/javascript" src="/resources/form/view/tip.js"></script>
    <script type="text/javascript" src="/resources/form/view/toast.js"></script>

    <script type="text/javascript">
        $(function () {
            fd.design.init();
        });
        //保存form到服务器
        var _form = {
            id: $('#formId').val()
        };
        function preview() {
            if (!window.winPreview || window.winPreview.closed) {
                window.winPreview = window.open("$!basePath/bi/form/design/preview/" + _form.id);
            } else {
                window.winPreview.location.reload();
            }
        }
        function saveForm(callback) {
            var form = $(fd.config.containerPanel);
            if (form.children().length == 1) {
                console.log(_form.id.length);
                return ns.tip.alert("请先添加视图控件到表单容器中");
            }
            if (_form.id.length == 0) {
                ns.tip.prompt("请输入表单名称", function (name) {
                    _saveForm(callback, true, name);
                });
            } else {
                _saveForm(callback, true, "");
            }
        }
        var timer = undefined;
        function _saveForm(callback, showTip, name) {
            ns.showLoadingbar();
            var html = fd.design._html();
            jQuery.post("/manage/form/design/save", {
                html: html,
                name: name,
                id: _form.id
            }, function (data) {
                if (data.success == true) {
                    _form.id = data.id;
                    if (showTip)
                        ns.tip.toast.success("保存成功！");
                    if (!timer)//定时5分钟保存一次
                        timer = setInterval(function () {
                            _saveForm(null, false, "");
                        }, 1000 * 60 * 5);
                } else {
                    ns.tip.toast.error(data.message);
                }
                ns.closeLoadingbar();
                if (typeof (callback) == "function")
                    callback();
            });
        }
    </script>
</body>
</html>
