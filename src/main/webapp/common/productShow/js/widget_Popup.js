 var Attention = { 		    
	attentioned:0, 
	open : function(){
		if(APP.attention.val){
		   //链接地址
		   location.href=APP.attention.URL;
		}
		else{
		   //生成二维码		 
			if(document.getElementById("Attention")!=undefined){
	           document.getElementById("Attention").style.display = "block";
			   setTimeout(function(){document.getElementById("Attentioncon").style.bottom="0"},1);
			}else{
			   var div = document.createElement("div");
			   div.innerHTML = '<div data-role="widget" data-widget="Attention" id="Attention">\
			   <div></div>\
				<div id="Attentioncon"><p>长按二维码，关注公众号</p>\
				   <img src="http://open.weixin.qq.com/qr/code/?username='+APP.attention.AttentionId+'" style=" ">\
				   <h3>'+APP.limit.name+'</h3>\
				   <div id="Attention_close"><img src ="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAC0lEQVQIW2NkAAIAAAoAAggA9GkAAAAASUVORK5CYII=" /></div>\
				</div>\
			  </div>';
			   document.body.appendChild(div);				 		   
			   setTimeout(function(){document.getElementById("Attentioncon").style.bottom="0"},1);				 	
			}
			document.getElementById("Attention_close").onclick=function(){				  
			   document.getElementById("Attentioncon").style.bottom="-70%";
			   setTimeout(function(){document.getElementById("Attention").style.display = "none"},300);				  
			}
		 }	 
	}
}

var Popup = { 
		    
	  fengxianged:0, 
	  fengxiang : function(){
		  if(Popup.fengxianged){
			 document.getElementById("fengxiang").style.display = "block";
		  }else{
			 var div = document.createElement("div");
			 div.innerHTML = '<div data-role="widget" data-widget="fengxiang" id="fengxiang" style="width:100%; height:100%; background:rgba(0,0,0,0.8); z-index:1000; position:fixed; color:#fff; top:0; max-width:640px; display:block;"><div style=" overflow:hidden; margin-bottom:-15px"><img src="/vshop/Assets/imgs/widget_fengxiang.png" style="max-width:120px; float:right; margin-top:65px; margin-right:40px;"></div><section style="margin:5%; width:90%; display:block; font-size:15px;">1、点击右上角按钮<span style="display:inline-block; width:40px; background:url(/vshop/Assets/imgs/widget_icons_2.png); background-repeat:no-repeat; background-size:40px auto; height:18px; background-position:0 -385px"></span><br /><br />2、点击　<span style="display:inline-block; width:40px; background:url(/vshop/Assets/imgs/widget_icons_2.png); background-repeat:no-repeat; background-size:40px auto; height:40px; background-position:0 -236px; vertical-align:middle"></span>　发送给朋友<br /><br />&nbsp;&nbsp;　点击　<span style="display:inline-block; width:40px; background:url(/vshop/Assets/imgs/widget_icons_2.png); background-repeat:no-repeat; background-size:40px auto; height:40px; background-position:0 -290px; vertical-align:middle"></span>　分享到朋友圈<br /><br />&nbsp;&nbsp;　点击　<span style="display:inline-block; width:40px; background:url(/vshop/Assets/imgs/widget_icons_2.png); background-repeat:no-repeat; background-size:40px auto; height:40px; background-position:0 -340px; vertical-align:middle"></span>　将店铺收藏至微信<br /><br /></section></div>';
			 document.body.appendChild(div);				   
			 Popup.fengxianged =1;
		  }
		  document.getElementById("fengxiang").onclick=function(){
			   this.style.display = "none";
		  }
		},
	
	  guanzhu : function(){
		  
		 Attention.open();
	   
	  }

};   			  