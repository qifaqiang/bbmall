<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'MyJsp.jsp' starting page</title>
<link rel="stylesheet" type="text/css"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/js/webuploader/webuploader.css">
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/js/webuploader/style.css">
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/js/webuploader/demo.css">
<link
	href="${SHOPDOMAIN}/system/theme/assets/global/css/components-md.css"
	id="style_components" rel="stylesheet" type="text/css" />
<!--引入JS-->
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/js/webuploader/webuploader.js"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootbox/bootbox.min.js"
	type="text/javascript"></script>
<script src="${SHOPDOMAIN}/common/js/common.js" type="text/javascript"></script>
</head>

<body>
	<!--dom结构部分-->
	<div id="uploader">
		<div id="uploader-demo">
			<div id="imgPathWebUploader"></div>
			<div id="filePicker" style="">选择图片</div>
			<!--用来存放item-->
			<div style="margin-left: 40px;margin-top: 10px;display: none"
				id="isfirstWebUploader">首图</div>
			<div id="fileList" class="uploader-list filelist"></div>

		</div>
	</div>
	<script>
		var SHOPDOMAIN = "${SHOPDOMAIN}";
	</script>
	<script src="${SHOPDOMAIN}/js/webuploader/wximgupload.js" type="text/javascript"></script>
</body>
</html>
