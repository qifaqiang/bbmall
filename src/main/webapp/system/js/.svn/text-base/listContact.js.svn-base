$(document).ready(function() {

	// 弹窗——批量删除联系人
	$(document).on("click",".listContact_removeObjectSignle",function() {
		var delId=$(this).attr("id");
		 
		var title=$(this).attr("title");
		 
		 bootbox.dialog({
			 
			 message: "确定要删除这个"+title+"?",
			 title: "提示",
			 buttons: {
			 success: {
			 label: "取消!",
			 className: "white",
			 callback: function() {
			 }
			 },
			 main: {
			 label: "确定!",
			 className: "blue",
			 callback: function() {
				 
				 var DATA = '';
					DATA = '{"CTIDS":"'+delId+'","EXCLUSION":"false"}';
					$.ajax({
						url: "delone.html",
						type: "post",
						data: {
							"DATA": DATA
						},
						beforeSend:function(){
							$('body').append('<div class="tips_cover"></div><div class="cover_content"><div class="coverIng"><span>正在删除'+title+'，请稍候...</span></div></div>');
						},
						success: function(data){
							if (eval("("+data+")").flag) {
								$('.cover_content').empty().append('<div class="cover_after"><span>成功删除'+title+'~</span></div>');
								window.location.href=window.location.href;
							}else{
								$('.cover_content').empty().append('<div class="cover_after"><span>不能删除'+title+'~</span></div>');
								window.location.href=window.location.href;
							}
						
						}
					});
			 }
			 }
			 } 
		 });
	});
	
	// 弹窗——批量删除联系人
	$(document).on("click",".listContact_removeObjectSignleDouble",function() {
		var delId=$(this).attr("id");
		var title=$(this).attr("title");
		
		 bootbox.dialog({
			 message: "确定要删除这个"+title+"?",
			 title: "提示",
			 buttons: {
			 success: {
			 label: "取消!",
			 className: "white",
			 callback: function() {
			 }
			 },
			 main: {
			 label: "确定!",
			 className: "blue",
			 callback: function() {
				 bootbox.dialog({
					 message: "确定要删除这个"+title+"?",
					 title: "再次提示",
					 buttons: {
					 success: {
					 label: "取消!",
					 className: "white",
					 callback: function() {
					 }
					 },
					 main: {
					 label: "确定!",
					 className: "blue",
					 callback: function() {
						 var DATA = '';
							DATA = '{"CTIDS":"'+delId+'","EXCLUSION":"false"}';
								$.ajax({
									url: "/system/syscompany/delone.html",
									type: "post",
									data: {
										"DATA": DATA
									},
									beforeSend:function(){
										$('body').append('<div class="tips_cover"></div><div class="cover_content"><div class="coverIng"><span>正在删除'+title+'，请稍候...</span></div></div>');
									},
									success: function(data){
										if (eval("("+data+")").flag) {
											$('.cover_content').empty().append('<div class="cover_after"><span>成功删除'+title+'~</span></div>');
											window.location.href=window.location.href;
										}
									}
								});
					 		}
					 	}
					 } 
				 });
				 
			 }
			 }
			 } 
		 });
	});
	
	// 弹窗——批量删除联系人
	$(document).on("click",".listContact_up",function() {
		var types=$(this).attr("type");
		var id=$(this).attr("id");
		if(types=="1"){
			$(".p_"+id).fadeOut();
			$(this).attr("type","0");
			$(this).html("展开<i class='fa fa-angle-double-down'></i>");
			//fa-angle-double-up
		}else{
			$(".p_"+id).fadeIn();
			$(this).attr("type","1");
			$(this).html("收起<i class='fa fa-angle-double-up'></i>");
		}
	});
	
	// 弹窗——批量删除联系人
	$(document).on("click",".listContact_removeObjectWinning",function() {
		var delId=$(this).attr("id");
		var title=$(this).attr("title");
		 bootbox.dialog({
			 message: "确定要删除这个"+title+"?",
			 title: "提示",
			 buttons: {
			 success: {
			 label: "取消!",
			 className: "white",
			 callback: function() {
			 }
			 },
			 main: {
			 label: "确定!",
			 className: "blue",
			 callback: function() {
					 var DATA = '';
						DATA = '{"CTIDS":"'+delId+'","EXCLUSION":"false"}';
						$.ajax({
							url: "delonewinning.html",
							type: "post",
							data: {
								"DATA": DATA
							},
							beforeSend:function(){
								$('body').append('<div class="tips_cover"></div><div class="cover_content"><div class="coverIng"><span>正在删除'+title+'，请稍候...</span></div></div>');
							},
							success: function(data){
								if (eval("("+data+")").flag) {
									$('.cover_content').empty().append('<div class="cover_after"><span>成功删除'+title+'~</span></div>');
									window.location.href=window.location.href;
								}
							}
						});
			 		}
			 	}
			 } 
		 });
	});
});


function alertError(content){
	bootbox.dialog({
		message : content,
		title : "提示",
		buttons : {
			success : {
				label : "取消!",
				className : "white",
				callback : function() {
				}
			},
			main : {
				label : "确定!",
				className : "blue",
				callback : function() {
				}
			}
		}
	});
}