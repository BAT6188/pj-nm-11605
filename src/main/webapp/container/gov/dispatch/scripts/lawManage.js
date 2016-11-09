//@ sourceURL=lawManage.js
var gridTable = $('#table'),
    feedbackRecordTable=$("#feedbackRecordTable"),
    overBtn = $('#overBtn'),
    dealWithBtn = $('#dealWith'),
    feedbackBtn = $('#feedback'),
    eventMsgForm = $("#eventMsg"),
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
                title: '企业名称',
                field: 'enterpriseName',
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
                title: '状态',
                field: 'status',
                editable: false,
                sortable: false,
                align: 'center',
                events: operateEvents,
                formatter: function (value, row, index) {

                    /**
                     * 1:未调度
                     * 2:已发送
                     * 3:已反馈
                     * 4:已处罚
                     * 5:已办结
                     * 状态
                     */
                    if (value==1){
                        value="未调度"
                    }else if(value==2){
                        value="已发送"
                    }else if(value==3){
                        value='<a class="btn btn-md btn-warning view" data-toggle="modal" data-target="#lookOverFeedbackForm">已反馈</a>'
                    }else if(value==4){
                        value="已处罚"
                    }else if(value==5){
                        value="已办结"
                    }

                    return value
                }
            },
            {
                field: 'reason',
                title: '现场监察监测报告',
                sortable: false,
                align: 'center',
                editable: false
            },

            {
                field: '',
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
                    if (row.status==1){
                        value="未调度"
                    }else if(row.status==2){
                        value="已发送"
                    }else if(row.status==3){
                        value='已反馈'
                    }else if(row.status==4){
                        value="<a class='btn btn-md btn-warning punish'>已处罚</a>"
                    }else if(row.status==5){
                        value="已办结"
                    }

                    return value
                }
            },
            {
                field: 'monitorCaseId',
                sortable: false,
                align: 'center',
                editable: false,
                visible:false
            },
            {
                field: 'overValue',
                title: '超标值',
                sortable: false,
                align: 'center',
                editable: false,
                visible:false
            },
            {
                field: 'thrValue',
                title: '超标阀值',
                sortable: false,
                align: 'center',
                editable: false,
                visible:false
            },
            {
                field: 'sendRemark',
                title: '备注',
                sortable: false,
                align: 'center',
                editable: false,
                visible:false
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

function lookOverFormatter(value, row, index) {
    return '<button type="button" class="btn btn-md btn-warning lookOver" data-toggle="modal" data-target="#eventMsg">查看</button>';
}

window.lookOverEvents = {
    'click .lookOver': function (e, value, entity, index) {
        resetEventMsgFormData();
        if (!entity) {return false}
        var id = entity.id;

        eventMsgForm.find("input").attr("disabled",true);
        eventMsgForm.find("textarea").attr("disabled",true);
        eventMsgForm.find("select").attr("disabled",true);

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
        $("#sendRemark").val(entity.sendRemark);
        $("#senderName").val(entity.senderName);
        $("#senderId").val(entity.senderId);
        $("#sendTime").val(entity.sendTime);


        uploaderToggle(".aUploader")
        var fuOptions = getUploaderOptions(entity.monitorCaseId);
        fuOptions.callbacks.onSessionRequestComplete = function () {
            $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
            $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"");
        };
        uploader = new qq.FineUploader(fuOptions);
        $(".qq-upload-button").hide();

        $("#dispatch").hide();
        $("#cancel").text("关闭")
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
        $("#lookOverFeedbackForm_sendRemark").val(row.sendRemark);

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

$("#dealWith").bind("click",function () {
    setEventMsgFormData(getSelections()[0]);
});

$("#feedback").bind("click",function () {
    feedbackForm.find("input").val("")
    feedbackForm.find("textarea").val("")
    $("#dispatchId").val(getSelections()[0].id)

    uploaderToggle(".bUploader")
    uploader = new qq.FineUploader(getUploaderOptions());

    $("#feedbackTo").show();

});




/**============列表搜索相关处理============**/
//搜索按钮处理
$("#search").click(function () {
    var queryParams = {};
    var s_enterpriseName = $("#s_enterpriseName").val();
    var s_source = $("#s_source").val();
    var s_status = $("#s_status").val();
    var start_eventTime=$("#start_eventTime").val()
    var end_eventTime=$("#end_eventTime").val()
    var blockLevelId=$(".s_blockLevelId").val()
    var blockId=$(".s_blockId").val()

    queryParams['role']=role

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
    if (s_status){
        queryParams["status"] = s_status;
    }
    if (start_eventTime){
        queryParams["startEventTime"] = start_eventTime;
    }
    if (end_eventTime){
        queryParams["endEventTime"] = end_eventTime;
    }
    gridTable.bootstrapTable('refresh',{
        query:queryParams
    });
});

/**============选择人员对话框============**/
var options = {
    params:{
        orgCode:['0170001300'],//组织机构代码(必填，组织机构代码)
        type:3  //1默认加载所有，2只加载当前机构下人员，3只加载当前机构下的组织机构及人员
    },
    title:"人员选择",//弹出框标题(可省略，默认值：“组织机构人员选择”)
    width:"60%",        //宽度(可省略，默认值：850)
}

var model = $.fn.MsgSend.init(1,options,function(e,data){
    var d=$.param({ids:data.ids},true)
    d+="&sourceId="+data.sourceId;
    console.log("发送："+d)
    $.ajax({
        url: rootPath + "/action/S_dispatch_DispatchTask_saveToEnvProStaPersonList.action",
        type:"post",
        data:d,
        success:function (msg) {
            eventMsgForm.modal('hide');
            gridTable.bootstrapTable('refresh');
        }
    });
});

/**============表单初始化相关代码============**/
//初始化表单验证
var ef_eventMsgForm = eventMsgForm.easyform({
    success:function (ef_eventMsgForm) {
        var entity={}
        entity.id=$("#id").val();
        entity.content=$("#content").val();
        entity.sendRemark=$("#sendRemark").val();
        entity.removeId=$("#removeId").val();
        entity.attachmentIds = getAttachmentIds();
        console.log("点调度按钮，只保存能编辑的表单数据："+JSON.stringify(entity))

        saveAjax(entity,function (msg) {
            gridTable.bootstrapTable('refresh');
            model.open(msg);//打开dialog
        });
    },
    error:function () {
        console.log("error")
    }
});

//表单 保存按钮
$("#dispatch").bind('click',function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    ef_eventMsgForm.submit(false);
});

/**
 * 设置 处置按钮打开的对话框
 * @param entity
 * @returns {boolean}
 */
function setEventMsgFormData(entity) {
    resetEventMsgFormData();
    if (!entity) {return false}
    var id = entity.id;

    eventMsgForm.find("input").attr("disabled",true);

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
    $("#sendRemark").val(entity.sendRemark);

    if(entity.senderName==null){
        $("#senderName").val(userName);
        $("#senderId").val(userId);
    }else {
        $("#senderName").val(entity.senderName);
        $("#senderId").val(entity.senderId);
    }

    if(null==entity.sendTime){
        $("#sendTime").val((new Date()).format("yyyy-MM-dd hh:mm"));
    }else {
        $("#sendTime").val(entity.sendTime);
    }

    uploaderToggle(".aUploader")
    uploader = new qq.FineUploader(getUploaderOptions(entity.monitorCaseId));

    $("#dispatch").show();
    $("#cancel").text("取消")
}

/**
 * 重置表单
 */
function resetEventMsgFormData() {
    eventMsgForm.find("input[type!='radio'][type!='checkbox']").val("");
    $("textarea").val("");
    uploader = new qq.FineUploader(getUploaderOptions());
    disabledForm(false);
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
    $.each(options,function (i,v) {
        var option = $("<option>").val(v.code).text(v.name);
        $(selector).append(option);
    })
}

var code={code:"monitor_office_source"};
var monitor_office_source=dict.getDctionnary(code)
appendOption("#source",monitor_office_source)

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
    Ewin.confirm({ title:"办结提示",message: "是否归入办结管理？" }).on(function (e) {
        if (!e) {
            return;
        }

        var ids = getIdSelections();
        $.ajax({
            url: rootPath + "/action/S_dispatch_DispatchTask_overStatus.action",
            type:"post",
            data:  $.param({ids:ids},true),
            success:function (msg) {
                gridTable.bootstrapTable('refresh');
            }
        });

    })
})

/************  新增（现场监察）表单 ******************/
var newXianChangJianChaForm=$("#newXianChangJianChaForm");
$("#insert").click(function () {
    newXianChangJianChaForm.find("input").attr("disabled",false);
    newXianChangJianChaForm.find("textarea").attr("disabled",false);

    $("#eventTime_newXianChangJianChaForm").val((new Date()).format("yyyy-MM-dd hh:mm"))
})

function saveXianChangJianChaAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_dispatch_DispatchTask_saveXianChangJianChaAjax.action",
        type:"post",
        data:entity,
        success:callback
    });
}

//初始化表单验证
var ef_newXianChangJianChaForm = newXianChangJianChaForm.easyform({
    success:function (ef_newXianChangJianChaForm) {
        var entity = newXianChangJianChaForm.find("form").formSerializeObject();
        console.log("保存 现场监察："+JSON.stringify(entity))

        saveXianChangJianChaAjax(entity,function (msg) {
            newXianChangJianChaForm.modal('hide');
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
});




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

/**
 * Autocomplete  enterpriseName
 */
$( function() {

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

} );



