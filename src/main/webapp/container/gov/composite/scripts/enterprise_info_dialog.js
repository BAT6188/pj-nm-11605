var EnterpriseInfoDialog = function(){
    var dialog = {
        _model:$("#enterpriseInfoDialog"),
        _content:$('#enterpriseInfoContent'),
        _width:$(window).width()-50,
        _height:$(window).height()-200,
        init:function () {
            //初始化dialog大小
            this._model.find(".modal-dialog")
                .width(this._width);
            this._content.height(this._height);
            this._content.html("");


        },
        show:function (id) {
            this._content.load(rootPath+"/container/gov/enterprise/mainEnterprise.jsp?handleType=look&id="+id);
            this._model.modal('show');
        },
        dialog:function () {
            if (arguments.length == 1) {
                if (typeof(arguments[0]) == "string") {
                    if (this[arguments[0]]){
                        this[arguments[0]]();
                    }
                }
            }
        }
    };
    dialog.init();
    return dialog;
}();
//EnterpriseInfoDialog.show();



