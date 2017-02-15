window.onload = getMyLocation;
var ourCoords = {
	latitude : 36.67159999847618,
	longitude : 117.1258232996854
};

function getMyLocation() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(displayLocation, displayError);
	} else {
		alert("您的浏览器不支持获取地理位置");
	}
}

function displayLocation(position) {
	var latitude = position.coords.latitude;
	var longitude = position.coords.longitude;

	var km = GetDistance(latitude, longitude, 36.67159999847618,
			117.1258232996854);
	var distance = km * 1000;
	alert(distance);
	return false;

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
		3 : "获取信息超时"
	};
	var errorMessage = errorTypes[error.code];
	if (error.code == 0 || error.code == 2) {
		errorMessage = errorMessage + " " + error.message;
	}

}