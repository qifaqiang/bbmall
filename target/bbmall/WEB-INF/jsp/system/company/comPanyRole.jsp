<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/jstree/dist/themes/default/style.min.css" />
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-toastr/toastr.min.css" />
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			 设置角色 
			<small>设置角色</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>用户管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span>设置角色</span></li>
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
					action="saveCompanyRole.html" novalidate="novalidate">
					<div class="form-body">
						<div class="alert alert-danger display-hide">
							<button data-close="alert" class="close"></button>
							页面填写信息出错了，请先检查再提交。
						</div>
						<div class="alert alert-success display-hide">
							<button data-close="alert" class="close"></button>
							验证通过，正在提交！
						</div>
						<input type="hidden" value="${company.companyId}" name="companyId">
						<div class="form-group">
							<label class="control-label col-md-3">用户名称 <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									readonly="readonly" value="${company.login }" name="name"
									style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">所属角色 <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">

								<select name="roleId" class="form-control">
									 
									<c:forEach items="${sclist }" var="scc">
										<option value="${scc.id }"
											<c:if test="${company.userRole.id==scc.id }"> selected="selected" </c:if>>${scc.name }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						 
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green ssubmit" type="submit">提交</button>
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
<script src="${SHOPDOMAIN}/system/js/ui-tree.js"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jstree/dist/jstree.min.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery-validation/js/jquery.validate.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery-validation/js/additional-methods.min.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-markdown/lib/markdown.js"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/admin/pages/scripts/form-validation.js"></script>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script src="${SHOPDOMAIN}/system/js/jquery.form.js"></script>
<script>
	jQuery(document).ready(function() {
		FormValidation.init();
	});
</script>