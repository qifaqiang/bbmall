var MessageBox = {
    show:function(){
        var mMessage = (typeof(arguments[0]) != 'undefined') ? arguments[0] : '恭喜您，商品已成功加入购物车';
        var autotime = (typeof(arguments[1]) != 'undefined') ? arguments[1] : 3500;
        var effect = (typeof(arguments[2]) != 'undefined') ? arguments[2] : 'scroll';

        var el = $('<div class="m-global-tips"><dl class="clearfix"><dt class="m-global-suctips"></dt><dd><p>操作成功！</p>'+mMessage+'</dd></dl></div>');

        $('body').append(el);
        switch(effect){
            case 'downslide' :
                el.addClass('m-ani-in').delay(autotime).show(200,function(){
                    var $this=$(this)
                    $this.removeClass('m-ani-in').addClass('m-ani-out').delay(1000).hide(0, function(){  $this.remove(); });
                });
                break;
            default:
              el.delay(autotime).fadeOut(200,function(){
                $(this).remove();
              });
              break;
        }

    }

};
