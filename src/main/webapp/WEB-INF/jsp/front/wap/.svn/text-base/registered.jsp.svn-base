<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet"
	href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<div class="w-main">
	<form action="${SHOPDOMAIN}/userRegister.html" class="form-horizontal"
		id="form_config" method="post">
		<div class="w-marginTop13 w-padding37 w-bgCF">

			<input type="text" autocomplete="off" id="phones"
				class="w-textInput w_mobile w_merchants_login w_require "
				name="phone" placeholder="手机号" /> <input type="hidden"
				name="fromCompanyId" />
			<div class="w-textInput">
				<input type="text" class="w-validationInput w_require " name="code"
					placeholder="短信验证码" /> <input type="button" class="fr w-getCode"
					id="hsbtn" value="获取验证码" />
			</div>
			<input type="password" class="w-textInput w_passwd" name="pwd"
				placeholder="密码" />
		</div>
		<input type="submit" class="w-subBtn w-margin-top" value="注册" />
	</form>
	<div class="w-registered ">
		<span class="fl">已有账号请&nbsp;<a href="login.html"><em>登录</em></a></span>
		<a href="retrievePassword.html"> <span class="fr">找回密码</span>
		</a>
	</div>
</div>
<script>
	document.title = "注册";
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
			type : 1
		}, function(data) {
			if (data.res_code == '0') {
				$.tooltip('发送成功~', 2500, true);
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
			$.tooltip("请输入手机号再获取验证码");
		}

	}
</script>
<jsp:include page="foot-validate.jsp"></jsp:include>
<script>
	jQuery(document).ready(
			function() {
				$("input[name='fromCompanyId']").val(
						localStorage.getItem("fromCompanyId"));
				FormValidation.init();
			});
</script>