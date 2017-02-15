<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<style>
.input-group .float_n {
	float: none;
	display: inline;
}
</style>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			用户管理 <small>注册用户列表</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>用户管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span>注册列表</span> <c:if test="${not empty param.pid }">
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
<form action="userList.html" method="post" id="list">
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

					<div class="table-responsive">
						<div class="input-group"
							style="text-align: left; margin-bottom: 3px;">
							<%-- <select name="orderbyss" class="form-control "
								style="width: 200px;float: right; margin-right: 10px">
								<option value="0">默认</option>
								<option value="5"
									<c:if test="${orderbyss==5 }"> selected="selected" </c:if>>多多豆数量
									大到小</option>
							</select>&nbsp; <span style="float:right;height:35px;line-height:35px">排序:&nbsp;</span> --%>
							<input type="text" name="name" class="form-control "
								style="width: 120px;float: right; margin-right: 10px"
								value="${user.name}"/>&nbsp; <span
								style="float:right;height:35px;line-height:35px">昵称:&nbsp;</span>
							<span class="input-group-btn" id="search"> <a
								class="btn green"> <i class="fa fa-search"></i> 搜索
							</a>
							</span>
						</div>
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th >#</th>
									<th >昵称</th>
									<th >头像</th>
									<th>性别</th>
									<th>用户名</th>
									<th>联系电话</th>
									<th>ip</th>
									<th>注册来源</th>
									<th>是否可用</th>
									<th style="width:25%" >操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty objList}">
										<c:forEach items="${objList}" var="product" varStatus="vs">
											<tr class="">
												<td>${vs.index +1}</td>
												<td>${product.name}</td>
												<td><img src="${USERIMGSRC }${product.pic_url}"
												width="120px" /></td>
												<td>${product.sex==0?"男":"女"}</td>
												<td>${product.login}</td>
												<td>${product.mobile}</td>
												<td>${product.ip}</td>
												<td>${empty product.company_name?"无":product.company_name}</td>
												<c:if test="${product.is_del==1 }">
													<td>可用</td>
												</c:if>
												<c:if test="${product.is_del==0 }">
													<td>禁用</td>
												</c:if>
												<td style="width: 20%"><a
													class="btn default btn-xs green listContact_ResetPasswd"
													style="margin-top:5px;" href="javascript:void(0)"
													id="${product.id}"> <i class="fa fa-cogs"></i> 重置密码
												</a><a id="${product.id }" style="margin-top:5px;"
													class="btn default btn-xs black listContact_removeObjectSignle"
													title="${product.name}" href="javascript:void(0)">
														<i class="fa fa-trash-o"></i> 删除
												</a>
												<a id="${product.id }" style="margin-top:5px;"
													types="${product.is_del}"
													class="btn default btn-xs purple listContact_setdisable"
													href="javascript:void(0)"> <i
														class="fa ${product.is_del==1?"fa-lock":"fa-unlock" }"></i>${product.is_del==1?"禁用":"启用" }
												</a>
												</td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr class="main_info">
											<td colspan="10">没有相关数据</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
						<div class="page_and_btn">
							<div></div>
							${user.page.pageStr }
						</div>
					</div>
				</div>
			</div>
			<!-- END SAMPLE TABLE PORTLET-->
		</div>
	</div>
</form>
<script>
	function selectAlbum(id) {
		window.location.href = "${SHOPDOMAIN}/system/archiveUsersAlbum/archiveUsersAlbumlist.html?UserId="
				+ id;
	}
	function selectSteetSnap(id) {

		window.location.href = "${SHOPDOMAIN}/system/streetBeatUpload/streetBeatUsIdList.html?UserId="
				+ id;
	}

	function update(id) {
		window.location.href = "${SHOPDOMAIN}/system/archiveUsers/listArchiveUser.html?userId="
				+ id;
	}
	function watchsub(id) {
		window.location.href = "list.html?pid=" + id
				+ "&typeid=${param.typeid}";
	}
	//设置用户为jinyon
	$("#search").click(function() {

		$("#list").submit();
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

	// 禁用
	$(".listContact_setdisable")
			.click(
					function() {
						var delId = $(this).attr("id");
						var types = $(this).attr("types");
						var res = $(this).attr("types") == 1 ? "禁用" : "启用";
						bootbox
								.dialog({
									message : "确定要" + res + "该注册用户吗？",
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

</script>
<!-- END PAGE CONTENT-->
<script src="${SHOPDOMAIN}/system/js/listContact.js"></script>
<jsp:include page="../foot.jsp"></jsp:include>