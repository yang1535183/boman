<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<c:set var="username" value="${username}"/>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>权限管理系统</title>
	<link href="/resources/zheng-admin/images/logo_favicon.ico" rel="shortcut icon">
	<link href="${basePath}/resources/zheng-admin/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-admin/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-admin/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-admin/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-admin/css/admin.css" rel="stylesheet"/>
	<link href="/resources/assets/css/font-awesome.min.css" rel="stylesheet" type="text/css">

	<script src="/resources/home/js/jquery-1.11.1.min.js"></script>
	<%--<script src="/resources/home/js/bootstrap.js"></script>--%>
	<script src="/resources/home/js/echarts.js"></script>
	<script src="/resources/home/js/chartdata.js"></script>
	<%--<link rel="stylesheet" href="/resources/home/css/bootstrap.css" />--%>
	<link rel="stylesheet" href="/resources/home/css/index.css" />

	<style>
		/** skins **/
		<c:forEach var="upmsSystem" items="${upmsSystems}">
														  #${upmsSystem.name} #header {background: ${upmsSystem.theme};}
		/*#${upmsSystem.name} .content_tab{background: ${upmsSystem.theme};}*/
		#${upmsSystem.name} .s-profile>a{background: url(${basePath}${upmsSystem.banner}) left top no-repeat;}
		</c:forEach>
	</style>
	<style>
		.z-message-icon{
			font-size:14px;
			position:absolute;
			top:22%;
			left:65%;
			display:inline-block;
			width:15px;
			height:15px;
			line-height:15px;
			background:#36a2f5;
			color:white;
			border-radius:10px;
		}
		.z-message{
			height:70px;
			line-height:70px;
			font-size:16px;
		}
		.bigger{
			display: block;
			overflow: hidden;
			background: #f3f4f4;
			padding: 15px;
			letter-spacing: .5px;
		}
		.notification-label{
			float: right;
			border-radius: 10px;
			padding: 0px 5px;
			color: #fff;
			background-color: #e91e63;
			line-height: 24px;
			font-size: 12px;
		}
		.z-message-tails{
			overflow-x: hidden;
			overflow-y: scroll;
			width: 300px;
			position: absolute;
			left: -250%;
		}
		.bigger h3{
			margin: 0 auto;
			font-weight: normal;
			font-size: 13px;
			line-height: 24px;
			display: inline-block;
		}
		.dropdown-menu-list {
			list-style: none;
			margin: 0px;
			padding: 0px;
		}
		.dropdown-menu-list li{
			padding-left: 5px;
			padding-top: 3px;
			margin-top: 3px;
		}
		.dropdown-menu-list li:hover{
			background: #f3f4f4;
		}
		.dropdown-menu-list li a .time {
			float: right;
			max-width: 75px;
			font-size: 11px;
			font-weight: 400;
			opacity: .7;
			filter: alpha(opacity=70);
			text-align: right;
			padding: 1px 5px;
			margin: 2px 0px;
			background: #29A176;
			color: white;
			border-radius: 4px;
		}
		.dropdown-menu-list li a .details {
			overflow: hidden;
			font-weight: 300;
			line-height: 20px;
			white-space: normal;
			font-size: 13px;
			padding: 8px 15px 8px 0;
			text-shadow: none;
			color: #888;
		}
		.dropdown-menu-list li a .details i{
			font-size: 9px;
		}
		.cc-details{
			width: 58%;
			display: inline-block;
			overflow: hidden;
			white-space: nowrap;
			vertical-align: middle;
			text-overflow: ellipsis;
		}
		.notification-icon {
			width: 24px;
			height: 24px;
			text-align: center;
			display: inline-block;
			vertical-align: middle;
			font-size: 15px;
			color: #fff;
			line-height: 0.05;
			padding: 9px 0;
			border-radius: 50%;
			margin-right: 10px;
		}
		.deepPink-bgcolor {
			background-color: #e91e63 !important;
			color: #fff !important;
		}
		/**/
		.addct{
			width:52%;
			float:left;
			height:70px;
			line-height:70px;
		}
		/*切换系统*/
		#header li.addcur {
			width: 268px;
			padding: 6px 0px;
			margin: 0px;
			background: #327fe4;
			color: #fff;
			text-align: left;
			text-indent: 16px;
		}
		#header ul {
			padding: 0px;
			margin: 0px;
		}
		/*全屏模式 列*/
		li.addhx {
			background: #fff;
			height: auto;
		}
	</style>
</head>
<body>
<header id="header">
	<ul id="menu">
		<li id="guide" class="line-trigger">
			<div class="line-wrap">
				<div class="line top"></div>
				<div class="line center"></div>
				<div class="line bottom"></div>
			</div>
		</li>
		<li id="logo" class="hidden-xs">
			<a href="${basePath}/manage/index">
				<img src="${basePath}/resources/zheng-admin/images/logo.png"  style="width: 30px;height: 30px; margin:16px 10px 00px 10px"/>
			</a>
			<span id="system_title">权限管理系统</span>
		</li>
		<div class="content_tab  addct">
			<ul id="tabs" class="tabs">
				<li id="tab_home" data-index="home" data-closeable="false" class="cur">
					<span class="waves-effect waves-light" href="javascript:;">首页</span>
				</li>
			</ul>
		</div>
		<li class="pull-right">
			<ul class="hi-menu">
				<!-- 搜索 -->
				<li class="dropdown">
					<a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
						<i class="him-icon zmdi zmdi-search"></i>
					</a>
					<ul class="dropdown-menu dm-icon pull-right">
						<form id="search-form" class="form-inline">
							<div class="input-group">
								<input id="keywords" type="text" name="keywords" class="form-control" placeholder="搜索"/>
								<div class="input-group-btn">
									<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
								</div>
							</div>
						</form>
					</ul>
				</li>
				<li class="dropdown">
					<a href="javascript:;" class="waves-effect waves-light z-message" data-toggle="dropdown" data-hover="dropdown" data-close-others="true" aria-expanded="false">
						<i class="fa fa-bell-o"></i>
						<span class="z-message-icon" id="message_count">0</span>
					</a>
					<ul class="dropdown-menu dm-icon z-message-tails">
						<li class="bigger">
							<h3><span class="bold">未读消息</span></h3>
							<span class="notification-label">共<span id="notification-count">0</span>条</span>
						</li>
						<li>
							<ul class="dropdown-menu-list" id="wxInfo">
								<li>
									<a href="javascript:Tab.addTab('cs', '${basePath}/manage/message/index');"> <span class="time">just now</span> <span class="details"> <span class="notification-icon deepPink-bgcolor">
														<i class="fa fa-check"></i> </span> <span class="cc-details">这是一个你未读的消息，这是一个你未读的消息，这是一个你未读的消息!</span></span> </a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
						<i class="him-icon zmdi zmdi-dropbox"></i>
					</a>
					<ul class="dropdown-menu dm-icon pull-right">
						<li class="skin-switch  addcur">
							请选择系统切换
						</li>
						<c:forEach var="upmsSystem" items="${upmsSystems}">
							<li>
								<a class="waves-effect switch-systems" href="javascript:;" systemid="${upmsSystem.systemId}" systemname="${upmsSystem.name}" systemtitle="${upmsSystem.title}"><i class="${upmsSystem.icon}"></i> ${upmsSystem.title}</a>
							</li>
						</c:forEach>
					</ul>
				</li>
				<li class="dropdown">
					<a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
						<i class="him-icon zmdi zmdi-more-vert"></i>
					</a>
					<ul class="dropdown-menu dm-icon pull-right">
						<li class="hidden-xs addhx">
							<a class="waves-effect" data-ma-action="fullscreen" href="javascript:fullPage();"><i class="zmdi zmdi-fullscreen"></i> 全屏模式</a>
						</li>
						<li>
							<a class="waves-effect" data-ma-action="clear-localstorage" href="javascript:;"><i class="zmdi zmdi-delete"></i> 清除缓存</a>
						</li>
						<li>
							<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-face"></i> 隐私管理</a>
						</li>
						<li>
							<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-settings"></i> 系统设置</a>
						</li>
						<li>
							<a class="waves-effect" href="${basePath}/sso/logout"><i class="zmdi zmdi-run"></i> 退出登录</a>
						</li>
					</ul>
				</li>
			</ul>
		</li>
	</ul>
</header>
<section id="main">
	<!-- 左侧导航区 -->
	<aside id="sidebar">
		<!-- 个人资料区 -->

		<!-- /个人资料区 -->
		<!-- 菜单区 -->
		<ul class="main-menu">
			<li>
				<a class="waves-effect" href="javascript:Tab.addTab('首页', 'home');">首页</a>
			</li>
			<c:forEach var="upmsPermission" items="${upmsPermissions}" varStatus="status">
				<c:if test="${upmsPermission.pid == 0}">
					<li class="sub-menu system_menus system_${upmsPermission.systemId} ${status.index}" <c:if test="${upmsPermission.systemId != 1}">style="display:none;"</c:if>>
					<a class="waves-effect" href="javascript:;"><i class="${upmsPermission.icon}"></i> ${upmsPermission.name}</a>
					<ul >
						<c:forEach var="subUpmsPermission" items="${upmsPermissions}">
							<c:if test="${subUpmsPermission.pid == upmsPermission.permissionId}">
								<c:forEach var="upmsSystem" items="${upmsSystems}">
									<c:if test="${subUpmsPermission.systemId == upmsSystem.systemId}">
										<c:set var="systemBasePath" value="${upmsSystem.basepath}"/></c:if>
								</c:forEach>
								<li><a class="waves-effect" href="javascript:Tab.addTab('${subUpmsPermission.name}', '${systemBasePath}${subUpmsPermission.uri}');">${subUpmsPermission.name}</a></li>
							</c:if>
						</c:forEach>
					</ul>
					</li>
				</c:if>
			</c:forEach>
			<li>
				<div class="upms-version">&copy; BOMAN V1.0.0</div>
			</li>
		</ul>
		<!-- /菜单区 -->
	</aside>
	<!-- /左侧导航区 -->
	<section id="content">
		<div class="content_tab">
			<div class="tab_left">
				<a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-left"></i></a>
			</div>
			<div class="tab_right">
				<a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-right"></i></a>
			</div>

		</div>
		<div class="content_main">
			<div id="iframe_home" class="iframe cur">
				<%----%>
				<div class="container-fluid addcf ">
					<!--待收货 待上架 待拣货 拣货中-->
					<div class="row addrow">
						<div class="col-md-3">
							<div class="coldeer">
								<h2>
								<span class="cir-one">
									<img src="/resources/home/img/icon-one.png" alt="" />
								</span>
									<label for="">招商管理系统</label>
								</h2>
								<b class="cir-b">
									<h3>
										<img src="/resources/home/img/icon-red.png" alt="" />
										<span id="zscount">0</span></h3>
									<p>待办个数</p>
								</b>
							</div>
						</div>
						<div class="col-md-3">
							<div class="coldeer">
								<h2>
								<span class="cir-two">
									<img src="/resources/home/img/icon-two.png" alt="" />
								</span>
									<label for="">待办</label>
								</h2>
								<b class="cir-b">
									<h3>
										<img src="/resources/home/img/icon-green.png" alt="" />
										0</h3>
									<p>待办个数</p>
								</b>
							</div>
						</div>
						<div class="col-md-3">
							<div class="coldeer">
								<h2>
								<span class="cir-three">
									<img src="/resources/home/img/icon-three.png" alt="" />
								</span>
									<label for="">待办</label>
								</h2>
								<b class="cir-b">
									<h3>
										<img src="/resources/home/img/icon-red.png" alt="" />
										0</h3>
									<p>待办个数</p>
								</b>
							</div>
						</div>
						<div class="col-md-3">
							<div class="coldeer">
								<h2>
								<span class="cir-four">
									<img src="/resources/home/img/icon-four.png" alt="" />
								</span>
									<label for="">待办</label>
								</h2>
								<b class="cir-b">
									<h3>
										<img src="/resources/home/img/icon-red.png" alt="" />
										0</h3>
									<p>待办个数</p>
								</b>
							</div>
						</div>
					</div>
					<!--待收货 待上架 待拣货 拣货中-->

					<!--图表-->
					<div class="row">
						<div class="col-md-4 adcm4">
							<div class="colpic" id="con-one"></div>
						</div>
						<div class="col-md-4 adcm4">
							<div class="colpic" id="con-two"></div>
						</div>
						<div class="col-md-4 adcm4">
							<div class="colpic" id="con-three"></div>
						</div>
					</div>
					<!--图表-->
				</div>
				<%----%>
			</div>
		</div>
	</section>
</section>
<footer id="footer"></footer>
<script>var BASE_PATH = '${basePath}';</script>
<script src="${basePath}/resources/zheng-admin/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/waves-0.7.5/waves.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/BootstrapMenu.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/device.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/jquery.cookie.js"></script>
<script src="${basePath}/resources/zheng-admin/js/admin.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/fullPage/jquery.fullPage.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/fullPage/jquery.jdirk.min.js"></script>

<script src="${basePath}/resources/zheng-admin/js/socket.io.js"></script>
<script src="${basePath}/resources/zheng-admin/js/moment.min.js"></script>

<script>

    function add0(m){return m<10?'0'+m:m }

    function format(shijianchuo)
    {
        var time = new Date(shijianchuo);
        var m = time.getMonth()+1;
        var d = time.getDate();
        var h = time.getHours();
        return add0(m)+'/'+add0(d)+':'+add0(h);
    }

	$(function () {
        /*$.ajax({
            type: 'get',
            url: '${basePath}/zhaoshang/project/count',
            success:function (result) {
                $('#zscount').html(result);
            }

        })*/



        $.ajax({
            type: 'get',
            url: '${basePath}/manage/message/notice',
            success:function (result) {
                var len = result.length;
                console.log(len);
                var value = $("#message_count").text();
                $("#message_count").text(parseInt(value) + len);
                $("#notification-count").text($("#message_count").text());
                var wxList = "";
                $.each(result,function(i,item){
					wxList += "<li onclick='deleteElement(this)' id='wxList" + item.id+"'>" + "<a href=\"javascript:Tab.addTab('我的消息', '${basePath}/manage/message/index');\">" +
                    "<span class='time'>"+ format(item.ctime) +"</span> <span class='details'> <span class='notification-icon deepPink-bgcolor'>" +
                    "<i class='fa fa-check'></i> </span> <span class='cc-details'>" + item.tittle+
                    "</span></span> </a></li>";
				})
                $('#wxInfo').text("");
                $('#wxInfo').append(wxList);
            },
            error:function () {

            }
        });

    })

    function isEmpty(obj){
        if(typeof obj == "undefined" || obj == null || obj == ""){
            return true;
        }else{
            return false;
        }
    }

	window.onload = function (ev) {
        var name = '${username}';
        if(isEmpty(name)){
			return false;
		}
        var socket =  io.connect('http://upms.zhangshuzheng.cn:40000?username='+name);
        socket.on('connect', function() {
            console.log("Client has connected to the server!");
        });
        socket.on('advert_info', function(data) {
            console.log(data.id);
            var value = $("#message_count").text();
            $("#message_count").text(parseInt(value) + 1);
            $("#notification-count").text($("#message_count").text());

            var wxList = "";
            wxList += "<li onclick='deleteElement(this)' id='wxList" + data.id+"'>" + "<a href=\"javascript:Tab.addTab('我的消息', '${basePath}/manage/message/index');\">" +
                "<span class='time'>"+ format(data.ctime) +"</span> <span class='details'> <span class='notification-icon deepPink-bgcolor'>" +
                "<i class='fa fa-check'></i> </span> <span class='cc-details'>" + data.tittle +
                "</span></span> </a></li>";
            $('#wxInfo').append(wxList);
        });
        socket.on('disconnect', function() {
            console.log("The client has disconnected!");
        });
        socket.on('connect_error', function(data){
            console.log(JSON.stringify(data)+ ' - connect_error');
            socket.disconnect()
        });
	}

	//已办
	function myReceived(){
		$.ajax({
			type: 'get',
			url: '${basePath}/manage/myReceived',
			success:function (result) {
				var html ='';
				for(var i=0;i<result.length;i++){
					<%--	var time =changeDateFormat(data[i].startTime);--%>
					html +='<li><a  onclick="deleteElement(\"' + data[i].taskId + '\")"' + href="#"><i></i>'+data[i].title+'</a><b>'+time+'</b></li>';
					/*html +='<li><a href="javascript:Tab.addTab(' + '"我的待办"' + ', + 'http://upms.zhangshuzheng.cn:8010\/oa\/oa\/transact\/\' ' + data[i].taskId + ' );"><i></i>'+data[i].actName+'</a><b>'+time+'</b></li>';*/
				}
				$("#yb").html(html);
			},
			error:function () {
			}
		});
	}
	
	function daiban(id) {
		Tab.addTab('系统管理', 'http://upms.zhangshuzheng.cn:8010/oa/oa/transact/'+ id)
	}

</script>
</body>
</html>
