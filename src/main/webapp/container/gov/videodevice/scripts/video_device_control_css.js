/*-----------------------------------------------------------------------------------*/
/*	进度条控制
 /*-----------------------------------------------------------------------------------*/
(function($){
    $('.progressBar').each(function(){
        var width = 140;
        var $box = $(this);
        var $bg =$box.find(".bg");
        var $bgcolor = $box.find('.bgcolor');
        var $btn = $box.find('.bt');
        var $text = $box.find('.text');
        var statu = false;
        var ox = 0;
        var lx = 0;
        var left = 0;
        var bgleft = 0;
        $btn.mousedown(function(e){
            lx = $btn.offset().left;
            ox = e.pageX - left;
            statu = true;
        });
        $(document).mouseup(function(){
            statu = false;
        });
        $box.mousemove(function(e){
            if(statu){
                left = e.pageX - ox;
                if(left < 0){
                    left = 0;
                }
                if(left > width){
                    left = width;
                }
                $btn.css('left',left);
                $bgcolor.width(left);
                $text.html( parseInt(left*100/width) + '%');
            }
        });
        $bg.click(function(e){
            if(!statu){
                bgleft = $bg.offset().left;
                left = e.pageX - bgleft;
                if(left < 0){
                    left = 0;
                }
                if(left > width){
                    left = width;
                }
                $btn.css('left',left);
                $bgcolor.stop().animate({width:left},width);
                $text.html( parseInt(left*100/width) + '%');
            }
        });
    });
})(jQuery);
/*-----------------------------------------------------------------------------------*/
/*	video操作按钮/录制停止
 /*-----------------------------------------------------------------------------------*/
$(".controlIcon").click(function(){
    $(this).toggleClass("pause");
});
$(".btnList a").click(function(){
    $(this).toggleClass("active");
});
/*-----------------------------------------------------------------------------------*/
/*	云台控制高度调整
 /*-----------------------------------------------------------------------------------*/
function pageHeight(){
    var clientH = document.body.clientHeight;
    var lines = document.querySelectorAll(".inner p");
    var progressBars = document.querySelectorAll(".progressBar");
    var direction = document.querySelector(".direction");
    if(clientH < 621){
        for (var i = 0, len = lines.length; i < len; i++) {
            var line = lines[i];
            line.style.lineHeight = "20px";
        }
        for (var i = 0, len = progressBars.length; i < len; i++) {
            var progressBar = progressBars[i];
            progressBar.style.height = "18px";
        }
        direction.style.marginTop = "0px";
        direction.style.marginBottom = "0px";
    }
}
window.onload = pageHeight();
window.onresize = pageHeight;
/*-----------------------------------------------------------------------------------*/
/*	方向盘控制
 /*-----------------------------------------------------------------------------------*/
$(".direction").on("click","a",function(event){
    event = event ? event : window.event;
    var obj = event.srcElement ? event.srcElement : event.target;
    var objName = obj.className;
    switch (objName){
        case'topDir':
            $(".direction").css({
                transition:"backgroundImage .6s",
                webkitTransition:"backgroundImage .6s",
                oTransition:"backgroundImage .6s",
                msTransition:"backgroundImage .6s",
                mozTransition:"backgroundImage .6s",
                backgroundImage:"url(image/north-mini.png)"
            });
            break;
        case'bottomDir':
            $(".direction").css({
                transition:"backgroundImage .6s",
                webkitTransition:"backgroundImage .6s",
                oTransition:"backgroundImage .6s",
                msTransition:"backgroundImage .6s",
                mozTransition:"backgroundImage .6s",
                backgroundImage:"url(image/south-mini.png)"
            });
            break;
        case'leftDir':
            $(".direction").css({
                transition:"backgroundImage .6s",
                webkitTransition:"backgroundImage .6s",
                oTransition:"backgroundImage .6s",
                msTransition:"backgroundImage .6s",
                mozTransition:"backgroundImage .6s",
                backgroundImage:"url(image/west-mini.png)"
            });
            break;
        case'rightDir':
            $(".direction").css({
                transition:"backgroundImage .6s",
                webkitTransition:"backgroundImage .6s",
                oTransition:"backgroundImage .6s",
                msTransition:"backgroundImage .6s",
                mozTransition:"backgroundImage .6s",
                backgroundImage:"url(image/east-mini.png)"
            });
            break;
        default:
            return false;
    }
});