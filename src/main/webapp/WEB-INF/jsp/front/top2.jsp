<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/share.css" />
<link rel="stylesheet" href="${SHOPDOMAIN}/front/css/web/Personal.css" />
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/web/ntab.js"></script>
<script type="text/javascript" src="${SHOPDOMAIN}/front/js/web/jscarousel.js"></script>
<div class="per_toppart">
	<div class="per_top">
		<div class="per_topleft flW ">
			<div class="per_toplogo flW">
				<a href="${SHOPDOMAIN}/index.html">
				<img width="223px" height="52px" src="${SHOPDOMAIN}/front/images/web/pcLogo.png" />
				</a>
			</div>
			<div class="per_toplogotxt flW">
				<h3  id="lingquan">个人中心</h3>
					<p>
						<a href="index.html">返回首页</a>
					</p>
			</div>
		</div>
		<div class="headerSearchW frW">
			<div class="searchBoxW">
				<input type="text" value=""  id="sea" /> <a href="javascript:void(0)" class="frW  we">搜索</a>
			</div>
		</div>
	</div>
	<div class="fox"></div>
</div>
<div class="s-line"></div>
<script>
$(document).on("click",".we",function(event){
	var se=$('#sea').val();
		window.location.href="productList.html?search="+se;
});
</script>
