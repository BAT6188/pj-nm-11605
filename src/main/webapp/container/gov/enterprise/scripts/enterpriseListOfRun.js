var gridTable = $('#table'),
    add = $('#add'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    selections = [];
/**
 * 删除请求
 * @param ids 多个,号分隔
 * @param callback
 */
function deleteAjax(ids, callback) {
    if(ids!=undefined && ids!=""){
        $.ajax({
            url: rootPath + "/action/S_enterprise_Enterprise_delete.action",
            type:"post",
            data:$.param({deletedId:ids},true),//阻止深度序列化，向后台传递数组
            dataType:"json",
            success:callback
        });
    }else{
        $(".alert-warning").show();
        setTimeout(function () {
            $(".alert-warning").hide();
        }, 2000);
    }
}

/*信息列表*/
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_enterprise_Enterprise_list.action",
        height: 500,
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
        queryParams:pageUtils.localParams,
        columns: [
            {
                field: 'state',
                checkbox: true,
                align: 'center',
                radio:false,  //  true 单选， false多选
                valign: 'middle'
            },
            {
                field: 'name',
                title: '排污单位名称',
                sortable: true,
                align: 'center'
            }, {
                field: 'orgCode',
                title: '组织机构代码',
                sortable: true,
                align: 'center'
            }, {
                field: 'artificialPerson',
                title: '企业法人',
                sortable: true,
                align: 'center'
            }, {
                field: 'apPhone',
                title: '联系方式',
                sortable: true,
                align: 'center'
            }, {
                field: 'status',
                title: '企业运行状态',
                sortable: true,
                align: 'center'
            }, {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: operateEvents,
                formatter: operateFormatter
            }

        ]
    });
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
            height: getHeight()
        });
    });
    //处理新增按钮
    add.click(function(){
       window.location.href = 'enterpriseAdd.jsp';
    });
    /*处理更新按钮*/
    updateBtn.click(function(){
        window.location.href = 'enterpriseEdit.jsp';
    });
    //处理删除按钮状态
    removeBtn.click(function () {
        var ids = getIdSelections();
        deleteAjax(ids,function (msg) {
            alert("删除成功！");
            gridTable.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
            removeBtn.prop('disabled', true);
        });

    });
    //搜索
    $("#search").click(function () {
        //查询之前重置table
        gridTable.bootstrapTable('resetSearch');
        var jsonData = $('#searchform').formSerializeObject();
        gridTable.bootstrapTable('refresh',{
            query:jsonData
        });
    });
    //重置搜索
    $("#searchFix").click(function () {
        $('#searchform')[0].reset();
        gridTable.bootstrapTable('resetSearch');
    });
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
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#scfForm">查看</button>';
}
// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, row, index) {
        setFormView(row);
    }
};
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
function getHeight() {
    return $(window).height() - $('h1').outerHeight(true);
}
initTable();

//初始化按钮状态
removeBtn.prop('disabled', true);
updateBtn.prop('disabled', true);
