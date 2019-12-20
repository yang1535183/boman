<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <title>用户选择</title>

    <script type="text/javascript" src="${basePath}/resources/zTree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${basePath}/resources/zTree/js/jquery.ztree.all.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${basePath}/resources/zTree/css/metroStyle/metroStyle.css"/>
</head>
<body>
<div>
    <ul id="ztree" class="ztree"></ul>
    <button onclick="checkUser()">选择</button>
    <button onclick="cloWin()">取消</button>
</div>
<script type="text/javascript">
    function cloWin() {
        // 获得frame索引
        var index = parent.layer.getFrameIndex(window.name);
        // 关闭当前frame
        parent.layer.close(index);
    }
    
    function checkUser() {
        var zTree = $.fn.zTree.getZTreeObj("ztree")
        var changeNodes = zTree.getChangeCheckedNodes();
        console.log(changeNodes);
        var userIds="";
        var userNames="";
        for (var i = 0; i < changeNodes.length; i ++) {
            userIds += changeNodes[i].id+",";
            userNames += changeNodes[i].username+",";
        }
        if(userIds.length > 0 && userIds.substring(0,1) == 0){
            userIds = userIds.substring(2,userIds.length-1);
            userNames = userNames.substring(3,userNames.length-1);
        }
        window.parent.checkUser(userIds,userNames);
        // 获得frame索引
        var index = parent.layer.getFrameIndex(window.name);
        // 关闭当前frame
        parent.layer.close(index);
    }

    var settingss = {
        data: {
            simpleData: {
                enable: true,  //true 、 false 分别表示 使用 、 不使用 简单数据模式
                idKey: "id",   //节点数据中保存唯一标识的属性名称
                pIdKey: "pid",    //节点数据中保存其父节点唯一标识的属性名称
                rootPId: -1  //用于修正根节点父节点数据，即 pIdKey 指定的属性值
            },
            key: {
                name: "username"  //zTree 节点数据保存节点名称的属性名称  默认值："name"
            }
        },
        check:{
            enable:true,  //true 、 false 分别表示 显示 、不显示 复选框或单选框
            nocheckInherit:true   //当父节点设置 nocheck = true 时，设置子节点是否自动继承 nocheck = true
        }
    };

    $(document).ready(function(){
        $.ajax({
            type:"get",
            url:"${basePath}/cj/mealRoll2/userList",
            async:true,
            success:function(res){
                zTreeObj = $.fn.zTree.init($("#ztree"), settingss, res.userlist); //初始化树
                // 默认选择节点
                var ids = "${receiverUserIds}".split(",");
                for(var i=0; i<ids.length; i++) {
                    var node = zTreeObj.getNodeByParam("id", ids[i]);
                    try{zTreeObj.checkNode(node, true, false);}catch(e){}
                }
                zTreeObj.expandAll(true);   //true 节点全部展开、false节点收缩
            }
        });
    });
</script>
</body>
</html>
