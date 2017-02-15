<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="topShop.jsp"></jsp:include>
	<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/productlist.css"/>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/productList.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/Personal.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/cmdCommon.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/login.css" />
<link rel="stylesheet"
	href="${SHOPDOMAIN}/front/css/web/productDetails.css" />
	<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/index.css" />

<!--右侧悬浮-->
<style>
.colorhov{color:#cbd13e;}
.zoomImg {height: 100%;transition: transform 0.3s ease 0s; width: 100%;}
img {border: 0 none;vertical-align: middle;}
li.liactive {background-color: #e70012;}
.proShoppingCartBtn.active { background-color: #e70012; color: #ffffff;}
.proBottom span { font-size: 16px; height: 25px; width: 90px;}
.proShoppingCartBtn { border: 1px solid #e5e5e5; color: #999999; float: right; text-align: center;}
.proShoppingCartBtn.active {background-color: #e70012;color: #ffffff;}
.proBottom span {font-size: 16px; height: 25px; width: 90px;}
.proShoppingCartBtn {border: 1px solid #e5e5e5; color: #999999;float: right; text-align: center;}
.posRelaivenone { float: none; position: relative;}
.hotProList li img { width: 100%;}
body{background:none}
.marginT25 { margin-top: 25px;}

</style>
<!--主要内容-->
<div id="mainW" style="overflow: hidden;">

	<!--面包屑导航-->
	<div class="breadCrumbNav wrapW">
		<a href="index.html">首页</a> &gt; <a href='#' id="tit"></a>
	</div>

	<!--列表-->
	<div class="wrapW">
		<div class="rightListTitle">
			<p>商品筛选</p>
		</div>
		<div class="proNameList">

			<ul>
				<li class="classifyList">
					<div class="classifyListMore pointerW" style="display: none">更多</div>
					<p class="ListTitleFront">品类：</p>
					<div class="classifyDetails classifyF">
						<div class="classifyListTotal">
							<a class="pinle" onclick="pinlei(null)" id="null"> <span class="pin pin_null active"
								>全部</span>
							</a>
						</div>
						<div class="classifylonely pinglei"></div>
					</div>

				</li>
				<!--规格-->
				<li class="classifyList">
					<p class="ListTitleFront">规格：</p>
					<div class="classifyDetails">
						<div class="classifyListTotal">
							<a  class="gu" href="javascript:void(0)" id="11" > 
							<span class="gui gui_11 active">全部</span>
							</a>
						</div>
						<div class="classifylonely">
							<a class="gu" href="javascript:void(0)" id="0"> <span class="gui gui_0">单品</span>
							</a> <a class="gu" href="javascript:void(0)" id="1"> <span class="gui gui_1">礼盒</span>
							</a>
						</div>
					</div>
				</li>
				<!--产地-->
				<li class="classifyList">
					<p class="ListTitleFront">产地：</p>
					<div class="classifyDetails">
						<div class="classifyListTotal">
							<a class="cha" href="javascript:void(0)" id="11"> <span class="chan chan_11 active"
								>全部</span>
							</a>
						</div>
						<div class="classifylonely">
							<a class="cha" href="javascript:void(0)" id="1"> <span class="chan chan_1">国内</span>
							</a> <a class="cha" href="javascript:void(0)" id="2"> <span class="chan chan_2">进口</span>
							</a>
						</div>
					</div>
				</li>
				<!--价格-->
				<li class="classifyList">
					<p class="ListTitleFront">价格：</p>
					<div class="classifyDetails">
						<div class="classifyListTotal">
							<a class="pric" href="javascript:void(0)" id="11"> <span class="price price_11 active"
								>全部</span>
							</a>
						</div>
						<div class="classifylonely ">
							<a  class="pric" href="javascript:void(0)" id="1" >
							 <span class="price price_1 ">100以下</span>
							</a> <a class="pric"
								href="javascript:void(0)" id="2"> <span class="price price_2 ">100-300</span>
							</a> <a class="pric"
								href="javascript:void(0)" id="3"> <span class="price price_3 ">300以上</span>
							</a>
							
						</div>
						
					</div>
				</li>
			</ul>
		</div>
		<!--左边热销推荐-->
		<div class="leftSidePro marginT25">
			<div class="leftSideListTop">热销推荐</div>
			<div class="leftSideList">
				<ul class="hotProList_p hotProList">
				</ul>
			</div>
		</div>
		<div class="rightList">

			<!--商品筛选条件：选中哪条加active,默认向下的箭头，调整方向 加up-->
			<div class="rightPro">
				<div class="rightFilterCondtion">
					<ul class="rightFilterCondtionUl">
						<li class="pai pai_ proCondtionDefault" id="moren">默认</li>
						<li class="pai pai_ "  id="xiaoliang">按销量</li>
						<li class="pai pai_ " id="jiage">按价格</li>
						<li class="pai pai_ " id="pingjia">按评价</li>
						<li class="pai pai_ " id="shijian">按时间</li>
					</ul>
				</div>
			</div>

			<div class="rightPro prolist">
				<!--收藏 加入购物车 active-->
				 
			</div>
		</div>
	</div>
</div>
<%--   <div class="posRelaivenone">
	                        <img prodId="${objLi.id?c}"  class="w-product w-product${objLi.id?c}" src="${USERIMGSRC}{{=imgZuhe(objLi.picuri,'_300')}}" class="zoomImg"
								data-original="${USERIMGSRC}$objLi.picuri?replace('.','_300.')}" width="190" alt=""/> --%>
<!--底部-->
<jsp:include page="footShop.jsp"></jsp:include>

<script id="p_productlist" type="text/x-dot-template">
<ul class="hotProList rightProList">
					<a  class="rightProList_">
{{ if(it.list&&it.list.length>0){  }}
	{{ for(var i=0; i<it.list.length;i++){ }}
	{{var objLi=it.list[i];}}
{{if((i+1)%4==0){}}
<li  id="colo"   style="margin-right: 0px;">
<a href="productDetail.html?prodId={{=objLi.id}}">
<div class="posRelaivenone">
{{if(objLi.ptname!=undefined){}}
					<div class="w-label" style="z-index:100">
						<p style="border-width:2px" class="w-labelP">{{=objLi.ptname}}</p>
					</div>
				{{}}}
       <img   class="zoomImg lazy w-product w-product{{=objLi.id}}"  prodId="{{=objLi.id}}" src="${SHOPDOMAIN}/front/images/wap/w-Loading.png"  data-original="${USERIMGSRC}{{=imgZuhe(objLi.picuri,'_300')}}" specId="{{=objLi.specid==undefined?'':objLi.specid}}"  width="206" alt=""/>
<div style="clear:both"></div>
</div>

<div class="leftSideWord" style="width:208px">
								<p class="ellipsisW">{{=objLi.name}}</p>
<p class="secSalesNum frW">已售<i>{{=objLi.salesCount}}</i>件</p>
								<p class="Proprice">￥{{=objLi.price.toFixed(2)}}</p>
</div>
</a>
<div class="proBottom">
								<span class="proCollectionBtn sp-cang w-cang{{=objLi.id}}" style="cursor:pointer" prodId="{{=objLi.id}}">收藏</span> 
                                <span class="proShoppingCartBtn sp-cart w-car{{=objLi.id}} " style="cursor:pointer" prodId="{{=objLi.id}}">加入购物车</span>
</div>
</li>
{{}else{}}
<li  id="colo" >
<a href="productDetail.html?prodId={{=objLi.id}}">
<div class="posRelaivenone">
{{if(objLi.ptname!=undefined){}}
					<div class="w-label" style="z-index:100">
						<p style="border-width:2px;" class="w-labelP">{{=objLi.ptname}}</p>
					</div>
				{{}}}
       <img   class="zoomImg  lazy w-product w-product{{=objLi.id}}"  prodId="{{=objLi.id}}"  src="${SHOPDOMAIN}/front/images/wap/w-Loading.png"  data-original="${USERIMGSRC}{{=imgZuhe(objLi.picuri,'_300')}}" specId="{{=objLi.specid==undefined?'':objLi.specid}}"  width="206" alt=""/>
<div style="clear:both"></div>
</div>

<div class="leftSideWord" style="width:208px">
								<p class="ellipsisW">{{=objLi.name}}</p>
                                <p class="secSalesNum frW">已售<i>{{=objLi.salesCount}}</i>件</p>
								<p class="Proprice">￥{{=objLi.price.toFixed(2)}}</p>
</div>
</a>
<div class="proBottom">
								<span class="proCollectionBtn sp-cang w-cang{{=objLi.id}}" style="cursor:pointer" prodId="{{=objLi.id}}">收藏</span> 
                                <span class="proShoppingCartBtn sp-cart w-car{{=objLi.id}} " style="cursor:pointer" prodId="{{=objLi.id}}">加入购物车</span>
</div>
</li>

{{}}}
	{{}}}
{{}}}
</a>
				</ul>
<div style="clear:both"></div>
<div class="page_and_btn">
		<div></div>{{=it.pageStr}}
</div>
</script>
<script id="h_hotProList" type="text/x-dot-template">
{{ if(it.list&&it.list.length>0){  }}
{{ for(var i=0; i<it.list.length;i++){ }}
{{var objLi=it.list[i];}}
    <a href="productDetail.html?prodId={{=objLi.id}}">
  <li id="colo"><img class="zoomImg " src="${USERIMGSRC}{{=imgZuhe(objLi.picuri,'_300')}}"  width="206" alt="" />
<div style="clear:both"></div>
	<div class="leftSideWord">
		<p>{{=objLi.name}}</p>
		<p class="Proprice">￥{{=objLi.price.toFixed(2)}}</p>
	</div></li>
</a>
{{}}}
{{}}}
</script>
<script id="pinglei" type="text/x-dot-template">
{{ if(it.list&&it.list.length>0){  }}
{{ for(var i=0; i<it.list.length;i++){ }}
{{var obj=it.list[i];}}
<a class="pinle" onclick="pinlei({{=obj.id}})" id="{{=obj.id}}"> <span class="pin pin_{{=obj.id}}">{{=obj.name}}</span>
							</a> 
{{}}}
{{}}}
</script>
<script>
var lipi=getRequest("ty");
if(lipi==1){
	$(".lipin").addClass("active");
	$(".shouye").removeClass("active");
}
document.title="电商平台     商品列表";
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
	var catalogId1, catalogId2, catalogId1name, catalogId2name, search, ty;
	search = theRequest['search'];
	if(search==null||search==""){
	}else{
		$("#searc").val(search);
	}
	catalogId1 = theRequest['catalogId1'];
	catalogId2 = theRequest['catalogId2'];
	catalogId1name = theRequest['catalogId1name'];
	catalogId2name = theRequest['catalogId2name'];
	ty = theRequest['ty'];
	function  pinlei(id) {
		if(id==null||id=="null"){
			$('.pin').removeClass("active");
			$(".pin_" + id).addClass('active');
			catalogId2 = null;
			productlist(1);
		}else{
		$('.pin').removeClass("active");
		$(".pin_" + id).addClass('active');
		catalogId2 = id;
		productlist(1);
		}
	}
	$(function() {
		productCatalog();
		productlist(1);
		hotProList();
		$("#moren").addClass("active");
		if(ty==1){
			ty = 1;
			$('.gui').removeClass("active");
			$(".gui_" + 1).addClass('active');
			$("#tit").html("搜索礼品");
		} 
		$(".sp-cang").click(function(){
		});
		//根据条件排序
		$('.pai').click(function() {
			if($(this).hasClass("active")){
				if($(this).hasClass("up")){
					$(this).removeClass("up");
					if($(this).attr("id")=="xiaoliang"){
						salesCount=1;
						productlist(1);
					}else if($(this).attr("id")=="jiage"){
						salesCount=5;
						productlist(1);
					}else if($(this).attr("id")=="pingjia"){
						salesCount=3;
						productlist(1);
					}else if($(this).attr("id")=="shijian"){
						salesCount=6;
						productlist(1);
					}
				}else{
					if($(this).attr("id")=="moren"){
						$('.pai').removeClass("active");
						$('.pai').removeClass("up");
						$(this).addClass("active");
						salesCount=1;
						productlist(1);
					}else{
						$(this).addClass("up");
					if($(this).attr("id")=="xiaoliang"){
						salesCount=7;
						productlist(1);
					}else if($(this).attr("id")=="jiage"){
						salesCount=2;
						productlist(1);
					}else if($(this).attr("id")=="pingjia"){
						salesCount=8;
						productlist(1);
					}else if($(this).attr("id")=="shijian"){
						salesCount=4;
						productlist(1);
					}
					}
				}
			}else{
				if($(this).attr("id")=="moren"){
					$('.pai').removeClass("active");
					$('.pai').removeClass("up");
					$(this).addClass("active");
					salesCount=1;
					productlist(1);
				}else{
				$('.pai').removeClass("active");
				$('.pai').removeClass("up");
				$(this).addClass("active");
				if($(this).attr("id")=="xiaoliang"){
					salesCount=1;
					productlist(1);
				}else if($(this).attr("id")=="jiage"){
					salesCount=5;
					productlist(1);
				}else if($(this).attr("id")=="pingjia"){
					salesCount=3;
					productlist(1);
				}else if($(this).attr("id")=="shijian"){
					salesCount=6;
					productlist(1);
				}
			}
			}
		});
		$('.classifyListMore').click(function() {
			//        $('.classifyList').css('height','auto')
			if ($('.classifyList').css('height') == '31px') {
				$('.classifyList').css('height', 'auto');
				$(this).html('收起');
			} else {
				$('.classifyList').css('height', '31px');
				$(this).html('更多')
			}
		});
		$('.pric').click(function() {
			$('.price').removeClass("active");
			$(".price_"+$(this).attr("id")).addClass('active');
			price = $(this).attr("id");
			productlist(1);
		});
		$('.cha').click(function() {
		$('.chan').removeClass("active");
		$(".chan_" + $(this).attr("id")).addClass('active');
		cha = $(this).attr("id");
		productlist(1);
	});
		$('.gu').click(function() {
			$('.gui').removeClass("active");
			$(".gui_" + $(this).attr("id")).addClass('active');
			ty = $(this).attr("id");
			productlist(1);
		});
	});
	var cha = 11;
	var price = 11;
	var salesCount=3;
	
	var first = 0;
	var totalPage = 0;
	//商品列表
	function productlist(num) {
		$.ajax({
			url : "${SHOPDOMAIN}/interfaces/productlist.html",
			type : "post",
			data : {
				"catalogId1" : catalogId1,
				"catalogId2" : catalogId2,
				"salesCount" : salesCount,
				"name" : "",
				"ty" : ty,
				name:search,
				origin : cha,
				price : price,
				"currentPage" : num,
				zu : 28
			},
			dataType : "json",
			success : function(result) {
				if(catalogId1name==undefined){
				}else{
					$("#tit").html(catalogId1name);
				}
				if(result.list.length==0){
					$(".prolist").empty();
					$(".prolist").html("<div class='per_noorder'>"
					+"<div class=' per_ per_noorderleft flW'><img src='${SHOPDOMAIN}/front/images/web/per_noorder.png' width='104' height='48' alt=''/></div>"
					+"<div class='per_noorderright flW'>"
					+"<ul>"
					+"<li>SORRY~暂时没有您想要的商品~</li>"
					+"<li>看看其他的吧！^_^</li>"
					+"</ul>"
					+"</div>"
					+"<div class='fox'></div>"
					+"</div>");
				}else{
				var totalNum = result.pageCount;
				totalPage = totalNum;
				var productList = doT.template($("#p_productlist").html())(
						result);
				$(".prolist").empty();
				$(".prolist").append(productList);
				$("img.lazy").lazyload();
				/* $('.rightProList li').each(function() {
					if (($(this).index()) %4 == 0&&$(this).index()!=0) {
						$(this).css('margin-right', 0)
					}
				}); */
				if(result.list.length!=0){
				getKuCunByProductId();
				getCarByProductId();
				getCangByProductId();
				}
			}
			}
		});
	}
	//左侧热销商品
	function hotProList() {
		$.ajax({
			url : "${SHOPDOMAIN}/interfaces/newproductList.html",
			type : "post",
			data : {},
			dataType : "json",
			success : function(result) {
				var productList = doT.template($("#h_hotProList").html())(
						result);
				$(".hotProList_p").append(productList);
			}
		});
	}
	//获取商品品类
	function productCatalog() {
		$.ajax({
			url : "${SHOPDOMAIN}/interfaces/productCatalog.html",
			type : "post",
			data : {
				catalogId1 : catalogId1
			},
			dataType : "json",
			success : function(result) {
				var productList = doT.template($("#pinglei").html())(result);
				$(".pinglei").append(productList);
				//				    多于10个种类 显示更多按钮
				if(catalogId2==null||catalogId2==""){
				}else{
					$('.pin').removeClass("active");
					$(".pin_" + catalogId2).addClass('active');
				}
				/*if ($('.classifylonely span').length > 13) {
					$('.classifyListMore').show();
				}*/
				/*alert($('.classifyF').height())*/
				if($('.classifyF').height()>36){
					$('.classifyListMore').show();
				}
				
			}
		});
	}
	//商品添加到购物车
	$(document).on("click",".sp-cart",function(event){
		var idd=$(this).attr("prodId");
		$.ajax({
	        type: "POST",
	        url: SHOPDOMAIN + "/wap/shopCart/addCart.html",
	        data: {
	        	prodId: $(this).attr("prodId"),
	            specId: $(".w-product"+$(this).attr("prodId")).attr("specid"),
	            count: 1,
	            companyId:$.cookie("sys_base_companyId")
	        },
	        async: true,
	        success: function (res) {
	            if(0 == res.res_code){
	            	showMessageAutoTime("商品已添加至购物车！",2000);
	            	$(".w-car" + idd).addClass("active");
					$(".w-car" + idd).html("已加购物车");
					cartCount();
	            }else{
	            	if(res.res_code=="1"){
	            		loginP("productList.html");
	            	}else{
	            		showMessage(res.message);
	            	}
	            }
	        },
	        dataType: "json"
	    });
		return false;
	});
	//商品添加到  收藏
	$(document).on("click",".sp-cang",function(event){
		var idd=$(this).attr("prodId");
		$.ajax({
	        type: "POST",
	        url: SHOPDOMAIN + "/interfaces/userCollection/addCart.html",
	        data: {
	        	prodId: $(this).attr("prodId")
	        },
	        async: true,
	        success: function (res) {
	            if(0 == res.res_code){
	            	showMessageAutoTime("收藏成功！",2000);
	            	$(".w-cang" + idd).addClass("active");
					$(".w-cang" + idd).html("已收藏");
	            }else{
	            	if(res.res_code=="1"){
	            		loginP("productList.html");
	            	}else{
	            		showMessageAutoTime(res.message,2000);
	            	}
	            }
	        },
	        dataType: "json"
	    });
		return false;
	});
	//检测当前页面中所有商品的是否在购物车中
	function getCarByProductId() {
		var companyid = $.cookie('sys_base_companyId');
		if (null != companyid) {
			var prod = "";
			$(".w-product").each(function() {
				prod += $(this).attr("prodId") + ",";
			})

			$.post(
					SHOPDOMAIN + '/interfaces/getCarProduct.html',
					{
						prodIds : prod,
						companyId : companyid
					},
					function(data) {
						if (data.res_code == '0') {
							$(data.list).each(
									function(i, item) {
										$(".w-car" + item).addClass("active");
										$(".w-car" + item).html("已加购物车");
									})
						}
					}, "json").error(function() {
				showError();
			});
		}
	}
	//检测当前页面中所有商品的是否已经收藏
	function getCangByProductId() {
		var companyid = $.cookie('sys_base_companyId');
		if (null != companyid) {
			var prod = "";
			$(".w-product").each(function() {
				prod += $(this).attr("prodId") + ",";
			})
			$.post(
					SHOPDOMAIN + '/interfaces/getCangProduct.html',
					{
						prodIds : prod,
						companyId : companyid
					},
					function(data) {
						if (data.res_code == '0') {
							$(data.list).each(
									function(i, item) {
										$(".w-cang" + item).addClass("active");
										$(".w-cang" + item).html("已收藏");
									})
						}
					}, "json").error(function() {
				showError();
			});
		}
	}
	
</script>
</body>
</html>