/**
 * 省市区级联js
 */
var hierarchy;

function initUlList(type, id, number) {
	hierarchy = number;
	var info = "";
	var town = "";
	var mark = "";
	if (number == 3) {
		town = "";
		mark = "<input type='hidden'   id='mark" + type
				+ "'  name='mark' value='3'>"
	} else {
		town = "<input type='text'   id='street" + type
				+ "' name='streetId'>";
		mark = "<input type='hidden' id='mark" + type
				+ "'  name='mark' value='4'>"
	}
	for (var i = 0; i < number; i++) {
		var name = switchareaName(i);
		if (i == 0) {
			var clic = "initProvince(" + "'" + type + "')";
		} else {
			clic = "";
		}
		info += " <div class='btn-group'>"
				+ "<button class='btn btn-default btn-sm dropdown-toggle' type='button' data-toggle='dropdown' aria-haspopup='true' id='loseaddress' onclick="
				+ clic + " title='zhuzhiprovinceid' aria-expanded='false'>"
				+ "<span id='areaname" + i + type + "'>" + name + "</span>"
				+ "<span class='caret'></span>" + "</button>"
				+ "<ul class='dropdown-menu' id='areaList" + i + type + "'>"
				+ "</ul>" + "</div>";
	}
	$("#" + id).html(info);

	var areaInfo = "  <input  type='text'  id='province"
			+ type + "' name='provinceId'/>"
			+ "<input   type='text' id='city" + type
			+ "' name='cityId'/>"
			+ "<input   type='text' id='area" + type
			+ "' name='areaId'/>" + town + mark
			+ "<input type='text'  id='location" + type
			+ "' name='regionName'>";
	$("#" + id + "Info").html(areaInfo);
}
function initProvince(type) {
	$("#areaList2" + type).empty();
	$("#areaList1" + type).empty();
	$("#areaname1" + type).text("市");
	$("#areaname2" + type).text("区/县");
	$("#areaList3" + type).empty();
	$("#areaname3" + type).text("镇/街道");
	$.ajax({
		url : SHOPDOMAIN + "/front/region/getprovincByLevel.html",
		type : "post",
		dataType : "json",
		success : function(data) {
			var province = "";
			$.each(data, function(i, n) {

				province += "<li><a onclick='choseCity(this)' type=" + type
						+ " id=" + n.regionid + " title=" + n.regionname + ">"
						+ n.regionname + "</a></li>";

				$("#areaList0" + type).html(province);
			});

		}
	});
}

function choseCity(e) {
	var type = e.type;
	$("#province" + type).val(e.id);
	$("#areaname0" + type).text(e.title);
	$("#areaList1" + type).empty();
	$("#location" + type).val("");
	$("#areaname1" + type).text("市");
	$("#areaList3" + type).empty();
	$("#areaname3" + type).text("镇/街道");
	if (e.title == "香港特别行政区" || e.title == "澳门特别行政区" || e.title == "台湾省") {
		$("#location" + type).val(e.title);
	}

	var provinceId = $("#province" + type).val();

	$.ajax({
		url : SHOPDOMAIN + "/front/region/getprovinceid.html",
		type : "post",
		data : {
			"regionid" : provinceId
		},
		dataType : "json",
		success : function(data) {
			var city = "";
			$.each(data, function(i, n) {
				city += "<li><a onclick='choseCountry(this)' type=" + type
						+ " id=" + n.regionid + " title=" + n.regionname + ">"
						+ n.regionname + "</a></li>";
				$("#areaList1" + type).html(city);
			});
		}
	});

}
function choseCountry(e) {

	var type = e.type;
	$("#areaname1" + type).text(e.title);
	$("#areaList2" + type).empty();
	$("#city" + type).val(e.id);
	$("#areaname2" + type).text("区/县");
	$("#location" + type).val("");
	$("#areaList3" + type).empty();
	$("#areaname3" + type).text("镇/街道");
	var cityId = $("#city" + type).val();
	$.ajax({
		url : SHOPDOMAIN + "/front/region/getcityid.html",
		type : "post",
		dataType : "json",
		data : {
			"regionid" : cityId
		},
		success : function(data) {
			var country = "";
			$.each(data, function(i, n) {
				country += "<li><a onclick='updateText(this)' type=" + type
						+ " id=" + n.regionid + " title=" + n.regionname + ">"
						+ n.regionname + "</a></li>";

			});
			$("#areaList2" + type).html(country);
		}
	});

}

function updateText(e) {
	var type = e.type;
	$("#areaname2" + type).text(e.title);
	$("#area" + type).val(e.id);
	var markinfo = $("#mark" + type).val();

	if (markinfo == 3) {
		var province = $("#areaname0" + type).text();
		var city = $("#areaname1" + type).text();
		var distroct = $("#areaname2" + type).text();
		if (city == "市辖区" || city == "县") {
			city = "";
		}
		$("#location" + type).val(province + city + distroct);
	} else {
		$("#areaList3" + type).empty();
		$("#areaname3" + type).text("镇/街道");
		var streetId = $("#area" + type).val();

		$.ajax({
			url : SHOPDOMAIN + "/front/region/getSteetId.html",
			type : "post",
			dataType : "json",
			data : {
				"streetId" : streetId
			},
			success : function(data) {

				var country = "";
				$.each(data, function(i, n) {
					country += "<li><a onclick='updatefinalText(this)' type="
							+ type + " id=" + n.regionid + " title="
							+ n.regionname + ">" + n.regionname + "</a></li>";

				});

				$("#areaList3" + type).html(country);
			}
		});
		$("#location" + type).val("");
	}
}
function updatefinalText(e) {
	var type = e.type;
	$("#street" + type).val(e.id);
	$("#areaname3" + type).text(e.title);
	var province = $("#areaname0" + type).text();
	var city = $("#areaname1" + type).text();
	var distroct = $("#areaname2" + type).text();
	var street = $("#areaname3" + type).text();
	if (city == "市辖区" || city == "县") {
		city = "";
	}
	$("#location" + type).val(province + city + distroct + street);
}

function switchareaName(number) {
	var areaname = "";
	switch (number) {
	case 0:
		areaname = "省/直辖市";
		break;
	case 1:
		areaname = "市";
		break;
	case 2:
		areaname = "区/县";
		break;
	case 3:
		areaname = "镇/街道";
		break;
	}
	return areaname;

}

function updateArea(provinceId, cityId, steetId, type, location) {
	var provinc = getArea(provinceId);
	var city = getArea(cityId);
	var street = getArea(steetId);

	$("#areaname0" + type).text(provinc.regionname);
	$("#province" + type).val(provinc.regionid);
	$("#areaname1" + type).text(city.regionname);
	$("#city" + type).val(city.regionid);
	initCityArea(provinc.regionid, type);
	initStreet(city.regionid, type);
	$("#areaname2" + type).text(street.regionname);
	$("#area" + type).val(street.regionid);
	/*
	 * $("#areaname3"+type).text(town.regionname);
	 * $("#street"+type).val(town.regionid);
	 */
	$("#location" + type).val(location);
}
function updateForArea(provinceId, cityId, steetId, towId, type, location) {
	var provinc = getArea(provinceId);
	var city = getArea(cityId);
	var street = getArea(steetId);
	var town = getArea(towId);
	$("#areaname0" + type).text(provinc.regionname);
	$("#province" + type).val(provinc.regionid);
	$("#areaname1" + type).text(city.regionname);
	$("#city" + type).val(city.regionid);
	initCityArea(provinc.regionid, type);
	initStreet(city.regionid, type);
	initTown(street.regionid, type);
	$("#areaname2" + type).text(street.regionname);
	$("#area" + type).val(street.regionid);
	$("#areaname3" + type).text(town.regionname);
	$("#street" + type).val(town.regionid);
	$("#location" + type).val(location);
}

function initCityArea(provinceId, type) {
	$.ajax({
		url : SHOPDOMAIN + "/front/region/getprovinceid.html",
		type : "post",
		data : {
			"regionid" : provinceId
		},
		dataType : "json",
		success : function(data) {
			var city = "";
			$.each(data, function(i, n) {
				city += "<li><a onclick='choseCountry(this)' type=" + type
						+ " id=" + n.regionid + " title=" + n.regionname + ">"
						+ n.regionname + "</a></li>";
				$("#areaList1" + type).html(city);
			});
		}
	});

}
function initStreet(cityId, type) {
	$.ajax({
		url : SHOPDOMAIN + "/front/region/getcityid.html",
		type : "post",
		dataType : "json",
		data : {
			"regionid" : cityId
		},
		success : function(data) {
			var country = "";
			$.each(data, function(i, n) {
				country += "<li><a onclick='updateText(this)' type=" + type
						+ " id=" + n.regionid + " title=" + n.regionname + ">"
						+ n.regionname + "</a></li>";

			});
			$("#areaList2" + type).html(country);
		}
	});
}
function initTown(streetId, type) {
	$.ajax({
		url : SHOPDOMAIN + "/front/region/getSteetId.html",
		type : "post",
		dataType : "json",
		data : {
			"streetId" : streetId
		},
		success : function(data) {

			var country = "";
			$.each(data, function(i, n) {
				country += "<li><a onclick='updatefinalText(this)' type="
						+ type + " id=" + n.regionid + " title=" + n.regionname
						+ ">" + n.regionname + "</a></li>";

			});

			$("#areaList3" + type).html(country);
		}
	});
}

function getArea(id) {
	var result;
	$.ajax({
		url : SHOPDOMAIN + "/region/getRegionById.html",
		type : "post",
		dataType : "json",
		async : false,
		data : {
			regionid : id
		},
		success : function(data) {

			result = data.region;
		}
	});
	return result;
}
