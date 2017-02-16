$("#lingquan").html("购物车");
		var i = 0;
		hotProList();
		var sp = new ScrollPic();
		sp.scrollContId = "FS_Cont_01"; //内容容器ID
		sp.arrLeftId = "FS_arr_left_01";//左箭头ID
		sp.arrRightId = "FS_arr_right_01"; //右箭头ID
		sp.dotListId = "FS_numList_01";//点列表ID
		sp.dotClassName = "";//点className
		sp.dotOnClassName = "current";//当前点className
		sp.listType = "";//列表类型(number:数字，其它为空)
		sp.listEvent = "onmouseover"; //切换事件
		sp.frameWidth = 1008;//显示框宽度
		sp.pageWidth = 988; //翻页宽度
		sp.upright = false; //垂直滚动
		sp.speed = 10; //移动速度(单位毫秒，越小越快)
		sp.space = 60; //每次移动像素(单位px，越大越快)
		sp.autoPlay = true; //自动播放
		sp.autoPlayTime = 5; //自动播放间隔时间(秒)
		sp.circularly = true;
		$(function() {
			initCart();
			$("#setAll").click(function() {
				var flag = $(this).prop("checked");
				$("#setAllT").prop("checked", flag);
				setAll(flag);
				setJieSuanBtn(flag);

			});

			$("#setAllT").click(function() {
				var flag = $(this).prop("checked");
				$("#setAll").prop("checked", flag);
				setAll(flag);
				setJieSuanBtn(flag);

			});

		});
		var shu = 0;
		//左侧热销商品
		function hotProList() {
			$.ajax({
				url : SHOPDOMAIN+"/interfaces/newproductList.html",
				type : "post",
				data : {},
				dataType : "json",
				success : function(result) {
					var productList = doT.template($(".jsCarousel").html())(
							result);
					//alert(productList);
					$("#FS_Cont_01").html(productList);
					i = result.list.length;
					//alert("1");
					if (i >= 4) {
						sp.initialize();
					}
				}
			});
		}
		function choseBox() {
			var flagl;
			flagl = $(this).prop("checked");

			var selectedCount;
			if (flagl == false) {
				selectedCount = $("input[name='checkboxBtn']:checked").length;
				selectedCount = selectedCount - 1;
			} else {
				selectedCount = $("input[name='checkboxBtn']:checked").length;
			}

			var allCount = $(":checkbox[name=checkboxBtn]").length;//所有条目复选框个数

			if (selectedCount == allCount) {
				$("#setAll").prop("checked", true);
				$("#setAllT").prop("checked", true);
				setJieSuanBtn(true);//使结算按钮可用
			} else if (selectedCount == 0 || selectedCount == -1) {
				$("#setAll").prop("checked", false);
				$("#setAllT").prop("checked", false);
				setJieSuanBtn(false);//使结算按钮不可用			
			} else {
				$("#setAll").prop("checked", false);
				$("#setAllT").prop("checked", false);
				setJieSuanBtn(true);//使结算按钮不可用			
			}
			showTotal();//重新计算

		}
		//商品添加到购物车
		$(document).on("click", ".jscarousal_btn", function(event) {
			var idd = $(this).attr("prodId");
			$.ajax({
				type : "POST",
				url : SHOPDOMAIN + "/wap/shopCart/addCart.html",
				data : {
					prodId : $(this).attr("prodId"),
					specId : $(this).attr("specid"),
					count : 1
				},
				async : true,
				success : function(res) {
					if (0 == res.res_code) {
						initCart();//初始化购物车
						showMessageAutoTime("商品已添加至购物车！", 2000);
						$(".w-car" + idd).addClass("active");
						$(".w-car" + idd).html("已加购物车");
						cartCount();
						$("#footSetAll").show();
						

					} else {
						if (res.res_code == "1") {
							loginP("productList.html");
						} else {
							showMessage(res.message);
						}
					}
				},
				dataType : "json"
			});
			return false;
		});
		//删除购物车单个
		function showMessageToDelete(message, id) {
			layer.msg(message,
					{
						skin : 'layer-ext-myskinGlobal',
						closeBtn : 2,
						shade : 0.3,
						btn : [ '确定', '取消' ],

						btn1 : function() {
							$
									.ajax({
										async : false,
										url : SHOPDOMAIN+"/wap/shopCart/deleteUserCartItem.html",
										type : "post",
										data : {
											"id" : id,
										},
										dataType : "json",
										success : function(data) {

											if (data.flag == true) {
												showMessageAutoTime(
														"删除成功", 1500);
												initCart();
												showTotal();//重新计算
												cartCount();
											}
										}
									});
						},

						btn2 : function() {

						},

						time : 0
					});
		}

		function deleteCart(id) {
			showMessageToDelete("确认删除吗?", id);

		}
		//批量删除购物车
		function deleteBach() {
			var ids = new Array();
			var index = 0;
			$(":checkbox[name=checkboxBtn]:checked").each(function() {//循环遍历所有被选中的条目
				ids[index++] = $(this).attr("carId");//把被选择条目的cartItemId保存到数组中
			});
			if (ids.length == 0) {
				showMessageAutoTime("请选择要删除的条目", 1500);
				return false;
			}
			$("#ids").val(ids);
			var idsl = $("#ids").val();
			showMessageToDeletes("确认删除所选条目吗?", idsl);
		}

		function toditu() {
			window.location.href = SHOPDOMAIN + '/ditu.html';
		}

		function toIndex() {
			window.location.href = SHOPDOMAIN + '/index.html';
		}

		function showMessageToDeletes(message, ids) {
			layer.msg(message,{
					skin : 'layer-ext-myskinGlobal',
					closeBtn : 2,
					shade : 0.3,
					btn : [ '确定', '取消' ],

					btn1 : function() {
						$
								.ajax({
									async : false,
									url : SHOPDOMAIN+"/wap/shopCart/deleteUserCartItemBatch.html",
									type : "post",
									data : {
										"ids" : ids,
									},
									dataType : "json",
									success : function(data) {

										if (data.flag == "1") {
											showMessageAutoTime(
													"删除成功", 1500);
											initCart();
											showTotal();//重新计算
											cartCount();
										}
									}
								});
					},

					btn2 : function() {

					},

					time : 0
				});

		}

		//设置结算按钮的样式
		function setJieSuanBtn(flag) {

			if (flag) {// 有效状态

				$("#jiesuan").removeClass("kill");//切换样式
				$("#jiesuan").unbind("click");//撤消“点击无效”
			} else {// 无效状态

				$("#jiesuan").addClass("kill");//切换样式
				$("#jiesuan").click(function() {//使其“点击无效”
					return false;
				});
			}
		}
         //计算合计
		function showTotal() {
			var total = 0;//创建total，准备累加
			var totalquantity = 0;
			/*
			1. 获取所有被勾选的复选框，遍历之
			 */
			$(":checkbox[name=checkboxBtn]:checked").each(function() {
				/*
				2. 通过复选框找到小计
				 */
				var carId = $(this).attr("carId");
				var subtotal = Number($("#" + carId + "subtotal").text());
				var quantity = Number($("#" + carId + "quantity").val());
				total += subtotal;
				totalquantity += quantity;
			});

			$("#total").text(Number(total).toFixed(2));
			$("#totalQuantity").text(Number(totalquantity));
		}

		//加数量
		function plus(cartId, productId, count, specId) {
			var quantity = Number($("#" + cartId + "quantity").val());
			var stockMap = isAbolveStock(productId, quantity + 1,
					specId);

			if (JSON.stringify(stockMap.map).indexOf("SPEC_") >= 0
					|| JSON.stringify(stockMap.map).indexOf("P_") >= 0) {

				var stock = JSON.stringify(stockMap.map);
				var sto = stock.split(":");
				var dd = sto[0].replace(/\"/g, "");
				var stockNumber = sto[1].replace("}", "");
				showMessageAutoTime("您选择的商品已超出库存系统将数量改为最大库存", 1500);

				sendUpdate(cartId, stockNumber, productId);

			} else {
				sendUpdate(cartId, quantity + 1, productId);
			}
			cartCount();
		}

		//减数量
		function reduction(cartId, productId, count, specId) {
			var quantity = Number($("#" + cartId + "quantity").val());

			if (quantity == 1 || quantity == 0) {
				return false;
			} else {
				var stockMap = isAbolveStock(productId,
						quantity - 1, specId);

				if (JSON.stringify(stockMap.map).indexOf("SPEC_") >= 0
						|| JSON.stringify(stockMap.map).indexOf("P_") >= 0) {

					var stock = JSON.stringify(stockMap.map);
					var sto = stock.split(":");
					var dd = sto[0].replace(/\"/g, "");
					var stockNumber = sto[1].replace("}", "");
					if (quantity > stockNumber) {
						showMessageAutoTime("您选择的商品已超出库存 系统将数量改为最大库存", 1500);
						sendUpdate(cartId, stockNumber, productId);
					} else {
						sendUpdate(cartId, quantity - 1, productId);
					}
				} else {
					sendUpdate(cartId, quantity - 1, productId);
				}

			}
			cartCount();
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
						input.val(data.quantity);
						var price = $("#" + cartItemId + "price").text();
						var fsubtotal = data.quantity * price;
						$("#" + cartItemId + "subtotal").text(fsubtotal.toFixed(2));
						showTotal();
					} else {
						alert("更新数量失败");
					}
				},
				error : function() {
					alert("网络繁忙,请稍后!");
				}
			});

		}
        //判断是否超出库存
		function isAbolveStock(prodIds, counts, specIds) {
			var stockMap;
			$.ajax({
				async : false,
				url : SHOPDOMAIN+"/wap/shopCart/ifAboveStock.html",
				type : "post",
				dataType : "json",

				data : {
					"prodIds" : prodIds,
					"counts" : counts,
					"specIds" : specIds
				},
				success : function(data) {

					stockMap = data;
				}

			});
			return stockMap;
		}
       //初始化购物车
		function initCart() {
            //获取购物车数据
            $.ajax({
                async : false,
                cache : false,
                url : SHOPDOMAIN+"/wap/shopCart/userCart.html",
                success : function(data) {
                    var dataInfo = $.parseJSON(data);
                    if (dataInfo.res_code == "1") {
                        showMessageAutoTime("您还没有登录", 2500);//提示语
                        window.location.href = "login.html";
                    } else if (dataInfo.res_code == "0") {

                        var evalText = doT.template($(
                            "#interpolationtmpl").html());
                        if (dataInfo.list == null
                            || dataInfo.list == "") {
                            $("#topSetAll").hide();
                            $("#footSetAll").hide();
                            $("#emptyCart")
                                .html(
                                    " <div class='per_noorder'>"
                                    + " <div class=' per_ per_noorderleft flW'><img src='"+SHOPDOMAIN+"/front/images/web/per_noorder.png' width='104' height='48' alt=''/></div>"
                                    + "<div class=' per_noorderright flW'>"
                                    + " <ul>"
                                    + "<li>SORRY~您的购物车还没有商品哦~</li>"
                                    + "<li>可以去看看有哪些想买的</li>"
                                    + " <li><input type='button' onclick='toIndex()' class='per_evaluate_btn' value='随便逛逛'></li>"
                                    + "</ul>"
                                    + "</div>");
                        } else {//登录后初始化购物车
                            $("#emptyCart").html(" <table width='100%' border='0' cellspacing='0' cellpadding='0'>"+
                                "<tbody id='shopping-cart'> </tbody></table>");
                            $("#shopping-cart").html(evalText(dataInfo.list));
                            $("#shopping-cart")
                                .append(
                                    " <div style='clear: both' id='bbbbb'></div>");
                            intCartT();//初始化购物车
                            setAll(true);//默认选中所有购物车条目
                        }

                    }
                }
            });

		}
      //再次初始化购物车
		function intCartT() {
			var prods = "";
			var counts = "";
			var specIds = "";
			var cartId = "";
			$(".check").each(function() {

				prods += $(this).attr("prod") + ",";
				counts += $(this).attr("count") + ",";
				specIds += $(this).attr("specId") + ",";

			});

			var stockMap = isAbolveStock(prods.replace(/,$/g, ""),
					counts.replace(/,$/g, ""), specIds.replace(/,$/g, ""));//判断是否 超出库存

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
							$("#" + specId + "a").insertAfter("#bbbbb");
							$("#" + specId + "a").find(
									":checkbox[name=checkboxBtn]").remove();
							$("#" + specId + "a").find(".check").removeClass(
				  					"check");
							$("#" + specId + "picture")
									.after(
											"  <div class='noneProing'><img src='"+SHOPDOMAIN+"/front/images/outstock_tag.png'  alt=''/></div>");
							

						} else {
							var cartId = $("#" + specId).attr("carid");

							var productId = $("#" + specId).attr("prodid");
							name += $("#" + specId + "name").text() + " ";
							showMessageAutoTime("您选择的商品已超出库存已将" + name
									+ "改为最大库存量", 2500);

							sendUpdate(cartId, stockNumber, productId);//更新数量

						}
					}
				}
			}

		}
      //结算按钮事件
		function jieSuan() {
			var cartItemIds = new Array();
			var index = 0;
			$(":checkbox[name=checkboxBtn]:checked").each(function() {
				cartItemIds[index++] = $(this).attr("carId");

			});

			if (cartItemIds.length == 0) {
				showMessageAutoTime("您未选择任何商品,请选择商品进行结算", 1500);
				return false;
			}
			$("#cartItemIds").val(cartItemIds);
			$.ajax({
				async : false,
				url : SHOPDOMAIN+"/wap/shopCart/saveCartItems.html",
				type : "post",
				dataType : "json",
				data : {
					"cartItemIds" : $("#cartItemIds").val(),
				},
				success : function(data) {
					if (data.res_code == "1") {
						window.location.href = "shoppingcart_account.html";
					} else {
						alert("error");
					}
				}
			});

		}
         //全选按钮
		function setAll(flag) {

			$(":checkbox[name=checkboxBtn]").prop("checked", flag);//让所有条目的复选框与参数flag同步
			showTotal();//重新设置合计
		}