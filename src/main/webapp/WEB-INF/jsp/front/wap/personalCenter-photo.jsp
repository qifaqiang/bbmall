<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="齐鲁干烘茶城">
<meta http-equiv="description" content="齐鲁干烘茶城">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<!-- 启用 WebApp 全屏模式（IOS）-->
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="yes" name="apple-touch-fullscreen">
<!--设置状态栏的背景颜色（IOS）-->
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<!-- 关闭电话号码的自动识别-->
<meta content="telephone=no" name="format-detection">
<!-- 关闭邮箱的自动识别-->
<meta content="email=no" name="format-detection">
<title></title>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/style.css?v=2">
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>

<script src="${SHOPDOMAIN}/front/js/wap/sonic.js"></script>
<script src="${SHOPDOMAIN}/front/js/wap/comm.js"></script>
<script src="${SHOPDOMAIN}/front/js/wap/hammer.js"></script>
<script src="${SHOPDOMAIN}/front/js/wap/iscroll-zoom.js"></script>
<script src="${SHOPDOMAIN}/front/js/wap/jquery.photoClip.js"></script>
<title>html5 图片上传 -访问手机相册</title>

<style type="text/css">
.tb960x90 {
	display: none !important;
	display: none
}

.btn-lg {
	background: #fca815;
	border: none;
	margin-top: 20px;
	color: white;
}

.btn:hover,.btn:focus {
	background: #fca815;
	border: none;
	color: white;
}
</style>
</head>


<button class="btn  btn-lg" data-toggle="modal" data-target="#myModal">点击上传图片</button>
<input type="file" id="file" style="opacity: 0;position: fixed;bottom: -100px">
<div id="plan" style="display:none">
	比例剪切后尺寸
	<canvas id="myCanvas"></canvas>
	<h2 onClick="Close();" style="cursor:pointer; display:inline-block">关闭</h2>
</div>
</div>
<img src="" fileName="" id="hit" style="display:none;z-index: 9">

<div id="cover"></div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="lazy_tip" id="lazy_tip">
				<span>1%</span><br> 载入中......
			</div>
			<div class="lazy_cover"></div>
			<div class="resource_lazy hide"></div>
			<div class="pic_edit">
				<h2 style="color:#4eaf7a;">双指旋转和双指缩放</h2>
				<div id="clipArea"></div>
				<button id="upload2">选择图片</button>
				<button id="clipBtn">上传图片</button>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>



	<script>
		document.title = "上传头像";
		var hammer = '';
		var currentIndex = 0;
		var body_width = $('body').width();
		var body_height = $('body').height();

		$("#clipArea").photoClip({
			width : body_width * 0.8125,
			height : body_width * 0.8125,
			file : "#file",
			view : "#hit",
			ok : "#clipBtn",
			loadStart : function() {
				//console.log("照片读取中");
				$('.lazy_tip span').text('');
				$('.lazy_cover,.lazy_tip').show();
			},
			loadComplete : function() {
				//console.log("照片读取完成");
				$('.lazy_cover,.lazy_tip').hide();
			},
			clipFinish : function(dataURL) {
				$('#hit').attr('src', dataURL);
				saveImageInfo();
			}
		});

		//图片上传
		function saveImageInfo() {

			var filename = $('#hit').attr('fileName');
			var img_data = $('#hit').attr('src');
			if (img_data == "") {
				alert('null');
			}
			//alert(filename+'|'+img_data);
			render(img_data);

			$("#image64").val(img_data);
			$.post("${SHOPDOMAIN}/updatetouxiang.html", {
				image : img_data
			}, function(data) {
				if (data.res_code == '0') {
					window.location.replace("${SHOPDOMAIN}/wap/personalCenter-myAccount.html");
				}
			}, "json").error(function() {
				showError();
			});
		}

		/*获取文件拓展名*/
		function getFileExt(str) {
			var d = /\.[^\.]+$/.exec(str);
			return d;
		}

		//图片上传结束
		$(function() {
			$('#upload2').on('click', function() {
				//图片上传按钮
				$('#file').click();
			});
		})

		function Close() {
			$('#plan').hide();
		}

		// 渲染 Image 缩放尺寸  
		function render(src) {
			var MAX_HEIGHT = 256; //Image 缩放尺寸 
			// 创建一个 Image 对象  
			var image = new Image();

			// 绑定 load 事件处理器，加载完成后执行  
			image.onload = function() {
				// 获取 canvas DOM 对象  
				var canvas = document.getElementById("myCanvas");
				// 如果高度超标  
				if (image.height > MAX_HEIGHT) {
					// 宽度等比例缩放 *=  
					image.width *= MAX_HEIGHT / image.height;
					image.height = MAX_HEIGHT;
				}
				// 获取 canvas的 2d 环境对象,  
				// 可以理解Context是管理员，canvas是房子  
				var ctx = canvas.getContext("2d");
				// canvas清屏  
				ctx.clearRect(0, 0, canvas.width, canvas.height);
				canvas.width = image.width; // 重置canvas宽高  
				canvas.height = image.height;
				// 将图像绘制到canvas上  
				ctx.drawImage(image, 0, 0, image.width, image.height);
				// !!! 注意，image 没有加入到 dom之中  

				var dataurl = canvas.toDataURL("image/jpeg");
				var imagedata = encodeURIComponent(dataurl);
				$('#plan').attr('data-src', dataurl);
				$('#plan').show();
			};
			// 设置src属性，浏览器会自动加载。  
			// 记住必须先绑定render()事件，才能设置src属性，否则会出同步问题。  
			image.src = src;
		};
	</script>


	<jsp:include page="foot-validate.jsp"></jsp:include>