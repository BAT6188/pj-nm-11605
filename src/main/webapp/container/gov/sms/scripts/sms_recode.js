var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#smsRecordForm"),
    formTitle = "短信发送记录",
    selections = [];



//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_sms_SmsRecord_save.action",
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
        url: rootPath + "/action/S_sms_SmsRecord_delete.action",
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
        url: rootPath+"/action/S_sms_SmsRecord_list.action",
        height: pageUtils.getTableHeight(),
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
            },
            {
                title: 'ID',
                field: 'id',
                align: 'center',
                valign: 'middle',
                sortable: false,
                visible:false
            },
            {
                title: '发送时间',
                field: 'sendTime',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '发送人编码',
                field: 'senderId',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '发送人姓名',
                field: 'senderName',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '短信内容',
                field: 'content',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
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
        gridTable.bootstrapTable('resetView');
    }, 200);

    //列表checkbox选中事件
    gridTable.on('check.bs.table uncheck.bs.table ' +
        'check-all.bs.table uncheck-all.bs.table', function () {
        //有选中数据，启用删除按钮
        removeBtn.prop('disabled', !gridTable.bootstrapTable('getSelections').length);
    });

    $(window).resize(function () {
        // 重新设置表的高度
        gridTable.bootstrapTable('resetView', {
            height: pageUtils.getTableHeight()
        });
    });
}

// 生成列表操作方法
function operateFormatter(value, row, index) {
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#smsRecordForm">查看</button>';
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

initTable();
/**============列表工具栏处理============**/
//初始化按钮状态
removeBtn.prop('disabled', true);
/**
 * 列表工具栏 新增和更新按钮打开form表单，并设置表单标识
 */
$("#add").bind('click',function () {
    resetForm();
});
/**
 * 列表工具栏 删除按钮
 */
removeBtn.click(function () {
    var ids = getIdSelections();
    Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
        if (!e) {
            return;
        }
        deleteAjax(ids,function (msg) {
            gridTable.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
            removeBtn.prop('disabled', true);
        });
    });


});



/**============列表搜索相关处理============**/
//搜索按钮处理
$("#search").click(function () {
    var queryParams = {};
    var senderId = $("#s_senderId").val();
    var senderName = $("#s_senderName").val();
    if (senderId){
        queryParams["senderId"] = senderId;
    }
    if (senderName){
        queryParams["senderName"] = senderName;
    }
    gridTable.bootstrapTable('refresh',{
        query:queryParams
    });
});

/**============表单初始化相关代码============**/
//绑定表单接收人列表查看按钮事件
$("#receiverListBtn").bind('click', function () {
    var id = $("#id").val();
    $("#receiverListTable").bootstrapTable('refresh',{
        query:{
            smsRecordId:id
        }
    });
});
//表单 保存按钮
$("#save").bind('click',function () {
    var entity = $("#smsRecordForm").find("form").formSerializeObject();
    saveAjax(entity,function (msg) {
        form.modal('hide');
        gridTable.bootstrapTable('refresh');
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
    form.find(".form-title").text("修改"+formTitle);
    var id = entity.id;
    $("#id").val(entity.id);
    $("#senderId").val(entity.senderId);
    $("#senderName").val(entity.senderName);
    $("#sendTime").val(entity.sendTime);
    $("#content").val(entity.content);

}
function setFormView(entity) {
    setFormData(entity);
    form.find(".form-title").text("查看"+formTitle);
    disabledForm(true);
    form.find("#save").hide();
    form.find(".btn-cancel").text("关闭");
}
function disabledForm(disabled) {
    form.find("input,textarea").attr("disabled",disabled);
}
/**
 * 重置表单
 */
function resetForm() {
    form.find(".form-title").text("新增"+formTitle);
    form.find("input[type!='radio'][type!='checkbox']").val("");
    disabledForm(false);
    form.find("#save").show();
    form.find(".btn-cancel").text("取消");
}
/**============短信接收人列表相关代码============**/

var ReceiverListTable= function () {
    $("#receiverListTable").bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_sms_SmsSendStatus_list.action",
        height: 400,
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
        queryParams:pageUtils.localParams,
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
                title: '接收人编码',
                field: 'receiverId',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '接收人姓名',
                field: 'receiverName',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '接收人电话',
                field: 'receiverPhone',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '接收状态',
                field: 'status',
                sortable: false,
                align: 'center',
                editable: false
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
    });
}();



