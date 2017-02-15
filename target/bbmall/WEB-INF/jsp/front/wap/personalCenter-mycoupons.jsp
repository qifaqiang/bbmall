<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
<link rel="stylesheet"
	href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<div class="w-main" style="padding-bottom:73px;">
	<div class="w-orderList w-fontsize30">
		<ul class="w-orderListUl">
			<div class="active">
				<li>未使用(<span id="1"></span>)
				</li>
			</div>
			<div>
				<li>已过期(<span id="2"></span>)
				</li>
			</div>
			<div>
				<li>已使用(<span id="3"></span>)
				</li>
			</div>
		</ul>
	</div>
	<div class="w-content">
		<div class="w-orderStatusNav  mycouponsCan" style="display: block">
		</div>

		<!--已过期-->
		<div class="w-orderStatusNav mycouponsExpire"></div>

		<!--已使用-->
		<div class="w-orderStatusNav  mycouponsOver"></div>
		<div class="couponBtn1 textCenter1  w-fontsize301 w-color91  jiazai"
			display="block">
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
	</div>
	<!-- 未使用优惠券 -->
	<script id="mycouponsCan" type="text/x-dot-template">
	{{ if(it.list&&it.list.length>0){  }}
   		{{ for(var i=0; i<it.list.length;i++){ }}
    		{{var obj=it.list[i];}}
   			<div class="w-orderList w-paddingTB30 w-padding37 overflowH">
                <div class="fl">
                    <div class="w-coupons-title w-fontsize24">
                        <div class="w-reduce-title w-reduce-title-effective ">满减</div>
                  		{{if(obj.needPrice==0){}}
                      		<div class="w-reduce-details">不限额使用</div>
                     	{{}else{}}
                     		<div class="w-reduce-details">满{{=obj.needPrice}}减{{=obj.substractPrice}}</div>
                   		{{}}}
					</div>
                    <div class="w-coupons-time w-color9 w-fontsize20">
						有效期：{{=obj.startTimeS}}-{{=obj.endTimeS}}
                    </div>
                </div>
			<div class="fr w-amount w-amount-effective">
				￥{{=obj.substractPrice}}
			</div>
		</div>
		{{}}}
	{{}}}

</script>
	<!-- 过期优惠券 -->
	<script id="mycouponsExpire" type="text/x-dot-template">
	{{ if(it.list&&it.list.length>0){  }}
		{{ for(var i=0; i<it.list.length;i++){ }}
			{{var obj=it.list[i];}}
			<div class="w-orderList w-paddingTB30 w-padding37 overflowH">
                <div class="fl">
                    <div class="w-coupons-title w-fontsize24">
                    	<div class="w-reduce-title w-reduce-title-invalid">满减</div>
                    	{{if(obj.needPrice==0){}}
                    		<div class="w-reduce-details">不限额使用</div>
                   		{{}else{}}
                     		<div class="w-reduce-details">满{{=obj.needPrice}}减{{=obj.substractPrice}}</div>
                    	{{}}}
                    </div>
				<div class="w-coupons-time w-color9 w-fontsize20">
					有效期：{{=obj.startTimeS}}-{{=obj.endTimeS}}
				</div>
			</div>
			<div class="fr w-amount w-amount-invalid">
				￥{{=obj.substractPrice}}
			</div>
		</div>
		{{}}}
	{{}}}
</script>
	<!-- 已使用优惠券 -->
<script id="mycouponsOver" type="text/x-dot-template">
	{{ if(it.list&&it.list.length>0){  }}
		{{ for(var i=0; i<it.list.length;i++){ }}
			{{var obj=it.list[i];}}
				<div class="w-orderList w-paddingTB30 w-padding37 overflowH">
                <div class="fl">
                    <div class="w-coupons-title w-fontsize24">
                        <div class="w-reduce-title w-reduce-title-invalid">立减</div>
					{{if(obj.needPrice==0){}}
                    	<div class="w-reduce-details">不限额使用</div>
                    {{}else{}}
                    	<div class="w-reduce-details">满{{=obj.needPrice}}减{{=obj.substractPrice}}</div>
                    {{}}}
                    </div>
 				<div class="w-coupons-time w-color9 w-fontsize20">
					 有效期：{{=obj.startTimeS}}-{{=obj.endTimeS}}
                </div>
			</div>
            <div class="fr w-amount w-amount-invalid">
				 ￥{{=obj.substractPrice}}
			</div>
		</div>
		{{}}}
	{{}}}
</script>
	<jsp:include page="foot-validate.jsp"></jsp:include>
	<script>
		document.title = "优惠券个人中心";
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
		$('.w-orderListUl>div')
				.click(
						function() {
							$(this).addClass('active').siblings().removeClass(
									'active');
							$('.w-content>.w-orderStatusNav').eq(
									$(this).index()).show().siblings().hide();
						});
		$(function() {
			currentNum = 1;
			mycouponsCan(data, currentNum);
			mycouponsExpire(data, currentNum);
			mycouponsOver(data, currentNum);
			scroll(0, 0);
		});

		var data = {};
		var step1 = true;
		var step2 = true;
		var step3 = true;
		var step1can = true;
		var step2can = true;
		var step3can = true;
		var currentNum1 = 1;
		var currentNum2 = 1;
		var currentNum3 = 1;
		var totalPage = 0;
		//可以使用的优惠券
		function mycouponsCan(data, num) {
			data["currentPage"] = num;
			$
					.ajax({
						url : "${SHOPDOMAIN}/interfaces/Coup/mycouponsCan.html",
						type : "post",
						data : {
							"currentPage" : num,
							"price" : price
						},
						dataType : "json",
						async : false,
						beforeSend : function() {
							$(".jiazai").show();
						},
						success : function(result) {
							if (result.res_code == '0') {
								step1can = true;
								$("#1").html(result.type);
								var totalNum = parseInt(result.pageCount);
								totalPage = totalNum;
								var productList = doT.template(
										$("#mycouponsCan").html())(result);
								console.log(num);
								console.log(totalNum);
								if (num <= totalNum || totalNum == 0) {
									if (productList.isAppend) {
										$(".mycouponsCan").append(productList);
									} else {
										$(".mycouponsCan").append(productList);
									}
									if (totalNum == 0) {
										$(".mycouponsCan")
												.html(
														"<a href='couponCenter-w.html'> <div class='w-margin27 w-margin-top couponBtn textCenter positionR w-fontsize30 w-color9'>更多好券，去领券中心看看<img src='${SHOPDOMAIN}/front/images/wap/enter.png' class='w-enterP' alt='去看看'/></div> </a>");
									}
								} else {
									step1 = false;
									num = totalNum;
									$(".mycouponsCan")
											.append(
													"<a href='couponCenter-w.html'> <div class='w-margin27 w-margin-top couponBtn textCenter positionR w-fontsize30 w-color9'>更多好券，去领券中心看看<img src='${SHOPDOMAIN}/front/images/wap/enter.png' class='w-enterP' alt='去看看'/></div> </a>");
									if (productList.isAppend) {
										$(".mycouponsCan").empty();
									}

								}
								$(".jiazai").hide();
								currentNum1++;
							} else {
								$
										.dialog(
												"confirm",
												"",
												"您还没有登录,点击确定去登录",
												0,
												function() {
													window.location.href = "login.html";
												});
							}
						}
					});
		}
		//过期失效的优惠券
		function mycouponsExpire(data, num) {
			data["currentPage"] = num;
			$
					.ajax({
						url : "${SHOPDOMAIN}/interfaces/Coup/mycouponsExpire.html",
						type : "post",
						data : {
							"currentPage" : num
						},
						dataType : "json",
						async : false,
						beforeSend : function() {
							$(".jiazai").show();
						},
						success : function(result) {
							$(".jiazai").hide();
							if (result.res_code == '0') {
								step2can = true;
								$("#2").html(result.type);
								var totalNum = parseInt(result.pageCount);
								totalPage = totalNum;
								var productList = doT.template(
										$("#mycouponsExpire").html())(result);
								if (totalNum == 0) {
									$(".mycouponsExpire")
											.html(
													"<a href='couponCenter-w.html'> <div class='w-margin27 w-margin-top couponBtn textCenter positionR w-fontsize30 w-color9'>更多好券，去领券中心看看<img src='${SHOPDOMAIN}/front/images/wap/enter.png' class='w-enterP' alt='去看看'/></div> </a>");
								} else {
									if (num <= totalNum) {
										if (productList.isAppend) {
											$(".mycouponsExpire").append(
													productList);
										} else {
											$(".mycouponsExpire").html(
													productList);
										}
									} else {
										step2 = false;
										num = totalNum;
										$(".mycouponsExpire")
												.append(
														"<a href='couponCenter-w.html'> <div class='w-margin27 w-margin-top couponBtn textCenter positionR w-fontsize30 w-color9'>更多好券，去领券中心看看<img src='${SHOPDOMAIN}/front/images/wap/enter.png' class='w-enterP' alt='去看看'/></div> </a>");
										if (productList.isAppend) {
											$(".mycouponsExpire").empty();
										}

									}
								}
							} else {
								showMessage(result.message);
							}
							currentNum2++;
						}
					});
		}
		//已使用的优惠券
		function mycouponsOver(data, num) {
			data["currentPage"] = num;
			$
					.ajax({
						url : "${SHOPDOMAIN}/interfaces/Coup/mycouponsOver.html",
						type : "post",
						async : false,
						data : {
							"currentPage" : num
						},
						dataType : "json",
						beforeSend : function() {
							$(".jiazai").show();
						},
						success : function(result) {
							$(".jiazai").hide();
							if (result.res_code == '0') {
								$("#3").html(result.type);
								step3can = true;
								var totalNum = parseInt(result.pageCount);
								totalPage = totalNum;
								var productList = doT.template(
										$("#mycouponsOver").html())(result);
								if (num <= totalNum || totalNum == 0) {
									if (productList.isAppend) {
										$(".mycouponsOver").append(productList);
									} else {
										$(".mycouponsOver").html(productList);
									}
									if (totalNum == 0) {
										$(".mycouponsOver")
												.html(
														"<a href='couponCenter-w.html'> <div class='w-margin27 w-margin-top couponBtn textCenter positionR w-fontsize30 w-color9'>更多好券，去领券中心看看<img src='${SHOPDOMAIN}/front/images/wap/enter.png' class='w-enterP' alt='去看看'/></div> </a>");
									}
								} else {
									$(".mycouponsOver")
											.append(
													"<a href='couponCenter-w.html'> <div class='w-margin27 w-margin-top couponBtn textCenter positionR w-fontsize30 w-color9'>更多好券，去领券中心看看<img src='${SHOPDOMAIN}/front/images/wap/enter.png' class='w-enterP' alt='去看看'/></div> </a>");
									step3 = false;
									num = totalNum;
									if (productList.isAppend) {
										$(".mycouponsOver").empty();
									}

								}
							} else {
								showMessage(result.message);
							}
							currentNum3++;
						}
					});
		}
		$(window)
				.scroll(
						function() {
							var scrollTop = $(this).scrollTop();
							var scrollHeight = $(document).height();
							var windowHeight = $(this).height();
							if (scrollHeight - (scrollTop + windowHeight) < 200) {
								if ($(".mycouponsCan").css("display").indexOf(
										"block") != -1
										&& step1 && step1can) {
									step1can = false;
									mycouponsCan(data, currentNum1);
								} else if ($(".mycouponsExpire").css("display")
										.indexOf("block") != -1
										&& step2 && step2can) {
									step2can = false;
									mycouponsExpire(data, currentNum2);
								} else if ($(".mycouponsOver").css("display")
										.indexOf("block") != -1
										&& step3 && step3can) {
									step3can = false;
									mycouponsOver(data, currentNum3);
								}
							}
						});
	</script>
	<jsp:include page="foot.jsp"></jsp:include>