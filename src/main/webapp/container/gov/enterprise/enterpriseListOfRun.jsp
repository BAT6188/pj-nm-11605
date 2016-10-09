<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/common/common_include.jsp" flush="true"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8">
    <title>排污单位列表</title>
    <style>
        .form-inline table {
            width: 100%;
            border-width:1px 0px 0px 1px;
            margin: 0 auto;
            text-align: right;
            color: rgba(64, 64, 64, 1.00);
        }

        .form-inline table td{
            border-width:0px 1px 1px 0px;
            padding:10px 0px;
        }

        .form-inline table .lefttd{
            margin-left: 10px;
            text-align: left;
        }

        .form-inline table th{
            font-weight: normal;
            padding: 0 10px 0 0;
        }

        .form-inline table img {
            vertical-align: middle;
            margin: 0 0 5px 0;
        }
        label{
            margin-left: 10px;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <!--搜索区域-->
    <div class="alert">
        <form class="form-inline" role="form" id="searchform">
            <table>
                <tr>
                    <td><div class="form-group">
                        <label for="name">单位名称：</label>
                    </div></td>
                    <td class="lefttd"><div class="form-group">
                        <input type="text" id="name" name="name" class="form-control" />
                    </div></td>
                    <td><div class="form-group">
                        <label for="orgCode">组织机构代码：</label>
                    </div></td>
                    <td class="lefttd"><div class="form-group">
                        <input type="text" id="orgCode" name="orgCode" class="form-control" />
                    </div></td>
                </tr>
                <tr>
                    <td><div class="form-group">
                        <label for="pollutantType">单位类型：</label>
                    </div></td>
                    <td class="lefttd"><div class="form-group">
                        <input type="text" id="pollutantType" name="pollutantType" class="form-control" />
                    </div></td>
                    <td><div class="form-group">
                        <label for="area">所属行政区：</label>
                    </div></td>
                    <td class="lefttd"><div class="form-group">
                        <input type="text" id="area" name="area" class="form-control" />
                    </div></td>
                </tr>
                <tr>
                    <td><div class="form-group">
                        <label for="isSpecial">特殊监管排污单位：</label>
                    </div></td>
                    <td class="lefttd"><div class="form-group">
                        <input type="text" id="isSpecial" name="isSpecial" class="form-control" />
                    </div></td>
                    <td><div class="form-group">
                        <label for="superviseType">排污单位监管类型：</label>
                    </div></td>
                    <td class="lefttd"><div class="form-group">
                        <input type="text" id="superviseType" name="superviseType" class="form-control" />
                    </div></td>
                </tr>
                <tr>
                    <td colspan="4">
                        <button id="search" type="button" class="btn btn-success" >查询</button>
                        <button id="searchFix" type="button" class="btn btn-default" >重置查询</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <!--列表区域-->
    <div id="toolbar">
        <button id="add" type="button" class="btn btn-primary" data-toggle="modal" data-target="#demoForm" >新增</button>
        <button id="update" type="button" class="btn btn-info" data-toggle="modal" data-target="#demoForm" >修改</button>
        <button id="remove" type="button" class="btn btn-danger" >删除</button>
    </div>
    <table id="table"
           data-toolbar="#toolbar"
           data-show-header="true"
           data-card-view="false"
           data-search="false"
           data-show-refresh="false"
           data-show-toggle="false"
           data-show-columns="false"
           data-show-export="false"
           data-detail-view="false"
           data-detail-formatter="detailFormatter"
           data-minimum-count-columns="2"
           data-show-pagination-switch="false"
           data-pagination="true"
           data-id-field="id"
           data-page-list="[10, 20, 30]"
           data-show-footer="false"
           data-side-pagination="server"
           data-striped="true"
           data-sort-name="id"
           data-sort-order="asc"
           data-click-to-select="true"
           data-response-handler="responseHandler">
    </table>
</div>
<script type="text/javascript" src="scripts/enterpriseListOfRun.js"></script>
</body>
</html>
