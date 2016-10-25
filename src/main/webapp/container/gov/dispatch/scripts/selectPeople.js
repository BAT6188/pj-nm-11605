/**
 * Created by Administrator on 2016/10/17.
 */

//-------------选择人员 ztree配置--------------------//
$(".scrollContent").slimScroll({
    height:"100%",
    railOpacity:.9,
    alwaysVisible:!1
});

var setting = {
    height:500,
    width:200,
    view: {
        showLine: true
    },
    async: {
        enable: true,
        url:rootPath + "/action/S_dispatch_MonitorCase_getOrgPersonList.action",
        // url:rootPath + "/container/gov/dispatch/selectPeople.json",
        autoParam:["id", "name=n", "level=lv"],
        otherParam:{"otherParam":"zTreeAsyncTest"},
        dataFilter: filter
    },
    callback: {
        onClick: zTreeOnClick
    }
};
function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i=0, l=childNodes.length; i<l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
}
$.fn.zTree.init($("#treeDemo1"), setting);
var treeObj = $.fn.zTree.getZTreeObj("treeDemo1");
setTimeout(function () {
    treeObj.expandAll(true);
},2000)





//-------------选择人员 table配置--------------------//
var gridSelectPeopleTable = $('#selectPeopleTable');
function initSelectPeopleTable() {
    gridSelectPeopleTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        height: 540,
        clickToSelect:true,//单击行时checkbox选中
        columns: [
            {
                title:"全选",
                checkbox: true,
                align: 'center',
                radio:false,  //  true 单选， false多选
                valign: 'middle'
            },{
                title: 'id',
                field: 'id',
                sortable: false,

                visible:false
            }, {
                title: '已选名单',
                field: 'name',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '职务',
                field: 'job',
                editable: false,
                sortable: false,
                align: 'center'
            }
        ]
    });
}

initSelectPeopleTable();

/**
 * 获取列表所有的选中数据id
 * @returns {*}
 */
function getIdsSelectionsFromGridSelectPeople() {
    return $.map(gridSelectPeopleTable.bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}

/**
 * 往表格中添加数据，如果已有这条数据则忽略
 */
function appendToGrid(treeNode) {
    if(!map.isHave(treeNode.id)){
        map.put(treeNode.id,treeNode.id);
        gridSelectPeopleTable.bootstrapTable("append",treeNode)
        gridSelectPeopleTable.bootstrapTable('checkAll');
    }
}

/**
 * 从表格中移除数据
 */
function removeFromGrid() {
    gridSelectPeopleTable.bootstrapTable('removeAll');
    map.removeAll();
}

function zTreeOnClick(event, treeId, treeNode) {
    if(!treeNode.parent){
        appendToGrid(treeNode);
        console.log("appendToGrid:"+JSON.stringify(treeNode))
    }

};

/**
 * 调度单点击发送按钮
 * @param flag  1-监控中心，监控办公室的调度单   2-执法管理列表的调度单
 */
function sendToBtn(flag) {
    var ids=getIdsSelectionsFromGridSelectPeople();
    if(ids.length==0){
        //TODO 如果没有选择人员时，点发送按钮，应该提示 选择人员，同时选择人员对话框 保持不关
        //Ewin.alert({message:"请选择人员"});
        return;
    }

    var data=$.param({ids:ids},true)

    var url;
    if(1==flag){
        var monitorCaseId=$("#monitorCaseId").val();
        data+="&monitorCaseId="+monitorCaseId;
        url=rootPath + "/action/S_dispatch_DispathTask_save.action"
    }else if(2==flag){
        var dispathTaskId=$("#dispathTaskId").val();
        data+="&dispathTaskId="+dispathTaskId;
        url=rootPath + "/action/S_dispatch_DispathTask_updateFromSendToBtn.action"
    }

    console.log("人员选择，点发送按钮："+JSON.stringify(data))
    sendAjax(url,data,function (msg) {
        console.log(msg)
    })
    removeFromGrid();
}



function sendAjax(url,data, callback) {
    $.ajax({
        url: url,
        type:"post",
        data:data,
        dataType:"json",
        success:callback
    });
}
