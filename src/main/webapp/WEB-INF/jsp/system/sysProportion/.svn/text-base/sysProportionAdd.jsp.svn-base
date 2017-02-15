<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			<c:choose>
				<c:when test="${empty company }">系统管理 </c:when>
				<c:when test="${not empty company }">系统设置</c:when>
			</c:choose>
			<small>系统设置</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>系统管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span>系统设置</span></li>
			</ul>
		</div>
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
					<i class="fa fa-reorder"></i>基本信息
				</div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form_config" method="post"
					action="save.html" enctype="multipart/form-data"
					novalidate="novalidate">
					<div class="form-body">
						<div class="alert alert-danger display-hide">
							<button data-close="alert" class="close"></button>
							页面填写信息出错了，请先检查再提交。
						</div>
						<div class="alert alert-success display-hide">
							<button data-close="alert" class="close"></button>
							验证通过，正在提交！
						</div>
						<input type="hidden" value="${SysProportion.id }" name="realid">
						<!-- <div class="form-group">
							<label class="control-label col-md-3">安卓版本名称 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_price"
									value="${SysProportion.androidVersion }" name="androidVersion"
									style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">安卓版本code： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text"
									class="form-control w_merchants_login w_require w_rbili"
									value="${SysProportion.androidCode }" name="androidCode"
									style="width:500px;">版本更新用这个
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3">支付宝partner：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${SysProportion.alipayPartner }" name="alipayPartner"
									style="width: 500px;">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3">支付宝key：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control  w_require"
									value="${SysProportion.alipayKey }" name="alipayKey"
									style="width: 500px;">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3">支付宝seller_email：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control  w_require"
									value="${SysProportion.alipaySellerEmail }"
									name="alipaySellerEmail" style="width: 500px;">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3">stmp地址： </label>
							<div class="col-md-4">
								<input type="text" class="form-control   "
									value="${SysProportion.emailHost }" name="emailHost"
									style="width: 500px;">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3">邮箱账户： </label>
							<div class="col-md-4">
								<input type="text" class="form-control   "
									value="${SysProportion.emailAttr }" name="emailAttr"
									style="width: 500px;">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3">邮箱密码： </label>
							<div class="col-md-4">
								<input type="text" class="form-control   "
									value="${SysProportion.emailPasswd }" name="emailPasswd"
									style="width: 500px;">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3">发件人昵称： </label>
							<div class="col-md-4">
								<input type="text" class="form-control   "
									value="${SysProportion.emailUser }" name="emailUser"
									style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">端口： </label>
							<div class="col-md-4">
								<input type="text" class="form-control   "
									value="${SysProportion.emailPort }" name="emailPort"
									style="width: 500px;">
							</div>
						</div> -->

						<div class="form-group">
							<label class="control-label col-md-3">多少小时自动确认收货：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_sort w_require "
									value="${SysProportion.autoAcceptTime }" name="autoAcceptTime"
									style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">多少小时自动确认终结：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_sort w_require "
									value="${SysProportion.autoOverTime }" name="autoOverTime"
									style="width: 500px;">
							</div>订单终结后，不允许退货，这时开始计算销售量
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">多少小时自动取消订单：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_sort w_require "
									value="${SysProportion.autoCancelTime }" name="autoCancelTime"
									style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">版权所有： </label>
							<div class="col-md-4">
								<input type="text" class="form-control   "
									value="${SysProportion.copyrights }" name="copyrights"
									style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">联系电话：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_rangelength w_require "
									value="${SysProportion.mobile }" name="mobile"
									style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">qq：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_isQq w_require "
									value="${SysProportion.qq }" name="qq"
									style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">关于我们：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<textarea name="aboutUs" id="aboutUs"
									class="form-control w_require"
									style="width: 700px; height: 480px;">${SysProportion.aboutUs }</textarea>
								<link rel="stylesheet"
									href="${SHOPDOMAIN}/js/kindeditor/themes/default/default.css" />
								<link rel="stylesheet"
									href="${SHOPDOMAIN}/js/kindeditor/plugins/code/prettify.css" />
								<script charset="utf-8"
									src="${SHOPDOMAIN}/js/kindeditor/kindeditor.js"></script>
								<script charset="utf-8"
									src="${SHOPDOMAIN}/js/kindeditor/lang/zh_CN.js"></script>
								<script charset="utf-8"
									src="${SHOPDOMAIN}/js/kindeditor/plugins/code/prettify.js"></script>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">评分设置：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<table>
									<tr>
										<td></td>
										<td>差评</td>
										<td></td>
										<td>中评</td>
										<td></td>
										<td>好评</td>
										<td></td>
									</tr>
									<tr>
										<td>0</td>
										<td>&nbsp; <</td>
										<td><input type="text"
											class="form-control w_rbili w_require "
											value="${SysProportion.middlePraiseScope }"
											name="middlePraiseScope" style="width: 40px;"></td>
										<td>&nbsp; <</td>
										<td><input type="text"
											class="form-control w_rbili w_require "
											value="${SysProportion.highPraiseScope }"
											name="highPraiseScope" style="width: 40px;"></td>
										<td>&nbsp; <</td>
										<td>5</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">首单立减金额：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_double w_require "
									value="${SysProportion.firstSubtractPrice }" name="firstSubtractPrice"
									style="width: 500px;">（元）
							</div>
						</div>
					</div>
					<div class="form-actions fluid" style="margin-top: 40px;">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green ssubmit" type="button">提交</button>
							<button class="btn grey-cascade sys_go_back" type="button" >返回</button>
						</div>
					</div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
	jQuery(document).ready(function() {
		FormValidation.init();
	});
	var editor1 = "";
	jQuery(document)
			.ready(
					function() {
						FormValidation.init();
						KindEditor
								.ready(function(K) {
									editor1 = K
											.create(
													'textarea[id="aboutUs"]',
													{
														cssPath : '${SHOPDOMAIN}/js/kindeditor/plugins/code/prettify.css',
														uploadJson : '${SHOPDOMAIN}/kindeditor/uploadJson.html?companyId=${sessionUser.companyId }',
														fileManagerJson : '${SHOPDOMAIN}/kindeditor/fileManagerJson.html',
														allowFileManager : true,
														afterCreate : function() {
															var self = this;
															K
																	.ctrl(
																			document,
																			13,
																			function() {
																				self
																						.sync();
																			});
															K
																	.ctrl(
																			self.edit.doc,
																			13,
																			function() {
																				self
																						.sync();
																			});
														}
													});
									prettyPrint();
								});
					});
	$(".ssubmit").click(function() {
		editor1.sync();
		$("#form_config").submit();
	})
</script>