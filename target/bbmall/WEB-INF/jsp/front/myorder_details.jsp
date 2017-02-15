<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link href="${SHOPDOMAIN}/front/css/web/share.css" rel="stylesheet" type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/Personal.css" rel="stylesheet" type="text/css">
<script src="${SHOPDOMAIN}/front/js/web/ntab.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
<style>
.per_toplogotxt {margin-left: 15px;width: 120px;}
body{background-color:white}</style>
<script id="interpolationtmpl" type="text/x-dot-template">
{{for(var i=0;i<it.length;i++){ }}
{{var uslog=it[i];}}  
<div class="per_toppart">
	<div class="per_top">
		<div class="per_topleft flW ">
			<div class="per_toplogo flW">
				<a href="index.html"><img src="${SHOPDOMAIN}/front/images/web/pcLogo.png" /></a>
			</div>
			<div class="per_toplogotxt flW">
				{{ if(uslog.status=="11") {}}
					<span>拍下商品</span>
				{{}}}
				{{ if(uslog.status=="20") {}}
					<span>付款</span>
				{{}}} 
				{{ if(uslog.status=="22") {}}
					<span>商家确认</span> 
				{{}}}
				{{ if(uslog.status=="30") {}}
					<span>卖家已发货</span>
				{{}}}
				{{ if(uslog.status=="40") {}}
					<span>已成交</span>
				{{}}}
				{{ if(uslog.status=="50") {}}
					<span>申请退款</span>
				{{}}}
				{{ if(uslog.status=="60") {}}
					<span>退款成功 </span>
				{{}}}
				{{ if(uslog.status=="100") {}}
					<span>系统自动取消
				{{}}} 
			</div>
		</div>
	</div>
	<div class="fox"></div>
	</div>

	<div class="per_return_main">
		<!--订单流程-->
		<div class="per_details_step">
			<div class="per_details_steptop">
				<ul>
					<li class="color">拍下商品</li>
					<li class="padleft color">付款</li>
					<li class="color padleft2">基地配送</li>
					<li class="color padleft3">确认收货</li>
				</ul>
			</div>
			<div class="per_details_stepmid">
				{{ if(uslog.status=="0"||uslog.status=="100") {}}
					<div class="per_details_stepmidinfor Order"></div>
				{{}}}    
				{{ if(uslog.status=="11") {}}
					<div class="per_details_stepmidinfor Order"></div>
				{{}}}
				{{ if(uslog.status=="20") {}} 
					<div class="per_details_stepmidinfor pay"></div>
				{{}}}
				{{ if(uslog.status=="22") {}} 
					<div class="per_details_stepmidinfor pay"></div>
				{{}}}
				{{ if(uslog.status=="30") {}} 
					<div class="per_details_stepmidinfor confirm"></div>
				{{}}}  
				{{ if(uslog.status=="40") {}}
					<div class="per_details_stepmidinfor"></div>
				{{}}}
			</div>
			<div class="per_details_steptop">
				<ul>
					<li class="padleft4">{{ if(uslog.addtime!=undefined) {}}{{=timeStamp2String(uslog.addtime)}}{{}}}</li> 
					<li class="padleft5">{{ if(uslog.pay_time!=undefined) {}} {{=timeStamp2String(uslog.pay_time)}}{{}}}</li>
					<li class="padleft6">{{ if(uslog.ship_time!=undefined) {}} {{=timeStamp2String(uslog.ship_time)}}{{}}}</li>
					<li class="padleft7">{{ if(uslog.accept_time!=undefined) {}}{{=timeStamp2String(uslog.accept_time)}}{{}}}</li>
				</ul>

			</div>
		</div>
       <div class="per_return_infor">
			<div class="per_receive">
				<div class="per_return_productleft flW">
					<h3>订单信息</h3>
					{{for(var j=0;j<uslog.orders.length;j++){ }}
						{{var ord=uslog.orders[j];}}
						<dl>
						<a href="productDetail.html?prodId={{=ord.prod_id}}"><img src="${USERIMGSRC}{{=imgZuhe(ord.prod_uri,'_300')}}" /></a>
						<dt>
							{{=ord.prod_name}}  <br/>
							规 &nbsp;&nbsp;格:<span>{{=ord.prod_spec_name}}</span>
							x{{=ord.prod_count}}
						</dt>
						<div class="fox"></div>
					</dl>
					{{}}}  
					<ul>
				        <li><span>基地: </span>{{=uslog.companyName}}</li>
						<li><span>订单编号: </span>{{=uslog.ordersn}}</li>
                        <li><span>下单时间: </span>{{ if(uslog.addtime!="undefined") {}}{{=timeStamp2String(uslog.addtime)}}{{}}}</li>
						<li><span>商品金额: </span>{{=(uslog.order_price).toFixed(2)}} 元</li>
						{{ if(uslog.user_ship_type==1) {}}<li><span>用户自提(提货码): </span>{{=(uslog.pick_code==undefined?"等待接单":uslog.pick_code)}}</li> {{}}}
						<li><span>优惠价格: </span>
							{{ if(uslog.coupons_price==null||uslog.coupons_price=="undefined"||uslog.coupons_price=="") {}}-0.00 元
							{{} else{ }}-{{=(uslog.coupons_price).toFixed(2)}} 元
							{{}}}
                        </li>
                        <li><span>运费价格: </span> 
							{{ if(uslog.ship_price==null||uslog.ship_price=="undefined"||uslog.ship_price=="") {}}0.00 元
			     		    {{} else{ }}
			          			  {{=(uslog.ship_price).toFixed(2)}}元
		        			{{}}}
						</li>
 						<li><span>促销优惠: </span>
                            {{ if(uslog.promotion_price==null||uslog.promotion_price=="undefined"||uslog.promotion_price=="") {}}-0.00元
			        		{{} else{ }}{{=(uslog.promotion_price).toFixed(2)}}元
		        			{{}}}
                        </li>
                        {{ if(uslog.first_order_price!=null&&uslog.first_order_price!="undefined"&&uslog.first_order_price!=0) {}}
                           <li><span>首单立减: </span>{{=(uslog.first_order_price).toFixed(2)}}元</li> 
                        {{}}}
						<li><span>总计: </span><span id="allprice">{{=(uslog.order_account).toFixed(2)}}</span>元</li>
						{{ if(uslog.status=="20"||uslog.status=="22"||uslog.status=="30"||uslog.status=="40"||uslog.status=="50") {}}
							<li>
								<span>支付方式: </span>
								{{ if(uslog.pay_form==1) {}}  pc(支付宝){{}}}
                                {{ if(uslog.pay_form==2) {}}微信支付{{}}}
                     			{{ if(uslog.pay_form==3) {}}pc(银联支付){{}}}
                    			{{ if(uslog.pay_form==4) {}}wap(支付宝){{}}}
                    			{{ if(uslog.pay_form==5) {}}微信支付{{}}}
                   				{{ if(uslog.pay_form==6) {}}wap(银联支付){{}}}
								{{ if(uslog.pay_form==8) {}}价格为零免支付{{}}}
                            </li>
                   			<li><span>支付时间:</span>{{=timeStamp2String(uslog.pay_time)}}</li> 
							<li><span>支付流水号:</span>{{=uslog.trading_code}}</li> 
                        {{}}}
  						<li><span>备注: </span>{{ if(uslog.remark!="") {}}{{=uslog.remark}}  {{} else{  }} 暂无{{}}}</li>
					</ul>

					<ul style="border-top: 1px solid #e5e5e5;"><li>
						<span>收货信息: </span> {{=uslog.consignee}}<br/>{{=uslog.mobile}}<br/>{{=uslog.address_name}}
					</li></ul>
				</div>
				<div class="per_details_pay flW">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody>
						{{ if(uslog.status=="0"||uslog.status=="100") {}}
							<tr>
								<td align="left" style="color:color: #e70012;">订单状态： 
								 该订单已取消,请重新下单。<a href="index.html" >去逛逛>></a>取消时间： {{=timeStamp2String(uslog.cancel_time)}}</td>
							</tr>
						{{}}}
						{{ if(uslog.status=="11") {}}
							<tr>
								<td  align="left">订单状态： 您的商品已拍下，等待买家付款</td>
							</tr>
							<tr>
								<td  class="color" >请在<span id="sysdate"></span>小时内付款，若未及时付款，系统将自动取消订单</td>
							</tr>
							<tr>
								<td align="left"><span class="flW">您还可以：</span><a class="per_details_btn2 flW" style="color:white" href="" id="toPay">立即付款</a><div class="per_details_btn flW" style="cursor:pointer" onclick="cancelOrder({{=uslog.id}});">取消订单</div>
									<span class="fss" locationId={{=uslog.id}}></span></td>
							</tr>
						{{}}}
						{{ if(uslog.status=="20") {}} 
							<tr>
								<td align="right" width="18%">订单状态： 
								</td>
								<td> 买家已付款，等待基地配送</td>
							</tr>
						{{}}}
						{{ if(uslog.status=="22") {}} 
							<tr>
								<td align="right"  width="18%">订单状态： 
								</td>
								<td>商家已确认，等待基地配送</td>
							</tr>
						{{}}}
						{{ if(uslog.status=="30") {}} 
							<tr>
								<td align="right"  width="18%">订单状态： 
								</td>
								<td> 基地已经配送，等待买家确认收货</td>
							</tr>
						{{}}}  
						{{ if(uslog.status=="22"||uslog.status=="30") {}}
							<tr class="per_receipt_red"  >
 								<td   align="right" width="18%">温馨提示：</td>
								<td>请确认收到货物后再点击确认收货</td>
							</tr>
							<tr>
								<td  align="right" > </td>
								<td><input type="button" class="per_feedback_btn" onclick="confirmreceipt({{=uslog.id}},{{=uslog.status}})" style="cursor:pointer" value="确认收货"></td>
							</tr>
						{{}}}
						{{ if(uslog.status=="40") {}} 
							<tr>
								<td width="18%" align="right">订单状态：</td>
								<td width="82%" class="color"><ul><li><img src="${SHOPDOMAIN}/front/images/web/per_return3.png" width="37" height="38" alt=""/></li>
								<li>交易已成功</li></ul></td>
							</tr>
							{{ if(ord.location_status==0) {}}{{}}}
							<tr>
								<td align="right">您还可以：</td>
								<td><div class="per_details_btn2 flW" style="cursor:pointer;margin-left:0;" onclick="javascript:window.location.href='myorder.html?ids=4'">立即评价</div> </td>
							</tr>
                                    
						{{}}}
						{{ if(uslog.status=="20"||uslog.status=="22"||uslog.status=="30"||uslog.status=="40") {}}
							{{ if(uslog.is_over=="0") {}}
							<tr>
								<td align="right" > 您还可以： </td>
								<td><div class="per_details_btn"  style="cursor:pointer;margin-left:0px" onclick="javascript:window.location.href='refundmanagement_applyinfor.html?ords={{=uslog.id}}'">申请退款</div></td>
							</tr>
							{{}}}
						{{}}}
						{{ if(uslog.status=="60") {}}
							<tr>
								<td align="right"> 您还可以：</td> 
								<td><a class="per_details_btn" href="index.html">继续逛逛</a></td>
							</tr>
						{{}}}
						</tbody>
					</table>
				</div>
				<div class="fox"></div>
			</div>
			<div class="fox"></div>
		</div>
	</div>
		
{{}}} 
</script>

<div id="showlist"></div>
<!--右侧悬浮-->
<jsp:include page="footShop.jsp"></jsp:include>
<script src="${SHOPDOMAIN}/front/js/wap/jquery.hDialog.js"></script>
<script>
	document.title = "个人中心-我的订单";
	jQuery(document).ready(function() {
		cho = getRequest("id");
		var usr = getRequest("usr");
		if (usr == 1) {
			window.location.replace("personalCenter-orderDetails.html?id=" + cho);
			usr = 0;
		}
		$.post(SHOPDOMAIN + '/interfaces/orderSele/orderDetailsByOrdersn.html', {
			cho : cho
		}, function(data) {
			if (data.res_code == '0') {
				var evalText = doT.template($("#interpolationtmpl").html());
				$("#showlist").html(evalText(data.list));
				$("#sysdate").html(data.systim);
				var url=SHOPDOMAIN+"/wap/fontOrder/toPcToPay.html?orderid=${param.id}&orderAccount="+$("#allprice").html()+"&pcOrdersn="+data.ordersn
				$("#toPay").attr("href",url);
						
			} else {
				if (data.res_code == '1') {
					showm(data.message);
					window.setTimeout((window.location.href = "index.html"), 10000);
				}
			}
		}, "json").error(function() {
			showError();
		});
		//FormValidation.init();
	});

	//    取消订单确认
	function cancelOrder() {
		var id = $(".fss").attr("locationId");
		showMessquxiao("确定取消订单？", id);

	}
	function showMessquxiao(message, id) {
		layer.msg(message, {
			skin : 'layer-ext-myskinGlobal',
			closeBtn : 2,
			shade : 0.3,
			btn : [ '确定' ],
			btn1 : function() {
				deleteOrd(id);
			},
			time : 0
		});
	}
	function deleteOrd(id) {
		chos = getRequest("id");
		$.post(SHOPDOMAIN + '/interfaces/orderSele/deleteOrders.html', {
			id : id,
			"cho" : chos
		}, function(data) {
			if (data.res_code == '0') {
				showMessageRefresh(data.message);
			} else {
				showMessageRefresh(data.message);
			}
		}, "json").error(function() {
			showMessageRefresh(data.message);
		});
	}
	//确认收货
	function confirmreceipt(id, status) {
		$.post(SHOPDOMAIN + '/interfaces/orderSele/confirmReceiptPc.html', {
			id : id,
			status : status
		}, function(data) {
			if (data.res_code == '0') {
				showMessageRefresh(data.message);
			} else {
				showMessageRefresh(data.message);
			}
		}, "json").error(function() {
			showMessageRefresh(data.message);
		});
	}
</script>