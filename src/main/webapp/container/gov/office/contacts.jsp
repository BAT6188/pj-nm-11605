<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>人员信息管理</title>
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
                        <label for="s_name">姓名：</label> <input type="text" id="s_name" class="form-control" />
                        <label for="s_department">单位：</label> <input type="text" id="s_department" class="form-control" />
                        <label for="s_position">职务：</label><input type="text" id="s_position" class="form-control" />
                    </form>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="reset" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <br/><br>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#scfForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#scfForm">
                        <i class="btnIcon edit-icon"></i><span>修改</span>
                    </button>
                    <button id="remove" type="button" class="btn btn-sm btn-danger">
                        <i class="btnIcon delf-icon"></i><span>删除</span>
                    </button>
                    <button id="export" type="button" class="btn btn-sm btn-success">
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
                <h4 class="modal-title form-title">添加人员信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">姓名<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="removeId" name="removeId">
                            <input type="text" id="name" name="name" class="form-control"
                                   data-message="姓名不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="department" class="col-sm-2 control-label">所属部门<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="department" name="department" class="form-control"
                                   data-message="所属部门不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="position" class="col-sm-2 control-label">职务<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="position" name="position" class="form-control"
                                   data-message="职务不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="address" class="col-sm-2 control-label">单位地址<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="address" id="address" name="address" class="form-control"
                                   data-message="单位地址不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="tel" class="col-sm-2 control-label">座机号码<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="tel" name="tel" class="form-control"
                                   data-message="单位地址不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                        <label for="phone" class="col-sm-2 control-label">手机号码<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="phone" name="phone" class="form-control"
                                   data-message="单位地址不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
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
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/office/scripts/contacts.js"></script>
</body>
</html>
