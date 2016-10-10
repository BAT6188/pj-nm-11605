var gridTable = $('#table'),
    removeBtn = $('#remove'),
    selections = [];
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        url: rootPath+"/action/S_office_PubInfo_list.action",
        height: 590,
        method:'post',
        queryParams:function (param) {
            var title = $("#s_title").val();
            var type =$("#s_type").val();
            var pubTime = $("#s_pubTime").val();
            var temp = {
                pubTime: pubTime,
                type: type,
                title:title,
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
            }, {
                title: 'ID',
                field: 'id',
                align: 'center',
                valign: 'middle',
                sortable: false,
                footerFormatter: totalTextFormatter
            },
            {
                title: '标题',
                field: 'title',
                editable: false,
                sortable: false,
                footerFormatter: totalNameFormatter,
                align: 'center'
            }, {
                title: '类型',
                field: 'type',
                sortable: false,
                align: 'center',
                editable: false,
                footerFormatter: totalPriceFormatter
            }, {
                title: '发布单位',
                field: 'pubOrgName',
                sortable: false,
                align: 'center',
                editable: false,
                footerFormatter: totalPriceFormatter
            }, {
                title: '会议时间',
                field: 'pubTime',
                sortable: false,
                align: 'center',
                editable: false,
                footerFormatter: totalPriceFormatter
            }

        ]
    });
    // sometimes footer render error.
    setTimeout(function () {
        gridTable.bootstrapTable('resetView');
    }, 200);

    //处理删除按钮状态
    removeBtn.click(function () {
        var ids = getIdSelections();
        deletePubInfo(ids,function (msg) {
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
        var title = $("#s_title").val();
        var type =$("#s_type").val();
        var pubTime = $("#s_pubTime").val();
        gridTable.bootstrapTable('refresh',{
            query:{title: title,type:type, pubTime: pubTime}
        });
    });
    //重置搜索
    $("#searchFix").click(function () {
        $("#s_title").val("");
        $("#s_type").val("");
        $("#s_pubTime").val("");
        gridTable.bootstrapTable('resetSearch');
    });

    //表单弹出框 保存按钮
    $("#savePubInfo").bind('click',function () {
         ef.submit(true);
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
        deletePubInfo(row.id, function (msg) {
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
//初始化表单验证
var ef = $("#pubInfoForm").easyform({
    success:function(ef){
        var pubinfo = {};
        pubinfo.id = $("#id").val();
        pubinfo.title = $("#title").val();
        pubinfo.type = $("#type").val();
        pubinfo.pubTime=$("#pubTime").val();
        pubinfo.pubOrgName=$("#pubOrgName").val();
        pubinfo.content=$("#content").val();
        pubinfo.attachmentIds = getAttachmentIds();
        pubinfo.removeId = $("#removeId").val();
        savePubInfo(pubinfo,function (msg) {
            $('#pubInfoForm').modal('hide');
            gridTable.bootstrapTable('refresh');
        });
    }
});

function updatePubInfo(pubinfo) {
    $.ajax({
        url: rootPath + "/action/S_office_PubInfo_save.action",
        type:"post",
        data:pubinfo,
        dataType:"json",
        success:function (msg) {
            alert("更新成功");
        }
    });
}

function deletePubInfo(ids,callback) {
    $.ajax({
        url: rootPath + "/action/S_office_PubInfo_delete.action",
        type:"post",
        data:$.param({deletedId:ids},true),//阻止深度序列化，向后台传递数组
        dataType:"json",
        success:callback
    });
}

function savePubInfo(pubinfo,callback) {
    $.ajax({
        url: rootPath +"/action/S_office_PubInfo_save.action",
        type:"post",
        data:pubinfo,
        dataType:"json",
        success:callback
    });
}
$("#add,#update").bind('click',function () {
    $("#pubInfoForm").attr("data-form-type",$(this).attr("id"));
});
//初始化表单数据
$("#pubInfoForm").on('show.bs.modal', function () {
    var pubinfo;
    var formType = $("#pubInfoForm").attr("data-form-type");
    if (formType == "update") {
        var selects = getSelections();
        if (selects && selects.length > 0) {
            pubinfo = selects[0];
        }
    }
    refreshPubInfoForm(pubinfo);
});

/**
 * 刷新表单数据
 * @param meeting
 */
function refreshPubInfoForm(pubinfo) {
    var id = "";
    if (pubinfo && (typeof(pubinfo) == "object")) {
        $("#pubInfoFormTitle").text("修改信息公告");
        id = pubinfo.id;
        $("#id").val(pubinfo.id);
        $("#title").val(pubinfo.title);
        $("#type").val(pubinfo.type);
        $("#pubTime").val(pubinfo.pubTime);
        $("#pubOrgName").val(pubinfo.pubOrgName);
        $("#content").val(pubinfo.content);

    }else{
        $("#pubInfoFormTitle").text("新增信息公告");
        $("#id").val("");
        $("#title").val("");
        $("#type").val("");
        $("#pubTime").val("");
        $("#pubOrgName").val("");
        $("#content").val("")


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

