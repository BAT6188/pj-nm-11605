<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>办结管理</title>
    <script src="<%=request.getContextPath()%>/common/scripts/dict.js"></script>
    <script>
        $('.modal-body').attr('style','max-height: '+pageUtils.getFormHeight()+'px;overflow-y: auto;overflow-x: hidden;padding:10px;');
    </script>
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
                            <label for="s_enterpriseName">企业名称：</label> <input type="text" id="s_enterpriseName" name="s_enterpriseName" class="form-control" />
                        </div>

                        <div class="form-group">
                            <label for="">事件时间：</label>
                            <div id="" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="">
                                <input class="form-control" size="16" id="start_eventTime"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            -
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="end_eventTime"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </form>
                    <p></p>
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="s_source">信息来源：</label>
                            <select id="s_source" name="s_source" class="form-control" style="width: 299px;">
                                <option value="">全部</option>
                                <option value="1">12369</option>
                                <option value="2">区长热线</option>
                                <option value="3">市长热线</option>
                                <option value="4">现场监察</option>
                                <option value="0">监控中心</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="">所属网格：</label>
                            <select class="form-control s_blockLevelId" style="width: 266px;display: none">
                            </select>
                            <%-----%>
                            <select class="form-control s_blockId" style="width: 266px;">
                            </select>
                        </div>

                    </form>

                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button type="button" class="btn btn-default" onclick="resetQuery()"><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
            </div>
            <div class="tableBox">
                <table id="table" class="table table-striped table-responsive">
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="detailDialog" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">查看</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">事件时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="removeId" name="removeId">
                            <input type="text" name="eventTime" class="form-control"
                            />
                        </div>

                        <label for="" class="col-sm-2 control-label">企业名称<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" name="enterpriseName" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">信息来源<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" name="source" class="form-control"
                            />
                        </div>

                        <label for="" class="col-sm-2 control-label">所属网格<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" name="blockName" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery" class="uploaderToggle aUploader"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="demoForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 951px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">查看</h4>
            </div>
            <div class="modal-body">
                <div class="modal-header">
                    <h4 class="modal-title form-title">企业基本信息</h4>
                </div>
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">企业名称：</label>
                        <div class="col-sm-4">
                            <input type="text" id="enterpriseName" name="enterpriseName" class="form-control"/>
                        </div>
                        <label for="" class="col-sm-2 control-label">企业类型：</label>
                        <div class="col-sm-4">
                            <input type="text" id="pollutantType" name="pollutantType" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">所属网格：</label>
                        <div class="col-sm-4">
                            <input type="text" id="blockLevelName" name="blockLevelName" class="form-control"/>
                        </div>
                        <label for="" class="col-sm-2 control-label"></label>
                        <div class="col-sm-4">
                            <input type="text" id="blockName" name="blockName" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">企业法人：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="artificialPerson" class="form-control"/>
                        </div>
                        <label for="" class="col-sm-2 control-label">联系方式：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="apPhone" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">监管人员：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="envPrincipal" class="form-control"/>
                        </div>
                        <label for="" class="col-sm-2 control-label">联系方式：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="epPhone" class="form-control"/>
                        </div>
                    </div>
                </form>

                <div class="modal-header">
                    <h4 class="modal-title form-title">事件内容</h4>
                </div>
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">事件内容：</label>
                        <div class="col-sm-10">
                            <textarea name="content" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">事件原因：</label>
                        <div class="col-sm-10">
                            <textarea name="caseReason" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                </form>

                <div class="modal-header">
                    <h4 class="modal-title form-title">处罚结果</h4>
                </div>
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">案件名称：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="caseName" class="form-control"/>
                        </div>
                        <label for="" class="col-sm-2 control-label">立案时间：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="filingDate" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">立案号：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="code" class="form-control"/>
                        </div>
                        <label for="" class="col-sm-2 control-label">决定书文号：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="decideCode" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                    <label for="" class="col-sm-2 control-label">违反条款：</label>
                    <div class="col-sm-4">
                        <input type="text" id="" name="provision" class="form-control"/>
                    </div>
                    <label for="" class="col-sm-2 control-label">履行情况：</label>
                    <div class="col-sm-4">
                        <input type="text" id="" name="exeDesc" class="form-control"/>
                    </div>
                </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">处罚类型：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="type" class="form-control"/>
                        </div>
                        <label for="" class="col-sm-2 control-label">处罚金额（万元）：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="money" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">处罚执行时间：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="exeDate" class="form-control"/>
                        </div>
                        <label for="" class="col-sm-2 control-label">处罚终止时间：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="endDate" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">经办人：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="attn" class="form-control"/>
                        </div>
                        <label for="" class="col-sm-2 control-label">结案日期：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="closedDate" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">决定书处罚内容：</label>
                        <div class="col-sm-10">
                            <textarea name="punishContent" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                </form>

                <div class="modal-header">
                    <h4 class="modal-title form-title">文件</h4>
                </div>
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery" class="uploaderToggle cUploader"></div>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<%--现场监察列表--%>
<div class="modal fade" data-backdrop="static" id="siteMonitoringReportDialog" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title" id="">现场监察</h4>
            </div>
            <div class="modal-body">
                <div class="tableBox">
                    <table id="table_siteMonitoringReportDialog" class="table table-striped table-responsive">
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" data-backdrop="static" id="addSiteMonitoringDialog" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="z-index: 1111111">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title" id="">现场监察</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">企业名称<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="dispatchId_addSiteMonitoringDialog" name="dispatchId">
                            <input type="text" id="" name="enterpriseName" class="form-control"
                                   data-message="企业名称不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>

                        <label for="blockId" class="col-sm-2 control-label">所属网格<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="" name="blockLevelId" class="form-control s_blockLevelId">
                            <select id="" name="blockId" class="form-control s_blockId" data-message="所属网格不能为空"
                                    data-easytip="position:top;class:easy-red;">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="checkPeople" class="col-sm-2 control-label">监察人员<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="checkPeople" name="checkPeople" class="form-control"
                                   data-message="监察人员不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="monitoringTime" class="col-sm-2 control-label">监察时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div id="" class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd">
                                <input class="form-control" id="monitoringTime" name="monitoringTime" size="16" type="text" value=""
                                       data-message="监察时间不能为空"
                                       data-easytip="position:top;class:easy-red;"
                                />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="isNotProblem" class="col-sm-3 control-label">是否存在问题<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4 radio">
                            <label><input type="radio" value="1" name="isNotProblem">是</label>
                            <label><input type="radio" value="2" name="isNotProblem" checked>否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">备注<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea  id="" name="sendRemark" class="form-control" rows="5"
                                       data-message="不能为空"
                                       data-easytip="position:top;class:easy-red;"
                            ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachmentIds" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery" class="uploaderToggle dUploader"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="save">保存</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!--查看反馈表单列表-->
<div class="modal fade" id="lookOverFeedbackForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width:842px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="">执法记录-现场回传</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="tableBox">
                        <table id="feedbackRecordTable" class="table table-striped table-responsive">
                        </table>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!--点反馈按钮打开反馈表单添加反馈信息 或 查看反馈表单详情-->
<div class="modal fade" id="feedbackForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabe3" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width:842px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="">执法反馈</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <input type="hidden" id="dispatchId" name="dispatchId">
                    <div class="form-group">
                        <label for="lawerName" class="col-sm-2 control-label">现场执法人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="lawerName" name="lawerName" class="form-control" data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="phone" class="col-sm-2 control-label">联系方式<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="phone" name="phone" class="form-control" data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exeTime" class="col-sm-2 control-label">执法时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div  class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="exeTime">
                                <input class="form-control" size="16" type="text" id="exeTime" name="exeTime" data-message="不能为空"
                                       data-easytip="position:top;class:easy-red;" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exeDesc" class="col-sm-2 control-label">执法详情<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="exeDesc" name="exeDesc" class="form-control" rows="4" cols="50" placeholder="" data-message="不能为空"
                                      data-easytip="position:top;class:easy-red;"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="caseReason" class="col-sm-2 control-label">事件原因<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="caseReason" name="caseReason" class="form-control" rows="4" cols="50" placeholder="" data-message="不能为空"
                                      data-easytip="position:top;class:easy-red;"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery" class="uploaderToggle bUploader"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="feedbackTo" data-toggle="modal">反馈</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="eventMsg_monitorCase" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width:943px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="">调度单</h4>
                <input type="hidden" id="id" name="id">
                <input type="hidden" id="removeId">
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">企业名称：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="enterpriseName" class="form-control" data-message="企业名称不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                            <input type="hidden" id="" name="enterpriseId"/>
                        </div>
                        <label for="" class="col-sm-2 control-label">事件时间：</label>
                        <div class="col-sm-4">
                            <input type="text" name="eventTime" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">企业环保负责人：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="supervisor" class="form-control"
                            />
                        </div>
                        <label for="" class="col-sm-2 control-label">联系方式：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="supervisorPhone" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">所属网格：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="blockName" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="portName" class="col-sm-2 control-label">排口名称：</label>
                        <div class="col-sm-4">
                            <input type="text" name="portName" class="form-control"
                            />
                        </div>
                        <label for="" class="col-sm-2 control-label">超标项：</label>
                        <div class="col-sm-4">
                            <input type="text" name="overObj" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">超标值：</label>
                        <div class="col-sm-4">
                            <input type="text" name="overValue" class="form-control" disabled
                            />
                        </div>

                        <label for="thrValue" class="col-sm-2 control-label">超标阀值：</label>
                        <div class="col-sm-4">
                            <input type="text" name="thrValue" class="form-control" disabled
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label">事件内容：</label>
                        <div class="col-sm-10">
                            <textarea name="content" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                    <hr/>

                    <div class="form-group">
                        <label for="senderName" class="col-sm-2 control-label">发送人：</label>
                        <div class="col-sm-4">
                            <input type="text" name="senderName" class="form-control"/>
                        </div>

                        <label for="sendTime" class="col-sm-2 control-label">发送时间：</label>
                        <div class="col-sm-4">
                            <input type="text" name="sendTime" class="form-control" disabled
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sendRemark" class="col-sm-2 control-label"> 备注：</label>
                        <div class="col-sm-10">
                            <textarea name="sendRemark" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dispatchPersonName" class="col-sm-2 control-label">调度人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="dispatchPersonName" name="dispatchPersonName" class="form-control"
                            />
                        </div>

                        <label for="dispatchTime" class="col-sm-2 control-label">调度时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="dispatchTime" name="dispatchTime" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dispatchContent" class="col-sm-2 control-label">调度内容：</label>
                        <div class="col-sm-10">
                            <textarea id="dispatchContent" name="dispatchContent" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <span id="isSendSmsSpan"><input type="checkbox" id="isSendSms"><label for="isSendSms">同时发送短信</label></span>
                <button type="button" class="btn btn-primary" id="dispatch" >调度</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="eventMsg_monitorOffice" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width:842px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="demoFormTitle">事件信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <input type="hidden" id="id" name="id">
                    <input type="hidden" id="removeId" name="removeId">
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">事件时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div id="datetimepicker1" class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd hh:ii">
                                <input class="form-control" size="16" type="text" value="" id="" name="eventTime" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>

                        <label for="" class="col-sm-2 control-label">接收人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="answer" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">事件对象<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="enterpriseName" class="form-control" data-message="事件对象不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                            <input type="hidden" id="" name="enterpriseId"/>
                        </div>

                        <label for="" class="col-sm-2 control-label">信息来源<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select id="" name="source" class="form-control caseSource">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">所属网格：</label>
                        <div class="col-sm-4">
                            <select id="" name="blockLevelId" class="form-control s_blockLevelId">
                            </select>
                        </div>

                        <label for="" class="col-sm-2 control-label"></label>
                        <div class="col-sm-4">
                            <select id="" name="blockId" class="form-control s_blockId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">监管人员：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="supervisor" class="form-control"/>
                        </div>

                        <%--<label for="supervisorPhone" class="col-sm-2 control-label">联系方式：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="supervisorPhone" class="form-control"
                            />
                        </div>--%>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">举报人姓名：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="informer" class="form-control"/>
                        </div>

                        <label for="" class="col-sm-2 control-label">联系方式：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="informerPhone" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">事件详情<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="" name="content" class="form-control" rows="4" cols="50" placeholder="" data-message="不能为空"
                                      data-easytip="position:top;class:easy-red;"></textarea>
                        </div>
                    </div>
                    <hr/>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">处理人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="senderName" class="form-control" data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>

                        <label for="" class="col-sm-2 control-label">联系方式<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="sendPhone" class="form-control" data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery" class="uploaderToggle zUploader"></div>
                        </div>
                    </div>


                </form>
            </div>
            <div class="modal-footer">
                <%--<input type="checkbox" id="isSendSms"><label for="isSendSms">同时发送短信</label>--%>
                <%--<button type="button" class="btn btn-primary" id="smsSend">短信发送</button>--%>
                <%--<button type="button" class="btn btn-primary" id="save">保存</button>--%>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script src="<%=request.getContextPath()%>/common/scripts/dict.js"></script>
<script src="<%=request.getContextPath()%>/common/scripts/uploaderUtil.js"></script>
<script src="<%=request.getContextPath()%>/container/gov/dispatch/scripts/loadBlockLevelAndBlockOption.js"></script>
<script src="<%=request.getContextPath()%>/container/gov/exelaw/scripts/overManage.js"></script>
</body>
</html>
