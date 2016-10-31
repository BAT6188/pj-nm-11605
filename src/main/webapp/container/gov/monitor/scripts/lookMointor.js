/**
 * Created by Administrator on 2016/10/28.
 */
$(".scrollContent").slimScroll({
    height:"100%",
    railOpacity:.9,
    alwaysVisible:!1
});
var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "code",
            pIdKey: "parentCode",
            rootPId: -1
        }
    },
    height:500,
    width:200,
    view: {
        showLine: false
    },
    async: {
        enable: true,
        url:rootPath + "/action/S_enterprise_Enterprise_getEnterprisePortZtree.action",
        autoParam:["code"],
        otherParam:{"code":enterpriseId},
        dataFilter: null
    },
    callback: {
        onClick: function(event, treeId, treeNode){
            console.log("事件---------------------");
            console.log(event);
            console.log("treeId---------------------");
            console.log(treeId);
            console.log("treeNode---------------------");
            console.log(treeNode);
            loadPortStatusHistory(treeNode.parentCode,treeNode.code,treeNode.name);
        }
    }
};
function loadPortStatusHistory(parentCode,code,name){
    switch(parentCode){
        case "gasPort":
            pageUtils.loadPageOfContent("#level3MenuContent",rootPath+"/container/gov/monitor/gasPortStatusHistory.jsp?id="+code+"&name="+name);
            break;
        case "waterPort":
            Ewin.alert("waterPort暂无页面！");
            break;
        case "noisePort":
            Ewin.alert("noisePort暂无页面！");
            break;
        case "fumesPort":
            Ewin.alert("fumesPort暂无页面！");
            break;
        default:
            break;
    }
}
$.fn.zTree.init($("#enterpriseZTree"), setting);
