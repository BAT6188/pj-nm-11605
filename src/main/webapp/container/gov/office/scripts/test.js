(function ($) {
    $.fn.BootstrapExport = function(clickObj,clickOptions){
        var that = this,
            currentTimeId = new Date().valueOf(),
            defaultOptions = {
                fileName:'下载文件'+new Date().valueOf(),
                type: 'excel',
                escape: false,
                dropMenu:{
                    0:{type:'selected',name:'下载已选择数据'},
                    1:{type:'basic',name:'下载当前页数据'},
                    2:{type:'all',name:'下载所有数据'}
                }
            },
            downloadTable,
            hiddenTableDiv,
            downloadTableData=[],
            downloadOptions={};
        var ExportObj = {
            init:function(){
                $('.hiddenTableDiv').remove();
                var initThat = this;
                initThat.createDownLoad();
                var thisOptions = $.extend({}, defaultOptions, clickOptions);
                if(clickObj){
                    var ulHtml = '<div class="export btn-group">'
                        + '<button id="export" type="button" class="btn btn-sm btn-success" data-toggle="dropdown">'
                        + '<span class="glyphicon glyphicon-export"></span>导出</button>'
                        + '<ul id="dropdownMenu'+currentTimeId+'" class="dropdown-menu" role="menu">';
                    $.each(thisOptions.dropMenu,function(k,v){
                        ulHtml +='<li data-type="' + v.type + '"><a href="javascript:void(0)">'+v.name+'</a></li>';
                    });
                    ulHtml +='</ul></div>';
                    $(clickObj).replaceWith(ulHtml);
                    $('#dropdownMenu'+currentTimeId).find('li').click(function(){
                        thisOptions.exportDataType = $(this).data('type');
                        initThat.exportTable(thisOptions);
                    })
                }
            },
            createDownLoad:function(){
                var tableId = "gridTable"+currentTimeId,hiddenTableDivId = "hiddenTableDiv"+currentTimeId;
                $('body').append('<div id="'+hiddenTableDivId+'" style="display: none" class="hiddenTableDiv"></div>');
                hiddenTableDiv = $('#'+hiddenTableDivId);
                hiddenTableDiv.append('<table id="'+tableId+'" class="table table-striped table-responsive"></table>');
                downloadTable = $('#'+tableId);
                var tableOptions = that.bootstrapTable('getOptions');
                var tableClumns = tableOptions.columns[0];
                that.bootstrapTable('refreshOptions',{onLoadSuccess:function(data){
                    var newColumns = [];
                    var columnIndex = 0;
                    $.each(tableClumns,function(k,v){
                        if(v.isDown){
                            v.visible = true;
                            newColumns[columnIndex] = v;
                            columnIndex +=1;
                        }
                    });
                    var newTableOptions= {
                        contentType: tableOptions.contentType,
                        sidePagination:tableOptions.sidePagination,
                        url: tableOptions.url,
                        height: tableOptions.height,
                        pageSize:data.total,
                        pageList:tableOptions.pageList,
                        method:tableOptions.method,
                        pagination:tableOptions.pagination,
                        queryParams:tableOptions.queryParams,
                        columns:newColumns,
                        onLoadSuccess:function(downloadData){
                            downloadTableData = downloadData;
                        }
                    };
                    downloadTable.bootstrapTable(newTableOptions);
                }});
            },
            exportTable:function(options){
                options = $.extend({}, defaultOptions, options);
                var exportObj = this;
                downloadOptions = options;
                switch (options.exportDataType){
                    case 'basic':
                        var currentData = that.bootstrapTable('getData',{useCurrentPage:true});
                        downloadTable.bootstrapTable('load',this.getLoadData(currentData));
                        exportObj.doExport();
                        downloadTable.bootstrapTable('load',downloadTableData);
                        break;
                    case 'selected':
                        var selectedData = that.bootstrapTable('getAllSelections');
                        if(selectedData.length>0){
                            downloadTable.bootstrapTable('load',this.getLoadData(selectedData));
                            exportObj.doExport();
                            downloadTable.bootstrapTable('load',downloadTableData);
                            break;
                        }else{
                            Ewin.alert('没有选择的数据可下载！');
                            break;
                        }
                    default:
                        downloadTable.bootstrapTable('refreshOptions',{
                            onLoadSuccess:function(downloadData){
                                downloadTableData = downloadData;
                                downloadTable.bootstrapTable('load',downloadTableData);
                                exportObj.doExport();
                            }
                        });
                }
            },
            doExport:function(){
                hiddenTableDiv.show();
                downloadTable.tableExport(downloadOptions);
                hiddenTableDiv.hide();
            },
            getLoadData:function(data){
                return {
                    rows:data,
                    total:data.length
                }
            }
        }
        ExportObj.init();
        return ExportObj;
    };
})(jQuery)