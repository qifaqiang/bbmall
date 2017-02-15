<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			订单详情 <small></small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					订单管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><span> 订单详情</span></li>
			<li><a href="javascript:history.go(-1)"> 返回</a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">

		<!-- BEGIN VALIDATION STATES-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>订单信息
				</div>
			</div>
			<div class="portlet-body form">
				<div class="form-wizard">
						<div class="navbar-inner">
							<ul class="row-fluid nav nav-pills steps">
								<li class="span3 active" style="width: 19.6%"><a
									class="step" href="#tab1"> <span class="number">1</span> <span
										class="desc" style="font-size:10pt"><i class="icon-ok"></i>
											创建 <fmt:formatDate value="${orders.addtime}" type="both"
												pattern="yyyy/MM/dd HH:mm:ss" /></span>
								</a></li>
								<li
									class="span3 <c:if test="${orders.status==20|| orders.status==30||orders.status==40||orders.status==50||orders.status==60 }">active</c:if>"
									style="width: 19.6%"><a class="step"
									style="background-color: #fff;" href="#tab2"> <span
										class="number">2</span> <span class="desc"
										style="font-size:10pt"><i class="icon-ok"></i> 付款 <fmt:formatDate
												value="${orders.payTime}" type="both"
												pattern="yyyy/MM/dd HH:mm:ss" /></span>
								</a></li>
								<li
									class="span3 <c:if test="${orders.status==30||orders.status==40||orders.status==50||orders.status==60 }">active</c:if>"
									style="width: 19.6%"><a class="step"
									style="background-color: #fff;" href="#tab3"> <span
										class="number">3</span> <span class="desc"
										style="font-size:10pt"><i class="icon-ok"></i> 发货 <fmt:formatDate
												value="${orders.shipTime}" type="both"
												pattern="yyyy/MM/dd HH:mm:ss" /></span>
								</a></li>
								<li
									class="span3 <c:if test="${orders.status==40 }">active</c:if>"
									style="width: 19.6%"><a class="step"
									style="background-color: #fff;" href="#tab4"> <span
										class="number">4</span> <span class="desc"
										style="font-size:10pt"><i class="icon-ok"></i> 确认收货 <fmt:formatDate
												value="${orders.acceptTime}" type="both"
												pattern="yyyy/MM/dd HH:mm:ss" /></span>
								</a></li>
								<li
									class="span3 <c:if test="${orders.is_over==1}">active</c:if>"
									style="width: 19.6%"><a class="step"
									style="background-color: #fff;" href="javascript:void(0)">
										<span class="number">5</span> <span class="desc"
										style="font-size:10pt"><i class="icon-ok"></i> 系统审核 <fmt:formatDate
												value="${orders.sysTime}" type="both"
												pattern="yyyy/MM/dd HH:mm:ss" /></span>
								</a></li>
							</ul>
						</div>
					<div class="progress progress-success progress-striped" id="bar">
						<div class="progress-bar"
							style="width: <c:choose>
									<c:when test="${ orders.status==11}">20%</c:when>
									<c:when test="${ orders.status==20}">40%</c:when>
									<c:when test="${ orders.status==30}">60%</c:when>
									<c:when test="${ orders.status==40}">80%</c:when>
									<c:when test="${ orders.status==0}">20%</c:when>
								</c:choose>;"></div>
					</div>
				</div>
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form_config" method="post"
					action="savewxpay.html" novalidate="novalidate">
					<div class="form-body"
						style="margin-top: -20px;float:left; width:50%">
						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left;width: 100%">订单状态 ：<c:choose>
									<c:when test="${ orders.status==11}">待支付</c:when>
									<c:when test="${ orders.status==20}">待接单</c:when>
									<c:when test="${ orders.status==22}">待发货</c:when>
									<c:when test="${ orders.status==30}">待收货</c:when>
									<c:when test="${ orders.status==40}">已完成</c:when>
									<c:when test="${ orders.status==50}">退货处理中</c:when>
									<c:when test="${ orders.status==60}">退货已完成</c:when>
									<c:when test="${ orders.status==0}">用户已取消</c:when>
									<c:when test="${ orders.status==100}">系统取消</c:when>
								</c:choose>
							</label>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left;width: 100%">付款方式 ：<c:choose>
									<c:when test="${ orders.status==11}">暂无</c:when>
									<c:when
										test="${ orders.status==20||orders.status==22|| orders.status==30||orders.status==40||orders.status==50||orders.status==60}">
										<c:choose>
											<c:when test="${ orders.payForm==1}">支付宝(pc)</c:when>
											<c:when test="${ orders.payForm==2}">微信扫码(pc)</c:when>
											<c:when test="${ orders.payForm==3}">银联支付(pc)</c:when>
											<c:when test="${ orders.payForm==4}">支付宝(wap)</c:when>
											<c:when test="${ orders.payForm==5}">微信支付</c:when>
											<c:when test="${ orders.payForm==6}">银联支付(wap)</c:when>
											<c:when test="${ orders.payForm==8}">订单价格0不需支付</c:when>
										</c:choose>
									</c:when>
								</c:choose>
							</label>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left;width: 100%">用户选择配送方式：<c:choose>
									<c:when test="${ orders.userShipType==1}">自提</c:when>
									<c:when test="${ orders.userShipType==2}">商家配送</c:when>
								</c:choose>
							</label>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left;width: 100%">收货信息 ：
								${orders.ul.addressName } &nbsp; &nbsp; ${orders.ul.consignee }
								&nbsp; &nbsp; ${orders.ul.mobile }&nbsp; &nbsp;
								${orders.ul.zipcode }</label>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left;width: 100%">买家留言 ：
								${orders.remark }</label>
						</div>
						<c:if test="${not empty orders.firstOrderPrice}">
						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left;width: 100%">首单立减 ：￥${orders.firstOrderPrice }元
							</label>
						</div></c:if>
					</div>
					<div class="form-body"
						style="margin-top: -20px;float:left; width:50%">
						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left;width: 100%">订单价格
								：￥${orders.orderAccount}元 </label>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left;width: 100%">促销折扣 ：￥${empty orders.promotionPrice?'0.00':orders.promotionPrice}元
							</label>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left;width: 100%">优惠券折扣 ：￥${empty orders.couponsPrice?'0.00':orders.couponsPrice}元
							</label>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left;width: 100%">运费 ：￥${empty orders.shipPrice?'0.00':orders.shipPrice}元
							</label>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3"
								style="text-align: left;width: 100%">支付流水号 ：${orders.serialid}
							</label>
						</div>
					</div>
					<div style="clear:both"></div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>商品信息
				</div>
			</div>
			<div class="portlet-body ">
				<!-- BEGIN FORM-->
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>商品</th>
							<th>单价(元)</th>
							<th>数量</th>
							<th>小计</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${orders.od }" var="ods">
							<tr class="">
								<td><img alt="" src="/uploads/${ods.prodUri}" width="50px">${ods.prodName }<c:if
										test="${not empty ods.prodSpecName }">[${ods.prodSpecName}]</c:if></td>
								<td style="vertical-align: middle;">￥${ods.prodPrice }</td>
								<td style="vertical-align: middle;">${ods.prodCount }</td>
								<td style="vertical-align: middle;">￥${ods.prodCount*ods.prodPrice }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div style="float: right">商品+优惠+运费小计:${orders.orderAccount }元</div>
				<br> <br>
				<!-- END FORM-->
			</div>
		</div>
		
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>订单记录
				</div>
			</div>
			<div class="portlet-body ">
				<!-- BEGIN FORM-->
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>状态</th>
							<th>时间</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${olList }" var="ods">
							<tr class="">
								<td>${ods.remark }</td>
								<td style="vertical-align: middle;">${fn:substring(ods.logTime,0,19) }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
<script
	src="${SHOPDOMAIN}/system/theme/assets/admin/layout/scripts/quick-sidebar.js"
	type="text/javascript"></script>