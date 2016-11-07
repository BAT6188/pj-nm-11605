<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String dispatchTaskId = request.getParameter("id");
%>
<script>
    var dispatchTaskId='<%=dispatchTaskId%>'
</script>
<!DOCTYPE html>
<html>
<head>
    <title>行政处罚</title>
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
                                <label for="s_caseName">案件名称：</label> <input type="text" id="s_caseName" style="width: 180px;" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label>立案时间：</label>
                                <div id="datetimepicker1" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                    <input class="form-control" size="16" id="start_filingDate"  type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                                -
                                <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                    <input class="form-control" size="16" id="end_filingDate"  type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                            </div>
                        </form>
                    <p></p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button type="button" class="btn btn-default" onclick="resetQuery()"><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#demoForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
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
<div class="modal fade" id="demoForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 1017px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">行政处罚信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="caseName" class="col-sm-2 control-label">案件名称<span class="text-danger">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="removeId" name="removeId">
                            <input type="hidden" id="dispatchTaskId" name="dispatchTaskId">
                            <input type="text" id="caseName" name="caseName" class="form-control"
                                   data-message="案件名称不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <div class="col-sm-1">
                            <button type="button" class="btn btn-primary" id="select" data-toggle="modal" data-target="#lawListForm">选择</button>
                        </div>

                        <label for="filingDate" class="col-sm-2 control-label">立案时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="filingDate"  name="filingDate" type="text" value="" data-message="立案时间不能为空"
                                       data-easytip="position:top;class:easy-red;" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="code" class="col-sm-2 control-label">立案号<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="code" name="code" class="form-control"
                                   data-message="立案号不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>

                        <label for="" class="col-sm-2 control-label">决定书文号<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="decideCode" name="decideCode" class="form-control"
                                   data-message="决定书文号不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="caseSource" class="col-sm-2 control-label">案件来源<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select id="caseSource" name="caseSource" class="form-control">
                                <option value="1">12369</option>
                                <option value="2">区长热线</option>
                                <option value="3">市长热线</option>
                                <option value="4">现场监察</option>
                                <option value="0">监控中心</option>
                            </select>
                        </div>

                        <label for="caseReason" class="col-sm-2 control-label">案由<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="caseReason" name="caseReason" class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="provision" class="col-sm-2 control-label">违反条款<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="provision" name="provision" class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>

                        <label for="exeDesc" class="col-sm-2 control-label">履行情况<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select id="exeDesc" name="exeDesc" class="form-control">
                                <option value="1">代履行</option>
                                <option value="2">自觉履行</option>
                                <option value="3">申请法院强制执行</option>
                                <option value="4">尚未执行</option>
                                <option value="5">无须执行</option>
                                <option value="6">已无法执行</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-sm-2 control-label">处罚类型<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select id="type" name="type" class="form-control">
                                <option value="1">罚款</option>
                                <option value="2">警告</option>
                                <option value="3">责令停产整顿</option>
                                <option value="4">责令停产、停业、关闭</option>
                            </select>
                        </div>

                        <label for="money" class="col-sm-2 control-label">处罚金额（万元）<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="money" name="money" class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exeDate" class="col-sm-2 control-label">处罚执行时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="exeDate"  name="exeDate" type="text" value="" data-message="不能为空"
                                       data-easytip="position:top;class:easy-red;" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>

                        <label for="endDate" class="col-sm-2 control-label">处罚终止时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="endDate"  name="endDate" type="text" value="" data-message="不能为空"
                                       data-easytip="position:top;class:easy-red;" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attn" class="col-sm-2 control-label">经办人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="attn" name="attn" class="form-control"
                                   data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>

                        <label for="closedDate" class="col-sm-2 control-label">结案日期<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="closedDate"  name="closedDate" type="text" value="" data-message="不能为空"
                                       data-easytip="position:top;class:easy-red;" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label">决定书处罚内容：</label>
                        <div class="col-sm-10">
                            <textarea id="content" name="content" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="save">保存</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<%--选择执法列表--%>
<div class="modal fade" id="lawListForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 1017px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">执法列表</h4>
            </div>
            <div class="modal-body">
                <form class="form-inline">
                    <div class="form-group">
                        <label for="s_enterpriseName">企业名称：</label> <input type="text" id="s_enterpriseName" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label for="s_source">信息来源：</label>
                        <select id="s_source" class="form-control">
                        <option value="">全部</option>
                        <option value="1">12369</option>
                        <option value="2">区长热线</option>
                        <option value="3">市长热线</option>
                        <option value="4">现场监察</option>
                        <option value="0">监控中心</option>
                        </select>

                    </div>
                    <div class="form-group">
                        <button type="button" id="sc" class="btn btn-md btn-success queryBtn"><span>查询</span></button>
                    </div>

                </form>

                <br/>
                <div class="tableBox">
                    <table id="lawTable" class="table table-striped table-responsive">
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="selected">选择</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/exelaw/scripts/punish.js"></script>
</body>
</html>
