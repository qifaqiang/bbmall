<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="top2.jsp"></jsp:include>

<link href="${SHOPDOMAIN}/front/css/web/share.css" rel="stylesheet" type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/Personal.css" rel="stylesheet" type="text/css">
<script src="${SHOPDOMAIN}/front/js/web/ntab.js"></script>


<div class="per_main">
	<jsp:include page="centerLeft.jsp"></jsp:include>
	<div class="per_mainright flW">
		<!--位置-->
		<div class="per_main_title">个人资料</div>
		<div class=" per_data">
			<form action="${SHOPDOMAIN}/saveupdatesecret.html" id="form_config" class="form-horizontal" method="post">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td width="21%" align="right">原密码：</td>
							<td width="79%"><input class="per_data_input  " type="password" name="oldpassword" id="oldpassword" placeholder="原密码"></td>
							<input type="hidden" name="urls" id="urls">
						</tr>
						<tr>
							<td align="right">新密码：</td>
							<td><input class="per_data_input  " type="password" placeholder="新密码" name="newpassword" id="newPass"></td>
						</tr>
						<tr>
							<td align="right">确认密码：</td>
							<td><input class="per_data_input " type="password" name="renewpassword" id="oldPass" placeholder="确认新密码"></td>
						</tr>
						<tr>
							<td align="right">&nbsp;</td>
							<td><input type="button" class="per_data_btn2" onclick="updatePas()" name="button" id="button" value="提交"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<div class="fox"></div>
	</div>
	<div class="fox"></div>
</div>


<jsp:include page="footShop.jsp"></jsp:include>
<script>
	document.title="个人中心-个人资料";
	function updatePas() {
		var oldpassword = $("#oldpassword").val();
		
		var newPass = $("#newPass").val();
		var oldPass = $("#oldPass").val();
		if (oldpassword.length<6||oldpassword.length>20) {
			showm("原密码长度6~20位!");
			return false;
		} else if (newPass.length<6||newPass.length>20) {
			showm("新密码长度6~20位!");
			return false;
		} else if (oldPass.length<6||oldPass.length>20) {
			showm("确认新密码长度6~20位!");
			return false;
		} else if (newPass != oldPass) {
			showm("确认新密码和新密码不一致!");
			return false;
		} else {
			 $("#form_config").submit();
		}
	}
	jQuery(document).ready(function() {
		$("#urls").val("personadata.html");
		$(".mima").addClass("per_nowcolor");
		FormValidation.init();
	});
</script>