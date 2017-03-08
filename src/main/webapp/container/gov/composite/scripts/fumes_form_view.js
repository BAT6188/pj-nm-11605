/**
 * Created by Administrator on 2016/10/28.
 */
var FumesHistoryDataFormViewDialog = function(){
    var modal=$("#liveFumesModal"),
        modalBody=$("#liveFumesModal").find(".modal-body"),
        width='80%',
        height=450;
    function init() {
        //初始化dialog大小
        modal.find(".modal-dialog").width(width);
        modalBody.height(pageUtils.getFormHeight(height));
        modalBody.find(".scrollContent").slimScroll({
            height:"100%",
            railOpacity:.9,
            alwaysVisible:!1
        });
    }
    function loadFumesHistoryGrid(enterpriseId){
        var setting = {
            data: {
                simpleData: {
                    enable: true,
                    idKey: "code",
                    pIdKey: "parentCode",
                    rootPId: -1
                }
            },
            height:500,
            width:200,
            view: {
                showLine: false
            },
            async: {
                enable: true,
                url:rootPath + "/action/S_enterprise_Enterprise_getFumesEnterprisePortZtree.action",
                autoParam:["code"],
                otherParam:{"code":enterpriseId},
                dataFilter: null
            },
            callback: {
                onClick: function(event, treeId, treeNode){
                    loadFumesHistoryTable(treeNode.parentCode,treeNode.code,treeNode.name);
                },
                onAsyncSuccess: function(event, treeId, treeNode, msg) {
                    treeObj.expandAll(true);
                    var node = treeObj.getNodeByTId("enterpriseZTree_2");
                    treeObj.selectNode(node,false);
                    loadFumesHistoryTable(node.code);
                }
            }
        };
        var treeObj = $.fn.zTree.init(modalBody.find("#enterpriseFumesPortZTree"), setting);
    }

    var fumesGridTable = $('#liveFumesTable'),
        isLoadFumesGridTableTable = false;
    function loadFumesHistoryTable(enterpriseId){
        //modal.find("#airName").html(airName);
        isLoadFumesGridTableTable.enterpriseId = enterpriseId;
        if(isLoadFumesGridTableTable){
            fumesGridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:10});
        }else{
            isLoadFumesGridTableTable = true;
            fumesGridTable.bootstrapTable({
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                sidePagination:"server",
                url: rootPath+"/action/S_port_FumesPortHistory_list.action",
                height: 500,
                method:'post',
                pagination:true,
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
                    localParams.enterpriseId = fumesGridTable.enterpriseId;
                    return localParams;
                },
                rowStyle:function(row,index) {
                    var dataType;
                    switch(row.dataStatus){
                        case '1':
                            dataType = 'danger alert-danger';
                            break;
                        case '2':
                            dataType = 'warning alert-warning';
                            break;
                        default:
                            dataType = 'success alert-success';
                    }
                    return { classes:dataType};
                },
                columns: [
                    {
                        title: '监测时间',
                        field: 'monitorTime',
                        sortable: false,
                        editable: false,
                        align: 'center'
                    },
                    {
                        title: '油烟（mg/L）',
                        field: 'fumes',
                        editable: false,
                        sortable: false,
                        align: 'center'
                    },
                    {
                        title: '烟气温度（℃）',
                        field: 'temperature',
                        editable: false,
                        sortable: false,
                        align: 'center'
                    },
                    {
                        title: '烟气湿度（％）',
                        field: 'humidity',
                        editable: false,
                        sortable: false,
                        align: 'center'
                    }
                ]
            });
        }
        // sometimes footer render error.
        setTimeout(function () {
            fumesGridTable.bootstrapTable('resetView');
        }, 200);

        $(window).resize(function () {
            // 重新设置表的高度
            fumesGridTable.bootstrapTable('resetView', {
                height: pageUtils.getTableHeight()-50
            });
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
                    var id = options['id'],name=options['name'];
                    if (id) {
                        loadFumesHistoryGrid(id,name);
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


