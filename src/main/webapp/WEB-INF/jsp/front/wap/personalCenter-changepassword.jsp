<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
<div class="w-main">
	<form action="${SHOPDOMAIN}/saveupdatesecret.html" id="form_config" class="form-horizontal" method="post">
		<div class="w-marginTop13 w-padding37 w-bgCF">
			<div class="">
				<input type="password" name="oldpassword" class=" w_passwd w_require w-textInput" placeholder="原密码" /> <br>
				 <input type="password" name="newpassword" class=" w_password w-textInput" id="newPass" placeholder="新密码" /><br> <input type="password" name="renewpassword"
					class=" w_renewPass w-textInput" id="oldPass" placeholder="确认新密码" />
			</div>
		</div>
		<input type="button" id="subs" onclick="updatePas()" class="w-subBtn w-margin-top" value="提交" />
	</form>
</div>
<script>
</script>
<jsp:include page="foot-validate.jsp"></jsp:include>
<script>
	document.title = "修改密码";
	function updatePas() {
		$("#form_config").submit();
	}
	jQuery(document).ready(function() {
		FormValidation.init();
	});
</script>