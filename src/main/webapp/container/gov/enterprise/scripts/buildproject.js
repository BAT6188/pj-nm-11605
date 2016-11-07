//@ sourceURL=buildproject.js
var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    hpform = $("#hpForm"),
    ysform = $("#ysForm"),
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
        url: rootPath + "/action/S_composite_BuildProject_saveYs.action",
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
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" >详情</button>';
}
// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, row, index) {
        var id=row.id;
        if (row.type == "1") {//环评
            //打开环评表单
            $("#hpForm").modal("show");
            //查询环评数据
            setFormView(row);
        }else{
            //打开验收表单
            setFormView(row);
            $("#ysForm").modal("show");
        }
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

$("#add").bind('click', function () {
    setTM(1);
    resetForm();
});
$("#addYS").bind('click', function () {
    setTM(2);
    resetForm();
});
$("#update").bind("click", function () {
    // setFormData(getSelections()[0]);
    var entity = getSelections()[0];
    setTM(entity.type);
    disabledForm(true);
    setFormData(entity);
    if(entity.type==2){
        $("#ysForm").modal("show");
    }else{
        $("#hpForm").modal("show");
    }
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


/**============列表搜索相关处理============**/
//搜索按钮处理
$("#search").click(function () {
    //查询之前重置table
    gridTable.bootstrapTable('resetSearch');
    var jsonData = $('#searchform').formSerializeObject();
    gridTable.bootstrapTable('refresh', {
        query: jsonData
    });
});
function getSerialize(form,type,allMap){
    var map={};
    var needSaves = $(form).find(type);
    if(needSaves.length>0){
        $.each(needSaves,function(k,v){
            var name = v.name;
            if(allMap[name]) map[name] = allMap[name];
        })
    }
    return map;
}
/**============表单初始化相关代码============**/
//初始化表单验证
var hp = hpform.easyform({
    success: function (ef) {
        var entity = hpform.find("form").formSerializeObject();
        entity.attachmentIds = getAttachmentIds();
        entity.enterpriseId = enterpriseId;
        entity.type = 1;
        console.log("基本信息：")
        console.log(getSerialize(hpform,'.basedata',entity));
        console.log("环评信息：")
        console.log(getSerialize(hpform,'.otherdata',entity));
        saveAjax(entity, function (msg) {
            hpform.modal('hide');
            gridTable.bootstrapTable('refresh');
        });
    }
});
//初始化表单验证
var ys = ysform.easyform({
    success: function (ef) {
        var entity = ysform.find("form").formSerializeObject();
        entity.attachmentIds = getAttachmentIds();
        entity.enterpriseId = enterpriseId;
        entity.type = 2;
        saveYs(entity, function (msg) {
            ysform.modal('hide');
            gridTable.bootstrapTable('refresh');
        });
    }
});

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
function setFormData(entity) {
    resetForm();
    if (!entity) {
        return false
    }
    var otherData;
    if(entity.type==2){
        otherData = entity.projectAcceptance;
    }else{
        otherData = entity.projectEIA;
    }
    dataForm.find(".modal-title").html("修改" + formTitle);
    /*基本信息设置*/
    var baseInputs = dataForm.find('.basedata');
    setInputData(baseInputs,entity);

    /*其他信息设置*/
    var otherInputs = dataForm.find('.otherdata');
    if(otherData)setInputData(otherInputs,otherData);

    var id = entity.id;
    uploader = new qq.FineUploader(getUploaderOptions(id));
}
function setInputData(inputs,data){
    $.each(inputs, function (k, v) {
        var tagId = $(v).attr('name');
        var value = data[tagId];
        if (v.tagName == 'SELECT') {
            $(v).find("option[value='" + value + "']").attr("selected", true);
        } else if(v.tagName == 'DIV'){
            if(value)$(v).find("input." + tagId + value).get(0).checked = true;
        }else{
            $(v).val(value);
        }
    });
}
var dataForm;
var formTitle;
function setTM(type){
    if(type==2){
        formTitle = ysformTitle;
        dataForm = ysform;
    }else{
        formTitle = hpformTitle;
        dataForm = hpform;
    }
}
function setFormView(entity) {
    setTM(entity.type);
    setFormData(entity);
    dataForm.find(".modal-title").text("查看" + formTitle);
    disabledForm(true);
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
    $("#fine-uploader-gallery").find('.qq-uploader-selector').attr('qq-drop-area-text', '暂无上传的附件');
    dataForm.find("#ysSave").hide();
    dataForm.find(".btn-cancel").text("关闭");
}
function disabledForm(disabled) {
    dataForm.find(".form-control").attr("disabled", disabled);
    dataForm.find('input[type="radio"]').attr("disabled", disabled);
    if (!disabled) {
        //初始化日期组件
        dataForm.find('.form_date').datetimepicker({
            language: 'zh-CN',
            autoclose: 1,
            minView: 2
        });
    } else {
        dataForm.find('.form_date').datetimepicker('remove');
    }
}

//初始化日期组件
$('.searchInput').datetimepicker({
    language: 'zh-CN',
    autoclose: 1,
    minView: 2
});
/*
//初始化日期组件
$('.end_Time').datetimepicker({
    language: 'zh-CN',
    autoclose: 1,
    minView: 2
});
$('#TimeContent').datetimepicker({
    language: 'zh-CN',
    autoclose: 1,
    minView: 2
});
$('#acceptTimeContent').datetimepicker({
    language: 'zh-CN',
    autoclose: 1,
    minView: 2
});
$('#replyTime_Content').datetimepicker({
    language: 'zh-CN',
    autoclose: 1,
    minView: 2
});
$('#replyTimeContent').datetimepicker({
    language: 'zh-CN',
    autoclose: 1,
    minView: 2
});
*/
/**
 * 重置表单
 */
function resetForm() {
    dataForm.find(".modal-title").html("新增" + formTitle);
    dataForm.find('form')[0].reset();
    uploader = new qq.FineUploader(getUploaderOptions());
    disabledForm(false);
    dataForm.find(".saveButton").show();
    dataForm.find(".btn-cancel").text("取消");
}

/*function setHPFormView(entity) {
    setFormData(entity);
    hpform.find(".form-title").text("查看" + hpformTitle);
    hpdisabledForm(true);
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text', "");
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
    hpform.find("#hpsave").hide();
    hpform.find(".btn-cancel").text("关闭");
}*/

/**
 * 重置表单
 */
/*function resetHPForm() {
    hpform.find(".model-title").text("新增" + hpformTitle);
    hpform.find("input[type!='radio'][type!='checkbox'],textarea").val("");
    uploader = new qq.FineUploader(getUploaderOptions());
    hpdisabledForm(false);
}*/

/*function hpdisabledForm(disabled) {
    //hpform.find("input").attr("disabled", disabled);
    if (!disabled) {
        //初始化日期组件
        $('.form_date').datetimepicker({
            language: 'zh-CN',
            autoclose: 1,
            minView: 2
        });
        /!*$('#acceptTime').datetimepicker({
            language: 'zh-CN',
            autoclose: 1,
            minView: 2
        });*!/
    } else {
        $('.form_date').datetimepicker('remove');
        //$('#acceptTime').datetimepicker('remove');
    }
}*/


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

/**
 * 绑定下载按钮事件
 */
$("#fine-uploader-gallery").on('click', '.qq-upload-download-selector', function () {
    var uuid = uploader.getUuid($(this.closest('li')).attr('qq-file-id'));
    window.location.href = rootPath + "/action/S_attachment_Attachment_download.action?id=" + uuid;
});


