var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#noiseForm"),
    formTitle = "噪声监测点",
    selections = [];


initMapBtn();
/*初始化标注按钮*/
function initMapBtn(){
    //绑定markDialog关闭事件
    MapMarkDialog.closed(function (mark) {
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
        MapMarkDialog.setMode(MapMarkDialog.MODE_POINT);
        MapMarkDialog.open();
    });
}
//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_port_NoisePort_save.action",
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
        url: rootPath + "/action/S_port_NoisePort_delete.action",
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
        url: rootPath+"/action/S_port_NoisePort_list.action",
        height: pageUtils.getTableHeight()-50,
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
        queryParams:function (param) {
            var temp = pageUtils.getBaseParams(param);
            temp.type = 1;
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
                title: '噪声源编号',
                field: 'number',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '监测点名称',
                field: 'name',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '噪声源类型',
                field: 'noiseType',
                editable: false,
                sortable: false,
                align: 'center',
                formatter:noiseTypeFormatter
            },
            {
                title: '功能区类别',
                field: 'fnType',
                editable: false,
                sortable: false,
                align: 'center',
                formatter:fnTypeFormatter
            },
            {
                title: '经度',
                field: 'longitude',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '纬度',
                field: 'latitude',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                field: 'portStatus',
                title: '当前状态',
                editable: false,
                sortable: false,
                align: 'center',
                formatter: function(value, row, index) {
                    switch(value){
                        case "0":
                            return '<img src="container/gov/enterprise/images/greenCircle.png" style="width: 20px;height: 20px;">';
                        case "1":
                            return '<img src="container/gov/enterprise/images/readCircle.png" style="width: 20px;height: 20px;">';
                        case "2":
                            return '<img src="container/gov/enterprise/images/yelloCircle.png" style="width: 20px;height: 20px;">';
                        default:
                            return '<img src="container/gov/enterprise/images/greenCircle.png" style="width: 20px;height: 20px;">';
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
            height: pageUtils.getTableHeight()-50
        });
    });
}

// 生成列表操作方法
function operateFormatter(value, row, index) {
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#noiseForm">查看</button>';
}
function noiseTypeFormatter(value, row, index){
    return dict.get('noiseType',value);
}
function fnTypeFormatter(value, row, index){
    return dict.get('noiseFnType',value);
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
        entity.type = 1;
        entity.attachmentId = getAttachmentIds();
        saveAjax(entity,function (msg) {
            $(".modal").modal('hide');
            Ewin.alert(updateSuccessMsg);
            gridTable.bootstrapTable('refresh');
        });
    }
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
    $("#fine-uploader-gallery").find('.qq-uploader-selector').attr('qq-drop-area-text','暂无上传的附件');
}
function disabledForm(disabled) {
    form.find('.formBtn').attr("disabled",disabled);
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

/**
 * 平面图标注
 */
function makePlaneMap(){
    var planeMapMarkDate = $('#planeMapMark').val();
    var data = (planeMapMarkDate=="")?"":JSON.parse(planeMapMarkDate);
    PlottingDialog.dialog({
        show:true,
        mode:"marker",
        data:data,
        attachmentId:enterpriseData.planeMap,
        callback:function (marker) {
            var str = JSON.stringify(marker);
            form.find('#planeMapMark').val(str);
        }
    });
}
/**
 * 查看平面图
 */
function lookPlaneMap(){
    var planeMapMarkDate = $('#planeMapMark').val();
    var data = (planeMapMarkDate=="")?"":JSON.parse(planeMapMarkDate);
    PlottingDialog.dialog({
        show:true,
        mode:"view",
        data:data,
        attachmentId:enterpriseData.planeMap
    });
}