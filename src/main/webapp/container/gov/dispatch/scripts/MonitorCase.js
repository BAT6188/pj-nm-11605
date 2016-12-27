var gridTable = $('#table'),
    form = $("#systemSendForm"),
    formTitle = "调度单",
    feedbackRecordTable=$("#feedbackRecordTable"),
    selections = [];

    function getTableHeight() {
        console.log($('.dealBox').outerHeight(true) + $('.banner').outerHeight(true) + $('.linear').outerHeight(true));
        return $(window).height() - $('.dealBox').outerHeight(true) - $('.banner').outerHeight(true) - $('.linear').outerHeight(true) - 8;
    }

    function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_dispatch_MonitorCase_list.action?source=0",
        // height: getTableHeight,
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
        queryParams:pageUtils.localParams,
        columns: [
            {
                checkbox: true,
                align: 'center',
                radio:false,  //  true 单选， false多选
                valign: 'middle'
            }, {
                title: 'id',
                field: 'id',
                align: 'center',
                valign: 'middle',
                sortable: false,
                footerFormatter: totalTextFormatter,
                visible:false
            },
            {
                title: '企业名称',
                field: 'enterpriseName',
                editable: false,
                sortable: false,
                align: 'center',
                formatter: function (value, row, index) {
                    var isNewDiv=""
                    if (row.selfReadStatus!='1'){
                        isNewDiv='<div id="isNew">&nbsp;</div>'
                    }
                    return '<div>'+value+isNewDiv+'</div>';
                }
            },
            {
                title: '事件时间',
                field: 'eventTime',
                editable: false,
                sortable: false,
                footerFormatter: totalTextFormatter,
                align: 'center'
            }, {
                field: 'blockLevelName',
                sortable: false,
                editable: false,
                visible:false
            }, {
                field: 'blockName',
                title: '所属网格',
                sortable: false,
                align: 'center',
                editable: false,
                footerFormatter: totalTextFormatter
            },{
                field: 'portName',
                title: '排口名称',
                sortable: false,
                align: 'center',
                editable: false
            },
            // {
            //     field: 'pollutantType',
            //     title: '污染源类型',
            //     sortable: false,
            //     align: 'center',
            //     editable: false
            // },
            {
                field: 'overObj',
                title: '监测指标',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                field: 'overValue',
                title: '检测值',
                sortable: false,
                align: 'center',
                editable: false,
                footerFormatter: totalTextFormatter
            },
            {
                field: 'thrValue',
                title: '标准值',
                sortable: false,
                align: 'center',
                editable: false
            },
            // {
            //     title: '监管人员',
            //     field: 'supervisor',
            //     editable: false,
            //     sortable: false,
            //     footerFormatter: totalTextFormatter,
            //     align: 'center'
            // },{
            //     field: 'reason',
            //     title: '原因',
            //     sortable: false,
            //     align: 'center',
            //     editable: false,
            //     formatter: function (value, row, index) {
            //         return dict.get("caseReason",value)
            //     }
            // },
            // {
            //     field: 'status',
            //     title: '状态跟踪',
            //     align: 'center',
            //     events: operateEvents,
            //     formatter: statusFormatter,
            //     visible:false
            // },
            // {
            //     title: '发送至',
            //     field: 'monitorOfficePersonName',
            //     editable: false,
            //     sortable: false,
            //     align: 'center'
            // },
            {
                field: 'overStatus',
                title: '办结状态',
                editable: false,
                sortable: false,
                align: 'center',
                events: overEvents,
                formatter: function (value, row, index) {
                    if (value=='1'){
                        return '<button type="button" class="btn btn-md btn-warning overView" data-toggle="modal" data-target="#overDialog">已办结</button>';
                    }else {
                        return '未办结'
                    }
                },
                visible:false
            },
            //未调度
            {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: operateEvents,
                formatter: operateFormatter
            },
            //已调度
            {
                field: 'queryFeedback',
                title: '操作',
                editable: false,
                sortable: false,
                align: 'center',
                events: queryFeedbackEvents,
                formatter: queryFeedbackFormatter,
                visible:false
            },{
                title: 'supervisorPhone',
                field: 'supervisorPhone',
                editable: false,
                sortable: false,
                footerFormatter: totalTextFormatter,
                visible:false
            }, {
                title: 'thrValue',
                field: 'thrValue',
                editable: false,
                sortable: false,
                footerFormatter: totalTextFormatter,
                visible:false
            },{
                title: 'content',
                field: 'content',
                editable: false,
                sortable: false,
                footerFormatter: totalTextFormatter,
                visible:false
            },{
                title: 'senderName',
                field: 'senderName',
                editable: false,
                sortable: false,
                footerFormatter: totalTextFormatter,
                visible:false
            },{
                title: 'sendTime',
                field: 'sendTime',
                editable: false,
                sortable: false,
                footerFormatter: totalTextFormatter,
                visible:false
            },{
                title: 'sendRemark',
                field: 'sendRemark',
                editable: false,
                sortable: false,
                visible:false
            }

        ]
    });
    // sometimes footer render error.
    setTimeout(function () {
        gridTable.bootstrapTable('resetView');
    }, 200);


    //搜索按钮处理
    $("#search").click(function () {
        gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
    });
    //重置搜索
    $("#searchFix").click(function () {
        resetQuery();
        $("#status_search").val(status_search)
        gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
    });



    //表单弹出框 保存按钮
    $("#saveDemo").bind('click',function () {
        ef.submit(true);
    });

    $(window).resize(function () {
        // 重新设置表的高度
        gridTable.bootstrapTable('resetView', {
            height: getHeight()
        });
    });
}


// 获取所有的选中数据
function getIdSelections() {
    return $.map(gridTable.bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}

// 获取所有的选中数据
function getSelections() {
    return $.map(gridTable.bootstrapTable('getSelections'), function (row) {
        return row
    });
}

// 设置默认选中
function responseHandler(res) {
    $.each(res.rows, function (i, row) {
        row.state = $.inArray(row.id, selections) !== -1;
    });
    return res;
}
// 生成详细信息方法
function detailFormatter(index, row) {
    var html = [];
    $.each(row, function (key, value) {
        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
    });
    return html.join('');
}
// 生成操作方法
function operateFormatter(value, row, index) {
    return [
        '<button  type="button" class="btn btn-primary like" data-toggle="modal" data-target="#systemSendForm" >系统调度</button>'+
        '&nbsp<button  type="button" class="btn btn-md btn-success" data-toggle="modal" data-target="#realTimeTrackingForm" >实时跟踪</button>'+
        '&nbsp<button type="button" class="btn btn-md btn-warning looks" data-status="false" data-toggle="modal" data-target="#systemSendForm">查看</button>'
    ].join('');
}

function statusFormatter(value, row, index) {
    var html
    if(0==value){
        html="未发送"
    }else if(1==value){
        html="已发送"
    }else if(2==value){
        html="已反馈"
    }
    return html;
}

// 操作事件
window.operateEvents = {
    'click .like': function (e, value, row, index) {
        var url=rootPath + "/action/S_dispatch_MonitorCase_updateSelfReadStatus.action";
        pageUtils.updateSelfReadStatus(url,row.id,1)
        refreshDemoForm(row);
        $("#isSendSms").show();
        $("#send").show();
        $("#messages").show();
        $("#changeBtn").text("取消");
        $("#content").attr("disabled",false);
        $("#senderName").attr("disabled",false);
        $("#sendRemark").attr("disabled",false);
        // form.find("input").attr("disabled",false);
        // form.find("textarea").attr("disabled",false);
    },
    'click .looks':function(e, value, row, index){
        var url=rootPath + "/action/S_dispatch_MonitorCase_updateSelfReadStatus.action";
        pageUtils.updateSelfReadStatus(url,row.id,1);
        refreshDemoForm(row);
        $("#demoFormTitle").text("查看"+formTitle);
        $("#isSendSms").hide();
        $("#send").hide();
        $("#messages").hide();
        $("#changeBtn").text("关闭");
        form.find("input").attr("disabled",true);
        form.find("textarea").attr("disabled",true);
    }
};


// 生成列表操作方法
function queryFeedbackFormatter(value, row, index) {
    if (row.status=='2'){
        return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#feedbackListDialog">已反馈</button>'+
            '&nbsp<button  type="button" class="btn btn-md btn-success" data-toggle="modal" data-target="#realTimeTrackingForm" >实时跟踪</button>'+
                '&nbsp<button type="button" class="btn btn-md btn-warning looks" data-toggle="modal" data-target="#systemSendForm">查看</button>';
    }else {
        return '未反馈'
            +'&nbsp<button  type="button" class="btn btn-md btn-success" data-toggle="modal" data-target="#realTimeTrackingForm" >实时跟踪</button>'
            + '&nbsp<button type="button" class="btn btn-md btn-warning looks" data-toggle="modal" data-target="#systemSendForm">查看</button>';
    }
}

window.overEvents = {
    'click .overView': function (e, value, row, index) {
        $("#overTime").val(row.overTime)
        $("#overSuggestion").val(row.overSuggestion)
    }
};

// 列表操作事件
window.queryFeedbackEvents = {
    'click .view': function (e, value, row, index) {
        feedbackRecordTable.bootstrapTable('refresh',{query:{id:row.id}})
    }
};
/**
 * 刷新表单数据
 * @param demo
 */
function refreshDemoForm(demo) {
    $("#id").val(demo.id);
    $("#status").val(demo.status);
    $("#eventTime").val(demo.eventTime);
    $("#enterpriseName").val(demo.enterpriseName);
    $("#blockName").val(demo.blockName);
    $("#blockLevelName").val(demo.blockLevelName);
    $("#supervisor").val(demo.supervisor);
    $("#supervisorPhone").val(demo.supervisorPhone);
    $("#overValue").val(demo.overValue);
    $("#thrValue").val(demo.thrValue);
    $("#content").val(demo.content);
    $("#senderName").val(userName);
    $("#sendTime").val((new Date()).format("yyyy-MM-dd hh:mm"));
    $("#sendRemark").val(demo.sendRemark);
    $("#portName").val(demo.portName);
    $("#overObj").val(demo.overObj);

}



function totalTextFormatter(data) {
    return 'Total';
}
function totalNameFormatter(data) {
    return data.length;
}
function totalPriceFormatter(data) {
    var total = 0;
    $.each(data, function (i, row) {
        total += +(row.price.substring(1));
    });
    return '$' + total;
}
function getHeight() {
    return $(window).height() - $('h1').outerHeight(true);
}

initTable();

//保存监控中心调度单
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_dispatch_MonitorCase_saveMonitor.action",
        type:"post",
        data:entity,
        dataType:"json",
        success:callback
    });
}

/************  短信发送  ****************/
var options_sms = {
    params:{
        // orgCode:[],
        //type:2  //1默认加载所有，2只加载当前机构下人员，3只加载当前机构下的组织机构及人员
    },
    title:"人员选择",//弹出框标题(可省略，默认值：“组织机构人员选择”)
    width:"60%",        //宽度(可省略，默认值：850)
}

var model_sms = $.fn.MsgSend.init(2,options_sms,function(e,data){ //短信发送第一个参数为2
    console.log(data);//回调函数，data为所选人员ID
    pageUtils.saveOperationLog({opType:'4',opModule:'监控中心',opContent:'短信发送数据',refTableId:''})
});

/************  组织机构发送  ****************/
var options = {
    params:{
        orgCode:[orgCodeConfig.org.jianChaDaDuiLingDao.orgCode],//组织机构代码(必填，组织机构代码)
        type:2 //1默认加载所有，2只加载当前机构下人员，3只加载当前机构下的组织机构及人员
    },
    choseMore:true,
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
            $("#systemSendForm").modal('hide');
            gridTable.bootstrapTable("refresh")

            var receivers = [];
            $.each(data.personObj,function (i,v) {
                var receiver1 = {receiverId:v.userId,receiverName:v.name};
                receivers.push(receiver1);
            })
            var msg = {
                'msgType':7,
                'title':'监控中心消息',
                'content':data.sourceId.content,
                'businessId':ret.id
            };
            pageUtils.sendMessage(msg, receivers);

            pageUtils.saveOperationLog({opType:'4',opModule:'监控中心',opContent:'发送数据',refTableId:''})
        }
    });
});


/**============表单初始化相关代码============**/
var buttonToggle;
//初始化表单验证
var ef = $("#systemSendForm").easyform({
    success:function (ef) {
        var id = $("#id").val();
        var senderName = $("#senderName").val();
        var sendTime = $("#sendTime").val();
        var content = $("#content").val();
        var sendRemark = $("#sendRemark").val();
        var status = $("#status").val();

        var entity = {senderName:senderName,sendTime:sendTime,
            content:content,sendRemark:sendRemark,
            status:status,id:id}

        if (buttonToggle=="#save"){
            entity.sendType="#save"
            if('0'!=entity.status){
                Ewin.alert("已调度状态不允许修改或再次发送");
                return;
            }
        }else if(buttonToggle=="#smsSend"){
            entity.sendType="#smsSend"
        }
        console.log("点发送按钮，保存调度单信息："+JSON.stringify(entity))

        saveAjax(entity,function (msg) {
            gridTable.bootstrapTable('refresh');
            if (buttonToggle=="#save"){
                entity.id=msg.id;
                entity.isSendSms=$("#isSendSms").is(':checked');
                model.open(entity);
            }else if(buttonToggle=="#smsSend"){
                model_sms.open(msg.id);
            }
        });
    }
});

//表单 保存按钮
$("#send").bind('click',function () {
    buttonToggle="#save"
    //验证表单，验证成功后触发ef.success方法保存数据
    ef.submit(false);
});

$('#smsSend').bind('click',function () {
    buttonToggle="#smsSend"
    //验证表单，验证成功后触发ef.success方法保存数据
    ef.submit(false);
});

function updateDemo(demo) {
    $.ajax({
        url: rootPath + "/action/S_monitorCase_MonitorCase_save.action",
        type:"post",
        data:demo,
        dataType:"json",
        success:function (msg) {
            alert("更新成功");
        }
    });
}


function saveDemo(demo,callback) {
    $.ajax({
        url: rootPath + "/action/S_monitorCase_MonitorCase_save.action",
        type:"post",
        data:demo,
        dataType:"json",
        success:callback
    });
}


//-------------datetimepicker配置--------------------//
$('#datetimepicker1').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1
});
$('#datetimepicker2').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1
});

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
initfeedbackRecordTable();



/********************  查询传输有效率列表  ********************/
var realTimeTrackingTable = $('#realTimeTrackingTable');
function initrealTimeTrackingTable() {
    realTimeTrackingTable.bootstrapTable('destroy');
    realTimeTrackingTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"",
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
            }, {
                title: 'ID',
                field: 'id',
                align: 'center',
                valign: 'middle',
                sortable: false,
                visible:false
            },
            {
                title: '超标时间',
                field: '',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                field: '',
                title: '超标值',
                sortable: false,
                align: 'center',
                valign: 'middle'
            }
        ]
    });
    // sometimes footer render error.
    setTimeout(function () {
        realTimeTrackingTable.bootstrapTable('resetView');
    }, 200);

    $(window).resize(function () {
        // 重新设置表的高度
        realTimeTrackingTable.bootstrapTable('resetView', {
            height: pageUtils.getTableHeight()
        });
    });
}

initrealTimeTrackingTable();


