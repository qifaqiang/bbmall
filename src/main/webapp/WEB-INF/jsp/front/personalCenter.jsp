<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="top2.jsp"></jsp:include>
<link href="${SHOPDOMAIN}/front/css/web/share.css" rel="stylesheet"
	type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/Personal.css" rel="stylesheet"
	type="text/css">
<script src="${SHOPDOMAIN}/front/js/web/jscarousel.js"
	type="text/javascript"></script>
<script src="${SHOPDOMAIN}/front/js/web/ScrollPic.js"
	type="text/javascript"></script>
<script src="${SHOPDOMAIN}/front/js/web/jquery.SuperSlide.2.1.1.js"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/jquery.cookie.js"></script>
<style>
#FS_Cont_01 {	width: 800px;}

.per_return {	padding: 20px 5px;}

.picScroll-left .hd .next {
	background: url(../front/images/web/per_right.png) no-repeat;
}

.picScroll-left .hd .prev {
	background: url(../front/images/web/per_left.png) no-repeat;
}
</style>
<script id="interpolationtmpl" type="text/x-dot-template">
{{for(var i=0;i<it.list.length ;i++){ }}
{{var uslog=it.list[i];}}
	

	{{ if(j==uslog.nextlist.length-1) {}}
		<tr class="per_return_btm">
	{{} else{}}
		<tr>
	{{}}}
	<td width="40%">
	{{var j=0;
		for(j=0;j<uslog.nextlist.length;j++){ }}
		
		{{var ord=uslog.nextlist[j]; if(j<3){}}
		<a href="myorder_details.html?id={{=uslog.id}}" style="margin-left:5px"><img src="${USERIMGSRC}{{=imgZuhe(ord.prod_uri,'_300')}}" width="100" height="100" alt="" /></a>
	{{}} if(j>3){}}
		....
	{{}}} 
	</td>
	<td width="8%">{{=(uslog.order_Account).toFixed(2)}}元</td> 
		<td width="15%">
			{{ if(uslog.status=="0") {}}用户取消{{}}}
			{{ if(uslog.status=="11") {}}未付款{{}}}
			{{ if(uslog.status=="20") {}}
				已付款
			{{}}} 
			{{ if(uslog.status=="22") {}}
				商家确认 
			{{}}}
			{{ if(uslog.status=="30") {}}
				卖家已发货
			{{}}}
			{{ if(uslog.status=="40") {}}
				已成交
			{{}}}
			{{ if(uslog.status=="50") {}}
				申请退款
			{{}}}
			{{ if(uslog.status=="60") {}}
				退款成功 
			{{}}}
			{{ if(uslog.status=="100") {}}
				系统自动取消
			{{}}} 
			</td>
			<td width="20%">{{=uslog.addtime}}</td>
				{{ if(uslog.status=="0") {}}
					<td width="10%"><a href="myorder_details.html?id={{=uslog.id}}">查看</a></td>
				{{}}}
				{{ if(uslog.status=="11") {}}
					<td width="10%"><a href="myorder_details.html?id={{=uslog.id}}">查看</a></td>
				{{}}}
				{{ if(uslog.status=="20") {}}
					<td width="10%"><a href="myorder_details.html?id={{=uslog.id}}">查看</a></td>
				{{}}} 
				{{ if(uslog.status=="22") {}}
					<td width="10%"><a href="myorder_details.html?id={{=uslog.id}}">查看</a></td>
				{{}}}
				{{ if(uslog.status=="30") {}}
					<td width="10%"><a href="myorder_details.html?id={{=uslog.id}}">查看</a></td>
				{{}}}
				{{ if(uslog.status=="40") {}}
					<td width="10%"><a href="myorder_details.html?id={{=uslog.id}}">查看</a></td>
				{{}}}
				{{ if(uslog.status=="50") {}}
					<td width="10%"><a href="refundmanagement_applyinfor.html?ords={{=uslog.id}}">查看</a></td>
				{{}}}
				{{ if(uslog.status=="60") {}}
					<td width="10%"><a href="refundmanagement_applyinfor.html?ords={{=uslog.id}}">查看</a></td>
				{{}}}
				{{ if(uslog.status=="100") {}}
					<td width="10%"><a href="myorder_details.html?id={{=uslog.id}}">查看</a></td>
				{{}}} 
	</tr>
     	 

{{}}}
</script>


<script id="interpolationtmpls" type="text/x-dot-template">
{{for(var i=0;i<it.list.length;i++){ }}
{{var uslog=it.list[i];}}
	<li>
		<div class="pic">
			<a href="productDetail.html?prodId={{=uslog.proId}}"><IMG src="${USERIMGSRC}{{=imgZuhe(uslog.picuri,'_300')}} ">
				</a>
		</div>
		<div class="title" id="per_collect_{{=uslog.proId}}" prods="{{=uslog.proId}}" companyId="{{=uslog.company_id}}" specIds="{{=uslog.specid}}">
			<p class="titleP">{{=uslog.name}}</p>
			<div class="titleDiv">
			<span class="titleDivSpan">{{=(uslog.price).toFixed(2)}}元</span>
			<button class="titleDivBtn"
				onclick="addtoCart({{=uslog.proId}})">加入购物车</button>
			</div>
		</div>
	</li>
{{}}}
</script>


<div class="per_main">
	<jsp:include page="centerLeft.jsp"></jsp:include>
	<!--Personal center right-->
	<div class="per_mainright flW">
		<!--Personal center step-->
		<div class="per_step">
			<div class="per_header flW">
				<a href="personadata.html"><img
					src="${USERIMGSRC}${sessionFrontUser.picUrl }" /></a>
			</div>
			<div class="per_steptxt flW">
				<h3>电商平台BABY</h3>
				<p>
					优惠券：<a href="mycoupons.html">&nbsp;<span id="cousys"></span></a>
				</p>
			</div>
			<div class="per_stepright flW">
				<ul>
					<a href="myorder.html"><li><img
							src="${SHOPDOMAIN}/front/images/web/per_all.png" />
							<h3>全部订单</h3></li></a>
					<a href="myorder.html?ids=1"><li><img
							src="${SHOPDOMAIN}/front/images/web/per_Pay.png" />
							<h3>
								待付款&nbsp;<span id="pendpay"></span>
							</h3></li></a>
					<a href="myorder.html?ids=2"><li><img
							src="${SHOPDOMAIN}/front/images/web/per_receive.png" />
							<h3>
								待收货&nbsp;<span id="inbound"></span>
							</h3></li></a>
					<a href="myorder.html?ids=3"><li><img
							src="${SHOPDOMAIN}/front/images/web/per_finish.png" />
							<h3>
								已完成&nbsp;<span id="completed"></span>
							</h3></li></a>
					<a href="myorder.html?ids=4"><li><img
							src="${SHOPDOMAIN}/front/images/web/per_evaluate.png" />
							<h3>
								待评价&nbsp;<span id="eva"></span>
							</h3></li></a>

				</ul>
			</div>

		</div>
		<!--Personal center myorder-->
		<div class="per_myorder">
			<div class="per_myorder_title">
				我的订单
				<div class="per_myorder_more frW">
					<a href="myorder.html?ids=0">查看全部</a>
				</div>
			</div>
			<div class=" per_myorder_con">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tbody id="showpaymentList">
					</tbody>
				</table>
				<div class="per_noorder" id="five" style="display:none">
					<div class=" per_ per_noorderleft flW">
						<img src="${SHOPDOMAIN}/front/images/web/per_noorder.png"
							width="104" height="48" alt="" />
					</div>
					<div class=" per_noorderright flW">
						<ul>
							<li>SORRY~您没有相关订单哦~</li>
							<li>可以去看看有哪些想买的</li>
							<li><a href="index.html"><input type="button"
									name="submi" id="submi" class="per_evaluate_btn" value="随便逛逛" /></a></li>
						</ul>
					</div>
					<div class="fox"></div>
				</div>

			</div>

		</div>
		<div class="per_collect">
			<div class="per_collect_title">
				我的收藏

				<div class="per_myorder_more frW">
					<a href="mycollection.html">查看全部</a>
				</div>
			</div>
			<div class="per_noorder" id="sixs" style="display:none">
				<div class=" per_ per_noorderleft flW">
					<img src="${SHOPDOMAIN}/front/images/web/per_noorder.png"
						width="104" height="48" alt="" />
				</div>
				<div class=" per_noorderright flW">
					<ul>
						<li>SORRY~您没有相关收藏哦~</li>
						<li>可以去看看有哪些想买的</li>
						<li><a href="index.html"><input type="button"
								name="submi" id="submi" class="per_evaluate_btn" value="随便逛逛" /></a></li>
					</ul>
				</div>
			</div>

			<div class="picScroll-left">
				<div class="hd" style="float: left">
					<a class="prev"></a>
				</div>
				
				<div class="bd" style="float: left">
				
					<ul class="picList">
						
					</ul>
				</div>
				<div class="hd" style="float: left">
					<a class="next"></a>
				</div>
			</div>
			<div class="fox"></div>
		</div>
		<div class="fox"></div>
	</div>
	<div class="fox"></div>
</div>
<jsp:include page="footShop.jsp"></jsp:include>
<jsp:include page="foot-validate.jsp"></jsp:include>
<script>
	document.title="个人中心";
	function addtoCart(prods) {
		var companyId = $("#per_collect_" + prods).attr("companyId");
		var specIds = $("#per_collect_" + prods).attr("specIds");
		if (prods != "") {
			isAbolveStock(prods, companyId, 1, specIds);
		} else {
			showms("请选择商品后再操作");
		}
	}
	function isAbolveStock(prodIds, compayId, counts, specIds) {
		compayId = $.cookie('sys_base_companyId');
		$.post(SHOPDOMAIN + '/wap/shopCart/addCart.html', {
			"prodId" : prodIds,
			"companyId" : compayId,
			"count" : counts,
			"specId" : specIds
		}, function(data) {
			if (data.res_code == '0') {
				showm(data.message);
				cartCount();
			} else {
				showm(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	

	}
	jQuery(document).ready(
			function() {
				getall();
				$.post(
						SHOPDOMAIN + '/interfaces/orderSele/orderperson.html',
						function(data) {
							if (data.res_code == '0') {
								$("#pendpay").html(data.countone);
								$("#inbound").html(data.counttwo);
								$("#eva").html(data.countEva);
								$("#completed").html(data.countthr);
								$("#cousys").html(data.cousys);
								var evalText = doT.template($(
										"#interpolationtmpl").text());
								if (data.list[0].nextlist == null) {
									$(".showpaymentList")
											.css('display', 'none');
									$("#five").css('display', 'block');
								} else {
									$("#showpaymentList").html(evalText(data));
								}
								if (data.list == "") {
									$("#five").css('display', 'block');
								}
							} else {
								showm("您还没有登录!");
								window.setTimeout(
										(window.location.href = "index.html"),
										10000);
							}
						}, "json").error(function() {
					showError();
				});
			});

	//  获取列表       

	function getall() {
		$.post(
				SHOPDOMAIN + '/interfaces/userCollection/colleListall.html',
				function(data) {
					if (data.res_code == '0') {
						var evalText = doT.template($("#interpolationtmpls")
								.html());
						if (data.list.length == 0) {
							$("#sixs").css('display', 'block');
							$(".arr_left").css('display', 'none');
							$(".arr_right").css('display', 'none');
							$('.hd').hide();
						} else {
							$(".picList").html(evalText(data));
							
							jQuery(".picScroll-left").slide({
								titCell : ".hd ul",
								mainCell : ".bd ul",
								autoPage : true,
								effect : "left",
								autoPlay : true,
								vis : 3,
								trigger : "click",
								interTime : 4000
							});
							if ($('.picList li').length <= 3) {
								$('.hd').hide();
								$('.picScroll-left').css("margin-left", '90px')
							}
						}
					}
				}, "json").error(function() {
			showError();
		});

	}
</script>
