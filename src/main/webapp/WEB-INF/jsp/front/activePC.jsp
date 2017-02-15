<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="topShop.jsp"></jsp:include>
<!--/*回到顶部*/-->
<link rel="stylesheet"
	href="${SHOPDOMAIN}/front/css/web/productList.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/Personal.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/cmdCommon.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/activePc.css" />
<script type="text/javascript" src="${SHOPDOMAIN}/front/layer/layer.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/jquery.cookie.js"></script>
<script src="${SHOPDOMAIN}/front/js/jquery.lazyload.min.js"></script>
<style>
.w-soldOut {background: rgba(0, 0, 0, 0) url("${SHOPDOMAIN}/front/images/web/outstock_tag.png") no-repeat scroll center center/contain;	bottom: 10px;	height: 50%;	position: absolute;	right: 10px;	width: 50%;}
.shopping-countW {	border-radius: 7px 7px 7px 0;behavior: url(js/PIE.htc);}
.shoppingCount {border-radius: 7px 7px 0 7px;behavior: url(js/PIE.htc);}
body {background: none}
</style>
<!--分隔线-->
<div class="s-line"></div>
<div class="activeBanZ"></div>

<div class="activeContent wrapW plistl"></div>
<script id="picurl" type="text/x-dot-template">
<div class="activeBan" style="background-image: url(${USERIMGSRC}{{=imgZuhe(it.pcspreadUrl,'_1920_350')}});background-repeat: no-repeat;background-position: center;height: 350px;">
<!-- 活动宣传图片 -->
</div>
</script>
<script id="activelist" type="text/x-dot-template">
<div class="activeProList">
<!-- 活动页面广告 -->
{{if(null!=it.adDetailList[it.nam-1]){}}
{{var adDet=it.adDetailList[it.nam-1];}}
<a href="{{=adDet.url}}">
	<div class="activeBan{{=it.nam+1}}">
        <img src="${USERIMGSRC}{{=imgZuhe(adDet.picUrl,'_1170_100')}}" width="1170" alt=""/>
    </div>
</a>
{{}}}
{{for(var i=0;i<it.list.length;i++){ }}
	{{var obj=it.list[i];}}
	<!-- 活动商品 -->
	<div class="activeProListWrap">
		<div class="activeProDetails">
			<a href="productDetail.html?prodId={{=obj.id}}">
                <div class="posRelaivenone ">
					<div class="w-product w-product{{=obj.id}}" prodId="{{=obj.id}}">
						<div class="w-images">
							<img src="${USERIMGSRC}{{=imgZuhe(obj.picuri,'_300')}}" class="zoomImg" width="208" alt=""/>
						</div>
					</div>
				</div>
				<div style='clear: both'></div>
                <div class="leftSideWord">
                    <p class="ellipsisW activeproName">{{=obj.name}}</p>
                    <p class="Proprice">￥{{=obj.price.toFixed(2)}}</p>
                </div>
			</a>
			<div class="joinShoppingCart  sp-cart" prodId="{{=obj.id}}"  speci="{{=obj.specid==undefined?'':obj.specid}}">
                    加入购物车
		</div>
	</div> 
</div>
{{}}}
</div>
</script>
<script>
document.title="电商平台     促销活动中心"
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
	var id;
	id=theRequest["id"];
	$(function() {
		activelist(1);
		promotionInfo();
	});
	
	function promotionInfo() {
		$.post(SHOPDOMAIN + '/interfaces/active/promotionInfo.html', {
			type : 0,
			id : id
		}, function(data) {
			if (data.res_code == '0') {
				var evalText = doT.template($("#picurl").html())(data.data);
				$(".activeBanZ").html(evalText);
			} else {
				showMessage(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}
	var coun=0;
	var pageCount=0;
	function activelist(nam) {
		$.post(SHOPDOMAIN + '/interfaces/active/activelist.html', {
			type : 0,
			id : id,
			zu :8,
			currentPage:nam
		}, function(data) {
			if (data.res_code == '0') {
				var evalText = doT.template($("#activelist").html());
				$(".plistl").append(evalText(data));
				var i=0;
				var prodID="";
				$(data.list).each(function(index,element){
					i++;
					if(i==1){
						prodID=element.id;
					}else{
						prodID=prodID+","+element.id;
					}
					element.id;
				});
				getKuCunByProductId(prodID);
				pageCount=data.pageCount;
				if(coun==0&&pageCount>=2){
				for(var i=2;i<=pageCount;i++){
					activelist(i);
				}
				coun++;
				}
			} else {
				showMessage(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}
	
	//检测当前页面中所有商品的库存情况
	function getKuCunByProductId(prodID) {
		var companyid = $.cookie('sys_base_companyId');
		if (null != companyid) {
			$.post(
					SHOPDOMAIN + '/interfaces/getKuCunByProductId.html',
					{
						prodIds : prodID,
						companyId : companyid
					},
					function(data) {
						if (data.res_code == '0') {
							var tempSoldOut = "<div class='w-soldOut'></div>";
							$(data.list).each(
									function(i, item) {
										$(".w-product" + item).children().eq(0)
												.append(tempSoldOut);
									})
						} else {
							showMessage(data.message);
						}
					}, "json").error(function() {
				showError();
			});
		}
	}
	$(document).on("click",".sp-cart",function(event){
		var idd=$(this).attr("prodId");
		$.ajax({
	        type: "POST",
	        url: SHOPDOMAIN + "/wap/shopCart/addCart.html",
	        data: {
	        	prodId: $(this).attr("prodId"),
	            specId: $(this).attr("speci"),
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
</script>