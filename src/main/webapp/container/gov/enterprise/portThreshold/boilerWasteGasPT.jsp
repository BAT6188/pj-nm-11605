<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/8
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8">
    <title>锅炉废气阀值管理</title>
    <script type="text/javascript">
        var enterpriseId=enterpriseData.id;
        $('input[name="enterpriseId"]').val(enterpriseId);
    </script>
    <style>
        .list-group-item{
            cursor: default;
        }
    </style>
</head>
<body>
<div class="form-div" style="width: 99%;">
    <a id="headTitle" href="javascript:void(0)" class="list-group-item active">锅炉废气阀值管理</a>
    <div style="width: 90%">
        <div id="BWGForm">
            <form class="form-horizontal" id="BWGSootForm" role="form" method="post" style="margin-top: 20px;">
                <input type="hidden" name="id" class="form-control" />
                <input type="hidden" name="type" value="BWG" />
                <input type="hidden" name="pollutantCode" value="BWGSoot"/>
                <input type="hidden" name="enterpriseId" value=""/>
                <input type="hidden" name="createTime" value="" class="form-control" />
                <div class="alert alert-success" style="margin-left: 100px;text-align: center;font-size: 15px;">锅炉烟尘</div>
                <div class="form-group">
                    <label for="overValue" class="col-sm-2 control-label">烟尘超标值：</label>
                    <div class="col-sm-4">
                        <input type="text" id="firstInput" name="overValue" title="烟尘超标值" class="form-control" readonly
                               data-message="不能为空"
                               data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="maxValue" class="col-sm-2 control-label">烟尘异常上限值：</label>
                    <div class="col-sm-4">
                        <input type="text" name="maxValue" class="form-control" title="烟尘异常上限值" readonly
                               data-message="不能为空"
                               data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                    </div>
                    <label for="minValue" class="col-sm-2 control-label">烟尘异常下限值：</label>
                    <div class="col-sm-4">
                        <input type="text" name="minValue" class="form-control" title="烟尘异常下限值" readonly
                               data-message="不能为空"
                               data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                    </div>
                </div>
            </form>
            <form class="form-horizontal" id="BWGSO2Form" role="form" method="post" style="margin-top: 20px;">
                <input type="hidden" name="id" class="form-control" />
                <input type="hidden" name="type" value="BWG" />
                <input type="hidden" name="pollutantCode" value="BWGSO2"/>
                <input type="hidden" name="enterpriseId" value=""/>
                <input type="hidden" name="createTime" value="" class="form-control" />
                <div class="alert alert-success" style="margin-left: 100px;text-align: center;font-size: 15px;">锅炉二氧化硫</div>
                <div class="form-group">
                    <label for="overValue" class="col-sm-2 control-label">二氧化硫超标值：</label>
                    <div class="col-sm-4">
                        <input type="text" name="overValue" class="form-control" title="二氧化硫超标值" readonly
                               data-message="不能为空"
                               data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="maxValue" class="col-sm-2 control-label">二氧化硫异常上限值：</label>
                    <div class="col-sm-4">
                        <input type="text" name="maxValue" class="form-control" title="二氧化硫异常上限值" readonly
                               data-message="不能为空"
                               data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                    </div>
                    <label for="minValue" class="col-sm-2 control-label">二氧化硫异常下限值：</label>
                    <div class="col-sm-4">
                        <input type="text" name="minValue" class="form-control" title="二氧化硫异常下限值" readonly
                               data-message="不能为空"
                               data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                    </div>
                </div>
            </form>
            <form class="form-horizontal" id="BWGNOxForm" role="form" method="post" style="margin-top: 20px;">
                <input type="hidden" name="id" class="form-control" />
                <input type="hidden" name="type" value="BWG" />
                <input type="hidden" name="pollutantCode" value="BWGNOx"/>
                <input type="hidden" name="enterpriseId" value=""/>
                <input type="hidden" name="createTime" value="" class="form-control" />
                <div class="alert alert-success" style="margin-left: 100px;text-align: center;font-size: 15px;">锅炉氮氧化物</div>
                <div class="form-group">
                    <label for="overValue" class="col-sm-2 control-label">氮氧化物超标值：</label>
                    <div class="col-sm-4">
                        <input type="text" name="overValue" class="form-control" title="氮氧化物超标值" readonly
                               data-message="不能为空"
                               data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="maxValue" class="col-sm-2 control-label">氮氧化物异常上限值：</label>
                    <div class="col-sm-4">
                        <input type="text" name="maxValue" class="form-control" title="氮氧化物异常上限值" readonly
                               data-message="不能为空"
                               data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                    </div>
                    <label for="minValue" class="col-sm-2 control-label">氮氧化物异常下限值：</label>
                    <div class="col-sm-4">
                        <input type="text" name="minValue" class="form-control" title="氮氧化物异常下限值" readonly
                               data-message="不能为空"
                               data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                    </div>
                </div>
            </form>
        </div>
        <div class="form-horizontal">
            <div class="form-group">
                <div class="col-sm-4"></div>
                <div class="col-sm-2">
                    <button id="saveForm" type="button" class="btn btn-success editBtn" style="display: none">保存</button>
                </div>
                <div class="col-sm-2">
                    <button id="toEditForm" type="button" class="btn btn-success lookBtn">编辑</button>
                    <button id="resetEditForm" type="button" class="btn btn-default editBtn" style="display: none">置空</button>
                </div>
                <div class="col-sm-2">
                    <button id="cancelEditForm" type="button" class="btn btn-warning editBtn" style="display: none">取消</button>
                </div>
                <div class="col-sm-2"></div>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/enterprise/portThreshold/scripts/boilerWasteGasPT.js"></script>
</body>
</html>
