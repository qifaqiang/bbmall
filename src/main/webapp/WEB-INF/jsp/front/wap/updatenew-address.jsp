<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/front/css/wap/part-page.css">
<link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css" />
<script type="text/javascript" src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/front/css/wap/updatenew-address.css">
<style>
.new-address .tle input {
	background: #fff;
}
</style>
<form action="${SHOPDOMAIN}/interfaces/userLocation/updateLocation.html" id="form_config" class="form-horizontal" method="post">
	<div class="w-main">
		<!--头部-->

		<div class="new-address first">
			<div class="tle">
				<input type="hidden" id="urls" name="urls" value=""> <input type="hidden" id="id" name="id"> 收&nbsp;货&nbsp;&nbsp;人：<input type="text"
					class="w_require" value="" id="consignee" placeholder="姓名" name="consignee">
			</div>
		</div>

		<div class="new-address">
			<div class="tle">
				手机号码：<input type="text" class="w-textInput w_mobile  w_require" placeholder="手机号码" id="mobile" name="mobile">
			</div>
		</div>
		<div class="new-address">
			<div class="tle">
				<span class="fl">所在地址：</span>
				<div id="areal" class="addressW18"></div>
				<div id="arealInfo" style="display: none" class="addressW18"></div>
				<div id="three" class="addressW18"></div>
				<div id="threeInfo" style="display: none" class="addressW18"></div>
				<input type="hidden" value="" id="address" name="address" style="width:500px;"><br />
			</div>

		</div>
		<div class="new-address">
			<div class="tle">
				详细地址：<input type="text" class="w_require" placeholder="详细地址" id="location" name="location">
			</div>
		</div>
		<!-- <div class="new-address">
			<div class="tle">
				邮政编码：<input type="text" class="w_require" placeholder="邮政编码" id="zipcode" name="zipcode">
			</div>
		</div> -->
		<div class="default fr" style="font-family:微软雅黑">
			<img id="statusimg" src="" /><input type="hidden" name="status" id="status" value="0">是否设为默认地址
		</div>

		<div style="margin-top:80px;">
			<input type="button" id="subs" onclick="updatePas()" class="w-subBtn w-margin-top" value="提交" />
		</div>
	</div>
</form>
<script>
	document.title = "更新地址";
	url = getRequest("urls");
	if (url != null || url != "") {
		$("#urls").val(url);
	}
	$(function() {
		$('.default').on('click', function() {
			var oImgSrc = $(this).find('img').attr('src');
			if (oImgSrc == '${SHOPDOMAIN}/front/images/wap/Round.png') {
				$(this).find('img').attr('src', '${SHOPDOMAIN}/front/images/wap/w-success.png');
				$("#status").val(true);
			} else {
				$(this).find('img').attr('src', '${SHOPDOMAIN}/front/images/wap/Round.png');
				$("#status").val(false);
			}
		});
	});
</script>
<jsp:include page="foot-validate.jsp"></jsp:include>
<script src="${SHOPDOMAIN}/front/js/provincialCascade.js"></script>
<script>
	jQuery(document).ready(function() {
		initUlList("updatel", "three", 3);
		id = getRequest("id");
		$.post(SHOPDOMAIN + '/interfaces/userLocation/seleById.html', {
			id : id,
		}, function(data) {
			if (data.res_code == '0') {
				$("#consignee").val(data.list.consignee);
				$("#mobile").val(data.list.mobile);
				$("#location").val(data.list.location);
				$("#address").val(data.list.regionName);
				$("#id").val(data.list.id);
				$("#zipcode").val(data.list.zipcode);
				if (data.list.status == 1) {
					$("#statusimg").attr('src', '${SHOPDOMAIN}/front/images/wap/w-success.png');

				} else {
					$("#statusimg").attr('src', '${SHOPDOMAIN}/front/images/wap/Round.png');

				}
				if (data.list.provinceId != null) {
					updateArea(data.list.provinceId, data.list.cityId, data.list.areaId, "updatel", data.list.regionName);
				}
			} else {
				showMessage(data.message);
			}
		}, "json").error(function() {
			showError();
		});
		FormValidation.init();
	});
	function updatePas() {
		if ($("#locationupdatel").val().trim() == "" || $("#cityupdatel").val().trim() == "" || $("#areaupdatel").val().trim() == ""
				|| $("#provinceupdatel").val().trim() == "") {
			showMessage("请选择完善位置信息！");
			return false;
		} else {
			$("#form_config").submit();
		}
	}
</script>
