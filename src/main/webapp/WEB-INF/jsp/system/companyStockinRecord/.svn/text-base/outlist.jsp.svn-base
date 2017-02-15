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
			库存记录列表 <small>基地库存记录出货表</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>出货记录列表</span> <i
					class="fa fa-angle-right"></i></li>
			</ul>
		</div>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<form action="list.html" method="post">
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
				<button class="btn btn-primary" onclick="javascript:window.location.href='add.html'" type="button">添加分类</button>
				<br><br>
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>商品名称</th>
									<th>数量</th>
									<th>基地名称</th>
									<th>操作人姓名</th>
									<th>操作时间</th>
									
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty objList}">
										<c:forEach items="${objList}" var="product" varStatus="vs">

											<tr class="">
												<td>${vs.index +1}</td>
												<td>${product.name}</td>
												<td>${product.sortn}</td>
												<td><a class="btn default btn-xs purple"
													href="javascript:void(0)" onclick="update(${product.id})">
														<i class="fa fa-edit"></i> 编辑
												</a> &nbsp; &nbsp; <c:if test="${empty param.pid }">
														<a class="btn default btn-xs green"
															href="javascript:void(0)"
															onclick="watchsub(${product.id})"> <i
															class="fa fa-search"></i> 下级分类
														</a> &nbsp; &nbsp;</c:if> <a id="${product.id }"
													class="btn default btn-xs black listContact_removeObjectSignle"
													title="分类" href="javascript:void(0)"> <i
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
							${sc.page.pageStr }
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
		window.location.href = "update.html?id=" + id
				+ "&typeid=${param.typeid}";
	}
	function watchsub(id) {
		window.location.href = "list.html?pid=" + id
				+ "&typeid=${param.typeid}";
	}
</script>
<!-- END PAGE CONTENT-->
<script src="${SHOPDOMAIN}/system/js/listContact.js"></script>
<jsp:include page="../foot.jsp"></jsp:include>