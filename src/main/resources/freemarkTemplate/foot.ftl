<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--/*回到顶部*/-->
<!--弹窗登陆-->
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/wap/jquery.validate.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery-validation/js/additional-methods.min.js"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/admin/pages/scripts/form-validation-pc.js"></script>
<script src="${SHOPDOMAIN}/system/js/jquery.form.js"></script>
<!--底部-->
<div id="footerW" style="margin-top:50px">
    <div class="footBarW">
        <div class="wrapW ofW">
            <ul>
                <li class="barL1">
                    <div>
                        <span class="barTitle">专业配送</span>

                        <span>与时俱进，鲜果速达</span>
                    </div>
                </li>
                <li class="barL2">

                    <div>
                        <span class="barTitle">基地直供</span>

                        <span>农田到餐桌，0时差</span>
                    </div>
                </li>
                <li class="barL3">

                    <div>
                        <span class="barTitle">43项检测</span>

                        <span>安全无农残</span>
                    </div>
                </li>
                <li class="barL4">

                    <div>
                        <span class="barTitle">满20免运费</span>

                        <span>今日下单今日送单</span>
                    </div>
                </li>
            </ul>
        </div>
    </div>


    <div class="footert">
    <#list sclist as prop>  
	  	<dl>
	        <dt><img src="${USERIMGSRC}<#if prop.pic?exists>${prop.pic?replace('.','_28_28.')}</#if>"/>${prop.name}</dt>
	        <#list prop.sn as propSub>  
		        <dd><a onclick="window.location.href='helpCenter.html?id=${propSub.id?c}'">${propSub.title}</a></dd>
	 		</#list>  
	    </dl>
 	</#list>  
    </div>

    <div class="wrapW">
        <div class=" bottomW">
            <p class="footerhotLineW">客服热线：${sp.mobile!''}</p>

            <p>版权所有 © ${sp.copyrights!''} </p>
        </div>
    </div>
</div>
<!--弹窗登陆-->
	<div class="loginFormP layerLoginBox12" style="display:none;margin:0">
				<div class="formTitle clearfixW">
					<span class="formTitleL">齐鲁干烘茶城会员</span> <a href="registered.html"><span
						class="formTitleR">立即注册</span></a>
				</div>
				<form action="${SHOPDOMAIN}/userLogin.html" class="form-horizontal "
					id="form_login" method="post">
					<div class="formInput formInput1">
						<label for="loginUsername"></label> <input type="text"
							class="inputStyle  loss w_require" placeholder="手机号"
							id="loginUsername" name="loginName" autocomplete="off" />
					</div>
					<div class="formInput formInput2 ">
						<label for="loginPass"></label> <input type="password"
							class="inputStyle w_passwd" placeholder="密码" id="loginPass"
							name="loginPwd" autocomplete="off" />
					</div>

					<div class="checkBoxSelf">
						<input type="checkbox" id="checkBoxL" name="checkBoxL" value="0" />
						<label for="checkBoxL">自动登录</label> <a href="forgotPassword.html"><span
							class="frW forgotPassL">忘记密码</span></a>
					</div>
					<div class="submitL">
					     <input type="hidden" name="url" id="urlP" />
						<input type="button" class="subBtnL w_save" value="登陆" />
					</div>
				</form>
			</div>
<!--弹窗登陆-->

<script>

//登陆弹窗
function loginP(url){
	layer.open({
        type:1,
        title:false,
        closeBtn:2,
        shade:0.3,
        area:['347px','365px'],
        content:$('.layerLoginBox12')
    });
	if ($.cookie('username') != undefined) {
		$("input[name='loginName']").val($.cookie('username'));
	}
	if ($.cookie('checkBoxL') != undefined) {
		$("#checkBoxL").attr("checked",'true');
	}
	$("#urlP").val(url);
	FormValidation.initLogin();
}
$(".submitL").click(function() {
	if($("#checkBoxL").is(':checked')){
		$.cookie('checkBoxL', 'true', {
			expires : 30
		});
	}else{
		$.cookie('checkBoxL', null);
	}
	$.cookie('username', $("input[name='loginName']").val(), {
		expires : 30
	});
	$("#form_login").submit();
});
	
/****	
function setDefaultCompanyId(type){
	$.ajax({
			type : "post",
			url :  '${SHOPDOMAIN}/interfaces/getCompanyXYOne.html',
			async : false,
			dataType : "json",
			success : function(data) {
				if (data.res_code == '0') {
					$.cookie('sys_user_address', "", {
						expires : 30
					}); // 存储一个带7天期限的 cookie
					$.cookie('sys_base_companyId', data.sys_base_companyId, {
						expires : 30
					});
					$.cookie('sys_address_auto', "false", {
						expires : 30
					});
					$.cookie('sys_company_name', data.sys_company_name, {
						expires : 30
					});
					$(".baseNameW").html($.cookie('sys_company_name'));
					if(type==1){
						getKuCunByProductId();
					}else{
						window.location.href=window.location.href;
					}
					
				} else {
					showMessage(data.message);
				}
			},
			error : function() {
				showError();
			}
		});
}
**/
function setDefaultCompanyId(type){
	$.ajax({
			type : "post",
			url :  '${SHOPDOMAIN}/interfaces/getCompanyXYById.html',
			async : false,
			dataType : "json",
			data:{companyId:39},
			success : function(data) {
				if (data.res_code == '0') {
					$.cookie('sys_user_address', "", {
						expires : 30
					}); // 存储一个带7天期限的 cookie
					$.cookie('sys_base_companyId', data.sys_base_companyId, {
						expires : 30
					});
					$.cookie('sys_address_auto', "false", {
						expires : 30
					});
					$.cookie('sys_company_name', data.sys_company_name, {
						expires : 30
					});
					//$(".baseNameW").html($.cookie('sys_company_name'));
					if(type==1){
						getKuCunByProductId();
					}else{
						window.location.href=window.location.href;
					}
					
				} else {
					showMessage(data.message);
				}
			},
			error : function() {
				showError();
			}
		});
}
$(function() {
	$('.rightFixed').find('li').hover(function () {
		$(this).addClass('on');
	}, function () {
		 $(this).removeClass('on');
	})
	$("img").lazyload({
		effect : "fadeIn"
	});
})

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
						var tempSoldOut = '<div class="noneProing"><img src="${SHOPDOMAIN}/front/images/outstock_tag.png"  alt=""/></div>';
						$(data.list).each(
								function(i, item) {
									$(".w-product" + item).after(tempSoldOut);
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
</body>
</html>