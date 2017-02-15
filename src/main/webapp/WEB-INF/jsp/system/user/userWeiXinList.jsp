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
			用户管理 <small>微信用户列表</small>
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
<form action="userWeiXinList.html" method="post" id="list">
	<input type="hidden" value="${param.pid }" name="pid">
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
						<select name="orderbyss" class="form-control "
							style="width: 200px;float: right; margin-right: 10px">
							<option value="0">默认</option>
							<option value="1"
								<c:if test="${orderbyss==1 }"> selected="selected" </c:if>>自己分享次数
								大到小</option>
							<option value="2"
								<c:if test="${orderbyss==2 }"> selected="selected" </c:if>>一级分享次数
								大到小</option>
							<option value="3"
								<c:if test="${orderbyss==3 }"> selected="selected" </c:if>>二级分享次数
								大到小</option>
							<option value="4"
								<c:if test="${orderbyss==4 }"> selected="selected" </c:if>>总计阅读次数
								大到小</option>
							<option value="5"
								<c:if test="${orderbyss==5 }"> selected="selected" </c:if>>多多豆数量
								大到小</option>
						</select>&nbsp; <span style="float:right;height:35px;line-height:35px">排序:&nbsp;</span>

						<input type="text" name="login" class="form-control "
							style="width: 120px;float: right; margin-right: 10px"
							value="${login }">&nbsp; <span
							style="float:right;height:35px;line-height:35px">平台手机号:&nbsp;</span>
						<span class="input-group-btn" id="search"> <a
							class="btn green"> <i class="fa fa-search"></i> 搜索
						</a>
						</span>
					</div>
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>平台手机号</th>
									<th>头像</th>
									<th>昵称</th>
									<th>性别</th>
									<th>自己分享次数</th>
									<th>一级分享次数</th>
									<th>二级分享次数</th>
									<th>总计阅读次数</th>
									<th>关注时间</th>
									<th>多多豆数量</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty objList}">
										<c:forEach items="${objList}" var="product" varStatus="vs">

											<tr class="">
												<td>${vs.index +1}</td>
												<td><c:if test="${empty product.login }">
													暂无绑定
												</c:if> <c:if test="${not empty product.login }">
													${product.login }
												</c:if></td>
												<td><img src="${product.pic_url}" width=50px></td>
												<td>${product.user_name }</td>
												<td>${product.sex==1?"男":"女" }</td>
												<td>${product.sharecount }</td>
												<td>${product.yisharecount }</td>
												<td>${product.ersharecount }</td>
												<td>${product.readcount }</td>
												<td>${fn:substring(product.add_time, 0, 19)}</td>
												<td>${product.wxbeancount}</td>
												<td><a class="btn default btn-xs green"
													style="margin-top:5px;" href="javascript:void(0)"
													onclick="selectxiaji(${product.id})"> <i
														class="fa fa-file-text-o"></i>查看下级用户
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
	function selectxiaji(id) {
		window.location.href = "${SHOPDOMAIN}/system/user/userWeiXinList.html?pid="
				+ id;
	}

	//设置用户为jinyon
	$("#search").click(function() {

		$("#list").submit();
	});
</script>
<!-- END PAGE CONTENT-->
<script src="${SHOPDOMAIN}/system/js/listContact.js"></script>
<jsp:include page="../foot.jsp"></jsp:include>