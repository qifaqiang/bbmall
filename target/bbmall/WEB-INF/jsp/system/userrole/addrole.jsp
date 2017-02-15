<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${SHOPDOMAIN}/system/theme/assets/global/plugins/jstree/dist/themes/default/style.min.css" />

<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			添加角色 <small>选择权限组成角色</small>
		</h3>
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>角色管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span>添加角色</span></li>
			</ul>
		</div>
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
					action="saverole.html" novalidate="novalidate">
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
							<label class="control-label col-md-3">角色名称 <span
								class="required"> * </span>
							</label>
							<div class="col-md-4">
								<input type="text" class="form-control w_require"
									value="${rolename.name }" name="name" style="width:500px;">
								<input type="hidden" value="${rolename.id }" name="id">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3">角色权限 <span
								class="required"> * </span>
							</label>
							<div class="col-md-4" style="width: 700px;">

								<div class="portlet-body">
									<div class="tree-demo jstree jstree-2 jstree-default"
										id="tree_2" role="tree" aria-activedescendant="j2_2"></div>
								</div>
							</div>
						</div>
						<input type="hidden" name="rolelist" id="rolelist">
					</div>
					<div class="form-actions fluid">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn green rollesubmit" type="button">提交</button>
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
<script>
	$(function() {
		//newRoleAddValidate();
		//addRoleValidate();//个人设置验证
		$(".rollesubmit").live("click", function(e) {

			var rolelist = '';
			$(".jstree-undetermined").each(function() {
				rolelist += $(this).parent().parent().attr("id") + ",";
			});

			$(".jstree-clicked").each(function() {
				rolelist += $(this).parent().attr("id") + ",";
			});
			$("#rolelist").val(rolelist);
			/*var rolesize = $("input[type=checkbox]:checked").length;*/
			if (rolelist != '') {
				$("#form_config").submit();
			} else {
				bootbox.dialog({
					message : "权限不能为空",
					title : "提示",
					buttons : {
						success : {
							label : "取消!",
							className : "white",
							callback : function() {
							}
						},
						main : {
							label : "确定!",
							className : "blue",
							callback : function() {
							}
						}
					}
				});
				//
			}
		});
	})
</script>

<!-- END PAGE CONTENT-->
<jsp:include page="../foot.jsp"></jsp:include>
<script src="${SHOPDOMAIN}/system/js/ui-tree.js"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jstree/dist/jstree.min.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-markdown/lib/markdown.js"></script>
<script>
	jQuery(document).ready(function() {
		Demo.init(); // init demo features
		FormValidation.init();
		var listTemp = '[${menu2 }]';
		var handleSample2 = function() {
			$('#tree_2').jstree({
				'plugins' : [ "checkbox", "types" ],
				'core' : {
					"themes" : {
						"responsive" : false
					},
					'data' : [ ${menu2 } ]
				},
				"types" : {
					"default" : {
						"icon" : "fa  fa-tag  icon-lg"
					}
				}
			});
		}
		handleSample2();
	});
</script>