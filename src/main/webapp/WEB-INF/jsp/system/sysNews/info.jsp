<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			帮助文章设置<small>信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					帮助文章设置 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 文章信息</a></li>
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
					enctype="multipart/form-data" action="save.html"
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
							<label class="control-label col-md-3">文章标题 ： <span class="required"> * </span> </label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${obj.title }" name="title" > <input type="hidden"
									value="${obj.id }" name="id">
							</div>
						</div>
						<div class="form-group">
						<label class="control-label col-md-3">所属分类 ：<span class="required"> * </span> </label>
							<div class="col-md-4">
								<select class="form-control w_require" name="catalogId">
									<c:forEach items="${list}" var="li">
											<option value="${li.id }" 
											<c:if test="${obj.catalogId==li.id }"> selected="selected"</c:if>>${li.name}</option>
											<%-- <c:forEach items="${li.sublist}" var="sub">
								        	<option value="${sub.id }"
											<c:if test="${obj.catalogId==sub.id }"> selected="selected"</c:if>>┕-${sub.name}</option>
										</c:forEach> --%>
										</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">内容：
							</label>
							<div class="col-md-4">
								<textarea name="content" id="content"
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
						<div class="form-group">
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green ssubmit" type="button" >提交</button>
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
											'textarea[id="content"]',
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
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />
