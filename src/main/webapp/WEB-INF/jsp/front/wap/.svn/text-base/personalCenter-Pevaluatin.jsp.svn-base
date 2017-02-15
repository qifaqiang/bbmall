<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="top.jsp"></jsp:include>
<script src="${SHOPDOMAIN}/front/js/jquery.raty.js"></script>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/js/webuploader/webuploader.css">
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/js/webuploader/style.css">
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/js/webuploader/demo2.css">
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/js/webuploader/webuploader.js"></script>
<div class="w-main">
	<form action="${SHOPDOMAIN}/interfaces/comment/commentComit.html" id="form_config" class="form-horizontal" method="post">
		<div class="w-orderList">
			<div class="w-orderList-content">
				<img src="" id="ima" alt="" /> <span><em class="ellipsis">评分</em> <br /> <em class="w-specifications" id="w-stars"></em><br /></span> <input
					type="hidden" name="starCount" id="starCount" value="" /> <input type="hidden" name="prodId" id="prodId" value="" /> <input type="hidden"
					name="orderDetailId" id="orderDetailId" value="" />

			</div>
			<div class="w-textarea">
				<textarea name="content" class=" w_require w_content" id="Etxt" maxlength="200" placeholder="请写下您对商品的感受，对他人的帮助很大哦~,长度10-200字之间"></textarea>
			</div>
			<div class=" w-addPhoto" style="border-top:1px solid #e5e5e5">
				<div class="">
					<label class="">评价图片：<span class="required"> * </span>
					</label>
					<div>
						<div id="uploader">
							<div id="uploader-demo" style="padding-left: 37px;  padding-right: 37px;">
								<input type="hidden" name="picuri" id="picurl_wx" value="1" />
								<div id="imgPathWebUploader" style="display: none"></div>
								<div id="filePicker" style="">选择图片（最多上传5张图片）</div>
								<!--用来存放item-->
								<div style="margin-left: 40px;margin-top: 10px;display: none;text-align:left" id="isfirstWebUploader"></div>
								<div id="fileList" class="uploader-list filelist"></div>
							</div>
						</div>
						<!-- 图片建议尺寸为350*350，比例1比1 -->
					</div>
				</div>
			</div>
		</div>
		<!--提交按钮-->
		<div class="w-bottomBtn" style="position:relative">
			<input type="submit" class="w-subBtn w-margin-top" value="提交" />
		</div>
	</form>
</div>

<script>
	document.title = "商品评价";
	cho = getRequest("ords");
	jQuery(document).ready(function() {
		/* $('.ellipsis').raty({
			score : 5
		});  */
		pro(cho);
	});
	function pro(id) {
		$.post(SHOPDOMAIN + '/interfaces/orderSele/getpro.html', {
			id : id,
		}, function(data) {
			if (data.res_code == '0') {
				//alert(imgZuhe(data.OrdersDt.prodUri, '_300'));
				$("#ima").attr("src", "${USERIMGSRC}" + imgZuhe(data.OrdersDt.prodUri, '_300'));
				$("#ima").attr("alt", data.OrdersDt.prodName);
				$("#prodId").val(data.OrdersDt.prodId);
				$("#orderDetailId").val(data.OrdersDt.id);
			}
		}, "json").error(function() {
			showError();
		});
	}
	//     评分
	$('#w-stars').raty({
		width : 300
	});
	var oldImgUrl = '${userCommentImgs }';//修改-以前的图片信息json格式
	var fileNumLimit = 10;
	var imgtype = "userCommentImg";
	var punit = "${userCommentInfo.unit}";
	jQuery(document).ready(function() {
		FormValidation.init();
		//只有当修改的时候才调用该方法
		if (oldImgUrl != "") {
			getImgByJson(oldImgUrl);
			//	$("[name='inventorynumbers']").next().html(punit);
		}
	});
	$(".w-subBtn").click(function(e) {
		$("#starCount").val($("input[name='score']").val());
		var $imgPathWebUploader = $("#imgPathWebUploader");
		/* if ($imgPathWebUploader.html() == "") {
			showMessage("请至少上传一张照片");
			return false;
		} else { */
		$("#picurl_wx").val($("#imgPathWebUploader").html());
	});

	//    textarea默认内容
	//    var placeholder = '长度10-200字之间\n请写下您对商品的感受，对他人的帮助很大哦~';
	//    $('textarea').val(placeholder);
	//    $('textarea').focus(function() {
	//        if ($(this).val() == placeholder) {
	//            $(this).val('');
	//        }
	//    });
	//
	//    $('textarea').blur(function() {
	//        if ($(this).val() == '') {
	//            $(this).val(placeholder);
	//        }
	//    });
</script>
<jsp:include page="foot-validate.jsp"></jsp:include>
<script src="${SHOPDOMAIN}/js/webuploader/wximgupload_wap.js" type="text/javascript"></script>
<script>
	jQuery(document).ready(function() {
		FormValidation.init();
	});
</script>

