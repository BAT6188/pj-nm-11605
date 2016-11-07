//@ sourceURL=portstatus_form_view.js
var PortStatusFormView = function(){
    var dialog = {
        _modal:$("#portStatusViewDialog"),
        _modalBody:$("#portStatusViewDialog").find(".modal-body"),
        _uploaderContainer:document.getElementById("portStatusViewDialog-fine-uploader-gallery"),
        _title:"排口状态信息",
        _width:900,
        _height:500,
        init:function () {
            var that = this;
            //初始化dialog大小
            that._modal.find(".modal-dialog")
                .width(that._width);
            that._modalBody.height(pageUtils.getFormHeight(that._height));
            /**
             * 绑定下载按钮事件
             */
            $(that._uploaderContainer).on('click', '.qq-upload-download-selector', function () {
                var uuid = uploader.getUuid($(this.closest('li')).attr('qq-file-id'));
                window.location.href = rootPath+"/action/S_attachment_Attachment_download.action?id=" + uuid;
            });

        },
        setPortId:function (portId) {
            var that = this;
            var result = false;
            $.ajax({
                url: rootPath + "/action/S_port_PortStatusHistory_getLastByPortId.action",
                type:"post",
                async:false,
                data:{"portId":portId},
                dataType:"json",
                success:function (lastPortStatus) {
                    result = lastPortStatus;
                    if (lastPortStatus) {
                        var status = lastPortStatus.status;
                        if (status == "1") {
                            that._title = "超标信息"
                        }else if (status == "2") {
                            that._title = "异常信息"
                        }
                        that.setFormView(lastPortStatus);
                    }

                }
            });
            return result;
        },
        open:function () {
            this._modal.modal('show');
        },
        /**
         * 设置表单数据
         * @param entity
         * @returns {boolean}
         */
        setFormData: function(entity) {
            this.resetForm();
            if (!entity) {return false}
            this._modalBody.find(".form-title").text("修改"+this._title);
            var id = entity.id;
            var inputs = this._modalBody.find('.form-control');
            $.each(inputs,function(k,v){
                var tagId = $(v).attr('name');
                var value = entity[tagId];
                if($(v)[0].tagName=='select'){
                    $(v).find("option[value='"+value+"']").attr("selected",true);
                }else{
                    $(v).val(value);
                }
            });
            var radios = this._modalBody.find('.isRadio');
            $.each(radios,function(k,v){
                var tagId = $(v).attr('id');
                var value = entity[tagId];
                if (value) {
                    $("input#"+tagId+value).get(0).checked=true;
                }
            });
            uploader = new qq.FineUploader(this.getUploaderOptions(id));
        },
        setFormView:function (entity) {
            this.setFormData(entity);
            this._modalBody.find(".form-title").text("查看"+this._title);
            this.disabledForm(true);
            var fuOptions = this.getUploaderOptions(entity.id);
            fuOptions.callbacks.onSessionRequestComplete = function () {
                $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
            };
            uploader = new qq.FineUploader(fuOptions);
            $(".qq-upload-button").hide();
            $(this._uploaderContainer).find('.qq-uploader-selector').attr('qq-drop-area-text','暂无上传的附件');
        },
        disabledForm: function(disabled) {
            this._modalBody.find(".form-control").attr("disabled",disabled);
            this._modalBody.find('.isRadio input').attr("disabled",disabled);
        },
        /**
         * 重置表单
         */
        resetForm: function() {
            this._modalBody.find(".form-title").text("新增"+this._title);
            //form.find("input[type!='radio'][type!='checkbox']").val("");
            this._modalBody.find('form')[0].reset();
            uploader = new qq.FineUploader(this.getUploaderOptions());
            this.disabledForm(false);
        },
        /**
         * 获取上传组件options
         * @param bussinessId
         * @returns options
         */
        getUploaderOptions: function(bussinessId) {
            var that = this;
            return {
                element: that._uploaderContainer,
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


    };
    dialog.init();

    return dialog;
}();



