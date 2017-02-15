<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="top2.jsp"></jsp:include>
<div class="per_main">
	<!--Personal center left-->
	<jsp:include page="centerLeft.jsp"></jsp:include>
	<!--Personal center right-->
	<div class="per_mainright flW">
		<!--位置-->
		<div class="per_main_title">意见反馈</div>
		<div class="per_feedback">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tbody>
					<tr>
						<td><textarea name="textarea" class="per_feedback_input"
								id="textarea" placeholder="您的意见或建议可能对我们有很大的帮助哦！"></textarea></td>
					</tr>
					<tr>
						<td><input type="button" class="per_feedback_btn" value="提交"
							onclick="subb()"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="fox"></div>
	</div>
	<div class="fox"></div>
</div>
</div>
<jsp:include page="footShop.jsp"></jsp:include>
<script>
	document.title = "个人中心-意见反馈";
	$("#lingquan").html("个人中心");
	$(".fankui").addClass("per_nowcolor");
	function subb() {
		if ($("#textarea").val() == null || $("#textarea").val() == "") {
			showMessage("内容不可为空！");
		} else {
			$.ajax({
				url : '${SHOPDOMAIN}/customer/help/feedBack.html',
				type : "post",
				data : {
					counting : $("#textarea").val()
				},
				dataType : "json",
				async : false,
				beforeSend : function() {
				},
				success : function(result) {
					if (result.res_code == '0') {
						showMessageRefresh(result.message);
					} else {
						showMessageRefresh(result.message);
					}
				}
			});
		}
	}
</script>
