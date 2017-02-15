<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="top2.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="${SHOPDOMAIN}/front/css/web/share.css" rel="stylesheet"
	type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/Personal.css" rel="stylesheet"
	type="text/css">
<script src="${SHOPDOMAIN}/front/js/web/ntab.js"></script>
<style>
.per_data table tr td li {float: left;line-height: 30px;margin-right: 75px;
}
</style>
<div class="per_main">
	<jsp:include page="centerLeft.jsp"></jsp:include>
	<!--Personal center right-->
	<div class="per_mainright flW">
		<!--位置-->
		<div class="per_main_title">个人资料</div>
		<div class=" per_data" style="min-height: 340px">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tbody>
					<tr>
						<td align="right">头像：</td>
						<td><ul>
								<li class="per_data_width"><div class="per_header flW"
										style="margin-left: 0px;margin-top: 0px">
										<a href="personadataPhoto.html"><img
											src="${USERIMGSRC}${sessionFrontUser.picUrl }" /></a>
									</div></li>
								</div>
								</a>
							</ul></td>
					</tr>
					<tr>
						<td width="21%" align="right">昵称：</td>
						<td width="79%"><input class="per_data_input"
							value="${sessionFrontUser.name}" type="text" id="nickname"
							name="nickname" id="textfield"> <input type="button"
							class="per_data_btn2   w_require w_6_20" onclick="upName()"
							name="nicknamea" id="nicknamea" value="修改"></td>
					</tr>
				</tbody>
			</table>

		</div>
		<div class="fox"></div>
	</div>
	<div class="fox"></div>
</div>

<jsp:include page="footShop.jsp"></jsp:include>

<script>
	document.title="个人中心-个人资料";
	function upName() {
		var nickname = $("#nickname").val();
		if (nickname.length > 5 && nickname.length < 21) {
			upnickname(nickname);
		} else {
			showm("长度在6-20位之间");
		}
	}
	function upnickname(nickname) {
		$.post(SHOPDOMAIN + '/updatenicheng.html', {
			nickname : nickname
		}, function(data) {
			if (data.res_code == '0') {
				showm(data.message);
			} else {
				if (data.res_code == '1') {
					showm(data.message);
				} else {
					showm(data.message);
				}
			}
		}, "json").error(function() {
			showError();
		});
	}
	jQuery(document).ready(function() {
		$(".ziliao").addClass("per_nowcolor");
		$("#lingquan").html("个人中心");
	});
</script>