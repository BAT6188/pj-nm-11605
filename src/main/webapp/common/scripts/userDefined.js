var pageUtils = {

    getTableHeight:function () {
        console.log(".banner height:"+$('.banner').outerHeight(true));
        console.log(".linear height:"+$('.linear').outerHeight(true));
        return $(window).height() - $('.dealBox').outerHeight(true) - $('.banner').outerHeight(true)-$('.linear').outerHeight(true) -26;
    },
    /**
     * 转换bootstrapTable 参数为本地参数
     * @param params
     * @returns {{}}
     */
    localParams: function localParams(params) {
        var localParams = {};
        //分页参数
        localParams.take = params.limit;
        localParams.skip = params.offset;
        localParams.page = params.offset / params.limit + 1;
        localParams.pageSize = params.limit;
        return localParams;
    },
    /**
     * 获取radio Value
     * @param name
     */
    getRadioValue:function (name) {
        var rv;
        $("input[name='"+name+"']").each(function (index, radio) {
            if ($(radio).prop("checked")) {
                rv = $(radio).val()+"";
            }
        });
        return rv;
    },
    /**
     * 设置radio value
     * @param name
     * @param value
     */
    setRadioValue:function (name, value) {
        $("input[name='"+name+"']").each(function (index,radio) {
            var rv = $(radio).val();
            if (value && rv==value) {
                $(radio).prop("checked",true);
            }else{
                $(radio).prop("checked",false);
            }
        });
    },
    /**
     * 截取字符前10位 日期串获取前10位
     * @param str
     * @returns {string}
     */
    sub10:function (str) {
        if(str){
            return str.substr(0,10);
        }
    }
};
(function($){
    $.fn.formSerializeObject = function(){
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if(this.value != undefined && this.value !=''){
                if (o[this.name] !== undefined) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            }
        });
        return o;
    };
    $.fn.allFormSerializeObject = function(){
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery);
