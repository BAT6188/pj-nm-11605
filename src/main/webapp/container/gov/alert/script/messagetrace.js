var MessageTracePage = function () {
    var gridTable = $('#table'),
        form = $("#messagetraceForm");

    /**============grid 列表初始化相关代码============**/
    function initTable() {
        gridTable.bootstrapTable({
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            sidePagination:"server",
            url: rootPath+"/action/S_alert_MessageTrace_list.action",
            height: 290,
            method:'post',
            pagination:true,
            clickToSelect:true,//单击行时checkbox选中
            pageSize: 5,
            queryParams:function (param) {
                var tempParam = pageUtils.getBaseParams(param);
                tempParam.businessId = getBusinessId();
                return tempParam;
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
                    title: '接收人',
                    field: 'receiverName',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '状态',
                    field: 'receiveStatus',
                    sortable: false,
                    align: 'center',
                    editable: false,
                    formatter:function (value, row, index) {
                        if (value == "2") {
                            return "已接收";
                        }else{
                            return "未接收";
                        }
                    }
                },
                {
                    title: '接收时间',
                    field: 'receiveTime',
                    sortable: false,
                    align: 'center',
                    editable: false
                }
            ]
        });
        // sometimes footer render error.
        setTimeout(function () {
            gridTable.bootstrapTable('resetView');
        }, 200);

    }

    initTable();

    /**============列表搜索相关处理============**/
//搜索按钮处理
    $("#search").click(function () {
        var queryParams = {};
        var receiverName = $("#s_receiverName").val();
        var receiveStatus = $("#s_receiveStatus").val();
        if (receiverName){
            queryParams["receiverName"] = receiverName;
        }
        if (receiveStatus){
            queryParams["receiveStatus"] = receiveStatus;
        }
        gridTable.bootstrapTable('refresh',{
            query:queryParams
        });
    });
    function getBusinessId(){
        if (businessId){
            return businessId;
        }else{
            return "EmptyBusinessId";
        }
    }
    function setBusinessId(bid) {
        businessId = bid;
    }
    function refreshTableGrid(businessId){
        setBusinessId(businessId);
        gridTable.bootstrapTable('refresh');
    }
    var pubMember = {
        refreshTableGrid:refreshTableGrid
    };
    return pubMember;
}();
var MessageTraceModal = function () {
    var $messageTraceModal = $("#messageTraceModal");
    var $modalDialog = $messageTraceModal.find(".modal-dialog");
    $modalDialog.width(780);
    var $modalBody = $messageTraceModal.find(".modal-body");
    $modalBody.height(370);

    var pubMember={
        show:function () {
            $messageTraceModal.modal("show");
        }
    };
    return pubMember;
}();

