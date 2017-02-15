<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/front/css/wap/part-page.css">
<link rel="stylesheet"
	href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css" />
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<style>
html,body,.w-main,#w-content1,#myTab,#myTabContent,#all,.classification,#myTabContent1
	{
	height: 100%;
}

body {
	padding-bottom: 184px;
}
.nav-tabs > li.active > a, .nav-tabs > li.active > a:hover, .nav-tabs > li.active > a:focus {
	border:none;
}
</style>
<div class="w-main">
	<!--头部-->
	<div id="w-header">
		<form class="sousuo">
			<input class="search" type="text" placeholder="搜索商品"> 
			<img class="search-img" src="${SHOPDOMAIN}/front/images/wap/search-img.png"   onclick='productsearch($(".search").val());' />
		</form>
	</div>

	<!-- 查出全部商品---类别 -->
	<div id="w-content1" class="product"></div>
</div>

<!--底部导航-->
<div id="w-footer">
	<ul>
		<a href="index.html">
			<li><img src="${SHOPDOMAIN}/front/images/wap/index.png" alt="首页" />
				<p class="" style="margin-bottom:0;">首页</p></li>
		</a>

		<a href="javascript:void(0)">
			<li><img
				src="${SHOPDOMAIN}/front/images/wap/classification1.png" alt="分类" />
				<p class="w-active" style="margin-bottom:0;">分类</p></li>
		</a>
		<a href="shopping-cart.html">
			<li><img src="${SHOPDOMAIN}/front/images/wap/shoppingCart.png"
				alt="购物车" />
				<p style="margin-bottom:0;">购物车</p></li>
		</a>
		<a href="personalCenter.html">
			<li><img src="${SHOPDOMAIN}/front/images/wap/mine.png" alt="我的" />
				<p style="margin-bottom:0;">我的</p></li>
		</a>
	</ul>
</div>
<div id="autoShow"></div>
<!-- 所有商品分类 -->
<script id="p_product" type="text/x-dot-template">
<ul id="myTab" class="nav nav-tabs" style="overflow-y: auto">
          {{var j=0;}}
             {{var  li=[];}}
	{{for(var i=0;i<it.length;i++){ }}
           {{var obj=it[i];}}
          {{if(obj.pid==0){  }}
         <li>
                <a data-toggle="tab" onclick='showhide({{=obj.id}})' href=".{{=obj.id}}" >
                    {{=sub5(obj.name)}}
                </a>
         </li>
                {{li[j]=obj; j++;}}
          {{}}}
	{{}}}
</ul>

<div id="myTabContent" class="tab-content  myTabContent" style="overflow-y: auto">
    {{for(var i=0;i<li.length;i++){ }}
             {{var objLi=li[i];}}
             <div id="{{=objLi.id}}" class="tab-pane fade in active {{=objLi.id}}">
                   <ul class="classification">
          {{for(var j=0;j<it.length;j++){ }}
                   {{var objIt=it[j];}}
               {{if(objLi.id==objIt.pid){  }}
                            <a href="productlist.html?catalogId2={{=objIt.id}}&catalogId1={{=objIt.pid}}&catalogId1name={{=objLi.name}}&catalogId2name={{=objIt.name}}"><li><img src="${USERIMGSRC}{{=imgZuhe(objIt.wap_pic,'_100')}}"/><h3>{{=objIt.name}}</h3></li></a>
                {{}}}
          {{}}}
                   </ul>
              </div>
	 {{}}}
</div>

<div id="myTabContent1" class="tab-content myTabContent1" style="overflow-y: auto">
  <div  class="tab-pane fade in active">
<ul id="2"  class="classification"> 
     {{for(var x=0;x<it.length;x++){ }}
            {{var obj=it[x];}}
          {{if(obj.pid!=0){  }}
               {{for(var j=0;j<li.length;j++){ }}
                   {{var objLi=li[j];}} 
                        {{if(objLi.id==obj.pid){}}   
               <a href="productlist.html?catalogId2={{=obj.id}}&catalogId1={{=obj.pid}}&catalogId1name={{=objLi.name}}&catalogId2name={{=obj.name}}"><li><img src="${USERIMGSRC}{{=imgZuhe(obj.wap_pic,'_100')}}"/><h3>{{=obj.name}}</h3></li></a>
                        {{}}}
               {{}}}
          {{}}}
	 {{}}}
</ul>
</div>
</div>
	</script>
<!-- search 后的局部刷新 -->
<script id="p_productsearch" type="text/x-dot-template">
             {{var j=0;}}
             {{var  li=[];}}
	{{for(var i=0;i<it.length;i++){ }}
           {{var obj=it[i];}}
          {{if(obj.pid==0){  }}
                {{li[j]=obj; j++;}}
          {{}}}
    {{}}}
    {{for(var i=0;i<li.length;i++){ }}
             {{var objLi=li[i];}}
             <div id="{{=objLi.id}}" class="tab-pane fade in active {{=objLi.id}} searchL">
                   <ul class="classification">
          {{for(var j=0;j<it.length;j++){ }}
                   {{var objIt=it[j];}}
               {{if(objLi.id==objIt.pid){  }}
                            <a href="productlist.html?catalogId2={{=objIt.id}}&catalogId1={{=objIt.pid}}&catalogId1name={{=objLi.name}}&catalogId2name={{=objIt.name}}"><li><img src="${USERIMGSRC}{{=imgZuhe(objIt.wap_pic,'_100')}}"/><h3>{{=objIt.name}}</h3></li></a>
                {{}}}
          {{}}}
                   </ul>
              </div>
	 {{}}}
	</script>
<script>
	document.title="商品分类";
	$(function() {
		 var companyId=$.cookie('sys_base_companyId');
	  	 if(companyId==null||companyId==""){
				$.dialog('alertHasOk', '', '为了更好的给您提供服务，请先选择距离您收货地最近的基地', 0,
					function() { window.location.href = SHOPDOMAIN + '/wap/ditu.html'
					});
	 	 }
		product();
	});

	function sub5(text){
		return text.substring(0,5);
	}
	//获取所有商品的分类名称
	
	function product() {
		$.post(SHOPDOMAIN + '/interfaces/pro/product.html', {
			type : 0,
		}, function(data) {
			if (data.res_code == '0') {
				var evalText = doT.template($("#p_product").html());
				$(".product").html(evalText(data.list));
				 $(".myTabContent").hide();
				 $(".myTabContent1").show();
			} else {
				showMessage(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}
	//获取所有商品的分类名称
	function productsearch(name) {
		window.location.href = SHOPDOMAIN + '/wap/productlist.html?search='+name;
		/* $.post(SHOPDOMAIN + '/interfaces/pro/product.html', {
			type : 0,
			name : name
		}, function(data) {
			if (data.res_code == '0') {
				var evalText = doT.template($("#p_product").text());
				$(".product").html(evalText(data.list));
				$(".myTabContent").hide();
				 $(".myTabContent1").show();
			} else {
				showMessage(data.message);
			}
		}, "json").error(function() {
			showError();
		}); */
	}
	
	function showhide(id){
		//productsearch("");
		$(".myTabContent1").hide();
		 $(".myTabContent").show();
		 /* $.post(SHOPDOMAIN + '/interfaces/pro/product.html', {
				type : 0,
				name : ""
			}, function(data) {
				if (data.res_code == '0') {
					var evalText = doT.template($("#p_productsearch").text());
					$(".myTabContent").html(evalText(data.list));
					$(".searchL").hide();
					 $("."+id).show();
				} else {
					showMessage(data.message);
				}
			}, "json").error(function() {
				showError();
			}); */
		 
	}
</script>
<jsp:include page="foot.jsp"></jsp:include>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/wap/wxshare.js"></script>
<script>
	$(function() {
		$('#myTab li:eq(1) a').tab('show');
	});
	var WXshare = {
			title :"电商平台",
			link : SHOPDOMAIN+"/wap/productcatalog.html",
			desc : '推荐一家好店，快来瞧一瞧',
			imgUrl:SHOPDOMAIN+"/front/images/whitelogo.png"
		}
	wx.config({
    	debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    	appId: '${appId}', // 必填，公众号的唯一标识
    	timestamp:${timestamp}, // 必填，生成签名的时间戳
    	nonceStr: '${nonceStr}', // 必填，生成签名的随机串
    	signature: '${signature}',// 必填，签名，见附录1
    	jsApiList: ['onMenuShareTimeline','onMenuShareQQ', 'onMenuShareAppMessage','hideMenuItems','showOptionMenu','hideOptionMenu','onMenuShareWeibo'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
</script>


