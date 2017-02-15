<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>电商平台登录</title>
<!--全局初始化-->
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/commonW.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/globalW.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/login.css" />
	<link rel="shortcut icon" href="${SHOPDOMAIN}/front/images/web/ico.ico" />
<!--全局初始化-->
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/web/common.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/web/commonFront.js"></script>	
<script type="text/javascript" src="${SHOPDOMAIN}/front/layer/layer.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/jquery.cookie.js"></script>
</head>
<body>
	<div id="loginHeader" class="wrapW">
		<div class="loginHeaderL">
			<a href="index.html"> <img
				src="${SHOPDOMAIN}/front/images/web/pcLogo.png" width="223"
				height="52" alt="" /></a> &nbsp;欢迎登陆
		</div>
	</div>
	<style>
		input:-webkit-autofill{
			background-color:red!important;
			background-image:none;
		}
	</style>
	<div class="loginForm">
		<div class="wrapW loginFormA">
			<div class="loginFormP">
				<div class="formTitle clearfixW">
					<span class="formTitleL">商城会员</span> <a href="registered.html"><span
						class="formTitleR">立即注册</span></a>
				</div>
				<form action="${SHOPDOMAIN}/userLogin.html" class="form-horizontal "
					id="form_login" method="post" autocomplete="off">
					<div class="formInput formInput1 ">
						<label for="loginUsername"></label> <input type="text"
							class="inputStyle  loss w_require" autocomplete="off" placeholder="手机号"
							id="loginUsername" name="loginName"  />
					</div>
					<div class="formInput formInput2 ">
						<label for="loginPass"></label> <input type="password"
							class="inputStyle w_passwd" placeholder="密码" id="loginPass"
							name="loginPwd" autocomplete="off" />
					</div>

					<div class="checkBoxSelf">
						<input type="checkbox" id="checkBoxL" name="checkBoxL" value="0" />
						<label for="checkBoxL">自动登录</label> <a href="forgotPassword.html"><span
							class="frW forgotPassL">忘记密码</span></a>
					</div>
					<div class="submitL">
						<input type="button" class="subBtnL w_save" value="登陆" />
					</div>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="${SHOPDOMAIN}/front/js/wap/jquery.validate.js"></script>
	<script type="text/javascript"
		src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery-validation/js/additional-methods.min.js"></script>
	<script src="${SHOPDOMAIN}/system/theme/assets/admin/pages/scripts/form-validation-pc.js"></script>
	<script src="${SHOPDOMAIN}/system/js/jquery.form.js"></script>

	<div class="footLogin ">
		<div class="loginFirstL">客服热线：${SYSPROPORTION.mobile}</div>
		<div class="loginsecL">版权所有 © ${SYSPROPORTION.copyrights}</div>
	</div>
	<script>
		if (document.referrer.indexOf("login.html") < 0
				&& document.referrer.indexOf("retrievePassword.html") < 0
				&& document.referrer.indexOf("registered.html") < 0) {
			$("#referrer").val(document.referrer);
		}
		$(".w_save").click(function() {
			if($("#checkBoxL").is(':checked')){
				$.cookie('checkBoxL', 'true', {
					expires : 30
				});
			}else{
				$.cookie('checkBoxL', null);
			}
			$.cookie('username', $("input[name='loginName']").val(), {
				expires : 30
			});
			$("#form_login").submit();
		})
		jQuery(document).ready(function() {
			if ($.cookie('username') != undefined) {
				$("input[name='loginName']").val($.cookie('username'));
			}
			if ($.cookie('checkBoxL') != undefined) {
				$("#checkBoxL").attr("checked",'true');
			}
			FormValidation.initLogin();
		});
	</script>
</body>
</html>