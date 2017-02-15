<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet"
	href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<div class="w-main">
	<div class="w-setup-list">
	
		<div class="w-setup-content" >
			<span id="aboutus">${SYSPROPORTION.aboutUs}</span>
		</div>
	</div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
<script>
	document.title = "关于我们";
</script>