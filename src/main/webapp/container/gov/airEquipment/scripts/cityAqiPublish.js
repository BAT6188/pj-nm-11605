var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#scfForm"),
    formTitle = "空气质量在线监测",
    selections = [];
$('.form_date').datetimepicker({
    language:   'zh-CN',
    format: 'yyyy-mm-dd hh:00',
    minView:1,
    todayBtn:  1,
    todayHighlight: 1,
    autoclose: 1,
    pickerPosition: "bottom-left"
});
/**============grid 列表初始化相关代码============**/
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_port_CityAqiPublish_list.action",
        height: pageUtils.getTableHeight(),
        method:'post',
        pagination:true,
        pageSize:15,
        clickToSelect:true,//单击行时checkbox选中
        detailView: true,
        onExpandRow: function (index, row, $detail) {
            getAirEquipmentHistory({monitoringTime:row.timePoint},function(data){
                expandTable($detail, data);
            });
        },
        rowStyle:function(row,index) {
            return { classes:getInfoStyle(row.aQI)};
        },
        queryParams:function(param){
            var temp = pageUtils.getBaseParams(param);
            return temp;
        },
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
                title: '监测地区',
                field: 'monitoringPosition',
                editable: false,
                sortable: false,
                align: 'center',
                formatter: function (value, row, index) {
                    return '鄂尔多斯东胜';
                }
            },
            {
                title: '监测时间',
                field: 'timePoint',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                field: 'aQI',
                title: 'AQI指数',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                field: 'quality',
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
            },
             {
                 field: 'operate',
                 title: '操作',
                 align: 'center',
                 events: operateEvents,
                 formatter: function (value, row, index) {
                     return '<button type="button" class="btn btn-md btn-info view">各监测点数据</button>' +
                         '<button type="button" style="display: none;"  class="btn btn-md btn-warning closeView">关闭</button>';
                 }
             }
        ]
    });
    // sometimes footer render error.
    setTimeout(function () {
        gridTable.bootstrapTable('resetView');
    }, 200);

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
        $(this).hide();
        $(this).next().show();
        gridTable.bootstrapTable('expandRow',index);
    },
    'click .closeView': function (e, value, row, index) {
        $(this).hide();
        $(this).prev().show();
        gridTable.bootstrapTable('collapseRow',index);
    }
};

function getInfoStyle(value){
    var style = 'default';
    if(value > 200){
        style = 'danger alert-danger';
    }else if(value > 100){
        style = 'warning alert-warning';
    }else if(value > 0){
        style = 'success alert-success';
    }
    return style;
}
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

function expandTable($detail, data) {
    buildTable($detail.html('<table></table>').find('table'), data);
}

function buildTable($el, data) {
    $el.bootstrapTable({
        rowStyle:function(row,index) {
            return { classes:getInfoStyle(row.airIndex)};
        },
        columns: [
            {
                title: '空气质量监测点',
                field: 'monitoringPosition',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '监测时间',
                field: 'monitoringTime',
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
        ],
        data: data,
    });
}
function getAirEquipmentHistory(entity,callback){
    $.ajax({
        url: rootPath + "/action/S_port_AirEquipmentHistory_findBySimple.action",
        type:"post",
        data:entity,
        dataType:"json",
        success:callback
    });
}

/**============列表工具栏处理============**/
//初始化按钮状态
removeBtn.prop('disabled', true);
updateBtn.prop('disabled', true);


/**============列表搜索相关处理============**/
//搜索按钮处理
//搜索按钮处理
$("#search").click(function () {
    gridTable.bootstrapTable('refreshOptions', {pageNumber: 1, pageSize: 15});
});
//重置搜索
$("#searchFix").click(function () {
    $('#searchform')[0].reset();
    gridTable.bootstrapTable('refreshOptions', {pageNumber: 1, pageSize: 15});
});









