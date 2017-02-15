<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
<div class="w-main">
	<form action="${SHOPDOMAIN}/updatenicheng.html" id="form_config" class="form-horizontal" method="post">
		<div class="w-modifyNickname w-padding37">
			<input type="text" name="nickname" class="w_require w_6_20" value="${sessionFrontUser.name}" placeholder="昵称" />
			<p class="w-margin-top w-color9 w-fontsize20">可由中英文、数字、符号组成</p>
		</div>
		<button class="w-subBtn w-margin-top" onclick="upName()">保存</button>
	</form>
</div>
<jsp:include page="foot-validate.jsp"></jsp:include>
<script type="text/javascript">
	document.title = "修改昵称"
	function upName() {
		//document.getElementById('form_config').submit();
		$("#form_config").submit();
	}
	jQuery(document).ready(function() {
		FormValidation.init();
	});
</script>
