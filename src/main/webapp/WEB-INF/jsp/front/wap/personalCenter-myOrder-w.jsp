<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter-myOrder-w.css" />
<style>
body {
	padding-bottom: 73px;
}
</style>
<script id="interpolationtmpl" type="text/x-dot-template">
{{for(var i=0;i<it.list.length ;i++){ }}
{{var uslog=it.list[i];}}

     {{ if(uslog.status =="11") {}}
   <a href="personalCenter-orderDetails.html?id={{=uslog.id}}">
 <div class="w-orderList">
			<div class="w-orderList-title">
				<span class="fl"> {{=uslog.companyName}}</span>
			</div>
   {{ }}}

{{ if(uslog.status=="20"||uslog.status=="22"|| uslog.status=="30") {}}
  <div class="w-orderList" onclick="javascript:window.location.href='personalCenter-orderDetails.html?id={{=uslog.id}}'" >
	<div class="w-orderList-title">
		<span class="fl">{{=uslog.companyName}}</span>
              {{ if(uslog.status=="20") {}}
				<span class="fr"> 已付款</span>
                {{}}} 
			    {{ if(uslog.status=="22") {}}
                      <span class="fr">  商家确认 </span>
			    {{}}}
                {{ if(uslog.status=="30") {}}
                   <span class="fr">  卖家已发货</span>
			      {{}}}
	</div>
{{ }}}


{{ if(uslog.status=="40"&&it.choose!=4) {}}
 <div class="w-orderList" onclick="javascript:window.location.href='personalCenter-orderDetails.html?id={{=uslog.id}}'">
	<div class="w-orderList-title">
		<span class="fl">{{=uslog.companyName}}</span> 
          <span class="fr"> 已成交</span>
	</div>
{{}}} 

  {{ if(uslog.status =="0"||uslog.status =="100") {}}
   <div class="w-orderList" onclick="javascript:window.location.href='personalCenter-orderDetails.html?id={{=uslog.id}}'">
	 <div class="w-orderList-title">
		<span class="fl">{{=uslog.companyName}}</span>
				<span class="fr">
                 {{ if(uslog.status=="0") {}}
			          	      用户取消 
			     {{}}}
                 {{ if(uslog.status=="100") {}}
			          	 系统自动取消
			     {{}}}
                     </span>
	</div>
   {{ }}}

     {{for(var j=0;j<uslog.nextlist.length;j++){ }}
         {{var ord=uslog.nextlist[j];}}

          {{ if(uslog.status =="0"||uslog.status =="100") {}}
           <div class="w-orderList-content">
		<img src="${USERIMGSRC}{{=imgZuhe(ord.prod_uri,'_300')}}" alt="" /> 
         <span> 
            <em class="ellipsis">{{=ord.prod_name}}</em><br /> 
            <em class="w-specifications">规格：{{=ord.prod_spec_name}}</em><br />
             <em> <i class="w-totalPrice">￥{{=(ord.prod_price).toFixed(2)}}</i> <i class="fr">x{{=ord.prod_count}}</i>
		</span>
	</div>
         {{ }}}
        {{ if(uslog.status =="11") {}}
			<div class="w-orderList-content">
				<img src="${USERIMGSRC}{{=imgZuhe(ord.prod_uri,'_300')}}" alt="" /> 
               <span> 
                    <em class="ellipsis">{{=ord.prod_name}}</em><br />
                    <em class="w-specifications">规格：{{=ord.prod_spec_name}}</em><br />
                    <em> <i class="w-totalPrice">￥{{=(ord.prod_price).toFixed(2)}}</i> <i class="fr">x{{=ord.prod_count}}</i>
				</span>
			</div>
       {{ }}}
 {{ if(uslog.status=="20"||uslog.status=="22"|| uslog.status=="30") {}}
	<div class="w-orderList-content">
		<img src="${USERIMGSRC}{{=imgZuhe(ord.prod_uri,'_300')}}" alt="" /> 
         <span> 
            <em class="ellipsis">{{=ord.prod_name}}</em><br /> 
            <em class="w-specifications">规格：{{=ord.prod_spec_name}}</em><br />
             <em> <i class="w-totalPrice">￥{{=(ord.prod_price).toFixed(2)}}</i> <i class="fr">x{{=ord.prod_count}}</i>
		</span>
	</div>

{{ }}}
{{ if(uslog.status=="40" && ord.location_status==0&&it.choose==4) {}} 
<div class="w-orderList" onclick="javascript:window.location.href='productShow.html?prodId={{=ord.prod_id}} '">
	<div class="w-orderList-content" style="position:relative">
		<img src="${USERIMGSRC}{{=imgZuhe(ord.prod_uri,'_300')}}" alt="" /> 
        <span> 
           <em class="ellipsis"> {{=ord.prod_name}}</em><br /> 
           <em class="w-specifications">规格：{{=ord.prod_spec_name}}</em><br /> 
           <em> <i class="w-totalPrice">￥{{=(ord.prod_price).toFixed(2)}}</i> <i class="fr">x{{=ord.prod_count}}</i>
		</span>
		<div class="w-orderList-operation-button" style="position:absolute;bottom:0px;right:37px;">
			<a href="personalCenter-Pevaluatin.html?ords={{=ord.odetId}}"><button class="w-btnOk">评&nbsp;&nbsp;价</button></a>
		</div>
	</div>
<div>
{{ }}}
 {{ if(uslog.status=="40" &&it.choose!=4) {}}

	<div class="w-orderList-content">
		<img src="${USERIMGSRC}{{=imgZuhe(ord.prod_uri,'_300')}}" alt="" /> 
        <span> 
           <em class="ellipsis"> {{=ord.prod_name}}</em><br /> 
           <em class="w-specifications">规格：{{=ord.prod_spec_name}}</em><br />
           <em> <i class="w-totalPrice">￥{{=(ord.prod_price).toFixed(2)}}</i> <i class="fr">x{{=ord.prod_count}}</i> 
		</span>
	</div>
{{}}} 
   
{{}}}
 
       {{ if(uslog.status =="0"||uslog.status =="100") {}}
<span class="fl w-realPay" style=" padding-left: 37px;padding-top:13px;" >合计：<i style="color: #ff0000">￥{{=(uslog.order_Account).toFixed(2)}}</i></span>
    <div class="w-orderList-operation">
		<div class="w-orderList-operation-button">
			<span class="refound" refoundId={{=uslog.id}}></span>
			 <button class="w-btnOk" onclick="javascript:window.location.href='personalCenter-orderDetails.html?id={{=uslog.id}}'"> 查看详情</button> 
		</div>
	</div>
</div>
        {{}}} 
   {{ if(uslog.status =="11") {}}
</a>
<span class="fl w-realPay" style=" padding-left: 37px;padding-top:13px;" >合计：<i style="color: #ff0000">￥{{=(uslog.order_Account).toFixed(2)}}</i></span>
 <div class="w-orderList-operation">
				<div class="w-orderList-operation-button">
					<button class="w-btnCancle cancelOrder">取消订单</button>
                  <button class="w-btnOk" onclick="javascript:window.location.href=' personalCenter-orderDetails.html?id={{=uslog.id}}'"> 去付款</button> 
                   <span class="fss" locationId={{=uslog.id}}></span>
				</div>
			</div>
         </div>
</div>
{{}}} 

 {{ if(uslog.status=="20"||uslog.status=="22"|| uslog.status=="30") {}}
<span class="fl w-realPay" style=" padding-left: 37px;padding-top:13px;" >合计：<i style="color: #ff0000">￥{{=(uslog.order_Account).toFixed(2)}}</i></span>
       <div class="w-orderList-operation">
		<div class="w-orderList-operation-button">
			<span class="refound" refoundId={{=uslog.id}}></span>
			 <button class="w-btnOk" onclick="javascript:window.location.href='personalCenter-orderDetails.html?id={{=uslog.id}}'"> 查看详情</button> 
		</div>
	</div>
</div>
{{}}}

{{ if(uslog.status=="40"&&it.choose!=4 ) {}}
<span class="fl w-realPay" style=" padding-left: 37px;padding-top:13px;" >合计：<i style="color: #ff0000">￥{{=(uslog.order_Account).toFixed(2)}}</i></span>
<div class="w-orderList-operation">
		<div class="w-orderList-operation-button">
			<span class="refound" refoundId={{=uslog.id}}></span>
			 <button class="w-btnOk" onclick="javascript:window.location.href='personalCenter-orderDetails.html?id={{=uslog.id}}'"> 查看详情</button> 
		</div>
	</div>
	</div>
{{}}} 

{{}}}
 
</script>

<div class="w-main  ">
	<div class="w-content">
		<!--全部的-->

		<div class="w-orderStatusNav ">

			<div id="showpaymentList"></div>
		</div>
		<div class="w-noOrder" id="one" style="display:none">
			<img src="${SHOPDOMAIN}/front/images/wap/hasnoOrder.png" alt="" />

			<p class="w-prompt">您还没有相关订单</p>

			<p class="w-guide">可以去看看有哪些想买的</p>
			<button class="w-randomBtn" onclick="window.location.href='index.html'">随便逛逛</button>
		</div>
	</div>
	<!--待付款-->



	<div class="w-orderStatusNav">

		<div id="showpaymentListPay"></div>
	</div>
	<div class="w-noOrder" id="two" style="display:none">
		<img src="${SHOPDOMAIN}/front/images/wap/hasnoOrder.png" alt="" />

		<p class="w-prompt">您还没有相关订单</p>

		<p class="w-guide">可以去看看有哪些想买的</p>
		<button class="w-randomBtn" onclick="window.location.href='index.html'">随便逛逛</button>
	</div>
</div>
<!--待收货-->

<div class="w-orderStatusNav w-main">


	<div id="showpaymentListRecev"></div>
</div>
<div class="w-noOrder" id="thr" style="display:none">
	<img src="${SHOPDOMAIN}/front/images/wap/hasnoOrder.png" alt="" />

	<p class="w-prompt">您还没有相关订单</p>

	<p class="w-guide">可以去看看有哪些想买的</p>
	<button class="w-randomBtn" onclick="window.location.href='index.html'">随便逛逛</button>
</div>
</div>

<!--已完成-->

<div class="w-orderStatusNav  w-main">

	<div id="showpaymentListComp"></div>
</div>
<div style="display:none" class="w-noOrder" id="four">
	<img src="${SHOPDOMAIN}/front/images/wap/hasnoOrder.png" alt="" />

	<p class="w-prompt">您还没有相关订单</p>

	<p class="w-guide">可以去看看有哪些想买的</p>
	<button class="w-randomBtn" onclick="window.location.href='index.html'">随便逛逛</button>
</div>
<div class="page_and_btn"></div>
</div>

<!--待评价-->
<div class="w-orderStatusNav w-main">


	<div id="showpaymentListEva"></div>
</div>
<div class="w-noOrder" id="fiv" style="display:none">
	<img src="${SHOPDOMAIN}/front/images/wap/hasnoOrder.png" alt="" />

	<p class="w-prompt">暂无待评价商品</p>

	<p class="w-guide">可以去看看有哪些想买的</p>
	<button class="w-randomBtn" onclick="window.location.href='index.html'">随便逛逛</button>
</div>
<div class="couponBtn1 textCenter1  w-fontsize301 w-color91  jiazaione" display="block">
	<div class="loadingNowDiv">
		<div class="spinner">
			<div class="spinner-container container1">
				<div class="circle1"></div>
				<div class="circle2"></div>
				<div class="circle3"></div>
				<div class="circle4"></div>
			</div>
			<div class="spinner-container container2">
				<div class="circle1"></div>
				<div class="circle2"></div>
				<div class="circle3"></div>
				<div class="circle4"></div>
			</div>
			<div class="spinner-container container3">
				<div class="circle1"></div>
				<div class="circle2"></div>
				<div class="circle3"></div>
				<div class="circle4"></div>
			</div>
		</div>
		<span class="loadingNow">&nbsp;正在加载中...</span>
	</div>
</div>
<div class="list" style="display:none"><center>—— 没有更多了 ——</center></div>
</div>

<script src="${SHOPDOMAIN}/front/js/wap/jquery.hDialog.js"></script>
<script>
	document.title = "我的订单";
	show1();
	//    tab切换
	$('.w-orderStatus-nav>ul>div').click(function() {

		$(this).addClass('active').siblings().removeClass('active');

		$('.w-content>.w-orderStatusNav').eq($(this).index()).show().siblings().hide();
		var thisIndex = $(this).index();

		window.location.href = 'personalCenter-myOrder-w.html?url=' + thisIndex;
		//                  alert('a')

	});
	function show1() {

		//              alert('b')
		var location = window.location.href;
		var str = location + "";
		var arr = str.split('?url=');
		var num = (arr[1]) * 1;
		switch (num) {
		case 1:
			document.title = "待付款订单";
			break;
		case 2:
			document.title = "待收货订单"
			break;
		case 3:
			document.title = "已完成订单";
			break;
		case 4:
			document.title = "待评价订单";
			break;
		case 0:
			document.title = "全部订单";
			break;
		default:
			document.title = "暂无";
		}

		//var oLis = document.getElementById("ulbody").getElementsByTagName("div");

		//oLis[num].className = "active";

		$('.w-orderStatusNav')[num].style.display = "block";

	}

	//    取消订单确认
	$(document).on('click', '.cancelOrder', function(e) {
		e.preventDefault();
		$.dialog('confirm', '', "确定要取消该订单吗？", 0, function() {
			$.closeDialog();
			$.tooltipDeleteCenter('正在取消...', 4000, true, function() {
				var id = $(".fss").attr("locationId");
				deleteOrd(id);
			});
		});

	});
	//    评价
	$(document).on('click', '#w-operation', function(e) {
		e.preventDefault();
		window.location.href = 'personalcenter-pevaluatin-w.html';
	});

	//    退款
	/* $(document).on('click', '#w-refund', function(e) {
		e.preventDefault();
		window.location.href = 'personalCenter-refundApplication-w.html';
	}); */
	//    确认收货
	$(document).on('click', '.w-confirmGoodsBtn', function(e) {
		e.preventDefault();
		$.dialog('confirm', '', "是否确认收货？", 0, function() {
			$.closeDialog();

		});
	});
	//    回到顶部
	$.goTop();
</script>
<jsp:include page="foot-validate.jsp"></jsp:include>
<script>
	var datas = {};
	var currentNum = 1;
	//var totalPage = 0;
	jQuery(document).ready(function() {
		//获取我的订单列表
		FormValidation.init();
	});
	$(function() {
		currentNum = 1;
		getList("${SHOPDOMAIN}/interfaces/orderSele/orderList.html", datas, currentNum, true);
		scroll(0, 0);
	});
	$(window).scroll(function() {
		var scrollTop = $(this).scrollTop();
		var scrollHeight = $(document).height();
		var windowHeight = $(this).height();
		if (scrollTop + windowHeight == scrollHeight) {
			currentNum++;
			getList("${SHOPDOMAIN}/interfaces/orderSele/orderList.html", datas, currentNum, true);
		}
	});
	function getList(url, datas, num, isAppend) {
		cho = getRequest("url");
		$.ajax({
			url : url,
			type : "post",
			data : {
				"cho" : cho,
				"currentPage" : num
			},
			dataType : "json",
			success : function(data) {
				//待付款
				//var result = JSON.parse(result);
			
				var totalNum = parseInt(data.pageCount);
				if(totalNum<num){
					$(".list").hide();
					$(".jiazaione").show(); 
				}
				if(totalNum==num&&totalNum!=0){
					$(".list").show();
					$(".jiazaione").hide();
				}
				//totalPage = totalNum;
				var evalText = doT.template($("#interpolationtmpl").html());
				if (data.pageCount == 0) {
					$(".jiazaione").hide();
					//$(".w-noOrder").css('display', 'block');
					if (data.choose == '0') {
						$("#one").css('display', 'block');
					} else if (data.choose == '1') {
						$("#two").css('display', 'block');
					} else if (data.choose == '2') {
						$("#thr").css('display', 'block');
					} else if (data.choose == '3') {
						$("#four").css('display', 'block');
					} else if (data.choose == '4') {
						$("#fiv").css('display', 'block');
					}$(".list").css('display', 'block');
				}
				if (num <= totalNum) {
					//$("#showUslogList").html(evalText(data.list));
					if (data.choose == '0') {
						if (data.pageCount == 0) {
							$("#one").css('display', 'block');
						}
						$("#showpaymentList").append(evalText(data));
					} else if (data.choose == '1') {
						if (data.pageCount == 0) {
							$("#two").css('display', 'block');
						}
						$("#showpaymentListPay").append(evalText(data));
					} else if (data.choose == '2') {
						if (data.pageCount == 0) {
							$("#thr").css('display', 'block');
						}
						$("#showpaymentListRecev").append(evalText(data));
					} else if (data.choose == '3') {
						if (data.pageCount == 0) {
							$("#four").css('display', 'block');
						}
						$("#showpaymentListComp").append(evalText(data));
					} else if (data.choose == '4') {
						if (data.shaixuan == 0) {
							currentNum = 0;
							$("#fiv").css('display', 'block');
						}
						$("#showpaymentListEva").append(evalText(data));
					}
					//alert($("#showpaymentList").html());
				} else {
					num = totalNum;
					$(".jiazaione").hide();
					$(".list").show();
					if(num==0){
						$(".jiazaione").hide();
						$(".list").hide();
					}
					if (!isAppend) {
						if (data.choose == '0') {
							$("#showpaymentList").empty();
						} else if (data.choose == '1') {
							$("#showpaymentListPay").empty();
						} else if (data.choose == '2') {
							$("#showpaymentListRecev").empty();
						} else if (data.choose == '3') {
							$("#showpaymentListComp").empty();
						} else if (data.choose == '4') {
							$("#showpaymentListEva").empty();
						}
					}
				}
				//<!--待收货-->
			}
		});
	}

	function deleteOrd(id) {
		$.post(SHOPDOMAIN + '/interfaces/orderSele/deleteOrders.html', {
			id : id
		}, function(data) {
			if (data.res_code == '0') {
				showMessageRefresh(data.message);
			} else {
				showMessageRefresh(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}
</script>