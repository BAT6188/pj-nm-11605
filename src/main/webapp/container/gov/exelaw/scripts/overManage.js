//@ sourceURL=lawManage.js
var gridTable = $('#table'),
    dialog=$("#detailDialog"),
    feedbackRecordTable=$("#feedbackRecordTable"),
    table_siteMonitoringReportDialog = $('#table_siteMonitoringReportDialog'),
    eventMsg_monitorOffice_dialog = $("#eventMsg_monitorOffice"),
    eventMsg_monitorCase_dialog = $("#eventMsg_monitorCase"),
    siteMonitoringReportDialog = $("#siteMonitoringReportDialog"),
    lookOverFeedbackForm = $("#lookOverFeedbackForm"),
    selections = [];
pageUtils.appendOptionFromDictCode(".caseSource",{code:"caseSource"})
/**============grid 列表初始化相关代码============**/
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_dispatch_DispatchTask_list.action?status=5",
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
                align: 'center'
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
                // events: operateEvents,
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
                title: '查看',
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

    $(window).resize(function () {
        // 重新设置表的高度
        gridTable.bootstrapTable('resetView', {
            height: pageUtils.getTableHeight()
        });
    });
}

window.punishEvents = {
    'click .punish': function (e, value, row, index) {
        var url = rootPath + "/container/gov/exelaw/punish.jsp?id=" + row.id;
        pageUtils.toUrl(url);

    }
};

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

window.siteMonitoringReportEvents = {
    'click .siteMonitoringReport': function (e, value, entity, index) {
        siteMonitoringReportDialog.find(".tableBox").data({dispatchId:entity.id});
        table_siteMonitoringReportDialog.bootstrapTable('refresh',{
            query:{dispatchId:entity.id}
        });
    }
};

// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, row, index) {

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

        lookOverFeedbackForm.find(".tableBox").data({dispatchId:row.id});
        feedbackRecordTable.bootstrapTable('refresh',{query:{dispatchId:row.id}})

    }
};

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
        queryParams:function (p) {
            p = pageUtils.localParams(p);
            p.dispatchId=lookOverFeedbackForm.find(".tableBox").data('dispatchId');
            return p;
        },
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



function feedbackStatusoperateFormatter(value, row, index) {
    if (row.status>='3'){
        return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#lookOverFeedbackForm">已反馈</button>';
    }else {
        return '未反馈'
    }
}
function resetDialog(dialog) {
    dialog.find('form')[0].reset();
    dialog.find("#isSendSms").attr("checked",false);
    uploader = new qq.FineUploader(getUploaderOptions());
    disabledForm(dialog,false);
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


                uploaderToggle(".zUploader")
                var fuOptions = getUploaderOptions(entity.id);
                fuOptions.callbacks.onSessionRequestComplete = function () {
                    $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
                    $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无上传的附件");
                };
                uploader = new qq.FineUploader(fuOptions);
                bindDownloadSelector();
                $(".qq-upload-button").hide();

            }

        }
    }
};

/*function lookOverFormatter(value, row, index) {
    return '<button type="button" class="btn btn-md btn-warning lookOver" data-toggle="modal" data-target="#detailDialog">查看</button>';
}

window.lookOverEvents = {
    'click .lookOver': function (e, value, entity, index) {
        // window.open(rootPath+"/action/S_officetemp_OfficeTemp_showTemplate.action?" +
        //     "id=OverManage&beanName=overManageService&bussinessId="+entity.id);

        var inputs = dialog.find('[name]');
        $.each(inputs,function(k,v){
            var tagId = $(v).attr('name');
            $(v).val(entity[tagId]);
        });

        var value=entity.source;
        if(1==value){
            value="12369"
        }else if (2==value){
            value="区长热线"
        }else if (3==value){
            value="市长热线"
        }else if (4==value){
            value="现场监察"
        }else if (0==value){
            value="监控中心"
        }
        dialog.find('[name=source]').val(value);

        dialog.find("input").attr("disabled",true);
        uploaderToggle(".aUploader")
        var fuOptions = getUploaderOptions(entity.id);
        fuOptions.callbacks.onSessionRequestComplete = function () {
            $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
            $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无上传的附件");
        };
        uploader = new qq.FineUploader(fuOptions);
        $(".qq-upload-button").hide();
    }
};*/

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

/**============列表搜索相关处理============**/
//搜索按钮处理
$("#search").click(function () {
    /*var queryParams = {};
    var s_enterpriseName = $("#s_enterpriseName").val();
    var s_source = $("#s_source").val();
    var start_eventTime=$("#start_eventTime").val()
    var end_eventTime=$("#end_eventTime").val()
    var blockLevelId=$(".s_blockLevelId").val()
    var blockId=$(".s_blockId").val()

    if (blockLevelId){
        queryParams["blockLevelId"] = blockLevelId;
    }
    if (blockId){
        queryParams["blockId"] = blockId;
    }
    if (s_enterpriseName){
        queryParams["enterpriseName"] = s_enterpriseName;
    }
    if (s_source){
        queryParams["source"] = s_source;
    }
    if (start_eventTime){
        queryParams["startEventTime"] = start_eventTime;
    }
    if (end_eventTime){
        queryParams["endEventTime"] = end_eventTime;
    }*/
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
});






//表单附件相关js
var uploader;//附件上传组件对象
var uploader2;
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
        validation: {
            itemLimit: 5
        },
        debug: true
    };
}

function getUploaderOptions2(bussinessId) {
    return {
        element: document.getElementById("fine-uploader-gallery2"),
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
                uploader2.setUuid(id, msg.id);
            },
            onDeleteComplete:function (id) {
                var file = uploader2.getUploads({id:id});
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
            endpoint: rootPath + '/Upload?type=2',
            params: {
                businessId:bussinessId
            }
        },
        session:{
            endpoint: rootPath + '/action/S_attachment_Attachment_listAttachment.action',
            params: {
                businessId:bussinessId,
                attachmentType:2
            }
        },
        deleteFile: {
            enabled: true,
            endpoint: rootPath + "/action/S_attachment_Attachment_delete.action",
            method:"POST"
        },
        validation: {
            itemLimit: 5
        },
        debug: true
    };
}

function getAttachmentIds(_uploader) {
    var ids = [];
    if( _uploader ==undefined){
        _uploader=[uploader];
    }
    $.each(_uploader,function (i,v) {
        if(v!=undefined){
            var attachments = v.getUploads();
            if (attachments && attachments.length) {
                for (var i = 0 ; i < attachments.length; i++){
                    ids.push(attachments[i].uuid);
                }
            }
        }
    })
    if (ids.length>0){
        return ids=ids.join(",");
    }else {
        return ''
    }
}

$("#fine-uploader-gallery2").on('click', '.qq-upload-download-selector', function () {
    var uuid = uploader2.getUuid($(this.closest('li')).attr('qq-file-id'));
    window.location.href = rootPath+"/action/S_attachment_Attachment_download.action?id=" + uuid;
});


/**
 * 绑定下载按钮事件
 */
$("#fine-uploader-gallery").on('click', '.qq-upload-download-selector', function () {
    var uuid = uploader.getUuid($(this.closest('li')).attr('qq-file-id'));
    window.location.href = rootPath+"/action/S_attachment_Attachment_download.action?id=" + uuid;
});

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

//------------------------现场监察报告列表---------------------------//
window.initTable_siteMonitoringReportDialog_operateEvents = {
    'click .initTable_siteMonitoringReportDialog_view': function (e, value, entity, index) {
        $("#isNotProblem"+entity.isNotProblem).get(0).checked=true;

        var inputs = $("#addSiteMonitoringDialog").find('input[type!="radio"],textarea');
        $.each(inputs,function(k,v){
            var tagId = $(v).attr('name');
            $(v).val(entity[tagId]);
        });

        disabledForm($("#addSiteMonitoringDialog"),true);
        uploaderToggle(".dUploader")
        var fuOptions = getUploaderOptions(entity.id);
        fuOptions.callbacks.onSessionRequestComplete = function () {
            $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
            $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无上传的附件");
        };
        uploader = new qq.FineUploader(fuOptions);

        var fuOptions2 = getUploaderOptions2(entity.id);
        fuOptions2.callbacks.onSessionRequestComplete = function () {
            $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
            $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无上传的附件");
        };
        uploader2 = new qq.FineUploader(fuOptions2);
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
        queryParams:function (p) {
            p = pageUtils.localParams(p);
            p.dispatchId=siteMonitoringReportDialog.find(".tableBox").data('dispatchId');
            return p;
        },
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

$(document).ready(function () {
    loadBlockLevelAndBlockOption(".s_blockLevelId",".s_blockId")
})





