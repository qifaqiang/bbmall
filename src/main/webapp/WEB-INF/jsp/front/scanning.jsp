<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>齐鲁干烘茶城</title>
<!--全局初始化-->
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/commonW.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/globalW.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/login.css" />
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
<link rel="shortcut icon" href="${SHOPDOMAIN}/front/images/web/ico.ico" />
</head>
<body>
	<div id="loginHeader" class="wrapW">
		<div class="loginHeaderL">
			<a href="index.html"> <img
				src="${SHOPDOMAIN}/front/images/web/pcLogo.png" width="210"
				height="45" alt="" /></a> &nbsp; &nbsp; &nbsp;掌上齐鲁干烘茶城，购物更便捷！
		</div>
	</div>
<style>
input:-webkit-autofill {background-color: red !important;background-image: none;
}
</style>
<div class="loginForm" style="height: 590px;  background-image: url(${SHOPDOMAIN}/front/images/web/scanningBan.jpg)">
</div>
	<div class="footLogin ">
		<div class="loginFirstL">客服热线：${SYSPROPORTION.mobile}</div>
		<div class="loginsecL">版权所有 © ${SYSPROPORTION.copyrights}</div>
	</div>
</body>
</html>