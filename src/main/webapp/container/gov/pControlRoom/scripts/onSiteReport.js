//@ sourceURL=lawManage.js
var gridTable = $('#table'),
    feedbackRecordTable=$("#feedbackRecordTable"),
    overBtn = $('#overBtn'),
    dealWithBtn = $('#dealWith'),
    feedbackBtn = $('#feedback'),
    eventMsgForm = $("#lookOverFeedbackForm"),
    feedbackForm=$("#feedbackForm"),
    selections = [];

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
        url: rootPath+"/action/S_dispatch_DispatchTask_list.action?role="+role,
        height: pageUtils.getTableHeight(),
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
        queryParams:function (param) {
            var temp = pageUtils.getBaseParams(param);
            temp.monitorReportStatus = 1;
            return temp;
        },
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
                title: '接电人',
                field: 'answer',
                editable: false,
                sortable: false,
                align: 'center',
                visible:false
            },
            {
                title: '信息来源',
                field: 'source',
                editable: false,
                sortable: false,
                align: 'center',
                formatter:function (value, row, index) {
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
                field: 'reason',
                title: '原因',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                field: 'reason',
                title: '现场监察监测报告',
                sortable: false,
                align: 'center',
                editable: false,
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
        overBtn.prop('disabled', !gridTable.bootstrapTable('getSelections').length);
        //选中一条数据启用修改按钮
        dealWithBtn.prop('disabled', !(gridTable.bootstrapTable('getSelections').length== 1));
        feedbackBtn.prop('disabled', !(gridTable.bootstrapTable('getSelections').length== 1));
    });

    $(window).resize(function () {
        // 重新设置表的高度
        gridTable.bootstrapTable('resetView', {
            height: pageUtils.getTableHeight()
        });
    });
}
var rowData = {}
function lookOverFormatter(value, row, index) {
    rowData[index] = row;
    switch (row.monitorReportReadStatus){
        case '1':
            return '<button type="button" class="btn btn-md btn-info lookOver" onclick="setFormData('+index+')" data-toggle="modal" data-target="#lookOverFeedbackForm">已查看</button>';
            break;
        default:
            return '<button type="button" class="btn btn-md btn-danger lookOver" onclick="setFormData('+index+',this)" data-toggle="modal" data-target="#lookOverFeedbackForm">未查看</button>';
    }
}
function changeLookStatus(id,obj){
    $.ajax({
        url: rootPath + "/action/S_dispatch_DispatchTask_updateEntity.action",
        type:"post",
        async:false,
        data:{id:id,monitorReportReadStatus:1},
        dataType:"json",
        success:function(data){
            $(obj).attr('class','btn btn-md btn-info view');
            $(obj).html('已查看');
        }
    });
}
function setFormData(index,obj){
    var entity = rowData[index];
    resetEventMsgFormData();
    if (!entity) {return false}
    var id = entity.id;
    if(obj){changeLookStatus(id,obj);}
    eventMsgForm.find(".form-control").attr("disabled",true);

    var inputs = eventMsgForm.find('.form-control');
    $.each(inputs,function(k,v){
        var tagId = $(v).attr('name');
        var value = entity[tagId];
        if($(v)[0].tagName=='SELECT'){
            $(v).find("option[value='"+value+"']").attr("selected",true);
        }else{
            $(v).val(value);
        }
    });

    uploaderToggle(".aUploader")
    var fuOptions = getUploaderOptions(entity.monitorCaseId);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无附件信息!");
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
    $("#dispatch").hide();
    $("#cancel").text("关闭")
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
overBtn.prop('disabled', true);
dealWithBtn.prop('disabled', true);
feedbackBtn.prop('disabled', true);

/**============列表搜索相关处理============**/
//搜索按钮处理
$("#search").click(function () {
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
});

/**============表单初始化相关代码============**/
/**
 * 重置表单
 */
function resetEventMsgFormData() {
    eventMsgForm.find('form')[0].reset();
    uploader = new qq.FineUploader(getUploaderOptions());
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

//初始化日期组件
$('.form_datetime').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1,
    pickerPosition: "bottom-left"
});

function appendOption(selector,options) {
    $.each(options,function (i,v) {
        var option = $("<option>").val(v.code).text(v.name);
        $(selector).append(option);
    })
}

var code={code:"monitor_office_source"};
var monitor_office_source=dict.getDctionnary(code)
appendOption("#source",monitor_office_source)

$(document).ready(function () {
    loadBlockLevelAndBlockOption(".s_blockLevelId",".s_blockId")
    loadBlockLevelAndBlockOption("#blockLevelId","#blockId")
    loadBlockLevelAndBlockOption("#blockLevelId_feedback","#blockId_feedback")
    loadBlockLevelAndBlockOption("#lookOverFeedbackForm_blockLevelId","#lookOverFeedbackForm_blockId")
    loadBlockLevelAndBlockOption("#blockLevelId_newXianChangJianChaForm","#blockId_newXianChangJianChaForm")

    if("monitor_master"==role){
        $("#insert").hide();
        $("#feedback").hide();
    }else if("env_pro_sta"==role){
        $("#dealWith").hide();
    }
})



