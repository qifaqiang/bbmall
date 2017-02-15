<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../../top.jsp"></jsp:include>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			商品分类设置 <small>分类 (注：如果要在wap上展示，分类名称：建议1-4个汉字，一级分类建议1-4个汉字)</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					商品分类设置 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 分类信息 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN VALIDATION STATES-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>信息
				</div>
				<div class="tools"></div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form_config" method="post"
					enctype="multipart/form-data" action="save.html"
					novalidate="novalidate">
					<div class="form-body">
						<div class="alert alert-danger display-hide">
							<button data-close="alert" class="close"></button>
							页面填写信息出错了，请先检查再提交。
						</div>
						<div class="alert alert-success display-hide">
							<button data-close="alert" class="close"></button>
							验证通过，正在提交！
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">分类名称 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require" 
									value="${obj.name }" name="name"> <input type="hidden"
									value="${obj.id }" name="id">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">排序 ： </label>
							<div class="col-md-4">
								<input type="text" class="form-control w_sort"
									value="${obj.sortn }" name="sortn">
							</div>
							从大到小
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">父分类名称 ： </label>
							<div class="col-md-4">
								<select class="form-control" name="pid">
									<option value="0">根分类</option>
									<c:forEach items="${list }" var="li">
										<option value="${li.id }"
											<c:if test="${obj.pid==li.id }"> selected="selected"</c:if>>┕-${li.name }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">是否放置wap导航 ： </label>
							<div class="col-md-4">
								<div class="btn-group" data-toggle="buttons">
									<label
										class="btn btn-default <c:if test="${obj.isTop==1||empty obj.isTop }">active</c:if>">
										<input type="radio"
										<c:if test="${obj.isTop==1||empty obj.isTop }"> checked="checked"</c:if>
										value="1" name="isTop" class="toggle"> 是
									</label> <label
										class="btn btn-default <c:if test="${obj.isTop==0 }">active</c:if>">
										<input type="radio"
										<c:if test="${obj.isTop==0 }"> checked="checked"</c:if>
										name="isTop" value="0" class="toggle"> 否
									</label>
								</div>
								只对父分类为根分类起作用（只能放置4的倍数）
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3">是否推荐 ： </label>
							<div class="col-md-4">
								<div class="btn-group" data-toggle="buttons">
									<label
										class="btn btn-default <c:if test="${obj.isRecommended==1||empty obj.isRecommended }">active</c:if>">
										<input type="radio"
										<c:if test="${obj.isRecommended==1||empty obj.isRecommended }"> checked="checked"</c:if>
										value="1" name="isRecommended" class="toggle"> 是
									</label> <label
										class="btn btn-default <c:if test="${obj.isRecommended==0 }">active</c:if>">
										<input type="radio"
										<c:if test="${obj.isRecommended==0 }"> checked="checked"</c:if>
										name="isRecommended" value="0" class="toggle"> 否
									</label>
								</div>
								只对父分类为根分类起作用
							</div>
						</div>
						<!-- <div class="form-group">
							<label class="control-label col-md-3">图片： </label>
							<div class="col-md-4">
								<div data-provides="fileinput" class="fileinput fileinput-new">
									<div class="input-group input-large">
										<div data-trigger="fileinput"
											class="form-control uneditable-input span3">
											<i class="fa fa-file fileinput-exists"></i>&nbsp; <span
												class="fileinput-filename"></span>
										</div>
										<span class="input-group-addon btn default btn-file"> <span
											class="fileinput-new"> 选择文件 </span> <span
											class="fileinput-exists"> 更改 </span> <input type="hidden"
											value="" name="..."><input type="file" name="myfile">
										</span> <a data-dismiss="fileinput"
											class="input-group-addon btn red fileinput-exists" href="#">
											删除 </a>
									</div>
									建议尺寸为100*100以上
									<c:if test="${not empty obj.pic }">
										<br>
										<br>
										<img src="${USERIMGSRC }${obj.pic}" height="120px"
											width="120px" />
									</c:if>
								</div>
							</div>
						</div> -->
						<div class="form-group">
							<label class="control-label col-md-3">wap首页图片： </label>
							<div class="col-md-4">
								<div data-provides="fileinput" class="fileinput fileinput-new">
									<div class="input-group input-large">
										<div data-trigger="fileinput"
											class="form-control uneditable-input span3">
											<i class="fa fa-file fileinput-exists"></i>&nbsp; <span
												class="fileinput-filename"></span>
										</div>
										<span class="input-group-addon btn default btn-file"> <span
											class="fileinput-new"> 选择文件 </span> <span
											class="fileinput-exists"> 更改 </span> <input type="hidden"
											value="" name="..."><input type="file"
											name="myfilewap">
										</span> <a data-dismiss="fileinput"
											class="input-group-addon btn red fileinput-exists" href="#">
											删除 </a>
									</div>
									建议尺寸为100*100以上
									<c:if test="${not empty obj.wapPic }">
										<br>
										<img src="${USERIMGSRC }${obj.wapPic}" height="50px"
											width="50px" />
									</c:if>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">pc首页图片： </label>
							<div class="col-md-4">
								<div data-provides="fileinput" class="fileinput fileinput-new">
									<div class="input-group input-large">
										<div data-trigger="fileinput"
											class="form-control uneditable-input span3">
											<i class="fa fa-file fileinput-exists"></i>&nbsp; <span
												class="fileinput-filename"></span>
										</div>
										<span class="input-group-addon btn default btn-file"> <span
											class="fileinput-new"> 选择文件 </span> <span
											class="fileinput-exists"> 更改 </span> <input type="hidden"
											value="" name="..."><input type="file"
											name="myfilepc">
										</span> <a data-dismiss="fileinput"
											class="input-group-addon btn red fileinput-exists" href="#">
											删除 </a>
									</div>
									建议尺寸为100*100以上
									<c:if test="${not empty obj.pcPic }">
										<br>
										<img src="${USERIMGSRC }${obj.pcPic}" height="50px"
											width="50px" />
									</c:if>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">wap首页描述 ： </label>
							<div class="col-md-4">
								<input type="text" class="form-control w_rangelength"
									value="${obj.wapDescn }" name="wapDescn">wap页面有两行，请用“;”(英文分号)分割
							</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green" type="submit">提交</button>
							<button class="btn grey-cascade sys_go_back" type="button">返回</button>
						</div>
					</div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
<script>
	jQuery(document).ready(function() {
		FormValidation.init();
	});
</script>
<!-- END PAGE CONTENT-->
<jsp:include page="../../foot.jsp"></jsp:include>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />
