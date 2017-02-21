<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="top2.jsp"></jsp:include>
<script src="${SHOPDOMAIN}/front/js/web/ntab.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/web/zzsc.js"></script>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/helpCenter.css" />
<style>
.per_mainleft li.hov a {
    color: #e70012;
}
</style>
	<div class="per_main">
		<!--Personal center left-->

		<div class="per_mainleft  flW">
		</div>
		<!--Personal center right-->
		<div class="per_mainright flW bordere5 width958">
			<!--面包屑导航-->
			<div class="helpCenterbreadNav">
				<a href="#" id="zhinan">帮助中心</a> &gt;
			</div>
			<div class="per_main_title width918Padding">
				<div class="tabs-01">
					<div class="tabcon">
						<h4 class="help-tit-l2"></h4>
						<ul class="help_list">
						<li style="text-align:center; font-size:40px; font-weight:bold; margin-top:100px;"><a> <b></b>齐鲁干烘茶城帮助中心
							</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="fox"></div>
	</div>
<script id="per_mainleft" type="text/x-dot-template">
	{{ for(var i=0; i<it.list.length;i++){ }}
	{{var obj=it.list[i];}}
		{{if(i==0){}}
			<h3>{{=obj.name}}</h3>
		{{}else{}}
			<h3  class="per_martop">{{=obj.name}}</h3>
		{{}}}
		<ul>
		{{ for(var j=0; j<obj.sn.length;j++){ }}
			{{var objL=obj.sn[j];}}
			{{var name1=obj.name;var title1=objL.title;}}
				<li class="wen wen_{{=objL.id}}"><a onclick="getnewlist({{=objL.id}})" id="wen_{{=objL.id}}" name="{{=obj.name}}" title="{{=objL.title}}">{{=objL.title}}</a></li>
		{{}}}
		</ul>
	{{}}}
</script>
<script id="per_mainright" type="text/x-dot-template">
	<div class="helpCenterbreadNav">
		<a href="#" id="zhinan">购物指南</a> &gt; <a href="#"  id="liucheng">购物流程</a>
			</div>
			<div class="per_main_title width918Padding">
				<div class="tabs-01">
					<div class="tabcon">
						<h4 class="help-tit-l2"></h4>
						<ul>
						{{ if(it.list&&it.list.length>0){  }}
							{{ for(var i=0; i<it.list.length;i++){ }}
							{{var obj=it.list[i];}}
								<li>{{=obj.content}}</li>
							{{}}}
						{{}}}
						</ul>
					</div>
				</div>
			</div>
</script>
	<script>
	document.title="齐鲁干烘茶城     帮助中心";
	$("#lingquan").html("帮助中心");
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
		getlist();	
		if(id!=undefined){
			getnewlist(id);
			id=undefined;
		}
		//鼠标移停事件
		$(".wen").hover(function(){
			$(this).addClass("hov");
		},function(){
			$(this).removeClass("hov");
		});
	});
	//得到帮助目录
	var data={};
	function getlist() {
		$.ajax({
			url:'${SHOPDOMAIN}/customer/help/helpList.html',
			type:"post",
			data: {
	        },
			dataType:"json",
			async:false,
			beforeSend:function(){
			},
			success:function(result){
			if (result.res_code == '0') {
				var productList = doT.template($("#per_mainleft").html())(result);
				$(".per_mainleft").append(productList);
			}else {
				showMessageRefresh(result.message);
				} 
			}});
			}
	//得到帮助文章
	function getnewlist(id) {
		$.ajax({
			url:'${SHOPDOMAIN}/customer/help/helpnewsList.html',
			type:"post",
			data: {
				id:id
	        },
			dataType:"json",
			async:false,
			beforeSend:function(){
			},
			success:function(result){
			if (result.res_code == '0') {
				var productList = doT.template($("#per_mainright").html())(result);
				$(".per_mainright").empty();
				$(".per_mainright").append(productList);
				$("#zhinan").html($("#wen_"+id).attr("name"));
				$("#liucheng").html($("#wen_"+id).attr("title"));
				
				$(".wen").removeClass("per_nowcolor");
				$(".wen_"+id).addClass("per_nowcolor");
			}else {
				showMessageRefresh(result.message);
				} 
			}});
			}
	</script>
	<jsp:include page="footShop.jsp"></jsp:include>
