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
</script>
<!DOCTYPE html>
<html>
<head>
    <title>现场监察</title>
    <script src="<%=request.getContextPath()%>/common/scripts/dict.js"></script>
    <script>
        $('.modal-body').attr('style','max-height: '+pageUtils.getFormHeight()+'px;overflow-y: auto;overflow-x: hidden;padding:10px;');
    </script>
</head>
<style>
    .ui-autocomplete { z-index:2147483647;}
</style>
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
                            <select id="s_source" name="source" class="form-control" style="width: 266px;">
                                <option value="">全部</option>
                                <option value="1">12369</option>
                                <option value="2">区长热线</option>
                                <option value="3">市长热线</option>
                                <option value="4">现场监察</option>
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
                                <input class="form-control" size="16" id="start_eventTime" name="startEventTime" type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            -
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="end_eventTime" name="endEventTime" type="text" value="" readonly>
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
                <button type="button" class="btn btn-default" onclick="resetQuery()"><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
            </div>
            <div class="tableBox">
                <table id="table" class="table table-striped table-responsive">
                </table>
            </div>
        </div>
    </div>
</div>

<!--查看反馈表单列表-->
<div class="modal fade" id="lookOverFeedbackForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width:842px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" style="text-align: center">执法详情</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" style="margin-right: 20px;">
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">事件时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input class="form-control" size="16" type="text" id="lookOverFeedbackForm_eventTime" name="eventTime">
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
                            <input type="text" id="lookOverFeedbackForm_enterpriseName" name="enterpriseName" class="form-control"
                            />
                        </div>

                        <label for="source" class="col-sm-2 control-label">信息来源<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select id="lookOverFeedbackForm_source" name="source" class="form-control">
                                <option value="1">12369</option>
                                <option value="2">区长热线</option>
                                <option value="3">市长热线</option>
                                <option value="4">现场监察</option>
                                <option value="0">监控中心</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="blockLevelId" class="col-sm-2 control-label">所属网格<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input id="lookOverFeedbackForm_blockLevelId" name="blockLevelName" class="form-control">
                            </input>
                        </div>

                        <label for="blockId" class="col-sm-2 control-label"></label>
                        <div class="col-sm-4">
                            <input id="lookOverFeedbackForm_blockId" name="blockName" class="form-control">
                            </input>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="supervisor" class="col-sm-2 control-label">监管人员<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="lookOverFeedbackForm_supervisor" name="supervisor" class="form-control"/>
                        </div>

                        <label for="supervisorPhone" class="col-sm-2 control-label">联系方式<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="lookOverFeedbackForm_supervisorPhone" name="supervisorPhone" class="form-control"
                            />
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="senderName" class="col-sm-2 control-label">发送人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="lookOverFeedbackForm_senderName" name="senderName" class="form-control" disabled
                            />
                        </div>

                        <label for="sendTime" class="col-sm-2 control-label">发送时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="lookOverFeedbackForm_sendTime" name="sendTime" class="form-control" disabled/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="overValue" class="col-sm-2 control-label">超标值：</label>
                        <div class="col-sm-4">
                            <input type="text" id="lookOverFeedbackForm_overValue" name="overValue" class="form-control"
                            />
                        </div>

                        <label for="thrValue" class="col-sm-2 control-label">超标阀值：</label>
                        <div class="col-sm-4">
                            <input type="text" id="lookOverFeedbackForm_thrValue" name="thrValue" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label">事件内容：</label>
                        <div class="col-sm-10">
                            <textarea id="lookOverFeedbackForm_content" name="content" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sendRemark" class="col-sm-2 control-label">备注：</label>
                        <div class="col-sm-10">
                            <textarea id="lookOverFeedbackForm_sendRemark" name="sendRemark" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                    <hr/>
                    <div class="modal-header" style="margin-bottom: 15px;">
                        <h4 class="modal-title" style="text-align: center">现场监察监测报告</h4>
                    </div>
                    <div class="form-group">
                        <label for="submitPerson" class="col-sm-2 control-label">报送人：</label>
                        <div class="col-sm-4">
                            <input type="text" name="submitPerson" class="form-control"/>
                        </div>

                        <label for="submitPersonPhone" class="col-sm-2 control-label">联系方式：</label>
                        <div class="col-sm-4">
                            <input type="text" name="submitPersonPhone" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="monitorReportRemark" class="col-sm-2 control-label">备注：</label>
                        <div class="col-sm-10">
                            <textarea id="monitorReportRemark" name="monitorReportRemark" class="form-control" rows="4" cols="50" placeholder=""></textarea>
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
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script src="<%=request.getContextPath()%>/common/scripts/uploaderUtil.js"></script>
<script src="<%=request.getContextPath()%>/container/gov/dispatch/scripts/loadBlockLevelAndBlockOption.js"></script>
<script src="<%=request.getContextPath()%>/container/gov/pControlRoom/scripts/onSiteReport.js"></script>
</body>
</html>
