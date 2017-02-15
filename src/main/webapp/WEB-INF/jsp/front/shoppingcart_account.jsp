<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
<link rel="stylesheet"
	href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css" />
<link href="${SHOPDOMAIN}/front/css/web/share.css" rel="stylesheet"
	type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/Personal.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<style>
.frW {margin-right: 40px;}
.mobile-error{display:none}
* {box-sizing: content-box;}
body{background-color:white}
</style>
<form action="${SHOPDOMAIN}/wap/fontOrder/createPcOrder.html"
	id="form1" method="post">
	<div class="per_toppart">
		<input type="hidden" id="companyId" name="companyId" value="">
		<input type="hidden" id="userhsipType" value="商家配送"> <input
			type="hidden" id="sendPrice" name="sendPrice"> <input
			type="hidden" id="chargeSendPrice" name="chargeSendPrice"> <input
			type="hidden" id="mortgagePrice"> <input type="hidden"
			id="promotionItems"> <input type="hidden" id="userShipType"
			name="userShipType" value="2"> <input type="hidden"
			id="firstSubstraPrice"> <input type="hidden" id="cartItems"
			name="cartItems"> <input type="hidden" id="sysCoupRecordId"
			name="sysCoupRecordId"> <input type="hidden" id="sysCrodId">
		<div class="per_top">
			<div class="per_topleft flW ">
				<div class="per_toplogo flW">
					<a href="${SHOPDOMAIN}/index.html">
					<img src="${SHOPDOMAIN}/front/images/web/pcLogo.png" />
					 </a>
				</div>
				<div class="per_toplogotxt flW">
					<span>结算</span>
				</div>
			</div>

		</div>
		<div class="fox"></div>
	</div>
	<div class="s-line"></div>
	<div class="per_return_main">
		<div class="per_shoptitle">填写并核对订单信息</div>
		<div class="per_pay">
			<h3>
				收货人信息<span class="frW clickBtnAddress"><a href="#">新增收货地址</a></span>
			</h3>

			<div class="per_payadr">
				<div class="tableAdress" style="height:100%">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						onMouseOver="adredit()" onMouseOut="editnone()">
							 <input type="hidden" id="shipAddressId" name="shipAddressId" value="" />
						<tbody id="chooseAddress">
						</tbody>
					</table>
				</div>
				<div class="showAndhide ">
					<span class="showSpan">更多地址</span> <i></i>
				</div>
			</div>
			<h3>支付方式</h3>

			<div class=" per_payadr">
				<div class="per_payadrsign per_payadrborder pointerW">在线支付</div>
				<div class="fox"></div>
			</div>
			<h3>配送方式</h3>

			<div class="per_payadr" id="nav">
				<div class="per_payadrsign  per_payadrborder pointerW"
					onClick="active(this)">商家配送</div>
				<div class="per_payadrsign pointerW " onClick="active(this)">上门自提</div>
				<div class="fox"></div>
			</div>
			<h3>商品清单</h3>

			<div class="per_payprgoods">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tbody id="shopping-cart">
					</tbody>
				</table>
			</div>
			<h3>添加备注</h3>

			<div class="per_remarks">
				<input class="per_remarks_input" id="remark" name="remark"
					type="text" placeholder="请将购物需求详细说明">
			</div>
			<div class="per_ticket">
				<div class="per_ticket_left" style="cursor: pointer;" onClick="ticket()">+</div>
				<div class="per_ticket_left02" style="cursor: pointer;" onClick="ticket02()">-</div>
				<div class="per_ticket_right flW">
					使用优惠券（<i id="recotdCount"></i>张可用）
				</div>
				<div class="fox"></div>
			</div>
			<div class="per_ticket_infor">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tbody id="conupsCord">


					</tbody>
				</table>

			</div>
			<div class=" per_payinfor">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td width="88%" align="right">商品总金额：</td>
							<td width="12%">￥<i id="allTotal">0.00</i></td>
						</tr>
						<tr>
							<td align="right">运费：</td>
							<td><i id="sendPriceinfo">0.00</i></td>
						</tr>
						<tr>
							<td align="right">优惠券：</td>
							<td>-￥<i id="mortgage">0.00</i></td>
						</tr>
						<tr id="firstdiv">
							<td align="right">活动优惠：</td>
							<td>-￥<i id="promPrice">0.00</i></td>
						</tr>
						<tr class="per_payinforbtm">
							<td align="right">应付总额：</td>
							<td><span>￥<i id="finlyTotal">0.00</i></span></td>
						</tr>

					</tbody>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tbody id="confirmAddress">


					</tbody>

					<tr>
						<td align="right"><a href="#" style="text-decoration: none;"><input type="button"
								class="per_payinforbtn" name="submitd" id="su"
								onclick="createOrder()" value="提交订单"></a></td>
					</tr>
				</table>


			</div>
		</div>
	</div>

</form>
<div class="layerBoxAddress" style="display: none">
	<form action="#" id="form_config" class="form-horizontal" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<td align="right">所在地址：</td>
					<td>
						<div class="new-address">
							<div class="tle">
								<div id="area" class="addressW18"></div>
								<div id="areaInfo" style="display: none" class="addressW18"></div>
								<div id="areadd" class="addressW18"></div>
								<div id="areaddInfo" style="display: none" class="addressW18"></div>
								<input type="hidden" value="" id="address" name="address"
									style="width: 500px;">
							</div>
						</div>
					</td>

				</tr>
				<tr>
					<td align="right">详细地址：</td>
					<td><input type="text" class="per_addadrinput2 w_require" maxlength="250"
						name="location" id="location" placeholder="请输入您的详细地址"> <input
						type="hidden" id="id" name="id"><input type="hidden"
						name="status" id="status" value="1"><input type="hidden"
						id="urls" name="urls" value=""></td>
				</tr>
				<tr>
					<td align="right">收货人：</td>
					<td><input type="text" class="per_addadrinput3 w_require" maxlength="10"
						value="" name="consignee" id="consignee" placeholder="请输入您的姓名"></td>
				</tr>
				<tr>
					<td align="right">手机号码：</td>
					<td><input type="text"
						class="per_addadrinput3   w_mobile w_require" name="mobile"  maxlength="11"
						id="mobile" placeholder="请输入您的手机号码"></td>
				</tr>
			</tbody>
		</table>
		<h3 class="inshow" style="display: none">
			<input type="button" class="per_addadrbtn" onclick="updatePas()"
				value="保存收货人信息">
		</h3>
		<h3 class="upshow" style="display: none">
			<input type="button" class="per_addadrbtn" onclick="submmit()"
				value="保存收货人信息">
		</h3>
	</form>
</div>
<script src="${SHOPDOMAIN}/front/js/web/ntab.js"></script>
<script src="${SHOPDOMAIN}/front/js/shoppingcart_account.js"></script>
<script id="interpolationtmpl" type="text/x-dot-template">
	{{for(i=0;i<it.length;i++){ }}
		{{var cart=it[i];
		var inventorynumber=""; 
		var specId="";
		var price=Number(cart.price).toFixed(2);
		if(cart.inventorynumber=="undefined"||cart.inventorynumber==""||cart.inventorynumber==null){
			inventorynumber="";
		} else{
			inventorynumber=cart.inventorynumber;
		}
		if(cart.spec_id=="0"){
			specId=cart.prod_id+"l";
		} else{
			specId=cart.spec_id; 
		}         
	}}  
	<tr  id="{{= specId}}a">
	<td id="{{=specId}}" carId={{=cart.id}}  prodId={{=cart.prod_id}}  width="11%">
		<div class="posRelaivenone">
			<img id="{{=specId}}picture" src="${USERIMGSRC}{{=imgZuhe(cart.picuri,'_300')}}" width="100" height="100" alt=""/>
		</div>
	</td>
	<input type="hidden" class="select-numberl" id={{=cart.id}}  prod={{=cart.prod_id}} count={{=cart.count}} specId={{=specId}}>
	<td width="40%" id="{{=specId}}name">{{=cart.name}}</td>
	<td width="9%" align="center" class="onchose"  title="{{=cart.count*cart.price}}" price="{{=price}}" id="{{=cart.id}}subtotal">
		{{ if(cart.spec_id!="0") {}}
			{{ if(undefined != cart.specDetailList && cart.specDetailList.length > 0 ) {}}
				{{var specList =cart.specDetailList;}}
					{{ for(var j=0;j<specList.length;j++) { }}
					{{ var spec = specList[j]; }}
						{{=spec.specificationName}}:{{=spec.specificationDetailName}}</br>
					{{ } }}
				{{ }}}
			{{} else{ }}
			    无
		{{ }}}
	</td>
	<td width="8%" align="center" id="{{=cart.id}}quantity">{{=cart.count}}</td>
	<td width="16%" align="center">￥{{=price}}</td>
	<td width="16%" align="center" id="{{=specId}}status">有货</td>
</tr>
{{}}}
</script>
<script id="confirmUserLocation" type="text/x-dot-template">
<tr>
	<td align="right">配送至：{{=it.regionName}}{{=it.location}}</td>
</tr>
<tr>
	<td align="right">收货人:{{=it.consignee}}</td>
</tr>
<tr>
	<td align="right">电话：{{=it.mobile}}</td>
</tr> 
</script>

<script id="conupsCordAction" type="text/x-dot-template">
{{for(i=0;i<it.length;i++){ }}
    {{
    var counps=it[i];
    var needPrice=counps.needPrice;
	if(needPrice=="0"){
       needPrice="不限额使用"  
	}
	}}
<tr>
	<td width="4%"><input type="radio" name="checkbox" id="{{=counps.id}}" class="foolRadio"></td>
	<td width="4%">￥{{=counps.substractPrice}}</td>
	<td width="8%">【满减】</td>
	<td width="68%">{{=needPrice}}减{{=counps.substractPrice}}元</td>
	<td width="16%">&nbsp;</td>
</tr>
{{}}}
</script>

<script id="userLocation" type="text/x-dot-template">
    {{for(i=0;i<it.length;i++){ }}
		{{
           var itt=it[i];
           var border="";
           var textInfo=itt.consignee.substring(0,6)+" "+itt.regionName.substring(0,3);
           if(itt.status==true){
                border="per_payadrborder";
				$("#shipAddressId").val(itt.id);
			}
      	}}
        <tr class="trPadding" {{=(border==""?"style='display:none'":"")}}>
			<td width="15%">
				<div class="per_payadrsign {{=border}}  ellipsisW" id="{{=itt.id}}" onclick="FoolActive(this)">{{=textInfo}}</div>
			</td>
			<td width="8%">{{=itt.consignee}}</td>
			<td width="56%">{{=itt.regionName}}{{=itt.location}}</td>
			<td width="12%" align="center">{{=itt.mobile}}</td>
			<td width="9%" align="center">
				<div class="per_payadr_edit"style="cursor:pointer"  onClick="changeLoc({{=itt.id}})">编辑</div>
			</td>
		</tr>

{{ } }}
</script>
<script>
	//防止订单重复提交
	window.history.forward(-1);
	
	document.title="确认订单";
	$(function() {
		initCart();//初始化

		//优惠券选中事件
		$(".foolRadio").change(function() {
			var id = $(this).attr("id");
			$("#sysCrodId").val(id);
			getMortgagePrice(id);//优惠券抵押

		});
		$('.rightFixed').find('li').hover(function() {
			$(this).addClass('on');
		}, function() {
			$(this).removeClass('on');
		})
		if($('.trPadding').length<=1){
            $('.showAndhide').hide();
        }
	})
</script>

<jsp:include page="footShop.jsp"></jsp:include>
<jsp:include page="foot-validate.jsp"></jsp:include>
<script src="${SHOPDOMAIN}/front/js/provincialCascade.js"></script>
<script src="${SHOPDOMAIN}/front/js/wap/jquery.hDialog.js"></script>
<script>
	jQuery(document).ready(function() {
		FormValidation.init();
	});
</script>
