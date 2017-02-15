$list = $("#fileList");// 图片存放位置
var count=0;// 已经上传的照片数量，用于修改删除或者修改的时候，默认成功图片数量
var Imgmap = new Map();// 存储图片得map
thumbnailWidth = 100;
thumbnailHeight = 100;
var uploader = WebUploader.create({

	// 选完文件后，是否自动上传。
	auto : true,
	fileVal : 'imgfile',
	formData : {
		imgtype:imgtype
	},
	// swf文件路径
	swf : SHOPDOMAIN+ '/js/webuploader/Uploader.swf',
	duplicate : true,
	// 文件接收服务端。
	server : SHOPDOMAIN+ '/publicinterface/getImgurlbyfile.html',
	// 选择文件的按钮。可选。
	// 内部根据当前运行是创建，可能是input元素，也可能是flash.
	pick : '#filePicker',
	// 只允许选择图片文件。
	accept : {
		title : 'Images',
		extensions : 'gif,jpg,jpeg,bmp,png',
		mimeTypes : 'image/*'
	},
	fileNumLimit :fileNumLimit,
	fileSingleSizeLimit : '3000000'
});
// 当有文件添加进来的时候
uploader.on('fileQueued',function(file) {
	var $li = $('<div id="' + file.id + '" class="file-item thumbnail">'
			+ '<img>'
			+ '<div class="info">'
			+ file.name + '</div>' + '</div>'), $img = $li
			.find('img');
	$btns = $(
			'<div class="file-panel fp_' + file.id + '">'
					+ '<span class="cancel">删除</span>'
					+ '<span class="rotateRight">向右旋转</span>'
					+ '<span class="rotateLeft">向左旋转</span></div>')
			.appendTo($li);
	$li.on('mouseenter', function() {
		$('.fp_' + file.id).stop().animate({
			height : 30
		});
	});

	$li.on('mouseleave', function() {
		$('.fp_' + file.id).stop().animate({
			height : 0
		});
	});

	$btns.on('click', 'span', function() {
		var index = $(this).index(), deg;
		switch (index) {
		case 0:// 删除
			uploader.setStats(8);
			delImg(file.id);
			return;

		case 1:// 右移
			var $nextId=$("#"+file.id).next();
			if(undefined!=$nextId.attr("id")){
				var tempSrc=Imgmap.get($nextId.attr("id"));
				var tempWu_File=$("#"+file.id);
				Imgmap.put($nextId.attr("id"), Imgmap.get(file.id));
				Imgmap.put(file.id, tempSrc);
				$nextId.after(tempWu_File);
				$('.fp_' + file.id).stop().animate({
					height : 0
				});
				$("#imgPathWebUploader").html(Imgmap.toString());
			}
			break;

		case 2:// 左移
			var $prevId=$("#"+file.id).prev();
			if(undefined!=$prevId.attr("id")){
				var tempSrc=Imgmap.get($prevId.attr("id"));
				var tempWu_File=$("#"+file.id);
				Imgmap.put($prevId.attr("id"), Imgmap.get(file.id));
				Imgmap.put(file.id, tempSrc);
				$prevId.before(tempWu_File);
				$('.fp_' + file.id).stop().animate({
					height : 0
				});
				$("#imgPathWebUploader").html(Imgmap.toString());
			}
			break;
		}
	});

	// $list为容器jQuery实例
	$list.append($li);

	// 创建缩略图
	// 如果为非图片文件，可以不用调用此方法。
	// thumbnailWidth x thumbnailHeight 为 100 x 100
	uploader.makeThumb(file, function(error, src) {
		if (error) {
			$img.replaceWith('<span>不能预览</span>');
			return;
		}

		$img.attr('src', src);
	}, thumbnailWidth, thumbnailHeight);
});
// 文件上传过程中创建进度条实时显示。
uploader.on('uploadProgress', function(file, percentage) {
	var $li = $('#' + file.id), $percent = $li.find('.progress span');

	// 避免重复创建
	if (!$percent.length) {
		$percent = $('<p class="progress"><span></span></p>').appendTo(
				$li).find('span');
	}

	$percent.css('width', percentage * 100 + '%');
});

// 文件上传成功，给item添加成功class, 用样式标记上传成功。
uploader.on('uploadSuccess', function(file, response) {
	Imgmap.put(file.id, response.filePath );
	$("#isfirstWebUploader").css("display","");
	$("#imgPathWebUploader").html(Imgmap.toString());
	$('#' + file.id).addClass('upload-state-done');
});

// 文件上传失败，显示上传出错。
uploader.on('uploadError', function(file) {
	var $li = $('#' + file.id), $error = $li.find('div.error');
	// 避免重复创建
	if (!$error.length) {
		$error = $('<div class="error"></div>').appendTo($li);
	}
	$error.text('上传失败');
});

// 完成上传完了，成功或者失败，先删除进度条。
uploader.on('uploadComplete', function(file) {
	$('#' + file.id).find('.progress').remove();
});

// 完成上传完了，成功或者失败，先删除进度条。
uploader.on('error', function(file) {
	showMessage(file);
});
function delImg(fileId) {
	var tempImgPath = $("#imgPathWebUploader").html();
	Imgmap.remove(fileId);
	if(Imgmap.size()==0){
		$("#isfirstWebUploader").css("display","");
	}
	$("#imgPathWebUploader").html(Imgmap.toString());
	$("#" + fileId).remove();
}


function getImgByJson(json){
	json = eval(json)  
	$("#isfirstWebUploader").css("display","");
	for(var i=0; i<json.length; i++)  
	{  
		Imgmap.put("WU_10000"+json[i].id, json[i].uri);
		var $li = $('<div id="WU_10000' + json[i].id + '" class="file-item thumbnail">'
				+ '<img src="'+USERIMGSRC+imgZuhe(json[i].uri,'_160')+'">'
				+ '<div class="info">'
				+ json[i].uri + '</div>' + '</div>'), $img = $li
				.find('img');
		$btns = $(
				'<div class="file-panel fp_WU_10000' + json[i].id + '" id="'+json[i].id+'">'
						+ '<span class="cancel">删除</span>'
						+ '<span class="rotateRight">向右旋转</span>'
						+ '<span class="rotateLeft">向左旋转</span></div>')
				.appendTo($li);
		$li.on('mouseenter', function() {
			var tempId=$(this).attr("id");
			$('.fp_' + tempId).stop().animate({
				height : 30
			});
		});
	
		$li.on('mouseleave', function() {
			var tempId=$(this).attr("id");
			$('.fp_' + tempId).stop().animate({
				height : 0
			});
		});
	
		$btns.on('click', 'span', function() {
			var tempId=$(this).parent().attr("id");
			var index = $(this).index(), deg;
			switch (index) {
			case 0:// 删除
				uploader.setStats(8);
				delImg("WU_10000"+tempId);
				return;
	
			case 1:// 右移
				var $nextId=$("#WU_10000"+tempId).next();
				if(undefined!=$nextId.attr("id")){
					var tempSrc=Imgmap.get($nextId.attr("id"));
					var tempWu_File=$("#WU_10000"+tempId);
					Imgmap.put($nextId.attr("id"), Imgmap.get("WU_10000"+tempId));
					Imgmap.put("WU_10000"+tempId, tempSrc);
					$nextId.after(tempWu_File);
					$('.fp_WU_10000'+tempId).stop().animate({
						height : 0
					});
					$("#imgPathWebUploader").html(Imgmap.toString());
				}
				break;
	
			case 2:// 左移
				var $prevId=$("#WU_10000"+tempId).prev();
				if(undefined!=$prevId.attr("id")){
					var tempSrc=Imgmap.get($prevId.attr("id"));
					var tempWu_File=$("#WU_10000"+tempId);
					Imgmap.put($prevId.attr("id"), Imgmap.get("WU_10000"+tempId));
					Imgmap.put("WU_10000"+tempId, tempSrc);
					$prevId.before(tempWu_File);
					$('.fp_WU_10000'+tempId).stop().animate({
						height : 0
					});
					$("#imgPathWebUploader").html(Imgmap.toString());
				}
				break;
			}
		});
	
		// $list为容器jQuery实例
		$list.append($li);
	} 
	count=i;
	$("#imgPathWebUploader").html(Imgmap.toString());
}
