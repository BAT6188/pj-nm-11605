/**
 * Created by Administrator on 2016/10/9.
 */
(function($){
    $.fn.formSerializeObject = function()
    {
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
})(jQuery);
