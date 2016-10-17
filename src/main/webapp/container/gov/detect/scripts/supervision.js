var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#scfForm"),
    formTitle = "网格人员",
    selections = [];



//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_composite_BlockFirst_save.action",
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
        url: rootPath + "/action/S_composite_BlockFirst_delete.action",
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
        url: rootPath+"/action/S_composite_BlockFirst_list.action",
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
                title: '单位名称',
                field: 'orgName',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '姓名',
                field: 'principal',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '管辖网格',
                field: 'areaDesc',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '联系方式',
                field: 'principalPhone',
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
        //选中一条数据启用修改按钮
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
/**============表单初始化相关代码============**/
//初始化表单验证
var ef = form.easyform({
    success:function (ef) {
        var entity = $("#scfForm").find("form").formSerializeObject();
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
    $("#removeId").val("");
    $("#orgName").val(entity.orgName);
    $("#principal").val(entity.principal);
    $("#areaDesc").val(entity.areaDesc);
    $("#principalPhone").val(entity.principalPhone);
    $("#orgAddress").val(entity.orgAddress);
    $("#position").val(entity.position);
    $("#areaPoints").val(entity.areaPoints);
    uploader = new qq.FineUploader(getUploaderOptions(id));
}
function setFormView(entity) {
    setFormData(entity);
    form.find(".form-title").text("查看"+formTitle);
    disabledForm(true);
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
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
    form.find(".form-title").text("新增"+formTitle);
    form.find("input[type!='radio'][type!='checkbox']").val("");
    uploader = new qq.FineUploader(getUploaderOptions());
    disabledForm(false);
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

$(".scrollContent").slimScroll({
    height:"100%",
    railOpacity:.9,
    alwaysVisible:!1
});
var setting = {
    height:500,
    width:150,
    view: {
        showLine: false
    },
    async: {
        enable: true,
        url:rootPath + "/demo/ztree/ztreeData.json",
        autoParam:["id", "name", "level"],
        otherParam:{"otherParam":"zTreeAsyncTest"},
        dataFilter: filter
    }
};
function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    for (var i=0, l=childNodes.length; i<l; i++) {
        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
    }
    return childNodes;
}
$.fn.zTree.init($("#treeDemo1"), setting);