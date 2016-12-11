//@ sourceURL=sharemeans.js
var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#shareMeansForm"),
    formTitle = "资料共享",
    selections = [];


function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_office_ShareMeans_list.action?orgCode="+orgCode,
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
                title: '标题',
                field: 'title',
                editable: false,
                sortable: false,
                align: 'center',
                isDown:true
            }, {
                title: '类型',
                field: 'type',
                sortable: false,
                align: 'center',
                editable: false,
                isDown:true
            }, {
                title: '发布部门',
                field: 'pubOrgName',
                sortable: false,
                align: 'center',
                editable: false,
                isDown:true
            }, {
                title: '发布时间',
                field: 'pubTime',
                sortable: false,
                align: 'center',
                editable: false,
                formatter:function (value, row, index) {
                    return pageUtils.sub10(value);
                },
                isDown:true
            },
            {
                field: 'status',
                title: '发布状态',
                sortable: false,
                align: 'center',
                editable: false,
                formatter:function (value, row, index) {
                    switch (value) {
                        case "1":
                            return '<span style="color: green;">已发布</span>';
                            break;
                        default:
                            return '<span style="color: red;">未发布</span>';
                    }
                },
                isDown:true
            },
            {
                title: '资料描述',
                field: 'description',
                align: 'center',
                sortable: false,
                visible: false,
                isDown:true
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

    //处理删除按钮状态
    removeBtn.click(function () {
        var ids = getIdSelections();
        Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
            if (!e) {
                return;
            }
            deleteShareMeans(ids,function (msg) {
                gridTable.bootstrapTable('remove', {
                    field: 'id',
                    values: ids
                });
                removeBtn.prop('disabled', true);
            });
        });
    });
    /**============列表搜索相关处理============**/
    $("#search").click(function () {
        gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
    });
//重置搜索
    $("#searchFix").click(function () {
        $('#searchform')[0].reset();
        gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
    });

    //表单弹出框 保存按钮
    $("#saveShareMeans").bind('click',function () {
         ef.submit(false);
    });

    $(window).resize(function () {
        // 重新设置表的高度
        gridTable.bootstrapTable('resetView', {
            height: pageUtils.getTableHeight()
        });
    });
    //导出
    gridTable.BootstrapExport($('#export'),{
        fileName:'资料共享',
        type: 'excel',
        escape: false
    });
}

// 生成列表操作方法
function operateFormatter(value, row, index) {
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#shareMeansForm">查看</button>';
}
// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, row, index) {
        setFormView(row);
        $("#status").val(row.status);
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
        console.log(row.pubOrgId +'+'+orgCode);
        if(row.pubOrgId==orgCode){
            updateBtn.prop('disabled', false);
        }else{
            updateBtn.prop('disabled', true);
            removeBtn.prop('disabled', true);
            Ewin.alert({message: "没有操作权限！"}).on(function (e) {
                if (!e) {
                    return;
                }
            });
        }
        return row;
    });
}


function getHeight() {
    return $(window).height() - $('h1').outerHeight(true);
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

$("#pub").bind("click",function () {
    var id=getIdSelections()[0];
    pubSharemean(id);
});


function pubSharemean(id){
    console.log(id);
    Ewin.confirm({ message: "是否发布信息" }).on(function (e) {
        if (!e) {
            return;
        }else{
            $.ajax({
                url: rootPath + "/action/S_office_ShareMeans_pubsave.action",
                type:"post",
                dataType:'json',
                data:{id:id},
                success: function(msg){
                    form.modal('hide');
                    gridTable.bootstrapTable('refresh');
                }
            });
        }
    })
}


//初始化表单验证
var ef = form.easyform({
    success:function(ef){
        var sharemeans = form.find("form").formSerializeObject();
        sharemeans.attachmentIds = getAttachmentIds();
        // sharemeans.status="0";
        saveShareMeans(sharemeans,function (msg) {
            form.modal('hide');
            gridTable.bootstrapTable('refresh');
        });
    }
});

function deleteShareMeans(ids,callback) {
    $.ajax({
        url: rootPath + "/action/S_office_ShareMeans_delete.action",
        type:"post",
        data:$.param({deletedId:ids},true),//阻止深度序列化，向后台传递数组
        dataType:"json",
        success:callback
    });
}

function saveShareMeans(sharemeans,callback) {
    $.ajax({
        url: rootPath +"/action/S_office_ShareMeans_save.action",
        type:"post",
        data:sharemeans,
        dataType:"json",
        success:callback
    });
}
//初始化日期组件
$('.form_datetime').datetimepicker({
    language:   'zh-CN',
    autoclose: 1,
    minView: 2
});

/**
 * 刷新表单数据
 * @param meeting
 */
function setFormData(sharemeans) {
    resetForm();
    if (!sharemeans) {return false}
    form.find(".form-title").text("修改"+formTitle);
    var id = sharemeans.id;
    $("#id").val(sharemeans.id);
    $("#removeId").val("");
    $("#title").val(sharemeans.title);
    $("#type").val(sharemeans.type);
    $("#pubTime").val(pageUtils.sub10(sharemeans.pubTime));
    $("#pubOrgName").val(sharemeans.pubOrgName);
    $("#pubOrgId").val(sharemeans.pubOrgId);
    $("#description").val(sharemeans.description);
    $("#status").val(sharemeans.status);
    if(sharemeans.status == 1){
       $('#pub').hide();
    }else{
        $('#pub').show();

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
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"暂无附件信息");
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
    form.find("#saveShareMeans").hide();
    form.find("#pub").hide();
    form.find(".btn-cancel").text("关闭");
}
function disabledForm(disabled) {
    form.find("input").attr("disabled",disabled);
    if (!disabled) {
        //初始化日期组件
        $('#pubTimeContent').datetimepicker({
            language:   'zh-CN',
            autoclose: 1,
            minView: 2
        });
    }else{
        $('#pubTimeContent').datetimepicker('remove');
    }

}
/**
 * 重置表单
 */
function resetForm() {
    form.find(".form-title").text("新增"+formTitle);
    form.find("input[type!='radio'][type!='checkbox'],textarea").val("");
    uploader = new qq.FineUploader(getUploaderOptions());
    $("#pubOrgName").val(orgName);
    $("#pubOrgId").val(orgCode);
    $("#status").val(0);
    console.log(orgCode);
    disabledForm(false);
    $('#pub').show();
    form.find("#saveShareMeans").show();
    form.find(".btn-cancel").text("取消")
}

//附件相关js
var uploader;//附件上传组件对象
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

