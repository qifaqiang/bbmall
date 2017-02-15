<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet"
	href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
	<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
<div class="w-main">
	<div class="w-setup-list" style="border-bottom:none">
		<a href="javascript:window.location.replace('personalCenter-photo.html')">
			<div class="w-setup-content w-height180 w-borderBottom">
				<span class="fl">头像</span> <span class="fr w-margintop1"> <span
					class="w-falseH"></span> <img
					src="${USERIMGSRC}${sessionFrontUser.picUrl}" alt="" width="100px"  /> <img
					src="${SHOPDOMAIN}/front/images/wap/toRight-w.png" alt="" />
				</span>
			</div>
		</a>
	</div>

	<div class="w-setup-list" style="margin-top:0;border-top:none">

		<a href="javascript:window.location.replace('personalCenter-modifyNickname.html')">
			<div class="w-setup-content ">
				<span class="fl">昵称</span> <span class="fr w-margintop1"> <span
					class="w-falseH"></span> <span class="w-color9">${sessionFrontUser.name}</span>
					<img src="${SHOPDOMAIN}/front/images/wap/toRight-w.png" alt="" />
				</span>
			</div>
		</a>
	</div>

	<div class="w-setup-list">
		<a href="javascript:window.location.replace('personalCenter-myAddress.html')">
			<div class="w-setup-content  w-borderBottom">
				<span class="fl">地址管理</span> <span class="fr w-margintop1"> <span
					class="w-falseH"></span> <img
					src="${SHOPDOMAIN}/front/images/wap/toRight-w.png" alt="" />
				</span>
			</div>
		</a> <a href="javascript:window.location.replace('personalCenter-changepassword.html')">
			<div class="w-setup-content ">
				<span class="fl">账户安全</span> <span class="fr w-margintop1"> <span
					class="w-falseH"></span> <span class="w-color9">可修改密码</span> <img
					src="${SHOPDOMAIN}/front/images/wap/toRight-w.png" alt="" />
				</span>
			</div>
		</a>
	</div>

	<form action="${SHOPDOMAIN}/logout.html" id="form_config"
		class="w-margin-top">
		<input type="button" class="w-exitBtn" value="退出登录" />
	</form>


</div>
<jsp:include page="foot-validate.jsp"></jsp:include>
<script type="text/javascript">
	document.title = "我的账户";
	$(".w-exitBtn").click(function() {
		$.cookie("token",null,{path: "/" });
		$("#form_config").submit();
	});
	jQuery(document).ready(function() {
		FormValidation.init();
	});
</script>