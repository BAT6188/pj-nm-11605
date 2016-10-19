var pageUtils = {

    getTableHeight:function () {
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
    },
    //加载菜单
    _mainMenu: [],//主菜单
    _subMenu:{}, //子菜单
    _getSubMenu: function(data) {//该函数取得被指定菜单激活的下拉式菜单或子菜单的句柄。
        var that = this;
        var items = [];//定义一个空数组items
        $.each(data, function (k, v) {//data目标数组，k数组的下标，v数组的元素
            var item = {
                id: v.id,
                text: v.text,
                url: v.navUrl
            }, children;

            items.push(item);

            children = v.childrenData;
            if (children && children.length > 0) {
                item.items = that._getSubMenu(children);
                item.expanded = true;
                delete item.url;
            }
        });
        return items.length > 0 ? items : null;
    },
    loadMenu:function (callback) {
        var that = this;
        if (this._mainMenu.length == 0) {
            //加载菜单
            $.ajax({
                url: apportalRootPath + '/main/app.action',
                dataType: 'jsonp',
                jsonp: '_ca11back',
                data: {
                    method: 'appMenuItem',
                    appId: '402883b3577422f00157743c07f10002',
                    subSysId: '402883b3577422f00157743f33440003',
                    requestMode: 'ajax',
                    SToken: SToken
                },
                success: function (data) {
                    data = JSON.parse(data);//此示例使用 JSON.parse 将 JSON 字符串转换为对象
                    //初始化菜单数据结构
                    $.each(data.childrenData, function (k, v) {
                        that._mainMenu.push({
                            code: v.name,
                            text: v.text
                        });
                        that._subMenu[v.name] = that._getSubMenu(v.childrenData);
                    });

                    callback(that._mainMenu,that._subMenu);
                }
            });
        }else{
            callback(that._mainMenu,that._subMenu);
        }
    },
    /**
     * 手动转换
     * @param params
     * @returns {{}}
     */
    getBaseParams:function (params){
        var localParams = {};
        //分页参数
        localParams.take = params.limit;
        localParams.skip = params.offset;
        localParams.page = params.offset / params.limit + 1;
        localParams.pageSize = params.limit;
        return localParams;
    },
    loading:function(msg){
        var showMsg = '数据载入中，请稍后......';
        if(msg!=undefined && msg!=""){
            showMsg = msg;
        }
        var returnMsg = '<table width=100% height=100% border=0 align=center valign=middle>'
        + '<tr height=50%><td align=center>&nbsp;</td></tr>'
        + '<tr><td align=center></td></tr>'
        + '<tr><td align=center>'+showMsg+'</td></tr>'
        + '<tr height=50%><td align=center>&nbsp;</td></tr>'
        + '</table>';
        return returnMsg;
    },
    /**
     * 导入页面
     * @param id
     * @param url
     */
    loadPageOfContent:function(id,url){
        $(id).html(pageUtils.loading()); // 设置页面加载时的loading图片
        $(id).load(url); // ajax加载页面
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
    var BootstrapAlerts={
        'warning':'警告！',
        'success':'提示：',
        'info':'信息：',
        'danger':'错误！'
    }
    $.fn.BootstrapAlertMsg = function(type,msg){
        var html = '<div class="alert alert-'+type+'">'
            + '<a href="#" class="close" data-dismiss="alert">'
            + '&times;</a>'
            + '<strong>'+BootstrapAlerts[type]+'！</strong>您的网络连接有问题。</div>'
        console.log(msg);
        console.log(this);
        $(this).prepend(html);
    };
})(jQuery);

/*消息提示框*/
(function ($) {

    window.Ewin = function () {
        var html = '<div id="[Id]" class="modal fade" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">' +
            '<div class="modal-dialog modal-sm">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>' +
            '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
            '</div>' +
            '<div class="modal-body">' +
            '<p>[Message]</p>' +
            '</div>' +
            '<div class="modal-footer">' +
            '<button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>' +
            '<button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';


        var dialogdHtml = '<div id="[Id]" class="modal fade" data-backdrop="static"  tabindex="-1" role="dialog" aria-labelledby="dailogModalLabel" aria-hidden="true">' +
            '<div class="modal-dialog">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>' +
            '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
            '</div>' +
            '<div class="modal-body">' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';
        var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
        var generateId = function () {
            var date = new Date();
            return 'mdl' + date.valueOf();
        }
        var init = function (options) {
            options = $.extend({}, {
                title: "操作提示",
                message: "提示内容",
                btnok: "确定",
                btncl: "取消",
                width: 200,
                auto: false
            }, options || {});
            var modalId = generateId();
            var content = html.replace(reg, function (node, key) {
                return {
                    Id: modalId,
                    Title: options.title,
                    Message: options.message,
                    BtnOk: options.btnok,
                    BtnCancel: options.btncl
                }[key];
            });
            $('body').append(content);
            $('#' + modalId).modal({
                width: options.width,
                backdrop: 'static'
            });
            $('#' + modalId).on('hide.bs.modal', function (e) {
                $('body').find('#' + modalId).remove();
            });
            return modalId;
        }

        return {
            alert: function (options) {
                if (typeof options == 'string') {
                    options = {
                        message: options
                    };
                }
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.ok').removeClass('btn-success').addClass('btn-primary');
                modal.find('.cancel').hide();

                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () { callback(true); });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            confirm: function (options) {
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.ok').removeClass('btn-primary').addClass('btn-success');
                modal.find('.cancel').show();
                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () { callback(true); });
                            modal.find('.cancel').click(function () { callback(false); });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            dialog: function (options) {
                options = $.extend({}, {
                    title: 'title',
                    url: '',
                    width: 800,
                    height: 550,
                    onReady: function () { },
                    onShown: function (e) { }
                }, options || {});
                var modalId = generateId();

                var content = dialogdHtml.replace(reg, function (node, key) {
                    return {
                        Id: modalId,
                        Title: options.title
                    }[key];
                });
                $('body').append(content);
                var target = $('#' + modalId);
                target.find('.modal-body').load(options.url);
                if (options.onReady())
                    options.onReady.call(target);
                target.modal();
                target.on('shown.bs.modal', function (e) {
                    if (options.onReady(e))
                        options.onReady.call(target, e);
                });
                target.on('hide.bs.modal', function (e) {
                    $('body').find(target).remove();
                });
            }
        }
    }();
})(jQuery);