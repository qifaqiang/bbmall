<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>电商平台</title>

</head>
<body>
<div class="clearfix"></div>
<div class="container-fluid" style="max-width:775px; padding-right: 15px;
  padding-left: 15px;
  margin-right: auto;
  margin-left: auto;">
    <div class="">
        <img src="${domain}/front/images/emailzhuangshi.jpg" class="img-responsive" style="width: 100%;display: block;
  max-width: 100%;
  height: auto;" alt=""/>
    </div>
    <div class="contain" style="background-color: #fff;
    padding-top: 15px;
    overflow: hidden;
    padding-left: 10px;
    padding-right: 10px;">
        <div class="col-md-offset-1 col-md-10 p_login" style=" margin-left: 8.33333333%;.col-md-10 {
    width: 83.33333333%;
  }">
            <h1 style=" margin-top:30px;
    margin-bottom: 30px;
    font-size: 25px;
    color: #fb9006;
    font-weight: bold;">恭喜您成为电商平台公益志愿者</h1>
            <p class="email_1" style="font-size: 16px;
    color: #2c2c2c;
    line-height: 25px;
    margin-bottom: 30px;">亲爱的${username}，您好！恭喜您成为电商平台公益志愿者！在这里，您可以行使您的志愿者权益，让我们一起为走失人群尽微薄之力！感谢您的参与！</p>
            <p class="email_2" style="font-size: 16px;
    color: #499322;
    line-height: 25px;">您的用户名：${login}</p>
            <p class="email_2" style="font-size: 16px;
    color: #499322;
    line-height: 25px;">您的密码：123456</p>
            <p class="email_3" style="font-size: 12px;
    color: red;">（请牢记您的用户名跟密码，有任何问题请及时与电商平台公益客服联系。）</p>
            <a href="${domain}/system/login.html" class="btn submitBtn email_4"style="background-color: #f49600;
            display: inline-block;
            text-decoration: none;
  padding: 6px 12px;
  margin-bottom: 0;
  font-size: 14px;
  font-weight: normal;
  line-height: 1.42857143;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  -ms-touch-action: manipulation;
      touch-action: manipulation;
  cursor: pointer;
  -webkit-user-select: none;
     -moz-user-select: none;
      -ms-user-select: none;
          user-select: none;
  background-image: none;
  border: 1px solid transparent;
  border-radius: 4px;
	color: #fff;margin-top: 25px;
    margin-bottom: 15px;" >请登录</a>
            <p class="email_5" style="   font-size: 12px;">如果无法直接登录，请点击以下链接直接登录。<a href="${domain}/system/login.html">${domain}/system/login.html</a></p>
            <hr class="divider" style="    margin-top: 55px;
    margin-bottom: 55px;"/>
            <h3>电商平台公益团队</h3>
            <p class="email_3" style="font-size: 12px;
    color: red;">(这是一封自动产生的Email，请勿自动回复。)</p>
                <div class="email_6" style="padding-top: 5px;
    font-size: 12px;
    margin-bottom: 65px;" >
                        <img src="${domain}/front/images/erweima.jpg" class="img-responsive border1"
                             alt="" style=" border: 1px solid #b0aeaf;
    margin-bottom: 15px;">
                        <p>电商平台公益APP扫描下载</p>
                </div>
        </div>
        </div>
        <script>
	        function goLogin(){
	        	window.location.href='${domain}/system/login.html';
	        }
        </script>
    </div>
</body>
</html>










