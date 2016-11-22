/**
 * Created by Administrator on 2016/10/21.
 */
var formDiv = $('#otherEnvFormDiv'),
    formTitle = "废水排口",
    updateSuccessMsg = "保存成功！"
    otherEnvEntity={};
loadInfo();
function loadInfo(){
    $.ajax({
        url: rootPath + "/action/S_enterprise_OtherEnv_list.action",
        type:"post",
        async:false,
        data:{"enterpriseId":enterpriseId},//阻止深度序列化，向后台传递数组
        dataType:"json",
        success:function(data){
            if(data.total>0){
                otherEnvEntity = data.rows[0];
                setFormEntity(otherEnvEntity);
                setLookType();
            }else{
                setEditType();
            }
        }
    });
}
function setFormEntity(entity){
    $('#OtherEnvForm').find('.form-control').each(function(){
        $(this).val(entity[this.name]);
    });
    if(entity.isISO){
        $("input#isISO"+entity.isISO).get(0).checked=true;
    }
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
    $("#fine-uploader-gallery").find('.qq-uploader-selector').attr('qq-drop-area-text','暂无上传的附件');
}
$('#toEditForm').click(function(){
    setEditType();
});
function setEditType(){
    $('#firstInput').focus();
    $('#toEditForm').hide();
    $('.isRadio').find('input').attr('disabled',false);
    $('.form-control').removeAttr('readonly');
    $('.editBtn').show();
    uploader = new qq.FineUploader(getUploaderOptions(otherEnvEntity.id));
}
function setLookType(){
    //$('#firstInput').focus();
    $('#toEditForm').show();
    $('.isRadio').find('input').attr('disabled',true);
    $('.form-control').attr('readonly','readonly');
    $('.editBtn').hide();
}
//初始化表单验证
var ef = formDiv.easyform({
    success:function (ef) {
        var entity = formDiv.find("form").formSerializeObject();
        entity.enterpriseId=enterpriseId;
        entity.attachmentId = getAttachmentIds();
        saveAjax(entity,function (msg) {
            $(".modal").modal('hide');
            Ewin.alert(updateSuccessMsg);
            setLookType();
        });
    }
});
$("#saveForm").bind('click',function () {
    //验证表单，验证成功后触发ef.success方法保存数据
    ef.submit(false);
});
$('#resetEditForm').click(function(){
    formDiv.find('form').each(function(){
        $(this)[0].reset();
    })
})
$('#cancelEditForm').click(function(){
    setFormEntity(otherEnvEntity);
    setLookType();
})

//保存ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_enterprise_OtherEnv_save.action",
        type:"post",
        data:entity,
        dataType:"json",
        success:callback
    });
}
var thisUrl = rootPath +'/container/gov/enterprise/otherEnv.jsp';
function reloadThisPage(){
    $('.main-right').html(pageUtils.loading()); // 设置页面加载时的loading图片
    $('.main-right').load(thisUrl); // ajax加载页面
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
            itemLimit: 3
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
