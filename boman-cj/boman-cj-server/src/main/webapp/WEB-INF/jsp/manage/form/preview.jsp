<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html class="no-js" lang="zh_CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
    <title>可视化表单预览</title>
    <link href="/resources/zheng-admin/images/logo_favicon.ico" rel="shortcut icon">
    <link rel="stylesheet" href="/resources/form/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/form/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/form/css/themify-icons.css">
    <link rel="stylesheet" href="/resources/form/css/simple-line-icons.css">
    <link rel="stylesheet" href="/resources/form/css/animate.min.css">
    <link rel="stylesheet" href="/resources/form/css/skins/palette.css">
    <link rel="stylesheet" href="/resources/form/toastr/toastr.min.css">
    <link rel="stylesheet" href="/resources/form/css/main.css">
    <%--<link rel="stylesheet" href="/resources/form/js/main.css">--%>
    <link rel="stylesheet" href="/resources/form/css/colorpicker.css">
    <link rel="stylesheet" href="/resources/form/select2/select2.css">
    <link rel="stylesheet" href="/resources/form/css/run.css"/>
</head>
<body>

<div class="app">
    <section class="layout">
        <!-- 内容主体 -->
        <section class="main-content">
            <header class="header navbar">
                <div class="btn-group tool-button">
                    <button type="button" class="btn btn-default navbar-btn" onclick="$('form').submit();"><i class="fa fa-bug"></i> 测试提交</button>
                    <a href="$!basePath/bi/form/datalist/$!form.id" target="_blank" class="btn btn-primary navbar-btn"><i class="fa fa-database"></i> 表单数据显示</a>
                </div>
                <div class="pull-right">
                    <button type="button" class="btn btn-default navbar-btn" onclick="window.close()"><i class="fa fa-close"></i> 关闭</button>
                </div>
            </header>
            <div class="content-wrap">
                <div class="wrapper no-p bg-default">
                    <div class="row">
                        <div class="col-lg-1"></div>
                        <div class="col-lg-10 bordered mt20 pt20 pb20 bg-white">
                            ${html}
                        </div>
                        <div class="col-lg-1"></div>
                    </div>
                </div>
                <a class="exit-offscreen"></a>
            </div>
        </section>
    </section>
</div>

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

<!-- 页面组件 -->
<script type="text/javascript" src="/resources/form/js/jquery.form.js"></script>

<!-- 本页面脚本 -->
<script type="text/javascript" src="/resources/form/js/formdesign.run.js"></script>
<script type="text/javascript" src="/resources/form/js/formdesign.validator.js"></script>

<script type="text/javascript">
    $(function(){
        fd.run.init();
    });
</script>

</body>
</html>
