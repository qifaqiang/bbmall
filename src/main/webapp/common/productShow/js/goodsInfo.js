//获取信息
function getProdInfo() {
	$.post(SHOPDOMAIN+ '/interfaces/productInfo/getProductInfo.html',{prodId : getRequest("prodId")},
		function(data) {
			if (data.res_code == '0') {
				var evalText = doT.template($(
						"#rollpictmpl").html());
				$(".slides").html(
						evalText(data.prodImgList));
				var prodInfo = data.prod;
				$("#label_title").html(prodInfo.name + " "+ prodInfo.note);
				document.title = prodInfo.name + " "+ prodInfo.note;
				if(undefined==prodInfo.max_price){
					prodInfo.max_price=0;
					APP.goodsInfo.priceRange = [prodInfo.price,prodInfo.price ];
				}else{
					APP.goodsInfo.priceRange = [prodInfo.price, prodInfo.max_price ];
				}
				if(undefined==prodInfo.max_market_price){
					prodInfo.max_market_price=0;
					APP.goodsInfo.priceOriginRange = [ prodInfo.market_price,prodInfo.market_price ];
				}else{
					APP.goodsInfo.priceOriginRange = [ prodInfo.market_price,
					  								prodInfo.max_market_price ];
				}
				
				$("#label_price").html(
						(prodInfo.price).toFixed(2));
				$("#label_price_original").html(
						(prodInfo.market_price).toFixed(2));
				$("#goods_det").html(prodInfo.descn);
				$(".img_small").html(
						"<img src=" + USERIMGSRC
								+ imgZuhe(prodInfo.picuri,
										'_300') + " />");
				$(".flexslider").flexslider({
					slideshowSpeed : 2000, //展示时间间隔ms
					animationSpeed : 1000, //滚动时间ms
					touch : true, //是否支持触屏滑动(比如可用在手机触屏焦点图)
					pauseOnHover : true,//鼠标滑向滚动内容时，是否暂停滚动 是否自动播放
				});
				
				var shipFree="";
				if(data.chargeSendPrice==0){
					shipFree="免运费";
				}else if(data.chargeSendPrice==0){
					shipFree=data.chargeSendPrice+"元";
				}else{
					shipFree="购物满"+data.sendPrice+"元免运费，"+data.sendPrice+"元之内运费"+data.chargeSendPrice+"元";
				}
				$(".shipFree").html(shipFree);
				
				addLoadEvent(getPicInfo); //监听轮播事件
				WXshare = {
						title : prodInfo.name,
						link : SHOPDOMAIN + "/wap/productShow.html?prodId="+goodsId,
						desc : prodInfo.note,
						imgUrl : USERIMGSRC + imgZuhe(prodInfo.picuri, '_300')
					}
			} else {
				if(data.res_code == 'productInfo.ER5001'){
					$.dialog("alertHasOk", "", data.message, 0,function(){
						history.go(-1);
					});
				}else if(data.res_code == 'productInfo.ER5002'){
					$.dialog('alertHasOk', '', '为了更好的给您提供服务，请先选择距离您收货地最近的基地', 0,
							function() {
						window.location.href = SHOPDOMAIN + '/wap/ditu.html'
							});
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
				APP.goodsInfo.collected = true;
				$(".icon_fav").addClass("on");
			}
		} else {
			showMessage(data.message);
		}
		}, "json").error(function() {
			showError();
		});
}

//购物车数量
function getCountByUserId() {
	$.post(SHOPDOMAIN+ '/wap/shopCart/getCountByUserId.html', function(data) {
			if (data.res_code == '0') {
				APP.goodsInfo.shopcartSum = data.count;
				$("#btn_link_shopcart").attr("data-count",data.count);
			}
		}, "json").error(function() {
			showError();
	});
}

function addLoadEvent(func) {
	//将函数作为参数，此函数就是 onload 触发时需要执行的某个函数
	var oldonload = window.onload;
	//将原来的 onload 的值赋给临时变量 oldonload。
	if (typeof window.onload != "function") {
		//判断 onload 的类型是否是 function。如果已经执行window.onload=function(){...} 赋值，那么此时 onload 的类型就是 function
		//否，则说明 onload 还没有被赋值，当前任务 func 为第一个加入的任务
		window.onload = func();

		//作为第一个任务，给 onload 赋值
	} else {
		//是，则说明 onload 已被赋值，onload 中先前已有任务加入
		window.onload = function() {
			oldonload();
			func();
			//作为后续任务，追加到先前的任务后面
		}
	}
}
var imgs = new Array();

var nowImgurl = "";
function getPicInfo() {
	var wbanner = document.getElementById('w-banner');

	var imgObj = wbanner.getElementsByTagName('img'); //获取图文中所有的img标签对象

	for (var i = 0; i < imgObj.length; i++) {
		imgs.push(imgObj[i].src);

		//下面调用微信内置图片浏览组建
		imgObj[i].onclick = function() {
			//获取当前点击图片url

			nowImgurl = this.src;

			WeixinJSBridge.invoke("imagePreview", {
				"urls" : imgs,
				"current" : nowImgurl
			})
		}
	}
}

function getPicInfo1() {
	var imgs2 = new Array();
	//var wbanner = document.getElementsByClassName('commentsPic');

	var imgObj = $(".commentsPic"); //获取图文中所有的img标签对象

	for (var i = 0; i < imgObj.length; i++) {
		imgs2.push(imgObj[i].src);

		//下面调用微信内置图片浏览组建
		imgObj[i].onclick = function() {
			//获取当前点击图片url

			nowImgurl = this.src;

			WeixinJSBridge.invoke("imagePreview", {
				"urls" : imgs2,
				"current" : nowImgurl
			})
		}
	}
}
//切换规格
function chosespec(curv){
	 $("#prodspecinfoid").val("");//清理一下规格值
     $("#maxInventory").val("0");
     
     var curxh_t = $(this).attr("curxh");
     var specLen = mainSpecList.length;
     
     if(specLen > 0){//存在规格
     	var spec_vt = new Array();
     	var ck_count = $(".label_specs:checked").length;
     	
     	if(ck_count == specLen){//所有规格都选中了,开始遍历匹配的规格
     		var price_t = "";
     		var count_t = "";
     		var specInfoId_t = "";
     		var marketPrice_t = "";

            $(".label_specs:checked").each(function (n,val) {
                spec_vt.push($(val).attr("specid"));
            })
     		
     		$.each(prodSpecInfoList,function(n,val){
     			var spec_id_v = val.id_val;
     			var ck_flag = true;
     			$.each(spec_vt,function(n1,val1){
        			if(spec_id_v.indexOf(val1)==-1){
        				ck_flag = false;
        				return false;
        			}
        		});
        		if(ck_flag){
        			price_t = val.price;
        			count_t = val.inventorycount;
        			specInfoId_t = val.specInfoId;
        			marketPrice_t = val.marketPrice;
        			specnamed_t = val.specname_val;
        			return false;
        		}else{
        			if(n == prodSpecInfoList.length-1){//如果是最后一个还是false 说明就么有这个规格
        				price_t = "";
        				count_t = 0;
        				specInfoId_t = "";
        				marketPrice_t = "";
        				specnamed_t = "";
        			}
        		}
     		});
     		$("#prodspecinfoid").val(specInfoId_t);//存放选中的规格值
     		$("#maxInventory").val(count_t);//存放最大库存
     		
     		if(price_t == ""){
     			$("#label_sku_price").html("暂无该产品规格");
     			$("#label_price").html("暂无该产品规格");//现价
     			$("#label_price_original").html("");//原价
     			
     			$("#label_sku_title").html("<div>请重新选择规格</div>");
     			$("#label_sku_title2").html("<label><div>请选择规格</div></label>");
     		}else{
				$("#label_sku_price").html("￥"+parseFloat(price_t).toFixed(2));
				$("#label_price").html("<label>"+(parseFloat(price_t).toFixed(2)).split(".")[0]+"</label>."+(parseFloat(price_t).toFixed(2)).split(".")[1]);
				$("#label_price_original").html("<label>"+(parseFloat(marketPrice_t).toFixed(2))+"</label>");
				
				$("#label_sku_title").html("<div>已选择 \""+specnamed_t+"\"</div>");
				$("#label_sku_title2").html("<label><div>已选择 \""+specnamed_t+"\"</div></label>");
     		}
			if(count_t <= 0){
				$("#label_sku_Stock").html("无货");
				$("#btn_add_shopcart").removeClass("on");
			}else if(count_t < 50){
				$("#label_sku_Stock").html("仅剩"+count_t+"件");
				$("#btn_add_shopcart").addClass("on");
			}else{
				$("#label_sku_Stock").html("库存充足");
				$("#btn_add_shopcart").addClass("on");
			}
     	}else{
     		
     	}
     }
}