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
    <title>废水阀值管理</title>
    <%
        String enterpriseId=request.getParameter("id");
    %>
    <script type="text/javascript">
        var enterpriseId='<%=enterpriseId%>';
    </script>
    <style>
        .Node-frame-menubar {
            width: auto;
            height: 400px;
            position: relative;
            left: 0px;
            border-right: 1px solid #e5e5e5;
            padding: 10px;
        }
        .list-group-item{
            cursor: default;
        }
    </style>
</head>
<body>
<div class="form-div" style="width: 99%;">
    <a id="headTitle" href="javascript:void(0)" class="list-group-item active">废水阀值管理</a>
    <div style="width: 90%">
        <form class="form-horizontal" role="form" id="flowForm" method="post" style="margin-top: 20px;">
            <div class="alert alert-success" style="margin-left: 100px;text-align: center;font-size: 15px;">流量</div>
            <input type="hidden" name="id" class="form-control" />
            <input type="hidden" name="type" value="WW" class="form-control" />
            <input type="hidden" name="pollutantCode" value="WWFlow" class="form-control" />
            <input type="hidden" name="enterpriseId" value="" class="form-control"/>
            <div class="form-group">
                <label for="overValue" class="col-sm-2 control-label">流量超标值<span class="text-danger">(*)</span>：</label>
                <div class="col-sm-4">
                    <input type="text" name="overValue" class="form-control" readonly/>
                </div>
            </div>
            <div class="form-group">
                <label for="maxValue" class="col-sm-2 control-label">流量异常上限值<span class="text-danger">(*)</span>：</label>
                <div class="col-sm-4">
                    <input type="text" name="maxValue" class="form-control" readonly/>
                </div>
                <label for="minValue" class="col-sm-2 control-label">流量异常下限值<span class="text-danger">(*)</span>：</label>
                <div class="col-sm-4">
                    <input type="text" name="minValue" class="form-control" readonly/>
                </div>
            </div>
        </form>
        <form class="form-horizontal" role="form" id="WWCODForm" method="post" style="margin-top: 20px;">
            <div class="alert alert-success" style="margin-left: 100px;text-align: center;font-size: 15px;">化学需氧量(COD)</div>
            <input type="hidden" name="id" class="form-control" />
            <input type="hidden" name="type" value="WW" class="form-control" />
            <input type="hidden" name="pollutantCode" value="WWCOD" class="form-control" />
            <input type="hidden" name="enterpriseId" value="" class="form-control" />
            <div class="form-group">
                <label for="overValue" class="col-sm-2 control-label">流量超标值<span class="text-danger">(*)</span>：</label>
                <div class="col-sm-4">
                    <input type="text" name="overValue" class="form-control" readonly/>
                </div>
            </div>
            <div class="form-group">
                <label for="maxValue" class="col-sm-2 control-label">流量异常上限值<span class="text-danger">(*)</span>：</label>
                <div class="col-sm-4">
                    <input type="text" name="maxValue" class="form-control" readonly/>
                </div>
                <label for="minValue" class="col-sm-2 control-label">流量异常下限值<span class="text-danger">(*)</span>：</label>
                <div class="col-sm-4">
                    <input type="text" name="minValue" class="form-control" readonly/>
                </div>
            </div>
        </form>
        <form class="form-horizontal" role="form" id="WWNH3-NForm" method="post" style="margin-top: 20px;">
            <div class="alert alert-success" style="margin-left: 100px;text-align: center;font-size: 15px;">氨氮</div>
            <input type="hidden" name="id" class="form-control" />
            <input type="hidden" name="type" value="WW" class="form-control" />
            <input type="hidden" name="pollutantCode" value="WWNH3-N" class="form-control" />
            <input type="hidden" name="enterpriseId" value="" class="form-control" />
            <div class="form-group">
                <label for="overValue" class="col-sm-2 control-label">流量超标值<span class="text-danger">(*)</span>：</label>
                <div class="col-sm-4">
                    <input type="text" name="overValue" class="form-control" readonly/>
                </div>
            </div>
            <div class="form-group">
                <label for="maxValue" class="col-sm-2 control-label">流量异常上限值<span class="text-danger">(*)</span>：</label>
                <div class="col-sm-4">
                    <input type="text" name="maxValue" class="form-control" readonly/>
                </div>
                <label for="minValue" class="col-sm-2 control-label">流量异常下限值<span class="text-danger">(*)</span>：</label>
                <div class="col-sm-4">
                    <input type="text" name="minValue" class="form-control" readonly/>
                </div>
            </div>
        </form>
        <form class="form-horizontal" role="form" id="WWPHForm" method="post" style="margin-top: 20px;">
            <div class="alert alert-success" style="margin-left: 100px;text-align: center;font-size: 15px;">PH值</div>
            <input type="hidden" name="id" class="form-control" />
            <input type="hidden" name="type" value="WW" class="form-control" />
            <input type="hidden" name="pollutantCode" value="WWPH" class="form-control" />
            <input type="hidden" name="enterpriseId" value="" class="form-control" />
            <div class="form-group">
                <label for="overValue" class="col-sm-2 control-label">流量超标值<span class="text-danger">(*)</span>：</label>
                <div class="col-sm-4">
                    <input type="text" name="overValue" class="form-control" readonly/>
                </div>
            </div>
            <div class="form-group">
                <label for="maxValue" class="col-sm-2 control-label">流量异常上限值<span class="text-danger">(*)</span>：</label>
                <div class="col-sm-4">
                    <input type="text" name="maxValue" class="form-control" readonly/>
                </div>
                <label for="minValue" class="col-sm-2 control-label">流量异常下限值<span class="text-danger">(*)</span>：</label>
                <div class="col-sm-4">
                    <input type="text" name="minValue" class="form-control" readonly"/>
                </div>
            </div>
        </form>
        <div class="form-horizontal">
            <div class="form-group">
                <div class="col-sm-4"></div>
                <div class="col-sm-2">
                    <button id="editForm" type="button" class="btn btn-success editBtn" style="display: none">保存</button>
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
<script>
    $(function(){
        $('#toEditForm').click(function(){
            $(this).hide();
            $('.form-control').removeAttr('readonly');
            $('.editBtn').show();
        });
    })
</script>
</body>
</html>
