<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="top2.jsp"></jsp:include>
<script type="text/javascript" src="http://apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css" />
<link href="${SHOPDOMAIN}/front/css/web/share.css" rel="stylesheet" type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/Personal.css" rel="stylesheet" type="text/css">
<script src="${SHOPDOMAIN}/front/js/web/ntab.js"></script>

<script>
	function deladr(uid) {
		$(".per_adr_" + uid).css("display", "none");
	}
</script>
<style>
* {box-sizing: content-box;}

.consignee-error {	display: none}

.mobile-error {	display: none}

.location-error {	display: none}
</style>
<script id="interpolationtmpl" type="text/x-dot-template">
{{for(var i=0;i<it.list.length;i++){ }}
    {{var uslog=it.list[i];}}
<tr class="per_adr_btm per_adr_1">
	<td width="11%">{{=uslog.consignee}}</td>
	<td width="17%">{{=uslog.mobile}}</td>
	<td width="19%">{{=uslog.regionName}}</td>
	<td width="26%">{{=uslog.location}}</td>
	<td width="17%">
		<ul>
			<li class="per_adr_edit"><a href="#"  onclick="changeLoc({{=uslog.id}})">编辑</a></li>
			<li>|</li>
			<li class="per_adr_del"> <a  href="javascript:void(0)"onclick="deleteuslog({{=uslog.id}})">删除</a></li>
            {{ if(uslog.status==0) {}}
				 <span class="fl w-radio w-unchecked cursor" locationId={{=uslog.id}}> <a  href="javascript:void(0)"onclick="editstatus({{=uslog.id}},true)">设为默认</a></span>
			{{} else{ }}
			    <li><div class="per_adr_default">默</div></li>
			{{ }}}
		</ul>
	</td>
</tr>

{{}}}
<tr style="background-color:#f1f1f1;">
	<td colspan="8">
	<div>
 		<div class="page_and_btn">
		{{=it.pageStr}}</div>
	</div>
   	</td>
</tr>
</script>

<div class="per_main">
	<jsp:include page="centerLeft.jsp"></jsp:include>
	<div class="per_mainright flW">
		<!--位置-->
		<div class="per_main_title">收货地址</div>
		<div class="per_adr">
			<div class="per_adr_addbtn">
				<span class="clickBtnAddress"><a href="#" style="cursor:pointer">新增收货地址</a></span>
			</div>
			<div class="per_adr_infor">
				<div class="per_noorder" id="sixs" style="display:none">
					<div class=" per_ per_noorderleft flW">
						<img src="${SHOPDOMAIN}/front/images/web/per_noorder.png" width="10" height="10" alt="" />
					</div>
					<div class=" per_noorderright flW">
						<ul>
							<li>SORRY~您没有相关收货地址哦~</li>
							<li>可以去看看有哪些想买的</li>
							<li><a href="index.html"><input type="button" name="submi" id="submi" class="per_evaluate_btn" value="随便逛逛" /></a></li>
						</ul>
					</div>
				</div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr class="per_adr_title">
						<td width="13%">收货人</td>
						<td width="13%">手机号码</td>
						<td width="19%">所在地区</td>
						<td width="26%">详细地址</td>
						<td width="17%">操作</td>
					</tr>
					<tbody id="showUslogList">
					</tbody>
				</table>
			</div>
		</div>
		<div class="fox"></div>
	</div>
	<div class="fox"></div>
</div>

<div class="layerBoxAddress" style="display:none">
	<form action="${SHOPDOMAIN}/interfaces/userLocation/saveLocation.html" id="form_config" class="form-horizontal" method="post">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<td align="right">所在地址：</td>
					<td>
						<div class="new-address">
							<div class="tle">
								<div id="area" class="addressW18"></div>
								<div id="areaInfo" style="display: none" class="addressW18"></div>
								<div id="areadd" class="addressW18"></div>
								<div id="areaddInfo" style="display: none" class="addressW18"></div>
								<input type="hidden" value="" id="address" name="address" style="width:500px;">
							</div>
						</div>
					</td>

				</tr>
				<tr>
					<td align="right">详细地址：</td>
					<td><input type="text" class="per_addadrinput2  w_require" name="location" id="location" placeholder="请输入您的详细地址"> <input type="hidden" name="status" id="status" value="0"><input type="hidden" id="urls" name="urls" value="">
						<input type="hidden" id="id" name="id"></td>
				</tr>
				<tr>
					<td align="right">收货人：</td>
					<td><input type="text" class="per_addadrinput3  w_require" value="" name="consignee" id="consignee" placeholder="请输入您的姓名"></td>
				</tr>
				<tr>
					<td align="right">手机号码：</td>
					<td><input type="text" class="per_addadrinput3   w_mobile w_require" name="mobile" id="mobile" placeholder="请输入您的手机号码"></td>
				</tr>
			</tbody>
		</table>
		<h3 class="inshow" style="display:none">
			<input type="button" class="per_addadrbtn" onclick="updatePas()" value="保存收货人信息">
		</h3>
		<h3 class="upshow" style="display:none">
			<input type="button" class="per_addadrbtn" onclick="submmit()" value="保存收货人信息">
		</h3>
	</form>
</div>
<jsp:include page="footShop.jsp"></jsp:include>
<jsp:include page="foot-validate.jsp"></jsp:include>


<script src="${SHOPDOMAIN}/front/js/provincialCascade.js"></script>
<script src="${SHOPDOMAIN}/front/js/wap/jquery.hDialog.js"></script>
<script>
	document.title="个人中心-收货地址";
	function showm(showmess) {
		layer.msg(showmess, {
			time : 1000
		});
	}
	$(function() {
		$(".address").addClass("per_nowcolor");
		url = "receiveaddress.html";
		if (url != null || url != "") {
			$("#urls").val(url);
		}
	});
	function updatePas() {
		if ($("#locationadd").val().trim() == "" || $("#cityadd").val().trim() == "" || $("#areaadd").val().trim() == "" || $("#provinceadd").val().trim() == "") {
			showMessage("请选择完善位置信息！");
			return false;
		} else {
			var location = $("#location").val();
			var consignee = $("#consignee").val();
			if (location == "") {
				showm("详细地址不能为空");
			} else if (location.length > 255) {
				showm("详细地址长度超过限制");
			} else if (consignee == "" && consignee.length == 0) {
				showm("收货人不能为空");
			} else if (consignee.length > 20) {
				showm("收货人长度超过限制");
			} else {
				$("#form_config").submit();
			}
		}
		$(".consignee-error").css('display', 'none');
		$(".location-error").css('display', 'none');
	}
</script>
<script>
	$('.clickBtnAddress').click(function() {
		layer.open({
			type : 1,
			title : false,
			closeBtn : 2,
			shade : 0.3,
			area : [ '700px', '400px' ],
			content : $('.layerBoxAddress')
		});
		initUlList("add", "areadd", 3);
		$("#consignee").val("");
		$("#location").val("");
		$("#mobile").val("");
		$(".inshow").css('display', 'block');
		$(".upshow").css('display', 'none');
		$('#form_config').attr("action", "${SHOPDOMAIN}/interfaces/userLocation/saveLocation.html");
	});
	function edituslog(id) {
		window.location.replace("${SHOPDOMAIN}/wap/updatenew-address.html?id=" + id);
	}
	function deleteuslog(id) {
		delteaddres(id);
	}
	//修改状态
	function editstatus(id, str) {
		$.post(SHOPDOMAIN + '/interfaces/userLocation/upstatus.html', {
			id : id,
			str : str
		}, function(data) {
			if (data.res_code == '0') {
				getLocationList(1);
				$("#lingquan").html("收货地址");
				showMessageRefresh(data.message);
			} else {
				showMessageRefresh(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}
	//删除
	function delteaddres(id) {
		$.post(SHOPDOMAIN + '/interfaces/userLocation/deleteLocation.html', {
			id : id,
		}, function(data) {
			if (data.res_code == '0') {
				showm(data.message);
				getLocationList(1);
			} else {
				showMessageRefresh(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}
	$(document).ready(function() {
		//获取列表
		getLocationList(1);
		$("#lingquan").html("收货地址");
	});
	//收货地址列表
	function getLocationList(currentPage) {
		$.ajax({
			url : "${SHOPDOMAIN}/interfaces/userLocation/LocationLists.html",
			type : "post",
			data : {
				"currentPage" : currentPage,
				"showCount" : 10
			},
			dataType : "json",
			success : function(data) {
				if (data.res_code == 0) {
					var evalText = doT.template($("#interpolationtmpl").html());
					if (data.list == "") {
						$("#sixs").css('display', 'block');
					} else {
						$("#showUslogList").html(evalText(data));
					}
				}
				if (data.res_code == '1') {
					showm("您还没有登录!");
					window.setTimeout((window.location.href = "index.html"), 10000);
				}
				$(".page_and_btn ul li span").css("background-color", "#e70012");
				$(".page_and_btn ul li span").css("color", "#fff");
			}
		});
	}
	//更新

	function changeLoc(id) {
		initUlList("updatel", "areadd", 3);
		layer.open({
			type : 1,
			title : false,
			closeBtn : 2,
			shade : 0.3,
			area : [ '700px', '400px' ],
			content : $('.layerBoxAddress')
		});
		$(".inshow").css('display', 'none');
		$(".upshow").css('display', 'block');
		//修改表单的action
		$('#form_config').attr("action", "${SHOPDOMAIN}/interfaces/userLocation/updateLocation.html");
		$.post(SHOPDOMAIN + '/interfaces/userLocation/seleById.html', {
			id : id,
		}, function(data) {
			if (data.res_code == '0') {
				$("#consignee").val(data.list.consignee);
				$("#mobile").val(data.list.mobile);
				$("#location").val(data.list.location);
				$("#address").val(data.list.regionName);
				$("#id").val(data.list.id);
				if (data.list.provinceId != null) {
					updateArea(data.list.provinceId, data.list.cityId, data.list.areaId, "updatel", data.list.regionName);
				}
			} else {
				showMessage(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}
	function submmit() {
		if ($("#locationupdatel").val().trim() == "" || $("#cityupdatel").val().trim() == "" || $("#areaupdatel").val().trim() == "" || $("#provinceupdatel").val().trim() == "") {
			showMessage("请选择完善位置信息！");
			return false;
		} else {
			var location = $("#location").val();
			var consignee = $("#consignee").val();
			if (location == "") {
				showm("详细地址不能为空");
			} else if (location.length > 255) {
				showm("详细地址长度超过限制");
			} else if (consignee == "" && consignee.length == 0) {
				showm("收货人不能为空");
			} else if (consignee.length > 20) {
				showm("收货人长度超过限制");
			} else {
				$("#form_config").submit();
			}
			$(".consignee-error").css('display', 'none');
			$(".location-error").css('display', 'none');
		}
	}
</script>
<script>
	jQuery(document).ready(function() {
		FormValidation.init();
	});
</script>