<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- END PAGE CONTENT-->
</div>
</div>
<!-- END CONTENT -->
<!-- BEGIN QUICK SIDEBAR -->
<a href="javascript:;" class="page-quick-sidebar-toggler"><i
	class="icon-close"></i></a>
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<div class="page-footer">
	<div class="page-footer-inner">2016 &copy; 技术支持 xxx信息技术有限公司</div>
	<div class="scroll-to-top">
		<i class="icon-arrow-up"></i>
	</div>
</div>
<!-- END FOOTER -->
<!--[if lt IE 9]>
<script src="${SHOPDOMAIN}/system/theme/assets/global/plugins/respond.min.js"></script>
<script src="${SHOPDOMAIN}/system/theme/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery-migrate.min.js"
	type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery-ui/jquery-ui.min.js"
	type="text/javascript"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
	type="text/javascript"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
	type="text/javascript"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery.blockui.min.js"
	type="text/javascript"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery.cokie.min.js"
	type="text/javascript"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/uniform/jquery.uniform.min.js"
	type="text/javascript"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"
	type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/scripts/metronic.js"
	type="text/javascript"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/admin/layout/scripts/layout.js"
	type="text/javascript"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/admin/layout/scripts/quick-sidebar.js"
	type="text/javascript"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/admin/layout/scripts/demo.js"
	type="text/javascript"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootbox/bootbox.min.js"
	type="text/javascript"></script>
<script src="${SHOPDOMAIN}/system/js/common.js" type="text/javascript"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery-validation/js/jquery.validate.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery-validation/js/additional-methods.min.js"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/admin/pages/scripts/form-validation.js"></script>
<script src="${SHOPDOMAIN}/system/js/jquery.form.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/bootstrap-select/bootstrap-select.min.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript"
	src="${SHOPDOMAIN}/system/theme/assets/global/plugins/jquery-multi-select/js/jquery.multi-select.js"></script>
<script
	src="${SHOPDOMAIN}/system/theme/assets/admin/pages/scripts/components-dropdowns.js"></script>
<script>
	jQuery(document).ready(function() {
		// initiate layout and plugins
		Metronic.init(); // init metronic core components
		Layout.init(); // init current layout
		QuickSidebar.init(); // init quick sidebar
		Demo.init(); // init demo features
	});
	$(function() {

		$(".page-sidebar-menu #menus${typeid}").addClass("active open");
		$("#menus${typeid}").children().eq(0).find('.arrow').remove();
		$("#menus${typeid}")
				.children()
				.eq(0)
				.append(
						"<span class='selected'></span> <span class='arrow open'></span>");

	})
</script>
</body>
<!-- END BODY -->
</html>