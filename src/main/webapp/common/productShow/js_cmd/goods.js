 
define(function(require, exports, module){
	 // var  $= require("lib_cmd/zepto");//加载部分组件在html页执行js代码固放在了页面中	
	 // Swiper = require("lib_cmd/swipe-cmd");//加载部分组件在html页执行js代码固放在了页面中
	var	main = require("js_cmd/main"),
	    myDialog = require("lib_cmd/myDialog"),
		scrollEvt = require("lib_cmd/scrollEvt"),
		iTemplate = require("lib_cmd/iTemplate"),
		iScroll = require("lib_cmd/goods_iScroll"),
		addressSelect = null,
		skuData = {},
		resData = {},
		$eles = {},
		ele = {},
		MobileGood = {};		

	   function initPage(){		//
		
		//定义对象comment 
		var tab_comment ={};	
		MobileGood.Refresh = function Refresh(){
		    if (APP.isMobile) {			   
				MobileGood.baseSwiper.refresh();
				MobileGood.conSwiper.refresh();	
				//MobileGood.CommentSwiper.refresh();	
			}
		};
		
		
		
		ele.getSkuData = function getSkuData(){
			//if(ele.buyedSum < ele.limitSum){
			/*失败提示信息*/
			function FailureInfo(str){
			  $eles.btip_widget.show().find("#btip_label").html(str);
			  $eles.link_sku_dialog.addClass("hidden");
			  ele.btn_disabled = false;	
			  $(".End").css("padding-bottom","30px");
			  $eles.list_comments.css("padding-bottom","30px");
			}
			
				var _l = loading();
				$.ajax({
					type: "POST",
					url: APP.urls.goodInfo,
					data: {
					    GoodsId: APP.goodsId
					    // companyId:$.cookie("sys_base_companyId")
					},
					async:true,
					success: function(res){
						_l.destroy();
						if(0 == res.res_code){
							prodType = res.prodType;
							isSpecification = res.isSpecification;
							mainSpecList = res.prodSpecList;
							detailSpecList = res.prodSpecDetailList;
							prodSpecInfoList = res.prodSpecInfoList;
							$("#prodtype").val(prodType);//商品规格
							$("#isSpecification").val(isSpecification);//商品是否开启规格
							$("#maxInventory").val(res.maxInventory);
							
							var Datas = res.Data;
							/*SKU数据*/
							ele.skuData = res;
							
							APP.goodsInfo.totalInventory=res.maxInventory;
							$eles.label_sku_price.html("￥"+$("#label_price").text());
							$eles.label_sku_Stock.html("库存"+res.maxInventory+"件");
							
							//限制购买
							if(APP.goodsInfo.buyedSum >= APP.goodsInfo.limitSum){
							   FailureInfo('该商品最多可购买' + APP.goodsInfo.limitSum + '件,您已经不能再购买了');							
							   return;
							}else if(0 == APP.goodsInfo.totalInventory){
								
							   FailureInfo('您来晚了，商品已经卖完啦')	;
							   return;
							}
						    /*商品优惠*/
							if(Number(Datas.Discount.total) > 0 ){
						     var Info ='<span>{Title}</span>';
							 var Desc_TPL_Con ="{Activity}";	
							 	
							 if(Number(Datas.Discount.total) == 1){ 							  
								$eles.Discount_label.css({"overflow": "hidden","text-overflow": "ellipsis","white-space":"nowrap"});			          
								var _Activity ="";
								var _resData = Datas.Discount.DisInfo[0];
								if(_resData.Description.length == 1){
									 _Activity += _resData.Description[0].Activity;
								}else{
							        for(var i = 0 ; i< _resData.Description.length; i++){
								      _Activity += _resData.Description[i].Activity+"； ";
								    }
								}
								var _DataResult = "<span>"+_resData.Title+"</span>"+_Activity;	
								// console.log("1111"+_DataResult);
							 }else{
								Info = '<span>{Title}</span>';	
							    var _DataResult = $(iTemplate.makeList(Info, Datas.Discount.DisInfo));
							 }	
							 $eles.Discount_label.append(_DataResult);									  
						     						  
						     var TPL = '<li><div><label>{Title}</label></div><div><p>{TimeStart}-{TimeEnd}</p><p>{Description}</p></div></li>';
							 var Desc_TPL='<p>{Activity}</p>';							
							 var Discount_Temp = $(iTemplate.makeList(TPL, Datas.Discount.DisInfo,function(k,v){								 
								 var  Desc_Temp = iTemplate.makeList(Desc_TPL, v.Description);								 
								 return {  Description:	Desc_Temp }
							 }));							 
							 $eles.Discount_list.find("ul").append(Discount_Temp);	
							 
						  }else{
						     $eles.dl_GoodDiscount.addClass("hidden");
						  }	
						  /*商品活动标签*/
						  if(Datas.Label){
						    $eles.label_activityTag.removeClass("vhidden").html(Datas.Label);	
						  }
						  					
						}
					},
					dataType: "json"
				});
			//}
		}
		ele.getSkuData();	

		
		//收藏
		$eles.icon_fav1[0].onclick  = function(evt){
			var that = this, _collected = ele.collected;
			if(that.disabled){
				return;
			}
			ele.collected = !_collected;
			that.disabled = true;
			$.ajax({
		        type: "POST",
		        url: APP.urls.fav_url,
		        data: {
		            type: !_collected
		        },
		        async: true,
		        success: function (res){
		        	that.disabled = false;
		        	if(0 == res.res_code){
		        		tip(res.message, { classes: "otip", t: 2000 });
		        	}else{
		        		ele.collected = _collected;
		        		if(res.res_code="1"){
		        			$.dialog("confirm","","您还没有登录,点击确定去登录",0,function(){
		            			  window.location.href="login.html";
		            		});
		        		}
		        	}
		        },error:function() {
					showError();
				},
	        	dataType: "json"
		    });
		}
		//修改购买数量 点击 + - 的操作
		$eles.table_number.on("click", function(evt){
			var et = evt.target, en = et.tagName, newSku = ele.curSku;
			var prodtype_t = $("#prodtype").val();
			var isSpecification_t = $("#isSpecification").val();
			var sepcId = $("#prodspecinfoid").val();
			var curmaxInventory = $("#maxInventory").val();
			
			if(prodtype_t == 1 || isSpecification_t == 0){//把礼盒包或者没有开启规格的的规格默认成0
				sepcId ="0";
			}
			if(curmaxInventory == 0){
				tip("该商品规格已无货", { classes: "otip", t: 2000 });	
				$eles.sku_number.val(1);
				return;	
			}
			
			if(sepcId !="" && "INPUT" == en && "button" == et.type){
				var curcount_t = $("#sku_number").val();
			    
			    if("+" == et.value){
			    	if(parseInt(curmaxInventory)>curcount_t){
						var oNewBtnHtml = parseInt(curcount_t) + 1;
						$eles.sku_number.val(oNewBtnHtml);
						return;	
					}else{
						$eles.sku_number.val(curmaxInventory);
						tip("您最多可以购买"+curmaxInventory+"件", { classes: "otip", t: 2000 });
						return;	
					}
			    }else{
			    	if (curcount_t <= 1) {
						var oNewBtnHtml = 1;
						$eles.sku_number.val(oNewBtnHtml);
					} else {
						var oNewBtnHtml = parseInt(curcount_t) - 1;
						$eles.sku_number.val(oNewBtnHtml);
					}
			    }
			}
			if(sepcId == "" && "INPUT" == en &&  "button" == et.type ){
				tip("请先选择规格", { classes: "otip", t: 2000 });
				return;
			}
		});
		//修改购买数量
		$eles.sku_number.on("change", function(evt){
			var oldvalue =  parseInt(this.value);
			this.value = this.value.replace(/\D/g,'');	
			if(isNaN(oldvalue)){			  
			  tip("请输入数值", { classes: "otip", t: 2000 });
			  $eles.sku_number.val(1);
			  return;
			}	
			var prodtype_t = $("#prodtype").val();
			var isSpecification_t = $("#isSpecification").val();
			var sepcId = $("#prodspecinfoid").val();
			var curmaxInventory = $("#maxInventory").val();
			
			if(prodtype_t == 1 || isSpecification_t==0){//把礼盒包或者没有开启规格的规格默认成0
				sepcId ="0";
			}
			
			if(undefined==sepcId || "" == sepcId){
				tip("请先选择规格！", { classes: "otip", t: 2000 });
				$eles.sku_number.val(1);
				return;
			}
			if(curmaxInventory == 0){
				tip("该商品规格已无货", { classes: "otip", t: 2000 });	
				$eles.sku_number.val(1);
				return;	
			}
			if(oldvalue > curmaxInventory){
				tip("您最多可以购买"+curmaxInventory+"件", { classes: "otip", t: 2000 });	
				$eles.sku_number.val(curmaxInventory);
				return;					   
			}			
		});
		
		//限制购买
		if(APP.goodsInfo.limitSum ==0){
			APP.goodsInfo.limitSum = Infinity;
		}else{
		  if(APP.goodsInfo.buyedSum >= APP.goodsInfo.limitSum){
			$eles.btip_widget.show().find("#btip_label").html('该商品最多可购买'+APP.goodsInfo.limitSum+'件');
		  }		
		}		
		
		//加入购物车
		$eles.btn_add_shopcart.on("click", function(evt){
			if($("#maxInventory").val() == 0){
				return;
			}
			ele.sku_dialog_show = [true, 0, "add2Shopcart"];
		});
		//直接购买
		$eles.btn_buy.on("click", function(evt){
			if(!ele.btn_disabled || APP.goodsInfo.buyedSum >= APP.goodsInfo.limitSum){
				return;
			}
			ele.sku_dialog_show = [true, 0, "buy"];
		});
		
		//用户中心
		$eles.btn_user_center.on('click',function(evt){			
		   location.href = APP.urls.user_center;	
		});
		
		//详情切换tab浮动	
		var div_nav = {
			div_nav: $eles.div_navs,		
			fixedtop: 0,
			top:0,
			start: function(){
				window.addEventListener("scroll", this, false);
			},
			handleEvent: function(){
				this.fiexdtop = $("#goodheader").offset().top;			
		        this.top = document.documentElement.scrollTop || document.body.scrollTop;
		       	this.div_nav[this.top > this.fiexdtop?"addClass":"removeClass"]("fixeds");
			}
		}.start();
		
		//加入车 on dialog
		$eles.btn_dialog_add_shopcart.on("click", function(evt){
			var prodtype_t = $("#prodtype").val();
			var isSpecification_t = $("#isSpecification").val();
			var sepcId = $("#prodspecinfoid").val();
			
			if(prodtype_t == 1 || isSpecification_t == 0){//把礼盒包或者没哟开启规格的规格默认成0
				sepcId ="0";
			}
			if("" == sepcId || "null" == sepcId || undefined==sepcId){
				tip("请先选择规格", { classes: "otip", t: 2000 });
			}else{
				add2Shopcart(this, evt);
			}
		});
		//直接购买 on dialog
		$eles.btn_dialog_buy.on("click", function(evt){
			//if(!ele.curSku.id){
				//tip
			//}else{
				buy(this, evt);
			//}
		});
		//btn sku ok
		$eles.btn_dialog_ok.on("click", function(evt){
			var that = this;
			if(ele.sku_dialog_show[2]){
				if("add2Shopcart" == ele.sku_dialog_show[2]){
					add2Shopcart(that, evt);
				}else{
					buy(that, evt);
				}
			}
		});

		$eles.go_bargin.on('click', function(evt){
			history.go(-1);
		});		
		
		//打开sku窗口
		$eles.label_sku_title2.on("click", function(evt){
			ele.sku_dialog_show = [true, 1];
		});
		//
		//ele.detailTableIndex = 1;
		for(var k in APP.goodsInfo){
			ele[k] = APP.goodsInfo[k];
		}

		//
		if(navigator.userAgent.toString().toLowerCase().indexOf("micromess")>-1){
			require.async("lib_cmd/imagePreview2", function(){
				console.log("imagePreview2");
			});
		}

	}	
		
	/*******************
		ajax 方法	
	*******************/
	function add2Shopcart(thi, evt){
		if($("#maxInventory").val() == 0 || APP.goodsInfo.buyedSum >= APP.goodsInfo.limitSum){
			return;
		}
		/**
	    var sku = ele.curSku;
	    if(!sku.id){
	    	tip(ele.good_Prompt, { classes: "otip", t: 2000 });
	    	return;
	    }
	    if (!sku.sum) {
	    	var tips = [];
	        if(0!= sku.status){
	        	tips.unshift("该商品已经下架了哦");
	        }else if(0 == sku.inventory){
	        	tips.unshift("您来晚了，商品已经卖完啦");
	        }else if(0 == sku.BuyMaxNum){
	        	tips.unshift("该商品最多可购买"+sku.BuyMaxNum+"件哦");
	        }
	        alert(tips[0]);
	        return;
	    }
	    sku.sum = Math.min(Math.max(1, sku.sum), sku.BuyMaxNum, sku.inventory);
		**/
		
		var prodtype_t = $("#prodtype").val();
		var isSpecification_t = $("#isSpecification").val();
		var sepcId = $("#prodspecinfoid").val();
		var curmaxInventory = $("#maxInventory").val();
		
		if(prodtype_t == 1 || isSpecification_t == 0){//把礼盒包或者没有开启规格的规格默认成0
			sepcId ="0";
		}
		
		if(undefined==sepcId || "" == sepcId){
			tip("请先选择规格！", { classes: "otip", t: 2000 });
			return;
		}
		if(curmaxInventory == "" || curmaxInventory == 0){
			tip("您来晚了，商品已经卖完啦", { classes: "otip", t: 2000 });
			return;
		}
		
	    thi.setAttribute("disabled", "disabled");
	    var _loading = loading();
	    $.ajax({
	        type: "POST",
	        url: APP.urls.add2shopcart_url,
	        data: {
	        	prodId: APP.goodsId,
	            specId: sepcId,
	            count: $("#sku_number").val()
	            // companyId:$.cookie("sys_base_companyId")
	        },
	        async: true,
	        success: function (res) {
	            _loading.destroy();
	            ele.sku_dialog_show = [false, 1];
	            thi.removeAttribute("disabled");
	            if(0 == res.res_code){
	            	ele.shopcartSum += $("#sku_number").val()*1;								
					tip("商品已添加至购物车", { classes: "otip", t: 2000 });						
					MobileGood.Refresh();
						
                }else{
                	if(res.res_code=="1"){
                		$.dialog("confirm","","您还没有登录,点击确定去登录",0,function(){
	            			  window.location.href="login.html";
	            		});
                	}else{
                		tip(res.message, { classes: "otip", t: 2000 })
                	}
	            }
	        },
	        dataType: "json"
	    });
	}
	//
	function buy(thi, evt){
		if(!ele.btn_disabled || APP.goodsInfo.buyedSum >= APP.goodsInfo.limitSum){
			return;
		}
		//限制非粉丝购买
		if(APP.limit && (1 == APP.limit.val) ){
			ele.sku_dialog_show = [false, 1];
			MobileGood.Refresh();
			window.dialog_limit_buy.open();
			return;
		}
	    var sku = ele.curSku;
	    if(!sku.id){
	    	tip(ele.good_Prompt, { classes: "otip", t: 2000 });
	    	return;
	    }
	   
	    if (!sku.sum) {
	        var tips = [];
	        if(0!= sku.status){
	        	tips.unshift("该商品已经下架了哦");
	        }else if(0 == sku.inventory){
	        	tips.unshift("您来晚了，商品已经卖完啦");
	        }else if(0 == sku.BuyMaxNum){
	        	tips.unshift("该商品最多可购买"+sku.BuyMaxNum+"件哦");
	        }
	        alert(tips[0]);
	        return;
	    }
	}
	
	function initComments(){
		 tab_comment = (function(){
			 var TPL = '<li>\
			              <div class="comment_title"><img  style="background-image:url('+USERIMGSRC+'{HeadUrl});" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAC0lEQVQIW2NkAAIAAAoAAggA9GkAAAAASUVORK5CYII=" class="img_wrap">{NickName}<span></span></div>\
						   <div class="comment_grade"><label class="comment_rate" data-rate="{Star}"></label></div>\
						   <p class="comment_content" style="clear:both;">{Content}</p>\
						   <div class="commnet_info">\
						      <div class="comment_times">{addtime}</div>\
							  <div class="comment_tag"></div>\
						   </div>\
						   <div style="overflow: hidden" id="commentsPic">{picurl}</div>\
						   <div class="reply" style="display:none" >商家回复：</div>\
                         </li>';
			$eles.loading_bottom = $(main.ele.loading_bottom);
			$eles.list_comments.append($eles.loading_bottom);
			function tab_comment(idx){
				var isLoading = false;
				this.index = idx;
				this.pageIndex = 1;
				this.pageSize = 10;
				this.end = false;
				this.on = false;
				this.scrollEvt = new scrollEvt(this);
				this.commnetNum = function(idx){
				  var _comments = APP.goodsInfo.comment;
				  return [_comments.total,_comments.A,_comments.B,_comments.C,][idx];				
				};				
				
				//
				Object.defineProperty(this, "isLoading", {
					get: function(){
						return isLoading;
					},
					set: function(v){
						isLoading = v;
						$eles.loading_bottom[isLoading?"removeClass":"addClass"]("vhidden");
					}
				});
			}
			tab_comment.prototype = {
				close: function(){
					var that = this;
					that.on = false;
					that.scrollEvt.stop();
					return that;
				},
				open: function(){
					var that = this;
					/*if(!$("#list_comments_"+ele.commonTableIndex+" .nomore").hasClass("hidden")){
						console.log(ele.commonTableIndex)
				     return
				    }*/
					that.loadData();
					//
					this.open = function(){
						//
						this.on = true;
						if(this.on && !this.end){
							this.scrollEvt.start();
						}
					}
					this.open();
					return that;
				},
				
				loadData: function(){
					var that = this;
					console.log($("#list_comments_"+ele.commonTableIndex+" .nomore").hasClass("hidden"));
					if(that.isLoading || !$("#list_comments_"+ele.commonTableIndex+" .nomore").hasClass("hidden")){
						return;
					}
								
					that.isLoading = true;
					 $.ajax({
			            type: "POST",
			            url: APP.urls.comments_url,
			            data: {
			            	type:that.index,
							GoodsId:APP.goodsId,
							pageIndex:that.pageIndex
			            },
			            async: true,
			            success: function (res) {
			            	(function(that){
								  that.isLoading = false;	
								  if(0 == res.res_code){	
									  
									  APP.goodsInfo.comment.total=res.countAll;
									  APP.goodsInfo.comment.A=res.countA;
									  APP.goodsInfo.comment.B=res.countB;
									  APP.goodsInfo.comment.C=res.countC;
									  comment=APP.goodsInfo.comment;
									  
									  if(res.countAll>0){
										  $eles.div_nav.eq(1).find("span").html((comment.total<999)?comment.total:'999+');
										  $("#nav_comments").children().eq(1).html("<label>好评("+res.countA+")</label>");
										  $("#nav_comments").children().eq(2).html("<label>中评("+res.countB+")</label>");
										  $("#nav_comments").children().eq(3).html("<label>差评("+res.countC+")</label>");
									  }else{
										  $eles.div_nav.eq(1).find("span").html('0');
										  $("#nav_comments").children().eq(1).html("<label>好评(0)</label>");
										  $("#nav_comments").children().eq(2).html("<label>中评(0)</label>");
										  $("#nav_comments").children().eq(3).html("<label>差评(0)</label>");
										  $eles.nav_comments.remove();
										  $eles.list_comments.html('<div class="no_comments"><img style ="width:105px; height:105px;"  src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAC0lEQVQIW2NkAAIAAAoAAggA9GkAAAAASUVORK5CYII="><p>暂无评价</p></div>');
										  $(".no_comments").css("height",APP.ScreenHeight-100);
									  }
									  
									  if(0 == res.Data.length || (that.pageIndex > Math.ceil(that.commnetNum(ele.commonTableIndex) / that.pageSize) ) ){
										  if(APP.isMobile){
											MobileGood.CommentPull = 0;
										  }else{
											that.scrollEvt.stop();											 								
										  }
										  $("#list_comments_"+ele.commonTableIndex).find(".nomore").removeClass("hidden");	
									  }else{	
										  $.each(res.Data, function(idx, obj) {
											  if(undefined!=obj.picurl){
												  var kpic=obj.picurl.split(";");
												  var temps="";
												  for(var i=0;i<kpic.length;i++){
													  if(kpic[i]==""){
														  
													  }else{
														  temps+='<div class="product-imgs-li">\
																<img class="commentsPic" src="'+USERIMGSRC+"/"+imgZuhe(kpic[i],'_200')+'" bgsrc="'+USERIMGSRC+"/"+kpic[i]+'" />\
																</div>\
																<div class="product-img-space"></div>'
													  }
													  
												  }
												  obj.picurl=temps;
											  }else{
												  obj.picurl="";
											  }
											  
										  });	
										  	
										  $("#list_comments_"+that.index).append($(iTemplate.makeList(TPL, res.Data)) );
										  if(APP.isMobile){
												 MobileGood.CommentPull = 1;
												// MobileGood.CommentSwiper.refresh();
										  }
										  console.log("res.Data.length"+res.Data.length);
										  console.log("that.pageSize"+that.pageSize);
										  if(res.Data.length < that.pageSize){	
											  $("#list_comments_"+ele.commonTableIndex).find(".nomore").removeClass("hidden");	
										  }
																				  
									  }
								  }else{
									  console.log("get comment error of type:"+ that.index);
								  }
								  console.log(that.index);
								  $("#list_comments_"+that.index).siblings().removeClass("on");
								  $("#list_comments_"+that.index).addClass("on");
								  that.pageIndex++;	
								  var r = document.documentElement.clientWidth;
									var s = r - 32 - 13 * 5;
									var q = s / 4;
									if (q > 120) {
										q = 120
									}
									$(".product-imgs-li").width(q).height(q);
									$(".product-imgs-li").find("img").each(function(v) {
										var t = $(this);
										var x;
										var u;
										var w = new Image();
										w.src = $(t).attr("bgsrc");
										w.onload = function() {
											x = this.width;
											u = this.height;
											if (u > x) {
												t.width(q);
												t.css("marginTop", (q - t.height()) / 2 + "px")
											} else {
												t.height(q);
												t.css("marginLeft", (q - t.width()) / 2 + "px")
											}
										}
									})
									addLoadEvent(getPicInfo1);
			            	})(that);
			            },
			            dataType: "json"
			        });
					return that;
				}
			}
			tab_comment.tabs = [0,0,0,0];
			return tab_comment;
		})();
		//
		tab_comment.tabs = tab_comment.tabs.map(function(v, idx){
			return new tab_comment(idx);
		});
		//评论切换tab
		$eles.nav_comments.on("click", function(evt){
			 
			var et = evt.target, en = et.tagName, idx = 0;
			if("LI" == en){
				idx = et.getAttribute("data-idx");
				ele.commonTableIndex = idx;	
				$("#list_comments_"+idx).siblings().removeClass("on");
				$("#list_comments_"+idx).addClass("on");
				if(APP.isMobile){MobileGood.CommentPull =1;	}
				$("#loading_bottom").removeClass("hidden");	
				if(idx ==1 && !APP.goodsInfo.comment.A){ no_comment(idx); }
				if(idx ==2 && !APP.goodsInfo.comment.B){ no_comment(idx); }
				if(idx ==3 && !APP.goodsInfo.comment.C){ no_comment(idx); }  
					  
				tab_comment.tabs.forEach(function(v, k){
					tab_comment.tabs[k][k==idx?"open":"close"]();
				  });		
				
				if(APP.isMobile){ MobileGood.CommentSwiper.refresh();}
				
			}
			  var r = document.documentElement.clientWidth;
				var s = r - 32 - 13 * 5;
				var q = s / 4;
				if (q > 120) {
					q = 120
				}
				$(".product-imgs-li").width(q).height(q);
				$(".product-imgs-li").find("img").each(function(v) {
					var t = $(this);
					var x;
					var u;
					var w = new Image();
					w.src = $(t).attr("src");
					w.onload = function() {
						x = this.width;
						u = this.height;
						if (u > x) {
							t.width(q);
							t.css("marginTop", (q - t.height()) / 2 + "px")
						} else {
							t.height(q);
							t.css("marginLeft", (q - t.width()) / 2 + "px")
						}
					}
				})
				addLoadEvent(getPicInfo1);
		});
		function no_comment(idx){
		    var info;
			if(idx == 1){ info="暂无好评";}else if(idx == 2){info="暂无中评";}else if(idx == 3){info="暂无差评";}			
		    $(".list_comments").removeClass("on");
		    $("#list_comments_"+idx).addClass("on").html('<div class="no_comments"><img style ="width:105px; height:105px;"  src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAC0lEQVQIW2NkAAIAAAoAAggA9GkAAAAASUVORK5CYII="><p>'+info+'</p></div>');
		    $(".list_comments .no_comments").css("height",APP.ScreenHeight-140);
		    if(APP.isMobile){MobileGood.CommentPull =0;}
			$("#loading_bottom").addClass("hidden");			
		}
		//默认
		ele.commonTableIndex = 0;
		tab_comment.tabs[ele.commonTableIndex].open();	
  }
 	//dom ready
	$(function(){
		$eles = {
			label_title: $("#label_title"),
			slider_3_wrap: $("#slider_3_wrap"),
			label_price: $("#label_price"),
			label_activityTag: $("#label_activityTag"),
			icon_fav1: $("#icon_fav1"),
			label_price_original: $("#label_price_original"),
			Freight: $("#Freight"),
			
			Discount_top:$("#Discount_top"),
			Discount_label:$("#Discount_label"),
			Discount_list:$("#Discount_list"),
			//
			btn_selectAddr: $("#btn_selectAddr"),
			freight_2:$(".freight_2 > div > div"),
			//
			label_sku_price: $("#label_sku_price"),
			label_sku_title2: $("#label_sku_title2"),
			sku_dialog: $("#sku_dialog"),
			link_sku_dialog: $(".link_sku_dialog"),
			label_sku_title: $("#label_sku_title"),
			sku_close: $("#sku_close"),
			sku_widget_mask:$("#sku_dialog .widget_mask"),
			list_sku: $("#list_sku"),
			btn_dialog_add_shopcart: $("#btn_dialog_add_shopcart"),
			btn_dialog_buy: $("#btn_dialog_buy"),
			btn_dialog_ok: $("#btn_dialog_ok"),
			sku_btn_group: $("#sku_btn_group"),
			shop_area:$(".link_to_shop .shop_area"),
			dl_GoodDiscount:$("#dl_GoodDiscount"),
			//底部菜单
			btn_user_center: $("#btn_user_center"),
			btn_link_shopcart: $("#btn_link_shopcart"),
			btn_add_shopcart: $("#btn_add_shopcart"),
			btn_buy: $("#btn_buy"),
			btip_widget: $("#btip_widget"),			
			//sku
			table_number: $("#table_number"),
			sku_Purchase: $("#table_number .box div"),
			sku_number: $("#sku_number"),
			sku_inventory: $("#sku_inventory"),
			label_sku_Stock: $("#label_sku_Stock"),
			//商品详情页头部			
			 pullDown : $("#pullDown"),			 
			goodheader: $("#goodheader"),
			div_navs: $("#div_nav"),		
			div_nav: $("#goodheader ul li"),
			Parameter: $("#Parameter"),
			nav_comments: $("#nav_comments"),
			list_comments: $("#list_comments"),
			goodsinfo:$(".goodsinfo"),
			
			tools_widget_goTop:$("#tools_widget_goTop"),//返回顶部
			dialog_guid:$(".dialog_guid"),
			
			//选择地址
			areaList_dialog:$("#areaList_dialog"),
			areaList_send:$("#areaList_send"),
			areaList_li:$("#areaList_choose ul li"),		
			
			//
			go_bargin: $("#fixed_btn_bargain")
		}
		//对图片进行裁剪处理
		var width = $eles.slider_3_wrap.width(),
			height = width;// * 0.75;
		$eles.slider_3_wrap.css({
			"max-height": height+'px',
			"height": height+'px'
		});
		$eles.slider_3_wrap.find('img').each(function(){
			var w = this.width,
				h = this.height,
				newH = h*width/w;
			$(this).css({
				"width": "100%",
				"max-width": "100%"
			});
			//if(newH > height){
			var diff = (newH-height)/2;
			$(this).css('margin-top', -diff+'px');
			//}
		
		});
			
	 /*  Mobile style start */ 
	if(APP.isMobile){	
	   /*  判断手机是不是ios设备 */ 
	    var isiOS = !!navigator.userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);   
       $("#container,.page-container").css("height",APP.ScreenHeight+"px");	   
	   MobileGood = {		 
		  /*  page structor style start */ 
	     pageSwiper : new Swiper('.page-container',{ 
			mode: 'vertical',
			noSwiping:true,
			onlyExternal:true,
			speed : 600,	
			onSlideChangeStart: function(swiper){	
			   if(swiper.activeLoopIndex){				   
				   if ($(".dialog_guid").length > 0 ) {   $(".dialog_guid").removeClass("on"); }
				   $eles.tools_widget_goTop.removeClass("hidden");
				}else{
				   if ($(".dialog_guid").length > 0 ) {   $(".dialog_guid").addClass("on"); }
				   
				   $eles.tools_widget_goTop.addClass("hidden");		    
				}	
				MobileGood.Refresh();			                   
            }
	     }), 		 
		 /*  page1 con*/  
		 basePosition : 0,
		 basemaxPosition:0,		
		 baseSwiper: new iScroll(document.getElementById("goodsBasecon"), {
			scrollbars: true,
			mouseWheel: true,
			/*useTransform:true,*/
			useTransition:false,			
			scrollbarClass:"myscrollbar",
			onScrollMove:function(){			
			    var iscoll_point;
				MobileGood.basePosition = this.y
			    if($(".dialog_guid").length > 0){
					($(".dialog_guid").hasClass("on"))? iscoll_point = (this.pointY < 51):iscoll_point = (this.pointY < 1);					
				}else{
				   iscoll_point = (this.pointY < 1);
				}	
				if(APP.isMobile && isiOS && (this.y < this.maxScrollY) && iscoll_point && (!MobileGood.pageSwiper.activeLoopIndex)){
					MobileGood.pageSwiper.swipeNext();																
			    }
			},					
			onTouchEnd: function(){ 	
			  if(this.y < (this.maxScrollY - 50)){  MobileGood.pageSwiper.swipeNext(); 
			     if(ele.ajaxed){
					ele.ajaxed = 0;
					setTimeout(function(){MobileGood.Refresh()},1000);					
				 }
			  }		 
			}
	    }),
		
		 /*  public area*/
		 pullDownEl : document.getElementById('pullDown'),
		 pullpublic:function(pos){		
			  var _opacitys =(pos/50).toFixed(1)-0.1;
			  $eles.pullDown.css({"display":"block","opacity":_opacitys});	  
			   if (pos > 60 && ! this.pullDownEl.className.match('flip')) {
				  this.pullDownEl.className = 'flip';
				  this.pullDownEl.querySelector('.pullDownLabel').innerHTML = '释放，返回商品基本信息';
			  } else if (pos < 60 &&  this.pullDownEl.className.match('flip')) {	  
				  this.pullDownEl.className = '';
				  this.pullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉，返回商品基本信息';
			  }  		
		 },	  
		 /*  page2 con*/	
		conPosition: 0,
		conSwiper: new iScroll(document.getElementById("goodscons"), {
			scrollbars: true,
			mouseWheel: true,
			/*useTransform:false,
			useTransition:true,*/
			scrollbarClass:"myscrollbar",
			onScrollMove:function(){	
			  MobileGood.conPosition =  this.y;		
			  MobileGood.pullpublic(this.y);
			  if(APP.isMobile && isiOS && (this.y < this.maxScrollY) && (this.pointY <1) && (MobileGood.pageSwiper.activeLoopIndex)){
					this.scrollTo(0, this.maxScrollY, 400);				
			   }	
			  if(this.y < (this.maxScrollY-10)){ 
			     MobileGood.conSwiper.refresh();
			  }			
			},	
			onTouchEnd: function(){ 		
			  $eles.pullDown.css({"display":"none","opacity":0});		
			  if (MobileGood.conPosition>58) {  MobileGood.pageSwiper.swipePrev(); MobileGood.conPosition = 0; this.refresh(); } }
	    }),		
		 /*  Parameter-con con*/ 		
		ParamPosition:0,
		ParamSwiper: new iScroll(document.getElementById("Parameter"), {
			scrollbars: true,
			mouseWheel: true,
			/*useTransform:false,
			useTransition:true,*/
			scrollbarClass:"myscrollbar",
			onScrollMove:function(){	
			  MobileGood.ParamPosition =  this.y;		
			  MobileGood.pullpublic(this.y);
			  if(APP.isMobile && isiOS && (this.y < this.maxScrollY) && (this.pointY <0) && (MobileGood.pageSwiper.activeLoopIndex)){
					this.scrollTo(0, this.maxScrollY, 400);				
			  }			
			},	
			onTouchEnd: function(){ 		
			  $eles.pullDown.css({"display":"none","opacity":0});		
			  if (MobileGood.ParamPosition > 58) {  MobileGood.pageSwiper.swipePrev(); MobileGood.ParamPosition = 0; this.refresh(); } }
	    }),	  
	    /*  Comment-con con*/  
		CommentPosition: 0,
		CommentPull:1,	
		CommentSwiper:new iScroll(document.getElementById("Commenter"), {
			scrollbars: true,
			mouseWheel: true,
			/*useTransform:false,
			useTransition:true,*/
			scrollbarClass:"myscrollbar",
			onScrollMove:function(){	
			  MobileGood.CommentPosition =  this.y;		
			  MobileGood.pullpublic(this.y);
			  if(APP.isMobile && isiOS && (this.y < this.maxScrollY) && (this.pointY <1) && (MobileGood.pageSwiper.activeLoopIndex)){
					this.scrollTo(0, this.maxScrollY, 400);				
			  }			  	  			
			},				
			onScrollEnd:function(){	
			
			   if(this.y < (this.maxScrollY+10) && APP.goodsInfo.comment.total ){  
				 if(MobileGood.CommentPull){
				   tab_comment.tabs[ele.commonTableIndex].loadData(); this.refresh();
				 }
			   }		
			},			
			onTouchEnd: function(){ 		
			  $eles.pullDown.css({"display":"none","opacity":0});		
			    if (MobileGood.CommentPosition > 58) {  MobileGood.pageSwiper.swipePrev(); MobileGood.CommentPosition = 0; this.refresh(); } 				
			  }
	    }),
		Refresh:function(){
		    if (APP.isMobile) {			   
				MobileGood.baseSwiper.refresh();
				MobileGood.conSwiper.refresh();	
				//MobileGood.CommentSwiper.refresh();	
				
			}
		}
		
	   }		
	   $eles.tools_widget_goTop.on("click",function(){  MobileGood.baseSwiper.scrollTo(0, 0, 200);   MobileGood.pageSwiper.swipePrev(); 	 }); //手机端返回上一页
    /*  page structor style end */  
	}else{
	  $("#container").removeClass("IsMobile").addClass("IsComputer");		
	}
	  //设置cookies
		window.setCookie = function setCookie(name,value)
		{
			var Days = 30;
			var exp = new Date();
			exp.setTime(exp.getTime() + Days*24*60*60*1000);
			document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();		
		}
		
		//读取cookies
		window.getCookie = function getCookie(name)
		{
			var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		 
			if(arr=document.cookie.match(reg))
		 
				return (arr[2]);
			else
				return null;
		}
		
	  //商品详情头部
	  $eles.div_nav.on("click", function(i){			
		  var indexs = $eles.div_nav.index(this);
		  $eles.div_nav.removeClass("active");
		  $eles.div_nav.eq(indexs).addClass("active");
		  $("#goodheader ul .activeborder").css({"left":indexs *50 +"%"});
		  var r = document.documentElement.clientWidth;
			var s = r - 32 - 13 * 5;
			var q = s / 4;
			if (q > 120) {
				q = 120
			}
			$(".product-imgs-li").width(q).height(q);
			$(".product-imgs-li").find("img").each(function(v) {
				var t = $(this);
				var x;
				var u;
				var w = new Image();
				w.src = $(t).attr("src");
				w.onload = function() {
					x = this.width;
					u = this.height;
					if (u > x) {
						t.width(q);
						t.css("marginTop", (q - t.height()) / 2 + "px")
					} else {
						t.height(q);
						t.css("marginLeft", (q - t.width()) / 2 + "px")
					}
				}
			})
			addLoadEvent(getPicInfo1);
		  if(APP.isMobile){
			$eles.goodsinfo.css({"z-index":"1","opacity":"0"});
			$eles.goodsinfo.eq(indexs).css({"z-index":"3","opacity":"1"});
		  }else{
			$eles.goodsinfo.css("display","none");
			$eles.goodsinfo.eq(indexs).css("display","block");
		  }
	  });
	  //优惠详情信息
	   $eles.Discount_top.on("click", function(evt){
	      $eles.Discount_top.find("a").toggleClass("on");
		  $eles.Discount_top.find("a").hasClass("on") ? $eles.Discount_top.find("p").html("优惠详情：") :$eles.Discount_top.find("p").html("优惠：");
		  $eles.Discount_list.toggleClass("hidden");
		  $eles.Discount_label.toggleClass("hidden");
		  $eles.Discount_top.find("span").toggleClass("hidden");	
		  MobileGood.Refresh();	  
  	   });
	   
	   if(navigator.userAgent.indexOf('Android') > -1 || navigator.userAgent.indexOf('Linux') > -1){$eles.sku_number.css("padding-top","1px");}
		
		//关闭sku窗口
		$eles.sku_close.on("click", function(evt){
			ele.sku_dialog_show = [false, 1];
			MobileGood.Refresh();	  		
		});				
		$eles.sku_widget_mask.on("click", function(evt){
			ele.sku_dialog_show = [false, 1];
			MobileGood.Refresh();	  		
		});		
		//ele Object
		ele = (function(){
			function Ele(){
				var skuData = {};
				var lstProductJson = {};
				var curSku = {};
				this.KEYS = [];
				var skuTitle = "";
				this.skuTitles = [[], []];
				//
				var title = "";				
				var collected = false;
				var shopcartSum = 0;
				var comment = {};
				
				var detailTableIndex = 1;
				var commonTableIndex = 0;
				var btn_disabled = false;
				var sku_dialog_show = false;
				var good_Prompt = "";			
			
				$("#goodsImages").attr("src",APP.goodsInfo.imgs);

				Object.defineProperty(this, "skuData", {
					get: function(){
						return skuData;
					},
					set: function(v){
						var that = this;
						skuData = v;
						that.lstProductJson = skuData.Data.SKU.lstProductJson;
						that.curSku = {};
						initSkuView.call(that, skuData);
						if((0 == APP.goodsInfo.limitSum) &&(0 == APP.goodsInfo.buyedSum) ){
							APP.goodsInfo.limitSum = Infinity;
						}
												
						/*失败提示信息*/
						function FailureInfo(str){
						  $eles.btip_widget.show().find("#btip_label").html(str);
						  $eles.link_sku_dialog.addClass("hidden");
						  ele.btn_disabled = false;	
						  $(".End").css("padding-bottom","30px");
						  $eles.list_comments.css("padding-bottom","30px");
						}		
						
						
						if(0 != this.skuData.maxInventory){
							ele.btn_disabled = false;
						}else{
							ele.btn_disabled = true;
						}
						
						//商品已经下架或删除 
						if(APP.goodsInfo.isdelordowm){
						  FailureInfo('该商品已经下架了哦');			 
						  return
						}
						
					}
				});				

				function getZuhe(arr){
					//arr_temp checked 选中的组合s
					var arr_temp = [];
					for(var i=0, ci; ci = arr[i]; i++){
						for(var j=0, len = arr_temp.length, cj; cj = arr_temp[j]; j++){
							if(j>=len){
								break;
							}
							var t = cj.slice(0);
							t.push(ci);
							arr_temp.push(t);
						}
						arr_temp.push([ci]);
					}
					return arr_temp;
				}

				//
				Object.defineProperty(this, "lstProductJson", {
					get: function(){
						return lstProductJson;
					},
					set: function(v){
						v.forEach(function(v, k){
							v.sum = v.BuyMaxNum&&v.inventory?1:0;
							lstProductJson[v.key] = v;
						});
					}
				});
								
				//
				Object.defineProperty(this, "curSku", {
					get: function(){
						return curSku;
					},
					set: function(v){
						var that = this, label_price = "", label_price_original = "";
						curSku = v;
                        var IsHiddenMarket = false;
    					
						if(APP.goodsInfo.priceRange[0] == APP.goodsInfo.priceRange[1]){
							label_price = formatPrice([APP.goodsInfo.priceRange[0].toFixed(2)].join("-") );
						}else{
							label_price = formatPrice([APP.goodsInfo.priceRange[0].toFixed(2), APP.goodsInfo.priceRange[1].toFixed(2)].join("-") );
						}
						if(APP.goodsInfo.priceOriginRange[0] == APP.goodsInfo.priceOriginRange[1]){
							label_price_original = formatPrice([APP.goodsInfo.priceOriginRange[0].toFixed(2)].join("-"));
						}else{
							label_price_original = formatPrice([APP.goodsInfo.priceOriginRange[0].toFixed(2), APP.goodsInfo.priceOriginRange[1].toFixed(2)].join("-"));
						}
                        
					
						$eles.label_price.html(label_price);
						$eles.label_price_original.html("￥"+label_price_original);
						$eles.label_price_original.parent()[IsHiddenMarket ?"addClass":"removeClass"]("hidden");
						$eles.label_sku_price.html("￥"+label_price);
						//
						function formatPrice(str){
							return str.replace(/(\d+)\./g, function($1, $2){
								return '<label>'+$2+'</label>.';
							});
						}
						
					}
				});
				//商品title
				Object.defineProperty(this, "title",{
					set: function(v){
						title = v;
						$eles.label_title.html(title);
					}
				});
				//商品title
				Object.defineProperty(this, "imgs",{
					set: function(v){
                          var slider_3_wraps = new Swiper('#slider_3_wrap',{   	
							speed:500,
							loop:true,
							calculateHeight:true,  	
							pagination: '#slider_4_indicate',
											
						});
					}
				});
				
				//商品详情tab
				Object.defineProperty(this, "detailTableIndex",{
					get: function(){
						return detailTableIndex;
					},
					set: function(v){
						detailTableIndex = v;
						$eles.div_nav.find("a").removeClass("on").eq(detailTableIndex).addClass("on");
						$eles.div_sections.find("section").removeClass("on").eq(detailTableIndex).addClass("on");
					}
				});
				//商品评论tab
				Object.defineProperty(this, "commonTableIndex", {
					get: function(){
						return commonTableIndex;
					},
					set: function(v){
						commonTableIndex = v;
						$eles.nav_comments.find("li").removeClass("on").eq(commonTableIndex).addClass("on");
						$eles.list_comments.find("ul").removeClass("on").eq(commonTableIndex).addClass("on");
					}
				});
				
				
				//评论
				Object.defineProperty(this, "comment", {
					get: function(){
						return comment;
					},
					set: function(v){
						comment = v;
						//
						$eles.div_nav.eq(1).find("span").html((comment.total<999)?comment.total:'999+');
						if(comment.total){							
							$eles.nav_comments.html(iTemplate.makeList('<li class="on" data-idx="0">\
										<label>全部</label>\
										</li>\
										<li data-idx="1">\
											<label>好评({A})</label>\
										</li>\
										<li data-idx="2">\
											<label>中评({B})</label>\
										</li>\
										<li data-idx="3">\
											<label>差评({C})</label>\
										</li>', [comment]));
							initComments();
						}else{
							$eles.nav_comments.remove();
							$eles.list_comments.html('<div class="no_comments"><img style ="width:105px; height:105px;"  src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAC0lEQVQIW2NkAAIAAAoAAggA9GkAAAAASUVORK5CYII="><p>暂无评价</p></div>');
							$(".no_comments").css("height",APP.ScreenHeight-100);
						}
					}
				});
				
				//收藏商品
				Object.defineProperty(this, "collected", {
					get: function(){
						return collected;
					},
					set: function(v){
						collected = v;
						//					
						$("#icon_fav1").find("span")[collected?"addClass":"removeClass"]("on");
						
					}
				});
				//购物车
				Object.defineProperty(this, "shopcartSum", {
					get: function(){
						return shopcartSum;
					},
					set: function(v){
						shopcartSum = v;
						//
						$eles.btn_link_shopcart.attr({"data-count":shopcartSum, "href":APP.urls.shopcart_page});
					}
				});
				//
				Object.defineProperty(this, "skuTitle", {
					get: function(){
						var titleArr = ["<div>", "</div>"];
						//未选择的属性
						if(this.skuTitles[1].length){
							titleArr.splice(1, 0, "请选择 ", this.skuTitles[1].join(" "));
							if(0 == this.skuTitles[0].length){
								//titleArr.splice(3, 0, this.skuTitles[1].length?" 及":" " ,"数量");
							}
						}else{
							titleArr.splice(1, 0, skuTitle);
							if(skuData.lstSKUVal == null){
								//titleArr.push("数量："+this.curSku.sum);	
							}
													
						}
						return titleArr.join("");
					},
					set: function(v){
						var that = this, _skuTitle = this.skuTitles = [[], []], sku_val = [];
						for(var i=0, ci, flag; ci = skuData.Data.SKU.lstSKUVal[i]; i++){
							flag = v.length?ci.lstVal.some(function(v1){
								return v==v1.key?[true, sku_val.push(v1.val)][0]: false;
							}):false;
							_skuTitle[flag?0:1].push(ci.name);
						}
						skuTitle = _skuTitle;
						var html = '请选择 '+skuTitle[1].join(" ");
						ele.good_Prompt = html;
						
						if(skuTitle[0].length && !skuTitle[1].length){
							html = skuTitle[0].join('{key}');
							html = sku_val.reduce(function(p, c, i, thi){
								return p+'\"'+c+'\" ';
							}, '');
						}
						skuTitle = '已选择 '+ html;
						//$eles.label_sku_title.html(that.skuTitle);
						
						//$eles.label_sku_title2.find("label").eq(0).html(that.skuTitle);
					}
				});
				//显示sku弹窗
				Object.defineProperty(this, "sku_dialog_show", {
					get: function(){
						return sku_dialog_show;
					},
					set: function(v){
						var _sku_dialog_show = sku_dialog_show;
						//v:array [a1, a2, a3] a1:flag to show, a2:source, a3:callback method
						sku_dialog_show = v;
						$eles.sku_dialog[sku_dialog_show[0]?"removeClass":"addClass"]("hidden");
						$eles.sku_btn_group.attr("class","sku_btn_group").addClass(sku_dialog_show[2]?"group_2":"group_1");
						if(false == _sku_dialog_show){
							new iScroll(document.getElementById("scroller_sku_list"),{
								hideScrollbar:false,
								handleClick:false,
								onBeforeScrollStart: function(e){},
								onScrollMove:function(e){	
								  if(APP.isMobile && isiOS && (this.y < this.maxScrollY) && (this.pointY <1) ){								
										this.scrollTo(0, this.maxScrollY, 400);				
								   }				
								},	
								
							} );
						}
					}
				});
								
				//【立即购买】【加入购物车】按钮灰掉
				Object.defineProperty(this, "btn_disabled", {
					get: function(){
						return btn_disabled;
					},
					set: function(v){
						btn_disabled = v;
						if(0 == this.skuData.maxInventory){
							btn_disabled = true;
						}else{
							btn_disabled = false;
						}
						$eles.btn_add_shopcart[btn_disabled?"removeClass":"addClass"]("on");
						$eles.btn_buy[btn_disabled?"removeClass":"addClass"]("on");
					}
				});
			}
			//
			function initSkuView(skuData){
				var that = this;
				/**
				var TPL_section = '<section>\
									<div>\
										<label>{specificationName}</label>\
									</div>\
									<div class="prodspecdetaildiv_{k}">\
										{skus}\
									</div>\
								</section>',
					TPL_sku = '<label class="label_radio">\
								<input type="checkbox" name="sku_{idx}" value="{key}"/>\
								<span>{detailName}</span>\
							</label>',
					HTML = '',
					HTML_sku = "";
					HTML = iTemplate.makeList(TPL_section, skuData.prodSpecList, function(k, v){
						that.KEYS[k] = [];
						
						HTML_sku = iTemplate.makeList(TPL_sku, skuData.prodSpecDetailList, function(kk, vv){
							that.KEYS[k].push(vv.key);
							return {
								idx: k
							}
						});
					return {
						skus: HTML_sku
					}
				});
					**/
				HTML = '';
				if(skuData.prodType == 0){
					//普通商品
					$.each(skuData.prodSpecList,function(index,item){
						var detail_spec = "";
						var cur_main_specid = item.id;
						$.each(skuData.prodSpecDetailList,function(n,item2){
							if(item2.prodSpecId==cur_main_specid){
								detail_spec +="<label class='label_radio'>\
									<input type='radio' class='label_specs chooseRadio' name='sku_"+(index+1)+"' curxh='"+(index+1)+"' value='"+item2.detailName+"' specid='"+item.id+"_"+item2.id+"' onClick='chosespec(this);'/>\
									<span>"+item2.detailName+"</span></label>";
							}
						});
						
						HTML += "<section>"
									+"<div>"
										+"<label>"+item.specificationName+"</label>"
									+"</div>"
									+"<div class='prodspecdetaildiv_"+(index+1)+"'>"
										+ detail_spec
									+"</div>"
								+"</section>";
					});
				}else{
					HTML += "<section>"
								+"<div>"
									+"<label>规格</label>"
								+"</div>"
								+"<div>"
									+"<label class='label_radio'>"
									+"<input type='radio' class='chooseRadio' name='sku_1' curxh='1' value='礼盒'/>"
									+"<span>礼盒</span></label>"
								+"</div>"
						   +"</section>";
				}
				
				//console.log("HTML:"+HTML);
				$eles.list_sku.html(HTML);
				//礼盒包 或者没哟开启规格的产品 直接选择购买数量
				if(skuData.prodType == 1|| skuData.isSpecification == 0){
					$("#label_sku_title").html("<div>请选择购买数量</div>");
					$("#label_sku_title2").html("<label><div>请选择购买数量</div></label>");
				}
			}
			//
			return new Ele();
		})();
		ele.ajaxed = 1;
		initPage();
		if(!$eles.Parameter.find("li").length){
		   $eles.Parameter.find(".table").html('<div class="no_comments"><img style ="width:105px; height:105px;"  src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAC0lEQVQIW2NkAAIAAAoAAggA9GkAAAAASUVORK5CYII=" class="no_Attribute"><p>暂无商品属性</p></div>');
		   $eles.Parameter.find(".no_comments").css("height",APP.ScreenHeight-100);
		}	
		
		
		if (!APP.isMobile) {	
			ele.ajaxed = 0;
		}else{
			$eles.goodheader.css("z-index",10);
			$("#goods_det").css("min-height",APP.ScreenHeight-140);
			window.onpageshow = function(){
			   if(ele.ajaxed){
				  ele.ajaxed = 0;
			   }
			   setTimeout(function(){MobileGood.Refresh()},1000);
			}			
		}
		
	});
	
	module.exports = $;
	
});