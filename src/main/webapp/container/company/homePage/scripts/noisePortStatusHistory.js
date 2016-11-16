var gridTable = $('#table'),
    selections = [];
$('.form_date').datetimepicker({
    language:   'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 1,
    forceParse: 0,
    minuteStep:5,
    pickerPosition: "bottom-left"
});
/**============grid 列表初始化相关代码============**/
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_port_NoisePortHistory_list.action",
        height: pageUtils.getTableHeight()-100,
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
        queryParams:function (param) {
            var temp = pageUtils.getBaseParams(param);
            temp.portId = portId;
            return temp;
        },
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
                title: 'Leq(db)',
                field: 'leqdb',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: 'sd',
                field: 'sd',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: 'Lmax(dB)',
                field: 'lmax',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: 'Lmin(dB)',
                field: 'lmin',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: 'L5(dB)',
                field: 'lFive',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: 'L10(dB)',
                field: 'lTen',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: 'L50(dB)',
                field: 'lFifty',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: 'L90(dB)',
                field: 'lNinety',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: 'L95(dB)',
                field: 'lNinetyFive',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: 'Le',
                field: 'le',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '昼间上限(dB)',
                field: 'dayMax',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '夜间上限(dB)',
                field: 'nightMax',
                editable: false,
                sortable: false,
                align: 'center'
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
            height:pageUtils.getTableHeight()-100,
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
    gridTable.bootstrapTable('resetSearch');
    var jsonData = $('#searchform').formSerializeObject();
    if(jsonData){
        if(checkSearchForm(jsonData)){
            gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
        }
    }
});
//重置搜索
$("#searchFix").click(function () {
    $('#searchform')[0].reset();
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
});
function checkSearchForm(jsonData){
    if(jsonData.startTime<jsonData.endTime || (!jsonData.startTime && !jsonData.endTime)){
        return true;
    }else{
        if(jsonData.startTime && !jsonData.endTime){
            Ewin.alert("缺少结束时间！");
        }
        if(!jsonData.startTime && jsonData.endTime){
            Ewin.alert("缺少开始时间！");
        }
        if(jsonData.startTime>jsonData.endTime){
            Ewin.alert("开始时间要小于结束时间！");
        }
        return false;
    }
}