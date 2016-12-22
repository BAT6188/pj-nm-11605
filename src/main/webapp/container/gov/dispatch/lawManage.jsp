<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //portal中配置的根据用户角色不同分配的url参数不同，以此区分登录的是 监察大队领导角色（monitor_master） 还是 环保站人员角色（env_pro_sta）
    String role = request.getParameter("role");

    //判断是否是 环保站人员角色登录的现场检查
    String live_check = request.getParameter("live_check");
%>
<script>
    var role='<%=role%>'
    var live_check='<%=live_check%>'

    console.log("登录人员的角色是："+role)
    console.log("是否是环保站人员角色登录的现场检查："+live_check)

    $('.modal-body').attr('style','max-height: '+pageUtils.getFormHeight()+'px;overflow-y: auto;overflow-x: hidden;padding:10px;');
</script>
<!DOCTYPE html>
<html>
<head>
    <title>执法管理</title>
    <%--<%@include file="/common/msgSend/msgSend.jsp"%>--%>
    <style>
        .ui-autocomplete { z-index:2147483647;}
        #isNew{
            display: inline;
            top: -10px;
            position: relative;
            background: url('<%=request.getContextPath()%>/common/images/isNew.png') no-repeat;
        }
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
                            <label for="s_enterpriseName">企业名称：</label> <input type="text" id="s_enterpriseName" name="enterpriseName" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="s_source">信息来源：</label>
                            <select id="s_source" name="source" class="form-control caseSource" style="width: 266px;">
                                <option value="">全部</option>
                                <option value="0">监控中心</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="s_status">反馈状态：</label>
                            <select id="s_status" name="status" class="form-control" style="width: 266px;">
                                <option value="">全部</option>
                                <option value="1">未调度</option>
                                <option value="2">已发送</option>
                                <option value="3">已反馈</option>
                                <option value="4">已处罚</option>
                                <option value="5">已办结</option>
                            </select>
                        </div>
                    </form>
                    <p></p>
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="">事件时间：</label>
                            <div id="" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="">
                                <input class="form-control" size="16" id="start_eventTime" name="startEventTime"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            -
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="end_eventTime" name="endEventTime"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="blockLevelId">所属区域：</label>
                            <select class="form-control s_blockLevelId" name="blockLevelId" style="width: 266px;">
                            </select>
                            -
                            <select class="form-control s_blockId" name="blockId" style="width: 266px;">
                            </select>
                        </div>

                    </form>

                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <%--<button id="insert" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#newXianChangJianChaForm">--%>
                        <%--<i class="btnIcon edit-icon"></i><span>新增（现场监察）</span>--%>
                    <%--</button>--%>
                    <button id="dealWith" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#eventMsg">
                        <i class="btnIcon edit-icon"></i><span>处置</span>
                    </button>
                    <button id="feedback" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#feedbackForm">
                        <i class="btnIcon edit-icon"></i><span>反馈</span>
                    </button>
                    <button id="overBtn" type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#overDialog">
                        <i class="btnIcon delf-icon"></i><span>办结</span>
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

<div class="modal fade" id="overDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width: 844px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="">办结</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="overSuggestion" class="col-sm-2 control-label">办结意见：</label>
                        <div class="col-sm-10">
                            <input id="overId" name="id" type="hidden"/>
                            <textarea id="overSuggestion" name="overSuggestion" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="overSure">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<%--新增（现场监察）表单--%>
<div class="modal fade" id="newXianChangJianChaForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width: 844px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="">现场监察</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="eventTime" class="col-sm-2 control-label">事件时间：</label>
                        <div class="col-sm-4">
                            <div id="" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="">
                                <input class="form-control" id="eventTime_newXianChangJianChaForm"  name="eventTime" type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>

                        <label for="enterpriseName" class="col-sm-2 control-label">企业名称：</label>
                        <div class="col-sm-4">
                            <input type="text" id="enterpriseName_newXianChangJianChaForm" name="enterpriseName" class="form-control" data-message="企业名称不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                            <input type="hidden" id="enterpriseId_newXianChangJianChaForm" name="enterpriseId"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="blockLevelId" class="col-sm-2 control-label">所属网格<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select id="blockLevelId_newXianChangJianChaForm" name="blockLevelId" class="form-control">
                            </select>
                        </div>

                        <label for="blockId" class="col-sm-2 control-label"></label>
                        <div class="col-sm-4">
                            <select id="blockId_newXianChangJianChaForm" name="blockId" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="supervisor" class="col-sm-2 control-label">监管人员：</label>
                        <div class="col-sm-4">
                            <input type="text" id="supervisor_newXianChangJianChaForm" name="supervisor" class="form-control" disabled
                            />
                        </div>

                        <label for="supervisorPhone" class="col-sm-2 control-label">联系方式：</label>
                        <div class="col-sm-4">
                            <input type="text" id="supervisorPhone_newXianChangJianChaForm" name="supervisorPhone" class="form-control" disabled
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="overValue" class="col-sm-2 control-label">超标值：</label>
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
                    <div class="form-group">
                        <label for="sendRemark" class="col-sm-2 control-label"> 备注：</label>
                        <div class="col-sm-10">
                            <textarea name="sendRemark" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="saveXianChangJianChaBtn">发送</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
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
                <h4 class="modal-title" id="">查看反馈</h4>
            </div>
            <div class="modal-body">
                <h4 class="modal-title" id="">执法详情</h4>
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">事件时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input class="form-control" size="16" type="text" id="lookOverFeedbackForm_eventTime">
                        </div>

                        <label for="answer" class="col-sm-2 control-label">接电人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="lookOverFeedbackForm_answer" name="answer" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="enterpriseId" class="col-sm-2 control-label">企业名称<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="lookOverFeedbackForm_enterpriseName" class="form-control"
                            />
                        </div>

                        <label for="source" class="col-sm-2 control-label">信息来源<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select id="lookOverFeedbackForm_source" class="form-control caseSource">
                                <option value="0">监控中心</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="blockLevelId" class="col-sm-2 control-label">所属网格<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select id="lookOverFeedbackForm_blockLevelId" class="form-control">
                            </select>
                        </div>

                        <label for="blockId" class="col-sm-2 control-label"></label>
                        <div class="col-sm-4">
                            <select id="lookOverFeedbackForm_blockId" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="supervisor" class="col-sm-2 control-label">监管人员<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="lookOverFeedbackForm_supervisor" class="form-control"/>
                        </div>

                        <label for="supervisorPhone" class="col-sm-2 control-label">联系方式<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="lookOverFeedbackForm_supervisorPhone" class="form-control"
                            />
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="senderName" class="col-sm-2 control-label">发送人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="lookOverFeedbackForm_senderName" class="form-control" disabled
                            />
                        </div>

                        <label for="sendTime" class="col-sm-2 control-label">发送时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="lookOverFeedbackForm_sendTime" class="form-control" disabled/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="overValue" class="col-sm-2 control-label">超标值：</label>
                        <div class="col-sm-4">
                            <input type="text" id="lookOverFeedbackForm_overValue" class="form-control"
                            />
                        </div>

                        <label for="thrValue" class="col-sm-2 control-label">超标阀值：</label>
                        <div class="col-sm-4">
                            <input type="text" id="lookOverFeedbackForm_thrValue" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label">事件内容：</label>
                        <div class="col-sm-10">
                            <textarea id="lookOverFeedbackForm_content" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sendRemark" class="col-sm-2 control-label">备注：</label>
                        <div class="col-sm-10">
                            <textarea id="lookOverFeedbackForm_sendRemark" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                    <hr/>
                    <h4 class="modal-title" id="">执法记录-现场回传</h4>
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

<!--处置按钮弹出框 或 查看按钮弹出框 事件信息-->
<div class="modal fade" id="eventMsg" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
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
                        <label for="eventTime" class="col-sm-2 control-label">事件时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div id="datetimepicker1" class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="eventTime">
                                <input class="form-control" size="16" type="text" id="eventTime" name="eventTime" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                        <label for="answer" class="col-sm-2 control-label">接电人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="answer" name="answer" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">企业名称<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="enterpriseName" name="enterpriseName" class="form-control" data-message="企业名称不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                            <input type="hidden" id="enterpriseId" name="enterpriseId"/>
                        </div>
                        <label for="source" class="col-sm-2 control-label">信息来源<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select id="source" name="source" class="form-control caseSource">
                                <option value="0">监控中心</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="blockLevelId" class="col-sm-2 control-label">所属网格<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select id="blockLevelId" name="blockLevelId" class="form-control">
                            </select>
                        </div>
                        <label for="blockId" class="col-sm-2 control-label"></label>
                        <div class="col-sm-4">
                            <select id="blockId" name="blockId" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="supervisor" class="col-sm-2 control-label">监管人员<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="supervisor" name="supervisor" class="form-control"/>
                        </div>

                        <label for="supervisorPhone" class="col-sm-2 control-label">联系方式<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="supervisorPhone" name="supervisorPhone" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="senderName" class="col-sm-2 control-label">发送人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="senderName" name="senderName" class="form-control" disabled
                            />
                        </div>

                        <label for="sendTime" class="col-sm-2 control-label">发送时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="sendTime" name="sendTime" class="form-control lookover" disabled/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="overValue" class="col-sm-2 control-label">超标值：</label>
                        <div class="col-sm-4">
                            <input type="text" id="overValue" name="overValue" class="form-control"
                            />
                        </div>

                        <label for="thrValue" class="col-sm-2 control-label">超标阀值：</label>
                        <div class="col-sm-4">
                            <input type="text" id="thrValue" name="thrValue" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label">事件内容：</label>
                        <div class="col-sm-10">
                            <textarea id="content" name="content" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                    <hr/>
                    <div class="form-group">
                        <label for="sendRemark" class="col-sm-2 control-label">备注：</label>
                        <div class="col-sm-10">
                            <textarea id="sendRemark" name="sendRemark" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dispatchPersonName" class="col-sm-2 control-label">调度人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="dispatchPersonName" class="form-control"
                            />
                        </div>

                        <label for="dispatchTime" class="col-sm-2 control-label">调度时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="dispatchTime" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dispatchContent" class="col-sm-2 control-label">调度内容：</label>
                        <div class="col-sm-10">
                            <textarea id="dispatchContent" class="form-control" rows="4" cols="50" placeholder=""></textarea>
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
                <span id="isSendSmsSpan"><input type="checkbox" id="isSendSms"><label for="isSendSms">同时发送短信</label></span>
                <button type="button" class="btn btn-primary" id="dispatch" >调度</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!--现场监察监测报告-->
<div class="modal fade" id="monitorReport" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width:842px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="">现场监察监测报告</h4>
            </div>
            <div class="modal-body">
                <h4 class="modal-title" id="">事件信息</h4>
                <form class="form-horizontal" role="form">
                    <input type="hidden" id="id_monitorReport" name="id">
                    <div class="form-group">
                        <label for="eventTime" class="col-sm-2 control-label">事件时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div id="" class="input-group date form_datetime lookover" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="">
                                <input class="form-control noEdit" size="16" type="text" id="eventTime_monitorReport" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                        <label for="answer" class="col-sm-2 control-label">接电人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="answer_monitorReport" class="form-control noEdit"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="enterpriseName" class="col-sm-2 control-label">企业名称<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="enterpriseName_monitorReport"  class="form-control noEdit" data-message="企业名称不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                        <label for="source" class="col-sm-2 control-label">信息来源<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select id="source_monitorReport" class="form-control noEdit caseSource">
                                <option value="0">监控中心</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="blockLevelId" class="col-sm-2 control-label">所属网格<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select id="blockLevelId_monitorReport"  class="form-control noEdit">
                            </select>
                        </div>
                        <label for="blockId" class="col-sm-2 control-label"></label>
                        <div class="col-sm-4">
                            <select id="blockId_monitorReport" class="form-control noEdit">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="supervisor" class="col-sm-2 control-label">监管人员<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="supervisor_monitorReport" class="form-control noEdit"/>
                        </div>

                        <label for="supervisorPhone" class="col-sm-2 control-label">联系方式<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="supervisorPhone_monitorReport" class="form-control noEdit"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="senderName" class="col-sm-2 control-label">发送人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="senderName_monitorReport" class="form-control noEdit"
                            />
                        </div>

                        <label for="sendTime" class="col-sm-2 control-label">发送时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="sendTime_monitorReport" class="form-control noEdit"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="overValue" class="col-sm-2 control-label">超标值：</label>
                        <div class="col-sm-4">
                            <input type="text" id="overValue_monitorReport" class="form-control  noEdit"
                            />
                        </div>

                        <label for="thrValue" class="col-sm-2 control-label">超标阀值：</label>
                        <div class="col-sm-4">
                            <input type="text" id="thrValue_monitorReport" class="form-control noEdit"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label">事件内容：</label>
                        <div class="col-sm-10">
                            <textarea id="content_monitorReport" name="content" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sendRemark" class="col-sm-2 control-label">备注：</label>
                        <div class="col-sm-10">
                            <textarea id="sendRemark_monitorReport" name="sendRemark" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>

                </form>
                <h4 class="modal-title" id="">现场监察监测报告</h4>
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="supervisor" class="col-sm-2 control-label">报送人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="submitPerson_monitorReport" name="submitPerson" class="form-control" data-message="不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>

                        <label for="supervisorPhone" class="col-sm-2 control-label">联系方式：</label>
                        <div class="col-sm-4">
                            <input type="text" id="submitPersonPhone_monitorReport" name="submitPersonPhone" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="supervisorPhone" class="col-sm-2 control-label">备注：</label>
                        <div class="col-sm-10">
                            <textarea name="monitorReportRemark_monitorReport" name="monitorReportRemark" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
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
                <button type="button" class="btn btn-primary" id="save_monitorReport" >发送</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script>
    pageUtils.appendOptionFromDictCode(".caseSource",{code:"caseSource"})
</script>
<script src="<%=request.getContextPath()%>/common/scripts/dict.js"></script>
<script src="<%=request.getContextPath()%>/common/scripts/uploaderUtil.js"></script>
<script src="<%=request.getContextPath()%>/container/gov/dispatch/scripts/loadBlockLevelAndBlockOption.js"></script>
<script src="<%=request.getContextPath()%>/container/gov/dispatch/scripts/lawManage.js"></script>
</body>
</html>
