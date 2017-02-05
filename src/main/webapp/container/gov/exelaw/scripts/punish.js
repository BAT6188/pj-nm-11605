var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#demoForm"),
    formTitle = "行政处罚信息",
    selections = [];





//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_exelaw_Punish_save.action",
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
        url: rootPath + "/action/S_exelaw_Punish_delete.action",
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
        url: rootPath+"/action/S_exelaw_Punish_list.action?dispatchTaskId="+dispatchTaskId,
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
                title: '案件名称',
                field: 'caseName',
                editable: false,
                sortable: false,
                isDown:true,
                align: 'center'
            },
            {
                title: '案由',
                field: 'caseReason',
                sortable: false,
                align: 'center',
                isDown:true,
                editable: false
            },
            {
                title: '立案时间',
                field: 'filingDate',
                editable: false,
                sortable: false,
                isDown:true,
                align: 'center'
            },
            {
                title: '处罚类型',
                field: 'type',
                editable: false,
                sortable: false,
                align: 'center',
                isDown:true,
                formatter:function (value, row, index) {
                    // return dict.getDctionnaryName(dict.getDctionnary({code:'punishType'}),value)
                    return dict.get("punishType",value)
                }
            },
            {
                title: '结案日期',
                field: 'closedDate',
                editable: false,
                sortable: false,
                isDown:true,
                align: 'center'
            },
            {
                title: '履行情况',
                field: 'exeDesc',
                editable: false,
                sortable: false,
                align: 'center',
                isDown:true,
                formatter:function (value, row, index) {
                    return dict.get("exeDesc",value)
                }
            },
            {
                title: '案件来源',
                field: 'caseSource',
                editable: false,
                sortable: false,
                isDown:true,
                align: 'center',
                formatter:function (value, row, index) {
                    return dict.get("caseSource",value)
                }
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
        updateBtn.prop('disabled', !(gridTable.bootstrapTable('getSelections').length== 1));
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
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#demoForm">查看</button>';
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
updateBtn.prop('disabled', true);
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
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
});
//重置搜索
$("#searchFix").click(function () {
    resetQuery();
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
});


//初始化日期组件
$('.form_datetime').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1
});

/**============表单初始化相关代码============**/

//初始化表单验证
var ef = form.easyform({
    success:function (ef) {
        var entity = $("#demoForm").find("form").formSerializeObject();
        entity.attachmentIds = getAttachmentIds();
        saveAjax(entity,function (msg) {
            form.modal('hide');
            gridTable.bootstrapTable('refresh');
        });
    }
});

//表单 保存按钮
$("#save").bind('click',function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    ef.submit(false);
});

$("#update").bind("click",function () {
    setFormData(getSelections()[0]);
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
    $("#removeId").val("");
    for(p in entity){
        var selector="#"+p
        $(selector).val(entity[p])
    }

    uploader = new qq.FineUploader(getUploaderOptions(id));
}
function setFormView(entity) {
    setFormData(entity);
    form.find(".form-title").text("查看"+formTitle);
    disabledForm(true);
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无上传的附件");
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
    form.find("#save").hide();
    form.find(".btn-cancel").text("关闭");
}
function disabledForm(disabled) {
    form.find("input").attr("disabled",disabled);
    form.find("textarea").attr("disabled",disabled);
    form.find("select").attr("disabled",disabled);

    if (!disabled) {
        //初始化日期组件
        $('.lookover').datetimepicker({
            language:   'zh-CN',
            autoclose: 1,
            minView: 2
        });

    }else{
        $('.lookover').datetimepicker('remove');
    }

}

/**
 * 重置表单
 */
function resetForm() {
    form.find(".form-title").text("新增"+formTitle);
    form.find("input[type!='radio'][type!='checkbox']").val("");
    form.find("textarea").val("");
    uploader = new qq.FineUploader(getUploaderOptions());
    disabledForm(false);
    form.find("#save").show();
    form.find(".btn-cancel").text("取消");

    $("#filingDate").val((new Date()).format("yyyy-MM-dd hh:mm"))

    if(dispatchTaskId){
        $.ajax({
            url: rootPath + "/action/S_dispatch_DispatchTask_list.action",
            type:"post",
            data:{id:dispatchTaskId},
            success:function (d) {
                d=JSON.parse(d)
                if (d.total>0){
                    var row=d.rows[0]
                    setFormValueFromSelected(row)
                }
            }
        });
    }
}

//表单附件相关js
var uploader;//附件上传组件对象
/**
 * 获取上传组件options
 * @param bussinessId
 * @returns options
 */
function getUploaderOptions(bussinessId) {
    return {
        element: document.getElementById("fine-uploader-gallery"),
        template: 'qq-template',
        chunking: {
            enabled: false,
            concurrent: {
                enabled: true
            }
        },
        resume: {
            enabled: false
        },
        retry: {
            enableAuto: false,
            showButton: false
        },
        failedUploadTextDisplay: {
            mode: 'custom'
        },
        callbacks: {
            onComplete:function (id,fileName,msg,request) {
                uploader.setUuid(id, msg.id);
            },
            onDeleteComplete:function (id) {
                var file = uploader.getUploads({id:id});
                var removeIds = $("#removeId").val();
                if (removeIds) {
                    removeIds+= ("," + file.uuid)
                }else{
                    removeIds = file.uuid;
                }
                $("#removeId").val(removeIds);
            },
            onAllComplete: function (succeed) {
                var self = this;
                $.each(succeed, function (k, v) {
                    $('.qq-upload-download-selector', self.getItemByFileId(v)).toggleClass('qq-hide', false);
                });
            }
        },
        request: {
            endpoint: rootPath + '/Upload',
            params: {
                businessId:bussinessId
            }
        },
        session:{
            endpoint: rootPath + '/action/S_attachment_Attachment_listAttachment.action',
            params: {
                businessId:bussinessId
            }
        },
        deleteFile: {
            enabled: true,
            endpoint: rootPath + "/action/S_attachment_Attachment_delete.action",
            method:"POST"
        },
        validation: {
            itemLimit: 5
        },
        debug: true
    };
}


/**
 * 绑定下载按钮事件
 */
$("#fine-uploader-gallery").on('click', '.qq-upload-download-selector', function () {
    var uuid = uploader.getUuid($(this.closest('li')).attr('qq-file-id'));
    window.location.href = rootPath+"/action/S_attachment_Attachment_download.action?id=" + uuid;
});


/********************  选择执法列表  ********************/
var lawTable = $('#lawTable')
function initlawTable() {
    lawTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_dispatch_DispatchTask_list.action?notOver=TRUE",
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
        queryParams:pageUtils.localParams,
        columns: [
            {
                title:"全选",
                checkbox: true,
                align: 'center',
                radio:true,  //  true 单选， false多选
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
                title: '事件时间',
                field: 'eventTime',
                sortable: false,
                align: 'center',
                editable: false,
                formatter:function (value, row, index) {
                    return pageUtils.sub16(value);
                }
            },
            {
                title: '企业名称',
                field: 'enterpriseName',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '信息来源',
                field: 'source',
                editable: false,
                sortable: false,
                align: 'center',
                formatter:function (value, row, index) {
                    if(1==value){
                        value="12369"
                    }else if (2==value){
                        value="区长热线"
                    }else if (3==value){
                        value="市长热线"
                    }else if (4==value){
                        value="现场监察"
                    }else if (0==value){
                        value="监控中心"
                    }
                    return value;
                }
            },
            {
                field: 'reason',
                title: '原因',
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

    $(window).resize(function () {
        // 重新设置表的高度
        gridTable.bootstrapTable('resetView', {
            height: pageUtils.getTableHeight()
        });
    });
}
initlawTable();
$("#sc").click(function () {
    var queryParams = {};
    var s_enterpriseName = $("#s_enterpriseName").val();
    var s_source = $("#s_source").val();
    if (s_enterpriseName){
        queryParams["enterpriseName"] = s_enterpriseName;
    }
    if (s_source){
        queryParams["source"] = s_source;
    }
    lawTable.bootstrapTable('refresh',{
        query:queryParams
    });
})

function getLawTableSelections() {
    return $.map(lawTable.bootstrapTable('getSelections'), function (row) {
        return row;
    });
}

$("#selected").click(function () {
    var rows=getLawTableSelections();
    if (rows.length>0){
        var row=rows[0]
        setFormValueFromSelected(row)
        $("#lawListForm").modal('hide');
    }

})

function setFormValueFromSelected(row) {
    $("#dispatchTaskId").val(row.id)
    $("#caseName").val(row.enterpriseName+"处罚")
    $("#caseSource").val(row.source)
    $("#caseReason").val(row.reason)
}

var exportBtn = $('#export'); //下载按钮
var options = {
    fileName:'行政处罚',  //自定义文件名
    type: 'excel',     //json,xml,csv,txt,sql,excel 文件类型(默认为excel，可不填)
    escape: false
}
gridTable.BootstrapExport(exportBtn,options);

