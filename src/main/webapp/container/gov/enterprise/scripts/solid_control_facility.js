var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#scfForm"),
    selections = [];



//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_enterprise_SolidControlFacility_save.action",
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
        url: rootPath + "/action/S_enterprise_SolidControlFacility_delete.action",
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
        url: rootPath+"/action/S_enterprise_SolidControlFacility_list.action",
        height: 590,
        method:'post',
        queryParams:function (param) {
            var temp = {
                //分页参数
                take: param.limit,
                skip: param.offset,
                page: param.offset/param.limit + 1,
                pageSize: param.limit
            };
            return temp;
        },
        columns: [
            {
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
                title: '设施名称',
                field: 'name',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '建设日期',
                field: 'createTime',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '投运日期',
                field: 'openDate',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '设计处理能力',
                field: 'ability',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '运行情况',
                field: 'status',
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
    return [
        '<a class="view" data-toggle="modal" data-target="#scfForm" href="javascript:void(0)" title="详情">',
        '<i class="glyphicon glyphicon-list-alt"></i>',
        '</a>&nbsp;&nbsp;',
        '<a class="remove" href="javascript:void(0)" title="删除">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, row, index) {
        alert('You click like action, row: ' + JSON.stringify(row));
    },
    'click .remove': function (e, value, row, index) {
        deleteAjax(row.id, function (msg) {
            gridTable.bootstrapTable('remove', {
                field: 'id',
                values: [row.id]
            });
        });

    }
};
/**============列表工具栏处理============**/
//初始化按钮状态
removeBtn.prop('disabled', true);
updateBtn.prop('disabled', true);
/**
 * 列表工具栏 新增和更新按钮打开form表单，并设置表单标识
 */
$("#add,#update").bind('click',function () {
    //设置表单是新增还是更新的标识
    form.attr("data-form-type",$(this).attr("id"));
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
    return $(window).height() - $('h1').outerHeight(true);
}
initTable();

/**============列表搜索相关处理============**/
//搜索按钮处理
$("#search").click(function () {
    //查询之前重置table
    gridTable.bootstrapTable('resetSearch');
    var name = $("#s_name").val();
    var age = $("#s_age").val();
    gridTable.bootstrapTable('refresh',{
        query:{name: name, age: age}
    });
});
//搜索重置搜索
$("#searchFix").click(function () {
    $("#s_name").val("");
    $("#s_age").val("");
    gridTable.bootstrapTable('resetSearch');
});

/**============表单初始化相关代码============**/
//初始化表单数据
form.on('show.bs.modal', function () {
    var entity;
    var formType = form.attr("data-form-type");
    if (formType == "update") {
        var selects = getSelections();
        if (selects && selects.length > 0) {
            entity = selects[0];
        }
    }
    if (entity && (typeof(entity) == "object")) {
        setFormData(entity);
    }else{
        resetForm();
    }
});
//初始化日期组件
$('#createTimeContent').datetimepicker({
    language:   'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 0
});
$('#openDateContent').datetimepicker({
    language:   'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 0
});
//初始化表单验证
var ef = form.easyform({
    success:function (ef) {
        var entity = {};
        entity.id = $("#id").val();
        entity.name = $("#name").val();
        entity.age = $("#age").val();
        entity.attachmentIds = getAttachmentIds();
        entity.removeId = $("#removeId").val();
        saveAjax(entity,function (msg) {
            form.modal('hide');
            gridTable.bootstrapTable('refresh');
        });
    }
});

//表单 保存按钮
$("#save").bind('click',function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    ef.submit(true);
});

/**
 * 设置表单数据
 * @param entity
 * @returns {boolean}
 */
function setFormData(entity) {
    if (!entity) {return false}
    form.find(".formTitle").text("修改固体废物治理设施");
    var id = entity.id;
    $("#id").val(entity.id);
    $("#removeId").val("");
    $("#name").val(entity.name);
    $("#createTime").val(entity.createTime);
    $("input[name='status']").val(entity.status);
    $("#openDate").val(entity.openDate);
    $("#crafts").val(entity.crafts);
    $("#ability").val(entity.ability);
    $("#realAbility").val(entity.realAbility);
    uploader = new qq.FineUploader(getUploaderOptions(id));
}
/**
 * 重置表单
 */
function resetForm() {
    form.find(".formTitle").text("新增固体废物治理设施");
    form.find("input").val("");
    uploader = new qq.FineUploader(getUploaderOptions());
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
            acceptFiles: ['.jpeg', '.jpg', '.gif', '.png'],
            allowedExtensions: ['jpeg', 'jpg', 'gif', 'png'],
            itemLimit: 3
        },
        debug: true
    };
}
/**
 * 获取附件列表ids
 * @returns {*}
 */
function getAttachmentIds() {
    var attachments = uploader.getUploads();
    if (attachments && attachments.length) {
        var ids = [];
        for (var i = 0 ; i < attachments.length; i++){
            ids.push(attachments[i].uuid);
        }
        return ids.join(",");
    }
    return "";
}

/**
 * 绑定下载按钮事件
 */
$("#fine-uploader-gallery").on('click', '.qq-upload-download-selector', function () {
    var uuid = uploader.getUuid($(this.closest('li')).attr('qq-file-id'));
    window.location.href = rootPath+"/action/S_attachment_Attachment_download.action?id=" + uuid;
});

