<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="top2.jsp"></jsp:include>

<script src="${SHOPDOMAIN}/front/js/web/ntab.js"></script>
<script src="${SHOPDOMAIN}/front/js/jquery.raty.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/web/zzsc.js"></script>
<link href="${SHOPDOMAIN}/front/css/web/share.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/js/webuploader/webuploader.css">
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/js/webuploader/style.css">
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/js/webuploader/demo2.css">
<script type="text/javascript" src="${SHOPDOMAIN}/js/webuploader/webuploader.js"></script>
<style>
#w-stars img {width: 34px;	height: 34px;	border: none;	display: inline-block;	margin-left: 10px;
}

.w-addPhoto {	text-align: center;}

element.style {	background: #ffffff none repeat scroll 0 0;	cursor: pointer;	display: block;	height: 100%;	opacity: 0;	width: 100%;
}
</style>

<script type="text/javascript">
	var carr_in_1_w = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
	var carr_in_1_h = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
	var carr_jushang = (carr_in_1_h - 100) / 2;
	//居上高度=  （总高度-div内同高度 ）/2
	var carr_juzuo = (carr_in_1_w - 460) / 2;
	$(document).ready(function() {
		$('.per_boxmain').css('height', carr_in_1_h);
		$('.per_box_w').css('width', carr_in_1_w);
		$('.per_box').css('margin', carr_jushang + 'px ' + carr_juzuo + 'px 0' + carr_juzuo + 'px');
		$('.per_boxmain').css('display', 'none');
	});
</script>

<script id="interpolationtmpl" type="text/x-dot-template">
{{for(var i=0;i<it.list.length ;i++){ }}
{{var uslog=it.list[i];}}
{{ if(uslog.nextlist!="") {}}  
<h3 id="i" style="background-color:#f1f1f1;width: 948px; height: 40px; line-height: 40px; border: solid 1px #E5E5E5;  margin-top: 20px; padding: 5px;">
	{{=uslog.companyName}}<span>订单编号{{=uslog.ordersn}}</span>
</h3>
{{for(var j=0;j<uslog.nextlist.length ;j++){ }}
	{{var ord=uslog.nextlist[j];}}
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tbody>
			<tr>
				<td width="108" rowspan="2"><a href="productDetail.html?prodId={{=ord.prod_id}}"><img src="${USERIMGSRC}{{=imgZuhe(ord.prod_uri,'_300')}}" alt="查看详情"width="100" height="100" alt="" /></a></td>
				<td width="172" rowspan="2" align="left">{{=ord.prod_name}}</td>
				<td width="50" rowspan="2" align="center">{{=ord.prod_spec_name}}</td>
				<td width="75" rowspan="2" align="center">X{{=ord.prod_count}}</td>
				{{ if(j==0) {}}
				<td width="92" rowspan="2" align="center">{{=(uslog.order_Account).toFixed(2)}}</td>
				<td width="91" rowspan="2" align="center">
					{{ if(uslog.status=="0") {}}用户取消 {{}}}
					{{ if(uslog.status=="11") {}}未付款{{}}}
					{{ if(uslog.status=="20") {}}已付款{{}}} 
					{{ if(uslog.status=="22") {}}商家确认{{}}}
					{{ if(uslog.status=="30") {}}卖家已发货{{}}}
					{{ if(uslog.status=="40") {}}已成交{{}}}
					{{ if(uslog.status=="50") {}}申请退款{{}}}
					{{ if(uslog.status=="60") {}}退款成功 {{}}}
					{{ if(uslog.status=="100") {}}系统自动取消{{}}} 
				</td>
				<td width="128" rowspan="2" align="center">{{=uslog.addtime}}</td>
				<td width="85" align="center"> 
					{{ if(uslog.status=="11") {}}
					<p class="per_order_link1">
						<a href="javascript:void(0);"onclick="javascript:window.location.href='${SHOPDOMAIN}/wap/fontOrder/toPcToPay.html?orderid={{=uslog.id}}&orderAccount={{=uslog.order_Account.toFixed(2)}}&pcOrdersn={{=uslog.ordersn}}'">立即付款</a>
					</p>
					<p  class="per_order_link1">
						<a href="javascript:void(0);" onclick="quxiao({{=uslog.id}})">取消订单</span>
					</p>
					{{}}}
                              
					{{ if(uslog.status=="20"||uslog.status=="22"||uslog.status=="30"||uslog.status=="40") {}}
						{{ if(uslog.is_over==0) {}}
							<p class="per_order_link1"><a href="refundmanagement_applyinfor.html?ords={{=uslog.id}}">退&nbsp;&nbsp;货</a></p>
						{{}}}
					{{}}}
					{{ if(uslog.status=="50"||uslog.status=="60") {}}
						<p class="per_order_link1"> <a href="refundmanagement_applyinfor.html?ords={{=uslog.id}}">查看详情</a></p>
					{{}else {}}
						<p class="per_order_link1"> <a href="myorder_details.html?id={{=uslog.id}}">查看详情</a></p>
					{{}}}
					{{ if(uslog.status=="22"||uslog.status=="30") {}}
						<p class="per_order_link1"><a href="myorder_details.html?id={{=uslog.id}}">确认收货</a></p></td>
					{{}}}
					{{ if(uslog.status=="40" && ord.location_status==0&&it.choose==4) {}} 
						<p class="per_order_link1" id="showOrNo"> <a href="javascript:void(0)" onclick="showOrNo({{=ord.odetId}})">评&nbsp;&nbsp;价</a></p>
					</td>
				</tr>
    <form id="form_config_{{=ord.odetId}}" method="post">
	<div class="per_evaluate2 flW" >
		<div class="per_evaluate_btm per_evaluate_bor">
			<table width="100%" border="0" class="evalu"cellspacing="0" cellpadding="0" style="display:none" id="form-horizontal_{{=ord.odetId}}">
				<tbody>
					<tr>
						<td width="8%" align="right">评分：</td>
						<td width="92%">
							<div class="w-orderList" style="display:block">
								<div class="w-orderList-content">
									  <span> <em class="w-specifications" id="w-stars_{{=ord.odetId}}"></em><br /></span> <input type="hidden" name="starCount"
										id="starCount_{{=ord.odetId}}" value="" class="startCount_{{=ord.odetId}}" /> <input type="hidden" name="prodId" id="prodId" value="{{=ord.prod_id}}" /> <input type="hidden" name="orderDetailId"
										id="orderDetailId" value="{{=ord.odetId}}" />
								</div>
						</td>
					</tr>
					<tr>
						<td align="right" valign="top">评价：</td>
						<td><textarea class="per_evaluate_textarea w_require w_content" name="content" id="Etxt_{{=ord.odetId}}" placeholder="（1-500字）请写下您对商品的感受，您的评价对他人有很大的帮助哦~"></textarea></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
					<div class=" w-addPhoto" >
					<div class="">
					<label class="">评价图片：
					</label>
					<div>
						<div id="uploader">
							<div id="uploader-demo" style="padding-left: 37px;  padding-right: 37px;">
								<input type="hidden" name="picuri" id="picurl_wx_{{=ord.odetId}}" value="1" />
								<div id="imgPathWebUploader_{{=ord.odetId}}" style="display: none"></div>
								<div id="filePicker_{{=ord.odetId}}" onclick="deal({{=ord.odetId}});" style="">选择图片（最多上传5张图片）</div>
								<!--用来存放item-->
								<div style="margin-left: 40px;margin-top: 10px;display: none;text-align:left" id="isfirstWebUploader_{{=ord.odetId}}"></div>
								<div id="fileList_{{=ord.odetId}}"  onmouseover="bigImg({{=ord.odetId}})" class="uploader-list filelist"></div>
							</div>
						</div>
						<!-- 图片建议尺寸为350*350，比例1比1 -->
					</div>
					</div>
					</div>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="button" name="submit" id="submit" class="per_evaluate_btn upload_btn" onclick="evalution({{=ord.odetId}},{{=ord.prod_id}})"value="提交评价"></td>
					</tr>
				</tbody>
			</table>
		</div>
		</div>
	</form>

	{{}}}
	{{} else{ }}
	<td width="90" rowspan="3" align="center"></td>
	<td width="91" rowspan="2" align="center"></td>
	<td width="128" rowspan="2"></td>
	<td width="85" rowspan="2" align="center">{{ if(uslog.status=="40" && ord.location_status==0&&it.choose==4) {}}
		<p class="per_order_link2">
			 <p class="per_order_link1" id="showOrNo"> <a href="javascript:void(0)" onclick="showOrNo({{=ord.odetId}})">评&nbsp;&nbsp;价</a></p>
		</p> 
	</td>
    <tr>
    <form id="form_config_{{=ord.odetId}}" class="evalu" method="post">
	<div class="per_evaluate2 flW" >
		<div class="per_evaluate_btm per_evaluate_bor">

			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:none"  id="form-horizontal_{{=ord.odetId}}">
				<tbody>
					<tr>
						<td width="8%" align="right">评分：</td>
						<td width="92%">
							<div class="w-orderList" style="display:block">
								<div class="w-orderList-content">
									  <span> <em class="w-specifications" id="w-stars_{{=ord.odetId}}" ></em><br /></span> <input type="hidden" name="starCount"
										id="starCount_{{=ord.odetId}}" value="" /> <input type="hidden" name="prodId" id="prodId" value="{{=ord.prod_id}}" /> <input type="hidden" name="orderDetailId"
										id="orderDetailId" value="{{=ord.odetId}}" />

								</div>
						</td>
					</tr>
					<tr>
						<td align="right" valign="top">评价：</td>
						<td><textarea class="per_evaluate_textarea w_require w_content" name="content" id="Etxt_{{=ord.odetId}}" placeholder="（1-500字）请写下您对商品的感受，您的评价对他人有很大的帮助哦~"></textarea></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
              <div class=" w-addPhoto">
				<div class="">
					<label class="">评价图片：<span class="required"> * </span>
					</label>
					<div>
						<div id="uploader">
							<div id="uploader-demo" style="padding-left: 37px;  padding-right: 37px;">
								<input type="hidden" name="picuri" id="picurl_wx_{{=ord.odetId}}" value="1" />
								<div id="imgPathWebUploader_{{=ord.odetId}}" style="display: none"></div>
								<div id="filePicker_{{=ord.odetId}}" onclick="deal({{=ord.odetId}});" style="">选择图片（最多上传5张图片）</div>
								<!--用来存放item-->
								<div style="margin-left: 40px;margin-top: 10px;display: none;text-align:left" id="isfirstWebUploader_{{=ord.odetId}}"></div>
								<div id="fileList_{{=ord.odetId}}" onmouseover="bigImg({{=ord.odetId}})" class="uploader-list filelist"></div>
							</div>
						</div>
						<!-- 图片建议尺寸为350*350，比例1比1 -->
					</div>
				</div>
			</div>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="button" name="submit" id="submit_{{=ord.odetId}}" onclick="evalution({{=ord.odetId}},{{=ord.prod_id}})"class="per_evaluate_btn upload_btn" value="提交评价" ></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	</form>
	</tr>
	{{}}}
	{{}}}
	</tr>
	</tbody>
	</table>
	{{}}} 
	{{}}}
	{{}}} 
	<div class="fox"></div>

 	<div class="page_and_btn"style="padding-top:10px; height:50px; background-color:#f1f1f1;">
		<div></div>{{=it.pageCounts}}
	</div>
</script>

<div class="per_main">
	<!--Personal center left-->
	<jsp:include page="centerLeft.jsp"></jsp:include>
	<div class="per_mainright flW">
		<!--位置-->
		<div class="per_main_title">我的订单</div>
		<div class="per_order_title">
			<ul id="myTab0">
				<li class="active" id="allpr" style="cursor:pointer" onclick="nTabs(this,0)">全部订单</li>
				<li class="normal" id="onepr" style="cursor:pointer" onclick="nTabs(this,1)">待付款</li>
				<li class="normal" id="twopr" style="cursor:pointer" onclick="nTabs(this,2)">待收货</li>
				<li class="normal" id="thrpr" style="cursor:pointer" onclick="nTabs(this,3)">已完成</li>
				<li class="normal" id="foupr" style="cursor:pointer" onclick="nTabs(this,4)">待评价</li>
			</ul>
		</div>
		<div class="active" id="myTab0_Content0">
			<div class="per_order_con border_btm">
				<div class="per_order_contitle per_order_bgcolor">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td width="120">商品</td>
								<td width="196"></td>
								<td width="74" align="center">规格</td>
								<td width="78" align="center">单价X数量</td>
								<td width="102" align="center">共计（元）</td>
								<td width="103" align="center">状态</td>
								<td width="140" align="center">时间</td>
								<td width="97" align="center">操作</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="per_order_infor" id="showpaymentList">
					<div class="page_and_btn"></div>
				</div>
				<div class="per_noorder" id="one" style="display:none">
					<div class=" per_ per_noorderleft flW">
						<img src="${SHOPDOMAIN}/front/images/web/per_noorder.png" width="104" height="48" alt="" />
					</div>
					<div class=" per_noorderright flW">
						<ul>
							<li>SORRY~您没有相关订单哦~</li>
							<li>可以去看看有哪些想买的</li>
							<li><a href="index.html"><input type="submit" name="submit" id="submit" class="per_evaluate_btn" value="随便逛逛"></a></li>
						</ul>
					</div>
					<div class="fox"></div>
				</div>
			</div>
		</div>
		<div class="none" id="myTab0_Content1">
			<div class="per_order_con border_btm">
				<div class="per_order_contitle per_order_bgcolor">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td width="120">商品</td>
								<td width="196">&nbsp;</td>
								<td width="74" align="center">规格</td>
								<td width="68" align="center">数量</td>
								<td width="102" align="center">共计（元）</td>
								<td width="103" align="center">状态</td>
								<td width="140" align="center">时间</td>
								<td width="97" align="center">操作</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="per_order_infor" id="showpaymentListPay">
					<div class="page_and_btn1"></div>
				</div>
				<div class="per_noorder" id="two" style="display:none">
					<div class=" per_ per_noorderleft flW">
						<img src="${SHOPDOMAIN}/front/images/web/per_noorder.png" width="104" height="48" alt="" />
					</div>
					<div class=" per_noorderright flW">
						<ul>
							<li>SORRY~您没有相关订单哦~</li>
							<li>可以去看看有哪些想买的</li>
							<li><a href="index.html"><input type="submit" name="submit" id="submit" class="per_evaluate_btn" value="随便逛逛"></a></li>
						</ul>
					</div>
					<div class="fox"></div>
				</div>
			</div>
		</div>
		<div class="none" id="myTab0_Content2">
			<div class="per_order_con border_btm">
				<div class="per_order_contitle per_order_bgcolor">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td width="120">商品</td>
								<td width="196">&nbsp;</td>
								<td width="74" align="center">规格</td>
								<td width="68" align="center">数量</td>
								<td width="102" align="center">共计（元）</td>
								<td width="103" align="center">状态</td>
								<td width="140" align="center">时间</td>
								<td width="97" align="center">操作</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="per_order_infor" id="showpaymentListRecev"></div>
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
		</div>
		<div class="none" id="myTab0_Content3">
			<div class="per_order_con border_btm">
				<div class="per_order_contitle per_order_bgcolor">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td width="120">商品</td>
								<td width="196">&nbsp;</td>
								<td width="74" align="center">规格</td>
								<td width="68" align="center">数量</td>
								<td width="102" align="center">共计（元）</td>
								<td width="103" align="center">状态</td>
								<td width="140" align="center">时间</td>
								<td width="97" align="center">操作</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="per_noorder" id="four" style="display:none">
					<div class=" per_ per_noorderleft flW">
						<img src="${SHOPDOMAIN}/front/images/web/per_noorder.png" width="104" height="48" alt="" />
					</div>
					<div class=" per_noorderright flW">
						<ul>
							<li>SORRY~您没有待评价订单哦~</li>
							<li>可以去看看有哪些想买的</li>
							<li><a href="index.html"><input type="button" name="submit" id="submit" class="per_evaluate_btn" value="随便逛逛" /></a></li>
						</ul>
					</div>
					<div class="fox"></div>
				</div>
				<div class="per_order_infor" id="showpaymentListComp"></div>
			</div>
		</div>
		<div class="none" id="myTab0_Content4">
			<div class="per_order_con border_btm">
				<div class="per_order_contitle per_order_bgcolor">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td width="120">商品</td>
								<td width="196">&nbsp;</td>
								<td width="74" align="center">规格</td>
								<td width="68" align="center">数量</td>
								<td width="102" align="center">共计（元）</td>
								<td width="103" align="center">状态</td>
								<td width="140" align="center">时间</td>
								<td width="97" align="center">操作</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="per_noorder" id="five" style="display:none">
					<div class=" per_ per_noorderleft flW">
						<img src="${SHOPDOMAIN}/front/images/web/per_noorder.png" width="104" height="48" alt="" />
					</div>
					<div class=" per_noorderright flW">
						<ul>
							<li>SORRY~您没有相关订单哦~</li>
							<li>可以去看看有哪些想买的</li>
							<li><a href="index.html"><input type="button" name="submi" id="submi" class="per_evaluate_btn" value="随便逛逛" /></a></li>
						</ul>
					</div>
					<div class="fox"></div>
				</div>
				<div class="per_order_infor" id="showpaymentListEva"></div>
				<div id="uploader" style="display:none">
					<div id="uploader-demo" style="padding-left: 37px;  padding-right: 37px;">
						<input type="hidden" name="picuri" id="picurl_wx_1" value="1" />
						<div id="imgPathWebUploader_1" style="display: none"></div>
						<div id="filePicker_1" style="">选择图片（最多上传5张图片）</div>
						<!--用来存放item-->
						<div style="margin-left: 40px;margin-top: 10px;display: none;text-align:left" id="isfirstWebUploader_1"></div>
						<div id="fileList_1" class="uploader-list filelist"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>
<div class="fox"></div>

<jsp:include page="footShop.jsp"></jsp:include>

<script>
	document.title = "个人中心-我的订单";
	var oldImgUrl = '${userCommentInfo}';//修改-以前的图片信息json格式
	var evacom = 1;
	var fileNumLimit = 10000;
	var multiple = false;
	var imgtype = "userCommentImg";
	var punit = "${userCommentInfo.unit}";
	if (oldImgUrl != "") {
		getImgByJson(oldImgUrl);
	}

	jQuery(document).ready(function() {
		FormValidation.init();
	});
	function bigImg(id) {
		evacom = id;
	}
	function deal(id) {
		evacom = id;
		$list = $("#fileList_" + evacom);
	}
	function amy_evaluate() {
		$(".per_evaluate").css("display", "block");
	}
	function evaluate_off() {
		$(".per_evaluate").css("display", "none");

	}

	function showOrNo(ids) {
		Imgmap = new Map();
		$('#w-stars_' + ids).raty({
			width : 300
		});
		evacom = ids;
		/* var fileNumLimit_evacom = 5; */
		var itid = "#filePicker_" + ids;
		$.each(Imgmothermap, function(i, item) {
			if (ids != item.id) {
				uploader.addButton({
					id : itid,
					innerHTML : '选择图片（最多上传5张图片）',
					multiple : false
				});
			}
		});
		Imgmap = new Map();
		Imgmothermap.put("eva_" + evacom, Imgmap);
		$list = $("#fileList_" + evacom);
		if (Imgmothermap == null) {
			evacom = ids;
			uploader.addButton({
				id : itid,
				innerHTML : '选择图片（最多上传5张图片）',
				multiple : false
			});
		}
		var bloc = "#form-horizontal_" + ids;
		$(bloc).toggle();
	}
	$(document).ready(function() {
		$("#lingquan").html("我的订单");
		$(".per_evaluate_del").click(function() {
			$(this).parent().remove();
		});
	});
	function box() {
		$(".per_boxmain").css("display", "block");
	}
	function offbox() {
		$(".per_boxmain").css("display", "none");
	}
</script>
<script>
	//var totalPage = 0;
	$(function() {
		$(".myorder").addClass("per_nowcolor");
		ids = getRequest("ids");
		if (ids > 0 && ids < 5) {
			nTabs($('#myTab0 li').eq(ids)[0], ids);
		}
		getList(1, 0);
	});

	function nTabs(thisObj, Num) {
		//alert(thisObj.id);
		if (thisObj.className == "active")
			return;
		var tabObj = thisObj.parentNode.id;
		var tabList = document.getElementById(tabObj).getElementsByTagName("li");
		for (i = 0; i < tabList.length; i++) {
			if (i == Num) {
				thisObj.className = "active";
				document.getElementById(tabObj + "_Content" + i).style.display = "block";
			} else {
				tabList[i].className = "normal";
				document.getElementById(tabObj + "_Content" + i).style.display = "none";
			}
		}
		getList(1, Num);
	}
	function getList(num, cho) {
		if (cho == null) {
			for (var i = 0; i < $("#myTab0 li").length; i++) {
				if ($("#myTab0 li").eq(i).attr("class") == "active") {
					cho = i;
				}
			}
		}
		$.ajax({
			url : "${SHOPDOMAIN}/interfaces/orderSele/orderList.html",
			type : "post",
			data : {
				"cho" : cho,
				"currentPage" : num
			},
			dataType : "json",
			success : function(data) {
				var evalText = doT.template($("#interpolationtmpl").html());
				var page = data.pageCount;
				//eval();
				if (data.choose == '0') {
					if (data.pageCount == 0) {
						$("#one").css('display', 'block');
					} else {
						$("#showpaymentList").html(evalText(data));
					}
				} else if (data.choose == '1') {
					if (data.pageCount == 0) {
						$("#two").css('display', 'block');
					} else {
						$("#showpaymentListPay").html(evalText(data));
					}
				} else if (data.choose == '2') {
					if (data.pageCount == 0) {
						$("#three").css('display', 'block');
					} else {
						$("#showpaymentListRecev").html(evalText(data));
					}
				} else if (data.choose == '3') {
					if (data.pageCount == 0) {
						$("#four").css('display', 'block');
					} else {
						$("#showpaymentListComp").html(evalText(data));
					}
				} else if (data.choose == '4') {
					if (data.shaixuan == 0) {
						$("#five").css('display', 'block');
					} else {
						$("#showpaymentListEva").html(evalText(data));
					}
				}
				$(".page_and_btn ul li span").css("background-color", "#e70012");
				$(".page_and_btn ul li span").css("color", "#fff");
				if (data.res_code == '1') {
					showm("您还没有登录!");
					window.setTimeout((window.location.href = "index.html"), 10000);
				}
				Imgmothermap = new Map();
			}
		});
	}
	function showm(showmess) {
		layer.msg(showmess, {});
	}
	function deleteOrd(id) {
		$.post(SHOPDOMAIN + '/interfaces/orderSele/deleteOrders.html', {
			id : id
		}, function(data) {
			if (data.res_code == '0') {
				showm(data.message);
				window.setTimeout(window.location.href = "myorder.html?ids=0");
			} else {
				showMessageRefresh(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}

	function evalution(id, proId) {
		var starCount = $("#w-stars_" + id + " input[name='score']").val();
		var content = $("#Etxt_" + id).val();
		var picuri = Imgmothermap.get("eva_" + evacom);
		if (starCount == "") {
			showm("评分必填");
		} else if (content == "") {
			showm("内容必填");
		} else if (content.length<6||content.length>200) {
			showm("内容长度必须要在6~200才可以~");
		} else {
			$.post(SHOPDOMAIN + '/interfaces/comment/commentComitPc.html', {
				"starCount" : starCount,
				"content" : content,
				"picuri" : picuri.toString(),
				"orderDetailId" : id,
				"ProdId" : proId
			}, function(data) {
				if (data.res_code == '0') {
					showm(data.message);
					nTabs($('#myTab0 li').eq(4)[0], 4);
					getList(1, 4);
				} else {
					showm(data.message);
				}
			}, "json").error(function() {
				showError();
			});
		}

	}

	//  取消订单确认
	function quxiao(id) {
		showMessquxiao("确定取消订单？", id);
	}
	function showMessquxiao(message, id) {
		layer.msg(message, {
			skin : 'layer-ext-myskinGlobal',
			closeBtn : 2,
			shade : 0.3,
			btn : [ '确定' ],
			btn1 : function() {
				deleteOrd(id);
			},
			time : 0
		});
	}
</script>
<script src="${SHOPDOMAIN}/js/webuploader/wximgupload_pc.js" type="text/javascript"></script>
<script>
	
</script>