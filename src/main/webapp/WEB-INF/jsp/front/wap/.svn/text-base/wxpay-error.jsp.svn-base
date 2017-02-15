<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付失败</title>
<meta content="telephone=no, address=no" name="format-detection">
<meta
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport">
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>

<style type="text/css">
html,body {
	height: 100%;
	width: 100%;
}

* {
	margin: 0px;
	padding: 0px;
	-webkit-box-sizing: border-box;
}

.body {
	text-align: center;
	width: 100%;
	padding: 60px 20px;
}

.body .ordernum {
	font-size: 14px;
	line-height: 30px;
}

.body .ziti {
	font-size: 14px;
	line-height: 30px;
}

.body .money {
	font-size: 20px;
	font-weight: bold;
	line-height: 60px;
}

.body .time {
	font-size: 16px;
	font-weight: bold;
	line-height: 30px;
}

.body .btn {
	display: block;
	background: #25a52e;
	text-decoration: none;
	border-radius: 2px;
	color: #fff;
	height: 44px;
	line-height: 44px;
	font-size: 18px;
	margin-top: 20px;
}
</style>
</head>
<body>
	<section class="body">
		<div class="money">
			${error }
		</div>
		<a href="javascript:void(0);" class="btn" id="getBrandWCPayRequest">查看订单</a>
	</section>
</body>
<script>
	$("#getBrandWCPayRequest").click(function() {
		window.location.href = "${SHOPDOMAIN}/wap/personalCenter.html";
	})
</script>
</html>