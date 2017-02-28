var gridTable = $('#table'),
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
        url: rootPath+"/action/S_port_WaterPortHistory_list.action",
        height: pageUtils.getTableHeight()-50,
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
        queryParams:pageUtils.localParams,
        rowStyle:function(row,index) {
            var dataType;
            switch(row.dataStatus){
                case '1':
                    dataType = 'danger alert-danger';
                    break;
                case '2':
                    dataType = 'warning alert-warning';
                    break;
                default:
                    dataType = 'success alert-success';
            }
            return { classes:dataType};
        },
        columns: [
            {
                title: '监测时间',
                field: 'monitorTime',
                sortable: false,
                editable: false,
                align: 'center'
            },
            {
                title: '流量（升/秒）',
                field: 'flowLiveValue',
                editable: false,
                sortable: false,
                align: 'center',
                formatter:function (value, row, index) {
                    if(row.flowStatus){
                        if(row.flowStatus!=0){
                            value='<span style="color:red;font-weight: bolder;">'+value+'</span>'
                        }
                    }
                    return value;
                }
            },
            {
                title: '化学需氧量（毫克/升）',
                field: 'oxygenLiveValue',
                editable: false,
                sortable: false,
                align: 'center',
                formatter:function (value, row, index) {
                    if(row.oxygenStatus){
                        if(row.oxygenStatus!=0){
                            value='<span style="color:red;font-weight: bolder;">'+value+'</span>'
                        }
                    }
                    value=value+"("+row.oxygenStandardValue+")"
                    return value;
                }
            },
            {
                title: '氨氮（毫克/升）',
                field: 'nitrogenLiveValue',
                editable: false,
                sortable: false,
                align: 'center',
                formatter:function (value, row, index) {
                    if(row.nitrogenStatus){
                        if(row.nitrogenStatus!=0){
                            value='<span style="color:red;font-weight: bolder;">'+value+'</span>'
                        }
                    }
                    value=value+"("+row.nitrogenStandardValue+")"
                    return value;
                }
            },
            {
                title: 'ph值',
                field: 'phLiveValue',
                editable: false,
                sortable: false,
                align: 'center',
                formatter:function (value, row, index) {
                    if (value){
                        if(row.phStatus){
                            if(row.phStatus!=0){
                                value='<span style="color:red;font-weight: bolder;">'+value+'</span>'
                            }
                        }
                        value=value+"("+row.phStandardValue+")"
                    }
                    return value;
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
            height: pageUtils.getTableHeight()-50
        });
    });
}

// 生成列表操作方法
function operateFormatter(value, row, index) {
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" onclick="jumpToUrl(\'/container/gov/monitor/lookMonitor.jsp?id='+row.id+'\')">查看</button> ' +
        '&nbsp;&nbsp;<button type="button" class="btn btn-md btn-warning view" data-toggle="modal">地图查看</button>';
}
function jumpToUrl(url){
    $('#level2content').html(pageUtils.loading()); // 设置页面加载时的loading图片
    $('#level2content').load(rootPath+url); // ajax加载页面
}
// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, row, index) {
        $('.saveBtn').hide();
        $('.lookBtn').show();
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
    return $(window).height() - $('.dealBox').outerHeight(true)-160;
}
initTable();
/**============列表工具栏处理============**/


/**============列表搜索相关处理============**/
//搜索按钮处理
$("#search").click(function () {
    //查询之前重置table
    //gridTable.bootstrapTable('resetSearch');
    var jsonData = $('#searchform').formSerializeObject();
    if(jsonData){
        if(pageUtils.checkSearchFormTimes(jsonData.startTime,jsonData.endTime)){
            gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
        }
    }
});
//重置搜索
$("#resetSearch").click(function () {
    resetQuery();
    if(portId=='null'){
        $('#s_enterpriseId').val(enterpriseId);
        $('#portName').html("当前企业所有废气排口");
    }else{
        $('#s_portId').val(portId);
    }
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
});