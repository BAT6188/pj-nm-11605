var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#scfForm"),
    formTitle = "现场监察",
    selections = [];

//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_port_AirEquipment_save.action",
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
        url: rootPath + "/action/S_port_AirEquipment_delete.action",
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
        url: rootPath+"/action/S_port_AirEquipment_list.action",
        height: pageUtils.getTableHeight(),
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
        queryParams:function(param){
            var temp = pageUtils.getBaseParams(param);
            return temp;
        },
        columns: [
            // {
            //     title:"全选",
            //     checkbox: true,
            //     align: 'center',
            //     radio:false,  //  true 单选， false多选
            //     valign: 'middle'
            // },
            {
                title: 'ID',
                field: 'id',
                align: 'center',
                valign: 'middle',
                sortable: false,
                visible:false
            },
            {
                title: '空气质量监测点',
                field: 'airMonitoringName',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                field: 'airIndex',
                title: 'AQI指数',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                field: 'airQualityGrade',
                title: '空气质量等级',
                sortable: false,
                align: 'center',
                editable: false
            },

            {
                field: 'primaryPollutant',
                title: '首要污染物',
                sortable: false,
                align: 'center',
                editable: false,
            }


            // {
            //     field: 'operate',
            //     title: '操作',
            //     align: 'center',
            //     events: operateEvents,
            //     formatter: operateFormatter
            // }

        ]
    });
    // sometimes footer render error.
    setTimeout(function () {
        gridTable.bootstrapTable('resetView');
    }, 200);

    //列表checkbox选中事件
    // gridTable.on('check.bs.table uncheck.bs.table ' +
    //     'check-all.bs.table uncheck-all.bs.table', function () {
    //     //有选中数据，启用删除按钮
    //     removeBtn.prop('disabled', !gridTable.bootstrapTable('getSelections').length);
    //     //选中一条数据启用修改按钮
    //     updateBtn.prop('disabled', !(gridTable.bootstrapTable('getSelections').length== 1));
    //
    // });

    $(window).resize(function () {
        // 重新设置表的高度
        gridTable.bootstrapTable('resetView', {
            height: pageUtils.getTableHeight()
        });
    });
}

// 生成列表操作方法
// function operateFormatter(value, row, index) {
//     return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#scfForm">查看</button>';
// }
// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, row, index) {
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

function getHeight() {
    return $(window).height() - $('.dealBox').outerHeight(true) - 13;
}
initTable();



/**============列表工具栏处理============**/
//初始化按钮状态
removeBtn.prop('disabled', true);
updateBtn.prop('disabled', true);


/**============列表搜索相关处理============**/
//搜索按钮处理
//搜索按钮处理
$("#search").click(function () {
    gridTable.bootstrapTable('refreshOptions', {pageNumber: 1, pageSize: pageUtils.PAGE_SIZE});
});
//重置搜索
$("#searchFix").click(function () {
    $('#searchform')[0].reset();
    gridTable.bootstrapTable('refreshOptions', {pageNumber: 1, pageSize: pageUtils.PAGE_SIZE});
});









