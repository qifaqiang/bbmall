<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<jsp:include page="top.jsp"></jsp:include>
<title>支付成功</title>
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
<div class="per_return_main" style="padding-top:80px; ">
	<div class="per_paywey" style="padding-bottom:160px;">

		<ul class="payUl">
			<li class="payUlFli"><img
				src="${SHOPDOMAIN}/front/images/web/payS.png" /> 恭喜，您已支付成功！</li>
			<li>支付方式：&nbsp;${zhifu }</li>
			<li>支付流水号：&nbsp;${ordersn }</li>
			<li>支付金额：&nbsp;￥<fmt:formatNumber type="number" value="${orderPrice }" pattern="0.00" maxFractionDigits="2"/>  </li>

		</ul>
		<div style="text-align:center; margin-top:30px;">
			您还可以：
			<button href="" class="paySBtn goOn">继续购物</button>
			<button href="myorder.html" class="paySBtn seeOr">查看订单</button>
		</div>


	</div>
</div>
<!--底部-->
<jsp:include page="footShop.jsp"></jsp:include>
<script>
$(".goOn").click(function(){
	window.location.href="${SHOPDOMAIN}/index.html";
})

$(".seeOr").click(function(){
	window.location.href="${SHOPDOMAIN}/myorder.html";
})
</script>
</body>
</html>
