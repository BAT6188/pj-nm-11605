<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>超标记录</title>
    <script type="text/javascript">
        var enterpriseId=enterpriseData.id;
    </script>
</head>
<body>
<div class="content content1 clearfix">
    <a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="cursor: default;">超标记录列表</a>
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
                    <div class="form-inline">
                        <div class="form-group">
                            <label for="snumber" class="labelMarginLeft">排口编号：</label><input type="text" id="snumber" name="number" class="form-control">
                            <label for="sname" class="labelMarginLeft">排口名称：</label><input type="text" id="sname" name="name" class="form-control">
                        </div>
                    </div>
                    </form>
                    </p>
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
<!--添加表单-->
<div class="modal fade" id="otherProductForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="otherProductModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 900px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">超标记录信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">设备名称<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id" class="form-control">
                            <input type="hidden" id="createTime" name="createTime" class="form-control">
                            <input type="text" id="name" name="name" class="form-control"
                                   data-message="设备名称不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                        <label for="code" class="col-sm-2 control-label">设备编码<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
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
                        <label for="status" class="col-sm-2 control-label">状态<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="status">
                            <label class="checkbox-inline">
                                <input type="radio" name="status" id="status1" value="1" data-easytip="class:easy-red;" data-message="请选择一个状态">是
                            </label>
                            <label class="checkbox-inline">
                                <input type="radio" name="status" id="status0" value="0">否
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="quantity" class="col-sm-2 control-label">数量：</label>
                        <div class="col-sm-4">
                            <input type="text" id="quantity" name="quantity" class="form-control"
                                   data-easyform="null;number"
                                   data-easytip="position:top;class:easy-red;"
                                   data-message="请填写数字类型！">
                        </div>
                        <label for="unit" class="col-sm-2 control-label">计量单位：</label>
                        <div class="col-sm-4">
                            <input type="text" id="unit" name="unit" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="attachmentId" name="attachmentId" class="form-control">
                            <input type="hidden" id="removeId" name="removeId" class="form-control">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="fine-uploader-gallery"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default lookBtn" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/enterprise/portStatusHistory/scripts/portStatusHistory.js"></script>
</body>
</html>
