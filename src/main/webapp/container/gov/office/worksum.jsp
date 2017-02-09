<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>工作总结系统</title>
    <style>
        .nav-tabs li a{
            width: 200px;
            text-align: center;
            font-size: 15px;
            color: #337ab7;
        }
        .active{
            border-bottom: hidden;
        }
        .active a{
            color: black;
        }
    </style>
    <script>
        $('#s_pubOrgId').val(orgId);
    </script>
</head>
<body>
<div class="content content1 clearfix">
    <div class="wrap">
        <div class="mainBox">
            <ul id="myTab" class="nav nav-tabs">
                <li class="active" onclick="changeTab(1)"><a href="#plan" data-toggle="tab">计划</a></li>
                <li onclick="changeTab(2)"><a href="#schedule" data-toggle="tab">进度</a></li>
                <li onclick="changeTab(3)"><a href="#sumEnd" data-toggle="tab" >总结</a></li>
            </ul>
            <div class="dealBox" style="border-top: hidden">
                <div class="sideTitle left">
                        <span class="blueMsg">
                            <img class="tipImg" src="<%=request.getContextPath()%>/common/images/searchTip.png" alt=""/>
                            <span class="text">查询</span>
                        </span>
                </div>
                <div class="queryBox marginLeft0">
                        <form role="form" id="searchform">
                            <input type="hidden" id="s_type" name="type" value="1" class="form-control"/>
                            <div class="form-inline">
                                <div class="form-group">
                                    <label for="s_title">工作标题：</label> <input type="text" id="s_title" name="title" style="width: 254px;" class="form-control" />
                                </div>
                                <div class="form-group">
                                    <label for="publishStatus" class="labelMarginLeft">发布状态：</label>
                                    <select class="form-control" name="publishStatus" style="width: 238px;">
                                        <option value="">全部</option>
                                        <option value="0">未发布(本单位数据)</option>
                                        <option value="1">已发布(本单位数据)</option>
                                    </select>
                                </div>
                            </div>
                            <p></p>
                            <div class="form-inline">
                                <div class="form-group">
                                    <label for="s_title">发布部门：</label> <input type="text" id="s_pubOrgName" name="pubOrgName" style="width: 254px;" class="form-control" />
                                </div>
                                <div class="form-group">
                                    <label for="s_pubTime" class="labelMarginLeft">发布时间：</label>
                                    <div id="datetimepicker1" class="input-group date form_datetime" data-date=""  data-date-format="yyyy-mm-dd" data-link-field="registTime" data-link-format="yyyy-mm-dd">
                                        <input class="form-control" size="16" type="text" id="startTime" name="startTime" value="" readonly placeholder="开始时间">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                    —
                                    <div id="datetimepicker2" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="registTime" data-link-format="yyyy-mm-dd">
                                        <input class="form-control" size="16" type="text" id="endTime" name="endTime" value="" readonly placeholder="结束时间">
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                            </div>
                        </form>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="reset" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <br/><br>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#workSumForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning">
                        <i class="btnIcon edit-icon"></i><span>修改</span>
                    </button>
                    <button id="remove" type="button" class="btn btn-sm btn-danger">
                        <i class="btnIcon delf-icon"></i><span>删除</span>
                    </button>
                    <button id="export" type="button" class="btn btn-sm btn-success">
                        <span class="glyphicon glyphicon-export"></span>导出
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
<div class="modal fade"  data-backdrop="static"  id="workSumForm" data-form-type="add"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog"  style="width: 900px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">创建工作总结</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label"><span class="titleName">工作计划</span>标题<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id" class="form-control">
                            <input type="hidden" id="publishStatus" name="publishStatus" class="form-control">
                            <input type="hidden" id="removeId" name="removeId" class="form-control">
                            <input type="text" id="title" name="title" class="form-control needEdit"
                                   data-message="标题不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <%--<label for="pubTime" class="col-sm-2 control-label">提交时间：</label>--%>
                        <%--<div class="col-sm-4">--%>
                            <%--&lt;%&ndash;<input class="form-control" id="pubTime" name="pubTime" placeholder="系统自动添加" size="16" type="text" value="" readonly>&ndash;%&gt;--%>
                            <%--<div id="pubTimeContent" class="input-group date form_date" data-date="" data-link-field="createTime" data-date-format="yyyy-mm-dd" >--%>
                                <%--<input class="form-control" id="createTime" name="createTime" size="16" type="text"  readonly--%>
                                       <%--data-message="发布时间不能为空"--%>
                                       <%--data-easytip="position:top;class:easy-red;">--%>
                                <%--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>--%>
                                <%--<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <%--<label for="createTime" class="col-sm-2 control-label">提交时间：<span class="text-danger">*</span>：</label>--%>
                        <%--<div class="col-sm-4">--%>
                            <%--<div id="pubTimeContent" class="input-group date form_date" data-date="" data-link-field="createTime"  data-link-format="yyyy-mm-dd">--%>
                                <%--<input class="form-control needEdit" id="createTime" name="createTime" size="16" type="text" value="" readonly--%>
                                       <%--data-message="发布时间不能为空"--%>
                                       <%--data-easytip="position:top;class:easy-red;">--%>
                                <%--<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>--%>
                                <%--<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>--%>
                            <%--</div>--%>
                        <%--</div>--%>



                        <label for="createTime" class="col-sm-2 control-label">提交时间：<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div id="pubTimeContent" class="input-group date form_datetime col-md-12" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                                <input class="form-control" id="createTime" name="createTime" size="16" type="text" value="" readonly
                                       data-message="时间不能为空"
                                       data-easytip="position:top;class:easy-red;"
                                />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="pubOrgName" class="col-sm-2 control-label">发布单位：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="pubOrgId" name="pubOrgId" class="form-control">
                            <input type="text" id="pubOrgName" name="pubOrgName" class="form-control" readonly >
                        </div>
                        <label for="type" class="col-sm-2 control-label">类型：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="type" name="type" class="form-control" >
                            <input type="text" id="typeName" name="typeName" class="form-control" readonly >
                            <%--<select class="form-control" id="type" name="type" readonly>
                                <option value="1">工作计划</option>
                                <option value="2">工作进度</option>
                                <option value="3">工作总结</option>
                            </select>--%>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-sm-2 control-label"><span class="titleName">工作计划</span>描述<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea  id="description" name="description" class="form-control needEdit" rows="5"
                                       data-message="描述不能为空"
                                       data-easytip="position:top;class:easy-red;"
                            ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件</label>
                        <div class="col-sm-10">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-md btn-warning" id="publishBtn">发布</button>
                <button type="button" class="btn btn-primary needHide" id="saveWorkSum">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<%@include file="/common/paizhao/paizhao.jsp"%>
<script src="<%=request.getContextPath()%>/container/gov/office/scripts/worksum.js"></script>
</body>
</html>
