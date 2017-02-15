<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=EOoXgpt1vSiCvajqlwxEscuZ"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/system/js/baidumap.js"></script>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			<c:choose>
				<c:when test="${empty company }">添加基地用户 </c:when>
				<c:when test="${not empty company }">修改基地 </c:when>
			</c:choose>
			<small>构建基地用户</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>用户管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span>基地用户信息</span></li>
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
					action="save.html" novalidate="novalidate">
					<div class="form-body">
						<div class="alert alert-danger display-hide">
							<button data-close="alert" class="close"></button>
							页面填写信息出错了，请先检查再提交。
						</div>
						<div class="alert alert-success display-hide">
							<button data-close="alert" class="close"></button>
							验证通过，正在提交！
						</div>
						<input type="hidden" value="${sc.id }" name="id">
						<div class="form-group">
							<label class="control-label col-md-3">基地商户名称 ：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${company.companyName }" name="companyName"
									style="width:500px;"> <input type="hidden"
									value="${company.companyId }" name="companyId">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">联系人 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${company.companyContact }" name="companyContact"
									style="width:500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">登录用户名(建议手机号) ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text"
									class="form-control w_merchants_login w_require"
									<c:if test="${!empty company.login }"> readonly="readonly"</c:if>
									value="${company.login }" name="login" style="width:500px;">
								<input type="hidden" value="${company.login }" id="loginBack">保存后不可修改，初始密码123456。
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">描述 ： </label>
							<div class="col-md-4">
								<input type="text" class="form-control w_rangelength"
									value="${company.companyDes }" name="companyDes"
									style="width:500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">联系电话 ：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_mobile w_require"
									value="${company.mobile }" name="mobile" style="width:500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">最低起送价 ：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_kucun"
									value="${company.sendPrice }" name="sendPrice"
									style="width:500px;">(元)
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">不足起送价需支付 ：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_kucun"
									value="${company.chargeSendPrice }" name="chargeSendPrice"
									style="width:500px;">(元)
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">基地位置: <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<div id="area"></div>
								<div id="areaInfo" style="display: none"></div>
								<div id="areadd"></div>
								<div id="areaddInfo" style="display: none"></div>
								<input type="text" class="form-control w_require"
									value="${company.address }" name="address" style="width:500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">基地坐标 ： </label>
							<div class="col-md-4">
								<div id="baidumap" class="baidumap"
									style="width:800px;height: 500px"></div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">经度 ：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require" id="xpostion"
									value="${empty company.xpostion?'117.08928':company.xpostion }"
									name="xpostion" readonly="readonly" style="width:500px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">纬度 ：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require" id="ypostion"
									value="${empty company.ypostion?'36.67124':company.ypostion}"
									name="ypostion" readonly="readonly" style="width:500px;">
							</div>
						</div>

					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green ssubmit" type="submit">提交</button>
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
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
<script src="${SHOPDOMAIN}/front/js/provincialCascade.js"></script>
<script>
	jQuery(document).ready(
			function() {
				// 百度地图API功能
				var data = {};
				if ("${company.xpostion}" == "") {
					data.lng = '117.08928';
					data.lat = '36.67124';
				} else {
					data.lng = '${company.xpostion }';
					data.lat = '${company.ypostion }';
				}

				createmap(data);

				FormValidation.init();
				var isUpdate = "${empty company.address }";
				if (isUpdate == "true") {
					initUlList("add", "areadd", 4);//添加
					
				} else {
					initUlList("update", "area", 4);//修改
					updateForArea("${company.provinceId}", "${company.cityId}",
							"${company.areaId}", "${company.streetId}",
							"update", "${company.regionName}");
				}

			});
	<c:if test="${not empty company.address }">
	$(".ssubmit").click(
			function() {
				if ($("#locationupdate").val().trim() == ""
						|| $("#cityupdate").val().trim() == ""
						|| $("#areaupdate").val().trim() == ""
						|| $("#streetupdate").val().trim() == ""
						|| $("#provinceupdate").val().trim() == "") {
					showMessage("请选择完善位置信息！");
					return false;
				}
			});
	</c:if>
	<c:if test="${empty company.address }">
	$(".ssubmit").click(
			function() {
				if ($("#locationadd").val().trim() == ""
						|| $("#cityadd").val().trim() == ""
						|| $("#areaadd").val().trim() == ""
						|| $("#streetadd").val().trim() == ""
						|| $("#provinceadd").val().trim() == "") {
					showMessage("请选择完善位置信息！");
					return false;
				}
			});
	</c:if>
</script>