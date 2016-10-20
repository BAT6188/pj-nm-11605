<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%
        String enterpriseId=request.getParameter("id");
    %>
    <title>其他生产设备信息</title>
    <script type="text/javascript">
        var enterpriseId='<%=enterpriseId%>';
    </script>
</head>
<body>
<div class="content content1 clearfix">
    <a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="cursor: default;">其他生产设备信息列表</a>
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
                    <p>
                    <form class="form-horizontal" role="form" id="searchform">
                        <label for="number">主要产品（服务）：</label> <input type="text" id="portNumber" name="product" class="form-control" />
                        <label for="name">主要原辅材料名称：</label> <input type="text" id="portName" name="rawMaterial" class="form-control" />
                    </form>
                    </p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#otherProductForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#otherProductForm">
                        <i class="btnIcon edit-icon"></i><span>修改</span>
                    </button>
                    <button id="remove" type="button" class="btn btn-sm btn-danger">
                        <i class="btnIcon delf-icon"></i><span>删除</span>
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
<div class="modal fade" id="otherProductForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="otherProductModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加其他生产设备信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">设备名称<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-9">
                            <input type="hidden" id="id" name="id" class="form-control">
                            <input type="hidden" id="createTime" name="createTime" class="form-control">
                            <input type="text" id="name" name="name" class="form-control"
                                   data-message="设备名称不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="code" class="col-sm-3 control-label">设备编码<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-9">
                            <input type="text" id="code" name="code" class="form-control"
                                   data-message="设备编码不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="model" class="col-sm-2 control-label">设备型号：</label>
                        <div class="col-sm-4">
                            <input type="text" id="model" name="model" class="form-control">
                        </div>
                        <label for="status" class="col-sm-2 control-label">状态：</label>
                        <div class="col-sm-4">
                            <input type="text" id="status" name="status" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">

                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-3 control-label">附件：</label>
                        <div class="col-sm-9">
                            <input type="hidden" id="attachmentId" name="attachmentId" class="form-control">
                            <input type="hidden" id="removeId" name="removeId" class="form-control">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary saveBtn" id="save" style="display: none">保存</button>
                <button id="cancelBtn" type="button" class="btn btn-default saveBtn" data-dismiss="modal" style="display: none">取消</button>
                <button type="button" class="btn btn-default lookBtn" data-dismiss="modal" style="display: none">关闭</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/enterprise/proDeviceInfo/scripts/otherProductList.js"></script>
</body>
</html>
