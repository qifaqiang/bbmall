<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/front/css/wap/personalCenter.css">
<div class="w-main">
	<!--头部-->
	<form action="">
		<div class="w-content">
			<div class="w-orderStatusNav  mycouponsCan" style="display: block">
			</div>
		</div>
	</form>
</div>
<style>
.w-subBtn1 {
	background: #fca815 none repeat scroll 0 0;
	border: medium none;
	border-radius: 10px;
	color: #ffffff;
	display: block;
	font-family: "Microsoft YaHei", "微软雅黑";
	font-size: 30px;
	height: 73px;
	line-height: 73px;
	margin: 0 auto;
	text-align: center;
	width: 590px;
	margin-top: 40px;
}
/* .zuch {
	background: #FFFFFF none repeat scroll 0 0;
}  */
.w-amount-effective1 {
	background: rgba(0, 0, 0, 0) url("../../images/wap/w-denomination.png")
		no-repeat scroll center center;
}

.w-amount1 {
	background-size: contain;
	color: #ffffff;
	font-size: 48px;
	height: 100px;
	line-height: 100px;
	text-align: center;
	width: 170px;
}
</style>
<!-- 未使用优惠券 -->
<script id="mycouponsCan" type="text/x-dot-template">
{{ if(it.list&&it.list.length>0){  }}
	{{ for(var i=0; i<it.list.length;i++){ }}
		{{var obj=it.list[i];}}
		{{if(obj.needPrice<=price){}}
			<div class="w-orderList w-paddingTB30 w-padding37 overflowH  choose"  id={{=obj.id}}>
				<div class="fl">
					<div class="fl w-unchecked w-choosechecke  ch_{{=obj.id}}" id={{=obj.id+1}}></div>
                        <div class="w-coupons-title w-fontsize24 w-marginL60px">
                            <div class="w-reduce-title w-reduce-title-effective ">满减</div>
                            {{if(obj.needPrice==0){}}
								<div class="w-reduce-details">不限额使用</div>
							{{}else{}}
                     			<div class="w-reduce-details">满{{=obj.needPrice}}减{{=obj.substractPrice}}</div>
                    		{{}}}
                        </div>
                        <div class="w-coupons-time w-color9 w-fontsize20 w-marginL60px">
                         		有效期：{{=obj.startTimeS}}-{{=obj.endTimeS}}
                        </div>
                    </div>
                    <div class="fr w-amount w-amount-effective">
                    	￥{{=obj.substractPrice}}
                  </div>
                </div>
		{{}}}
	{{}}}
{{}}}

{{ if(it.list&&it.list.length>0){  }}
	{{ for(var i=0; i<it.list.length;i++){ }}
		{{var obj=it.list[i];}}
		{{if(obj.needPrice>price){}}
			<div class="w-orderList w-paddingTB30 w-padding37 overflowH zuch">
                    <div class="fl">
                        <div class="w-coupons-title w-fontsize24 w-marginL60px">
                            <div class="w-reduce-title w-reduce-title-effective1 ">满减</div>
                            {{if(obj.needPrice==0){}}
                      			<div class="w-reduce-details">不限额使用</div>
                      		{{}else{}}
                     			<div class="w-reduce-details ">满{{=obj.needPrice}}减{{=obj.substractPrice}}</div>
                    		{{}}}
                        </div>
                        <div class="w-coupons-time w-color9 w-fontsize20 w-marginL60px">
                         		有效期：{{=obj.startTimeS}}-{{=obj.endTimeS}}
                        </div>
                    </div>
                    <div class="fr w-amount w-amount-effective12 ">
                    	￥{{=obj.substractPrice}}
                  </div>
                </div>
		{{}}}
		{{if(i==it.list.length-1){}}
			<a>            
				<div class="w-subBtn1 w-margin-top"  onclick="returny()">确定</div></a>
			<!--{{if(currentNum==it.pageCount){}}
					<br><div class="list"><center><<已经没有更多了...>></center></div>
		{{}}}-->
			<!--去领券中心看看-->
            <a href="couponCenter-w.html">
                <div class="w-margin27 w-margin-top couponBtn textCenter positionR w-fontsize30 w-color9">
                    更多好券，去领券中心看看
                    <img src="${SHOPDOMAIN}/front/images/wap/enter.png" class="w-enterP" alt="去看看"/>
                </div>
            </a>
		{{}}}
	{{}}}
{{}}}
{{if(it.list.length==0){}}
<div class='w-margin27 w-margin-top couponBtn textCenter positionR w-fontsize30 w-color9  zuchunye'  stay='display:none'>  没有符合的优惠券！</div>
			<!--去领券中心看看-->
            <a href="couponCenter-w.html">
                <div class="w-margin27 w-margin-top couponBtn textCenter positionR w-fontsize30 w-color9">
                    更多好券，去领券中心看看
                    <img src="${SHOPDOMAIN}/front/images/wap/enter.png" class="w-enterP" alt="去看看"/>
                </div>
            </a>
{{}}}

</script>
<script>
	document.title = "选择优惠券";
	//首先得到URL传过来的值//得到用户使用的价格
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
	var price;
	price = theRequest['price'];
	var data = {};
	var currentNum = 0;
	var totalPage = 0;
	//可以使用的优惠券
	function mycouponsCan(data, num) {
		data["currentPage"] = num;
		$.ajax({
			url : "${SHOPDOMAIN}/interfaces/Coup/mycouponsCan.html",
			type : "post",
			data : {
				currentPage : num,
				pirce : price
			},
			dataType : "json",
			success : function(result) {
				if (result.res_code == '0') {
					var totalNum = parseInt(result.pageCount);
					totalPage = totalNum;
					var productList = doT.template($("#mycouponsCan").html())(
							result);
					if (num <= totalNum || totalNum == 0) {
						if (productList.isAppend) {
							$(".mycouponsCan").append(productList);
						} else {
							$(".mycouponsCan").append(productList);
						}
					} else {
						num = totalNum;
						if (productList.isAppend) {
							$(".mycouponsCan").empty();
						}

					}
				} else {
					$.dialog("confirm", "", "您还没有登录,点击确定去登录", 0, function() {
						window.location.href = "login.html";
					});
				}
			}
		});
	}
	$(function() {
		currentNum = 1;
		mycouponsCan(data, currentNum);
		scroll(0, 0);
		$('.w-orderStatusNav').on('click', '.choose', function() {
			$('.w-unchecked').removeClass('w-checked').addClass('w-unchecked');
			$('.ch_' + $(this).attr('id')).addClass('w-checked');
		});
	});
	$(window).scroll(function() {
		var scrollTop = $(this).scrollTop();
		var scrollHeight = $(document).height();
		var windowHeight = $(this).height();
		if (scrollTop + windowHeight == scrollHeight) {
			currentNum++;
			product(data, currentNum);
		}

	});
	function returny() {
		var i = $(".w-checked").attr("id") - 1;
		if (i != null && i != "") {
			window.location.href = SHOPDOMAIN
					+ '/wap/cart-confirmation-order.html?syCrodId=' + i;
		}
	}
</script>
</body>
</html>