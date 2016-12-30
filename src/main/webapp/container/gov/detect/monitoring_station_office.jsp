<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>监测站办公室</title>
    <%--<%@include file="/common/msgSend/msgSend.jsp"%>--%>
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
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="">监测对象：</label> <input type="text" id="s_enterpriseName" style="width: 180px;" class="form-control" />
                            <label for="">监测内容：</label> <input type="text" id="s_monitorContent" style="width: 180px;" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="">委托时间：</label>
                            <div id="" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
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
                    <p></p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button type="button" class="btn btn-default" onclick="resetQuery()"><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <%--<button id="checkButton" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#lookOverFeedbackDetailForm">
                        <i class="btnIcon edit-icon"></i><span>查看</span>
                    </button>--%>
                    <button id="shouLiButton" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#demoForm">
                        <i class="btnIcon edit-icon"></i><span>受理</span>
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
<!--委托监测表单-->
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
                    <div class="form-group demoForms">
                        <label for="enterpriseName" class="col-sm-2 control-label">监测对象<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="enterpriseName" name="enterpriseName" class="form-control" data-message="企业名称不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="enterpriseName" class="col-sm-2 control-label demoForms">监测内容<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="monitorContent" name="monitorContent" class="form-control " data-message="监测内容不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group demoForms">
                        <label for="enterpriseName" class="col-sm-2 control-label">申请部门<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select id="applyOrgId" name="applyOrgId" style="width: 274px;" class="form-control demoForms"></select>
                        </div>

                        <label for="enterpriseName" class="col-sm-2 control-label">申请人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="applicant" name="applicant" class="form-control demoForms" data-message="申请人不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group demoForms">
                        <label for="enterpriseName" class="col-sm-2 control-label">联系方式<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="applicantPhone" name="applicantPhone" class="form-control demoForms" data-message="联系方式不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="enterpriseName" class="col-sm-2 control-label">委托时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div  class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd hh:ii">
                                <input class="form-control demoForms" size="16" type="text" value="" id="monitorTime" name="monitorTime" data-message="监测时间不能为空"
                                       data-easytip="position:top;class:easy-red;" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group demoForms">
                        <label for="enterpriseName" class="col-sm-2 control-label">委托单位地点<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="trustOrgAddress" name="trustOrgAddress" class="form-control demoForms" data-message="委托单位地点不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="enterpriseName" class="col-sm-2 control-label">监测地点<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="monitorAddress" name="monitorAddress" class="form-control demoForms" data-message="监测地点不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group demoForms">
                        <label for="enterpriseName" class="col-sm-2 control-label">监测内容详情<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="monitorContentDetail" name="monitorContentDetail" class="form-control demoForms" rows="4" cols="50" placeholder="" data-message="监测内容详情不能为空"
                                      data-easytip="position:top;class:easy-red;"></textarea>
                        </div>
                    </div>
                    <div class="form-group demoForms">
                        <label for="enterpriseName" class="col-sm-2 control-label">审核人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="auditor" name="monitorAddress" class="form-control demoForms" data-message="监测地点不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="enterpriseName" class="col-sm-2 control-label">联系方式：</label>
                        <div class="col-sm-4">
                            <input type="text" id="auditorPhone" name="monitorAddress" class="form-control demoForms" data-message="监测地点不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group demoForms">
                        <label for="enterpriseName" class="col-sm-2 control-label">审批时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="auditTime" name="auditTime" class="form-control demoForms" data-message="监测地点不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group demoForms">
                        <label for="enterpriseName" class="col-sm-2 control-label">审批意见<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="auditSuggestion" name="auditSuggestion" class="form-control demoForms" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                    <%--<div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery" class="uploaderToggle aUploader"></div>
                        </div>
                    </div>--%>

                    <div class="form-group ">
                        <label for="officeShouLiPersonName" class="col-sm-2 control-label">受理人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="officeShouLiPersonName" name="officeShouLiPersonName" class="form-control" data-message="受理人不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="officeShouLiTime" class="col-sm-2 control-label">受理时间<span class="text-danger">*</span>：</label>
                        <div id="datetimepicker2" class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="officeShouLiTime">
                            <input class="form-control" size="16" id="officeShouLiTime"  name="officeShouLiTime" type="text" value="" readonly >
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">发送至<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select name="sendToSelect" class="form-control"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="officeShouLiYiJian" class="col-sm-2 control-label">批示意见<span class="text-danger"></span>：</label>
                        <div class="col-sm-10">
                            <textarea id="officeShouLiYiJian" name="officeShouLiYiJian" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="sendButton">发送</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
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
                <h4 class="modal-title form-title">委托监测</h4>
            </div>
            <div class="modal-body">
                <div class="modal-header">
                    <h4 class="modal-title form-title">委托监测</h4>
                </div>
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">监测对象<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="enterpriseName_lookOverFeedbackDetailForm"  class="form-control" data-message="企业名称不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="enterpriseName" class="col-sm-2 control-label">监测内容<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="monitorContent_lookOverFeedbackDetailForm"  class="form-control" data-message="监测内容不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">申请部门<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="applyOrg_lookOverFeedbackDetailForm" class="form-control"/>
                        </div>

                        <label for="enterpriseName" class="col-sm-2 control-label">申请人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="applicant_lookOverFeedbackDetailForm"  class="form-control" data-message="申请人不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">联系方式<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="applicantPhone_lookOverFeedbackDetailForm"  class="form-control" data-message="联系方式不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="enterpriseName" class="col-sm-2 control-label">委托时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div  class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd hh:ii">
                                <input class="form-control" size="16" type="text" value="" id="monitorTime_lookOverFeedbackDetailForm"  data-message="监测时间不能为空"
                                       data-easytip="position:top;class:easy-red;" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">委托单位地点<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="trustOrgAddress_lookOverFeedbackDetailForm" class="form-control" data-message="委托单位地点不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="enterpriseName" class="col-sm-2 control-label">监测地点<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="monitorAddress_lookOverFeedbackDetailForm"  class="form-control" data-message="监测地点不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">监测内容详情<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="monitorContentDetail_lookOverFeedbackDetailForm"  class="form-control" rows="4" cols="50" placeholder="" data-message="监测内容详情不能为空"
                                      data-easytip="position:top;class:easy-red;"></textarea>
                        </div>
                    </div>
                </form>

                <div class="modal-header">
                    <h4 class="modal-title form-title">审批信息</h4>
                </div>
                <form class="form-horizontal" role="form">
                    <div class="form-group demoForms">
                        <label for="enterpriseName" class="col-sm-2 control-label">审核人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="auditor_lookOverFeedbackDetailForm" name="monitorAddress" class="form-control demoForms" data-message="监测地点不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="enterpriseName" class="col-sm-2 control-label">联系方式：</label>
                        <div class="col-sm-4">
                            <input type="text" id="auditorPhone_lookOverFeedbackDetailForm" name="monitorAddress" class="form-control demoForms" data-message="监测地点不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group demoForms">
                        <label for="enterpriseName" class="col-sm-2 control-label">审批时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="auditTime_lookOverFeedbackDetailForm" name="auditTime" class="form-control demoForms" data-message="监测地点不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group demoForms">
                        <label for="enterpriseName" class="col-sm-2 control-label">审批意见<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="auditSuggestion_lookOverFeedbackDetailForm" name="auditSuggestion" class="form-control demoForms" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                </form>

                <div class="modal-header">
                    <h4 class="modal-title form-title">受理信息</h4>
                </div>
                <form class="form-horizontal" role="form">
                    <div class="form-group ">
                        <label for="officeShouLiPersonName" class="col-sm-2 control-label">受理人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="officeShouLiPersonName_lookOverFeedbackDetailForm" name="officeShouLiPersonName" class="form-control" data-message="受理人不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="officeShouLiTime" class="col-sm-2 control-label">受理时间<span class="text-danger">*</span>：</label>
                        <div id="" class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="officeShouLiTime">
                            <input class="form-control" size="16" id="officeShouLiTime_lookOverFeedbackDetailForm"  name="officeShouLiTime" type="text" value="" readonly >
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="officeShouLiYiJian" class="col-sm-2 control-label">批示意见<span class="text-danger"></span>：</label>
                        <div class="col-sm-10">
                            <textarea id="officeShouLiYiJian_lookOverFeedbackDetailForm" name="officeShouLiYiJian" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                </form>

                <div class="modal-header">
                    <h4 class="modal-title form-title">监测站反馈</h4>
                </div>
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">监测人员<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="monitor"  class="form-control"/>
                        </div>
                        <label for="" class="col-sm-2 control-label">联系方式<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="monitorPhone"  class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">反馈内容<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <textarea id="feedbackContent"  class="form-control" rows="4" cols="50" ></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <hr>
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
<script src="<%=request.getContextPath()%>/container/gov/detect/scripts/monitoring_station_office.js"></script>
</body>
</html>
