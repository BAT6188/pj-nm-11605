<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>申请委托监测</title>
    <style>
        .ui-autocomplete { z-index:2147483647;}
    </style>
</head>
<body>
<div class="content content1 clearfix">
    <div class="wrap">
        <div class="mainBox">
            <div class="dealBox">
                <div class="sideTitle left">
                        <span class="blueMsg">
                            <img class="tipImg" src="<%=request.getContextPath()%>/common/images/searchTip.png" alt=""/>
                            <span class="text">查询</span>
                        </span>
                </div>
                <div class="queryBox marginLeft0">
                        <form class="form-inline">
                            <div class="form-group">
                                <label for="s_enterpriseName">企业名称：</label> <input type="text" id="s_enterpriseName" style="width: 180px;" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="">监测时间：</label>
                                <div id="datetimepicker1" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                    <input class="form-control" size="16" id="start_monitorTime"  type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                                -
                                <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                    <input class="form-control" size="16" id="end_monitorTime"  type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                            </div>
                        </form>

                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button type="button" class="btn btn-default" onclick="resetQuery()"><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#demoForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#demoForm">
                        <i class="btnIcon edit-icon"></i><span>修改</span>
                    </button>
                    <button id="remove" type="button" class="btn btn-sm btn-danger">
                        <i class="btnIcon delf-icon"></i><span>删除</span>
                    </button>
                </p>
            </div>
            <div class="tableBox">
                <table id="table" class="table table-striped table-responsive">
                </table>
            </div>
        </div>
    </div>
</div>
<!--添加表单-->
<div class="modal fade" id="demoForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 917px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">委托监测</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <input type="hidden" id="id" name="id">
                    <input type="hidden" id="removeId" name="removeId">
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">企业名称<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="enterpriseName" name="enterpriseName" class="form-control" data-message="企业名称不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                            <input type="hidden" id="enterpriseId" name="enterpriseId"/>
                        </div>

                        <label for="enterpriseName" class="col-sm-2 control-label">监测内容<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="monitorContent" name="monitorContent" class="form-control" data-message="监测内容不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">申请单位<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="applyOrg" name="applyOrg" class="form-control" data-message="申请单位不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="enterpriseName" class="col-sm-2 control-label">申请人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="applicant" name="applicant" class="form-control" data-message="申请人不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">联系方式<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="applicantPhone" name="applicantPhone" class="form-control" data-message="联系方式不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="enterpriseName" class="col-sm-2 control-label">监测时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div  class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii">
                                <input class="form-control" size="16" type="text" value="" id="monitorTime" name="monitorTime" data-message="监测时间不能为空"
                                       data-easytip="position:top;class:easy-red;" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">委托单位地点<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="trustOrgAddress" name="trustOrgAddress" class="form-control" data-message="委托单位地点不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="enterpriseName" class="col-sm-2 control-label">监测地点<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="monitorAddress" name="monitorAddress" class="form-control" data-message="监测地点不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">监测内容详情<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="monitorContentDetail" name="monitorContentDetail" class="form-control" rows="4" cols="50" placeholder="" data-message="监测内容详情不能为空"
                                      data-easytip="position:top;class:easy-red;"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="saveAndSend">发送</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/exelaw/scripts/trustMonitor.js"></script>
</body>
</html>
