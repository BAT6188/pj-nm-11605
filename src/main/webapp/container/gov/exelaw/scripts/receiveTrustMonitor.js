var gridTable = $('#table'),
    checkButton = $('#checkButton'),
    form = $("#demoForm"),
    auditForm=$("#auditForm"),
    formTitle = "Demo",
    selections = [];

//保存ajax请求
function saveAndAgreeAndSend(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_exelaw_TrustMonitor_saveAndAgreeAndSend.action",
        type:"post",
        data:entity,
        dataType:"json",
        success:callback
    });
}

/**============grid 列表初始化相关代码============**/
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_exelaw_TrustMonitor_list.action?module=receiveTrustMonitor",
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
                title: '企业名称',
                field: 'enterpriseName',
                editable: false,
                sortable: false,
                align: 'center',
                events: approveAndSendEvents,
                formatter: function (value, row, index) {
                    var isNewDiv=""
                    if (row.selfReadStatusForJianchadadui!='1'){
                        isNewDiv='<div id="isNew">&nbsp;</div>'
                    }
                    return '<div style="cursor: pointer;padding: 8px;color: #109e16;" class="approveAndSend" data-toggle="modal" data-target="#demoForm">'+value+isNewDiv+'</div>';
                }
            },
            {
                title: '企业所在网格',
                field: 'blockName',
                sortable: false,
                align: 'center',
                editable: false,
                formatter:function (value, row, index) {
                    return value;
                }
            },
            {
                title: '申请部门',
                field: 'applyOrg',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '申请人',
                field: 'applicant',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '联系方式',
                field: 'applicantPhone',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '监测内容',
                field: 'monitorContent',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '监测时间',
                field: 'monitorTime',
                editable: false,
                sortable: false,
                align: 'center',
                formatter:function (value, row, index) {
                    return pageUtils.sub16(value);
                }
            },
            {
                title: '发送至',
                field: 'monitoringStationOfficePersonNameList',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '审批意见',
                field: 'auditSuggestionStatus',
                editable: false,
                sortable: false,
                align: 'center',
                events: lookoverAuditFormEvents,
                formatter:auditFormFormatter
            },
            {
                field: 'status',
                title: '反馈状态',
                align: 'center',
                events: operateEvents,
                formatter: operateFormatter
            },
            {
                field: 'operate',
                title: '查看',
                align: 'center',
                events: {
                    'click .justLook': function (e, value, entity, index) {
                        setEntity(entity);
                    }
                },
                formatter: function(value,row,index){
                    return '<button id="checkButton" type="button" class="btn btn-sm btn-warning justLook" data-toggle="modal" data-target="#lookOverFeedbackDetailForm"><span>查看</span></button>';
                }
            },
        ]
    });
    // sometimes footer render error.
    setTimeout(function () {
        gridTable.bootstrapTable('resetView');
    }, 200);

    //列表checkbox选中事件
    gridTable.on('check.bs.table uncheck.bs.table ' +
        'check-all.bs.table uncheck-all.bs.table', function () {
        //选中一条数据启用修改按钮
        checkButton.prop('disabled', !(gridTable.bootstrapTable('getSelections').length== 1));
    });

    $(window).resize(function () {
        // 重新设置表的高度
        gridTable.bootstrapTable('resetView', {
            height: pageUtils.getTableHeight()
        });
    });
}

// 列表操作事件
window.approveAndSendEvents = {
    'click .approveAndSend': function (e, value, row, index) {
        var url=rootPath + "/action/S_exelaw_TrustMonitor_updateSelfReadStatusForJianchadadui.action";
        pageUtils.updateSelfReadStatus(url,row.id,1)
        setFormData(row);
    }
};
function auditFormFormatter(value, row, index) {
    if(value=='1'){
        value='同意'
    }else if(value=='2'){
        value='不同意'
    }else {
        value='-'
    }
    return '<div style="cursor: pointer;padding: 8px;color: #c3a61d;" class="view" data-toggle="modal" data-target="#auditForm">'+value+'</div>';
}
window.lookoverAuditFormEvents = {
    'click .view': function (e, value, entity, index) {
        for(p in entity){
            var selector="#"+p
            $(selector).val(entity[p])
        }
        disabledForm(auditForm,true)
        $("#save").hide()
        $("#cancel").text("关闭")
    }
};

// 生成列表操作方法
function operateFormatter(value, row, index) {
    if (value==7){
        value='<div style="cursor: pointer;padding: 8px;color: #c3a61d;" class="view" data-toggle="modal" data-target="#lookOverFeedbackDetailForm">已反馈</div>'
    }else {
        value="未反馈"
    }
    return value;
}
// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, entity, index) {
        disabledForm($("#lookOverFeedbackDetailForm"),true);
        $("#enterpriseName_lookOverFeedbackDetailForm").val(entity.enterpriseName);
        $("#monitorContent_lookOverFeedbackDetailForm").val(entity.monitorContent);
        $("#applyOrg_lookOverFeedbackDetailForm").val(entity.applyOrg);
        $("#applicant_lookOverFeedbackDetailForm").val(entity.applicant);
        $("#applicantPhone_lookOverFeedbackDetailForm").val(entity.applicantPhone);
        $("#monitorTime_lookOverFeedbackDetailForm").val(entity.monitorTime);
        $("#trustOrgAddress_lookOverFeedbackDetailForm").val(entity.trustOrgAddress);
        $("#monitorAddress_lookOverFeedbackDetailForm").val(entity.monitorAddress);
        $("#monitorContentDetail_lookOverFeedbackDetailForm").val(entity.monitorContentDetail);

        $("#monitor").val(entity.monitor);
        $("#monitorPhone").val(entity.monitorPhone);
        $("#feedbackContent").val(entity.feedbackContent);

        uploaderToggle(".bUploader")
        var fuOptions = getUploaderOptions(entity.id);
        fuOptions.callbacks.onSessionRequestComplete = function () {
            $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
            $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无上传的附件");
        };
        uploader = new qq.FineUploader(fuOptions);
        bindDownloadSelector();
        $(".qq-upload-button").hide();
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
checkButton.prop('disabled', true);

$("#checkButton").bind("click",function () {
    var entity=getSelections()[0];
    setEntity(entity);
});

function setEntity(entity){
    $("#lookOverFeedbackDetailForm").find("input").attr("disabled",true);
    $("#lookOverFeedbackDetailForm").find("textarea").attr("disabled",true);
    $("#enterpriseName_lookOverFeedbackDetailForm").val(entity.enterpriseName);
    $("#monitorContent_lookOverFeedbackDetailForm").val(entity.monitorContent);
    $("#applyOrg_lookOverFeedbackDetailForm").val(entity.applyOrg);
    $("#applicant_lookOverFeedbackDetailForm").val(entity.applicant);
    $("#applicantPhone_lookOverFeedbackDetailForm").val(entity.applicantPhone);
    $("#monitorTime_lookOverFeedbackDetailForm").val(entity.monitorTime);
    $("#trustOrgAddress_lookOverFeedbackDetailForm").val(entity.trustOrgAddress);
    $("#monitorAddress_lookOverFeedbackDetailForm").val(entity.monitorAddress);
    $("#monitorContentDetail_lookOverFeedbackDetailForm").val(entity.monitorContentDetail);

    $("#monitor").val(entity.monitor);
    $("#monitorPhone").val(entity.monitorPhone);
    $("#feedbackContent").val(entity.feedbackContent);

    uploaderToggle(".bUploader")
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无上传的附件");
    };
    uploader = new qq.FineUploader(fuOptions);
    bindDownloadSelector();
    $(".qq-upload-button").hide();
}


/**============列表搜索相关处理============**/
//搜索按钮处理
$("#search").click(function () {
    var queryParams = {};
    var applyOrgId = $("#s_applyOrgId").val();
    var start_monitorTime = $("#start_monitorTime").val();
    var end_monitorTime = $("#end_monitorTime").val();
    var blockLevelId = $(".s_blockLevelId").val();
    var blockId = $(".s_blockId").val();

    queryParams["module"] = "receiveTrustMonitor";
    if (blockLevelId){
        queryParams["blockLevelId"] = blockLevelId;
    }
    if (blockId){
        queryParams["blockId"] = blockId;
    }
    if (applyOrgId){
        queryParams["applyOrgId"] = applyOrgId;
    }
    if (start_monitorTime){
        queryParams["start_monitorTime"] = start_monitorTime;
    }
    if (end_monitorTime){
        queryParams["end_monitorTime"] = end_monitorTime;
    }
    gridTable.bootstrapTable('refresh',{
        query:queryParams
    });
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

/**============配置组织发送弹出框============**/
var options = {
    params:{
        orgCode:[orgCodeConfig.org.dongShengQuHuanBaoJu.orgCode],//组织机构代码(必填，组织机构代码)
        type:3 //1默认加载所有，2只加载当前机构下人员，3只加载当前机构下的组织机构及人员
    },
    choseMore:false,
    title:"人员选择",//弹出框标题(可省略，默认值：“组织机构人员选择”)
    width:"60%",        //宽度(可省略，默认值：850)
}
var model = $.fn.MsgSend.init(1,options,function(e,data){
    var d=pageUtils.sendParamDataToString(data)
    d+="&auditor="+userName;
    console.log("发送："+d)
    $.ajax({
        url: rootPath + "/action/S_exelaw_TrustMonitor_saveToMonitorOfficeAndMasterPersonList.action",
        type:"post",
        data:d,
        success:function (ret) {
            form.modal('hide');
            gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});

            var receivers = [];
            $.each(data.personObj,function (i,v) {
                var receiver1 = {receiverId:v.userId,receiverName:v.name};
                receivers.push(receiver1);
            })
            var msg = {
                'msgType':10,
                'title':'委托监测',
                'content':data.sourceId.content,
                'businessId':ret
            };
            pageUtils.sendMessage(msg, receivers);

            pageUtils.saveOperationLog({opType:'4',opModule:'委托监测',opContent:'发送数据',refTableId:''})
        }
    });
});

/**============表单初始化相关代码============**/

//初始化表单验证
var ef = form.easyform({
    success:function (ef) {
        var entity = $("#demoForm").find("form").formSerializeObject();
        entity.attachmentIds = getAttachmentIds();
        console.log("同意并发送："+JSON.stringify(entity))
        saveAndAgreeAndSend(entity,function (msg) {
            gridTable.bootstrapTable('refresh');

            entity.id=msg.id;
            //TODO 委托监测短信内容
            entity.smsContent=entity.monitorContentDetail
            entity.isSendSms=$("#isSendSms").is(':checked');
            model.open(entity);
        });
    }
});

//表单 保存按钮
$("#saveAndAgreeAndSend").bind('click',function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    ef.submit(false);
});
/**
 * 设置表单数据
 * @param entity
 * @returns {boolean}
 */
function setFormData(entity) {
    resetForm();
    if (!entity) {return false}

    disabledForm(form,true);
    $("#id").attr("disabled",false);
    $("#removeId").attr("disabled",false);
    $(".edit").attr("disabled",false);

    var id = entity.id;
    $("#id").val(entity.id);
    $("#removeId").val("");
    $("#enterpriseName").val(entity.enterpriseName);
    $("#enterpriseId").val(entity.enterpriseId);
    $("#monitorContent").val(entity.monitorContent);
    $("#applyOrgId").val(entity.applyOrgId);
    $("#applicant").val(entity.applicant);
    $("#applicantPhone").val(entity.applicantPhone);
    $("#monitorTime").val(entity.monitorTime);
    $("#trustOrgAddress").val(entity.trustOrgAddress);
    $("#monitorAddress").val(entity.monitorAddress);
    $("#monitorContentDetail").val(entity.monitorContentDetail);

    $("#auditorForSend").val(userName)
    $("#auditTimeForSend").val((new Date()).format("yyyy-MM-dd hh:mm"))
    $("#auditPositionForSend").val(entity.auditPosition)
    $("#auditorPhoneForSend").val(entity.auditorPhone)
    $("#auditSuggestionForSend").val(entity.auditSuggestion)

    uploaderToggle(".aUploader")
    uploader = new qq.FineUploader(getUploaderOptions(id));
    bindDownloadSelector();
}
function setFormView(entity) {
    setFormData(entity);
    form.find(".form-title").text("查看"+formTitle);
    disabledForm(form,true);
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无上传的附件");
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
    form.find("#save").hide();
    form.find(".btn-cancel").text("关闭");
}
function disabledForm(selector,disabled) {
    selector.find("input").attr("disabled",disabled);
    selector.find("textarea").attr("disabled",disabled);
    selector.find("select").attr("disabled",disabled);
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
/**
 * 重置表单
 */
function resetForm() {
    form.find("input[type!='radio'][type!='checkbox']").val("");
    // uploader = new qq.FineUploader(getUploaderOptions());
    disabledForm(form,false);
}


/**============不同意表单============**/
$("#saveAndNotAgree").click(function () {
    Ewin.confirm({ message: "确认不同意吗？" }).on(function (e) {
        if (!e) {
            return;
        }

        $.ajax({
            url: rootPath + "/action/S_exelaw_TrustMonitor_saveNotAgreeForm.action?trustMonitorId="+$("#id").val(),
            success:function (msg) {
                form.modal('hide');
                gridTable.bootstrapTable('refresh');
            }
        });

    });
})



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
        validation: {
            itemLimit: 5
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

$(document).ready(function () {
    var optionsSetting={code:"orgId",name:"orgName"}
    ajaxLoadOption(rootPath+"/action/S_exelaw_TrustMonitor_getEnvironmentalProtectionStationList.action","#s_applyOrgId",optionsSetting)
    ajaxLoadOption(rootPath+"/action/S_exelaw_TrustMonitor_getEnvironmentalProtectionStationList.action","#applyOrgId",optionsSetting)

    loadBlockLevelAndBlockOption(".s_blockLevelId",".s_blockId")


})


