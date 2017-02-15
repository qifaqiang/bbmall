<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/front/css/wap/active.css" />
<style>
body {
	background: #EEEEEE;
}
</style>
<div class="w-main">
	<!--广告-->
	<div class="activeBan"></div>
	<!--商品列表-->
	<div class="productList">
		<ul class="plist">
		</ul>
	</div>
</div>
<script id="picurl" type="text/x-dot-template">
 <img src="${USERIMGSRC}{{=imgZuhe(it.spreadUrl,'_640_251')}}" />
        <div class="activeBack">
            <a href="javascript:history.go(-1)">
            <img src="${SHOPDOMAIN}/front/images/wap/activeBack.png" alt="返回上一页"/>
            </a>
        </div>

</script>
<script id="activelist" type="text/x-dot-template">
{{for(var i=0;i<it.length;i++){ }}
           {{var obj=it[i];}}
<li>
<div class="w-product w-product{{=obj.id}}" prodId="{{=obj.id}}">
					<div class="w-images">
                <img src="${USERIMGSRC}{{=imgZuhe(obj.picuri,'_300')}}" width="206" alt=""/>
</div></div>
                <div class="activeWord">
                    <span class="proTitle ellipsis">
                        {{=obj.name}}
                    </span>
                    <span class="proPrice">
                        <i>￥{{=obj.price}}</i>/{{=obj.unit}}
                    </span>
                    <button onclick="window.location.href='productShow.html?prodId={{=obj.id}}'">立即抢购</button>
                </div>
            </li>
{{}}}
<div style='clear: both'></div><div class='list'><center>—— 没有更多商品了 ——</center></div >
</script>
<jsp:include page="foot.jsp"></jsp:include>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/wap/wxshare.js"></script>
<script>
	document.title = "促销商品";
	$(function() {
		$.goTop();
		activelist();
		promotionInfo();
	});
	var id = getRequest('id');

	function promotionInfo() {
		$.post(SHOPDOMAIN + '/interfaces/active/promotionInfo.html', {
			type : 0,
			id : id
		}, function(data) {
			if (data.res_code == '0') {
				var evalText = doT.template($("#picurl").html());
				$(".activeBan").html(evalText(data.data));
				var WXshare = {
						title :data.name,
						link : window.location.href,
						desc : '推荐一家好店，快来瞧一瞧',
						imgUrl:USERIMGSRC+data.data.spreadUrl
				}
				wx.config({
				    	debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				    	appId: '${appId}', // 必填，公众号的唯一标识
				    	timestamp:${timestamp}, // 必填，生成签名的时间戳
				    	nonceStr: '${nonceStr}', // 必填，生成签名的随机串
				    	signature: '${signature}',// 必填，签名，见附录1
				    	jsApiList: ['onMenuShareTimeline','onMenuShareQQ', 'onMenuShareAppMessage','hideMenuItems','showOptionMenu','hideOptionMenu','onMenuShareWeibo'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				});
			} else {
				showMessage(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}
	function activelist() {
		$.post(SHOPDOMAIN + '/interfaces/active/activelist.html', {
			type : 0,
			id : id,
			zu :1000,
			currentPage:1
		}, function(data) {
			if (data.res_code == '0') {
				var evalText = doT.template($("#activelist").html());
				$(".plist").html(evalText(data.list));
				getKuCunByProductId();
			} else {
				showMessage(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}
	
	//检测当前页面中所有商品的库存情况
	function getKuCunByProductId() {
		var companyid = $.cookie('sys_base_companyId');
		if (null != companyid) {
			var prod = "";
			$(".w-product").each(function() {
				prod += $(this).attr("prodId") + ",";
			})

			$.post(
					SHOPDOMAIN + '/interfaces/getKuCunByProductId.html',
					{
						prodIds : prod,
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
</script>