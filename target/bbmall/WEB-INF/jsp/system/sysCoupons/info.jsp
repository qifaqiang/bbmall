<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			优惠券管理 <small>列表</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					优惠券管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 信息 </a></li>
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
							<label class="control-label col-md-3">名称 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${obj.name }" name="name"> <input type="hidden"
									value="${obj.id }" name="id">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">备注 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${obj.remark }" name="remark">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">类型 ： </label>
							<div class="col-md-4">
								<div class="btn-group " data-toggle="buttons">
									<label
										class="btn btn-default typecoupons1 <c:if test="${obj.type==1||empty obj.type }">active</c:if>">
										<input type="radio"
										<c:if test="${obj.type==1||empty obj.type }"> checked="checked"</c:if>
										value="1" name="type" class="toggle"> 公开领取
									</label> <label
										class="btn btn-default  typecoupons0 <c:if test="${obj.type==0 }">active</c:if>">
										<input type="radio"
										<c:if test="${obj.type==0 }"> checked="checked"</c:if>
										name="type" value="0" class="toggle"> 注册赠送 禁止领取
									</label>
								</div>
								&nbsp; &nbsp; 选择：注册赠送 禁止领取，那么总数量为0
							</div>
						</div>

						<div class="form-group starttime"
							<c:if test="${obj.type==1||empty obj.type }">style="display:11"</c:if>
							<c:if test="${obj.type==0 }">style="display:none"</c:if>>
							<label class="control-label col-md-3">允许使用时间 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control" readonly="readonly"
									id="defaultrange_modal" name="starttime"
									value="<fmt:formatDate value="${obj.startTime}"
														type="both" pattern="yyyy-MM-dd" /> ${empty obj.startTime?"
									":"~"  } <fmt:formatDate
														value="${obj.endTime}" type="both"
														pattern="yyyy-MM-dd" />" style="width: 520px;">
							</div>
						</div>
						<div class="form-group validTime"
							<c:if test="${obj.type==1||empty obj.type }">style="display:none"</c:if>>
							<label class="control-label col-md-3">领取后有效时间 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_positive_number"
									value="${obj.validTime }" name="validTime">
							</div>
							(天)
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">满多少 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require w_weight"
									value="${obj.needPrice }" name="needPrice">
							</div>
							(元)
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">减多少 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require w_weight"
									value="${obj.substractPrice }" name="substractPrice">
							</div>
							(元)
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">总数量 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require w_kucun"
									value="${obj.allCount }" name="allCount">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">可用数量 ： </label>
							<div class="col-md-4">
								<input type="text" class="form-control" readonly="readonly"
									value="${obj.leftCount }">
							</div>
						</div>


					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green submitss" type="button">提交</button>
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

	$(".typecoupons1").click(function() {
		$(".validTime").css("display", "none");
		$(".starttime").css("display", "");
		$("input[name=allCount]").removeAttr("readonly");
	})
	$(".typecoupons0").click(function() {
		$(".starttime").css("display", "none");
		$(".validTime").css("display", "");
		$("input[name=allCount]").attr("readonly", "readonly").val(0);
	})

	$(".submitss").click(function() {
		var temp = $("input[name=type]:checked").val();
		if (temp == 1) {//领取
			if ($("input[name=starttime]").val().trim() == "") {
				showMessage("请填写允许使用时间!");
				return false;
			}
		} else {//赠送
			if ($("input[name=validTime]").val().trim() == "") {
				showMessage("请填写领取后有效时间!");
				return false;
			}
		}
		$("#form_config").submit();
	});
</script>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
