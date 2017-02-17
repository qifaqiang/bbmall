<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="top.jsp"></jsp:include>
<!-- /.modal -->
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title">
	概览
</h3>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a href="index.html">指示版</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a href="#">概览</a><a data-content="&lt;p&gt;&lt;strong&gt;待发货订单：&lt;/strong&gt;发货管理中所有待发货的订单数；&lt;/p&gt;&lt;p&gt;&lt;strong&gt;待处理维权：&lt;/strong&gt;维权管理列表所有处于维权中的维权单数；&lt;/p&gt;&lt;p&gt;&lt;strong&gt;昨日下单数：&lt;/strong&gt;昨日提交的订单总数；&lt;/p&gt;&lt;p&gt;&lt;strong&gt;昨日成交金额：&lt;/strong&gt;昨日总的成交金额（统计昨日已支付的实收款）。&lt;/p&gt;" data-trigger="hover" data-placement="bottom" data-html="true" data-toggle="popover" href="javascript:;" data-original-title="" title=""><i style="color: #000;vertical-align: -10%;" class="fa fa-question-circle fa-lg m-l-xs" ></i></a></li>
	</ul>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row">
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="dashboard-stat blue-madison">
			<div class="visual">
				<i class="fa fa-comments"></i>
			</div>
			<div class="details">
				<div class="number">0</div>
				<div class="desc">待接发订单</div>
			</div>
			<a href="#" class="more"> View more <i
				class="m-icon-swapright m-icon-white"></i>
			</a>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="dashboard-stat red-intense">
			<div class="visual">
				<i class="fa fa-bar-chart-o"></i>
			</div>
			<div class="details">
				<div class="number">0</div>
				<div class="desc">待处理维权</div>
			</div>
			<a href="#" class="more"> View more <i
				class="m-icon-swapright m-icon-white"></i>
			</a>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="dashboard-stat green-haze">
			<div class="visual">
				<i class="fa fa-shopping-cart"></i>
			</div>
			<div class="details">
				<div class="number">0</div>
				<div class="desc">昨日下单数</div>
			</div>
			<a href="" class="more"> View more <i
				class="m-icon-swapright m-icon-white"></i>
			</a>
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
		<div class="dashboard-stat purple-plum">
			<div class="visual">
				<i class="fa fa-globe"></i>
			</div>
			<div class="details">
				<div class="number">0.00</div>
				<div class="desc">昨日成交金额</div>
			</div>
			<a href="javascript:void(0)" class="more"> View more <i
				class="m-icon-swapright m-icon-white"></i>
			</a>
		</div>
	</div>
</div>
<style>
	.borderRightP{
		border-right:1px solid #e4e4e4;
		
	}
	.paddingTB15 h3{
		padding:15px 0;
	}
	.paddingTB10 h3{
		padding:10px 0;
	}
	.portlet.solid {
		padding:0 10px;
	}
</style>
<div class="row">
	<div class="col-md-7 col-sm-7">
		<!-- BEGIN PORTLET-->
		<div class="portlet solid grey-cararra bordered">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-bar-chart-o"></i>
					7天订单数据（<span id="last7days"></span>）
					<a data-content="&lt;p style='color:#999'&gt;&lt;strong style='color:#333'&gt;下单数：&lt;/strong&gt;每日提交的订单数；&lt;/p&gt;&lt;p style='color:#999'&gt;&lt;strong style='color:#333'&gt;成交数：&lt;/strong&gt;每日成交的订单数（统计每日已支付的订单）；&lt;/p&gt;&lt;p style='color:#999'&gt;&lt;strong style='color:#333'&gt;维权单数：&lt;/strong&gt;每日申请的维权单数。&lt;/p&gt;" data-trigger="hover" data-placement="bottom" data-html="true" data-toggle="popover" href="javascript:;" data-original-title="" title="" ><i class="fa fa-question-circle fa-lg m-l-xs" style="vertical-align:0"></i></a>
				</div>
				<div style="clear:both"></div>
				<div id="main" style="height:300px;"></div>
				<div style="margin: 20px 0 0px 0px">
					<div class="row" style="border-top:1px solid #e4e4e4;margin:0 -10px 0px; ">
						<div class="col-md-4 col-sm-3 col-xs-6 text-stat text-center borderRightP paddingTB15">
							<h3 id="sql1Count"></h3>
						</div>
						<div class="col-md-4 col-sm-3 col-xs-6 text-stat text-center borderRightP paddingTB15">
							<h3 id="sql2Count"></h3>
						</div>
						<div class="col-md-4 col-sm-3 col-xs-6 text-stat text-center paddingTB15">
							<h3 id="sql3Count"></h3>
						</div>
					</div>
				</div>

			</div>
		</div>
		<!-- END PORTLET-->
	</div>
	<div class="col-md-5 col-sm-5">
		<!-- BEGIN PORTLET-->
		<div class="portlet solid grey-cararra bordered">
			<div class="portlet-title">
				<div class="caption">
					<i class="fa fa-bar-chart-o"></i>
					7天交易数据（<span id="last7Charge"></span>）
					<a data-content="&lt;p style='color:#999'&gt;&lt;strong style='color:#333'&gt;成交金额：&lt;/strong&gt;7天内总的成交金额（统计7天内已支付的实收款）；&lt;/p&gt;&lt;p style='color:#999'&gt;&lt;strong style='color:#333'&gt;退款金额：&lt;/strong&gt;7天内成功退款的金额总数；&lt;/p&gt;&lt;p style='color:#999'&gt;&lt;strong style='color:#333'&gt;实际收入：&lt;/strong&gt;7天内的实际收入=成交金额&mdash;退款金额。&lt;/p&gt;" data-trigger="hover" data-placement="bottom" data-html="true" data-toggle="popover" href="javascript:;" data-original-title="" title="" ><i class="fa fa-question-circle fa-lg m-l-xs" style="vertical-align:0"></i></a>
				</div>
				<div style="clear:both"></div>
				<div id="main2" style="height:280px;"></div>
				<div style="margin: 8px 0 0 0">
					<div class="row" >
						<div class="col-md-12 col-sm-3 col-xs-6 text-stat">
							<h3 id="sql23Count"  style="font-size:18px; padding:9px 0; font-weight:bold"></h3>
						</div>
					</div>
					<div class="row" style="margin:0 -10px 0px;border-top:1px solid #e4e4e4">
						<div class="col-md-6 col-sm-3 col-xs-6 text-stat text-center borderRightP paddingTB10">
							<h3 id="sql21Count"></h3>
						</div>
						<div class="col-md-6 col-sm-3 col-xs-6 text-stat text-center paddingTB10">
							<h3 id="sql22Count"></h3>
						</div>
					</div>
				</div>

			</div>
		</div>
		<!-- END PORTLET-->
	</div>
</div>

<jsp:include page="foot.jsp"></jsp:include>
<script src="${SHOPDOMAIN}/js/echarts/dist/echarts.js"></script>
<script type="text/javascript">
	var myChart;var myChart2;
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

	], function(ec) {
		myChart = ec.init(document.getElementById('main'));
		myChart2 = ec.init(document.getElementById('main2'));

		// 过渡---------------------
		myChart.showLoading({
			text : '正在努力的读取数据中...', //loading话术
		});
		myChart2.showLoading({
			text : '正在努力的读取数据中...', //loading话术
		});
		$.ajax({
			url : "${SHOPDOMAIN}/system/statistic/mainLastChargeSql.html",
			type : "post",
			async:false,
			dataType : "json",
			success : function(data) {
				$("#last7Charge").html(data.dateSE);

				// 图表使用-------------------
				myChart2.hideLoading();
				option = {
					    tooltip : {
					        trigger: 'axis',
					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
					            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
					        }
					    },
					    legend: {
					        data:['成交额', '维权金额'],y : 260
					    },
					    calculable : true,
					    xAxis : [
					        {
					            type : 'value'
					        }
					    ],
					    grid : {
							y : 35,
							x:40,
							x2:50
						},
					    yAxis : [
					        {
					            type : 'category',
					            axisTick : {show: false},
					            data : data.date
					        }
					    ],
					    series : [
					        
					        {
					            name:'成交额',
					            type:'bar',
					            stack: '总量',
					            barWidth : 5,
					            itemStyle: {normal: {
					                label : {show: true}
					            }},
					            data: data.sql1
					        },
					        {
					            name:'维权金额',
					            type:'bar',
					            stack: '总量',
					            itemStyle: {normal: {
					                label : {show: true, position: 'left'}
					            }},
					            data: data.sql2
					        }
					    ]
					};
				
				myChart2.setOption(option);
				
				$("#sql21Count").html("成交金额<br>¥"+data.sql1Count.toFixed(2));
				$("#sql22Count").html("退款金额<br>¥"+data.sql2Count.toFixed(2));
				$("#sql23Count").html("实际收入:¥"+(data.sql1Count-data.sql2Count).toFixed(2));
			}
		});
		
		$.ajax({
			url : "${SHOPDOMAIN}/system/statistic/mainLastOrderSql.html",
			type : "post",
			dataType : "json",
			async:false,
			success : function(data) {
				$("#last7days").html(data.dateSE);

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
						data : [ '下单数', '成交数', '维权单数' ],
						y : 280
					},
					grid : {
						y : 35
					},
					toolbox : {
						show : true,
						feature : {
							mark : {
								show : true
							},
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
						type : 'category',
						boundaryGap : false,
						data : data.date
					} ],
					yAxis : [ {
						type : 'value',
						axisLabel : {
							formatter : '{value}'
						}
					} ],
					series : [ {
						name : '下单数',
						type : 'line',
						data : data.sql1,
						markLine : {
							data : [ {
								type : 'average',
								name : '平均值'
							} ]
						}
					}, {
						name : '成交数',
						type : 'line',
						data : data.sql2,
						markLine : {
							data : [ {
								type : 'average',
								name : '平均值'
							} ]
						}
					}, {
						name : '维权单数',
						type : 'line',
						data : data.sql3,
						markLine : {
							data : [ {
								type : 'average',
								name : '平均值'
							} ]
						}
					} ]
				};
				myChart.setOption(option);
				
				$("#sql1Count").html(data.sql1Count+"<br>下单数");
				$("#sql2Count").html(data.sql2Count+"<br>成交数");
				$("#sql3Count").html(data.sql3Count+"<br>维权单数");
			}
		});
	});
	
	function GetDateStr(AddDayCount) {
	    var dd = new Date();
	    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
	    var y = dd.getFullYear();
	    var m = dd.getMonth()+1;//获取当前月份的日期
	    var d = dd.getDate();
	    return y+"-"+(m<10?"0"+m:m)+"-"+(d<10?"0"+d:d)+' 00:00';
	}
	
	$(function() {
		 $("[data-toggle='popover']").popover(); 
		$.ajax({
			url : "${SHOPDOMAIN}/system/statistic/mainStatistic.html",
			type : "post",
			dataType : "json",
			async:false,
			success : function(data) {
				var temp="orders";
				if(data.roleId==40){
					temp="ordersBase";
				}
				$.each(data.statictis, function(index, item) {
					if (index == 3) {
						$(".number").eq(index).parent().next().attr("href","${SHOPDOMAIN}/system/"+temp+"/orderslist.html?typeid=15&ty=-1&ttime="+GetDateStr(-1)+" ~ "+GetDateStr(0));
						$(".number").eq(index).html(item.counts.toFixed(2));
					} else if (index == 0) {
						$(".number").eq(index).parent().next().attr("href","${SHOPDOMAIN}/system/"+temp+"/orderslist.html?typeid=15&ty=20");
						$(".number").eq(index).html(item.counts);
					} else if (index == 1) {
						$(".number").eq(index).parent().next().attr("href","${SHOPDOMAIN}/system/"+temp+"/returnorderslist.html?typeid=15&ty=50");
						$(".number").eq(index).html(item.counts);
					} else if (index == 2) {
						$(".number").eq(index).parent().next().attr("href","${SHOPDOMAIN}/system/"+temp+"/orderslist.html?typeid=15&ty=-1&ttime="+GetDateStr(-1)+" ~ "+GetDateStr(0));
						$(".number").eq(index).html(item.counts);
					}

				})
			}
		});
	})
</script>