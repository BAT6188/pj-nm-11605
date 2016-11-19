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
            loadPortStatusHistory(treeNode.parentCode,treeNode.code,treeNode.name);
        },
        onAsyncSuccess: function(event, treeId, treeNode, msg) {
            var node = treeObj.getNodeByTId("enterpriseZTree_2");
            if(node){
                treeObj.expandAll(true);
                treeObj.selectNode(node,false);
                loadPortStatusHistory(node.parentCode,node.code,node.name);
            }else{
                Ewin.alert({message:"没有查询到与该企业相关的排口信息!<br/>稍后将自动返回...",hideTimes:1500}).on(function(e){
                    $('#level2content').html(pageUtils.loading()); // 设置页面加载时的loading图片
                    $('#level2content').load(rootPath+"/container/gov/monitor/enterpriseMointor/mainMonitor.jsp"); // ajax加载页面
                });
            }
        }
    }
};
function loadPortStatusHistory(parentCode,code,name){
    switch(parentCode){
        case "gasPort":
            pageUtils.loadPageOfContent("#level3MenuContent",rootPath+"/container/gov/monitor/enterpriseMointor/gasPortStatusHistory.jsp?id="+code);
            break;
        case "waterPort":
            pageUtils.loadPageOfContent("#level3MenuContent",rootPath+"/container/gov/monitor/enterpriseMointor/waterPortStatusHistory.jsp?id="+code);
            break;
        default:
            if(code=="gasPort"){
                pageUtils.loadPageOfContent("#level3MenuContent",rootPath+"/container/gov/monitor/enterpriseMointor/gasPortStatusHistory.jsp");
            }else if(code=="waterPort"){
                pageUtils.loadPageOfContent("#level3MenuContent",rootPath+"/container/gov/monitor/enterpriseMointor/waterPortStatusHistory.jsp");
            }
            break;
    }
}
var treeObj = $.fn.zTree.init($("#enterpriseZTree"), setting);


