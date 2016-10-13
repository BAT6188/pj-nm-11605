var gridTable = $('#table'),
    selections = [];
var h=$(window).height()-105;
console.log(h)
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        url: rootPath+"/action/S_dispatch_MonitorCase_list.action",
        height: 540,
        method:'post',
        queryParams:function (param) {
            var name = $("#s_name").val();
            var age = $("#s_age").val();
            var temp = {
                name: name,
                age: age,
                //分页参数
                take: param.limit,
                skip: param.offset,
                page: param.offset/param.limit + 1,
                pageSize: param.limit
            };
            return temp;
        },
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
                footerFormatter: totalTextFormatter
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
        //查询之前重置table
        gridTable.bootstrapTable('resetSearch');
        var name = $("#s_name").val();
        var age = $("#s_age").val();
        gridTable.bootstrapTable('refresh',{
            query:{name: name, age: age}
        });
    });
    //重置搜索
    $("#searchFix").click(function () {
        $("#s_name").val("");
        $("#s_age").val("");
        gridTable.bootstrapTable('resetSearch');
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
        '<button  type="button" class="btn btn-primary like" data-toggle="modal" data-target="#demoForm" >系统发送</button>'
    ].join('');
}

// 操作事件
window.operateEvents = {
    'click .like': function (e, value, row, index) {
        //alert('You click like action, row: ' + JSON.stringify(row));
        refreshDemoForm(row);
    }
};

/**
 * 刷新表单数据
 * @param demo
 */
function refreshDemoForm(demo) {
    var id = "";
    id = demo.id;
    $("#eventTime").val(demo.eventTime);
    $("#enterpriseName").val(demo.enterpriseName);
    $("#blockName").val(demo.blockName);
    $("#supervisor").val(demo.supervisor);
    $("#overValue").val(demo.overValue);
    $("#thrValue").val(demo.thrValue);
    $("#content").val(demo.content);
    $("#senderName").val(demo.senderName);
    $("#sendTime").val(demo.sendTime);
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
//初始化表单验证
var ef = $("#demoForm").easyform({
    success:function (ef) {
        var demo = {};
        saveDemo(demo,function (msg) {
            $('#demoForm').modal('hide');
            gridTable.bootstrapTable('refresh');
        });
    },
    error:function (ef,input, rull) {
        alert($(input).attr("id"));
    }
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


$(".scrollContent").slimScroll({
    height:"100%",
    railOpacity:.9,
    alwaysVisible:!1
});

var setting = {
    height:500,
    width:200,
    view: {
        showLine: true
    },
    async: {
        enable: true,
        url:"selectPeople.json",
        autoParam:["id", "name=n", "level=lv"],
        otherParam:{"otherParam":"zTreeAsyncTest"},
        dataFilter: filter
    }
};
function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i=0, l=childNodes.length; i<l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
}
$.fn.zTree.init($("#treeDemo1"), setting);

function f(p) {
    console.log("fffffffff")
}
