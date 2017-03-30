<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/front/css/wap/part-page.css">
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter.css"/>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/productlist.css"/>
<div class="w-main" style="padding-bottom:73px;">
	<!--头部-->
	<div id="w-header">
		<form class="sousuo">
			<input class="search" type="text" placeholder="搜索商品" id="name">
			<img class="search-img"
				src="${SHOPDOMAIN}/front/images/wap/search-img.png"
				onclick='orderInfo(0);' />
		</form>
	</div>
	<style>
		.fixedShopIcon{
			position:fixed;
			bottom:200px;
			right:20px;
			z-index:1000
		}
		.countshop{
			display: inline-block;
			padding: 0 5px;
			height: 20px;
			line-height:20px;
			min-width: 16px;
			border-radius: 20px;
			background: #ff2e45;
			color: #ffffff;
			text-align: center;
			font-size: 16px;
			position: absolute;
			right: 50px;
			top: -5px;
			-webkit-box-sizing: border-box;
		}
	</style>
	<!-- 右侧购物车图标 -->
	<div class="fixedShopIcon" onclick="javascript:window.location.href='shopping-cart.html'">
		<img src="${SHOPDOMAIN}/front/images/wap/shoppingCartIcon.png" width="80"/>
		<div class="countshoptemp"></div>
	</div>
	<!--商品-->
	<div id="w-content">
		<span id="title"> 
		</span>
		<div class="screening">
			<ul>
				<li id="1">
				<a href="javascript:void(0)" onclick='orderInfo(1);'>
						销量  <!-- 
						<img class="black xiaoliangh" src="${SHOPDOMAIN}/front/images/wap/huisanjiao.png" />
					    <img class="green xiaoliangg" src="${SHOPDOMAIN}/front/images/wap/huisanjiao-g.png"  style="display:none"/>
				 --></a></li>

				<li id="2">
				<a href="javascript:void(0)" onclick='orderInfo(2);'>
						价格  
						<img class="fa black jiagehdown" src="${SHOPDOMAIN}/front/images/wap/huisanjiao.png" /> 
						
					    <img class="green jiagegdown" src="${SHOPDOMAIN}/front/images/wap/huisanjiao-g.png" style="display:none"/>
						<img class="green  jiagegup" src="${SHOPDOMAIN}/front/images/wap/huisanjiao-gup.png" style="display:none"/>
				</a>
				</li>

				<li id="3">
				<a href="javascript:void(0)" onclick='orderInfo(3);'>
						评价 <!-- 
						 <img class="black pingjiah" src="${SHOPDOMAIN}/front/images/wap/huisanjiao.png" />
					     <img class="green pingjiag" src="${SHOPDOMAIN}/front/images/wap/huisanjiao-g.png" style="display:none"/>
				 --></a>
				</li>
				<li id="4">
				<a href="javascript:void(0)" onclick='orderInfo(4);'>
						上架时间 
                         <img class="black shijianhdown2" src="${SHOPDOMAIN}/front/images/wap/huisanjiao.png" /> 
					    <img class="green shijiangdown2" src="${SHOPDOMAIN}/front/images/wap/huisanjiao-g.png"  style="display:none"/>
					    <img class="green shijiangup2" src="${SHOPDOMAIN}/front/images/wap/huisanjiao-gup.png" style="display:none"/>
					</span>
				</a></li>
			</ul>
		</div>

		<div class="frs_column">
			<ul class="productlist">
			</ul>
		</div>
	</div>
	<div class="couponBtn1 textCenter1  w-fontsize301 w-color91  jiazai"  display="block">
                 <div class="loadingNowDiv"> <div class="spinner"> <div class="spinner-container container1"> <div class="circle1"></div> <div class="circle2"></div> <div class="circle3"></div> <div class="circle4"></div> </div> <div class="spinner-container container2"> <div class="circle1"></div> <div class="circle2"></div> <div class="circle3"></div> <div class="circle4"></div> </div> <div class="spinner-container container3"> <div class="circle1"></div> <div class="circle2"></div> <div class="circle3"></div> <div class="circle4"></div> </div> </div> <span class="loadingNow">&nbsp;正在加载中...</span></div>
         </div>
	</div>
</div> 

<script id="p_productlist" type="text/x-dot-template">
{{ if(it.list&&it.list.length>0){  }}
	{{ for(var i=0; i<it.list.length;i++){ }}
	{{var objLi=it.list[i];}}
	<li class="spacing ">
		<a href="javascript:void(0)" class='hrefProdMain' >
			<div class="w-product w-product{{=objLi.id}}" prodId="{{=objLi.id}}" specId="{{=objLi.specid==undefined?'':objLi.specid}}">
				<div class="w-images">
				{{if(objLi.ptname!=undefined){}}
					<div class="w-label">
						<p>{{=objLi.ptname}}</p>
					</div>
				{{}}}
				<img class="fruits-img w-product" src="${SHOPDOMAIN}/front/images/wap/w-Loading.png" data-original="${USERIMGSRC}{{=imgZuhe(objLi.picuri,'_300')}}" alt="{{=objLi.name}}" />
				</div>
			</div>
            <h3 class="ellipsis">{{=objLi.name}}</h3>
            <div class="price" >￥{{=objLi.price}}<span class="sizeColor">/{{=objLi.unit}}</span><!-- &nbsp;&nbsp;&nbsp;{{=objLi.salesCount}}人付款 -->
				<img class="sp-cart" src="${SHOPDOMAIN}/front/images/wap/shoppingCart1.png"   prodId="{{=objLi.id}}"/></div>
		</a>
	</li>
	{{}}}
{{}}}
</script>
<jsp:include page="foot.jsp"></jsp:include> 
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/wap/wxshare.js"></script>
<script>
document.title=""
var canScroll=true;
$(".green").hide();
$(".black").show();
//首先得到URL传过来的值
var url = decodeURI(location.search); //获取url中"?"符后的字串
var theRequest = new Object();
var name= $(".search").val();
if (url.indexOf("?") != -1) {
    var str = url.substr(1);
    strs = str.split("&");
    for(var i = 0; i < strs.length; i ++) {
        theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
    }
}
var catalogId1,catalogId2,catalogId1name,catalogId2name,search;
search = theRequest['search'];
catalogId1 = theRequest['catalogId1'];
catalogId2 = theRequest['catalogId2'];
catalogId1name = theRequest['catalogId1name'];
catalogId2name = theRequest['catalogId2name'];
	//图标排序函数
	function orderInfo(id) {
		currentNum=1;
		switch(id){
		//查询所有商品
		case 0:
			ty=null;
			var name=$(".search").val();
			$(".productlist").empty();
			productlist("","",9,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
			$("#2").removeAttr("class");
			$("#3").removeAttr("class");
			$("#4").removeAttr("class");
			$("#1").removeAttr("class");
			//箭头颜色控制
			$(".green").hide();
			$(".black").hide();
			$(".pingjiah").show();
			$(".xiaoliangh").show();
			$(".shijianhdown2").show();
			$(".jiagehdown").show();
			if(name==""||name==null){
			}else{
			document.title=name;
			}
			  break;
		//查询销量从高到低
		case 1:
			var name=$(".search").val();
			$(".productlist").empty();
			//字体显示为绿色
			$("#2").removeAttr("class");
			$("#3").removeAttr("class");
			$("#4").removeAttr("class");
			$("#1").addClass("on");

			$(".green").hide();
			$(".black").hide();
			$(".pingjiah").show();
			$(".xiaoliangg").show();
			$(".shijianhdown2").show();
			$(".jiagehdown").show();
			//$("#xiaoliangg").show();
			if($("#title").val()==" "){
				productlist("","",id,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
				}
			else{
				if($("#title").val()==catalogId1name){
					productlist(catalogId1,"",id,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
				}else{
				productlist(catalogId1,catalogId2,id,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
				}
			}
			
			//字体显示为黑色
			//$("#1").removeAttr("class");
			  break;
		//查询价格从高到低、从低到高
		case 2:
			var name=$(".search").val();
			$(".productlist").empty();
			//字体颜色控制
			$("#1").removeAttr("class");
			$("#3").removeAttr("class");
			$("#4").removeAttr("class");
			$("#2").addClass("on");
			
			if($("#title").val()==" "){
				if($(".jiagegdown").css("display").indexOf("inline") != -1){
					//箭头颜色控制
					$(".green").hide();
					$(".black").hide();
					$(".pingjiah").show();
					$(".xiaoliangh").show();
					$(".shijianhdown2").show();
					
					 $(".jiagegup").show();
		                $(".jiagegdown").hide();
	                productlist("","",id,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
	          }
	        else{
	        	//箭头颜色控制
	        	$(".green").hide();
				$(".black").hide();
				$(".pingjiah").show();
				$(".xiaoliangh").show();
				$(".shijianhdown2").show();
				
				$(".jiagegdown").show();
                $(".jiagegup").hide();
	                productlist("","",5,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
	         }
				
				}
			else{
				if($("#title").val()==catalogId1name){
					if($(".jiagegdown").css("display").indexOf("inline") != -1){
			        	$(".green").hide();
						$(".black").hide();
						$(".pingjiah").show();
						$(".xiaoliangh").show();
						$(".shijianhdown2").show();
			            	$(".jiagegup").show();
			                $(".jiagegdown").hide();
			                 productlist(catalogId1,"",id,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
			          }
			        else{
			        	$(".green").hide();
						$(".black").hide();
						$(".pingjiah").show();
						$(".xiaoliangh").show();
						$(".shijianhdown2").show();
			            	$(".jiagegdown").show();
			                $(".jiagegup").hide();
			                productlist(catalogId1,"",5,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
			         }
				}
				else{
					if($(".jiagegdown").css("display").indexOf("inline") != -1){
			        	$(".green").hide();
						$(".black").hide();
						$(".pingjiah").show();
						$(".xiaoliangh").show();
						$(".shijianhdown2").show();
			            	$(".jiagegup").show();
			                $(".jiagegdown").hide();
			                 productlist(catalogId1,catalogId2,id,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
			          }
			        else{
			        	$(".green").hide();
						$(".black").hide();
						$(".pingjiah").show();
						$(".xiaoliangh").show();
						$(".shijianhdown2").show();
			            	$(".jiagegdown").show();
			                $(".jiagegup").hide();
			                productlist(catalogId1,catalogId2,5,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
			         }
				}
			        
			}
			  break;
		//查询评价从高到低
		case 3:
			var name=$(".search").val();
			$(".productlist").empty();
			$("#2").removeAttr("class");
			$("#1").removeAttr("class");
			$("#4").removeAttr("class");
			$("#3").addClass("on");
			$(".green").hide();
			$(".black").hide();
			$(".pingjiag").show();
			$(".xiaoliangh").show();
			$(".shijianhdown2").show();
			$(".jiagehdown").show();
			if($("#title").val()==" "){
				productlist("","",id,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
				}
			else{
				if($("#title").val()==catalogId1name){
					productlist(catalogId1,"",id,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
				}else{
			productlist(catalogId1,catalogId2,id,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
				}
			}
			  break;
		//查询上架时间从高到低、从低到高
		case 4:
			var name=$(".search").val();
			$(".productlist").empty();
			$("#2").removeAttr("class");
			$("#3").removeAttr("class");
			$("#1").removeAttr("class");
			$("#4").addClass("on");
			if($("#title").val()==" "){
				if($(".shijiangdown2").css("display").indexOf("inline") != -1){
					$(".green").hide();
					$(".black").hide();
					$(".pingjiah").show();
					$(".xiaoliangh").show();
					$(".jiagehdown").show();
					
	            	$(".shijiangup2").show();
	                $(".shijiangdown2").hide();
	                productlist("","",id,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
	          }
	        else{
	        	$(".green").hide();
				$(".black").hide();
				$(".pingjiah").show();
				$(".xiaoliangh").show();
				$(".jiagehdown").show();
	        	
	            	$(".shijiangdown2").show();
	                $(".shijiangup2").hide();
	                productlist("","",6,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
	         }
				}
			else{
				if($("#title").val()==catalogId1name){
					if($(".shijiangdown2").css("display").indexOf("inline") != -1){
				    	 $(".green").hide();
							$(".black").hide();
							$(".pingjiah").show();
							$(".xiaoliangh").show();
							$(".jiagehdown").show();
					    $(".shijiangup2").show();
					    $(".shijiangdown2").hide();
				      	productlist(catalogId1,"",id,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
			      	}
				    else{
				    	$(".green").hide();
						$(".black").hide();
						$(".pingjiah").show();
						$(".xiaoliangh").show();
						$(".jiagehdown").show();
						
				    	$(".shijiangdown2").show();
				    	$(".shijiangup2").hide();
				       productlist(catalogId1,"",6,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
				       }
				}
				else{
					if($(".shijiangdown2").css("display").indexOf("inline") != -1){
				    	 $(".green").hide();
							$(".black").hide();
							$(".pingjiah").show();
							$(".xiaoliangh").show();
							$(".jiagehdown").show();
					    $(".shijiangup2").show();
					    $(".shijiangdown2").hide();
				      	productlist(catalogId1,catalogId2,id,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
			      	}
				    else{
				    	$(".green").hide();
						$(".black").hide();
						$(".pingjiah").show();
						$(".xiaoliangh").show();
						$(".jiagehdown").show();
						
				    	$(".shijiangdown2").show();
				    	$(".shijiangup2").hide();
				       productlist(catalogId1,catalogId2,6,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
				       }
				}
			     
			}
			  break;
			//搜索一级商品下的商品  
		case 8:
			var name=$(".search").val();
			$(".productlist").empty();
			$("#2").removeAttr("class");
			$("#3").removeAttr("class");
			$("#4").removeAttr("class");
			$("#1").removeAttr("class");
			//箭头颜色控制
			$(".green").hide();
			$(".black").hide();
			$(".pingjiah").show();
			$(".xiaoliangh").show();
			$(".shijianhdown2").show();
			$(".jiagehdown").show();
			productlist(catalogId1,"",1,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
			$("#title").val(catalogIdname);
			break;
		default:
			break;
		}
	}
    var ty;
    ty = theRequest['ty'];
    if(ty==1||ty==0){
    }else{
    	ty=null;
    }
	//瀑布流
	//获取所有商品的分类名称
	var data={};
	var currentNum=0;
	var totalPage=0;
	function productlist(catalogId1,catalogId2,id,name,url,data,num,isAppend,showHtmlLocationId,dotTemp) {
		if(id==0){
			$(".jiagehdown").show();
			 $(".jiagehup").hide();
			 $(".shijianhdown2").show();
			 $(".shijianhup2").hide();
		}
		if($(".shijiangdown2").css("display").indexOf("inline") != -1 || $(".shijiangup2").css("display").indexOf("inline") != -1){
			 $(".shijianhdown2").hide();$(".shijianhup2").hide();
		 }
		 if($(".jiagegdown").css("display").indexOf("inline") != -1 || $(".jiagegup").css("display").indexOf("inline") != -1){
			 $(".jiagehdown").hide();$(".jiagehup").hide();
		 }
		 if(name==null||name==""){
			 document.title="商品列表";
		 }
		$.ajax({
			url:url,
			type:"post",
			data: {
                "catalogId1": catalogId1,
                "catalogId2":catalogId2,
                "salesCount": id,
                "name":name,
                "ty"  :ty,
                "currentPage" :num
            },
			dataType:"json",
			beforeSend:function(){
				$(".jiazai").show();
			},
			success:function(result){
				var totalNum = result.pageCount;
				totalPage=totalNum;
				if(num<=totalNum || totalNum==0){
					if (result.res_code == '0') {
						/* var evalText = doT.template($("#p_productlist").text()); 
						var productList =evalText(result.list); */
						var productList = doT.template($("#"+dotTemp).html())(result);
						if(isAppend){
							var i=0;
							var prodID="";
							$("."+showHtmlLocationId).append(productList);
							if(null!=result.list &&""!=result.list){
							$(result.list).each(
									
									function(j, item){
										i++;
										if(i==1){
											prodID=item.id;
										}else{
										prodID=prodID+","+item.id;
										}
								 }); 
							getKuCunByProductId(prodID);
							}
							/* if判断是否搜索到了商品 */
							if((productList==""||productList==undefined)& num==1){
								var tempSold="<div class='w-margin27 w-margin-top couponBtn textCenter positionR w-fontsize30 w-color9  zuchunye'  stay='display:none'>  暂时没有您想要的商品哦！</div>";
			                $("."+showHtmlLocationId).append(tempSold);
							} 
						}else{
							$("."+showHtmlLocationId).html(productList);
							var i=0;
							var prodID="";
							$(reselt.list).each(function(index,element){
								i++;
								if(i==1){
									prodID=element.id;
								}else{
									prodID=prodID+","+element.id;
								}
								element.id;
							});
							getKuCunByProductId(prodID);
						}
						canScroll=true;
						$("img").lazyload({
							effect : "fadeIn"
						});
						$(".jiazai").hide();
					} else {
						showMessage(result.message);
					}
				}else{
					num=totalNum;
					$(".jiazai").hide();
					$(".w-main").css("padding-bottom","0px");
					$("."+showHtmlLocationId).append("<div style='clear: both'></div><div class='list'><center>—— 没有更多商品了 ——</center></div >");
					if(!isAppend){
						$("."+showHtmlLocationId).empty();
					}
					
				};
			}
		
	    });
	}
	
	$(function() {
		$.goTop();
		currentNum=1;
	    if (url.indexOf("?") != -1) {
	        if(search!=null&&search!=""){
	        	$(".search").val(search);
	        	orderInfo(0);
	        }else{
	        if(strs.length==2){
	        	$("#title").val(catalogId1name);
                 $(".green").hide();
	    	    if(catalogId1==null&catalogId2==null){
	    	    	$("#title").val("全部商品列表");
	    	    }
	    	    productlist(catalogId1,catalogId2,0,"","${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");	
	    	    scroll(0,0);
	        }else{
	    		$("#title").val(catalogId1name+"."+catalogId2name);
	    	    $(".green").hide();
	    	    
	    	    if(catalogId1==null&catalogId2==null){
	    	    	$("#title").val("全部商品列表");
	    	    }
	    	    productlist(catalogId1,catalogId2,0,"","${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");	
	    	    scroll(0,0);
	        }}
	    }else{
		if($("#title").val()==undefined){
			$("#title").val("全部商品列表");
		}
	    $(".green").hide();
	    if(catalogId1==null&catalogId2==null){
	    	$("#title").val("全部商品列表");
	    }
	    productlist(catalogId1,catalogId2,0,"","${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");	
	    scroll(0,0);
	    }
	    document.title=$("#title").val();
	});

$(window).scroll(function(){
    var name= $(".search").val();
	var scrollTop = $(this).scrollTop();
	var scrollHeight = $(document).height();
	var windowHeight = $(this).height();
	if(scrollHeight-(scrollTop + windowHeight) < 200&&canScroll){
		canScroll=false;
		currentNum++;
		 if($("#title").val()==" "){
			 if($("#1").hasClass("on") ){
			 productlist("","",1,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist"); 
			 }else if($(".pingjiag").css("display").indexOf("inline") != -1 ){
				 productlist("","",3,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist"); 
			 }else if($(".jiagegdown").css("display").indexOf("inline") != -1 ){
				 productlist("","",5,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
			 }else if($(".jiagegup").css("display").indexOf("inline") != -1 ){
				 productlist("","",2,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
			 }else if($(".shijiangdown2").css("display").indexOf("inline") != -1 ){
				 productlist("","",6,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
			 }else if($(".shijiangup2").css("display").indexOf("inline") != -1 ){
				 productlist("","",4,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
			 }else{
				 productlist("","",0,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
			 }
		 }
		 else{
			 if($("#title").val()==catalogId1name){
				 if($("#1").hasClass("on") ){
					 productlist(catalogId1,"",1,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist"); 
					 }else if($("#3").hasClass("on") ){
						 productlist(catalogId1,"",3,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist"); 
					 }else if($(".jiagegdown").css("display").indexOf("inline") != -1 ){
						 productlist(catalogId1,"",5,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
					 }else if($(".jiagegup").css("display").indexOf("inline") != -1 ){
						 productlist(catalogId1,"",2,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
					 }else if($(".shijiangdown2").css("display").indexOf("inline") != -1 ){
						 productlist(catalogId1,"",4,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
					 }else if($(".shijiangup2").css("display").indexOf("inline") != -1 ){
						 productlist(catalogId1,"",6,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
					 }else{
						 productlist(catalogId1,"",0,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
					 }
			 }
			 else{
				 if($("#1").hasClass("on")){
					 productlist(catalogId1,catalogId2,0,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
					 }else if($("#3").hasClass("on")  ){
						 productlist(catalogId1,catalogId2,0,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
					 }else if($(".jiagegdown").css("display").indexOf("inline") != -1 ){
						 productlist(catalogId1,catalogId2,5,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
					 }else if($(".jiagegup").css("display").indexOf("inline") != -1 ){
						 productlist(catalogId1,catalogId2,2,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
					 }else if($(".shijiangdown2").css("display").indexOf("inline") != -1 ){
						 productlist(catalogId1,catalogId2,4,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
					 }else if($(".shijiangup2").css("display").indexOf("inline") != -1 ){
						 productlist(catalogId1,catalogId2,6,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
					 }else{
						 productlist(catalogId1,catalogId2,0,name,"${SHOPDOMAIN}/interfaces/productlist.html",data,currentNum,true,"productlist","p_productlist");
					 }
			 }
		 }
		 
		}
	});
	
//检测当前页面中所有商品的库存情况
function getKuCunByProductId(prodId) {
    $.post(
        SHOPDOMAIN + '/interfaces/getKuCunByProductId.html',
        {
            prodIds : prodId,
            companyId : companyid
        },
        function(data) {
            if (data.res_code == '0') {
                var tempSoldOut = "<a class='w-soldOut'></a>";
                $(data.list).each(
                    function(i, item) {
                        $(".w-product" + item).children().eq(0)
                            .append(tempSoldOut);
                    });
            } else {
                showMessage(data.message);
            }
        }, "json").error(function() {
        showError();
    });
}


$(document).on("click",".sp-cart",function(event){
	$.ajax({
        type: "POST",
        url: SHOPDOMAIN + "/wap/shopCart/addCart.html",
        data: {
        	prodId: $(this).attr("prodId"),
            specId: $(".w-product"+$(this).attr("prodId")).attr("specid"),
            count: 1
        },
        async: true,
        success: function (res) {
            if(0 == res.res_code){
            	$.tooltip("商品已添加至购物车", 2000, true);
            	var tempCount=$(".countshoptemp").html();
            	$(".countshoptemp").html(parseInt(tempCount)+1);
            }else{
            	if(res.res_code=="1"){
            		$.dialog("confirm","","您还没有登录,点击确定去登录",0,function(){
            			  window.location.href="login.html";
            		});
            	}else{
            		$.tooltip(res.message);
            	}
            }
        },
        dataType: "json"
    });
	return false;
}); 

$(document).on("click",".hrefProdMain",function(event){
	window.location.href='productShow.html?prodId='+$(this).children().eq(0).attr("prodId");
}); 

function addCart(prodId){event.stopPropagation();//阻止冒泡事件，上级的单击事件不会被调用 
}
$(function() {
	getCountByUserId();
});
//购物车数量
function getCountByUserId() {
	$.post(SHOPDOMAIN+ '/wap/shopCart/getCountByUserId.html', function(data) {
			if (data.res_code == '0') {
				$(".countshoptemp").addClass("countshop").html(data.count);
			}
		}, "json").error(function() {
			showError();
	});
}
</script>
<script type="text/javascript"
	src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
<script src="${SHOPDOMAIN}/front/js/wap/ceju.js"></script>

