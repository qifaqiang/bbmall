<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="top.jsp"></jsp:include>
<link href="${SHOPDOMAIN}/front/css/web/commonW.css" rel="stylesheet"
	type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/share.css" rel="stylesheet"
	type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/globalW.css" rel="stylesheet"
	type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/Personal.css" rel="stylesheet"
	type="text/css">
<script src="js/ntab.js"></script>
<style>body{background-color:white}</style>
<input type="hidden" id="ordersn" value="${orderid }">
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
<div class="per_return_main">
	<div class="per_money_submit">
		<div class="per_money_submitleft flW">
			<img src="${SHOPDOMAIN}/front/images/web/per_return3.png" />
		</div>
		
		<div>
			<div class="per_money_submitright flWt flW" >
			<h1>您已成功提交订单！  订单号：${ pcOrdersn}</h1>
			<h3>请在<span style="color:red">${SYSPROPORTION.autoCancelTime }小时</span>内付款，我们将尽快为您送达！</h3>
			
		</div>
		<ul class="frW" style="margin-right:100px;">
				<li style="text-align:center;line-height:30px;">订单金额：¥<fmt:formatNumber value="${orderAccount }" type="currency" pattern=".00"/></li>
				<li></li>
				<li>
					<div class="per_money_btn" style="border:none;line-height:30px;">
						<a target="_blank" href="${SHOPDOMAIN}/myorder_details.html?id=${orderid}">查看订单详情>></a>
					</div>
				</li>
			</ul>
		</div>
		
		<div class="fox"></div>
	</div>
	<div class="per_money_pay">
		<h3>请选择支付方式</h3>
		<p>
			选择支付方式支付<span>${orderAccount }</span>元
		</p>
		<div class="per_money_payway">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tbody>
					<tr>
						<td><input type="radio" name="radio" id="radio1"
							value="wxpay"> <label for="radio1"><img
								src="${SHOPDOMAIN}/front/images/web/per_payicon2.png" disabled
								width="198" height="78" alt="" /></label></td>
						<td><input type="radio" name="radio" id="radio2"
							value="alipay"> <label for="radio2"><img
								src="${SHOPDOMAIN}/front/images/web/per_payicon1.png" disabled
								width="198" height="78" alt="" /></label></td>
						<td><input type="radio" name="radio" id="radio3"
							value="unionpay"> <label for="radio3"><img
								src="${SHOPDOMAIN}/front/images/web/per_payicon3.png" disabled
								width="198" height="78" alt="" /></label></td>
					</tr>

				</tbody>
			</table>
			<h3>
				<input type="button" class="per_payinforbtn" name="submit"
					id="submitt" value="立即支付">
			</h3>
		</div>
	</div>
	<div class="per_money_other">
		<i>您还可以：</i> <i><a href="${SHOPDOMAIN}/index.html">返回首页</a></i>
		<i><a href="${SHOPDOMAIN}/myorder.html">查看我的订</a>单</i>
	</div>
</div>
<!--底部-->
<!--右侧悬浮-->
<jsp:include page="footShop.jsp"></jsp:include>
<script type="text/javascript">
	document.title="齐鲁干烘茶城-收银台";
	$(function() {
		$("#submitt")
				.click(
						function() {
							var type = $("input[type='radio']:checked").val();
							if (type == null || type == "") {
								showMessageAutoTime("请选择您的支付方式", 2500);
							} else {
								if (type == "wxpay") {
									window.location.href = "${SHOPDOMAIN}/wap/fontOrder/shoppingcartWxScanPay.html?orderid="
											+ ${orderid}+"&ordersn=${param.pcOrdersn}";
								}else if (type == "unionpay") {
									showIsPay();
									window.open("${SHOPDOMAIN}/unionpay/jstoorder.html?ordersn=${param.pcOrdersn}");
								}else if (type == "alipay") {
									showIsPay();
									window.open("${SHOPDOMAIN}/alipay/jstoorder.html?ordersn=${param.pcOrdersn}");
								}
							}
						});
	});
	
	
	function showIsPay() {
		layer.msg('请您在新打开的支付平台页面进行支付，支付完成前请不要关闭该窗口', {
			skin : 'layer-ext-myskinGlobal',
			closeBtn : 2,
			shade : 0.3,
			btn : [ '已完成支付', '重新支付' ],
			btn1 : function() {
				window.location.href = SHOPDOMAIN + '/myorder.html';
			},
			cancel : function() {
				layer.close();
			},
			time : 0

		});
	}
</script>
</body>
</html>

