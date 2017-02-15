<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />

<!-- /.modal -->
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title">
	会员统计 ( 累计会员数：<span id="allcount">0</span> )
</h3>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/front/css/tongji.css" />
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a href="#">统计管理</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a href="#">会员统计</a>
	</ul>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row m-t-sm">
	<div class="col-sm-11 m-b-xs">
		<div class="form-group" style="float: left">
			<div class="btn-group" style="float: left">
				<a href="javascript:getCharts(1)"
					class="btn btn-default btn-sm btn-s-sm sm1 active"
					style="height:30px;">昨天</a><a href="javascript:getCharts(2)"
					class="btn btn-default sm2 btn-sm btn-s-sm " style="height:30px;">最近7天</a><a
					href="javascript:getCharts(3)"
					class="btn btn-default sm3 btn-sm btn-s-sm " style="height:30px;">最近30天</a>
			</div>
			<div class="input-prepend input-group input-noneTable" style="">
				<span class="add-on  input-group-addon spanActive input-group-btn "
					style=""><i
					class="glyphicon glyphicon-calendar fa fa-calendar "></i>选择月份</span> <input
					type="text" style="" name="ttime" id=""
					class="form-control form_datetime inputActive" readonly="readonly"
					value="2017-01"> <span class="m-l m-lStyle "></span>
				<button onclick="toexcel()" type="button" class="btn btn-sm green"
					style="margin-left: 15px; float: left;height: 31px;">
					<i class="fa fa-share"></i> 导出(只支持月份导出)
				</button>
			</div>

		</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN SAMPLE TABLE PORTLET-->
		<div class="portlet box gray" style="padding:0 10px 10px 10px!important;">
			<div class="portlet-title">
				<div class="caption" style="color: black;font-weight:bold">所有基地会员总计
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
				<div id="main" style="height: 500px;"></div>
			</div>
		</div>
		<!-- END PORTLET-->
	</div>
</div>
<div class="row row2">
	<div class="col-md-12 col-sm-12">
		<!-- BEGIN PORTLET-->
		<div class="portlet solid grey-cararra bordered">
			<div class="portlet-title">
				<div id="main2" style="height: 400px;"></div>
			</div>
		</div>
		<!-- END PORTLET-->
	</div>
</div>
<jsp:include page="../foot.jsp"></jsp:include>
<script src="${SHOPDOMAIN}/js/echarts/dist/echarts.js"></script>
<script type="text/javascript">
	var nowday = "";
	var myChart;
	var myChart2;
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
		myChart2 = ec.init(document.getElementById('main2'));

		// 过渡---------------------
		myChart.showLoading({
			text : '正在努力的读取数据中...', //loading话术
		});
		myChart2.showLoading({
			text : '正在努力的读取数据中...', //loading话术
		});
		fun1();
	});

	//初始化图形
	function getCharts(type) {
		$(".sm" + type).addClass("active").siblings().removeClass("active");
		$(".input-prepend").removeClass("active");
		if (type == 1) {
			myChart.clear();
			myChart2.clear();
			fun1();
		}
		if (type == 2) {
			myChart.clear();
			myChart2.clear();
			fun2(type);
		}
		if (type == 3) {
			myChart.clear();
			myChart2.clear();
			fun2(type);
		}
	}

	//图像1
	function fun1() {
		$.ajax({
			url : "${SHOPDOMAIN}/system/statistic/memberLastOneDayAdmin.html",
			type : "post",
			dataType : "json",
			async : false,
			success : function(data) {
				var str1 = '[';
				var str2 = '[';
				$.each(data.companyName, function(index, item) {
					str1 += '{ "name": "' + item + '", "type": "bar","data":"'
							+ data.sql1[index] + '" },';
					str2 += '{ "name": "' + item + '", "value":"'
							+ data.sql1[index] + '" },';
				})
				
				var companyMemberCountHtml="";
				$.each(data.companyMemberCount, function(key, val) {
					companyMemberCountHtml += '<div class="divFloat">'+key+':'+val+'人</div>';
				})
				$(".companyMemberCountHtml").html(companyMemberCountHtml);
				str1 = str1.substring(0, str1.length - 1);
				str1 += ']';
				str2 = str2.substring(0, str2.length - 1);
				str2 += ']';
				var objSeries = $.parseJSON(str1);
				var objSeries2 = $.parseJSON(str2);
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
						data : data.companyName,
						y : 480
					},
					grid : {
						y : 35
					},
					toolbox : {
						show : true,
						feature : {
							saveAsImage : {
								show : true
							}
						}
					},
					calculable : true,
					xAxis : [ {
						type : 'category',
						data : [ '基地' ]
					} ],
					yAxis : [ {
						type : 'value',
					} ],
					series : objSeries
				};
				myChart.setOption(option);

				myChart2.hideLoading();
				var option2 = {
					title : {
						text : '各个基地所占比例',
						subtext : '',
						x : 'center'
					},
					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b} : {c} ({d}%)"
					},
					legend : {
						orient : 'vertical',
						x : 'left',
						data : data.companyName
					},
					toolbox : {
						show : true,
						feature : {
							magicType : {
								show : true,
								type : [ 'pie', 'funnel' ],
								option : {
									funnel : {
										x : '25%',
										width : '50%',
										funnelAlign : 'left',
										max : 1548
									}
								}
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
					series : [ {
						name : '各个基地所占比例',
						type : 'pie',
						radius : '55%',
						center : [ '50%', '60%' ],
						data : objSeries2
					} ]
				};
				myChart2.setOption(option2);
				$("#allcount").html(data.allcount);
				$(".form_datetime").val(data.date);
				nowday = data.date;

			}
		});
	}
	
	//图像2
	function fun2(types, times) {
		$.ajax({
			url : "${SHOPDOMAIN}/system/statistic/memberRangeDayAdmin.html",
			type : "post",
			dataType : "json",
			data : {
				type : types,
				times : times
			},
			async : false,
			success : function(data) {
				var str1 = '[';
				var str2 = '[';
				$.each(data.companyId, function(index, item) {
					var tempCounts = "";
					var tempSums = 0;
					var tempCompanyId = item;
					$.each(data.sql1[tempCompanyId], function(index, item) {
						tempCounts += item + ",";
						tempSums += item;
					})
					str1 += '{ "name": "' + data.companyName[index]
							+ '","stack": "总量", "type": "bar","data":['
							+ tempCounts.substring(0, tempCounts.length - 1)
							+ '] },';
					str2 += '{ "name": "' + data.companyName[index]
							+ '", "value":"' + tempSums + '" },';
				})
				str1 = str1.substring(0, str1.length - 1);
				str1 += ']';
				var objSeries = eval("(" + str1 + ")");
				str2 = str2.substring(0, str2.length - 1);
				str2 += ']';
				
				var objSeries2 = $.parseJSON(str2);
				// 图表使用-------------------
				myChart.hideLoading();
				var option = {
					tooltip : {
						trigger : 'axis',
						axisPointer : {
							type : 'shadow'
						}
					},
					legend : {
						data : data.companyName
					},
					toolbox : {
						show : true,
						orient : 'vertical',
						y : 'center',
						feature : {
							mark : {
								show : true
							},
							magicType : {
								show : true,
								type : [ 'line', 'bar', 'stack', 'tiled' ]
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
						type : 'category',
						data : data.dateListNoYears
					} ],
					yAxis : [ {
						type : 'value',
						splitArea : {
							show : true
						}
					} ],
					grid : {
						x2 : 40
					},
					series : objSeries
				};
				myChart.setOption(option);

				myChart2.hideLoading();
				var option2 = {
					title : {
						text : '各个基地所占比例',
						subtext : '',
						x : 'center'
					},
					tooltip : {
						trigger : 'item',
						formatter : "{a} <br/>{b} : {c} ({d}%)"
					},
					legend : {
						orient : 'vertical',
						x : 'left',
						data : data.companyName
					},
					toolbox : {
						show : true,
						feature : {
							magicType : {
								show : true,
								type : [ 'pie', 'funnel' ],
								option : {
									funnel : {
										x : '25%',
										width : '50%',
										funnelAlign : 'left',
										max : 1548
									}
								}
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
					series : [ {
						name : '各个基地所占比例',
						type : 'pie',
						radius : '55%',
						center : [ '50%', '60%' ],
						data : objSeries2
					} ]
				};
				myChart2.setOption(option2);
			}
		});
	}

	//初始化
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
			$(".sm1").removeClass("active");
			$(".sm2").removeClass("active");
			$(".sm3").removeClass("active");
			$(this).parent().addClass("active");
			if ("" == $(".form_datetime").val()) {
				$(".form_datetime").val(nowday)
			}
			console.log($(".form_datetime").val());
			myChart.clear();
			myChart2.clear();
			fun2(4, $(".form_datetime").val());
		});
	});
	
	//到处excel
	function toexcel() {
		window
				.open("${SHOPDOMAIN}/system/statistic/memberadmintoexcel.html?times="
						+ $(".form_datetime").val());
	}
</script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/moment.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>

