<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>消息查看</title>
	<jsp:include page="/resources/inc/head.jsp" flush="true"/>
	<style>
		.title{
			display: inline-block;
			width: 100%;
			text-align: center;
			cursor: pointer;
			white-space: nowrap;
			overflow: hidden;
			text-overflow: ellipsis;
		}
	</style>
</head>
<body>
<div id="main">
	<div id="toolbar">
		<shiro:hasPermission name="upms:log:delete"><a class="waves-effect waves-button" href="javascript:;" onclick="deleteAction()"><i class="zmdi zmdi-close"></i> 删除消息</a></shiro:hasPermission>
	</div>
	<table id="table"></table>
</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>

<script>
    var $table = $('#table');
    $(function() {
        // bootstrap table初始化
        $table.bootstrapTable({
            url: '${basePath}/manage/message/list',
            height: getHeight(),
            striped: true,
            search: true,
            showRefresh: true,
            showColumns: true,
            minimumCountColumns: 2,
            clickToSelect: true,
            detailView: false,
            detailFormatter: 'detailFormatter',
            pagination: true,
            paginationLoop: false,
            sidePagination: 'server',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            idField: 'id',
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'ck', checkbox: true},
                {field: 'id', title: '序号', sortable: false, align: 'center',width:'10%'},
                {field: 'tittle', title: '标题',formatter:'tittleFormatter',width:'40%'},
                {field: 'sendName', title: '发送人'},
                {field: 'status', title: '状态', formatter: 'lockedFormatter'},
            ]
        });
    });

    function tittleFormatter(value, row, index) {
        if(row.status == '1'){
            return '<span class="title" onclick="viewTips('+ row.id +')">' + row.tittle + '</span>';
		}
		return '<span class="title" onclick="removeTips('+ row.id +')">' + row.tittle + '</span>';
    }

    function lockedFormatter(value, row, index) {
        if (value == 1) {
            return '<span class="label label-default">已读</span>';
        } else {
            return '<span class="label label-success">未读</span>';
        }
    }

    // 删除
    var deleteDialog;
    function deleteAction() {
        var rows = $table.bootstrapTable('getSelections');
        if (rows.length == 0) {
            $.confirm({
                title: false,
                content: '请至少选择一条记录！',
                autoClose: 'cancel|3000',
                backgroundDismiss: true,
                buttons: {
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        } else {
            deleteDialog = $.confirm({
                type: 'red',
                animationSpeed: 300,
                title: false,
                content: '确认删除该系统吗？',
                buttons: {
                    confirm: {
                        text: '确认',
                        btnClass: 'waves-effect waves-button',
                        action: function () {
                            var ids = new Array();
                            for (var i in rows) {
                                ids.push(rows[i].logId);
                            }
                            $.ajax({
                                type: 'get',
                                url: '${basePath}/manage/log/delete/' + ids.join("-"),
                                success: function(result) {
                                    if (result.code != 1) {
                                        if (result.data instanceof Array) {
                                            $.each(result.data, function(index, value) {
                                                $.confirm({
                                                    theme: 'dark',
                                                    animation: 'rotateX',
                                                    closeAnimation: 'rotateX',
                                                    title: false,
                                                    content: value.errorMsg,
                                                    buttons: {
                                                        confirm: {
                                                            text: '确认',
                                                            btnClass: 'waves-effect waves-button waves-light'
                                                        }
                                                    }
                                                });
                                            });
                                        } else {
                                            $.confirm({
                                                theme: 'dark',
                                                animation: 'rotateX',
                                                closeAnimation: 'rotateX',
                                                title: false,
                                                content: result.data.errorMsg,
                                                buttons: {
                                                    confirm: {
                                                        text: '确认',
                                                        btnClass: 'waves-effect waves-button waves-light'
                                                    }
                                                }
                                            });
                                        }
                                    } else {
                                        deleteDialog.close();
                                        $table.bootstrapTable('refresh');
                                    }
                                },
                                error: function(XMLHttpRequest, textStatus, errorThrown) {
                                    $.confirm({
                                        theme: 'dark',
                                        animation: 'rotateX',
                                        closeAnimation: 'rotateX',
                                        title: false,
                                        content: textStatus,
                                        buttons: {
                                            confirm: {
                                                text: '确认',
                                                btnClass: 'waves-effect waves-button waves-light'
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        }
    }

    function removeTips(id) {
        var child = parent.document.getElementById("wxList"+id);
        child.parentNode.removeChild(child);

        var wxList = parent.document.getElementById("message_count").innerHTML;
        parent.document.getElementById("message_count").innerHTML = parseInt(wxList)-1;
        parent.document.getElementById("notification-count").innerHTML = parseInt(wxList)-1;
        setTimeout(dialog(id),300);
        /*var updateDialog = $.dialog({
            animationSpeed: 300,
            resizable:true,
            autoOpen: false,
            width:600,
            modal: true,
            title: '查看详情',
            content: 'url:${basePath}/manage/message/view/' + id,
            onContentReady: function () {
                initMaterialInput();
            }
        });*/
    }

    function viewTips(id) {
        setTimeout(dialog(id),50);
    }

    function dialog(id) {
		window.location.href = '${basePath}/manage/message/view/' + id;
    }
</script>
</body>
</html>
