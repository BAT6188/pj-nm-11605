var DemoPage = function () {
    var gridTable = $('#table'),
        removeBtn = $('#remove'),
        updateBtn = $('#update'),
        form = $("#taskChildForm"),
        formTitle = "任务类型";

    //保存ajax请求
    function saveAjax(entity, callback) {
        $.ajax({
            url: rootPath + "/action/S_office_Task_save.action",
            type:"post",
            data:entity,
            dataType:"json",
            success:callback
        });
    }
    /**
     * 删除请求
     * @param ids 多个,号分隔
     * @param callback
     */
    function deleteAjax(ids, callback) {
        $.ajax({
            url: rootPath + "/action/S_office_Task_delete.action",
            type:"post",
            data:$.param({deletedId:ids},true),//阻止深度序列化，向后台传递数组
            dataType:"json",
            success:callback
        });
    }
    /**============grid 列表初始化相关代码============**/
    function initTable() {
        gridTable.bootstrapTable({
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            sidePagination:"server",
            url: rootPath+"/action/S_office_Task_list.action",
            height: pageUtils.getTableHeight(),
            method:'post',
            pagination:true,
            clickToSelect:true,//单击行时checkbox选中
            queryParams:function localParams(params) {
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
            var jsonData = $('.queryBox').find('form').formSerializeObject();
            if(!$.isEmptyObject(jsonData)){
                $.extend(localParams,jsonData);
            }
            localParams.taskType=1;
            localParams.parentTaskId=parentTaskId;
            return localParams;
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
                    title: '年度任务',
                    field: 'parentTaskName',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '任务类型',
                    field: 'taskName',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '发布单位',
                    field: 'taskCreateDepartment',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '发布时间',
                    field: 'taskPubTime',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '描述',
                    field: 'taskRemark',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '状态',
                    field: 'taskStatus',
                    editable: false,
                    sortable: false,
                    align: 'center',
                    formatter:function (value, row, index) {
                        switch (value){
                            case '0':
                                return '未发布';
                            case '1':
                                return '未完成';
                            case '2':
                                return '已完成';
                            case '3':
                                return '已办结';
                            default:
                                return '未发布';
                        }
                    }
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
            'check-all.bs.table uncheck-all.bs.table', function () {
            //有选中数据，启用删除按钮
            removeBtn.prop('disabled', !gridTable.bootstrapTable('getSelections').length);
            //选中一条数据启用修改按钮
            updateBtn.prop('disabled', !(gridTable.bootstrapTable('getSelections').length== 1));
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
        return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#taskForm">详情</button>';
    }
// 列表操作事件
    window.operateEvents = {
        'click .view': function (e, value, row, index) {
            var url = rootPath + "/container/gov/office/taskChild.jsp?parentTaskId=" + row.id;
            pageUtils.toUrl(url);
        }
    };
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
    /**
     * 列表工具栏 新增和更新按钮打开form表单，并设置表单标识
     */
    $("#add").bind('click',function () {
        resetForm();
        editEntity = {};
        $("#taskCreateDepartment").val(orgName);
        $("#dispatchDutyDepartmentCode").val(orgCode);
        $("#taskCreateDepartment").attr("readonly",true)
    });
    updateBtn.bind("click",function () {
        var entity = getSelections()[0];
        setFormData(entity);
        editEntity = entity;
        $("#taskCreateDepartment").attr("readonly",true)
    });
    var editEntity = {};
    /**
     * 列表工具栏 删除按钮
     */
    removeBtn.click(function () {
        var ids = getIdSelections();
        Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
            if (!e) {
                return;
            }
            deleteAjax(ids,function (msg) {
                if(msg.success){
                    gridTable.bootstrapTable('remove', {
                        field: 'id',
                        values: ids
                    });
                    removeBtn.prop('disabled', true);
                    Ewin.alert(msg.msg)
                }else {
                    Ewin.alert(msg.msg)
                }

            });
        });
    });

    /**============列表搜索相关处理============**/
//搜索按钮处理
    $("#search").click(function () {
        gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
    });
//重置搜索
    $("#searchFix").click(function () {
        resetQuery();
        gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
    });

//初始化日期组件
    $('.searDate').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });

    /**============表单初始化相关代码============**/

//初始化表单验证
    var ef = form.easyform({
        success:function (ef) {
            var entity = form.find("form").formSerializeObject();
            if(!$.isEmptyObject(editEntity)){
                $.extend({}, editEntity , entity || {});
            }
            entity.taskType = 1;
            entity.parentTaskId = parentTaskId;
            entity.status=0;
            entity.attachmentIds = getAttachmentIds();
            saveAjax(entity,function (msg) {
                form.modal('hide');
                gridTable.bootstrapTable('refresh');
            });
        }
    });

//表单 保存按钮
    $("#save").bind('click',function () {
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
        if (!entity) {return false}
        form.find(".form-title").text("修改"+formTitle);
        var id = entity.id;
        $("#removeId").val("");
        for(p in entity){
            var selector="#"+p
            $(selector).val(entity[p])
        }
        uploader = new qq.FineUploader(pageUtils.getUploaderOptions('fine-uploader-gallery',id));
    }
    function setFormView(entity) {
        setFormData(entity);
        form.find(".form-title").text("查看"+formTitle);
        disabledForm(true);
        form.find("#save").hide();
        form.find(".btn-cancel").text("关闭");
        var fuOptions = pageUtils.getUploaderOptions('fine-uploader-gallery',entity.id);
        fuOptions.callbacks.onSessionRequestComplete = function () {
            $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        };
        uploader = new qq.FineUploader(fuOptions);
        $(".qq-upload-button").hide();
        $("#fine-uploader-gallery").find('.qq-uploader-selector').attr('qq-drop-area-text','暂无上传的附件');
    }
    function disabledForm(disabled) {
        form.find("input").attr("disabled",disabled);
        form.find("textarea").attr("disabled",disabled);
        if (!disabled) {
            //初始化日期组件
            $('.editDatetime').datetimepicker({
                language:'zh-CN',
                autoclose: 1,
                minView: 2,
                pickerPosition:'bottom-left'
            });
        }else{
            $('.editDatetime').datetimepicker('remove');
        }

    }
    /**
     * 重置表单
     */
    function resetForm() {
        form.find(".form-title").text("新增"+formTitle);
        form.find("input[type!='radio'][type!='checkbox']").val("");
        form.find("textarea").val("");
        disabledForm(false);
        form.find("#save").show();
        form.find(".btn-cancel").text("取消");
        uploader = new qq.FineUploader(pageUtils.getUploaderOptions('fine-uploader-gallery'));
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
}();

