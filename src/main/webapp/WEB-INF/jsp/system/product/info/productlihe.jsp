<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/js/webuploader/webuploader.css">
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/js/webuploader/style.css">
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/js/webuploader/demo.css">
<script type="text/javascript"
	src="${SHOPDOMAIN}/js/webuploader/webuploader.js"></script>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/select2/select2.css" />
<style>
select {
	background-color: #fff;
	border: 1px solid #ccc;
	width: 100px;
	height: 25px;
}

.green1 {
	padding: 10px 17px 9px
}

.form-control,.help-block {
	display: inline;
}

.list1 {
	float: left;
	margin: 0 15px 15px 0px;
}
</style>
<script id="prodSpecListShow" type="text/x-dot-template">
{{for(var i=0;i<it.length;i++){ }}
    {{var product=it[i];}}
        <tr class="" id="{{=product.type}}_{{=product.id}}_{{=product.productId}}">
			<td>
				<div class="checker">
					<span class="">
						<input name="prodSpecIdsCK" class="group-checkable prodSpecIdsCK" type="checkbox" value="{{=product.type}}_{{=product.id}}_{{=product.productId}}" onClick="ckoneprodspec(this);"/>
					</span>
				</div>
			</td>
			<td>{{=product.productName}}</td>
			<td>
				{{ if(1 == product.type) {}}
					{{ if(undefined != product.specInfoDetailList && product.specInfoDetailList.length > 0 ) {}}
						{{var specList =product.specInfoDetailList;}}

						{{ for(var j=0;j<specList.length;j++) { }}
							{{ var spec = specList[j]; }}
							&nbsp;&nbsp;{{=spec.specificationDetailName}}
						{{ } }}
					{{ }}}
				{{} else{ }}
			    	没有启用规格
				{{ }}}
			</td>
		</tr>
{{}}}
</script>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			商品管理 <small>录入礼盒信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					商品管理 </a> <i class="fa fa-angle-right"></i></li>
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
					<i class="fa fa-reorder"></i>基本信息
				</div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form_config" method="post"
					action="saveinfo.html" novalidate="novalidate">
					<div class="form-body">
						<div class="alert alert-danger display-hide">
							<button data-close="alert" class="close"></button>
							页面填写信息出错了，请先检查再提交。
						</div>
						<div class="alert alert-success display-hide">
							<button data-close="alert" class="close"></button>
							验证通过，正在提交！
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">商品名称： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${prodctInfo.name }" name="name" style="width:510px;">
								<input type="hidden" value="${prodctInfo.id }" name="id">
								<input type="hidden" value="1" name="type"> <input
									type="hidden" name="proBasicCount">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">商品备注： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${prodctInfo.note }" name="note" style="width:510px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">实际价格： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_price"
									value="${prodctInfo.price }" name="price" style="width:510px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">市场价格： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_price"
									value="${prodctInfo.marketPrice }" name="marketPrice"
									style="width:510px;">
							</div>
						</div>
						<div class="form-group" style="display: none">
							<label class="control-label col-md-3">单位： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require" value="礼盒"
									name="unit" style="width:510px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">商品分类： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<select class="form-control w_require" name="catalogId2" id="catalogId2">
									<c:forEach items="${SClist}" var="product" varStatus="vs">
										<option value="${product.id }" disabled="disabled"
											<c:if test="${prodctInfo.catalogId2==product.id }"> selected="selected"</c:if>>${product.name }</option>
										<c:forEach items="${product.sublist}" var="sub">
											<option value="${sub.id }"
												<c:if test="${prodctInfo.catalogId2==sub.id }"> selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;|__${sub.name }</option>
										</c:forEach>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">选择商品：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<button type="button" class="btn green green1"
										onclick="choseProd();">选择商品</button>
									<div style="width: 600px;">
										<table id='sure_prod_table' class='table table-bordered table-hover'>
											<tbody>
												<tr>
													<th>商品名称</th>
													<th>规格</th>
													<th width="50px">操作</th>
												</tr>
												<c:forEach items="${productPackage }" var="pp">
													<tr>
														<td>
														<input type='hidden' name='prod_sprc_ids' value="${pp.type }_${pp.specificationInfoId}_${pp.productId}"/>
														${pp.productName }
														</td>
														<td>
															<c:choose>
																<c:when test="${pp.type == 1 }">
																	<c:forEach items="${pp.psidList }" var="prodspecdeta">
																		&nbsp;&nbsp;${prodspecdeta.specificationDetailName }
																	</c:forEach>
																</c:when>
																<c:otherwise>没有启用规格</c:otherwise>
															</c:choose>
														</td>
														<td><a onclick="delguigechanp(this)" href='javascript:void(0)'> 删除</a></td>
												 	</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>	
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">产地 ： </label>
							<div class="col-md-4">
								<div class="btn-group" data-toggle="buttons">
									<label
										class="btn btn-default <c:if test="${product.origin==1||empty product.origin }">active</c:if>">
										<input type="radio"
										<c:if test="${product.origin==1||empty product.origin }"> checked="checked"</c:if>
										value="1" name="origin" class="toggle"> 本地
									</label> <label
										class="btn btn-default <c:if test="${product.origin==2 }">active</c:if>">
										<input type="radio"
										<c:if test="${product.origin==2}"> checked="checked"</c:if>
										name="origin" value="0" class="toggle"> 进口
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">推荐产品 ： </label>
							<div class="col-md-4">
								<div class="btn-group" data-toggle="buttons">
									<label
										class="btn btn-default <c:if test="${prodctInfo.istop==1||empty prodctInfo.istop }">active</c:if>">
										<input type="radio"
										<c:if test="${prodctInfo.istop==1||empty prodctInfo.istop }"> checked="checked"</c:if>
										value="1" name="istop" class="toggle"> 推荐产品
									</label> <label
										class="btn btn-default <c:if test="${prodctInfo.istop==0 }">active</c:if>">
										<input type="radio"
										<c:if test="${prodctInfo.istop==0}"> checked="checked"</c:if>
										name="istop" value="0" class="toggle"> 普通产品
									</label>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3">商品图片：<span
								class="required"> * </span>
							</label>
							<div class="col-md-4" style="width: 800px">
								<div id="uploader">
									<div id="uploader-demo">
										<input type="hidden" name="picuri" id="picurl_wx" value="1" />
										<div id="imgPathWebUploader" style="display: none"></div>
										<div id="filePicker" style="">选择图片（最多上传10张图片）</div>
										<!--用来存放item-->
										<div style="margin-left: 40px;margin-top: 10px;display: none"
											id="isfirstWebUploader">首图</div>
										<div id="fileList" class="uploader-list filelist"></div>
									</div>
								</div>
								图片建议尺寸为1000*1000，比例1比1
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">商品标签： </label>
							<div class="col-md-4"><div class="input-group">
								<select class="form-control input-xlarge select2me"
									name="prodTagsId">
									<option value="0">暂无</option>
									<c:forEach items="${proTags}" var="product" varStatus="vs">
										<option value="${product.id }"
											<c:if test="${prodctInfo.prodTagsId==product.id }"> selected="selected"</c:if>>${product.name }</option>
									</c:forEach>
								</select></div>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3">商品描述 ： </label>
							<div class="col-md-4">
								<textarea name="descn" id="descn"
									style="width:700px;height:480px;">${prodctInfo.descn }</textarea>
								<link rel="stylesheet"
									href="${SHOPDOMAIN}/js/kindeditor/themes/default/default.css" />
								<link rel="stylesheet"
									href="${SHOPDOMAIN}/js/kindeditor/plugins/code/prettify.css" />
								<script charset="utf-8"
									src="${SHOPDOMAIN }/js/kindeditor/kindeditor.js"></script>
								<script charset="utf-8"
									src="${SHOPDOMAIN }/js/kindeditor/lang/zh_CN.js"></script>
								<script charset="utf-8"
									src="${SHOPDOMAIN}/js/kindeditor/plugins/code/prettify.js"></script>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">上架时间 ： <span
								class="required"> * </span></label>
							<div class="col-md-4">
								<input type="radio" value="1" name="sellStatus" id="sellStatus"
									class="sellStatus toggle"
									<c:if test="${prodctInfo.sellStatus==1}">  checked="checked"</c:if>
									<c:if test="${empty prodctInfo.sellStatus}">  checked="checked"</c:if>><label
									for="sellStatus""> 立即上架</label> <br> <input type="radio"
									value="2" name="sellStatus" id="sellStatusdown"
									class="sellStatus toggle"
									<c:if test="${prodctInfo.sellStatus==2}">  checked="checked"</c:if>><label
									for="sellStatusdown""> 暂不上架，放入仓库中</label> <br>
							</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green rollesubmit" type="submit">提交</button>
						</div>
					</div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
<jsp:include page="../../foot.jsp"></jsp:include>

<script>
	var oldImgUrl = '${prodImgs }';//修改-以前的图片信息json格式
	var fileNumLimit=10;
	var imgtype="productImg";
	jQuery(document).ready(function() {
		ComponentsDropdowns.init();
		FormValidation.init();
		//只有当修改的时候才调用该方法
		if (oldImgUrl != "") {
			getImgByJson(oldImgUrl);
		}
	});
	$(function() {
		var editor1 = "";
		KindEditor
				.ready(function(K) {
					editor1 = K
							.create(
									'textarea[id="descn"]',
									{
										cssPath : '${SHOPDOMAIN }/js/kindeditor/plugins/code/prettify.css',
										uploadJson : '${SHOPDOMAIN }/kindeditor/uploadJson.html?companyId=${sessionUser.companyId }',
										fileManagerJson : '${SHOPDOMAIN }/kindeditor/fileManagerJson.html',
										allowFileManager : true,
										afterCreate : function() {
											var self = this;
											K.ctrl(document, 13, function() {
												self.sync();
												document.forms['form1']
														.submit();
											});
											K.ctrl(self.edit.doc, 13,
													function() {
														self.sync();
														document.forms['form1']
																.submit();
													});
										}
									});
					prettyPrint();
				});
		$(".rollesubmit").live("click", function(e) {
			editor1.sync();
			/**
			var proBasic = checkProBasic();
			if (proBasic == "") {
				return false;
			} else {
				$("input[name='proBasicCount']").val(proBasic);
			}
			**/
			var obj_cont = $("[name='prod_sprc_ids']").length;
			if (0 == obj_cont) {
				showMessage("请至少选择一款产品！");
				return false;
			}
			
			var $imgPathWebUploader = $("#imgPathWebUploader");
			if ($imgPathWebUploader.html() == "") {
				showMessage("请至少上传一张照片");
				return false;
			} else {
				$("#picurl_wx").val($("#imgPathWebUploader").html());
			}
		});

	})
	
	//选择商品 事件
	function choseProd(){
		var catalogHTML = "<select class='form-control' id='catalogId3' onChange='getSpecProdByCaId(1);'><option value='-1'>先选择商品规格</option>";
		$("#catalogId2 option").each(function(){
			if("disabled" == $(this).attr("disabled")){
				catalogHTML += "<option value='"+$(this).val()+"' disabled='disabled'>"+$(this).text()+"</option>";
			}else{
				catalogHTML += "<option value='"+$(this).val()+"'>"+$(this).text()+"</option>";
			}
		});
		catalogHTML +="</select>";
			
		//var tempLinkUrl="";
		var html = "选择分类:"+catalogHTML+"<br>"
				+ "<br>请选择相应规格的商品:<br>"
				+"<table id='chose_prod_table' class='table table-bordered table-hover'><tbody><tr><th width='20px'></th><th>商品名称</th><th>规格</th></tr></tbody></table><div class='page_and_btn'></div>";
		bootbox
				.dialog({
					message : html,
					title : "选择商品",
					buttons : {
						success : {
							label : "取消!",
							className : "white",
							callback : function() {
							}
						},
						main : {
							label : "确定!",
							className : "blue",
							callback : function() {
								var checkedcount = $(".prodSpecIdsCK:checked").length;
								if(""!=checkedcount && checkedcount > 0 ){
									var surprodHTML ="";
									$(".prodSpecIdsCK:checked").each(function(){
										var cur_v = $(this).val();
										var flag = true;
										$("input[name=prod_sprc_ids]").each(function(){
											if($(this).val()==cur_v){
												flag = false;
												return false;
											}
										});
										if(flag){
											surprodHTML += "<tr>"
														 +"<td>"
														 	+"<input type='hidden' name='prod_sprc_ids' value='"+cur_v+"'/>"
														 	+$("#"+cur_v+" td:eq(1)").html()
														 +"</td>"
														 +"<td>"
														 	+$("#"+cur_v+" td:eq(2)").html()
														 +"</td>"
														 +"<td>"
														 	+"<a onclick='delguigechanp(this)' href='javascript:void(0)'> 删除</a>"
														 +"</td>"
													  +"</tr>";
										}
									});
									$("#sure_prod_table tbody").append(surprodHTML);
								}else{
									showMessage("请先选择相应规格的商品");
								}
							}
						}
					}
				});
	}
	
	//根据分类ID获取相应的产品及相应的规格
	function getSpecProdByCaId(currentPage){
		//先把之前的产品列表删掉,再去查询
		var prod_tr_length = $("#chose_prod_table tbody tr").length;
		if(prod_tr_length > 1){
			$("#chose_prod_table tbody tr:not(:first)").remove();//排除第一行，其它行都删掉
		}
		
		var cur_cataId = $("#catalogId3  option:selected").val();
		if("-1" != cur_cataId){
			$.ajax({
				url : "getSpecProdByCaId.html",
				type : "post",
				dataType : "json",
				sync : false,//同步
				data : {
					"currentPage" : currentPage,
					"catalogId" : cur_cataId,
					"showCount":20
				},
				success : function(data) {
					var page = data.page.pageStr;
					var evalText = doT.template($("#prodSpecListShow").html());
					$("#chose_prod_table tbody").append(evalText(data.list));
					$(".page_and_btn").html("<div></div>" + page);
				}
			});
		}
	}
	
	//点击复选框的操作
	function ckoneprodspec(event){
		if ($(event).is(":checked")) {
			$(event).attr("checked", "true");//
			$(event).parent().addClass("checked");

		} else {
			$(event).removeAttr("checked");//
			$(event).parent().removeClass("checked");
		}
	}
	//删除选中的产品
	function delguigechanp(k) {
		$(k).parent().parent().remove()
	}
</script>
<script src="${SHOPDOMAIN}/js/webuploader/wximgupload.js"
	type="text/javascript"></script>
<!-- END PAGE CONTENT-->
