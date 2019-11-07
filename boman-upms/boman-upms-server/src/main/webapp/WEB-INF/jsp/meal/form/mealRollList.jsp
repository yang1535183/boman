<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>餐卷领取</title>
    <jsp:include page="/resources/inc/head.jsp" flush="true"/>
    <script type="text/javascript" src="${basePath}/resources/zTree/js/jquery-1.4.4.min.js"></script>
</head>
<body>
<div id="main">
    <div id="toolbar">
        <%--<a class="waves-effect waves-button" href="javascript:;" onclick="createAction()"><i class="zmdi zmdi-plus"></i> 新增餐卷</a>--%>
        <%--<a class="waves-effect waves-button" href="javascript:;" onclick="deleteAction()"><i class="zmdi zmdi-close"></i> 删除餐卷</a>--%>
    </div>
    <table id="table"></table>
    <jsp:include page="/resources/inc/footer.jsp" flush="true"/>
</div>

<script type="text/javascript">
    //转换日期格式(时间戳转换为datetime格式)
    function changeDateFormat(cellval) {
        var dateVal = cellval + "";
        if (cellval != null) {
            var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

            var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
            var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
            var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

            return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
        }
    }
    
    var $table = $('#table');
    
    $(function () {
        // bootstrap table初始化
        $table.bootstrapTable({
            url: '${basePath}/meal/mealRoll/mealRollListData',
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
            idField: 'roleId',
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'ck', checkbox: true},
                {field: 'id', title: '编号', sortable: true, align: 'center'},
                {field: 'createBy.username', title: '创建人'},
                {field: 'createDate', title: '创建时间',
                    //获取日期列的值进行转换
                    formatter: function (value, row, index) {
                        return changeDateFormat(value)
                    }
                },
                {field: 'receiver.realname', title: '领用人'},
                {field: 'receiveDate', title: '领用时间',
                    //获取日期列的值进行转换
                    formatter: function (value, row, index) {
                        return changeDateFormat(value)
                    }
                },
                {field: 'faceValue', title: '面值'},
                {field: 'action', title: '操作', align: 'center', formatter: 'actionFormatter', events: 'actionEvents', clickToSelect: false}
            ]
        });
    })

    // 格式化操作按钮
    function actionFormatter(value, row, index) {
        return [
            '<a class="update" href="javascript:;" onclick="receive('+row.id+')" data-toggle="tooltip" title="领取"><i class="glyphicon glyphicon-edit"></i></a>　',
            '<a class="delete" href="javascript:;" onclick="preview('+row.id+')" data-toggle="tooltip" title="预览"><i class="glyphicon glyphicon-eye-open"></i></a>'
        ].join('');
    }

    // 餐卷领取
    function receive(id) {
        var x;
        var r=confirm("确认领取？");
        if (r==true){
            $.ajax({
                url: "${basePath}/meal/mealRoll/receiveMealRoll",
                data: "id="+id,
                async: false,
                success: function(data){
                    alert(data.msg);
                    window.location.reload();
                }
            });
        }
    }

    /**
     * 预览表单
     */
    var createDialog;
    function preview(id) {
        //window.open("/meal/mealRoll/preview/" + id );
        createDialog = $.dialog({
            animationSpeed: 300,
            title: '查看餐卷',
            content: 'url:${basePath}/meal/mealRoll/create?opt=showView&id='+id,
            onContentReady: function () {
                initMaterialInput();
                initUploader();
            }
        });
    }
</script>
</body
</html>
