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
        url:rootPath + "/container/gov/dispatch/selectPeople.json",
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
                footerFormatter: totalTextFormatter,
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
                field: 'zhiwu',
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
function getIdSelectionsFromGridSelectPeople() {
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
    if(!treeNode.isParent){
        appendToGrid(treeNode);
    }

};

/**
 * 点击发送按钮
 */
$("#sendTo").click(function () {
    var ids=getIdSelectionsFromGridSelectPeople();
    console.log(ids)

    removeFromGrid();
})
