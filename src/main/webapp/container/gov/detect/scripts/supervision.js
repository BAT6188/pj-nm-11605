//@ sourceURL=supervision.js
var gridTable = $('#table'),
    addBtn = $('#add'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#scfForm"),
    formTitle = "网格人员",
    selections = [];

//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_composite_Block_save.action",
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
        url: rootPath + "/action/S_composite_Block_delete.action",
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
        url: rootPath + "/action/S_composite_Block_list.action",
        height: pageUtils.getTableHeight(),
        method: 'post',
        pagination: true,
        clickToSelect: true,//单击行时checkbox选中
        // queryParams:pageUtils.localParams,
        queryParams: function (param) {
            var temps = pageUtils.getBaseParams(param);
            temps.blockLevelId = ztreeId;
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
                title: '网格名称',
                field: 'orgName',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '管辖区域',
                field: 'orgAddress',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '上级网格',
                field: 'parentBlockId',
                sortable: false,
                align: 'center',
                editable: false,
                formatter: function (value, row, index) {
                    if (value == 1) {
                        value = "一级网格"
                    } else if (value == 3) {
                        value = "二级网格"
                    } else if (value == 4) {
                        value = "三级网格"
                    } else {
                        value = "东胜区"
                    }
                    return value
                }
            },
            {
                title: '网格长',
                field: 'blockLeader',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '联系方式',
                field: 'blockLeaderTel',
                sortable: false,
                align: 'center',
                editable: false
            },
            {
                title: '环保负责人',
                field: 'environmentalLeader',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '联系方式',
                field: 'environmentalPhone',
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
        'check-all.bs.table uncheck-all.bs.table load-success.bs.table', function () {
        resetToolbarBtnStatus();
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
function resetToolbarBtnStatus(){
    //有选中数据，启用删除按钮
    removeBtn.prop('disabled', !gridTable.bootstrapTable('getSelections').length);
    //选中一条数据启用修改按钮
    updateBtn.prop('disabled', !(gridTable.bootstrapTable('getSelections').length == 1));
    // addBtn.prop('disabled', !(ztreeId == "4"));
}
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
function showEditPointsBtn(blockLevel) {
    // if (blockLevel==4) {
        $('#lookPoints').hide();
        $('#editPoints').show();
    // }else{
    //     $('#lookPoints').show();
    //     $('#editPoints').hide();
    // }

}
/**
 * 列表工具栏 新增和更新按钮打开form表单，并设置表单标识
 */
$("#add").bind('click', function () {
    showEditPointsBtn(ztreeId);
    resetForm();
});
$("#update").bind("click", function () {
    var entity = getSelections()[0];
    showEditPointsBtn(ztreeId);
    setFormData(entity);
    setPointsMarkBtnByPoints(entity.areaPoints);

});

function setPointsMarkBtnByPoints(areaPoints) {
    // if (ztreeId == "4") {
        if(!areaPoints){
            setPointsMarkBtn('add');
        }else{
            setPointsMarkBtn('edit');
        }
    // }else{
    //     setPointsMarkBtn("look");
    // }
}
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
            zTreeOnSuccess();
        });
    });
});

$("#search").click(function () {
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
});
//重置搜索
$("#searchFix").click(function () {
    $('#searchform')[0].reset();
    gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
});
/**============表单初始化相关代码============**/
//初始化表单验证
var ef = form.easyform({
    success: function (ef) {
        var entity = $("#scfForm").find("form").formSerializeObject();
        entity.attachmentIds = getAttachmentIds();
        entity.blockLevelId = ztreeId;
        entity.blockLevelName = ztreeName;
        entity.parentBlockId = $("#parentBlockId").val();
        saveAjax(entity, function (msg) {
            form.modal('hide');
            gridTable.bootstrapTable('refresh');
            zTreeOnSuccess();
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
    $("#orgName").val(entity.orgName);
    $("#principal").val(entity.principal);
    $("#parentBlockId").val(entity.parentBlockId);
    $("#areaDesc").val(entity.areaDesc);
    $("#blockLevelId").val(entity.blockLevelId);
    $("#blockLeaderTel").val(entity.blockLeaderTel);
    $("#blockLeader").val(entity.blockLeader);
    $("#principalPhone").val(entity.principalPhone);
    $("#orgAddress").val(entity.orgAddress);
    $("#position").val(entity.position);
    $("#areaPoints").val(entity.areaPoints);
    $("#childBlockId").val(entity.childBlockId);

    uploader = new qq.FineUploader(getUploaderOptions(id));
}
function setFormView(entity) {
    setFormData(entity);
    $('#editPoints').hide();
    // setPointsMarkBtnByPoints(entity.areaPoints);
    setPointsMarkBtn("look");
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
}
/**
 * 重置表单
 */
function resetForm() {
    setPointsMarkBtn('add');
    form.find(".form-title").text("新增" + formTitle);
    form.find("input[type!='radio'][type!='checkbox']").val("");
    $("textarea").val("");
    uploader = new qq.FineUploader(getUploaderOptions());
    BlockOption(blockLevelId);
    ChildBlockOption(childBlockLevelId);
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
//地图标绘
/*$(function () {
    initMapBtn();
});*/
/*初始化标注按钮*/
function initMapBtn() {
        //绑定markDialog关闭事件
        MapMarkDialog.closed(function (mark) {
            if (mark) {
                $("#areaPoints").val(mark);
            } else {
                Ewin.alert({message: "请选择坐标"});
                return false;
            }
            if( $("#areaPoints").val()){
                setPointsCheckType(true);
                $('#editPoints').html("重新标绘");
            }
        });
        //设置标绘模式
        MapMarkDialog.setMode("polygon");
        MapMarkDialog.open();
}
function lookMapBtn() {
    var points=$("#areaPoints").val();
    var blockLevelId=$("#blockLevelId").val();
    //如果是二级网格获取管辖区域的所有坐标，并显示
    if (blockLevelId == "2") {
        points = loadChildrenBlockPoints($("#id").val());
    }
    if (points){
        MapMarkDialog.setMode(MapMarkDialog.MODE_VIEW,{type:MapMarkDialog.VIEW_POLYGON,"points":points});
        MapMarkDialog.open();
    }else{
        Ewin.alert({message: "未找到该网格坐标"});
    }

}
function loadChildrenBlockPoints(parentBlockId) {
    var points = [];
    $.ajax({//不为空，加载数据
        url: rootPath + "/action/S_composite_Block_findChildrenBlock.action",
        type: "post",
        async:false,
        dataType: "json",
        data: {"parentBlockId": parentBlockId},
        success: function (blocks) {
            if (blocks && blocks.length > 0){
                for(var i =0; i < blocks.length; i++) {
                    var block = blocks[i];
                    if (block.areaPoints) {
                        points.push(block.areaPoints);
                    }
                }
            }

        }
    });
    return points;
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

var ztreeId;
var ztreeName;
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
        url: rootPath + "/action/S_composite_BlockLevel_getBlock.action",
        autoParam: ["id"]
    },
    callback: {
        onAsyncSuccess: zTreeOnAsyncSuccess,
        onClick: zTreeOnClick
    }
};
$("#ztree").height($(window).height()-135);
$.fn.zTree.init($("#ztree"), setting);
//默认加载第一个节点
var firstAsyncSuccessFlag = 0;
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
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
        ztreeId = nodes[0].id;
    }
    selectChildZreeId();
    //刷新表格列表
    gridTable.bootstrapTable('refresh');
}



//点击节点事件
function zTreeOnClick(event, treeId, treeNode) {
    if (treeNode.isParent) {
        ztreeId = treeNode.id;
        ztreeName = treeNode.name;
    } else {
        var treeObj = $.fn.zTree.getZTreeObj("ztree");
        var sNodes = treeObj.getSelectedNodes();
        if (sNodes.length > 0) {
            var node = sNodes[0].getParentNode();
            ztreeId = node.id;
            ztreeName = node.name;
        }
    }
    selectZreeId();
    selectChildZreeId();
    gridTable.bootstrapTable('refresh');
}
//右侧列表添加数据刷新zTree
function zTreeOnSuccess() {
    var treeObj = $.fn.zTree.getZTreeObj("ztree");
    var node = treeObj.getSelectedNodes();
    if(node.length >0){
        var currentNode = node[0];
        if(currentNode.isParent==true){//级别节点，直接刷新
            var preNode = treeObj.getNodeByParam("id",currentNode.id );
            treeObj.selectNode(preNode,true);//指定选中ID的节点
            treeObj.expandNode(preNode, true, false);//指定选中ID节点展开
            treeObj.selectNode(preNode);
            treeObj.reAsyncChildNodes(preNode, "refresh");
        }else{//网格节点，刷新级别节点
            var preNode = node[0].getParentNode();//获取当前选中父节点
            var noda = treeObj.getNodeByParam("id",preNode.id );
            treeObj.selectNode(noda,true);//指定选中ID的节点
            treeObj.expandNode(noda, true, false);//指定选中ID节点展开
            treeObj.selectNode(noda);
            treeObj.reAsyncChildNodes(noda, "refresh");
        }
    }
    removeBtn.prop('disabled', true);
    updateBtn.prop('disabled', true);
}
//获取选中节点ID
var blockLevelId;
function selectZreeId() {
    var treeObj = $.fn.zTree.getZTreeObj("ztree");
    var sNodes = treeObj.getSelectedNodes();
    //获得当前节点的下级节点
    if (sNodes.length > 0) {
        //当前节点的前一个节点是子节点如果是子节点
        var node = sNodes[0].getPreNode();
        var preNode = sNodes[0].getParentNode();
        if (node != null && node.isParent) {
            blockLevelId = node.id;
            console.log(blockLevelId)
        } else if (node != null && node.isParent == false) {
            var preNode = sNodes[0].getParentNode();
            var childNode = preNode.getPreNode();
            if (childNode == null) {
                blockLevelId = "";
            } else {
                blockLevelId = childNode.id;
                console.log(blockLevelId)
            }
        } else if (node == null && sNodes[0].isParent) {
            blockLevelId = "";
            console.log(blockLevelId)
        } else if (node == null && sNodes[0].isParent == false) {
            var preNode = sNodes[0].getParentNode();
            if (preNode != null) {
                var childNode = preNode.getPreNode();
                if (childNode == null) {
                    blockLevelId = "";
                } else {
                    blockLevelId = childNode.id;
                    console.log(blockLevelId)
                }
            } else {
                blockLevelId = "";
            }
        }
    }
    return blockLevelId;
}
//默认加载表单select赋值
function BlockOption(blockLevelId) {
    if (blockLevelId == "" || blockLevelId==null) {//如果id为空，option设置默认值
        $('#parentBlockId').find("option").remove();
        $('#parentBlockId').append("<option value=''>东胜区</option>")
    } else {
        $.ajax({//不为空，加载数据
            url: rootPath + "/action/S_composite_Block_findLevelById.action",
            type: "post",
            async:false,
            dataType: "json",
            data: {blockLevelId: blockLevelId},
            success: function (msg) {
                $('#parentBlockId').empty();
                $('#parentBlockId').append("<option value=''>请选择</option>");
                if (msg && msg.length >0){
                    var is2Level = (msg[0].blockLevelId == "3");
                    for (var i = 0; i < msg.length; i++) {
                        $('#parentBlockId').append("<option value='" + msg[i].id + "'>" + (is2Level?msg[i].principal:msg[i].orgName) + "</option>")
                    }
                }

            }
        });
    }
}
//获取当前节点的下级节点
var childBlockLevelId;
function selectChildZreeId() {
    var treeObj = $.fn.zTree.getZTreeObj("ztree");
    var sNodes = treeObj.getSelectedNodes();
    //获得当前节点的下级节点
    if (sNodes.length > 0) {
        //当前节点的前一个节点是子节点如果是子节点
        var node = sNodes[0].getNextNode();
        if (node != null && node.isParent) {
            childBlockLevelId = node.id;
            console.log(blockLevelId)
        } else if (node != null && node.isParent == false) {
            var preNode = sNodes[0].getParentNode();
            var childNode = preNode.getNextNode();
            if (childNode == null) {
                childBlockLevelId = "";
            } else {
                childBlockLevelId = childNode.id;
                console.log(blockLevelId)
            }
        } else if (node == null && sNodes[0].isParent) {
            childBlockLevelId = "";
            console.log(blockLevelId)
        } else if (node == null && sNodes[0].isParent == false) {
            var preNode = sNodes[0].getParentNode();
            if (preNode != null) {
                var childNode = preNode.getNextNode();
                if (childNode == null) {
                    childBlockLevelId = "";
                } else {
                    childBlockLevelId = childNode.id;
                }
            } else {
                childBlockLevelId = "";
            }
        }
    }
    return childBlockLevelId;
}
//默认赋值表单select赋值
function ChildBlockOption(childBlockLevelId) {
    if (childBlockLevelId == "" || childBlockLevelId==null) {//如果id为空，option设置默认值
        $('#childBlockId').find("option").remove();
        $('#childBlockId').empty();
    } else {
        $.ajax({//不为空，加载数据
            url: rootPath + "/action/S_composite_Block_findLevelById.action",
            type: "post",
            async:false,
            dataType: "json",
            data: {blockLevelId: childBlockLevelId},
            success: function (msg) {
                $('#childBlockId').empty();
                // $('#childBlockId').append("<option value=''>请选择</option>");
                if (msg && msg.length >0){
                    for (var i = 0; i < msg.length; i++) {
                        $('#childBlockId').append("<option value='" + msg[i].id + "'>" + (msg[i].principal) + "</option>")
                    }
                }

            }
        });
    }
}
