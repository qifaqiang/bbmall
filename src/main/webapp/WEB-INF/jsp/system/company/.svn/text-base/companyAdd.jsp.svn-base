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
				<c:when test="${empty company }">添加系统用户 </c:when>
				<c:when test="${not empty company }">修改系统 </c:when>
			</c:choose>
			<small>构建系统用户</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>用户管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span>系统用户信息</span></li>
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
					action="save.html" novalidate="novalidate">
					<div class="form-body">
						<div class="alert alert-danger display-hide">
							<button data-close="alert" class="close"></button>
							页面填写信息出错了，请先检查再提交。
						</div>
						<div class="alert alert-success display-hide">
							<button data-close="alert" class="close"></button>
							验证通过，正在提交！
						</div>
						<input type="hidden" value="${sc.id }" name="id">
						<div class="form-group">
							<label class="control-label col-md-3">商户名称 ：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${company.companyName }" name="companyName"
									style="width:500px;"> <input type="hidden"
									value="${company.companyId }" name="companyId">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">联系人 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${company.companyContact }" name="companyContact"
									style="width:500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">登录用户名 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text"
									class="form-control w_merchants_login w_require"
									<c:if test="${!empty company.login }"> readonly="readonly"</c:if>
									value="${company.login }" name="login" style="width:500px;">
								<input type="hidden" value="${company.login }" id="loginBack">保存后不可修改，初始密码123456。
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">所属角色 <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">

								<select name="roleId" class="form-control">

									<c:forEach items="${sclist }" var="scc">
										<c:if test="${scc.id!=40 }">
											<option value="${scc.id }"
												<c:if test="${company.userRole.id==scc.id }"> selected="selected" </c:if>>${scc.name }</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">手机号 ： </label>
							<div class="col-md-4">
								<input type="text" class="form-control w_mobile"
									value="${company.mobile }" name="mobile" style="width:500px;">
							</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green ssubmit" type="sussbmit">提交</button>
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
<script>
	jQuery(document).ready(function() {
		FormValidation.init();
	});
</script>