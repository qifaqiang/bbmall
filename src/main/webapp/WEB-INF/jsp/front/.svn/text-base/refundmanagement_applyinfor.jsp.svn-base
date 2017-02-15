<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${SHOPDOMAIN}/front/css/web/share.css" rel="stylesheet" type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/Personal.css" rel="stylesheet" type="text/css">
<script src="${SHOPDOMAIN}/front/js/web/ntab.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
<script id="interpolationtmpl" type="text/x-dot-template">
{{for(var i=0;i<it.length;i++){ }}
{{var uslog=it[i];}}  
<h3>退款产品</h3>
	{{for(var j=0;j<uslog.orders.length;j++){ }}
		{{var ord=uslog.orders[j];}}
		<dl>
			<a href="productDetail.html?prodId={{=ord.prod_id}}"><img src="${USERIMGSRC}{{=imgZuhe(ord.prod_uri,'_300')}}" /></a>
			<dt>
				{{=ord.prod_name}} <span>{{=ord.prod_spec_name}}</span>
			</dt>
			<div class="fox"></div>
		</dl>
		{{ if(j==uslog.orders.length-1) {}}
		<ul>
			<li><span>商品金额：</span>{{=(uslog.order_price).toFixed(2)}} 元</li>
			<li><span>优惠价格：</span>{{ if(uslog.coupons_price==null||uslog.coupons_price=="undefined"||uslog.coupons_price=="") {}}-0.00 元
				{{} else{ }}-{{=(uslog.coupons_price).toFixed(2)}} 元
				{{}}}
			</li>
			<li><span>运费价格：</span>{{ if(uslog.ship_price==null||uslog.ship_price=="undefined"||uslog.ship_price=="") {}}0.00 元
				{{} else{ }}{{=(uslog.ship_price).toFixed(2)}} 元
				{{}}}
			</li>
			<li><span>促销优惠：</span>{{ if(uslog.promotion_price==null||uslog.promotion_price=="undefined"||uslog.promotion_price=="") {}}-0.00元
				{{} else{ }}{{=(uslog.promotion_price).toFixed(2)}} 元
				{{}}}
			</li>
			{{ if(uslog.first_order_price!=null&&uslog.first_order_price!="undefined"&&uslog.first_order_price!=0) {}}
				<li><span>首单立减：</span>{{=(uslog.first_order_price).toFixed(2)}} 元</li> 
			{{}}}
			<li><span>总 &nbsp; &nbsp; &nbsp;计：</span>{{=(uslog.order_account).toFixed(2)}} 元</li>
			{{ if(uslog.status=="20"||uslog.status=="22"||uslog.status=="30"||uslog.status=="40"||uslog.status=="50") {}}
				<li>
					<span>支付方式：</span>
					{{ if(uslog.pay_form==1) {}}  pc(支付宝){{}}}
            		{{ if(uslog.pay_form==2) {}}微信支付{{}}}
                	{{ if(uslog.pay_form==3) {}}pc(银联支付){{}}}
                	{{ if(uslog.pay_form==4) {}}wap(支付宝){{}}}
                	{{ if(uslog.pay_form==5) {}}微信支付{{}}}
                	{{ if(uslog.pay_form==6) {}}wap(银联支付){{}}}
					{{ if(uslog.pay_form==8) {}}价格为零免支付{{}}}
				</li>
				<li>
					<span>支付时间：{{=timeStamp2String(uslog.pay_time)}}</span>
				</li> 
			{{}}}
		</ul>
		<h3>订单信息</h3>
		<ul>
			<li><span>基地：</span>{{=uslog.companyName}}</li>
			<li><span>订单编号：</span>{{=uslog.ordersn}}</li>
			<li><span>下单时间：</span>{{=timeStamp2String(uslog.addtime)}}</li>
				{{ if(uslog.user_ship_type==1) {}}<li><span>用户自提(提货码): </span>{{=(uslog.pick_code==undefined?"等待接单":uslog.pick_code)}}</li> {{}}}
			<li><span>备 &nbsp; &nbsp; &nbsp; 注:</span>{{ if(uslog.remark!="") {}}{{=uslog.remark}}  {{} else{  }} 暂 无{{}}}</li>
		</ul>
		{{}}}
    {{}}}
{{}}}
</script>
<div class="per_toppart">
	<div class="per_top">
		<div class="per_topleft flW ">
			<div class="per_toplogo flW">
				<a href="index.html"><img src="${SHOPDOMAIN}/front/images/web/pcLogo.png" /></a>
			</div>
			<div class="per_toplogotxt flW">
				<span>退款管理</span>
			</div>
		</div>

	</div>
	<div class="fox"></div>
</div>


<div class="per_return_main">
	<div class="per_return_infor">
		<div class="per_return_step">
			<div class="per_return_steptop">
				<ul>
					<li>买家申请退款</li>
					<li class="center  ">商家处理申请</li>
					<li class="right  ">退款完成</li>
				</ul>
				<div class="fox"></div>
			</div>
			<div class="per_return_stepbtm">
				<div class="per_return_stepbtminfor per_return_stepbtm20" id="jincheng"></div>
			</div>
			<div class="per_return_steptop">
				<ul>
					<li class="padleft6" id="padleft1"></li>
					<li class="center return_color" id="padleft2">&nbsp;</li>
					<li class="right return_color" id="padleft3"></li>
				</ul>
			</div>
		</div>
		<div class="per_return_product">
			<div class="per_return_productleft flW" id="showlist"></div>
			<div class="per_application flW">
				<form action="${SHOPDOMAIN}/interfaces/orderSele/refuondOrd.html" id="form_refound" class="form-horizontal" method="post">
					<table width="100%" border="0" cellspacing="0" id="shenqing" cellpadding="0" style="display:none;">
						<tbody>
							<tr>
								<td width="15%" align="right">交易金额：</td>
								<td width="85%"><input name="textfield" type="text" class="per_adr_input" readonly id="textfield" value=""></td>
								<input type="hidden" id="orderIds" name="orderId" value="" />
								<input type="hidden" id="webchoo" name="webchoo" value="1" />
							</tr>
							<tr>
								<td align="right" valign="top">退款原因：</td>
								<td><textarea class="per_feedback_input w_require w_content" name="remark" placeholder="请填写退款原因，最多200字"></textarea></td>
							</tr>
							<tr>
								<td align="right">&nbsp;</td>
								<td><input type="button" onclick="commitSu();" class="per_application_btn" value="提交退款申请"></td>
							</tr>
						</tbody>
					</table>
				</form>
				<div class="per_return_productright flW">

					<div class="per_return_txt" id="refunding" style="display:none;">
						<table width="100%" border="0" cellspacing="0" id="shenqing" cellpadding="0" style="display:none;">
							<tr>
								<h3>您的订单商家已经正在退款过程中…</h3>
								<p>
									<br /> <span id="addtime" style="display:none"></span>
								</p>
							</tr>
						</table>
					</div>
					<div class="per_return_txt" id="refoundsucc" style="display:none;">
						<h3>
							<img src="${SHOPDOMAIN}/front/images/web/per_return3.png" class="succornot" width="37" height="38" alt="" /> <span id="succorno"></span><br /> 系统回复： <span id="sysremark"></span>
						</h3>
					</div>
				</div>
			</div>
			<div class="fox"></div>
		</div>
		<div class="fox"></div>
	</div>

</div>

<jsp:include page="footShop.jsp"></jsp:include>
<script
	src="${SHOPDOMAIN}/system/theme/assets/admin/pages/scripts/form-validation-pc.js"></script>
<script src="${SHOPDOMAIN}/front/js/wap/jquery.hDialog.js"></script>
<script>
	document.title="个人中心-退款管理";
	var cho = getRequest("ords");
	function detail(cho) {
		$.post(SHOPDOMAIN + '/interfaces/orderSele/refuondOrdById.html', {
			id : cho
		}, function(data) {
			if (data.res_code == '0') {
				if (data.OrdersReturn.status == 0) {
					//审核中
					$("#jincheng").removeClass().addClass("per_return_stepbtminfor per_return_stepbtm60");
					$("#refunding").css('display', 'block');

					$("#addtime").html(timeStamp2String(data.orders.addtime));
					$("#padleft1").html(timeStamp2String(data.orders.receivingTime));
					$("#padleft3").html("");
					$(".succornot").attr("src", "${SHOPDOMAIN}/front/images/wap/w-tuikuansucc.png");
				}
				if (data.OrdersReturn.status == 1) {
					//审核通过 
					$("#jincheng").removeClass().addClass("per_return_stepbtminfor");
					$("#refoundsucc").css('display', 'block');
					$("#succorno").html("退款成功");
					$("#addtime").html(timeStamp2String(data.orders.addtime));
					$("#padleft1").html(timeStamp2String(data.OrdersReturn.addtime));
					$("#padleft3").html(timeStamp2String(data.OrdersReturn.recordtime));
					$(".succornot").attr("src", "${SHOPDOMAIN}/front/images/web/per_return3.png");
				}
				if (data.OrdersReturn.status == 2) {
					//审核不通过 
					$("#jincheng").removeClass().addClass("per_return_stepbtminfor");
					$("#refoundsucc").css('display', 'block');
					$("#succorno").html("退款失败");
					$("#addtime").html(timeStamp2String(data.addtime));
					$("#padleft1").html(timeStamp2String(data.OrdersReturn.addtime));
					$("#padleft3").html(timeStamp2String(data.OrdersReturn.recordtime));
					$(".succornot").attr("src", "${SHOPDOMAIN}/front/images/wap/w-tuikuanfail.png");
				}
				if (data.OrdersReturn.sysRemark == null || data.OrdersReturn.sysRemark == "") {
					$("#sysremark").html("暂无回复");
					$("#addtime").html(timeStamp2String(data.addtime));
					$("#padleft1").html(timeStamp2String(data.OrdersReturn.addtime));
				} else {
					$("#addtime").html(timeStamp2String(data.addtime));
					$("#padleft1").html(timeStamp2String(data.OrdersReturn.addtime));
					$("#padleft3").html(timeStamp2String(data.OrdersReturn.recordtime));
					$("#sysremark").html(data.OrdersReturn.sysRemark);
				}

			}
		}, "json").error(function() {
			showError();
		});
	}
	jQuery(document).ready(function() {

		cho = getRequest("ords");
		$("#orderIds").val(cho);
		$.post(SHOPDOMAIN + '/interfaces/orderSele/orderDetailsByOrdersn.html', {
			cho : cho
		}, function(data) {
			if (data.res_code == '0') {
				var evalText = doT.template($("#interpolationtmpl").html());
				$("#showlist").html(evalText(data.list));
				var statu = data.list[0].status;
				if (statu == "20" || statu == "22" || statu == "30" || statu == "40") {
					$("#shenqing").css('display', 'block');
					$("#textfield").val(data.list[0].order_account + "元");
				}
				if (statu == "50") {
					detail(cho);
				}
				if (statu == "60") {
					detail(cho);
					//$("#refunding").css('display', 'block');
				}
			} else {
				if (data.res_code == '1') {
					showm("您还没有登录!");
					window.setTimeout((window.location.href = "index.html"), 10000);
				} else {
					showMessage(data.message);
				}
			}
		}, "json").error(function() {
			showError();
		});
	});
	function commitSu() {
		$("#form_refound").submit();
	}
	jQuery(document).ready(function() {
		FormValidation.initRefound();
	});
</script>
</script>
