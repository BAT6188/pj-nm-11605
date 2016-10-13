/**
 * Created by root on 2016/10/8.
 */
/* 设置内容区域高度使满屏*/
function loadHeight(){
    var contents = document.querySelectorAll(".content");
    var pagenationBoxs = document.querySelectorAll(".pagenationBox");
    var tableBoxs = document.querySelectorAll(".tableBox");
    for (var i = 0, len = contents.length; i < len; i++) {
        var content = contents[i];
        content.style.height=(document.body.clientHeight - 76 -35)+"px";//右侧列表宽度自适应
    }
    for (var i = 0, len = contents.length; i < len; i++) {
        var content = contents[i];
        content.style.height=(document.body.clientHeight - 76 -35)+"px";//右侧列表宽度自适应
    }
}
window.onload=loadHeight();
window.onresize=loadHeight;

/* 侧边栏显示切换*/
$(".container").bind("mouseleave",
    function(e) {
        var w = $(this).width();
        var h = $(this).height();
        var x = (e.pageX - this.offsetLeft - (w / 2)) * (w > h ? (h / w) : 1);
        var y = (e.pageY - this.offsetTop - (h / 2)) * (h > w ? (w / h) : 1);
        var direction = Math.round((((Math.atan2(y, x) * (180 / Math.PI)) + 180) / 90) + 3) % 4;
        var eventType = e.type;
        var dirName = new Array('1','2','3','4');//上右下左
        if(e.type == 'mouseleave'){
            if(dirName[direction]=='4'){//判断为左侧移出
                $(".siderNav").show();
            }
        }
    });
$(".siderNav").mouseleave(function(){
    $(".siderNav").hide();
});
/* 内部横向小导航列表切换*/ ///菜单加载中绑定
/*$(".navList li").each(function(){
    $(this).click(function(){
        $(this).addClass("linear-hover").siblings().removeClass("linear-hover");
    });
});
$(".navList li").click(function(){
 var index = $(this).index()+1;
 var contentShow = 'content'+index;
 $("."+contentShow).show().siblings(".content").hide();
    $("."+contentShow).html("");
    $("."+contentShow).load(rootPath+"/container/gov/enterprise/solid_control_facility.jsp");
 });*/

/* 子页面子菜单切换--eg 阈值管理（污染源系统）*/
$(".subMenu-list li").each(function(){
    $(this).click(function(){
        $(this).addClass("active").siblings().removeClass("active");
    });
});
$(".subMenu-list li").click(function(){
    var index = $(this).index()+1;
    var contentShow = 'subContent'+index;
    $("."+contentShow).show().siblings(".subContent").hide();
});
/* 子页面子菜单切换--eg 图表（综合统计）*/
$(".chart-list li").each(function(){
    $(this).click(function(){
        $(this).addClass("active").siblings().removeClass("active");
    });
});
$(".chart-list li").click(function(){
    var index = $(this).index()+1;
    var contentShow = 'chartBox'+index;
    $("."+contentShow).show().siblings(".chartBox").hide();
});
/*子页面 左侧折叠菜单 **/
function MenuSwitch(className){
    this._elements = [];
    this._default = -1;
    this._className = className;
    this._previous = false;
}
MenuSwitch.prototype.setDefault = function(id){
    this._default = Number(id);
}
MenuSwitch.prototype.setPrevious = function(flag){
    this._previous = Boolean(flag);
}
MenuSwitch.prototype.collectElementbyClass = function(){
    this._elements = [];
    var allelements = document.getElementsByClassName("menuDiv");
    for(var i=0;i<allelements.length;i++){
        var mItem = allelements[i];
        if (typeof mItem.className == "string" && mItem.className == this._className){
            var h3s = mItem.getElementsByTagName("h3");
            var uls = mItem.getElementsByTagName("ul");
            if(h3s.length == 1 && uls.length == 1){
                h3s[0].style.cursor = "hand";
                if(this._default == this._elements.length){
                    uls[0].style.display = "block";
                }else{
                    uls[0].style.display = "none";
                }
                this._elements[this._elements.length] = mItem;
            }
        }
    }
}
MenuSwitch.prototype.open = function(mElement){
    var uls = mElement.getElementsByTagName("ul");
    uls[0].style.display = "block";
}
MenuSwitch.prototype.close = function(mElement){
    var uls = mElement.getElementsByTagName("ul");
    uls[0].style.display = "none";
}
MenuSwitch.prototype.isOpen = function(mElement){
    var uls = mElement.getElementsByTagName("ul");
    return uls[0].style.display == "block";
}
MenuSwitch.prototype.toggledisplay = function(header){
    var mItem;
    if(window.addEventListener){
        mItem = header.parentNode;
    }else{
        mItem = header.parentElement;
    }
    if(this.isOpen(mItem)){
        this.close(mItem);
    }else{
        this.open(mItem);
    }
    if(!this._previous){
        for(var i=0;i<this._elements.length;i++){
            if(this._elements[i] != mItem){
                var uls = this._elements[i].getElementsByTagName("ul");
                uls[0].style.display = "none";
            }
        }
    }
}
MenuSwitch.prototype.init = function(){
    var instance = this;
    this.collectElementbyClass();
    if(this._elements.length==0){
        return;
    }
    for(var i=0;i<this._elements.length;i++){
        var h3s = this._elements[i].getElementsByTagName("h3");
        if(window.addEventListener){
            h3s[0].addEventListener("click",function(){instance.toggledisplay(this);},false);
        }else{
            h3s[0].onclick = function(){instance.toggledisplay(this);}
        }
    }
}
/*折叠菜单 激活状态切换*/
$(".menuDiv ul li").each(function(){
    $(this).click(function(){
        $(this).addClass("curLi").siblings().removeClass("curLi");
    });
});
/*模态框垂直居中*/
function centerModals() {
    $('.modal').each(function(i) {
        var $clone = $(this).clone().css('display', 'block').appendTo('body'); var top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 2);
        top = top > 0 ? top : 0;
        $clone.remove();
        $(this).find('.modal-content').css("margin-top", top);
    });
}
$('.modal').on('show.bs.modal', centerModals);
$(window).on('resize', centerModals);