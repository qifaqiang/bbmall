<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<style>
.table tbody tr td{
vertical-align: middle
}
</style>
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
						<input type="hidden" value="${objs.vComment }" name="vComment">
						<input type="hidden" value="${objs.vTime }" name="vTime">
							<input type="text" class="form-control w_mobile"
							value="${ objs.content}" style="width: 180px; float: right;"
							name="content" placeholder="" id="title"> <span
							style="float:right;height:35px;line-height:35px">评价内容:&nbsp;</span>
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>用户名</th>
									<th>内容</th>
									<th>商品ID</th>
									<th>星星数量</th>
									<th>图片</th>
									<th>评价时间</th>
									<th>审核状态</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty objList}">
										<c:forEach items="${objList}" var="product" varStatus="vs">

											<tr class="">
												<td>${vs.index +1}</td>
												<td>${product.userId}--${product.id }</td>
												<td>${product.content}</td>
												<td>${product.prodId}</td>
												<td>${product.starCount}</td>
												<td><img src="${USERIMGSRC }${product.picurl}"
												width="120px" /></td>
												<td>${product.addtime}</td>
												<c:if test="${product.vState==1 }">
													<td>审核通过</td>
												</c:if>
												<c:if test="${product.vState==2 }">
													<td>审核不通过</td>
												</c:if>
												<c:if test="${product.vState==3 }">
													<td>未审核</td>
												</c:if>
												<td><a class="btn default btn-xs purple listContact_update"
														id="${product.id}" content="${product.content}"
														prod_id="${product.prodId}" star_count="${product.starCount}"
														pic_url="${product.picurl}" v_state="${product.vState}"
														v_comment="${product.vComment}" v_time="${product.vTime}"
														user_id="${product.userId}" addtime="${product.addtime}" 
														href="javascript:void(0)"> <i class="fa fa-edit"></i>
															编辑(审核)
													</a>&nbsp; &nbsp; <a id="${product.id }"
												class="btn default btn-xs black listContact_removeObjectSignle"
												title="评论" href="javascript:void(0)"> <i
													class="fa fa-trash-o"></i> 删除
											</a>
											</td>
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
	function editProduct(id) {
		window.location.href = "update.html?id=" + id;
	}
	
	var tempParentId=0;
	$(document).on("click",".listContact_update",function() {
						var id = $(this).attr("id");
						var content = $(this).attr("content");
						var prodId = $(this).attr("prod_id");
						var starCount = $(this).attr("star_count");
						var picUrl = $(this).attr("pic_url");
						var vState = $(this).attr("v_state")==""?"0":$(this).attr("v_state");
						var userId = $(this).attr("user_id");
						var addtime = $(this).attr("addtime");
						var vComment = $(this).attr("v_comment");
						var vTime = $(this).attr("v_time");
						var tempLinkUrl=""
						var html ="内容text:<input type='text'  value='"+vComment+"'><br>"
						+ "审核是否通过:<select class='form-control v_state'><option value='0' "+(vState==1?"selected":"")+">通过</option><option value='2' "+(vState==0?"selected":"")+">不通过</option><option value='3' "+(vState==0?"selected":"")+">未审核</option></select><br>";
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
												tempParentId=$(".v_state").val();
												$.ajax({
														url : "save.html",
														type : "post",
														dataType:"json",
														sync:false,
														data : {
															"id" : id,
															"vComment" : $(".v_comment").val()
														},
														success : function(data) {
															if (data.type=="success") {
																if(tempParentId==0){
																	window.location.href = window.location.href;
																}else{
																	getSubInfo(tempParentId,1,tempParentId)
																}
															} else {
																alertError(data.content);
															}
														}
													});
											}
										}
									}
								});
					});
	

	<!---->
		//设置系统用户为jinyon
		$("#search").click(function() {
			$("#list").submit();
		});
</script>
<!-- END PAGE CONTENT-->
<script src="${SHOPDOMAIN}/system/js/listContact.js"></script>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>