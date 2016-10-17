/**
 * Created by Administrator on 2016/10/9.
 */
var enterpriseForm =$('#enterpriseForm');
initZTree();
initSelect();
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
        MapMarkDialog.setMode("point");
        MapMarkDialog.open();
    });
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
    /*var selects = $('select');
    $.each(selects,function(k,v){
        var thisId = $(v).attr('id');
        var thisOptionLength = $(v)[0].options.length;
        if(thisOptionLength>12){
            $('#'+thisId).select2({
                language: "zh-CN",
                height:"20px",
                placeholder: "请选择",
                minimumResultsForSearch: 2,
                allowClear: true
            });
        }else{
            $('#'+thisId).select2({
                language: "zh-CN",
                placeholder: "请选择",
                minimumResultsForSearch: Infinity,
                allowClear: true
            });
        }
    })*/
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
                    console.log(treeNode);
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
}

//附件相关js
var uploader ;
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
            addEnterpriseForm();
            break;
        default:
            addEnterpriseForm();
    }
}
//初始化表单验证
var isEditBtnFromlook = false;
function saveForm(){
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
    setEnterpriseForm();
    setLookBtn();
}
/*新建*/
function addEnterpriseForm(){
    $('#headTitle').html('新增企业信息');
    uploader = new qq.FineUploader(getUploaderOptions(''));//附件上传组件对象
    initTimeInput();
    $('.addBtn').show();
    /*添加按钮*/
    $('#saveForm').click(function(){
        //验证表单，验证成功后触发ef.success方法保存数据
        $('#isDel').val('0');
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
    setEnterpriseForm();
    setEditBtn(false);
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
    var fuOptions = getUploaderOptions(enterpriseId);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
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
    var thisUrl = rootPath +'/container/gov/enterprise/basicInfo/enterpriseInfo.jsp?handleType=look&id='+enterpriseId;
    //$(".main-right").load(url);
    $('.main-right').html(pageUtils.loading()); // 设置页面加载时的loading图片
    $('.main-right').load(thisUrl); // ajax加载页面
}
/*显示并设置保存和编辑状态按钮*/
function setEditBtn(isFromEditBtn){
    uploader = new qq.FineUploader(getUploaderOptions(enterpriseId));//附件上传组件对象
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
        $('#resetEditForm').click(function(){
            reloadThisPage();
        });
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
function setEnterpriseForm(){
    $.ajax({
        url: rootPath + "/action/S_enterprise_Enterprise_getEnterpriseInfo.action",
        type:"post",
        data:{"id":id},//阻止深度序列化，向后台传递数组
        dataType:"json",
        success:function(data){
            var inputs = $('.form-control');
            $.each(inputs,function(k,v){
                var tagId = $(v).attr('id');
                var value = data[tagId];
                if($(v)[0].tagName=='select'){
                    $(v).find("option[value='"+value+"']").attr("selected",true);
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
    });
}
