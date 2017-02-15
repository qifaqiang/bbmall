<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/front/css/wap/part-page.css">
<link rel="stylesheet"
	href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<link rel="stylesheet"
	href="${SHOPDOMAIN}/front/css/wap/chooseAddress.css" />
<script id="interpolationtmpl" type="text/x-dot-template">
{{for(var i=0;i<it.length;i++){ }}
    {{var uslog=it[i];}}
		<a  href="javascript:void(0)"onclick="editStatus({{=uslog.id}});" >
             <div  class="w-paddingTB10 w-padding37 w-fontsize30 w-borderBottom" style="background: white;">
					<img src="${SHOPDOMAIN}/front/images/wap/iconfont-dizhi1.png" width="34" alt="" />

					<div style="display: inline-block;vertical-align:middle;padding-left:15px;width: 80%">
						<p class="w-addressName">{{=uslog.consignee}}&nbsp;{{=uslog.mobile}}</p>

						<p class="w-color9">{{=uslog.regionName}}{{=uslog.location}}</p>
					</div>
					<div style="display:inline-block;margin-left: 10px;">
					<a  href="javascript:void(0)"onclick="edituslog({{=uslog.id}});event.cancelBubble=true" ><img src="${SHOPDOMAIN}/front/images/wap/w-editor.png" width="40" alt="" /></a>
					</div>
	        </div>
</a>
{{}}}
</script>
<div class="w-main">
	<div class="address">
		<div>
			<div id="showUslogList"></div>
		</div>
	</div>
	<a href="new-address.html?url=cart-confirmation-order.html"><button
			class="w-subBtn w-margin-top">+新建地址</button></a>
</div>
<jsp:include page="foot.jsp"></jsp:include>
<script>
document.title = "选择地址";
	function edituslog(id) {
		window.location.href = "${SHOPDOMAIN}/wap/updatenew-address.html?id="
				+ id + "&urls=cart-confirmation-order.html";
	}
	function editStatus(id) {
		$
				.post(
						SHOPDOMAIN + '/interfaces/userLocation/upstatus.html',
						{
							id : id,
							str : true
						},
						function(data) {
							if (data.res_code == '0') {
								window.location.href = "${SHOPDOMAIN}/wap/cart-confirmation-order.html";
							}
						}, "json").error(function() {
					showError();
				});
	}
	jQuery(document).ready(function() {
		$.ajax({
			url : "${SHOPDOMAIN}/interfaces/userLocation/LocationList.html",
			type : "post",
			dataType : "json",
			success : function(data) {
				var evalText = doT.template($("#interpolationtmpl").html());
				$("#showUslogList").html(evalText(data.list));
			}
		});
	});
</script>
