<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/HBox.css" />
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
<script id="interpolationtmpl" type="text/x-dot-template">
{{for(var i=0;i<it.length;i++){ }}
{{var uslog=it[i];}}
    <div class="w-content">
		<div class="w-orderStatusNav w-show">
			<div class="w-orderList">
				<div class="w-orderList-title-details">
					<span class="fl" id="people">收货人：{{=uslog.consignee}}</span> 
                    <div style="clear: both"></div>
                     <span id="telphone" >电&nbsp;&nbsp;&nbsp;话：{{=uslog.mobile}}</span>
					<div style="clear: both"></div>
					<span id="address">收货地址： {{=uslog.address_name}}</span>
				</div>
			</div>
           
			<div class="w-orderList">
				<div class="w-orderList-title">
					<span  class="fl">{{=uslog.companyName}}</span> <span class="fr" style="display: inline-block">
                    {{ if(uslog.status=="0") {}}
			          	      用户取消 
			        {{}}}
                     {{ if(uslog.status=="11") {}}
			          	  未付款
			        {{}}}
                     {{ if(uslog.status=="20") {}}
			          	    已付款
			        {{}}} 
                     {{ if(uslog.status=="22") {}}
			          	     商家确认 
			        {{}}}
                    {{ if(uslog.status=="30") {}}
			          	  发货
			        {{}}}
                    {{ if(uslog.status=="40") {}}
			          	  完成
			        {{}}}
                     {{ if(uslog.status=="50") {}}
			          	  申请退款
			        {{}}}
                     {{ if(uslog.status=="60") {}}
			          	 退款成功 
			        {{}}}
                     {{ if(uslog.status=="100") {}}
			          	 系统自动取消
			        {{}}} 
                      </span>
				</div>
               {{for(var j=0;j<uslog.orders.length;j++){ }}
                   {{var ord=uslog.orders[j];}}
          <a href="productShow.html?prodId={{=ord.prod_id}}">
				  <div class="w-orderList-content">
					  <img src="${USERIMGSRC}{{=imgZuhe(ord.prod_uri,'_300')}}" alt="" /> <span> <em class="ellipsis">{{=ord.prod_name}}</em><br /> <em class="w-specifications">规格：{{=ord.prod_count}}{{=ord.prod_spec_name}}</em><br />
						<em> <i class="w-totalPrice">￥{{=(ord.prod_price).toFixed(2)}}</i> <i class="fr">x{{=ord.prod_count}}</i>
					  </em>
					  </span>
			      </div>
           </a>
               {{}}} 
			</div>
           
			<!--商品金额-->
			<div class="w-orderList">
				<div class="w-orderList-title-details" style="overflow: hidden">
					<span class="fl">商品金额</span> <span class="fr " style="display: inline-block;color: #ff0000">-￥{{=(uslog.order_price).toFixed(2)}}</span>
					<div style="clear: both"></div>
					<span class="fl">优惠价格</span> <span class="fr " style="display: inline-block;color: #ff0000">
           
                  -￥ {{ if({{=uslog.coupons_price==0||uslog.coupons_price==null}}) {}}
			          	0.00
			      {{} else{ }}
			           {{=(uslog.coupons_price).toFixed(2)}}
		        	{{ }}}

                  </span>
                   <div style="clear: both"></div>
                    <span class="fl">运费价格</span> <span class="fr " style="display: inline-block;color: #ff0000">
                   -￥{{ if({{=uslog.ship_price}}==0) {}}
			          	0.00
			      {{} else{ }}
			          {{=(uslog.ship_price).toFixed(2)}}
		        	{{ }}}
                 </span>

					<div style="clear: both"></div>
                    <span class="fl">促销优惠价格</span> <span class="fr " style="display: inline-block;color: #ff0000">
                   {{ if({{=uslog.promotion_price}}==null) {}}
			         -￥ 0.00
			        {{} else{ }}
			         -￥ {{=(uslog.promotion_price).toFixed(2)}}
		        	{{ }}}
                  </span>
                   <div style="clear: both"></div>
                    <!--<span class="fr w-realPay" style="display: inline-block">实付款：<i style="color: #ff0000">￥{{=(uslog.order_account).toFixed(2)}}</i></span>-->
				</div>
			</div>
			<!--订单信息-->
			<div class="w-orderList">
				<div class="w-orderList-title-details">
					<p>订单编号：{{=uslog.ordersn}}</p>
                    <p>下单时间：{{=uslog.addtime}}</p>
                     {{ if(uslog.status=="20"||uslog.status=="22"||uslog.status=="30"||uslog.status=="40"||uslog.status=="50") {}}
                     <p>支付方式：
                    {{ if(uslog.pay_type==1) {}}
			          	      支付宝支付
			        {{}}}
                     {{ if(uslog.pay_type==2) {}}
			          	      微信支付
			        {{}}}
                     {{ if(uslog.pay_type==3) {}}
			          	     银联支付
			        {{}}}
                    <p>支付时间：{{=uslog.pay_time}}</p>
					{{ if(uslog.user_ship_type==1) {}}
						<p style="color:red">用户自提(提货码)：{{=(uslog.pick_code==undefined?"等待接单":uslog.pick_code)}}
						</p>
					{{}}}
                    {{}}}
				</div>
			</div>
		</div>
	</div>
{{}}}
</script>

<div class="w-main">

	<!--头部-->
	<div id="showlist"></div>
</div>
<!--点击领取红包-->
<%-- <div class="w-redBag bounceInDown ">

	<img src="${SHOPDOMAIN}/front/images/wap/w-redBag.png" alt="" />

</div>
<!--点击弹出发红包蒙版-->
<div id="HBox" class="dialog" style="display: none">
	<div class="w-boxDetails">
		<p class="w-congratulation">恭喜获得10个红包可以分享</p>
		<p class="w-shareInstructions">分享红包给好友，红包用于抵扣在线支付金额</p>

		<button class="w-shareRedBagBtn" id="w-shareRedBagBtn">发红包</button>
	</div>
</div> --%>

<script src="${SHOPDOMAIN}/wap/jquery.hDialog.js"></script>
<script>
	/* $(function() {
		//        自定义发红包弹窗
		$('.w-redBag').hDialog({
			effect : 'fadeOutDown',
			width : 547,
			height : 735,
			closeBg : ''

		});
		//        点击发红包按钮
		$('#w-shareRedBagBtn').on('click', function() {

		});

	}); */
</script>
<jsp:include page="foot.jsp"></jsp:include>
<script>
	document.title = "已取消订单";
	jQuery(document).ready(function() {
		cho = getRequest("id");
		$.post(SHOPDOMAIN + '/interfaces/orderSele/orderDetailsByOrdersn.html', {
			cho : cho
		}, function(data) {
			if (data.res_code == '0') {
				var evalText = doT.template($("#interpolationtmpl").html());
				$("#showlist").html(evalText(data.list));
			} else {
				showMessage(data.message);
			}
		}, "json").error(function() {
			showError();
		});
		//FormValidation.init();
	});

	//    退款
	$(document).on('click', '#w-refund', function(e) {
		var refound = $(".refound").attr("refoundId");
		refoundDeal(refound);
	});

	function refoundDeal(id) {
		$.post(SHOPDOMAIN + '/interfaces/orderSele/refuondOrd.html', {
			id : id
		}, function(data) {
			if (data.res_code == '0') {
				showMessageRefresh(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}
</script>