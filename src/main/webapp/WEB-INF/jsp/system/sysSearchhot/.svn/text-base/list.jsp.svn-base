<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			系统设置 <small>热点搜索管理</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>系统管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span>热点搜索管理</span> <c:if test="${not empty param.pid }">
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
<form action="list.html?date=<%=System.currentTimeMillis()%>"
	method="post" id="list">
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
							type="button">添加热点搜索</button>
						<input type="text" class="form-control "
							style="width: 100px; float: right; margin-right: 10px"
							name="name" placeholder="" id="name" value="${objs.name}">

						<span style="float:right;line-height:35px;height:35px;">名称:&nbsp;</span>
						<span class="input-group-btn" id="searchlll"> <a
							class="btn green"> <i class="fa fa-search"></i>搜索
						</a>
						</span>
					</div>
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>名称</th>
									<th>排序</th>
									<th>建立时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty objList}">
										<c:forEach items="${objList}" var="product" varStatus="vs">

											<tr class="">
												<td>${vs.index +1}</td>
												<td>${product.name }</td>
												<td>${product.sortn }</td>
												<td><fmt:formatDate value="${product.addtime}"
														type="both" pattern="yyyy/MM/dd HH:mm:ss" /></td>
												<td><a class="btn default btn-xs purple"
													href="javascript:void(0)" onclick="update(${product.id})">
														<i class="fa fa-edit"></i> 编辑
												</a> &nbsp; &nbsp; <a id="${product.id }"
													class="btn default btn-xs black listContact_removeObjectSignle"
													title="" href="javascript:void(0)"> <i
														class="fa fa-trash-o"></i> 删除
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
							${objs.page.pageStr }
						</div>
					</div>
				</div>
			</div>
			<!-- END SAMPLE TABLE PORTLET-->
		</div>
	</div>
</form>
<script>
	function update(id) {
		window.location.href = "update.html?id=" + id;
	}

	$("#searchlll").click(function() {

		$("#list").submit();

	});
</script>
<!-- END PAGE CONTENT-->
<script src="${SHOPDOMAIN}/system/js/listContact.js"></script>
<jsp:include page="../foot.jsp"></jsp:include>