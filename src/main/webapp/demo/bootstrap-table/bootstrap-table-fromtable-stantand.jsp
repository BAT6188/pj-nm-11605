<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp"%>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>面板布局</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link href="<%=request.getContextPath()%>/scripts/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/scripts/font-awesome-4.6.3/css/font-awesome.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/scripts/bootstrap-table-1.11.0/bootstrap-table.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/pageStyle.css" rel="stylesheet">

    <script src="<%=request.getContextPath()%>/scripts/jquery1.12.4/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/scripts/bootstrap-3.3.7/js/bootstrap.js"></script>

    <script src="<%=request.getContextPath()%>/scripts/bootstrap-table-1.11.0/bootstrap-table.js"></script>
    <script src="<%=request.getContextPath()%>/scripts/bootstrap-table-1.11.0/locale/bootstrap-table-zh-CN.js"></script>

    <script src="<%=request.getContextPath()%>/scripts/bootstrap-table-1.11.0/x-editable/bootstrap-editable.js"></script>
    <script src="<%=request.getContextPath()%>/scripts/bootstrap-table-1.11.0/extensions/editable/bootstrap-table-editable.js"></script>

    <script src="<%=request.getContextPath()%>/scripts/bootstrap-table-1.11.0/table-export/tableExport.js"></script>
    <script src="<%=request.getContextPath()%>/scripts/bootstrap-table-1.11.0/extensions/export/bootstrap-table-export.js"></script>

</head>
<body>
<div class="container">
    <div class="alert alert-info"> 数据表格的标准应用应用</div>
    <div id="toolbar">
        <button id="remove" type="button" class="btn btn-default" >删除</button>
        <button id="search" type="button" class="btn btn-default" >查询</button>
        <button id="searchFix" type="button" class="btn btn-default" >全局查询</button>
    </div>
    <table id="table"
           data-toolbar="#toolbar"
           data-show-header="true"
           data-card-view="false"
           data-search="true"
           data-show-refresh="true"
           data-show-toggle="false"
           data-show-columns="false"
           data-show-export="true"
           data-detail-view="false"
           data-detail-formatter="detailFormatter"
           data-minimum-count-columns="2"
           data-show-pagination-switch="false"
           data-pagination="true"
           data-id-field="id"
           data-page-list="[10, 25]"
           data-show-footer="false"
           data-side-pagination="server"
           data-url="fromdata.json"
           data-striped="true"
           data-sort-name="id"
           data-sort-order="asc"
           data-click-to-select="true"
           data-response-handler="responseHandler">
    </table>
</div>

</body>
</html>

<script>
    var $table = $('#table'),
            $remove = $('#remove'),
            selections = [];
    function initTable() {
        $table.bootstrapTable({
            height: 500,
            method:'post',
            queryParams:function (param) {
                param.var1=11;
                return param;
            },
            columns: [
                    {
                        field: 'state',
                        checkbox: true,
                        align: 'center',
                        radio:false,  //  true 单选， false多选
                        valign: 'middle'
                    }, {
                    title: 'Item ID',
                    field: 'id',
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                    footerFormatter: totalTextFormatter
                },
                    {
                        field: 'name',
                        title: 'Item Name',
                        sortable: true,
                        editable: true,
                        footerFormatter: totalNameFormatter,
                        align: 'center'
                    }, {
                    field: 'price',
                    title: 'Item Price',
                    sortable: true,
                    align: 'center',
                    editable: {
                        type: 'text',
                        title: 'Item Price',
                        validate: function (value) {
                            value = $.trim(value);
                            if (!value) {
                                return 'This field is required';
                            }
                            if (!/^\$/.test(value)) {
                                return 'This field needs to start width $.'
                            }
                            var data = $table.bootstrapTable('getData'),
                                    index = $(this).parents('tr').data('index');
                            console.log(data[index]);
                            return '';
                        }
                    },
                    footerFormatter: totalPriceFormatter
                }, {
                    field: 'operate',
                    title: 'Item Operate',
                    align: 'center',
                    events: operateEvents,
                    formatter: operateFormatter
                }

            ]
        });
        // sometimes footer render error.
        setTimeout(function () {
            $table.bootstrapTable('resetView');
        }, 200);
        $table.on('check.bs.table uncheck.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
            // save your data, here just save the current page
            selections = getIdSelections();
            // push or splice the selections if you want to save all data selections
        });
        $table.on('expand-row.bs.table', function (e, index, row, $detail) {
            if (index % 2 == 1) {
                $detail.html('Loading from ajax request...');
                $.get('LICENSE', function (res) {
                    $detail.html(res.replace(/\n/g, '<br>'));
                });
            }
        });
        $table.on('all.bs.table', function (e, name, args) {
            console.log(" 全选 ");
        });
        $remove.click(function () {
            var ids = getIdSelections();
            $table.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
            $remove.prop('disabled', true);
        });

        $("#search").click(function () {
            $table.bootstrapTable('refresh',
                    {
                        query: {foo: 'bar'}
                    });
        });
        $("#searchFix").click(function () {
            $table.bootstrapTable('resetSearch','data111');
        });

        $(window).resize(function () {
            // 重新设置表的高度
            $table.bootstrapTable('resetView', {
                height: getHeight()
            });
        });
    }
    // 获取所有的选中数据
    function getIdSelections() {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
    // 设置默认选中
    function responseHandler(res) {
        $.each(res.rows, function (i, row) {
            row.state = $.inArray(row.id, selections) !== -1;
        });
        return res;
    }
    // 生成详细信息方法
    function detailFormatter(index, row) {
        var html = [];
        $.each(row, function (key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });
        return html.join('');
    }
    // 生成操作方法
    function operateFormatter(value, row, index) {
        return [
            '<a class="like" href="javascript:void(0)" title="Like">',
            '<i class="glyphicon glyphicon-heart"></i>',
            '</a>  ',
            '<a class="remove" href="javascript:void(0)" title="Remove">',
            '<i class="glyphicon glyphicon-remove"></i>',
            '</a>'
        ].join('');
    }
    // 操作事件
    window.operateEvents = {
        'click .like': function (e, value, row, index) {
            alert('You click like action, row: ' + JSON.stringify(row));
        },
        'click .remove': function (e, value, row, index) {
            $table.bootstrapTable('remove', {
                field: 'id',
                values: [row.id]
            });
        }
    };
    function totalTextFormatter(data) {
        return 'Total';
    }
    function totalNameFormatter(data) {
        return data.length;
    }
    function totalPriceFormatter(data) {
        var total = 0;
        $.each(data, function (i, row) {
            total += +(row.price.substring(1));
        });
        return '$' + total;
    }
    function getHeight() {
        return $(window).height() - $('h1').outerHeight(true);
    }
    initTable();
</script>
