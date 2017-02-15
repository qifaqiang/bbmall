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

.form-control, .help-block {
	display: inline;
}

em {
	font-style: normal;
}
.hidden{display:none;}
</style>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			商品管理 <small>录入商品信息</small>
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
									value="${prodctInfo.name }" name="name" style="width: 510px;">
								<input type="hidden" value="${prodctInfo.id }" name="id">
								<input type="hidden" value="0" name="type">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">商品备注： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${prodctInfo.note }" name="note" style="width: 510px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">单位： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require" id="punit"
									value="${prodctInfo.unit }" name="unit" style="width: 510px;">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">商品分类： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<select class="form-control w_require" name="catalogId2">
									<c:forEach items="${SClist}" var="product" varStatus="vs">
										<option value="${product.id }" disabled="disabled">${product.name }</option>
										<c:forEach items="${product.sublist}" var="sub">
											<option value="${sub.id }"
												<c:if test="${prodctInfo.catalogId2==sub.id }"> selected="selected"</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;|__${sub.name }</option>
										</c:forEach>
									</c:forEach>
								</select>
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
							<label class="control-label col-md-3">是否启用规格 ： </label>
							<div class="col-md-4">
								<div class="btn-group" data-toggle="buttons">
									<label
										class="btn btn-default isSpecbtn <c:if test="${prodctInfo.isSpecification==1 ||empty prodctInfo.isSpecification }">active</c:if>">
										<input type="radio"
										<c:if test="${prodctInfo.isSpecification==1||empty prodctInfo.isSpecification }"> checked="checked"</c:if>
										value="1" name="isSpecification" class="toggle"> 启用
									</label> <label
										class="btn btn-default isSpecbtn <c:if test="${prodctInfo.isSpecification==0 }">active</c:if>">
										<input type="radio"
										<c:if test="${prodctInfo.isSpecification==0}"> checked="checked"</c:if>
										name="isSpecification" value="0" class="toggle"> 暂不启用
									</label>
								</div>
							</div>
						</div>
						
						<div class="form-group guige_div hidden">
							<label class="control-label col-md-3">规格 ： </label>
							<div class="col-md-4">
								<input type="hidden" id="prodSpecMainStr" value="${prodSpecMainStr }" />
								<input type="hidden" id="prodSpecInfoDetailStr" value="${prodSpecInfoDetailStr }" />
								<div style="width: 600px;" >
									<c:forEach items="${psList }" var="prodspec">
										<div class="checker">
											<span class=""> <input name="prodspec_ck" id="spec_${prodspec.id }"
												class="group-checkable" type="checkbox"
												value="${prodspec.id }" title="${prodspec.specificationName }"/>
											</span>
										</div>
										${prodspec.specificationName }
										&nbsp;&nbsp;
										<c:forEach items="${prodspec.specDetailList }" var="specdeta_tt">
											<input type="hidden" class="specDeta_${prodspec.id }" name="${specdeta_tt.detailName }" value="${specdeta_tt.id }"></input>
										</c:forEach>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="form-group guige_div hidden">
							<label class="control-label col-md-3">规格值： <span
								class="required"> * </span></label>
							<div class="col-md-4">
								<div style="width: 600px;" id="guiges">
									<button type="button" class="btn green green1"
										onclick="guigeadd()">添加规格</button>
										<div>
											<table id="prodSpec_tb" class="table table-bordered table-hover">
												<tr>
													<c:forEach items="${psList }" var="prodspec2">
														<th class="th${prodspec2.id } hidden">${prodspec2.specificationName }</th>
													</c:forEach>
													<th>市场价格</th>
													<th>价格</th>
													<th style='width:20%;'>操作</th>
												</tr>
												<c:forEach items="${proSpecInfoList }" var="psil">
													<tr>
														<c:forEach items="${psList }" var="prodspec3">
															<td class="th${prodspec3.id } hidden">
																<select class="form-control" style="width: 120px;" id="${psil.id }_${prodspec3.id}_s" name="specDetalInpt_${prodspec3.id }">
																	<c:forEach items="${prodspec3.specDetailList }" var="specdeta_tt2">
																		<option value="${specdeta_tt2.id }">${specdeta_tt2.detailName }</option>
																	</c:forEach>
																</select>
															</td>
														</c:forEach>
														<td>
															<input type="hidden" name="skus" value="${psil.id }">
															<input type="hidden" name="inventorynumbers" value='1'>
															<input type="text" style="width:80px;" name="marketPrices" class="form-control ck_price" value="${psil.marketPrice }" oninput="OnInput(event)" onpropertychange="OnPropChanged(event)">
														</td>
														<td><input type='text' style='width:80px;' name="prices" class='form-control ck_price' value="${psil.price }" oninput="OnInput(event)" onpropertychange="OnPropChanged(event)"></td>
														<td><a onclick="delguige(this)" href='javascript:void(0)'> 删除</a></td>
													</tr>
												</c:forEach>
											</table>
										</div>
								</div>
							</div>
						</div>

						<div class="form-group noguige_div hidden">
							<label class="control-label col-md-3">市场价格： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control"
									value="${prodctInfo.marketPrice }" name="marketPrice" style="width: 150px;">元
							</div>
						</div>
						
						<div class="form-group noguige_div hidden">
							<label class="control-label col-md-3">价格： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="hidden" name="remark" value="1">
								<input type="hidden" name="inventorynumber" value="1">
								<input type="text" class="form-control"
									value="${prodctInfo.price }" name="price" style="width: 150px;">元
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
										<div
											style="margin-left: 40px; margin-top: 10px; display: none"
											id="isfirstWebUploader">首图</div>
										<div id="fileList" class="uploader-list filelist"></div>
									</div>
								</div>
								图片建议尺寸为1000*1000，比例1比1
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">商品标签： </label>
							<div class="col-md-4">
								<div class="input-group">
									<select class="form-control input-xlarge select2me"
										style="width: 150px;" name="prodTagsId">
										<option value="0">暂无</option>
										<c:forEach items="${proTags}" var="product" varStatus="vs">
											<option value="${product.id }"
												<c:if test="${prodctInfo.prodTagsId==product.id }"> selected="selected"</c:if>>${product.name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3">商品描述 ： </label>
							<div class="col-md-4">
								<textarea name="descn" id="descn"
									style="width: 700px; height: 480px;">${prodctInfo.descn }</textarea>
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
	var fileNumLimit = 10;
	var imgtype = "productImg";
	var punit = '${prodctInfo.unit}';
	var isSpecInit = '${prodctInfo.isSpecification}';
	jQuery(document).ready(function() {
		ComponentsDropdowns.init();
		FormValidation.init();
		//只有当修改的时候才调用该方法
		if (oldImgUrl != "") {
			getImgByJson(oldImgUrl);
			//$("[name='inventorynumbers']").next().html(punit);
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
			
			var isSpecCheck  = $("input[name=isSpecification]:checked").val();
			if("1"==isSpecCheck){//开启规格的校验
				var obj = $("[name='inventorynumbers']").length;
				if (0 == obj) {
					showMessage("请至少添加一项规格！");
					return false;
				}
				
				//校验规格不能重复   begin
				var ck_specids = {};//保存已经存在的规格
				var isflag = 0;
				$("#prodSpec_tb tr:not(:first)").each(function(nn,kk){
					var specdetailids_t ="";
					$(".group-checkable:checked").each(function(index,k){//循环获得选中的规格
						var tid_t = $(k).attr("value");
						specdetailids_t += $(kk).find("select[name=specDetalInpt_"+tid_t+"]").val()+"_";
					});
					
					if(specdetailids_t == ""){
						showMessage("数据存在异常，请刷新或者重新进入该页面");
						isflag =1;
						return false;
					}else{
						var ishave_t = 0;
						$.each(ck_specids,function(ii,mm){
							if(mm == specdetailids_t){
								ishave_t = 1;
								return false;
							}
						});
						
						if(ishave_t == 0){
							ck_specids[nn]= specdetailids_t;
						}else{
							showMessage("存在重复的规格，请修改后重新提交");
							isflag = 1;
							return false;
						}
					}
				});
				
				if(isflag !=0){
					return false;
				}
				//校验规格不能重复   end
			}

			var $imgPathWebUploader = $("#imgPathWebUploader");
			if ($imgPathWebUploader.html() == "") {
				showMessage("请至少上传一张照片");
				return false;
			} else {
				$("#picurl_wx").val($("#imgPathWebUploader").html());
			}
		});
		//是否启用规格初始化 begin
		if(null == isSpecInit || "" == isSpecInit || "1"==isSpecInit){
			$(".guige_div").removeClass("hidden");
			
			$(".noguige_div").addClass("hidden");
			$("input[name=marketPrice]").removeClass("w_price");
			$("input[name=price]").removeClass("w_price");
		}else{
			$(".guige_div").addClass("hidden");
			
			$(".noguige_div").removeClass("hidden");
			$("input[name=marketPrice]").addClass("w_price");
			$("input[name=price]").addClass("w_price");
		}
		//是否启用规格初始化 end
		
		//初始化选中的规格 begin
		var prodSpecMainStr = $("#prodSpecMainStr").val();
		if(null != prodSpecMainStr && "" != prodSpecMainStr && "null" != prodSpecMainStr){
			var prodSpecMainStrs = prodSpecMainStr.split(",");
			$.each(prodSpecMainStrs,function(n,val) {
				$("#spec_"+val).attr("checked", "true");//
				$("#spec_"+val).parent().addClass("checked");
				$(".th"+val).removeClass("hidden");
			});
		}
		var prodSpecInfoDetailStr = $("#prodSpecInfoDetailStr").val();
		if(null != prodSpecInfoDetailStr && "" != prodSpecInfoDetailStr && "null" != prodSpecInfoDetailStr){
			var prodSpecInfoDetailStrs = prodSpecInfoDetailStr.split(",");
			$.each(prodSpecInfoDetailStrs,function(n,val) {
				var tt = val.split("_");
				$("#"+tt[0]+"_"+tt[1]+"_s option[value="+tt[2]+"]").attr("selected", "selected");//
			});
		}
		
		//初始化选中的规格 end
		
		//主规格复选框的绑定事件
		$(".group-checkable").click(function() {
			if ($(this).is(":checked")) {
				$(this).attr("checked", "true");//
				$(this).parent().addClass("checked");
				
				var idt = $(this).attr("value");
				$(".th"+idt).removeClass("hidden");
			} else {
				$(this).removeAttr("checked");//
				$(this).parent().removeClass("checked");
				
				var idt = $(this).attr("value");
				$(".th"+idt).addClass("hidden");
				
				var cur_zspec_box = $(".group-checkable:checked").length;
				if(0 == cur_zspec_box || undefined == cur_zspec_box){//如果一个都没有选中 则删掉所有的规格值
					var tr_length = $("#prodSpec_tb tr").length;
					if(tr_length > 1){
						$("#prodSpec_tb tr:not(:first)").remove();//排除第一行，其它行都删掉
					}
				}
			}
		});
		
		//是否启用的绑定事件
		$(".isSpecbtn").click(function(){
			$("input[name=isSpecification]").removeAttr("checked");
			$(this).find("input").attr("checked","checked");
			var isSpecV = $(this).find("input").attr("value");
			if(isSpecV == "1"){//开启规格
				$(".guige_div").removeClass("hidden");
				
				$(".noguige_div").addClass("hidden");
				$("input[name=marketPrice]").removeClass("w_price");
				$("input[name=price]").removeClass("w_price");
			}else{//不启用规格
				$(".guige_div").addClass("hidden");
			
				$(".noguige_div").removeClass("hidden");
				$("input[name=marketPrice]").addClass("w_price");
				$("input[name=price]").addClass("w_price");
			}
		});
		
	});

	// Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
    function OnInput (event) {
        var cur_v = event.target.value;
		var cru_len = cur_v.length;
		var cur_last_v = cur_v.substring((cru_len-1),cru_len);
		var cur_except_last_v = cur_v.substring(0,(cru_len - 1));
		var reg = /^\d$/;
		if(!reg.test(cur_last_v)){
			if("." == cur_last_v){
				var dianCon = cur_except_last_v.indexOf(".");
				if(dianCon != -1){
					event.target.value = cur_except_last_v;
				}
			}else{
				event.target.value = cur_except_last_v;
			}
		} 
    }
    // Internet Explorer
    function OnPropChanged (event) {
        if (event.propertyName.toLowerCase () == "value") {
            var cur_v = event.srcElement.value;
			var cru_len = cur_v.length;
			var cur_last_v = cur_v.substring((cru_len-1),cru_len);
			var cur_except_last_v = cur_v.substring(0,(cru_len - 1));
			var reg = /^\d$/;
			if(!reg.test(cur_last_v)){
				if("." == cur_last_v){
					var dianCon = cur_except_last_v.indexOf(".");
					if(dianCon != -1){
						event.srcElement.value = cur_except_last_v;
					}
				}else{
					event.srcElement.value = cur_except_last_v;
				}
			} 
        }
    }

	function changeProBasic() {
		var vunit = $(".prodBasicsIds").find("option:selected").text().split(
				"-")[1];
		$("[name='inventorynumbers']").parent().find("em").html(vunit);
		$("[name='unit']").val(vunit);
	}
	//添加规格
	function guigeadd() {
		var ck_count = 0;
		$(".group-checkable").each(function(){
			if ($(this).is(":checked")) {
				ck_count += 1;
			}
		});
		if(0 == ck_count){
			showMessage("请至少选择一项规格！");
			return false;
		}
		
		var prodSpecDetalHTMLSon = "<tr>";
		$(".group-checkable").each(function(){
		    var title_t = $(this).attr("title");
		    var tid_t = $(this).attr("value");
		    var specDetalSelHTML = "<select class='form-control'	style='width: 120px;' name='specDetalInpt_"+tid_t+"'>";
		    $(".specDeta_"+tid_t).each(function(n,val) {
		    	specDetalSelHTML += "<option value='"+val.value+"'>"+val.name+"</option>";
		    });
		    specDetalSelHTML += "</select>";
		    if ($(this).is(":checked")) {
		    	prodSpecDetalHTMLSon += "<td class='th"+tid_t+"'>"+specDetalSelHTML+"</td>";
		    }else{
		    	prodSpecDetalHTMLSon += "<td class='th"+tid_t+" hidden'>"+specDetalSelHTML+"</td>";
		    }
		});
		prodSpecDetalHTMLSon += "<td><input type='hidden' name='skus'><input type='hidden' name='inventorynumbers' value='1'><input type='text' style='width:80px;' name='marketPrices' class='form-control' oninput='OnInput(event)' onpropertychange='OnPropChanged(event)'></td>"
								+"<td><input type='text' style='width:80px;' name='prices' class='form-control' oninput='OnInput(event)' onpropertychange='OnPropChanged(event)'></td>"
								+"<td><a onclick='delguige(this)' href='javascript:void(0)'> 删除</a></td></tr>";
		
		$("#prodSpec_tb").append(prodSpecDetalHTMLSon);
	}
	//删除规格
	function delguige(k) {
		$(k).parent().parent().remove()
	}
</script>
<script src="${SHOPDOMAIN}/js/webuploader/wximgupload.js"
	type="text/javascript"></script>
<!-- END PAGE CONTENT-->
