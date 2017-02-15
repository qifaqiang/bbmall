//获取信息
function getProdInfo() {
	$.post(SHOPDOMAIN+ '/interfaces/productInfo/getProductInfoPc.html',{prodId : getRequest("prodId"),companyId:$.cookie("sys_base_companyId")},
		function(data) {
			if (data.res_code == '0') {
				var evalText = doT.template($(
						"#rollpictmpl").html());
				$(".slides").html(
						evalText(data.prodImgList));
				var prodInfo = data.prod;
				$(".mainTitle").html(prodInfo.name );
				$(".viceTitle").html( prodInfo.note);
				document.title = prodInfo.name + " "+ prodInfo.note;
				if(undefined==prodInfo.max_price||prodInfo.max_price==prodInfo.price){
					$(".nowprice").html("￥"+prodInfo.price.toFixed(2));
				}else{
					$(".nowprice").html("￥"+prodInfo.price.toFixed(2)+"~"+prodInfo.max_price.toFixed(2));
				}
				if(undefined==prodInfo.max_market_price){
					$(".marketprice").html("￥"+prodInfo.market_price.toFixed(2));
				}else{
					$(".marketprice").html("￥"+prodInfo.market_price.toFixed(2)+"~"+prodInfo.max_market_price.toFixed(2));
				}
				APP.comment.total=data.commentCount;
				$eles.prodping.html("商品评价("+((data.commentCount<999)?data.commentCount:'999+')+")");
				var shipFree="";
				if(data.chargeSendPrice==0){
					shipFree="免运费";
				}else if(data.chargeSendPrice==0){
					shipFree=data.chargeSendPrice+"元";
				}else{
					shipFree="购物满"+data.sendPrice+"元免运费，"+data.sendPrice+"元之内运费"+data.chargeSendPrice+"元";
				}
				$(".shipFree").html(shipFree);
				
				$(".proDetails").html(prodInfo.descn);
				$(".img_small").html(
						"<img src=" + USERIMGSRC
								+ imgZuhe(prodInfo.picuri,
										'_300') + " />");
				initBase();
				$(".breadCrumbNav").children().eq(1).attr("href",SHOPDOMAIN+"/productList.html?catalogId1="+data.pc1id+"&catalogId1name="+data.pc1name).html(data.pc1name);
				$(".breadCrumbNav").children().eq(2).attr("href",SHOPDOMAIN+"/productList.html?catalogId1="+data.pc1id+"&catalogId1name="+data.pc1name+"&catalogId2="+data.pc2id+"&catalogId2name="+data.pc2name).html(data.pc2name);
				if(data.companyBaseINFO == 'productInfo.ER5002'){
					showMessageChooseCompany();
				}
				goodInfo();
			} else {
				if(data.res_code == 'productInfo.ER5001'){
					showMessageGoBack(data.message);
				}else{
					showMessage(data.message);
				}
			}
		}, "json").error(function() {
	showError();
});
}

//是否已收藏
function checkCollection() {
	$.post(SHOPDOMAIN+ '/interfaces/productInfo/getProductCollectionState.html',{prodId : getRequest("prodId")}, function(data) {
		if (data.res_code == '0') {
			if (data.type == "1") {
				$(".icon_fav").addClass("on");
			}
		} else {
			showMessage(data.message);
		}
		}, "json").error(function() {
			showError();
		});
}

$eles = {
		proStandardList:$(".proStandardList"),
		Discount_label:$(".cuxiaoTemp"),
		cuxiaoTemp: $(".cuxiaoTemp"),
		proEvaluationTabD:$(".proEvaluationTabD"),
		prodping:$("#prodping"),
		specinfo_div:$(".specinfo_div")
	}

var prodSpecInfoList ="";
var mainSpecList = "";
var detailSpecList = "";
var prodType ="";
var isSpecification = "";

function goodInfo(){
	$.ajax({
		type: "POST",
		url: APP.urls.goodInfo,
		dataType: "json",
		data: {
		    GoodsId: APP.goodsId,
		    companyId:$.cookie("sys_base_companyId")
		},
		async:false,
		success: function(res){
			if(0 == res.res_code){
				var Datas = res.Data;
				/*SKU数据*/
				skuData = Datas.SKU;
			    /*商品优惠*/
				var Info ="<div class='clearfixW'>"+
                "<span class='flW'>促销：</span>"+
                "<div class='flW proStandardList' style='margin-top: 0'>";
				var _DataResult="";
				$.each(Datas.Discount.DisInfo,function(index,item){
					var _resData = item;
					var _Activity ="";
					_Activity += _resData.Description[0].Activity;
					_DataResult +="<div><em class='hl_red_bg'>"+_resData.Title+"</em>"+
	                   "<span class='hl_red J-m-price'>"+_Activity+"</span></div>"
				})
				if(_DataResult!=""){
					$eles.cuxiaoTemp.append(Info+_DataResult+"</div></div>");
				}
				
				/*规格*/
				var TPL_sku = "";
				prodType = res.prodType;
				isSpecification = res.isSpecification;//是否开启规格
				mainSpecList = res.prodSpecList;
				detailSpecList = res.prodSpecDetailList;
				prodSpecInfoList = res.prodSpecInfoList;
				if(prodType == 0){//普通商品
					$.each(mainSpecList,function(index,item){
						var detail_spec = "";
						var cur_main_specid = item.id;
						$.each(detailSpecList,function(n,item2){
							if(item2.prodSpecId==cur_main_specid){
								detail_spec +="<label class='label_radio labelRadio'>\
									<input type='radio' class='label_specs chooseRadio' name='sku_"+(index+1)+"' curxh='"+(index+1)+"' value='"+item2.detailName+"' specid='"+item.id+"_"+item2.id+"'/>\
									<span class='spanClass'><b></b>"+item2.detailName+"</span></label>";
							}
						});
						
						TPL_sku += "<div class=\"clearfixW\" style=\"padding-top:6px;\">"
										+"<span class=\"flW \">"+item.specificationName+"：</span>"

										+"<div class=\"flW proStandardList prodspecdetaildiv_"+(index+1)+"\">"
											+ detail_spec
										+"</div>"
									+"</div>";
						
					});
				}else{
					TPL_sku += "<div class=\"clearfixW\" style=\"padding-top:6px;\">"
						+"<span class=\"flW \">规格：</span>"

						+"<div class=\"flW proStandardList\">"
							+"<label class='label_radio labelRadio'>\
								<input type='radio' class='chooseRadio' name='sku_1' curxh='1' value='礼盒'/>\
								<span class='spanClass'><b></b>礼盒</span></label>"
						+"</div>"
					+"</div>";
				}
				
				
				$eles.specinfo_div.html(TPL_sku);
				
				$("#prodtype").val(prodType);//商品规格
				$("#isSpecification").val(isSpecification);//是否开启规格
				$("#maxInventory").val(res.maxInventory);
				
				if(res.maxInventory <= 0){
					$(".proStatus").html("无货");
					$(".appendShopBtn").addClass("noneProShopBtn");
				}else{
					if(prodType == 1 || isSpecification == 0){//礼盒商品  或者没有开启规格
						if(res.maxInventory < 50){
							$(".proStatus").html("仅剩 "+res.maxInventory+"件");
							$(".appendShopBtn").removeClass("noneProShopBtn");
						}
					}
				}
				/**
				if(spNotDis.length==1){
					spNotDis.first().prop("checked",'true');
					var tempC=spNotDis.first().attr("inventory");
					if(tempC<50){
						$(".proStatus").html("仅剩"+tempC+"件");
					}
					//限制购买
					if(0 == tempC){
						$(".appendShopBtn").addClass("noneProShopBtn ");
						$(".proStatus").html("无货了");
					}
					//alert($("input[name='sku_0']:not(:disabled)").first().attr("price"));
					$(".nowprice").html("￥"+parseFloat(spNotDis.first().attr("price")).toFixed(2));
					$('input:checked').next().addClass('radioActive');
				}
				*/
			  }
			}
	});
}
//添加收藏
$('.appendCollBtn').click(function() {
	$.ajax({
        type: "POST",
        url: APP.urls.fav_url,
        data: {
            type: 1
        },
        async: true,
        success: function (res){
        	if(0 == res.res_code){
        		layer.open({
    				skin : 'layer-ext-myskincoll',
    				type : 0,
    				area : [ '400px' ],
    				title : false,
    				btn : [ '查看我的收藏' ],
    				content : '您已成功收藏该商品', //这里content是一个普通的String
    				btn1 : function() {
    					window.location.href = SHOPDOMAIN+"/mycollection.html";
    					layer.closeAll();
    				}
    			});
        	}else{
        		if(res.res_code="1"){
        			//登录
        			loginP(window.location.href);
        		}
        	}
        },error:function() {
			showError();
		},
    	dataType: "json"
    });
})
//加入购物车
$(".appendShopBtn").click(function(){
	if(!$(this).hasClass("noneProShopBtn")){
		//var sepcId=$("input[name='sku_0']:not(:disabled):checked").val();
		var prodtype_t = $("#prodtype").val();
		var isSpecification_t = $("#isSpecification").val();
		var sepcId = $("#prodspecinfoid").val();
		
		if(prodtype_t == 1 || isSpecification_t == 0){//把礼盒包和 没有开启规格的产品的规格默认成0  
			sepcId ="0";
		}
		if(undefined==sepcId || "" == sepcId){
			showMessage("请选择规格！");
		}else{
		 $.ajax({
		        type: "POST",
		        url: APP.urls.add2shopcart_url,
		        data: {
		        	prodId: APP.goodsId,
		            specId: sepcId,
		            count: $(".proCount").html(),
		            companyId:$.cookie("sys_base_companyId")
		        },
		        async: true,
		        success: function (res) {
		            if(0 == res.res_code){
		            	 layer.msg("商品已添加至购物车",{
		                     skin: 'layer-ext-myskinGlobal',
		                     closeBtn:2,
		                     shade:0.3,
		                     btn:['继续购物','去结算'],
		                     cancel: function () {
		                     },
		                     btn2: function () {
		                         window.location.href=SHOPDOMAIN+"/shoppingcart.html";
		                     },
		                     time:0
		                 });
		            	 cartCount();
		             }else{
		             	if(res.res_code=="1"){
		             		//登录
		             		loginP(window.location.href);
		             	}else{
		             		showError(res.message);
		             	}
		            }
		        },
		        dataType: "json"
		    });
		}

	}
})
//评价标志位
var initPing0=0;
var initPing1=0;
var initPing2=0;
var initPing3=0;
$(".proEvaluationTabDD").click(function(){
	//1.点击li时候要切换样式
	$(this).addClass('active').siblings().removeClass(
			'active');
	//2.根据li的索引值，来确定哪个显示 哪个隐藏
	$('.proEvaluationContentList .evaList').eq(
			$(this).index()).show().siblings().hide();
	
	if(!$(this).hasClass("ischeck")){
		$(this).addClass("ischeck");
		getCommentList(1);
	}
	
});

$("#prodping").click(function(){
	if(initPing0!=0){
		return;
	}else{
		getCommentList(1);
	}
})

function getCommentList(page){
	var type=$(".active .proEvaluationTabD").attr("clstag");
	if(undefined==type){
		type=0;
	}
	$.ajax({
        type: "POST",
        url: APP.urls.comments_url,
        data: {
        	type:type,
			GoodsId:APP.goodsId,
			pageIndex:page,
			from:"pc"
        },
        async: true,
        success: function (res) {
			  if(0 == res.res_code){	
				  APP.comment.A=res.countA;
				  APP.comment.B=res.countB;
				  APP.comment.C=res.countC;
				  comment=APP.comment;
				  if(APP.comment.total>0){
					  $eles.proEvaluationTabD.eq(0).html("全部评价（"+APP.comment.total+"条）");
					  $eles.proEvaluationTabD.eq(1).html("好评（"+res.countA+"条）");
					  $eles.proEvaluationTabD.eq(2).html("中评（"+res.countB+"条）");
					  $eles.proEvaluationTabD.eq(3).html("差评（"+res.countC+"条）");
				  }else{
					  $eles.proEvaluationTabD.eq(0).html("全部评价（0条）");
					  $eles.proEvaluationTabD.eq(1).html("好评（0条）");
					  $eles.proEvaluationTabD.eq(2).html("中评（0条）");
					  $eles.proEvaluationTabD.eq(3).html("差评（0条）");
				  }
				  
				  var evalText = doT.template($("#commenttmpl").html())(res);
				  if(res.Data.length==0){
					  $(".ping"+type).html("暂无评论").addClass("unEvaList");
				  }else{
					  $(".ping"+type).html(evalText);
				  }
				  

				  $('.td2StarCount').raty({readOnly: true, width: 120,score: function() {
				      return $(this).attr('data-score');
				    }
				  });
				  //相册
				  layer.config({
					extend : 'extend/layer.ext.js'
				  });
				  layer.ready(function() {
					layer.photos({
						photos : '.imgs',
						shift : -1
					})
				  });
				  //console.log("res.Data.length"+res.Data.length);
			  }else{
				  //console.log("get comment error of type:"+ that.index);
			  }
			  
			  switch (type) {
				case 0:
					initPing0++;
					break;
				case 1:
					initPing1++
					break;
				case 2:
					initPing2++
					break;
				case 3:
					initPing3++
				break;
				default:
					break;
			}
        },
        dataType: "json"
    });
}

//左侧热销商品
function hotProList(){
	$.ajax({
		url:SHOPDOMAIN+"/interfaces/newproductList.html",
		type:"post",
		data: {
        },
		dataType:"json",
		success:function(result){
			var productList = doT.template($("#h_hotProList").html())(result);
			$(".hotProList").append(productList);
		}
    });
}
