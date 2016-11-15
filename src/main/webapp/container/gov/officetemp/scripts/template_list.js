// 定义常量
define('template-config', function() {
    /**
     * 数据源字段
     */
    var fields = {
        id: {validation: {length: 36}},
        name: {validation: {required: true, length: 256}},
        filePath: {validation: {required: true, length: 256}},
        fileName: {validation: {required: true, length: 256}},
        dataFileName: {validation: {required: true, length: 256}}
    };

    var dataSource = {
        serverSorting: true,
        serverPaging: true,
        pageSize: 10,
        transport: {
            read: {url : rootPath + "/action/S_officetemp_OfficeTemp_list.action",method:"POST"},
            create: rootPath + "/action/S_officetemp_OfficeTemp_save.action",
            update: rootPath + "/action/S_officetemp_OfficeTemp_save.action",
            destroy: rootPath + "/action/S_officetemp_OfficeTemp_delete.action"
        },
        schema: {
            data: 'rows',
            total: 'total',
            model: { id: 'id', fields: fields }
        }
    };

    var columns = [
        {field: 'id', title: '模板id', editOnly:true, width: 256},
       {field: 'name', title: '模板名称', width: 256},
       {field: 'filePath', title: '模板位置'},
        {field: 'fileName', title: '模板名'},
        {field: 'dataFileName', title: '数据文件名'},
        {
            field: 'id',
            title: '操作',
            command: [
                {
                    text: "编辑模板",
                    click: function (e, rowData) {
                        window.open(rootPath + "/action/S_officetemp_OfficeTemp_editTemplate.action?id="+rowData.id);
                    }
                }
            ]
        },
        {
            field: "attachmentId",
            title: "附件",
            editOnly: true,
            editor:createUpload,
            template: function(data) {//查看时附件显示样式
                return viewAttrHandle(data.id);
            }
        },

    ];

    //创建文件上传
    function createUpload(container, options){
        var businessId = options.model.id;
        //创建附件上传控件
        $('<input name="' + options.field + '" id="' + options.field + '" type="hidden" />').appendTo(container);
        var removeIds = $('<input name="removeId"  data-bind="value:removeId" type="hidden" value="">').appendTo(container);
        $('<input name="files" id="'+options.field+'_att" type="file" />')
            .appendTo(container).hwuiAttachment({
                hiddenInputEl: '#'+options.field,
                businessId:businessId,//附件加载
                maxFileCount:"5",
                removeId: removeIds,
                model: options.model,
                supportsRemove: true,//附件删除
                /* success:function(operation) {
                 $("#"+options.field).change();//调用 change 事件后，隐藏的附件ID input 才会向后台business Action 传值，
                 }*/
            });
    }
    /**
     * 附件查看
     * @param businessId
     * @returns {string}
     */
    function viewAttrHandle(businessId) {
        var loadAttrUrl = rootPath + '/action/S_attachment_Attachment_listAttachment.action';
        var htmlStr = '无附件';
        $.ajax({
            type : "POST",
            url : loadAttrUrl,
            data:{"businessId" : businessId},
            cache : false,
            async: false,
            dataType : "json",
            success :  function(files){
                if (files.length > 0) {
                    htmlStr = '<ul class="k-upload-files k-reset" style="border:1px solid #c5c5c5;" >';
                    $.each(files,function(i,file){
                        var downloadUrl = rootPath+"/action/S_attachment_Attachment_download.action?id="+file.id;
                        htmlStr += '<li class="k-file k-file-success">';
                        htmlStr += '<span class="k-progress" style="width: 100%;"></span>';
                        htmlStr += '<span class="k-icon k-i-mage/jpeg"></span>';
                        htmlStr += '<a href=' + downloadUrl + ' title="下载  ' + file.name + '" class="k-filename" style="max-width:400px;"> 下载  ' + file.name + '</a>';
                        htmlStr += '</li>';
                    });
                    htmlStr += "</ul>";
                }
            }
        });
        return htmlStr;
    }
    var grid = {
        dataSource: dataSource, //数据源
        editable: {//可编辑
            mode: 'popup'
        },
        pageable: {
            refresh: true, //显示刷新按钮
            pageSizes: true //显示可切换每页条数按钮
        },
        selectable: 'multiple',
        title: '模板列表',
        toolbar: ['create', 'edit', 'destroy'],
        columns: columns,
        dblclick:function(e){
            e.model.editable = false;
        }
    };

    return {
        dataSourceOptions: dataSource,
        columns: columns,
        gridOptions: grid
    };
});
// 定义查询相关设置
define('template-search',['hwquery', 'core', 'binder'], function($, hwui) {
    var $searchArea = $('#templateSearchArea'),
        searchModel = hwui.observable({
            page: 1
        });

    hwui.bind($searchArea, searchModel);

    $searchArea.find(".k-button-reset").click(function() {
        searchModel.forEach(function(value, key) {
            searchModel.set(key, null);
        });
    });

    function registerSearchEvent(onSearch) {
        var searchBtn = $searchArea.find(".k-button-search");
        searchBtn.click(onSearch);
        $searchArea.off('keypress').on('keypress', function(e) {
            if (e.keyCode == 13) {
                $(e.target).trigger('change');
                searchBtn.trigger('click');
            }
        });
    }

    return {
        searchArea: $searchArea,
        searchModel: searchModel,
        onSearch: registerSearchEvent
    };
});
// 定义列表相关操作
hwui().require('template-config', 'template-search', 'effect', 'grid', 'attachment', function($, hwui, config, search) {
    config.gridOptions.editable.template = hwui.template($("#localeDialog").text());
    var grid = $('#templateGrid').hwuiGrid(config.gridOptions).data('hwuiGrid');
    search.onSearch(function() {
        grid.dataSource.query(search.searchModel.toJSON());
    });

});