<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>齐鲁干烘茶城 后台管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="${SHOPDOMAIN}/system/theme/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${SHOPDOMAIN}/system/theme/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
<link href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${SHOPDOMAIN}/system/theme/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
<link href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="${SHOPDOMAIN}/system/theme/assets/global/css/plugins-md.css" rel="stylesheet" type="text/css" />
<script src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<link href="${SHOPDOMAIN}/system/theme/assets/global/css/components-md.css" id="style_components" rel="stylesheet" type="text/css" />
<link href="${SHOPDOMAIN}/system/theme/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css" />
<link id="style_color" href="${SHOPDOMAIN}/system/theme/assets/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css" />
<link href="${SHOPDOMAIN}/system/theme/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-toastr/toastr.min.css" />
<!-- END THEME STYLES -->
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/system/theme/assets/global/plugins/select2/select2.css" />
<link rel="shortcut icon" href="${SHOPDOMAIN}/front/images/web/ico.ico" />
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/system/css/component.css" />
<script type="text/javascript" src="${SHOPDOMAIN}/common/js/doT.js"></script>
</head>
<style>
.line_height {
	line-height: 31px;
	height: 31px;
}
</style>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-md page-header-fixed page-quick-sidebar-over-content ">
	<!-- BEGIN HEADER -->
	<div class="page-header md-shadow-z-1-i navbar navbar-fixed-top">
		<!-- BEGIN HEADER INNER -->
		<div class="page-header-inner">
			<!-- BEGIN LOGO -->
			<div class="page-logo">
				<a href="${SHOPDOMAIN}/system/index.html" class="navbar-brand" style="color: white">齐鲁干烘茶城管理系统 </a>
				<div class="menu-toggler sidebar-toggler hide">
					<!-- DOC: 删除 the above "hide" to enable the sidebar toggler button on header -->
				</div>
			</div>
			<!-- END LOGO -->
			<!-- BEGIN RESPONSIVE MENU TOGGLER -->
			<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"> </a>
			<!-- END RESPONSIVE MENU TOGGLER -->
			<!-- BEGIN TOP NAVIGATION MENU -->
			<div class="top-menu">
				<ul class="nav navbar-nav pull-right">
					<!-- BEGIN NOTIFICATION DROPDOWN -->
					<c:if test="${not empty sessionRoleId}">
						<li class="dropdown">
							<a href="javascript:;" onclick="sysRefresh()" class="dropdown-toggle"> <i class="icon-key"></i> <span class="btnRef" style="color: #fffdfd">刷新系统缓存</span>
							</a>
						</li>
					</c:if>

					<li class="dropdown dropdown-user">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
						data-close-others="true"> <img src="${SHOPDOMAIN}/front/images/people.jpg" class="img-circle hide1" alt=""> <span
							class="username username-hide-on-mobile"> <c:if test="${empty sessionUser.companyName }">默认用户</c:if> <c:if
									test="${not empty sessionUser.companyName }">${sessionUser.companyName }</c:if>
						</span> <i class="fa fa-angle-down"></i>
					</a>
						<ul class="dropdown-menu dropdown-menu-default">
							<li><a href="${SHOPDOMAIN}/system/company/updatesecret.html?typeid=45"> <i class="icon-key"></i> 修改密码
							</a></li>
						</ul></li>
					<!-- END USER LOGIN DROPDOWN -->
					<!-- BEGIN QUICK SIDEBAR TOGGLER -->
					<li class="dropdown dropdown-quick-sidebar-toggler">
						<a href="${SHOPDOMAIN}/system/logout.html" class="dropdown-toggle"> <i
							class="icon-logout"></i>
						</a>
					</li>
					<!-- END QUICK SIDEBAR TOGGLER -->
				</ul>
			</div>
			<!-- END TOP NAVIGATION MENU -->
		</div>
		<!-- END HEADER INNER -->
	</div>
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar-wrapper">
			<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
			<!-- DOC: 更改 data-auto-speed="200" to adjust the sub menu slide up/down speed -->
			<div class="page-sidebar navbar-collapse collapse">
				<!-- BEGIN SIDEBAR MENU -->
				<ul class="page-sidebar-menu page-sidebar-menu-light " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
					<!-- DOC: To remove the sidebar toggler from the sidebar you just need to completely remove the below "sidebar-toggler-wrapper" LI element -->
					<li class="sidebar-toggler-wrapper">
						<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
						<div class="sidebar-toggler"></div> <!-- END SIDEBAR TOGGLER BUTTON -->
					</li>
					<!-- DOC: To remove the search box from the sidebar you just need to completely remove the below "sidebar-search-wrapper" LI element -->
					<li class="start" id="menus0"><a href="javascript:;"> <i class="icon-home"></i> <span class="title">指示板</span> <span class="selected"></span>
							<span class="arrow open"></span>
					</a>
						<ul class="sub-menu">
							<li><a href="${SHOPDOMAIN}/system/index.html?typeid=0"> <i class="icon-bar-chart"></i> 概览
							</a></li>
						</ul></li>

					<c:forEach items="${ROLEWIXIN }" var="roles">
						<li id="menus${roles.id }"><a href="javascript:;"> <i class="fa ${roles.imgUrl }"></i> <span class="title"> ${roles.name } </span> <span
								class="arrow "> </span>
						</a>
							<ul class="sub-menu">
								<c:forEach items="${roles.sysMenuList }" var="rolesecond">
									<li><a href="${SHOPDOMAIN}${rolesecond.linkUrl }?typeid=${roles.id} "> ${rolesecond.name } </a></li>
								</c:forEach>
							</ul></li>

					</c:forEach>
				</ul>
				<!-- END SIDEBAR MENU -->
			</div>
		</div>
		<script>
			var SHOPDOMAIN = "${SHOPDOMAIN}";
			var USERIMGSRC = "${USERIMGSRC}";

            function sysRefresh() {
                $.ajax({
                    url : "${SHOPDOMAIN}/system/sysProportion/sysRefresh.html",
                    type : "post",
                    beforeSend:function(){
                        $(".btnRef").html("刷新中……");
                    },
                    success : function(data) {
                        if (data == "success") {
                            showMessage("刷新成功");
                        } else {
                            showMessage(data);
                        }
                        $(".btnRef").html("刷新系统缓存");
                    }
                });
            }
		</script>
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">