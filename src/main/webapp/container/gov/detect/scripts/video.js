//@ sourceURL=video.js
var VideoPage = function () {
    var videoPage = {
        villageEnvId:undefined,
        /**
         * 获取列表所有的选中数据id
         * @returns {*}
         */
        getIdSelections: function () {
            return $.map(gridTableVideo.bootstrapTable('getSelections'), function (row) {
                return row.id
            });
        },

        /**
         *  获取列表所有的选中数据
         * @returns {*}
         */
        getSelections:function () {
            return $.map(gridTableVideo.bootstrapTable('getSelections'), function (row) {
                return row;
            });
        },
        /**
         * 获取上传组件options
         * @param bussinessId
         * @returns options
         */
        getUploaderOptionsVideo:function(bussinessId) {
        return {
            element: document.getElementById("video-fine-uploader-gallery"),
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
                    uploaderVideo.setUuid(id, msg.id);
                },
                onDeleteComplete:function (id) {
                    var file = uploaderVideo.getUploads({id:id});
                    var removeIds = $("#video_removeId").val();
                    if (removeIds) {
                        removeIds+= ("," + file.uuid)
                    }else{
                        removeIds = file.uuid;
                    }
                    $("#video_removeId").val(removeIds);
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
    };
    var gridTableVideo = $('#videoTable'),
        removeVideoBtn = $('#removeVideo'),
        updateVideoBtn = $('#updateVideo'),
        videoform = $("#videoForm"),
        formTitleVideo = "摄像头",
        selections = [];


//保存ajax请求
    function saveVideoAjax(entity, callback) {
        $.ajax({
            url: rootPath + "/action/S_composite_Video_save.action",
            type:"post",
            data:entity,
            success:callback
        });
    }
    /**
     * 删除请求
     * @param ids 多个,号分隔
     * @param callback
     */
    function deleteVideoAjax(ids, callback) {
        $.ajax({
            url: rootPath + "/action/S_composite_Video_delete.action",
            type:"post",
            data:$.param({deletedId:ids},true),//阻止深度序列化，向后台传递数组
            dataType:"json",
            success:callback
        });
    }
    /**============grid 列表初始化相关代码============**/
    function initVideoTable() {
        gridTableVideo.bootstrapTable({
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            sidePagination:"server",
            url: rootPath+"/action/S_composite_Video_list.action",
            height: pageUtils.getTableHeight(),
            method:'post',
            pagination:true,
            clickToSelect:true,//单击行时checkbox选中
            // queryParams:pageUtils.localParams,
            queryParams:function (param) {
                var temps = pageUtils.getBaseParams(param);
                if (videoPage.villageEnvId){
                    temps.unitId = videoPage.villageEnvId;
                }
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
                    title: '名称',
                    field: 'name',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '所属单位',
                    field: 'unitName',
                    editable: false,
                    sortable: false,
                    align: 'center'
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
            gridTableVideo.bootstrapTable('resetView');
        }, 200);

        //列表checkbox选中事件
        gridTableVideo.on('check.bs.table uncheck.bs.table ' +
            'check-all.bs.table uncheck-all.bs.table', function () {
            //有选中数据，启用删除按钮
            removeVideoBtn.prop('disabled', !gridTableVideo.bootstrapTable('getSelections').length);
            //选中一条数据启用修改按钮
            updateVideoBtn.prop('disabled', !(gridTableVideo.bootstrapTable('getSelections').length== 1));
        });

        $(window).resize(function () {
            // 重新设置表的高度
            gridTableVideo.bootstrapTable('resetView', {
                height: pageUtils.getTableHeight()
            });
        });
    }

// 生成列表操作方法
    function operateFormatter(value, row, index) {
        return '<button type="button" id="look" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#videoForm">查看</button>';
    }

// 列表操作事件
    window.operateEvents = {
        'click .view': function (e, value, row, index) {
            setVideoFormView(row);
        }
    };


    initVideoTable();
    /**============列表工具栏处理============**/
//初始化按钮状态
    removeVideoBtn.prop('disabled', true);
    updateVideoBtn.prop('disabled', true);
    /**
     * 列表工具栏 新增和更新按钮打开form表单，并设置表单标识
     */
    $("#addVideo").bind('click',function () {
        resetVideoForm();
        $("#videoForm").modal("show");
    });
    $("#updateVideo").bind("click",function () {
        setVideoFormData(videoPage.getSelections()[0]);
        $("#videoForm").modal("show");
    });
    /**
     * 列表工具栏 删除按钮
     */
    removeVideoBtn.click(function () {
        var ids = videoPage.getIdSelections();
        Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
            if (!e) {
                return;
            }
            deleteVideoAjax(ids,function (msg) {
                gridTableVideo.bootstrapTable('remove', {
                    field: 'id',
                    values: ids
                });
                updateVideoBtn.prop('disabled', true);
                removeVideoBtn.prop('disabled', true);
            });
        });
    });
    /**============列表搜索相关处理============**/
//搜索按钮处理
    $("#search").click(function () {
        var queryParams = {};
        var name = $("#s_name").val();
        var principal = $("#s_principal").val();
        if (name){
            queryParams["name"] = name;
        }
        if (principal){
            queryParams["principal"] = principal;
        }
        gridTableVideo.bootstrapTable('refresh',{
            query:queryParams
        });
    });

    /**============表单初始化相关代码============**/

//初始化表单验证
    var videEf = videoform.easyform({
        success:function (ef) {
            var entity = videoform.find("form").formSerializeObject();
            entity.attachmentIds = getAttachmentIdsVideo();
            entity.unitId=videoPage.villageEnvId;
            saveVideoAjax(entity,function (msg) {
                videoform.modal('hide');
                gridTableVideo.bootstrapTable('refresh');
            });
        }
    });

//表单 保存按钮
    $("#saveVideo").bind('click',function () {
        //验证表单，验证成功后触发ef.success方法保存数据
        videEf.submit(false);
    });
    $("#cancelVideoForm").bind('click',function () {
        $("#videoForm").modal("hide");
    });
    /**
     * 设置表单数据
     * @param entity
     * @returns {boolean}
     */
    function setVideoFormData(entity) {
        resetVideoForm();
        if (!entity) {return false}
        videoform.find(".form-title").text("修改"+formTitleVideo);
        $("#video_id").val(entity.id);
        $("#video_removeId").val("");
        $("#video_name").val(entity.name);
        $("#unitId").val(entity.unitId);
        $("#unitName").val(entity.unitName);
        $("#longitude").val(entity.longitude);
        $("#latitude").val(entity.latitude);
        uploaderVideo = new qq.FineUploader(videoPage.getUploaderOptionsVideo(entity.id));
    }
    function setVideoFormView(entity) {
        setVideoFormData(entity);
        videoform.find(".form-title").text("查看"+formTitleVideo);
        disabledForm(true);
        var fuOptions = videoPage.getUploaderOptionsVideo(entity.id);
        fuOptions.callbacks.onSessionRequestComplete = function () {
            $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
            $("#video-fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"");
        };
        uploaderVideo = new qq.FineUploader(fuOptions);
        $(".qq-upload-button").hide();
        videoform.find("#saveVideo").hide();
        videoform.find(".cancel").text("关闭");
    }
    function disabledForm(disabled) {
        videoform.find("input").attr("disabled",disabled);
        if (!disabled) {
            //初始化日期组件
            $('#pubTimeContent').datetimepicker({
                language:   'zh-CN',
                autoclose: 1,
                minView: 2
            });
        }else{
            $('#pubTimeContent').datetimepicker('remove');
        }

    }
    /**
     * 重置表单
     */
    function resetVideoForm() {
        videoform.find(".form-title").text("新增"+formTitleVideo);
        videoform.find("input[type!='radio'][type!='checkbox']").val("");
        $("textarea").val("");
        uploaderVideo = new qq.FineUploader(videoPage.getUploaderOptionsVideo());
        disabledForm(false);
        videoform.find("#saveVideo").show();
        videoform.find(".cancel").text("取消");
    }

    //表单附件相关js
    var uploaderVideo;//附件上传组件对象

    /**
     * 获取附件列表ids
     * @returns {*}
     */
    function getAttachmentIdsVideo() {
        var attachments = uploaderVideo.getUploads();
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
    $("#video-fine-uploader-gallery").on('click', '.qq-upload-download-selector', function () {
        var uuid = uploaderVideo.getUuid($(this.closest('li')).attr('qq-file-id'));
        window.location.href = rootPath+"/action/S_attachment_Attachment_download.action?id=" + uuid;
    });
    return videoPage;
}(jQuery);


