//@ sourceURL=EnvTestProgram.js
var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#EnvTestProgramForm"),
    formTitle = "环境自测方案",
    selections = [];

$('.form_date').datetimepicker({
    language:   'zh-CN',
    autoclose: 1,
    startView: 'decade',
    minView: 'decade',
    format: 'yyyy',
    pickerPosition: "bottom-left"
});
//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_enterprise_EnvTestProgram_save.action",
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
        url: rootPath + "/action/S_enterprise_EnvTestProgram_delete.action",
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
        url: rootPath+"/action/S_enterprise_EnvTestProgram_list.action",
        height: getHeight(),
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
        queryParams:function (param) {
            var temp = pageUtils.getBaseParams(param);
            temp.enterpriseId = enterpriseId;
            return temp;
        },
        columns: [
            {
                title:"全选",
                checkbox: true,
                align: 'center',
                radio:false,  //  true 单选， false多选
                valign: 'middle'
            },
            {
                title: '附件名称',
                field: 'attachmentName',
                editable: false,
                align: 'center',
                sortable: false,
            },
            {
                title: '创建时间',
                field: 'createTime',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '主要内容',
                field: 'content',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '年份',
                field: 'year',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '发布状态',
                field: 'pubStatus',
                editable: false,
                sortable: false,
                align: 'center',
                formatter:function(value, row, index){
                    if(value == 1){
                        return "已发布";
                    }else{
                        return "未发布";
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
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#EnvTestProgramForm">查看</button>'; //+
    //'<button type="button" style="margin-left: 5px;" class="btn btn-primary" onclick="makePlaneMap()">标注平面图</button>';
}
// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, row, index) {
        $('.saveBtn').hide();
        $('.lookBtn').show();
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

function getHeight() {
    return $(window).height() - $('.dealBox').outerHeight(true) - 160;
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
    updateSuccessMsg = '添加'+formTitle+'成功!';
    $('.saveBtn').show();
    $('.lookBtn').hide();
    resetForm();
});
$("#update").bind("click",function () {
    updateSuccessMsg = '修改'+formTitle+'成功!';
    $('.saveBtn').show();
    $('.lookBtn').hide();
    var entity = getSelections()[0];
    setFormData(entity);
    if(!entity.planeMapMark){
        setPlaneMarkBtn('add');
    }else{
        setPlaneMarkBtn('edit');
    }
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
            Ewin.alert('删除成功！');
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

/**============表单初始化相关代码============**/
var updateSuccessMsg = '提交成功';
//初始化表单验证
var ef = form.easyform({
    success:function (ef) {
        var entity = form.find("form").formSerializeObject();
        if(pubType){
            entity.pubStatus = 1;
        }else{
            entity.pubStatus = 0;
        }
        entity.enterpriseId=enterpriseId;
        entity.attachmentId = getAttachmentIds();
        saveAjax(entity,function (msg) {
            $(".modal").modal('hide');
            Ewin.alert(updateSuccessMsg);
            gridTable.bootstrapTable('refresh');
        });
    }
});
var pubType = false;
//表单 保存按钮
$("#save").bind('click',function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    pubType = false;
    ef.submit(false);
});
$('#pubBtn').bind('click',function(){
    pubType = true;
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
    var inputs = form.find('.form-control');
    $.each(inputs,function(k,v){
        var tagId = $(v).attr('name');
        var value = entity[tagId];
        if($(v)[0].tagName=='select'){
            $(v).find("option[value='"+value+"']").attr("selected",true);
        }else{
            $(v).val(value);
        }
    });
    var radios = form.find('.isRadio');
    $.each(radios,function(k,v){
        var tagId = $(v).attr('id');
        var value = entity[tagId];
        $("input#"+tagId+value).get(0).checked=true;
    });
    uploader = new qq.FineUploader(getUploaderOptions(id));
}
var thisEntity;
function setFormView(entity) {
    thisEntity = entity;
    setFormData(entity);
    form.find(".form-title").text("查看"+formTitle);
    disabledForm(true);
    if(!entity.planeMapMark){
        setPlaneMarkBtn('lookNull');
    }else{
        setPlaneMarkBtn('look');
    }
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
    $("#fine-uploader-gallery").find('.qq-uploader-selector').attr('qq-drop-area-text','暂无上传的附件');
}
function disabledForm(disabled) {
    form.find(".form-control").attr("disabled",disabled);
    form.find(".formBtn").attr("disabled",disabled);
    form.find('.isRadio input').attr("disabled",disabled);
}
/**
 * 重置表单
 */
function resetForm() {
    setPlaneMarkBtn('add');
    form.find(".form-title").text("新增"+formTitle);
    form.find("input[type!='radio'][type!='checkbox']").val("");
    form.find('form')[0].reset();
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
                $('#attachmentName').val(fileName);
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
                $('#attachmentName').val("");
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
            itemLimit: 1
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

