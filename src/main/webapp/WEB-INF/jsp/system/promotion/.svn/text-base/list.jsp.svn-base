<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			促销列表<small> 列表</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>促销管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span>促销列表</span>
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
							type="button">添加促销</button>
					</div>
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>名称</th>
									<th>使用时间范围</th>
									<th>满额</th>
									<th>折扣方式</th>
									<th>是否全场促销</th>
									<!-- <th>是否推荐</th> -->
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty objList}">
										<c:forEach items="${objList}" var="product" varStatus="vs">
											<tr class="">
												<td>${vs.index +1}</td>
												<td>${product.name}</td>
												<td><fmt:formatDate value="${product.startTime}"
														type="both" pattern="yyyy-MM-dd" /> ~ <fmt:formatDate
														value="${product.endTime}" type="both"
														pattern="yyyy-MM-dd" /></td>
												<td>${product.needPrice}</td>
												<td><c:if test="${product.substractPrice==null||product.substractPrice==0 }">打${product.discount}折</c:if><c:if test="${product.discount==null||product.discount==0 }">减${product.substractPrice}元</c:if></td>
												<td><c:if test="${product.allProduct==0 }">否</c:if><c:if test="${product.allProduct==1 }">是</c:if></td>
												<!--<td><c:if test="${product.istop==1 }">已经推荐 </c:if><c:if test="${product.istop==0 }">未推荐 </c:if></a></td>-->
												<td><a class="btn default btn-xs purple"
													href="javascript:void(0)"
													onclick="editProduct(${product.id})"> <i
														class="fa fa-edit"></i> 编辑
												</a> &nbsp; &nbsp;<a id="${product.id }"
													class="btn default btn-xs black listContact_removeObjectSignle"
													title="促销"
													 href="javascript:void(0)"> <i
														class="fa fa-trash-o"></i> 删除
												</a> &nbsp; &nbsp;
												<!--<a class="btn default btn-xs green "  href="javascript:void(0)" onclick="updateIsTop(${product.id},${product.istop})"> <i class="fa fa-edit"></i> 
												<c:if test="${product.istop==1 }">取消推荐 </c:if><c:if test="${product.istop==0 }">推荐 </c:if></a>
												 --></td>
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
							${obj.page.pageStr }
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
	function deleteProduct(id) {
		window.location.href = "delone.html?id=" + id;
	}
	function receivelist(id) {
		window.location.href = "receivelist.html?couponsId=" + id;
	}
	function receivelist() {
		window.location.href = window.location.href;
	}
	//设置推荐
	function updateIsTop(id,type){
	    var res=type==1?"推荐":"取消推荐";
		  bootbox
			.dialog({
				message : "确定要" + res + "该商品吗？",
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
							$.ajax({
										url : "${SHOPDOMAIN}/system/promotion/setIsTop.html",
										type : "post",
										data : {
											"id" : id,
											"istop" : type
										},
										success : function(
												data) {
											 
											if (eval("("
													+ data
													+ ")").flag) {
												//var currentPage=  $(".pagination").children("li").find("span").text();
												receivelist();
											} else {
												alert("error");
											}
										}
									});
						}
					}
				}
			});
	}
</script>
<!-- END PAGE CONTENT-->
<script src="${SHOPDOMAIN}/system/js/listContact.js"></script>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>