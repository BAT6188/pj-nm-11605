//@ sourceURL=monitoring_station_office.js
var gridTable = $('#table'),
    checkButton = $('#checkButton'),
    form = $("#demoForm"),
    formTitle = "委托监测",
    selections = [];


/**============grid 列表初始化相关代码============**/
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_exelaw_TrustMonitor_list.action?module=monitoring_station_office",
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
                events: sendEvents,
                formatter: function (value, row, index) {
                    return '<div style="cursor: pointer;padding: 8px;color: #109e16;" class="send" data-toggle="modal" data-target="#demoForm">'+value+'</div>';
                }
            },
            {
                title: '监测内容',
                field: 'monitorContent',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '监测时间',
                field: 'monitorTime',
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
                field: 'status',
                title: '状态',
                align: 'center',
                formatter:function (value, row, index) {
                    if (value>=5){
                        value="已发送"
                    }else {
                        value="未发送"
                    }
                    return value;
                }
            },
            {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: operateEvents,
                formatter: operateFormatter
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
window.sendEvents = {
    'click .send': function (e, value, row, index) {
        setFormData(row);
    }
};

// 生成列表操作方法
function operateFormatter(value, row, index) {
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#demoForm">反馈</button>';
}
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
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"");
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();


});




/**============列表搜索相关处理============**/
//搜索按钮处理
$("#search").click(function () {
    var queryParams = {};
    var enterpriseName = $("#s_enterpriseName").val();
    var start_monitorTime = $("#start_monitorTime").val();
    var end_monitorTime = $("#end_monitorTime").val();

    if (enterpriseName){
        queryParams["enterpriseName"] = enterpriseName;
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

/**
 * 设置表单数据
 * @param entity
 * @returns {boolean}
 */
function setFormData(entity) {
    $("#demoForm").find("input").attr("disabled",true)
    $("#demoForm").find("textarea").attr("disabled",true)
    $("#demoForm").find("select").attr("disabled",true)
    $("#id").attr("disabled",false);
    for(p in entity){
        var selector="#"+p
        $(selector).val(entity[p])
    }

    uploaderToggle(".aUploader")
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"");
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
}
function setFormView(entity) {
    setFormData(entity);
    form.find(".form-title").text("查看"+formTitle);
    disabledForm(true);
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"");
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
    form.find("#save").hide();
    form.find(".btn-cancel").text("关闭");
}

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

/**
 * 绑定下载按钮事件
 */
$("#fine-uploader-gallery").on('click', '.qq-upload-download-selector', function () {
    var uuid = uploader.getUuid($(this.closest('li')).attr('qq-file-id'));
    window.location.href = rootPath+"/action/S_attachment_Attachment_download.action?id=" + uuid;
});

$(document).ready(function () {
    var optionsSetting={code:"orgId",name:"orgName"}
    ajaxLoadOption(rootPath+"/action/S_exelaw_TrustMonitor_getEnvironmentalProtectionStationList.action","#applyOrgId",optionsSetting)

})


