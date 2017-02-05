<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/12
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>执法统计</title>
    <style type="text/css">
        .chart-list {
            text-align: center;
            height: 42px;
        }
        .chart-list li {
            float: left;
            width: 33.33%;
            height: 100%;
        }
        .ui-autocomplete { z-index:2147483647; }

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
                            <label for="s_name" class="ui-widget">企业名称：</label> <input type="text" id="s_name" style="width: 180px;" class="form-control" />
                            <%--<input id="selCompanyBtn" style="color: #fff;background-color: #449d44;border-color: #398439; width:15%;" type="button" value="选择" class="form-control" data-toggle="modal" data-target="#demoForm"/>--%>
                        </div>
                        <div class="form-group">
                            <label for="">日期：</label>
                            <div id="datetimepicker1" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm" data-link-field="sendTime">
                                <input class="form-control" size="16" id="start_createTime"  type="text" value="" readonly placeholder="开始时间">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            -
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm" data-link-field="sendTime">
                                <input class="form-control" size="16" id="end_createTime"  type="text" value="" readonly placeholder="结束时间">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </form>
                    <p></p>
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="">执法类型：</label>
                            <select class="form-control" name="" id="lawType">
                                <option value="">请选择</option>
                                <option value="1">12369</option>
                                <option value="2">区长热线</option>
                                <option value="3">市长热线</option>
                                <option value="0">监测中心</option>
                            </select>

                        </div>
                    </form>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button type="button" class="btn btn-default" onclick="resetQuery()"><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
            </div>
        <div class="tableBox">
            <div class="chart-box">
                <div class="chart-list">
                    <ul class="clearfix">
                        <li id="columnBtn" data-checked="1"><a href="javascript:;">柱状图</a></li>
                        <li  id="pieBtn" data-checked="2"><a href="javascript:;">饼状图</a></li>
                        <li id="lineBtn" data-checked="3"><a href="javascript:;">折线图</a></li>
                    </ul>
                </div>
                <div id="container" style="min-width:100%;min-height:100%;text-align: center;width:90%;"></div>
                <%--<div class="chart-content">--%>
                    <%--<div class="chartBox chartBox1">--%>
                        <%--<div class="chart">--%>
                            <%--<img src="<%=request.getContextPath()%>/common/images/tree/chart1.png" alt=""/>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="chartBox chartBox2">--%>
                        <%--<div class="chart">--%>
                            <%--<img src="<%=request.getContextPath()%>/common/images/tree/chart2.png" alt=""/>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="chartBox chartBox3">--%>
                        <%--<div class="chart">--%>
                            <%--<img src="<%=request.getContextPath()%>/common/images/tree/chart3.png" alt=""/>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </div>
            </div>
        </div>
    </div>
</div>


<%--执法管理列表--%>
<div class="modal fade" id="lawListForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 1017px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">执法管理列表</h4>
            </div>
            <div class="modal-body">
                <div class="tableBox">
                    <table id="lawTable" class="table table-striped table-responsive">
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="closeBtn" class="btn btn-default btn-cancel" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<%--执法详情 --%>
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
                    <%--<div class="form-group">--%>
                        <%--<label for="" class="col-sm-2 control-label">所属网格：</label>--%>
                        <%--<div class="col-sm-4">--%>
                            <%--<select id="" name="blockLevelId" class="form-control s_blockLevelId">--%>
                            <%--</select>--%>
                        <%--</div>--%>

                        <%--<label for="" class="col-sm-2 control-label"></label>--%>
                        <%--<div class="col-sm-4">--%>
                            <%--<select id="" name="blockId" class="form-control s_blockId">--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">所属网格：</label>
                        <div class="col-sm-4">
                            <input type="text" id="" name="blockName" class="form-control"
                            />
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

<script src="<%=request.getContextPath()%>/common/scripts/dict.js"></script>
<script src="<%=request.getContextPath()%>/common/scripts/uploaderUtil.js"></script>
<script src="<%=request.getContextPath()%>/container/gov/dispatch/scripts/loadBlockLevelAndBlockOption.js"></script>
<script src="<%=request.getContextPath()%>/container/gov/statistics/scripts/law_statistics.js"></script>
<script type="text/javascript">
    pageUtils.appendOptionFromDictCode(".caseSource",{code:"caseSource"})
    $('.modal-body').attr('style','max-height: '+pageUtils.getFormHeight()+'px;overflow-y: auto;overflow-x: hidden;padding:10px;');
    $( function() {

        $( "#s_name" ).autocomplete({
            source: function( request, response ) {
                $.ajax( {
                    url: rootPath + "/action/S_enterprise_Enterprise_list.action",
                    dataType: "json",
                    type:'post',
                    data: {
                        name: request.term
                    },
                    success: function( data ) {
                        for(var i = 0;i<data.rows.length;i++){
                            console.log(data.rows[i].name);
                            var result = [];
                            for(var i = 0; i <  data.rows.length; i++) {
                                result.push(data.rows[i].name);
                            }
                            response( result);
                        }
                    }
                } );
            },
        } );
    } );

</script>

</body>
</html>
