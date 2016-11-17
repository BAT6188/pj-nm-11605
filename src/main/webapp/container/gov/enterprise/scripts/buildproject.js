//@ sourceURL=buildproject.js
var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    buildForm = $('#buildForm');
hpform = $("#hpForm"),
    ysform = $("#ysForm"),
    buildFormTitle = "建设项目";
hpformTitle = "建设项目环评及其他许可情况",
    ysformTitle = "建设项目竣工环境保护验收审批信息",

    selections = [];

//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_composite_BuildProject_save.action",
        type: "post",
        data: entity,
        dataType: "json",
        success: callback
    });
}
//保存ajax请求
function saveYs(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_composite_ProjectAcceptance_save.action",
        type: "post",
        data: entity,
        dataType: "json",
        success: callback
    });
}

//保存ajax请求
function saveHp(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_composite_ProjectEIA_save.action",
        type: "post",
        data: entity,
        dataType: "json",
        success: callback
    });
}
/**
 * 删除请求
 * @param ids 多个,号分隔
 * @param callback
 */
function deleteAjax(ids, callback) {
    $.ajax({
        url: rootPath + "/action/S_composite_BuildProject_delete.action",
        type: "post",
        data: $.param({deletedId: ids}, true),//阻止深度序列化，向后台传递数组
        dataType: "json",
        success: callback
    });
}
/**============grid 列表初始化相关代码============**/
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination: "server",
        url: rootPath + "/action/S_composite_BuildProject_list.action",
        height: pageUtils.getTableHeight(),
        method: 'post',
        pagination: true,
        clickToSelect: true,//单击行时checkbox选中
        queryParams: function (param) {
            var temps = pageUtils.getBaseParams(param);
            temps.enterpriseId = id;
            return temps;
        },
        // queryParams:pageUtils.localParams,
        columns: [
            {
                title: "全选",
                checkbox: true,
                align: 'center',
                radio: false,  //  true 单选， false多选
                valign: 'middle'
            },
            {
                title: 'ID',
                field: 'id',
                align: 'center',
                valign: 'middle',
                sortable: false,
                visible: false
            },
            {
                title: '项目名称',
                field: 'name',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '行政区',
                field: 'area',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '批复时间',
                field: 'replyTime',
                sortable: false,
                align: 'center',
                editable: false,
                formatter: function (value, row, index) {
                    return pageUtils.sub10(value);
                }
            },
            {
                title: '建设性质',
                field: 'buildNature',
                sortable: false,
                align: 'center',
                editable: false,
                formatter: function (value, row, index) {
                    if (1 == value) {
                        value = "新建"
                    } else if (2 == value) {
                        value = "改扩建"
                    } else if (3 == value) {
                        value = "技术改造"
                    }
                    return value;
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
        //选中一条数据启用修改按钮
        updateBtn.prop('disabled', !(gridTable.bootstrapTable('getSelections').length == 1));
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
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal"  data-target="#buildForm">详情</button>'
        + '&nbsp;&nbsp;&nbsp;' + '<button id="add" type="button" class="btn btn-sm btn-success hp-btn" data-toggle="modal" data-target="#hpForm">环评</button>'
        + '&nbsp;&nbsp;&nbsp;' + '<button id="addYS" type="button" class="btn btn-sm btn-success ys-btn"  data-toggle="modal" data-target="#ysForm">验收</button>';

}
// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, row, index) {
        setFormView(row);
    },
    'click .hp-btn': function (e, value, row, index) {
        resetHPForm();
        disabledHP(true);
        if (!row) {return false}
        var inputs = hpform.find('.basedata');
        $.each(inputs, function (k, v) {
            var tagId = $(v).attr('name');
            var value = row[tagId];
            if (v.tagName == 'SELECT') {
                $(v).find("option[value='" + value + "']").attr("selected", true);
            } else if (v.tagName == 'DIV') {
                if (value)$(v).find("input." + tagId + value).get(0).checked = true;
            } else {
                $(v).val(value);
            }
        });
        $("#hp_projectId").val(row.id);
        setHPFormData(row.projectEIA)
    },
    'click .ys-btn': function (e, value, row, index) {
        resetYSForm();
        disabledYS(true);
        if (!row) {return false}
        var inputs = ysform.find('.basedata');
        $.each(inputs, function (k, v) {
            var tagId = $(v).attr('name');
            var value = row[tagId];
            if (v.tagName == 'SELECT') {
                $(v).find("option[value='" + value + "']").attr("selected", true);
            } else if (v.tagName == 'DIV') {
                if (value)$(v).find("input." + tagId + value).get(0).checked = true;
            } else {
                $(v).val(value);
            }
        });
        var input = ysform.find('.hpdata');
        $.each(input, function (k, v) {
            var tagId = $(v).attr('name');
            var value = (row.projectEIA)[tagId];
            if (v.tagName == 'SELECT') {
                $(v).find("option[value='" + value + "']").attr("selected", true);
            } else if (v.tagName == 'DIV') {
                if (value)$(v).find("input." + tagId + value).get(0).checked = true;
            } else {
                $(v).val(value);
            }
        });
        var buildinput = ysform.find('.builddata');
        $.each(buildinput, function (k, v) {
            var tagId = $(v).attr('name');
            var value = row[tagId];
            if (v.tagName == 'SELECT') {
                $(v).find("option[value='" + value + "']").attr("selected", true);
            } else if (v.tagName == 'DIV') {
                if (value)$(v).find("input." + tagId + value).get(0).checked = true;
            } else {
                $(v).val(value);
            }
        });
        $("#ys_projectId").val(row.id);
        setYSFormData(row.projectAcceptance)
    }
};
function disabledHP(disabled) {
    hpform.find(".basedata").attr("disabled", disabled);
}
function disabledYS(disabled) {
    ysform.find(".basedata").attr("disabled", disabled);
    ysform.find(".hpdata").attr("disabled", disabled);
}

/**
 * 获取列表所有的选中数据id
 * @returns {*}
 */
function getIdSelections() {
    return $.map(gridTable.bootstrapTable('getSelections'), function (row) {
        return row.id;
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

$("#add").bind('click', function () {
    resetForm();
});
$("#update").bind("click", function () {
    setFormData(getSelections()[0]);
});
/**
 * 列表工具栏 删除按钮
 */
removeBtn.click(function () {
    var ids = getIdSelections();
    Ewin.confirm({message: "确认要删除选择的数据吗？"}).on(function (e) {
        if (!e) {
            return;
        }
        deleteAjax(ids, function (msg) {
            gridTable.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
            removeBtn.prop('disabled', true);
        });
    });
});
//项目
function setFormData(entity) {
    resetForm();
    if (!entity) {
        return false
    }
    buildForm.find(".modal-title").text("修改" + buildFormTitle);
    var id = entity.id;
    var inputs = buildForm.find('.basedata');
    $.each(inputs, function (k, v) {
        var tagId = $(v).attr('name');
        var value = entity[tagId];
        if (v.tagName == 'SELECT') {
            $(v).find("option[value='" + value + "']").attr("selected", true);
        } else if (v.tagName == 'DIV') {
            if (value)$(v).find("input." + tagId + value).get(0).checked = true;
        } else {
            $(v).val(value);
        }
    });
    uploader = new qq.FineUploader(getUploaderOptions(id));
}
function setFormView(entity) {
    setFormData(entity);
    buildForm.find(".modal-title").text("查看" + buildFormTitle);
    disabledForm(true);
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#build-fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#build-fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text', "暂无附件信息");
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
    buildForm.find("#buildSave").hide();
    buildForm.find(".btn-cancel").text("关闭");
}
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

/**============表单初始化相关代码============**/

//初始化表单验证
var ef = buildForm.easyform({
    success: function (ef) {
        var entity = buildForm.find("form").formSerializeObject();
        entity.attachmentIds = getAttachmentIds();
        entity.enterpriseId = enterpriseId;
        saveAjax(entity, function (msg) {
            buildForm.modal('hide');
            gridTable.bootstrapTable('refresh');
        });
    }
});
//表单 保存按钮
$("#buildSave").bind('click', function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    ef.submit(false);
});

function resetForm() {
    buildForm.find(".modal-title").text("新增" + buildFormTitle);
    buildForm.find("input[type!='radio'][type!='checkbox']").val("");
    uploader = new qq.FineUploader(getUploaderOptions());
    disabledForm(false);
    buildForm.find("#buildSave").show();
    buildForm.find(".btn-cancel").text("取消");
}
function disabledForm(disabled) {
    buildForm.find(".form-control").attr("disabled", disabled);
    buildForm.find('input[type="radio"]').attr("disabled", disabled);
    if (!disabled) {
        //初始化日期组件
        buildForm.find('.form_date').datetimepicker({
            language: 'zh-CN',
            autoclose: 1,
            minView: 2
        });
    } else {
        buildForm.find('.form_date').datetimepicker('remove');
    }
}
//初始化日期组件
$('.searchInput').datetimepicker({
    language: 'zh-CN',
    autoclose: 1,
    minView: 2
});





var hp = hpform.easyform({
    success: function (ef) {
        var entity = hpform.find("form").formSerializeObject();
        entity.projectId  = $("#hp_projectId").val();
        entity.attachmentIds = gethpAttachmentIds();
        entity.id = $("#idEIA").val();
        saveHp(entity, function (msg) {
            hpform.modal('hide');
            gridTable.bootstrapTable('refresh');
        });
    }
});

var ys = ysform.easyform({
    success: function (ef) {
        var entity = ysform.find("form").formSerializeObject();
        entity.projectId = $("#ys_projectId").val();
        entity.attachmentIds = getysAttachmentIds();
        entity.id = $("#idAcceptance").val();
        saveYs(entity, function (msg) {
            ysform.modal('hide');
            gridTable.bootstrapTable('refresh');
        });
    }
});

function setHPFormData(entity) {
    if (!entity) {
        return false
    }
    hpform.find(".modal-title").text("修改" + hpformTitle);
    var id = entity.id;
    var inputs = hpform.find('.otherdata');
    $.each(inputs, function (k, v) {
        var tagId = $(v).attr('name');
        var value = entity[tagId];
        if (v.tagName == 'SELECT') {
            $(v).find("option[value='" + value + "']").attr("selected", true);
        } else if (v.tagName == 'DIV') {
            if (value)$(v).find("input." + tagId + value).get(0).checked = true;
        } else {
            $(v).val(value);
        }
    });
    hpUploader = new qq.FineUploader(getHPUploaderOptions(id));
}

function setYSFormData(entity) {
    if (!entity) {return false}
    ysform.find(".modal-title").text("修改" + hpformTitle);
    var id = entity.id;
    var inputs = ysform.find('.otherdata');
    $.each(inputs, function (k, v) {
        var tagId = $(v).attr('name');
        var value = entity[tagId];
        if (v.tagName == 'SELECT') {
            $(v).find("option[value='" + value + "']").attr("selected", true);
        } else if (v.tagName == 'DIV') {
            if (value)$(v).find("input." + tagId + value).get(0).checked = true;
        } else {
            $(v).val(value);
        }
    });
    ysUploader = new qq.FineUploader(getYSUploaderOptions(id));
}
function resetHPForm() {
    hpform.find(".modal-title").html("新增" + ysformTitle);
    hpform.find('form')[0].reset();
    hpUploader = new qq.FineUploader(getHPUploaderOptions());
    disabledHPForm(false);
    hpform.find(".saveButton").show();
    hpform.find(".btn-cancel").text("取消");
}
function resetYSForm() {
    ysform.find(".modal-title").html("新增" + hpformTitle);
    ysform.find('form')[0].reset();
    ysUploader = new qq.FineUploader(getYSUploaderOptions());
    disabledYSForm(false);
    ysform.find(".saveButton").show();
    ysform.find(".btn-cancel").text("取消");
}

function disabledHPForm(disabled) {
    hpform.find(".form-control").attr("disabled", disabled);
    hpform.find('input[type="radio"]').attr("disabled", disabled);
    if (!disabled) {
        //初始化日期组件
        hpform.find('.form_date').datetimepicker({
            language: 'zh-CN',
            autoclose: 1,
            minView: 2
        });
    } else {
        hpform.find('.form_date').datetimepicker('remove');
    }
}
function disabledYSForm(disabled) {
    ysform.find(".form-control").attr("disabled", disabled);
    ysform.find('input[type="radio"]').attr("disabled", disabled);
    if (!disabled) {
        //初始化日期组件
        ysform.find('.form_date').datetimepicker({
            language: 'zh-CN',
            autoclose: 1,
            minView: 2
        });
    } else {
        ysform.find('.form_date').datetimepicker('remove');
    }
}

/*//初始化表单验证
var hp = hpform.easyform({
    success: function (ef) {
        var id=getIdSelections()[0];
        var entity = hpform.find("form").formSerializeObject();
        entity.attachmentIds = gethpAttachmentIds();
        entity.enterpriseId = enterpriseId;
        entity.type = 1;
        var hpxx = getSerialize(hpform, '.otherdata', entity);
        hpxx.id = $("#idEIA").val();
        hpxx.projectId = id;
            saveHp(hpxx, function (msg) {
            });
            hpform.modal('hide');
            gridTable.bootstrapTable('refresh');
    }
});
//初始化表单验证
var ys = ysform.easyform({
    success: function (ef) {
        var id=getIdSelections()[0];
        var entity = ysform.find("form").formSerializeObject();
        entity.attachmentIds = getysAttachmentIds();
        entity.enterpriseId = enterpriseId;
        entity.type = 2;
        var ysxx = getSerialize(ysform, '.otherdata', entity);
        ysxx.id = $("#idAcceptance").val();
            ysxx.projectId = id;
            saveYs(ysxx, function (msg) {
            });
            ysform.modal('hide');
            gridTable.bootstrapTable('refresh');
    }
});*/

//表单 保存按钮
$("#hpsave").bind('click', function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    hp.submit(false);
});
//表单 保存按钮
$("#ysSave").bind('click', function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    ys.submit(false);
});

/**
 * 设置表单数据
 * @param entity
 * @returns {boolean}
 */

// 重置表


/*function setBuildFormData(entity) {
    if (!entity) {
        return false
    }
    var otherData;
    if (entity.type == 2) {
        otherData = entity.projectAcceptance;
    } else {
        otherData = entity.projectEIA;
    }
    dataForm.find(".modal-title").html("修改" + formTitle);
    /!*其他信息设置*!/
    var otherInputs = dataForm.find('.otherdata');
    if (otherData)setInputData(otherInputs, otherData);
    var id = entity.id;
    hpUploader = new qq.FineUploader(getHPUploaderOptions(id));
    ysUploader = new qq.FineUploader(getYSUploaderOptions(id));
}
function setInputData(inputs, data) {
    $.each(inputs, function (k, v) {
        var tagId = $(v).attr('name');
        var value = data[tagId];
        if (v.tagName == 'SELECT') {
            $(v).find("option[value='" + value + "']").attr("selected", true);
        } else if (v.tagName == 'DIV') {
            if (value)$(v).find("input." + tagId + value).get(0).checked = true;
        } else {
            $(v).val(value);
        }
    });
}
var dataForm;
var formTitle;
function setTM(type) {
    if (type == 2) {
        formTitle = ysformTitle;
        dataForm = ysform;
    } else {
        formTitle = hpformTitle;
        dataForm = hpform;
    }
}
function setBuildFormView(entity) {
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#build-fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#build-fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text', "暂无附件信息");
    };
    uploader = new qq.FineUploader(fuOptions);

    setTM(entity.type);
    setBuildFormData(entity);
    dataForm.find(".modal-title").text("查看" + formTitle);
    disabledBuildForm(true);
    var fuOptions = getHPUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
    };
    var fuOption = getYSUploaderOptions(entity.id);
    fuOption.callbacks.onSessionRequestComplete = function () {
        $("#ys-fine-uploader-gallery").find(".qq-upload-delete").hide();
    };
    hpUploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
    $("#fine-uploader-gallery").find('.qq-uploader-selector').attr('qq-drop-area-text', '暂无上传的附件');

    ysUploader = new qq.FineUploader(fuOption);
    $(".qq-upload-button").hide();
    $("#ys-fine-uploader-gallery").find('.qq-uploader-selector').attr('qq-drop-area-text', '暂无上传的附件');

    dataForm.find("#ysSave").hide();
    dataForm.find("#hpsave").hide();
    dataForm.find(".btn-cancel").text("关闭");
}*/

//表单附件相关js
var uploader;//项目上传组件对象
var ysUploader;//项目上传组件对象
var hpUploader;
function getYSUploaderOptions(bussinessId) {
    return {
        element: document.getElementById("ys-fine-uploader-gallery"),
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
            onComplete: function (id, fileName, msg, request) {
                ysUploader.setUuid(id, msg.id);
            },
            onDeleteComplete: function (id) {
                var file = ysUploader.getUploads({id: id});
                var removeIds = $("#removeId").val();
                if (removeIds) {
                    removeIds += ("," + file.uuid)
                } else {
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
                businessId: bussinessId
            }
        },
        session: {
            endpoint: rootPath + '/action/S_attachment_Attachment_listAttachment.action',
            params: {
                businessId: bussinessId
            }
        },
        deleteFile: {
            enabled: true,
            endpoint: rootPath + "/action/S_attachment_Attachment_delete.action",
            method: "POST"
        },
        validation: {
            itemLimit: 5
        },
        debug: true
    };
}

function getHPUploaderOptions(bussinessId) {
    return {
        element: document.getElementById("hp-fine-uploader-gallery"),
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
            onComplete: function (id, fileName, msg, request) {
                hpUploader.setUuid(id, msg.id);
            },
            onDeleteComplete: function (id) {
                var file = hpUploader.getUploads({id: id});
                var removeIds = $("#removeId").val();
                if (removeIds) {
                    removeIds += ("," + file.uuid)
                } else {
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
                businessId: bussinessId
            }
        },
        session: {
            endpoint: rootPath + '/action/S_attachment_Attachment_listAttachment.action',
            params: {
                businessId: bussinessId
            }
        },
        deleteFile: {
            enabled: true,
            endpoint: rootPath + "/action/S_attachment_Attachment_delete.action",
            method: "POST"
        },
        validation: {
            itemLimit: 5
        },
        debug: true
    };
}
/**
 * 获取上传组件options
 * @param bussinessId
 * @returns options
 */
function getUploaderOptions(bussinessId) {
    return {
        element: document.getElementById("build-fine-uploader-gallery"),
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
            onComplete: function (id, fileName, msg, request) {
                uploader.setUuid(id, msg.id);
            },
            onDeleteComplete: function (id) {
                var file = uploader.getUploads({id: id});
                var removeIds = $("#removeId").val();
                if (removeIds) {
                    removeIds += ("," + file.uuid)
                } else {
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
                businessId: bussinessId
            }
        },
        session: {
            endpoint: rootPath + '/action/S_attachment_Attachment_listAttachment.action',
            params: {
                businessId: bussinessId
            }
        },
        deleteFile: {
            enabled: true,
            endpoint: rootPath + "/action/S_attachment_Attachment_delete.action",
            method: "POST"
        },
        validation: {
            itemLimit: 5
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
        for (var i = 0; i < attachments.length; i++) {
            ids.push(attachments[i].uuid);
        }
        return ids.join(",");
    }
    return "";
}
function getysAttachmentIds() {
    var attachments = ysUploader.getUploads();
    if (attachments && attachments.length) {
        var ids = [];
        for (var i = 0; i < attachments.length; i++) {
            ids.push(attachments[i].uuid);
        }
        return ids.join(",");
    }
    return "";
}
function gethpAttachmentIds() {
    var attachments = hpUploader.getUploads();
    if (attachments && attachments.length) {
        var ids = [];
        for (var i = 0; i < attachments.length; i++) {
            ids.push(attachments[i].uuid);
        }
        return ids.join(",");
    }
    return "";
}

/**
 * 绑定下载按钮事件
 */
$("#build-fine-uploader-gallery").on('click', '.qq-upload-download-selector', function () {
    var uuid = uploader.getUuid($(this.closest('li')).attr('qq-file-id'));
    window.location.href = rootPath + "/action/S_attachment_Attachment_download.action?id=" + uuid;
});
$("#ys-fine-uploader-gallery").on('click', '.qq-upload-download-selector', function () {
    var uuid = ysUploader.getUuid($(this.closest('li')).attr('qq-file-id'));
    window.location.href = rootPath + "/action/S_attachment_Attachment_download.action?id=" + uuid;
});
$("#hp-fine-uploader-gallery").on('click', '.qq-upload-download-selector', function () {
    var uuid = hpUploader.getUuid($(this.closest('li')).attr('qq-file-id'));
    window.location.href = rootPath + "/action/S_attachment_Attachment_download.action?id=" + uuid;
});
initZTree();
/*初始化 树结构*/
$(".scrollContent").slimScroll({
    height: "100%",
    railOpacity: .9,
    alwaysVisible: !1
});
function initZTree() {
    var treeCode = ['industryType'];
    $.each(treeCode, function (k, v) {
        var setting = {
            data: {
                simpleData: {
                    enable: true,
                    idKey: "code",
                    pIdKey: "parentCode",
                    rootPId: -1
                }
            },
            height: 500,
            width: 200,
            view: {
                showLine: false
            },
            async: {
                enable: true,
                url: rootPath + "/S_dict_Dict_multipleList.action",
                autoParam: ["code"],
                otherParam: {"code": v},
                dataFilter: null
            },
            callback: {
                onDblClick: function (event, treeId, treeNode) {
                    if (treeNode.check_Child_State == -1) {
                        $('#' + v).val(treeNode.name);
                        $('#' + v + 'ModalClose').trigger('click');
                    }
                }
            }
        };
        var zTree = $.fn.zTree.init($('#' + v + 'Tree'), setting);
        $('#' + v + 'ModalSure').click(function () {
            var nodes = zTree.getSelectedNodes();
            var selectNode = nodes[0];
            if (selectNode.check_Child_State == -1) {
                $('#' + v).val(selectNode.name);
                $('#' + v + 'ModalClose').trigger('click');
            }
        })
    })
}
var searInputs = ysform.find('.searchName');
console.log(searInputs);
$(function () {
    searInputs.autocomplete({
        select: function (e, item) {
            console.log(item.item.label);
            console.log(item.item.value);
            var projectId = item.item.value;
            $.ajax({
                url: rootPath + "/action/S_composite_BuildProject_findByBuildId.action",
                dataType: "json",
                type: 'post',
                data: {projectId: projectId},
                success: function (msg) {
                    $('#eiaNameEIA').empty();
                    $('#euNameEIA').empty();
                    $('#replyCodeEIA').empty();
                    $('#replyTimeEIA').empty();
                    for (var i = 0; i < msg.length; i++) {
                        console.log(msg[i].euName);
                        console.log(msg[i].replyEIACode);
                        console.log(msg[i].replyEIATime);
                        $("#eiaNameEIA").val(item.item.label);
                        $("#euNameEIA").val(msg[i].euName);
                        $("#replyCodeEIA").val(msg[i].replyEIACode);
                        $("#replyTimeEIA").val(msg[i].replyEIATime);
                    }
                }
            });
        },
        source: function (request, response) {
            $.ajax({
                url: rootPath + "/action/S_composite_BuildProject_findByName.action",
                dataType: "json",
                type: 'post',
                data: {
                    name: request.term
                },
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        // console.log(data[i].name);
                        var result = [];
                        for (var i = 0; i < data.length; i++) {
                            var item = {
                                label: data[i].name,
                                value: data[i].id
                            };
                            result.push(item);
                        }
                        response(result);
                    }
                }
            });
        }
    });
});


