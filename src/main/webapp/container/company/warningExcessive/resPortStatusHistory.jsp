<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/9
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%--<%@include file="/common/common_include.jsp"%>--%>
    <title>预警及排污超标处理情况报送</title>
    <script type="text/javascript">

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
                    <form class="form-inline" id="searchform">
                        <div class="form-group">
                            <label for="res_title" class="ui-widget">标题：</label> <input type="text" id="res_title" style="width: 180px;" class="form-control" />
                            <%--<input id="selCompanyBtn" style="color: #fff;background-color: #449d44;border-color: #398439; width:15%;" type="button" value="选择" class="form-control" data-toggle="modal" data-target="#demoForm"/>--%>
                        </div>
                        <div class="form-group">
                            <label for="">发布时间：</label>
                            <div id="datetimepicker" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd" data-link-field="sendTime">
                                <input class="form-control" size="16" id="start_createTime"  type="text" value="" readonly placeholder="开始时间">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            -
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd " data-link-field="sendTime">
                                <input class="form-control" size="16" id="end_createTime"  type="text" value="" readonly placeholder="结束时间">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </form>
                    <p></p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="reset" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <%--<button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#scfForm">--%>
                        <%--<i class="btnIcon add-icon"></i><span>新建</span>--%>
                    <%--</button>--%>
                    <%--<button id="disposal" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#eventMsg">--%>
                        <%--<i class="btnIcon edit-icon"></i><span>处置</span>--%>
                    <%--</button>--%>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#scfForm">
                        <i class="btnIcon edit-icon"></i><span>处置</span>
                    </button>
                    <button id="remove" type="button" class="btn btn-sm btn-danger">
                        <i class="btnIcon delf-icon"></i><span>删除</span>
                    </button>
                    <button id="export" type="button" class="btn btn-sm btn-success" >
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
<div class="modal fade" data-backdrop="static" id="scfForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title" id="demoFormTitle">处置情况</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="res_title" class="col-sm-2 control-label">标题：<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="removeId" name="removeId">
                            <input type="text" id="title" name="res_title" class="form-control"
                                   data-message="用户名不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="publishingUnit" class="col-sm-2 control-label">发布单位：<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="publishingUnit" name="publishingUnit" class="form-control"
                                   data-message="工艺处理不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="release_time" class="col-sm-2 control-label form_datetime">发布时间：<span class="text-danger">*</span>：</label>
                        <div class="col-sm-5">
                            <div id="datetimepicker2" class="input-group date col-md-10 form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="dtp_input" data-link-format="yyyy-mm-dd">
                                <input class="form-control" id="release_time" name="release_time" size="16" type="text" value="" readonly
                                       data-message="时间不能为空"
                                       data-easytip="position:top;class:easy-red;"
                                />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                        <label for="release_person" class="col-sm-2 control-label">发布人<span class="text-danger">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" id="release_person" name="release_person" class="form-control"
                                   data-message="工艺处理不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">

                        <label for="contact" class="col-sm-2 control-label">联系方式：<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="contact" name="contact" class="form-control"
                                   data-message="工艺处理不为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="realtimeData" class="col-sm-2 control-label">实时数据：<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="realtimeData" name="realtimeData" class="form-control"
                                   data-message="实时数据不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="maxValue" class="col-sm-2 control-label">上限值：<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="maxValue" name="maxValue" class="form-control"
                                   data-message="上限值不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group" style="display: none">

                        <label for="isNoTickling" class="col-sm-2 control-label">上限值：<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="isNoTickling" name="isNoTickling" class="form-control"
                                   data-message="上限值不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="solution" class="col-sm-2 control-label">解决方案*：</label>
                        <div class="col-sm-10">
                            <textarea  id="solution" name="solution" class="form-control" rows="5"
                                       data-message="解决方案不能为空"
                                       data-easytip="position:top;class:easy-red;"
                            ></textarea>
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
                <button type="button" class="btn btn-primary" id="send">发送</button>
                <button type="button" class="btn btn-primary" id="save">保存</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="<%=request.getContextPath()%>/container/company/warningExcessive/scripts/resPortStatusHistory.js"></script>
</body>
</html>

