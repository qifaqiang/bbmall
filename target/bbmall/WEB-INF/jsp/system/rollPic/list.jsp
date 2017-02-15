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
			轮播图管理<small>轮播图设置</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>轮播图管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span>轮播图列表</span></li>
			</ul>
		</div>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<style>
.table tbody tr td{
vertical-align: middle
}
</style>
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
							type="button">添加轮播图</button>
						<input type="text" class="form-control w_mobile "
							value="${ objs.title}" style="width: 180px; float: right;"
							name="title" placeholder="" id="title"> <span
							style="float:right;height:35px;line-height:35px">轮播图标题:&nbsp;</span>
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
									<th>标题</th>
									<th>图片地址</th>
									<th>链接地址</th>
									<th>排序</th>
									<th>放置位置</th>
									<th>录入时间</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty objList}">
										<c:forEach items="${objList}" var="product" varStatus="vs">

											<tr class="">
												<td>${vs.index +1}</td>
												<td>${product.title}</td>
												<td><img
													src="${USERIMGSRC }${fn:replace(product.picUrl,'.','_640_300.')}"
													width="180px" /></td>
												<td>${product.linkUrl}</td>
												<td>${product.sorts}</td>
												<td>${product.type==1?"pc":"wap"}</td>
												<td><fmt:formatDate value="${product.addtime}"
														type="both" pattern="yyyy/MM/dd HH:mm:ss" /></td>
												<td><a class="btn default btn-xs purple"
													href="javascript:void(0)"
													onclick="editProduct(${product.id})"> <i
														class="fa fa-edit"></i> 编辑
												</a> &nbsp; &nbsp;<a id="${product.id }"
													class="btn default btn-xs black listContact_removeObjectSignle"
													title="图片" href="javascript:void(0)"> <i
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
	function editProduct(id) {
		window.location.href = "update.html?id=" + id;
	}

	//设置系统用户为jinyon
	$("#search").click(function() {
		$("#list").submit();
	});
	function editCompany(id) {
		window.location.href = "update.html?companyId=" + id;
	}
</script>
<!-- END PAGE CONTENT-->
<script src="${SHOPDOMAIN}/system/js/listContact.js"></script>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>