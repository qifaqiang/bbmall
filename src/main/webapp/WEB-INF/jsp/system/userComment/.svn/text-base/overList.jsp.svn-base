<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<style>
.table tbody tr td {
	vertical-align: middle
}
</style>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			用户评价列表<small>评价列表</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>评价管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span> 已审核列表</span></li>
			</ul>
		</div>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<form action="overList.html" method="post" id="overList">
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
						<input type="text" class="form-control w_mobile"
							value="${ objs.content}" style="width: 180px; float: right;"
							name="content" placeholder="" id="content"> <span
							style="float:right;height:35px;line-height:35px">评价内容:&nbsp;</span>
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
									<th>用户名</th>
									<th>内容</th>
									<th>商品</th>
									<th>星星数量</th>
									<th style="min-width:270px;">图片</th>
									<th>评价时间</th>
									<th>审核状态</th>
									<th>审核时间</th>
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
												<td style="width: 25%" title="${product.content}"><c:if
														test="${fn:length(product.content)>100}">
												${fn:substring(product.content,0,100)}...
												</c:if> <c:if test="${fn:length(product.content)<100}">
												${product.content}
												</c:if></td>
												<td>${product.productName}</td>
												<td><span style="color:#fea321;font-size: 15pt"><c:choose>
															<c:when test="${product.starCount==5}">
															★★★★★
														</c:when>
															<c:when test="${product.starCount==4}">
															★★★★
														</c:when>
															<c:when test="${product.starCount==3}">
															★★★
														</c:when>
															<c:when test="${product.starCount==2}">
															★★
														</c:when>
															<c:when test="${product.starCount==1}">
															★
														</c:when>
														</c:choose></span></td>
												<td><c:if test="${not empty product.picurl }">
														<c:forEach items="${fn:split(product.picurl,';')}"
															var="obj">
															<a href="${USERIMGSRC }${obj}" target="_blank"><img
																src="${USERIMGSRC }${fn:replace(obj,'.','_200.')}" width="120px" /></a>
														</c:forEach>
													</c:if></td>
												<td><fmt:formatDate value="${product.addtime}"
														type="both" pattern="yyyy/MM/dd HH:mm:ss" /></td>
												<c:if test="${product.vState==1 }">
													<td>审核通过</td>
												</c:if>
												<c:if test="${product.vState==2 }">
													<td>审核不通过</td>
												</c:if>
												<c:if test="${product.vState==3 }">
													<td>未审核</td>
												</c:if>
												<td><fmt:formatDate value="${product.vTime}"
														type="both" pattern="yyyy/MM/dd HH:mm:ss" /></td>
												<td style="text-align:center;"><a id="${product.id }"
													class="btn default btn-xs black listContact_removeObjectSignle"
													title="评论" href="javascript:void(0)"> <i
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
							${objs.page.pageStr}
						</div>
					</div>
				</div>
			</div>
			<!-- END SAMPLE TABLE PORTLET-->
		</div>
	</div>
</form>
<script>
	//设置系统用户为jinyon
	$("#search").click(function() {
		$("#overList").submit();
	});
</script>
<!-- END PAGE CONTENT-->
<script src="${SHOPDOMAIN}/system/js/listContact.js"></script>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>