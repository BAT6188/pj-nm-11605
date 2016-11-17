var gridTable = $('#table'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#demoForm"),
    formTitle = "Demo",
    selections = [];



//保存表单ajax请求
function saveAjax(entity, callback) {
    $.ajax({
        url: rootPath + "/action/S_exelaw_PollutantPayment_save.action",
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
        url: rootPath + "/action/S_exelaw_PollutantPayment_delete.action",
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
        url: rootPath+"/action/S_exelaw_PollutantPayment_list.action?enterpriseId="+enterpriseId,
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
                field: 'enterpriseId',
                editable: false,
                sortable: false,
                align: 'center',
                visible:false
            },
            {
                title: '企业名称',
                field: 'enterpriseName',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '企业法人',
                field: 'enterpriseAP',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '联系方式',
                field: 'apPhone',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '缴费金额',
                field: 'payMoney',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '登记日期',
                field: 'registDate',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '缴费日期',
                field: 'payDate',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '距缴费日期',
                field: 'rangeDays',
                editable: false,
                sortable: false,
                align: 'center'
            },
            {
                title: '缴费状态',
                field: 'paymentStatus',
                sortable: false,
                align: 'center',
                editable: false,
                formatter:function (value, row, index) {
                    if (0==value){
                        value="未缴费"
                    }else if(1==value){
                        value="已缴费"
                    }else if(2==value){
                        value="未按时缴费"
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
    return '<button type="button" class="btn btn-md btn-warning view" data-toggle="modal" data-target="#demoForm">查看</button>';
}
// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, row, index) {
        setFormView(row);
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
});
$("#update").bind("click",function () {
    setFormData(getSelections()[0]);
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
    var queryParams = {};
    var enterpriseName = $("#s_enterpriseName").val();
    var paymentStatus = $("#s_paymentStatus").val();
    if (enterpriseName){
        queryParams["enterpriseName"] = enterpriseName;
    }
    if (paymentStatus){
        queryParams["paymentStatus"] = paymentStatus;
    }
    search(queryParams)
});

function search(queryParams) {
    gridTable.bootstrapTable('refresh',{
        query:queryParams
    });
}



/**
 * 按时间查询表单
 * @type {*|jQuery|HTMLElement}
 */
var yearUl = $('#year');

var year = new Date().getFullYear();
for ( var i = year; i >=2014; i--) {
    $("<li class='year'><a href='#'>" + i + "</a></li>").appendTo(yearUl);
}

var dropdownMenu = $("#dropdownMenu");
//按年份查询
dropdownMenu.find("#year .year").bind('click',function(){
    var year = parseInt($(this).text());
    $('#selYear').text(year);
    var startYdate = year +　'-'+'01' + '-'+'01';
    var lastYdate = year + '-'+ '12' + '-'+ '31';
    search({
        startYdate:startYdate,
        lastYdate:lastYdate

    });
});

//按季度查询
dropdownMenu.find(".tm").bind('click',function(){
    var seasons= $(this).text();
    var selYear = $('#selYear').text();
    if(selYear == "年份"){
        Ewin.confirm({ message: "请选择年份!" });
        return ;
    }
    if(seasons == "第一季度"){
        var startYdate = selYear + '-' +'01' + '-' + '01' ;
        var lastYdate = selYear + '-' + '03' + '-' + '31';
    }else if(seasons == "第二季度"){
        var startYdate= selYear + '-' + '04' + '-' + '01';
        var lastYdate = selYear + '-' + '06' + '-' + '30';
    }else if(seasons == "第三季度"){
        var startYdate = selYear + '-' + '07' + '-'+ '01';
        var lastYdate = selYear + '-' + '09' + '-' + '30';
    }else if(seasons = "第四季度"){
        var startYdate = selYear + '-' + '10' + '-' + '01';
        var lastYdate = selYear + '-' + '12' + '-'+ '31';
    }
    // search(startSdate, lastSdate);
    search({
        startYdate:startYdate,
        lastYdate:lastYdate

    });
});

//按月份查询
dropdownMenu.find("li[class='month']").bind("click", function() {
    var mNum = parseInt(this.value);
    var m = mNum > 9 ? mNum : ("0"+mNum);
    var selYear = $('#selYear').text();
    if(selYear == "年份"){
        Ewin.confirm({ message: "请选择年份!" });
        return;
    }
    var  day = new Date(selYear,m,0);
    var startYdate = selYear +'-'+ m + '-'+'01';
    var lastYdate = selYear + '-' + m + '-' + day.getDate();//获取当月最后一天日期
    search({
        startYdate:startYdate,
        lastYdate:lastYdate
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
            entity.enterpriseName=$("#enterpriseName").val();
            entity.enterpriseId=$("#enterpriseId").val();
            entity.enterpriseAP=$("#enterpriseAP").val();

        entity.attachmentIds = getAttachmentIds();
        console.info("保存表单数据："+JSON.stringify(entity))
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
    var id = entity.id;
    $("#id").val(entity.id);
    $("#removeId").val("");

    $("#enterpriseName").val(entity.enterpriseName);
    $("#enterpriseId").val(entity.enterpriseId);

    $("#enterpriseAP").val(entity.enterpriseAP);
    $("#apPhone").val(entity.apPhone);
    $("#payMoney").val(entity.payMoney);

    $("#registDate").val(entity.registDate);
    $("#payDate").val(entity.payDate);
    $("#alertDate").val(entity.alertDate);
    $("#realertDate").val(entity.realertDate);
    $("#paymentStatus").val(entity.paymentStatus);
    $("#remark").val(entity.remark);

    uploader = new qq.FineUploader(getUploaderOptions(id));
}
function setFormView(entity) {
    setFormData(entity);
    disabledForm(true);
    var fuOptions = getUploaderOptions(entity.id);
    fuOptions.callbacks.onSessionRequestComplete = function () {
        $("#fine-uploader-gallery").find(".qq-upload-delete").hide();
        $("#fine-uploader-gallery").find("[qq-drop-area-text]").attr('qq-drop-area-text',"");
    };
    uploader = new qq.FineUploader(fuOptions);
    $(".qq-upload-button").hide();
    form.find("#save").hide();
    form.find(".btn-cancel").text("关闭");
}
/**
 * 禁用表单
 * @param disabled  true,false
 */
function disabledForm(disabled) {
    form.find("input").attr("disabled",disabled);
    form.find("select").attr("disabled",disabled);
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
    form.find("input[type!='radio'][type!='checkbox']").val("");
    form.find("textarea").val("");
    uploader = new qq.FineUploader(getUploaderOptions());
    disabledForm(false);

    $(".noEdit").attr("disabled",true)
    $.ajax({
        url: rootPath + "/action/S_enterprise_Enterprise_getEnterpriseInfoById.action",
        type:"post",
        data:{enterpriseId:enterpriseId},
        success:function (entity) {
            var entity = JSON.parse(entity);
            console.log(entity)
            $("#enterpriseName").val(entity.name)
            $("#enterpriseId").val(entity.id)
            $("#enterpriseAP").val(entity.artificialPerson)
        }
    });

    form.find("#save").show();
    form.find(".btn-cancel").text("取消");
}

//表单附件相关js
var uploader;//附件上传组件对象
/**
 * 获取上传组件options
 * @param bussinessId
 * @returns options
 */
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
            itemLimit: 5
        },
        debug: true
    };
}
/**
 * 获取附件列表ids
 * @returns {*}
 */
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

/**
 * Autocomplete
 */
$( function() {

    $("#enterpriseName").autocomplete({
        source: function( request, response ) {
            $.ajax( {
                url: rootPath + "/action/S_enterprise_Enterprise_list.action",
                method:'post',
                dataType: "json",
                data: {
                    name: request.term
                },
                success: function( data ) {
                    for(var i = 0;i<data.rows.length;i++){
                        var result = [];
                        for(var i = 0; i <  data.rows.length; i++) {
                            var ui={};
                            ui.id=data.rows[i].id
                            ui.value=data.rows[i].name
                            result.push(ui);
                        }
                        response( result);
                    }
                }
            } );
        },
        select: function( event, ui ) {
            console.info(ui.item.id)
            $("#enterpriseId").val(ui.item.id)
        },
    } );
} );


