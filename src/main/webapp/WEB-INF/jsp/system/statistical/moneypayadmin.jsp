<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />

<!-- /.modal -->
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title">支付资金统计(统计根据每月支付和退款的订单)</h3>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/front/css/tongji.css" />

<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a href="#">统计管理</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a href="#">资金统计</a>
	</ul>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row m-t-sm">
	<div class="col-sm-11 m-b-xs">
		<div class="form-group" style="float: left">
			<div class="btn-group" style="float: left"></div>
			<div class="input-prepend input-group input-noneTable" style="">
				<span class="add-on  input-group-addon spanActive input-group-btn "
					style=""><i
					class="glyphicon glyphicon-calendar fa fa-calendar "></i>选择月份</span> <input
					type="text" style="" name="ttime" id="ttime"
					class="form-control form_datetime inputActive" readonly="readonly"
					value="${now_month }"> <span class="m-l m-lStyle "></span>
				<button onclick="toexcel()" type="button" class="btn btn-sm green"
					style="margin-left: 15px; float: left;height: 31px;">
					<i class="fa fa-share"></i> 导出
				</button>
			</div>

		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN SAMPLE TABLE PORTLET-->
		<div class="portlet box gray"
			style="padding:0 10px 10px 10px!important;">
			<div class="portlet-title">
				<div class="caption" style="color: black;font-weight:bold">资金总计
				</div>
			</div>
			<div class="portlet-body   companyMemberCountHtml borderLeft">
			</div>
		</div>
		<!-- END SAMPLE TABLE PORTLET-->
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-sm-12">
		<!-- BEGIN PORTLET-->
		<div class="portlet solid grey-cararra bordered">
			<div class="portlet-title">
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>基地</th>
								<th >微信支付</th>
								<th >支付宝支付</th>
								<th >银联支付</th>
								<th >合计</th>
							</tr>
						</thead>
						<tbody id="tbodyss">
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- END PORTLET-->
	</div>
</div>
<jsp:include page="../foot.jsp"></jsp:include>
<script src="${SHOPDOMAIN}/js/echarts/dist/echarts.js"></script>
<script type="text/javascript">
	var nowday = "${now_month }";

	fun1();

	function fun1() {
		$.ajax({
			url : "${SHOPDOMAIN}/system/statistic/moneypayadminMonth.html",
			type : "post",
			dataType : "json",
			async : false,
			data : {
				terms : $("#ttime").val()
			},
			success : function(data) {
				var companyList=data.mapCompanyName;
				var html="";
				//总计微信支付
				var allweixinzhifuyingshou=0;
				var allweixinzhifutuikuan=0;
				var allweixinzhifushishou=0;
				
				//总计支付宝支付
				var allzhifubaozhifuyingshou=0;
				var allzhifubaozhifutuikuan=0;
				var allzhifubaozhifushishou=0;
				
				//总计银联支付
				var allyinlianzhifuyingshou=0;
				var allyinlianzhifutuikuan=0;
				var allyinlianzhifushishou=0;
				
				//总计所有支付
				var allzhifuyingshou=0;
				var allzhifutuikuan=0;
				var allzhifushishou=0;
				$.each(data.mapAll, function(index, item) {
					html+="<tr><td>"+data.mapCompanyName[index];
					//微信
					var weixinzhifuyingshou=0;
					var weixinzhifutuikuan=0;
					var weixinzhifushishou=0;
					
					//支付宝
					var zhifubaozhifuyingshou=0;
					var zhifubaozhifutuikuan=0;
					var zhifubaozhifushishou=0;
					
					//银联
					var yinlianzhifuyingshou=0;
					var yinlianzhifutuikuan=0;
					var yinlianzhifushishou=0;
					
					//合计
					var hezhifuyingshou=0;
					var hezhifutuikuan=0;
					var hezhifushishou=0;
					
					
					//1pc(支付宝) 2pc（微信扫码） 3pc（银联支付） 4wap（支付宝）
					// 5wap（微信） 6wap（银联支付）
					
					if(undefined!=item[1]&&undefined!=item[1]["yinshou"]){
						zhifubaozhifuyingshou=item[1]["yinshou"];
						hezhifuyingshou +=zhifubaozhifuyingshou;
					}
					if(undefined!=item[2]&&undefined!=item[2]["yinshou"]){
						weixinzhifuyingshou=item[2]["yinshou"];
						hezhifuyingshou +=weixinzhifuyingshou;
					}
					if(undefined!=item[3]&&undefined!=item[3]["yinshou"]){
						yinlianzhifuyingshou=item[3]["yinshou"];
						hezhifuyingshou +=yinlianzhifuyingshou;
					}
					if(undefined!=item[4]&&undefined!=item[4]["yinshou"]){
						zhifubaozhifuyingshou=item[4]["yinshou"];
						hezhifuyingshou +=zhifubaozhifuyingshou;
					}
					if(undefined!=item[5]&&undefined!=item[5]["yinshou"]){
						weixinzhifuyingshou=item[5]["yinshou"];
						hezhifuyingshou +=weixinzhifuyingshou;
					}
					if(undefined!=item[6]&&undefined!=item[6]["yinshou"]){
						yinlianzhifuyingshou=item[6]["yinshou"];
						hezhifuyingshou +=yinlianzhifuyingshou;
					}
					
					
					if(undefined!=item[1]&&undefined!=item[1]["tuikuan"]){
						zhifubaozhifutuikuan=item[1]["tuikuan"];
						hezhifutuikuan+=zhifubaozhifutuikuan;
					}
					if(undefined!=item[2]&&undefined!=item[2]["tuikuan"]){
						weixinzhifutuikuan=item[2]["tuikuan"];
						hezhifutuikuan+=weixinzhifutuikuan;
					}
					if(undefined!=item[3]&&undefined!=item[3]["tuikuan"]){
						yinlianzhifutuikuan=item[3]["tuikuan"];	
						hezhifutuikuan+=yinlianzhifutuikuan;
					}
					if(undefined!=item[4]&&undefined!=item[4]["tuikuan"]){
						zhifubaozhifutuikuan=item[4]["tuikuan"];
						hezhifutuikuan+=zhifubaozhifutuikuan;
					}
					if(undefined!=item[5]&&undefined!=item[5]["tuikuan"]){
						weixinzhifutuikuan=item[5]["tuikuan"];
						hezhifutuikuan+=weixinzhifutuikuan;
					}
					if(undefined!=item[6]&&undefined!=item[6]["tuikuan"]){
						yinlianzhifutuikuan=item[6]["tuikuan"];
						hezhifutuikuan+=yinlianzhifutuikuan;
					}
					
					
					//计算收入
					weixinzhifushishou=weixinzhifuyingshou-weixinzhifutuikuan;
					zhifubaozhifushishou=zhifubaozhifuyingshou-zhifubaozhifutuikuan;
					yinlianzhifushishou=yinlianzhifuyingshou-yinlianzhifutuikuan;
					hezhifushishou=hezhifuyingshou-hezhifutuikuan;
					
					
					//计算全局
					allzhifuyingshou+=hezhifuyingshou;
					allzhifutuikuan+=hezhifutuikuan;
					allzhifushishou+=hezhifushishou;
					
					allweixinzhifushishou+=weixinzhifushishou;
					allweixinzhifuyingshou+=weixinzhifuyingshou;
					allweixinzhifutuikuan+=weixinzhifutuikuan;
					
					allzhifubaozhifushishou+=zhifubaozhifushishou;
					allzhifubaozhifuyingshou+=zhifubaozhifuyingshou;
					allzhifubaozhifutuikuan+=zhifubaozhifutuikuan;
					
					allyinlianzhifushishou+=yinlianzhifushishou;
					allyinlianzhifuyingshou+=yinlianzhifuyingshou;
					allyinlianzhifutuikuan+=yinlianzhifutuikuan;
					
					
					html+="</td><td>实收："+weixinzhifuyingshou.toFixed(2)+"</td><td>实收："+zhifubaozhifuyingshou.toFixed(2)+"</td><td>实收："+yinlianzhifuyingshou.toFixed(2)+"</td><td>实收："+hezhifuyingshou.toFixed(2)+"</td></tr>";
				})
				var tip="<div class='divFloat'>微信支付实收:"+allweixinzhifuyingshou.toFixed(2)+
				"</div><div class='divFloat'>支付宝支付实收:"+allzhifubaozhifuyingshou.toFixed(2)+
				"</div><div class='divFloat'>银联支付实收:"+allyinlianzhifuyingshou.toFixed(2)+
				"</div><div class='divFloat'>合计实收:"+allzhifushishou.toFixed(2)+"</div>";
				
				$(".companyMemberCountHtml").html(tip);
				
				$("#tbodyss").html(html);

			}
		});
	}

	$(function() {
		$('.form_datetime').parent('.input-group').on(
				'click',
				'.input-group-btn',
				function(e) {
					$(this).parent('.input-group').find('.form_datetime')
							.datepicker("show");
				});
		$(".form_datetime").datepicker({
			format : 'yyyy-mm',
			weekStart : 1,
			autoclose : true,
			startView : 2,
			maxViewMode : 1,
			minViewMode : 1,
			forceParse : false,
		});
		$(".form_datetime").datepicker().on("hide", function(ev) {
			fun1();
		});
	});
	function toexcel() {
		window
				.open("${SHOPDOMAIN}/system/statistic/moneypayadminexcel.html?terms="
						+ $(".form_datetime").val());
	}
</script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/moment.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>

