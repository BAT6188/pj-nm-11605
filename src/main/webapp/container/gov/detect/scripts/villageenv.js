//@ sourceURL=villageenv.js
var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#scfForm"),
    formTitle = "农村生态环境监管",
    selections = [];

//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_composite_VillageEnv_save.action",
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
        url: rootPath + "/action/S_composite_VillageEnv_delete.action",
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
        url: rootPath + "/action/S_composite_VillageEnv_list.action",
        height: pageUtils.getTableHeight(),
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
                title: '乡镇名称',
                field: 'name',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '所属区域',
                field: 'address',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '网格负责人',
                field: 'principal',
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
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#scfForm">查看</button>'
        + '&nbsp;&nbsp;<button type="button" class="btn btn-md btn-warning video" data-villageenv-id="' + row.id + '" data-toggle="modal" data-target="#lookVideo">视频</button>';

}
var villageEnvId;
// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, row, index) {
        setFormView(row);
    },
    'click .video': function (e, value, row, index) {
        VideoPage.villageEnvId = $(this).data("villageenv-id");
        VideoPage.blockLevelId=row.blockLevelId;
        VideoPage.blockId=row.blockId;
        VideoPage.blockName=row.blockName;
        VideoPage.villageEnvName=row.name;
        VideoPage.refreshGridTable();
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
    $('#lookPoints').hide();
    $('#editPoints').show();
    resetForm();
});
$("#update").bind("click", function () {
    $('#lookPoints').hide();
    $('#editPoints').show();
    var entity = getSelections()[0];
    setFormData(entity);
    if(!entity.points){
        setPointsMarkBtn('add');
    }else{
        setPointsMarkBtn('edit');
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
        var queryParams = {};
        var name = $("#s_name").val();
        var principal = $("#s_principal").val();
        if (name) {
            queryParams["name"] = name;
        }
        if (principal) {
            queryParams["principal"] = principal;
        }
        gridTable.bootstrapTable('refresh', {
            query: queryParams
        });
    });

    /**============表单初始化相关代码============**/

//初始化表单验证
    var ef = form.easyform({
        success: function (ef) {
            var entity = $("#scfForm").find("form").formSerializeObject();
            entity.attachmentIds = getAttachmentIds();
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
        var inputs = form.find('.form-control');
        $.each(inputs,function(k,v){
            var tagId = $(v).attr('id');
            var value = entity[tagId];
            if(v.tagName=='SELECT'){
                if(tagId=="blockLevelId" && value!=null){
                    var childData = searchForAjax("/action/S_composite_Block_findLevelById.action",{blockLevelId: value});
                    $('#blockId').empty();
                    $('#blockId').append($("<option>").val("").text("---请选择---"));
                    $.each(childData,function(k,v){
                        $('#blockId').append($("<option>").val(v.id).text(v.orgName));
                    });
                }
                $(v).find("option[value='"+value+"']").attr("selected",true);
            }else{
                $(v).val(value);
            }
        });
        uploader = new qq.FineUploader(getUploaderOptions(id));
    }

    function setFormView(entity) {
        setFormData(entity);
        $('#editPoints').hide();
        if(!entity.points){
            setPointsMarkBtn('lookNull');
        }else{
            setPointsMarkBtn('look');
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
        form.find("#save").hide();
        form.find(".btn-cancel").text("关闭");
    }

    function disabledForm(disabled) {
        form.find("input").attr("disabled", disabled);
        form.find("textarea").attr("disabled", disabled);
        form.find("select").attr("disabled", disabled);
        $("#mapMarkBtn").attr("disabled", disabled);
        if (!disabled) {
            //初始化日期组件
            $('#pubTimeContent').datetimepicker({
                language: 'zh-CN',
                autoclose: 1,
                minView: 2
            });
        } else {
            $('#pubTimeContent').datetimepicker('remove');
        }

    }

    /**
     * 重置表单
     */
    function resetForm() {
        setBlock('#blockLevelId','#blockId');
        setPointsMarkBtn('add');
        form.find(".form-title").text("新增" + formTitle);
        form.find("input[type!='radio'][type!='checkbox']").val("");
        $("textarea").val("");
        uploader = new qq.FineUploader(getUploaderOptions());
        disabledForm(false);
        form.find("#save").show();
        form.find(".cancel").text("取消");
    }
function setBlock(pSelector,cSelector){
    var pBlock = $(pSelector),cBlock = $(cSelector),blockMap={};
    var blockLevel = searchForAjax("/action/S_composite_BlockLevel_list.action",null);
    if(blockLevel.total){
        pBlock.empty();
        pBlock.append($("<option>").val("").text("---请选择---"));
        $.each(blockLevel.rows,function(k,v){
            pBlock.append($("<option>").val(v.id).text(v.name));
            var child = searchForAjax("/action/S_composite_Block_findLevelById.action",{blockLevelId: v.id});
            blockMap[v.id] = child;
        });
        pBlock.change(function(){
            var pid = $(this).val();
            var childData = blockMap[pid];
            cBlock.empty();
            cBlock.append($("<option>").val("").text("---请选择---"));
            $.each(childData,function(k,v){
                cBlock.append($("<option>").val(v.id).text(v.orgName));
            });
        });
    }
}

function searchForAjax(url,entity) {
    var returnData;
    $.ajax({
        url: rootPath + url,
        method:'post',
        async :false,
        data:entity,
        dataType:"json",
        success:function(data) {
            returnData = data;
        }
    });
    return returnData;
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
function initMapBtn() {
    //绑定markDialog关闭事件
    MapMarkDialog.closed(function (mark) {
        if (mark) {
            $("#points").val(mark);
        } else {
            Ewin.alert({message: "请选择坐标"});
            return false;
        }
        if( $("#points").val()){
            setPointsCheckType(true);
            $('#editPoints').html("重新标绘");
        }
    });
    //设置标绘模式
    MapMarkDialog.setMode("polygon");
    MapMarkDialog.open();
}
function lookMapBtn() {
    var points=$("#points").val();
    MapMarkDialog.setMode(MapMarkDialog.MODE_VIEW,{type:MapMarkDialog.VIEW_POLYGON,"points":points});
    MapMarkDialog.open();
}
function setPointsMarkBtn(type){
    $('#cheackPoints').attr('disabled',false);
    switch (type){
        case 'add':
            setPointsCheckType(false);
            $('#editPoints').html("标绘");
            break;
        case 'edit':
            setPointsCheckType(true);
            $('#editPoints').html("重新标绘");
            break;
        case 'look':
            setPointsCheckType(true);
            $('#lookPoints').show();
            break;
        case 'lookNull':
            setPointsCheckType(false);
            $('#lookPoints').hide();
            break;
        default:
            $('#editPoints').html("标绘");
    }
}
function setPointsCheckType(isMarked){
    if(isMarked){
        $('#cheackPoints').prop("checked",true);
        $('#cheackPoints').attr('disabled',true);
    }else{
        $('#cheackPoints').prop("checked",false);
        $('#cheackPoints').attr('disabled',true);
    }
}
