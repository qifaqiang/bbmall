<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="top.jsp"></jsp:include>
<script type="text/javascript">
	document.title="买生鲜上齐鲁干烘茶城";
	var orderId = GetQueryString("ordersn");
	$("#ordersn").val(orderId);
	var redBagMobile = localStorage.getItem("redBagMobile");

	if (redBagMobile != null && redBagMobile != "") {

		window.location.href = "${SHOPDOMAIN}/wap/wxShare/getSnsInfo.html?mobile="
				+ redBagMobile + "&ordersn=" + orderId + "&ifRmobile=have";

	}

	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
</script>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/global.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/redBag.css" />
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
	var WXshare;
	$(function() {

		/*  if(initOrder(orderId)){
		  initRedBagRule();
		}   */
		$("#ordersn").val(orderId);
		initRedBagRule();

		$(".w-codeBtn").click(function() {

			if ($("#phones").val().trim() != "") {
				getPhoneCode(this);
			} else {
				$.tooltip("请输入手机号再获取验证码");
			}
		});

		$("#toGetRecod").click(function() {
			if ($("#phones").val().trim() == "") {
				$.tooltip("请输入手机号");
				return false;
			}
			if ($("#codes").val().trim() == "") {
				$.tooltip("请输入验证码");
				return false;
			}

			checkCode();

		});

	});
	//校验验证码

	function checkCode() {

		var phone = $("#phones").val();
		var code = $("#codes").val();
		var ordersn = $("#ordersn").val();
		$.ajax({
			url : "${SHOPDOMAIN}/wap/wxShare/toWxShareCode.html",
			type : "post",
			data : {
				"phone" : phone,
				"code" : code,
				"ordersn" : ordersn
			},
			dataType : "json",
			success : function(data) {
				// alert(JSON.stringify(data));
				if (data.res_code == "0") {
					window.location.href = data.res_url;
				} else {
					$.tooltip("验证码不正确");
					return false;
				}
			}
		});

	}
    //判断是否抢过红包
	function ifHaveGetRecord(mobile, ordersn) {
		var result = 0;
		$.ajax({
			async : false,
			url : "${SHOPDOMAIN}/wap/wxShare/selectIfHaveRecord.html",
			dataType : "json",
			data : {
				"mobile" : mobile,
				"ordersn" : ordersn
			},
			type : "post",
			success : function(data) {
				result = data.result;
			}
		});
		return result;
	}
	function test() {
		$.tooltip('昵称还没填呢...');
	}
//判断红包个数
	function ifAboveCount(ordersn) {
		var count = "0";
		$.ajax({
			async : false,
			url : "${SHOPDOMAIN}/wap/wxShare/selecCountByOrdersn.html",
			dataType : "json",
			data : {
				"ordersn" : ordersn
			},
			type : "post",
			success : function(data) {
				count = data.count;
			}
		});
		return count;
	}

	/* function getRedBagMobile(){
	  var redBagMobile=localStorage.getItem("redBagMobile");
	  return redBagMobile;
	}
	 */
//获取验证码
	function getPhoneCode(kthis) {
		$.post(SHOPDOMAIN + '/getPhoneCode.html', {
			phone : $("#phones").val(),
			type : 5
		}, function(data) {
			if (data.res_code == '0') {
				$.tooltip('发送成功~', 2500, true);
				time(kthis);
			} else {
				showMessage(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}
    //规则信息
	function initRedBagRule() {
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
					title : "买生鲜上齐鲁干烘茶城",
					link : SHOPDOMAIN + "/wap/redBag.html?ordersn="
							+ getRequest('ordersn'),
					desc : '齐鲁干烘茶城专享优惠券,可以在店铺中使用',
					imgUrl : USERIMGSRC + conf.picUrl
				}
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

	/**判断是否可以的orderid
	 */
	function initOrder(orderId) {
		var boolean = false;
		$.ajax({
			async : false,
			url : "${SHOPDOMAIN}/wap/wxShare/judgmentOrderState.html",
			dataType : "json",
			data : {
				"orderId" : orderId
			},
			type : "post",
			success : function(data) {
				// alert(data.orders.status);
				var status = data.orders.status;
				if (status == "20" || status == "22" || status == "30"
						|| status == "40") {
					boolean = true;
				}
			}

		});
		return true;
	}

	var wait = 60;

	function time(o) {

		if (wait == 0) {

			o.removeAttribute("disabled");

			o.value = "再次获取验证码";

			wait = 60;

		} else {

			o.setAttribute("disabled", true);

			o.value = "重新发送(" + wait + ")";

			wait--;

			setTimeout(function() {

				time(o);

			},

			1000);

		}

	}
</script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/wap/wxshare.js"></script>
<div class="w-main">
	<img src="" id="shareImg" alt="" />

	<div class="w-shareMiddle">
		<div class="w-shareMiddleInput">
			<form action="${SHOPDOMAIN}/wap/wxShare/toWxShareCode.html"
				id="">
				<p class="w-inputP">输入手机号领取红包</p>
				<div class="w-padding1020">
					<input type="text" class="w-phoneInput w_mobile  w_require"
						name="phone" id="phones" placeholder="请输入手机号" />
				</div>
				<div class="w-padding102050">
					<input type="text" class="w-codeInput w_require w-validationInput"
						id="codes" name="code" placeholder="请输入验证码" />
				</div>
				<input id="ordersn" type="hidden" name="ordersn"> <input
					id="rdBagCount" type="hidden" name=""> <input type="button"
					class="w-codeBtn" value="获取验证码" id="hsbtn" /> <br /> <input
					type="button" class="w-subBtn w-subBtnCode" id="toGetRecod"
					value="马上领取" />

			</form>
		</div>
	</div>


	<div class="w-shareBottom w-paddingLR37">
		<div class="w-shareBottom-title">
			<hr />

			<p class="w-Htitle">活动细则</p>
			<hr />
			<div style="clear: both"></div>
		</div>
		<div class="w-shareDetails  "></div>
	</div>
</div>
<jsp:include page="foot.jsp"></jsp:include>