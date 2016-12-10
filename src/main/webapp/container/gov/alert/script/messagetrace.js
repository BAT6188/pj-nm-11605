var MessageTracePage = function () {
    var gridTable = $('#table'),
        form = $("#messagetraceForm");

    //保存ajax请求
    function saveAjax(entity, callback) {
        $.ajax({
            url: rootPath + "/action/S_alert_MessageTrace_save.action",
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
            url: rootPath + "/action/S_alert_MessageTrace_delete.action",
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
            url: rootPath+"/action/S_alert_MessageTrace_list.action",
            height: pageUtils.getTableHeight(),
            method:'post',
            pagination:true,
            clickToSelect:true,//单击行时checkbox选中
            queryParams:function (param) {
                var tempParam = pageUtils.getBaseParams(param);
                tempParam.businessId = businessId;
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


        $(window).resize(function () {
            // 重新设置表的高度
            gridTable.bootstrapTable('resetView', {
                height: pageUtils.getTableHeight()
            });
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

    var pubMember = {};
    return pubMember;
}();

