<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>摄像头</title>
    <script type="text/javascript">
        $('.modal-body').attr('style','max-height: '+pageUtils.getFormHeight()+'px;overflow-y: auto;overflow-x: hidden;padding:10px;');
    </script>
</head>

<body>
<div class="content content1 clearfix">
    <div class="wrap">
        <div class="mainBox">
            <div class="dealBox">
                <p class="btnListP">
                    <button id="addVideo" type="button" class="btn btn-sm btn-success">
                        <i class="btnIcon add-icon"></i><span>新建</span>
                    </button>
                    <button id="updateVideo" type="button" class="btn btn-sm btn-warning">
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
<div class="modal fade" data-backdrop="static" id="videoForm" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <%--<button type="button" id="videoClose" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
                <h4 class="modal-title form-title">添加摄像头</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="video_name" class="col-sm-2 control-label">名称<span class="text-danger">*</span>：</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="video_id" name="id" class="form-control">
                            <input type="hidden" id="video_removeId" name="removeId" class="form-control">
                            <input type="text" id="video_name" name="name" class="form-control"
                                   data-message="名称不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">所属网格<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <select class="form-control needshow" id="video_blockLevelId" name="blockLevelId">
                            </select>
                        </div>

                        <label for="" class="col-sm-2 control-label"></label>
                        <div class="col-sm-4">
                            <select class="form-control needshow" id="video_blockId" name="blockId">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="unitName" class="col-sm-2 control-label">所属单位<span class="text-danger">*</span>：</label>
                        <div class="col-sm-4">
                            <input type="hidden" id="unitId" name="unitId">
                            <input type="text" id="unitName" name="unitName" class="form-control"
                                   data-message="所属单位不能为空"
                                   data-easytip="position:top;class:easy-red;"
                            />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="longitude" class="col-sm-2 control-label">经度：</label>
                        <div class="col-sm-4">
                            <input type="text" id="longitude" name="longitude" class="form-control" readonly >
                        </div>
                        <label for="latitude" class="col-sm-2 control-label">纬度：</label>
                        <div class="col-sm-4">
                            <div class="input-group">
                                <input type="text" class="form-control" id="latitude" name="latitude" readonly>
                                <span class="input-group-btn">
                                    <button class="btn btn-default formBtn" type="button" id="mapVideoBtn">
                                        标注
                                    </button>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="attachment" class="col-sm-2 control-label">附件：</label>
                        <div class="col-sm-10">
                            <jsp:include page="/common/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
                            <div id="video-fine-uploader-gallery"></div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="saveVideo">保存</button>
                <button type="button" class="btn btn-default  cancel" id="cancelVideoForm">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="<%=request.getContextPath()%>/container/gov/detect/scripts/video.js"></script>
<script>

</script>
</body>
</html>
