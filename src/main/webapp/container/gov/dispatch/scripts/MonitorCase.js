var gridTable = $('.tableTab'),
    selections = [];

var status_search="";

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
                title: '事件时间',
                field: 'eventTime',
                editable: false,
                sortable: false,
                footerFormatter: totalTextFormatter,
                align: 'center'
            },
            {
                title: '企业名称',
                field: 'enterpriseName',
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
            }, {
                title: '监管人员',
                field: 'supervisor',
                editable: false,
                sortable: false,
                footerFormatter: totalTextFormatter,
                align: 'center'
            },{
                field: 'reason',
                title: '原因',
                sortable: false,
                align: 'center',
                editable: false,
                formatter: reasonFormatter
            },{
                field: 'overValue',
                title: '超标值',
                sortable: false,
                align: 'center',
                editable: false,
                footerFormatter: totalTextFormatter
            },{
                field: 'operate',
                title: '查看',
                align: 'center',
                events: operateEvents,
                formatter: operateFormatter
            }, {
                field: 'status',
                title: '状态跟踪',
                align: 'center',
                events: operateEvents,
                formatter: statusFormatter,
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
                footerFormatter: totalTextFormatter,
                visible:false
            }

        ]
    });
    // sometimes footer render error.
    setTimeout(function () {
        gridTable.bootstrapTable('resetView');
    }, 200);


    //搜索
    $("#search").click(function () {
        gridTable.bootstrapTable('resetSearch');
        var enterpriseName = $("#searchEnterpriseName").val();
        var startSendTime=$("#start_sendTime").val()
        var endSendTime=$("#end_sendTime").val()
        var reason = $("#s_reason").val();
        var blockLevelId = $("#s_blockLevelId").val();
        var blockId = $("#s_blockId").val();
        gridTable.bootstrapTable('refresh',{
            query:{enterpriseName: enterpriseName,reason:reason,
                startSendTime:startSendTime,endSendTime:endSendTime,
                blockLevelId:blockLevelId,blockId:blockId,
                status_search:status_search}
        });

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
        '<button  type="button" class="btn btn-primary like" data-toggle="modal" data-target="#systemSendForm" >系统发送</button>'
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

function reasonFormatter(value, row, index) {
    var html
    if(1==value){
        html="异常"
    }else if(2==value){
        html="超标"
    }
    return html;
}

// 操作事件
window.operateEvents = {
    'click .like': function (e, value, row, index) {
        refreshDemoForm(row);
    }
};
/**
 * 刷新表单数据
 * @param demo
 */
function refreshDemoForm(demo) {
    $("#id").val(demo.id);
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

var options = {
    params:{
        orgCode:['0170001300'],//组织机构代码(必填，组织机构代码)
        type:2  //1默认加载所有，2只加载当前机构下人员，3只加载当前机构下的组织机构及人员
    },
    title:"人员选择",//弹出框标题(可省略，默认值：“组织机构人员选择”)
    width:"60%",        //宽度(可省略，默认值：850)
}

var model = $.fn.MsgSend.init(1,options,function(e,data){
    var d=$.param({ids:data.ids},true)
    d+="&sourceId="+data.sourceId;
    console.log("发送："+d)
    $.ajax({
        url: rootPath + "/action/S_dispatch_DispatchTask_save.action",
        type:"post",
        data:d,
        success:function (msg) {
            $("#systemSendForm").modal('hide');
            gridTable.bootstrapTable("refresh")
        }
    });
});

//初始化表单验证
var ef = $("#systemSendForm").easyform({
    success:function (ef) {
        var id = $("#id").val();
        var senderName = $("#senderName").val();
        var sendTime = $("#sendTime").val();
        var content = $("#content").val();
        var sendRemark = $("#sendRemark").val();
        var entity = {senderName:senderName,sendTime:sendTime,
            content:content,sendRemark:sendRemark,
            id:id}
        console.log("点发送按钮，保存调度单信息："+JSON.stringify(entity))

        saveAjax(entity,function (msg) {
            model.open(msg.id);//打开dialog
        });
    }
});

//表单 保存按钮
$("#send").bind('click',function () {
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


$(function(){
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        var activeTab = $(e.target).attr("href");

        if("#noDispath"==activeTab){
            status_search=0

            gridTable.bootstrapTable('hideColumn',"status");
            gridTable.bootstrapTable('showColumn',"operate");
        }else {
            status_search='!0'

            gridTable.bootstrapTable('showColumn',"status");
            gridTable.bootstrapTable('hideColumn',"operate");
        }

        gridTable.bootstrapTable('refresh',{
            query:{status_search: status_search}
        });

    });
});


$(document).ready(function () {
    loadBlockLevelAndBlockOption("#s_blockLevelId","#s_blockId")
})

