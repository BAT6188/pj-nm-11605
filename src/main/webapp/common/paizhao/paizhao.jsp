<%@ page language="java" pageEncoding="utf8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>拍照</title>
    <style type="text/css">
    </style>
</head>
<body>
<div class="modal fade" id="paizhaoDialog" data-form-type="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabe3" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width:842px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="">拍照</h4>
            </div>
            <div class="modal-body">
                <object id="axCam_Ocx" style=" width:100%; height:544px;"
                        classid="clsid:ce2d72f2-ad28-4013-a24b-c3f76c5a1944"  codebase="CamOcx.cab #version=1,0,0,1">
                </object>
                <div style="margin-left: 50%;">
                    <button type="button" onclick = "Capture();">拍照</button>
                    <button type="button" onclick = "UpdataFile();">上传</button>

                </div>
                <div id="imageDiv">
                    <img id="img1" alt="" src="" style="width:100px;height: 88px;"/>
                    <img id="img2" alt="" src="" style="width:100px;height: 88px;"/>
                    <img id="img3" alt="" src="" style="width:100px;height: 88px;"/>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</body>
</html>
<%--<script src="<%=request.getContextPath()%>/demo/easyform/js/jquery-2.1.0.min.js"></script>--%>
<script src="${pageContext.request.contextPath}/common/paizhao/scripts/paizhao.js"></script>