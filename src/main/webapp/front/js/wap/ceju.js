window.onload = getMyLocation;
var map;
var gpsPoint;
var baiduPoint;
var baiduAddress;
var sys_address_auto = true;
var sys_base_companyId;
var new_user = true;
var sys_company_name;
var sys_addresss;
var wx_lat;
var wx_lng;

function getMyLocation() {
	var fromCompanyId=getRequest("companyId");
	fromCompanyId = 39;
	if(undefined!=fromCompanyId){
		localStorage.setItem("fromCompanyId",fromCompanyId);
		$.ajax({
			type : "post",
			url : SHOPDOMAIN + '/interfaces/getCompanyXYById.html',
			data :{companyId:fromCompanyId},  
			async : false,
			dataType : "json",
			success : function(data) {
				if (data.res_code == '0') {
					$.cookie('sys_company_name', data.sys_company_name, {
						expires : 7
					});
					$.cookie('sys_base_companyId', data.sys_base_companyId, {
						expires : 7
					});
					$.cookie('sys_address_auto',"false", {
						expires : 7
					});
					$(".w-address").html($.cookie('sys_company_name'));
					return false;
				} else {
					showMessage(data.message);
				}
			},
			error : function() {
				showError();
			}
		});
	}
	if (null != $.cookie('sys_address_auto')) {// cookie中存在记录信息
		new_user = false;
		if ($.cookie('sys_address_auto') == "false") {// 用户手动选择过
			sys_address_auto = false;
		} else {// 自动选择
			sys_address_auto = true;
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(displayLocation,
						displayError, {
							enableHighAccuracy : true,
							timeout : 2000,
							maximumAge : 36000
						});
			} else {
				$.dialog('alertHasOk', '', '为了更好的给您提供服务，请先选择距离您收货地最近的基地', 0,
						function() {
					window.location.href = SHOPDOMAIN + '/wap/ditu.html'
						});
			}
		}
		$(".w-address").html($.cookie('sys_company_name'));
	} else {// 未存在，全新用户进入
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(displayLocation,
					displayError, {
						enableHighAccuracy : true,
						timeout : 2000,
						maximumAge : 36000
					});
		} else {
			$.dialog('alertHasOk', '', '为了更好的给您提供服务，请先选择距离您收货地最近的基地', 0,
					function() {
				window.location.href = SHOPDOMAIN + '/wap/ditu.html'
					});
		}
	}
}

function displayLocation(position) {
	var latitude = position.coords.latitude;
	var longitude = position.coords.longitude;
	map = new BMap.Map("map");
	gpsPoint = new BMap.Point(longitude, latitude); // 创建点坐标
	map.centerAndZoom(gpsPoint, 15);
	// 根据坐标逆解析地址
	var geoc = new BMap.Geocoder();
	BMap.Convertor.translate(gpsPoint, 0, translateCallback);
	return false;

}

translateCallback = function(point) {
	baiduPoint = point;
	wx_lat=baiduPoint.lat;
	wx_lng=baiduPoint.lng;
	var km = 0;
	$.ajax({
		type : "post",
		url : SHOPDOMAIN + '/interfaces/getCompanyXY.html',
		async : false,
		dataType : "json",
		success : function(data) {
			if (data.res_code == '0') {
				$.each(data.list, function(name, value) {
					var kmTemp = GetDistance(baiduPoint.lat, baiduPoint.lng,
							value.ypostion, value.xpostion);
					if (km == 0) {
						km = kmTemp;
						sys_base_companyId = value.company_id;
						sys_company_name = value.company_name;
					} else {
						if (km > kmTemp) {
							km = kmTemp;
							sys_base_companyId = value.company_id;
							sys_company_name = value.company_name;
						}
					}
				});
			} else {
				showMessage(data.message);
			}
		},
		error : function() {
			showError();
		}
	});
	var geoc = new BMap.Geocoder();
	geoc.getLocation(baiduPoint, getCityByBaiduCoordinate);
}

function getCityByBaiduCoordinate(rs) {
	baiduAddress = rs.addressComponents;
	var addresss = baiduAddress.province + "," + baiduAddress.city + ","
			+ baiduAddress.district + "," + baiduAddress.street + ","
			+ baiduAddress.streetNumber;
	
	sys_addresss=addresss;
	var marker = new BMap.Marker(baiduPoint); // 创建标注
	map.addOverlay(marker); // 将标注添加到地图中
	
	if (new_user) {// 系统初始化
		cookieInit();
	} else {
		if (sys_address_auto) {// 系统自动选择，那么就忽略，
			if ($.cookie('sys_user_address') == address) {// 旧信息与当前信息一致，只需要重新赋值就可以
				cookieInit();
			} else {// 与旧信息不一致，提示用户是否切换基地
				$.dialog('confirm', '', '您的地理位置发生了变化，是否切换基地', 0, function() {
					window.location.href = SHOPDOMAIN + '/wap/ditu.html'
				});
			}
		} else {//

		}
	}
	// alert("sys_user_address" + $.cookie('sys_user_address'));
	// alert("sys_base_companyId" + $.cookie('sys_base_companyId'));
	// alert("sys_address_auto" + $.cookie('sys_address_auto'));
}

function Rad(d) {
	return d * Math.PI / 180.0;// 经纬度转换成三角函数中度分表形式。
}

function GetDistance(lat1, lng1, lat2, lng2) {

	var radLat1 = Rad(lat1);
	var radLat2 = Rad(lat2);
	var a = radLat1 - radLat2;
	var b = Rad(lng1) - Rad(lng2);
	var s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
			+ Math.cos(radLat1) * Math.cos(radLat2)
			* Math.pow(Math.sin(b / 2), 2)));
	s = s * 6371.004;// EARTH_RADIUS;
	s = Math.round(s * 10000) / 10000; // 输出为公里
	// s=s.toFixed(4);

	return s;
}

function computeDistance(startCoords, destCoords) {
	var startLatRads = degreesToRadians(startCoords.latitude);
	var startLongRads = degreesToRadians(startCoords.longitude);
	var destLatRads = degreesToRadians(destCoords.latitude);
	var destLongRads = degreesToRadians(destCoords.longitude);

	var Radius = 6371.004; // radius of the Earth in km
	var distance = Math.acos(Math.sin(startLatRads) * Math.sin(destLatRads)
			+ Math.cos(startLatRads) * Math.cos(destLatRads)
			* Math.cos(startLongRads - destLongRads))
			* Radius;

	return distance;
}

function degreesToRadians(degrees) {
	radians = (degrees * Math.PI) / 180;
	return radians;
}

// ------------------ End Ready Bake -----------------

function displayError(error) {

	var errorTypes = {
		0 : "未知错误",
		1 : "暂时获取不到位置信息",
		2 : "位置服务被拒绝",
		3 : "获取信息超时",
		4 : "未知错误"
	};
	var errorMessage = errorTypes[error.code];

	if (error.code == 0 || error.code == 2) {
		errorMessage = errorMessage + " " + error.message;
	}
	$.dialog('alertHasOk', '', '为了更好的给您提供服务，请先选择距离您收货地最近的基地', 0, function() {
		window.location.href = SHOPDOMAIN + '/wap/ditu.html'
	});
}

function cookieInit() {
	$.cookie('sys_user_address', sys_addresss, {
		expires : 7
	}); // 存储一个带7天期限的 cookie
	$.cookie('sys_base_companyId', sys_base_companyId, {
		expires : 7
	});
	$.cookie('sys_address_auto', "true", {
		expires : 7
	});
	$.cookie('sys_company_name', sys_company_name, {
		expires : 7
	});
	$.cookie('sys_lng_lat', wx_lng+"-"+wx_lat, {
		expires : 7
	});
	$(".w-address").html(sys_company_name);
	getKuCunByProductId();
}
function clearCookie() {
	$.cookie('sys_user_address', null); // 存储一个带7天期限的 cookie
	$.cookie('sys_base_companyId', null);
	$.cookie('sys_address_auto', null);
	$.cookie('sys_company_name', null);
	$.cookie('sys_lng_lat', null);
	//alert("cookie已经清空");
}