<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<jsp:include page="topShop.jsp"></jsp:include>
<!--轮播-->
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/flexslider.css" />
<!--首页-->
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/index.css" />

<!--轮播-->
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/jquery.flexslider-min.js"></script> 
<!--公告-->
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/web/jq_scroll.js"></script> 
<!--主要内容-->

<style>
.w-labelzu {
    background: #fca815 none repeat scroll 0 0;
    border: 1px solid #fca815;
    color: #fff;
    left: 3px;
    position: absolute;
    text-align: center;
    top: 0;
    width: 40px;
}

.w-labelzu p {
    border: 3px solid #fff;
    font-size: 18px;
    padding: 3px;
    position: relative;
}

</style>
<div id="mainW">
    <!--轮播-->
    <div class="container_b">
        <div class="flexslider">
            <ul class="slides">
             	<#list rplist as prop>  
             	<#if prop.pic_url??>
       				<li style="background: url('${USERIMGSRC}${prop.pic_url?replace('.','_1920_350.')}') center"><a href="${prop.link_url}"></a></li>
 				</#if>
 				</#list>  
            </ul>
        </div>
    </div>
    <script>
        $(function () {
            $(".flexslider").flexslider({
                slideshowSpeed: 5000, //展示时间间隔ms
                animationSpeed: 1000, //滚动时间ms
                touch: true, //是否支持触屏滑动(比如可用在手机触屏焦点图)
                pauseOnHover: true//鼠标滑向滚动内容时，是否暂停滚动
            });
        });
    </script>
    <!--基地直送。。。公告-->
    <!--
    <div class="wrapW">
        <div class="secNavW">
            <ul class="addsecNavW">
                    <li class="announcement">
	                    <div class="announceTitle">
	                        <span>系统公告</span>
	                        <a href="announcementList.html" class="annMore">more</a>
	                    </div>
	                    <style>
	
	                    </style>
	
	                    <div id="scrollDiv">
	                        <ul id='sysnotice'>
	                        </ul>
	                    </div>
                </li>
            </ul>
        </div>
    </div>
	-->
    <!--广告位-->
    <div class="secBanner wrapW marginB25 tcW">
    	<#list adlist as prop>  
    		 	<a href="${prop.url}"><img src="${SHOPDOMAIN}/front/images/wap/w-Loading.png"
						data-original="${USERIMGSRC}${prop.usepicurl}" height="200" alt=""/></a>
 		</#list>  
    </div>


    <!--新品推荐-->
    <div class="firstModule wrapW">
        <!--广告-->
        <div class="moduleTitle">
        	新品推荐
        </div>

        <!--商品列表-->
        <div class="productList marginB25 borderTopB">
            <!--第一行-->
            <ul class="firstLine3">
           		<#list tuiProduct as prop> 
               	<#if prop_index lt 3>
	                <a href="${SHOPDOMAIN}/productDetail.html?prodId=${prop.id?c}">
	                    <li><div class="posRelaivenone">
	                        <img prodId="${prop.id?c}"  class="w-product w-product${prop.id?c} zoomImg" src="${SHOPDOMAIN}/front/images/wap/w-Loading.png" class="zoomImg"
								data-original="${USERIMGSRC}${prop.picuri?replace('.','_300.')}" width="190" alt=""/>
							</div>
	                        <div class="firstLineName">
	                            <p class="productName ellipsisW">${prop.name}</p>
	                            <p class="FirproductPrice">￥${prop.price?string("0.00")}</p>
	                            <p class="salesNum">已售<i>${prop.sales_count}</i>件</p>
                    		</div>
	                    </li>
	                </a>
                </#if>
                </#list>
            </ul>

            <!--第二行-->

            <ul class="secLine5">
            	<#list tuiProduct as prop> 
               	<#if prop_index gt 2>
	               <a href="${SHOPDOMAIN}/productDetail.html?prodId=${prop.id?c}">
                    <li><div class="posRelaivenone">
	                        <img prodId="${prop.id?c}" class="w-product w-product${prop.id?c} zoomImg" src="${SHOPDOMAIN}/front/images/wap/w-Loading.png" class="zoomImg"
								data-original="${USERIMGSRC}${prop.picuri?replace('.','_300.')}" width="190" alt=""/>
						</div>
						<div style="clear:both"></div>
                        <div class="secLineName">
                            <p class="secProName ellipsisW">${prop.name}</p>
                            <p class="secSalesNum frW">已售<i>${prop.sales_count}</i>件</p>
                            <p class="secProPrice">￥${prop.price?string("0.00")}</p>
                        </div>
                    </li>
                </a>
                </#if>
                </#list>
            </ul>
        </div>
    </div>

    <!--产品分类 水果-->
    <#list productCatalogLIst as mainprop> 
    <div class="secModule wrapW info-item  info-item${mainprop_index}">
        <!--广告-->
        <div class="firstMouduleBan marginB25 tcW">
        	<#list adlist7 as ad>
			    <#if ad_index ==mainprop_index>
			    	<#if ad.usepicurl??>
			    	<a href="${ad.url}">
			    		<img src="${SHOPDOMAIN}/front/images/wap/w-Loading.png"
						data-original="${USERIMGSRC}${ad.usepicurl}" height="100" alt=""/>
						</a>
			    	</#if>
			    </#if>
			</#list> 
        </div>
        <div class="moduleTitle">
           	 ${mainprop.name}
        </div>
        <div class="productList marginB25 borderTopB">
            <ul class="secLine5">
            	<#list mainprop.productList as prop> 
                <a href="${SHOPDOMAIN}/productDetail.html?prodId=${prop.id?c}">
                    <li>
                    	<div class="posRelaivenone">
                    	<#if prop.ptname??>
                    	                    <div class="w-labelzu" style="z-index:100">
						                 <p style="border-width:2px;" class="w-labelPzu">${prop.ptname}</p>
				                        	</div>
				        </#if>
	                        <img  prodId="${prop.id?c}" class="w-product zoomImg w-product${prop.id?c}" src="${SHOPDOMAIN}/front/images/wap/w-Loading.png" class="zoomImg"
								data-original="${USERIMGSRC}${prop.picuri?replace('.','_300.')}" width="207" alt=""/>
						</div>
						<div style="clear:both"></div>
                        <div class="secLineName">
                            <p class="secProName ellipsisW">${prop.name}</p>
                            <p class="secSalesNum frW">已售<i>${prop.sales_count}</i>件</p>
                            <p class="secProPrice">￥${prop.price?string("0.00")}</p>
                        </div>
                    </li>
                </a>
                </#list>
            </ul>
        </div>

    </div>
    </#list>

</div>

<!--左侧楼梯导航-->

<div class="leftside">

    <ul>
    <#list productCatalogLIst as prop>  
        <li class="">
        <div class="imgFdiv imgLeftW">
       	<#if prop.pcPic??> 
			<img src="${USERIMGSRC}${prop.pcPic?replace('.','_100GRAY.')!''}" width="40" class=" gray" alt=""/>
    	</#if> 
    <i class="imgNextI"></i>
</div>
            
            <div class="divLeftH">
            	${prop.name}
            </div>
        </li>
 	</#list>  
    </ul>
</div>

<script id="sysnoticetmpl" type="text/x-dot-template">
	{{for(var i=0;i<it.length;i++){ }}
   		{{var obj=it[i];}}
		 <li class="ellipsisW"  ><em class="circleW"></em><a href="${SHOPDOMAIN}/announcementDetails.html?id={{=obj.id}}">{{=obj.title}}</a></li>
	{{}}}
</script>
<script id="adtmpl" type="text/x-dot-template">
	{{for(var i=0;i<it.length;i++){ }}
   		{{var obj=it[i];}}
		{{if(undefined!=obj.usepicurl&&undefined!=obj.url&&''!=obj.usepicurl&&''!=obj.url){}}
		<li>
			<a href="{{=obj.url}}"> <img alt="{{=obj.name}}"
				src="${USERIMGSRC}{{=obj.usepicurl}}">
			</a>
		</li>
		{{}}}
	{{}}}
</script>
<script>
    $(function () {
    	//左侧楼梯导航
        var side = $(".leftside ul li");

		//浏览器大小变化时，导航改变位置
        window.onresize = function () {
            var w = $(window).width();
            var leftWhiteSpace = (w - 1170) / 2 //左边留白

            var fixLeft = leftWhiteSpace - 70
            $('.leftside').css("left", fixLeft + 'px')
        }
        window.onscroll = function () {
            var t = $(document).scrollTop(), h = $(window).height();
            var tH = $(document).height();
            var w = $(window).width();
            var screenheight = window.screen.availHeight; //获取屏幕高
            var sw = window.screen.availWidth;
            var leftWhiteSpace = (w - 1170) / 2 //左边留白
            var fixLeft = leftWhiteSpace - 70
            $('.leftside').css("left", fixLeft + 'px')
            var bodyheight = document.body.clientHeight;//获取body高
            var scr = $(window).scrollTop();

            if (t > 1000 && h + scr < document.body.clientHeight - 150) {
                $('.leftside').show();
            } else {
                $('.leftside').hide();
            }

            $(".info-item").each(function (e) {
                var a = $(this).offset().top;
                if (t > a - h / 2) {
                    $(side).removeClass("active");
                    $(side).eq(e).addClass("active");
                }
            });
        }
        $(side).click(function () {
            _this = this,
                    $("html, body").animate({
                                scrollTop: $(".info-item").eq($(this).index()).offset().top
                            },
                            500,
                            function () {
                                $(side).removeClass("active"),
                                        $(_this).addClass("active")
                            })
        });
        var companyId = $.cookie('sys_base_companyId');
		if (companyId == null || companyId == "") {
			//首页弹出框
			/****
			var index1 = layer.open({
				title : false,
				type : 1,
				closeBtn : false,
				area : [ '700px', '360px' ],
				skin : 'layer-ext-myskinIndex',
				content : $('#indexlayer')
			});
			$('#close').click(function() {
				setDefaultCompanyId(1);
				layer.close(index1);
			})
			$('#choose').click(function() {
				window.location.href = SHOPDOMAIN + '/ditu.html';
				layer.close(index1);
			})
			$('#default').click(function() {
				setDefaultCompanyId(1);
				layer.close(index1);
			})
			***/
			setDefaultCompanyId(1);
		} else {
			getKuCunByProductId();
			//$(".baseNameW").html($.cookie('sys_company_name'));
		}
        
    })
   
		
    
    
     //公告滚动
    $(function () {
        $("#scrollDiv").Scroll({line: 1, speed: 800, timer: 1000, up: "but_up", down: "but_down"});
        $('.rightFixed').find('li').hover(function () {
            $(this).addClass('on');
        }, function () {
            $(this).removeClass('on');
        })
        noticeList();
      	getAd();
        
    })
    
    //获取公告
	function noticeList() {
		$.post(SHOPDOMAIN + '/interfaces/noticeList.html', function(data) {
			if (data.res_code == '0') {
				var evalText = doT.template($("#sysnoticetmpl").html());
				$("#sysnotice").html(evalText(data.list));
			} else {
				showMessage(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}
	
	//获取公告
	function AdList() {
		$.post(SHOPDOMAIN + '/interfaces/noticeList.html', function(data) {
			if (data.res_code == '0') {
				var evalText = doT.template($("#sysnoticetmpl").html());
				$("#sysnotice").html(evalText(data.list));
			} else {
				showMessage(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}
	
	//获取广告专区
	function getAd() {
		$.post(SHOPDOMAIN + '/interfaces/getXuanChuan.html',{type:11}, function(data) {
			if (data.res_code == '0') {
				var evalText = doT.template($("#adtmpl").html());
				$(".addsecNavW").prepend(evalText(data.list));
			} else {
				showMessage(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}
</script>
<jsp:include page="footShop.jsp"></jsp:include>