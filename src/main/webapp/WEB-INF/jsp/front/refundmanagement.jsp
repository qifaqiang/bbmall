<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="top2.jsp"></jsp:include>
<link href="${SHOPDOMAIN}/front/css/web/share.css" rel="stylesheet" type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/Personal.css" rel="stylesheet" type="text/css">
<script src="${SHOPDOMAIN}/front/js/web/ntab.js"></script>
<script id="interpolationtmpl" type="text/x-dot-template">
<tr class="per_return_title">
	<td width="12%" align="left">订单信息</td>
	<td width="11%" align="center">订单编号</td>
	<td width="22%" align="left">商品名称</td>
	<td width="16%" align="left">基地信息</td>
	<td width="11%" align="center">交易金额</td>
	<td width="11%" align="center">退款状态</td>
	<td width="8%" align="center">操作</td>
</tr>
{{for(var i=0;i<it.list.length;i++){ }}
    {{var uslog=it.list[i];}}
        {{for(var j=0;j<uslog.nextlist.length;j++){ }}
			{{var ord=uslog.nextlist[j];}}
				{{ if(j==uslog.nextlist.length-1) {}}
					<tr class="per_return_btm">
				{{} else{}}
					<tr>
				{{}}}
					<td><a href="productDetail.html?prodId={{=ord.prod_id}}"><img src="${USERIMGSRC}{{=imgZuhe(ord.prod_uri,'_300')}}" width="100" height="100" alt="" /></a></td>
					{{ if(j==0) {}}
						<td align="center"> {{=uslog.ordersn}}</td>
					{{} else{}}
						<td align="center"></td>
					{{}}}
						<td>{{=ord.prod_name}}{{=ord.prod_spec_name}}</td>
						{{ if(j==0) {}}
							<td>{{=uslog.companyName}}</td>
			             	<td align="center">{{=(uslog.order_Account).toFixed(2)}} </td>
						    <td align="center" class="per_return_greentxt">
                               {{ if(uslog.orderRetstatus== '0') {}}
                                  		审核中
			                   {{}}}
                               {{ if(uslog.orderRetstatus== '1') {}}
                                  		退款成功
			                   {{}}}
                                {{ if(uslog.orderRetstatus== '2') {}}
                                  		退款失败
			                   {{}}}
                           </td>
						   <td align="center"><a href="refundmanagement_applyinfor.html?ords={{=uslog.id}}">查看</a></td>
			           	{{} else{}}
							<td align="center"></td>
					     	<td align="center" class="per_return_redtxt"></td>
						    <td align="center" class="per_return_greentxt"></td>
						    <td align="center"></td>
		        	   {{}}}
				</tr>
			{{}}}
	{{}}}
	<tr style="background-color:#f1f1f1;">
  		<td colspan="8">
	<div>
 	<div class="page_and_btn" style="padding-top:10px; height:50px; background-color:#f1f1f1;">
		<div></div>{{=it.pageStr}}
	</div>
   </td>
</tr>
</script>

<div class="per_main">
	<!--Personal center left-->
	<jsp:include page="centerLeft.jsp"></jsp:include>
	<!--Personal center right-->
	<div class="per_mainright flW">
		<!--位置-->
		<div class="per_main_title">退款管理</div>
		<div class="per_return">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tbody id="showUslogList">
				</tbody>
			</table>
			<div class="per_noorder" id="three" style="display:none">
				<div class=" per_ per_noorderleft flW">
					<img src="${SHOPDOMAIN}/front/images/web/per_noorder.png" width="104" height="48" alt="" />
				</div>
				<div class=" per_noorderright flW">
					<ul>
						<li>SORRY~您没有相关订单哦~</li>
						<li>可以去看看有哪些想买的</li>
						<li><a href="index.html"><input type="button" name="submit" id="submit" class="per_evaluate_btn" value="随便逛逛"></a></li>
					</ul>
				</div>
				<div class="fox"></div>
			</div>
		</div>
		<div class="fox"></div>
	</div>
	<div class="fox"></div>
</div>

<jsp:include page="footShop.jsp"></jsp:include>
<script>
	document.title="个人中心-退款管理";
	jQuery(document).ready(function() {
		//获取我的订单列表 
		var datas = {};
		var currentNum = 0;
		$(".tuikuan").addClass("per_nowcolor");
		$("#lingquan").html("我的退款");
		$(function() {
			currentNum = 1;
			getList("${SHOPDOMAIN}/interfaces/orderSele/refuondOrdList.html", datas, currentNum);
		});
		function getList(url, datas, num) {
			$.ajax({
				url : url,
				type : "post",
				data : {
					"currentPage" : num
				},
				dataType : "json",
				success : function(data) {
					if (data.res_code == '0') {
						var evalText = doT.template($("#interpolationtmpl").html());
						if (data.pageCount == 0) {
							$(".per_noorder").css('display', 'block');
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
				},
				error : function(data) {
					showMessage(data.message);
					;
				}
			});
		}
	});
</script>
