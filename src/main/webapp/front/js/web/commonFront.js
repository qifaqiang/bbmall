/**
 * Created by Administrator on 2015/12/17.
 */
$(function () {
//                        二维码
    $('.phoneContent').hover(function () {
        $('.dropDownappW').show();
    }, function () {
        $('.dropDownappW').hide();
    });
    $('.dropDownappW').hover(function () {
        $(this).show();
    }, function () {
        $(this).hide();
    });

//        左侧分类列表
    $('.item').hover(function () {
        $(this).addClass('on')
        $(this).find($('.i-mlW')).show();

    }, function () {
        $(this).removeClass('on')
        $(this).find($('.i-mlW')).hide()
    });



});
//    回到顶部
$(function () {
    $("a.topLink").click(function () {
        $("html, body").animate({
            scrollTop: $($(this).attr("href")).offset().top + "px"
        }, {
            duration: 500,
            easing: "swing"
        });
        return false;
    });
});
    //    滚动距离大于100时，显示回到顶部
$(function () {
    $(window).scroll(function () {
        var scrollTop = $(document).scrollTop();
        if (scrollTop > 100) {
            $('.topPos').show();
        } else {
            $('.topPos').hide();
        }
    })
})
$(function () {
    layer.config({
        extend:['skin/myskin/style.css']
//                skin:'layer-ext-myskin'
    });
})

$(function () {
    $('.rightFixed').find('li').hover(function () {
        $(this).addClass('on');
    }, function () {
        $(this).removeClass('on');
    })
})