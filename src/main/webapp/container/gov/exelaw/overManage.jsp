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
                            <label for="">所属区域：</label>
                            <select class="form-control s_blockLevelId" style="width: 266px;">
                            </select>
                            -
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
                            <div id="fine-uploader-gallery"></div>
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

<script src="<%=request.getContextPath()%>/container/gov/dispatch/scripts/loadBlockLevelAndBlockOption.js"></script>
<script src="<%=request.getContextPath()%>/container/gov/exelaw/scripts/overManage.js"></script>
</body>
</html>
