var UIToastr = function() {
	var kk = function(text) {
		alert(text);
	}
	return {
		// main function to initiate the module
		init : function() {

			var i = -1, toastCount = 0, $toastlast, getMessage = function() {
				i++;
				if (i === msgs.length) {
					i = 0;
				}

				return msgs[i];
			};
		},
		kk2 : function(title,msg,shortCutFunction) {
			toastr.options = {
				"closeButton" : true,
				"debug" : false,
				"positionClass" : "toast-top-right",
				"onclick" : null,
				"showDuration" : "1000",
				"hideDuration" : "1000",
				"timeOut" : "5000",
				"extendedTimeOut" : "1000",
				"showEasing" : "swing",
				"hideEasing" : "linear",
				"showMethod" : "fadeIn",
				"hideMethod" : "fadeOut"
			};

			$("#toastrOptions").text(
					"Command: toastr[" + shortCutFunction + "](\"" + msg
							+ (title ? "\", \"" + title : '')
							+ "\")\n\ntoastr.options = "
							+ JSON.stringify(toastr.options, null, 2));

			var $toast = toastr[shortCutFunction](msg, title);
			$toastlast = $toast;
		}

	};

}();