<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../../top.jsp"></jsp:include>
<!-- END STYLE CUSTOMIZER -->
<!-- BEGIN PAGE HEADER-->
<style>
<!--
.table>tbody>tr>td {
	vertical-align: middle;
}
-->
</style>
<div class="row">
	<div class="col-md-12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			商品分类管理 <small>商品分类信息</small>
		</h3>
		<ul class="page-breadcrumb breadcrumb">
			<li><i class="fa fa-home"></i> <a href="javascript:void(0)">
					商品管理 </a> <i class="fa fa-angle-right"></i></li>
			<li><a href="javascript:void(0)"> 分类列表 </a></li>
		</ul>
		<!-- END PAGE TITLE & BREADCRUMB-->
	</div>
</div>
<!-- END PAGE HEADER-->
<!-- BEGIN PAGE CONTENT-->
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
						onclick="javascript:window.location.href='add.html'" type="button">添加商品分类</button>
					</span>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>分类名称</th>
								<th>PC首页信息</th>
								<th>WAP首页信息</th>
								<th>排序</th>
								<th>是否放置wap导航</th>
								<th>是否推荐</th>
								<th>录入时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty objList}">
									<c:forEach items="${objList}" var="product" varStatus="vs">
										<tr>
											<td>${product.name}&nbsp; <a id="${product.id }" type="1"
												class="btn default btn-xs green listContact_up"
												href="javascript:void(0)">收起 <i
													class="fa fa-angle-double-up "></i>
											</a></td>
											<td><img src="${USERIMGSRC }${product.pcPic}" width="50px" /></td>
											<td>是否可用：${product.isTop==1?"是":"否"}<br>描述：${product.wapDescn}<br>
												wap首页图片：<img src="${USERIMGSRC }${product.wapPic}" width="50px" /></td>
											<td>${product.sortn}</td>
											<td>${product.isTop==1?"是":"否"}</td>
											<td>${product.isRecommended==1?"是":"否"}</td>
											<td><fmt:formatDate value="${product.addtime}"
													type="both" pattern="yyyy/MM/dd HH:mm:ss" /></td>
											<td><a class="btn default btn-xs purple"
												href="javascript:void(0)"
												onclick="editProduct(${product.id})"> <i
													class="fa fa-edit"></i> 编辑
											</a> &nbsp; &nbsp;<a id="${product.id }"
												class="btn default btn-xs black"
												title="分类" href="javascript:void(0)"  onclick="deleteAllProduct(${product.id })"> </i> 删除
											</a></td>
										</tr>
										<c:forEach items="${product.sublist}" var="sub">
											<tr class="p_${product.id }">
												<td>&nbsp;&nbsp;&nbsp;&nbsp;|_____${sub.name}</td>
												<td>--</td>
												<td>--</td>
												<td>${sub.sortn}</td>
												<td>${product.isTop==1?"是":"否"}</td>
												<td>${product.isRecommended==1?"是":"否"}</td>
												<td><fmt:formatDate value="${sub.addtime}" type="both"
														pattern="yyyy/MM/dd HH:mm:ss" /></td>
												<td><a class="btn default btn-xs purple"
													href="javascript:void(0)" onclick="editProduct(${sub.id})">
														<i class="fa fa-edit"></i> 编辑
												</a> &nbsp; &nbsp;<a id="${sub.id }"
													class="btn default btn-xs black "
													title="分类" href="javascript:void(0)"  onclick="deleteProduct(${sub.id})"> <i
														class="fa fa-trash-o"></i> 删除
												</a></td>
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
					<div class="page_and_btn">
						<div></div>
						${obj.page.pageStr }
					</div>
				</div>
			</div>
		</div>
		<!-- END SAMPLE TABLE PORTLET-->
	</div>
</div>
<script>
	function editProduct(id) {
		window.location.href = "update.html?id=" + id;
	}
	function deleteProduct(id){
		$.ajax({
			url : "delone.html",
			type : "post",
			dataType : "json",
			async : false,//同步
			data : {
				"id" : id
			},
			success : function(data) {
				if (data.type == "success") {
					var obj=data.productList;
					var stringBuilder = [];
					var mes="该商品分类下无商品是否确认删除？";
					var j=0;
					$(obj).each( function(i, e){
						   //i为元素的索引，从0开始,
						  //e为当前处理的元素
						stringBuilder.push("商品名称："+e.name+"<br>");
						   j=j+i;
						});
					if(j!=0){
						 bootbox.dialog({
						        message: stringBuilder.join(""),
						        title: "提示：请先删除该商品分类下的商品",
						        buttons: {
						            success: {
						                label: "取消!",
						                className: "white",
						                callback: function() {}
						            },
						            main: {
						                label: "确定!",
						                className: "blue",
						                callback: function() {
						                }
						            }
						        }
						    });
					}else{
						 bootbox.dialog({
						        message: mes,
						        title: "提示：",
						        buttons: {
						            success: {
						                label: "取消!",
						                className: "white",
						                callback: function() {}
						            },
						            main: {
						                label: "删除!",
						                className: "blue",
						                callback: function() {
						                    $.ajax({
						                        url: "delete.html",
						                        type: "post",
						                        dataType: "json",
						                        async: false,//同步
						                        data: {
						                            "id": id
						                        },
						                        beforeSend:function(){
													$("body").append("<div class='tips_cover'></div><div class='cover_content'><div class='coverIng'><span>正在删除，请稍候...</span></div></div>");
												},
						                        success: function(data) {
						                        	if("success"== data.type){
						                        		$(".cover_content").empty().append("<div class='cover_after'><span>成功删除</span></div>");
						                        		window.location.href = window.location.href;
						                        	}else{
						                        		$(".cover_content").empty().append("<div class='cover_after'><span>删除失败</span></div>");
						                        		window.location.href = window.location.href;
						                        	}
						                        }
						                    });
						                }
						            }
						        }
						    });
				    }
				} else {
					showMessage("删除失败");
				}
			},
			error : function() {
				showMessage("error……");
			}
		});
	}
	
	function deleteAllProduct(id){
		$.ajax({
			url : "delone1.html",
			type : "post",
			dataType : "json",
			async : false,//同步
			data : {
				"id" : id
			},
			success : function(data) {
				if (data.type == "success") {
					var obj=data.productCatalogList;
					var stringBuilder = [];
					var mes="该分类下无子分类是否确认删除？";
					var j=0;
					$(obj).each( function(i, e){
						   //i为元素的索引，从0开始,
						  //e为当前处理的元素
						stringBuilder.push("分类名称："+e.name+"<br>");
						   j=j+i;
						});
					if(j!=0){
						 bootbox.dialog({
						        message: stringBuilder.join(""),
						        title: "提示：请先删除该分类下的子分类",
						        buttons: {
						            success: {
						                label: "取消!",
						                className: "white",
						                callback: function() {}
						            },
						            main: {
						                label: "确定!",
						                className: "blue",
						                callback: function() {
						                }
						            }
						        }
						    });
					}else{
						 bootbox.dialog({
						        message: mes,
						        title: "提示：",
						        buttons: {
						            success: {
						                label: "取消!",
						                className: "white",
						                callback: function() {}
						            },
						            main: {
						                label: "删除!",
						                className: "blue",
						                callback: function() {
						                    $.ajax({
						                        url: "delete.html",
						                        type: "post",
						                        dataType: "json",
						                        async: false,
						                        data: {
						                            "id": id
						                        },
						                        beforeSend:function(){
													$("body").append("<div class='tips_cover'></div><div class='cover_content'><div class='coverIng'><span>正在删除，请稍候...</span></div></div>");
												},
						                        success: function(data) {
						                        	if("success"==data.type){
						                        		$(".cover_content").empty().append("<div class='cover_after'><span>成功删除</span></div>");
						                        		window.location.href = window.location.href;
						                        	}else{
						                        		$(".cover_content").empty().append("<div class='cover_after'><span>删除失败</span></div>");
						                        		window.location.href = window.location.href;
						                        	}
						                        }
						                    });
						                }
						            }
						        }
						    });
				    }
				} else {
					showMessage("删除失败");
				}
			},
			error : function() {
				showMessage("error……");
			}
		});
	}
</script>
<!-- END PAGE CONTENT-->
<script src="${SHOPDOMAIN}/system/js/listContact.js"></script>
</div>
<!-- END PAGE CONTENT-->
<jsp:include page="../../foot.jsp"></jsp:include>