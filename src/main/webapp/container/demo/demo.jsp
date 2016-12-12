<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/common_include.jsp"%>
    <title>Demo</title>
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
                                <label for="s_name">姓名：</label> <input type="text" id="s_name" style="width: 180px;" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="s_age">年龄：</label> <input type="text" id="s_age"  style="width: 180px;" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="">创建时间：</label>
                                <div id="datetimepicker1" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                    <input class="form-control" size="16" id="start_createTime"  type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                                -
                                <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                    <input class="form-control" size="16" id="end_createTime"  type="text" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                            </div>
                        </form>
                    <p></p>
                    <form class="form-inline">
                        <div class="form-group">
                            <label for="s_name">姓名：</label> <input type="text" id="demo_s_name" style="width: 180px;" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="s_age">年龄：</label> <input type="text" id="demo_s_age"  style="width: 180px;" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label for="">创建时间：</label>
                            <div id="demo_s_datetimepicker1" class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="demo_sstart_createTime"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            -
                            <div class="input-group date form_datetime" data-date="" data-date-format="yyyy-mm-dd hh:ii" data-link-field="sendTime">
                                <input class="form-control" size="16" id="demo_send_createTime"  type="text" value="" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                        </div>
                    </form>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button type="button" class="btn btn-default" onclick="resetQuery()"><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#demoForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#demoForm">
                        <i class="btnIcon edit-icon"></i><span>修改</span>
                    </button>
                    <button id="remove" type="button" class="btn btn-sm btn-danger">
                        <i class="btnIcon delf-icon"></i><span>删除</span>
                    </button>

                    <button id="demo_showWord" type="button" class="btn btn-sm btn-success">
                        <span>显示word</span>
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
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">姓名<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="removeId" name="removeId">
                            <input type="text" id="name" name="name" class="form-control"
                                   data-message="用户名不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>


                    </div>
                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label">年龄<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <input type="text" id="age" name="age" class="form-control"
                                   data-easyform="uint:0 100;"
                                   data-message="年龄必须为14~100范围内整数"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label">性格<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <select name="xg" class="form-control">
                                <option value="1">温柔</option>
                                <option value="2">奔放</option>
                                <option value="3">泼辣</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label">x坐标<span class="text-danger">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" id="longitude" name="longitude" class="form-control"
                                   data-easyform="float:8 10;"
                                   data-message="坐标必须为数字"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="age" class="col-sm-2 control-label">y坐标<span class="text-danger">*</span>：</label>
                        <div class="col-sm-3">
                            <input type="text" id="latitude" name="latitude" class="form-control"
                                   data-easyform="float:8 10;"
                                   data-message="坐标必须为数字"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <button type="button" class="btn btn-primary" id="mapMarkBtn">标绘</button>
                    </div>
                    <div class="form-group">
                        <label for="remark" class="col-sm-2 control-label">备注：</label>
                        <div class="col-sm-10">
                            <textarea  id="remark" name="remark" class="form-control" rows="5"
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
                <button type="button" class="btn btn-primary" id="save">保存</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<%@include file="/common/gis/map_mark.jsp"%>
<script src="<%=request.getContextPath()%>/container/demo/scripts/demo.js"></script>
</body>
</html>
