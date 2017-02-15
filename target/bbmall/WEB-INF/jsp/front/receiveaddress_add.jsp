<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="top2.jsp"></jsp:include>
<link href="${SHOPDOMAIN}/front/css/web/share.css" rel="stylesheet" type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/Personal.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css" />
<script src="${SHOPDOMAIN}/front/js/web/ntab.js"></script>
<script type="text/javascript" src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<div class="per_main">
	<jsp:include page="centerLeft.jsp"></jsp:include>
	<!--Personal center right-->
	<div class="per_mainright flW">
		<!--位置-->
		<div class="per_main_title">收货地址</div>
		<div class="per_adr_addinfor">

			<form action="${SHOPDOMAIN}/interfaces/userLocation/saveLocation.html" id="form_config" class="form-horizontal" method="post">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td width="28%" align="right">收货人：</td>
							<td width="72%"><input type="text" class=" per_adr_input w_require" value="" name="consignee" id="consignee" placeholder="请输入您的姓名"></td>
						</tr>
						<tr>
							<td align="right">手机号码：</td>
							<td><input type="text" class="per_adr_input w-textInput w_mobile  w_require" name="mobile" id="mobile" placeholder="请输入您的手机号码"></td>
						</tr>
						<tr>
							<td>所在地址</td>
							<td>
								<div class="new-address">
									<div class="tle">
										<div id="area" class="addressW18"></div>
										<div id="areaInfo" style="display: none" class="addressW18"></div>
										<div id="areadd" class="addressW18"></div>
										<div id="areaddInfo" style="display: none" class="addressW18"></div>
										<input type="hidden" value="" id="address" name="address" style="width:500px;"><br />
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td align="right">详细地址：</td>
							<td><input type="text" class="per_addadrinput2 w_require" name="location" id="location" placeholder="请输入您的详细地址"> <input
								type="hidden" name="status" id="status" value="0"></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><input type="button" class="per_adr_btn2 w-subBtn w-margin-top" onclick="updatePas()" value="提交" /></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<div class="fox"></div>
	</div>
	<div class="fox"></div>
</div>
<div class="rightFixed">

	<ul class="">
		<a href="#">
			<li class="shoppingPos">

				<div class="shoppingCount">1000</div> 购物车
		</li>
		</a>
		<a title="点击这里给我发消息" href="http://wpa.qq.com/msgrd?v=3&amp;uin=123456789&amp;site=www.cactussoft.cn&amp;menu=yes" target="_blank">
			<li class="servicePos">客服</li>
		</a>
		<a href="#">
			<li class="collectionPos">收藏</li>
		</a>
		<a href="#headerW" class="topLink">
			<div>
				<li class="topPos" style="display: none"></li>
			</div>

		</a>
	</ul>
</div>

<jsp:include page="foot-validate.jsp"></jsp:include>
<script src="${SHOPDOMAIN}/front/js/provincialCascade.js"></script>
<script>
	document.title="个人中心-收货地址";
	$(function() {
		initUlList("add", "areadd", 3);
		url = getRequest("url");
		if (url != null || url != "") {
			$("#urls").val(url);
		}
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
<script>
	jQuery(document).ready(function() {
		FormValidation.init();
	});
</script>