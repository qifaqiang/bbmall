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
			角色控制 <small>选择权限组成角色</small>
		</h3> 
		<div class="page-bar">
			<ul class="page-breadcrumb">
				<li><i class="fa fa-home"></i> <span>角色管理</span> <i
					class="fa fa-angle-right"></i></li>
				<li><span>角色控制 </span></li>
			</ul>
		</div>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
<form action="list.html" method="post">
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
				<button class="btn btn-primary listContact_add"  t_pid="0" t_level="0"
					type="button">添加一級权限</button>
				<br> <br>
					<div class="table-responsive">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>名称</th>
									<th>排序</th>
									<th>是否展示</th>
									<th>url</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty objList}">
										<c:forEach items="${objList}" var="product" varStatus="vs">
											<tr class="s_${product.id }">
												<td><i class="fa ${product.imgUrl }"></i>&nbsp;${product.name}</td>
												<td>${product.ord}</td>
												<td>${product.visable==1?"展示":"隐藏" }</td>
												<td>${product.linkUrl}</td>
												<td><a
													class="btn default btn-xs purple listContact_update"
													t_name="${product.name }" t_id="${product.id }" t_ord="${product.ord }" t_visable="${product.visable }" t_linkUrl="${product.linkUrl }" t_imgUrl="${product.imgUrl }" t_pid="${product.parentId }" t_level="1"
													href="javascript:void(0)"> <i class="fa fa-edit"></i>
														编辑
													</a> &nbsp; &nbsp; 
													<a class="btn default btn-xs red listContact_add" t_pid="${product.id }" t_level="1"
														href="javascript:void(0)"> <i class="fa fa-search"></i>
														添加
													</a> &nbsp; &nbsp; 
													<a id="${product.id }" class="btn default btn-xs black listContact_removeObjectSignle"
														title="菜单" href="javascript:void(0)"> <i
														class="fa fa-trash-o"></i> 删除
													</a>
												</td>
											</tr>
											<c:forEach items="${product.sysMenuList}" var="sub">
												<tr class="ss_${product.id } s_${sub.id }">
													<td>&nbsp;|——${sub.name}</td>
													<td>${sub.ord}</td>
													<td>${sub.visable==1?"展示":"隐藏" }</td>
													<td>${sub.linkUrl}</td>
													<td><a
														class="btn default btn-xs purple listContact_update"
														t_name="${sub.name }" t_id="${sub.id }" t_ord="${sub.ord }" t_visable="${sub.visable }" t_linkUrl="${sub.linkUrl }" t_imgUrl="${sub.imgUrl }" t_pid="${sub.parentId }" t_level="2"
														href="javascript:void(0)"> <i class="fa fa-edit"></i>
															编辑
														</a> &nbsp; &nbsp; 
														<a class="btn default btn-xs red listContact_add"  t_pid="${sub.id }" t_level="2"
																href="javascript:void(0)"> <i class="fa fa-search"></i>
																添加
														</a> &nbsp; &nbsp; 
														<a class="btn default btn-xs green listContact_look" t_id="${sub.id }" t_level="2"
															href="javascript:void(0)"> <i class="fa fa-search"></i>
															查看
														</a> &nbsp; &nbsp; 
														<a id="${sub.id }"
															class="btn default btn-xs black listContact_removeObjectSignle"
															title="菜单" href="javascript:void(0)"> <i
																class="fa fa-trash-o"></i> 删除
														</a>
													</td>
												</tr>
											</c:forEach>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr class="main_info">
											<td colspan="7">没有相关数据</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- END SAMPLE TABLE PORTLET-->
		</div>
	</div>
</form>
<script>
	var tempParentId=0;
	$(document).on("click",".listContact_update",function() {
						var id = $(this).attr("t_id");
						var name = $(this).attr("t_name");
						var visable = $(this).attr("t_visable");
						var linkUrl = $(this).attr("t_linkUrl");
						var imgUrl = $(this).attr("t_imgUrl");
						var ord = $(this).attr("t_ord")==""?"0":$(this).attr("t_ord");
						var pid = $(this).attr("t_pid");
						var level = $(this).attr("t_level");
						var tempLinkUrl=""
						var html = "图标(1级不可空):<input type='text' class='form-control t_imgUrl' value='"+imgUrl+"'><br>"
								+ "名称(不可空):<input type='text' class='form-control t_name' value='"+name+"'><br>"
								+ "排序:<input type='text' class='form-control t_ord' value='"+ord+"'><br>"
								+ "是否展现:<select class='form-control t_visable'><option value='1' "+(visable==1?"selected":"")+">是</option><option value='0' "+(visable==0?"selected":"")+">否</option></select><br>"
								+ "linkUrl(新增：add.html，编辑：update.html，删除：delone.html):<input type='text' class='form-control t_linkUrl' value='"+linkUrl+"'><br>"
								+ "pid:<input type='text'  disabled='disabled' class='form-control t_pid' value='"+pid+"'><br>";
						bootbox
								.dialog({
									message : html,
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
												tempParentId=$(".t_pid").val();
												$.ajax({
														url : "saveMenu.html",
														type : "post",
														dataType:"json",
														sync:false,
														data : {
															"id" : id,
															"imgUrl" : $(".t_imgUrl").val(),
															"name" : $(".t_name").val(),
															"ord" : $(".t_").val(),
															"visable" : $(".t_visable").val(),
															"linkUrl" : $(".t_linkUrl").val(),
															"parentId" : $(".t_pid").val()															
														},
														success : function(data) {
															if (data.type=="success") {
																if(tempParentId==0){
																	window.location.href = window.location.href;
																}else{
																	getSubInfo(tempParentId,1,tempParentId)
																}
															} else {
																alertError(data.content);
															}
														}
													});
											}
										}
									}
								});
					});
	
	$(document).on("click",".listContact_add",function() {
					var name = "";
					var visable =1;
					var linkUrl = "";
					var imgUrl = "";
					var ord = "0";
					var pid = $(this).attr("t_pid");
					var level = $(this).attr("t_level");
					var tempLinkUrl=""
					var html = "图标(1级不可空):<input type='text' class='form-control t_imgUrl' value='"+imgUrl+"'><br>"
							+ "名称(不可空):<input type='text' class='form-control t_name' value='"+name+"'><br>"
							+ "排序:<input type='text' class='form-control t_ord' value='"+ord+"'><br>"
							+ "是否展现:<select class='form-control t_visable'><option value='1' "+(visable==1?"selected":"")+">是</option><option value='0' "+(visable==0?"selected":"")+">否</option></select><br>"
							+ "linkUrl(新增：add.html，编辑：update.html，删除：delone.html):<input type='text' class='form-control t_linkUrl' value='"+linkUrl+"'><br>"
							+ "pid:<input type='text'  disabled='disabled' class='form-control t_pid' value='"+pid+"'><br>";
					bootbox
							.dialog({
								message : html,
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
											$.ajax({
													url : "saveMenu.html",
													type : "post",
													dataType:"json",
													sync:false,
													data : {
														"imgUrl" : $(".t_imgUrl").val(),
														"name" : $(".t_name").val(),
														"ord" : $(".t_").val(),
														"visable" : $(".t_visable").val(),
														"linkUrl" : $(".t_linkUrl").val(),
														"parentId" : $(".t_pid").val()															
													},
													success : function(data) {
														if (data.type=="success") {
															if(pid==0){
																window.location.href = window.location.href;
															}else{
																getSubInfo(pid,level,pid)
															}
														} else {
															alertError(data.content);
														}
													}
												});
										}
									}
								}
							});
				});
	$(document).on("click",".listContact_look",function() {
		getSubInfo($(this).attr("t_id"),$(this).attr("t_level"),$(this).attr("t_id"));
	});
	
	var isGet=true;
	function getSubInfo(parentId,level,tempSite){
		if(isGet){
			isGet=false;
			$(".ss_"+parentId).remove();
			$(".sss_"+parentId).remove();
			$.ajax({
					url : "getSubList.html",
					type : "post",
					dataType:"json",
					sync:false,
					data : {
						"parentId" : parentId														
					},
					success : function(data) {
							var html="";
							$.each(data,function(n,item){
								var tempClass="";
								var tempName="";
								var canLook=false;
								if(level==2){
									tempClass="sss_"+parentId+" s_"+item.id;
									tempName="&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;|——"+item.name;
									//canLook=true;
								}else if(level==1){
									tempClass="ss_"+parentId+" s_"+item.id;
									tempName="&nbsp;|——"+item.name;
									canLook=true;
								}
								html+="<tr class='"+tempClass+"'><td>"+tempName+"</td><td>"+item.ord+"</td><td>"+(item.visable==1?"展示":"隐藏")+"</td><td>"+item.linkUrl+"</td>"+
									"<td><a href='javascript:void(0)' t_pid='"+parentId+"' t_imgurl='' t_linkurl='"+item.linkUrl+"' t_visable='"+item.visable+"' t_ord='"+item.ord+"' t_id='"+item.id+"' t_name='"+item.name+"' class='btn default btn-xs purple listContact_update'> <i class='fa fa-edit'></i>"+
									"编辑</a> &nbsp; &nbsp; "+
									(canLook?"<a href='javascript:void(0)' t_pid='"+parentId+"' class='btn default btn-xs red listContact_add'> <i class='fa fa-search'></i>"+
									"添加</a> &nbsp; &nbsp; ":"")+
									(canLook?"<a href='javascript:void(0)' t_id='"+item.id+"' t_level='2' class='btn default btn-xs green listContact_look'> <i class='fa fa-search'></i>"+
									"查看</a> &nbsp; &nbsp; ":"")+
									"<a href='javascript:void(0)' title='分类' class='btn default btn-xs black listContact_removeObjectSignle' id='"+item.id+"'> <i class='fa fa-trash-o'></i> 删除"+
									"</a></td></tr>"
							});
							if(data==""){
								alertError("无数据");
							}
							
							$(".s_"+tempSite).after(html);
							//$(".ss_"+tempSite).after(html);
							isGet=true;
					}
				});	
		}
	}
	$(function(){
		
	});
</script>
<!-- END PAGE CONTENT-->
<script src="${SHOPDOMAIN}/system/js/listContact.js"></script>
<jsp:include page="../foot.jsp"></jsp:include>