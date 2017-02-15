<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>
<jsp:include page="../../top.jsp"></jsp:include>
<!-- <script src="${SHOPDOMAIN}/front/js/ZeroClipboard.js"></script> -->
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			广告管理 <small>广告管理</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>广告管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span>广告管理</span> <c:if test="${not empty param.pid }">
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
							onclick="javascript:window.location.href='add.html'" type="button">添加广告</button>
					</div>
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>标题</th>
									<th>录入时间</th>
									<th>图片</th>
									<th>地址</th>
									<th>开始时间</th>
									<th>结束时间</th>
									<!-- <th>点击次数</th>
									<th>展示次数</th> -->
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
												<td><fmt:formatDate value="${product.addtime}"
														type="both" pattern="yyyy/MM/dd HH:mm:ss" /></td>
												<td>
												<c:if test="${product.type==5 }"><img src="${USERIMGSRC}${fn:replace(product.picUrl,'.','_1170_200.') }" width="100px"></c:if>
												<c:if test="${product.type==6 }"><img src="${USERIMGSRC}${fn:replace(product.picUrl,'.','_640_250.') }" width="100px"></c:if>
												<c:if test="${product.type==7 }"><img src="${USERIMGSRC}${fn:replace(product.picUrl,'.','_1170_100.') }" width="100px"></c:if>
												<c:if test="${product.type==8 }"><img src="${USERIMGSRC}${fn:replace(product.picUrl,'.','_1170_100.') }" width="100px"></c:if>
												</td>
												<td>${product.url }</td>
												<td><fmt:formatDate value="${product.startTime}"
														type="both" pattern="yyyy/MM/dd HH:mm:ss" /></td>
												<td><fmt:formatDate value="${product.endTime}"
														type="both" pattern="yyyy/MM/dd HH:mm:ss" /></td>
												<!--<td>${product.clickCount }</td>
												<td>${product.showCount }</td> -->
												<td style="position:relative"><a
													class="btn default btn-xs purple" href="javascript:void(0)"
													onclick="update(${product.id})"> <i class="fa fa-edit"></i>
														编辑
												</a> &nbsp; &nbsp; <a id="${product.id }"
													class="btn default btn-xs black listContact_removeObjectSignle"
													title="" href="javascript:void(0)"> <i
														class="fa fa-trash-o"></i> 删除
												</a> &nbsp; &nbsp;</td>

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
	$("#search").click(function() {
		$("#list").submit();
	});
</script>
<!-- END PAGE CONTENT-->
<script src="${SHOPDOMAIN}/system/js/listContact.js"></script>
<jsp:include page="../../foot.jsp"></jsp:include>