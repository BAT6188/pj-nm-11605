//@ sourceURL=index.js
var mainMenu = [
    {
        code:"company",
        text:"首页"
    }
];
var subMenu = {
    "company":[
        {
            id:"1",
            text:"首页",
            url: rootPath+"/container/company/homePage/homePage.jsp"
        },
        {
            id:"2",
            text:"预警及排污超标处理情况报送",
            url:rootPath+"/container/company/warningExcessive/resPortStatusHistory.jsp"
        },
        {
            id:"3",
            text:"隐患自查自报",
            url:rootPath+"/container/company/dangerInspection/dangerInspection.jsp"
        },
        {
            id:"",
            text:"信息公告",
            url:""
        },
        {
            id:"5",
            text:"一企一档企业台账",
            url:rootPath+"/container/company/companyAccount/mainEnterprise.jsp"
        }
    ]
};
loadMenu();
function toUrl(url){
    var subMenuId = "";
    for (var i = 0; i < subMenu["company"].length; i++) {
        var sm = subMenu["company"][i];
        if (sm.url && sm.url.indexOf(url) != -1) {
            subMenuId = sm.id;
        }
    }
    if (subMenuId) {
        $("#level2Menu").find("li[data-menu-id='"+subMenuId+"']").click();
    }else{
        Ewin.alert("未找到地址");
    }
}
function loadMenu() {

    var siderUl = $(".siderNav>ul");
    //加载一级主菜单
    // for (var i = 0; i < mainMenu.length; i++) {
    //     var menu = mainMenu[i];
    //     var li = $('<li data-main-code="' + menu.code + '"><a href="javascript:void(0);"><dl><dt><img src="' + rootPath + '/common/images/company.ico" alt=""/></dt><dd>' + menu.text + '</dd></dl></a></li>');
    //     siderUl.append(li);
    // }
    loadLevel2Menu(subMenu["company"]);
    siderUl.find("li").bind('click', function () {
        var mainMenuCode = $(this).attr("data-main-code");
        loadLevel2Menu(subMenu[mainMenuCode]);
    });


}
/**
 * 加载子菜单
 * @param subMenus
 */
function loadLevel2Menu(subMenus) {
    debugger;
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
$("#level2content").unbind("mouseleave");
$(".siderNav").unbind("mouseleave");