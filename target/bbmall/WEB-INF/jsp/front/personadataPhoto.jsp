<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="top2.jsp"></jsp:include>
<link href="${SHOPDOMAIN}/front/css/web/share.css" rel="stylesheet" type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/commonPhoto.css" rel="stylesheet" type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/Personal.css" rel="stylesheet" type="text/css">
<script src="${SHOPDOMAIN}/front/js/web/ntab.js"></script> 
<style>
.per_data table tr td li {float: left;line-height: 30px;margin-right: 75px;
}

.container {width: 600px;}
</style>
<div class="per_main">
	<jsp:include page="centerLeft.jsp"></jsp:include>
	<!--Personal center right-->
	<div class="per_mainright flW">
		<!--位置-->
		<div class="per_main_title">个人资料</div>
		<div class=" per_data">
				<div class="demo">
					<p id="swfContainer">
						本组件需要安装Flash Player后才可使用，请从<a href="http://dl.pconline.com.cn/html_2/1/114/id=8122&pn=0.html" target="_blank">这里</a>下载安装。
					</p>
					<!-- 	<button type="button" id="upload" style="display:none;margin-top:8px;">swf外定义的上传按钮，点击可执行上传保存操作</button> -->
				</div>

		</div>
		<div class="fox"></div>
	</div>
	<div class="fox"></div>
</div>

<jsp:include page="footShop.jsp"></jsp:include>

<script>
	document.title="个人中心-个人资料";
	var img;
	jQuery(document).ready(function() {
		$(".ziliao").addClass("per_nowcolor");
		$("#lingquan").html("个人中心");
	});
</script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/web/swfobject.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/web/fullAvatarEditor.js"></script>
<script type="text/javascript">
	swfobject.addDomLoadEvent(function() {
		var swf = new fullAvatarEditor("${SHOPDOMAIN}/front/fullAvatarEditor.swf", "${SHOPDOMAIN}/front/expressInstall.swf", "swfContainer", {
			id : 'swf',
			upload_url : '${SHOPDOMAIN}/file/updatetouxiangPc.html', //上传接口
			method : 'post', //传递到上传接口中的查询参数的提交方式。更改该值时，请注意更改上传接口中的查询参数的接收方式
			src_upload : 0, //是否上传原图片的选项，有以下值：0-不上传；1-上传；2-显示复选框由用户选择
			avatar_box_border_width : 0,
			avatar_sizes : '300*300',
			avatar_sizes_desc : '100*100像素'
		}, function(msg) {
			switch (msg.code) {
			case 1:
				break;
			case 2:
				showm("已成功加载图片到编辑面板。");
				document.getElementById("upload").style.display = "inline";
				break;
			case 3:
				if (msg.type == 0) {
					showm("摄像头已准备就绪且用户已允许使用。");
				} else if (msg.type == 1) {
					showm("摄像头已准备就绪但用户未允许使用！");
				} else {
					showm("摄像头被占用！");
				}
				break;
			case 5:
				if (msg.content.res_code == '0') {
					showm("保存成功!");
					return false;
				} else if (msg.content.res_code == '1') {
					showm("您还没有登录!");
					window.setTimeout((window.location.href = "index.html"), 10000);
				} else {
					showm("保存失败!");
				}
				break;
			}
		});
	});
</script>