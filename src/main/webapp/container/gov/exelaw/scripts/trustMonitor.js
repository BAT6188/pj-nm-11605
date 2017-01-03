var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    lookForm = $("#lookOverFeedbackDetailForm"),
    demoForm = $("#demoForm");
    auditForm=$("#auditForm"),
    formTitle = "Demo",
    selections = [];

//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_exelaw_TrustMonitor_save.action",
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
        url: rootPath + "/action/S_exelaw_TrustMonitor_delete.action",
        type:"post",
        data:$.param({deletedId:ids},true),//阻止深度序列化，向后台传递数组
        dataType:"json",
        success:callback
    });
}
var monitorContent = {
    "1":"水源地监测报告",
    "2":"大气污染防治监测报告",
    "3":"水污染防治监测报告",
    "4":"噪声监测报告",
    "5":"土壤污染防治监测报告"
}
/**============grid 列表初始化相关代码============**/
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_exelaw_TrustMonitor_list.action",
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
                title: '监测对象',
                field: 'enterpriseName',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '监测内容',
                field: 'monitorContent',
                sortable: false,
                align: 'center',
                editable: false,
                formatter:function(value,row,index){
                    return monitorContent[value];
                }
            },
            {
                title: '委托时间',
                field: 'monitorTime',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '委托人',
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
                title: '审核人',
                field: 'environmentalProtectionStationSelectPersonNameList',
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
                //events: lookoverAuditFormEvents,
                formatter:auditFormFormatter
            },
            {
                title: '被委托单位反馈状态',
                field:'status',
                align: 'center',
                //events: operateEvents,
                formatter: operateFormatter
            },
            {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: seeOperateEvents,
                formatter: seeOperateFormatter
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

function auditFormFormatter(value, row, index) {
    if(value=='1'){
        return '<div style="padding: 8px;color: green;" class="view" >同意</div>';//cursor: pointer;data-toggle="modal" data-target="#auditForm"
    }else if(value=='2'){
        return '<div style="padding: 8px;color: red;" class="view" >不同意</div>';
    }else {
        return '-'
    }

}
window.lookoverAuditFormEvents = {
    'click .view': function (e, value, entity, index) {
        for(p in entity){
            var selector="#"+p
            $(selector).val(entity[p])
        }
        disabledForm(auditForm,true);
    }
};


// 生成列表操作方法
function operateFormatter(value, row, index) {
    if (value==7){
        value='<div style="padding: 8px;color: #c3a61d;" class="view" >已反馈</div>';//cursor: pointer;data-toggle="modal" data-target="#lookOverFeedbackDetailForm"
    }else {
        value="未反馈"
    }
    return value;
}
// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, entity, index) {
        $("#lookOverFeedbackDetailForm").find("input").attr("disabled",true);
        $("#lookOverFeedbackDetailForm").find("textarea").attr("disabled",true);
        $('.lookover').datetimepicker('remove');
        for(p in entity){
            var selector="#"+p+"_lookOverFeedbackDetailForm"
            $(selector).val(entity[p])
        }
        /*监测站反馈*/
        $("#monitor").val(entity.monitor);
        $("#monitorPhone").val(entity.monitorPhone);
        $("#feedbackContent").val(entity.feedbackContent);


        var fuOptions = getUploaderOptions(entity.id);
        fuOptions.callbacks.onSessionRequestComplete = function () {
            $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
            $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无上传的附件");
        };
        uploader = new qq.FineUploader(fuOptions);
        $(".qq-upload-button").hide();
    }
};

function seeOperateFormatter(value, row, index) {
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#lookOverFeedbackDetailForm">查看</button>';
}
window.seeOperateEvents = {
    'click .view': function (e, value, row, index) {
        $('#modalTopBoday').scrollTop(0);
        setFormView(row);
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
    setFormData(getSelections()[0],demoForm);
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
$("#search").click(function () {
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
});
//重置搜索
$("#searchFix").click(function () {
    $('#searchform')[0].reset();
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
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

/**============配置组织发送弹出框============**/
var options = {
    params:{
        orgCode:[orgCodeConfig.org.jianChaDaDuiLingDao.orgCode],//组织机构代码(必填，组织机构代码)
        type:2
    },
    choseMore:false,
    title:"人员选择",//弹出框标题(可省略，默认值：“组织机构人员选择”)
    width:"60%",        //宽度(可省略，默认值：850)
}
var model = $.fn.MsgSend.init(1,options,function(e,data){
    var d=pageUtils.sendParamDataToString(data)
    console.log("发送："+d)
    $.ajax({
        url: rootPath + "/action/S_exelaw_TrustMonitor_saveToEnvironmentalProtectionStationSelectPersonList.action",
        type:"post",
        data:d,
        success:function (ret) {
            demoForm.modal('hide');
            gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});

            var receivers = [];
            $.each(data.personObj,function (i,v) {
                var receiver1 = {receiverId:v.userId,receiverName:v.name};
                receivers.push(receiver1);
            })
            var msg = {
                'msgType':9,
                'title':'申请委托监测',
                'content':data.sourceId.monitorContentDetail,
                'businessId':ret
            };
            pageUtils.sendMessage(msg, receivers);

            pageUtils.saveOperationLog({opType:'4',opModule:'申请委托监测',opContent:'发送数据',refTableId:''})
        }
    });
});

/**============表单初始化相关代码============**/

//初始化表单验证
var ef = demoForm.easyform({
    success:function (ef) {
        var entity = demoForm.find("form").formSerializeObject();
        entity.enterpriseSelf=0
        saveAjax(entity,function (msg) {
            gridTable.bootstrapTable('refresh');

            entity.id=msg.id;
            //TODO 申请委托监测短信内容
            entity.smsContent=entity.monitorContentDetail
            entity.isSendSms=$("#isSendSms").is(':checked');
            model.open(entity);
        });
    }
});


//表单 保存按钮
$("#saveAndSend").bind('click',function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    ef.submit(false);
});
/**
 * 设置表单数据
 * @param entity
 * @returns {boolean}
 */
function setFormData(entity,dialogForm) {
    resetForm();
    if (!entity) {return false}
    var id = entity.id;
    var inputs = dialogForm.find('.form-control');
    $.each(inputs,function(k,v){
        var tagId = $(v).attr('name');
        var value = entity[tagId];
        $(v).val(value);
    });
    var fuOptions = getUploaderOptions(id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无上传的附件");
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
}
function setFormView(entity) {
    setFormData(entity,lookForm);
    disabledForm(lookForm,true);
    /*var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无上传的附件");
    };*/
    //form.find("#saveAndSend").hide();
    //form.find("#isSendSmsSpan").hide();
    //form.find(".btn-cancel").text("关闭");
}
function disabledForm(selector,disabled) {
    selector.find("input").attr("disabled",disabled);
    selector.find("textarea").attr("disabled",disabled);
    selector.find("select").attr("disabled",disabled);

    if (!disabled) {
        //初始化日期组件
        $('.lookover').datetimepicker({
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

    }else{
        $('.lookover').datetimepicker('remove');
    }
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
    return currentdate;
}
/**
 * 重置表单
 */
function resetForm() {
    demoForm.find("input[type!='radio'][type!='checkbox']").val("");
    demoForm.find("#isSendSms").attr("checked",false);
    demoForm.find("textarea").val("");
    demoForm.find("#applicant").val(userName);
    demoForm.find("#applyOrgId").val(orgName);
    demoForm.find("#applicantPhone").val(mobile);
    demoForm.find("#trustOrgAddress").val("监测站");
    demoForm.find("#monitorTime").val(getNowFormatDate());
    disabledForm(demoForm,false);
    demoForm.find("#saveAndSend").show();
    demoForm.find("#isSendSmsSpan").show();
    demoForm.find(".btn-cancel").text("取消");
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
        },
    } );

} );


$(document).ready(function () {
    var optionsSetting={code:"orgId",name:"orgName"}
    ajaxLoadOption(rootPath+"/action/S_exelaw_TrustMonitor_getEnvironmentalProtectionStationList.action","#applyOrgId",optionsSetting)
})