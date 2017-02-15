<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter.css"/>
</head>
<style>
    html, body {
        height: 100%;
    }
</style>
<body>
<div class="w-main">

    <div class="w-custompPhoneNumber" >
        <a href="tel:" id="mobile"class="w-custompPhoneNumber-a">
        	鲜易达客服专线:<br>${SYSPROPORTION.mobile }
        </a><br><br>
        <a href="javascript:void(0)" id="qq"class="w-custompPhoneNumber-a">
        	QQ:<br>${SYSPROPORTION.qq }
        </a>
    </div>

</div>
<jsp:include page="foot.jsp"></jsp:include>
<script>
document.title = "联系客服";
</script>