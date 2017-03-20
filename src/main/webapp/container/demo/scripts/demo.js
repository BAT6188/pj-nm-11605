var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#demoForm"),
    formTitle = "Demo";

//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_demo_Demo_save.action",
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
        url: rootPath + "/action/S_demo_Demo_delete.action",
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
        url: rootPath+"/action/S_demo_Demo_list.action",
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
                title: '名称',
                field: 'name',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '年龄',
                field: 'age',
                sortable: false,
                align: 'center',
                editable: false,
                formatter:function (value, row, index) {
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
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#demoForm">查看</button>';
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
    Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
        if (!e) {
            return;
        }
        deleteAjax(ids,function (msg) {
            gridTable.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
            removeBtn.prop('disabled', true);
        });
    });


});

$("#demo_showWord").bind("click",function () {
    var selectModel = getSelections()[0];
    Ewin.confirm({ message: "确认要导出吗？" }).on(function (e) {
        if (!e) {
            return;
        }
        window.open(rootPath+"/action/S_officetemp_OfficeTemp_showTemplate.action?" +
            "id=Demo&beanName=demoService&bussinessId="+selectModel.id);
    });

});



/**============列表搜索相关处理============**/
//搜索按钮处理
$("#search").click(function () {
    var queryParams = {};
    var name = $("#s_name").val();
    var age = $("#s_age").val();
    if (name){
        queryParams["name"] = name;
    }
    if (age){
        queryParams["age"] = age;
    }
    gridTable.bootstrapTable('refresh',{
        query:queryParams
    });
});

//初始化日期组件
$('.form_datetime').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    forceParse: 0,
    showMeridian: 1
});

/**============表单初始化相关代码============**/

//初始化表单验证
var ef = form.easyform({
    success:function (ef) {
        var entity = $("#demoForm").find("form").formSerializeObject();
        entity.attachmentIds = getAttachmentIds([uploader0,uploader1,uploader2]);
        saveAjax(entity,function (msg) {
            form.modal('hide');
            gridTable.bootstrapTable('refresh');
        });
    }
});
//绑定markDialog关闭事件
MapMarkDialog.closed(function (mark) {
    console.log(mark);
    if (mark) {
        $("#longitude").val(mark.x);
        $("#latitude").val(mark.y);
    }else{
        Ewin.alert({message:"请选择坐标"});
        return false;
    }
});
$('#mapMarkBtn').bind('click', function () {
    //设置标绘模式
    MapMarkDialog.setMode("polygon");
    MapMarkDialog.open();
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
    $("#removeId").val("");

    var inputs = form.find('[name]');
    $.each(inputs,function(k,v){
        var tagId = $(v).attr('name');
        $(v).val(entity[tagId]);
    });
    uploader0=$("#fine-uploader-gallery").initFineUploader(0,entity.id,function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"");
    });
    uploader1=$("#a1").initFineUploader(1,entity.id,function () {
        $("#a1").find(".qq-upload-delete").hide();
        $("#a1").find("[qq-drop-area-text]").attr('qq-drop-area-text',"");
    });
    uploader2=$("#a2").initFineUploader(2,entity.id,function () {
        $("#a2").find(".qq-upload-delete").hide();
        $("#a2").find("[qq-drop-area-text]").attr('qq-drop-area-text',"");
    });

}
function setFormView(entity) {
    setFormData(entity);
    form.find(".form-title").text("查看"+formTitle);
    disabledForm(true);
    uploader0=$("#fine-uploader-gallery").initFineUploader(0,entity.id,function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"");
    });
    uploader1=$("#a1").initFineUploader(1,entity.id,function () {
        $("#a1").find(".qq-upload-delete").hide();
        $("#a1").find("[qq-drop-area-text]").attr('qq-drop-area-text',"");
    });
    uploader2=$("#a2").initFineUploader(2,entity.id,function () {
        $("#a2").find(".qq-upload-delete").hide();
        $("#a2").find("[qq-drop-area-text]").attr('qq-drop-area-text',"");
    });
    $(".qq-upload-button").hide();
    form.find("#save").hide();
    form.find(".btn-cancel").text("关闭");
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
    uploader0=$("#fine-uploader-gallery").initFineUploader(0);
    uploader1=$("#a1").initFineUploader(1);
    uploader2=$("#a2").initFineUploader(2);
    disabledForm(false);
    form.find("#save").show();
    form.find(".btn-cancel").text("取消");
}

//表单附件相关js
var uploader0;//附件上传组件对象
var uploader1;//附件上传组件对象
var uploader2;//附件上传组件对象

(function($){
    function getUploaderOptions(_that,_type,_bussinessId) {
        return {
            element: _that[0],
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
                    if(this instanceof qq.FineUploader){
                        console.log("true")
                    }
                    console.log(this)
                    this.setUuid(id, msg.id);
                },
                onDeleteComplete:function (id) {
                    var file = this.getUploads({id:id});
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
                endpoint: rootPath + '/Upload?type='+_type,
                params: {
                    businessId:_bussinessId
                }
            },
            session:{
                endpoint: rootPath + '/action/S_attachment_Attachment_listAttachment.action',
                params: {
                    businessId:_bussinessId,
                    attachmentType:_type
                }
            },
            deleteFile: {
                enabled: true,
                endpoint: rootPath + "/action/S_attachment_Attachment_delete.action",
                method:"POST"
            },
            validation: {
                itemLimit: 5
            },
            debug: true
        };
    }

    $.fn.initFineUploader = function(type,bussinessId,onSessionRequestComplete){
        var that=this;
        var uploaderOptions = getUploaderOptions(that,type,bussinessId);
        if (onSessionRequestComplete){
            uploaderOptions.callbacks.onSessionRequestComplete=onSessionRequestComplete;
        }

        var _uploader = new qq.FineUploader(uploaderOptions);
        this.on('click', '.qq-upload-download-selector', function () {
            var uuid = _uploader.getUuid($(this.closest('li')).attr('qq-file-id'));
            window.location.href = rootPath+"/action/S_attachment_Attachment_download.action?id=" + uuid;
        });
        console.log(this)
        console.log(this[0])
        return _uploader;
    };
})(jQuery);

/**
 * 获取附件列表ids
 * @returns {*}
 */
function getAttachmentIds(_uploaderArray) {
    var ids = [];
    $.each(_uploaderArray,function (i,v) {
        if(v!=undefined){
            var attachments = v.getUploads();
            if (attachments && attachments.length) {
                for (var i = 0 ; i < attachments.length; i++){
                    ids.push(attachments[i].uuid);
                }
            }
        }
    })
    if (ids.length>0){
        return ids=ids.join(",");
    }else {
        return ''
    }
}





