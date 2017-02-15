<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<!-- BEGIN PAGE HEADER-->
<style type="text/css">
.p_active {	position: relative;	border: 1px solid #1f897f;}
.prod_show {	border: 1px solid #ccc;	width: 24%;	float: left;	margin-right: 1%;	margin-top: 1%;	padding: 10px 1%;}
.prod_show:hover {	border: 1px solid #1f897f;}
.prod_adt {	border: 1px solid #ccc;	width: 24%;	float: left;	margin-right: 1%;	margin-top: 1%;	padding: 10px 1%;}
.prod_adt:hover {	border: 1px solid #1f897f;}
#showList {	overflow: hidden}
.p_active a {	border: 1px solid #F0F0E8;	background-color: #FFF;	padding: 6px;	display: block;}
.p_active a:hover {	border: 1px solid #000;	border-color: green;	text-decoration: none;}
.p_active a span {	display: none;	text-align: center;	font-size: 12px;}
.p_active a:hover span {	color: #FFF;	display: block;	border-color: green;	width: 114px;	position: absolute;	top: 94px;	left: 0px;	line-height: 20px;}
div.p_active:BEFORE {	position: absolute;	content: '';	right: 0px;	bottom: 0px;	height: 20px;	width: 20px;	color: black;	font-size: 12px;	/*border:1px solid #1f897f;*/	background: url('${SHOPDOMAIN}/system/image/w-right.png') no-repeat;
}
.Pchoice {	display: inline-block}
#percent20 {	margin-top: 20px;	margin-bottom: 20px;	width: 100%}
#percent20 th {	width: 20%;	text-align: center}
.form-control {	width: 20%;}
.form-horizontal .radio {	padding-top: 0}
.radio input[type=radio] {	margin-left: -10px}
.col-md-5 {	width: 66%;}
</style>
<script id="interpolationtmpl" type="text/x-dot-template">
{{for(var i=0;i<it.length;i++){ }}
    {{var product=it[i];var tempClass='';}}
	{{if(Imgmap.get("pro_"+product.id)!=undefined){}}
	{{tempClass='p_active'}}}
	<div class="prod_show {{=tempClass}}" style="" id="pro_{{=product.id}}" val="{{=product.id}}">
		<img src="{{=USERIMGSRC+ imgZuhe(product.picuri,'_160')}}" width="50" height="54" style="float:left;" alt="" ></img> 
		<div style="margin-left:55px;">
			<span style="display:inline-block;height:36px;line-height:18px;overflow:hidden">{{=product.name}}</span> 
			<span style="display:block">￥{{=product.price}}</span> 
		</div>
    </div>
{{}}}
</script>
<script id="selectedListtmpl" type="text/x-dot-template">
{{for(var i=0;i<it.length;i++){ }}
	{{var product=it[i];var tempClass='';}}
	{{if(Imgmap.get("pro_"+product.id)!=undefined){}}
		{{tempClass='p_active'}}}
		<div class="prod_show {{=tempClass}}" style="border:1px solid #ccc" id="pro_{{=product.id}}" val="{{=product.id}}">
			<img src="{{=USERIMGSRC+ imgZuhe(product.picuri,'_160')}}" width="50" height="54" style="float:left;" alt="" ></img> 
			<div style="margin-left:55px;">
				<span style="display:inline-block;height:36px;line-height:18px; overflow:hidden">{{=product.name}}</span> 
				<span style="display:block">￥{{=product.price}}</span> 
			</div>
		</div>
{{}}}
</script>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			促销管理 <small>列表 </small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)"> 促销管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 信息 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN VALIDATION STATES-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-reorder"></i>信息
				</div>
				<div class="tools"></div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form_config" method="post" enctype="multipart/form-data" action="save.html" novalidate="novalidate">
					<div class="form-body">
						<c:if test="${not empty obj.id }">
							<div class="portlet-body">
								<div class="alert alert-block alert-info fade in">
									<button data-dismiss="alert" class="close" type="button"></button>
									<p>wap促销地址：${SHOPDOMAIN}/wap/active.html?id=${obj.id }</p>
									<p>pc促销地址：${SHOPDOMAIN}/activePC.html?id=${obj.id }</p>
								</div>
							</div>
						</c:if>
						<div class="alert alert-danger display-hide">
							<button data-close="alert" class="close"></button>
							页面填写信息出错了，请先检查再提交。
						</div>
						<div class="alert alert-success display-hide">
							<button data-close="alert" class="close"></button>
							验证通过，正在提交！
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">活动名称 ： <span class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" style="width: 520px;" class="form-control w_require" value="${obj.name }" name="name"> <input type="hidden"
									value="${obj.id }" name="id" id="eventname">
							</div>
						</div>
						<!-- <div class="form-group">
							<label class="control-label col-md-3">活动标签 ： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require w_2_4"
									value="${obj.tags }" name="tags" id="activTab">
							</div>
							2-4个字，在商品详情中显示
						</div>
						 
						<div class="form-group">
							<label class="control-label col-md-3">列表图片：<span
								class="required">  </span>
							</label>
							<div class="col-md-4">
								<div data-provides="fileinput" class="fileinput fileinput-new">
									<div class="input-group input-large">
										<div data-trigger="fileinput"
											class="form-control uneditable-input span3">
											<i class="fa fa-file fileinput-exists"></i>&nbsp; <span
												class="fileinput-filename"></span>
										</div>
										<span class="input-group-addon btn default btn-file"> <span
											class="fileinput-new"> 选择文件 </span> <span
											class="fileinput-exists"> 更改 </span> <input type="hidden"
											value="" name="..."><input type="file" name="myfile1">
										</span> <a data-dismiss="fileinput"
											class="input-group-addon btn red fileinput-exists" href="#">
											删除 </a>
									</div>

									<c:if test="${not empty obj.picUrl }">
										<br>
										<br>
										<img
											src="${USERIMGSRC }${fn:replace(obj.picUrl,'.','_640_300.')}"
											height="120px" width="320px" />
									</c:if>
								</div>
							</div>
						</div>-->

						<div class="form-group">
							<label class="control-label col-md-3">wap端促销页面头部图片：<span class="required"> </span>
							</label>
							<div class="col-md-4">
								<div data-provides="fileinput" class="fileinput fileinput-new">
									<div class="input-group input-large">
										<div data-trigger="fileinput" class="form-control uneditable-input span3">
											<i class="fa fa-file fileinput-exists"></i>&nbsp; <span class="fileinput-filename"></span>
										</div>
										<span class="input-group-addon btn default btn-file"> <span class="fileinput-new"> 选择文件 </span> <span class="fileinput-exists">
												更改 </span> <input type="hidden" value="" name="..."><input type="file" name="myfile2">
										</span> <a data-dismiss="fileinput" class="input-group-addon btn red fileinput-exists" href="#"> 删除 </a>
									</div>
									wap版本：图片为640*251
									<c:if test="${not empty obj.spreadUrl }">
										<br>
										<br>
										<img src="${USERIMGSRC }${fn:replace(obj.spreadUrl,'.','_640_251.')}" height="120px" width="320px" />
									</c:if>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">PC端促销页面头部图片：<span class="required"> </span>
							</label>
							<div class="col-md-4">
								<div data-provides="fileinput" class="fileinput fileinput-new">
									<div class="input-group input-large">
										<div data-trigger="fileinput" class="form-control uneditable-input span3">
											<i class="fa fa-file fileinput-exists"></i>&nbsp; <span class="fileinput-filename"></span>
										</div>
										<span class="input-group-addon btn default btn-file"> <span class="fileinput-new"> 选择文件 </span> <span class="fileinput-exists">
												更改 </span> <input type="hidden" value="" name="..."><input type="file" name="myfile3">
										</span> <a data-dismiss="fileinput" class="input-group-addon btn red fileinput-exists" href="#"> 删除 </a>
									</div>
									pc版本：图片为1920*350
									<c:if test="${not empty obj.pcspreadUrl}">
										<br>
										<br>
										<img src="${USERIMGSRC}${fn:replace(obj.pcspreadUrl,'.','_1920_350.')}" height="120px" width="320px" />
									</c:if>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3"> 活动时间 ： <span class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" readonly="readonly" class="form-control w_require" id="defaultrange_modal" name="starttime"
									value="<fmt:formatDate value="${obj.startTime}"
														type="both" pattern="yyyy-MM-dd" /> ${empty obj.startTime?"
									":"~"  } <fmt:formatDate
														value="${obj.endTime}" type="both"
														pattern="yyyy-MM-dd" />" style="width: 520px;">
							</div>
						</div>
						<!--  
						<div class="form-group">
							<label class="control-label col-md-3">是否推荐 ： </label>
							<div class="col-md-4">
								<div class="btn-group" data-toggle="buttons">
									<label  
										class="btn btn-default <c:if test="${obj.istop==1||empty obj.istop }">active</c:if>">
										<input type="radio"
										<c:if test="${obj.istop==1||empty obj.istop }"> checked="checked"</c:if>
										value="1" id="smProduct" name="istop" class="toggle">
										是</input>
									</label> <label  
										class="btn btn-default <c:if test="${obj.allProduct==0 }">active</c:if>">
										<input type="radio"
										<c:if test="${obj.istop==0 }"> checked="checked"</c:if>
										id="allProduct" name="istop" value="0" class="toggle">
										否</input>
									</label>
								</div>
							</div>
							
							
						</div>-->
						<div class="form-group">
							<label class="control-label col-md-3">全场商品参加 ： </label>
							<div class="col-md-4">
								<div class="btn-group" data-toggle="buttons">
									<label onclick="btn1()" class="btn btn-default <c:if test="${obj.allProduct==1 }">active</c:if>"> <input type="radio"
										<c:if test="${obj.allProduct==1 }"> checked="checked"</c:if> value="1" id="smProduct" name="allProduct" class="toggle"> 是</input>
									</label> <label onclick="btn2()" class="btn btn-default <c:if test="${obj.allProduct==0||empty obj.allProduct }">active</c:if>"> <input
										type="radio" <c:if test="${obj.allProduct==0||empty obj.allProduct }"> checked="checked"</c:if> id="allProduct" name="allProduct" value="0"
										class="toggle"> 否</input>
									</label>
								</div>
							</div>
						</div>
						<div class="form-group" id="form-group">
							<label class="control-label col-md-3">参与商品 ： </label>
							<div class="col-md-8">
								<div class="tabbable-custom ">
									<ul class="nav nav-tabs ">
										<li class="active"><a data-toggle="tab" href="#tab_5_1" id="shangjia"> 上架中 </a></li>
										<li class=""><a data-toggle="tab" href="#tab_5_2" id="yixuanze"> 上次修改保存的商品 </a></li>
									</ul>
									<div class="tab-content" style="overflow: hidden">
										<div id="tab_5_1" class="tab-pane active">
											<table id="percent20">
												<td>
												<tr>
													<span>分类:&nbsp;</span>
													<select class="form-control Pchoice" name="catalogId" id="catalogId" style="width: 90px;">
														<option value="-1">请选择</option>
														<c:forEach items="${catList}" var="li">
															<option value="${li.id }cf" ${catalogId==li.id?"selected='selected'":"" }>${li.name }</option>
															<c:forEach items="${li.sublist }" var="sub">
																<option value="${sub.id }ct" ${catalogId==sub.id?"selected='selected'":"" }>|_${sub.name }</option>
															</c:forEach>
														</c:forEach>
													</select>
												</tr>
												<tr>
													<span style="">&nbsp;&nbsp;商品名称:&nbsp;</span>
													<input type="text" class="form-control Pchoice" value="" style="width: 180px;" id="proname" name="proname" placeholder="">

												</tr>
												<tr>
													&nbsp;
													<span class="input-group-btn Pchoice" onclick="search()"> <a href="javascript:;" class="btn green Pchoice" id="search"
														<i class="fa fa-search"></i> 搜索</a>
													</span>
												</tr>
												</td>
												<tr>
													<th><input type="checkbox" onclick="select_orno()" class="select_allno" />选择本页</th>
													<input type="hidden" name="prodId" id="sellect_hid" value="" />
													<!-- <td>
													<th onclick='orderInfo("istop");'>推荐状态 <span class="fa fa-caret-down " id="istopUp" title="1"></span> <span class="fa fa-caret-up "
														title="2" id="istopDown"></span>
													</th>
													</td> -->
													<td>
													<th onclick='orderInfo("sort2");'>销量 <span class="fa fa-caret-down " id="sort2Up" title="1"></span> <span class="fa fa-caret-up "
														title="2" id="sort2Down"></span> </span>
													</th>
													</td>
													<td>
													<th onclick='orderInfo("timeStatus");'>添加时间 <span class="fa fa-caret-down " id="timeStatusUp" title="1"></span> <span
														class="fa fa-caret-up " title="2" id="timeStatusDown"></span> </span>
													</th>
													</td>
													<td>
													<th onclick='orderInfo("sort1");'>价格 <span class="fa fa-caret-down " id="sort1Up" title="1"></span> <span class="fa fa-caret-up "
														title="2" id="sort1Down"></span> </span>
													</th>
													</td>
												</tr>
												<div id="listorder">
													<input type="hidden" id="orerHidden" title="">
												</div>
											</table>
											<div id="showList"></div>
											<div class="page_and_btn"></div>
										</div>
										<div id="tab_5_2" class="tab-pane">
											<div id="showProList"></div>
											<div id="zanwu"></div>
											<div class="page_and_btns"></div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3">活动条件 ： <span class="required"> * </span>
							</label>
							<div class="col-md-5" style="padding-left:36px;">
								满&nbsp;(*)元<input type="text" class="form-control w_require w_weight" value="${obj.needPrice }" name="needPrice"
									style="width: 100px; display: inline-block" id="needPrice">
							</div>

						</div>

						<div class="form-group">
							<label class="control-label col-md-3">活动内容 ： <span class="required"> * </span>
							</label>
							<div class="col-md-5">
								<input type="radio" class="radioPay" id="activities" <c:if test="${obj.type==1}">checked="checked" </c:if> name="type" value="1">&nbsp;打(*)折<input
									type="text" class="form-control w_discount" <c:if test="${obj.type==1}">value="${obj.discount}"</c:if> <c:if test="${obj.type==0}">disabled</c:if> name="discount" id="discount"
									style="display: inline-block;width:100px;">
								<div style="margin-top:10px;">
									<input type="radio" id="activitiess" <c:if test="${obj.type==0}">checked="checked" </c:if> class="radioPay" name="type" value="0">&nbsp;减(*)元<input
										type="text" class="form-control w_discountss" style="display: inline-block;width:100px;"
										<c:if test="${obj.type==0}">value="${obj.substractPrice }" </c:if> <c:if test="${obj.type==1}">disabled</c:if>  name="substractPrice" id="substractPrice">
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">PC版广告 ： <span class="required"> * </span>
						</label>
						<div class="col-md-5" style="padding-left:36px;">
							<table>
								<tr>
									<!-- <td>全选 <input type="checkbox" onclick="select_adt()"></td> -->
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<c:forEach var="gga" items="${adDetil}" varStatus="status">
										<%-- <td><input type="checkbox" id="${gga.id}" name="adtid">：${gga.name }&nbsp;&nbsp;</td>
										<td><img src="${USERIMGSRC}${gga.picUrl}" width="50" height="54" style="float:left;" alt=""></img></td> --%>
										<div val="1385" style="" class="prod_adt ">
											<img width="100%"  alt="" style="float:left;" src="${USERIMGSRC}${gga.usepicurl}">
											<div style="margin-left:px;">
												<span id="sapce"><input type="checkbox" id="${gga.id}" name="adtid">：${gga.name }</span>
											</div>
										</div>
										<c:if test="${status.count%2==0}">
								</tr>
								<tr>
									</c:if>
									</c:forEach>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td><input type="hidden" id="adDetailIds" name="adDetailIds" /></td>
								</tr>
							</table>
						</div>

					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green ssubmit" type="submit">提交</button>
							<button class="btn grey-cascade sys_go_back" type="button">返回</button>
						</div>
					</div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
<script src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/moment.js"></script>
<script src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
<script>
	var choo = 1;
	if (${obj.allProduct==1}) {
		document.getElementById('form-group').style.display = 'none';
		choo = 0;
	}
	function aa() {
		if (${obj.id!=null}) {
			var id_arr = new Array();
			id_arr = ("${obj.adDetailIds}").split(",");
			if (id_arr.length > 0) {
				for (var i = 0; i < id_arr.length; i++) {
					$("#" + id_arr[i]).attr("checked", true).parent().attr("class", "checked");
				}
			}
		}
	} 
	if (${obj.allProduct==0}) {
		choo = 1;
	}
	var Imgmap = new Map();
	$('#yixuanze').click(function() {
		getProList(1, 2);
	});
	$('#shangjia').click(function() {
		getList(1, 1);
	});

	$(document).ready(function() {
		$('.radioPay').click(function() {
			if ($('#activities').prop('checked') == true) {
				$('#discount').prop('disabled', false);
				$('#substractPrice').prop('disabled', true)
			} else if ($('#activitiess').prop('checked') == true) {
				$('#discount').prop('disabled', true);
				$('#substractPrice').prop('disabled', false)
			}
		});
	});

	function select_orno() {
		$("#tab_5_1").find(".prod_show").each(function() {
			if ($(".select_allno").parent().hasClass("checked")) {
				//$('.prod_show').filter('.p_active').css('border-color', '');
				if ($(this).hasClass('p_active')) {
					Imgmap.remove($(this).attr("id"));
					$(this).removeClass("p_active");
				}
			} else {
				//$('.prod_show').filter('.p_active').css('border-color', 'red');
				if (!$(this).hasClass('p_active')) {
					Imgmap.put($(this).attr("id"), $(this).attr("val"));
					$(this).addClass("p_active");

				}
			}
		});
	}
	function btn1() {
		choo = 0;
		document.getElementById('form-group').style.display = 'none';
	}
	function btn2() {
		choo = 1;
		document.getElementById('form-group').style.display = 'block';
	}

	$(function() {
		getList(1, 1);
		var str = $("#eventname").val();
		if (str != null && str != "") {
			getProList(1, 1);
		} else {
			$("#zanwu").html("暂无选择");
		}
	});
	function search() {
		getList(1, 1);
	}
	function getProList(currentPage, impu) {
		var id = $("#eventname").val();
		$.ajax({
			url : "${SHOPDOMAIN}/system/promotion/getselect.html",
			type : "post",
			data : {
				"currentPage" : currentPage,
				"showCount" : 20,
				"id" : id,
			},
			dataType : "json",
			success : function(data) {
				var page = data.page.pageStr;
				var evalText = doT.template($("#selectedListtmpl").html());
				$("#showProList").html(evalText(data.list));
				if (data.list == "") {
					$("#zanwu").html("暂无选择");
				}
				if (impu == 2) {
					$.each(Imgmap, function(i, items) {
						$.each(data.list, function(i, item) {
							if (items == item.id) {
								$("#pro_" + item.id).addClass("p_active");
							}
						});
					});
				}
				if (impu == 1) {
					$.each(data.list, function(i, item) {
						Imgmap.put("pro_" + item.id, item.id);
						$("#pro_" + item.id).addClass("p_active");
					});
				}

				$(".page_and_btns").html("<div></div>" + page);
			}
		});
	}
	function getList(currentPage, impu) {
		var catalogId = $("#catalogId").val();
		var name = $("#proname").val();
		var namel = $("#orerHidden").attr("title");
		var value = $("#orerHidden").val();
		$.ajax({
			url : "${SHOPDOMAIN}/system/product/getlist.html",
			type : "post",
			data : {
				"currentPage" : currentPage,
				"showCount" : 20,
				"catalogId" : catalogId,
				"name" : name,
				"orderInfo" : namel + "," + value,
			},
			dataType : "json",
			success : function(data) {
				var page = data.page.pageStr;
				var evalText = doT.template($("#interpolationtmpl").html());
				$("#showList").html(evalText(data.list));
				$(".page_and_btn").html("<div></div>" + page);
				$('.checker .checked').removeClass().addClass("");
				if (impu == 2 && namel != "") {
					$.each(data.list, function(i, item) {
						Imgmap.put("pro_" + item.id, item.id);
						$("#pro_" + item.id).addClass("p_active");
					});
				}
				aa();
			}
		});
	};
	jQuery(document).ready(function() {
		//遍历每个classs,判断这个是checked
		$(document).on("click", ".prod_show", function() {
			//放到map中
			if ($(this).hasClass('p_active')) {
				Imgmap.remove($(this).attr("id"));
				$(this).removeClass("p_active");
			} else {
				Imgmap.put($(this).attr("id"), $(this).attr("val"));
				$(this).addClass("p_active");
			}
		});
		$(".ssubmit").click(function() {
			$("#sellect_hid").val(Imgmap);
			var va = $("input[name='type']:checked").val();
			var id_array = new Array();
			$('input[name="adtid"]:checked').each(function() {
				id_array.push($(this).attr('id'));//向数组中添加元素  
			});
			var idstr = id_array.join(',');//将数组元素连接起来以构建一个字符串  
			$("#adDetailIds").val(idstr);
			if (va == '1') {
				var t = $("#discount");
				if (t.val().trim() == "") {
					showMessage("活动内容打折必填");
					return false;
				}
				if ($("#discount").val() < 0 || $("#discount").val() > 100) {
					showMessage("打折必须在0-100数字");
					return false;
				}
				if (choo == 1) {
					if ($("#sellect_hid").val() == "") {
						showMessage("请选择参与商品");
						return false;
					}
				}
			} else {
				var t = $("#substractPrice");
				var ne = $("#needPrice");
				if (t.val().trim() == "") {
					showMessage("活动内容减价必填");
					return false;
				}
				if ((t.val().trim() - ne.val().trim()) > 0) {
					showMessage("减价不能超过活动条件值");
					return false;
				}
				if (choo == 1) {
					if ($("#sellect_hid").val() == "") {
						showMessage("请选择参与商品");
						return false;
					}
				}
			}
		});
		FormValidation.init();
		$('#defaultrange_modal').daterangepicker({
			timePicker : true,
			timePickerIncrement : 30,
			format : 'YYYY-MM-DD'

		}, function(start, end, label) {
			$('#defaultrange_modal input').val(start.format('YYYY-MMMM-DD') + ' ~ ' + end.format('YYYY-MMMM-DD'));
		});
	});

	//图标排序 
	function orderInfo(id) {
		var dpy = $("#" + id + "Up").css("display");
		if (dpy.indexOf("block") != -1) {
			$(".fa").show();
			$("#" + id + "Up").hide();
			$("#" + id + "Down").show();
			$("#orerHidden").attr("title", id);
			$("#orerHidden").val($("#" + id + "Up").attr("title"));
			getList(1);
		} else {
			$("#" + id + "Down").hide();
			$("#" + id + "Up").show();
			$("#orerHidden").attr("title", id);
			$("#orerHidden").val($("#" + id + "Down").attr("title"));
			getList(1);
		}

	}
</script>
<script type="text/javascript" src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />