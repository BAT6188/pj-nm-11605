/**
 * Created by Administrator on 2016/10/17.
 */
var sourceId_msgSend; //源id
var MsgSend = {};
(function($){
    $.fn.MsgSend = {
        init:function(type,options,callback){
            options = $.extend({}, {
                title: "人员选择",
                url:rootPath + "/action/S_alert_MsgSend_getOrgPersonList.action",
                params:{orgCode:["0170001000"],type:1},
                btnok: "发送",
                btncl: "取消",
                width: 850,
                auto: false
            }, options || {});
            var width = isNaN(options.width)?options.width:options.width+"px;";
            options.params.orgCode = options.params.orgCode!=null?options.params.orgCode:["0170001000"];
            options.params.type = options.params.type!=null?options.params.type:1;

            var dialog;
            var treeObj;
            if(type==1){//系统发送
                dialog = $('#selectOrgPeopleDialog');
                treeObj=setDialogTypeOne(dialog,options,callback);
            }else{//短信发送
                dialog = $('#selectContactsDialog');
                options.params.findType="2";
                treeObj=setDialogTypeTwo(dialog,options,callback);
            }
            dialog.find('.modal-dialog').attr('style','width:'+width);
            dialog.find('.modal-title').html(options.title);

            var msgSendTools = {
                open:function(sourceId){
                    sourceId_msgSend=sourceId;
                    dialog.modal('show');
                    treeObj.expandAll(true);
                },
                expandZtree:function(){
                    treeObj.expandAll(true);
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
        $(dialog).find('.sendToButton').click(function () {
            var persons = getIdsSelectionsFromGridSelectPeople();
            if(persons.length==0){
                Ewin.alert("请选择人员");
                return;
            }
            var returnData={
                personObj:persons[0],
                sourceId:sourceId_msgSend
            }
            callback(true,returnData);
            $('#search_orgPeople').val('');
            search_ztree('orgPeopleZtree', 'search_orgPeople');
            removeFromGrid();
            dialog.modal('hide');
        });
    }

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
        data:{
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parentId",
                rootPId: "-1",
            }
        },
        async: {
            enable: true,
            url:options.url,//"/container/gov/dispatch/selectPeople.json"
            autoParam:["id", "name=n", "level=lv"],
            otherParam:options.params,
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
    var treeObj = $.fn.zTree.init($("#orgPeopleZtree"), setting);
    MsgSend.tree = treeObj;
    setTimeout(function () {
        treeObj.expandAll(true);
    },500)
//-------------选择人员 table配置--------------------//
    var gridSelectPeopleTable = $('#selectOrgPeopleTable');
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
                }, {
                    title: 'id',
                    field: 'id',
                    visible:false
                },
                {
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
        return '<button type="button" class="close buttonClose" value="'+row.id+'"><span aria-hidden="true" title="删除">×</span></button>';
    }
    /**
     * 获取列表所有的选中数据id
     * @returns {*}
     */
    function getIdsSelectionsFromGridSelectPeople() {
        return $.map(gridSelectPeopleTable.bootstrapTable('getSelections'), function (row) {
            return row;
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
    var selectedId="";
    function zTreeOnClick(event, treeId, treeNode) {
        if(treeNode.check_Child_State=="-1" && treeNode.id!="false"){
            if(selectedId!=""){
                jsMap.remove(selectedId);
                gridSelectPeopleTable.bootstrapTable('removeAll');
            }
            selectedId = treeNode.id;
            appendToGrid(treeNode);
        }
    };

    return treeObj;
}
/**************通讯录选择***************************/
function setDialogTypeTwo(dialog,options,callback){
    if (callback && callback instanceof Function) {
        $(dialog).find('.sendToButton').click(function () {
            var smsContent = $("#msgContents").val();

            if(!smsContent){
                Ewin.alert("请填写短信内容");
                return;
            }
            var ids = getIdsSelectionsFromGridSelectPeople();
            if(ids.length==0){
                Ewin.alert("请选择人员");
                return;
            }
            var msg = $(dialog).find('#msgContents').val();
            var returnData = {
                ids: ids,
                info: msg,
                sourceId: sourceId_msgSend
            };
            //发送短信
            var selectPeoples = getSelectPeoples();
            //转换短信接收人结构
            var receivers = [];
            var RECEIVER_SOURCE_CONTACTS = "2";
            for(var i =0; i < selectPeoples.length; i++){
                var people = selectPeoples[i];
                var receiver = {};
                receiver.receiverId = people.id;
                receiver.receiverName = people.name;
                receiver.receiverPhone = people.mobilePhone;
                receiver.receiverSource = RECEIVER_SOURCE_CONTACTS;
                receivers.push(receiver);
            }
            $.ajax({
                url:rootPath + "/action/S_sms_SmsRecord_sendSms.action",
                type:"post",
                dataType:"json",
                data:{'senderId':userId,'senderName':userName,'content':smsContent,"receivers":JSON.stringify(receivers)},
                success:function (sendStatuses) {
                    if (sendStatuses && sendStatuses.length > 0) {
                        $("#msgContents").val("");
                        Ewin.alert("短信发送成功");
                    }
                }
            });
            callback(true,returnData);
            $('#search_contacts').val('');
            search_ztree('contactsZtree', 'search_contacts');
            removeFromGrid();
            dialog.modal('hide');
        });
    }

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
        data:{
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parentId",
                rootPId: "-1",
            }
        },
        async: {
            enable: true,
            url:options.url,//"/container/gov/dispatch/selectPeople.json"
            autoParam:["id", "name=n", "level=lv"],
            otherParam:options.params,
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
    var treeObj = $.fn.zTree.init($("#contactsZtree"), setting);
    MsgSend.tree = treeObj;
    setTimeout(function () {
        treeObj.expandAll(true);
    },500);
//-------------选择人员 table配置--------------------//
    var gridSelectPeopleTable = $('#selectContactsTable');
    function initSelectPeopleTable() {
        gridSelectPeopleTable.bootstrapTable({
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            height: 400,
            clickToSelect:true,//单击行时checkbox选中
            columns: [
                {
                    title:"全选",
                    checkbox: true,
                    align: 'center',
                    radio:false,  //  true 单选， false多选
                    valign: 'middle'
                },{
                    title: '姓名',
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
                {
                    title: '移动电话',
                    field: 'mobilePhone',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '办公室电话',
                    field: 'officePhone',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '部门',
                    field: 'department',
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
    function getSelectPeoples() {
        return $.map(gridSelectPeopleTable.bootstrapTable('getSelections'), function (row) {
            return row
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
        if(treeNode.check_Child_State=="-1" && treeNode.id!="false"){
            appendToGrid(treeNode);
        }
    };

    return treeObj;
}
