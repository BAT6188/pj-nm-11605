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
    <a id="headTitle" href="javascript:void(0)" class="list-group-item active">废水阀值管理</a>
    <div style="width: 90%">
        <div id="wasteWaterForm">
            <div class="form-horizontal">
                <div class="form-group">
                    <div class="col-sm-6">
                        <form class="form-horizontal" id="wwFlowForm" role="form" method="post" style="margin-top: 20px;">
                            <input type="hidden" name="id" class="form-control" />
                            <input type="hidden" name="type" value="ww" />
                            <input type="hidden" name="pollutantCode" value="wwFlow"/>
                            <input type="hidden" name="enterpriseId" value=""/>
                            <input type="hidden" name="createTime" value="" class="form-control" />
                            <div class="alert alert-success" style="margin-left: 100px;text-align: center;font-size: 15px;">流量</div>
                            <div class="form-group">
                                <label for="overValue" class="col-sm-4 control-label">流量超标值：</label>
                                <div class="col-sm-8">
                                    <input type="text" id="firstInput" name="overValue" title="流量超标值" class="form-control needCheck" readonly
                                           data-message="不能为空"
                                           data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="maxValue" class="col-sm-4 control-label">流量异常上限值：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="maxValue" class="form-control needCheck" title="流量异常上限值" readonly
                                           data-message="不能为空"
                                           data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="minValue" class="col-sm-4 control-label">流量异常下限值：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="minValue" class="form-control needCheck" title="流量异常下限值" readonly
                                           data-message="不能为空"
                                           data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-sm-6">
                        <form class="form-horizontal" id="wwOxygenForm" role="form" method="post" style="margin-top: 20px;">
                            <input type="hidden" name="id" class="form-control" />
                            <input type="hidden" name="type" value="ww" />
                            <input type="hidden" name="pollutantCode" value="wwOxygen"/>
                            <input type="hidden" name="enterpriseId" value=""/>
                            <input type="hidden" name="createTime" value="" class="form-control" />
                            <div class="alert alert-success" style="margin-left: 0px;text-align: center;font-size: 15px;">化学需氧量(COD)</div>
                            <div class="form-group">
                                <label for="overValue" class="col-sm-4 control-label">化学需氧量超标值：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="overValue" class="form-control needCheck" title="化学需氧量超标值" readonly
                                           data-message="不能为空"
                                           data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="maxValue" class="col-sm-4 control-label">化学需氧量异常上限值：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="maxValue" class="form-control needCheck" title="化学需氧量异常上限值" readonly
                                           data-message="不能为空"
                                           data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="minValue" class="col-sm-4 control-label">化学需氧量异常下限值：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="minValue" class="form-control needCheck" title="化学需氧量异常下限值" readonly
                                           data-message="不能为空"
                                           data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="form-horizontal">
                <div class="form-group">
                    <div class="col-sm-6">
                        <form id="wwNitrogenForm" role="form" method="post" style="margin-top: 20px;">
                            <input type="hidden" name="id" class="form-control" />
                            <input type="hidden" name="type" value="ww" />
                            <input type="hidden" name="pollutantCode" value="wwNitrogen"/>
                            <input type="hidden" name="enterpriseId" value=""/>
                            <input type="hidden" name="createTime" value="" class="form-control" />
                            <div class="alert alert-success" style="margin-left: 100px;text-align: center;font-size: 15px;">氨氮</div>
                            <div class="form-group">
                                <label for="overValue" class="col-sm-4 control-label">氨氮超标值：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="overValue" class="form-control needCheck" title="氨氮超标值" readonly
                                           data-message="不能为空"
                                           data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="maxValue" class="col-sm-4 control-label">氨氮异常上限值：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="maxValue" class="form-control needCheck" title="氨氮异常上限值" readonly
                                           data-message="不能为空"
                                           data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="minValue" class="col-sm-4 control-label">氨氮异常下限值：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="minValue" class="form-control needCheck" title="氨氮异常下限值" readonly
                                           data-message="不能为空"
                                           data-easytip="position:top;class:easy-red;" placeholder="（毫克/立方米）"/>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-sm-6">
                        <form class="form-horizontal" id="wwPhForm" role="form" method="post" style="margin-top: 20px;">
                            <input type="hidden" name="id" class="form-control" />
                            <input type="hidden" name="type" value="ww" />
                            <input type="hidden" name="pollutantCode" value="wwPh"/>
                            <input type="hidden" name="enterpriseId" value=""/>
                            <input type="hidden" name="createTime" value="" class="form-control" />
                            <div class="alert alert-success" style="margin-left: 0px;text-align: center;font-size: 15px;">PH值</div>
                            <div class="form-group">
                                <label for="overValue" class="col-sm-4 control-label">PH值超标值：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="overValue" class="form-control needCheck" title="PH值超标值" readonly
                                           data-message="不能为空"
                                           data-easytip="position:top;class:easy-red;" placeholder="（毫克/升）"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="maxValue" class="col-sm-4 control-label">PH值异常上限值：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="maxValue" class="form-control needCheck" title="PH值异常上限值" readonly
                                           data-message="不能为空"
                                           data-easytip="position:top;class:easy-red;" placeholder="（毫克/升）"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="minValue" class="col-sm-4 control-label">PH值异常下限值：</label>
                                <div class="col-sm-8">
                                    <input type="text" name="minValue" class="form-control needCheck" title="PH值异常下限值" readonly
                                           data-message="不能为空"
                                           data-easytip="position:top;class:easy-red;" placeholder="（毫克/升）"/>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
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
<script src="<%=request.getContextPath()%>/container/company/portThreshold/scripts/wasteWaterPT.js"></script>
</body>
</html>
