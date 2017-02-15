<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="top2.jsp"></jsp:include>
<!DOCTYPE html>
<head lang="en">
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>电商平台领券中心</title>
    <!--全局初始化-->
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/productList.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/Personal.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/cmdCommon.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/login.css" />
</head>
<style>
body {     background-color: #FFFFFF ;    font-family: "Microsoft YaHei","΢���ź�";    font-size: 14px;}
.couponsListUlBtnhui {    background: #AAAAAA none repeat scroll 0 0;     border-radius: 5px;    color: #ffffff;    cursor: pointer;    font-size: 18px;    height: 40px;    line-height: 40px;    margin: 10px auto 20px;    text-align: center;    width: 150px;    }
</style>
<!--头部-->
<div id="mainW">
	<div class="wrapW" >
		<div class="couponsBan">
			<img src="${SHOPDOMAIN}/front/images/web/couponsBan.jpg" height="100"
				alt="" />
		</div>
		<div class="couponsList couponsListUl12 ">
			<ul class="couponsListUl">
			</ul>
		</div>
	</div>
</div>
<jsp:include page="footShop.jsp"></jsp:include>    
<script id="couponCenter" type="text/x-dot-template">
<ul class="couponsListUl">
{{ if(it.list&&it.list.length>0){  }}
	{{ for(var i=0; i<it.list.length;i++){ }}
    {{var obj=it.list[i];}}
		{{var use=obj.userId;}}
			{{if(use=="undefined" || obj.userId==null||obj.userId==0){}}
                <li class="couponsListUlLi">
                    <div class="couponsListUlDiv">
                        <div  class="couponsListPrice">￥{{=obj.substractPrice}}</div>
           				{{if(obj.needPrice==0){}}
							<div class="couponsListDetails">【不限额优惠券】</div>
						{{}else{}}
                        	<div class="couponsListDetails">【满减：满{{=obj.needPrice}}减{{=obj.substractPrice}}】</div>
						{{}}}
                        <div class="couponsListTime">{{=obj.startTimeS}}-{{=obj.endTimeS}}</div>
                    	</div>
                    	<div class="couponsListUlBtn" id="{{=obj.id}}" href="javascript:void(0)" onclick='editProduct({{=obj.id}})'>
                        	立即领取
                    </div>
                </li>
			{{}}}
		{{}}}
	{{}}}
{{ if(it.list&&it.list.length>0){  }}
	{{ for(var i=0; i<it.list.length;i++){ }}
	{{var obj=it.list[i];}}
		{{var use=obj.userId;}}
		{{if(!(use=="undefined" || obj.userId==null||obj.userId==0)){}}
			<li class="couponsListUlLi">
				<div class="couponsListUlDiv">
					<div  class="couponsListPrice">￥{{=obj.substractPrice}}</div>
					{{if(obj.needPrice==0){}}
						<div class="couponsListDetails">【不限额优惠券】</div>
					{{}else{}}
                        <div class="couponsListDetails">【满减：满{{=obj.needPrice}}减{{=obj.substractPrice}}】</div>
					{{}}}
                        <div class="couponsListTime">{{=obj.startTimeS}}-{{=obj.endTimeS}}</div>
                    </div>
                    <div class="couponsListUlBtnhui"  href="javascript:void(0)">
						已领取
                    </div>
                </li>
			{{}}}  
		{{}}}
	{{}}}
{{ if(it.list.length==0){  }}
<div class="per_noorder" id="five" style="display:block">
					<div class=" per_ per_noorderleft flW">
						<img src="${SHOPDOMAIN}/front/images/web/per_noorder.png"
							width="104" height="48" alt="" />
					</div>
					<div class=" per_noorderright flW">
						<ul>
							<li>~哎呀，优惠券都被抢光了~</li>
							<li>先去其他地方逛逛吧！</li>
							<li><a href="index.html"><input type="button"
									name="submi" id="submi" class="per_evaluate_btn" value="随便逛逛" /></a></li>
						</ul>
					</div>
					<div class="fox"></div>
				</div>
{{}}}
</ul>
<div class="page_and_btn per_order_bgcolor"  >
		<div></div>{{=it.pageStr}}
</div>
<div style="clear:both"></div>
</script>
<script>
document.title="电商平台     领券中心"
$("#lingquan").html("领券中心");
$(function() {
	getlist(1);	
	$(".page_and_btn ul li span").css("background-color", "#e70012");
	$(".page_and_btn ul li span").css("color", "#fff");
});
//获取所有优惠券信息
var data={};
function getlist(num) {
	$.ajax({
		url:'${SHOPDOMAIN}/interfaces/Coup/sysCoupons.html',
		type:"post",
		data: {
            "currentPage" :num
        },
		dataType:"json",
		async:false,
		beforeSend:function(){
		},
		success:function(result){
		if (result.res_code == '0') {
			var totalNum = parseInt(result.pageCount);
			totalPage=totalNum;
			var productList = doT.template($("#couponCenter").html())(result);
			$(".couponsListUl12").empty();
			$(".couponsListUl12").append(productList);
			$(".page_and_btn ul li span").css("background-color", "#e70012");
			$(".page_and_btn ul li span").css("color", "#fff");
		}else {
			showMessageRefresh(result.message);
			} 
		}});
		}
	//记录领优惠券信息
	function editProduct(id) {
		$.ajax({
			url : '${SHOPDOMAIN}/interfaces/Coup/sysCouponsLing.html',
			type : "post",
			dataType : "json",
			sync : false,
			data : {
				"id" : id
			},
			success : function(data) {
				if (data.res_code == '1') {
					loginP("couponCenter.html");
				} else if (data.res_code == '0') {
					showMessage(data.message);
					$("#"+id).removeClass("couponsListUlBtn");
					//已经领取
					$("#"+id).addClass("couponsListUlBtnhui");
					$("#"+id).html("已领取");
					
				}else{
					showMessage(data.message);
					}
				}
		});
	}
	$("#subBtn").click(function() {
		//让button无法再次点击
		$(this).attr("disabled", "disabled");
		//执行其它代码，比如提交事件等
		myFunc();
	});

	//防止重复点击多次相应的问题
	var _timer = {};
	function delay_till_last(id, fn, wait) {
		if (_timer[id]) {
			window.clearTimeout(_timer[id]);
			delete _timer[id];
		}
		return _timer[id] = window.setTimeout(function() {
			fn();
			delete _timer[id];
		}, wait);
	}
</script>
