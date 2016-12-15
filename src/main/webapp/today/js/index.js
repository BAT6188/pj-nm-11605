/**
 * 设置zTree树
 */
var setting = {
    async: {
        enable: true,
        url: getUrlByNodeId
    },
    check: {
        enable: false
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    view: {
        expandSpeed: ""
    },
    callback: {
        beforeExpand: beforeExpand,
        onAsyncSuccess: onAsyncSuccess,
        onAsyncError: onAsyncError,
        onClick: zTreeOnClick
    }
};
var zNodes =[
    {name:"经典视频检索", id:"1",isParent:true,iconOpen:"zTree/css/zTreeStyle/img/diy/1_open.png", iconClose:"zTree/css/zTreeStyle/img/diy/1_close.png"},
    {name:"基于内容检索", id:"2",isParent:true,iconOpen:"zTree/css/zTreeStyle/img/diy/1_open.png", iconClose:"zTree/css/zTreeStyle/img/diy/1_close.png"},
    {name:"基于语义检索", id:"3",isParent:true,iconOpen:"zTree/css/zTreeStyle/img/diy/1_open.png", iconClose:"zTree/css/zTreeStyle/img/diy/1_close.png"}
];
/**
 * @author ZhengHaibo
 * 功能：通过NodeId获得节点的孩子节点
 * 调用：当父节点展开时，调用，返回该父节点的子节点
 * 后台数据格式：JSON
 * @param treeId 树控件的Id
 * @param treeNode 树节点对象：包含Id等信息
 * @return
 */
function getUrlByNodeId(treeId, treeNode) {
    return "getNodesDataById?treeNodeId="+treeNode.id;
}
/**
 * 展开之前执行的函数
 * @param treeId
 * @param treeNode
 * @return
 */
function beforeExpand(treeId, treeNode) {
    if (!treeNode.isAjaxing) {
        ajaxGetNodes(treeNode, "refresh");
        return true;
    } else {
        alert("Loading...");
        return false;
    }
}
/**
 * 加载成功后执行的函数
 * @param event 封装了js的事件
 * @param treeId 树控件的Id
 * @param treeNode 树节点对象
 * @param msg 返回的JSON格式的消息
 * @return
 */
function onAsyncSuccess(event, treeId, treeNode, msg) {
    console.log("treeId");
    console.log(treeId);
    console.log("treeNode");
    console.log(treeNode);
    console.log("msg");
    console.log(msg);
    if (!msg || msg.length == 0) {
        return;
    }
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    treeNode.icon = "";
    zTree.updateNode(treeNode);//更新树结构
    zTree.selectNode(treeNode.children[0]);//设置为第一个子节点为选中状态
}
function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    alert("Error ! 异步获取数据异常");
    treeNode.icon = "";
    zTree.updateNode(treeNode);
}
function ajaxGetNodes(treeNode, reloadType) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    if (reloadType == "refresh") {
        treeNode.icon = "zTree/css/zTreeStyle/img/loading.gif";
        zTree.updateNode(treeNode);
    }
    zTree.reAsyncChildNodes(treeNode, reloadType, true);
}
/**
 * 功能：当点击树节点时，调用该函数
 * @param event
 * @param treeId
 * @param treeNode
 * @return
 */
function zTreeOnClick(event, treeId, treeNode) {
    console.log("zTreeOnClickLog:--------------------------------")
    console.log(treeId);
    console.log(treeNode);
    console.log(treeNode.id);
    switch(treeNode.id+""){//根据树节点的Id判断
        case "1":
        case "11":
            document.getElementById("iframepage").src="tree_click_page.jsp?treeNodeId="+treeNode.id;
            break;
        case "12":
            document.getElementById("iframepage").src="tree_click_page.jsp?treeNodeId="+treeNode.id;
            break;
        case "13":
            document.getElementById("iframepage").src="tree_click_page.jsp?treeNodeId="+treeNode.id;
            break;
        case "2":
        case "21":
            document.getElementById("iframepage").src="tree_click_page.jsp?treeNodeId="+treeNode.id;
            break;
        case "22":
            document.getElementById("iframepage").src="tree_click_page.jsp?treeNodeId="+treeNode.id;
            break;
        case "23":
            document.getElementById("iframepage").src="tree_click_page.jsp?treeNodeId="+treeNode.id;
            break;
        case "24":
            document.getElementById("iframepage").src="tree_click_page.jsp?treeNodeId="+treeNode.id;
            break;
        case "3":
        case "31":
            document.getElementById("iframepage").src="tree_click_page.jsp?treeNodeId="+treeNode.id;
            break;
    }
}
$(document).ready(function(){
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);//初始化zTree树
});