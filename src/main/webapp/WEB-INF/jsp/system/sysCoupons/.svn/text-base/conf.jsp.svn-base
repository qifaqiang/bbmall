<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			优惠券管理 <small>分享赢优惠券配置 </small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					优惠券管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 信息 </a></li>
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
					<i class="fa fa-reorder"></i>信息
				</div>
				<div class="tools"></div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form_config" method="post"
					enctype="multipart/form-data" action="saveconf.html"
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
						<div class="form-group">
							<label class="control-label col-md-3">最低金额 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require w_price"
									value="${fn:split(obj.priceRange," -")[0] }" name="priceRangel"><input
									type="hidden" value="1" name="id">
							</div>(元)
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">最高金额 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require w_price"
									value="${fn:split(obj.priceRange," -")[1] }" name="priceRangeh">
							</div>(元)
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">有效天数 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text"
									class="form-control w_require w_positive_number"
									value="${obj.timeRange }" name="timeRange">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">每次总数量 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text"
									class="form-control w_require w_positive_number"
									value="${obj.count }" name="count">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">图片： </label>
							<div class="col-md-4">
								<div data-provides="fileinput" class="fileinput fileinput-new">
									<div class="input-group input-large">
										<div data-trigger="fileinput"
											class="form-control uneditable-input span3">
											<i class="fa fa-file fileinput-exists"></i>&nbsp; <span
												class="fileinput-filename"></span>
										</div>
										<span class="input-group-addon btn default btn-file"> <span
											class="fileinput-new"> 选择文件 </span> <span
											class="fileinput-exists"> 更改 </span> <input type="hidden"
											value="" name="..."><input type="file" name="myfile">
										</span> <a data-dismiss="fileinput"
											class="input-group-addon btn red fileinput-exists" href="#">
											删除 </a>
									</div>图片建议尺寸为640*700

									<c:if test="${not empty obj.picUrl }">
										<br>
										<img src="${USERIMGSRC }${obj.picUrl}" height="100px"
											width="100px" />
									</c:if>
								</div>
								
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">规则 ： </label>
							<div class="col-md-4">
								<textarea name="content" id="descn"
									style="width:700px;height:480px;">${obj.content }</textarea>
								<link rel="stylesheet"
									href="${SHOPDOMAIN}/js/kindeditor/themes/default/default.css" />
								<link rel="stylesheet"
									href="${SHOPDOMAIN}/js/kindeditor/plugins/code/prettify.css" />
								<script charset="utf-8"
									src="${SHOPDOMAIN }/js/kindeditor/kindeditor.js"></script>
								<script charset="utf-8"
									src="${SHOPDOMAIN }/js/kindeditor/lang/zh_CN.js"></script>
								<script charset="utf-8"
									src="${SHOPDOMAIN}/js/kindeditor/plugins/code/prettify.js"></script>
							</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green rollesubmit" type="submit">提交</button>
							<button class="btn grey-cascade sys_go_back" type="button">返回</button>
						</div>
					</div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
<script>
	jQuery(document)
			.ready(
					function() {
						FormValidation.init();
						var editor1 = "";
						KindEditor
								.ready(function(K) {
									editor1 = K
											.create(
													'textarea[id="descn"]',
													{
														cssPath : '${SHOPDOMAIN }/js/kindeditor/plugins/code/prettify.css',
														uploadJson : '${SHOPDOMAIN }/kindeditor/uploadJson.html?companyId=${sessionUser.companyId }',
														fileManagerJson : '${SHOPDOMAIN }/kindeditor/fileManagerJson.html',
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
																				document.forms['form1']
																						.submit();
																			});
															K
																	.ctrl(
																			self.edit.doc,
																			13,
																			function() {
																				self
																						.sync();
																				document.forms['form1']
																						.submit();
																			});
														}
													});
									prettyPrint();
								});
						$(".rollesubmit").live(
								"click",
								function(e) {
									editor1.sync();
									var lows = parseFloat($(
											"[name='priceRangel']").val());
									var highs = parseFloat($(
											"[name='priceRangeh']").val());
									if (lows >= highs) {
										showMessage("最高金额要大于最低金额！")
										return false;
									}
								});
					});
</script>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />
