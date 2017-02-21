<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>齐鲁干烘茶城  登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/uniform/css/uniform.default.css"
	rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/select2/select2.css"
	rel="stylesheet" type="text/css" />
<link
	href="${SHOPDOMAIN}/system/theme/assets/admin/pages/css/login-soft.css"
	rel="stylesheet" type="text/css" />
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME STYLES -->
<link
	href="${SHOPDOMAIN}/system/theme/assets/global/css/components-md.css"
	id="style_components" rel="stylesheet" type="text/css" />
<link href="${SHOPDOMAIN}/system/theme/assets/global/css/plugins-md.css"
	rel="stylesheet" type="text/css" />
<link
	href="${SHOPDOMAIN}/system/theme/assets/admin/layout/css/layout.css"
	rel="stylesheet" type="text/css" />
<link id="style_color"
	href="${SHOPDOMAIN}/system/theme/assets/admin/layout/css/themes/darkblue.css"
	rel="stylesheet" type="text/css" />
<link
	href="${SHOPDOMAIN}/system/theme/assets/admin/layout/css/custom.css"
	rel="stylesheet" type="text/css" />
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-md login">
	<!-- BEGIN LOGO -->
	<div class="logo">
		<a href="index.html"> <img
			src="${SHOPDOMAIN}/front/images/whitelogo.png"
			alt="" />
		</a>
	</div>
	<!-- END LOGO -->
	<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
	<div class="menu-toggler sidebar-toggler"></div>
	<!-- END SIDEBAR TOGGLER BUTTON -->
	<!-- BEGIN LOGIN -->
	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form class="login-form" action="login.html" method="post"
			id="login_items">
			<h3 class="form-title">登陆您的账户</h3>
			<div class="alert alert-danger display-hide">
				<button class="close" data-close="alert"></button>
				<span> 请输入您的用户名和密码 </span>
			</div>
			<div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">用户名</label>
				<div class="input-icon">
					<i class="fa fa-user"></i> <input
						class="form-control placeholder-no-fix" type="text"
						autocomplete="off" placeholder="用户名" name="loginname" />
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">密码</label>
				<div class="input-icon">
					<i class="fa fa-lock"></i> <input
						class="form-control placeholder-no-fix" type="password"
						autocomplete="off" placeholder="密码" name="password" />
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn blue pull-right">
					登陆 <i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
		</form>
		<!-- END LOGIN FORM -->
	</div>
	<!-- END LOGIN -->
	<!-- BEGIN COPYRIGHT -->
	<div class="copyright">2016 &copy; 技术支持 xxx信息技术有限公司</div>
	<!-- END COPYRIGHT -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<!--[if lt IE 9]>
<script src="${SHOPDOMAIN}/system/theme/assets/global/plugins/respond.min.js"></script>
<script src="${SHOPDOMAIN}/system/theme/assets/global/plugins/excanvas.min.js"></script>
<![endif]-->
	<script
		src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery.min.js"
		type="text/javascript"></script>
	<script
		src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery-migrate.min.js"
		type="text/javascript"></script>
	<script
		src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script
		src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery.blockui.min.js"
		type="text/javascript"></script>
	<script
		src="${SHOPDOMAIN}/system/theme/assets/global/plugins/uniform/jquery.uniform.min.js"
		type="text/javascript"></script>
	<script
		src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery.cokie.min.js"
		type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script
		src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery-validation/js/jquery.validate.js"
		type="text/javascript"></script>
	<script
		src="${SHOPDOMAIN}/system/theme/assets/global/plugins/backstretch/jquery.backstretch.min.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="${SHOPDOMAIN}/system/theme/assets/global/plugins/select2/select2.min.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script
		src="${SHOPDOMAIN}/system/theme/assets/global/scripts/metronic.js"
		type="text/javascript"></script>
	<script
		src="${SHOPDOMAIN}/system/theme/assets/admin/layout/scripts/layout.js"
		type="text/javascript"></script>
	<script
		src="${SHOPDOMAIN}/system/theme/assets/admin/pages/scripts/login-soft.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<script src="${SHOPDOMAIN}/system/js/jquery.form.js"></script>
	<script>
		jQuery(document).ready(function() {
			Metronic.init(); // init metronic core components
			Layout.init(); // init current layout
			Login.init();
			// init background slide images
			$.backstretch(
							[
									"${SHOPDOMAIN}/system/theme/assets/admin/pages/media/bg/1.jpg",
									"${SHOPDOMAIN}/system/theme/assets/admin/pages/media/bg/2.jpg",
									"${SHOPDOMAIN}/system/theme/assets/admin/pages/media/bg/3.jpg",
									"${SHOPDOMAIN}/system/theme/assets/admin/pages/media/bg/4.jpg" ],
							{
								fade : 1000,
								duration : 8000
							});
			$(".alert-danger span").html(" 请输入您的用户名和密码");
			var rtype="${param.isreg}";
			if(rtype!=""){
				jQuery('.login-form').hide();
				jQuery('.register-form').show();
			}
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>