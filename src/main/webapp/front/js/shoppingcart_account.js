
	function FoolActive(e){
		$(".trPadding").find('.per_payadrsign').removeClass('per_payadrborder');
		$(e).addClass("per_payadrborder");
		var userId=$(e).attr("id");
		var userLocation=getUserLocationById(userId);
		var confirmAddressText = doT.template($("#confirmUserLocation").html());
	    $("#confirmAddress").html(confirmAddressText(userLocation));
	    $("#shipAddressId").val(userId);
	}

	//优惠券可顶用的钱
	function getMortgagePrice(id) {
		$("#sysCoupRecordId").val(id);
		$.ajax({
			async : false,
			url : SHOPDOMAIN+"/wap/shopCart/getMortgagePrice.html",
			dataType : "json",
			type : "post",
			data : {
				"id" : id
			},
			success : function(data) {
				// alert(JSON.stringify(data));
				var substracrPrice = data.sysCRod.substractPrice;
				$("#mortgage").text( Number(substracrPrice).toFixed(2));
				$("#mortgagePrice").val(substracrPrice);
				var shipText = $("#userhsipType").val();

				showTotal(shipText);
			}

		});

	}

	/**校验优惠券
	 */
	function checkMortgage(id) {
		var boolean = false;
		var total = 0;
		$(".onchose").each(function() {
			total += Number($(this).attr("title"));
		});
		$.ajax({
			async : false,
			url : SHOPDOMAIN+"/wap/fontOrder/checkMortgage.html",
			dataType : "json",
			data : {
				"id" : id,
				"price" : total
			},
			type : "post",
			success : function(data) {
				//alert(JSON.stringify(data));
				if (data.result == "1") {
					boolean = true;
				}
			}
		});
		return boolean;
	}

	//初始化
	function initCart() {
		$.ajax({
			async : false,
			type : "post",
			url : SHOPDOMAIN+"/wap/shopCart/showCartItem.html",
			success : function(data) {

				var dataInfo = $.parseJSON(data);
				if (dataInfo.res_code == "0") {
					$("#firstSubstraPrice").val(
							dataInfo.firstSubstraPrice);
					if (dataInfo.firstSubstraPrice != null
							&& dataInfo.firstSubstraPrice != "") {

						$("#firstdiv").after(
								" <tr><td align='right'>首单立减：</td> <td >-￥<i id='mortgage'>"
										+ dataInfo.firstSubstraPrice
										+ "</i></td></tr>");
					}
					$("#companyId").val(dataInfo.companyId);

					if (dataInfo.userLocation != null
							&& dataInfo.userLocation != ""
							&& dataInfo.userLocation != "underfined") {
						var addressText = doT.template($(
								"#userLocation").html());
						var dd=	getUserLocation();
			             
						$("#chooseAddress").html(addressText(dd));//收货地址
						var confirmAddressText = doT.template($("#confirmUserLocation").html());
						$("#confirmAddress")
								.html(confirmAddressText(dataInfo.userLocation));

					} else {//无收货地址
						$("#chooseAddress")
								.html(
										"<tr ><td width='15%'></td><td width='8%'> </td>"
												+ "<td width='56%'>您还没有收货地址,请先建立收货地址。</td>"
												+ " <td width='12%' align='center'> </td>"
												+ " <td width='9%' align='center'>"
												+ " </td></tr>");
					}
					//alert(dataInfo.list);
					var evalText = doT.template($("#interpolationtmpl")
							.html());
					$("#shopping-cart").html(evalText(dataInfo.list));//初始化购物车条目
					$("#shopping-cart")
							.append(
									" <div style='clear: both' id='bbbbb'></div>");
					getCompanyInfo();//配送费
					intCartT();
					getPromotionActivity();//促销活动
					var shipText = $("#userhsipType").val();

					showTotal(shipText);
					getSysCouponRecord();//优惠券
				}//有数据
			}
		});
	}
	//获取收货地址信息
	function getUserLocation(){
		var userLocation;
		$.ajax({
			async:false,
			url : SHOPDOMAIN+"/interfaces/userLocation/LocationLists.html",
		    type:"post",
		    dataType:"json",
		    data:{"currentPage":1,"showCount":10},
		    success:function(data){
		     
		      if(data.res_code=="0"){
		    	  userLocation=data.list;
		      }
		    }
		});
		
		return userLocation;
	}
	//通过id获取收货地址详细信息
	function getUserLocationById(id){
		var userLocation;
		$.ajax({
			async:false,
			url : SHOPDOMAIN+"/interfaces/userLocation/seleById.html",
		    type:"post",
		    dataType:"json",
		    data:{"id":id},
		    success:function(data){
		     
		      if(data.res_code=="0"){
		    	  userLocation=data.list;
		      }
		    }
		});
		
		return userLocation;
	}

	/**配送费
	 */
	function getCompanyInfo() {
		var companyId = $("#companyId").val();

		$.ajax({
			async : false,
			url : SHOPDOMAIN+"/wap/shopCart/getCompanyInfo.html",
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
			url : SHOPDOMAIN+"/wap/shopCart/getPromotionActivity.html",
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

	//判断库存
	function isAbolveStock(prodIds, compayId, counts, specIds) {
		if (prodIds == null || prodIds == "") {
			return false;
		}
		var stockMap;
		$.ajax({
			async : false,
			url : SHOPDOMAIN+"/wap/shopCart/ifAboveStock.html",
			type : "post",
			dataType : "json",

			data : {
				"prodIds" : prodIds,
				"companyId" : compayId,
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
		//最后计算合计
		Total(shipText);

	}
	//最后计算合计
	function Total(shipText) {

		var promPrice = $("#promPrice").text();
		var mortgagePrice = $("#mortgagePrice").val();
		var total = $("#allTotal").text();
		var sendPrice = $("#sendPrice").val();
		var chargeSendPrice = $("#chargeSendPrice").val();
		var affectivepay = total - promPrice - mortgagePrice;

		if (parseFloat(affectivepay) <= 0) {
			affectivepay = 0;
		} else {
			var fistPrice = $("#firstSubstraPrice").val();
			if (fistPrice != null && fistPrice != "") {
				affectivepay = parseFloat(affectivepay) - parseFloat(fistPrice);
				if (parseFloat(affectivepay) <= 0) {
					affectivepay = 0;
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
				$("#sendPriceinfo").text(
						"+¥" + parseFloat(chargeSendPrice).toFixed(2));

				affectivepay = parseFloat(affectivepay)
						+ parseFloat(chargeSendPrice);
			}
		} else {

			$("#sendPriceinfo").text("+¥0.00");
		}
		// parseFloat(oC6)-parseFloat(oC7)
		$("#effectivepay").text(Number(affectivepay).toFixed(2));
		$("#finlyTotal").html(parseFloat(affectivepay).toFixed(2));
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
			url : SHOPDOMAIN+"/wap/shopCart/getPromotionActivity.html",
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
	//优惠券可用个数
	function getSysCouponRecord() {
		var needPrice = $("#allTotal").text();
		$
				.ajax({
					async : false,
					url : SHOPDOMAIN+"/wap/shopCart/getSysCouposRecordByUserId.html",
					type : "post",
					data : {
						"neddPrice" : needPrice
					},
					dataType : "json",
					success : function(data) {
						// alert(JSON.stringify(data));
						$("#recotdCount").text(data.list.length);
						var conupsText = doT.template($("#conupsCordAction")
								.html());
						$("#conupsCord").html(conupsText(data.list));//

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
		//判断库存 
		var stockMap = isAbolveStock(prods.replace(/,$/g, ""), companyId,
				counts.replace(/,$/g, ""), specIds.replace(/,$/g, ""));

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
						$("#" + specId + "picture")
								.after(
										"  <div class='noneProing'><img src='"+SHOPDOMAIN+"/front/images/outstock_tag.png'  alt=''/></div>");
						$("#" + specId + "a").find(".onchose").removeClass(
								"onchose");
						$("#" + specId + "status").text("无货");
						$("#" + specId + "a").find(".select-numberl")
								.removeClass("select-numberl");
						name += $("#" + specId + "name").text() + " ";
						showMessageAutoTime("库存不足" + name + "暂时无货", 1500);
						var shipText = $("#userhsipType").text();
						showTotal(shipText);
					} else {

						ifAbove = "ifAbove";
						var cartId = $("#" + specId).attr("carid");
						var productId = $("#" + specId).attr("prodid");
						name += $("#" + specId + "name").text() + " ";
						$("#" + cartId).attr("count", stockNumber);
						showMessageAutoTime(
								"您选择的商品已超出库存，已将" + name + "改为最大库存量", 1500);
						sendUpdate(cartId, stockNumber, productId);
						var shipText = $("#userhsipType").text();
						showTotal(shipText);
					}
				}
			}

		}
		return ifAbove;
	}

	//更改购物车数量
	function sendUpdate(cartItemId, quantity, productId) {

		var input = $("#" + cartItemId + "quantity");
		var subtotal = $("#" + cartItemId + "subtotal");
		$.ajax({
			async : false,

			url : SHOPDOMAIN+"/wap/shopCart/updateQuantity.html",
			data : {
				"id" : cartItemId,
				"count" : quantity,
				prodId : productId
			},
			dataType : "json",
			success : function(data) {
				if (data.res_code == "1") {
					input.text("X" + data.quantity);
					var price = $("#" + cartItemId + "subtotal").attr("price");
					var fsubtotal = data.quantity * price;
					$("#" + cartItemId + "subtotal").attr("title", fsubtotal);
					var shipText = $("#userhsipType").text();
					(shipText);
				} else {
					alert("更新数量失败");
				}
			},
			error : function() {
				alert("网络繁忙,请稍后!");
			}
		});

	}
	//创建订单
	function createOrder() {
		var shipAddreessId = $("#shipAddressId").val();
		if (shipAddreessId == null || shipAddreessId == "") {
			showMessageAutoTime("您还没有收货地址,请先建立收货地址", 2500);
			return false;
		}

		var shipText = $("#userhsipType").val();
       
		if (shipText == "商家配送") {

			$("#userShipType").val("2");

		} else {

			$("#userShipType").val("1");

		}
		getCompanyInfo();//配送费
		var ifAbove = intCartT();//校验库存 
		getPromotionActivity();//促销活动
		var sysCordId = $("#sysCrodId").val();
		if (sysCordId != null && sysCordId != "") {
			getMortgagePrice(sysCordId);//优惠券抵押
		}
		 
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
			} else {
				showMessageAutoTime("您没有商品要提交请去选择商品", 1500);
			}

		}

	}

	//选中状态切换更改后
	function active(e) {
		if ($(e).hasClass("per_payadrborder") == false) {
			$(e).siblings().removeClass("per_payadrborder");
			$(e).addClass("per_payadrborder");
			//alert($(e).text());
			showTotal($(e).text());
			if ($(e).text() == "商家配送") {
				$("#userShipType").val("2");
			} else {
				$("#userShipType").val("1");
			}

			$("#userhsipType").val($(e).text());
		}

	}
	//优惠券使用
	function ticket() {
		$(".per_ticket_left").css("display", "none");
		$(".per_ticket_left02").css("display", "block");
		$(".per_ticket_infor").css("display", "block");

	}
	function ticket02() {
		$(".per_ticket_left").css("display", "block");
		$(".per_ticket_left02").css("display", "none");
		$(".per_ticket_infor").css("display", "none");

	}
	function adredit() {
		$(".per_payadr_edit").css("display", "block")
	}
	function editnone() {
		$(".per_payadr_edit").css("display", "none")
	}

	
$('.clickBtnAddress').click(function() {
		layer.open({
			type : 1,
			title : false,
			closeBtn : 2,
			shade : 0.3,
			area : [ '700px', '400px' ],
			content : $('.layerBoxAddress')
		});
		initUlList("add", "areadd", 3);
		$(".inshow").css('display', 'block');
		$(".upshow").css('display', 'none');

		$("#consignee").val("");
		$("#location").val("");
		$("#mobile").val("");
		//$('#form_config').attr("action", SHOPDOMAIN+"/interfaces/userLocation/saveLocation.html");
	});
	//更新
	function changeLoc(id) {
		//var id = $("#shipAddressId").val();
		initUlList("updatel", "areadd", 3);
		layer.open({
			type : 1,
			title : false,
			closeBtn : 2,
			shade : 0.3,
			area : [ '700px', '400px' ],
			content : $('.layerBoxAddress')
		});
		$(".inshow").css('display', 'none');
		$(".upshow").css('display', 'block');
		//修改表单的action
		//$('#form_config').attr("action", SHOPDOMAIN+"/interfaces/userLocation/updateLocationPc.html");
		$.post(
				SHOPDOMAIN + '/interfaces/userLocation/seleById.html',
				{
					id : id,
				},
				function(data) {
					if (data.res_code == '0') {
						$("#consignee").val(data.list.consignee);
						$("#mobile").val(data.list.mobile);
						$("#location").val(data.list.location);
						$("#address").val(data.list.regionName);
						$("#id").val(data.list.id);
						if (data.list.provinceId != null) {
							updateArea(data.list.provinceId, data.list.cityId,
									data.list.areaId, "updatel",
									data.list.regionName);
						}
					} else {
						showMessage(data.message);
					}
				}, "json").error(function() {
			showError();
		});
	}
	//更新
	function submmit() {
		//alert("更新");
		if ($("#locationupdatel").val().trim() == ""
				|| $("#cityupdatel").val().trim() == ""
				|| $("#areaupdatel").val().trim() == ""
				|| $("#provinceupdatel").val().trim() == "") {
			showMessage("请选择完善位置信息！");
			return false;
		} else {
			url = "shoppingcart_account.html";
			if (url != null || url != "") {
				$("#urls").val(url);
			}
			if ($("#consignee").val() == "") {
				showm("收货人必填");
				return false;
			} else if ($("#mobile").val() == "") {
				showm("手机号必填");
				return false;
			} else if ($("#location").val() == "") {
				showm("详细地址必填");
				return false;
			} else {
				var myreg = /^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/;
				if (!myreg.test($("#mobile").val())) {
					showm('请输入有效的手机号码');
					return false;
				} else {
					//alert($("#mobile").val());
					url = "shoppingcart_account.html";
					$.ajax({
						url : SHOPDOMAIN+"/interfaces/userLocation/updateLocationPc.html",
						data : $("#form_config").serialize(),
						dataType : "json",
						async : false,
						success : function(data) {
							if (data.res_code == '0') {
								showm(data.message);
								layer.closeAll();
								//	showMessage(data.message);
								initCart();
							} else {
								showMessage(data.message);
							}
						},
						error : function() {
							showError();
						}
					});
				}
			}
		}
	}
	
	function showm(showmess) {
		layer.msg(showmess, {
			time : 1000
		});
	}
	//添加方法

	function updatePas() {
		//alert("添加");
		if ($("#locationadd").val().trim() == ""
				|| $("#cityadd").val().trim() == ""
				|| $("#areaadd").val().trim() == ""
				|| $("#provinceadd").val().trim() == "") {
			showMessage("请选择完善位置信息！");
			return false;
		} else {
			url = "shoppingcart_account.html";
			if (url != null || url != "") {
				$("#urls").val(url);
			}
			if ($("#consignee").val() == "") {
				showm("收货人必填");
				return false;
			} else if ($("#mobile").val() == "") {
				showm("手机号必填");
				return false;
			} else if ($("#location").val() == "") {
				showm("详细地址必填");
				return false;
			} else {
 
				var myreg = /^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/;
 
				if (!myreg.test($("#mobile").val())) {
					showm('请输入有效的手机号码');
					return false;
				} else {
					$.ajax({
								url : SHOPDOMAIN+"/interfaces/userLocation/saveLocation.html",
								type:"POST",
								data : $("#form_config").serialize(),
								dataType : "json",
								async : false,
								success : function(data) {
									if (data.res_code == '0') {
										layer.closeAll();
										initCart();
										showm(data.message);
									} else {
										showMessage(data.message);
									}
								},
								error : function() {
									showError();
								}
							});
				}
			}

		}
	}

    $('.showAndhide').on('click', function(){
        if($('.showSpan').html()=="更多地址"){
       	 $("#chooseAddress .trPadding").show();
            $('.showSpan').html('收起地址');
            $(this).addClass('hideArrow')

        }else{
       	 $("#chooseAddress .trPadding").hide();
            $('.tableAdress .per_payadrborder').parent().parent().show();
            $('.showSpan').html('更多地址');
            $(this).removeClass('hideArrow')
        }
    })
	