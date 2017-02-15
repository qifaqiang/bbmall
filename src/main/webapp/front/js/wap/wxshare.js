wx.ready(function () {
	wx.showOptionMenu();
	wx.onMenuShareTimeline({// 获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
	    title: WXshare.title, // 分享标题
	    link: WXshare.link, // 分享链接
	    imgUrl: WXshare.imgUrl, // 分享图标
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    	
	    }
	});
	
	wx.onMenuShareAppMessage({// 获取“分享给朋友”按钮点击状态及自定义分享内容接口
		title:  WXshare.title, // 分享标题
	    link: WXshare.link, // 分享链接
		imgUrl:  WXshare.imgUrl, // 分享图标
		desc: WXshare.desc, // 分享描述
	    type: '', // 分享类型,music、video或link，不填默认为link
	    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
	
	 //分享给QQ
    wx.onMenuShareQQ({
    	title:  WXshare.title, // 分享标题
	    link: WXshare.link, // 分享链接
		imgUrl:  WXshare.imgUrl, // 分享图标
		desc: WXshare.desc, // 分享描述
        success: function () {
            // 用户确认分享后执行的回调函数
            weiKeShare();
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        }
    });
    
    //发送到腾迅微博
    wx.onMenuShareWeibo({
    	title:  WXshare.title, // 分享标题
	    link: WXshare.link, // 分享链接
		imgUrl:  WXshare.imgUrl, // 分享图标
		desc: WXshare.desc, // 分享描述
        success: function () {
            if (window.goodsId)
                weiKeShare();
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        }
    });
    
	wx.hideMenuItems({
	      menuList: [
	        'menuItem:readMode', // 阅读模式
	        'menuItem:share:facebook', 
	        'menuItem:share:QZone', 
	        'menuItem:originPage', 
	        'menuItem:share:email',
	        'menuItem:share:brand',
	        'menuItem:copyUrl' // 复制链接
	      ],
	      success: function (res) {
	      },
	      fail: function (res) {
	      }
	    });
});