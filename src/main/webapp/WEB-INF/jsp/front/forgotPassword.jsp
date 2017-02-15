<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>电商平台找回密码</title>
<!--全局初始化-->
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/common/js/doT.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/web/common.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/layer/layer.js"></script>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/login.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/commonW.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/globalW.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/cmdCommon.css" />
<link rel="stylesheet"
	href="${SHOPDOMAIN}/front/layer/skin/myskin/style.css" />
<script src="${SHOPDOMAIN}/system/js/jquery.form.js"></script>
</head>
<body>
	<!--头部-->
	<div id="headerW" style="background: #f1f1f1">
		<div class="wrapW ofW">
			<div class="headerLogoW flW">
				<img src="${SHOPDOMAIN}/front/images/web/pcLogo.png" width="210"
					height="45" alt="" /> <span class="headerTitleL">找回密码</span>
			</div>
			<div class="frW">
				<a href="registered.html"><span class="forgotPassSpan borderR">注册</span></a>
				<a href="login.html"><span class="forgotPassSpan">登陆</span></a>
			</div>
		</div>
	</div>
	<!--分隔线-->
	<div class="s-line"></div>

	<div id="mainW">
		<div class="wrapW forgotPForm">
			<div class="forPrompt">
				<img src="${SHOPDOMAIN}/front/images/web/forgotPassPrompt.png"
					alt="" />&nbsp;&nbsp; 请输入您要找回登录密码的账户名
			</div>
			<form action="${SHOPDOMAIN}/retrievePassword.html"
				class="form-horizontal " id="form_config" method="post">
				<table>
					<tr>
						<td class="inputTitle "><label for="accountW">账号：</label></td>
						<td class="inputTd "><div class="formInput height42"
								style="margin-bottom:0; width:320px; line-height:38px;">
								<input class="inputStyle  w_mobile w_forget_loginsPC  w_require"
									style="height:40px;border:none;line-height:40px;"
									autocomplete="off" type="text" name="phone" id="phones"
									placeholder="手机号码" />
							</div></td>
						<td class="lastTd">&nbsp;</td>
					</tr>
					<tr>
						<td class="inputTitle"><label for="verCode">验证码：</label></td>
						<td class="inputTd"><input type="text"
							style="height:40px;line-height:40px; "
							class="inputStyle" name="captcha" placeholder="验证码" /></td>
						<td class="lastTd"><div class="borderE5">
								<img src="${SHOPDOMAIN}/captcha.svl" class="fr w-getCode1"
									alt="" onclick="chCaptcha();" id="capImg" />
							</div></td>
					</tr>
					<tr>
						<td class="inputTitle"><label for="moVercode">手机校验码：</label></td>
						<td class="inputTd"><input type="text"
							style="height:40px;line-height:40px; " class="inputStyle"
							name="code" id="moVercode" /></td>
						<td class="lastTd"><input type="button" class="getVCode"
							id="hsbtn" value="获取验证码" /></td>
					</tr>
					<tr>
						<td class="inputTitle"><label for="setNewP"></label>设置新密码：</td>
						<td class="inputTd"><div class=" formInput height42"
								style="margin-bottom:0; width:320px; line-height:38px;">
								<input type="password" class="inputStyle w_passwd"
									style="height:40px;line-height:40px; border:none"
									name="password" id="setNewP" placeholder="请输入密码" />
							</div></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请输入6~11个字符</td>
						<td class="lastTd">&nbsp;</td>
					</tr>
					<tr>
						<td class="inputTitle"><label for="confirmNewP">确认新密码：</label></td>
						<td class="inputTd"><input type="password"
							style="height:40px;line-height:40px; " class="inputStyle"
							id="confirmNewP" /></td>
						<td class="lastTd">&nbsp;</td>
					</tr>
					<tr>
						<td></td>
						<td class="inputTd"><input type="button"
							class="forPSubBtn  submitL" value="提交" /></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<script type="text/javascript"
		src="${SHOPDOMAIN}/front/js/wap/jquery.validate.js"></script>
	<script type="text/javascript"
		src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery-validation/js/additional-methods.min.js"></script>
	<script
		src="${SHOPDOMAIN}/system/theme/assets/admin/pages/scripts/form-validation-pc.js"></script>
	<script src="${SHOPDOMAIN}/system/js/jquery.form.js"></script>
	<script type="text/javascript"
		src="${SHOPDOMAIN}/front/js/jquery.cookie.js"></script>
	<div class="footLogin ">
		<div class="loginFirstL">客服热线：${SYSPROPORTION.mobile}</div>
		<div class="loginsecL">版权所有 ©${SYSPROPORTION.copyrights}</div>
	</div>
	<script>
		document.title = "电商平台    找回密码"
		var SHOPDOMAIN = "${SHOPDOMAIN}";
		var wait = 60;
		function time(o) {
			if (wait == 0) {
				o.removeAttribute("disabled");
				o.value = "再次获取验证码";
				wait = 60;
			} else {
				o.setAttribute("disabled", true);
				o.value = "重新发送(" + wait + ")";
				wait--;
				setTimeout(function() {
					time(o)
				}, 1000)
			}
		}

		function getPhoneCode(kthis) {
			$.post(SHOPDOMAIN + '/getPhoneCode.html', {
				phone : $("#phones").val(),
				type : "2"
			}, function(data) {
				if (data.res_code == '0') {
					showMessageAutoTime("发送成功~", 2500);
					;
					time(kthis);
				} else {
					showMessage(data.message);
				}
			}, "json").error(function() {
				showError();
			});
		}
		document.getElementById("hsbtn").onclick = function() {
			if ($("#phones").val().trim() != "") {
				getPhoneCode(this);
			} else {
				showMessage("请输入手机号再获取验证码");
			}
		}
		$(".submitL").click(function() {
			if ($("#setNewP").val() == $("#confirmNewP").val()) {
				$("#form_config").submit();
			} else {
				showMessage("请输入密码 两次密码不一致！")
			}
		});
		function chCaptcha() {
			$("#capImg").attr("src",
					"${SHOPDOMAIN}/captcha.svl?time=" + new Date().getTime());
		}
		jQuery(document).ready(function() {
			FormValidation.init();
		});
	</script>
</body>
</html>