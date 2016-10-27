var gridTable = $('#table'),
    add = $('#add'),
    removeBtn = $('#remove'),
    updateBtn = $('#update'),
    form = $("#addNewEnterpriseForm"),
    selections = [];
/**
 * 删除请求
 * @param ids 多个,号分隔
 * @param callback
 */
function deleteAjax(ids, callback) {
    if(ids!=undefined && ids!=""){
        Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
            if (!e) {
                return;
            }
            $.ajax({
                url: rootPath + "/action/S_enterprise_Enterprise_delete.action",
                type:"post",
                data:$.param({deletedId:ids},true),//阻止深度序列化，向后台传递数组
                dataType:"json",
                success:callback
            });
        });
    }else{
        Ewin.alert('请选择一条记录!');
    }
}

/*信息列表*/
function initTable() {
    gridTable.bootstrapTable({
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sidePagination:"server",
        url: rootPath+"/action/S_enterprise_Enterprise_list.action",
        height: 500,
        method:'post',
        pagination:true,
        clickToSelect:true,//单击行时checkbox选中
        queryParams:function (param) {
            var temp = pageUtils.getBaseParams(param);
            temp.isDel = '0';
            return temp;
        },
        columns: [
            {
                field: 'state',
                checkbox: true,
                align: 'center',
                radio:false,  //  true 单选， false多选
                valign: 'middle'
            },{
                field: 'status',
                title: '企业运行状态',
                sortable: false,
                align: 'center',
                formatter:statusFormatter
            }, {
                field: 'name',
                title: '排污单位名称',
                sortable: false,
                align: 'center'
            }, {
                field: 'orgCode',
                title: '组织机构代码',
                sortable: false,
                align: 'center'
            }, {
                field: 'artificialPerson',
                title: '企业法人',
                sortable: false,
                align: 'center'
            }, {
                field: 'apPhone',
                title: '联系方式',
                sortable: false,
                align: 'center'
            }, {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: operateEvents,
                formatter: operateFormatter
            }

        ]
    });
    //gridTable.bootstrapTable('hideColumn', 'id');
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
            height: getHeight()
        });
    });
    //处理新增按钮
    add.click(function(){
        jumpToUrl('/container/gov/enterprise/basicInfo/enterpriseInfo.jsp?handleType=add');
    });
    /*处理更新按钮*/
    updateBtn.click(function(){
        var id = getIdSelections();
        jumpToUrl('/container/gov/enterprise/mainEnterprise.jsp?handleType=edit&id='+id);
    });
    //处理删除按钮状态
    removeBtn.click(function () {
        var ids = getIdSelections();
        Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
            if (!e) {
                return;
            }
            $('#enterpriseId').val(ids);
            $('#delEnterpriseModal').modal('show');
        });
    });
    //搜索
    $("#search").click(function () {
        //查询之前重置table
        gridTable.bootstrapTable('resetSearch');
        var jsonData = $('#searchform').formSerializeObject();
        gridTable.bootstrapTable('refresh',{
            query:jsonData
        });
    });
    //重置搜索
    $("#resetSearch").click(function () {
        $('#searchform')[0].reset();
        gridTable.bootstrapTable('resetSearch');
    });
    initModel();
}
/*model*/
function initModel(){
    /*添加按钮*/
    $('#saveForm').click(function(){
        $('#enterpriseForm').ajaxSubmit({
            type: 'post', // 提交方式 get/post
            async:false,
            dataType:"json",
            url: rootPath+"/action/S_enterprise_Enterprise_save.action", // 需要提交的 url
            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
                if(data.success){
                    form.modal('hide');
                    gridTable.bootstrapTable('refresh');
                }
            }
        });
    });
    /*重置按钮*/
    $('#resetAddForm').click(function(){
        $('#enterpriseForm')[0].reset();
    });
    /*删除排污企业*/
    $('#makeSureDel').click(function(){
        $('#delEnterpriseModal').modal('hide');
        $('#deleteEnterpriseForm').ajaxSubmit({
            type: 'post', // 提交方式 get/post
            async:false,
            dataType:"json",
            url: rootPath+"/action/S_enterprise_Enterprise_deleteEnterprise.action", // 需要提交的 url
            success: function(data) { // data 保存提交后返回的数据，一般为 json 数据
                console.log(data);
                if(data.success){
                    Ewin.alert("删除成功！");
                    gridTable.find('tr.selected').remove();
                    removeBtn.prop('disabled', true);
                }else{
                    Ewin.alert("系统连接故障！");
                }
            }
        });
    })
}
/*企业运行状态*/
function  statusFormatter(value, row, index){
    switch(value){
        case "0":
            return '<img src="container/gov/enterprise/images/grayCircle.png" style="width: 20px;height: 20px;">';
        case "1":
            return '<img src="container/gov/enterprise/images/greenCircle.png" style="width: 20px;height: 20px;">';
        default:
            return '<img src="container/gov/enterprise/images/grayCircle.png" style="width: 20px;height: 20px;">';
    }
}
// 生成操作方法
function operateFormatter(value, row, index) {
    return '<button type="button" class="btn btn-md btn-warning view" onclick="jumpToUrl(\'/container/gov/enterprise/mainEnterprise.jsp?handleType=look&id='+row.id+'\')">查看</button>';
}
function jumpToUrl(url){
    $('#level2content').html(pageUtils.loading()); // 设置页面加载时的loading图片
    $('#level2content').load(rootPath+url); // ajax加载页面
}
// 列表操作事件
window.operateEvents = {
    'click .view': function (e, value, row, index) {
        setFormView(row);
    }
};
// 获取所有的选中数据
function getIdSelections() {
    return $.map(gridTable.bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}
// 获取所有的选中数据
function getSelections() {
    return $.map(gridTable.bootstrapTable('getSelections'), function (row) {
        return row
    });
}
// 设置默认选中
function responseHandler(res) {
    $.each(res.rows, function (i, row) {
        row.state = $.inArray(row.id, selections) !== -1;
    });
    return res;
}
function getHeight() {
    return $(window).height() - $('h1').outerHeight(true);
}
initTable();

//初始化按钮状态
removeBtn.prop('disabled', true);
updateBtn.prop('disabled', true);
