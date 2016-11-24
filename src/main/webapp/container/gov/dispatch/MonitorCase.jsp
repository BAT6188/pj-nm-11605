<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>监控中心</title>
    <%@include file="/common/msgSend/msgSend.jsp"%>
</head>
<style>
    a{
        color: #0b0c0d;
    }
    .nav-tabs > li.active > a, .nav-tabs > li.active > a:hover, .nav-tabs > li.active > a:focus{
        font-weight: bolder;
    }

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
<body>
<div class="content content1 clearfix">
    <div class="wrap">
        <div class="mainBox">
            <ul id="myTab" class="nav nav-tabs">
                <li>
                    <a href="#noDispath" data-toggle="tab" onclick="changeTab(1)">未调度</a>
                </li>
                <li><a href="#yesDispath" data-toggle="tab" onclick="changeTab(2)">已调度</a></li>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="dealBox">
                    <div class="sideTitle left">
                        <span class="blueMsg">
                            <img class="tipImg" src="<%=request.getContextPath()%>/common/images/searchTip.png" alt=""/>
                            <span class="text">查询</span>
                        </span>
                    </div>
                    <div class="queryBox marginLeft0">
                        <form class="form-inline">
                            <input id="status_search" name="status_search" type="hidden" value=""/>
                            <div class="form-group">
                                <label for="searchEnterpriseName">企业名称：</label> <input type="text" id="searchEnterpriseName" name="enterpriseName" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="">调度时间：</label>
                                <div id="datetimepicker1" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                    <input class="form-control" id="start_sendTime" name="startSendTime" size="16" type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                                -
                                <div id="datetimepicker2" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                    <input class="form-control" id="end_sendTime" name="endSendTime" size="16" type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                            </div>
                        </form>
                        <p></p>
                        <form class="form-inline">
                            <div class="form-group">
                                <label for="">原&nbsp;&nbsp;因：</label>
                                <select id="s_reason" name="reason" class="form-control caseReason" style="width: 301px;">
                                    <option value="">全部</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="">调度网格：</label>
                                <select id="s_blockLevelId" name="blockLevelId" class="form-control"  style="width: 262px;">
                                </select>
                                -
                                <select id="s_blockId" name="blockId" class="form-control"  style="width: 262px;">
                                </select>
                            </div>
                        </form>
                    </div>
                    <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                    <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                </div>
                <div class="tableBox">
                    <table id="table" class="table table-striped table-responsive">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!--调度单-->
<div class="modal fade" id="systemSendForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width:718px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="demoFormTitle">调度单</h4>
                <input type="hidden" id="id">
                <input type="hidden" id="removeId">
                <input type="hidden" id="status" name="status">
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="eventTime" class="col-sm-2 control-label">事件时间：</label>
                        <div class="col-sm-4">
                            <input type="text" id="eventTime" class="form-control" disabled
                            />
                        </div>

                        <label for="enterpriseName" class="col-sm-2 control-label">企业名称：</label>
                        <div class="col-sm-4">
                            <input type="text" id="enterpriseName" class="form-control"  disabled
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="blockLevelName" class="col-sm-2 control-label">所属网格：</label>
                        <div class="col-sm-4">
                            <input type="text" id="blockLevelName" class="form-control"  disabled
                            />
                        </div>

                        <label for="blockName" class="col-sm-2 control-label"></label>
                        <div class="col-sm-4">
                            <input type="text" id="blockName" class="form-control" disabled
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="supervisor" class="col-sm-2 control-label">监管人员：</label>
                        <div class="col-sm-4">
                            <input type="text" id="supervisor" class="form-control" disabled
                            />
                        </div>

                        <label for="supervisorPhone" class="col-sm-2 control-label">联系方式：</label>
                        <div class="col-sm-4">
                            <input type="text" id="supervisorPhone" class="form-control" disabled
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="overValue" class="col-sm-2 control-label">超标值：</label>
                        <div class="col-sm-4">
                            <input type="text" id="overValue" class="form-control" disabled
                            />
                        </div>

                        <label for="thrValue" class="col-sm-2 control-label">超标阀值：</label>
                        <div class="col-sm-4">
                            <input type="text" id="thrValue" class="form-control" disabled
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="content" class="col-sm-2 control-label">事件内容：</label>
                        <div class="col-sm-10">
                            <textarea id="content" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                    <hr/>

                    <div class="form-group">
                        <label for="senderName" class="col-sm-2 control-label">发送人：</label>
                        <div class="col-sm-4">
                            <input type="text" id="senderName" class="form-control"
                            />
                        </div>

                        <label for="sendTime" class="col-sm-2 control-label">发送时间：</label>
                        <div class="col-sm-4">
                            <input type="text" id="sendTime" class="form-control" disabled
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sendRemark" class="col-sm-2 control-label"> 备注：</label>
                        <div class="col-sm-10">
                            <textarea id="sendRemark" class="form-control" rows="4" cols="50" placeholder=""></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="smsSend">短信发送</button>
                <button type="button" class="btn btn-primary" id="send">发送</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!--查看 执法反馈列表-->
<div class="modal fade" id="feedbackListDialog" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 868px">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="">执法反馈列表</h4>
            </div>
            <div class="tableBox">
                <table id="feedbackRecordTable" class="table table-striped table-responsive">
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<%--查看执法反馈详情--%>
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
                            <input type="text" id="lawerName" name="lawerName" class="form-control"/>
                        </div>

                        <label for="phone" class="col-sm-2 control-label">联系方式<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="phone" name="phone" class="form-control"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exeTime" class="col-sm-2 control-label">执法时间<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <div  class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="exeTime">
                                <input class="form-control" size="16" type="text" id="exeTime" name="exeTime" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exeDesc" class="col-sm-2 control-label">执法详情：</label>
                        <div class="col-sm-10">
                            <textarea id="exeDesc" name="exeDesc" class="form-control" rows="4" cols="50" placeholder=""></textarea>
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
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="<%=request.getContextPath()%>/container/gov/dispatch/scripts/loadBlockLevelAndBlockOption.js"></script>
<script>
    var status_search="0";
    $(function () {
        $("#status_search").val(status_search)
        $('#myTab a:first').tab('show')
        loadBlockLevelAndBlockOption("#s_blockLevelId","#s_blockId")
        pageUtils.appendOptionFromDictCode(".caseReason",{code:"caseReason"})
    });
    function changeTab(f) {
        if (f=='1'){
            status_search=0
            gridTable.bootstrapTable('hideColumn',"status");
            gridTable.bootstrapTable('hideColumn',"queryFeedback");
            gridTable.bootstrapTable('showColumn',"operate");
        }else{
            status_search='!0'
            gridTable.bootstrapTable('showColumn',"status");
            gridTable.bootstrapTable('showColumn',"queryFeedback");
            gridTable.bootstrapTable('hideColumn',"operate");
        }
        $("#status_search").val(status_search)
        gridTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:pageUtils.PAGE_SIZE});
    }
</script>
<script src="<%=request.getContextPath()%>/common/scripts/dict.js"></script>
<script src="<%=request.getContextPath()%>/container/gov/dispatch/scripts/MonitorCase.js"></script>
</body>
</html>
