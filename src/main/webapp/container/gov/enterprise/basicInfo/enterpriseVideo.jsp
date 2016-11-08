<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>摄像头</title>
    <script src="<%=request.getContextPath()%>/common/scripts/dict.js"></script>
    <script type="text/javascript">
        var enterpriseId=enterpriseData.id;
        $(function(){
            initSelect();
        });
        /*初始化选择菜单*/
        function initSelect(){
            var dictData = dict.getDctionnary({code:['monitorType']});
            /*数据字典*/
            var optionsHtml = '';
            $.each(dictData,function(i,obj){
                optionsHtml +='<option value="'+ obj.code+'">'+ obj.name+'</option>';
            })
            $('#monitorType').append(optionsHtml);
        }
    </script>
    <style>
        .table-responsive .success{
            text-align: right;
        }
    </style>
</head>

<body>
<div class="content content1 clearfix">
    <a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="cursor: default;">摄像头列表</a>
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
                    <form class="form-inline" role="form" id="searchform">
                        <label for="number">摄像头编号：</label> <input type="text" id="portNumber" name="number" class="form-control" />
                        <label for="name">摄像头名称：</label> <input type="text" id="portName" name="name" class="form-control" />
                    </form>
                    </p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="addVideo" type="button" class="btn btn-sm btn-success"  data-toggle="modal" data-target="#videoForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="updateVideo" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#videoForm">
                        <i class="btnIcon edit-icon"></i><span>修改</span>
                    </button>
                    <button id="removeVideo" type="button" class="btn btn-sm btn-danger">
                        <i class="btnIcon delf-icon"></i><span>删除</span>
                    </button>
                </p>
            </div>
            <div class="tableBox">
                <table id="videoTable" class="table table-striped table-responsive">
                </table>
            </div>
        </div>
    </div>
</div>
<!--添加表单-->
<div class="modal fade" id="videoForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="videoModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 930px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加摄像头</h4>
            </div>
            <div class="modal-body">
                <div class="tableDiv" style="max-height: 600px;overflow-y: auto;overflow-x: hidden;">
                    <form class="form-horizontal"  action="" style="margin: 0 10px;" role="form">
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="table table-bordered table-responsive">
                                    <tbody>
                                    <tr>
                                        <td class="success"><span class="text-danger">(*)</span>摄像头编号</td>
                                        <td>
                                            <div class="col-sm-12">
                                                <input type="hidden" id="id" name="id" class="form-control">
                                                <input type="hidden" id="unitId" name="unitId" class="form-control">
                                                <input type="hidden" id="createTime" name="createTime" class="form-control">
                                                <input type="text" id="number" name="number" class="form-control"
                                                       data-message="编号不能为空"
                                                       data-easytip="position:top;class:easy-red;"/>
                                            </div>
                                        </td>
                                        <td  class="success"><span class="text-danger">(*)</span>摄像头名称</td>
                                        <td>
                                            <div class="col-sm-12">
                                                <input type="text" id="name" name="name" class="form-control"
                                                       data-message="名称不能为空"
                                                       data-easytip="position:top;class:easy-red;"/>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success">排口位置</td>
                                        <td>
                                            <div class="col-sm-12">
                                                <input type="text" id="position" name="position" class="form-control">
                                            </div>
                                        </td>
                                        <td  class="success">监测类型</td>
                                        <td>
                                            <div class="col-sm-12">
                                                <select id="monitorType" name="monitorType" class="form-control">
                                                </select>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success">平面图</td>
                                        <td colspan="3">
                                            <div class="col-sm-12" id="planeMap">
                                                <input type="hidden" id="planeMapMark" name="planeMapMark" class="form-control">
                                                <button type="button" class="btn btn-info lookBtn" style="display: none" onclick="lookPlaneMap()">查看平面图标注</button>
                                                <button type="button" class="btn btn-primary saveBtn" style="display: none" onclick="makePlaneMap()">平面图标注</button>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="success">附件区</td>
                                        <td colspan="3">
                                            <div class="col-sm-12">
                                                <input type="hidden" id="attachmentId" name="attachmentId" class="form-control">
                                                <input type="hidden" id="removeId" name="removeId" class="form-control">
                                                <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                                                <div id="fine-uploader-gallery"></div>
                                            </div>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </form>
                </div>
                <%--<form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="number" class="col-sm-2 control-label">摄像头编号<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id" class="form-control">
                            <input type="hidden" id="unitId" name="unitId" class="form-control">
                            <input type="hidden" id="createTime" name="createTime" class="form-control">
                            <input type="text" id="number" name="number" class="form-control"
                                   data-message="编号不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                        <label for="name" class="col-sm-2 control-label">摄像头名称<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="name" name="name" class="form-control"
                                   data-message="名称不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="position" class="col-sm-2 control-label">排口位置：</label>
                        <div class="col-sm-4">
                            <input type="text" id="position" name="position" class="form-control">
                        </div>
                        <label for="monitorType" class="col-sm-2 control-label">监测类型：</label>
                        <div class="col-sm-4">
                            <select id="monitorType" name="monitorType" class="form-control">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="planeMap" class="col-sm-2 control-label">平面图<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4" id="planeMap">
                            <input type="hidden" id="planeMapMark" name="planeMapMark" class="form-control">
                            <button type="button" class="btn btn-info lookBtn" style="display: none" onclick="lookPlaneMap()">查看平面图标注</button>
                            <button type="button" class="btn btn-primary saveBtn" style="display: none" onclick="makePlaneMap()">平面图标注</button>
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
                </form>--%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary saveBtn" id="save" style="display: none">保存</button>
                <button id="cancelBtn" type="button" class="btn btn-default saveBtn" data-dismiss="modal" style="display: none">取消</button>
                <button type="button" class="btn btn-default lookBtn" data-dismiss="modal" style="display: none">关闭</button>
            </div>
        </div>
    </div>
</div>

<script src="<%=request.getContextPath()%>/container/gov/enterprise/basicInfo/scripts/enterpriseVideo.js"></script>
</body>
</html>
