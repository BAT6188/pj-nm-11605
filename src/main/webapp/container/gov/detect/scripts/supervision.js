//@ sourceURL=container/gov/detect/scripts/supervision.js
var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#scfForm"),
    formTitle = "网格人员",
    selections = [];

//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_composite_Block_save.action",
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
        url: rootPath + "/action/S_composite_Block_delete.action",
        type:"post",
        data:$.param({deletedId:ids},true),//阻止深度序列化，向后台传递数组
        dataType:"json",
        success:callback
    });
    zTreeOnAsyncSuccess();
}
/**============grid 列表初始化相关代码============**/
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_composite_Block_list.action",
        height: pageUtils.getTableHeight(),
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
       // queryParams:pageUtils.localParams,
        queryParams:function (param) {
            var temps = pageUtils.getBaseParams(param);
            temps.blockLevelId = ztreeId;
            return temps;
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
                title: 'ID',
                field: 'id',
                align: 'center',
                valign: 'middle',
                sortable: false,
                visible:false
            },
            {
                title: '单位名称',
                field: 'orgName',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '姓名',
                field: 'principal',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '管辖网格',
                field: 'parentBlockId',
                sortable: false,
                align: 'center',
                editable: false,
                formatter: function (value, row, index) {
                    if (value==1){
                        value="一级网格"
                    }else if(value==2){
                        value="二级网格"
                    }else if(value==3){
                        value="三级网格"
                    }else if(value==4){
                        value="四级网格"
                    }else{
                        value=""
                    }
                    return value
                }
            },
            {
                title: '联系方式',
                field: 'principalPhone',
                sortable: false,
                align: 'center',
                editable: false
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


var ztreeId;
var ztreeName;
var nextztreeid;
var setting = {
    view: {
        showLine: true
    },
    data: {
        keep: {
            leaf: true
        }
    },
    check:{
        enable:true
    },
    async: {
        enable: true,
        url:rootPath+"/action/S_composite_BlockLevel_getBlock.action",
        autoParam: ["id"]
    },
    callback: {
        onAsyncSuccess: zTreeOnAsyncSuccess,
        onClick: zTreeOnClick
    }
};
$.fn.zTree.init($("#ztree"), setting);
//默认加载第一个节点
var firstAsyncSuccessFlag = 0;
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg){
    var treeObj = $.fn.zTree.getZTreeObj("ztree");
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
        ztreeId=nodes[0].id;
    }
    //刷新表格列表
    gridTable.bootstrapTable('refresh');
}

//点击节点事件
var blockLevelId;
function zTreeOnClick(event, treeId, treeNode) {
    if(treeNode.isParent){
        ztreeId=treeNode.id;
        ztreeName=treeNode.name;
    }else{
        var treeObj = $.fn.zTree.getZTreeObj("ztree");
        var sNodes = treeObj.getSelectedNodes();
        if (sNodes.length > 0) {
            var node = sNodes[0].getParentNode();
            ztreeId=node.id;
            ztreeName=node.name;
        }
    }
    selectZreeId();
    zTreeOnSuccess();
    // gridTable.bootstrapTable('refresh');
}
//右侧列表添加数据刷新zTree
function zTreeOnSuccess(){
    var treeObj = $.fn.zTree.getZTreeObj("ztree");
    var node = treeObj.getSelectedNodes();
    if (node.length>0) {
        var selectNode = node[0];
        if(selectNode.isParent){
            //. 重新异步加载当前选中的第一个节点
            treeObj.reAsyncChildNodes(selectNode,"refresh");
        }
    }
    gridTable.bootstrapTable('refresh')
}

    function selectZreeId(){
        var treeObj = $.fn.zTree.getZTreeObj("ztree");
        var sNodes = treeObj.getSelectedNodes();
        //获得当前节点的下级节点
        if (sNodes.length > 0) {
            //当前节点的前一个节点是子节点如果是子节点
            var node = sNodes[0].getPreNode();
            if(node !=null && node.isParent){
                blockLevelId=node.id;
                console.log(blockLevelId)
            }else if(node==null){
                var preNode = sNodes[0].getParentNode();
                var childNode = preNode.getPreNode();
                if(childNode==null){
                    blockLevelId="";
                }else{
                    blockLevelId= childNode.id;
                    console.log(blockLevelId)
                }
            }else if(node.isParent==false){
                var preNode = node.getParentNode();
                var childNode = preNode.getPreNode();
                if(childNode==null){
                    blockLevelId="";
                }else{
                    blockLevelId= childNode.id;
                    console.log(blockLevelId)
                }
            }else if(node==null && node.isParent==false){
                var preNode = node.getParentNode();
                var childNode = preNode.getPreNode();
                if(childNode==null){
                    blockLevelId="";
                }else{
                    blockLevelId= childNode.id;
                    console.log(blockLevelId)
                }
            }
        }
        return blockLevelId
    }
    //默认加载表单select赋值
    function BlockOption(blockLevelId) {
        if(blockLevelId==null){
            $('#parentBlockId').find("option").remove();
            $('#parentBlockId').prop('disabled', true)
        }
        $.ajax({
            url: rootPath + "/action/S_composite_Block_findLevelById.action",
            type:"post",
            dataType:"json",
            data: {blockLevelId: blockLevelId},
            success: function (msg) {
                $('#parentBlockId').empty();
                $('#parentBlockId').prop('disabled', false);
                for( var i=0;i<msg.length;i++){
                    $('#parentBlockId').append("<option value='"+ msg[i].id +"'>" + msg[i].orgName +"</option>")
                }
            }
        });
}
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
        zTreeOnAsyncSuccess();
    });
});
/**============表单初始化相关代码============**/
//初始化表单验证
var ef = form.easyform({
    success:function (ef) {
        var entity = $("#scfForm").find("form").formSerializeObject();
        entity.attachmentIds = getAttachmentIds();
        entity.blockLevelId=ztreeId;
        entity.blockLevelName=ztreeName;
        if(blockLevelId==null){
            entity.parentBlockId="";
        }
        entity.parentBlockId=blockLevelId;
        saveAjax(entity,function (msg) {
            form.modal('hide');
            zTreeOnSuccess();
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
    zTreeOnSuccess();
    resetForm();
    if (!entity) {return false}
    form.find(".form-title").text("修改"+formTitle);
    var id = entity.id;
    $("#id").val(entity.id);
    $("#removeId").val("");
    $("#orgName").val(entity.orgName);
    $("#principal").val(entity.principal);
    $("#areaDesc").val(entity.areaDesc);
    $("#blockLevelId").val(entity.blockLevelId);
    $("#blockLeaderTel").val(entity.blockLeaderTel);
    $("#blockLeader").val(entity.blockLeader);

    $("#principalPhone").val(entity.principalPhone);
    $("#orgAddress").val(entity.orgAddress);
    $("#position").val(entity.position);
    $("#areaPoints").val(entity.areaPoints);

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
    form.find("input").attr("disabled",disabled);
}
/**
 * 重置表单
 */
function resetForm() {

    form.find(".form-title").text("新增"+formTitle);
    form.find("input[type!='radio'][type!='checkbox']").val("");
    $("textarea").val("");
    uploader = new qq.FineUploader(getUploaderOptions());
    BlockOption(blockLevelId);
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