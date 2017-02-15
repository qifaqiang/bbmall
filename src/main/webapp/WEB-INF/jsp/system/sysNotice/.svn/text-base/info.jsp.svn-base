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
				<c:when test="${empty obj }">新增系统公告 </c:when>
				<c:when test="${not empty obj }">系统公告修改</c:when>
			</c:choose>
			<small>系统设置</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>系统管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span>系统公告设置</span></li>
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
						<input type="hidden" value="${obj.id }" name="id">
						<div class="form-group">
							<label class="control-label col-md-3">标题 ：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${obj.title}" name="title" style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">作者：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${obj.author}" name="author" style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">来源 ：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${obj.source}" name="source" style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">内容： </label>
							<div class="col-md-4">
								<textarea name="content" id="shopHead"
									style="width: 700px; height: 480px;">${obj.content }</textarea>
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
					</div>
					<div class="form-actions fluid" style="margin-top: 40px;">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green ssubmit" type="button">提交</button>
							<button class="btn gray" type="button"
								onclick="javascript:history.go(-1)">返回</button>
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
<!-- BEGIN PAGE LEVEL SCRIPTS -->
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
													'textarea[id="shopHead"]',
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