<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="topShop.jsp"></jsp:include>
<link href="${SHOPDOMAIN}/front/css/web/share.css" rel="stylesheet"
	type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/Personal.css" rel="stylesheet"
	type="text/css">
<script src="${SHOPDOMAIN}/front/js/web/ntab.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/web/zzsc.js"></script>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/helpCenter.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/announcement.css" />
<body style="background: white">
<div class="s-line"></div>
<style>
.s-line {height: 3px;background: #e70012;margin-top: -3px;}
</style>
<div class="per_main">
	<!--Personal center left-->
	<!--Personal center right-->
	<div class="frW bordere5 annRight">
		<div class="helpCenterbreadNav borderB  ellipsisW"></div>
		<div class="per_main_title width878Padding">
			<div class="annDetails"></div>
		</div>
	</div>
	<div class="fox"></div>
</div>
<script id="borderB" type="text/x-dot-template">
	<a href="announcementList.html">系统公告</a> &gt; <a href="#">{{=it.content.title}}</a>
</script>
<script id="annDetails" type="text/x-dot-template">
		<h1 class="annTitle">{{=it.content.title}}</h1>
		<div class="annTime">时间：{{=it.time}}</div>
		<div class="annDetailsContent">
			<p>{{=it.content.content}}</p>
		</div>
</script>
<script>
	//首先得到URL传过来的值
	var url = decodeURI(location.search); //获取url中"?"符后的字串
	var theRequest = new Object();
	var name = $(".search").val();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
	}
	var id;
	id = theRequest['id'];

	$(function() {
		noticeCount(id);
	});

	//得到公告列表
	function noticeCount(id) {
		$.ajax({
			url : "${SHOPDOMAIN}/customer/help/noticeCount.html",
			type : "post",
			data : {
				id : id
			},
			dataType : "json",
			success : function(result) {
				var st = doT.template($("#borderB").html())(result);
				$(".borderB").append(st);
				var productList = doT.template($("#annDetails").html())(
						result);
				$(".annDetails").append(productList);
			}
		});
	}
</script>

<jsp:include page="footShop.jsp"></jsp:include>