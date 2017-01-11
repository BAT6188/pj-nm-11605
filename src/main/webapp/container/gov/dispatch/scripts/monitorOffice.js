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
                title: '举报对象',
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
                    return pageUtils.sub10(value);
                }
            },
            {
                title: '信息来源',
                field: 'source',
                editable: false,
                sortable: false,
                align: 'center',
                formatter:function (value, row, index) {
                    return dict.get("caseSource",value)
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
                title: '所属网格',
                field: 'blockName',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '举报人姓名',
                field: 'informer',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '状态',
                field: 'overStatus',
                editable: false,
                sortable: false,
                align: 'center',
                events:{
                    'click .overStatusView': function (e, value, row, index) {
                        $('#overDialog').find('#overTime').val(row.overTime);
                        $('#overDialog').find('#overSuggestion').val(row.overSuggestion);
                    }
                },
                formatter: function (value, row, index) {
                    switch(value){
                        case '1':
                            return '<button type="button" class="btn btn-md btn-warning overStatusView" data-toggle="modal" data-target="#overDialog">已办结</button>';
                        default:
                            return '未办结';
                    }
                }
            },
            {
                field: 'monitorReportStatus',
                title: '现场监察报告',
                sortable: false,
                align: 'center',
                editable: false,
                //events: monitorReportEvents,
                formatter: function (value, row, index) {
                    switch(value){
                        case '1':
                            return '<button type="button" class="btn btn-md btn-warning overStatusView" data-toggle="modal" data-target="#overDialog">已报送</button>';
                        default:
                            return '未报送';
                    }
                }
            },
            {
                field: 'col2',
                title: '行政处罚',
                sortable: false,
                align: 'center',
                editable: false,
                events: {
                    'click .punish': function (e, value, row, index) {
                        var url = rootPath + "/container/gov/exelaw/punish.jsp?id=" + row.dispatchId;
                        pageUtils.toUrl(url);
                    }
                },
                formatter: function (value, row, index) {
                    console.log('行政处罚-----------');
                    console.log(row.status);
                    if(row.status>=4){
                        value="<a class='btn btn-md btn-warning punish'>已处罚</a>"
                    }else{
                        value="——"
                    }
                    return value
                }
            },
            {
                title: '反馈状态',
                field: 'status',
                editable: false,
                sortable: false,
                align: 'center',
                events: operateEvents,
                formatter: operateFormatter
            },
            {
                title: '操作',
                editable: false,
                sortable: false,
                align: 'center',
                events: {
                    'click .lookView': function (e, value, row, index) {
                        setFormView(row);
                    }
                },
                formatter: function (value, row, index) {
                    return '<button type="button" class="btn btn-md btn-warning lookView" data-toggle="modal" data-target="#eventMsg">查看</button>';
                }

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
    if(row.status && row.status>1){
        return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#feedbackListDialog">已反馈</button>';
    }else {
        return '未反馈';
    }
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
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
});
//重置搜索
$("#searchFix").click(function () {
    resetQuery();
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
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
        orgCode:[orgCodeConfig.org.jianChaDaDuiLingDao.orgCode],//组织机构代码(必填，组织机构代码)
        type:2  //1默认加载所有，2只加载当前机构下人员，3只加载当前机构下的组织机构及人员
    },
    choseMore:false,
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
        success:function (ret) {
            ret=JSON.parse(ret);
            eventMsgForm.modal('hide');
            gridTable.bootstrapTable('refresh');

            var receivers = [];
            $.each(data.personObj,function (i,v) {
                var receiver1 = {receiverId:v.userId,receiverName:v.name};
                receivers.push(receiver1);
            })
            var msg = {
                'msgType':6,
                'title':'监察大队办公室消息',
                'content':data.sourceId.content,
                'businessId':ret.id
            };
            pageUtils.sendMessage(msg, receivers);

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
        entity.blockLevelId=$('#blockLevelId').val();
        entity.attachmentIds = getAttachmentIds();
        console.log("点发送按钮，保存调度单信息："+JSON.stringify(entity))
        saveAjax(entity,function (msg) {
            pageUtils.saveOperationLog({opType:'1',opModule:'监察大队办公室',opContent:'新增数据',refTableId:''})
            gridTable.bootstrapTable('refresh');
            if (buttonToggle=="#save"){
                // entity.id=msg.id;
                // entity.smsContent=entity.content
                // entity.isSendSms=$("#isSendSms").is(':checked');
                // model.open(entity);

                var blockId=entity.blockId;
                $.ajax({
                    url: rootPath + "/action/S_office_Contacts_list.action?blockId="+blockId,
                    // type:"post",
                    dataType:"json",
                    success:function (o) {
                        console.log(o)
                        var d=""
                        $.each(o.rows,function (i,v) {
                            d+="&ids="+v.apportalPersonId
                            d+="&names="+v.apportalUserName
                        })
                        d+="&sourceId="+msg.id
                        console.log("保存的信息："+d);
                        $.ajax({
                            url: rootPath + "/action/S_dispatch_DispatchTask_save.action",
                            type:"post",
                            data:d,
                            success:function (ret) {
                                ret=JSON.parse(ret);
                                eventMsgForm.modal('hide');
                                gridTable.bootstrapTable('refresh');

                                var receivers = [];
                                $.each(o.rows,function (i,v) {
                                    var receiver1 = {receiverId:v.apportalUserId,receiverName:v.apportalUserName};
                                    receivers.push(receiver1);
                                })
                                var msg = {
                                    'msgType':6,
                                    'title':'监察大队办公室消息',
                                    'content':entity.content,
                                    'businessId':ret.id
                                };
                                pageUtils.sendMessage(msg, receivers);
                            }
                        });
                    }
                });




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
    var inputs = eventMsgForm.find('.form-control');
    $.each(inputs,function(k,v){
        var tagId = $(v).attr('id');
        var value = entity[tagId];
        if(v.tagName=='SELECT'){
            $(v).find("option[value='"+value+"']").attr("selected",true);
        }else{
            $(v).val(value);
        }
    });

    uploaderToggle(".aUploader");
    uploader = new qq.FineUploader(getUploaderOptions(id));
    bindDownloadSelector();
}
function setFormView(entity) {
    setFormData(entity);
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无上传的附件");
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
    disabledForm(true);

    $("#save").hide();
    $("#cancel").text("关闭");

}
function disabledForm(disabled) {
    eventMsgForm.find(".form-control").attr("disabled",disabled);
    if (!disabled) {
        //初始化日期组件
        $('#datetimepicker1').datetimepicker({
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
        $('#datetimepicker1').datetimepicker('remove');
    }
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
    disabledForm(false);

    $("#blockLevelId").val('3');
    $("#blockLevelId").attr("disabled",true);


    uploaderToggle(".aUploader")
    uploader = new qq.FineUploader(getUploaderOptions());
    bindDownloadSelector();

    $("#save").show();
    $("#cancel").text("取消");
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


//初始化日期组件
/*$('.form_datetime').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1
});*/

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

/*function disabledForm(dialogSelector,disabled) {
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
}*/


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
        disabledForm($("#feedbackForm"),true)
        $("#lawerName").val(row.lawerName)
        $("#phone").val(row.phone)
        $("#exeTime").val(row.exeTime)
        $("#exeDesc").val(row.exeDesc)

        uploaderToggle(".bUploader")
        var fuOptions = getUploaderOptions(row.id);
        fuOptions.callbacks.onSessionRequestComplete = function () {
            $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
            $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无上传的附件");
        };
        uploader = new qq.FineUploader(fuOptions);
        bindDownloadSelector();
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



