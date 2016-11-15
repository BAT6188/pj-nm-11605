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
        url:rootPath + "/action/S_enterprise_Enterprise_getFumesEnterprisePortZtree.action",
        autoParam:["code"],
        otherParam:{"code":enterpriseId},
        dataFilter: null
    },
    callback: {
        onClick: function(event, treeId, treeNode){
            loadPortStatusHistory(treeNode.parentCode,treeNode.code,treeNode.name);
        },
        onAsyncSuccess: function(event, treeId, treeNode, msg) {
            treeObj.expandAll(true);
            var node = treeObj.getNodeByTId("enterpriseZTree_3");
            treeObj.selectNode(node,false);
            pageUtils.loadPageOfContent("#level3MenuContent",rootPath+"/container/gov/monitor/fumesMonitor/fumesPortStatusHistory.jsp?id="+node.code);
        }
    }
};
var treeObj = $.fn.zTree.init($("#enterpriseZTree"), setting);


