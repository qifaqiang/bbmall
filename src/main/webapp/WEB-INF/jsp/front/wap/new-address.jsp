<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/front/css/wap/part-page.css">
<link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<script type="text/javascript" src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/new-address.css" />

<form action="${SHOPDOMAIN}/interfaces/userLocation/saveLocation.html" id="form_config" class="form-horizontal" method="post">
	<div class="w-main">

		<div class="new-address first">
			<div class="tle">
			            <input type="hidden" id="urls" name="urls"value="">
				收&nbsp;货&nbsp;&nbsp;人：<input type="text" class="w_require" value="" id="consignee" placeholder="姓名" name="consignee">
			</div>
		</div>

		<div class="new-address">
			<div class="tle">
				手机号码：<input type="text" class="w-textInput w_mobile  w_require" placeholder="手机号码" id="mobile" name="mobile">
			</div>
		</div>
		<div class="new-address">
			<div class="tle">
				<span  class="fl">所在地址：</span>
				<div id="area"  class="addressW18"></div>
				<div id="areaInfo" style="display: none"  class="addressW18"></div>
				<div id="areadd"  class="addressW18"></div>
				<div id="areaddInfo" style="display: none"  class="addressW18"></div>
				<input type="hidden" value="" id="address" name="address" style="width:500px;"><br />
			</div>

		</div>
		<style>
			.new-address .tle input{
				background:#fff;
			}
		</style>
		<div class="new-address">
			<div class="tle">
				详细地址：<input type="text" class="w_require" placeholder="详细地址" id="location" name="location">
			</div>
		</div>
        	<!-- <div class="new-address" style="overflow:hidden">
        	<div class="tle"> -->
		<div class="default fr">
			<img src="${SHOPDOMAIN}/front/images/wap/Round.png" /><input type="hidden" name="status" id="status" value="1">是否设为默认地址
		</div>

	</div>
	<div style="margin-top:80px;">
			<input type="button"  class="w-subBtn w-margin-top" onclick="updatePas()"  value="提交" />
		</div>
</form>
<script>
document.title = "新建地址";
	$(function() {
		initUlList("add", "areadd", 3);
		url = getRequest("url");
		if(url!=null||url!=""){
			$("#urls").val(url);
		}
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
	function updatePas() {
		if ($("#locationadd").val().trim() == "" || $("#cityadd").val().trim() == "" || $("#areaadd").val().trim() == ""
				|| $("#provinceadd").val().trim() == "") {
			showMessage("请选择完善位置信息！");
			return false;
		} else {
			$("#form_config").submit();
		}
	}
</script>
<jsp:include page="foot-validate.jsp"></jsp:include>
<script src="${SHOPDOMAIN}/front/js/provincialCascade.js"></script>
<script>
	jQuery(document).ready(function() {
		FormValidation.init();
	});
</script>
