var DemoPage = function () {
    var gridTable = $('#table'),
        removeBtn = $('#remove'),
        updateBtn = $('#update'),
        form = $("#demoForm"),
        formTitle = "指标任务";

    //保存ajax请求
    function saveAjax(entity, callback) {
        $.ajax({
            url: rootPath + "/action/S_office_CreateMode_save.action",
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
            url: rootPath + "/action/S_office_CreateMode_delete.action",
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
            url: rootPath+"/action/S_office_CreateMode_list.action?publishOrgId="+orgId,
            height: pageUtils.getTableHeight(),
            method:'post',
            pagination:true,
            clickToSelect:true,//单击行时checkbox选中
            queryParams:pageUtils.localParams,
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
                    title: '任务名称',
                    field: 'name',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '发布单位',
                    field: 'publishOrgName',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '发布时间',
                    field: 'publishTime',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '上报截止时间',
                    field: 'deadline',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '上报状态',
                    field: 'status',
                    editable: false,
                    sortable: false,
                    align: 'center',
                    formatter:function (value, row, index) {
                        if(value==1){
                            value="完成"
                        }else {
                            value="未完成"
                        }
                        return value;
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
        return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#demoForm">详情</button>';
    }
// 列表操作事件
    window.operateEvents = {
        'click .view': function (e, value, row, index) {
            var url = rootPath + "/container/gov/office/createModeDetailForInner.jsp?createModeId=" + row.id;
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

        $("#publishOrgName").val(orgName)
        $("#publishOrgId").val(orgId)
        $("#publishOrgName").attr("disabled",true)
    });
    $("#update").bind("click",function () {
        setFormData(getSelections()[0]);

        $("#publishOrgName").attr("disabled",true)
    });
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
                gridTable.bootstrapTable('remove', {
                    field: 'id',
                    values: ids
                });
                removeBtn.prop('disabled', true);
            });
        });


    });

    /**============列表搜索相关处理============**/
//搜索按钮处理
    $("#search").click(function () {
        var queryParams = $("#queryBox").find("form").formSerializeObject();
        gridTable.bootstrapTable('refresh',{
            query:queryParams
        });
    });

//初始化日期组件
    $('.form_datetime').datetimepicker({
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
            var entity = $("#demoForm").find("form").formSerializeObject();
            entity.publishOrgName=$("#publishOrgName").val()
            entity.status=0
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

    }
    function setFormView(entity) {
        setFormData(entity);
        form.find(".form-title").text("查看"+formTitle);
        disabledForm(true);
        form.find("#save").hide();
        form.find(".btn-cancel").text("关闭");
    }
    function disabledForm(disabled) {
        form.find("input").attr("disabled",disabled);
        form.find("textarea").attr("disabled",disabled);
        if (!disabled) {
            //初始化日期组件
            $('#createTimeContent').datetimepicker({
                language:   'zh-CN',
                autoclose: 1,
                minView: 2
            });
            $('#openDateContent').datetimepicker({
                language:   'zh-CN',
                autoclose: 1,
                minView: 2
            });
        }else{
            $('#createTimeContent').datetimepicker('remove');
            $('#openDateContent').datetimepicker('remove');
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
    }



}();

