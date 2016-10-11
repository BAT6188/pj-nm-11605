/**
 * Created by Administrator on 2016/10/9.
 */
/*添加按钮*/
$('#saveForm').click(function(){
    $('#enterpriseAddForm').ajaxSubmit({
        type: 'post', // 提交方式 get/post
        async:false,
        dataType:"json",
        url: rootPath+"/action/S_enterprise_Enterprise_save.action", // 需要提交的 url
        success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
            if(data.success){
                window.location.href = 'enterpriseListOfRun.jsp';
            }
        }
    });
});
/*重置按钮*/
$('#resetForm').click(function(){
    $('#enterpriseAddForm')[0].reset();
});
/*数据字典*/
var dictData = dict.getDctionnary({code:['registType','scale','affiliation','industrialPark']});
//var firstData = dict.getMultipleDctionnary({code:'industryType',parentCode:'-1'});
initIndestryType();
initArea();
initValley();
initRegistType();
initAffiliation();
initScale();
initIndustrialPark();
/*初始化行业类别 树结构*/
$(".scrollContent").slimScroll({
    height:"100%",
    railOpacity:.9,
    alwaysVisible:!1
});
function initIndestryType(){
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
            otherParam:{"code":"industryType"},
            dataFilter: null
        },
        callback: {
            onDblClick: zTreeOnDblClick
        }
    };
    function zTreeOnDblClick(event, treeId, treeNode) {
        if(treeNode.check_Child_State == -1){
            $('#industryType').val(treeNode.name);
            $('#industryTypeModalClose').trigger('click');
        }
    };
    var industryTypeTree = $.fn.zTree.init($("#industryTypeTree"), setting);
    $('#industryTypeModalSure').click(function(){
        var nodes = industryTypeTree.getSelectedNodes();
        var selectNode = nodes[0];
        if(selectNode.check_Child_State == -1){
            $('#industryType').val(selectNode.name);
            $('#industryTypeModalClose').trigger('click');
        }
    })
}
/*初始化行政区 树结构*/
function initArea(){
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
            otherParam:{"code":"area"},
            dataFilter: null
        },
        callback: {
            onDblClick: zTreeOnDblClick
        }
    };
    function zTreeOnDblClick(event, treeId, treeNode) {
        if(treeNode.check_Child_State == -1){
            $('#area').val(treeNode.name);
            $('#areaModalClose').trigger('click');
        }
    };
    var areaTree = $.fn.zTree.init($("#areaTree"), setting);
    $('#areaModalSure').click(function(){
        var nodes = areaTree.getSelectedNodes();
        var selectNode = nodes[0];
        if(selectNode.check_Child_State == -1){
            $('#area').val(selectNode.name);
            $('#areaModalClose').trigger('click');
        }
    })
}
/*初始化所属流域 树结构*/
function initValley(){
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
            otherParam:{"code":"valley"},
            dataFilter: null
        },
        callback: {
            onDblClick: zTreeOnDblClick
        }
    };
    function zTreeOnDblClick(event, treeId, treeNode) {
        if(treeNode.check_Child_State == -1){
            $('#valley').val(treeNode.name);
            $('#valleyModalClose').trigger('click');
        }
    };
    var valleyTree = $.fn.zTree.init($("#valleyTree"), setting);
    $('#valleyModalSure').click(function(){
        var nodes = valleyTree.getSelectedNodes();
        var selectNode = nodes[0];
        if(selectNode.check_Child_State == -1){
            $('#valley').val(selectNode.name);
            $('#valleyModalClose').trigger('click');
        }
    })
}
/*初始化工业园区名称*/
function initIndustrialPark(){
    var industrialParkData = dictData.industrialPark;
    var optionsHtml = '';
    $.each(industrialParkData,function(k,v){
        optionsHtml +='<option value="'+ v.code+'">'+ v.name+'</option>';
    })
    $('#industrialPark').append(optionsHtml);
}
/*初始化排污单位规模*/
function initScale(){
    var scaleData = dictData.scale;
    var optionsHtml = '';
    $.each(scaleData,function(k,v){
        optionsHtml +='<option value="'+ v.code+'">'+ v.name+'</option>';
    })
    $('#scale').append(optionsHtml);
}
/*初始化隶属关系*/
function initAffiliation(){
    var affiliationData = dictData.affiliation;
    var optionsHtml = '';
    $.each(affiliationData,function(k,v){
        optionsHtml +='<option value="'+ v.code+'">'+ v.name+'</option>';
    })
    $('#affiliation').append(optionsHtml);
}
/*初始化登记注册类型*/
function initRegistType(){
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
            return true;
            break;
        default:
            return true;
    }
}
/*查看信息*/
function lookEnterpriseForm(){

}
/*编辑信息*/
function editEnterpriseForm(){

}
/*获取企业信息*/
function setEnterpriseForm(){

}
