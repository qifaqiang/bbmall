<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="top.jsp"></jsp:include>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<div class="w-main">
	<!--头部-->
	 	<form action="${SHOPDOMAIN}/interfaces/orderSele/refuondOrd.html" id="form_config" class="form-horizontal" method="post">
		<div class="w-setup-list w-fontsize24">
			<div class="w-setup-content w-height97 w-borderBottom">
				交易金额：<input type="text" class="w-refundNum" id="jine" readonly value="" /> <input type="hidden" id="orderIds" name="orderId" value="" />
			</div>
			<div class="w-padding37">
				<span class="fl w-paddingT30">退款原因：</span>
				<textarea name="remark" maxlength="200" class="w-refunReason w_require w_content" id="" placeholder="请填写退款原因，最多200字"></textarea>
			</div>
		</div>
		<input type="submit" class="w-subBtn w-margin-top" id="submitBtn" value="提交申请">
	</form>
</div>
<jsp:include page="foot-validate.jsp"></jsp:include>
<script>
	document.title = "退款申请";
	id = getRequest("id");
	$(function() {
		$("#orderIds").val(id);
		selepirce(id);
	});
	function selepirce(id) {
		$.post(SHOPDOMAIN + '/interfaces/orderSele/selePrice.html', {
			id : id
		}, function(data) {
			if (data.res_code == '0') {
				$("#jine").val("￥"+data.price);
			}
		}, "json").error(function() {
			showError();
		});
	}
	jQuery(document).ready(function() {
		FormValidation.init();
	});
</script>
