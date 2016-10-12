var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#scfForm"),
    formTitle = "传输有效率",
    selections = [];

//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_port_TransportEfficient_save.action",
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
        url: rootPath + "/action/S_port_TransportEfficient_delete.action",
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
        url: rootPath+"/action/S_port_TransportEfficient_list.action",
        height: getHeight(),
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
        queryParams:pageUtils.localParams,
        columns: [
            {
                title:"全选",
                checkbox: true,
                align: 'center',
                radio:false,  //  true 单选， false多选
                valign: 'middle'
            }, {
                title: 'ID',
                field: 'id',
                align: 'center',
                valign: 'middle',
                sortable: false,
                visible:false
            },
            {
                title: '企业名称',
                field: 'enterpriseName',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                field: 'blockName',
                title: '所属网格名称',
                sortable: false,
                align: 'center',
                editable: false,
                visible: false
            },
            {
                field: 'startTime',
                title: '开始时间',
                sortable: false,
                align: 'center',
                editable: false,
                visible:false,
                formatter:function (value, row, index) {
                    return pageUtils.sub10(value);
                }
            },
            {
                field: 'endTime',
                title: '结束时间',
                sortable: false,
                align: 'center',
                editable: false,
                visible:false,
                formatter:function (value, row, index) {
                    return pageUtils.sub10(value);
                }
            },
            {
                field: 'transpor',
                title: '传输率',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                field: 'efficient',
                title: '有效率',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                field: 'ratio',
                title: '传输有效率',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                field: 'remark',
                title: '备注',
                sortable: false,
                align: 'center',
                editable: true,
                visible:false
            }
        ]
    });
    // sometimes footer render error.
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
}



// 生成列表操作方法
function operateFormatter(value, row, index) {
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#scfForm">查看</button>';
}
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
/**
 * 列表工具栏 新增和更新按钮打开form表单，并设置表单标识
 */
$("#add").bind('click',function () {
    resetForm();
});
$("#update").bind("click",function () {
    setFormData(getSelections()[0]);
});
/**
 * 列表工具栏 删除按钮
 */
removeBtn.click(function () {
    var ids = getIdSelections();
    deleteAjax(ids,function (msg) {
        gridTable.bootstrapTable('remove', {
            field: 'id',
            values: ids
        });
        removeBtn.prop('disabled', true);
    });

});

/**============列表搜索相关处理============**/
//搜索按钮处理
$("#search").click(function () {
    //查询之前重置table
    var queryParams = {};
    var enterpriseName = $("#s_enterpriseName").val();
    if (enterpriseName){
        queryParams["enterpriseName"] = enterpriseName;
    }
    search(queryParams);
});
function search(params) {
    gridTable.bootstrapTable('refresh',{
        query:params
    });
}

/**============表单初始化相关代码============**/
//初始化表单验证
var ef = form.easyform({
    success:function (ef) {
        var entity = $("#scfForm").find("form").formSerializeObject();
        // entity.attachmentId = getAttachmentIds();
        saveAjax(entity,function (msg) {
            form.modal('hide');
            gridTable.bootstrapTable('refresh');
        });
    },
    error:function (ef,input, rull) {
        alert($(input).attr("id"));
    }
});

//表单 保存按钮
$("#save").bind('click',function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    ef.submit(false);
});
//初始化日期组件
$('#datetimepicker').datetimepicker({
    language:   'zh-CN',
    autoclose: 1,
    minView: 2
});
$('#datetimepicker2').datetimepicker({
    language:   'zh-CN',
    autoclose: 1,
    minView: 2
});

/**
 * 按时间查询表单
 * @type {*|jQuery|HTMLElement}
 */
var yearUl = $('#year');

var year = new Date().getFullYear();
for ( var i = year; i >=2014; i--) {
    $("<li class='year'>" + i + "</li>").appendTo(yearUl);
}

var statistical = $("#statistical");
//按年份查询
statistical.find("#year .year").bind('click',function(){
    var year = parseInt($(this).text());
    $('#selYear').text(year);
    var startYdate = year +　'-'+'01' + '-'+'01';
    var lastYdate = year + '-'+ '12' + '-'+ '31';
    search({
        startYdate:startYdate,
        lastYdate:lastYdate
        
    });
});

//按季度查询
statistical.find(".tm").bind('click',function(){
    var seasons= $(this).text();
    var selYear = $('#selYear').text();
    if(selYear == "年份"){
        alert("请选择年份");
        return ;
    }
    if(seasons == "第一季度"){
        var startYdate = selYear + '-' +'01' + '-' + '01' ;
        var lastYdate = selYear + '-' + '03' + '-' + '31';
    }else if(seasons == "第二季度"){
        var startYdate= selYear + '-' + '04' + '-' + '01';
        var lastYdate = selYear + '-' + '06' + '-' + '30';
    }else if(seasons == "第三季度"){
        var startYdate = selYear + '-' + '07' + '-'+ '01';
        var lastYdate = selYear + '-' + '09' + '-' + '30';
    }else if(seasons = "第四季度"){
        var startYdate = selYear + '-' + '10' + '-' + '01';
        var lastYdate = selYear + '-' + '12' + '-'+ '31';
    }
    // search(startSdate, lastSdate);
    search({
        startYdate:startYdate,
        lastYdate:lastYdate
        
    });
});

//按月份查询
statistical.find("li[class='month']").bind("click", function() {
    var mNum = parseInt(this.value);
    var m = mNum > 9 ? mNum : ("0"+mNum);
    var selYear = $('#selYear').text();
    if(selYear == "年份"){
        alert("请选择年份!");
        return;
    }
    var  day = new Date(selYear,m,0);
    var startYdate = selYear +'-'+ m + '-'+'01';
    var lastYdate = selYear + '-' + m + '-' + day.getDate();//获取当月最后一天日期
    search({
        startYdate:startYdate,
        lastYdate:lastYdate
    });
});

/**
 * 设置表单数据
 * @param entity
 * @returns {boolean}
 */
function setFormData(entity) {
    resetForm();
    if (!entity) {return false}
    form.find(".form-title").text("修改水污染治理设施");
    var id = entity.id;
    $("#id").val(entity.id);
    $("#enterpriseName").val(entity.enterpriseName);
    $("#blockName").val(entity.blockName);
    $("#startTime").val(pageUtils.sub10(entity.startTime));
    $("#endTime").val(pageUtils.sub10(entity.endTime));
    $("#transpor").val(entity.transpor);
    $("#efficient").val(entity.efficient);
    $("#ratio").val(entity.ratio);
    $("#remark").val(entity.remark);
    // uploader = new qq.FineUploader(getUploaderOptions(id));
}

function setFormView(entity) {
    setFormData(entity);
    form.find(".form-title").text("查看水污染治理设施");
    disabledForm(true);

}
function disabledForm(disabled) {
    form.find("input").attr("disabled",disabled);
    if (!disabled) {
        //初始化日期组件
        $('#createTimeContent').datetimepicker({
            language:   'zh-CN',
            autoclose: 1,
            minView: 2
        });
        $('#openDateContent').datetimepicker({
            language:   'zh-CN',
            autoclose: 1,
            minView: 2
        });
    }else{
        $('#createTimeContent').datetimepicker('remove');
        $('#openDateContent').datetimepicker('remove');
    }

}

/**
 * 重置表单
 */
function resetForm() {
    form.find(".form-title").text("新增" + formTitle);
    form.find("input[type!='radio'][type!='checkbox']").val("");
    // uploader = new qq.FineUploader(getUploaderOptions());
    disabledForm(false);
}


