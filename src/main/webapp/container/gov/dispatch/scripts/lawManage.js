//@ sourceURL=lawManage.js
var gridTable = $('#table'),
    feedbackRecordTable=$("#feedbackRecordTable"),
    table_siteMonitoringReportDialog = $('#table_siteMonitoringReportDialog'),
    overBtn = $('#overBtn'),
    dealWithBtn = $('#dealWith'),
    feedbackBtn = $('#feedback'),
    addSiteMonitoring = $('#addSiteMonitoring'),
    addPunish = $('#addPunish'),
    eventMsg_monitorOffice_dialog = $("#eventMsg_monitorOffice"),
    eventMsg_monitorCase_dialog = $("#eventMsg_monitorCase"),
    feedbackForm=$("#feedbackForm"),
    overDialog=$("#overDialog"),
    addSiteMonitoringDialog=$("#addSiteMonitoringDialog"),
    selections = [];

$("#addPunish").click(function () {
    var row=getSelections()[0];
    var url = rootPath + "/container/gov/exelaw/punish.jsp?id=" + row.id;
    pageUtils.toUrl(url);
})

$("#addSiteMonitoring").click(function () {
    resetDialog(addSiteMonitoringDialog);
    var row=getSelections()[0];
    var inputs = addSiteMonitoringDialog.find('.form-control');
    $.each(inputs,function(k,v){
        var tagId = $(v).attr('name');
        $(v).val(row[tagId]);
    });

    $("#dispatchId_addSiteMonitoringDialog").val(row.id)
    $("#checkPeople").val(userName)
    $("#monitoringTime").val((new Date()).format("yyyy-MM-dd"))

    uploaderToggle(".dUploader")
    uploader = new qq.FineUploader(getUploaderOptions());
    bindDownloadSelector();

    $("#addSiteMonitoringDialog").find("#save").show();
    $("#addSiteMonitoringDialog").find(".btn-cancel").text("取消");

})

//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_dispatch_DispatchTask_dispathTaskBtnSave.action",
        type:"post",
        data:entity,
        success:callback
    });
}

/**============grid 列表初始化相关代码============**/
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_dispatch_DispatchTask_list.action?role="+role+"&notOver=TRUE",
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
                valign: 'middle'
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
                title: '事件对象',
                field: 'enterpriseName',
                editable: false,
                sortable: false,
                align: 'center',
                formatter: function (value, row, index) {
                    var isNewDiv=""
                    if (role=='monitor_master'){
                        if (row.monitorMasterSelfReadStatus!='1'){
                            isNewDiv='<div id="isNew">&nbsp;</div>'
                        }
                    }else if(role=='env_pro_sta'){
                        if (row.huanBaoZhanSelfReadStatus!='1'){
                            isNewDiv='<div id="isNew">&nbsp;</div>'
                        }
                    }
                    return value+isNewDiv;
                }
            },
            {
                title: '事件时间',
                field: 'eventTime',
                sortable: false,
                align: 'center',
                editable: false,
                formatter:function (value, row, index) {
                    return pageUtils.sub16(value);
                }
            },
            {
                title: '事件来源',
                field: 'source',
                editable: false,
                sortable: false,
                align: 'center',
                formatter:function (value, row, index) {
                    if (value==0){
                        value="监控中心"
                    }else {
                        value=dict.get("caseSource",value)
                    }
                    return value;
                }
            },
            {
                title: '所属网格',
                field: 'blockName',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '调度状态',
                field: 'status',
                editable: false,
                sortable: false,
                align: 'center',
                events: operateEvents,
                formatter: function (value, row, index) {

                    /**
                     * 1:未调度
                     * 2:已调度
                     * 3:已反馈
                     * 4:已处罚
                     * 5:已办结
                     * 状态
                     */
                    if (value==1){
                        value="未调度"
                    }else{
                        value="已调度"
                    }

                    return value
                }
            },
            {
                field: 'sendToPerson',
                title: '发送至',
                sortable: false,
                align: 'center',
                editable: false,
                formatter: function (value, row, index) {
                    if(row.monitorMasterPersonNameList!=undefined){
                        value=row.monitorMasterPersonNameList
                    }
                    if(row.envProStaPersonNameList!=undefined){
                        value=" "+row.envProStaPersonNameList
                    }
                    return pageUtils.sub30(value);
                }
            },
            {
                title: '发送人',
                field: 'dispatchPersonName',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                field: 'monitorReportStatus',
                title: '现场监察报告',
                sortable: false,
                align: 'center',
                editable: false,
                events: siteMonitoringReportEvents,
                formatter: function (value, row, index) {
                    if(value=='1'){
                        value="已报送";
                        value="<a class='btn btn-md btn-warning siteMonitoringReport' data-toggle='modal' data-target='#siteMonitoringReportDialog'>"+value+"</a>";
                    }else {
                        value="未报送"
                    }
                    return value;
                }
            },
            {
                field: 'col2',
                title: '行政处罚',
                sortable: false,
                align: 'center',
                editable: false,
                events: punishEvents,
                formatter: function (value, row, index) {
                    /**
                     * 1:未调度
                     * 2:已发送
                     * 3:已反馈
                     * 4:已处罚
                     * 5:已办结
                     * 状态
                     */
                    if(row.punishStatus==1){
                        value="<a class='btn btn-md btn-warning punish'>已处罚</a>"
                    }else{
                        value="--"//未处罚
                    }
                    return value
                }
            },
            {
                title: '反馈状态',
                field: 'feedbackStatus',
                editable: false,
                sortable: false,
                align: 'center',
                events: operateEvents,
                formatter: feedbackStatusoperateFormatter
            },
            {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: lookOverEvents,
                formatter: lookOverFormatter
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
        // overBtn.prop('disabled', !gridTable.bootstrapTable('getSelections').length);
        //选中一条数据启用修改按钮
        dealWithBtn.prop('disabled', !(gridTable.bootstrapTable('getSelections').length== 1));
        feedbackBtn.prop('disabled', !(gridTable.bootstrapTable('getSelections').length== 1));
        addSiteMonitoring.prop('disabled', !(gridTable.bootstrapTable('getSelections').length== 1));
        addPunish.prop('disabled', !(gridTable.bootstrapTable('getSelections').length== 1));
        overBtn.prop('disabled', !(gridTable.bootstrapTable('getSelections').length== 1));
    });

    $(window).resize(function () {
        // 重新设置表的高度
        gridTable.bootstrapTable('resetView', {
            height: pageUtils.getTableHeight()
        });
    });
}

function feedbackStatusoperateFormatter(value, row, index) {
    if (row.status>='3'){
        return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#lookOverFeedbackForm">已反馈</button>';
    }else {
        return '未反馈'
    }
}

function lookOverFormatter(value, row, index) {
    return '<button type="button" class="btn btn-md btn-warning lookOver">查看</button>';
}

window.lookOverEvents = {
    'click .lookOver': function (e, value, entity, index) {
        if (entity) {
            var id = entity.id;
            if(entity.source==0){
                eventMsg_monitorCase_dialog.modal('show')
                resetDialog(eventMsg_monitorCase_dialog);
                disabledForm(eventMsg_monitorCase_dialog,true)

                var inputs = eventMsg_monitorCase_dialog.find('[name]');
                $.each(inputs,function(k,v){
                    var tagId = $(v).attr('name');
                    $(v).val(entity[tagId]);
                });

                $("#dispatch").hide();
                $("#isSendSmsSpan").hide();
                $("#cancel").text("关闭")

            }else {
                eventMsg_monitorOffice_dialog.modal('show')
                resetDialog(eventMsg_monitorOffice_dialog);
                disabledForm(eventMsg_monitorOffice_dialog,true)

                var inputs = eventMsg_monitorOffice_dialog.find('[name]');
                $.each(inputs,function(k,v){
                    var tagId = $(v).attr('name');
                    $(v).val(entity[tagId]);
                });


                uploaderToggle(".aUploader")
                var fuOptions = getUploaderOptions(entity.monitorCaseId);
                fuOptions.callbacks.onSessionRequestComplete = function () {
                    $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
                    $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无上传的附件");
                };
                uploader = new qq.FineUploader(fuOptions);
                bindDownloadSelector();
                $(".qq-upload-button").hide();

                var url=rootPath + "/action/S_dispatch_DispatchTask_updateMonitorMasterSelfReadStatus.action";
                pageUtils.updateSelfReadStatus(url,id,1)
            }

        }
    }
};

window.siteMonitoringReportEvents = {
    'click .siteMonitoringReport': function (e, value, entity, index) {
        table_siteMonitoringReportDialog.bootstrapTable('refresh',{
            query:{dispatchId:entity.id}
        });
    }
};

window.punishEvents = {
    'click .punish': function (e, value, row, index) {
        var url = rootPath + "/container/gov/exelaw/punish.jsp?id=" + row.id;
        pageUtils.toUrl(url);

    }
};

// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, row, index) {
        console.log(JSON.stringify(row));

        $("#lookOverFeedbackForm_eventTime").val(row.eventTime);
        $("#lookOverFeedbackForm_answer").val(row.answer);
        $("#lookOverFeedbackForm_enterpriseName").val(row.enterpriseName);
        $("#lookOverFeedbackForm_source").val(row.source);
        $("#lookOverFeedbackForm_blockLevelId").val(row.blockLevelId);
        $("#lookOverFeedbackForm_blockId").val(row.blockId);
        $("#lookOverFeedbackForm_supervisor").val(row.supervisor);
        $("#lookOverFeedbackForm_supervisorPhone").val(row.supervisorPhone);
        $("#lookOverFeedbackForm_senderName").val(row.senderName);
        $("#lookOverFeedbackForm_sendTime").val(row.sendTime);
        $("#lookOverFeedbackForm_content").val(row.sendRemark);
        $("#lookOverFeedbackForm_sendRemark").val(row.sendRemark);
        disabledForm($("#lookOverFeedbackForm"),true)

        feedbackRecordTable.bootstrapTable('refresh',{query:{dispatchId:row.id}})

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

initTable();
/**============列表工具栏处理============**/
//初始化按钮状态
overBtn.prop('disabled', true);
dealWithBtn.prop('disabled', true);
feedbackBtn.prop('disabled', true);
addSiteMonitoring.prop('disabled', true);
addPunish.prop('disabled', true);

$("#dealWith").bind("click",function () {
    var row=getSelections()[0];
    if(row){
        if(row.source=='0'){
            var url=rootPath + "/action/S_dispatch_DispatchTask_updateMonitorMasterSelfReadStatus.action";
            pageUtils.updateSelfReadStatus(url,getIdSelections()[0],1)
            eventMsg_monitorCase_dialog.modal('show');
            setEventMsgFormData(row);
        }else {
            Ewin.alert("不能够处置信访案件")
        }

    }


});

$("#feedback").bind("click",function () {
    feedbackForm.find("input").val("")
    feedbackForm.find("textarea").val("")
    disabledForm(feedbackForm,false)
    if (getSelections()[0]){
        $("#dispatchId").val(getSelections()[0].id)
        $("#caseReason").val(getSelections()[0].caseReason)
    }
    $("#lawerName").val(userName)
    $("#phone").val(mobile)

    uploaderToggle(".bUploader")
    uploader = new qq.FineUploader(getUploaderOptions());
    bindDownloadSelector();

    $("#feedbackTo").show();

    var url=rootPath + "/action/S_dispatch_DispatchTask_updateHuanBaoZhanSelfReadStatus.action";
    pageUtils.updateSelfReadStatus(url,getIdSelections()[0],1)

});

/***********  现场监察监测报告  *******************/
/*var $monitorReport = $("#monitorReport");
var options_monitorReport = {
    params:{
        orgCode:[orgCodeConfig.org.wuKongShi.orgCode],//组织机构代码(必填，组织机构代码)
        type:2
    },
    choseMore:false,
    title:"人员选择",//弹出框标题(可省略，默认值：“组织机构人员选择”)
    width:"60%",        //宽度(可省略，默认值：850)
}
var model_monitorReport = $.fn.MsgSend.init(1,options_monitorReport,function(e,data){
    console.info("回调函数data参数："+JSON.stringify(data))

    var d=pageUtils.sendParamDataToString(data)
    console.log("发送："+d)
    $.ajax({
        url: rootPath + "/action/S_dispatch_DispatchTask_saveToEnvProStaPersonList.action",
        type:"post",
        data:d,
        success:function (msg) {
            $monitorReport.modal('hide');
            gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
        }
    });
});

var ef_$monitorReport = $monitorReport.easyform({
    success:function (ef) {
        var entity = $monitorReport.find("form").formSerializeObject();
        entity.attachmentIds = getAttachmentIds();
        console.log("发送："+JSON.stringify(entity))
        $.ajax({
            url: rootPath + "/action/S_dispatch_DispatchTask_saveMonitorReport.action",
            type:"post",
            data:entity,
            success:function (id) {
                entity.id=id;
                entity.smsContent=entity.content
                // entity.isSendSms=$("#isSendSms").is(':checked');
                model_monitorReport.open(entity);
                gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
            }
        });

    }
});

$("#save_monitorReport").click(function () {
    ef_$monitorReport.submit(false);
})*/




/**============列表搜索相关处理============**/
//搜索按钮处理
$("#search").click(function () {
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
});
//重置搜索
$("#searchFix").click(function () {
    resetQuery();
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
});


/**============选择人员对话框============**/
var options = {
    params:{
        orgCode:[orgCodeConfig.org.jianChaDaDui.orgCode],//组织机构代码(必填，组织机构代码)
        type:3  //1默认加载所有，2只加载当前机构下人员，3只加载当前机构下的组织机构及人员
    },
    title:"人员选择",//弹出框标题(可省略，默认值：“组织机构人员选择”)
    width:"60%",        //宽度(可省略，默认值：850)
}

var model = $.fn.MsgSend.init(1,options,function(e,data){
    console.info("回调函数data参数："+JSON.stringify(data))

    var d=pageUtils.sendParamDataToString(data)
    console.log("发送："+d)
    $.ajax({
        url: rootPath + "/action/S_dispatch_DispatchTask_saveToEnvProStaPersonList.action",
        type:"post",
        data:d,
        success:function (ret) {
            eventMsg_monitorCase_dialog.modal('hide');
            gridTable.bootstrapTable('refresh');

            var receivers = [];
            $.each(data.personObj,function (i,v) {
                var receiver1 = {receiverId:v.userId,receiverName:v.name};
                receivers.push(receiver1);
            })
            var msg = {
                'msgType':8,
                'title':'执法管理消息',
                'content':data.sourceId.content,
                'businessId':ret
            };
            pageUtils.sendMessage(msg, receivers);

            pageUtils.saveOperationLog({opType:'4',opModule:'执法管理',opContent:'发送数据',refTableId:''})
        }
    });
});

/**============表单初始化相关代码============**/
//初始化表单验证
var ef_eventMsg_monitorCase_dialog = eventMsg_monitorCase_dialog.easyform({
    success:function (ef_eventMsg_monitorCase_dialog) {
        var entity={}
        entity.id=$("#eventMsg_monitorCase").find("#id").val();
        // entity.sendRemark=$("#sendRemark").val();
        entity.dispatchPersonName=$("#dispatchPersonName").val();
        entity.dispatchTime=$("#dispatchTime").val();
        entity.dispatchContent=$("#dispatchContent").val();
        console.log("点调度按钮，只保存能编辑的表单数据："+JSON.stringify(entity))

        saveAjax(entity,function (id) {
            gridTable.bootstrapTable('refresh');
            entity.id=id;
            entity.smsContent=entity.content;
            entity.isSendSms=$("#isSendSms").is(':checked');
            model.open(entity);
        });
    },
    error:function () {
        console.log("error")
    }
});

//表单 保存按钮
$("#dispatch").bind('click',function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    ef_eventMsg_monitorCase_dialog.submit(false);
});

/**
 * 设置 处置按钮打开的对话框
 * @param entity
 * @returns {boolean}
 */
function setEventMsgFormData(entity) {
    resetDialog(eventMsg_monitorCase_dialog);
    if (!entity) {return false}
    var id = entity.id;

    disabledForm(eventMsg_monitorCase_dialog,true)
    $("#dispatchContent").attr("disabled",false);
    $("#isSendSms").attr("disabled",false);

    var inputs = eventMsg_monitorCase_dialog.find('[name]');
    $.each(inputs,function(k,v){
        var tagId = $(v).attr('name');
        $(v).val(entity[tagId]);
    });

    if(entity.dispatchPersonName){
        $("#dispatchPersonName").val(entity.dispatchPersonName);
    }else {
        $("#dispatchPersonName").val(userName);
    }

    if(entity.dispatchTime){
        $("#dispatchTime").val(entity.dispatchTime);
    }else {
        $("#dispatchTime").val((new Date()).format("yyyy-MM-dd hh:mm"));
    }

    if(entity.dispatchContent){
        $("#dispatchContent").val(entity.dispatchContent);
    }
    $("#dispatch").show();
    $("#isSendSmsSpan").show();
    $("#cancel").text("取消")
}

/**
 * 重置表单
 */
function resetDialog(dialog) {
    dialog.find('form')[0].reset();
    dialog.find("#isSendSms").attr("checked",false);
    uploader = new qq.FineUploader(getUploaderOptions());
    disabledForm(dialog,false);
}

function disabledForm(dialogSelector,disabled) {
    dialogSelector.find("input").attr("disabled",disabled);
    dialogSelector.find("textarea").attr("disabled",disabled);
    dialogSelector.find("select").attr("disabled",disabled);

    if (!disabled) {
        //初始化日期组件
        $('.lookover').datetimepicker({
            language:   'zh-CN',
            autoclose: 1,
            minView: 2
        });

    }else{
        $('.lookover').datetimepicker('remove');
    }
}

/**============表单初始化相关代码============**/
var ef_feedbackForm = feedbackForm.easyform({
    success:function (ef_feedbackForm) {

        var entity = feedbackForm.find("form").formSerializeObject();
        entity.attachmentIds = getAttachmentIds();
        console.log("点反馈按钮："+JSON.stringify(entity))
        $.ajax({
            url: rootPath + "/action/S_dispatch_Feedback_save.action",
            type:"post",
            data:entity,
            dataType:"json",
            success:function (msg) {
                feedbackForm.modal("hide")
                gridTable.bootstrapTable('refresh');
                feedbackRecordTable.bootstrapTable('refresh');
                pageUtils.saveOperationLog({opType:'1',opModule:'执法管理',opContent:'添加执法反馈',refTableId:''})
            }
        });
    },
    error:function () {
        console.log("error")
    }
});

//表单 保存按钮
$("#feedbackTo").bind('click',function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    ef_feedbackForm.submit(false);
});


//表单附件相关js
var uploader;//附件上传组件对象
/**
 * 获取上传组件options
 * @param bussinessId
 * @returns options
 */
function getUploaderOptions(bussinessId) {
    return {
        element: document.getElementById("fine-uploader-gallery"),
        template: 'qq-template',
        chunking: {
            enabled: false,
            concurrent: {
                enabled: true
            }
        },
        resume: {
            enabled: false
        },
        retry: {
            enableAuto: false,
            showButton: false
        },
        failedUploadTextDisplay: {
            mode: 'custom'
        },
        callbacks: {
            onComplete:function (id,fileName,msg,request) {
                uploader.setUuid(id, msg.id);
            },
            onDeleteComplete:function (id) {
                var file = uploader.getUploads({id:id});
                var removeIds = $("#removeId").val();
                if (removeIds) {
                    removeIds+= ("," + file.uuid)
                }else{
                    removeIds = file.uuid;
                }
                $("#removeId").val(removeIds);
            },
            onAllComplete: function (succeed) {
                var self = this;
                $.each(succeed, function (k, v) {
                    $('.qq-upload-download-selector', self.getItemByFileId(v)).toggleClass('qq-hide', false);
                });
            }
        },
        request: {
            endpoint: rootPath + '/Upload',
            params: {
                businessId:bussinessId
            }
        },
        session:{
            endpoint: rootPath + '/action/S_attachment_Attachment_listAttachment.action',
            params: {
                businessId:bussinessId
            }
        },
        deleteFile: {
            enabled: true,
            endpoint: rootPath + "/action/S_attachment_Attachment_delete.action",
            method:"POST"
        },
        debug: true
    };
}

/**
 * 获取附件列表ids
 * @returns {*}
 */
function getAttachmentIds() {
    var attachments = uploader.getUploads();
    if (attachments && attachments.length) {
        var ids = [];
        for (var i = 0 ; i < attachments.length; i++){
            ids.push(attachments[i].uuid);
        }
        return ids.join(",");
    }
    return "";
}

//初始化日期组件
$('.form_datetime').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1
});

function appendOption(selector,options) {
    $.each(options,function (i,v) {
        var option = $("<option>").val(v.code).text(v.name);
        $(selector).append(option);
    })
}

/**
 * 查看反馈表单事件
 * @type {{[click .see]: Window.seeEvent.'click .see'}}
 */
window.seeEvent = {
    'click .see': function (e, value, row, index) {
        console.log(JSON.stringify(row))

        $("#lawerName").val(row.lawerName)
        $("#phone").val(row.phone)
        $("#exeTime").val(row.exeTime)
        $("#exeDesc").val(row.exeDesc)
        $("#caseReason").val(row.caseReason)
        disabledForm($("#feedbackForm"),true)

        uploaderToggle(".bUploader")
        var fuOptions = getUploaderOptions(row.id);
        fuOptions.callbacks.onSessionRequestComplete = function () {
            $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
            $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无上传的附件");
        };
        uploader = new qq.FineUploader(fuOptions);
        bindDownloadSelector();
        $(".qq-upload-button").hide();

        $("#feedbackTo").hide();
    }
};

/***************** 执法记录-现场回传 ***************************/
function initfeedbackRecordTable() {
    feedbackRecordTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_dispatch_Feedback_list.action",
        method:'post',
        pagination:true,
        pageSize:5,
        pageList:[5],
        queryParams:pageUtils.localParams,
        columns: [
            {
                title: 'ID',
                field: 'id',
                align: 'center',
                valign: 'middle',
                sortable: false,
                visible:false
            },
            {
                title: '现场执法人',
                field: 'lawerName',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '联系方式',
                field: 'phone',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '执法时间',
                field: 'exeTime',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '执法详情',
                field: 'exeDesc',
                editable: false,
                sortable: false,
                align: 'center',
                visible:false
            },
            {
                title: '查看',
                field: 'exeDesc',
                editable: false,
                sortable: false,
                align: 'center',
                events: seeEvent,
                formatter: function (value, row, index) {
                    html='<a class="btn btn-md btn-warning see" data-toggle="modal" data-target="#feedbackForm">详情</a>'
                    return html
                }
            }

        ]
    });
    // sometimes footer render error.
    setTimeout(function () {
        feedbackRecordTable.bootstrapTable('resetView');
    }, 200);

}
initfeedbackRecordTable()

$("#overBtn").click(function () {
    $("#overId").val(getIdSelections())
})

$("#overSure").click(function () {
    Ewin.confirm({ title:"办结提示",message: "是否归入办结管理？" }).on(function (e) {
        if (!e) {
            return;
        }

        var entity = overDialog.find("form").formSerializeObject();
        $.ajax({
            url: rootPath + "/action/S_dispatch_DispatchTask_overStatus.action",
            type:"post",
            data:  entity,
            success:function (msg) {
                overDialog.modal('hide');
                gridTable.bootstrapTable('refresh');
            }
        });

    })
})

/************  新增（现场监察）表单 ******************/
/*var newXianChangJianChaForm=$("#newXianChangJianChaForm");
$("#insert").click(function () {
    disabledForm(newXianChangJianChaForm,false)

    $("#eventTime_newXianChangJianChaForm").val((new Date()).format("yyyy-MM-dd hh:mm"))
    newXianChangJianChaForm.find("input[type!='radio'][type!='checkbox']").val("");
    $("textarea").val("");
})

function saveXianChangJianChaAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_dispatch_DispatchTask_saveXianChangJianChaAjax.action",
        type:"post",
        data:entity,
        success:callback
    });
}*/

//------------ 现场监察发送，保存发送人员和 ------------------//
/*var options_newXianChangJianChaForm = {
    params:{
        orgCode:[orgCodeConfig.org.jianChaDaDui.orgCode],//组织机构代码(必填，组织机构代码)
        type:2  //1默认加载所有，2只加载当前机构下人员，3只加载当前机构下的组织机构及人员
    },
    choseMore:false,
    title:"人员选择",//弹出框标题(可省略，默认值：“组织机构人员选择”)
    width:"60%",        //宽度(可省略，默认值：850)
}

var model_newXianChangJianChaForm = $.fn.MsgSend.init(1,options_newXianChangJianChaForm,function(e,data){
    console.info("回调函数data参数："+JSON.stringify(data))

    var d=pageUtils.sendParamDataToString(data)
    console.log("发送："+d)
    $.ajax({
        url: rootPath + "/action/S_dispatch_DispatchTask_saveToMonitorMasterPersonNameList.action",
        type:"post",
        data:d,
        success:function (msg) {
            newXianChangJianChaForm.modal('hide');
            gridTable.bootstrapTable('refresh');
        }
    });
});

//初始化表单验证
var ef_newXianChangJianChaForm = newXianChangJianChaForm.easyform({
    success:function (ef_newXianChangJianChaForm) {
        var entity = newXianChangJianChaForm.find("form").formSerializeObject();
        console.log("保存 现场监察："+JSON.stringify(entity))

        saveXianChangJianChaAjax(entity,function (msg) {
            msg=JSON.parse(msg)
            console.log(msg)
            entity.id=msg.id;
            entity.smsContent=entity.content
            // entity.isSendSms=$("#isSendSms").is(':checked');
            model_newXianChangJianChaForm.open(entity);
            gridTable.bootstrapTable('refresh');
        });
    },
    error:function () {
        console.log("error")
    }
});

//表单 保存按钮
$("#saveXianChangJianChaBtn").bind('click',function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    ef_newXianChangJianChaForm.submit(false);
});*/




$(document).ready(function () {
    loadBlockLevelAndBlockOption(".s_blockLevelId",".s_blockId")
    loadBlockLevelAndBlockOption("#blockLevelId","#blockId")
    loadBlockLevelAndBlockOption("#blockLevelId_feedback","#blockId_feedback")
    loadBlockLevelAndBlockOption("#lookOverFeedbackForm_blockLevelId","#lookOverFeedbackForm_blockId")
    // loadBlockLevelAndBlockOption("#blockLevelId_newXianChangJianChaForm","#blockId_newXianChangJianChaForm")

    if("monitor_master"==role){
        $("#insert").hide();
        $("#feedback").hide();
    }else if("env_pro_sta"==role){
        $("#dealWith").hide();
    }
})

/**
 * Autocomplete  enterpriseName
 */
/*$( function() {

    $("#enterpriseName_newXianChangJianChaForm").autocomplete({
        source: function( request, response ) {
            $.ajax( {
                url: rootPath + "/action/S_enterprise_Enterprise_list.action",
                method:'post',
                dataType: "json",
                data: {
                    name: request.term
                },
                success: function( data ) {
                    for(var i = 0;i<data.rows.length;i++){
                        var result = [];
                        for(var i = 0; i <  data.rows.length; i++) {
                            var ui={};
                            ui.id=data.rows[i].id
                            ui.value=data.rows[i].name
                            ui.envPrincipal=data.rows[i].envPrincipal
                            ui.epPhone=data.rows[i].epPhone
                            result.push(ui);
                        }
                        response( result);
                    }
                }
            } );
        },
        select: function( event, ui ) {
            console.info(ui.item.id)
            $("#enterpriseId_newXianChangJianChaForm").val(ui.item.id)
            $("#supervisor_newXianChangJianChaForm").val(ui.item.envPrincipal)
            $("#supervisorPhone_newXianChangJianChaForm").val(ui.item.epPhone)
        },
    } );

} );*/

function saveAjax_addSiteMonitoringDialog(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_exelaw_SiteMonitoring_save.action",
        type:"post",
        data:entity,
        dataType:"json",
        success:callback
    });
}

var ef_addSiteMonitoringDialog = addSiteMonitoringDialog.easyform({
    success:function (ef_addSiteMonitoringDialog) {
        var entity = $("#addSiteMonitoringDialog").find("form").formSerializeObject();
        entity.attachmentIds = getAttachmentIds();
        entity.userId=userId;
        console.log(entity);
        saveAjax_addSiteMonitoringDialog(entity,function (msg) {
            addSiteMonitoringDialog.modal('hide');
            gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
        });
    }
});

//表单 保存按钮
$("#save").bind('click',function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    ef_addSiteMonitoringDialog.submit(false);
});

//------------------------现场监察报告列表---------------------------//
window.initTable_siteMonitoringReportDialog_operateEvents = {
    'click .initTable_siteMonitoringReportDialog_view': function (e, value, entity, index) {
        $("#isNotProblem"+entity.isNotProblem).get(0).checked=true;

        var inputs = $("#addSiteMonitoringDialog").find('input[type!="radio"],textarea');
        $.each(inputs,function(k,v){
            var tagId = $(v).attr('name');
            $(v).val(entity[tagId]);
        });

        $("#addSiteMonitoringDialog").find('[name="isNotProblem"]').val()

        disabledForm($("#addSiteMonitoringDialog"),true);
        uploaderToggle(".dUploader")
        var fuOptions = getUploaderOptions(entity.id);
        fuOptions.callbacks.onSessionRequestComplete = function () {
            $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
            $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无上传的附件");
        };
        uploader = new qq.FineUploader(fuOptions);
        bindDownloadSelector();
        $(".qq-upload-button").hide();
        $("#addSiteMonitoringDialog").find("#save").hide();
        $("#addSiteMonitoringDialog").find(".btn-cancel").text("关闭");
    }
};

function initTable_siteMonitoringReportDialog() {
    table_siteMonitoringReportDialog.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_exelaw_SiteMonitoring_list.action",
        // height: pageUtils.getTableHeight(),
        method:'post',
        pagination:true,
        pageSize:5,
        pageList:[5],
        queryParams:pageUtils.localParams,
        columns: [
            {
                title: 'ID',
                field: 'id',
                align: 'center',
                valign: 'middle',
                sortable: false,
                visible:false
            },
            {
                title: '企业名称',
                field: 'enterpriseName',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                field: 'blockName',
                title: '所属网格',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                field: 'checkPeople',
                title: '监察人员',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                field: 'monitoringTime',
                title: '监察时间',
                sortable: false,
                align: 'center',
                editable: false,
                formatter:function (value, row, index) {
                    return pageUtils.sub10(value);
                }
            },

            {
                field: 'isNotProblem',
                title: '是否存在问题',
                sortable: false,
                align: 'center',
                editable: false,
                formatter:function (value, row, index) {
                    if(1==value){
                        value="是"
                    }else if(2==value){
                        value="否"
                    }
                    return value;
                }
            },
            {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: initTable_siteMonitoringReportDialog_operateEvents,
                formatter: function (value, row, index) {
                    return '<button type="button" class="btn btn-md btn-warning initTable_siteMonitoringReportDialog_view" data-toggle="modal" data-target="#addSiteMonitoringDialog">查看</button>';
                }
            }

        ]
    });
    // sometimes footer render error.
    setTimeout(function () {
        table_siteMonitoringReportDialog.bootstrapTable('resetView');
    }, 200);

}
initTable_siteMonitoringReportDialog();




