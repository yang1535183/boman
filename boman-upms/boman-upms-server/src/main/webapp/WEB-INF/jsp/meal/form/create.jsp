<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="createDialog" class="crudDialog">
	<form id="createForm" method="post">
		<input name = "id" value="${mealRoll.id}" type="hidden">
		<input name = "delFlag" value="${mealRoll.delFlag}" type="hidden"/>
		<div class="form-group">
			<label for="faceValue">面值</label>
			<input id="faceValue" type="text" class="form-control" name="faceValue" maxlength="20" value="${mealRoll.faceValue}">
		</div>
		<div class="form-group">
			<label for="coopBusiness">合作商家</label>
			<input id="coopBusiness" type="text" class="form-control" name="coopBusiness" maxlength="32"  value="${mealRoll.coopBusiness}">
		</div>
		<div class="form-group">
			<label for="receiverUserNames">领取人</label>
			<input id="receiverUserNames" type="text" class="form-control" name="receiverUserNames" maxlength="32"  value="${mealRoll.receiver.realname}"
					onclick="chooseUser()">
			<input id="receiverUserIds" type="hidden" name="receiverUserIds" maxlength="32"  value="${mealRoll.receiver.userId}">

		</div>
		<div class="form-group">
			<span>有效时间起</span>
			<input id="startDate" type="date" class="form-control" name="startDate" maxlength="20" value="<fmt:formatDate value="${mealRoll.startDate}" pattern="yyyy-MM-dd" />">
		</div>
		<div class="form-group">
			<span>有效时间止</span>
			<input id="endDate" type="date" class="form-control" name="endDate" maxlength="150" value="<fmt:formatDate value="${mealRoll.endDate}" pattern="yyyy-MM-dd" />">
		</div>
		<div class="form-group">
			<label for="remarks">备注</label>
			<input id="remarks" type="text" class="form-control" name="remarks" maxlength="32"  value="${mealRoll.remarks}">
		</div>
		<div class="form-group text-right dialog-buttons">
			<c:choose>
				<c:when test="${opt eq 'showView'}">
				</c:when>
				<c:otherwise>
					<a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
				</c:otherwise>
			</c:choose>
			<a class="waves-effect waves-button" href="javascript:;" onclick="createDialog.close();">取消</a>
		</div>
	</form>
</div>
<script type="text/javascript">
	// 选择用户
	function chooseUser() {
	    var receiverUserIds = $("#receiverUserIds").val();
        window.open("${basePath}/meal/mealRoll/chooseUser?receiverUserIds="+receiverUserIds,"","width=200,height=300,top=100");
    }

    // 用户回显
    function checkUser(userIds,userNames) {
        $("#receiverUserIds").val(userIds);
	    $("#receiverUserNames").val(userNames);
	    console.log("userIds==="+userIds);
	    console.log("userNames==="+userNames);
    }
function initUploader() {
	//百度上传按钮
	var uploader = WebUploader.create({
		// swf文件路径
		swf: '${basePath}/resources/zheng-admin/plugins/webuploader-0.1.5/Uploader.swf',
		// 文件接收服务端
		method: 'POST',
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick: {
			"id": '#picker',
			"multiple": false
		},
		// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		resize: false,
		// 选完文件后，是否自动上传。
		auto: false,
		// 只允许选择图片文件
		accept: {
			title: '图片文件',
			extensions: 'gif,jpg,jpeg,bmp,png',
			mimeTypes: 'image/*'
		}
	});
	uploader.on( 'fileQueued', function(file) {
		$.ajax({
			url: '${ZHENG_OSS_ALIYUN_OSS_POLICY}',
			type: 'GET',
			dataType: 'jsonp',
			jsonp: 'callback',
			success: function(result) {
				var suffix = get_suffix(file.name);
				var random_name = random_string();
				uploader.options.formData.key = result.dir + '/' + random_name + suffix;
				uploader.options.formData.policy = result.policy;
				uploader.options.formData.OSSAccessKeyId = result.OSSAccessKeyId;
				uploader.options.formData.success_action_status = 200;
				uploader.options.formData.callback = result.callback;
				uploader.options.formData.signature = result.signature;
				uploader.options.server = result.action;
				uploader.upload();
			},
			error: function(msg) {
				console.log(msg);
			}
		});
	});
	uploader.on( 'uploadSuccess', function(file, response) {
		$('#avatar').val(response.data.filename).focus();
	});
	uploader.on( 'uploadError', function(file) {
		console.log('uploadError', file);
	});
}
// 根据路径获取后缀
function get_suffix(filename) {
	var pos = filename.lastIndexOf('.');
	var suffix = '';
	if (pos != -1) {
		suffix = filename.substring(pos);
	}
	return suffix;
}
// 随机字符串
function random_string(len) {
	len = len || 32;
	var chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
	var maxPos = chars.length;
	var pwd = '';
	for (i = 0; i < len; i++) {
		pwd += chars.charAt(Math.floor(Math.random() * maxPos));
	}
	return pwd;
}
function createSubmit() {
    $.ajax({
        type: 'post',
        url: '${basePath}/meal/mealRoll/create',
        data: $('#createForm').serialize(),
        beforeSend: function() {
            if ($('#faceValue').val() == '') {
                $('#faceValue').focus();
                return false;
            }

            if ($('#receiverUserNames').val() == '') {
                $('#receiverUserNames').focus();
                return false;
            }
        },
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
							content: result.data.errorMsg || result.data,
							buttons: {
								confirm: {
									text: '确认',
									btnClass: 'waves-effect waves-button waves-light'
								}
							}
						});
				}
			} else {
				createDialog.close();
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
</script>