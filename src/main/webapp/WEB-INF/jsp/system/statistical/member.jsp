<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../top.jsp"></jsp:include>
 
 
<link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-datepicker/css/datepicker3.css"/>
     <!-- BEGIN PAGE HEADER-->
<h3 class="page-title">会员统计  </h3>
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
		<div class="form-group" style="float:left">
			<div class="btn-group" style="float:left">
				<a href="javascript:getCharts(1)"
					class="btn btn-default btn-sm btn-s-sm sm1 active">昨天</a><a
					href="javascript:getCharts(2)"
					class="btn btn-default sm2 btn-sm btn-s-sm ">最近7天</a><a
					href="javascript:getCharts(3)"
					class="btn btn-default sm3 btn-sm btn-s-sm ">最近30天</a>
									<style>
.input-group .input-group-addon>i {
	font-size: 11px;
	line-height: 11px;
	font-weight: bold;
	color: #666;
}

.spanActive {
	height: 30px;
	border-radius:0;
	padding: 6px 10px;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1), 0 1px 2px rgba(0, 0, 0, 0.18);
}

.input-group .inputActive {
	width: 180px;
	height: 30px;
	line-height:11px;
	padding:7px 10px 5px 12px;
	font-size: 11px;
	font-weight: bold;
	cursor: pointer;
	float:left;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1), 0 1px 2px rgba(0, 0, 0, 0.18);
}

.inputActive:hover{
	box-shadow: 0 3px 6px rgba(0, 0, 0, 0.2), 0 3px 6px rgba(0, 0, 0, 0.26);
	background-color:#d4d4d4;
	border-color:#d4d4d4;
	border-radius:5px;
}
.input-noneTable.active .spanActive,.input-noneTable.active .inputActive {
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
.input-noneTable{
	display:inline-block;
}
.spanActive{
	width:auto;
	display:inline-block;
	float:left;
}
</style>
				

</div>
<div class="input-prepend input-group input-noneTable" style="">
		<span class="add-on  input-group-addon spanActive input-group-btn" style=""><i
			class="glyphicon glyphicon-calendar fa fa-calendar ">选择月份</i></span> <input
			type="text" style="" name="ttime" id=""
				class="form-control form_datetime inputActive "
				readonly="readonly" value="2017-01"> <span class="m-l m-lStyle "></span>
		</div>

</div>
</div>
</div>                    
<div id="main" style="height:500px;"></div>  	
<jsp:include page="../foot.jsp"></jsp:include>
<script src="${SHOPDOMAIN}/js/echarts/dist/echarts.js"></script>
<script>
var nowday="";
var myChart;
$(function(){
	require.config({
		paths : {
			echarts : '${SHOPDOMAIN}/js/echarts/dist'
		}
	});
	require(['echarts', 'echarts/chart/line','echarts/chart/bar'],function(ec){
		myChart = ec.init(document.getElementById('main'));
		 fun1();
	});
	
	//日期
	$(".form_datetime").datepicker(
			{
				format: 'yyyy-mm', 
				weekStart: 1, 
				autoclose: true, 

				startView: 2, 
				maxViewMode: 1,
				minViewMode:1,
				forceParse: false,  
			}); 
	 
	$(".form_datetime").datepicker().on("hide",function(e){
		$(".sm1").removeClass("active");$(".sm2").removeClass("active");$(".sm3").removeClass("active");
		$(this).parent().addClass("active");
		if(""==$(".form_datetime").val()){
			$(".form_datetime").val(nowday);
		}
		myChart.clear();
		fun2(4,$(".form_datetime").val());
	});
	 
});

//获取图像
function getCharts(type) {
	$(".sm" + type).addClass("active").siblings().removeClass("active");
	if (type == 1) {
		myChart.clear();
	 
		fun1();
	}
	if (type == 2) {
		myChart.clear();
		 
		fun2(type);
	}
	if (type == 3) {
		myChart.clear();
		 
		fun2(type);
	}
}

//图像1
function fun1(){
	$.ajax({
		url : "${SHOPDOMAIN}/system/statistic/memberLastOneDay.html",
		type : "post",
		dataType : "json",
		async:false,
		success : function(data) {
			 var counts=new Array();
			 if(data.sql[0]==null||data.sql[0]==undefined){
					counts[0]=0;
				}else{
					counts[0]=data.sql[0].counts;
				}
			 
			var	option = {
				    title : {
				        text: '会员统计',
				        subtext: ''
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['会员人数' ]
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : ['']
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            axisLabel : {
				                formatter: '{value} '
				            }
				        }
				    ],
				    series : [
				        {
				            name:'会员人数',
				            type:'line',
				            data:counts,
				            markPoint : {
				                data : [
				                   
				                   
				                ]
				            },
				            markLine : {
				                data : [
				                    {type : 'average', name: '平均值'}
				                ]
				            }
				        },
				         
				    ]
				};
			myChart.setOption(option);
		}
	
	});
	
}

//图像2
function fun2(types,times){
	$.ajax({
		url : "${SHOPDOMAIN}/system/statistic/memberRangeDay.html",
		type:"post",
		async:false,
		dataType:"json",
		data : {
			type : types,
			times : times
		},
		success:function(data){
		 
		 var dated=new Array();
		 var counts=new Array();
		 $.each(data.sql,function(i,n){
			 
			 dated[i]=n.ADDTIME;
	         counts[i]=n.counts;
		 });
		  
		 if(data.sql[0]==null||data.sql[0]==undefined){
			 dated[0]=0;
			 counts[0]=0;
		 }
		 
			var	option = {
				    title : {
				        text: '会员统计',
				        subtext: ''
				    },
				    tooltip : {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['会员人数' ]
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : dated
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            axisLabel : {
				                formatter: '{value} '
				            }
				        }
				    ],
				    series : [
				        {
				            name:'会员人数',
				            type:'line',
				            data:counts,
				            markPoint : {
				                data : [
				                   
				                   
				                ]
				            },
				            markLine : {
				                data : [
				                    {type : 'average', name: '平均值'}
				                ]
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
<script type="text/javascript" src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	
