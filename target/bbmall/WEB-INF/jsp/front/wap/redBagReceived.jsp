<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/redBag.css" />
<script>
	document.title = "买生鲜上电商平台";
</script>
<div class="w-main">
	<img src="" id="shareImg" alt="" /> <input type="hidden"
		id="redBagMobile" value="${mobile }"> <input type="hidden"
		id="ordersn" value="${ordersn }">
	<div class="w-shareMiddle">
		<div class="w-shareMiddleInput">
			<div class="w-haveToReceive" id="redPrice">
				<!--已领取-->
				${redPrice }
			</div>
			<button class="w-subBtnCode" onclick="toTop();">立即使用</button>
		</div>
	</div>

	<!--领红包用户列表-->
	<div class="w-shareBottom w-paddingLR37">
		<div class="w-shareBottom-title1">
			<hr />

			<p class="w-Htitle">看朋友们手气如何</p>
			<hr />
			<div style="clear: both"></div>

		</div>
		<div class="w-shareDetails" id="redBagList"></div>

	</div>
	<div style="clear: both"></div>
	<!--规则说明-->
	<div class="w-shareBottom w-paddingLR37">
		<div class="w-shareBottom-title">
			<hr />

			<p class="w-Htitle">活动细则</p>
			<hr />
			<div style="clear: both"></div>

		</div>
		<div class="w-shareDetails"></div>

	</div>
</div>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	//时间戳转换函数
	var WXshare;
	function timeStamp2String(time) {
		var datetime = new Date();
		datetime.setTime(time);
		var year = datetime.getFullYear();
		var month = datetime.getMonth() + 1 < 10 ? "0"
				+ (datetime.getMonth() + 1) : datetime.getMonth() + 1;
		var date = datetime.getDate() < 10 ? "0" + datetime.getDate()
				: datetime.getDate();
		var hour = datetime.getHours() < 10 ? "0" + datetime.getHours()
				: datetime.getHours();
		var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes()
				: datetime.getMinutes();
		var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds()
				: datetime.getSeconds();
		return month + "-" + date + " " + hour + ":" + minute;
	}

	function getTip() {
		var arr = [ "换个姿势领，金额会更高", "此包只有天上有", "手气好棒呀", "红包领的好，心情就是好" ];
		var index = Math.floor((Math.random() * arr.length));
		return (arr[index]);
	}
</script>
<script id="interpolationtmpl" type="text/x-dot-template">
       {{for(i=0;i<it.length;i++){ }}
       {{  var bag=it[i];
       	var addtime=timeStamp2String(bag.addtime);
        var rInfo=getTip();
		}}
          <div class="w-userDetails">
                <div class="fl">
                    <div class="w-userDetailsHeader fl">
                        <img src="{{=bag.pic_url}}" alt="头像"/>
                    </div>
                    <div class="w-userNumTime">
                        <p><span>{{=bag.nick_name}}</span>{{=addtime}}</p>
                        <p>{{=rInfo}}</p>
                    </div>
                </div>
                <div class="fr w-amount">
                {{=bag.substract_price}}元
                </div>
            </div>
     {{ } }}
 
   </script>

<script type="text/javascript">
	$(function() {//
		//deleteDoctorLocalStorage("redBagMobile");
		$("#ordersn").val(GetQueryString("ordersn"));
		var ordersn = $("#ordersn").val();
		if (ordersn == null || ordersn == "") {
			ordersn = GetQueryString("ordersn");
			$("#ordersn").val(ordersn);
		}

		$.ajax({
			url : "${SHOPDOMAIN}/wap/wxShare/allRedBagByOrderSn.html",
			type : "post",
			dataType : "json",
			data : {
				ordersn : ordersn
			},
			success : function(data) {
				// alert(JSON.stringify(data));
				var evalText = doT.template($("#interpolationtmpl").html());
				$("#redBagList").html(evalText(data.list));
			}

		});
		initRedBagRule();//初始化规则
		var redBagMobile = $("#redBagMobile").val();
		var mark = GetQueryString("mark");
		if (mark == null || mark == "") {

			saveLocalStrage(redBagMobile);

		}/* else{
						  if(mark=="aleradyG"){
						  $("#redPrice").text("已抢光");
					  }else{
						  $("#redPrice").text("已抢过");
					  }
					  } */
	});

	//删除登录信息localStorage()
	function deleteDoctorLocalStorage(loginName) {
		localStorage.removeItem(loginName);

	}
	function toTop() {
		window.location.href = "${SHOPDOMAIN}/wap/index.html";
	}

	function saveLocalStrage(redBagMobile) {
		localStorage.setItem("redBagMobile", redBagMobile);

	}
	function initRedBagRule() {//初始化规则
		$.ajax({
			async : false,
			url : "${SHOPDOMAIN}/wap/wxShare/redBagRule.html",
			dataType : "json",
			type : "post",
			success : function(data) {

				var conf = data.conf;
				$(".w-shareDetails").html(conf.content);
				$("#shareImg").attr("src", USERIMGSRC + conf.picUrl);
				$("#rdBagCount").val(conf.count);
				WXshare = {
					title : "买生鲜上电商平台",
					link : SHOPDOMAIN + "/wap/redBag.html?ordersn="
							+ getRequest('ordersn'),
					desc : '电商平台专享优惠券,可以在店铺中使用',
					imgUrl : USERIMGSRC + conf.picUrl
				};
				wx.config({
					debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
					appId : '${appId}', // 必填，公众号的唯一标识
					timestamp : ${timestamp}, // 必填，生成签名的时间戳
					nonceStr : '${nonceStr}', // 必填，生成签名的随机串
					signature : '${signature}',// 必填，签名，见附录1
					jsApiList : [ 'onMenuShareTimeline', 'onMenuShareQQ',
							'onMenuShareAppMessage', 'showOptionMenu',
							'hideOptionMenu', 'onMenuShareWeibo' ]
				// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				});
			}
		});
	}

	//获取url参数
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
</script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/wap/wxshare.js"></script>
<jsp:include page="foot.jsp"></jsp:include>