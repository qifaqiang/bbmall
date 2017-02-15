<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../../top.jsp"></jsp:include>
<script id="prodSpecStockListShow" type="text/x-dot-template">
{{for(var i=0;i<it.length;i++){ }}
    {{var product=it[i];}}
        <tr class="">
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
			<td><span id="ct_{{=product.type}}_{{=product.id}}_{{=product.productId}}">{{=product.inventorycount}}</span></td>
			<td>
				<input type="text" style="width:50px" id="in_{{=product.type}}_{{=product.id}}_{{=product.productId}}"/> &nbsp;&nbsp;
				<a
					class="btn default btn-xs purple" stockId="{{=product.stockId}}"
					id="{{=product.type}}_{{=product.id}}_{{=product.productId}}"
					onClick="addProdStock(this);" href="javascript:void(0)">
					<i class="fa fa-edit"></i> 进货
				</a>
			</td>
		</tr>
{{}}}
</script>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			基地库存列表 <small> 列表</small>
		</h3>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<style>
.product input {
	width: 50px;
}

.listContact_addStock {
	display: inline-block;
}

.range {
	display: inline-block;
}
</style>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-md-6" style="width: 100%">
		<!-- BEGIN SAMPLE TABLE PORTLET-->
		<div class="portlet box blue">
			<div class="portlet-title">
				<div class="caption">
					<i class=" fa fa-cogs"></i>信息
				</div>
			</div>
			<div class="portlet-body">
				<div class="input-group"
					style="text-align: left; margin-bottom: 3px;">
					<select style="width: 150px; float: right;margin-right: 10px;margin-top: 3px"
						class="form-control select2me" id="companyList"
						name="companyList" data-placeholder="选择">
						<option value="01">--选择基地--</option>
						<c:forEach items="${CompanyList}" var="p" varStatus="vs">
							<option value="${p.companyId}">${p.companyName }</option>
						</c:forEach>
					</select> <span style="float:right;height:35px;line-height:35px">基地商户名称:&nbsp;</span>
					<span class="input-group-btn" id="search"> <a
						href="javascript:;" class="btn green"
						<i class="fa fa-search"></i> 搜索</a>
					</span>
				</div>
				<div class="table-responsive">
					<div style="width:20%;float:left;">
						<table class="table table-bordered table-hover" id="company_table">
							<thead>
								<tr>
									<th bgcolor="gray">基地名称</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${CompanyList }" var="company">
									<tr>
										<td><a onClick="showallprod(${company.companyId },1);" href="javascript:void(0);">${company.companyName }</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div style="width:79%;float:right;">
						<table class="table table-bordered table-hover" id="prod_table">
							<thead>
								<tr bgcolor="gray">
									<th style="text-align: center" 
										class="start">产品名称</th>
									<th style="text-align: center"
										class="start">规格</th>
									<th style="width:80px;text-align: center"
										class="start">当前库存</th>
									<th style="width:180px;text-align: center"
										class="start">操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<div class='page_and_btn'></div>
					</div>
				</div>
			</div>
		</div>
		<!-- END SAMPLE TABLE PORTLET-->
	</div>
</div>
<input type="hidden" id="companyId" value=""></input>
<script>
	$(function(){
		$("#companyId").val("");
		
		//搜索按钮绑定
		$("#search").click(function() {
			$("#prod_table tbody").html("");//清掉之前的信息
			//得到公司的选值
			var selectCompany = $("#companyList").find("option:selected")
					.val();
			//如果公司和产品都不选中的话
			if (selectCompany == "01") {
				return false;
			}
			showallprod(selectCompany,1);
		});
	});
	//点击左侧的基地名称，然后查询所有的产品信息
	function showallprod(companyId,currentPage){
		$("#prod_table tbody").html("");//清掉之前的信息
		if("" != companyId){
			$("#companyId").val("");
			$("#companyId").val(companyId);
			$.ajax({
				url : "getallprodbycompanyid.html",
				type : "post",
				dataType : "json",
				sync : false,
				data : {
					"currentPage" : currentPage,
					"companyId" : companyId,
					"showCount":20
				},
				success : function(data) {
					var page = data.page.pageStr;
					var evalText = doT.template($("#prodSpecStockListShow").html());
					$("#prod_table tbody").append(evalText(data.list));
					$(".page_and_btn").html("<div></div>" + page);
				},
				error : function() {
					showMessage("error……");
				}
			});
		}else{
			showMessage("操作有误");
		}
	}
	//专为分页用
	function showallprodforpage(currentPage){
		var companyId_temp = $("#companyId").val();
		if("" != companyId_temp){
			showallprod(companyId_temp,currentPage)
		}else{
			showMessage("操作有误");
		}
	}
	
	function addProdStock(event){
		var id = $(event).attr("id");
		var inventorynumber = $("#in_" + id).val();
		var companyId_t = $("#companyId").val();
		var stockId_t = $(event).attr("stockId");

		var reg = /^\d*$/;
		if (reg.test(inventorynumber) && inventorynumber > 0
				&& (inventorynumber + "").indexOf(".") == -1
				&& (inventorynumber + "").indexOf("+") == -1) {
			$.ajax({
				url : "addStockNew.html",
				type : "post",
				dataType : "json",
				async : false,
				data : {
					"id" : stockId_t,
					"companyId" : companyId_t,
					"values" : id,
					"inventorycount" : inventorynumber
				},
				success : function(data) {
					if (data.type == "success") {
						$("#ct_" + id).html(data.invent);
						showMessage("进货成功！");
					} else {
						showMessage("进货失败，请重试或者联系管理员！");
					}
				},
				error : function() {
					showMessage("error……");
				}
			});
		} else {
			showMessage("请输入大于0 的正整数！！！");
		}
	}
</script>
<!-- END PAGE CONTENT-->
<jsp:include page="../../foot.jsp"></jsp:include>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" />
