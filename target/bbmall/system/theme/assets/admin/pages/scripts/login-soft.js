//form表单下的input的name和value传值，转换成json
function tojson(formid) {
	var dataArr = new Array();
	formid
			.find("input")
			.each(
					function(i) {
						var name = $(this).attr("name");
						if (name != "" && name != undefined) {
							if ((name == "PASSWORD") || name == "OLDPASSWORD"
									|| name == "NEWPASSWORD"
									|| name == "NEWPASSWORDAGAIN") {
								if ($(this).val() != "") {
									var value = hex_md5($(this).val());
								} else {
									var value = "";
								}
							} else if ($(this).attr("type") == "checkbox") {
								var value = ($(this).attr("checked") == "checked") ? "true"
										: "false";
							} else {
								var value = $(this).val();
							}
							dataArr.push('"' + name + '":"' + $.trim(value)
									+ '"');
						}
					});
	var dataStr = dataArr.join(",");
	dataStr = '{' + dataStr + '}';
	return dataStr;
}

var Login = function() {

	var handleLogin = function() {
		$('.login-form').validate({
			errorElement : 'span', // default input error message container
			errorClass : 'help-block', // default input error message class
			focusInvalid : false, // do not focus the last invalid input
			rules : {
				username : {
					required : true
				},
				password : {
					required : true
				},
				remember : {
					required : false
				}
			},

			messages : {
				username : {
					required : "用户名必填"
				},
				password : {
					required : "密码必填"
				}
			},

			invalidHandler : function(event, validator) { // display error
				// alert on form
				// submit
				$('.alert-danger', $('.login-form')).show();
			},

			highlight : function(element) { // hightlight error inputs
				$(element).closest('.form-group').addClass('has-error'); // set
				// error
				// class
				// to
				// the
				// control
				// group
			},

			success : function(label) {
				label.closest('.form-group').removeClass('has-error');
				label.remove();
			},

			errorPlacement : function(error, element) {
				error.insertAfter(element.closest('.input-icon'));
			},

			submitHandler : function(form) {
				var formid = $("#login_items");
				var dataLogin = tojson(formid);
				$.ajax({
					type : "post",
					url : "login.html",
					data : {
						DATA : dataLogin
					},
					success : function(data) {
						if (eval("(" + data + ")").flag) {
							$(".login_error").css("display", "none");
							location.href = "index.html";
						} else {
							$('.alert-danger span').html("用户名或密码错误");
							$('.alert-danger', $('.login-form')).show();
							return false;
						}
					},
					error : function() {
						alert("error");
					}
				});
			}
		});

		$('.login-form input').keypress(function(e) {
			if (e.which == 13) {
				if ($('.login-form').validate().form()) {
					$('.login-form').submit();
				}
				return false;
			}
		});
	}

	return {
		// main function to initiate the module
		init : function() {
			handleLogin();
		}
	};

}();