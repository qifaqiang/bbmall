define(function(require, exports, module){
    var $ = require("lib_cmd/zepto"),
    	myDialog = require("lib_cmd/myDialog"),		
		_APP = window.APP||{};
		window.$ = $,
		$eles = {},
		ele = {};

	//
	ele = (function(){
		function Ele(){
			var loading_bottom = '<div data-role="widget" data-widget="loading_bottom" id="loading_bottom" class="loading_bottom">\
							<div class="widget_wrap">\
								<ul class="tbox">\
									<li>\
										<div class="loading_wrap">\
											<span class="loading">&nbsp;</span>\
										</div>\
									</li>\
									<li>\
										正在加载，请稍后...\
									</li>\
								</ul>\
								<ul class="tbox">\
									<li></li>\
									<li style="text-align:center;">\
										没有更多了\
									</li>\
								</ul>\
							</div>\
						</div>';
			var img_src = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAC0lEQVQIW2NkAAIAAAoAAggA9GkAAAAASUVORK5CYII=";
			
			this.loading_bottom = loading_bottom;
			this.img_src = img_src;
		}
		//
		return new Ele();
	})();
	
	//window.onload事件	
	function addLoadEvent(func){ 
     var oldonload=window.onload; 
		if(typeof window.onload!='function'){ 
			window.onload=func; 
		}else{ 
			window.onload=function(){ 
				oldonload(); 
				func(); 
			} 
		} 
	} 
	
	module.exports = {
		ele: ele,
		addLoadEvent:addLoadEvent
	}
});