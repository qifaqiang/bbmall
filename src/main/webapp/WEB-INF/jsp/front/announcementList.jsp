<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="topShop.jsp"></jsp:include>
<link href="${SHOPDOMAIN}/front/css/web/share.css" rel="stylesheet" type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/Personal.css" rel="stylesheet" type="text/css">
<script src="${SHOPDOMAIN}/front/js/web/ntab.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/web/zzsc.js"></script>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/helpCenter.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/announcement.css" />
<body style="background: white">
<div class="s-line"></div>
<style>
    .s-line {height: 3px;background: #e70012;margin-top: -3px;}
</style>
<div class="per_main" >
    <!--Personal center left-->
    <!--Personal center right-->
    <div class="frW bordere5 annRight">
        <div class="helpCenterbreadNav borderB">
            <a href="#">系统公告</a>
        </div>
        <div class="per_main_title width878Padding">

            <div class="tabs-01">
                <div class="tabcon">
                </div>
            </div>
        </div>
    </div>
    <div class="fox"></div>
</div>
<script id="help_list" type="text/x-dot-template">
	<ul class="help_list">
		{{for(var i=0;i<it.list.length;i++){ }}
           {{var obj=it.list[i];}}
				<li class="borderBe5">
					<a href="announcementDetails.html?id={{=obj.id}}">
						<div class="flW title_width ellipsisW">{{=obj.title}}</div>
						<div class="frW">{{=obj.addtimeString}}</div>
					</a>
				</li>
			{{}}}
			<div class="page_and_btn">
				<div></div>
				{{=it.pageStr}}
			</div>
			<div style="clear:both"></div>
		</ul>
</script>
<script>
noticelist(1);
//得到公告列表
function noticelist(nom){
	$.ajax({
		url : "${SHOPDOMAIN}/customer/help/noticelist.html",
		type : "post",
		data : {
			currentPage:nom
		},
		dataType : "json",
		success : function(result) {
			var productList = doT.template($("#help_list").html())(result);
			$(".tabcon").html(productList);
		}
	});
}
</script>
<jsp:include page="footShop.jsp"></jsp:include>