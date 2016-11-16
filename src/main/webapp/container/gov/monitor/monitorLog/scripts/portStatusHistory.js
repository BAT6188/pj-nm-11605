var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#otherProductForm"),
    formTitle = "超标记录",
    selections = [],
    blockMap = {};
setBlockMap();
function setBlockMap(){
    $.ajax({
        url: rootPath + "/action/S_composite_BlockLevel_getAllBlocksZtree.action",
        method:'post',
        async :false,
        dataType:"json",
        success:function(data) {
            $.each(data,function(k,v){
                blockMap[v.id] = v.name;
            })
        }
    });
}
$('.form_date').datetimepicker({
    language:   'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 0,
    pickerPosition: "bottom-left"
});
/**============grid 列表初始化相关代码============**/
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_port_PortStatusHistory_list.action",
        height: getHeight(),
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
        queryParams:function (param) {
            var temp = pageUtils.getBaseParams(param);
            return temp;
        },
        rowStyle:function(row,index) {
            var dataType;
            switch(row.portStatus){
                case '1':
                    dataType = 'danger alert-danger';
                    break;
                case '2':
                    dataType = 'warning alert-warning';
                    break;
                default:
                    dataType = 'success alert-success';
            }
            return { classes:dataType};
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
                title: '企业名称',
                field: 'enterpriseName',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '所属网格',
                field: 'blockLevelName',
                editable: false,
                sortable: false,
                align: 'center',
                formatter: function(value, row, index) {
                    var blockLevelName = blockMap[row.blockLevelId],blockName=blockMap[row.blockId];
                    if(blockLevelName==undefined) blockLevelName = "未定义";
                    if(blockName==undefined) blockName = "未定义";
                    return  blockLevelName+ "-" +blockName;
                }
            },
            {
                title: '超标时间',
                field: 'startTime',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '监测指标',
                field: 'pollutantCode',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '状态',
                field: 'status',
                editable: false,
                sortable: false,
                align: 'center',
                formatter: statusFormatter
            },
            {
                field: 'operate',
                title: '操作',
                align: 'center',
                //events: operateEvents,
                formatter: operateFormatter
            }
        ]
    });
    // sometimes footer render error.
    setTimeout(function () {
        gridTable.bootstrapTable('resetView');
    }, 200);

    //列表checkbox选中事件
    /*gridTable.on('check.bs.table uncheck.bs.table ' +
        'check-all.bs.table uncheck-all.bs.table', function () {
        //有选中数据，启用删除按钮
        removeBtn.prop('disabled', !gridTable.bootstrapTable('getSelections').length);
        //选中一条数据启用修改按钮
        updateBtn.prop('disabled', !(gridTable.bootstrapTable('getSelections').length== 1));
    });*/

    $(window).resize(function () {
        // 重新设置表的高度
        gridTable.bootstrapTable('resetView', {
            height: getHeight()
        });
    });
}

// 生成列表操作方法
function operateFormatter(value, row, index) {
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#otherProductForm">查看</button>';
}
var statusType = {
    '1':'在用',
    '0':'停用'
}
function statusFormatter(value, row, index){
    return statusType[value];
}
// 列表操作事件
/*window.operateEvents = {
    'click .view': function (e, value, row, index) {
        $('.saveBtn').hide();
        $('.lookBtn').show();
        setFormView(row);
    }
};*/
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
    return $(window).height() - $('.dealBox').outerHeight(true) - 200;
}
initTable();
/**============列表工具栏处理============**/
//初始化按钮状态
//removeBtn.prop('disabled', true);
//updateBtn.prop('disabled', true);
/**
 * 列表工具栏 新增和更新按钮打开form表单，并设置表单标识
 */
/*$("#add").bind('click',function () {
    updateSuccessMsg = '添加'+formTitle+'成功!';
    $('.saveBtn').show();
    $('.lookBtn').hide();
    resetForm();
});
$("#update").bind("click",function () {
    updateSuccessMsg = '修改'+formTitle+'成功!';
    $('.saveBtn').show();
    $('.lookBtn').hide();
    setFormData(getSelections()[0]);
});*/
/**
 * 列表工具栏 删除按钮
 */
/*removeBtn.click(function () {
    var ids = getIdSelections();
    $('.mainBox').BootstrapConfirm('确认要删除选择的数据吗？',function(){
        deleteAjax(ids,function (msg) {
            $('.mainBox').BootstrapAlertMsg('success','删除成功!',2000);
            gridTable.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
            removeBtn.prop('disabled', true);
        });
    });
});*/

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
/*var updateSuccessMsg = '提交成功';
//初始化表单验证
var ef = form.easyform({
    success:function (ef) {
        var entity = form.find("form").formSerializeObject();
        entity.enterpriseId=enterpriseId;
        entity.attachmentId = getAttachmentIds();
        saveAjax(entity,function (msg) {
            form.find('#cancelBtn').trigger('click');
            $('.mainBox').BootstrapAlertMsg('success',updateSuccessMsg,2000);
            gridTable.bootstrapTable('refresh');
        });
    }
});

//表单 保存按钮
$("#save").bind('click',function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    ef.submit(false);
});*/
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
function setFormView(entity) {
    setFormData(entity);
    form.find(".form-title").text("查看"+formTitle);
    disabledForm(true);
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
}
function disabledForm(disabled) {
    form.find(".form-control").attr("disabled",disabled);
    form.find('.isRadio input').attr("disabled",disabled);
}
/**
 * 重置表单
 */
function resetForm() {
    form.find(".form-title").text("新增"+formTitle);
    //form.find("input[type!='radio'][type!='checkbox']").val("");
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

