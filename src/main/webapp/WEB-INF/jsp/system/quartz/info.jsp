<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<style>
.green1 {padding: 10px 17px 9px}
.form-control, .help-block {display: inline;}
em {font-style: normal;}
</style>
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			系统管理 <small>定时任务信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					系统管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 定时任务信息 </a></li>
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
					<i class="fa fa-reorder"></i>信息
				</div>
				<div class="tools"></div>
			</div>
			<div class="portlet-body form">
				<!-- BEGIN FORM-->
				<form class="form-horizontal" id="form_config" method="post"
					enctype="multipart/form-data" action="save.html"
					novalidate="novalidate">
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
							<label class="control-label col-md-3">名称 ： <span class="required"> * </span> </label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${obj.name }" name="name"> <input type="hidden"
									value="${obj.jobId }" name="jobId">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">任务分组： <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<c:choose>
									<c:when test="${obj.jobId != null && obj.jobId != ''  }">
										<select class="form-control w_require" name="jobGroupName">
											<option value="${obj.jobGroupName }" >${obj.jobGroupName }</option>
										</select>
									</c:when>
									<c:otherwise>
										<select class="form-control w_require" name="jobGroupName">
											<option value="AutoAcceptOrderJob" <c:if test="${obj.jobGroupName =='AutoAcceptOrderJob' }"> selected="selected" </c:if> >自动确认收获定时任务</option>
											<option value="AutoCancelOrderJob" <c:if test="${obj.jobGroupName =='AutoCancelOrderJob' }"> selected="selected" </c:if> >自动取消订单定时任务</option>
											<option value="AutoClearSmsJob" <c:if test="${obj.jobGroupName =='AutoClearSmsJob' }"> selected="selected" </c:if> >自动清理过期的验证码定时任务</option>
											<option value="AutoCouponsoverdueJob" <c:if test="${obj.jobGroupName =='AutoCouponsoverdueJob' }"> selected="selected" </c:if> >优惠券自动失效定时任务</option>
											<option value="AutoOverOrderJob" <c:if test="${obj.jobGroupName =='AutoOverOrderJob' }"> selected="selected" </c:if> >自动完成订单的定时任务</option>
										</select>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">执行时间 ： <span class="required"> * </span> </label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${obj.cronExpression }" name="cronExpression">
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3">是否开启 ： </label>
							<div class="col-md-4">
								<div class="btn-group" data-toggle="buttons">
									<label
										class="btn btn-default <c:if test="${obj.jobStatus==1||empty obj.jobStatus }">active</c:if>">
										<input type="radio"
										<c:if test="${obj.jobStatus==1||empty obj.jobStatus }"> checked="checked"</c:if>
										value="1" name="jobStatus" class="toggle"> 立即开启
									</label> <label
										class="btn btn-default <c:if test="${obj.jobStatus ==0 || obj.jobStatus==2  }">active</c:if>">
										<input type="radio"
										<c:if test="${obj.jobStatus ==0 || obj.jobStatus==2 }"> checked="checked"</c:if>
										name="jobStatus" value="2" class="toggle"> 暂不开启
									</label>
								</div>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-md-3">任务描述： <span class="required"> * </span> </label>
							<div class="col-md-4">
								<textarea class="form-control w_require" rows="5" cols="5" name="description">${obj.description }</textarea>
							</div>
						</div>
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green rollesubmit" type="submit" >提交</button>
							<button class="btn grey-cascade sys_go_back" type="button" >返回</button>
						</div>
					</div>
				</form>
				<!-- END FORM-->
			</div>
		</div>
		<!-- END VALIDATION STATES-->
	</div>
</div>
<script type="text/javascript">
$(function(){
	$(".rollesubmit").live("click", function(e) {
	});
});
</script>
<script>
	jQuery(document).ready(function() {
		FormValidation.init();
	});
</script>
<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
