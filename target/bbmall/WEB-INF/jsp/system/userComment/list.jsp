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
				<li><span> 待审核列表</span></li>
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
									<th>用户名</th>
									<th>内容</th>
									<th>商品</th>
									<th>星星数量</th>
									<th style="min-width:270px;">图片</th>
									<th>评价时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty objList}">
										<c:forEach items="${objList}" var="product" varStatus="vs">
											<tr class="">
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
												<td>
												<c:if test="${not empty product.picurl }">
												<c:forEach items="${fn:split(product.picurl,';')}"
														var="obj">
														<a href="${USERIMGSRC }${obj}" target="_blank"><img
															src="${USERIMGSRC }${fn:replace(obj,'.','_200.')}" width="120px" /></a>
													</c:forEach></c:if></td>
												<td><fmt:formatDate value="${product.addtime}"
														type="both" pattern="yyyy/MM/dd HH:mm:ss" /></td>
												<td><a
													class="btn default btn-xs purple listContact_update"
													id="${product.id}" content="${product.content}"
													prod_id="${product.prodId}"
													star_count="${product.starCount}"
													pic_url="${product.picurl}" v_state="${product.vState}"
													v_comment="${product.vComment}" v_time="${product.vTime}"
													user_id="${product.userId}" addtime="${product.addtime}"
													href="javascript:void(0)"> <i class="fa fa-edit"></i>
														审核
												</a> <a id="${product.id }"
													class="btn default btn-xs black listContact_removeObjectSignle"
													title="评论" href="javascript:void(0)"> <i
														class="fa fa-trash-o"></i> 删除
												</a></td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr class="main_info">
											<td colspan="8">没有相关数据</td>
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
		$("#list").submit();
	});
	var tempParentId = 0;
	$(document)
			.on(
					"click",
					".listContact_update",
					function() {
						var id = $(this).attr("id");
						var vState = $(this).attr("v_state") == "" ? "0" : $(
								this).attr("v_state");
						var vComment = $(this).attr("v_comment");
						//var tempLinkUrl="";
						var html = "审核是否通过:<select  class='form-control v_state'><option value='1' "
								+ (vState == 1 ? "selected" : "")
								+ ">通过</option><option value='2' "
								+ (vState == 2 ? "selected" : "")
								+ ">不通过</option></select><br>"
								+ "<br>请输入审核内容:<br><textarea class='form-control v_comment' name='v_comment' id='vComment' style='width: 550px; height:100px;'>"
								+ vComment + "</textarea>";
						bootbox
								.dialog({
									message : html,
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
												tempParentId = $(".v_state")
														.val();
												$
														.ajax({
															url : "update.html",
															type : "post",
															dataType : "json",
															sync : false,
															data : {
																"id" : id,
																"vComment" : $(
																		".v_comment")
																		.val(),
																"vState" : $(
																		".v_state")
																		.val()
															},
															success : function(
																	data) {
																window.location.href = window.location.href;
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
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>