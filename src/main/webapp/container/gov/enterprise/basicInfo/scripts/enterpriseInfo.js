/**
 * Created by Administrator on 2016/10/9.
 */
//@ sourceURL=enterpriseInfo.js
var enterpriseForm =$('#enterpriseForm');
setBlock('#blockLevelId','#blockId');
initZTree();
initSelect();
initMapBtn();
/*初始化标注按钮*/
// function initMapBtn(){
//     //绑定markDialog关闭事件
//     MapMarkDialog.closed(function (mark) {
//         if (mark) {
//             $("#longitude").val(mark.x);
//             $("#latitude").val(mark.y);
//         }else{
//             Ewin.alert({message:"请选择坐标"});
//             return false;
//         }
//     });
//     $('#mapMarkBtn').bind('click', function () {
//         //设置标绘模式
//         MapMarkDialog.setMode(MapMarkDialog.MODE_POINT);
//         MapMarkDialog.open();
//     });
// }

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


        var longitude = $("#longitude").val();
        var latitude =  $("#latitude").val();

        var punctuation = longitude + "," + latitude;

        var blockId = $("#blockId").val();
        var points = loadBlockPoints(blockId);

        var blockLevelId = $("#blockLevelId").val();
        // if (blockLevelId == "2") {
        //     points = loadChildrenBlockPoints(blockId);
        // }
        
        //设置标绘模式

        MapMarkDialog.setMode(MapMarkDialog.MODE_POINT,{
            type:MapMarkDialog.VIEW_POINT,
            "points":punctuation,
            backgroundOverlay:{
                type:MapMarkDialog.MODE_POLYGON,
                "points": points
            },
            coordinatePoint:{
                type:MapMarkDialog.VIEW_POINT,
                "points":punctuation
            }
        });
        MapMarkDialog.open();
    });
}


function loadBlockPoints(blockId) {
    var points;
    $.ajax({//不为空，加载数据
        url: rootPath + "/action/S_composite_Block_findBlockId.action",
        type: "post",
        async:false,
        dataType: "json",
        data: {"blockId": blockId},
        success: function (blocks) {
            if (blocks && blocks.length > 0){
                points = blocks[0].areaPoints;
            }

        }
    });
    return points;
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
            console.log(blocks);
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


/*初始化选择菜单*/
function initSelect(){
    /*数据字典*/
    var dictData = dict.getDctionnary({code:['registType','scale','affiliation','industrialPark']});
    $.each(dictData,function(k,v){
        var optionsHtml = '';
        $.each(v,function(i,obj){
            optionsHtml +='<option value="'+ obj.code+'">'+ obj.name+'</option>';
        })
        $('#'+k).append(optionsHtml);
    });
}
function setBlock(pSelector,cSelector){
    var pBlock = $(pSelector),cBlock = $(cSelector);
    var blockLevel = allBlockMap;
    if(blockLevel){
        $.each(blockLevel,function(k,v){
            pBlock.append($("<option>").val(v.id).text(v.name));
            var childData = v.blocks;
            $.each(childData,function(m,n){
                cBlock.append($("<option>").val(n.id).text(n.orgName+" ("+n.blockLeader+")"));
            });
        });
        pBlock.change(function(){
            var pid = $(this).val();
            var childData = allBlockMap[pid].blocks;
            cBlock.empty();
            $.each(childData,function(k,v){
                cBlock.append($("<option>").val(v.id).text(v.orgName+" ("+v.blockLeader+")"));
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
/*初始化 树结构*/
$(".scrollContent").slimScroll({
    height:"100%",
    railOpacity:.9,
    alwaysVisible:!1
});
function initZTree(){
    var treeCode = ['industryType','area','valley'];
    $.each(treeCode,function(k,v){
        var setting = {
            data: {
                simpleData: {
                    enable: true,
                    idKey: "code",
                    pIdKey: "parentCode",
                    rootPId: -1
                }
            },
            height:500,
            width:200,
            view: {
                showLine: false
            },
            async: {
                enable: true,
                url:rootPath + "/S_dict_Dict_multipleList.action",
                autoParam:["code"],
                otherParam:{"code":v},
                dataFilter: null
            },
            callback: {
                onDblClick: function(event, treeId, treeNode){
                    if(treeNode.check_Child_State == -1){
                        $('#'+v).val(treeNode.name);
                        $('#'+v+'ModalClose').trigger('click');
                    }
                }
            }
        };
        var zTree = $.fn.zTree.init($('#'+v+'Tree'), setting);
        $('#'+v+'ModalSure').click(function(){
            var nodes = zTree.getSelectedNodes();
            var selectNode = nodes[0];
            if(selectNode.check_Child_State == -1){
                $('#'+v).val(selectNode.name);
                $('#'+v+'ModalClose').trigger('click');
            }
        })
    })
}

function initTimeInput(){
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
}

//附件相关js
var uploader ;
var planeMapUploader ;
function getPlaneMapUploaderOptions(id) {
    return {
        element: document.getElementById("fine-uploader-planemap"),
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
                planeMapUploader.setUuid(id, msg.id);
            },
            onDeleteComplete:function (id) {
                var file = planeMapUploader.getUploads({id:id});
                deletePlaneMap[file.uuid] = true;
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
            endpoint: rootPath + '/Upload?type=planeMap&businessId='+id,
            paramsInBody:true,
            params: {
                businessId:id,
                type:"planeMap"
            }
        },
        session:{
            endpoint: rootPath + '/action/S_attachment_Attachment_listAttachment.action',
            params: {
                businessId:id,
                attachmentType:"planeMap"
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
            itemLimit: 10
        },
        debug: true
    };
}
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
            endpoint: rootPath + '/Upload?type=normalFile&businessId='+bussinessId,
            params: {
                businessId:bussinessId,
                type:"normalFile"
            }
        },
        session:{
            endpoint: rootPath + '/action/S_attachment_Attachment_listAttachment.action',
            params: {
                businessId:bussinessId,
                attachmentType:"normalFile"
            }
        },
        deleteFile: {
            enabled: true,
            endpoint: rootPath + "/action/S_attachment_Attachment_delete.action",
            method:"POST"
        },
        validation: {
            itemLimit: 3
        },
        debug: true
    };
}

function getAttachmentIds() {
    var ids = [];
    var attachments = uploader.getUploads();
    if (attachments && attachments.length) {
        for (var i = 0 ; i < attachments.length; i++){
            ids.push(attachments[i].uuid);
        }
    }
    var pattachments = planeMapUploader.getUploads();
    if(pattachments && pattachments.length){
        for (var i = 0 ; i < pattachments.length; i++){
            ids.push(pattachments[i].uuid);
        }
    }
    return ids.join(",");
}
var deletePlaneMap={};
function getplaneMapUploader(){
    var ids = "";
    var pattachments = planeMapUploader.getUploads();
    if(pattachments && pattachments.length){
        for (var i = 0 ; i < pattachments.length; i++){
            if(deletePlaneMap && deletePlaneMap[pattachments[i].uuid]==undefined){
                if(ids!=""){
                    ids +=","+pattachments[i].uuid;
                }else{
                    ids +=pattachments[i].uuid;
                }
            }
        }
    }
    return ids;
}
/**
 * 绑定下载按钮事件
 */
$("#fine-uploader-gallery").on('click', '.qq-upload-download-selector', function () {
    var uuid = uploader.getUuid($(this.closest('li')).attr('qq-file-id'));
    window.location.href = rootPath+"/action/S_attachment_Attachment_download.action?id=" + uuid;
});
$("#fine-uploader-planemap").on('click', '.qq-upload-download-selector', function () {
    var uuid = planeMapUploader.getUuid($(this.closest('li')).attr('qq-file-id'));
    window.location.href = rootPath+"/action/S_attachment_Attachment_download.action?id=" + uuid;
});

initEnterpriseForm(handleType);
/*根据操作内容初始化表单*/
function initEnterpriseForm(type){
    switch(type){
        case "look":
            lookEnterpriseForm();
            break;
        case "edit":
            editEnterpriseForm();
            break;
        case "add":
            addEnterpriseForm();
            break;
        default:
            addEnterpriseForm();
    }
}
//初始化表单验证
var isEditBtnFromlook = false;
function saveForm(){
    //$('#planeMap').val(getplaneMapUploader());
    $('#blockLevelName').val($("#blockLevelId").find("option:selected").text());
    $('#blockName').val($("#blockId").find("option:selected").text());
    $('#attachmentId').val(getAttachmentIds());
    if(checkForm('enterpriseForm')){
        $('#enterpriseForm').ajaxSubmit({
            type: 'post', // 提交方式 get/post
            async:false,
            dataType:"json",
            url: rootPath+"/action/S_enterprise_Enterprise_save.action", // 需要提交的 url
            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
                if(data.success){
                    if(isEditBtnFromlook){
                        resetEnterpriseData();
                        reloadThisPage();
                    }else{
                        pageUtils.loadPageOfContent('#level2content',enterpriseListOfRunUrl);
                    }
                }
            }
        });
    }else{
        return false;
    }
}
/*查看信息*/
function lookEnterpriseForm(){
    $('#headTitle').html('查看企业信息');
    setEnterpriseForm(true);
}
/*新建*/
function addEnterpriseForm(){
    $('#headTitle').html('新增企业信息');
    planeMapUploader = new qq.FineUploader(getPlaneMapUploaderOptions(''));
    uploader = new qq.FineUploader(getUploaderOptions(''));//附件上传组件对象
    initTimeInput();
    $('.addBtn').show();
    /*添加按钮*/
    $('#saveForm').click(function(){
        //验证表单，验证成功后触发ef.success方法保存数据
        $('#isDel').val('0');
        $('#haveFumesPort').val("0");
        saveForm();
        //ef.submit(false);
    });
    /*重置按钮*/
    $('#resetAddForm').click(function(){
        $('#enterpriseForm')[0].reset();
    });
    /*取消按钮*/
    $('#cancelAddForm').click(function(){
        pageUtils.loadPageOfContent('#level2content',enterpriseListOfRunUrl);
    });
}
/*编辑信息*/
function editEnterpriseForm(){
    $('#headTitle').html('编辑企业信息');
    setEnterpriseForm(false);
}
var enterpriseListOfRunUrl = rootPath +'/container/gov/enterprise/enterpriseAccount.jsp';
/*显示并设置查看状态按钮*/
function setLookBtn(){
    $("select").prop("disabled", true);
    $('.needshow').attr('readonly','readonly');
    $('.fieldset').attr('disabled','disabled');
    $('.formBtn').attr('disabled','disabled');
    $('.lookBtn').show();
    /*设置上传*/
    //$('#lookPlaneMapBtn').hide();
    var pfuOptions = getPlaneMapUploaderOptions(enterpriseData.id);
    pfuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-planemap").find(".qq-upload-delete").hide();
    };
    planeMapUploader = new qq.FineUploader(pfuOptions);
    $("#fine-uploader-planemap").find('.qq-uploader-selector').attr('qq-drop-area-text','暂无平面图');
    $("#fine-uploader-planemap").show();

    var fuOptions = getUploaderOptions(enterpriseData.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
    $("#fine-uploader-gallery").find('.qq-uploader-selector').attr('qq-drop-area-text','暂无上传的附件');
    /*上传*/
    $('#toEditForm').click(function(){
        $('#headTitle').html('编辑企业信息');
        setEditBtn(true);
    })
    $('#backList').click(function(){
        pageUtils.loadPageOfContent('#level2content',enterpriseListOfRunUrl);
    })
}
function reloadThisPage(){
    var thisUrl = rootPath +'/container/gov/enterprise/basicInfo/enterpriseInfo.jsp?handleType=look';
    //$(".main-right").load(url);
    $('.level3MenuContent').html(pageUtils.loading()); // 设置页面加载时的loading图片
    $('.level3MenuContent').load(thisUrl); // ajax加载页面
}
/*显示并设置保存和编辑状态按钮*/
function setEditBtn(isFromEditBtn){
    //$('#lookPlaneMapBtn').hide();
    $("#fine-uploader-planemap").show();
    planeMapUploader = new qq.FineUploader(getPlaneMapUploaderOptions(enterpriseData.id));
    uploader = new qq.FineUploader(getUploaderOptions(enterpriseData.id));//附件上传组件对象
    $('.lookBtn').hide();
    $('.editBtn').show();
    initTimeInput();
    if(isFromEditBtn){
        $("select").prop("disabled", false);
        $('.needshow').removeAttr('readonly');
        $('.fieldset').removeAttr('disabled');
        $('.formBtn').removeAttr('disabled');
        /*添加按钮*/
        $('#editForm').click(function(){
            isEditBtnFromlook = true;
            saveForm();
            //ef.submit(false);
        });
        /*重置按钮*/
        $('#resetEditForm').hide();
        /*取消按钮*/
        $('#cancelEditForm').click(function(){
            reloadThisPage();
        })
    }else{
        /*添加按钮*/
        $('#editForm').click(function(){
            saveForm();
            //ef.submit(false);
        });
        /*重置按钮*/
        $('#resetEditForm').click(function(){
            $('#enterpriseForm')[0].reset();
        });
        /*取消按钮*/
        $('#cancelEditForm').click(function(){
            pageUtils.loadPageOfContent('#level2content',enterpriseListOfRunUrl);
        });
    }
}
/*获取企业信息*/
function setEnterpriseForm(flag){
    var data=enterpriseData;
    if(flag){
        setLookBtn();
    }else{
        setEditBtn(false);
    }
    var inputs = $('.form-control');
    $.each(inputs,function(k,v){
        var tagId = $(v).attr('id');
        var value = data[tagId];
        if(v.tagName=='SELECT'){
            if(tagId=="blockLevelId" && value!=null){
                var childData = allBlockMap[value].blocks;
                // $('#blockId').empty();
                $.each(childData,function(k,v){
                    $('#blockId').append($("<option>").val(v.id).text(v.orgName+" ("+v.blockLeader+")"));
                });
            }
            $(v).find("option[value='"+value+"']").attr("selected",true);
            console.log($(v).find("option[value='"+value+"']").attr("selected",true));
        }else{
            $(v).val(value);
        }
    });
    $("input#isSpecial"+data.isSpecial).get(0).checked=true;
    $("input#pollutantLevel"+data.pollutantLevel).get(0).checked=true;
    var pollutantTypes = data.pollutantType.split(',');
    $.each(pollutantTypes,function(k,v){
        $("input#pollutantType"+ v.replace(/\s/g,'')).attr("checked", true);
    })
}
/**
 * 查看平面图
 */
function lookPlaneMap(){
    PlottingDialog.dialog({
        show:true,
        mode:"view",
        attachmentId:$('#planeMap').val()
    });
}

function resetEnterpriseData(){
    $.ajax({
        url: rootPath + "/action/S_enterprise_Enterprise_getEnterpriseInfo.action",
        type:"post",
        async:false,
        data:{"id":id},//阻止深度序列化，向后台传递数组
        dataType:"json",
        success:function(data){
            enterpriseData = data;
        }
    });
}

$("#backList").bind('click',function(){
    $(".modal-backdrop").hide();

});
