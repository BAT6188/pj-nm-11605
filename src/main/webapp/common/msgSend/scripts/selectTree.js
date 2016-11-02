var treeObj;
$('#search_condition').keydown(function(event){
    event=document.all?window.event:event;
    if((event.keyCode || event.which)==13){
        search_ztree('treeDemo1', 'search_condition');
    }
});
/**
 * 展开树
 * @param treeId
 */
function expand_ztree(treeId){
    treeObj.expandAll(true);
}
/**
 * 收起树：只展开根节点下的一级节点
 * @param treeId
 */
function close_ztree(treeId){
    var nodes = treeObj.transformToArray(treeObj.getNodes());
    var nodeLength = nodes.length;
    for (var i = 0; i < nodeLength; i++) {
        if (nodes[i].id == '0') {
            //根节点：展开
            treeObj.expandNode(nodes[i], true, true, false);
        } else {
            //非根节点：收起
            treeObj.expandNode(nodes[i], false, true, false);
        }
    }
}

/**
 * 搜索树，高亮显示并展示【模糊匹配搜索条件的节点s】
 * @param treeId
 * @param searchConditionId 文本框的id
 */
function search_ztree(treeId, searchConditionId){
    treeObj = $.fn.zTree.getZTreeObj(treeId);
    searchByFlag_ztree(treeId, searchConditionId, "");
}

/**
 * 搜索树，高亮显示并展示【模糊匹配搜索条件的节点s】
 * @param treeId
 * @param searchConditionId     搜索条件Id
 * @param flag                  需要高亮显示的节点标识
 */
function searchByFlag_ztree(treeId, searchConditionId, flag){
    //<1>.搜索条件
    var searchCondition = $('#' + searchConditionId).val();
    //<2>.得到模糊匹配搜索条件的节点数组集合
    var highlightNodes = new Array();
    if (searchCondition != "") {
        highlightNodes = treeObj.getNodesByParamFuzzy("name", searchCondition, null);
        if(highlightNodes.length==0) {
            flag=searchCondition;
        }else{
            flag=true;
        }
    }else{
        highlightNodes = null;
    }
    //<3>.高亮显示并展示【指定节点s】
    highlightAndExpand_ztree(treeId, highlightNodes, flag);
}

/**
 * 高亮显示并展示【指定节点s】
 * @param treeId
 * @param highlightNodes 需要高亮显示的节点数组
 * @param flag           需要高亮显示的节点标识
 */
var otherAddNode=null;
function highlightAndExpand_ztree(treeId, highlightNodes, flag){
    //<2>.收起树, 只展开根节点下的一级节点
    close_ztree(treeId);
    //<3>.把指定节点的样式更新为高亮显示，并展开
    if (highlightNodes!=null) {
        $("#"+treeId).find("li").hide();
        if(flag==true){
            for (var i = 0; i < highlightNodes.length; i++) {
                $("#"+treeId).find("li#"+highlightNodes[i].tId).show();
                expandParentNode(treeId,highlightNodes[i]);
            }
        }else{
            if(otherAddNode!=null){
                otherAddNode.name = "没有找到\""+flag+"\"";
                treeObj.updateNode(otherAddNode);
                $("#"+treeId).find("li#"+otherAddNode.tId).show();
            }else{
                otherAddNode=treeObj.addNodes(null, {id:"false",name:"没有找到\""+flag+"\"",icon:"common/images/ztree/Button_warning_icon.png"})[0];
            }
        }
        treeObj.expandAll(true);
    }else{
        $("#"+treeId).find("li").show();
        if(otherAddNode!=null){
            $("#"+treeId).find("li#"+otherAddNode.tId).hide();
        }
        treeObj.expandAll(true);
    }
}
function expandParentNode(treeId,node){
    var parentNode = node.getParentNode();
    if(parentNode!=null){
        $("#"+treeId).find("li#"+parentNode.tId).show();
        if(!parentNode.isFirstNode){
            expandParentNode(treeId,parentNode);
        }
    }
}
/**
 * 递归得到指定节点的父节点的父节点....直到根节点
 */
function getParentNodes_ztree(treeId, node){
    if (node != null) {
        //var treeObj = $.fn.zTree.getZTreeObj(treeId);
        var parentNode = node.getParentNode();
        console.log(parentNode);
        return getParentNodes_ztree(treeId, parentNode);
    } else {
        return node;
    }
}

/**
 * 设置树节点字体样式
 */
function setFontCss_ztree(treeId, treeNode) {
    if (treeNode.id == 0) {
        //根节点
        return {color:"#333", "font-weight":"bold"};
    } else if (treeNode.isParent == false){
        //叶子节点
        return (!!treeNode.highlight) ? {color:"#ff0000", "font-weight":"bold"} : {color:"#660099", "font-weight":"normal"};
    } else {
        //父节点
        return (!!treeNode.highlight) ? {color:"#ff0000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
    }
}