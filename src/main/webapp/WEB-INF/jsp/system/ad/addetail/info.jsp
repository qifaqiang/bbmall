<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />

<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			<c:choose>
				<c:when test="${empty rollPic }">新增广告 </c:when>
				<c:when test="${not empty rollPic }">广告修改</c:when>
			</c:choose>
			<small>设置</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>广告管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span>广告设置</span></li>
			</ul>
		</div>
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
					<i class="fa fa-reorder"></i>基本信息
				</div>
			</div>
			<div class="portlet-body form">

				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form_config" method="post"
					action="save.html" enctype="multipart/form-data"
					novalidate="novalidate">

					<div class="form-body">
						<div class="portlet-body">
							<div class="alert alert-block alert-info fade in">
								<button data-dismiss="alert" class="close" type="button"></button>
								<h4 class="alert-heading">帮助:类型定义!</h4>
								<c:forEach items="${adlist }" var="tlists" varStatus="index">
									<p>${index.index+1 }.${tlists.name }类型:长度:${tlists.width }
										宽度:${tlists.high }</p>
								</c:forEach>
								<c:if test="${empty adlist }">
									<p style="color:red">请先添加广告类型</p>
								</c:if>
							</div>
						</div>

						<div class="alert alert-danger display-hide">
							<button data-close="alert" class="close"></button>
							页面填写信息出错了，请先检查再提交。
						</div>
						<div class="alert alert-success display-hide">
							<button data-close="alert" class="close"></button>
							验证通过，正在提交！
						</div>
						<input type="hidden" value="${rollPic.id }" name="id">
						<div class="form-group">
							<label class="control-label col-md-3">名称：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${rollPic.name}" name="name" style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">时间范围：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									readonly="readonly" id="defaultrange_modal"
									value="<fmt:formatDate value='${rollPic.startTime}'
														type='both' pattern='yyyy-MM-dd' />${not empty rollPic.startTime?'~':''}<fmt:formatDate value='${rollPic.endTime}'
														type='both' pattern='yyyy-MM-dd' />"
									name="setime" style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">状态 ：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4"><label for="son">
								<input type="radio" id="son" class="form-control"
									${empty rollPic.state?"checked='checked'":""}
									${rollPic.state==1?"checked='checked'":""} name="state"
									value="1"> 启用</label> <label for="sdown"><input
									id="sdown" type="radio"
									${rollPic.state==0?"checked='checked'":""} class="form-control"
									name="state" value="0"> 禁用</label>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">类型 ：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<select name="type" class="form-control w_require"
									style="width: 500px;">
									<c:forEach items="${adlist }" var="tlists">
										<option value="${tlists.id }"
											<c:if test="${rollPic.type==tlists.id }">selected="selected"</c:if>>${tlists.name }</option>
									</c:forEach>
								</select>修改类型的时候，必须重新上传图片
							</div>

						</div>
						<div class="form-group">
							<label class="control-label col-md-3">链接地址 ：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									placeholder="请以'http://'开头" value="${rollPic.url}" name="url"
									style="width: 500px;">
							</div>
						</div>

						<!-- <div class="form-group">
							<label class="control-label col-md-3">点击次数 ：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_kucun"
									value="${rollPic.clickCount}" name="clickCount"
									style="width: 500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">展示次数：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_kucun"
									value="${rollPic.showCount}" name="showCount"
									style="width: 500px;">
							</div>
						</div> -->
						<div class="form-group">
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
											class="fileinput-exists"> 变更 </span> <input type="hidden"
											value="" name="..."><input type="file" name="myfile">
										</span> <a data-dismiss="fileinput"
											class="input-group-addon btn red fileinput-exists" href="#">
											删除 </a>
									</div>

								</div>

							</div>
						</div>
						<div class="form-group">
							<c:if test="${not empty rollPic.picUrl }">
								<label class="control-label col-md-3"></label>
							</c:if>
							<div class="col-md-3">
								<c:if test="${not empty rollPic.picUrl }">
									<c:if test="${rollPic.type==5 }">
										<img
											src="${USERIMGSRC}${fn:replace(rollPic.picUrl,'.','_1170_200.') }"
											width="300px">
									</c:if>
									<c:if test="${rollPic.type==6 }">
										<img
											src="${USERIMGSRC}${fn:replace(rollPic.picUrl,'.','_640_250.') }"
											width="300px">
									</c:if>
									<c:if test="${product.type==7 }">
										<img
											src="${USERIMGSRC}${fn:replace(rollPic.picUrl,'.','_1170_100.') }"
											width="300px">
									</c:if>

								</c:if>
							</div>
						</div>
					</div>
					<div class="form-actions fluid" style="margin-top: 40px;">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green ssubmit" type="button">提交</button>
							<button class="btn gray" type="button"
								onclick="javascript:history.go(-1)">返回</button>
						</div>
					</div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../../foot.jsp"></jsp:include>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/moment.js"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>

<script>
	jQuery(document).ready(
			function() {
				FormValidation.init();
				$('#defaultrange_modal').daterangepicker(
						{
							timePicker : true,
							timePickerIncrement : 30,
							format : 'YYYY-MM-DD'

						},
						function(start, end, label) {
							$('#defaultrange_modal input').val(
									start.format('YYYY-MMMM-DD') + ' ~ '
											+ end.format('YYYY-MMMM-DD'));
						});
			});
	$(".ssubmit").click(function() {
		$("#form_config").submit();
	})
</script>