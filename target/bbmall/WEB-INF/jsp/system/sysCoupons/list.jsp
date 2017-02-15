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
			优惠券列表<small>列表</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>优惠券管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span>优惠券列表</span>
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
							type="button">添加优惠券</button>
					</div>
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>名称</th>
									<th>备注</th>
									<th>使用时间范围</th>
									<th>满额</th>
									<th>扣减</th>
									<th>总共数量</th>
									<th>可用数量</th>
									<th>类型</th>
									<th style="width:30%">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty objList}">
										<c:forEach items="${objList}" var="product" varStatus="vs">

											<tr class="">
												<td>${vs.index +1}</td>
												<td>${product.name}</td>
												<td>${product.remark }</td>
												<td><c:if test="${product.type==1 }">
														<fmt:formatDate value="${product.startTime}" type="both"
															pattern="yyyy-MM-dd" /> ~ <fmt:formatDate
															value="${product.endTime}" type="both"
															pattern="yyyy-MM-dd" />
													</c:if> <c:if test="${product.type==0 }">
													获取后${product.validTime }天内可用
												</c:if></td>
												<td>${product.needPrice }</td>
												<td>${product.substractPrice }</td>
												<td>${product.allCount }</td>
												<td>${product.leftCount }</td>
												<td>${product.type==1?"公开领取":"注册赠送" }</td>
												<td style="width: 25%"><a
													class="btn default btn-xs green" href="javascript:void(0)"
													onclick="receivelist(${product.id})"> <i
														class="fa fa-file-o"></i> 查看领取状态
												</a> <a class="btn default btn-xs purple"
													href="javascript:void(0)"
													onclick="editProduct(${product.id})"> <i
														class="fa fa-edit"></i> 编辑
												</a> <a id="${product.id }"
													class="btn default btn-xs black listContact_removeObjectSignle"
													title="${product.name}" href="javascript:void(0)"> <i
														class="fa fa-trash-o"></i> 删除
												</a></td>
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
	function receivelist(id) {
		window.location.href = "receivelist.html?couponsId=" + id;
	}
</script>
<!-- END PAGE CONTENT-->
<script src="${SHOPDOMAIN}/system/js/listContact.js"></script>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>