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
			帮助分类 <small>帮助文章分类列表</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>分类管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span>分类列表</span> <c:if test="${not empty objs.id }">
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
							onclick="javascript:window.location.href='add.html?typeid=${objs.id}'"
							type="button">添加帮助文章分类</button>
					</div>
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>分类名称</th>
									<th>录入时间</th>
									<th>是否首页展示</th>
									<th>排序</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty objList}">
										<c:forEach items="${objList}" var="product" varStatus="vs">
											<tr class="">
												<td>${product.name}&nbsp;<%-- <a id="${product.id }"
													type="1" class="btn default btn-xs green listContact_up"
													href="javascript:void(0)">收起 <i
														class="fa fa-angle-double-up "></i>
												</a> --%></td>
												<td><fmt:formatDate value="${product.addtime}"
														type="both" pattern="yyyy/MM/dd HH:mm:ss" /></td>
												<c:if test="${product.isWep=='0'}"><td>是</td></c:if>
												<c:if test="${product.isWep=='1'}"><td>否</td></c:if>
												<td>${product.sortn}</td>
												<td><a class="btn default btn-xs purple"
													href="javascript:void(0)"
													onclick="editProduct(${product.id})"> <i
														class="fa fa-edit"></i> 编辑
												</a> &nbsp; &nbsp;<a id="${product.id }"
													class="btn default btn-xs black listContact_removeObjectSignle"
													title="分类" href="javascript:void(0)"> <i
														class="fa fa-trash-o"></i> 删除
												</a></td>
											</tr>
											<%--二级分类 --%>
											<%-- <c:forEach items="${product.sublist}" var="sub">
												<tr class="p_${product.id }">
													<td>&nbsp;&nbsp;&nbsp;&nbsp;|_____${sub.name}</td>
													<td><fmt:formatDate value="${sub.addtime}" type="both"
															pattern="yyyy/MM/dd HH:mm:ss" /></td>
													<td>${sub.sortn}</td>
													<td><a class="btn default btn-xs purple"
														href="javascript:void(0)" onclick="editProduct(${sub.id})">
															<i class="fa fa-edit"></i> 编辑
													</a> &nbsp; &nbsp;<a id="${sub.id }"
														class="btn default btn-xs black listContact_removeObjectSignle"
														title="分类" href="javascript:void(0)"> <i
															class="fa fa-trash-o"></i> 删除
													</a></td>
												</tr>
											</c:forEach> --%>
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
	function editProduct(id) {
		window.location.href = "update.html?id=" + id;
	}
</script>
<!-- END PAGE CONTENT-->
<script src="${SHOPDOMAIN}/system/js/listContact.js"></script>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>