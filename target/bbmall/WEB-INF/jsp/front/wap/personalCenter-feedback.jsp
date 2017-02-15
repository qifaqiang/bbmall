<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<div class="w-main">

	<div class="w-orderList">
		<div class="w-textarea">
			<form action="${SHOPDOMAIN}/interfaces/sysFeedback/sysFeedAdd.html" id="form_config" class="form-horizontal" method="post">
				<input type="text" style="padding:20px 37px;" class=" w_mobile_email w-textInput w_require"  id="mobile" name="mobile" placeholder="联系方式 手机或者邮箱" >
				<textarea name="content" class=" w_require w_content" id="content" name="content" maxlength="200" placeholder="请输入您对我们的意见，我们将不断进行优化"></textarea>
				<input type="submit" class="w-subBtn w-margin-top" value="提交" />
			</form>

		</div>
	</div>
</div>
<script>
	document.title="意见反馈";
	var wxtime=3000;
	jQuery(document).ready(function() {
		FormValidation.init();
	});
</script>
<jsp:include page="foot-validate.jsp"></jsp:include>