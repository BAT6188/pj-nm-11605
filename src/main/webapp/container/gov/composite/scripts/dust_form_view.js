//@ sourceURL=noise_form_view.js
var DustFormViewDialog = function(){
    var modal=$("#dustFormViewDialog"),
        modalBody=$("#dustFormViewDialog").find(".modal-body"),
        uploaderContainer=document.getElementById("dustFormViewDialog-fine-uploader-gallery"),
        width=900,
        height=570;
    function init() {
        //初始化dialog大小
        modal.find(".modal-dialog")
            .width(width);
        modalBody.height(pageUtils.getFormHeight(height));
        /**
         * 绑定下载按钮事件
         */
        $(uploaderContainer).on('click', '.qq-upload-download-selector', function () {
            var uuid = uploader.getUuid($(this.closest('li')).attr('qq-file-id'));
            window.location.href = rootPath+"/action/S_attachment_Attachment_download.action?id=" + uuid;
        });
    }
    /**
     * 获取上传组件options
     * @param bussinessId
     * @returns options
     */
    function getUploaderOptions(bussinessId) {
        return {
            element: uploaderContainer,
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
     * 设置表单数据
     * @param entity
     * @returns {boolean}
     */
    function setFormData(entity) {
        resetForm();
        if (!entity) {return false}
        var id = entity.id;
        var inputs = modalBody.find('.form-control');
        $.each(inputs,function(k,v){
            var tagId = $(v).attr('name');
            var value = entity[tagId];
            if($(v)[0].tagName=='select'){
                $(v).find("option[value='"+value+"']").attr("selected",true);
            }else{
                $(v).val(value);
            }
        });
        var radios = modalBody.find('.isRadio');
        $.each(radios,function(k,v){
            var tagId = $(v).attr('id');
            var value = entity[tagId];
            if (value) {
                $("input#"+tagId+value).get(0).checked=true;
            }
        });
        uploader = new qq.FineUploader(getUploaderOptions(id));
    }

    function setFormView(entity) {
        setFormData(entity);
        disabledForm(true);
        var fuOptions = getUploaderOptions(entity.id);
        fuOptions.callbacks.onSessionRequestComplete = function () {
            $(uploaderContainer).find(".qq-upload-delete").hide();
        };
        uploader = new qq.FineUploader(fuOptions);
        $(".qq-upload-button").hide();
        $(uploaderContainer).find('.qq-uploader-selector').attr('qq-drop-area-text','暂无上传的附件');
    }
    function disabledForm(disabled) {
        modalBody.find(".form-control").attr("disabled",disabled);
        modalBody.find('.isRadio input').attr("disabled",disabled);
    }
    /**
     * 重置表单
     */
    function resetForm() {
        //form.find("input[type!='radio'][type!='checkbox']").val("");
        modalBody.find('form')[0].reset();
        uploader = new qq.FineUploader(getUploaderOptions());
        disabledForm(false);
    }

    function loadNoiseInfo(id) {
        $.ajax({
            url: rootPath + "/action/S_port_DustPort_load.action",
            type:"post",
            async:false,
            data:{"id":id},
            dataType:"json",
            success:function (dustPort) {
                if (dustPort) {
                    setFormView(dustPort);
                }
            }
        });
    }
    var dialog = {
        modal:function () {
            if (arguments.length == 1) {
                if (typeof arguments[0] == "string") {
                    modal.modal(arguments[0]);
                }
                if (typeof arguments[0] == "object") {
                    var options = arguments[0];
                    if (options['id']) {
                        loadNoiseInfo(options['id']);
                    }
                }

            }else if (arguments.length == 2) {
                modal.on(arguments[0],arguments[1]);
            }else{
                modal.modal("toggle");
            }
        }
    };
    init();

    return dialog;
}();



