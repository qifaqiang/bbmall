<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/wap/personalCenter.css" />
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
<script id="interpolationtmpl" type="text/x-dot-template">
{{for(var i=0;i<it.length;i++){ }}
    {{var uslog=it[i];}}
    <a href="personalCenter-refundDetails.html?ords={{=uslog.id}}">
      <div class="w-orderStatusNav w-show">
		<div class="w-orderList">
        <div class="w-orderList-title" style="border-bottom:none">
                   	<span class="fl"> 订单号:{{=uslog.ordersn}}</span>
		</div>    
      {{for(var j=0;j<uslog.nextlist.length;j++){ }}
         {{var ord=uslog.nextlist[j];}}
    <div class="w-orderList-content">
		  <img src="${USERIMGSRC}{{=imgZuhe(ord.prod_uri,'_300')}}" alt="" /> 
          <span> 
            <em class="ellipsis">{{=ord.prod_name}}</em><br /> 
            <em class="w-specifications">规格：{{=ord.prod_count}}{{=ord.prod_spec_name}}</em><br />
		 </span>
          {{ if(uslog.nextlist.length==j+1) {}}
				<div class="w-orderList-operation-button"style="margin-top: -10px;">
			         <button class="w-btnOk" onclick="javascript:window.location.href='personalCenter-refundDetails.html?ords={{=uslog.id}}'">退款详情</button>
		       </div>
          {{}}} 
 </div>
	
{{}}}
<!--<em>合计 <i class="w-totalPrice">￥{{=uslog.order_Account}}</i></em>-->
  </div>
     </div>
	</a>  
{{}}}
</script>
<div class="w-main">
	<!--头部-->
		<div id="showUslogList"></div>
	<div class="w-noOrder" style="display:none">
		<img src="${SHOPDOMAIN}/front/images/wap/hasnoOrder.png" alt="" />

		<p class="w-prompt">您还没有退款记录</p>

		<button class="w-randomBtn" onclick="window.location.href='index.html'">随便逛逛</button>
	</div>
</div>
<script>
document.title="退款管理";
	jQuery(document).ready(function() {
		//获取我的订单列表 
		var datas = {};
		var currentNum = 0;
		$(function() {
			currentNum = 1;
			getList("${SHOPDOMAIN}/interfaces/orderSele/refuondOrdList.html", datas, currentNum, true);
			scroll(0, 0);
		});
		$(window).scroll(function() {
			var scrollTop = $(this).scrollTop();
			var scrollHeight = $(document).height();
			var windowHeight = $(this).height();
			if (scrollTop + windowHeight == scrollHeight) {
				currentNum++;
				getList("${SHOPDOMAIN}/interfaces/orderSele/refuondOrdList.html", datas, currentNum, true);
			}
		});
		function getList(url, datas, num, isAppend) {
			$.ajax({
				url : url,
				type : "post",
				data : {
					"currentPage" : num
				},
				dataType : "json",
				success : function(data) {
					var totalNum = parseInt(data.pageCount);
					var evalText = doT.template($("#interpolationtmpl").html());
					if (data.pageCount == 0) {
						$(".w-noOrder").css('display', 'block');
					}
					if (num <= totalNum) {
						if (data.pageCount > 0) {
							$("#showUslogList").append(evalText(data.list));
						}
					} else {
						num = totalNum;
						if (!isAppend) {
							if (data.choose == '0') {
								$("#showUslogList").empty();
							}
						}
					}
				}
			});
		}
	});
</script>