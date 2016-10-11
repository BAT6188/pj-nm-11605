/**
 * Created by Administrator on 2016/10/9.
 */
var dictData = dict.getDctionnary({code:['registType','scale','affiliation','industrialPark']});
var firstData = dict.getMultipleDctionnary({code:'industryType',parentCode:'-1'});
console.log(firstData);
setRegistType();
setAffiliation();
setScale();
setIndustrialPark();
/*初始化工业园区名称*/
function setIndustrialPark(){
    var industrialParkData = dictData.industrialPark;
    var optionsHtml = '';
    $.each(industrialParkData,function(k,v){
        optionsHtml +='<option value="'+ v.code+'">'+ v.name+'</option>';
    })
    $('#industrialPark').append(optionsHtml);
}
/*初始化排污单位规模*/
function setScale(){
    var scaleData = dictData.scale;
    var optionsHtml = '';
    $.each(scaleData,function(k,v){
        optionsHtml +='<option value="'+ v.code+'">'+ v.name+'</option>';
    })
    $('#scale').append(optionsHtml);
}
/*初始化隶属关系*/
function setAffiliation(){
    var affiliationData = dictData.affiliation;
    var optionsHtml = '';
    $.each(affiliationData,function(k,v){
        optionsHtml +='<option value="'+ v.code+'">'+ v.name+'</option>';
    })
    $('#affiliation').append(optionsHtml);
}
/*初始化登记注册类型*/
function setRegistType(){
    var registTypeData = dictData.registType;
    var optionsHtml = '';
    $.each(registTypeData,function(k,v){
        optionsHtml +='<option value="'+ v.code+'">'+ v.name+'</option>';
    })
    $('#registType').append(optionsHtml);
}

$('#datetimepicker1').datetimepicker({
    language:   'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 0
});
$('#datetimepicker2').datetimepicker({
    language:   'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 0
});
$('#datetimepicker3').datetimepicker({
    language:   'zh-CN',
    weekStart: 1,
    todayBtn:  1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 0
});

//附件相关js
var uploader = new qq.FineUploader(getUploaderOptions(''));//附件上传组件对象
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
