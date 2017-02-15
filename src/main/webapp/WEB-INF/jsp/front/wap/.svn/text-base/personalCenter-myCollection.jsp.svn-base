<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
<script id="interpolationtmpl" type="text/x-dot-template">
{{for(var i=0;i<it.length;i++){ }}
 {{var uslog=it[i];}}
 <a href="productShow.html?prodId={{=uslog.proId}}">
 <div class="w-orderList w-borderBottomNone">
        <!--收藏列表productShow.html?prodId=1369-->
   <div class="w-orderList-content" style="background:white" >
      <img src="${USERIMGSRC}{{=imgZuhe(uslog.picuri,'_300')}}" alt=" "/>
       <span>
             <em class="ellipsis">{{=uslog.name}}</em><br/>
             <input type="hidden" id ="productId" value="{{=uslog.proId}}">
             <em>
              <i class="w-totalPrice">￥{{=(uslog.price).toFixed(2)}}</i>  
             <em class="w-right-icon2" >  
              <i class="wdelete w-delCollection  w-undelCollection fr"></i></em>
           </em>
       </span>
   </div>
</div>
</a>
{{ } }}
</script>
<style>
.w-orderList {
	background: none;
	border-top: none
}
</style>
<div class="w-main">
	<!--头部-->
	<div class="w-orderList w-borderBottomNone">
		<!--收藏列表-->
		<div id="showUslogList"></div>
		<div class="couponBtn1 textCenter1  w-fontsize301 w-color91  jiazai" display="block">
			<div class="loadingNowDiv">
				<div class="spinner">
					<div class="spinner-container container1">
						<div class="circle1"></div>
						<div class="circle2"></div>
						<div class="circle3"></div>
						<div class="circle4"></div>
					</div>
					<div class="spinner-container container2">
						<div class="circle1"></div>
						<div class="circle2"></div>
						<div class="circle3"></div>
						<div class="circle4"></div>
					</div>
					<div class="spinner-container container3">
						<div class="circle1"></div>
						<div class="circle2"></div>
						<div class="circle3"></div>
						<div class="circle4"></div>
					</div>
				</div>
				<span class="loadingNow">&nbsp;正在加载中...</span>
			</div>
		</div>
		<div class="w-noOrder" style="display:none">
			<p class="w-prompt">暂无收藏</p>
			<img src="${SHOPDOMAIN}/front/images/wap/nocollect.png" alt="" />

		</div>
	</div>
</div>

<script src="${SHOPDOMAIN}/front/js/wap/jquery.hDialog.js"></script>
<script>
	document.title = "我的收藏";
	//删除收藏

	$(document).on('click', '.wdelete', function(e) {
		e.preventDefault();
		id = $("#productId").val();
		deleteColl(id);
	});
	function deleteColl(id) {
		$.post(SHOPDOMAIN + '/interfaces/userCollection/deleteCollect.html', {
			id : id
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
</script>
<jsp:include page="foot-validate.jsp"></jsp:include>
<script>
	var datas = {};
	//  获取列表   
	$(window).scroll(function() {
		var scrollTop = $(this).scrollTop();
		var scrollHeight = $(document).height();
		var windowHeight = $(this).height();
		if (scrollTop + windowHeight == scrollHeight) {
			currentNum++;
			getList("${SHOPDOMAIN}/interfaces/userCollection/colleLists.html", datas, currentNum, true);
		}
	});
	$(function() {
		currentNum = 1;
		getList("${SHOPDOMAIN}/interfaces/userCollection/colleLists.html", datas, currentNum, true);
		scroll(0, 0);
	});
	function getList(url, datas, num, isAppend) {
		$.post(url, {
			"currentPage" : num,
			"showCount" : 10
		}, function(data) {
			$(".jiazai").hide();
			var totalNum = data.list.length;
			if (data.res_code == '0') {
				if (data.list.length == 0) {
					$(".w-noOrder").css('display', 'block');
				} else {
					if (num <= totalNum) {
						var evalText = doT.template($("#interpolationtmpl").html());
						$("#showUslogList").append(evalText(data.list));
					} else {
						num = totalNum;
						if (!isAppend) {
							$("#interpolationtmpl").empty();
						}
					}
				}
			} else {
				showMessage(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}
	FormValidation.init();
</script>