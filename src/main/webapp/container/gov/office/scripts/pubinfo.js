//@ sourceURL=pubinfo.js
var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    pub = $('#pub'),
    form = $("#scfForm"),
    formTitle = "信息公告",
    selections = [];

//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_office_PubInfo_save.action",
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
        url: rootPath + "/action/S_office_PubInfo_delete.action",
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
        url: rootPath + "/action/S_office_PubInfo_list.action?orgCode=" + orgCode,
        height: pageUtils.getTableHeight() - 45,
        method: 'post',
        pagination: true,
        clickToSelect: true,//单击行时checkbox选中
        queryParams: pageUtils.localParams,
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
                title: '标题',
                field: 'title',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '类型',
                field: 'type',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '发布部门',
                field: 'pubOrgName',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '发布时间',
                field: 'pubTime',
                sortable: false,
                align: 'center',
                editable: false,
                formatter: function (value, row, index) {
                    return pageUtils.sub10(value);
                }
            },
            {
                field: 'status',
                title: '发布状态',
                sortable: false,
                align: 'center',
                editable: false,
                formatter: function (value, row, index) {
                    switch (value) {
                        case "1":
                            return '<span style="color: green;">已发布</span>';
                            break;
                        default:
                            return '<span style="color: red;">未发布</span>';
                    }
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

        // pub.prop('disabled', !(gridTable.bootstrapTable('getSelections').length== 1));
        pub.prop('disabled', !(gridTable.bootstrapTable('getSelections').length == 1));
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
    return '<button type="button" class="btn btn-md btn-warning view"  data-id="' + row.id + '" data-toggle="modal" data-target="#scfForm">查看</button>';
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
// pub.prop('disabled', true);
/**
 * 列表工具栏 新增和更新按钮打开form表单，并设置表单标识
 */
$("#add").bind('click', function () {
    resetForm();
    $('#pub').attr('disabled', false);
    $("#pubOrgName").attr("disabled", true)

});
$("#update").bind("click", function () {
    var entity = getSelections()[0];
    if (entity.status == 1) {
        $('#pub').attr('disabled', true);
    } else {
        $('#pub').attr('disabled', false);
    }
    setFormData(entity);

    $("#pubOrgName").attr("disabled", true)
});
$("#pub").bind("click", function () {
    ef2.submit(false);
});
function pubInfo(id) {
    $.ajax({
        url: rootPath + "/action/S_office_PubInfo_pubsave.action",
        type: "post",
        dataType: 'json',
        data: {id: id},
        success: function (msg) {
            form.modal('hide');
            gridTable.bootstrapTable('refresh');
            var receivers = [];
            if (msg && msg.persons && msg.persons.length> 0) {
                var receivers = [];
                $.each(msg.persons, function (i, v) {
                    var receiver1 = {receiverId: v.userId, receiverName: v.userName};
                    receivers.push(receiver1);
                });
            }
            if(msg && msg.pubInfos && msg.pubInfos.length> 0){
                var msg = {
                    'msgType':3,
                    'title':msg.pubInfos.createModeName,
                    'content':msg.pubInfos.content,
                    'businessId':msg.pubInfos.id
                }
            }
                pageUtils.sendMessage(msg, receivers);
            }
    })
}
var ef2 = form.easyform({
    success: function (ef2) {
        Ewin.confirm({message: "是否发布信息"}).on(function (e) {
            if (!e) {
                return;
            } else {
                var entity = $("#scfForm").find("form").formSerializeObject();
                entity.attachmentIds = getAttachmentIds();
                entity.pubOrgName = $("#pubOrgName").val();
                if (entity.grade) {
                    if ($.isArray(entity.grade)) {//判断是否是数组
                        entity.grade = entity.grade.join(",");
                    }
                }
                saveAjax(entity, function (pub) {
                    form.modal('hide');
                    gridTable.bootstrapTable('refresh');
                    pubInfo(pub.id);
                    form.modal('hide');
                });
            }
        })

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
//搜索按钮处理
$("#search").click(function () {
    gridTable.bootstrapTable('refreshOptions', {pageNumber: 1, pageSize: pageUtils.PAGE_SIZE});
});
//重置搜索
$("#searchFix").click(function () {
    $('#searchform')[0].reset();
    gridTable.bootstrapTable('refreshOptions', {pageNumber: 1, pageSize: pageUtils.PAGE_SIZE});
});
/**============表单初始化相关代码============**/

var ef = form.easyform({
    success: function (ef) {
        var entity = $("#scfForm").find("form").formSerializeObject();
        entity.attachmentIds = getAttachmentIds();
        entity.pubOrgName = $("#pubOrgName").val();
        if (entity.grade) {
            if ($.isArray(entity.grade)) {//判断是否是数组
                entity.grade = entity.grade.join(",");
            }
        }
        saveAjax(entity, function (pub) {
            form.modal('hide');
            gridTable.bootstrapTable('refresh');
        });
    }
});

//表单 保存按钮
$("#save").bind('click', function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    ef.submit(false);
});
//初始化日期组件
$('.form_datetime').datetimepicker({
    language: 'zh-CN',
    autoclose: 1,
    minView: 2
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
    form.find(".form-title").text("修改" + formTitle);
    var id = entity.id;
    $("#id").val(entity.id);
    $("#removeId").val("");
    $("#title").val(entity.title);
    $("#pubTime").val(pageUtils.sub10(entity.pubTime));
    $("#pubOrgName").val(entity.pubOrgName);
    $("#pubOrgId").val(entity.pubOrgId);
    $("#userID").val(entity.userID);
    $("#userName").val(entity.userName);
    $("#status").val(entity.status);
    $("#type").val(entity.type);
    if (entity.grade) {
        var gradeArray = entity.grade.split(",");//
        for (var i = 0; i < gradeArray.length; i++) {
            var orgCode = gradeArray[i];
            $('input[name="grade"][value="' + orgCode + '"]').prop("checked", true);
        }
    }
    $("#content").val(entity.content);
    /*    if(entity.status == 1){
     form.find('#pub').hide();
     }else{
     form.find('#pub').show();
     }*/
    uploader = new qq.FineUploader(getUploaderOptions(id));
}

function setFormView(entity) {
    setFormData(entity);
    if (entity.status == 1) {
        $('#pub').attr('disabled', true);
    } else {
        $('#pub').attr('disabled', false);
    }
    form.find(".form-title").text("查看" + formTitle);
    disabledForm(true);
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text', "暂无附件信息");
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
    form.find("#pub").hide();
    form.find("#save").hide();
    form.find(".btn-cancel").text("关闭");
}

function disabledForm(disabled) {
    form.find("input").attr("disabled", disabled);
    form.find("textarea").attr("disabled", disabled);
    form.find("select").attr("disabled", disabled);
    if (!disabled) {
        //初始化日期组件
        $('.form_date').datetimepicker({
            language: 'zh-CN',
            autoclose: 1,
            minView: 2
        });
    } else {
        $('.form_date').datetimepicker('remove');
    }

}

/**
 * 重置表单
 */
function resetForm() {
    form.find(".form-title").text("新增" + formTitle);
    form.find("input[type!='radio'][type!='checkbox']").val("");
    $("textarea").val("");
    uploader = new qq.FineUploader(getUploaderOptions());
    $("#pubOrgName").val(orgName);
    $("#pubOrgId").val(orgCode);
    $("#userID").val(userId);
    $("#userName").val(userName);
    $("#status").val(0);

    // $("#grade").val("");
    orgOption();
    disabledForm(false);
    form.find('#pub').show();
    form.find("#save").show();
    form.find(".btn-cancel").text("取消");
}

function orgOption() {
    $.ajax({
        url: rootPath + "/action/S_office_PubInfo_findOrg.action",
        type: "post",
        async: false,
        dataType: "json",
        success: function (org) {
            $('#grade').empty();
            for (var i = 0; i < org.length; i++) {
                // $('#grade').append("<option value='" + msg[i].orgCode + "'>" + msg[i].orgName + "</option>")
                $('#grade').append("<label><input type='checkbox' name='grade' value='" + org[i].orgCode + "' >" + org[i].orgName + "</label>&nbsp;&nbsp;")

            }
        }
    })
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

