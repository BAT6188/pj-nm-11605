var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#scfForm"),
    formTitle = "环保手册",
    selections = [];

//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_office_Manual_save.action",
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
        url: rootPath + "/action/S_office_Manual_delete.action",
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
        url: rootPath + "/action/S_office_Manual_list.action",
        height: pageUtils.getTableHeight(),
        method: 'post',
        pagination: true,
        clickToSelect: true,//单击行时checkbox选中
        // queryParams:pageUtils.localParams,
        queryParams: function (param) {
            var temps = pageUtils.getBaseParams(param);
            temps.pid = ztreeId;
            return temps;
        },
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
                title: '文件名称',
                field: 'fileName',
                editable: false,
                sortable: false,
                align: 'center'
            }, {
                title: '颁布单位',
                field: 'enactOrgName',
                editable: false,
                sortable: false,
                align: 'center'
            }, {
                title: '颁布时间',
                field: 'pubTime',
                editable: false,
                sortable: false,
                align: 'center'
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
    }, 200)

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
    deleteAjax(ids, function (msg) {
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
    var queryParams = {};
    var name = $("#s_fileName").val();
    var type = $("#s_type").val();
    if (name) {
        queryParams["name"] = name;
    }
    if (type) {
        queryParams["type"] = type;
    }
    gridTable.bootstrapTable('refresh', {
        query: queryParams
    });
});

/**============表单初始化相关代码============**/

//初始化表单验证
var ef = form.easyform({
    success: function (ef) {
        var entity = form.find("form").formSerializeObject();
        entity.attachmentIds = getAttachmentIds();
        entity.pid = ztreeId;
        saveAjax(entity, function (msg) {
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
    $("#fileName").val(entity.fileName);
    $("#createTime").val(pageUtils.sub10(entity.createTime));
    $("#type").val(entity.type);
    $("#level").val(entity.level);
    $("#enactOrgName").val(entity.enactOrgName);
    $("#pubTime").val(pageUtils.sub10(entity.pubTime));
    $("#content").val(entity.content);
    $("#fitRange").val(entity.fitRange);
    $("#remark").val(entity.remark);

    uploader = new qq.FineUploader(getUploaderOptions(id));
}
function setFormView(entity) {
    setFormData(entity);
    form.find(".form-title").text("查看" + formTitle);
    disabledForm(true);
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无附件信息");
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
    form.find("#save").hide();
    form.find(".btn-cancel").text("关闭");
}
function disabledForm(disabled) {
    form.find("input").attr("disabled", disabled);
    form.find("textarea").attr("disabled", disabled);
    form.find("select").attr("disabled", disabled);
    if (!disabled) {
        //初始化日期组件
        $('#pubTimeContent').datetimepicker({
            language: 'zh-CN',
            autoclose: 1,
            minView: 2
        });
        $('#createTimeContent').datetimepicker({
            language: 'zh-CN',
            autoclose: 1,
            minView: 2
        });
    } else {
        $('#createTimeContent').datetimepicker('remove');
        $('#pubTimeContent').datetimepicker('remove');
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
    CotalogOption(manualId);
    disabledForm(false);
    form.find("#save").show();
    form.find(".btn-cancel").text("取消");
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

/*$(".scrollContent").slimScroll({
    height: "100%",
    railOpacity: .9,
    alwaysVisible: !1
});*/
var ztreeId;
var ztreeNode;
var setting = {
    view: {
        showLine: true
    },
    data: {
        keep: {
            leaf: true
        }
    },
    check: {
        enable: true
    },
    async: {
        enable: true,
        url: rootPath + "/action/S_office_ManualCatalog_getManualCatalogTree.action",
        autoParam: ["id"]
    },
    callback: {
        onAsyncSuccess: zTreeOnAsyncSuccess,
        onClick: zTreeOnClick
    }
};
var ztree = $.fn.zTree.init($("#manualZTree"), setting);

//删除节点
$("#deleteZTree").bind('click', function () {
    var treeObj = $.fn.zTree.getZTreeObj("manualZTree");
    var nodes = treeObj.getSelectedNodes();
    if (nodes.length > 0) {
        var node = nodes[0];
        var selectId = node.id;
        if (node != null && node.isParent && node.id == "root") {
            Ewin.alert({message: "根目录不能删除！"}).on(function (e) {
                if (!e) {
                    return;
                }
            });
        }else if (node.id == null) {
            Ewin.alert({message: "请选择数据"}).on(function (e) {
                if (!e) {
                    return;
                }
            });
        } else if (node.isParent && node.children.length > 0) {
            Ewin.alert({message: "不能删除！"}).on(function (e) {
                if (!e) {
                    return;
                }
            });
        }else if(node.isParent){
            $.ajax({
                url: rootPath + "/action/S_office_Manual_findByZtreeId.action",
                type: "post",
                data: {selectId: selectId},
                dataType: "json",
                success: function (msg) {
                    if (msg.length > 0) {//有数据不能删除该数据
                        Ewin.alert({message: "不能删除该数据"}).on(function (e) {
                            if (!e) {
                                return;
                            }
                        });
                    }else {//
                        $.ajax({
                            url: rootPath + "/action/S_office_ManualCatalog_delete.action",
                            type: "post",
                            data: {deletedId: ztreeId},
                            dataType: "json",
                            success: function () {
                                zTreeOnAsyncSuccess();
                            }
                        });
                    }
                }
            });
        } else if (node.isParent == false) {
            $.ajax({
                url: rootPath + "/action/S_office_Manual_findByZtreeId.action",
                type: "post",
                data: {selectId: selectId},
                dataType: "json",
                success: function (msg) {
                    if (msg.length > 0) {//有数据不能删除该数据
                        Ewin.alert({message: "不能删除该数据"}).on(function (e) {
                            if (!e) {
                                return;
                            }
                        });
                    }else {//
                        $.ajax({
                            url: rootPath + "/action/S_office_ManualCatalog_delete.action",
                            type: "post",
                            data: {deletedId: ztreeId},
                            dataType: "json",
                            success: function () {
                                zTreeOnAsyncSuccess();
                            }
                        });
                    }
                }
            });
        }else {
            $.ajax({
                url: rootPath + "/action/S_office_ManualCatalog_delete.action",
                type: "post",
                data: {deletedId: ztreeId},
                dataType: "json",
                success: function () {
                    zTreeOnAsyncSuccess();
                }
            });
        }
    }
});

//增加节点
$("#addZTree").bind('click', function () {
    $("#name").val("");
    $('#addNodes').modal('show');

});
//添加节点
$("#saveNodes").bind('click', function () {
    var manualId;
    if(ztreeNode.isParent==false){
        var node=ztreeNode.getParentNode();
        manualId=node.id;
    }else{
        manualId = ztreeId;
    }
    var name = $("#name").val();
    $.ajax({
        url: rootPath + "/action/S_office_ManualCatalog_save.action",
        type: "post",
        data: {manualId: manualId, name: name},
        dataType: "json",
        success: function (msg) {
            zTreeOnSuccess();
        }
    });
    $('#addNodes').modal('hide');
});

//默认加载第一个节点
var firstAsyncSuccessFlag = 0;
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
    var treeObj = $.fn.zTree.getZTreeObj("manualZTree");
    //调用默认展开第一个节点
    if (firstAsyncSuccessFlag == 0) {
        //获取全部节点数据
        var nodes = treeObj.getNodes();
        //展开当前选择的第一个节点
        treeObj.expandNode(nodes[0], true);
        //. 单独选中根节点中第一个节点
        treeObj.selectNode(nodes[0]);
        firstAsyncSuccessFlag = 1;
        //获去第一个节点id
        ztreeId = nodes[0].id;
    }
    if(ztreeId=="root"){
        $("#add").prop('disabled', true);
    }
    //刷新表格列表
    gridTable.bootstrapTable('refresh');
}

//点击节点事件
function zTreeOnClick(event, treeId, treeNode) {
    if (treeNode != null) {
        ztreeId = treeNode.id;
        ztreeName = treeNode.name;
        ztreeNode = treeNode;

        gridTable.bootstrapTable('refresh');

        if(ztreeId=="root"){
            $("#add").prop('disabled', true);
        }else{
            $("#add").prop('disabled', false);
        }
    }
    zTreeOnSuccess();
    selectZreeId();
}
//右侧列表添加数据刷新zTree
function zTreeOnSuccess() {
    var treeObj = $.fn.zTree.getZTreeObj("manualZTree");
    var node = treeObj.getSelectedNodes();
    if (node.length > 0) {
            //. 重新异步加载当前选中的第一个节点
        treeObj.reAsyncChildNodes(node[0], "refresh");
    }
}

//获取树name信息
var manualId;
function selectZreeId() {
    var treeObj = $.fn.zTree.getZTreeObj("manualZTree");
    var sNodes = treeObj.getSelectedNodes();
    //获得当前节点的下级节点
    if (sNodes.length > 0) {
        var node = sNodes[0];
        if (node.id == "root") {
            return;
        } else if (node.isParent == true) {
            manualId = node.id
        } else {
            var nodez = node.getParentNode();
            manualId = nodez.id;
        }
    }
}


//默认加载表单select赋值
function CotalogOption(manualId) {
    $.ajax({
        url: rootPath + "/action/S_office_ManualCatalog_findByZtreeId.action",
        type: "post",
        dataType: "json",
        data:{manualId:manualId},
        success: function (msg) {
            $('#type').empty();
            for (var i = 0; i < msg.length; i++) {
                $('#type').append("<option value='" + msg[i].id + "'>" + msg[i].name + "</option>")
            }
        }
    });
}
