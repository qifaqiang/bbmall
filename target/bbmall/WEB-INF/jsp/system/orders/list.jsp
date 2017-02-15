<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<style>
.table-responsive>.table>tbody>tr>td,.table-responsive>.table>tbody>tr>th,.table-responsive>.table>tfoot>tr>td,.table-responsive>.table>tfoot>tr>th,.table-responsive>.table>thead>tr>td,.table-responsive>.table>thead>tr>th
	{
	word-break: break-all;
	white-space: normal;
}
</style>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			订单列表 <small></small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					订单管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 订单列表 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-6" style="width: 100%">
		<!-- BEGIN SAMPLE TABLE PORTLET-->
		<form action="orderslist.html?typeid=${param.typeid }" method="post">
			<input type="hidden" value="${param.ty }" name="ty">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class=" fa fa-cogs"></i>信息
					</div>
				</div>
				<div class="portlet-body">
					<div class="col-md-4" style="width:1050px;">
						<div class="controls">
							<select
								style="width: 150px; float: left;margin-right: 10px;margin-top: 3px"
								class="form-control select2me" id="companyId" name="companyId"
								data-placeholder="选择">
								<option value="0">--筛选基地--</option>
								<c:forEach items="${ListCompanyName}" var="p" varStatus="vs">
									<option value="${p.companyId}"
										<c:if test="${orders.companyId==p.companyId }"> selected="selected"</c:if>>${p.companyName }</option>
								</c:forEach>
							</select> <input type="text" id="pickCode" style="width: 150px;float:left"
								name="pickCode" id="pickCode" placeholder="提货码" class="form-control"
								value="${pickCode }" class="span4"> <input type="text"
								style="width: 160px;margin-left:15px;float:left" name="ordersn"
								placeholder="订单编号" class="form-control" id="ordersn" value="${os.ordersn }"
								class="span4">
								<input type="text" style="width: 160px;margin-left:15px;float:left" name="tradingCode"
								placeholder="支付流水号" class="form-control" id="tradingCode" value="${os.tradingCode }"
								class="span4">
							<div class="input-prepend input-group" style="margin-left:680px;">
								<span class="add-on input-group-addon"><i
									class="glyphicon glyphicon-calendar fa fa-calendar"></i></span> <input
									type="text" style="width: 280px" name="ttime"
									placeholder="订单时间" id="ttime" class="form-control"
									readonly="readonly" value="${ttime }" class="span4">

							</div>

						</div>
					</div>
					<button class="btn purple" type="submit">
						<i class="fa fa-check"></i> 查找
					</button>
					<button class="btn green" type="button" onclick="toexcel()">
						<i class="fa fa-share"></i> 导出
					</button>
					<br> <br>
					<div class="span6">
						<!--BEGIN TABS-->
						<div class="tabbable tabbable-custom">
							<ul class="nav nav-tabs">
								<li class="gt-1"><a data-toggle="tab" href="#tab_1_1"
									onclick="gotos('-1')">所有订单(${s11+s20+s22+s100+s30+s40+s0 })</a></li>
								<li class="gt11"><a data-toggle="tab" href="#tab_1_2"
									onclick="gotos('11')">待付款(${empty s11 ?0 :s11 })</a></li>
								<li class="gt20"><a data-toggle="tab" href="#tab_1_3"
									onclick="gotos('20')">待接单(${empty s20 ?0 :s20})</a></li>
								<li class="gt22"><a data-toggle="tab" href="#tab_1_3"
									onclick="gotos('22')">待发货(${empty s22 ?0 :s22})</a></li>
								<li class="gt30"><a data-toggle="tab" href="#tab_1_4"
									onclick="gotos('30')">待收货(${empty s30 ?0 :s30})</a></li>
								<li class="gt40"><a data-toggle="tab" href="#tab_1_5"
									onclick="gotos('40')">交易完成(${empty s40 ?0 :s40 })</a></li>
								<li class="gt0"><a data-toggle="tab" href="#tab_1_6"
									onclick="gotos('0')">已关闭(${s0+s100==0 ?0 :s0+s100 })</a></li>
							</ul>
							<div class="tab-content">
								<div id="tab_1_1" class="tab-pane active">
									<div class="table-responsive" style="margin-top: 10px">
										<c:if test="${ orders.status==20}">
											<a href="javascript:void(0)" onclick="receivingOrderBatch()"
												class="btn btn-sm green"> 批量接单<i class="fa fa-hdd-o"></i>
											</a>
											<br>
											<br>
										</c:if>
										<c:if test="${ orders.status==22}">
											<!-- <a href="javascript:void(0)" onclick="addshipbatch()"
												class="btn btn-sm green"> 批量填写快递单号 <i class="fa fa-link"></i>
											</a> --> &nbsp; <a href="javascript:void(0)"
												onclick="toexcelbatch()" class="btn btn-sm green">
												导出未发货的订单 <i class="fa fa-share"></i>
											</a> &nbsp; <a href="javascript:void(0)" onclick="tosendbatch()"
												class="btn btn-sm green"> 批量发货<i class="fa fa-truck"></i>
											</a>
											<br>
											<br>
										</c:if>
										<table class="table table-bordered table-hover"
											style="TABLE-LAYOUT: fixed">
											<thead>
												<tr>
													<th style="text-align: center;width:25%;width:270px;">商品</th>
													<th style="text-align: center;width:10%">收货人</th>
													<th style="text-align: center;width:10%">商品价格</th>
													<th style="text-align: center;width:10%">运费价格</th>
													<th style="text-align: center;width:10%">优惠价格</th>
													<th style="text-align: center;width:10%">实付金额</th>
													<th style="text-align: center;width:20%">留言</th>
													<c:if test="${ orders.status==20}">
														<th style="text-align: center;width:20%">快递单号</th>
													</c:if>
													<th style="text-align: center;width:15%">操作</th>
												</tr>
											</thead>
											<tbody>
												<tr class="">
													<td colspan="9"
														style="background-color: white;font-size: 10pt;line-height:30px;"></td>
												</tr>
												<c:choose>
													<c:when test="${not empty orResultlist}">
														<c:forEach items="${orResultlist}" var="ords"
															varStatus="vs">
															<tr class="">
																<td colspan="9"
																	style="background-color: white;font-size: 10pt;line-height:30px;">订单编号：${ords.ordersn }
																	&nbsp; &nbsp; &nbsp; &nbsp;<fmt:formatDate
																		value="${ords.addtime}" type="both"
																		pattern="yyyy/MM/dd HH:mm:ss" />&nbsp; &nbsp; &nbsp;
																	&nbsp; <span style="color: red"><c:choose>
																			<c:when test="${ ords.status==11}">待支付</c:when>
																			<c:when test="${ ords.status==20}">待接单</c:when>
																			<c:when test="${ ords.status==22}">待发货</c:when>
																			<c:when test="${ ords.status==30}">待收货</c:when>
																			<c:when test="${ ords.status==40}">已完成</c:when>
																			<c:when test="${ ords.status==50}">退货处理中</c:when>
																			<c:when test="${ ords.status==60}">退货已完成</c:when>
																			<c:when test="${ ords.status==0}">用户已取消</c:when>
																			<c:when test="${ ords.status==100}">系统取消</c:when>
																		</c:choose></span>&nbsp; &nbsp; &nbsp; &nbsp; ${ords.company.companyName }&nbsp;
																	&nbsp; &nbsp; &nbsp;
																	<button type="button" class="btn btn-sm blue"
																		onclick="print(${ords.id})">打印</button>
																</td>
															</tr>
															<tr class="">
																<td
																	style="width:30%;vertical-align: middle;width:270px;"><c:forEach
																		items="${ords.od}" var="products">
																		<div style="">
																			<div style="float: left">
																				<img width="50px"
																					src="${USERIMGSRC }${fn:replace(products.prodUri,'.','_160.')}" />
																			</div>
																			<div style="float: left;">
																				<c:if test="${fn:length(products.prodName)>=10}">
																					${fn:substring(products.prodName,0,10)}...
																				</c:if>
																				<c:if test="${fn:length(products.prodName)<10}">
																					${products.prodName}
																				</c:if>
																				<c:if test="${not empty products.prodSpecName }">[${products.prodSpecName}]</c:if>
																				<br> <span style="color: gray">
																					&nbsp;￥${products.prodPrice} &nbsp;
																					数量：${products.prodCount} &nbsp;</span>
																			</div>
																		</div>
																		<br>
																		<br>
																		<br>
																	</c:forEach></td>
																<td
																	style="width:10%;text-align: center;vertical-align: middle;">${ords.ul.consignee}<br>${ords.ul.mobile}
																	<c:if test="${ords.userShipType==1}">
																		<br><span style='color:red'>自提  </span></c:if><c:if test="${not empty ords.pickCode}">
																		提货码：<br>${ords.pickCode }</c:if>
																	</td>
																<td
																	style="width:10%;text-align: center;vertical-align: middle;">￥${empty ords.orderPrice?"0.00":ords.orderPrice}
																	<c:if test="${not empty ords.firstOrderPrice}"><br>首单立减￥${ords.firstOrderPrice }</c:if>
																	</td>
																<td
																	style="width:10%;text-align: center;vertical-align: middle;">￥${empty ords.shipPrice?"0.00":ords.shipPrice}</td>
																<td
																	style="width:10%;text-align: center;vertical-align: middle;">促销折扣：￥${empty ords.promotionPrice?"0.00":ords.promotionPrice}<br>
																	优惠券折扣：￥${empty ords.couponsPrice?"0.00":ords.couponsPrice}
																</td>
																<td
																	style="width:10%;text-align: center;vertical-align: middle;">￥${empty ords.orderAccount?"0.00":ords.orderAccount}</td>
																<td
																	style="width:20%;text-align: center;vertical-align: middle;word-wrap:break-word;">${ords.remark}</td>
																<c:if test="${ orders.status==20}">
																	<td
																		style="width:20%;text-align: center;vertical-align: middle;word-wrap:break-word;">${ords.shipName}<br>${ords.shipCode}</td>
																</c:if>
																<td
																	style="width:10%;text-align: center;vertical-align: middle;"><button
																		type="button" class="btn btn-sm blue"
																		onclick="toinfo(${ords.id})">查看详情</button> <br> <c:choose>
																		<c:when test="${ ords.status==22}">
																			<!-- <button type="button" class="btn   green" style="margin-top: 10px"
																				onclick="updateship(${ords.id},'${ords.shipId}','${ords.shipCode}')">
																				修改快递单号</button>
																			<br> -->
																			<button type="button" class="btn btn-sm green"
																				style="margin-top: 10px" onclick="fahuo(${ords.id})">标记发货</button>
																			<br>
																		</c:when>
																	</c:choose> <c:choose>
																		<c:when test="${ ords.status==11}">
																			<button type="button" class="btn btn-sm green"
																				style="margin-top: 10px"
																				onclick="updateprice(${ords.id},'${ords.orderPrice}','${ords.shipPrice}')">
																				修改价格</button>
																			<button type="button" class="btn btn-sm red"
																				style="margin-top: 10px"
																				onclick="cancelOrder(${ords.id})">取消订单</button>
																		</c:when>
																	</c:choose> <c:choose>
																		<c:when test="${ ords.status==20}">
																			<button type="button" class="btn btn-sm green"
																				style="margin-top: 10px"
																				onclick="receivingOrder(${ords.id})">接单</button>
																			<button type="button" class="btn btn-sm purple"
																				style="margin-top: 10px"
																				onclick="chargeCompany(${ords.id})">修改配送基地</button>
																		</c:when>
																	</c:choose></td>
															</tr>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<tr class="main_info">
															<td colspan="7">没有相关数据</td>
														</tr>
													</c:otherwise>
												</c:choose>
											</tbody>
										</table>
										<div class="page_and_btn">
											<div></div>
											${os.page.pageStr }
										</div>
									</div>
								</div>
							</div>
						</div>
						<!--END TABS-->
					</div>
				</div>
			</div>
		</form>
		<!-- END SAMPLE TABLE PORTLET-->
	</div>
</div>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/moment.js"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>

<script>
	function toinfo(id) {
		window.location.href = "ordersinfo.html?typeid=${param.typeid}&orderid="
				+ id;

	}
	//修改订单的快递单号
	function updateship(id, shippingname, shipsn) {
		var shipname = "${ss.name}";
		if (shipname == "") {
			alert("请先录入物流信息");
			return;
		}
		var html = "<div>物流公司:" + shipname + "</div>";

		bootbox
				.dialog({
					message : '<div style="margin-left:100px">'
							+ html
							+ '<br><div>快递单号:<input type="text" class="form-control" style="width:200px" id="yundanhao" value="'+shipsn+'"></div></div>',
					title : "修改物流单号",
					buttons : {
						main : {
							label : "提交!",
							className : "blue",
							callback : function() {
								$
										.ajax({
											url : '${SHOPDOMAIN}/system/orders/updateship.html',
											type : 'post',
											dataType : 'json',
											data : {
												orderid : id,
												shipcode : $("#yundanhao")
														.val()
											},
											success : function(data) {
												if (data.Status == "1") {
													window.location.href = window.location.href;
												} else {
													alert(data.Message);
												}
											}
										})
							}
						}
					}
				});
	}

	//修改订单的价格
	function updateprice(id, orderPrice, shipPrice) {
		var html = "<div>商品价格:<input class='form-control' style='width:200px' id='orderPricec' value='"+orderPrice+"' ><br><div>运费价格:<input  id='shipPricec' class='form-control' style='width:200px' value='"+shipPrice+"' ></div></div>";
		bootbox
				.dialog({
					message : '<div style="margin-left:100px">' + html,
					title : "修改订单的价格 注意：总价格必须大于0",
					buttons : {
						main : {
							label : "提交!",
							className : "blue",
							callback : function() {
								if (MoneyCheck($("#orderPricec").val())
										&& MoneyCheck($("#shipPricec").val())) {
									$
											.ajax({
												url : '${SHOPDOMAIN}/system/orders/updatePrice.html',
												type : 'post',
												dataType : 'json',
												data : {
													orderid : id,
													orderPrice : $(
															"#orderPricec")
															.val(),
													shipPrice : $("#shipPricec")
															.val()
												},
												success : function(data) {
													if (data.Status == "1") {
														window.location.href = window.location.href;
													} else {
														alert(data.Message);
													}
												}
											});
								} else {
									return false;
								}
							}
						}
					}
				});
	}

	//批量填写订单编号
	function addshipbatch() {
		var shipname = "${ss.name}";
		if (shipname == "") {
			alert("请先录入物流信息");
			return;
		}
		var html = "<div>物流公司:" + shipname + "</div>";

		bootbox
				.dialog({
					message : '<div style="margin-left:100px">'
							+ html
							+ '<div>快递单号:<input type="text" class="form-control" style="width:200px" id="yundanhao" value="">(为数字)</div></div>',
					title : "批量填写快递单号，请填写首个快递单号",
					buttons : {
						main : {
							label : "提交!",
							className : "blue",
							callback : function() {
								$
										.ajax({
											url : '${SHOPDOMAIN}/system/orders/addshipbatch.html',
											type : 'post',
											dataType : 'json',
											data : {
												ttime : $("#ttime").val(),
												shipcode : $("#yundanhao")
														.val()
											},
											success : function(data) {
												if (data.Status == "1") {
													bootbox
															.dialog({
																message : '批量成功添加了：'
																		+ data.count
																		+ '条发货单号，订单号为：'
																		+ data.Message,
																title : "系统提示",
																buttons : {
																	main : {
																		label : "确定!",
																		className : "blue",
																		callback : function() {
																			window.location.href = window.location.href;
																		}
																	}
																}
															});
												} else {
													alert(data.Message);
												}
											}
										})
							}
						}
					}
				});
	}

	//批量发货
	function tosendbatch() {
		bootbox
				.dialog({
					message : '确定要批量发货吗?',
					title : "提示",
					buttons : {
						main : {
							label : "发货!",
							className : "blue",
							callback : function() {
								$
										.ajax({
											url : '${SHOPDOMAIN}/system/orders/tosendbatch.html',
											type : 'post',
											dataType : 'json',
											data : {
												ttime : $("#ttime").val()
											},
											success : function(data) {
												if (data.Status == "1") {
													bootbox
															.dialog({
																message : '批量发货了：'
																		+ data.count
																		+ '条发货单号，订单号为：'
																		+ data.Message,
																title : "系统提示",
																buttons : {
																	main : {
																		label : "确定!",
																		className : "blue",
																		callback : function() {
																			window.location.href = window.location.href;
																		}
																	}
																}
															});
												} else {
													alert(data.Message);
												}
											}
										})
							}
						}
					}
				});
	}

	//发货
	function fahuo(id) {
		bootbox
				.dialog({
					message : '确定要发货吗？',
					title : "提示",
					buttons : {
						main : {
							label : "发货!",
							className : "blue",
							callback : function() {
								$
										.ajax({
											url : '${SHOPDOMAIN}/system/orders/fahuo.html',
											type : 'post',
											dataType : 'json',
											data : {
												orderid : id
											},
											success : function(data) {
												if (data.Status == "1") {
													window.location.href = window.location.href;
												} else {
													alert(data.Message);
												}
											}
										})
							}
						}
					}
				});
	}

	//批量接单
	function receivingOrderBatch() {
		bootbox
				.dialog({
					message : '确定要批量接单吗？',
					title : "提示",
					buttons : {
						main : {
							label : "接单!",
							className : "blue",
							callback : function() {
								$
										.ajax({
											url : '${SHOPDOMAIN}/system/orders/receivingOrderBatch.html',
											type : 'post',
											dataType : 'json',
											beforeSend : function() {
												showTip('正在接单中请稍后……');
											},
											success : function(data) {
												bootbox.hideAll();
												if (data.Status == "1") {
													showMessageVsRefresh("成功接单"
															+ data.count + "条！")
												} else {
													showMessage(data.Message);
												}
											}
										})
							}
						}
					}
				});
	}

	//单个接单
	function receivingOrder(orderid) {
		bootbox
				.dialog({
					message : '确定要接单吗？',
					title : "提示",
					buttons : {
						main : {
							label : "接单!",
							className : "blue",
							callback : function() {
								$
										.ajax({
											url : '${SHOPDOMAIN}/system/orders/receivingOrder.html',
											type : 'post',
											dataType : 'json',
											data : {
												orderid : orderid
											},
											beforeSend : function() {
												showTip('正在接单中请稍后……');
											},
											success : function(data) {
												bootbox.hideAll();
												if (data.Status == "1") {
													showMessageVsRefresh("成功接单！")
												} else {
													showMessage(data.Message);
												}
											}
										})
							}
						}
					}
				});
	}

	//消该订单
	function cancelOrder(orderid) {
		bootbox
				.dialog({
					message : '确定要取消该订单吗？',
					title : "提示",
					buttons : {
						main : {
							label : "取消订单!",
							className : "blue",
							callback : function() {
								$
										.ajax({
											url : '${SHOPDOMAIN}/system/orders/cancelOrder.html',
											type : 'post',
											dataType : 'json',
											data : {
												orderid : orderid
											},
											beforeSend : function() {
												showTip('正在取消中请稍后……');
											},
											success : function(data) {
												bootbox.hideAll();
												if (data.Status == "1") {
													showMessageVsRefresh("成功取消！")
												} else {
													showMessage(data.Message);
												}
											}
										})
							}
						}
					}
				});
	}

	//切换基地
	function chargeCompany(id) {
		var temp = "<select class='form-control' id='companyBaseId' style='width:300px'>";
		<c:forEach items="${ListCompanyName}" var="lc">
		temp += "<option value='"+${lc.companyId}+"'>${lc.companyName}</option>";
		</c:forEach>
		var html = "<div>新基地位置:" + temp + "</select></div>";
		bootbox
				.dialog({
					message : '<div style="margin-left:100px">' + html,
					title : "修改配送基地",
					buttons : {
						main : {
							label : "提交!",
							className : "blue",
							callback : function() {
								$
										.ajax({
											url : '${SHOPDOMAIN}/system/orders/chargeCompany.html',
											type : 'post',
											dataType : 'json',
											data : {
												orderid : id,
												newCompanyId : $(
														"#companyBaseId").find(
														"option:selected")
														.val()
											},
											success : function(data) {
												if (data.Status == "1") {
													window.location.href = window.location.href;
												} else {
													alert(data.Message);
												}
											}
										});
							}
						}
					}
				});
	}

	function gotos(ty) {
		window.location.href = "orderslist.html?typeid=${param.typeid}&ty="
				+ ty + "&ttime=" + $("#ttime").val() + "&companyId=" + $("#companyId").val()+ "&pickCode=" + $("#pickCode").val()+"&ordersn="+$("#ordersn").val();
	}

	function toexcel() {
		window
				.open("${SHOPDOMAIN}/system/orders/toexcel.html?typeid=${param.typeid}&ttime="
						+ $("#ttime").val()
						+ "&ty=${param.ty}"
						+ "&pickCode="
						+ $("#pickCode").val()+ "&tradingCode="
						+ $("#tradingCode").val()+"&companyId="+$("#companyId").find("option:selected").val());
	}
	
	//导出订单
	function toexcelbatch() {
		window
				.open("${SHOPDOMAIN}/system/orders/toexcelbatch.html?typeid=${param.typeid}&ttime="
						+ $("#ttime").val()
						+ "&pickCode="
						+ $("#pickCode").val()+ "&tradingCode="
						+ $("#tradingCode").val()+"&companyId="+$("#companyId").find("option:selected").val());
	}

	function print(orderId) {
		var left = ($(window.parent.parent).width() - 450) / 2;

		window
				.open(
						"${SHOPDOMAIN}/system/orders/toprint.html?orderid="
								+ orderId,
						"小票打印",
						"height=750,width=200,top=0,left="
								+ left
								+ ",toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no");
	}

	$(function() {
		var party = "${param.ty}";
		if (party == "") {
			$(".gt-1").addClass("active");
		} else {
			$(".gt" + party).addClass("active");
		}
	})

	$(document).ready(function() {
		$('#ttime').daterangepicker({
			timePicker : false,
			timePickerIncrement : 30,
			format : 'YYYY-MM-DD HH:mm'

		}, function(start, end, label) {
			console.log(start.toISOString(), end.toISOString(), label);
		});
	});
</script>

<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>