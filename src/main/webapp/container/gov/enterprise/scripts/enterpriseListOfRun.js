var gridTable = $('#table'),
    removeBtn = $('#remove'),
    selections = [];
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        url: rootPath+"/action/S_enterprise_Enterprise_list.action",
        height: 500,
        method:'post',
        queryParams:function (param) {
            var name = $("#s_name").val();
            var age = $("#s_age").val();
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
                field: 'state',
                checkbox: true,
                align: 'center',
                radio:false,  //  true 单选， false多选
                valign: 'middle'
            },
            {
                field: 'name',
                title: '排污单位名称',
                sortable: true,
                editable: true,
                footerFormatter: totalNameFormatter,
                align: 'center'
            }, {
                field: 'orgCode',
                title: '组织机构代码',
                sortable: true,
                align: 'center'
            }, {
                field: 'artificialPerson',
                title: '企业法人',
                sortable: true,
                align: 'center'
            }, {
                field: 'apPhone',
                title: '联系方式',
                sortable: true,
                align: 'center'
            }, {
                field: 'status',
                title: '企业运行状态',
                sortable: true,
                align: 'center'
            }, {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: operateEvents,
                formatter: operateFormatter
            }

        ]
    });
    setTimeout(function () {
        gridTable.bootstrapTable('resetView');
    }, 200);

    //处理删除按钮状态
    removeBtn.click(function () {
        var ids = getIdSelections();
        deleteDemos(ids,function (msg) {
            gridTable.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
            removeBtn.prop('disabled', true);
        });

    });

    //搜索
    $("#search").click(function () {
        //查询之前重置table
        gridTable.bootstrapTable('resetSearch');
        var jsonData = $('#searchform').formSerializeObject();
        console.log(jsonData);
        gridTable.bootstrapTable('refresh',{
            query:jsonData
        });
    });
    //重置搜索
    $("#searchFix").click(function () {
        $("#s_name").val("");
        $("#s_age").val("");
        gridTable.bootstrapTable('resetSearch');
    });

    //表单弹出框 保存按钮
    $("#saveDemo").bind('click',function () {
        var result = ef.submit(true);
        if (!result) {
            return false;
        }
        var demo = {};
        demo.id = $("#id").val();
        demo.name = $("#name").val();
        demo.age = $("#age").val();
        demo.attachmentIds = getAttachmentIds();
        demo.removeId = $("#removeId").val();
        saveDemo(demo,function (msg) {
            $('#demoForm').modal('hide');
            gridTable.bootstrapTable('refresh');
        });
    });

    $(window).resize(function () {
        // 重新设置表的高度
        gridTable.bootstrapTable('resetView', {
            height: getHeight()
        });
    });
}
// 获取所有的选中数据
function getIdSelections() {
    return $.map(gridTable.bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}

// 获取所有的选中数据
function getSelections() {
    return $.map(gridTable.bootstrapTable('getSelections'), function (row) {
        return row
    });
}

// 设置默认选中
function responseHandler(res) {
    $.each(res.rows, function (i, row) {
        row.state = $.inArray(row.id, selections) !== -1;
    });
    return res;
}
// 生成详细信息方法
function detailFormatter(index, row) {
    var html = [];
    $.each(row, function (key, value) {
        html.push('<p><b>' + key + ':</b> ' + value + '</p>');
    });
    return html.join('');
}
// 生成操作方法
function operateFormatter(value, row, index) {
    return [
        '<a class="like" href="javascript:void(0)" title="Like">',
        '<i class="glyphicon glyphicon-heart"></i>',
        '</a>  ',
        '<a class="remove" href="javascript:void(0)" title="Remove">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
// 操作事件
window.operateEvents = {
    'click .like': function (e, value, row, index) {
        alert('You click like action, row: ' + JSON.stringify(row));
    },
    'click .remove': function (e, value, row, index) {
        deleteDemos(row.id, function (msg) {
            gridTable.bootstrapTable('remove', {
                field: 'id',
                values: [row.id]
            });
        });

    }
};
function totalTextFormatter(data) {
    return 'Total';
}
function totalNameFormatter(data) {
    return data.length;
}
function totalPriceFormatter(data) {
    var total = 0;
    $.each(data, function (i, row) {
        total += +(row.price.substring(1));
    });
    return '$' + total;
}
function getHeight() {
    return $(window).height() - $('h1').outerHeight(true);
}
initTable();
//初始化表单
var ef = $("#demoForm").easyform();

function updateDemo(demo) {
    $.ajax({
        url: rootPath + "/action/S_enterprise_Enterprise_save.action",
        type:"post",
        data:demo,
        dataType:"json",
        success:function (msg) {
            alert("更新成功");
        }
    });
}

function deleteDemos(ids,callback) {
    $.ajax({
        url: rootPath + "/action/S_enterprise_Enterprise_delete.action",
        type:"post",
        data:$.param({deletedId:ids},true),//阻止深度序列化，向后台传递数组
        dataType:"json",
        success:callback
    });
}

function saveDemo(demo,callback) {
    $.ajax({
        url: rootPath + "/action/S_enterprise_Enterprise_save.action",
        type:"post",
        data:demo,
        dataType:"json",
        success:callback
    });
}

//初始化表单
$("#demoForm").on('show.bs.modal', function () {
    var selects = getSelections();
    var demo;
    if (selects && selects.length > 0) {
        demo = selects[0];
    }
    refreshDemoForm(demo);
});

/**
 * 刷新表单数据
 * @param demo
 */
function refreshDemoForm(demo) {
    var id = "";
    if (demo && (typeof(demo) == "object")) {
        $("#demoFormTitle").text("修改Dmo");
        id = demo.id;
        $("#id").val(demo.id);
        $("#name").val(demo.name);
        $("#age").val(demo.age);
    }else{
        $("#demoFormTitle").text("新增Dmo");
        $("#id").val("");
        $("#name").val("");
        $("#age").val("");
    }
    uploader = new qq.FineUploader(getUploaderOptions(id));
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