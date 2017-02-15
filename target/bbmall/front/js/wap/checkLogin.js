if (userId == "") {
	window.location.href = "login.html";
}else{
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() { WeixinJSBridge.call('hideOptionMenu'); });
}
