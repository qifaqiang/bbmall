<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter.css"/>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
<div class="w-main">
    <!--头部-->


    <!--内容-->

    <div class="w-setup-list">
        <a href="personalCenter-setUp-wi.html">
            <div class="w-setup-content">
                <span class="fl">关于我们</span>
            <span class="fr">
                <span class="w-falseH"></span>
                <img src="${SHOPDOMAIN}/front/images/wap/toRight-w.png" alt=""/>
            </span>
            </div>
        </a>
    </div>
    <form action="${SHOPDOMAIN}/logout.html" id="form_config" class="w-margin-top">
        <input type="button" class="w-exitBtn"  value="退出登录"/>
    </form>
</div>
 <jsp:include page="foot-validate.jsp"></jsp:include>
<script>
document.title="关于我们";
  $(".w-exitBtn").click(function() {
		$("#form_config").submit();
	});
	jQuery(document).ready(function() {
		FormValidation.init();
	});
</script>