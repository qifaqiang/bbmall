<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>电商平台</title>
    <!--全局初始化-->
    <link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/commonW.css" />
    <link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/globalW.css" />
    <link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/login.css"/>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/jquery.validate.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/web/common.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery-validation/js/additional-methods.min.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/layer/layer.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/web/commonFront.js"></script>	
<script src="${SHOPDOMAIN}/system/theme/assets/admin/pages/scripts/form-validation-pc.js"></script>
<script src="${SHOPDOMAIN}/front/js/jquery.lazyload.min.js"></script>
<script src="${SHOPDOMAIN}/system/js/jquery.form.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/jquery.cookie.js"></script>
    <title>电商平台注册</title>
</head>
<body>
<div id="loginHeader" class="wrapW">
    <div class="loginHeaderL">
        <a href="index.html">
            <img src="${SHOPDOMAIN}/front/images/web/pcLogo.png" width="210" height="45" alt=""/></a>
       &nbsp; 欢迎注册
    </div>
</div>
<div class="loginForm">
    <div class="wrapW loginFormA">
        <div class="loginFormP" style="margin-top:10px;">
            <div class="formTitle clearfixW">
                <span class="formTitleL">手机注册</span>
                <a href="login.html"><span class="formTitleR">请登录</span></a>
                <span class="formTitleHave">已有账户</span>
            </div>
            <form action="${SHOPDOMAIN}/userRegister.html" id="form_regis" method="post">
            <span class="war"></span>
                <div class="formInput height42">
                    <label for="telRes" class="labelTel">手机号码</label>
                    <input type="text" id="phones" name="phone" class="tel telRes w_mobile w_merchants_loginPC w_require" placeholder="请输入手机号"/>
                </div>
                <div class="formInput height42 ">
                    <label for="telRes2" class="labelTel">手机验证码</label>
                    <input type="text" id="telRes2"  name="code" style="width: 80px;" class="tel telRes w-validationInput w_require "/>
                    <!--<div >获取验证码</div>-->
                    <input type="button" class="getCodeR" id="hsbtn" value="获取验证码"/>
                </div>
                <div class="formInput height42">
                    <label for="telRes3" class="labelTel">输入密码</label>
                    <input type="password" id="telRes3" class="tel telRes w_passwd" placeholder="请输入密码6~11个字符" id="loginPass"
							name="pwd" autocomplete="off"/>
                </div>
                <div class="formInput height42">
                    <label for="telRes4" class="labelTel">确认密码</label>
                    <input type="password" id="telRes4" class="tel telRes"/>
                    <img class="mimg getCodeR mi" src="${SHOPDOMAIN}/front/images/wap/w-success.png" alt="首页" />
                    <div class="getCodeR mimawar mima  mi"  value=""/>两次密码不一致！</div>
                </div>
                <span class="warP" id="imageP"></span>
                <%-- <div class="formInput height42 ">
                    <label for="telRes6" class="labelTel">图形验证码</label>
                    <input type="text" id="telRes6" style="width: 80px;" class="tel telRes"/>
                    <img
					src="${SHOPDOMAIN}/captcha.svl" class="getCodeR fr w-getCode1  shezhi" alt=""
					onclick="chCaptcha();" id="capImg" />
                </div> --%>
                <div class="submitL">
                    <input type="button" class="subBtnL" value="注册"/>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="footLogin ">
    <div class="loginFirstL">
        客服热线：${SYSPROPORTION.mobile}
    </div>
    <div class="loginsecL">
        版权所有 ©${SYSPROPORTION.copyrights}
    </div>
</div>
<script>
var SHOPDOMAIN = "${SHOPDOMAIN}";
$('.mimg').hide();
$('.mima').hide();
//选中时改变他的背景色
$('.tel').focus(function (){
    $(this).parent().addClass('itemFocus');
});
//失去焦点时变为蓝色
$('.tel').blur(function (){
    $(this).parent().removeClass('itemFocus');
});
$(".submitL").click(function() {
	if($("#telRes3").val()==$("#telRes4").val()){
	$("#form_regis").submit();
	}else{
		showMessage("请输入密码 两次密码不一致！")
	}
});
document.title="注册";
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
			time(o)
		}, 1000)
	}
}

function getPhoneCode(kthis) {
	$.post(SHOPDOMAIN + '/getPhoneCode.html', {
		phone : $("#phones").val(),
		type : 1
	}, function(data) {
		if (data.res_code == '0') {
			showMessageAutoTime("发送成功~", 2500);
			time(kthis);
		} else {
			showMessage(data.message);
		}
	}, "json").error(function() {
		showError();
	});
}

document.getElementById("hsbtn").onclick = function() {
	if ($("#phones").val().trim() != "") {
		getPhoneCode(this);
	} else {
		showMessage("请输入手机号再获取验证码");
	}

}
</script>
    <script>
	jQuery(document).ready(function() {
		FormValidation.initRegis();
	});
</script>
</body>
</html>
