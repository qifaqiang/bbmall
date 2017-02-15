<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<script> document.title="领券中心"; </script>
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/front/css/wap/part-page.css">
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/front/css/wap/couponCenter.css">
<body>
<style>
.couponBtn1 { line-height: 70px;}
.textCenter1 { text-align: center;}
.w-color91 { color: #999;}
.w-fontsize301 { font-size: 30px;}
</style>
<div class="w-main"  style="padding-bottom:73px;">
    <!--头部-->
    <div  id="zidingyi">
	  	<div class="couponBtn1 textCenter1  w-fontsize301 w-color91  jiazai"  display="block">
	                 <div class="loadingNowDiv"> <div class="spinner"> <div class="spinner-container container1"> <div class="circle1"></div> <div class="circle2"></div> <div class="circle3"></div> <div class="circle4"></div> </div> <div class="spinner-container container2"> <div class="circle1"></div> <div class="circle2"></div> <div class="circle3"></div> <div class="circle4"></div> </div> <div class="spinner-container container3"> <div class="circle1"></div> <div class="circle2"></div> <div class="circle3"></div> <div class="circle4"></div> </div> </div> <span class="loadingNow">&nbsp;正在加载中...</span></div>
	    </div>
	</div>
</div>
</body>
<jsp:include page="foot.jsp"></jsp:include> 

<script id="couponCenter" type="text/x-dot-template">
{{ if(it.list&&it.list.length>0){  }}
	{{ for(var i=0; i<it.list.length;i++){ }}
		{{var obj=it.list[i];}}
			{{var use=obj.userId;}}
				{{if(use=="undefined" || obj.userId==null||obj.userId==0){}}
					<div class="w-marginLR10 w-marginTop13 w-couponlist w-couponlist-unReceive" >
					<div class="fl  w-couponCenterListTitle" >
						<div class="w-amount w-amount-effective fl">
						￥{{=obj.substractPrice}}
						</div>
						<div class="w-marginL190">
							<div class="w-coupons-title w-fontsize24">
								{{if(obj.needPrice==0){}}
									<div class="">不限额使用</div>
								{{}else{}}
									<div class="">满{{=obj.needPrice}}减{{=obj.substractPrice}}</div>
								{{}}}
							</div>
						<div class="w-coupons-time w-color9 w-fontsize20">
							有效期：{{=obj.startTimeS}}-{{=obj.endTimeS}}
						</div>
					</div>
				</div>
				<div class="fr receiveBtn" href="javascript:void(0)" onclick='editProduct({{=obj.id}})'></div>
			</div>
        {{}else{}}
			<div class="w-marginLR10 w-marginTop13 w-couponlist w-couponlist-Received" >
        	<div class="fl  w-couponCenterListTitle" >
            <div class="w-amount w-amount-effective fl">
                	￥{{=obj.substractPrice}}
            </div>
            <div class="w-marginL190">
                <div class="w-coupons-title w-fontsize24">

                   {{if(obj.needPrice==0){}}
                     <div class="">不限额使用</div>
                      {{}else{}}
                    <div class="">满{{=obj.needPrice}}减{{=obj.substractPrice}}</div>
                    {{}}}

                </div>
                <div class="w-coupons-time w-color9 w-fontsize20">
                                                                                             有效期：{{=obj.startTimeS}}-{{=obj.endTimeS}}
                </div>
            </div>
        </div>
        <div class="fr receiveBtn"></div>
    </div>
	{{}}}
{{}}}
{{}}}
</script>
<script>
document.title="领券中心";
var canGetList=true;
	$(function() {
		currentNum=1;
		product(data,currentNum);		
		scroll(0,0);
	});

	//获取所有优惠券信息
	 var data={};
	 var currentNum=0;
	 var totalPage=0;
	function product(data,num) {
		data["currentPage"]=num;
		$.ajax({
			url:SHOPDOMAIN+"/interfaces/Coup/sysCoupons.html",
			type:"post",
			data: {
                "currentPage" :num
            },
			dataType:"json",
			async:false,
			beforeSend:function(){
				$(".jiazai").show();
			},
			success:function(result){
			if (result.res_code == '0') {
				$(".jiazai").hide();
				var totalNum = parseInt(result.pageCount);
				totalPage=totalNum;
				var productList = doT.template($("#couponCenter").html())(result);
				if(num<=totalNum){
					if(productList.isAppend){
						$("#zidingyi").append(productList);
					}else{
						$("#zidingyi").append(productList);
					}
					canGetList=true;
				}else{
					canGetList=false;
					$(".jiazai").hide();
					$(".w-main").css("padding-bottom","0px");
					if(num==1){
						$("#zidingyi").append("<div style='clear: both'></div><div class='list'><center>—— 晚了一步！优惠券都被抢光了 ——</center></div >");
					}else{
						$("#zidingyi").append("<div style='clear: both'></div><div class='list'><center>—— 没有更多优惠券了 ——</center></div >");
					}
					
					if(productList.isAppend){
						$("#zidingyi").empty();
					} 
				}
			}else {
				showMessageRefresh(result.message);
				} 
			}});
			}
	
	//记录领优惠券信息
	function editProduct(id){
			$.ajax({
                url: SHOPDOMAIN+"/interfaces/Coup/sysCouponsLing.html",
                type: "post",
                dataType: "json",
                sync: false,
                data: {
                	"id" : id
                },
                success: function(data) {
                	if(data.res_code=='1'){
                		$.dialog("confirm","","您还没有登录,点击确定去登录",0,function(){
                			  window.location.href="login.html";
                		});
                	}else{
                		showMessageRefresh(data.message);
                	}
                }
            });
	}
	$(window).scroll(function(){
		var scrollTop = $(this).scrollTop();
		var scrollHeight = $(document).height();
		var windowHeight = $(this).height();
		if(scrollHeight-(scrollTop + windowHeight) < 200&canGetList){
			currentNum++;
			canGetList=false;
			product(data,currentNum);
			}
		
		});
	$("#subBtn").click(function(){
	    //让button无法再次点击
	    $(this).attr("disabled","disabled");
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

