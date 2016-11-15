var gridTable = $('#table'),
    add = $('#add'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    selections = [];
function initTimeInput(){
    $('#datetimepicker1').datetimepicker({
        language:   'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $('#datetimepicker2').datetimepicker({
        language:   'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
}
/**
 * 删除请求
 * @param ids 多个,号分隔
 * @param callback
 */
function deleteAjax(ids, callback) {
    if(ids!=undefined && ids!=""){
        Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                url: rootPath + "/action/S_enterprise_Enterprise_delete.action",
                type:"post",
                data:$.param({deletedId:ids},true),//阻止深度序列化，向后台传递数组
                dataType:"json",
                success:callback
            });
        });
    }else{
        Ewin.alert('请选择一条记录!');
    }
}

/*信息列表*/
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_enterprise_Enterprise_list.action",
        height: pageUtils.getTableHeight()-40,
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
        queryParams:function (param) {
            var temp = pageUtils.getBaseParams(param);
            temp.isDel = '1';
            return temp;
        },
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
                title: '单位名称',
                sortable: false,
                align: 'center'
            }, {
                field: 'delOpinion',
                title: '删除意见',
                sortable: false,
                align: 'center'
            }, {
                field: 'artificialPerson',
                title: '企业法人',
                sortable: false,
                align: 'center'
            }, {
                field: 'delerName',
                title: '操作人',
                sortable: false,
                align: 'center'
            },
            {
                field: 'delTime',
                title: '操作时间',
                align: 'center'
            },
            {
                field: 'status',
                title: '企业运行状态',
                sortable: false,
                align: 'center',
                formatter: statusFormatter
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
            height: pageUtils.getTableHeight()-40
        });
    });
    //处理新增按钮
    add.click(function(){
       window.location.href = 'mainEnterprise.jsp?handleType=add';
    });
    /*处理更新按钮*/
    updateBtn.click(function(){
        var id = getIdSelections();
        window.location.href = 'mainEnterprise.jsp?handleType=edit&id='+id;
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
        gridTable.bootstrapTable('destroy');
        initTable();
    });
    //重置搜索
    $("#searchFix").click(function () {
        $('#searchform')[0].reset();
        gridTable.bootstrapTable('destroy');
        initTable();
    });
}
/*企业运行状态*/
function  statusFormatter(value, row, index){
    switch(value){
        case "0":
            return '<img src="container/gov/enterprise/images/grayCircle.png" style="width: 20px;height: 20px;">';
        case "1":
            return '<img src="container/gov/enterprise/images/greenCircle.png" style="width: 20px;height: 20px;">';
        default:
            return '<img src="container/gov/enterprise/images/grayCircle.png" style="width: 20px;height: 20px;">';
    }
}
// 生成操作方法
function operateFormatter(value, row, index) {
    return '<button type="button" class="btn btn-md btn-warning view"><a href="mainEnterprise.jsp?handleType=look&id='+row.id+'">查看</a></button>';
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
initTimeInput();
initTable();

//初始化按钮状态
removeBtn.prop('disabled', true);
updateBtn.prop('disabled', true);
