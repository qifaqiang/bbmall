<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			系统定时任务列表 <small> 列表</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>系统定时任务管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span>系统定时任务列表</span></li>
			</ul>
		</div>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<form action="list.html" method="post" id="list">
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
						<button class="btn btn-primary"
							onclick="javascript:window.location.href='add.html'"
							type="button">添加任务</button>
						<input type="text" class="form-control w_mobile "
							value="${ objs.name}" style="width: 180px; float: right;"
							name="name" placeholder="" id="mobile"> 
							<span style="float:right;height:35px;line-height:35px">名称:&nbsp;</span>
							<span
							class="input-group-btn"> <a href="javascript:;"
							class="btn green" id="search"> <i class="fa fa-search"></i>搜索
						</a>
						</span>
					</div>
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>任务名称</th>
									<th>任务分组名称标识</th>
									<th>创建时间</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty objList}">
										<c:forEach items="${objList}" var="scheduleJob" varStatus="vs">

											<tr class="">
												<td>${vs.index +1}</td>
												<td>${scheduleJob.name}</td>
												<td>
													${scheduleJob.jobGroupName }
												</td>
												<td>
													${scheduleJob.createTime }
												</td>
												<td>
													<c:if test="${scheduleJob.jobStatus == '0' }">
														停止
													</c:if>
													<c:if test="${scheduleJob.jobStatus == '1' }">
														正在运行
													</c:if>
													<c:if test="${scheduleJob.jobStatus == '2' }">
														暂停
													</c:if>
												</td>
												<td style="width: 25%">
												<a class="btn default btn-xs purple"
												href="javascript:void(0)"
												onclick="editProductSpec(${scheduleJob.jobId})"> <i
													class="fa fa-edit"></i> 编辑
												</a>
												<c:if test="${scheduleJob.jobStatus == '0' }">
													<a idd="${scheduleJob.jobId }" 
														class="btn default btn-xs black restartbtn"
														title="${scheduleJob.name}" href="javascript:void(0)">
															<i class="fa fa-trash-o"></i> 重启
													</a>
												</c:if>
												<c:if test="${scheduleJob.jobStatus == '1' }">
													<a idd="${scheduleJob.jobId }" 
														class="btn default btn-xs black pausebtn"
														title="${scheduleJob.name}" href="javascript:void(0)">
															<i class="fa fa-trash-o"></i> 暂停
													</a>
												</c:if>
												<c:if test="${scheduleJob.jobStatus == '2' }">
													<a idd="${scheduleJob.jobId }" 
														class="btn default btn-xs black resumebtn"
														title="${scheduleJob.name}" href="javascript:void(0)">
															<i class="fa fa-trash-o"></i> 恢复
													</a>
												</c:if>
												<a id="${scheduleJob.jobId }" 
													class="btn default btn-xs black listContact_removeObjectSignle"
													title="${scheduleJob.name}" href="javascript:void(0)">
														<i class="fa fa-trash-o"></i> 删除
												</a></td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr class="main_info">
											<td colspan="6">没有相关数据</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
						<div class="page_and_btn">
							<div></div>
							${objs.page.pageStr }
						</div>
					</div>
				</div>
			</div>
			<!-- END SAMPLE TABLE PORTLET-->
		</div>
	</div>
</form>
<script>
	function editProductSpec(id) {
		window.location.href = "update.html?id=" + id;
	}
	//设置用户为jinyon
	$("#search").click(function() {
		$("#list").submit();
	});
	$(function(){
		
		//暂停按钮的操作
		$(".pausebtn").click(function(){
			var jobId=$(this).attr("idd");
			var title=$(this).attr("title");
			 
			 bootbox.dialog({
				 message: "确定要暂停这个"+title+"?",
				 title: "提示",
				 buttons: {
				 success: {
				 label: "取消!",
				 className: "white",
				 callback: function() {
				 }
				 },
				 main: {
				 label: "确定!",
				 className: "blue",
				 callback: function() {
					 var DATA = '';
						DATA = '{"CTIDS":"'+jobId+'","EXCLUSION":"false"}';
						$.ajax({
							url: "pauseone.html",
							type: "post",
							data: {
								"DATA": DATA
							},
							beforeSend:function(){
								$('body').append('<div class="tips_cover"></div><div class="cover_content"><div class="coverIng"><span>正在暂停'+title+'，请稍候...</span></div></div>');
							},
							success: function(data){
								if (eval("("+data+")").flag) {
									$('.cover_content').empty().append('<div class="cover_after"><span>操作成功'+title+'~</span></div>');
									window.location.href=window.location.href;
								}else{
									$('.cover_content').empty().append('<div class="cover_after"><span>操作失败'+title+'~</span></div>');
									window.location.href=window.location.href;
								}
							
							}
						});
				 	}
				 }
				 } 
			 });
		
		});
		
		//恢复按钮的操作
		$(".resumebtn").click(function(){
			var jobId=$(this).attr("idd");
			var title=$(this).attr("title");
			 
			 bootbox.dialog({
				 message: "确定要恢复这个"+title+"?",
				 title: "提示",
				 buttons: {
				 success: {
				 label: "取消!",
				 className: "white",
				 callback: function() {
				 }
				 },
				 main: {
				 label: "确定!",
				 className: "blue",
				 callback: function() {
					 var DATA = '';
						DATA = '{"CTIDS":"'+jobId+'","EXCLUSION":"false"}';
						$.ajax({
							url: "resumeone.html",
							type: "post",
							data: {
								"DATA": DATA
							},
							beforeSend:function(){
								$('body').append('<div class="tips_cover"></div><div class="cover_content"><div class="coverIng"><span>正在恢复'+title+'，请稍候...</span></div></div>');
							},
							success: function(data){
								if (eval("("+data+")").flag) {
									$('.cover_content').empty().append('<div class="cover_after"><span>恢复成功'+title+'~</span></div>');
									window.location.href=window.location.href;
								}else{
									$('.cover_content').empty().append('<div class="cover_after"><span>恢复失败'+title+'~</span></div>');
									window.location.href=window.location.href;
								}
							}
						});
				 	}
				 }
				 } 
			 });
		});
		
		//重启按钮的操作
		$(".restartbtn").click(function(){
			var jobId=$(this).attr("idd");
			var title=$(this).attr("title");
			 
			 bootbox.dialog({
				 message: "确定要重启这个"+title+"?",
				 title: "提示",
				 buttons: {
				 success: {
				 label: "取消!",
				 className: "white",
				 callback: function() {
				 }
				 },
				 main: {
				 label: "确定!",
				 className: "blue",
				 callback: function() {
					 var DATA = '';
						DATA = '{"CTIDS":"'+jobId+'","EXCLUSION":"false"}';
						$.ajax({
							url: "restartone.html",
							type: "post",
							data: {
								"DATA": DATA
							},
							beforeSend:function(){
								$('body').append('<div class="tips_cover"></div><div class="cover_content"><div class="coverIng"><span>正在重启'+title+'，请稍候...</span></div></div>');
							},
							success: function(data){
								if (eval("("+data+")").flag) {
									$('.cover_content').empty().append('<div class="cover_after"><span>重启成功'+title+'~</span></div>');
									window.location.href=window.location.href;
								}else{
									$('.cover_content').empty().append('<div class="cover_after"><span>重启失败'+title+'~</span></div>');
									window.location.href=window.location.href;
								}
							}
						});
				 	}
				 }
				 } 
			 });
		});
	})
</script>
<!-- END PAGE CONTENT-->
<script src="${SHOPDOMAIN}/system/js/listContact.js"></script>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>