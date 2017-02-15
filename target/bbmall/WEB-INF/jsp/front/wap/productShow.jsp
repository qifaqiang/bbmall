<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>电商平台</title>
<meta charset="utf-8">
<meta content="" name="description">
<meta content="" name="keywords">
<meta content="eric.wu" name="author">
<meta content="application/xhtml+xml;charset=UTF-8"
	http-equiv="Content-Type">
<meta content="no-cache,must-revalidate" http-equiv="Cache-Control">
<meta content="no-cache" http-equiv="pragma">
<meta content="0" http-equiv="expires">
<meta content="telephone=no, address=no" name="format-detection">
<meta
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport">
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/common/js/doT.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/wap/common.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/jquery.cookie.js"></script>
<script src="${SHOPDOMAIN}/front/js/jquery.lazyload.min.js"></script>

<link href="${SHOPDOMAIN}/common/productShow/css/reset.css"
	rel="stylesheet" />
<link href="${SHOPDOMAIN}/common/productShow/css/common.css"
	rel="stylesheet" />
<link href="${SHOPDOMAIN}/common/productShow/css/detailgoods.css"
	rel="stylesheet" />
<link href="${SHOPDOMAIN}/common/productShow/css/goods.css"
	rel="stylesheet" />
<link href="${SHOPDOMAIN}/front/css/wap/hDialog.css" rel="stylesheet" />

<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/flexslider-wap.css" />
<script src="${SHOPDOMAIN}/common/productShow/js/zeptos.js"></script>
<script src="${SHOPDOMAIN}/common/productShow/js/swipe-cmd.js"></script>
<script src="${SHOPDOMAIN}/common/productShow/js/sea.js"></script>
<script src="${SHOPDOMAIN}/common/productShow/js/widget_Popup.js"></script>
<style>
</style>

<script>
	var SHOPDOMAIN = "${SHOPDOMAIN}";
	var USERIMGSRC = "${USERIMGSRC}";
	var userId = "${sessionFrontUser.id}";
	var goodsId = getRequest("prodId");
	var stat_webtype = "detail";
	var APP = {
		no_commentsUrl : "${SHOPDOMAIN}/common/productShow/images/no_comments.png",
		page : "detail1",
		message : true,
		goTop : true,
		isMobile : (/iphone|ipad/gi).test(navigator.appVersion),
		goodsId : getRequest("prodId"),
		limit : {
			"val" : 0,
			"name" : "电商平台"
		}, //val: 1 只允许粉丝购买
		attention : {
			val : 1,
			AttentionId : 'zhangshisc',
			URL : "http://mp.weixin.qq.com/s?__biz=MzA5MjMxMTQxMA==&mid=208283479&idx=1&sn=978eaf136211b4a201f867d103ae3443#rd"
		}, //关注引导的微信公众号 val:0-二维码 1-链接
		goodsInfo : {
			isdelordowm : false,
			imgs : "http://shopimg.weimob.com/353380/Goods/1512061120452121.jpg@0e_640w_640h_0c_0i_0o_70Q_1x.src",
			collected : false,//是否收藏
			shopcartSum : 0,//购物车数量
			comment : {
				total : 1,
				A : 0,
				B : 0,
				C : 0
			},
			limitSum : 0,//限购数
			buyedSum : 0,//已购买数
			priceRange : [ 0, 0 ],
			priceOriginRange : [ 0, 0 ],
			totalInventory : 1
		},
		Authentication : true,
		Store : false,//是否有线下门店,
		ScreenHeight : $(window).height(),
		ExpressType : 2, //0全国包邮  1 统一运费 2运费模板
		ExpressFee : 0,//针对统一运费时的运费金额
		FreightTemplateId : 2667,
		urls : {
			shopcart_page : "shopping-cart.html",
			Authentication_page : "#",
			user_center : "personalCenter.html",
			goodInfo : SHOPDOMAIN
					+ "/interfaces/productInfo/getProductSepcInfo.html?prodId="
					+ getRequest("prodId"),
			comments_url : SHOPDOMAIN
					+ "/interfaces/productInfo/getComment.html?prodId="
					+ getRequest("prodId"),
			add2shopcart_url : SHOPDOMAIN + "/wap/shopCart/addCart.html",
			buy : "",
			fav_url : SHOPDOMAIN
					+ "/interfaces/productInfo/changeProductCollectionState.html?prodId="
					+ getRequest("prodId")
		}
	}
	if (APP.isMobile) {
		document.addEventListener('touchmove', function(e) {
			e.preventDefault();
		}, false);
	}
	var ScreenWidth = $(window).width();
	var gsubstr = function(str, n) {
		var r = /[^\x00-\xff]/g;
		if (str.replace(r, "mm").length <= n) {
			return str;
		}
		var m = Math.floor(n / 2);
		for (var i = m; i < str.length; i++) {
			if (str.substr(0, i).replace(r, "mm").length >= n) {
				return str.substr(0, i) + "...";
			}
		}
		return str;
	} //字符串区分（中文两个字符英文一个字符）

	setTimeout(function() {
		(function(l) {
			seajs.config({
				base : "${SHOPDOMAIN}/common/productShow/",
				map : [ [ ".js", (l && l[1] || "") + ".js?v=1773406158" ] ]
			});
			seajs.use("js_cmd/goods.js");
		})(location.href.match(/de(\-\d+)bug/));
	}, 0);
</script>

</head>
<style>
#goodsComment {
	margin-bottom: 10px
}
</style>
<body onselectstart="return true;" ondragstart="return false;">
	<div id="container" class="IsMobile container">
		<div class="page-container swiper_con">
			<div class="swiper-wrapper swiper-no-swiping">
				<div class="swiper-slide">
					<!--商品基本信息开始-->
					<div class="goodsBase">
						<div id="goodsBasecon">
							<div id="Basescroller">
								<!--商品基本内容开始-->
								<header data-role="header">
									<div class="header_top" id="">
										<span class="verNull"></span> <a href="index.html"> <img
											class="fr"
											src="${SHOPDOMAIN}/common/productShow/images/index.png"
											height="17" alt="" />
										</a> <a href="productcatalog.html"> <img class="fr marginR10"
											src="${SHOPDOMAIN}/common/productShow/images/search-img1.png"
											height="17" alt="" />
										</a>
									</div>
									<!-- 轮播 -->
									<div id="w-banner">
										<div class="flexslider">
											<ul class="slides">
											</ul>
										</div>
										<script type="text/javascript"
											src="${SHOPDOMAIN}/front/js/jquery.flexslider-min.js"></script>
									</div>

								</header>
								<!--头部图片轮播-->

								<!--基本信息-->
								<div class="section_body info_basic">
									<div class="tbox title_and_fav">
										<div id="label_title" class="title" style="width: 100%;"></div>
										<div>
											<div class="fav" id="icon_fav1">
												<span class="icon_fav">&nbsp;</span> <label>&nbsp;</label>
											</div>
										</div>
									</div>
									<div class="tbox price_and_fav price_and_sale">
										<div>
											<div class="price">
												<p>
													现价：￥<span id="label_price">100</span><span
														id="label_activityTag" class="label_activityTag vhidden"></span>
												</p>
												<p>
													原价：<span id="label_price_original">50</span>
												</p>
											</div>
										</div>
									</div>
									<div class="freight_2">
										<div>
											<div>
												<div class="shipFree">送至</div>
											</div>
										</div>
									</div>
								</div>

								<!--优惠信息-->
								<div id="dl_GoodDiscount" class="section_body dl_GoodDiscount">
									<div id="Discount_top" class="Discount_top">
										<div>
											<p>优惠：</p>

											<div class="Discount_label" id="Discount_label"
												style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
											</div>
										</div>
										<div>
											<a href="javascript:;">&nbsp;</a>
										</div>
									</div>
									<div id="Discount_list" class="Discount_list hidden">
										<ul>
										</ul>
									</div>
								</div>

								<!--请选择 尺码 颜色 以及数量-->
								<div class="section_body link_sku_dialog"
									style="margin-bottom: 15px; border-bottom: 1px solid #e5e5e5; border-top: 1px solid #e5e5e5">
									<a id="label_sku_title2" href="javascript:;" class="arrow">
										<label>
											<div>请选择规格</div>
										</label>
									</a>
								</div>


								<!--下拉样式-->
								<div class="pullTest">继续拖动，查看详情</div>
								<div id="pullUp">
									<span class="pullUpIcon"></span>
								</div>
								<!--商品基本内容结束-->
							</div>
						</div>
					</div>
					<!--商品基本信息结束-->
				</div>


				<div class="swiper-slide">

					<div id="goodheader">
						<div id="div_nav">
							<ul>
								<li class="active">图文详情</li>

								<li>评价(<span></span>)
								</li>
								<div class="activeborder"></div>
							</ul>
						</div>
					</div>

					<div id="pullDown">
						<span class="pullDownIcon"></span><span class="pullDownLabel">下拉，返回商品基本信息</span>
					</div>
					<div class="goodsinfo scence0">
						<div class="goodstuwen">
							<div id="goodscons">

								<div class="goodscroller">
									<!--商品详情-->
									<div id="goods_det" data-widget="img_prev_view"></div>
									<!--商品详情结束-->
									<div class="End">
										<label>没有更多了</label>
									</div>
								</div>
							</div>
						</div>
					</div>


					<!-- con-container -->
					<div class="goodsinfo scence1">
						<div class="Commentcon">
							<div id="Commenter">
								<div class="goodscroller ">
									<ul class="nav_comments box" id="nav_comments">
										<li class="on" data-idx="0"><label>全部</label></li>
										<li data-idx="1"><label>好评(1)</label></li>
										<li data-idx="2"><label>中评(0)</label></li>
										<li data-idx="3"><label>差评(0)</label></li>
									</ul>

									<div id="goodsComment">
										<ul id="list_comments_0" class="list_comments on">
											<!---->
											<div class="nomore hidden">没有更多了</div>
										</ul>
										<ul id="list_comments_1" class="list_comments">
											<!---->
											<div class="nomore hidden">没有更多了</div>
										</ul>
										<ul id="list_comments_2" class="list_comments">
											<!---->
											<div class="nomore hidden">没有更多了</div>
										</ul>
										<ul id="list_comments_3" class="list_comments">
											<!---->
											<div class="nomore hidden">没有更多了</div>
										</ul>
										<div data-role="widget" data-widget="loading_bottom"
											id="loading_bottom" class="loading_bottom vhidden">
											<div class="widget_wrap">
												<ul class="tbox">
													<li>
														<div class="loading_wrap">
															<span class="loading">&nbsp;</span>
														</div>
													</li>
													<li>正在加载，请稍后...</li>
												</ul>
												<ul class="tbox">
													<li></li>
													<li style="text-align: center;">没有更多了</li>
												</ul>
											</div>
										</div>
									</div>
									<div id="list_comments"></div>
								</div>
							</div>
						</div>
					</div>
					<!-- Parameter-con -->

					<div class="goodsinfo" style="display: block">
						<div class="Paramcon">
							<div id="Parameter">

								<div class="goodscroller"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--底部悬浮按钮-->
	<footer data-role="footer">
		<!---->


		<div data-role="copyright"></div>

		<div data-role="widget" data-widget="btip_widget"
			class="btip_widget hidden" id="btip_widget">
			<div class="widget_wrap">
				<label id="btip_label" class="btip_label"></label>
			</div>
		</div>
		<!---->
		<div data-role="widget" data-widget="">
			<div class="widget_wrap">
				<ul class="fixed_btn">
					<ol id="fixed_btn" class="tbox" style="position: fixed;">
						<li>
							<div style="width: 96px">
								<a href="javascript:;" id="btn_link_shopcart"
									class="btn_add btn_add_shopcart" data-count="0">&nbsp;</a> <a
									href="javascript:;" id="btn_user_center"
									class="btn_add btn_user_center">&nbsp;</a>

							</div>
						</li>
						<li>
							<div class="box">
								<div>
									<a href="javascript:;" id="btn_add_shopcart" class="btn on">
										<label>加入购物车</label>
									</a>
								</div>

							</div>
						</li>
					</ol>
				</ul>
			</div>
		</div>

	</footer>

	<!------goods sku--------->
	<div data-role="widget" data-widget="sku_dialog"
		class="sku_dialog hidden" id="sku_dialog" ontouchmove="return false;">
		<div class="widget_mask"></div>
		<div class="widget_wrap">
			<ul>
				<li>
					<dl class="sku_header tbox">
						<dd>
							<div class="title_pic">
								<span class="img_wrap img_small"> </span>
							</div>
						</dd>
						<dd>
							<span class="sku_close" id="sku_close">&nbsp;</span>

							<div class="sku_Choice">
								<div>
									<p id="label_sku_price"></p>
									<p id="label_sku_Stock"></p>
									<p id="label_sku_title">请选择规格</p>
								</div>
							</div>
						</dd>

					</dl>
				</li>
				<section id="scroller_sku_list" style="overflow: hidden;">
					<div
						style="transition-property: transform; -webkit-transition-property: transform; transform-origin: 0px 0px 0px; transform: translate(0px, 0px) translateZ(0px);">
						<div>
							<li>
								<div id="list_sku" class="list_sku">
									<section>
										<div>
											<label>尺码</label>
										</div>
										<div>
											<label class="label_radio"> <input type="checkbox"
												name="sku_0" value="102904281347120"> <span>S
											</span>
											</label><label class="label_radio"> <input type="checkbox"
												name="sku_0" value="102904281347121"> <span>M
											</span>
											</label><label class="label_radio"> <input type="checkbox"
												name="sku_0" value="102904281347122"> <span>L
											</span>
											</label>
										</div>
									</section>
								</div>
								<div class="table_choose">
									<table id="table_number" class="table_number">
										<tbody>
											<tr>
												<td><label style="white-space: pre; font-size: 14px">数量</label>
												</td>
												<td>
													<div class="sku_Rule">
														<div class="Purchase">
															<label id="sku_inventory" class="sku_inventory"></label>
														</div>
														<div class="Purchasecon">
															<div class="box">
																<div>
																	<input type="button" value="-" style="opacity: 0.5;">
																</div>
																<div>
																	<input type="tel" value="1" id="sku_number">
																</div>
																<div>
																	<input type="button" value="+" style="opacity: 1;">
																</div>
															</div>
														</div>

													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</li>
						</div>
					</div>
				</section>
				<li id="sku_btn_group" class="sku_btn_group group_1"><a
					href="javascript:;" id="btn_dialog_add_shopcart" style="width: 98%">加入购物车</a>
					<!--<a href="javascript:;" id="btn_dialog_buy">立刻购买</a>--> <a
					href="javascript:;" id="btn_dialog_ok">确认</a></li>
			</ul>
			<input type="hidden" id="prodspecinfoid"/>
			<input type="hidden" id="prodtype"/>
			<input type="hidden" id="isSpecification"/>
			<input type="hidden" id="maxInventory" value="0"/>
		</div>
	</div>
	<script id="rollpictmpl" type="text/x-dot-template">
	{{for(var i=0;i<it.length;i++){ }}
   		{{var obj=it[i];}}
		<li><img src="${USERIMGSRC}{{=imgZuhe(obj.uri,'_640')}}" /></li>
	{{}}}
	</script>
	<script src="${SHOPDOMAIN}/front/js/jquery.hDialog.js"></script>
	<script src="${SHOPDOMAIN}/front/js/wap/ceju.js"></script>
	<script src="${SHOPDOMAIN}/common/productShow/js/goodsInfo.js"></script>
	
	<script type="text/javascript"
		src="${SHOPDOMAIN}/front/js/wap/wxshare.js"></script>
	<script type="text/javascript">
	var prodSpecInfoList ="";
	var mainSpecList = "";
	var detailSpecList = "";
	var prodType ="";
	var isSpecification ="";

		$(function() {
			$("#maxInventory").val("0");
			$("#prodtype").val("");
			$("#isSpecification").val("");
			$("#prodspecinfoid").val("");
			
			getProdInfo();
			checkCollection();
			getCountByUserId();
			
		});
	</script>
	<jsp:include page="foot.jsp"></jsp:include>