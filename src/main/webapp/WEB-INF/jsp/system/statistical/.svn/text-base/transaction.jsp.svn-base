<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../top.jsp"></jsp:include>


<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css" />
<!-- BEGIN PAGE HEADER-->
<h3 class="page-title">交易统计</h3>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><i class="fa fa-home"></i> <a href="#">统计管理</a> <i
			class="fa fa-angle-right"></i></li>
		<li><a href="#">交易统计</a>
	</ul>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<div class="row m-t-sm">
	<div class="col-sm-11 m-b-xs">
		<div class="form-group" style="float:left">
			<div class="btn-group" style="float:left" id="dateTerms">
				<a href="javascript:getCharts(1)"
					class="btn btn-default btn-sm btn-s-sm sm1 active" title="1">昨天</a><a
					href="javascript:getCharts(2)"
					class="btn btn-default sm2 btn-sm btn-s-sm " title="2">最近7天</a><a
					href="javascript:getCharts(3)"
					class="btn btn-default sm3 btn-sm btn-s-sm " title="3">最近30天</a>
				<style>
.input-group .input-group-addon>i {
	font-size: 11px;
	line-height: 11px;
	font-weight: bold;
	color: #666;
}

.divFloat {
	float: left;
	width: 20%;
	text-align: center;
	border-right: 1px solid #ccc;
	border-bottom: 1px solid #ccc;
	padding: 8px 0;
	color: #555;
}

.portlet.box>.borderLeft {
	padding: 0;
	margin: 10px;
	border-top: 1px solid #ccc !important;
	border-left: 1px solid #ccc !important;
}

.portlet>.borderLeft {
	overflow: hidden;
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

.input-noneTable.active .spanActive, .input-noneTable.active .inputActive
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
			<input type="hidden" name="companyId" id="companyId">
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
						companyId="${listCompanyName[0].companyId }" type="button"
						id="companyName">${listCompanyName[0].companyName }</button>
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
				</div>
				<button onclick="toexcel()" type="button" class="btn btn-sm green"
					style="margin-left: 15px; float: left;height: 31px;">
					<i class="fa fa-share"></i> 导出(只支持月份导出)
				</button>
				<span class="m-l m-lStyle "></span>
			</div>
		</div>
	</div>
</div>

<div class="portlet-body   companyMemberCountHtml borderLeft">
	<div class="divFloat">
		成交额(用户已支付):<span class="m-l m-lStyle " id="turnover"></span>
	</div>
	<div class="divFloat">
		付款订单数:<span class="m-l m-lStyle " id="placeOrder"></span>
	</div>
	<div class="divFloat">
		下单人数:<span class="m-l m-lStyle " id="aleradyPay"></span>
	</div>
	<div class="divFloat">
		总订单数:<span class="m-l m-lStyle " id="allOrder"></span>
	</div>
</div>

<div id="terms" style="clear:both; padding-top:20px;">
	<a href="javascript:getCharts(5,'turnover','成交额')"
		class="btn btn-default btn-sm btn-s-sm smm5 active" title="turnover">
		成交额</a><a href="javascript:getCharts(6,'aleradyPay','付款订单数')"
		class="btn btn-default smm6 btn-sm btn-s-sm " title="aleradyPay">付款订单数</a><a
		href="javascript:getCharts(7,'placeOrder','下单人数')"
		class="btn btn-default smm7 btn-sm btn-s-sm " title="placeOrder">下单人数</a>
	<a href="javascript:getCharts(8,'allOrder','总订单数')"
		class="btn btn-default smm8 btn-sm btn-s-sm " title="allOrder">总订单数</a>
</div>
<div id="main" style="height:500px;"></div>

<jsp:include page="../foot.jsp"></jsp:include>
<script src="${SHOPDOMAIN}/js/echarts/dist/echarts.js"></script>
<script>
	var nowday = "";
	var myChart;
	function checkCompanyId(id, kthis) {
		$(".isCheckedd").attr("companyId", id).html($(kthis).html());
		if (id == 0) {

			$("#companyId").val("");
		} else {
			$("#companyId").val(id);
		}
		getCharts(5, 'turnover', '成交额');
		var dateTerms = $("#dateTerms").find(".active").attr("title");
		if (dateTerms == 1) {
			getAllInfoFun1();
		} else {
			if (dateTerms == null || dateTerms == undefined) {

				getAllInfoFun2(4, $(".form_datetime").val());
			} else {

				getAllInfoFun2(dateTerms, "");
			}
		}
	}

	//导出excel
	function toexcel() {

		window
				.open("${SHOPDOMAIN}/system/statistic/transactionToExcel.html?times="
						+ $(".form_datetime").val()
						+ "&companyName="
						+ $("#companyName").text()
						+ "&companyId="
						+ $("#companyId").val());
	}

	function getAllInfoFun1() {
		var comId = $("#companyId").val();

		$.ajax({
			url : "${SHOPDOMAIN}/system/statistic/transactionLastDayAll.html",
			type : "post",
			dataType : "json",
			data : {
				companyId : comId
			},
			success : function(data) {

				$("#allOrder").text(data.allOrder[0].count);
				$("#placeOrder").text(data.placeOrder[0].count);
				$("#aleradyPay").text(data.aleradyPay[0].count);
				if (data.turnover[0] == null) {
					$("#turnover").text(0);
				} else {
					$("#turnover").text(data.turnover[0].count);
				}
			}
		});

	}
	function getAllInfoFun2(types, times) {
		var comId = $("#companyId").val();

		$.ajax({
			url : "${SHOPDOMAIN}/system/statistic/transactionRangeDayAll.html",
			type : "post",
			dataType : "json",
			data : {
				type : types,
				companyId : comId,
				times : times,
			},
			success : function(data) {

				$("#allOrder").text(data.allOrder[0].count);
				$("#placeOrder").text(data.placeOrder[0].count);
				$("#aleradyPay").text(data.aleradyPay[0].count);
				if (data.turnover[0] == null) {
					$("#turnover").text(0);
				} else {
					$("#turnover").text(data.turnover[0].count);
				}
			}
		});

	}

	$(function() {

		getAllInfoFun1();
		require.config({
			paths : {
				echarts : '${SHOPDOMAIN}/js/echarts/dist'
			}
		});
		require([ 'echarts', 'echarts/chart/line', 'echarts/chart/bar' ],
				function(ec) {
					myChart = ec.init(document.getElementById('main'));
					/*  myChart.showLoading({
						text : '正在努力的读取数据中...', //loading话术
					}); 
					 */
					getCharts(5, 'turnover', '成交额');

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

		$(".form_datetime").datepicker().on("hide", function(e) {
			$(".sm1").removeClass("active");
			$(".sm2").removeClass("active");
			$(".sm3").removeClass("active");
			$(this).parent().addClass("active");
			if ("" == $(".form_datetime").val()) {
				$(".form_datetime").val(nowday);
			}
			myChart.clear();
			//fun2(type,"",terms,"总订单数");
			var terms = ($("#terms").find(".active").attr("title"));
			fun2(4, $(".form_datetime").val(), terms, "总订单数");
			getAllInfoFun2(4, $(".form_datetime").val());
		});

	});

	function getCharts(type, terms, title) {

		$(".sm" + type).addClass("active").siblings().removeClass("active");
		$(".input-prepend").removeClass("active");
		if (type == 5) {
			myChart.clear();
			$(".smm" + type).addClass("active").siblings()
					.removeClass("active");
			var dateTerms = $("#dateTerms").find(".active").attr("title");
			if (dateTerms == 1) {
				fun1("", terms, title);
			} else {
				var title = ($("#terms").find(".active").text());
				if (dateTerms == null || dateTerms == undefined) {

					fun2(4, $(".form_datetime").val(), terms, title);
				} else {
					fun2(dateTerms, "", terms, title);
				}
			}

		}
		if (type == 6) {
			$(".smm" + type).addClass("active").siblings()
					.removeClass("active");
			myChart.clear();
			var dateTerms = $("#dateTerms").find(".active").attr("title");
			if (dateTerms == 1) {
				fun1("", terms, title);
			} else {

				var title = ($("#terms").find(".active").text());
				if (dateTerms == null || dateTerms == undefined) {

					fun2(4, $(".form_datetime").val(), terms, title);
				} else {
					fun2(dateTerms, "", terms, title);
				}
			}
		}
		if (type == 7) {
			$(".smm" + type).addClass("active").siblings()
					.removeClass("active");
			myChart.clear();
			var dateTerms = $("#dateTerms").find(".active").attr("title");
			if (dateTerms == 1) {
				fun1("", terms, title);
			} else {

				var title = ($("#terms").find(".active").text());
				if (dateTerms == null || dateTerms == undefined) {

					fun2(4, $(".form_datetime").val(), terms, title);
				} else {
					fun2(dateTerms, "", terms, title);
				}
			}
		}
		if (type == 8) {
			$(".smm" + type).addClass("active").siblings()
					.removeClass("active");
			myChart.clear();

			var dateTerms = $("#dateTerms").find(".active").attr("title");
			if (dateTerms == 1) {
				fun1("", terms, title);
			} else {
				var title = ($("#terms").find(".active").text());
				if (dateTerms == null || dateTerms == undefined) {
					fun2(4, $(".form_datetime").val(), terms, title);
				} else {
					fun2(dateTerms, "", terms, title);
				}
			}
		}

		if (type == 1) {
			getCharts(5, 'turnover', '成交额');
			getAllInfoFun1();
		}

		if (type == 2) {

			var terms = ($("#terms").find(".active").attr("title"));
			var title = ($("#terms").find(".active").text());
			$(".sm" + type).addClass("active").siblings().removeClass("active");
			myChart.clear();

			fun2(type, "", terms, title);
			getAllInfoFun2(type, "");

		}
		if (type == 3) {
			$(".sm" + type).addClass("active").siblings().removeClass("active");
			myChart.clear();
			var title = ($("#terms").find(".active").text());
			var terms = ($("#terms").find(".active").attr("title"));
			$(".sm" + type).addClass("active").siblings().removeClass("active");
			myChart.clear();

			fun2(type, "", terms, title);
			getAllInfoFun2(type, "");
		}
	}

	function fun1(companyId, terms, title) {
		var comId = $("#companyId").val();

		$
				.ajax({
					url : "${SHOPDOMAIN}/system/statistic/transactionLastDay.html",
					type : "post",
					dataType : "json",
					data : {
						"companyId" : comId,
						"terms" : terms
					},
					async : false,
					success : function(data) {

						var text = new Array();
						text[0] = title;
						var date = new Array();
						var count = new Array();
						if (data.countList[0] == null
								|| data.countList[0] == undefined) {

							count[0] = 0;
							date[0] = 0;
						} else {

							date[0] = data.countList[0].ADDTIME;

							count[0] = data.countList[0].count;
						}
						var option = {
							title : {
								text : title,
							},
							tooltip : {
								trigger : 'axis'
							},
							legend : {
								data : text,
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
								data : date,
							} ],
							yAxis : [ {
								type : 'value',
								axisLabel : {
									formatter : '{value}'
								}
							} ],
							series : [ {
								name : title,
								type : 'line',
								data : count,
								markPoint : {
									data : [ {
										type : 'max',
										name : '最大值'
									},

									]
								},
								markLine : {
									data : [ {
										type : 'average',
										name : '平均值'
									} ]
								}
							} ]
						};
						myChart.setOption(option);
					}

				});

	}
	function fun2(types, times, terms, title) {

		var comId = $("#companyId").val();
		$
				.ajax({
					url : "${SHOPDOMAIN}/system/statistic/transactionRangeDay.html",
					type : "post",
					async : false,
					dataType : "json",
					data : {
						type : types,
						times : times,
						companyId : comId,
						terms : terms,
					},
					success : function(data) {

						var text = new Array();
						text[0] = title;
						var dated = new Array();
						var counts = new Array();

						$.each(data.countList, function(i, n) {

							dated[i] = n.ADDTIME;
							counts[i] = n.count;
						});

						if (data.countList[0] == null
								|| data.countList[0] == undefined) {

							counts[0] = 0;
							dated[0] = 0;
						}

						var option = {
							title : {
								text : title,
								subtext : ''
							},
							tooltip : {
								trigger : 'axis'
							},
							legend : {
								data : text
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
								data : dated
							} ],
							yAxis : [ {
								type : 'value',
								axisLabel : {
									formatter : '{value} '
								}
							} ],
							series : [ {
								name : title,
								type : 'line',
								data : counts,
								markPoint : {
									data : [

									]
								},
								markLine : {
									data : [ {
										type : 'average',
										name : '平均值'
									} ]
								}
							},

							]
						};
						myChart.setOption(option);

					}
				});

	}
</script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-daterangepicker/moment.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>


