var gridTable = $('#table'),
    selections = [];
var dictData = dict.getDctionnary({code:['opType']});
initSelect();
function initSelect(){
    var optionsHtml = '';
    $.each(dictData,function(i,obj){
        optionsHtml +='<option value="'+ obj.code+'">'+ obj.name+'</option>';
    })
    $('#opType').append(optionsHtml);
}
function initTimeInput(){
    $('.form_date').datetimepicker({
        language:   'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        pickerPosition: "bottom-left"
    });
}
function getNowDate(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    month = month<10?"0"+month:month;
    var day = date.getDay()<10?'0'+date.getDay():date.getDay();
    console.log(year+"-"+month+"-"+day);
    return year+"-"+month+"-"+day;
}
/*信息列表*/
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_alert_SysOperationLog_list.action",
        height: getHeight(),
        pageSize:15,
        pageList:[10,15,20,25,50,100],
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
        search:true,
        searchOnEnterKey:true,
        queryParams:pageUtils.localParams,
        columns: [
            {
                field: 'opModule',
                title: '操作模块',
                sortable: false,
                align: 'center'
            }, {
                field: 'opUser',
                title: '操作人',
                sortable: false,
                align: 'center'
            }, {
                field: 'opType',
                title: '操作类型',
                sortable: false,
                align: 'center',
                formatter:function(value, row, index){
                    var formateValue = dict.getDctionnaryName(dictData,value);
                    if(formateValue){
                        return formateValue;
                    }else{
                        return value;
                    }
                }
            },
            {
                field: 'opContent',
                title: '操作内容',
                sortable: false,
                align: 'center'
            },
            {
                field: 'opTime',
                title: '操作时间',
                align: 'center'
            }
        ]
    });
    setTimeout(function () {
        gridTable.bootstrapTable('resetView');
    }, 200);

    $(window).resize(function () {
        // 重新设置表的高度
        gridTable.bootstrapTable('resetView', {
            height: getHeight()
        });
    });
    //搜索
    $("#search").click(function () {
        gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
    });
    //重置搜索
    $("#searchFix").click(function () {
        $('#searchform')[0].reset();
        gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
    });
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
    return $(window).height() - $('.dealBox').outerHeight(true) - 150;
}
initTimeInput();
initTable();

