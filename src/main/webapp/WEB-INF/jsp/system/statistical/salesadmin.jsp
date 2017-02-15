<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />

<!-- /.modal -->
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title">销售统计（用户确认收货${SYSPROPORTION.autoOverTime}后小时，不可退货的时候才会计入统计）</h3>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a href="#">统计管理</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a href="#">销售统计</a>
	</ul>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row m-t-sm">
	<div class="col-sm-11 m-b-xs">
		<div class="form-group" style="float:left">
			<div class="btn-group" style="float:left">
				<style>
.input-group .input-group-addon>i {
	font-size: 11px;
	line-height: 11px;
	font-weight: bold;
	color: #666;
}

.spanActive {
	height: 30px;
	border-radius: 0;
	padding: 6px 10px;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1), 0 1px 2px rgba(0, 0, 0, 0.18);
}

.input-group .inputActive {
	width: 180px;
	height: 30px;
	line-height: 11px;
	padding: 7px 10px 5px 12px;
	font-size: 11px;
	font-weight: bold;
	cursor: pointer;
	float: left;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1), 0 1px 2px rgba(0, 0, 0, 0.18);
}

.inputActive:hover {
	box-shadow: 0 3px 6px rgba(0, 0, 0, 0.2), 0 3px 6px rgba(0, 0, 0, 0.26);
	background-color: #d4d4d4;
	border-color: #d4d4d4;
	border-radius: 5px;
}

.input-noneTable.active .spanActive,.input-noneTable.active .inputActive
	{
	background-color: #c7c7c7;
	border: none;
}

.m-lStyle {
	display: inline-block;
	height: 30px;
	line-height: 30px;
	font-size: 11px;
	font-weight: bold;
	padding-left: 10px;
}

.input-noneTable {
	display: inline-block;
}

.spanActive {
	width: auto;
	display: inline-block;
	float: left;
}
</style>


			</div>
			<div class="input-prepend input-group input-noneTable" style="">
				<span class="add-on  input-group-addon spanActive input-group-btn"
					style=""><i
					class="glyphicon glyphicon-calendar fa fa-calendar ">选择月份</i></span> <input
					type="text" style="" name="ttime" id=""
					class="form-control form_datetime inputActive " readonly="readonly"
					value="${date }"> <span class="m-l m-lStyle "></span>
			</div>
			<div class="input-prepend input-group input-noneTable" style="">
				<span class="add-on  input-group-addon spanActive input-group-btn"
					style=""><i class="glyphicon  fa ">基地</i></span>
				<div class="btn-group" style="float:left">
					<button class="btn gray isCheckedd"
						style="padding: 7px 14px 6px 14px;"
						companyId="${listCompanyName[0].companyId }" type="button">${listCompanyName[0].companyName }</button>
					<button data-toggle="dropdown" style="padding: 7px 14px 6px 14px;"
						class="btn gray dropdown-toggle" type="button">
						<i class="fa fa-angle-down"></i>
					</button>
					<ul role="menu" class="dropdown-menu">
						<li><a href="javascript:void(0)"
							<c:forEach items="${listCompanyName }" var="lcn">
							<li><a href="javascript:void(0)"
								onclick="checkCompanyId('${lcn.companyId }',this)">
									${lcn.companyName } </a></li>
						</c:forEach>
					</ul>
					<button onclick="toexcel()" type="button" class="btn btn-sm green"
						style="margin-left: 15px;float: left;height: 31px;">
						<i class="fa fa-share"></i> 导出
					</button>
				</div>
				<span class="m-l m-lStyle "></span>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-sm-12">
		<!-- BEGIN PORTLET-->
		<div class="portlet solid grey-cararra bordered">
			<div class="portlet-title">
				<div id="main" style="height: 1500px;"></div>
			</div>
		</div>
		<!-- END PORTLET-->
	</div>
</div>
<jsp:include page="../foot.jsp"></jsp:include>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="${SHOPDOMAIN}/js/echarts/dist/codemirror.js"></script>

<script src="${SHOPDOMAIN}/js/echarts/dist/echarts.js"></script>
<script type="text/javascript">
	var nowday = "${date }";
	var myChart;
	// Step:3 conifg ECharts's path, link to echarts.js from current page.
	// Step:3 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
	require.config({
		paths : {
			echarts : '${SHOPDOMAIN}/js/echarts/dist'
		}
	});

	// Step:4 require echarts and use it in the callback.
	// Step:4 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
	require([ 'echarts', 'echarts/chart/line', 'echarts/chart/bar',
			'echarts/chart/pie', 'echarts/chart/funnel' ], function(ec) {
		myChart = ec.init(document.getElementById('main'));

		// 过渡---------------------
		myChart.showLoading({
			text : '正在努力的读取数据中...', //loading话术
		});
		fun2();
	});

	function checkCompanyId(id, kthis) {
		$(".isCheckedd").attr("companyId", id).html($(kthis).html());
		fun2();
	}
	function fun2() {
		var companyId = $(".isCheckedd").attr("companyId");
		myChart.clear();
		$.ajax({
			url : "${SHOPDOMAIN}/system/statistic/salesRangeDayAdmin.html",
			type : "post",
			dataType : "json",
			data : {
				companyId : companyId,
				times : $(".form_datetime").val()
			},
			async : false,
			success : function(data) {
				var str1 = '[';
				var tempPbName = '[';
				$.each(data.sql1, function(k, v) {
					tempPbName += "'" + data.mapsPB[k] + "',";
					str1 += v + ",";
				})
				tempPbName = tempPbName.substring(0, tempPbName.length - 1);
				str1 = str1.substring(0, str1.length - 1);
				str1 += ']';
				tempPbName += ']';
				var objSeries = eval("(" + str1 + ")");
				var objName = eval("(" + tempPbName + ")");
				// 图表使用-------------------
				myChart.hideLoading();
				var option = {
					title : {
						text : '',
						subtext : ''
					},
					tooltip : {
						trigger : 'axis'
					},
					legend : {
						data : [ '销售量' ]

					},
					toolbox : {
						show : true,
						feature : {
							dataView : {
								show : true,
								readOnly : false
							},
							magicType : {
								show : true,
								type : [ 'line', 'bar' ]
							},
							restore : {
								show : true
							},
							saveAsImage : {
								show : true
							}
						}
					},
					calculable : true,
					xAxis : [ {
						type : 'value',
						boundaryGap : [ 0, 0.01 ]
					} ],
					yAxis : [ {
						type : 'category',
						data : objName
					} ],
					series : [ {
						name : '销售量',
						type : 'bar',
						data : objSeries
					} ]
				};

				myChart.setOption(option);
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
			if ("" == $(".form_datetime").val()) {
				$(".form_datetime").val(nowday)
			}
			fun2();
		});
	});
	function toexcel() {
		window
				.open("${SHOPDOMAIN}/system/statistic/salesadmintoexcel.html?companyId="
						+ $(".isCheckedd").attr("companyId")
						+ "&times="
						+ $(".form_datetime").val());
	}
</script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/moment.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>

