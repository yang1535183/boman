<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<style>
    .crudDialog{
        margin: 0;
        padding: 0;
    }
    .container{
        width: 80%;
        height: auto;
        margin: 20px 30px;
    }
    .container .title{
        padding-top: 20px;
        text-align: center;
    }
    .container .title h3{
        font-weight: normal;
    }
    .time{
        float: right;
        font-size: 12px;
        color: #888;

    }
    .clear{
        clear: both;
    }
    .content{
        padding-top: 20px;
        font-size: 16px;
        text-indent: 4px;
    }
</style>
<div id="createDialog" class="crudDialog">
    <div class="container">
        <div class="title"><h3>${messageText.tittle}</h3></div>
        <div class="time">
            日期：<span id="time">${messageText.ctime}</span>
        </div>
        <div class="content clear">${messageText.message}</div>
    </div>
</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
    $(function () {
        var value = $("#time").text();
        $("#time").text(format(parseInt(value)));
    })

    function add0(m){return m<10?'0'+m:m }

    function format(shijianchuo)
    {
        var time = new Date(shijianchuo);
        var y = time.getFullYear();
        var m = time.getMonth()+1;
        var d = time.getDate();
        var h = time.getHours();
        var mm = time.getMinutes();
        var s = time.getSeconds();
        return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
    }
</script>
