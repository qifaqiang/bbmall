<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/page.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/zoomImg.css" />
<link rel="shortcut icon" href="${SHOPDOMAIN}/front/images/web/ico.ico" />
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/common/js/doT.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/layer/layer.js"></script>
<script src="${SHOPDOMAIN}/front/js/jquery.lazyload.min.js"></script>
<script src="${SHOPDOMAIN}/front/js/web/jquery.zoomImgRollover.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/web/common.js"></script>
</head>
<style>
.shopping-countW {border-radius: 7px 7px 7px 0;behavior: url(${SHOPDOMAIN}/front/js/web/PIE.htc);}
.shoppingCount {border-radius: 7px 7px 0 7px;behavior: url(${SHOPDOMAIN}/front/js/web/PIE.htc);}
</style>
<body>
	<!--头部-->
	<div id="headerW">
		<!--快捷方式-->
		<div class="shorcutW clearfixW">
			<div class="wrapW ">
				<ul class="flW">
					<% /**
					<li class="baseNameW"></li>
					<a class="colorAbdW pointerW" href="${SHOPDOMAIN}/ditu.html">【更改】</a>
					 **/%>
					<a class="colorAbdW pointerW"  href="javaScript:alert('加入收藏夹失败，请使用Ctrl+D快捷键进行添加操作!');">【加入收藏夹】</a>
				</ul>
				<ul class="frW">
					<li>你好，欢迎来到齐鲁干烘茶城！</li>
					<c:if test="${not empty sessionFrontUser}">
						<li class="pLoginW"><a href="${SHOPDOMAIN}/personalCenter.html">${sessionFrontUser.name}</a></li>
						<li class="spaceW"></li>
						<li class="pRegisW"><a href="javascript:void(0)" class="w-exitBtn">退出</a></li>
						<li><a href="${SHOPDOMAIN}/myorder.html">我的订单</a></li>
					</c:if>
					<c:if test="${empty sessionFrontUser}">
						<li class="pLoginW"><a href="${SHOPDOMAIN}/login.html">请登录</a></li>
						<li class="spaceW"></li>
						<li class="pRegisW"><a href="${SHOPDOMAIN}/registered.html">免费注册</a></li>
					</c:if>

					<li class="phoneIconW">
						<div class="phoneContent">移动端</div>
						<div class="dropDownappW noneW">
							<img src="${SHOPDOMAIN}/front/images/web/qrCode.png" width="100" alt="" />
							<p>扫描关注微信</p>
						</div>
					</li>
					<li class="contactIconW">
						<div class="contactW">${SYSPROPORTION.mobile}</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<!--右侧悬浮-->
	<div class="rightFixed">
		<ul class="">
			<li class="shoppingPos" style="cursor: pointer;" title="查看我的收藏">
				<div class="" id="shopcart-count-fixed"></div>
			</li>
			<a title="点击这里给我发消息" href="http://wpa.qq.com/msgrd?v=3&amp;uin=${SYSPROPORTION.qq }&amp;site=www.cactussoft.cn&amp;menu=yes" target="_blank">
				<li class="servicePos"></li>
			</a>
			<li class="collectionPos" style="cursor: pointer;" title="查看我的收藏"></li>
			<a href="#headerW" class="topLink">
				<div>
					<li class="topPos" style="display: none"></li>
				</div>
			</a>
		</ul>
	</div>
	<!-- 首页弹窗 -->
	<div id="indexlayer" style="display: none">
		<img src="${SHOPDOMAIN}/front/images/web/indexlayer.png" alt="" />
		<div id="close"></div>
		<div id="choose"></div>
		<div id="default"></div>
	</div>
	<script>
		var SHOPDOMAIN = "${SHOPDOMAIN}";
		var USERIMGSRC = "${USERIMGSRC}";
		var userId = "${sessionFrontUser.id}";

		$(document).on("click", ".shoppingPos", function(event) {
			if (userId == "") {
				window.location.href = "login.html";
			} else {
				window.location.href = "${SHOPDOMAIN}/shoppingcart.html";
			}
		});
		$(document).on("click", ".collectionPos", function(event) {
			if (userId == "") {
				window.location.href = "login.html";
			} else {
				window.location.href = "${SHOPDOMAIN}/mycollection.html";
			}
		});
	</script>