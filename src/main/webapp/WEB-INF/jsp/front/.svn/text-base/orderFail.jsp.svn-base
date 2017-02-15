<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link href="${SHOPDOMAIN}/front/css/web/commonW.css" rel="stylesheet"
	type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/share.css" rel="stylesheet"
	type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/globalW.css" rel="stylesheet"
	type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/Personal.css" rel="stylesheet"
	type="text/css">
	
<link href="${SHOPDOMAIN}/front/css/web/pay.css" rel="stylesheet"
	type="text/css">	

<div class="per_toppart">
	<div class="per_top">
		<div class="per_topleft flW ">
			<div class="per_toplogo flW">
				<a href="${SHOPDOMAIN}/index.html"> <img
					src="${SHOPDOMAIN}/front/images/web/pcLogo.png" />
				</a>
			</div>
			<div class="per_toplogotxt flW">
				<span>收银台</span>
			</div>
		</div>
	</div>
	<div class="fox"></div>
</div>
<div class="per_return_main" style="padding-bottom:80px;">
	<div class="per_paywey">
		
		<div class="paFail" style="margin-bottom:50px">
			<img src="${SHOPDOMAIN}/front/images/web/payF.png" />&nbsp;&nbsp;&nbsp;
			${createOrderError }
		</div>
		<div style="text-align:center; ">
			<button class="paySBtn goOn" onclick="goCart()">返回购物车</button>
		</div>
	</div>
</div>
<!--底部-->
<jsp:include page="footShop.jsp"></jsp:include>
<script>
document.title="订单创建失败";
function goCart(){
	window.location.href="shoppingcart.html";
}
</script>
</body>
</html>
