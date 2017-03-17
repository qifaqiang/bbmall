<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="top.jsp"></jsp:include>
<script> document.title="确认订单"; </script>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/front/css/wap/part-page.css">
<link rel="stylesheet"
	href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<link rel="stylesheet"
	href="${SHOPDOMAIN}/front/css/wap/cart-confirmation-order.css" />

<form action="${SHOPDOMAIN}/wap/fontOrder/createOrder.html"
	id="form1" method="post">
	<input type="hidden" id="companyId" name="companyId" value="">
	<input type="hidden" id="payType" name="payType" value="5"> <input
		type="hidden" id="userShipType" name="userShipType"> <input 
		type="hidden" id="sendPrice" name="sendPrice"> <input
		type="hidden" id="chargeSendPrice" name="chargeSendPrice"> <input
		type="hidden" id="ifabove"> <input type="hidden"
		id="mortgagePrice"> <input type="hidden" id="sysCoupRecordId"
		name="sysCoupRecordId"> <input type="hidden" id="cartItems"
		name="cartItems">
		<input type="hidden" id="promotionItems" >
		<input type="hidden" id="firstSubstraPrice">
	<div class="w-main">
		<!--头部-->
		<div class="address" id="chooseAddress"></div>

		<div style="padding-left:37px; font-size:24px; 
    border-top: 1px solid #e5e5e5; background:white;padding-top:10px;padding-bottom:10px; ">
    		<img src="${SHOPDOMAIN}/front/images/wap/iconfont-dianpu.png" width="35"/>
			<span id="companyBaseName"></span>
		</div>
		<ul class="shopping-cart" id="shopping-cart" style="background:#f5f5f9">
		
		</ul>

		<div class="purchase-information">
			<div class="pcifmt one"  style="">
				<div style="width: 100%;">
					<input type="text" maxlength="50" id="remark" name="remark"
						style="width: 100%;background: none;border:none; border-radius: 5px;padding:5px 0px;"
						placeholder="给卖家留言">
				</div>
			</div>
			<div class="pcifmt one"  >
				<div class="w-checkWay">
					配送方式<span><em class="w-checkWayEm" id="userhsipType">商家配送</em><img
						src="${SHOPDOMAIN}/front/images/wap/Return2.png" /></span>
				</div>
			</div>

			<div class="pcifmt payment1">
				支付方式<span><i id="paytypeInfo">微信支付</i><img
					src="${SHOPDOMAIN}/front/images/wap/Return2.png" /></span><br>
			</div>

			<div class="pcifmt" onclick="toChoseRecord()">
				优惠券
				<div class="divBtn">
					<i id="SysCouReNumber">0</i>张可用
				</div>
				<span><img src="${SHOPDOMAIN}/front/images/wap/Return2.png" /></span>
			</div>

			<div class="pcifmt one">
				商品金额<span class="money">&yen;<span id="allTotal">0.00</span></span>
			</div>

			<div class="pcifmt one">
				商品优惠<span class="money">-&yen;<i id="mortgage">0.00</i></span>
			</div>

			<div class="pcifmt one">
				活动促销<span class="money">-&yen;<i id="promPrice">0.00</i></span>
			</div>
			<div class="pcifmt one" id="firstdiv">
				运费<span class="money"><i id="sendPriceinfo">0.00</i></span>
			</div>
				<div class="pcifmt one">
			<br>
			</div>
		</div>

		<div class="shopping">
			<div class="total">
				实付款：<span>¥<span id="effectivepay">0.00</span>
				</span>
			</div>
			<a>
				<div class="spright" onclick="createOrder()">提交订单</div>
			</a>
		</div>
	</div>
</form>
<!--选择配送方式-->
<div id="HBox" style="display: none">
	<div class="w-Hbox-title">配送方式</div>
	<div class="w-Hbox-list">
		<div class="w-Hbox-listDetails w-HboxChecked">商家配送</div>
		<div class="w-Hbox-listDetails w-HboxUnchecked">顾客自提</div>
	</div>
	<div class="w-Hbox-foot">
		<a class="w-Hbox-cancleBtn closeM">取消</a> <a
			class="w-Hbox-okBtn determine foolbatchsure">确定</a>
	</div>
</div>

<!--是否确认付款-->
<div id="HBox1" style="display: none">
	<div class="w-Hbox-title">确认付款</div>

	<div class="w-Hbox-list">
		<div class="w-Hbox-listDetails2">支付总金额：0元</div>
		
		<div class="w-Hbox-listDetails1 w-HboxChecked1" title="2">
			<img src="${SHOPDOMAIN}/front/images/wap/w-wechatPay.png" alt="微信支付" />
			微信支付
		</div>
		<div class="w-Hbox-listDetails1 w-HboxUnchecked1" title="1">
			<img src="${SHOPDOMAIN}/front/images/wap/w-treasurePay.png"
				alt="支付宝支付" /> 支付宝支付
		</div>
		<!-- 
		<div class="w-Hbox-listDetails1 w-HboxUnchecked1" title="3">
			<img src="${SHOPDOMAIN}/front/images/wap/w-unionpay.png" alt="银联支付" />
			银联支付
		</div> -->
	</div>
	<div class="w-Hbox-foot">
		<a class="w-Hbox-cancleBtn closeM">取消</a> <a
			class="w-Hbox-okBtn determine determine1">确定</a>
	</div>
</div>


<script src="${SHOPDOMAIN}/front/js/jquery.hDialog.js"></script>

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
           <a   href=""  id="{{= specId}}a"   >
            <li   id="{{=specId}}" carId={{=cart.id}}  prodId={{=cart.prod_id}}   >
           
                <div  class="select-number"   >
                     <input type="hidden" class="select-numberl" id={{=cart.id}}  prod={{=cart.prod_id}} count={{=cart.count}} specId={{=specId}}>
                    <div class="picture" id="{{=specId}}picture"><img src="${USERIMGSRC}{{=imgZuhe(cart.picuri,'_300')}}"/></div>
                </div>

                <div class="payable">
                    <h2 id="{{=specId}}name">{{=cart.name}}</h2>
                        
                    <h3  class="onchose"  title="{{=cart.count*cart.price}}" price="{{=price}}" id="{{=cart.id}}subtotal">
						{{ if(cart.spec_id != "0") {}}
							{{ if(undefined != cart.specDetailList && cart.specDetailList.length > 0 ) {}}
								{{var specList =cart.specDetailList;}}
									{{ for(var j=0;j<specList.length;j++) { }}
									{{ var spec = specList[j]; }}
										{{=spec.specificationName}}:{{=spec.specificationDetailName}}&nbsp;&nbsp;
									{{ } }}
								{{ }}}
							{{} else{ }}
			   				 无
						{{ }}}
					</h3>
                    <h4>&yen;{{=price}}<span id="{{=cart.id}}quantity">X{{=cart.count}}</span></h4>
                </div>
            </li>
        </a> 
      {{}}}

</script>

<script id="userLocation" type="text/x-dot-template">
    <a href="chooseAddress.html?url=cart-confirmation-order.html&id={{=it.id}}">
                  <input type="hidden" id="shipAddressId" name="shipAddressId" value={{=it.id}} />
   <div>
                <div class="w-paddingTB10 w-padding37  w-borderBottom" style="background: white;font-size:24px">
                   

                    <div style="display: inline-block;vertical-align:middle;width: 95%">
                        <p class="w-addressName">收货人：{{=it.consignee}}&nbsp;<span class="fr">{{=it.mobile}}</span></p>

                        <p class="w-color9">{{=it.regionName}}{{=it.location}}</p>
                    </div>
                    <img src="${SHOPDOMAIN}/front/images/wap/enter.png" alt=""/>
                </div>
            </div>
</a>

</script>
<script>
	//防止订单重复提交
	window.history.forward(-1);
	
	$(function() {//初始化
//		$("#companyBaseName").html($.cookie('sys_company_name'));
		$.ajax({
					async : false,
					type : "post",
					url : "${SHOPDOMAIN}/wap/shopCart/showCartItem.html",
					success : function(data) {
                          
						var dataInfo = $.parseJSON(data);
						if (dataInfo.res_code == "0") {
							 $("#firstSubstraPrice").val(dataInfo.firstSubstraPrice);
							if(dataInfo.firstSubstraPrice!=null&&dataInfo.firstSubstraPrice!=""){
							 
								 $("#firstdiv").after("<div class='pcifmt one' id='firstSubstraInfo' style='display:none;'>"+
											"首单减免<span class='money'><i id='sendPriceinfo'>"+dataInfo.firstSubstraPrice+"</i></span></div>");
							}
							
							$("#companyId").val(dataInfo.companyId);
							var evalText = doT.template($("#interpolationtmpl")
									.text());

							$("#shopping-cart").html(evalText(dataInfo.list));
							$("#shopping-cart")
									.append(
											" <div style='clear: both' id='bbbbb'></div>");
							if (dataInfo.userLocation != null
									&& dataInfo.userLocation != ""
									&& dataInfo.userLocation != "underfined") {
								var addressText = doT.template($(
										"#userLocation").text());
								;
								$("#chooseAddress").html(
										addressText(dataInfo.userLocation));
							} else {
								$("#chooseAddress")
										.html(
												"   <a href='chooseAddress.html?url=cart-confirmation-order.html'><div class='bg'>您还未创建订单地址<img src='${SHOPDOMAIN}/front/images/wap/Return2.png'/></a>");
							}
							getCompanyInfo();//配送费
							intCartT();//校验库存

							
							
							getPromotionActivity();//促销活动
							var sysCrodId = GetQueryString("syCrodId");
							if (sysCrodId != null && sysCrodId != "") {
							 if(checkMortgage(sysCrodId)){//校验优惠券是否可用
								 
								getMortgagePrice(sysCrodId);//优惠券抵押
						        }
							}
							var shipText = $("#userhsipType").text();
							showTotal(shipText);//计算总计
							var countRecord = getSysCouponRecord();//优惠券
						} else {
							$.dialog("confrim", "购物车选项失效", "请返回购物车重新购买", 1500);
						}
					}
				});

		//    自定义配送方式
		$('.w-checkWay').hDialog({
			width : 560,
			height : 370,
			closeHide : false
		});

		$('.w-Hbox-listDetails').on(
				'click',
				function() {
					 
					$('.w-Hbox-listDetails').removeClass('w-HboxChecked')
							.addClass('w-HboxUnchecked');
					$(this).removeClass('w-HboxUnchecked').addClass(
							'w-HboxChecked');
				 
				});
		$(document).on('click', '.determine', function() {
			var oDetails = $('.w-HboxChecked').html();
			$('.w-checkWayEm').html(oDetails);
		});
       $(".foolbatchsure").click(function(){
    		var oDetails = $('.w-HboxChecked').html();
    		 
    		showTotal(oDetails);
       });
		//是否确认付款

		$('.payment1').hDialog({
			box : '#HBox1',
			width : 560,
			height : 430,
			closeHide : false
		});
		$('.w-Hbox-listDetails1').on(
				'click',
				function() {
					 
					$('.w-Hbox-listDetails1').removeClass('w-HboxChecked1')
							.addClass('w-HboxUnchecked1');
					$(this).removeClass('w-HboxUnchecked1').addClass(
							'w-HboxChecked1')
				})
		$(document).on('click', '.determine1', function() {
			var oDetails = $('.w-HboxChecked1').attr('title');
			if (oDetails == 1) {
				$("#paytypeInfo").text("支付宝");
				$("#payType").val("4");
			} else if (oDetails == 2) {
				$("#paytypeInfo").text("微信支付");
				$("#payType").val("5");
			} else {
				$("#paytypeInfo").text("银联支付");
				$("#payType").val("6");
			}

		});
	});
  function toClick(){
	  $("#form1").submit(function(){
		 return false; 
	  });
  }
	//去选择优惠券
	function toChoseRecord() {
		var number = $("#SysCouReNumber").text();
     
		if (number != null && number != "" && number != "0") {
			var price=$("#allTotal").text();
		 
			window.location.href = SHOPDOMAIN + '/wap/chooseCoupons.html?price='+price;
		}else{
			return false;
		}
	}
	//去创建订单
	function createOrder() {
		var payType = $("#payType").val();
		if (payType == null || payType == "") {

			$(".payment1").trigger("click");
		}
		var shipAddressId = $("#shipAddressId").val();
		var shipText = $("#userhsipType").text();
		if (shipText == "商家配送") {
			$("#userShipType").val("2");
		} else {
			$("#userShipType").val("1");
		}
		if (shipAddressId == null || shipAddressId == "") {
			$.dialog("confrim", "您还没有收货地址", "请先建立收货地址", 1500);
			return false;
		}
		getCompanyInfo();//配送费
		var ifAbove = intCartT();//校验库存
	
		
		getPromotionActivity();//促销活动
		var sysCrodId = GetQueryString("syCrodId");
		if (sysCrodId != null && sysCrodId != "") {
			getMortgagePrice(sysCrodId);//优惠券抵押
		}
		var shipText = $("#userhsipType").text();
		showTotal(shipText);//计算总计
		var countRecord = getSysCouponRecord();//优惠券
		if (ifAbove != null && ifAbove != "") {

		} else {
			var ids = new Array();
			var index = 0;
			$(".onchose").each(function() {
				ids[index++] = $(this).attr("id").replace("subtotal", "");
			});
			$("#cartItems").val(ids);
			if (ids != null && ids != "") {
				$("#form1").submit();
			}

		}

	}
	//更改购物车数量
	function sendUpdate(cartItemId, quantity, productId) {

		var input = $("#" + cartItemId + "quantity");
		var subtotal = $("#" + cartItemId + "subtotal");
		$.ajax({
			async : false,

			url : "${SHOPDOMAIN}/wap/shopCart/updateQuantity.html",
			data : {
				"id" : cartItemId,
				"count" : quantity,
				prodId : productId
			},
			dataType : "json",
			success : function(data) {
				if (data.res_code == "1") {
					input.text("X" + data.quantity);
					var price= $("#"+cartItemId+"subtotal").attr("price");	
   				  var fsubtotal=data.quantity*price;
					$("#" + cartItemId + "subtotal").attr("title",
							fsubtotal);
					var shipText = $("#userhsipType").text();
					showTotal(shipText);
				} else {
					alert("更新数量失败");
				}
			},
			error : function() {
				alert("网络繁忙,请稍后!");
			}
		});

	}
	/**配送费
	 */

	function getCompanyInfo() {
		var companyId = $("#companyId").val();
		$.ajax({
			async : false,
			url : "${SHOPDOMAIN}/wap/shopCart/getCompanyInfo.html",
			type : "post",
			dataType : "json",
			data : {
				"companyId" : companyId
			},
			success : function(data) {
				var company = data.company;
				//alert(JSON.stringify(data));
				$("#sendPrice").val(company.sendPrice);
				$("#chargeSendPrice").val(company.chargeSendPrice);
			}
		});

	}
	//优惠券可用个数

	function getSysCouponRecord() {
		var index = 0;
		var allPrice = "";
		var startime = "";
		var endtime = "";
		var orderPrice = $("#allTotal").text();
	 

		$.ajax({
					async : false,
					url : "${SHOPDOMAIN}/wap/shopCart/getSysCouposRecordByUserId.html",
					type : "post",
					dataType : "json",

					success : function(data) {

						if (data.res_code == "0") {
						 // alert(data.list.length);
							if (data.list.length == "0") {
								$("#SysCouReNumber").text("0");
							} else {
								var list = data.list;
								for (var i = 0; i < list.length; i++) {
                                        
									// if(CompareDate(timeStamp2String(list[i].startTime.time), endtime=timeStamp2String(list[i].endTime.time))){//判断是否在时间段内
                                       
									if (list[i].needPrice <= orderPrice) {
										index++;
										 
										$("#SysCouReNumber").text(index);
									}
									// }
								}
							}

						}

					}
				});
		return index;
	};
     /**校验优惠券
     */
	function checkMortgage(id){
    	var boolean=false;
		var total = 0;
		$(".onchose").each(function() {
			total += Number($(this).attr("title"));
            });
		  $.ajax({
			 async:false,
			 url:"${SHOPDOMAIN}/wap/fontOrder/checkMortgage.html",
			 dataType:"json",
			 data:{"id":id,"price":total},
			 type:"post",
			 success:function(data){
			 //alert(JSON.stringify(data));
				 if(data.result=="1"){
					 boolean=true;
				 } 
			 }
		  });
		  return boolean;
	}
	
	//优惠券可顶用的钱
	function getMortgagePrice(id) {
		$("#sysCoupRecordId").val(id);
		$.ajax({
			async : false,
			url : "${SHOPDOMAIN}/wap/shopCart/getMortgagePrice.html",
			dataType : "json",
			type : "post",
			data : {
				"id" : id
			},
			success : function(data) {
				 // alert(JSON.stringify(data));
				var substracrPrice = data.sysCRod.substractPrice;
				$("#mortgage").text("已抵用" + Number(substracrPrice).toFixed(2));
				$("#mortgagePrice").val(substracrPrice);
			}

		});

	}
	//获取url参数
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}

	//促销活动
	function getPromotionActivity() {
		var ids = new Array();
		var index = 0;
		$(".onchose").each(function() {
			ids[index++] = $(this).attr("id").replace("subtotal", "");
		});
 
			$("#promotionItems").val("");
		$("#promotionItems").val(ids);
		var orderPrice = $("#allTotal").text();
		 
		$.ajax({
			async : false,
			url : "${SHOPDOMAIN}/wap/shopCart/getPromotionActivity.html",
			dataType : "json",
			data : {
				cartItemIds : $("#promotionItems").val(),
				"orderPrice" : orderPrice
			},
			type : "post",
			success : function(data) {
			   
				$("#promPrice").text(Number(data.promPrice).toFixed(2));
				 
			}
		});
	}

	//校验库存
	function intCartT() {
		var companyId = $("#companyId").val();
		var prods = "";
		var counts = "";
		var specIds = "";
		var cartId = "";
		var ifAbove = "";
		$(".select-numberl").each(function() {
			prods += $(this).attr("prod") + ",";
			counts += $(this).attr("count") + ",";
			specIds += $(this).attr("specId") + ",";
		});

		var stockMap = isAbolveStock(prods.replace(/,$/g, ""), companyId,
				counts.replace(/,$/g, ""), specIds.replace(/,$/g, ""));
		//alert(JSON.stringify(stockMap));
		if (stockMap != null && stockMap != "" && stockMap.map != null
				&& stockMap.map != "") {
			var stock = JSON.stringify(stockMap.map);
			var name = "";
			var stoArry = stock.split(",");
			for (var i = 0; i < stoArry.length; i++) {
				var sto = stoArry[i];
				var spec = sto.split(":");
				var specId = spec[0].replace(/\"/g, "");
				specId = specId.replace("SPEC_", "");
				specId = specId.replace("{", "");
				if (specId.indexOf("P_") >= 0) {
					specId = specId.replace("P_", "") + "l";
				}
				if (spec[1] != null && spec[1] != "") {
					stockNumber = spec[1].replace("}", "");
					if (stockNumber == "0") {
						ifAbove = "above";
						$("#" + specId + "a").insertAfter("#bbbbb");
						$("#" + specId + "picture").append(
								"<div class='w-soldOut'></div>");
						$("#" + specId + "a").find(".onchose").removeClass(
								"onchose");
						$("#" + specId + "a").find(".select-numberl")
								.removeClass("select-numberl");
						name += $("#" + specId + "name").text() + " ";
						$.dialog("confrim", "库存不足", "" + name + "暂时无货", 1500);
						var shipText = $("#userhsipType").text();
						showTotal(shipText);
					} else {

						ifAbove = "ifAbove";
						var cartId = $("#" + specId).attr("carid");
						var productId = $("#" + specId).attr("prodid");
						name += $("#" + specId + "name").text() + " ";
						$("#" + cartId).attr("count", stockNumber);
						$.dialog("confrim", "您选择的商品已超出库存", "已将" + name
								+ "改为最大库存量", 1500);
						sendUpdate(cartId, stockNumber, productId);
						var shipText = $("#userhsipType").text()
						showTotal(shipText);
					}
				}
			}

		}
		return ifAbove;
	}

	function intCartToOrder() {
		var companyId = $("#companyId").val();
		var ifAbove = "";
		var abolveInfo = "";
		var outInfo = "";
		var name = "";
		var outname = "";
		var prods = "";
		var counts = "";
		var specIds = "";
		var cartId = "";
		$(".select-numberl").each(function() {
			prods += $(this).attr("prod") + ",";
			counts += $(this).attr("count") + ",";
			specIds += $(this).attr("specId") + ",";
		});
		/* alert(prods.replace(/,$/g,""));
		alert(counts.replace(/,$/g,""));
		alert(specIds.replace(/,$/g,"")); */
		var stockMap = isAbolveStock(prods.replace(/,$/g, ""), companyId,
				counts.replace(/,$/g, ""), specIds.replace(/,$/g, ""));
		//alert(JSON.stringify(stockMap));
		if (stockMap != null && stockMap != "" && stockMap.map != null
				&& stockMap.map != "") {
			var stock = JSON.stringify(stockMap.map);
			var stoArry = stock.split(",");
			for (var i = 0; i < stoArry.length; i++) {
				var sto = stoArry[i];
				var spec = sto.split(":");
				var specId = spec[0].replace(/\"/g, "");
				specId = specId.replace("SPEC_", "");
				specId = specId.replace("{", "");
				if (specId.indexOf("P_") >= 0) {
					specId = specId.replace("P_", "") + "l";
				}
				if (spec[1] != null && spec[1] != "") {
					stockNumber = spec[1].replace("}", "");

					if (stockNumber == "0") {

						$("#" + specId + "a").insertAfter("#bbbbb");
						$("#" + specId + "picture").append(
								"<div class='w-soldOut'></div>");

						$("#" + specId + "a").find(".onchose").removeClass(
								"onchose");
						$("#" + specId + "a").find(".select-numberl")
								.removeClass("select-numberl");
						name += $("#" + specId + "name").text() + " ";
						abolveInfo = "暂时无货 ";
						var shipText = $("#userhsipType").text();
						showTotal(shipText);
					} else {
						var cartId = $("#" + specId).attr("carid");
						var productId = $("#" + specId).attr("prodid");
						outname += $("#" + specId + "name").text() + " ";
						outInfo = "改为最大库存量";
						sendUpdate(cartId, stockNumber, productId);
					}
				}
			}
		}

		return name + abolveInfo + outname + outInfo;
	}
	 
	
	//判断是否超出库存
	function isAbolveStock(prodIds, compayId, counts, specIds) {
		 if(prodIds==null||prodIds==""){
	    	 return false;
	     }
		var stockMap;
		$.ajax({
			async : false,
			url : "${SHOPDOMAIN}/wap/shopCart/ifAboveStock.html",
			type : "post",
			dataType : "json",

			data : {
				"prodIds" : prodIds,
//				"companyId" : compayId,
				"counts" : counts,
				"specIds" : specIds
			},
			success : function(data) {
				stockMap = data;
			}
		});
		return stockMap;
	}
//计算合计
	function showTotal(shipText) {
		var total = 0;
		$(".onchose").each(function() {
			total += Number($(this).attr("title"));

		});
		$("#allTotal").text(Number(total).toFixed(2));
		
		Total(shipText);
	}
	function Total(shipText) {
	 
		var promPrice = $("#promPrice").text();
		var mortgagePrice = $("#mortgagePrice").val();
		var total = $("#allTotal").text();
		
		var sendPrice = $("#sendPrice").val();
		var chargeSendPrice = $("#chargeSendPrice").val();
		var affectivepay = total - promPrice - mortgagePrice;
		if(parseFloat(affectivepay)<=0){
			 affectivepay=0;
		}else{ 
			var fistPrice=$("#firstSubstraPrice").val();
			if( fistPrice!=null&&fistPrice!="" ){
				$("#firstSubstraInfo").show();
				affectivepay=parseFloat(affectivepay)-parseFloat(fistPrice);
				if(parseFloat(affectivepay)<=0){
					affectivepay=0;
				}
			}
		}
		var ifsend = "";
		if (shipText == "商家配送") {
               
			if (parseFloat(total) >= parseFloat(sendPrice)) {
				ifsend = "免运费";
				$("#sendPriceinfo").text(ifsend);
			} else {
				ifsend = "运费";

				$("#sendPriceinfo").text("+¥"+parseFloat(chargeSendPrice).toFixed(2));

				affectivepay = parseFloat(affectivepay)
						+ parseFloat(chargeSendPrice);
			}
		} else {
			$("#sendPriceinfo").text("+¥0.00");
		}

		// parseFloat(oC6)-parseFloat(oC7)
		$("#effectivepay").text(Number(affectivepay).toFixed(2));
		$(".w-Hbox-listDetails2").text("支付总金额："+Number(affectivepay).toFixed(2));

	}
</script>
</body>
</html>

