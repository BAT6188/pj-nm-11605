/**
 * Created by Administrator on 2016/10/17.
 */
var sourceId_msgSend; //一个entity实体对象{isSendSms:true,content:"短信内容",其他实体字段}                 //源id
var MsgSend = {};
MsgSend.tree = {};

(function($){
    $.fn.MsgSend = {
        init:function(type,options,callback){
            options = $.extend({}, {
                title: "人员选择",
                url:rootPath + "/action/S_alert_MsgSend_getOrgPersonList.action",
                params:{orgCode:["dsgov"],type:1},
                choseMore:true,
                btnok: "发送",
                btncl: "取消",
                width: 850,
                auto: false
            }, options || {});
            var width = isNaN(options.width)?options.width:options.width+"px;";
            options.params.orgCode = options.params.orgCode!=null?options.params.orgCode:["0170001000"];
            options.params.type = options.params.type!=null?options.params.type:1;

            var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
            var dialog,treeObj,timeId = new Date().valueOf();
            if(type==1){//系统发送
                var html = $('#selectOrgPeopleDialog').html();
                var content = html.replace(reg, function (node, key) {
                    return {
                        Id: 'selModel'+timeId,
                        ScrollContent:'sContent'+timeId,
                        SearchOrgPeopleId:'search'+timeId,
                        SearchOrgPeopleBtnId:'searchBtn'+timeId,
                        orgPeopleZtreeId:'choseZtree'+timeId,
                        selectOrgPeopleTableId:'selectTable'+timeId,
                        Width:options.width,
                        Title: options.title,
                        btnok: options.btnok,
                        btncl: options.btncl
                    }[key];
                });
                options.timeId = timeId;
                $('#msgSendBoday').append(content);
                dialog = $('#selModel'+timeId);
                treeObj=setDialogTypeOne(dialog,options,callback);
            }else{//短信发送
                var html = $('#selectContactsDialog').html();
                var content = html.replace(reg, function (node, key) {
                    return {
                        Id: 'selModel'+timeId,
                        ScrollContent:'sContent'+timeId,
                        SearchInputId:'search'+timeId,
                        SearchBtnId:'searchBtn'+timeId,
                        ChoseZtreeId:'choseZtree'+timeId,
                        MsgContentsId:'msgContents'+timeId,
                        SelectTableId:'selectTable'+timeId,
                        Width:options.width,
                        Title: options.title,
                        btnok: options.btnok,
                        btncl: options.btncl
                    }[key];
                });
                options.timeId = timeId;
                options.params.findType="2";
                $('#msgSendBoday').append(content);
                dialog = $('#selModel'+timeId);
                treeObj=setDialogTypeTwo(dialog,options,callback);
            }
            var msgSendTools = {
                treeObj:treeObj,
                open:function(sourceId){
                    sourceId_msgSend=sourceId;
                    if (!sourceId_msgSend){
                        sourceId_msgSend={isSendSms:false}
                    }
                    dialog.modal('show');
                    treeObj.expandAll(true);
                },
                expandZtree:function(){
                    treeObj.expandAll(true);
                },
                clearChose:function(){
                    $(dialog).find('#search'+timeId).val('');
                    search_ztree('selModel'+timeId,'choseZtree'+timeId,'search'+timeId);
                }
            }
            return msgSendTools;
        }
    }
})(jQuery);

//-------------加载组织机构、人员url，需要区分 flag哪个源的数据：  1-监控中心，监控办公室的调度单   2-执法管理列表的调度单--------------------//
//-------------选择人员 ztree配置--------------------//
function setDialogTypeOne(dialog,options,callback){
    var modalId = 'selModel'+options.timeId,
        searchId = 'search'+options.timeId,
        searchBtnId='searchBtn'+options.timeId,
        choseZtreeId='choseZtree'+options.timeId,
        selectTableId='selectTable'+options.timeId;
    if (callback && callback instanceof Function) {
        $(dialog).find('.sendToButton').click(function () {
            var persons = getIdsSelectionsFromGridSelectPeople();
            if(persons.length==0){
                Ewin.alert("请选择人员");
                return;
            }
            //发送短信
            if (sourceId_msgSend.isSendSms){
                //转换短信接收人结构
                var receivers = [];
                var RECEIVER_SOURCE_CONTACTS = "2";
                for(var i =0; i < persons.length; i++){
                    var people = persons[i];
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
                    data:{'senderId':userId,'senderName':userName,'content':sourceId_msgSend.content,"receivers":JSON.stringify(receivers)},
                    success:function (sendStatuses) {
                        if (sendStatuses && sendStatuses.length > 0) {
                           console.log("短信发送成功")
                        }
                    }
                });
            }

            var returnData={
                personObj:persons,
                sourceId:sourceId_msgSend
            }
            callback(true,returnData);
            $(dialog).find('#'+searchId).val('');
            search_ztree(modalId,choseZtreeId, searchId);
            removeFromGrid();
            dialog.modal('hide');
        });
    }
    $('#sContent'+options.timeId).slimScroll({
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
        check : {
            autoCheckTrigger : true,
            chkboxType : {"Y": "ps", "N": "ps"},
            chkStyle : "checkbox",
            enable : options.choseMore?true:false,
            nocheckInherit : true,
            radioType : "level"
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
            onClick: zTreeOnClick,
            onCheck: zTreeOnCheck,
            onAsyncSuccess:function (event, treeId, treeNode, msg) {
                $('#'+searchId).keydown(function(event){
                    event=document.all?window.event:event;
                    if((event.keyCode || event.which)==13){
                        search_ztree(modalId,choseZtreeId, searchId);
                    }
                });
                $('#'+searchBtnId).click(function(){
                    search_ztree(modalId,choseZtreeId, searchId);
                });
            }
        }
    };
    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;
        for (var i=0, l=childNodes.length; i<l; i++) {
            childNodes[i].saveName = childNodes[i].name.replace(/\.n/g, '.');
            if(childNodes[i].pcode=="1"){
                childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.')+"(党员)";
            }else{
                childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            }
        }
        return childNodes;
    }

    var treeObj = $.fn.zTree.init($("#"+choseZtreeId), setting);
    MsgSend.tree[modalId] = treeObj;
    /*setTimeout(function () {
        treeObj.expandAll(true);
    },500)*/
//-------------选择人员 table配置--------------------//
    var gridSelectPeopleTable = $(dialog).find('#'+selectTableId);
    function initSelectPeopleTable() {
        gridSelectPeopleTable.bootstrapTable({
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            height: 540,
            clickToSelect:true,//单击行时checkbox选中
            uniqueId: "id",
            columns: [
                {
                    title:"全选",
                    checkbox: true,
                    align: 'center',
                    radio:false,  //  true 单选， false多选
                    valign: 'middle'
                },
                {
                    title: '已选名单',
                    field: 'saveName',
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
                    title: '联系方式',
                    field: 'mobilePhone',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                /*{
                    title: '操作',
                     field: 'id',
                     align: 'center',
                     editable: false,
                     sortable: false,
                     events: {
                         'click .buttonClose': function (e, value, row, index) {
                             treeObj.checkNode(row, false, true);
                             jsMap.remove(value);
                             gridSelectPeopleTable.bootstrapTable('removeByUniqueId',value);
                         }
                     },
                     formatter: operateFormatter
                 }*/
            ]
        });
    }

    initSelectPeopleTable();

    function operateFormatter(value, row, index) {
        return '<button type="button" class="close buttonClose"><span aria-hidden="true" title="删除">×</span></button>';
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
        if(options.choseMore){
            if(treeNode.checked && !jsMap.isHave(treeNode.id)){
                jsMap.put(treeNode.id,treeNode.id);
                gridSelectPeopleTable.bootstrapTable("append",treeNode);
                gridSelectPeopleTable.bootstrapTable('checkAll');
            }else{
                jsMap.remove(treeNode.id);
                gridSelectPeopleTable.bootstrapTable('removeByUniqueId',treeNode.id);
            }
        }else if(!jsMap.isHave(treeNode.id)){
            jsMap.put(treeNode.id,treeNode.id);
            gridSelectPeopleTable.bootstrapTable("append",treeNode);
            gridSelectPeopleTable.bootstrapTable('checkAll');
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
        if(options.choseMore){
            if(treeNode.checked){
                clickOrCheckZtree(true,treeNode,false);
            }else{
                clickOrCheckZtree(true,treeNode,true);
            }
        }else{
            if(treeNode.check_Child_State=="-1" && treeNode.couldChose){
                if(selectedId!=""){
                    jsMap.remove(selectedId);
                    gridSelectPeopleTable.bootstrapTable('removeAll');
                }
                selectedId = treeNode.id;
                appendToGrid(treeNode);
            }
        }
    };
    function zTreeOnCheck(event, treeId, treeNode) {
        clickOrCheckZtree(false,treeNode);
    };

    function clickOrCheckZtree(isClick,treeNode,checked){
        if(isClick){treeObj.checkNode(treeNode, checked, true);}
        if(treeNode.check_Child_State=="0"){
            $.each(treeNode.children,function(k,v){
                clickOrCheckZtree(isClick,v,checked);
            });
        }
        if(treeNode.check_Child_State=="-1" && treeNode.couldChose){
            appendToGrid(treeNode);
        }
    }
    return treeObj;
}
/**************通讯录选择***************************/
function setDialogTypeTwo(dialog,options,callback){
    var modalId = 'selModel'+options.timeId,
        searchId = 'search'+options.timeId,
        searchBtnId='searchBtn'+options.timeId,
        choseZtreeId='choseZtree'+options.timeId,
        msgContentsId='msgContents'+options.timeId,
        selectTableId='selectTable'+options.timeId;
    if (callback && callback instanceof Function) {
        $(dialog).find('.sendToButton').click(function () {
            var smsContent = $("#"+msgContentsId).val();

            if(!smsContent){
                Ewin.alert("请填写短信内容");
                return;
            }
            var persons = getSelectPeoples();
            if(persons.length==0){
                Ewin.alert("请选择人员");
                return;
            }
            var msg = $(dialog).find("#"+msgContentsId).val();
            var returnData = {
                personObj:persons,
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
                        $("#"+msgContentsId).val("");
                        Ewin.alert("短信发送成功");
                    }
                }
            });
            callback(true,returnData);
            $('#'+searchId).val('');
            search_ztree(modalId,choseZtreeId, searchId);
            removeFromGrid();
            dialog.modal('hide');
        });
    }

    $('#sContent'+options.timeId).slimScroll({
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
            onClick: zTreeOnClick,
            onAsyncSuccess:function (event, treeId, treeNode, msg) {
                $('#'+searchId).keydown(function(event){
                    event=document.all?window.event:event;
                    if((event.keyCode || event.which)==13){
                        search_ztree(modalId,choseZtreeId, searchId);
                    }
                });
                $('#'+searchBtnId).click(function(){
                    search_ztree(modalId,choseZtreeId, searchId);
                });
            }
        }
    };
    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;
        for (var i=0, l=childNodes.length; i<l; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
        }
        return childNodes;
    }
    var treeObj = $.fn.zTree.init($("#"+choseZtreeId), setting);
    MsgSend.tree[modalId] = treeObj;
    setTimeout(function () {
        treeObj.expandAll(true);
    },500);
//-------------选择人员 table配置--------------------//
    var gridSelectPeopleTable = $('#'+selectTableId);
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
        if(treeNode.check_Child_State=="-1" && treeNode.couldChose){
            appendToGrid(treeNode);
        }
    };
    return treeObj;
}
