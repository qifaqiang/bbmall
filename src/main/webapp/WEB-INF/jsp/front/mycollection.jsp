<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="top2.jsp"></jsp:include>
<link href="${SHOPDOMAIN}/front/css/web/share.css" rel="stylesheet" type="text/css">
<link href="${SHOPDOMAIN}/front/css/web/Personal.css" rel="stylesheet" type="text/css">
<script src="${SHOPDOMAIN}/front/js/web/ntab.js"></script>
<style>
.changCast {height: 25px;width: 100px;}
a {	color: Blue;}
</style>
<script id="interpolationtmpl" type="text/x-dot-template">
{{for(var i=0;i<it.list.length;i++){ }}
{{var uslog=it.list[i];}}
<div class="per_collect_listpart flW collect_{{=uslog.proId}}">
	<div class="per_collect_listpic">
		<a href="productDetail.html?prodId={{=uslog.proId}}">
		<img src="${USERIMGSRC}{{=imgZuhe(uslog.picuri,'_300')}}" widht="200" height="200" /></a>
		<div class="per_heart"  style="cursor:pointer"  onClick="delcollect({{=uslog.proId}})"></div>
		<div class="per_collect_over over_{{=uslog.proId}}"></div>
		<div class="per_collect_inner inner_{{=uslog.proId}}" >
			<h3>确定取消收藏？</h3>
			<ul>
				<li><input type="button" class="per_collect_overbtn" value="取消" onclick="cancel({{=uslog.proId}})"></li>
				<li><input type="button" class="per_collect_overbtn" value="确定" onclick="sure({{=uslog.proId}})"></li>

			</ul>
		</div>
	</div>
	<div class="per_collect_listtxt"id="per_collect_listtxt_{{=uslog.proId}}" prods="{{=uslog.proId}}" companyId="{{=uslog.company_id}}" specIds="{{=uslog.specid}}">
		<div style="width:150px; overflow:hidden; text-overflow:ellipsis; white-space:nowrap;"><input type="checkbox" id="subcheck_{{=uslog.proId}}" dataid="1" value="1" onclick="setSelectAll({{=uslog.proId}});" />  {{=uslog.name}} </div>
	</div>
	<div class="per_collect_price">
		<div class="per_collect_priceleft flW">{{=(uslog.price).toFixed(2)}}</div>
		<div class="per_collect_pricebtn frW " style="cursor:pointer" onclick="addtoCart({{=uslog.proId}})" >加入购物车</div>
	</div>
</div>
{{}}}
<div class="fox"></div>
	<div class="page_and_btn" style="padding-top:10px; height:50px; background-color:#f1f1f1;">
		<div></div>
	{{=it.pageStr}}
</div>
</script>
<!--Personal center left-->

<div class="per_main">
	<jsp:include page="centerLeft.jsp"></jsp:include>
	<!--Personal center left-->
	<div class="per_mainright flW">
		<!--位置-->
		<div class="per_main_title">我的收藏</div>
		<div class="per_collect_operate">
			<ul>
				<li><input type="checkbox" id="SelectAll" onclick="selectAll()" value="全选" /> 全选</li>
				<li class="changCast" style="cursor:pointer" onclick="allAddCast()"><img src="${SHOPDOMAIN}/front/images/web/rightFixed1.png" width="20" height="20" /> 加入购物车</li>
				<li>|</li>
				<li class="changCast" style="cursor:pointer" onclick="dealCollect()"><img src="${SHOPDOMAIN}/front/images/wap/del.png" width="20" height="20" /> 取消收藏</li>
			</ul>
		</div>
		<div class="per_collect_list " style="padding-bottom:0px;">
			<div id="showUslogList"></div>
			<div class="per_noorder" id="five" style="display:none; ">
				<div class=" per_ per_noorderleft flW">
					<img src="${SHOPDOMAIN}/front/images/web/per_noorder.png" width="104" height="48" alt="" />
				</div>
				<div class=" per_noorderright flW">
					<ul>
						<li>SORRY~您没有相关收藏哦~</li>
						<li>可以去看看有哪些想买的</li>
						<li><a href="index.html"><input type="button" name="submi" id="submi" class="per_evaluate_btn" value="随便逛逛" /></a></li>
					</ul>
				</div>
			</div>
			<div id="nouse" style="display:none"></div>
		</div>
	</div>
	<div class="fox"></div>
</div>
<jsp:include page="footShop.jsp"></jsp:include>
<jsp:include page="foot-validate.jsp"></jsp:include>

<script>
	document.title="个人中心-我的收藏";
	function showmess(showmess) {
		layer.msg(showmess, {
			time : 5000
		});
		window.location.href = window.location.href;
	}
	function showm(showmess) {
		layer.msg(showmess, {
			time : 5000
		});
	}
	function showms(showmess) {
		layer.msg(showmess, {
			time : 1000
		});
	}
	
	//删除收藏
	function delcollect(uid) {
		$(".over_" + uid).css("display", "block");
		$(".inner_" + uid).css("display", "block");

	}
	//取消
	function cancel(uid) {
		$(".over_" + uid).css("display", "none");
		$(".inner_" + uid).css("display", "none");

	}
	function sure(uid) {
		deleteColl(uid);
		getall(1);
	}

	//复选框事件
	//全选、取消全选的事件
	function selectAll() {

		//carr_checked=$("#SelectAll").attr("checked");
		//alert(carr_checked);
		if ($("#SelectAll").prop("checked")) {
			$(":checkbox").attr('checked', true);
			$(":checkbox").prop('checked', true);
		} else {
			$(":checkbox").attr("checked", false);
			$(":checkbox").prop("checked", false);
		}
	}
	//子复选框的事件

	function setSelectAll(id) {
		//当没有选中某个子复选框时，SelectAll取消选中
		if (!$("#subcheck_"+id).checked) {
			$("#SelectAll").attr("checked", false);
		}
		var chsub = $("input[type='checkbox'][id='subcheck']").length; //获取subcheck的个数sub
		var checkedsub = $("input[type='checkbox'][id='subcheck']:checked").length; //获取选中的subcheck的个数
		if (checkedsub == chsub && chsub!=0) {
			$("#SelectAll").attr("checked", true);
		}
	}
	var mess = "";
	var meserr = "";
	
	//检测库存是否充足
	function isAbolveStocks(prodIds, compayId, counts, specIds) {
		compayId = $.cookie('sys_base_companyId');
		$.ajax({
			async : false,
			url : "${SHOPDOMAIN}/wap/shopCart/addCarts.html",
			data : {
				"prodId" : prodIds,
				"companyId" : compayId,
				"count" : counts,
				"specId" : specIds
			},
			dataType : "json",
			success : function(data) {
				if (data.res_code == '0') {
					mess += data.resName + ",";
				} else {
					if (data.res_code == 'cart.ER6001') {
						meserr += data.resName + data.message + ";";
					} else {
						showm(data.message);
					}
				}
				cartCount();
			},
			error : function() {
				alert("网络繁忙,请稍后!");
			}
		});
	}
	
	//加入购物车
	function addtoCart(prods) {
		var companyId = $.cookie('sys_base_companyId');
		var specIds = $("#per_collect_listtxt_" + prods).attr("specIds");
		if (specIds == "undefined") {
			specIds = 0;
		}
		if (prods != "") {
			isAbolveStock(prods, companyId, 1, specIds);
		} else {
			showms("请选择商品后再操作");
		}
	}
	
	//全部加入购物车
	function allAddCast() {
		mess = "";
		meserr = "";
		var prods = "";
		var companyId = "";
		var specIds = "";
		$(".per_collect_listtxt").each(function(i, n) {
			var check = $(this).children().children().prop("checked");
			if (check == true) {
				prods = $(this).attr("prods");
				specIds = $(this).attr("specIds");
				if (specIds == "undefined") {
					specIds = 0;
				}
				companyId = $.cookie('sys_base_companyId');
				stockMap = isAbolveStocks(prods, companyId, 1, specIds);
			}
		});
		if (prods != "") {
			if (mess != "") {
				showm(mess + "添加购物车成功!" + "  " + meserr);
			} else {
				showm(meserr);
			}
		} else {
			showms("请选择商品后再操作");
		}

	}
	
	//删除收藏 多个
	function deleteColls(id) {
		$.ajax({
			async : false,
			url : "${SHOPDOMAIN}/interfaces/userCollection/deleteCollectPc.html",
			data : {
				id : id
			},
			dataType : "json",
			success : function(data) {
				if (data.res_code == '0') {
					mess += data.resName + ",";
				} else {
					if (data.res_code != '1') {
						meserr += data.resName + data.message + ";";
					} else {
						showm(data.message);
						window.setTimeout((window.location.href = "index.html"), 10000);
					}
				}
			},
			error : function() {
				alert("网络繁忙,请稍后!");
			}
		});
	}
	
	//处理收藏
	function dealCollect() {
		var prods = "";
		mess = "";
		meserr = "";
		$(".per_collect_listtxt").each(function(i, n) {
			var check = $(this).children().children().prop("checked");
			if (check == true) {
				prods = $(this).attr("prods");
				stockMap = deleteColls(prods);
			}
		});
		if (prods != "") {
			if (mess != "") {
				showmess(mess + "成功取消收藏!" + "  " + meserr);
			} else {
				showmess(meserr);
			}
		} else {
			showm("请选择商品后再操作");
		}

	}
	function isAbolveStock(prodIds, compayId, counts, specIds) {
		compayId = $.cookie('sys_base_companyId');
		$.post(SHOPDOMAIN + '/wap/shopCart/addCart.html', {
			"prodId" : prodIds,
			"companyId" : compayId,
			"count" : counts,
			"specId" : specIds
		}, function(data) {
			if (data.res_code == '0') {
				showm("添加购物车成功!");
			} else {
				showm(data.message);
			}
			cartCount();
		}, "json").error(function() {
			showError();
		});
		cartCount();
	}
	
	//删除一个
	function deleteColl(id) {
		$.post(SHOPDOMAIN + '/interfaces/userCollection/deleteCollect.html', {
			id : id
		}, function(data) {
			if (data.res_code == '0') {
				showmess(data.message);
			} else {
				showmess(data.message);
			}
		}, "json").error(function() {
			showError();
		});
	}
</script>
<script>
	jQuery(document).ready(function() {
		//  获取列表 
		$(".mycollection").addClass("per_nowcolor");
		$("#lingquan").html("我的收藏");
		getall(1);
	});
	jQuery(document).ready(function() {
		FormValidation.init();
	});
	function getall(currentPage) {
		$.post(SHOPDOMAIN + '/interfaces/userCollection/colleLists.html', {
			"currentPage" : currentPage,
			"showCount" : 10
		}, function(data) {
			if (data.res_code == '0') {
				var evalText = doT.template($("#interpolationtmpl").html());
				if (data.list.length == 0) {
					$(".per_noorder").css('display', 'block');
				} else {
					$("#showUslogList").html(evalText(data));
				}
			} else {
				if (data.res_code == '1') {
					showm("您还没有登录!");
					window.setTimeout((window.location.href = "index.html"), 10000);
				}
			}
			$(".page_and_btn ul li span").css("background-color", "#e70012");
			$(".page_and_btn ul li span").css("color", "#fff");
		}, "json").error(function() {
			showError();
		});
	}
</script>
