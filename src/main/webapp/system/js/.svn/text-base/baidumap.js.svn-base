// 百度地图API功能
function createmap(data) {
	var map = new BMap.Map("baidumap");
	if (data.lng && data.lat) {
		var point = new BMap.Point(data.lng, data.lat);
		map.centerAndZoom(point, 13);
	} else {
		// var geolocation = new BMap.Geolocation();
		// geolocation.getCurrentPosition(function(r) {
		// 	if (this.getStatus() == BMAP_STATUS_SUCCESS) {
		// 		map.centerAndZoom(r.point, 11);
		// 		map.addOverlay(new BMap.Marker(r.point));
		// 		updatedata(r.point);
		// 	} else {
		// 		var point = new BMap.Point(116.404, 39.915);
		// 		map.centerAndZoom(point, 11);
		// 		map.addOverlay(new BMap.Marker(point)); // 将标注添加到地图中
		// 		updatedata(point);
		// 	}
		// }, {
		// 	enableHighAccuracy: true
		// })
		var point = new BMap.Point(116.404, 39.915);
		map.centerAndZoom(point, 13);
		updatedata(point);
	}

	map.enableScrollWheelZoom(); //鼠标滚轴
	map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件

	//在中心显示一个标注
	var marker =new BMap.Marker(map.getCenter());
	map.addOverlay(marker);

	map.addEventListener("dragging", function showInfo() {
		var cp = map.getCenter();
		marker.setPosition(cp);
		updatedata(cp);
	});

	function updatedata(cp) {
		$('#xpostion').val(cp.lng);
		$('#ypostion').val(cp.lat);
	}
}