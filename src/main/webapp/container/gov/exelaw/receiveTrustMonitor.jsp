<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>接收委托监测</title>
    <%@include file="/common/msgSend/msgSend.jsp"%>
    <style>
        #isNew{
            display: inline;
            top: -10px;
            position: relative;
            background: url('<%=request.getContextPath()%>/common/images/isNew.png') no-repeat;
        }
    </style>
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
                    <form  id="searchform">
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="s_enterpriseName">监测对象：</label> <input type="text" id="s_enterpriseName" name="enterpriseName" style="width: 237px;" class="form-control" />
                                <label for="applyOrgId">&nbsp;委托部门：</label> <input type="text" name="applyOrgId" style="width: 237px;" class="form-control" />
                            </div>
                        </div>
                        <p></p>
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="s_enterpriseName">监测内容：</label>
                                <select name="monitorContent" class="form-control" style="width: 237px;">
                                    <option value="">全部</option>
                                    <option value="1">水源地监测报告</option>
                                    <option value="2">大气污染防治监测报告</option>
                                    <option value="3">水污染防治监测报告</option>
                                    <option value="4">噪声监测报告</option>
                                    <option value="5">土壤污染防治监测报告</option>
                                </select>
                                <label for="status">&nbsp;反馈状态：</label>
                                <select name="status" class="form-control" style="width: 237px;">
                                    <option value="">全部</option>
                                    <option value="1">未反馈</option>
                                    <option value="7">已反馈</option>
                                </select>
                            </div>
                        </div>
                        <p></p>
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="">委托时间：</label>
                                <div id="datetimepicker1" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                    <input class="form-control" name="start_monitorTime" size="16" id="start_monitorTime"  type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                                -
                                <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                    <input class="form-control" name="end_monitorTime" size="16" id="end_monitorTime"  type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                            </div>
                        </div>
                        <p></p>
                    </form>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button type="button" id="searchFix" class="btn btn-default"><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <%--<button id="checkButton" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#lookOverFeedbackDetailForm">
                        <i class="btnIcon edit-icon"></i><span>查看</span>
                    </button>--%>
                    <button id="shenPiButton" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#demoForm">
                        <i class="btnIcon edit-icon"></i><span>审批</span>
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
                    <input type="hidden" id="id" name="id" class="form-control">
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
                        <label for="enterpriseName" class="col-sm-2 control-label">委托部门<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="applyOrgId" name="applyOrgId" class="form-control" data-message="委托部门不能为空"
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

                        <label for="enterpriseName" class="col-sm-2 control-label">委托时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div  class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd hh:ii">
                                <input class="form-control" size="16" type="text" value="" id="monitorTime" name="monitorTime" data-message="委托时间不能为空"
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
                        <label for="enterpriseName" class="col-sm-2 control-label">委托原由<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="monitorContentDetail" name="monitorContentDetail" class="form-control" rows="4" cols="50" placeholder="" data-message="委托原由不能为空"
                                      data-easytip="position:top;class:easy-red;"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="auditor" class="col-sm-2 control-label">审批人员<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="auditorForSend" name="auditor" class="form-control edit" data-message="审批人员不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="auditTime" class="col-sm-2 control-label">审批时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div id="" class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control edit" size="16" id="auditTimeForSend" name="auditTime"  type="text" data-message="审批时间不能为空"
                                       data-easytip="position:top;class:easy-red;" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">职务：</label>
                        <div class="col-sm-4">
                            <input type="text" id="auditPositionForSend" name="auditPosition" class="form-control edit"/>
                        </div>

                        <label for="" class="col-sm-2 control-label">联系方式：</label>
                        <div class="col-sm-4">
                            <input type="text" id="auditorPhoneForSend" name="auditorPhone" class="form-control edit"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">审批意见<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="auditSuggestionForSend" name="auditSuggestion" class="form-control edit" rows="4" cols="50" placeholder=""></textarea>
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
                <button type="button" class="btn btn-primary" id="saveAndAgreeAndSend">同意并发送</button>
                <button type="button" class="btn btn-primary" id="saveAndNotAgree">不同意</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!--审批意见-->
<div class="modal fade" id="auditForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 917px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">审批意见</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <input type="hidden" id="trustMonitorId" name="trustMonitorId">
                    <div class="form-group">
                        <label for="auditor" class="col-sm-2 control-label">审批人员<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="auditor" name="auditor" class="form-control" data-message="审批人员不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="auditTime" class="col-sm-2 control-label">审批时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="auditTime" name="auditTime"  type="text" data-message="审批时间不能为空"
                                       data-easytip="position:top;class:easy-red;" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">职务：</label>
                        <div class="col-sm-4">
                            <input type="text" id="auditPosition" name="auditPosition" class="form-control"/>
                        </div>

                        <label for="" class="col-sm-2 control-label">联系方式<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="auditorPhone" name="auditorPhone" class="form-control" data-message="联系方式不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">审批意见：</label>
                        <div class="col-sm-10">
                            <textarea id="auditSuggestion" name="auditSuggestion" class="form-control" rows="4" cols="50" ></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="save">发送</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal" id="cancel">取消</button>
            </div>
        </div>
    </div>
</div>

<!--查看反馈详情表单-->
<div class="modal fade" id="lookOverFeedbackDetailForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 917px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">申请信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <input type="hidden" name="id">
                    <input type="hidden" name="removeId">
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">监测对象<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" name="enterpriseName" class="form-control" data-message="企业名称不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                            <input type="hidden" name="enterpriseId"/>
                        </div>
                        <label for="monitorContent" class="col-sm-2 control-label">监测内容<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select name="monitorContent" class="form-control">
                                <option value="1">水源地监测报告</option>
                                <option value="2">大气污染防治监测报告</option>
                                <option value="3">水污染防治监测报告</option>
                                <option value="4">噪声监测报告</option>
                                <option value="5">土壤污染防治监测报告</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="applyOrgId" class="col-sm-2 control-label">委托部门<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" name="applyOrgId" class="form-control" data-message="委托部门不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="applicant" class="col-sm-2 control-label">委托人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" name="applicant" class="form-control" data-message="委托人不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="applicantPhone" class="col-sm-2 control-label">联系方式<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" name="applicantPhone" class="form-control" data-message="联系方式不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="monitorTime" class="col-sm-2 control-label">委托时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div  class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd hh:ii">
                                <input class="form-control" size="16" type="text" value="" name="monitorTime" data-message="委托时间不能为空"
                                       data-easytip="position:top;class:easy-red;" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="trustOrgAddress" class="col-sm-2 control-label">被委托部门<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" name="trustOrgAddress" class="form-control" data-message="被委托部门不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="monitorAddress" class="col-sm-2 control-label">监测地点<span class="text-danger"></span>：</label>
                        <div class="col-sm-4">
                            <input type="text" name="monitorAddress" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">委托原由<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea name="monitorContentDetail" class="form-control" rows="4" cols="50" placeholder="" data-message="委托原由不能为空"
                                      data-easytip="position:top;class:easy-red;"></textarea>
                        </div>
                    </div>
                </form>
                <div class="modal-header">
                    <h4 class="modal-title form-title">监测站反馈</h4>
                </div>
                <form class="form-horizontal" role="form" style="margin-top: 10px;">
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">监测人员<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="monitor" name="monitor" class="form-control"/>
                        </div>
                        <label for="" class="col-sm-2 control-label">联系方式<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="monitorPhone" name="monitorPhone"  class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">反馈内容<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="feedbackContent" name="feedbackContent"  class="form-control" rows="4" cols="50" ></textarea>
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
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/common/scripts/uploaderUtil.js"></script>
<%--<script src="<%=request.getContextPath()%>/container/gov/dispatch/scripts/loadBlockLevelAndBlockOption.js"></script>--%>
<script src="<%=request.getContextPath()%>/container/gov/exelaw/scripts/receiveTrustMonitor.js"></script>
</body>
</html>
