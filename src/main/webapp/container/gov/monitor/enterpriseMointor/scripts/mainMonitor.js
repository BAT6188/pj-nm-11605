var gridTable = $('#table'),
    selections = [],
    blockMap = {};
setBlockMap();
function setBlockMap(){
    $.ajax({
        url: rootPath + "/action/S_composite_BlockLevel_getAllBlocksZtree.action",
        method:'post',
        async :false,
        dataType:"json",
        success:function(data) {
            $.each(data,function(k,v){
                blockMap[v.id] = v.name;
            })
        }
    });
}
$('#mapFrame').attr('width',$('#lookInMap').width()-180);
$('#mapFrame').attr('height',$(window).height()-300);
/**============grid 列表初始化相关代码============**/
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_enterprise_Enterprise_list.action",
        height: getHeight(),
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
        queryParams:function (param) {
            var temp = pageUtils.getBaseParams(param);
            temp.isDel = '0';
            //temp.haveFumesPort = '0';
            return temp;
        },
        columns: [
            /*{
                title:"全选",
                checkbox: true,
                align: 'center',
                radio:false,  //  true 单选， false多选
                valign: 'middle'
            },*/
            {
                title: '企业名称',
                field: 'name',
                sortable: false,
                editable: false,
                align: 'center'
            },
            {
                title: '企业状态',
                field: 'status',
                editable: false,
                sortable: false,
                align: 'center',
                formatter: function(value, row, index) {
                    switch(value){
                        case "0":
                            return '未运行';
                        case "1":
                            return '运行中';
                        default:
                            return '状态不明';
                    }
                }
            },
            {
                title: '所属网格',
                field: 'relateGrid',
                editable: false,
                sortable: false,
                align: 'center',
                formatter: function(value, row, index) {
                    var blockLevelName = blockMap[row.blockLevelId],blockName=blockMap[row.blockId];
                    if(blockLevelName==undefined) blockLevelName = "未选择";
                    if(blockName==undefined) blockName = "未选择";
                    return  blockLevelName+ "-" +blockName;
                }
            },
            {
                title: '污染源状态',
                field: 'pollutantStatus',
                editable: false,
                sortable: false,
                align: 'center',
                formatter: function(value, row, index) {
                    switch(value){
                        case "0":
                            return '<img src="container/gov/enterprise/images/greenCircle.png" style="width: 20px;height: 20px;">';
                        case "1":
                            return '<img src="container/gov/enterprise/images/readCircle.png" style="width: 20px;height: 20px;">';
                        case "2":
                            return '<img src="container/gov/enterprise/images/yelloCircle.png" style="width: 20px;height: 20px;">';
                        default:
                            return '<img src="container/gov/enterprise/images/greenCircle.png" style="width: 20px;height: 20px;">';
                    }
                }
            },
            {
                field: 'operate',
                title: '操作',
                align: 'center',
                //events: operateEvents,
                formatter: operateFormatter
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
            height: getHeight()
        });
    });
}

// 生成列表操作方法
function operateFormatter(value, row, index) {
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" onclick="jumpToUrl(\'/container/gov/monitor/enterpriseMointor/lookMonitor.jsp?id='+row.id+'\')">查看</button> ' +
        '&nbsp;&nbsp;<button type="button" class="btn btn-md btn-warning view">地图查看</button>';// onclick="lookInMap(\''+row.id+'\')"
}
function jumpToUrl(url){
    $('#level2content').html(pageUtils.loading()); // 设置页面加载时的loading图片
    $('#level2content').load(rootPath+url); // ajax加载页面
}
// 列表操作事件
function lookInMap(id){
    MapDialog.loadEnterprise(id);
    MapDialog.open();
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
    return $(window).height() - $('.dealBox').outerHeight(true)-170;
}
initTable();
/**============列表工具栏处理============**/


/**============列表搜索相关处理============**/
//搜索按钮处理
$("#search").click(function () {
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
});
//重置搜索
$("#searchFix").click(function () {
    $('#searchform')[0].reset();
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
});