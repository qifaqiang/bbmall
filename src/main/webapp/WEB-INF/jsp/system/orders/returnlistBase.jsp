<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />


<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			售后服务 <small></small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					售后服务 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 退货处理 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-6" style="width: 100%">
		<!-- BEGIN SAMPLE TABLE PORTLET-->
		<form action="returnorderslist.html?typeid=${param.typeid }" method="post">
			<input type="hidden" value="${param.ty }" name="ty">
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class=" fa fa-cogs"></i>信息
					</div>
				</div>
				<div class="portlet-body">
				<div class="col-md-4" style="width:540px;">
						<div class="controls">
							 <input type="text"
								id="ordersn" style="width: 160px;float:left" name="ordersn"
								placeholder="订单编号" class="form-control" value="${os.ordersn }"
								class="span4">
							<div class="input-prepend input-group" style="margin-left:180px;">
								<span class="add-on input-group-addon"><i
									class="glyphicon glyphicon-calendar fa fa-calendar"></i></span> <input
									type="text" style="width: 280px" name="ttime"
									placeholder="退货申请时间" id="ttime" class="form-control"
									readonly="readonly" value="${ttime }" class="span4">

							</div>

						</div>
					</div>
					<button class="btn purple" type="submit">
						<i class="fa fa-check"></i> 查找
					</button>
					<br> <br>
					<div class="span6">
						<!--BEGIN TABS-->
						<div class="tabbable tabbable-custom">
							<ul class="nav nav-tabs">
								<li class="gt-1"><a data-toggle="tab" href="#tab_1_1"
									onclick="gotos('-1')">所有申请(${s50+s60 })</a></li>
								<li class="gt50"><a data-toggle="tab" href="#tab_1_2"
									onclick="gotos('50')">申请中(${empty s50 ?0 :s50 })</a></li>
								<li class="gt60"><a data-toggle="tab" href="#tab_1_3"
									onclick="gotos('60')">处理完成(${empty s60 ?0 :s60})</a></li>
							</ul>
							<div class="tab-content">
								<div id="tab_1_1" class="tab-pane active">
									<div class="table-responsive" style="margin-top: 10px">
										<table class="table table-bordered table-hover"
											style="TABLE-LAYOUT: fixed">
											<thead>
												<tr>
													<th style="text-align: center;width:30%;width:270px;">商品</th>
													<th style="text-align: center;width:10%">买家信息</th>
													<th style="text-align: center;width:10%">商品价格</th>
													<th style="text-align: center;width:10%">运费价格</th>
													<th style="text-align: center;width:10%">优惠价格</th>
													<th style="text-align: center;width:10%">实付金额</th>
													<th style="text-align: center;width:20%">退货原因</th>
													<th style="text-align: center;width:20%">状态</th>
													<th style="text-align: center;width:10%">操作</th>
												</tr>
											</thead>
											<tbody>
												<tr class="">
													<td colspan="9"
														style="background-color: white;font-size: 10pt;line-height:30px;"></td>
												</tr>
												<c:choose>
													<c:when test="${not empty orResultlist}">
														<c:forEach items="${orResultlist}" var="orders"
															varStatus="vs">
															<tr class="">
																<td colspan="9"
																	style="background-color: white;font-size: 10pt;line-height:30px;">订单编号：${orders.ordersn }
																	&nbsp; &nbsp; &nbsp;
																	&nbsp;下单时间<fmt:formatDate
																		value="${orders.addtime}" type="both"
																		pattern="yyyy/MM/dd HH:mm:ss" />&nbsp; &nbsp; &nbsp;
																	&nbsp;退货申请时间<fmt:formatDate
																		value="${orders.rg.addtime}" type="both"
																		pattern="yyyy/MM/dd HH:mm:ss" />&nbsp; &nbsp; &nbsp; &nbsp; <span style="color: red"><c:choose>
																			<c:when test="${ orders.status==11}">待支付</c:when>
																			<c:when test="${ orders.status==20}">待发货</c:when>
																			<c:when test="${ orders.status==30}">待收货</c:when>
																			<c:when test="${ orders.status==40}">已完成</c:when>
																			<c:when test="${ orders.status==50}">退货处理中</c:when>
																			<c:when test="${ orders.status==60}">退货已处理</c:when>
																			<c:when test="${ orders.status==0}">已取消</c:when>
																		</c:choose></span>&nbsp; &nbsp; &nbsp;
																	&nbsp; ${orders.company.companyName }
																</td>
															</tr>
															<tr class="">
																<td
																	style="width:30%;text-align: center;vertical-align: middle;"><c:forEach
																		items="${orders.od}" var="products">
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
																				<c:if test="${not empty products.prodSpecName }">[${products.prodSpecName}]</c:if> <br> <span
																					style="color: gray"> ￥${products.prodPrice}
																					&nbsp; 数量：${products.prodCount}</span>
																			</div>
																		</div><br><br><br>
																	</c:forEach></td>
																<td
																	style="width:10%;text-align: center;vertical-align: middle;">${orders.ul.consignee}<br>${orders.ul.mobile}</td>
																<td
																	style="width:10%;text-align: center;vertical-align: middle;">￥${empty orders.orderPrice?"0.00":orders.orderPrice}
																	<c:if test="${not empty orders.firstOrderPrice}"><br>首单立减￥${orders.firstOrderPrice }</c:if></td>
																<td
																	style="width:10%;text-align: center;vertical-align: middle;">￥${empty orders.shipPrice?"0.00":orders.shipPrice}</td>
																<td
																	style="width:10%;text-align: center;vertical-align: middle;">促销折扣：￥${empty orders.promotionPrice?"0.00":orders.promotionPrice}<br>
																	优惠券折扣：￥${empty orders.couponsPrice?"0.00":orders.couponsPrice}
																</td>
																<td
																	style="width:10%;text-align: center;vertical-align: middle;">￥${empty orders.orderAccount?"0.00":orders.orderAccount}</td>
																<td
																	style="width:20%;text-align: center;vertical-align: middle;">${orders.rg.remark}</td>
																<td
																	style="width:20%;text-align: center;vertical-align: middle;word-wrap:break-word;">
																	<c:if test="${orders.rg.status==0}">退货处理中</c:if> <c:if
																		test="${orders.rg.status==1}">退货通过</c:if> <c:if
																		test="${orders.rg.status==2}">退货未通过</c:if>
																</td>
																<td
																	style="width:10%;text-align: center;vertical-align: middle;"><a
																	class="btn big blue" href="javascript:void(0)"
																	onclick="toinfo(${orders.id})"> <i class=""></i>
																		查看详情
																</a></td>
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

<script>
	function toinfo(id) {
		window.location.href = "ordersreturninfo.html?typeid=${param.typeid}&orderid="
				+ id;

	}
	function gotos(ty) {
		window.location.href = "returnorderslist.html?typeid=${param.typeid}&ty="
				+ ty+"&ordersn="+$("#ordersn").val();
	}

	function toexcel() {
		window
				.open("${SHOPDOMAIN}/system/orders/toexcel.html?typeid=${param.typeid}&from="
						+ $("#tfrom").val()
						+ "&tto="
						+ $("#tto").val()
						+ "&ty=${param.ty}");
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
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/moment.js"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>

<jsp:include page="../foot.jsp"></jsp:include>