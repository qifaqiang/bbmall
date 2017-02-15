<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />

<link rel="stylesheet" href="${SHOPDOMAIN}/system/css/globalAll.css" />
<title>404</title>
<style>
html, body {
	height: 100%;
}

@media screen and (max-width:640px) {
	.imgWidth {
		width: 100%;
	}
}
</style>
</head>
<body style="background: #fafafa;">
	<img src="${SHOPDOMAIN}/system/image/404.jpg" class="imgWidth"
		style="max-width: 1000px; display: block; margin: 0 auto" alt="" />
	<div style="text-align: center">
		<a href="javascript:history.go(-1)" class="btn btn-1">返回</a>
	</div>
</body>
</html>