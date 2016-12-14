//@ sourceURL=meetingnotice.js
var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#scfForm"),
    formTitle = "会议通知",
    selections = [];

//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_office_MeetingNotice_save.action",
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
function deleteAjax(ids, callback) {
    $.ajax({
        url: rootPath + "/action/S_office_MeetingNotice_delete.action",
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
        url: rootPath+"/action/S_office_MeetingNotice_list.action",
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
                title: '会议标题',
                field: 'title',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '会议类型',
                field: 'type',
                sortable: false,
                align: 'center',
                editable: false,
                formatter:meetingTypeFormatter
            },
            {
                title: '会议地点',
                field: 'address',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '会议时间',
                field: 'time',
                sortable: false,
                align: 'center',
                editable: false,
                formatter: function (value, row, index) {
                    return pageUtils.sub16(value);
                },
            },
            {
                title: '发布部门',
                field: 'pubOrgName',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '参会人员',
                field: 'participants',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                field: 'operate1',
                title: '接收状态',
                align: 'center',
                events: operateEvents,
                formatter: function (value, row, index) {
                    var msgHasUnReceived = MessageTraceModal.msgHasUnReceived(row.id);
                    if (msgHasUnReceived) {
                        return '<button type="button" class="btn btn-md btn-warning message-trace" data-toggle="modal">未接收</button>';
                    }else{
                        return '<button type="button" class="btn btn-md btn-success message-trace" data-toggle="modal">已接收</button>';
                    }
                }
            },
            {
                field: 'operate2',
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
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#scfForm">查看</button>';
}

function meetingTypeFormatter(value, row, index){
    return dict.get('type',value);
}

// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, row, index) {
        setFormView(row);
    },
    'click .message-trace': function (e, value, row, index) {
        MessageTraceModal.refreshTableGrid(row.id);
        MessageTraceModal.show();
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
    $("#pubOrgName").attr("disabled",true)
});
$("#update").bind("click",function () {
    setFormData(getSelections()[0]);
    $("#pubOrgName").attr("disabled",true)

});

//<!---组织机构发送--START-->
var options = {
    title:"信息发送",//弹出框标题(可省略，默认值：“人员选择”)
    width:"60%"     //宽度(可省略，默认值：850)
};
var model = $.fn.MsgSend.init(1,options,function(e,data){
    var entity=data.sourceId;
    console.log(data)
    var d,ids=[],names=[];
    var receivers = [];
    $.each(data.personObj,function(k,v){
        ids[k] = v.id;
        names[k]=v.name;

        var receiver1 = {receiverId:v.userId,receiverName:v.name};
        receivers.push(receiver1);
    });
    var msg = {
        'msgType':2,
        'title':'会议通知提醒',
        'content':entity.content,
        'businessId':entity.id
    };
    pageUtils.sendMessage(msg, receivers);

    d=$.param({ids:ids,names:names},true);
    d+="&id="+data.sourceId;
    console.log(d);//回调函数，data为所选人员ID
    $.ajax({
        url: rootPath + "/action/S_office_MeetingNotice_updateMeeting.action",
        type:"post",
        data:d,
        dataType:"json",
        success:function (msg) {
            form.modal('hide');
            gridTable.bootstrapTable('refresh');
            pageUtils.saveOperationLog({opType:'4',opModule:'会议通知',opContent:'发送信息',refTableId:''})
        }
    });
});

/************  短信发送  ****************/
var optionsMsg = {
    params:{
        // orgCode:[],//组织机构代码(必填，组织机构代码)
        //type:2  //1默认加载所有，2只加载当前机构下人员，3只加载当前机构下的组织机构及人员
    },
    title:"人员选择",//弹出框标题(可省略，默认值：“组织机构人员选择”)
    width:"60%"        //宽度(可省略，默认值：850)
};

var modelMsg = $.fn.MsgSend.init(2,optionsMsg,function(e,data){ //短信发送第一个参数为2

    var d=pageUtils.sendParamDataToString(data);
    $.ajax({
        url: rootPath + "/action/S_office_MeetingNotice_updateMeetingIsSms.action",
        type:"post",
        data:d,
        dataType:"json",
        success:function (msg) {
            form.modal('hide');
            pageUtils.saveOperationLog({opType: '4', opModule: '短信会议通知', opContent: '短信发送数据', refTableId: ''})
        }
    })
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
        });
    });
});



/**============列表搜索相关处理============**/
//搜索按钮处理
//搜索
$("#search").click(function () {
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
});
//重置搜索
$("#searchFix").click(function () {
    $('#searchform')[0].reset();
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
});

/**============表单初始化相关代码============**/

//初始化表单验证
var ef = form.easyform({
    success:function (ef) {
        var entity = $("#scfForm").find("form").formSerializeObject();
        entity.attachmentIds = getAttachmentIds();
        entity.pubOrgName=$("#pubOrgName").val();
        saveAjax(entity,function (msg) {
            $("#id").val(msg.id);
            if(buttonSend=="#save"){
                entity.id=msg.id;
                entity.isSendSms=$("#isSendSms").is(':checked');
                model.open(entity);
            }else{
                modelMsg.open(msg.id);
            }
            gridTable.bootstrapTable('refresh');


        });
    }
});
var buttonSend;
//表单 保存按钮
$("#save").bind('click',function () {
    buttonSend="#save";
    //验证表单，验证成功后触发ef.success方法保存数据
    ef.submit(false);
});

$('#smsSend').bind('click',function () {
    buttonSend="#smsSend";
    //验证表单，验证成功后触发ef.success方法保存数据
    ef.submit(false);
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
    resetForm();
    if (!entity) {return false}
    form.find(".form-title").text("修改"+formTitle);
    var id = entity.id;
    $("#id").val(entity.id);
    $("#removeId").val("");
    $("#title").val(entity.title);
    $("#address").val(entity.address);
    $("#type").val(entity.type);
    $("#pubOrgName").val(entity.pubOrgName);
    $("#pubOrgId").val(entity.pubOrgId);
    $("#linkMan").val(entity.linkMan);
    $("#linkPhone").val(entity.linkPhone);
    $("#time").val(pageUtils.sub16(entity.time));
    $("#content").val(entity.content);
    $("#participants").val(entity.participants);

}
function setFormView(entity) {
    setFormData(entity);
    form.find(".form-title").text("查看"+formTitle);
    disabledForm(true);
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无附件信息");
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
    form.find("#smsSend").hide();
    form.find("#save").hide();
    form.find(".btn-cancel").text("关闭");
}
function disabledForm(disabled) {
    form.find("input").attr("disabled",disabled);
    form.find("textarea").attr("disabled", disabled);
    if (!disabled) {
        //初始化日期组件
        $('.form_datetime').datetimepicker({
            language:   'zh-CN',
            autoclose: 1,
            minView: 2
        });
    }else{
        $('.form_datetime').datetimepicker('remove');
    }

}
/**
 * 重置表单
 */
function resetForm() {
    form.find(".form-title").text("新增"+formTitle);
    form.find("input[type!='radio'][type!='checkbox']").val("");
    form.find("#isSendSms").attr("checked",false);
    $("textarea").val("");
    uploader = new qq.FineUploader(getUploaderOptions());
    $("#pubOrgName").val(orgName);
    $("#pubOrgId").val(orgId);
    $("#isSms").val(0);
    disabledForm(false);
    form.find("#save").show();
    form.find(".btn-cancel").text("取消");
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

