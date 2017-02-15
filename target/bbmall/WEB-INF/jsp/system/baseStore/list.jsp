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
			基地商户管理 <small>系统基地商户列表</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>基地商户管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span>系统列表</span> <c:if test="${not empty param.pid }">
						<i class="fa fa-angle-right"></i>
						<a onclick="javascript :history.back(-1)">返回上一级</a>
					</c:if></li>
			</ul>
		</div>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<form action="list.html" method="post" id="list">
	<div class="row">
		<div class="col-md-6" style="width: 100%">
			<!-- BEGIN SAMPLE TABLE PORTLET-->
			<div class="portlet box blue">
				<div class="portlet-title">
					<div class="caption">
						<i class=" fa fa-cogs"></i>信息
					</div>
				</div>
				<div class="portlet-body">
					<div class="input-group"
						style="text-align: left; margin-bottom: 3px;">
						<button class="btn btn-primary"
							onclick="javascript:window.location.href='add.html'"
							type="button">添加基地商户</button>
						<input type="text" class="form-control " value="${ company.login}"
							style="width: 180px; float: right;" name="login" placeholder=""
							id="mobile"> <span
							style="float:right;height:35px;line-height:35px">登录名:&nbsp;</span>
						<span class="input-group-btn"> <a href="javascript:;"
							class="btn green" id="search"> <i class="fa fa-search"></i>搜索
						</a>
						</span>
					</div>
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>基地商户名称</th>
									<th>登录名</th>
									<th>联系人</th>
									<th>手机</th>
									<th>地址</th>
									<th>是否禁用</th>
									<th style="width:35%">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty objList}">
										<c:forEach items="${objList}" var="product" varStatus="vs">

											<tr class="">
												<td>${vs.index +1}</td>
												<td>${product.companyName}</td>
												<td>${product.login }</td>
												<td>${product.companyContact}</td>
												<td>${product.mobile}</td>
												<c:if test="${empty product.userRole }">
													<td>未设置角色</td>
												</c:if>
												<c:if test="${not empty product.userRole }">
													<td>${product.userRole.name}</td>
												</c:if>
												<c:if test="${product.isdel==1 }">
													<td>可用</td>
												</c:if>
												<c:if test="${product.isdel==0 }">
													<td>禁用</td>
												</c:if>
												<td style="width: 25%"> <a style="margin-top:5px;"
													onclick="editCompany(${product.companyId})" href="javascript:void(0)"
													class="btn default btn-xs purple"> <i
														class="fa fa-edit"></i> 编辑
												</a> <a style="margin-top:5px;"
													class="btn default btn-xs green listContact_ResetPasswd"
													href="javascript:void(0)" id="${product.companyId}"> <i
														class="fa fa-cogs"></i> 重置密码
												</a> <a id="${product.companyId }" style="margin-top:5px;"
													class="btn default btn-xs black listContact_removeObjectSignle"
													title="${product.login}" href="javascript:void(0)">
														<i class="fa fa-trash-o"></i> 删除
												</a> <a id="${product.companyId }" style="margin-top:5px;"
													types="${product.isdel}"
													class="btn default btn-xs purple listContact_setdisable"
													href="javascript:void(0)"> <i
														class="fa ${product.isdel==1?"fa-lock":"fa-unlock" }"></i>${product.isdel==1?"禁用":"启用" }
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
							${company.page.pageStr }
						</div>
					</div>
				</div>
			</div>
			<!-- END SAMPLE TABLE PORTLET-->
		</div>
	</div>
</form>
<script>
	//设置基地商户为jinyon
	$("#search").click(function() {
		$("#list").submit();
	});
	
	function editCompany(id) {
		window.location.href = "update.html?userId=" + id;
	}
	
	// 禁用
	$(".listContact_setdisable")
			.click(
					function() {
						var delId = $(this).attr("id");
						var types = $(this).attr("types");
						var res = $(this).attr("types") == 1 ? "禁用" : "启用";
						bootbox
								.dialog({
									message : "确定要" + res + "该系统基地商户吗？",
									title : "提示",
									buttons : {
										success : {
											label : "取消!",
											className : "white",
											callback : function() {
											}
										},
										main : {
											label : "确定!",
											className : "blue",
											callback : function() {
												$
														.ajax({
															url : "setDisable.html",
															type : "post",
															data : {
																"id" : delId,
																"types" : types
															},
															success : function(
																	data) {
																if (eval("("
																		+ data
																		+ ")").flag) {
																	window.location.href = window.location.href;
																} else {
																	alert("error");
																}
															}
														});
											}
										}
									}
								});
					});

	// 弹窗——批量重置密码联系人
	$(".listContact_ResetPasswd").click(function() {
		var delId = $(this).attr("id");
		bootbox.dialog({
			message : "确定要重置该账户密码为123456吗？",
			title : "提示",
			buttons : {
				success : {
					label : "取消!",
					className : "white",
					callback : function() {
					}
				},
				main : {
					label : "确定!",
					className : "blue",
					callback : function() {
						var DATA = '';
						DATA = '{"CTIDS":"' + delId + '","EXCLUSION":"false"}';
						$.ajax({
							url : "resetpasswd.html",
							type : "post",
							data : {
								"DATA" : DATA
							},
							success : function(data) {
								if (eval("(" + data + ")").flag) {
									bootbox.dialog({
										message : '成功重置了密码',
										title : "系统提示",
										buttons : {
											main : {
												label : "确定!",
												className : "blue",
												callback : function() {
												}
											}
										}
									});
								}
							}
						});
					}
				}
			}
		});
	});
</script>
<!-- END PAGE CONTENT-->
<script src="${SHOPDOMAIN}/system/js/listContact.js"></script>
<jsp:include page="../foot.jsp"></jsp:include>