function Map() {
	this.container = new Object();
}

Map.prototype.put = function(key, value) {
	this.container[key] = value;
}

Map.prototype.get = function(key) {
	return this.container[key];
}

Map.prototype.keySet = function() {
	var keyset = new Array();
	var count = 0;
	for ( var key in this.container) {
		// 跳过object的extend函数
		if (key == 'extend') {
			continue;
		}
		keyset[count] = key;
		count++;
	}
	return keyset;
}

Map.prototype.size = function() {
	var count = 0;
	for ( var key in this.container) {
		// 跳过object的extend函数
		if (key == 'extend') {
			continue;
		}
		count++;
	}
	return count;
}

Map.prototype.remove = function(key) {
	delete this.container[key];
}

Map.prototype.toString = function() {
	var str = "";
	for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
		str = str + this.container[keys[i]] + ";";
	}
	return str;
}

/** ***********************************转换时间格式************************************ */
Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
function showm(showmess) {
	layer.msg(showmess, {
		time : 3000
	});
}
function show() {
	bootbox.dialog({
		message : "您尚未登录，请登录后再进行此操作！",
		title : "<center>提示</center>",
		buttons : {
			main : {
				label : "确定",
				className : "blue",
				callback : function() {
					window.location.href = shop_do_main + "/login.html";
				}
			}
		}
	});
}

// 购物车数量
function cartCount() {
	$.post(SHOPDOMAIN + '/wap/shopCart/getCountByUserId.html', function(data) {
		if (data.res_code == '0') {
			$("#shopcart-count-top").css("background-color", "#e74b25").html(data.count);
			$("#shopcart-count-fixed").addClass("shoppingCount").html(data.count);
		} else {
			showMessage(data.message);
		}
	}, "json").error(function() {
		showError();
	});
}

function showMessageAutoTime(message, time) {
	layer.msg(message, {
		time : time
	})
}

function showMessage(message) {
	layer.msg(message, {
		skin : 'layer-ext-myskinGlobal',
		closeBtn : 2,
		shade : 0.3,
		btn : '确定',
		time : 0
	});
}

function showMessageChooseCompany() {
	layer.msg('为了更好的给您提供服务，请先选择距离您收货地最近的基地', {
		skin : 'layer-ext-myskinGlobal',
		closeBtn : 2,
		shade : 0.3,
		btn : [ '选择基地', '随便逛逛' ],
		btn1 : function() {
			window.location.href = SHOPDOMAIN + '/ditu.html';
		},
		cancel : function() {
			setDefaultCompanyId(2);
		},
		time : 0

	});
}

function showMessageRefresh(message) {
	layer.msg(message, {
		skin : 'layer-ext-myskinGlobal',
		closeBtn : 2,
		shade : 0.3,
		btn : [ '确定' ],
		btn1 : function() {
			window.location.href = window.location.href;
		},
		time : 0
	});
}
function showMessageGoBack(message) {
	layer.msg(message, {
		skin : 'layer-ext-myskinGlobal',
		closeBtn : 2,
		shade : 0.3,
		btn : [ '确定' ],
		btn1 : function() {
			history.go(-1);
		},
		time : 0
	});
}
function showMessageGoBack1(message) {
	layer.msg(message, {
		time : 5
	});
}

function showError(data) {
	if (data == undefined || data == "") {
		layer.msg("网络中断……", {
			skin : 'layer-ext-myskinGlobal',
			closeBtn : 2,
			shade : 0.3,
			btn : '确定',
			time : 0
		});
	} else {
		layer.msg(data, {
			skin : 'layer-ext-myskinGlobal',
			closeBtn : 2,
			shade : 0.3,
			btn : '确定',
			time : 0
		});
	}

}

function imgZuhe(imgurl, size) {
	if (undefined == imgurl || imgurl == "") {
		return "";
	} else {
		var fisrt = imgurl.substring(0, imgurl.indexOf('.'));
		var end = imgurl.substring(imgurl.indexOf('.'), imgurl.length);
		return fisrt + size + end;
	}
}

function getRequest(param) {
	var url = location.search; // 获取url中"?"符后的字串
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
		}
	}
	return theRequest[param];
}

$(function() {
	// 二维码
	$('.phoneContent').hover(function() {
		$('.dropDownappW').show();
	}, function() {
		$('.dropDownappW').hide();
	});
	$('.dropDownappW').hover(function() {
		$(this).show();
	}, function() {
		$(this).hide();
	});

	// 左侧分类列表
	$('.item').hover(function() {
		$(this).addClass('on')
		$(this).find($('.i-mlW')).show();

	}, function() {
		$(this).removeClass('on')
		$(this).find($('.i-mlW')).hide()
	});
	$(".baseNameW").html($.cookie('sys_company_name'));
	// 回到顶部
	$("a.topLink").click(function() {
		$("html, body").animate({
			scrollTop : $($(this).attr("href")).offset().top + "px"
		}, {
			duration : 500,
			easing : "swing"
		});
		return false;
	});
	// 滚动距离大于100时，显示回到顶部
	$(window).scroll(function() {
		var scrollTop = $(document).scrollTop();
		if (scrollTop > 100) {
			$('.topPos').show();
		} else {
			$('.topPos').hide();
		}
	})
	layer.config({
		extend : [ 'skin/myskin/style.css' ]
	// skin:'layer-ext-myskin'
	});
	$('.inputStyle').focus(function() {
		$(this).parent().addClass('itemFocus');
	})
	$('.inputStyle').blur(function() {
		$(this).parent().removeClass('itemFocus');
	});
})

var JPlaceHolder = {
	// 检测
	_check : function() {
		return 'placeholder' in document.createElement('input');
	},
	// 初始化
	init : function() {
		if (!this._check()) {
			this.fix();
		}
	},
	// 修复
	fix : function() {
		jQuery(':input[placeholder]').each(function(index, element) {
			var self = $(this), txt = self.attr('placeholder');
			self.wrap($('<div></div>').css({
				position : 'relative',
				zoom : '1',
				border : 'none',
				background : 'none',
				padding : 'none',
				margin : 'none'
			}));
			var pos = self.position(), h = self.outerHeight(true), paddingleft = self.css('padding-left');
			var holder = $('<span></span>').text(txt).css({
				position : 'absolute',
				left : pos.left,
				top : '6px',
				height : '30px',
				lienHeight : '30px',
				paddingLeft : paddingleft,
				color : '#aaa'
			}).appendTo(self.parent());
			self.focusin(function(e) {
				holder.hide();
			}).focusout(function(e) {
				if (!self.val()) {
					holder.show();
				}
			});
			holder.click(function(e) {
				holder.hide();
				self.focus();
			});
		});
	}
};
function timeStamp2String(time) {
	var datetime = new Date("yyyy-MM-dd HH:mm:ss");
	if (time != undefined) {
		datetime.setTime(time);
		var year = datetime.getFullYear();
		var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
		var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
		var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime.getHours();
		var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
		var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
		return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
	}
}
$(function() {
	if (typeof (userId) != "undefined") {
		if ("" != userId) {
			cartCount();
		}
	}
	;
	$(".w-exitBtn").click(function() {
		$.cookie("token", null);
		window.location.href = SHOPDOMAIN + "/logoutpc.html";
	});

})