//@ sourceURL = common/scripts/main.js
pageUtils.loadMenu(function (mainMenu, subMenu) {
    var siderUl = $(".siderNav>ul");
    //加载一级主菜单
    for (var i = 0; i < mainMenu.length; i++) {
        var menu = mainMenu[i];
        var li = $('<li data-main-code="' + menu.code + '"><a href="javascript:void(0);"><dl><dt><img src="' + rootPath + '/common/images/side-' + menu.code + '-icon.png" alt=""/></dt><dd>' + menu.text + '</dd></dl></a></li>');
        siderUl.append(li);
    }
    loadLevel2Menu(subMenu[menuCode]);
    siderUl.find("li").bind('click', function () {
        firsetLoad = false;
        var mainMenuCode = $(this).attr("data-main-code");
        loadLevel2Menu(subMenu[mainMenuCode]);
    });


});
/**
 * 加载子菜单
 * @param subMenus
 */
function loadLevel2Menu(subMenus) {
    var level2MenuUl = $("#level2Menu");
    if (subMenus && subMenus.length > 0) {
        level2MenuUl.html("");
        for(var i = 0; i < subMenus.length;i++) {
            var smenu = subMenus[i];
            var li = $('<li data-menu-id="'+smenu.id+'" data-url="'+smenu.url+'"><a href="javascript:void(0);" >'+smenu.text+'</a></li>');
            level2MenuUl.append(li);
        }
        //绑定事件
        level2MenuUl.find("li").bind('click',function () {
            $(window).trigger("menuchange",[this]);
            $(this).addClass("linear-hover").siblings().removeClass("linear-hover");
            var url = $(this).attr("data-url");
            $("#level2content").html("");
            if (url && url.indexOf(".jsp")>0) {
                $("#level2content").load(url);
            }

        });
        //加载第一个菜单
        var isUserClick = (!subMenuId || !firsetLoad);
        if (isUserClick) {
            level2MenuUl.find("li:first").click();
        }else{//指定菜单跳转
            level2MenuUl.find("li[data-menu-id='"+subMenuId+"']").click();
        }

    }
}
//定义短信发送modal


$("#mainSmsSendBtn").bind('click', function () {
    var options = {
        title: "短信发送",//弹出框标题(可省略，默认值：“人员选择”)
        width:"60%"
    };
    var model = $.fn.MsgSend.init(2,options,function(e,data){ //短信发送第一个参数为2
    });
    model.open();//打开dialog,
});
//消息弹窗
MessageDialog.modal({"userId":userId,countElement:$("#msgCountSpan")});
$("#msgListBtn").bind("click",function () {
    MessageDialog.modal("show");
});
