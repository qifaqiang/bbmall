<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:include page="top.jsp"></jsp:include>
<script> document.title="购物车"; </script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/wap/checkLogin.js"></script>
    <link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/front/css/wap/part-page.css">
     <link rel="stylesheet" type="text/css" href="${SHOPDOMAIN}/front/css/wap/shopCart.css">
<div class="w-main">

    <div class="information"<% /** onclick="toditu();"  **/ %>>所选商品由【<span id="address"></span>】为您配送<img src="${SHOPDOMAIN}/front/images/wap/Return1.png" style="margin-left:20px;"/></div>
    <div class="w-allcheckd" id="w-allcheckd"  style="" onclick="setAll();" title="flag">
        <div class="  paddingL13 w-allcheck"><img src="${SHOPDOMAIN}/front/images/wap/Round.png" id="allcheckImg"/><span>全选</span></div>
    </div>
    <div style="clear: both"></div>
    <ul class="shopping-cart" id="shopping-cart" >
         
        <div style="clear: both"></div>
      
    </ul>
    
    <div class="shopping">

        <div class="total" style="float:left;" >合计：¥<span id="total">0.00</span></div>

        <a href="javascript:void(0)">
            <div class="spright" style="width:225px" id="jiesuan" onclick="jieSuan();">立即结算</div>
        </a>

    </div>
    <form id="form1" action="${SHOPDOMAIN}/wap/shopCart/saveToSessionCartItems.html" method="post">
		<input type="hidden" name="cartItemIds" id="cartItemIds"/>
		 
	</form>
	<script id="interpolationtmpl" type="text/x-dot-template">
       {{for(i=0;i<it.length;i++){ }}
         {{          var cart=it[i];
                   
                     var inventorynumber=""; 
                     var specId="";
                     var price=Number(cart.price).toFixed(2);
 
                     if(cart.inventorynumber=="undefined"||cart.inventorynumber==""||cart.inventorynumber==null){
                          inventorynumber="";
                      } else{
                              inventorynumber=cart.inventorynumber;
                      }

					if(cart.spec_id=="0"){
						specId=cart.prod_id+"l";
					} else{
						specId=cart.spec_id;
					} 
                  }}
         
        <a  href="javascript:void(0)" id="{{= specId}}a">
            <li id="{{=specId}}" carId="{{=cart.id}}"  prodId="{{=cart.prod_id}}">
                <div class="check" id="{{=cart.id}}"  prod="{{=cart.prod_id}}" count="{{=cart.count}}" specId="{{=specId}}" onclick="choseProdut({{=cart.id}});" title="{{=cart.count*cart.price}}"><img title="{{=cart.count*cart.price}}" price="{{=price}}" id="{{=cart.id}}subtotal" src="${SHOPDOMAIN}/front/images/wap/Round.png"/ ></div>
                    
                <div class="select-number">
                    <div class="picture" onclick="goProdInfo({{=cart.prod_id}})"  id="{{= specId}}picture"><img src="${USERIMGSRC}{{=imgZuhe(cart.picuri,'_300')}}"/></div>
                    <div class="add-subtract" id="{{= specId}}abstract"><img src="${SHOPDOMAIN}/front/images/wap/reduction.png" class="w-reduce" onclick="reduction({{=cart.id}},{{=cart.prod_id}},{{=cart.count}},{{=cart.spec_id}})" />
                        <button id="{{=cart.id}}quantity">{{=cart.count}}</button>
                        <img src="${SHOPDOMAIN}/front/images/wap/plus.png" class="w-add" onclick="plus({{=cart.id}},{{=cart.prod_id}},{{=cart.count}},{{=cart.spec_id}})"/></div>
                </div>

                <div class="payable">
                    <h2 id="{{=specId}}name"  onclick="goProdInfo({{=cart.prod_id}})">{{=cart.name}}</h2>
                           
                    <h3>
						{{ if(cart.spec_id!="0") {}}
						{{ if(undefined != cart.specDetailList && cart.specDetailList.length > 0 ) {}}
							{{var specList =cart.specDetailList;}}

								{{ for(var j=0;j<specList.length;j++) { }}
									{{ var spec = specList[j]; }}
									{{=spec.specificationName}}:{{=spec.specificationDetailName}}&nbsp;&nbsp;
								{{ } }}
							{{ }}}
						{{} else{ }}
			    			无
						{{ }}}
					</h3>
                    <h4>&yen;{{=price}}</h4>
                    <img class="del" src="${SHOPDOMAIN}/front/images/wap/del.png" onclick="deleteCart({{=cart.id}})"/>
                </div>
            </li>
        </a> 
         
     {{ } }}
 
   </script>
    <script>
        $(function () {
        	  initCart();
        	  
         });
            
          function toditu(){
        	  window.location.href = SHOPDOMAIN + '/wap/ditu.html';
          }
          
          function goProdInfo(id){
        	  window.location.href="productShow.html?prodId="+id;
          }
        //初始化购物车
         function initCart(){
         
        	 var companyId=$.cookie('sys_base_companyId');
       	  		if(companyId==null||companyId==""){
       				$.dialog('alertHasOk', '', '为了更好的给您提供服务，请先选择距离您收货地最近的基地', 0,
						function() {
					window.location.href = SHOPDOMAIN + '/wap/ditu.html';
						});
       	 		 }  
       	    
        	  $.ajax({
        		  cache:false,
        		  async:false,
        		  url:"${SHOPDOMAIN}/wap/shopCart/userCart.html",
        		  data:{"companyId":companyId},
        		  success:function(data){
        			  
        			  var dataInfo=$.parseJSON(data);
        			  $("#address").text(dataInfo.Company.companyName);
        			  if(dataInfo.res_code=="1"){
            			  $.dialogToMap("alertHasOk","","您还没有登录,点击按钮去登录","去登录",0,function(){
                			  window.location.href="login.html";
                		  });
            		  }else if(dataInfo.res_code=="0"){//购物车没有数据
            			  var evalText = doT.template($("#interpolationtmpl").html());
            			  if(dataInfo.list==null||dataInfo.list==""){
            				  $("#shopping-cart").html("  <div class='w-noOrder'>"+
            			                "<img src='${SHOPDOMAIN}/front/images/wap/shoppingEmpty.png' alt=''/>"+

            			                "<p class='w-prompt'>购物车肚子空空</p>"+

            			                "<p class='w-guide'>可以去看看有哪些想买的</p>"+
            			                "<button class='w-randomBtn' onclick='window.location.href=\"index.html\"'>随便逛逛</button>"+
            			            "</div>");
            			  }else{//购物车数据
            			  $("#shopping-cart").html(evalText(dataInfo.list));
            			  $("#shopping-cart").append(" <div style='clear: both' id='bbbbb'></div>");
            			 	 intCartT();
            			 	 setAll();
            			  }
            		  }
        		  }
        	  });
        	  showTotal();//重新计算
      
         }
        //再次判断购物车
          function intCartT(){
        	  var companyId=$.cookie('sys_base_companyId');
        	  var prods="";
        	  var counts="" ;
        	  var specIds="";
        	  var cartId="";
        	  $(".check").each(function(){
        		 
        		  prods+=$(this).attr("prod")+",";
        		  counts+=$(this).attr("count")+",";
        		  specIds+=$(this).attr("specId")+",";
        		   
        	   });
        	     
        	  var stockMap= isAbolveStock(prods.replace(/,$/g,""),companyId,counts.replace(/,$/g,""),specIds.replace(/,$/g,"")); 
        	       
        	    if(stockMap!=null&&stockMap!=""&&stockMap.map!=null&&stockMap.map!=""){
        	  var stock=JSON.stringify(stockMap.map);
        	     var name="";
        	      var stoArry=stock.split(",");
        	      for(var i=0;i<stoArry.length;i++){
        	    
        	    	 var sto=stoArry[i];
        	    	 var spec=sto.split(":");
        	    	 var specId= spec[0].replace(/\"/g, "");
        	    	  
        	    	    specId=specId.replace("SPEC_","");
        	    	  
        	    	    specId=specId.replace("{","");
        	    	   if(specId.indexOf("P_")>=0){
        	    		   specId=specId.replace("P_","")+"l";
        	    		   
        	    	   } 
        	    	    if(spec[1]!=null&&spec[1]!=""){
        	    	   stockNumber= spec[1].replace("}","");
        	    	   
        	    	   if(stockNumber=="0"){ 
               			   $("#"+specId+"a").insertAfter("#bbbbb"); 
               			 $("#"+specId+"a").children("li").children(".check").empty();
               			 $("#"+specId+"abstract").remove();
               			 $("#"+specId+"picture").append("<div class='w-soldOut'></div>");
               			   
               		   }else{
               			 var cartId=$("#"+specId).attr("carid");
               			 
                  		  var productId=$("#"+specId).attr("prodid");
                  		  name+=$("#"+specId+"name").text()+" ";
                  		  $.dialog("confrim","您选择的商品已超出库存","已将"+name+"改为最大库存量",2500);
              		   	  sendUpdate(cartId,stockNumber,productId);
               			   
               			   
               		   }
        	    	    }
        	      }
        	    }
        	    
          }        
        //全选事件
         function setAll(){ 
         
          var acheckImgSrc =$("#allcheckImg").attr("src");
          if(acheckImgSrc == "${SHOPDOMAIN}/front/images/wap/Round.png") {
        	  $("#allcheckImg").attr("src","${SHOPDOMAIN}/front/images/wap/w-success.png");
        	  $(".check").children("img").attr("src", "${SHOPDOMAIN}/front/images/wap/w-success.png");
        	  $(".check").children("img").removeClass("outChosee").addClass("onChosee");
        	  setJieSuanBtn(true);//使结算按钮不可用	
             }else{
            	 $("#allcheckImg").attr("src","${SHOPDOMAIN}/front/images/wap/Round.png");
            	  $(".check").children("img").attr("src", "${SHOPDOMAIN}/front/images/wap/Round.png");
            	  $(".check").children("img").removeClass("onChosee").addClass("outChosee");
            	  setJieSuanBtn(false);//使结算按钮不可用	
             } 
          
          showTotal();
         }
         //显示合计
         function showTotal(){
        	  	var total = 0;//创建total，准备累加
        	  	var totalQuantity=0;
        	    $(".check").children("img").filter(".onChosee").each(function(){
        	    	 var subtotal=Number($(this).attr("title"));
        	    	 total+=subtotal;
        	       });
        	 
        		$("#total").text(Number(total).toFixed(2) );
        	     	  	
         }
         //减数量
         function reduction(cartId,productId,count,specId){
        	var quantity=Number($("#"+cartId+"quantity").text());
        	 var companyId=$.cookie('sys_base_companyId');
        	 if(quantity==1){
        		 return false;
        	 }else{
        		 var stockMap= isAbolveStock(productId,companyId,quantity+1,specId); 
        		  
        	 if(JSON.stringify(stockMap.map).indexOf("SPEC_")>=0||JSON.stringify(stockMap.map).indexOf("P_")>=0){
            	 
       		  var stock=JSON.stringify(stockMap.map);
       		   var sto=stock.split(":");
       		    var dd=sto[0].replace(/\"/g, "");
       		    var  stockNumber= sto[1].replace("}","");
       		    if(quantity>stockNumber){
       		    $.dialog("confrim","您选择的商品已超出库存","系统将数量改为最大库存",1500);
       			 sendUpdate(cartId,stockNumber,productId);
       		    }else{
       		     sendUpdate(cartId,quantity-1,productId);
       		    }
       	  }else{
       	    sendUpdate(cartId,quantity-1,productId);
              }
        	 
        	 
        	 }
         }
         //加数量
         function plus(cartId,productId,count,specId){
        	 var quantity=Number($("#"+cartId+"quantity").text());
        	 var companyId=$.cookie('sys_base_companyId');
        	 
        	 var stockMap= isAbolveStock(productId,companyId,quantity+1,specId); 
        	   
             if(JSON.stringify(stockMap.map).indexOf("SPEC_")>=0||JSON.stringify(stockMap.map).indexOf("P_")>=0){
            	 
        		  var stock=JSON.stringify(stockMap.map);
        		   var sto=stock.split(":");
        		    var dd=sto[0].replace(/\"/g, "");
        		    var  stockNumber= sto[1].replace("}","");
        		    $.dialog("confrim","您选择的商品已超出库存","系统将数量改为最大库存",1500);
        			 sendUpdate(cartId,stockNumber,productId);
        		    
        	  }else{
        	    sendUpdate(cartId,quantity+1,productId);
               }
        	  }
         
         //更改购物车数量
         function sendUpdate(cartItemId,quantity,productId){
     		var input=$("#"+cartItemId+"quantity");
     		var subtotal=$("#"+cartItemId+"subtotal");
     		$.ajax({
     			 async:false,
     			 
     			 url:"${SHOPDOMAIN}/wap/shopCart/updateQuantity.html",
     			 data:{"id":cartItemId,"count":quantity,prodId:productId},
     			 dataType:"json",
     			 success:function(data){
     				 if(data.res_code=="1"){
     				  input.text(data.quantity);
     				  var price= $("#"+cartItemId+"subtotal").attr("price");	
     				  var fsubtotal=data.quantity*price;
     			     $("#"+cartItemId+"subtotal").attr("title",fsubtotal);	
     					showTotal();
     				 }else{
     					 alert("更新数量失败");
     				 }
     			 },
     			 error:function(){
     				 alert("网络繁忙,请稍后!");
     			 }
     		});
     		
     	}
         //删除购物车
         function deleteCart(id){
        	 
        	 $.dialog('confirm', '', '确定要删除该商品吗？', 0, function () {
                 $.closeDialog(function () {
                	 $
						.ajax({
							async:false,
							url : "${SHOPDOMAIN}/wap/shopCart/deleteUserCartItem.html",
							type : "post",
							
							data : {
								"id" : id,
								 
							},
							success : function(
									data) {
								if (eval("("
										+ data
										+ ")").flag) {
									 $.dialog("confrim","删除购物车商品","删除成功",1500,function(){
										 initCart();
									 });
									
								} else {
									alert("error");
								}
							}
							
						});
                	 
                 });
             });
        	 
        	 
         }
         
        //复选框事件
         function choseProdut(id){
            var chekcImgSrc=$("#"+id).children("img").attr("src");
            if(chekcImgSrc == '${SHOPDOMAIN}/front/images/wap/Round.png'){
            	$("#"+id).children("img").attr("src",'${SHOPDOMAIN}/front/images/wap/w-success.png');
            	$("#"+id).children("img").removeClass("outChosee").addClass("onChosee");
            }else{
            	$("#"+id).children("img").attr("src",'${SHOPDOMAIN}/front/images/wap/Round.png');
            	$("#"+id).children("img").removeClass("onChosee").addClass("outChosee");
            }
            
        	var allCount = $(".check").length;//所有条目复选框个数
            
            var selectedCount= $(".check").children("img").filter(".onChosee").length;//选择的复选框
             if(allCount==selectedCount){
            	  $("#allcheckImg").attr('src','${SHOPDOMAIN}/front/images/wap/w-success.png');
            	  setJieSuanBtn(true);//使结算按钮不可用	
             }else{
            	 $("#allcheckImg").attr('src','${SHOPDOMAIN}/front/images/wap/Round.png');
            	
             }
             if(selectedCount==0){
            	  setJieSuanBtn(false);//使结算按钮不可用	
             }else{
            	  setJieSuanBtn(true);//使结算按钮不可用	
             }
            
            
            showTotal();//重新计算
         }
         //结算
        

       //设置结算按钮的样式
         function setJieSuanBtn(flag) {
       	 
         	if(flag) {// 有效状态
         		 
         		  $("#jiesuan").removeClass("kill");//切换样式
         		  $("#jiesuan").unbind("click");//撤消“点击无效”
         	} else {// 无效状态
         		 
         	$("#jiesuan").addClass("kill");//切换样式
         		$("#jiesuan").click(function() {//使其“点击无效”
         			return false;
         		});
         	}
         }
         //判断是否超出库存
         
         function isAbolveStock(prodIds,compayId,counts,specIds){
        	  var stockMap;
			 $.ajax({
				 async : false,
				 url:"${SHOPDOMAIN}/wap/shopCart/ifAboveStock.html",
		    	 type:"post",
		    	 dataType:"json",
		    	 
		    	 data:{"prodIds":prodIds,"companyId":compayId,"counts":counts,"specIds":specIds},
		         success:function(data){
		        	 
		        	 stockMap=data;
		         }
		    	
			 });
			 return stockMap;
         }
       
       
       //结算
        function jieSuan() {
    	 
       var cartItemIds=new Array();
       var index=0;
       var selectedCount= $(".check").children("img").filter(".onChosee").each(function(){
    	   cartItemIds[index++]=$(this).attr("id").replace("subtotal","");
       }) ;
       var companyId=$.cookie('sys_base_companyId');  
       if(cartItemIds.length==0){
    	   $.dialog("confrim","请选择商品进行结算","您未选择商品",1500);
    	   return false;
       }
        $("#cartItemIds").val(cartItemIds);
        
       $
		.ajax({
			url : "${SHOPDOMAIN}/wap/shopCart/saveCartItems.html",
			type : "post",
			dataType:"json",
			data : {
				"cartItemIds" :  $("#cartItemIds").val(),
				companyId:companyId, 
				 
			},
			success : function(
					data) {
				 
				   if(data.res_code=="1"){
					   
					   window.location.href="cart-confirmation-order.html";
				   }else{
					   alert("error");
				   }
			}
		});
       
       }
         
        
    </script>
</div>
<jsp:include page="foot.jsp"></jsp:include>
