<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/base.css" />
<link rel="stylesheet"
	href="${SHOPDOMAIN}/front/css/web/productDetails.css" />
<!--主要内容-->
<div id="mainW" style="overflow: hidden;">
	<!--面包屑导航-->
	<div class="breadCrumbNav wrapW">
		<a href="${SHOPDOMAIN}/index.html">首页</a> &gt; <a href='#'></a> &gt; <a href='#'></a>
	</div>

	<!--商品展示，图片，价格，加入购物车，收藏 等-->
	<div class="wrapW ofW">
		<!--商品图片展示-->
		<div class="flW">
			<div class="spec-scroll">
				<a class="prev"></a>

				<div class="items">
					<ul class="slides">
					</ul>
				</div>
				<a class="next"></a>
			</div>
			<div id="preview" class="spec-preview">
				<span class="jqzoom"> </span>
			</div>
			<!--缩图开始-->

			<!--缩图结束-->
		</div>
		<script>
			var userId = "${sessionFrontUser.id}";
			var goodsId = getRequest("prodId");
			var stat_webtype = "detail";
			var skuData;
			var APP = {
				no_commentsUrl : "${SHOPDOMAIN}/common/productShow/images/no_comments.png",
				page : "detail1",
				goodsId : getRequest("prodId"),
				comment : {
					total : 1,
					A : 0,
					B : 0,
					C : 0
				},
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
					add2shopcart_url : SHOPDOMAIN
							+ "/wap/shopCart/addCart.html",
					buy : "",
					fav_url : SHOPDOMAIN
							+ "/interfaces/productInfo/changeProductCollectionState.html?prodId="
							+ getRequest("prodId")
				}
			}
		</script>
        <style>
        	.spanF1{
        		 background:#f1f1f1;
        	}
        </style>
		<!--选择商品-->
		<div class="flW choosePro">
			<!--标题-->
			<div class="proTitle">
				<h1 class="mainTitle"></h1>

				<h3 class="viceTitle"></h3>
			</div>
			<!--价格-->
			<div class="col9 mt30">
				<div class="">
					<span class="lineH35Fl" >价格：</span><em class="Proprice fs24 nowprice lineH35Fl">￥0</em>
				</div>
				
			</div>
			<div style="clear: both"></div>
			<!--促销-->
			<div class="col9 cuxiaoTemp">
			</div>
			<!--规格库存-->
			<div class="col9 mt30">
				<div class="specinfo_div">
					<div class="clearfixW">
						<span class="flW ">规格：</span>
	
						<div class="flW proStandardList">
							<span class="proStandard"></span> <span class="proStandard"></span>
						</div>
					</div>
				</div>
				
				<div class="">
					<span>库存：</span> <em class="proStatus">充足</em>
				</div>
			</div>

			<!--运费数量-->
			<div class="col9 mt30 clearfixW">
				<div class="countTitle">
					<span>运费：</span> <span class="col3 shipFree">购物满20元免运费，20元之内运费4元</span>
				</div>
				<div>
					<span class="flW countTitle">数量：</span> <span class="proNum">
						<div class="reduceCount flW w-reduce" unselectable="on"
							style="-moz-user-select:none;" onselectstart="return false;">-
						</div>
						<div class="proCount flW" unselectable="on"
							style="-moz-user-select:none;" onselectstart="return false;">1
						</div>
						<div class="addCount flW w-add " unselectable="on"
							style="-moz-user-select:none;" onselectstart="return false;">+
						</div>
					</span>
				</div>
			</div>
			<div class="mt30">
				<input type="hidden" id="prodspecinfoid"/>
				<input type="hidden" id="prodtype"/>
				<input type="hidden" id="isSpecification"/>
				<input type="hidden" id="maxInventory" value="0"/>
				<!--无货状态添加noneProShopBtn-->
				<div class="appendShopBtn appendBtn flW ">加入购物车</div>
				<div class="appendCollBtn appendBtn flW ">放入收藏夹</div>
			</div>
		</div>
	</div>

	<div class="wrapW hotAndDetails ofW">
		<!--左边热销推荐-->
		<div class="leftSidePro">
			<div class="leftSideListTop">热销推荐</div>
			<div class="leftSideList">
				<ul class="hotProList">
				</ul>
			</div>
		</div>
		<!--商品详情，评价-->
		<div class="rightSidePro">
			<div class="rightSideTop">
				<ul>
					<li class="active">商品详情</li>
					<li id="prodping" class="">商品评价</li>
				</ul>
			</div>
			<div class="rightSideContent">
				<ul>
					<li style="display: block" class="proDetails proLiD">
					</li>


					<li class="proLiD">

						<div class="proEvaluationTab">
							<div class="proEvaluationTabDD active">
								<div class="proEvaluationTabD" clstag="0">全部评价（0条）</div>
							</div>
							<div class="proEvaluationTabDD">
								<div class="proEvaluationTabD" clstag="1">好评（0条）</div>
							</div>
							<div class="proEvaluationTabDD">
								<div class="proEvaluationTabD" clstag="2">中评（0条）</div>
							</div>
							<div class="proEvaluationTabDD">
								<div class="proEvaluationTabD borderRn" clstag="3">差评（0条）</div>
							</div>
						</div>
						<div class="proEvaluationContentList">
							<div class="evaList ping0" style="display: block">
							</div>
							<div class="evaList ping1"></div>
							<div class="evaList ping2"></div>
							<div class="evaList ping3"></div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>

<script id="rollpictmpl" type="text/x-dot-template">
	{{for(var i=0;i<it.length;i++){ }}
   		{{var obj=it[i];}}
		<li><img bimg="${USERIMGSRC}{{=imgZuhe(obj.uri,'_1000')}}"
							mImg="${USERIMGSRC}{{=imgZuhe(obj.uri,'_640')}}"
							src="${USERIMGSRC}{{=imgZuhe(obj.uri,'_160')}}"
							onmousemove="preview(this);"></li>
	{{}}}
</script>

<script id="commenttmpl" type="text/x-dot-template">
	{{for(var i=0;i<it.Data.length;i++){ }}
   		{{var obj=it.Data[i];}}
		<div class="evaListTablePad">
			<table><tr>
				<td class="td1Word" width="490px">
					<div class="evaDetailsWord">{{=obj.Content}}</div>
					<div class="showProPhoto clearfixW imgs" id="imgs">
						{{if(null!=obj.picurl&&""!=obj.picurl){}}
						{{var objImgs=obj.picurl.split(';');}}
							{{for(var j=0;j<objImgs.length;j++){ }}
   								{{var objImg=objImgs[j];}}
								{{if(null!=objImg&&""!=objImg){}}
									<img class="showPhotoSma" src="${USERIMGSRC}{{=imgZuhe(objImg,'_200')}}"
										layer-src="${USERIMGSRC}{{=objImg}}" width="100" alt="" />
							{{}}}}}
					</div>
					<div class="evaDetailsWord col9">{{=obj.addtime}}</div></td>
				<td class="td2Star">
					<div class="td2StarCount evaStar" data-score="{{=obj.Star}}"></div>
				</td>
				<td class="td3Photo"><img class="headerPhoto" src="${USERIMGSRC}{{=obj.HeadUrl}}"
					width="70" height="70" alt="" /></td>
				<td class="td4UserName breakW">{{=obj.NickName}}</td></tr>
			</table>
		</div>
	{{}}}
	<div class="page_and_btn">
		<div></div>{{=it.pageStr}}
	</div>
</script>
<script  id="h_hotProList" type="text/x-dot-template">
{{ if(it.list&&it.list.length>0){  }}
{{ for(var i=0; i<it.list.length;i++){ }}
{{var objLi=it.list[i];}}
    <a href="${SHOPDOMAIN}/productDetail.html?prodId={{=objLi.id}}">
  	<li><div class="posRelaivenone">
	<img src="${USERIMGSRC}{{=imgZuhe(objLi.picuri,'_300')}}" class="zoomImg" width="206" alt="" />
	</div>
	<div style="clear:both"></div>
	<div class="leftSideWord">
		<p>{{=objLi.name}}</p>
		<p class="Proprice">￥{{=objLi.price.toFixed(2)}}</p>
	</div></li>
</a>
{{}}}
{{}}}
</script>

<jsp:include page="footShop.jsp"></jsp:include>
<script src="${SHOPDOMAIN}/front/js/web/jquery.jqzoom.js"></script>
<script src="${SHOPDOMAIN}/front/js/jquery.raty.js"></script>
<script src="${SHOPDOMAIN}/front/js/web/goodsInfo.js"></script>
<script src="${SHOPDOMAIN}/front/js/web/base.js"></script>
<script>
	$(function() {
		$("#maxInventory").val("0");
		$("#prodtype").val("");
		$("#isSpecification").val("");
		$("#prodspecinfoid").val("");
		
		$('.proStandard').on('click', function() {
			$('.proStandard').removeClass('active');
			$(this).addClass('active')
		});

		//修改数量
		$('.w-add').on('click', function(e) {
			e.preventDefault();
			var oBtn = $(this).parent().find('.proCount');
			var oBtnHtml = $(this).parent().find('.proCount').html();
			var maxInventory=$("#maxInventory").val();
			if(parseInt(maxInventory)>oBtnHtml){
				var oNewBtnHtml = parseInt(oBtnHtml) + 1;
				oBtn.html(oNewBtnHtml);
			}else if(parseInt(maxInventory)==0){
				oBtn.html(1);
			}else{
				oBtn.html(maxInventory);
			}
		});
		$('.w-reduce').on('click', function(e) {
			e.preventDefault();
			var oBtn = $(this).parent().find('.proCount');
			var oBtnHtml = $(this).parent().find('.proCount').html();
			if (oBtnHtml <= 1) {
				var oNewBtnHtml = 1;
				oBtn.html(oNewBtnHtml);
			} else {
				var oNewBtnHtml = parseInt(oBtnHtml) - 1;
				oBtn.html(oNewBtnHtml);
			}
		});

		//tab切换
		$('.rightSideTop ul li')
				.click(
						function() {
							//            1.点击li时候要切换样式
							$(this).addClass('active').siblings().removeClass(
									'active');
							//2.根据li的索引值，来确定哪个显示 哪个隐藏
							$('.rightSideContent>ul>li').eq($(this).index())
									.show().siblings().hide();
						})
		//切换规格
		$(document).on("click",".label_specs",function() {
	        $("#prodspecinfoid").val("");//清理一下规格值
	        $("#maxInventory").val("0");
	        
	        var curxh_t = $(this).attr("curxh");
	        var specLen = mainSpecList.length;
	        
	        $(".prodspecdetaildiv_"+curxh_t+" .spanClass").removeClass('radioActive');
	        $(this).next().addClass('radioActive');
	        
	        if(specLen > 0){//存在规格
	        	var ck_count = 0;
	        	var spec_vt = new Array();
	        	
	        	for(var m = 1;m<= specLen;m++){
	        		ck_count += $("input:radio[name=sku_"+m+"]:checked").length;
	        	}
	        	
	        	if(ck_count == specLen){//所有规格都选中了,开始遍历匹配的规格
	        		var price_t = "";
	        		var count_t = "";
	        		var specInfoId_t = "";
	        		var marketPrice_t = "";
	        		for(var j = 1;j <= specLen;j++){
	        			spec_vt[(j-1)] = $("input:radio[name=sku_"+j+"]:checked").attr("specid");
	        		}
	        		
	        		$.each(prodSpecInfoList,function(n,val){
	        			var spec_id_v = val.id_val;
	        			var ck_flag = true;
	        			$.each(spec_vt,function(n1,val1){
		        			if(spec_id_v.indexOf(val1)==-1){
		        				ck_flag = false;
		        				return false;
		        			}
		        		});
		        		if(ck_flag){
		        			price_t = val.price;
		        			count_t = val.inventorycount;
		        			specInfoId_t = val.specInfoId;
		        			marketPrice_t = val.marketPrice;
		        			return false;
		        		}else{
		        			if(n == prodSpecInfoList.length-1){//如果是最后一个还是false 说明就么有这个规格
		        				price_t = "";
		        				count_t = 0;
		        				specInfoId_t = "";
		        				marketPrice_t = "";
		        			}
		        		}
	        		});
	        		$("#prodspecinfoid").val(specInfoId_t);//存放选中的规格值
	        		$("#maxInventory").val(count_t);//存放最大库存
	        		
	        		if(price_t == ""){
	        			$(".nowprice").html("暂无该产品规格");
	        		}else{
						$(".nowprice").html("￥"+parseFloat(price_t).toFixed(2));
	        		}
					if(count_t <= 0){
						$(".proStatus").html("无货");
						$(".appendShopBtn").addClass("noneProShopBtn");
					}else if(count_t < 50){
						$(".proStatus").html("仅剩"+count_t+"件");
						$(".appendShopBtn").removeClass("noneProShopBtn");
					}else{
						$(".proStatus").html("充足");
						$(".appendShopBtn").removeClass("noneProShopBtn");
					}
					
	        	}else{
	        		
	        	}
	        }
	        
		})
		
	});
	

	$(function() {
		getProdInfo();
		hotProList();
	})
</script>

