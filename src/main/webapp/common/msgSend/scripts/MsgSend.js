/*消息提示框*/
(function ($) {

    window.MsgSend = function () {
        var html = '<div id="[Id]" class="modal fade" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel" aria-hidden="true">' +
            '<div class="modal-dialog" style="width: [Width]px">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>' +
            '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
            '</div>' +
            '<div class="modal-body">' +
            '<div class="row"><div class="col-sm-3" id="MsgModelLeft"></div>'+
            '<div class="col-sm-9" id="MsgModelRight"></div></div>' +
            '</div>' +
            '<div class="modal-footer">' +
            '<button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>' +
            '<button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>' +
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
                //message: "提示内容",
                btnok: "发送",
                btncl: "取消",
                width: 1000,
                auto: false
            }, options || {});
            var modalId = generateId();
            var content = html.replace(reg, function (node, key) {
                return {
                    Id: modalId,
                    Title: options.title,
                    Width: options.width,
                    //Message: options.message,
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
            orgSend: function (options) {
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
                var leftHtml = '<div class="Node-frame-menubar"><div class="scrollContent" ><ul id="treeDemo" class="ztree"></ul></div></div>';
                var rightHtml = '<div class="mainBox"><div class="tableBox"><table id="selectPeopleTable" class="table table-striped table-responsive"> </table> </div> </div>';
                modal.find('#MsgModelLeft').append(leftHtml);
                $(".scrollContent").slimScroll({
                    height:"100%",
                    railOpacity:.9,
                    alwaysVisible:!1
                });

                var setting = {
                    height:500,
                    width:200,
                    view: {
                        showLine: true
                    },
                    async: {
                        enable: true,
                        // url:loadPersonUrl,
                        url:rootPath + "/container/gov/dispatch/selectPeople.json",
                        autoParam:["id", "name=n", "level=lv"],
                        otherParam:{"otherParam":"zTreeAsyncTest"},
                        dataFilter: filter
                    },
                    callback: {
                        onClick: zTreeOnClick
                    }
                };
                function zTreeOnClick(event, treeId, treeNode) {
                    if(!treeNode.parent){
                        appendToGrid(treeNode);
                        console.log("appendToGrid:"+JSON.stringify(treeNode))
                    }

                };
                function filter(treeId, parentNode, childNodes) {
                    if (!childNodes) return null;
                    for (var i=0, l=childNodes.length; i<l; i++) {
                        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
                    }
                    return childNodes;
                }
                $.fn.zTree.init($("#treeDemo"), setting);
                var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                setTimeout(function () {
                    treeObj.expandAll(true);
                },500);
                modal.find('#MsgModelRight').append(rightHtml);
                modal.find('.ok').removeClass('btn-primary').addClass('btn-success');
                modal.find('.cancel').show();
                return {
                    id: id,
                    onSure: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () { callback(true,id); });
                        }
                    },
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () { callback(true,id); });
                            modal.find('.cancel').click(function () { callback(false,id); });
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
            }
        }
    }();
})(jQuery);