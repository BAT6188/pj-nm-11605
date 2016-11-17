<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/common_include.jsp"%>
    <title>office模板管理</title>
</head>
<body>
<div id="templateManager" class="content content1 clearfix">
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
                                <label for="s_name">名称：</label> <input type="text" id="s_name" name="s_name" style="width: 180px;" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="s_fileName">模板文件名称：</label> <input type="text" id="s_fileName" name="s_fileName"  style="width: 180px;" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="s_dataFileName">模板数据名称：</label> <input type="text" id="s_dataFileName" name="s_dataFileName"  style="width: 180px;" class="form-control" />
                            </div>
                        </form>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button type="button" class="btn btn-default" onclick="resetQuery()"><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#templateFormModal">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#templateFormModal">
                        <i class="btnIcon edit-icon"></i><span>修改</span>
                    </button>
                    <button id="remove" type="button" class="btn btn-sm btn-danger">
                        <i class="btnIcon delf-icon"></i><span>删除</span>
                    </button>

                    <button id="tm_editWord" type="button" class="btn btn-sm btn-success">
                        <span>编辑</span>
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
<div class="modal fade" id="templateFormModal" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 650px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">名称<span class="text-danger">*</span>：</label>
                        <div class="col-sm-9">
                            <input type="hidden" id="id" name="id">
                            <input type="hidden" id="removeId" name="removeId">
                            <input type="text" id="name" name="name" class="form-control"
                                   data-message="名称不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>


                    </div>
                    <div class="form-group">
                        <label for="filePath" class="col-sm-3 control-label">存储路径<span class="text-danger">*</span>：</label>
                        <div class="col-sm-9">
                            <input type="text" id="filePath" name="filePath" value="doc\" class="form-control"
                                   data-message="存储路径不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="fileName" class="col-sm-3 control-label">模板文件名<span class="text-danger">*</span>：</label>
                        <div class="col-sm-9">
                            <input type="text" id="fileName" name="fileName" class="form-control" placeholder="*.doc"
                                   data-message="模板文件名不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dataFileName" class="col-sm-3 control-label">数据描述文件名<span class="text-danger">*</span>：</label>
                        <div class="col-sm-9">
                            <input type="text" id="dataFileName" name="dataFileName" class="form-control" placeholder="*.json"
                                   data-message="模板数据描述文件名不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-3 control-label">附件：</label>
                        <div class="col-sm-9">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="templateForm-fine-uploader-gallery"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary btn-save">保存</button>
                <button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/officetemp/scripts/template_manager.js"></script>
</body>
</html>
