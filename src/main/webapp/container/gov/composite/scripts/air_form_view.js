//@ sourceURL=air_form_view.js
var AirFormViewDialog = function(){
    var modal=$("#airFormViewDialog"),
        modalBody=$("#airFormViewDialog").find(".modal-body"),
        uploaderContainer=document.getElementById("airFormViewDialog-fine-uploader-gallery"),
        width=900,
        height=220;
    function init() {
        //初始化dialog大小
        modal.find(".modal-dialog")
            .width(width);
        modalBody.height(pageUtils.getFormHeight(height));
        /**
         * 绑定下载按钮事件
         */
        // $(uploaderContainer).on('click', '.qq-upload-download-selector', function () {
        //     var uuid = uploader.getUuid($(this.closest('li')).attr('qq-file-id'));
        //     window.location.href = rootPath+"/action/S_attachment_Attachment_download.action?id=" + uuid;
        // });
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
            $(v).val(value);
        });
        var radios = modalBody.find('.isRadio');
        $.each(radios,function(k,v){
            var tagId = $(v).attr('id');
            var value = entity[tagId];
            if (value) {
                $("input#"+tagId+value).get(0).checked=true;
            }
        });
        // uploader = new qq.FineUploader(getUploaderOptions(id));
    }

    function setFormView(entity) {
        setFormData(entity);
        disabledForm(true);
        // var fuOptions = getUploaderOptions(entity.id);
        // fuOptions.callbacks.onSessionRequestComplete = function () {
        //     $(uploaderContainer).find(".qq-upload-delete").hide();
        // };
        // uploader = new qq.FineUploader(fuOptions);
        // $(".qq-upload-button").hide();
        // $(uploaderContainer).find('.qq-uploader-selector').attr('qq-drop-area-text','暂无上传的附件');
    }
    function disabledForm(disabled) {
        modalBody.find(".form-control").attr("disabled",disabled);
        // modalBody.find('.isRadio input').attr("disabled",disabled);
    }

    //初始化日期组件
    $('#createTimeContent').datetimepicker({
        language:   'zh-CN',
        autoclose: 1,
        startView: 2,//月视图
        minView: 2
    });
    /**
     * 重置表单
     */
    function resetForm() {
        //form.find("input[type!='radio'][type!='checkbox']").val("");
        modalBody.find('form')[0].reset();
        // uploader = new qq.FineUploader(getUploaderOptions());
        disabledForm(false);
    }

    function loadAirInfo(id) {
        $.ajax({
            url: rootPath + "/action/S_port_AirEquipment_load.action",
            type:"post",
            async:false,
            data:{"id":id},
            dataType:"json",
            success:function (airEquipment) {
                if (airEquipment) {
                    setFormView(airEquipment);
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
                        loadAirInfo(options['id']);
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
var AirHistoryDataFormViewDialog = function(){
    var modal=$("#airEquipmentForm"),
        modalBody=$("#airEquipmentForm").find(".modal-body"),
        width=1000,
        height=450;
    function init() {
        //初始化dialog大小
        modal.find(".modal-dialog").width(width);
        modalBody.height(pageUtils.getFormHeight(height));
    }
    var airEquipmentHistoryTable = $('#airEquipmentTable'),
        isLoadAirEquipmentHistoryTable = false;
    function loadAirEquipmentHistoryTable(airStationCode,airName){
        modal.find("#airName").html(airName);
        airEquipmentHistoryTable.monitoringNumber = airStationCode;
        if(isLoadAirEquipmentHistoryTable){
            airEquipmentHistoryTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:8});
        }else{
            isLoadAirEquipmentHistoryTable = true;
            airEquipmentHistoryTable.bootstrapTable({
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                sidePagination:"server",
                url: rootPath+"/action/S_port_AirEquipmentHistory_list.action",
                method:'post',
                pagination:true,
                pageSize:8,
                clickToSelect:true,//单击行时checkbox选中
                queryParams:function(params){
                    var localParams = {};
                    //分页参数
                    localParams.take = params.limit;
                    localParams.skip = params.offset;
                    if(params.offset){
                        localParams.page = params.offset / params.limit + 1;
                    }else{
                        localParams.page = 1;
                    }
                    localParams.pageSize = params.limit;
                    localParams.monitoringNumber = airEquipmentHistoryTable.monitoringNumber;
                    return localParams;
                },
                columns: [
                    {
                        title: 'ID',
                        field: 'id',
                        align: 'center',
                        valign: 'middle',
                        sortable: false,
                        visible:false
                    },
                    {
                        title: '空气质量监测点',
                        field: 'monitoringPosition',
                        editable: false,
                        sortable: false,
                        align: 'center'
                    },
                    {
                        title: '监测时间',
                        field: 'monitoringTime',
                        editable: false,
                        sortable: false,
                        align: 'center'
                    },
                    {
                        field: 'airIndex',
                        title: 'AQI指数',
                        sortable: false,
                        align: 'center',
                        editable: false
                    },
                    {
                        field: 'airQualityGrade',
                        title: '空气质量等级',
                        sortable: false,
                        align: 'center',
                        editable: false
                    },

                    {
                        field: 'primaryPollutant',
                        title: '首要污染物',
                        sortable: false,
                        align: 'center',
                        editable: false,
                    }
                ]
            });
        }
    }

    var dialog = {
        modal:function () {
            if (arguments.length == 1) {
                if (typeof arguments[0] == "string") {
                    modal.modal(arguments[0]);
                }
                if (typeof arguments[0] == "object") {
                    var options = arguments[0];
                    var id = options['id'],name=options['name'];
                    if (id) {
                        loadAirEquipmentHistoryTable(id,name);
                    }
                }

            }else if (arguments.length == 2) {
                modal.on(arguments[0],arguments[1]);
            }else{
                modal.modal("toggle");
            }
        }
    };
    //init();
    return dialog;
}();



