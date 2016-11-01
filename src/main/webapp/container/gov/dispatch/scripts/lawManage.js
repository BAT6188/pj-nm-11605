var gridTable = $('#table'),
    overBtn = $('#overBtn'),
    dealWithBtn = $('#dealWith'),
    feedbackBtn = $('#feedback'),
    form = $("#eventMsg"),
    formTitle = "事件信息",
    selections = [];

loadBlockLevelAndBlockOption()


//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_dispatch_DispathTask_dispathTaskBtnSave.action",
        type:"post",
        data:entity,
        dataType:"json",
        success:callback
    });
}
/**
 * 删除请求
 * @param ids 多个,号分隔
 * @param callback
 */
/*function deleteAjax(ids, callback) {
    $.ajax({
        url: rootPath + "/action/S_dispatch_MonitorCase_delete.action",
        type:"post",
        data:$.param({deletedId:ids},true),//阻止深度序列化，向后台传递数组
        dataType:"json",
        success:callback
    });
}*/
/**============grid 列表初始化相关代码============**/
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_dispatch_DispathTask_list.action",
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
                    return pageUtils.sub10(value);
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
                        value="已反馈"
                        value='<a class="btn btn-md btn-warning view" data-toggle="modal" data-target="#feedbackStatusForm">'+value+'</a>'
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
                field: 'reason',
                title: '行政处罚',
                sortable: false,
                align: 'center',
                editable: false
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


// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, row, index) {
       console.log(JSON.stringify(row))

        $("#feedbackStatusForm_eventTime").val(row.eventTime);
        $("#feedbackStatusForm_answer").val(row.answer);
        $("#feedbackStatusForm_enterpriseId").val(row.enterpriseId);
        $("#feedbackStatusForm_source").val(row.source);
        $("#feedbackStatusForm_blockLevelId").val(row.blockLevelId);
        $("#feedbackStatusForm_blockId").val(row.blockId);
        $("#feedbackStatusForm_supervisor").val(row.supervisor);
        $("#feedbackStatusForm_supervisorPhone").val(row.supervisorPhone);
        $("#feedbackStatusForm_content").val(row.content);
        $("#feedbackStatusForm_senderName").val(row.senderName);
        $("#feedbackStatusForm_senderId").val(row.senderId);
        $("#feedbackStatusForm_sendTime").val(row.sendTime);

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
    $("#dispathId").val(getSelections()[0].id)

    uploader2 = new qq.FineUploader(getUploaderOptions2());

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

/**============表单初始化相关代码============**/

//初始化表单验证
var ef_dispatch = form.easyform({
    success:function (ef_dispatch) {
        //debugger
        //验证成功，打开选择人员 对话框
        $('#selectPeopleForm').modal('show');

        var entity = $("#eventMsg").find("form").formSerializeObject();
        entity.attachmentIds = getAttachmentIds();
        console.log("执法管理 调度单 点调度按钮，保存调度单信息："+JSON.stringify(entity))

        saveAjax(entity,function (msg) {
            //form.modal('hide');
            //gridTable.bootstrapTable('refresh');

            $("#dispathTaskId").val(msg.id)
        });
    },
    error:function () {
        console.log("error")
    }
});

//表单 保存按钮
$("#dispatch").bind('click',function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    ef_dispatch.submit(false);
});

var ef_feedbackTo = $("#feedbackForm").easyform({
    success:function (ef_feedbackTo) {

        var entity = $("#feedbackForm").find("form").formSerializeObject();
        entity.attachmentIds = getAttachmentIds2();
        console.log("执法管理 调度单 点调度按钮，保存调度单信息："+JSON.stringify(entity))

        $.ajax({
            url: rootPath + "/action/S_dispatch_Feedback_save.action",
            type:"post",
            data:entity,
            dataType:"json",
            success:function (msg) {
                
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
    ef_feedbackTo.submit(false);
});

/**
 * 设置调度表单数据
 * @param entity
 * @returns {boolean}
 */
function setEventMsgFormData(entity) {
    resetEventMsgFormData();
    if (!entity) {return false}
    var id = entity.id;
    $("#id").val(entity.id);

    $("#eventTime").val(entity.eventTime);
    $("#answer").val(entity.answer);
    $("#enterpriseId").val(entity.enterpriseId);
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



    uploader = new qq.FineUploader(getUploaderOptions(entity.monitorCaseId));
}

function disabledForm(disabled) {


}
/**
 * 重置调度表单
 */
function resetEventMsgFormData() {
    form.find("input[type!='radio'][type!='checkbox']").val("");
    $("textarea").val("");
    uploader = new qq.FineUploader(getUploaderOptions());
    disabledForm(false);
}

//表单附件相关js
var uploader;//附件上传组件对象
var uploader2;
var uploader3;
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
            itemLimit: 3
        },
        debug: true
    };
}

function getUploaderOptions3(bussinessId) {
    return {
        element: document.getElementById("fine-uploader-gallery3"),
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
                uploader3.setUuid(id, msg.id);
            },
            onDeleteComplete:function (id) {
                var file = uploader3.getUploads({id:id});
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
            itemLimit: 3
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

function getAttachmentIds2() {
    var attachments = uploader2.getUploads();
    if (attachments && attachments.length) {
        var ids = [];
        for (var i = 0 ; i < attachments.length; i++){
            ids.push(attachments[i].uuid);
        }
        return ids.join(",");
    }
    return "";
}

function getAttachmentIds3() {
    var attachments = uploader3.getUploads();
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

$("#fine-uploader-gallery2").on('click', '.qq-upload-download-selector', function () {
    var uuid = uploader2.getUuid($(this.closest('li')).attr('qq-file-id'));
    window.location.href = rootPath+"/action/S_attachment_Attachment_download.action?id=" + uuid;
});

$("#fine-uploader-gallery3").on('click', '.qq-upload-download-selector', function () {
    var uuid = uploader3.getUuid($(this.closest('li')).attr('qq-file-id'));
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


window.seeEvent = {
    'click .see': function (e, value, row, index) {
        console.log(JSON.stringify(row))

        $("#seeFeedbackForm_lawerName").val(row.lawerName)
        $("#seeFeedbackForm_phone").val(row.phone)
        $("#seeFeedbackForm_exeTime").val(row.exeTime)
        $("#seeFeedbackForm_exeDesc").val(row.exeDesc)
        $("#seeFeedbackForm_lawerName").val(row.lawerName)
        uploader3 = new qq.FineUploader(getUploaderOptions3(row.id));
    }
};
var tableStatus=$("#tableStatus")
function initTableStatus() {
    tableStatus.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_dispatch_Feedback_list.action",
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
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
                    html='<a class="btn btn-md btn-warning see" data-toggle="modal" data-target="#seeFeedbackForm">详情</a>'
                    return html
                }
            }

        ]
    });
    // sometimes footer render error.
    setTimeout(function () {
        tableStatus.bootstrapTable('resetView');
    }, 200);

}
initTableStatus()

$("#overBtn").click(function () {
    Ewin.confirm({ title:"办结提示",message: "是否归入办结管理？" }).on(function (e) {
        if (!e) {
            return;
        }

        var ids = getIdSelections();
        $.ajax({
            url: rootPath + "/action/S_dispatch_DispathTask_overStatus.action",
            type:"post",
            data:  $.param({ids:ids},true),
            success:function (msg) {
                gridTable.bootstrapTable('refresh');
            }
        });

    })
})



