<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
<div class="w-main">
	 

	<div class="w-orderList w-paddingTB10 w-padding37">
		<p class="w-refundSTitle">
			退款状况:  </span><img src="${SHOPDOMAIN}/front/images/wap/w-success.png" class="w-imgPright" alt="" /><span id ="succorno">
		</p>
		<p class="w-refundContent">
			审核信息：<span id="sysremark"> </span>
		</p>
	</div>
	<div class="w-orderList w-paddingTB10 w-padding37">
		<p class="w-refundContent">
			交易金额：<span id="countmoneys">元</span>
		</p>
		<p class="w-refundReason ">
			退款原因：<span id="remark"></span>
		</p>
		<p class="w-refundContent">
			申请时间：<span id="addtime"></span>
		</p>
		<p class="w-refundContent">
			订单号：<span id="ordersn"></span>
		</p>
		
		<p class="w-refundContent">
			支付来源：<span id="payForm"></span>
		</p>
		<p class="w-refundContent">
			支付时间：<span id="paytime"></span>
		</p>
		
	</div>
</div>
<script>
document.title = "退款状况";
	$(function() {
		var id = getRequest("ords");
		detail(id);
	});
	function detail(id) {
		$.post(SHOPDOMAIN + '/interfaces/orderSele/refuondOrdById.html', {
			id : id
		}, function(data) {
			if (data.res_code == '0') {
				if (data.OrdersReturn.status == 0 ) {
					//审核中
					$(".w-imgPright").attr("src","${SHOPDOMAIN}/front/images/wap/w-tuikuansucc.png");
					$("#succorno").html("审核中");
				}
				if (data.OrdersReturn.status == 1 ) {
					//审核通过
					$(".w-imgPright").attr("src","${SHOPDOMAIN}/front/images/wap/w-success.png");
					$("#succorno").html("退款成功");
				}
				if (data.OrdersReturn.status == 2 ) {
					//审核不通过
					$(".w-imgPright").attr("src","${SHOPDOMAIN}/front/images/wap/w-tuikuanfail.png");
					$("#succorno").html("退款失败");
				}
				if (data.OrdersReturn.sysRemark == null || data.OrdersReturn.sysRemark == "") {
					$("#sysremark").html("暂无回复");
				} else {
					$("#sysremark").html(data.OrdersReturn.sysRemark);
				}
				$("#remark").html(data.OrdersReturn.remark);
				$("#addtime").html(timeStamp2String(data.orders.addtime));
				$("#countmoneys").html("￥"+data.orders.orderAccount);
				$("#ordersn").html(data.orders.ordersn);
				if(data.orders.payForm==1){
					$("#payForm").html("pc(支付宝)");
				}
				if(data.orders.payForm==2){
					$("#payForm").html("微信支付");
				}
				if(data.orders.payForm==3){
					$("#payForm").html("pc(银联支付)");
				}
				if(data.orders.payForm==4){
					$("#payForm").html("wap(支付宝)");
				}
				if(data.orders.payForm==5){
					$("#payForm").html("微信支付"); 
				}
				if(data.orders.payForm==6){
					$("#payForm").html("wap(银联支付)"); 
				}
				if(data.orders.payForm==8){
					$("#payForm").html("价格为零免支付"); 
				}
				$("#paytime").html(timeStamp2String(data.orders.payTime));
			}
		}, "json").error(function() {
			showError();
		});
	}
</script>
