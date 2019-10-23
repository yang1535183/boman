<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title> </title>

   <%-- <link href="/resources/zheng-admin/css/demo.css" rel="stylesheet" media="all">--%>
    <link rel="stylesheet" type="text/css" href="/resources/zheng-admin/css/iframelogin.css">
    <!--[if IE]>

    <style type="text/css">
        li.remove_frame a {
            padding-top: 5px;
            background-position: 0px -3px;
        }

        #QrCodeImg{

            margin: auto;

            position: absolute;

            top: 0;

            left: 0;

            bottom: 0;

            right: 0;

        }
        .addlg{
            float:left;
            height:41px;
            line-height:41px;
        }
    </style>

    <![endif]-->


    <script type="text/javascript" src="/resources/zheng-admin/js/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/zheng-admin/js/juqery.qrcode.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {

            $('.icon-monitor').addClass('active');

            $(".icon-mobile-3").click(function () {
                $("#by").css("overflow-y", "auto");
                $('#iframe-wrap').removeClass().addClass('mobile-width-3');
                $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
                $(this).addClass('active');
                return false;
            });

            $(".icon-mobile-2").click(function () {
                $("#by").css("overflow-y", "auto");
                $('#iframe-wrap').removeClass().addClass('mobile-width-2');
                $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
                $(this).addClass('active');
                return false;
            });

            $(".icon-mobile-1").click(function () {
                $("#by").css("overflow-y", "auto");
                $('#iframe-wrap').removeClass().addClass('mobile-width');
                $('.icon-tablet,.icon-mobile,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
                $(this).addClass('active');
                return false;
            });

            $(".icon-tablet").click(function () {
                $("#by").css("overflow-y", "auto");
                $('#iframe-wrap').removeClass().addClass('tablet-width');
                $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
                $(this).addClass('active');
                return false;
            });

            $(".icon-monitor").click(function () {
                $("#by").css("overflow-y", "hidden");
                $('#iframe-wrap').removeClass().addClass('full-width');
                $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
                $(this).addClass('active');
                return false;
            });
        });
    </script>

    <script type="text/javascript">
        function Responsive($a) {
            if ($a == true) $("#Device").css("opacity", "100");
            if ($a == false) $("#Device").css("opacity", "0");
            $('#iframe-wrap').removeClass().addClass('full-width');
            $('.icon-tablet,.icon-mobile-1,.icon-monitor,.icon-mobile-2,.icon-mobile-3').removeClass('active');
            $(this).addClass('active');
            return false;
        };
    </script>

</head>
<body id="by">

<div id="iframe-wrap">
    <div class="bg1"></div>
    <div class="gyl">
        博曼管理平台

        <div class="gy2">打造国内最具规模的、最专业的服务平台 </div>
    </div>
    <div class="bg">
        <a href="javascript:void(0);" id="qrcode"><img src="/resources/zheng-admin/images/QRCode_QR.png" onclick="tab1();" style="width: 35px;height: 35px;" align="right" ></a>
        <a href="javascript:void(0);" id="computer" hidden><img src="/resources/zheng-admin/images/computer.png" onclick="tab2();"  style="width: 40px;height: 40px; " align="right"   ></a>
        <div class="cus_1">
            <div class="wel">用户登录</div>
            <div class="user">
                <div  style="" class="addlg">账号</div>
                <input type="text" id="username" name="用户" value="">
            </div>
            <div class="password">
                <div class="addlg">密码</div>
                <input  id="password" type="password" name="密码" value="">
            </div>
            <div class="rem">
                <input type="checkbox" onclick="remeberMe();" name="rememberMe" id="rememberMe" value="">
                <div id="reb">
                    记住密码
                </div>
            </div>
            <div class="fg">
                <div style="font-size: 11px;margin-top: 11px;">
                    <a style="font-size: 11px;" href="#">忘记密码？</a>
                </div>
            </div>
            <input id="login-bt" class="btn" type="button" name="登录" style="cursor: pointer" value="登录">
        </div>
        <div class="cus_2" >
            <img id="QrCodeImg"  src="" style="height: 90%; width: 90%" hidden>
        </div>
    </div>
</div>

</body>
<script src="${basePath}/resources/zheng-admin/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/waves-0.7.5/waves.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/checkbix/js/checkbix.min.js"></script>
<script>var BASE_PATH = '${basePath}';</script>
<script>var BACK_URL = '${param.backurl}';</script>
<script src="${basePath}/resources/zheng-admin/js/login.js"></script>
<script>
    $(function(){
//  判断是手机端还是电脑
        var isMobile = {
            Android: function () {
                return navigator.userAgent.match(/Android/i) ? true : false;
            },
            BlackBerry: function () {
                return navigator.userAgent.match(/BlackBerry/i) ? true : false;
            },
            iOS: function () {
                return navigator.userAgent.match(/iPhone|iPad|iPod/i) ? true : false;
            },
            Windows: function () {
                return navigator.userAgent.match(/IEMobile/i) ? true : false;
            },
            any: function () {
                return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Windows());
            }
        };
        if (isMobile.any()) {
            //如果是手机访问的话的操作！
            //alert('手机端');
            $('.bg').css({
                "position":"absolute",
                "left":"50%",
                "top":"50%",
                "transform":"translate(-50%,-50%)"
            });
        }else{
            //alert('电脑端');
        }

    })

    function tab2(){
        window.location.reload(true);
    }
    function tab1(){
        $(".cus_1").hide();
        $("#computer").show();
        $("#qrcode").hide();
        $("#QrCodeImg").show();
        var uuid;
        $.ajax({
            url : "/QR/QR/GetQrCodeServlet",
            type : "get",
            success: function (data) {
                var obj =data;
                //存储UUID
                uuid = obj.uuid;
                //显示二维码
                $("#QrCodeImg").attr("src", obj.qrCodeImg);
                //开始验证登录
                validateLogin();
            }
        });
        function validateLogin(){
            $.ajax({
                url : "/QR/QR/LongConnectionCheckServlet?uuid="+uuid,
                type : "get",
                success: function (data) {
                    if(data.username == ""){
                        validateLogin();
                    }else{
                        window.location.reload(true);
                    }
                }
            });
        }
    }

    function remeberMe(){
        $(".input-group").show();
        $("#QrCodeImg").hide();
    }

    <c:if test="${param.forceLogout == 1}">
    alert('您已被强制下线！');
    top.location.href = '${basePath}/sso/login';
    </c:if>
    //解决iframe下系统超时无法跳出iframe框架的问题
    if (window != top){
        top.location.href = location.href;
    }

</script>
</html>