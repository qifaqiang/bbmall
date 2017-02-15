<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet"
	href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<style>body{padding-bottom:86px}</style>
<div class="w-main w-paddingbottom">
	<!--头像 昵称 导航-->
	<div class="w-personalCentertop">
	
	
		<div class="w-photoName">
			<a
				href="${empty sessionFrontUser.id?'login.html':'personalCenter-myAccount.html'}">
				<div class="w-photoNameDiv">
				<img class="w-photoNameImg"
				src="<c:if test='${empty sessionFrontUser.id}'>
				${SHOPDOMAIN}/front/images/wap/w-headerPhotoDefault.png
				</c:if><c:if test='${not empty sessionFrontUser.id}'>
				${USERIMGSRC}${sessionFrontUser.picUrl }
				</c:if>"
				alt="头像" />
				</div>
				<p>${empty sessionFrontUser.id?"未登陆":sessionFrontUser.name}</p>
			</a>
		</div>


		<div class="messageNav">
			<ul>
				<!--  <a href="personalCenter-myIntegral.html"><li>我的积分</li></a>-->
				<a href="personalCenter-myCollection.html"><li class="w-margin">我的收藏</li></a>
				<a href="personalCenter-myAddress.html"><li>我的地址</li></a>
			</ul>
		</div>
	</div>

	<!--个人中心订单导航-->
	<div class="w-myOrderNav">
		<a onclick="ulrHtml(0)">
			<div class="w-myOrder">
				<img src="${SHOPDOMAIN}/front/images/wap/myorder.png" alt="订单" /> <span>我的订单</span>
				<div style="float: right" class="w-searchOrder">
					<span>查看全部订单</span> <img
						src="${SHOPDOMAIN}/front/images/wap/enter.png" alt="订单" />
				</div>
			</div>
		</a>



		<div class="w-orderStatus">
			<ul>
				<a onclick="ulrHtml(1)">
					<li><img src="${SHOPDOMAIN}/front/images/wap/NoPay.png"
						alt="待付款" />
						<p>待付款</p></li>
				</a>
				<a onclick="ulrHtml(2)">
					<li><img src="${SHOPDOMAIN}/front/images/wap/forTheGoods.png"
						alt="待收货" />
						<p>待收货</p></li>
				</a>


				<a onclick="ulrHtml(3)">
					<li><img src="${SHOPDOMAIN}/front/images/wap/w-done.png"
						alt="已完成" />
						<p>已完成</p></li>
				</a>
				<a onclick="ulrHtml(4)">
					<li><img src="${SHOPDOMAIN}/front/images/wap/w-toEvaluate.png"
						alt="待评价" />
						<p>待评价</p></li>
				</a>



			</ul>
		</div>
	</div>
	<script type="text/javascript">
		function ulrHtml(num) {
			var toUrl = "personalCenter-myOrder-w.html?url=" + num;
			window.location.href = toUrl;
		}
	</script>
	<!--个人中心操作导航-->
	<div class="w-myOrderNav">
		<a href="personalCenter-mycoupons.html">
			<div class="w-myOrder w-borderBottom">
				<img src="${SHOPDOMAIN}/front/images/wap/w-mycoupons.png"
					alt="我的优惠券" /> <span>我的优惠券</span>
			</div>
		</a> <a href="personalCenter-refundManagement.html">
			<div class="w-myOrder">
				<img src="${SHOPDOMAIN}/front/images/wap/w-refundManagment.png"
					alt="退款管理" /> <span>退款管理</span>
			</div>
		</a>
	</div>

	<div class="w-myOrderNav">
		<a href="personalCenter-customerService.html">
			<div class="w-myOrder w-borderBottom">
				<img src="${SHOPDOMAIN}/front/images/wap/w-contactW.png " alt="联系客服" />
				<span>联系客服</span>
			</div>
		</a> <a href="personalCenter-feedback.html">
			<div class="w-myOrder">
				<img src="${SHOPDOMAIN}/front/images/wap/w-feedback.png" alt="意见反馈" />
				<span>意见反馈</span>
			</div>
		</a> <a href="personalCenter-setUp-wi.html">
			<div class="w-myOrder">
				<img src="${SHOPDOMAIN}/front/images/wap/iconfont-guanyuwomen.png" alt="关于我们" />
				<span>关于我们</span>
			</div>
		</a>

	</div>

	<!--底部导航-->
	<div id="w-footer">

		<ul>
			<a href="index.html"><li><img
					src="${SHOPDOMAIN}/front/images/wap/index.png" alt="首页" />
					<p class="">首页</p></li> </a>

			<a href="productcatalog.html">
				<li><img
					src="${SHOPDOMAIN}/front/images/wap/classification.png" alt="分类" />
					<p>分类</p></li>
			</a>
			<a href="shopping-cart.html">
				<li><img src="${SHOPDOMAIN}/front/images/wap/shoppingCart.png"
					alt="购物车" />
					<p>购物车</p></li>
			</a>
			<a href="javascript:void(0)">
				<li><img src="${SHOPDOMAIN}/front/images/wap/mine1.png"
					alt="我的" />
					<p class="w-active">我的</p></li>
			</a>
		</ul>
	</div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
<script>
	document.title = "个人中心";
	$(function() {
		//getPersonMess();
	});
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() { WeixinJSBridge.call('hideOptionMenu'); });
</script>