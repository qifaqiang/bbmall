<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			帮助分类设置 <small>分类</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					帮助分类设置 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 分类信息 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<style>
.yseno{
  height: 34px;
    line-height: 1.42857;
    padding: 6px 12px;
    width: 25%;
}

</style>
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
							<label class="control-label col-md-3">帮助类型名称 ： <span class="required"> * </span> </label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${obj.name }" name="name"> <input type="hidden"
									value="${obj.id }" name="id">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">父分类名称 ： </label>
							<div class="col-md-4">
								<select class="form-control" name="pid">
									<option value="0">根分类</option>
									<%-- <c:forEach items="${list}" var="li">
										<option value="${li.id }"
											<c:if test="${obj.pid==li.id }"> selected="selected"</c:if>>┕-${li.name }</option>
									</c:forEach> --%>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">是否首页显示 ： </label>
							<div class="col-md-4">
								<select class="yseno" name="isWep">
									<option value="0" <c:if test="${obj.isWep==0 }"> selected="selected"</c:if>>是</option>
									<option value="1" <c:if test="${obj.isWep==1 }"> selected="selected"</c:if>>否</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">添加图片 ： </label>
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
									<%-- <c:out value=" ${not empty obj.pic} "  escapeXml="false" /> --%>
									<c:if test="${not empty obj.pic}">
										<br><br>
										<img src="${USERIMGSRC }${fn:replace(obj.pic,'.','_28_28.')}" width="30px" />
									</c:if>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">排序 ： <span class="required"> * </span> </label>
							<div class="col-md-4">
								<input type="text" class="form-control w_rbili"
									value="${obj.sortn }" name="sortn"> 
							</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green" type="submit" >提交</button>
							<button class="btn grey-cascade sys_go_back" type="button" >返回</button>
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
<jsp:include page="../foot.jsp"></jsp:include>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />
