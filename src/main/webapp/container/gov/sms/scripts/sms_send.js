var SMSSendDialog = function(){
    var dialogModal = $("#smsSendDialog");
    var dialog = {
        _points:undefined,
        init:function () {
            var that = this;
            that.initTree();
            that.initTable();


            //初始化dialog大小
            $("#smsSendDialogOK").bind('click', function (e) {
                var result = that._closed(that._points);
                if (!(result === false)) {
                    dialogModal.modal('hide');
                }
            });

        },
        initTree:function () {
            $(".orgTree")
        },
        initTable:function () {
            var gridTable = dialogModal.find(".table");
            //初始化已选择列表table
            gridTable.bootstrapTable({
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                sidePagination:"server",
                url: rootPath+"/action/S_sms_SmsSendStatus_list.action",
                height: 260,
                method:'post',
                pagination:true,
                clickToSelect:true,//单击行时checkbox选中
                queryParams:pageUtils.localParams,
                columns: [
                    {
                        title: 'ID',
                        field: 'id',
                        align: 'center',
                        valign: 'middle',
                        sortable: false,
                        visible:false
                    },
                    {
                        title: '姓名',
                        field: 'receiverId',
                        sortable: false,
                        align: 'center',
                        editable: false
                    },
                    {
                        title: '职务',
                        field: 'receiverName',
                        sortable: false,
                        align: 'center',
                        editable: false
                    },
                    {
                        title: '移动电话',
                        field: 'receiverPhone',
                        sortable: false,
                        align: 'center',
                        editable: false
                    },
                    {
                        title: '办公室电话',
                        field: 'status',
                        sortable: false,
                        align: 'center',
                        editable: false
                    },
                    {
                        title: '部门',
                        field: 'status',
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
        },
        open:function () {
            dialogModal.modal('show');
        },
        _closed:function (points,okBtn) {

        },
        closed:function (callback) {
            this._closed = callback;
        }
    };
    dialog.init();
    return dialog;
}();
SMSSendDialog.init();
SMSSendDialog.open();




