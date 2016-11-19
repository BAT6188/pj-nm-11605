<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>油烟排口</title>
    <script type="text/javascript">
        var enterpriseId=enterpriseData.id;
        $('.modal-body').attr('style','max-height: '+pageUtils.getFormHeight()+'px;overflow-y: auto;overflow-x: hidden;padding:10px;');
    </script>
</head>
<body>
<div class="content content1 clearfix">
    <div class="wrap">
        <div class="mainBox">
            <a id="headTitle" href="javascript:void(0)" class="list-group-item active" style="cursor: default;">油烟排口列表</a>
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
                        <label for="number">排口编号：</label> <input type="text" id="portNumber" name="number" class="form-control" />
                        <label for="name">排口名称：</label> <input type="text" id="portName" name="name" class="form-control" />
                    </form>
                    </p>
                </div>
                <button type="button" id="search" class="btn btn-md btn-success queryBtn"><i class="btnIcon query-icon"></i><span>查询</span></button>
                <button id="searchFix" type="button" class="btn btn-default queryBtn" ><i class="glyphicon glyphicon-repeat"></i><span>重置</span></button>
                <p class="btnListP">
                    <button id="add" type="button" class="btn btn-sm btn-success" data-toggle="modal" data-target="#fumesPortForm">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="update" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#fumesPortForm">
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
<div class="modal fade" id="fumesPortForm" data-backdrop="static" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="fumesPortModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 880px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title form-title">添加油烟排口</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="number" class="col-sm-2 control-label">排口编号<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="id" name="id" class="form-control">
                            <input type="hidden" id="enterpriseId" name="enterpriseId" class="form-control">
                            <input type="hidden" id="createTime" name="createTime" class="form-control">
                            <input type="text" id="number" name="number" class="form-control"
                                   data-message="排口编号不能为空"
                                   data-easytip="position:top;class:easy-red;"/>
                        </div>
                        <label for="name" class="col-sm-2 control-label">排口名称<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4">
                            <input type="text" id="name" name="name" class="form-control"
                                   data-message="排口名称不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="monitorType" class="col-sm-2 control-label">监测类型：</label>
                        <div class="col-sm-4">
                            <select id="monitorType" name="monitorType" class="form-control">
                                <option value="01">自动</option><option value="02">手动</option><option value="03">自动加手动</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isFumes" class="col-sm-2 control-label">监测油烟<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isFumes">
                            <label class="checkbox-inline"><input type="radio" name="isFumes" id="isFumes1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测油烟">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isFumes" id="isFumes0" value="0">否</label>
                        </div>
                        <label for="isTemperature" class="col-sm-2 control-label">监测烟气温度<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isTemperature">
                            <label class="checkbox-inline"><input type="radio" name="isTemperature" id="isTemperature1" value="1" data-easytip="class:easy-red;"  data-message="请选择是否监测烟气温度">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isTemperature" id="isTemperature0" value="0">否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="isHumidity" class="col-sm-2 control-label">监测烟气湿度<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4 isRadio" id="isHumidity">
                            <label class="checkbox-inline"><input type="radio" name="isHumidity" id="isHumidity1" value="1" data-easytip="class:easy-red;" data-message="请选择是否监测烟气湿度">是</label>
                            <label class="checkbox-inline"><input type="radio" name="isHumidity" id="isHumidity0" value="0">否</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="planeMap" class="col-sm-2 control-label">平面图<span class="text-danger">(*)</span>：</label>
                        <div class="col-sm-4" id="planeMap">
                            <input type="hidden" id="planeMapMark" name="planeMapMark" class="form-control">
                            <%--<span id="planeMapMarkType" class="btn-success textSpan"></span>--%>
                            <input type="checkbox" name="planeMapMarkType" id="planeMapMarkType">标注状态
                            <button type="button" id="lookPlaneMapMark" class="btn btn-info lookBtn" style="display: none" onclick="lookPlaneMap()">查看标注</button>
                            <button type="button" id="editPlaneMapMark" class="btn btn-primary saveBtn" style="display: none" onclick="makePlaneMap()">平面图标注</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="attachmentId" name="attachmentId" class="form-control" data-easyform="null;">
                            <input type="hidden" id="removeId" name="removeId" class="form-control" data-easyform="null;">
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
<script src="<%=request.getContextPath()%>/container/gov/enterprise/basicInfo/scripts/fumesPortList.js"></script>
</body>
</html>
