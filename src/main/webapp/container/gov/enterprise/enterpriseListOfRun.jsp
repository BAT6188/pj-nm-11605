<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/common/common_include.jsp" flush="true"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8">
    <title>排污单位列表</title>
</head>
<body>
<div class="container-fluid">
    <!--搜索区域-->
    <div class="alert alert-warning" style="display: none">
        <a href="#" class="close" data-dismiss="alert">
            &times;
        </a>
        <strong>提示！</strong><span>请选择列表中的一条数据！</span>
    </div>
        <form class="form-horizontal" role="form" id="searchform">
            <div class="form-group">
                <label for="name" class="col-sm-1 control-label">单位名称：</label>
                <div class="col-sm-4">
                    <input type="text" id="name" name="name" class="form-control" />
                </div>
                <label for="pollutantType" class="col-sm-2 control-label">单位类型：</label>
                <div class="col-sm-4">
                    <select class="form-control" id="pollutantType" name="pollutantType">
                        <option value="">全部</option>
                        <option value="01">废水</option>
                        <option value="02">废气</option>
                        <option value="03">污水处理厂</option>
                        <option value="04">重金属</option>
                        <option value="05">畜禽养殖</option>
                        <option value="06">固废</option>
                        <option value="07">危险废物</option>
                        <option value="08">省级实验室</option>
                        <option value="09">二级以上医院</option>
                        <option value="10">其他</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label for="orgCode" class="col-sm-1 control-label">组织机构代码：</label>
                <div class="col-sm-4">
                    <input type="text" id="orgCode" name="orgCode" class="form-control" />
                </div>
                <label for="superviseType" class="col-sm-2 control-label">排污单位监管类型：</label>
                <div class="col-sm-4">
                    <select style="width: 100%" class="form-control"  id="superviseType" name="superviseType">
                        <option value="">全部</option>
                        <option value="01">重点排污单位</option>
                        <option value="02">一般排污单位</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="area" class="col-sm-1 control-label">所属行政区：</label>
                <div class="col-sm-4">
                    <input type="text" id="area" name="area" class="form-control" />
                </div>
                <label for="isSpecial" class="col-sm-2 control-label">特殊监管排污单位：</label>
                <div class="col-sm-4">
                    <select style="width: 100%" class="form-control"  id="isSpecial" name="isSpecial">
                        <option value="">全部</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-9">

                </div>
                <div class="col-sm-1" style="text-align: right;">
                    <button id="search" type="button" class="btn btn-success" >查询</button>
                </div>
                <div class="col-sm-1" style="text-align: right;">
                    <button id="searchFix" type="button" class="btn btn-default" >重置</button>
                </div>
            </div>

        </form>
    </div>

    <!--列表区域-->
    <div id="toolbar">
        <button id="add" type="button" class="btn btn-primary" data-toggle="modal" data-target="#demoForm" >
            <span class="glyphicon glyphicon-plus"></span>新增
        </button>
        <button id="remove" type="button" class="btn btn-danger" >
            <span class="glyphicon glyphicon-minus"></span>删除
        </button>
        <button id="export" type="button" class="btn btn-success" >
            <span class="glyphicon glyphicon-export"></span>导出
        </button>
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
