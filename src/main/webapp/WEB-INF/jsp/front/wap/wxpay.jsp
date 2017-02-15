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
<title>微支付</title>
<meta content="telephone=no, address=no" name="format-detection">
<meta
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport">
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>

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

.dialog {
	position: absolute;
	width: 100%;
	height: 100%;
	left: 0;
	top: 0;
	display: none;
}

.dialog.on {
	display: block;
}

.dialog .dialog_mask {
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	z-index: 1000;
	background: rgba(0, 0, 0, 0.3);
}

.dialog .dialog_body {
	position: absolute;
	left: 50%;
	top: 50%;
	width: 180px;
	z-index: 1100;
	background: #ffffff;
	border-radius: 3px;
	margin: -50px -90px;
	padding: 15px 10px 15px;
	font-size: 12px;
	color: #333333;
	text-align: center;
}

.dialog .dialog_body p {
	width: 160px;
	vertical-align: middle;
}
</style>
</head>
<body>
	<section class="body">
		<div class="ordernum">订单编号：${orderSn}</div>
		<div class="money">
			共计金额￥<fmt:formatNumber value="${price }" pattern="##.##"
				minFractionDigits="2"></fmt:formatNumber>
		</div>
		<div class="time">下单时间：${time}</div>
		<a href="javascript:void(0);" class="btn" id="getBrandWCPayRequest">确认支付</a>
	</section>

	<section id="dialog" class="dialog">
		<div class="dialog_mask">&nbsp;</div>
		<div class="dialog_body">
			<p id="dialog_content">正在查询支付结果...</p>
		</div>
	</section>

</body>
<script type="text/javascript">
	var obj = eval(${json});
	if (obj.errorMessage != "") {
		$("#getBrandWCPayRequest").html(obj.errorMessage + "");
	} else if (parseFloat(obj.agent) < 5) {
		alert("您的微信版本低于5.0无法使用微信支付");
	} else {
		document.addEventListener(
			'WeixinJSBridgeReady',
			function onBridgeReady() {
				//公众号支付
				jQuery('a#getBrandWCPayRequest').click(function(e) {
						WeixinJSBridge.invoke(
							'getBrandWCPayRequest',
										{
											"appId" : obj.appId, //公众号名称，由商户传入  
											"timeStamp" : obj.timeStamp, //时间戳，自 1970 年以来的秒数  
											"nonceStr" : obj.nonceStr, //随机串  
											"package" : obj.packageValue, //<span style="font-family:微软雅黑;">商品包信息</span>  
											"signType" : obj.signType, //微信签名方式:  
											"paySign" : obj.paySign//微信签名  
										},
										function(res) {
											WeixinJSBridge.log(res.err_msg);
											$("#dialog").show();
											if (res.err_msg == 'get_brand_wcpay_request:ok') {
											    timer();
											} else if (res.err_msg == 'get_brand_wcpay_request:cancel') {
												//弹出微信支付的返回结果，比如微信支付接口繁忙、银行暂停支持等
												alert("您已取消支付。");
												$("#dialog").hide();
												$dialog_content.html("正在查询支付结果...");
											} else {
												alert("很抱歉，付款失败！");
												$("#dialog").hide();
												$dialog_content.html("正在查询支付结果...");
											}
											return false;
										});
					});
				//WeixinJSBridge.log('yo~ ready.');
			}, false);
	}
	var $dialog_content = $("#dialog_content"), _time = 3;
	function timer() {
		$dialog_content.html("恭喜您，付款成功！<br/><span style='display:inline-block;width:20px;text-align:center;'>"
						+ (_time--)+ "</span> 秒后跳转页面").show();
		if (-1 == _time) {
			window.location.href = obj.sendUrl;
		} else {
			setTimeout(function() {timer();}, 1000);
		}
	};
</script>
</html>