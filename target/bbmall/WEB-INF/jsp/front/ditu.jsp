<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="电商平台">
<meta http-equiv="description" content="电商平台">
<meta charset="UTF-8">
<!-- 启用 WebApp 全屏模式（IOS）-->
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<!--设置状态栏的背景颜色（IOS）-->
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<!-- 关闭邮箱的自动识别-->
<meta content="email=no" name="format-detection">
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;"
	name="viewport" />
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=L7uiBspEEkoroWef6mZEO66e"></script>
<script type="text/javascript"
	src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/front/js/jquery.cookie.js"></script>
<link rel="shortcut icon" href="${SHOPDOMAIN}/front/images/web/ico.ico" />
<style type="text/css">
html,body {	min-height: 0;height: 100%;background: none;padding: 0;	margin: 0;}
h1 {padding: 0;margin: 0;}
.w-main {min-height: 0;width: 100%;height: 100%;}
#allmap {width: 100%;/*height:100%;*/}
#w-header {width: 100%;height: 44px;
	/*background: salmon;*/
	color: #333333;position: relative;z-index: 1000;
	/*position: fixed;*/
	/*top: 0;*/
	/*left: 0;*/
	/*right: 0;*/
	line-height: 44px;margin: 0 auto;background: white;}
.w-left-icon {width: 17px;height: 17px;position: relative;top: 13px;text-align: center;left: 13px;cursor: pointer;
	float: left;}
.w-left-icon img {position: absolute;top: 0;left: 0;}
.w-right-icon {	height: 17px;position: relative;top: 13px;text-align: center;right: 13px;cursor: pointer;
	float: right;}
.w-right-icon img {	position: absolute;	top: 0;	right: 0;}
#w-header img {	height: 17px;}
#w-header h1 {position: absolute;top: 0;left: 10%;right: 10%;line-height: 44px;font-size: 18px;font-weight: normal;
	height: 44px;font-family: '微软雅黑'}
p {padding: 0;	margin: 5px 0;}
.chooseBtn {font-size: 14px;padding: 10px 15px;float: right;border-radius: 5px;margin-top: 10px;
	background: #fca815;color: white}
</style>
<title>选择基地</title>
<body>
<div id="w-header">
	<div class="w-left-icon">
		<!-- <a href="javascript:history.go(-1);"><img
			src="${SHOPDOMAIN}/front/images/wap/w-blackBack.png" height="0.17rem"
			alt="返回" /></a> -->
	</div>
	<h1 style="text-align:center">注：请点击红心选择基地</h1>

</div>
<div id="allmap"></div>
</body>
<script>
	var cliH = document.body.clientHeight;
	var hH = $('#w-header').height();
	var lat,lng;
	$('#allmap').height(cliH - hH + 'px');
	var wx_document=document.referrer==""?"${SHOPDOMAIN}/index.html":document.referrer;
</script>
<script type="text/javascript">
	// 百度地图API功能
	
	if(null!=$.cookie('sys_lng_lat')){
		lat=$.cookie('sys_lng_lat').split("-")[0];
		lng=$.cookie('sys_lng_lat').split("-")[1];
	}else{
		lat=117.08928;
		lng=36.67124;
	}
	
	var map = new BMap.Map("allmap");
	map.centerAndZoom(new BMap.Point(lat, lng), 12); // 初始化地图,设置中心点坐标和地图级别
	var navigationControl = new BMap.NavigationControl({
		// 靠左上角位置
		anchor : BMAP_ANCHOR_TOP_LEFT,
		// LARGE类型
		type : BMAP_NAVIGATION_CONTROL_LARGE,
		// 启用显示定位
		enableGeolocation : true
	});
	map.addControl(navigationControl);
	// 添加定位控件
	var geolocationControl = new BMap.GeolocationControl();
	geolocationControl.addEventListener("locationSuccess", function(e) {
		// 定位成功事件
		var address = '';
		address += e.addressComponent.province;
		address += e.addressComponent.city;
		address += e.addressComponent.district;
		address += e.addressComponent.street;
		address += e.addressComponent.streetNumber;
		alert("当前定位地址为：" + address);
	});
	geolocationControl.addEventListener("locationError", function(e) {
		// 定位失败事件
		alert(e.message);
	});
	map.addControl(geolocationControl);

	$.ajax({
		type : "post",
		url :  '${SHOPDOMAIN}/interfaces/getCompanyXY.html',
		async : false,
		dataType : "json",
		success : function(data) {
			if (data.res_code == '0') {
				$.each(data.list, function(name, value) {
					var sContent = "<h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+value.company_name+"</h4>"
						+ "<p>地址："+value.region_name+value.address+"</p>"
						+ "<p>电话：<a href='tel:"+value.mobile+"'>"+value.mobile+"</p>"
						+ "<a onclick='toBasicCompany("+value.company_id+",\""+value.company_name+"\");'><span class='chooseBtn'>点击选择基地</span></a>"
						+ "</div>";
					var point = new BMap.Point(value.xpostion, value.ypostion);
					var label = new BMap.Label(value.company_name, {
						offset : new BMap.Size(20, -10)
					});
					var marker = new BMap.Marker(point);
					map.addOverlay(marker);
					marker.setLabel(label);
					var infoWindow = new BMap.InfoWindow(sContent); // 创建信息窗口对象
					marker.addEventListener("click", function() {
						this.openInfoWindow(infoWindow);
					});
				});
			} else {
				showMessage(data.message);
			}
		},
		error : function() {
			showError();
		}
	});
	
	function toBasicCompany(sys_base_companyId,sys_company_name) {
		$.cookie('sys_user_address', "", {
			expires : 30
		}); // 存储一个带7天期限的 cookie
		$.cookie('sys_base_companyId', sys_base_companyId, {
			expires : 30
		});
		$.cookie('sys_address_auto', "false", {
			expires : 30
		});
		$.cookie('sys_company_name', sys_company_name, {
			expires : 30
		});
		window.location.href=wx_document;
	}
</script>
