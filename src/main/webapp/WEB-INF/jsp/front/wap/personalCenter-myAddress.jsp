<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
<script id="interpolationtmpl" type="text/x-dot-template">
{{for(var i=0;i<it.length;i++){ }}
    {{var uslog=it[i];}}
    <div class="w-orderList ">
         <div class="w-paddingTB10 w-padding37 w-fontsize30 w-borderBottom">
             <p class="w-addressName">{{=uslog.consignee}}&nbsp;{{=uslog.mobile}}</p>
             <p class="w-color9">{{=uslog.regionName}}{{=uslog.location}}</p>
         </div>
         <div class=" w-padding37 w-fontsize30 overflowH w-height80 w-color9" >
             {{ if(uslog.status==0) {}}
				 <span class="fl w-radio w-unchecked cursor" locationId={{=uslog.id}}>设为默认</span>
			{{} else{ }}
			     <span class="fl w-radio w-checked cursor" locationId={{=uslog.id}}>设为默认</span>
			{{ }}}
             <span class="fr  ">
                 <a  href="javascript:window.location.replace('${SHOPDOMAIN}/wap/updatenew-address.html?id={{=uslog.id}}')" ><span class="w-editor cursor">编辑</span></a>
                 <a  href="javascript:void(0)"onclick="deleteuslog({{=uslog.id}})"><span class="w-delete cursor">删除</span></a>
             </span>
         </div>
     </div>
{{}}}
</script>

<div class="w-main">

	<div id="showUslogList"></div>

	<div class="w-noOrder" style="display:none">
		<img src="${SHOPDOMAIN}/front/images/wap/noaddress.png" alt="" />

	</div>
	<a href="javascript:window.location.replace('new-address.html')"><button class="w-subBtn w-margin-top">+新建地址</button></a>
</div>
</div>
<script src="${SHOPDOMAIN}/front/js/wap/jquery.hDialog.js"></script>

<script>
	document.title = "我的地址";
	function edituslog(id) {
		window.location.replace("${SHOPDOMAIN}/wap/updatenew-address.html?id=" + id);
	}
	function deleteuslog(id) {
		$(document).on('click', '.w-delete', function() {
			$.dialog('confirm', '', '确定要删除该地址吗？', 0, function() {
				delteaddres(id);
				$.closeDialog();
			});
		});
	}
	//修改状态
	function editstatus(id, str) {
		$.post(SHOPDOMAIN + '/interfaces/userLocation/upstatus.html', {
			id : id,
			str : str
		}, function(data) {
			if (data.res_code == '0') {
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
				showMessageRefresh(data.message);
			} else {
				showMessageRefresh(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}
	$(document).ready(function() {
		//     点击选中
		$(document).on('click', '.w-radio', function() {
			var str = true;//str=true不默认
			$('.w-radio').removeClass('w-checked').addClass('w-unchecked');
			$(this).removeClass('w-unchecked').addClass('w-checked');
			var id = $(this).attr("locationId");
			if ($('.w-radio').hasClass('w-checked')) {
				str = 1;
				editstatus(id, str);
			} else {
				str = 0;
				editstatus(id, str);
			}
		});
		//获取列表
		$.ajax({
			url : "${SHOPDOMAIN}/interfaces/userLocation/LocationList.html",
			type : "post",
			//data : {},
			dataType : "json",
			success : function(data) {
				var evalText = doT.template($("#interpolationtmpl").html());
				$("#showUslogList").html(evalText(data.list));
				if (data.list.length == 0) {
					$(".w-noOrder").css('display', 'block');
				}
			}
		});

	});
</script>
<jsp:include page="foot-validate.jsp"></jsp:include>
<script>
	jQuery(document).ready(function() {
		FormValidation.init();
	});
</script>
