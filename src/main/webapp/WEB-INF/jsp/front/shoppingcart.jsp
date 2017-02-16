<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="top2.jsp"></jsp:include>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
<div class="per_toppart">
<script src="${SHOPDOMAIN}/front/js/web/ScrollPic.js"
	type="text/javascript"></script>
<style>
.kill {background-color: #f1f1f1;color: #999999;}
body{background-color:white}
</style>
	<div class="per_return_main">
		<div class="per_shoptitle">我的购物车</div>
		<%--<div class="per_choice">
			所选商品由【<span id="address"></span>】为您配送 满<i id="comSendPrice"></i>(元)免运费；不足<i
				id="comSendPriceT"></i>(元)，收运费<i id="comChargeSendPrice"></i>(元)。<span
				class="frW"> <% /**<a href="#" onclick="toditu()"><img
					src="${SHOPDOMAIN}/front/images/web/arr-r.png" /></a> **/%></span>
		</div>--%>
		<div class="per_car_title">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<input type="hidden" id="ids">
				<input type="hidden" id="cartItemIds">
				<tbody id="topSetAll">

					<td width="6%"><input type="checkbox" checked="checked"
						id="setAll"><span>全选</span></td>
					<td width="19%">商品</td>
					<td width="27%">&nbsp;</td>
					<td width="8%" align="center">规格</td>
					<td width="9%" align="center">单价（元）</td>
					<td width="9%" align="center">数量</td>
					<td width="12%" align="center">小计（元）</td>
					<td width="10%" align="center">操作</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="per_car_con" id="emptyCart">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tbody id="shopping-cart">
				</tbody>
			</table>
		</div>
		<div class="per_car_counts">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tbody id="footSetAll">
					<tr>
						<td width="6%"><input type="checkbox" checked="checked"
							name="checkbox2" id="setAllT"> <label for="checkbox2">全选</label><label
							for="checkbox2"></label></td>
						<td width="57%"><a onclick="deleteBach()">删除</a></td>
						<td width="8%">已选<i id="totalQuantity">1</i>件商品
						</td>
						<td width="12%">总价（不含运费）：</td>
						<td width="8%"><span>￥<i id="total">0</i></span></td>
						<td width="9%"><input type="button" class="per_payinforbtn"
							name="submit" onclick="jieSuan();" id="jiesuan" value="去结算"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- 代码 开始 -->
		<div class="per_car_intro">
			<div class="per_car_introtitle">热销推荐</div>
			<div class="per_car_introcon">
				<div class="scroll">
					<div id="FS_arr_left_01" class="arr_left">
						<a title="上一个" href="javascript:;"></a>
					</div>
					<div id="FS_Cont_01" class="scroll_cont" data-client="focus">
					</div>
					<div id="FS_arr_right_01" class="arr_right">
						<a title="下一个" href="javascript:;"></a>
					</div>
					<div id="FS_numList_01" class="scroll_num"></div>
				</div>
			</div>
		</div>
	</div>
	<!--wrapper end-->
	<!-- 代码 结束 -->
	<!-- 购物车条目模板 -->
	<script id="interpolationtmpl" type="text/x-dot-template">
	{{for(i=0;i<it.length;i++){ }}
		{{
		var cart=it[i];
		var inventorynumber=""; 
		var value_t=""; 
		var specId="";
		var price=Number(cart.price).toFixed(2);
		
		if(cart.spec_id=="0"){
			specId=cart.prod_id+"l";
		} else{
			specId=cart.spec_id;
		} 
		}}
		<tr class="sure_1" id="{{= specId}}a">
			<td width="3%" class="check" id={{=cart.id}}  prod={{=cart.prod_id}} count={{=cart.count}} specId={{=specId}}    >
				<input type="checkbox"  price={{=price}} id="{{=specId}}" carId={{=cart.id}}  prodId={{=cart.prod_id}} name="checkboxBtn"  onclick="choseBox()"></td>
			<td width="9%" style="cursor: pointer;" onclick="window.location.href='${SHOPDOMAIN}/productDetail.html?prodId={{=cart.prod_id}}'">
				<div class="posRelaivenone">
					<img id="{{= specId}}picture" src="${USERIMGSRC}{{=imgZuhe(cart.picuri,'_300')}}" width="100" height="100" alt=""/>
				</div>
			</td>
			<td width="39%" style="cursor: pointer;"  id="{{= specId}}name" onclick="window.location.href='${SHOPDOMAIN}/productDetail.html?prodId={{=cart.prod_id}}'">{{=cart.name}} </td>
			<td width="9%" align="center">
				{{ if(cart.spec_id!="0") {}}
					{{ if(undefined != cart.specDetailList && cart.specDetailList.length > 0 ) {}}
						{{var specList =cart.specDetailList;}}

						{{ for(var j=0;j<specList.length;j++) { }}
							{{ var spec = specList[j]; }}
							{{=spec.specificationName}}:{{=spec.specificationDetailName}}</br>
						{{ } }}
					{{ }}}
				{{} else{ }}
			    	无
				{{ }}}
            </td>
			<td width="10%" align="center" id="{{=cart.id}}price">{{=price}}</td>
			<td width="10%" align="center">
				<div class="per_car_num">
				<div class="per_car_numleft flW" onclick="reduction({{=cart.id}},{{=cart.prod_id}},{{=cart.count}},{{=cart.spec_id}})"><input type="button" class="per_car_numbtn" value="-"></div>
				<div class=" per_car_numcenter flW"  ><input type="text" id="{{=cart.id}}quantity" class="per_car_numinput sure_num_1"  value="{{=cart.count}}"  disabled="disabled"></div>
				<div class=" per_car_numleft flW" onclick="plus({{=cart.id}},{{=cart.prod_id}},{{=cart.count}},{{=cart.spec_id}})" ><input type="button"   class="per_car_numbtn" value="+"></div>
				</div>
			</td>
			<td width="10%" align="center" id="{{=cart.id}}subtotal" >{{=(cart.count*cart.price).toFixed(2)}}</td>
			<td width="10%" align="center" onclick="deleteCart({{=cart.id}})"><a>删除</a></td>
		</tr>
	{{ } }}
	</script>
	<script class="jsCarousel" type="text/x-dot-template">
	{{ if(it.list&&it.list.length>0){  }}
	{{ for(var i=0; i<it.list.length;i++){ }}
	{{var objLi=it.list[i];}}
		<div class="box">
		<div>
			<a href="${SHOPDOMAIN}/productDetail.html?prodId={{=objLi.id}}" target=_blank> <img src="${USERIMGSRC}{{=imgZuhe(objLi.picuri,'_300')}}" alt="" width="208" height="208"> </a>
			<h3>{{=objLi.name}} </h3>
			<div class="box_btm">
			<div class="box_left">￥{{=objLi.price.toFixed(2)}}</div>
			<div class="box_right jscarousal_btn" style="cursor:pointer" prodId="{{=objLi.id}}" specid="{{=objLi.specid}}">加入购物车</div>
			</div>
		</div>
    	</div>
		{{}}}
	{{}}}
	</script>
	<!--图片滚动js-->
	
	<script src="${SHOPDOMAIN}/front/js/web/shoppingcar.js"></script>
	<script type="text/javascript">
	document.title="电商平台-购物车";
		$(document).ready(function() {
			$('#jsCarousel').jsCarousel({
				onthumbnailclick : function(src) {
					// 可在这里加入点击图片之后触发的效果
					//$("#overlay_pic").attr('src', src);
					$(".overlay").show();
				},
				autoscroll : true
			});

			$(".overlay").click(function() {
				$(this).hide();
			});

		});
	</script>
	<script type="text/javascript">
		var carr_in_1_w = window.innerWidth
				|| document.documentElement.clientWidth
				|| document.body.clientWidth;
		var carr_in_1_h = window.innerHeight
				|| document.documentElement.clientHeight
				|| document.body.clientHeight;
		var carr_jushang = (carr_in_1_h - 100) / 2;
		//居上高度=  （总高度-div内同高度 ）/2
		var carr_juzuo = (carr_in_1_w - 460) / 2;
		$(document).ready(
				function() {
					$('.per_boxmain').css('height', carr_in_1_h);
					$('.per_box_w').css('width', carr_in_1_w);
					$('.per_box').css(
							'margin',
							carr_jushang + 'px ' + carr_juzuo + 'px 0'
									+ carr_juzuo + 'px');
					$('.per_boxmain').css('display', 'none');
				});
	</script>
	<jsp:include page="footShop.jsp"></jsp:include>