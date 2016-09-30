
var $table = $('#table'),
    $remove = $('#remove'),
    selections = [];
function initTable() {
    $table.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        height: 600,
        method:'post',
        queryParams:function (param) {
            var name = $("#s_name").val();
            var age = $("#s_age").val();
            console.log(param);
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
                field: 'id',
                checkbox: true,
                align: 'center',
                radio:false,  //  true 单选， false多选
                valign: 'middle'
            }, {
                title: 'ID',
                field: 'id',
                align: 'center',
                valign: 'middle',
                sortable: true,
                footerFormatter: totalTextFormatter
            },
            {
                field: 'name',
                title: '姓名',
                sortable: true,
                editable: false,
                footerFormatter: totalNameFormatter,
                align: 'center'
            }, {
                field: 'age',
                title: '年龄',
                sortable: true,
                align: 'center',
                editable: false,
                footerFormatter: totalPriceFormatter
            }, {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: operateEvents,
                formatter: operateFormatter
            }

        ]
    });
    // sometimes footer render error.
    setTimeout(function () {
        $table.bootstrapTable('resetView');
    }, 200);
    $table.on('check.bs.table uncheck.bs.table ' +
        'check-all.bs.table uncheck-all.bs.table', function () {
        $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
        // save your data, here just save the current page
        selections = getIdSelections();
        // push or splice the selections if you want to save all data selections
    });
    $table.on('expand-row.bs.table', function (e, index, row, $detail) {
        if (index % 2 == 1) {
            $detail.html('Loading from ajax request...');
            $.get('LICENSE', function (res) {
                $detail.html(res.replace(/\n/g, '<br>'));
            });
        }
    });
    $table.on('all.bs.table', function (e, name, args) {
        //console.log(" 全选 ");
    });
    $remove.click(function () {
        var ids = getIdSelections();
        deleteDemos(ids,function (msg) {
            $table.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
            $remove.prop('disabled', true);
        });

    });

    //搜索
    $("#search").click(function () {
        //查询之前重置table
        $table.bootstrapTable('resetSearch');
        var name = $("#s_name").val();
        var age = $("#s_age").val();
        $table.bootstrapTable('refresh',{
            query:{name: name, age: age}
        });
    });
    //重置搜索
    $("#searchFix").click(function () {
        $("#s_name").val("");
        $("#s_age").val("");
        $table.bootstrapTable('resetSearch');
    });

    //表单弹出框 保存按钮
    $("#saveDemo").bind('click',function () {
        var demo = {};
        demo.name = $("#name").val();
        demo.age = $("#age").val();
        saveDemo(demo,function (msg) {
            $('#demoForm').modal('hide');
            $table.bootstrapTable('refresh');
        });
    });

    $(window).resize(function () {
        // 重新设置表的高度
        $table.bootstrapTable('resetView', {
            height: getHeight()
        });
    });
}
// 获取所有的选中数据
function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.id
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
        '<a class="like" href="javascript:void(0)" title="Like">',
        '<i class="glyphicon glyphicon-heart"></i>',
        '</a>  ',
        '<a class="remove" href="javascript:void(0)" title="Remove">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
// 操作事件
window.operateEvents = {
    'click .like': function (e, value, row, index) {
        alert('You click like action, row: ' + JSON.stringify(row));
    },
    'click .remove': function (e, value, row, index) {
        deleteDemos(row.id, function (msg) {
            $table.bootstrapTable('remove', {
                field: 'id',
                values: [row.id]
            });
        });

    }
};
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

function updateDemo(demo) {
    $.ajax({
        url: rootPath + "/action/S_demo_Demo_save.action",
        type:"post",
        data:demo,
        dataType:"json",
        success:function (msg) {
            alert("更新成功");
        }
    });
}

function deleteDemos(ids,callback) {
    $.ajax({
        url: rootPath + "/action/S_demo_Demo_delete.action",
        type:"post",
        data:$.param({deletedId:ids},true),//阻止深度序列化，向后台传递数组
        dataType:"json",
        success:callback
    });
}

function saveDemo(demo,callback) {
    $.ajax({
        url: rootPath + "/action/S_demo_Demo_save.action",
        type:"post",
        data:demo,
        dataType:"json",
        success:callback
    });
}