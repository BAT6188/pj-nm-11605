
    var gridTable = $('#table'),
        removeBtn = $('#remove'),
        updateBtn = $('#update'),
        form = $("#taskChildForm"),
        feedbackForm = $('#feedbackForm'),
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
            localParams.taskStatus = taskStatus;
            var jsonData = $('.queryBox').find('form').formSerializeObject();
            if(!$.isEmptyObject(jsonData)){
                $.extend(localParams,jsonData);
            }
            localParams.taskType=2;
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
                    field: 'topParentName',
                    editable: false,
                    sortable: false,
                    align: 'center',
                    formatter:function (value, row, index) {
                        return parentEntity.parentTaskName;
                    }
                },
                {
                    title: '任务类型',
                    field: 'parentTaskName',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '工作任务',
                    field: 'taskContent',
                    editable: false,
                    sortable: false,
                    align: 'center',
                    formatter:function (value, row, index) {
                        if(value.length>10){
                            value = value.substr(0,8)+'..';
                        }
                        return value;
                    }
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
                    title: '任务截止时间',
                    field: 'taskDeadline',
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
                /*{
                    title: '审核状态',
                    field: 'reviewStatus',
                    editable: false,
                    sortable: false,
                    visible:reviewerRSV,
                    align: 'center',
                    formatter:function (value, row, index) {
                        switch (value){
                            case '0':
                                return '待审核';
                            case '1':
                                return '有反馈需审核';
                            case '2':
                                return '审核通过';
                            case '3':
                                return '审核未通过';
                            default:
                                return '待审核';
                        }
                    }
                },*/
                {
                    title: '反馈列表',
                    field: 'reviewList',
                    editable: false,
                    sortable: false,
                    visible:taskRSV,
                    align: 'center',
                    events: {
                        'click .feedbackListView': function (e, value, row, index) {
                            initFeedbackTable(row.id);
                        }
                    },
                    formatter:function (value, row, index) {
                        return '<button type="button" class="btn btn-primary feedbackListView" data-toggle="modal" data-target="#feedbackListModal">查看列表</button>';
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
            removeBtn.prop('disabled', !(gridTable.bootstrapTable('getSelections').length==1));
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
        var html = '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#taskChildForm">查看</button>';
        if(role=='feedbacker'){
            html +='&nbsp;<button type="button" class="btn btn-primary feedbackView" data-toggle="modal" data-target="#feedbackForm">反馈</button>';
        }
        if(role=='reviewer'){
            if(row.reviewStatus==1){
                html +='&nbsp;<button type="button" class="btn btn-primary reviewView" data-toggle="modal" data-target="#reviewForm">审核</button>';
            }
        }
        return html;
    }
// 列表操作事件
    window.operateEvents = {
        'click .view': function (e, value, row, index) {
            setFormView(row);
        },
        'click .feedbackView': function (e, value, row, index) {
            resetFeedbackForm();
            $('#feedbackFormSaveBtn').show();
            feedbackEditEntity.taskId = row.id;
            feedbackEditEntity.feedbackerId = userId;
            feedbackEditEntity.feedbacker = userName;
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
        $('#parentTaskName').val(parentEntity.taskName);
        $('#dispatchDutyLeader').val(parentEntity.dispatchDutyLeader);
        $('#dispatchDutyDepartment').val(parentEntity.dispatchDutyDepartment);
        editEntity = {};
        editEntity.taskType = 2;
        editEntity.parentTaskId = parentEntity.id;
        editEntity.parentTaskName = parentEntity.taskName;
        editEntity.taskCreateDepartment = orgName;
        editEntity.taskCreateDepartmentCode = orgCode;
        editEntity.dispatchDutyLeaderId = parentEntity.dispatchDutyLeaderId;
        editEntity.dispatchDutyDepartmentCode = parentEntity.dispatchDutyDepartmentCode;
    });
    updateBtn.bind("click",function () {
        var entity = getSelections()[0];
        if(entity.taskStatus=='0'){
            setFormData(entity);
            editEntity = entity;
            form.modal('show');
        }else{
            Ewin.alert("任务已发布暂不可修改!")
        }
    });
    var editEntity = {};
    /**
     * 列表工具栏 删除按钮
     */
    removeBtn.click(function () {
        var entity = getSelections()[0];
        if(entity.taskStatus=='0'){
            Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
                if (!e) {
                    return;
                }
                deleteAjax(entity.id,function (msg) {
                    if(msg.success){
                        gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
                        removeBtn.prop('disabled', true);
                        Ewin.alert(msg.msg)
                    }else {
                        Ewin.alert(msg.msg)
                    }

                });
            });
        }else{
            Ewin.alert("任务已发布暂不可删除!")
        }
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
    var entityStatus = 0;
    var ef = form.easyform({
        success:function (ef) {
            var entity = form.find("form").formSerializeObject();
            if(!$.isEmptyObject(editEntity)){
                entity = $.extend({}, editEntity , entity || {});
            }
            entity.taskStatus = entityStatus;
            entity.attachmentIds = getAttachmentIds();
            saveAjax(entity,function (msg) {
                form.modal('hide');
                gridTable.bootstrapTable('refresh');
            });
        }
    });

//表单 保存按钮
    $("#save").bind('click',function () {
        entityStatus = 0;
        //验证表单，验证成功后触发ef.success方法保存数据
        ef.submit(false);
    });
    $('#publish').bind('click',function () {
        entityStatus = 1;
        //验证表单，验证成功后触发ef.success方法保存数据
        ef.submit(false);
    });
    /**
     * 设置表单数据
     * @param entity
     * @returns {boolean}
     */
    var uploader;
    function setFormData(entity) {
        resetForm();
        if (!entity) {return false}
        form.find(".form-title").text("修改"+formTitle);
        var id = entity.id;
        $("#removeId").val("");
        var inputs = form.find('.form-control');
        $.each(inputs,function(k,v){
            var tagId = $(v).attr('name');
            var value = entity[tagId];
            $(v).val(value);
        });
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
        form.find(".form-control").attr("disabled",disabled);
        if (!disabled) {
            //初始化日期组件
            $('.editDatetime').datetimepicker({
                language:'zh-CN',
                autoclose: 1,
                minView: 2,
                todayBtn: 1,
                todayHighlight: 1,
                pickerPosition:'bottom-left'
            });
            var beginDate = new Date();
            beginDate.addDays(1);
            $('.editDatetime').datetimepicker('setStartDate',beginDate.Format('yyyy-MM-dd hh:mm:ss'));
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

    /*----------------------------------------反馈--------------------------------------------*/
    var feedbackUploader;
    $('#feedbackFormSaveBtn').bind('click',function () {
        feedbackEditEntity.reviewStatus = 0;
        feedbackEf.submit(false);
    });
    $('#feedbackFormSubBtn').bind('click',function () {
        feedbackEditEntity.reviewStatus = 1;
        feedbackEf.submit(false);
    });
    function saveFeedbackAjax(entity, callback) {
        $.ajax({
            url: rootPath + "/action/S_office_TaskFeedBack_save.action",
            type:"post",
            data:entity,
            dataType:"json",
            success:callback
        });
    }
    function deleteFeedbackAjax(ids, callback) {
        $.ajax({
            url: rootPath + "/action/S_office_TaskFeedBack_delete.action",
            type:"post",
            data:$.param({deletedId:ids},true),//阻止深度序列化，向后台传递数组
            dataType:"json",
            success:callback
        });
    }
    var feedbackEditEntity={};
    var feedbackEf = feedbackForm.easyform({
        success:function (feedbackEf) {
            var entity = feedbackForm.find("form").formSerializeObject();
            if(!$.isEmptyObject(feedbackEditEntity)){
                entity = $.extend({}, feedbackEditEntity , entity || {});
            }
            entity.attachmentIds = getFeedbackAttachmentIds();
            saveFeedbackAjax(entity,function (msg) {
                feedbackForm.modal('hide');
                if(entity.id){
                    gridFeedbackTable.bootstrapTable('refresh');
                }else{
                    gridTable.bootstrapTable('refresh');
                }
                if(entity.reviewStatus==1){
                    var receivers = [];
                    var receiver1 = {receiverId: parentEntity.dispatchDutyLeaderId, receiverName: parentEntity.dispatchDutyLeader};
                    receivers.push(receiver1);
                    var Msg = {
                        'msgType': '22',
                        'title': '工作任务反馈需领导审核',
                        'content': '任务进展情况:'+entity.feedbackContent,
                        'businessId': msg.id
                    }
                    pageUtils.sendMessage(Msg, receivers);
                }
            });
        }
    });
    function setFeedbackFormData(entity) {
        resetFeedbackForm();
        if (!entity) {return false}
        feedbackForm.find(".form-title").text("修改"+formTitle);
        var id = entity.id;
        $("#removeId").val("");
        var inputs = feedbackForm.find('.form-control');
        $.each(inputs,function(k,v){
            var tagId = $(v).attr('name');
            var value = entity[tagId];
            $(v).val(value);
        });
        feedbackUploader = new qq.FineUploader(pageUtils.getUploaderOptions('feedback-uploader-gallery',id));
    }
    function setFeedbackFormView(entity) {
        setFeedbackFormData(entity);
        feedbackForm.find(".form-title").text("查看反馈");
        disabledFeedbackForm(true);
        feedbackForm.find("#feedbackFormSaveBtn").hide();
        feedbackForm.find(".btn-cancel").text("关闭");
        var fuOptions = pageUtils.getUploaderOptions('feedback-uploader-gallery',entity.id);
        fuOptions.callbacks.onSessionRequestComplete = function () {
            $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        };
        feedbackUploader = new qq.FineUploader(fuOptions);
        $(".qq-upload-button").hide();
        $("#feedback-uploader-gallery").find('.qq-uploader-selector').attr('qq-drop-area-text','暂无上传的附件');
    }
    function resetFeedbackForm() {
        disabledFeedbackForm(false);
        feedbackForm.find(".form-title").text("添加反馈");
        feedbackForm.find(".form-control").val("");
        feedbackForm.find("#feedbackFormSaveBtn").show();
        feedbackForm.find(".btn-cancel").text("取消");
        feedbackUploader = new qq.FineUploader(pageUtils.getUploaderOptions('feedback-uploader-gallery'));
    }

    function disabledFeedbackForm(disabled) {
        feedbackForm.find(".form-control").attr("disabled",disabled);
    }

    function getFeedbackAttachmentIds() {
        var attachments = feedbackUploader.getUploads();
        if (attachments && attachments.length) {
            var ids = [];
            for (var i = 0 ; i < attachments.length; i++){
                ids.push(attachments[i].uuid);
            }
            return ids.join(",");
        }
        return "";
    }
    var gridFeedbackTable = $('#feedbackTable'),isLoadFeedbackGrid = false;
    function initFeedbackTable(taskId) {
        gridFeedbackTable.taskId = taskId;
        if(isLoadFeedbackGrid){
            gridFeedbackTable.bootstrapTable('refreshOptions',{pageNumber:1});
        }else{
            isLoadFeedbackGrid = true;
            gridFeedbackTable.bootstrapTable({
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                sidePagination:"server",
                url: rootPath+"/action/S_office_TaskFeedBack_list.action",
                //height: 500,
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
                    localParams.taskId = gridFeedbackTable.taskId;
                    return localParams;
                },
                columns: [
                    {
                        title: '任务进展情况',
                        field: 'feedbackContent',
                        editable: false,
                        sortable: false,
                        align: 'center',
                        formatter:function (value, row, index) {
                            if(value.length>10){
                                value = value.substr(0,10)+'..';
                            }
                            return value;
                        }
                    },
                    {
                        title: '反馈时间',
                        field: 'feedbackTime',
                        editable: false,
                        sortable: false,
                        align: 'center'
                    },
                    {
                        title: '反馈人',
                        field: 'feedbacker',
                        editable: false,
                        sortable: false,
                        align: 'center'
                    },
                    {
                        title: '审核状态',
                        field: 'reviewStatus',
                        editable: false,
                        sortable: false,
                        visible:true,
                        align: 'center',
                        formatter:function (value, row, index) {
                            switch (value){
                                case '1':
                                    return '已提交审核';
                                case '2':
                                    return '审核通过';
                                case '3':
                                    return '审核未通过';
                                default:
                                    return '未提交审核';
                            }
                        }
                    },
                    {
                        field: 'operation',
                        title: '操作', //反馈人员
                        align: 'center',
                        visible:feedbackerRSV,
                        events: {
                            'click .subReviewView': function (e, value, row, index) {
                                feedbackEditEntity = row;
                                feedbackEditEntity.reviewStatus = 1;
                                feedbackEf.submit(false);
                            },
                            'click .editReviewView': function (e, value, row, index) {
                                setFeedbackFormData(row);
                                $('#reviewDiv').hide();
                                if(row.reviewStatus==0){
                                    $('#feedbackFormSaveBtn').show();
                                }else{
                                    $('#feedbackFormSaveBtn').hide();
                                }
                            },
                            'click .lookReviewView': function (e, value, row, index) {
                                setFeedbackFormView(row);
                                $('#reviewDiv').show();
                            },
                            'click .delteReviewView': function (e, value, row, index) {
                                Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
                                    if (!e) {
                                        return;
                                    }
                                    deleteFeedbackAjax(row.id,function(data){
                                        if(data.success){
                                            Ewin.alert('删除成功！');
                                            gridFeedbackTable.bootstrapTable('refresh');
                                        }
                                    });
                                });
                            }
                        },
                        formatter: function (value, row, index) {
                            var html = '';
                            switch (row.reviewStatus){
                                case '0':
                                    html = '<button type="button" class="btn btn-md btn-success subReviewView">提交审核</button>' +
                                        '&nbsp;<button type="button" class="btn btn-md btn-warning editReviewView" data-toggle="modal" data-target="#feedbackForm">修改</button>' +
                                        '&nbsp;<button type="button" class="btn btn-md btn-danger delteReviewView">删除</button>';
                                    break;
                                case '1':
                                    html = '<button type="button" class="btn btn-md btn-default">正在审核..</button>';
                                    break;
                                case '2':
                                    html = '<button type="button" class="btn btn-md btn-warning lookReviewView" data-toggle="modal" data-target="#feedbackForm">查看审核</button>';
                                    break;
                                case '3':
                                    html = '<button type="button" class="btn btn-md btn-warning editReviewView" data-toggle="modal" data-target="#feedbackForm">修改</button>';
                                    break;
                                default:
                                    html = '<button type="button" class="btn btn-md btn-warning editReviewView" data-toggle="modal" data-target="#feedbackForm">修改</button>';
                            }
                            return html;
                        }
                    },
                    {
                        field: 'reviewOperation',
                        title: '操作',//审核人员
                        align: 'center',
                        visible:reviewerRSV,
                        events: {
                            'click .reviewView': function (e, value, row, index) {
                                setFeedbackFormView(row);
                                feedbackEditEntity = row;
                                $('#reviewDiv').show();
                                $('.review').attr("disabled",false);
                            },
                            'click .lookReviewView': function (e, value, row, index) {
                                setFeedbackFormView(row);
                                $('#reviewDiv').show();
                            }
                        },
                        formatter: function (value, row, index) {
                            var html = '';
                            switch (row.reviewStatus){
                                case '0':
                                    html = '<button type="button" class="btn btn-md btn-default">未提交审核</button>';
                                    break;
                                case '1':
                                    html = '<button type="button" class="btn btn-md btn-success reviewView" data-toggle="modal" data-target="#feedbackForm">审核</button>';
                                    break;
                                case '2':
                                    html = '<button type="button" class="btn btn-md btn-warning lookReviewView" data-toggle="modal" data-target="#feedbackForm">查看</button>';
                                    break;
                                case '3':
                                    html = '<button type="button" class="btn btn-md btn-warning lookReviewView" data-toggle="modal" data-target="#feedbackForm">查看</button>';
                                    break;
                                default:
                                    html = '<button type="button" class="btn btn-md btn-default">未提交审核</button>';
                            }
                            return html;
                        }
                    }
                ]
            });
            // sometimes footer render error.
            setTimeout(function () {
                gridFeedbackTable.bootstrapTable('resetView');
            }, 200);

            $(window).resize(function () {
                // 重新设置表的高度
                gridFeedbackTable.bootstrapTable('resetView', {
                    height: pageUtils.getTableHeight()
                });
            });
        }
    }

