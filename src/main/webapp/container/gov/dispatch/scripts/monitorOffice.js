var gridTable = $('#table'),
    feedbackRecordTable=$("#feedbackRecordTable"),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    eventMsgForm = $("#eventMsg"),
    formTitle = "事件信息",
    selections = [];

loadBlockLevelAndBlockOption();

//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_dispatch_MonitorCase_save.action",
        type:"post",
        data:entity,
        dataType:"json",
        success:callback,
        error:function (msg) {
            console.error(msg)
        }
    });
}
/**
 * 删除请求
 * @param ids 多个,号分隔
 * @param callback
 */
function deleteAjax(ids, callback) {
    $.ajax({
        url: rootPath + "/action/S_dispatch_MonitorCase_delete.action",
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
        url: rootPath+"/action/S_dispatch_MonitorCase_list.action",
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
                title: '投诉对象',
                field: 'enterpriseName',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '接电时间',
                field: 'eventTime',
                sortable: false,
                align: 'center',
                editable: false,
                formatter:function (value, row, index) {
                    return pageUtils.sub10(value);
                }
            },
            {
                title: '接电人',
                field: 'answer',
                editable: false,
                sortable: false,
                align: 'center'
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
                title: '状态跟踪',
                field: 'status',
                editable: false,
                sortable: false,
                align: 'center',
                formatter: function (value, row, index) {
                    /**
                     * 状态
                     * 0：未调度
                     * 1：已调度
                     * 2：已反馈
                     */
                    if (value==0){
                        value="未调度"
                    }else if (value==1){
                        value="已调度"
                    }else if (value==2){
                        value='已反馈'
                    }
                    return value;
                }
            },
            {
                title: '操作',
                editable: false,
                sortable: false,
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
        //有选中数据，启用删除按钮
        removeBtn.prop('disabled', !gridTable.bootstrapTable('getSelections').length);
        //选中一条数据启用修改按钮
        updateBtn.prop('disabled', !(gridTable.bootstrapTable('getSelections').length== 1));
    });

    $(window).resize(function () {
        // 重新设置表的高度
        gridTable.bootstrapTable('resetView', {
            height: pageUtils.getTableHeight()
        });
    });
}

// 生成列表操作方法
function operateFormatter(value, row, index) {
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#feedbackListDialog">查看反馈</button>';
}
// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, row, index) {
        feedbackRecordTable.bootstrapTable('refresh',{query:{id:row.id}})
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
removeBtn.prop('disabled', true);
updateBtn.prop('disabled', true);
/**
 * 列表工具栏 新增和更新按钮打开form表单，并设置表单标识
 */
$("#add").bind('click',function () {
    resetForm();
});
$("#update").bind("click",function () {
    setFormData(getSelections()[0]);
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

            pageUtils.saveOperationLog({opType:'3',opModule:'监察大队办公室',opContent:'删除数据',refTableId:''})
        });
    });

});



/**============列表搜索相关处理============**/
//搜索按钮处理
$("#search").click(function () {
    var queryParams = {};
    var search_enterpriseName = $("#search_enterpriseName").val();
    var search_source = $("#search_source").val();
    var startConnTime=$("#start_connTime").val()
    var endConnTime=$("#end_connTime").val()

    // var status = pageUtils.getRadioValue("s_status");
    if (search_enterpriseName){
        queryParams["enterpriseName"] = search_enterpriseName;
    }
    if (search_source){
        queryParams["source"] = search_source;
    }
    if (startConnTime){
        queryParams["startConnTime"] = startConnTime;
    }
    if (endConnTime){
        queryParams["endConnTime"] = endConnTime;
    }
    gridTable.bootstrapTable('refresh',{
        query:queryParams
    });
});

/**============短信选择人员对话框============**/
var options_sms = {
    params:{
        // orgCode:[],//组织机构代码(必填，组织机构代码)
        //type:2  //1默认加载所有，2只加载当前机构下人员，3只加载当前机构下的组织机构及人员
    },
    title:"人员选择",//弹出框标题(可省略，默认值：“组织机构人员选择”)
    width:"60%",        //宽度(可省略，默认值：850)
}

var model_sms = $.fn.MsgSend.init(2,options_sms,function(e,data){ //短信发送第一个参数为2
    console.info("回调函数data参数："+JSON.stringify(data))

    var d=$.param({ids:data.personObj.id},true)
    d+=$.param({names:data.personObj.name},true)
    d+="&sourceId="+data.sourceId;
    console.log("发送："+d)
    $.ajax({
        url: rootPath + "/action/S_dispatch_MonitorCase_sendSms.action",
        type:"post",
        data:d,
        success:function (msg) {
            gridTable.bootstrapTable('refresh');
            pageUtils.saveOperationLog({opType:'4',opModule:'监察大队办公室',opContent:'短信发送数据',refTableId:''})
        }
    });


});

/**============组织机构选择人员对话框============**/
var options = {
    params:{
        orgCode:['0170001300'],//组织机构代码(必填，组织机构代码)
        type:2  //1默认加载所有，2只加载当前机构下人员，3只加载当前机构下的组织机构及人员
    },
    title:"人员选择",//弹出框标题(可省略，默认值：“组织机构人员选择”)
    width:"60%",        //宽度(可省略，默认值：850)
}

var model = $.fn.MsgSend.init(1,options,function(e,data){
    console.info("回调函数data参数："+JSON.stringify(data))

    var d=pageUtils.sendParamDataToString(data)

    console.log("发送："+d)
    $.ajax({
        url: rootPath + "/action/S_dispatch_DispatchTask_save.action",
        type:"post",
        data:d,
        success:function (msg) {
            // eventMsgForm.modal('hide');
            gridTable.bootstrapTable('refresh');
            pageUtils.saveOperationLog({opType:'4',opModule:'监察大队办公室',opContent:'发送数据',refTableId:''})
        }
    });
});

/**============表单初始化相关代码============**/
var buttonToggle;
//初始化表单验证
var ef = eventMsgForm.easyform({
    success:function (ef) {
        var entity = $("#eventMsg").find("form").formSerializeObject();

        if (buttonToggle=="#save"){
            entity.sendType="#save"
            if('0'!=entity.status){
                Ewin.alert("已调度状态不允许修改或再次发送");
                return;
            }
        }else if(buttonToggle=="#smsSend"){
            entity.sendType="#smsSend"
        }
        entity.attachmentIds = getAttachmentIds();
        console.log("点发送按钮，保存调度单信息："+JSON.stringify(entity))
        saveAjax(entity,function (msg) {
            pageUtils.saveOperationLog({opType:'1',opModule:'监察大队办公室',opContent:'新增数据',refTableId:''})
            gridTable.bootstrapTable('refresh');
            if (buttonToggle=="#save"){
                model.open(msg.id);
            }else if(buttonToggle=="#smsSend"){
                model_sms.open(msg.id);
            }

        });
    },
    error:function () {
        console.log("error")
    }
});

//表单 保存按钮
$('#save').bind('click',function () {
    buttonToggle="#save"
    //验证表单，验证成功后触发ef.success方法保存数据
    ef.submit(false);
});
$('#smsSend').bind('click',function () {
    buttonToggle="#smsSend"
    //验证表单，验证成功后触发ef.success方法保存数据
    ef.submit(false);
});

//初始化日期组件
$('#createTimeContent').datetimepicker({
    language:   'zh-CN',
    autoclose: 1,
    minView: 2
});
$('#openDateContent').datetimepicker({
    language:   'zh-CN',
    autoclose: 1,
    minView: 2
});
/**
 * 设置表单数据
 * @param entity
 * @returns {boolean}
 */
function setFormData(entity) {
    resetForm();
    if (!entity) {return false}
    var id = entity.id;
    $("#id").val(entity.id);

    $("#eventTime").val(entity.eventTime);
    $("#answer").val(entity.answer);
    $("#enterpriseId").val(entity.enterpriseId);
    $("#enterpriseName").val(entity.enterpriseName);
    $("#source").val(entity.source);
    $("#blockLevelId").val(entity.blockLevelId);
    $("#blockId").val(entity.blockId);
    $("#supervisor").val(entity.supervisor);
    $("#supervisorPhone").val(entity.supervisorPhone);
    $("#content").val(entity.content);
    $("#senderName").val(entity.senderName);
    $("#sendPhone").val(entity.sendPhone);
    $("#status").val(entity.status);

    uploaderToggle(".aUploader")
    uploader = new qq.FineUploader(getUploaderOptions(id));
}
function setFormView(entity) {
    setFormData(entity);
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
}
/**
 * 重置表单
 */
function resetForm() {
    eventMsgForm.find("input[type!='radio'][type!='checkbox']").val("");
    $("textarea").val("");

    $("#status").val('0');
    $("#eventTime").val((new Date()).format("yyyy-MM-dd hh:mm"));
    $("#answer").val(userName);
    $("#senderName").val(userName);

    uploaderToggle(".aUploader")
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
    showMeridian: 1
});

function appendOption(selector,options) {
    $(selector).empty();
    $.each(options,function (i,v) {
        var option = $("<option>").val(v.code).text(v.name);
        $(selector).append(option);
    })
}

var code={code:"monitor_office_source"};
var monitor_office_source=dict.getDctionnary(code)
appendOption("#source",monitor_office_source)

/**
 * Autocomplete  enterpriseName
 */
$( function() {

    $("#enterpriseName").autocomplete({
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
                            ui.blockLevelId=data.rows[i].blockLevelId
                            ui.blockId=data.rows[i].blockId
                            result.push(ui);
                        }
                        response( result);
                    }
                }
            } );
        },
        select: function( event, ui ) {
            console.info(ui.item.id)
            $("#enterpriseId").val(ui.item.id)
            $("#supervisor").val(ui.item.envPrincipal)
            $("#supervisorPhone").val(ui.item.epPhone)
            $("#blockLevelId").val(ui.item.blockLevelId)
            $("#blockId").val(ui.item.blockId)
        },
    } );

} );


$(document).ready(function () {
    loadBlockLevelAndBlockOption("#blockLevelId","#blockId")
})

/***************** 执法反馈列表 ***************************/
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

        uploaderToggle(".bUploader")
        var fuOptions = getUploaderOptions(row.id);
        fuOptions.callbacks.onSessionRequestComplete = function () {
            $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
            $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"");
        };
        uploader = new qq.FineUploader(fuOptions);
        $(".qq-upload-button").hide();
    }
};

function initfeedbackRecordTable() {
    feedbackRecordTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_dispatch_MonitorCase_queryFeedbackListByMonitorCaseId.action",
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



