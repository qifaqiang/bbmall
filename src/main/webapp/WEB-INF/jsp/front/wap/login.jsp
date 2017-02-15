<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet"
	href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<div class="w-main">
	<form action="${SHOPDOMAIN}/userLogin.html" class="form-horizontal"
		id="form_config" method="post">
		<div class="w-marginTop13 w-padding37 w-bgCF">
			<input type="text" class="w-textInput w_require" name="loginName"
				placeholder="用户名/手机号" /> <input type="password" name="loginPwd"
				class="w-textInput  w_passwd" placeholder="密码" />
		</div>
		<input type="button" class="w-subBtn w_save w-margin-top" value="登录" />
		<input type="hidden" id="referrer" value="" />

	</form>
	<div class="w-registered ">
		<span class="fl">手机快速&nbsp;<a href="registered.html"><em>注册</em></a></span>
		<a href="retrievePassword.html"> <span class="fr">找回密码</span>
		</a>
	</div>
</div>
<script src="${SHOPDOMAIN}/front/js/jquery.hDialog.js"></script>
<!-- END CORE PLUGINS -->
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/wap/jquery.validate.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery-validation/js/additional-methods.min.js"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/admin/pages/scripts/form-validation-front-login.js"></script>
<script src="${SHOPDOMAIN}/system/js/jquery.form.js"></script>
<script>
	document.title = "登录";
	if (document.referrer.indexOf("login.html") < 0
			&& document.referrer.indexOf("retrievePassword.html") < 0
			&& document.referrer.indexOf("registered.html") < 0) {
		$("#referrer").val(document.referrer);
	}
	$(".w_save").click(function() {
		$.cookie('username', $("input[name='loginName']").val(), {
			expires : 30,
			path:"/"
		});
		$("#form_config").submit();
	})
	jQuery(document).ready(function() {
		deleteDoctorLocalStorage("redBagMobile");
		if ($.cookie('username') != undefined) {
			$("input[name='loginName']").val($.cookie('username'));
		}
		FormValidation.init();
		
	});
	
	
	//删除登录信息localStorage()
	  function deleteDoctorLocalStorage(loginName){
	  	localStorage.removeItem(loginName);
	   
	  }
</script>
</body>
</html>
