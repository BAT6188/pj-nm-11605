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

    <script type="text/javascript">
        $(function () {
            var $table = $('#table');
            var data = [
                {
                    "id": 0,
                    "name": "Item 0",
                    "price": "$0"
                },
                {
                    "id": 1,
                    "name": "Item 1",
                    "price": "$1"
                },
                {
                    "id": 2,
                    "name": "Item 2",
                    "price": "$2"
                },
                {
                    "id": 3,
                    "name": "Item 3",
                    "price": "$3"
                },
                {
                    "id": 4,
                    "name": "Item 4",
                    "price": "$4"
                },
                {
                    "id": 5,
                    "name": "Item 5",
                    "price": "$5"
                }
            ];
            $table.bootstrapTable({data: data});
        });

    </script>

</head>
<body>
<div class="container">

    <div class="alert alert-info"> 通过数据简单填充一个表</div>
    <p></p>
    <table id="table">
        <thead>
        <tr>
            <th data-field="id">ID</th>
            <th data-field="name">Item Name</th>
            <th data-field="price">Item Price</th>
        </tr>
        </thead>
    </table>
</div>


</body>
</html>