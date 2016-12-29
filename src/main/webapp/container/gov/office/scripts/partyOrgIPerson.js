var gridTable = $('#table'),
    addBtn = $("#add"),
    removeBtn = $('#remove'),
    form = $("#scfForm"),
    formTitle = "党员",
    thisOrgId="",
    thisOrgName="",
    selections = [];

var orgTreeObj;
function initZtree(data) {
    // data=JSON.parse(data)
    var setting = {
        height:500,
        width:200,
        view: {
            showLine: true
        },
        data:{
            key: {
                name: "orgName"
            },
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parentId",
                rootPId: "root",
            }
        },
        callback: {
            onClick: orgTreeOnClick,
        }
    };
    function orgTreeOnClick(event, treeId, treeNode){
        $('.hidden').val("");
        if(treeNode.parentId!="root"){
            thisOrgId=treeNode.id;
            thisOrgName=treeNode.orgName;
            $('#s_orgId').val(treeNode.id);
            searchForm();
            addBtn.prop('disabled',false);
        }else{
            $('#s_orgId').val("");
            searchForm();
            addBtn.prop('disabled',true);
        }
    }
    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;
        for (var i=0, l=childNodes.length; i<l; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
        }
        return childNodes;
    }
    orgTreeObj = $.fn.zTree.init($("#orgZtree"), setting,data);
    var nodes = orgTreeObj.getNodes();
    orgTreeObj.expandAll(true);
    $('#orgScrollContent').slimScroll({
        height:"100%",
        railOpacity:.9,
        alwaysVisible:!1
    });
    $('#orgDiv').find('table').remove();
    //orgTreeObj.expandAll(true);
}

if (window.partOrgTreeObjData){
    initZtree(window.partOrgTreeObjData);
}else {
    $.ajax({
        url: rootPath + "/action/S_office_PartyOrg_getPartyOrgZtree.action",//"/container/gov/dispatch/selectPeople.json"
        type:"post",
        traditional:true,
        dataType:"json",
        success:function (data) {
            initZtree(data);
            window.partOrgTreeObjData=data;
        }
    });

}



//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_office_PartyOrgIperson_savePartyOrgIperson.action",
        type:"post",
        traditional:true,
        data:entity,
        dataType:"json",
        success:callback
    });
}
/**
 * 删除请求
 * @param ids 多个,号分隔
 * @param callback
 */
function deleteAjax(ids, callback) {
    $.ajax({
        url: rootPath + "/action/S_office_PartyOrgIperson_delete.action",
        type:"post",
        data:$.param({deletedId:ids},true),//阻止深度序列化，向后台传递数组
        dataType:"json",
        success:callback
    });
}
/**============grid 列表初始化相关代码============**/
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_office_PartyOrgIperson_list.action",
        height: pageUtils.getTableHeight(),
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
        queryParams:pageUtils.localParams,
        columns: [
            {
                title:"全选",
                checkbox: true,
                align: 'center',
                radio:false,  //  true 单选， false多选
                valign: 'middle',
                formatter:function(value, row, index){
                    row.userName = row.iPerson.userName;
                    row.officeTel = row.iPerson.officeTel;
                    row.mobile = row.iPerson.mobile;
                    /*$.each(row.iPerson,function(i,j){
                        row[i]=j;
                    })*/
                }
            },
            {
                title: 'ID',
                field: 'id',
                align: 'center',
                valign: 'middle',
                sortable: false,
                visible:false
            },
            {
                title: '姓名',
                field: 'userName',
                editable: false,
                sortable: false,
                align: 'center',
                isDown:true
            },
            {
                title: '座机号码',
                field: 'officeTel',
                sortable: false,
                align: 'center',
                editable: false,
                isDown:true
            },{
                title: '手机号码',
                field: 'mobile',
                sortable: false,
                align: 'center',
                editable: false,
                isDown:true
            }
        ]
    });
    // sometimes footer render error.
    setTimeout(function () {
        gridTable.bootstrapTable('resetView');
    }, 200);

    //列表checkbox选中事件
    gridTable.on('check.bs.table uncheck.bs.table ' +
        'check-all.bs.table uncheck-all.bs.table', function () {
        //有选中数据，启用删除按钮
        removeBtn.prop('disabled', !gridTable.bootstrapTable('getSelections').length);
    });

    $(window).resize(function () {
        // 重新设置表的高度
        gridTable.bootstrapTable('resetView', {
            height: pageUtils.getTableHeight()
        });
    });

    gridTable.BootstrapExport($('#export'),{
        fileName:'通讯录',  //自定义文件名
    });
}

// 生成列表操作方法
function operateFormatter(value, row, index) {
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#scfForm">查看</button>';
}
// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, row, index) {
        setFormView(row);
    }
};
/**
 * 获取列表所有的选中数据id
 * @returns {*}
 */
function getIdSelections() {
    return $.map(gridTable.bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}

/**
 *  获取列表所有的选中数据
 * @returns {*}
 */
function getSelections() {
    return $.map(gridTable.bootstrapTable('getSelections'), function (row) {
        return row;
    });
}
function searchForm(){
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
}
initTable();
/**============列表工具栏处理============**/
//初始化按钮状态
addBtn.prop('disabled', true);
removeBtn.prop('disabled', true);
/**
 * 列表工具栏 新增和更新按钮打开form表单，并设置表单标识
 */
addBtn.bind('click',function () {
    choseModel.open();
});
/**
 * 列表工具栏 删除按钮
 */
removeBtn.click(function () {
    var ids = getIdSelections();
    Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
        if (!e) {
            return;
        }
        deleteAjax(ids,function (msg) {
            gridTable.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
            removeBtn.prop('disabled', true);
        });
    });
});
var choseModel = $.fn.MsgSend.init(1,options,function(e,obj){ //短信发送第一个参数为2
    var ipersons = obj.personObj;
    var saveIPersons = [];
    $.each(ipersons,function(k,v){
        saveIPersons.push(v.iperson);
    });
    var entity={};
    entity.partyOrgId = $('#s_orgId').val();
    entity.personList = JSON.stringify(saveIPersons);
    saveAjax(entity,function(data){
        if(data.success){
            Ewin.alert("添加成功!");
        }
    })
});
/**============列表搜索相关处理============**/

/**============表单初始化相关代码============**/






