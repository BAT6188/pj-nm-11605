/**
 * Created by Administrator on 2016/10/28.
 */
var WaterGasHistoryDataFormViewDialog = function(){
    var modal=$("#liveWaterGasModal"),
        modalBody=$("#liveWaterGasModal").find(".modal-body"),
        width='80%',
        height=450;
    function init() {
        //初始化dialog大小
        //modal.find(".modal-dialog").width(1000);
        modalBody.height(pageUtils.getFormHeight(height));
        modalBody.find(".scrollContent").slimScroll({
            height:"100%",
            railOpacity:.9,
            alwaysVisible:!1
        });
    }
    function loadWaterAndGasHistoryGrid(enterpriseId){
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
                url:rootPath + "/action/S_enterprise_Enterprise_getEnterprisePortZtree.action",
                autoParam:["code"],
                otherParam:{"code":enterpriseId},
                dataFilter: null
            },
            callback: {
                onClick: function(event, treeId, treeNode){
                    loadPortStatusHistory(treeNode.parentCode,treeNode.code,treeNode.name,enterpriseId);
                },
                onAsyncSuccess: function(event, treeId, treeNode, msg) {
                    var node = treeObj.getNodeByTId("enterpriseZTree_2");
                    if(node){
                        treeObj.expandAll(true);
                        treeObj.selectNode(node,false);
                        loadPortStatusHistory(node.parentCode,node.code,node.name,enterpriseId);
                    }
                }
            }
        };
        var treeObj = $.fn.zTree.init(modalBody.find("#enterpriseWaterGasPortZTree"), setting);
    }
    function loadPortStatusHistory(parentCode,code,name,enterpriseId){
        switch(parentCode){
            case "gasPort":
                loadGasHistoryTable('',code);
                break;
            case "waterPort":
                loadWaterHistoryTable('',code);
                break;
            default:
                if(code=="gasPort"){
                    loadGasHistoryTable(enterpriseId,'');
                }else if(code=="waterPort"){
                    loadWaterHistoryTable(enterpriseId,'');
                }
                break;
        }
    }
    var wgGridTable = $('#liveWaterGasTable');
    function loadWaterHistoryTable(enterpriseId,portId){
        wgGridTable.bootstrapTable('destroy');
        wgGridTable.bootstrapTable({
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                sidePagination:"server",
                url: rootPath+"/action/S_port_WaterPortHistory_list.action?enterpriseId="+enterpriseId+"&portId="+portId,
                height: 500,
                method:'post',
                pagination:true,
                pageSize:10,
                clickToSelect:true,//单击行时checkbox选中
                queryParams:pageUtils.localParams,
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
                        title: '流量（升/秒）',
                        field: 'flowLiveValue',
                        editable: false,
                        sortable: false,
                        align: 'center',
                        formatter:function (value, row, index) {
                            if(row.flowStatus){
                                if(row.flowStatus!=0){
                                    value='<span style="color:red;font-weight: bolder;">'+value+'</span>'
                                }
                            }
                            return value;
                        }
                    },
                    {
                        title: '化学需氧量（毫克/升）',
                        field: 'oxygenLiveValue',
                        editable: false,
                        sortable: false,
                        align: 'center',
                        formatter:function (value, row, index) {
                            if(row.oxygenStatus){
                                if(row.oxygenStatus!=0){
                                    value='<span style="color:red;font-weight: bolder;">'+value+'</span>'
                                }
                            }
                            value=value+"("+row.oxygenStandardValue+")"
                            return value;
                        }
                    },
                    {
                        title: '氨氮（毫克/升）',
                        field: 'nitrogenLiveValue',
                        editable: false,
                        sortable: false,
                        align: 'center',
                        formatter:function (value, row, index) {
                            if(row.nitrogenStatus){
                                if(row.nitrogenStatus!=0){
                                    value='<span style="color:red;font-weight: bolder;">'+value+'</span>'
                                }
                            }
                            value=value+"("+row.nitrogenStandardValue+")"
                            return value;
                        }
                    },
                    {
                        title: 'ph值',
                        field: 'phLiveValue',
                        editable: false,
                        sortable: false,
                        align: 'center',
                        formatter:function (value, row, index) {
                            if (value){
                                if(row.phStatus){
                                    if(row.phStatus!=0){
                                        value='<span style="color:red;font-weight: bolder;">'+value+'</span>'
                                    }
                                }
                                value=value+"("+row.phStandardValue+")"
                            }
                            return value;
                        }
                    }
                ]
            });
        // sometimes footer render error.
        setTimeout(function () {
            wgGridTable.bootstrapTable('resetView');
        }, 200);

        $(window).resize(function () {
            // 重新设置表的高度
            wgGridTable.bootstrapTable('resetView', {
                height: pageUtils.getTableHeight()-50
            });
        });
    }
    function loadGasHistoryTable(enterpriseId,portId){
        wgGridTable.bootstrapTable('destroy');
        wgGridTable.bootstrapTable({
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            sidePagination:"server",
            url: rootPath+"/action/S_port_GasPortHistory_list.action?enterpriseId="+enterpriseId+"&portId="+portId,
            height: 500,
            method:'post',
            pagination:true,
            pageSize:10,
            clickToSelect:true,//单击行时checkbox选中
            queryParams:pageUtils.localParams,
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
                    title: '氮氧化物（毫克/立方米）',
                    field: 'nitrogenLiveValue',
                    editable: false,
                    sortable: false,
                    align: 'center',
                    formatter:function (value, row, index) {
                        if(row.nitrogenStatus!=0){
                            value='<span style="color:red;font-weight: bolder;">'+value+'</span>'
                        }
                        value=value+"("+row.nitrogenStandardValue+")"
                        return value;
                    }
                },
                {
                    title: '二氧化硫（毫克/立方米）',
                    field: 'sulfurLiveValue',
                    editable: false,
                    sortable: false,
                    align: 'center',
                    formatter:function (value, row, index) {
                        if(row.sulfurStatus!=0){
                            value='<span style="color:red;font-weight: bolder;">'+value+'</span>'
                        }
                        value=value+"("+row.sulfurStandardValue+")"
                        return value;
                    }
                },
                {
                    title: '废气流量（立方米/小时）',
                    field: 'gasFlowLiveValue',
                    editable: false,
                    sortable: false,
                    align: 'center',
                    formatter:function (value, row, index) {
                        if(row.gasFlowStatus!=0){
                            value='<span style="color:red;font-weight: bolder;">'+value+'</span>'
                        }
                        return value;
                    }
                },
                {
                    title: '烟尘（毫克/立方米）',
                    field: 'dustLiveValue',
                    editable: false,
                    sortable: false,
                    align: 'center',
                    formatter:function (value, row, index) {
                        if(row.dustStatus!=0){
                            value='<span style="color:red;font-weight: bolder;">'+value+'</span>'
                        }
                        value=value+"("+row.dustStandardValue+")"
                        return value;
                    }
                },
                {
                    title: '氧含量（%）',
                    field: 'oxygenLiveValue',
                    editable: false,
                    sortable: false,
                    align: 'center',
                    formatter:function (value, row, index) {
                        if(row.oxygenStatus!=0){
                            value='<span style="color:red;font-weight: bolder;">'+value+'</span>'
                        }
                        if(row.oxygenStandardValue!=0){
                            value=value+"("+row.oxygenStandardValue+")"
                        }
                        return value;
                    }
                }
            ]
        });
        // sometimes footer render error.
        setTimeout(function () {
            wgGridTable.bootstrapTable('resetView');
        }, 200);

        $(window).resize(function () {
            // 重新设置表的高度
            wgGridTable.bootstrapTable('resetView', {
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
                    var id = options['id'];
                    if (id) {
                        loadWaterAndGasHistoryGrid(id);
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


