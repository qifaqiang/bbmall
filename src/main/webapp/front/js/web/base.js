//=====================全局函数========================
//Tab控制函数
function tabs(tabId, tabNum){
	// 设置点击后的切换样式
	$(tabId + " .tab li").removeClass("curr");
	$(tabId + " .tab li").eq(tabNum).addClass("curr");
	// 根据参数决定显示内容
	$(tabId + " .tabcon").hide();
	$(tabId + " .tabcon").eq(tabNum).show();
}
// =====================全局函数========================

// ==================图片详细页函数=====================
// 鼠标经过预览图片函数
function preview(img){
	$("#preview .jqzoom img").attr("src",$(img).attr("mImg"));
	$("#preview .jqzoom img").attr("jqimg",$(img).attr("bimg"));
}

function initBase(){
	$sidel=$(".slides").children().eq(0).children();
// 图片放大镜效果
	$(".jqzoom").html("<img width='400px' jqimg='"+$sidel.attr("mimg")+"' src='"+$sidel.attr("bimg")+"' />").jqueryzoom({xzoom:380,yzoom:410});

// 图片预览小图移动效果,页面加载时触发
	var tempLength = 0; // 临时变量,当前移动的长度
	var viewNum = 5; // 设置每次显示图片的个数量
	var moveNum = 1; // 每次移动的数量
	var moveTime = 300; // 移动速度,毫秒
	var scrollDiv = $(".spec-scroll .items ul"); // 进行移动动画的容器
	var scrollItems = $(".spec-scroll .items ul li"); // 移动容器里的集合
	var moveLength = scrollItems.eq(0).width() * moveNum; // 计算每次移动的长度
	var countLength = (scrollItems.length - viewNum) * scrollItems.eq(0).width(); // 计算总长度,总个数*单个长度
	  
	// 下一张
	$(".spec-scroll .next").bind("click",function(){
		if(tempLength < countLength){
			if((countLength - tempLength) > moveLength){
				scrollDiv.animate({top:"-=" + moveLength + "px"}, moveTime);
				tempLength += moveLength;
			}else{
				scrollDiv.animate({top:"-=" + (countLength - tempLength) + "px"}, moveTime);
				tempLength += (countLength - tempLength);
			}
		}
	});
	// 上一张
	$(".spec-scroll .prev").bind("click",function(){
		if(tempLength > 0){
			if(tempLength > moveLength){
				scrollDiv.animate({left: "+=" + moveLength + "px"}, moveTime);
				tempLength -= moveLength;
			}else{
				scrollDiv.animate({left: "+=" + tempLength + "px"}, moveTime);
				tempLength = 0;
			}
		}
	});
}
// ==================图片详细页函数=====================
