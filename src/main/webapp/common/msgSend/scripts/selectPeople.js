/**
 * Created by Administrator on 2016/10/17.
 */
var sourceId_msgSend //源id
(function($){
    $.fn.MsgSend = {
        init:function(type,options,callback){
            var dialog;
            if(type==1){
                dialog = $('#selectPeopleDialog');
            }else{

            }
            options = $.extend({}, {
                title: "组织机构人员选择",
                //message: "提示内容",
                btnok: "发送",
                btncl: "取消",
                width: 850,
                auto: false
            }, options || {});
            var width = isNaN(options.width)?options.width:options.width+"px;";
            dialog.find('.modal-dialog').attr('style','width:'+width);
            dialog.find('#formTitle').html(options.title);
            setDialogTypeOne(dialog,options,callback);
            var msgSendTools = {
                open:function(sourceId){
                    sourceId_msgSend=sourceId;
                    dialog.modal('show');
                }
            }
            return msgSendTools;
        }
    }
})(jQuery);

//-------------加载组织机构、人员url，需要区分 flag哪个源的数据：  1-监控中心，监控办公室的调度单   2-执法管理列表的调度单--------------------//
//-------------选择人员 ztree配置--------------------//
function setDialogTypeOne(dialog,options,callback){
    if (callback && callback instanceof Function) {
        $(dialog).find('#sendTo').click(function () {
            var ids = getIdsSelectionsFromGridSelectPeople();
            if(ids.length==0){
                Ewin.alert("请选择人员");
                return;
            }
            callback(true,ids,sourceId_msgSend);
            removeFromGrid();
            dialog.modal('hide');
        });
    }

    $(".scrollContent").slimScroll({
        height:"100%",
        railOpacity:.9,
        alwaysVisible:!1
    });
    var asyncData;
    if(options.Async){
        var async = options.Async;
        asyncData={
            enable: true,
            url:async.url,//"/container/gov/dispatch/selectPeople.json"
            autoParam:["id", "name=n", "level=lv"],
            otherParam:async.params,
            dataFilter: filter
        }
    }else{
        asyncData={
            enable: true,
            url:rootPath + "/S_dict_Dict_getOrgPersonList.action",//"/container/gov/dispatch/selectPeople.json"
            autoParam:["id", "name=n", "level=lv"],
            otherParam:{"orgCode":options.orgCode},
            dataFilter: filter
        }
    }
    var setting = {
        height:500,
        width:200,
        view: {
            showLine: true
        },
        data:{
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parentId",
                rootPId: "-1",
            }
        },
        async: asyncData,
        callback: {
            onClick: zTreeOnClick
        }
    };
    function filter(treeId, parentNode, childNodes) {
        console.log(childNodes);
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
                },
                /*{
                    field: 'operate',
                    align: 'center',
                    events: operateEvents,
                    formatter: operateFormatter
                }*/
            ]
        });
    }

    initSelectPeopleTable();

    function operateFormatter(value, row, index) {
        console.log(row.id);
        return '<button type="button" class="close buttonClose" value="'+row.id+'"><span aria-hidden="true" title="删除">×</span></button>';
    }
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
        if(!jsMap.isHave(treeNode.id)){
            jsMap.put(treeNode.id,treeNode.id);
            gridSelectPeopleTable.bootstrapTable("append",treeNode);
            gridSelectPeopleTable.bootstrapTable('checkAll');
            $(dialog).find('.buttonClose').click(function(){
                var id = $(this).attr('value');
                jsMap.remove(id);
                gridSelectPeopleTable.bootstrapTable('remove', {
                    field: 'id',
                    values: id
                });
            });
        }
    }

    /**
     * 从表格中移除数据
     */
    function removeFromGrid() {
        gridSelectPeopleTable.bootstrapTable('removeAll');
        jsMap.removeAll();
    }

    function zTreeOnClick(event, treeId, treeNode) {
        if(!treeNode.parent){
            appendToGrid(treeNode);
        }
    };

}
