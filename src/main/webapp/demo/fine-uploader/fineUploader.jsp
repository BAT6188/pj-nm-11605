<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%@include file="/common/common_include.jsp" %>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <title>上传组件</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->

    <link href="<%=request.getContextPath()%>/scripts/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/scripts/font-awesome-4.6.3/css/font-awesome.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/scripts/fine-uploader-5.11.8/fine-uploader-gallery.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/scripts/fine-uploader-5.11.8/fine-uploader-new.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/pageStyle.css" rel="stylesheet">

    <script src="<%=request.getContextPath()%>/scripts/jquery1.12.4/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/scripts/bootstrap-3.3.7/js/bootstrap.js"></script>

    <script src="<%=request.getContextPath()%>/scripts/fine-uploader-5.11.8/fine-uploader.core.js"></script>
    <script src="<%=request.getContextPath()%>/scripts/fine-uploader-5.11.8/fine-uploader.jquery.js"></script>
    <script src="<%=request.getContextPath()%>/scripts/fine-uploader-5.11.8/fine-uploader.js"></script>

    <style>

    </style>
</head>
<body>
<div class="container">
    <div class="alert alert-info"> 上传组件</div>
    <p></p>
    <jsp:include page="/scripts/fine-uploader-5.11.8/templates/upload-template.jsp" flush="false" ></jsp:include>
    <div id="fine-uploader-gallery"></div>
    <p></p>
    <%--<div class="alert alert-info"> 人为触发上传组件</div>--%>
    <%--<p></p>--%>
    <%--<style>--%>
        <%--#trigger-upload {--%>
            <%--color: white;--%>
            <%--background-color: #00ABC7;--%>
            <%--font-size: 14px;--%>
            <%--padding: 7px 20px;--%>
            <%--background-image: none;--%>
        <%--}--%>

        <%--#fine-uploader-manual-trigger .qq-upload-button {--%>
            <%--margin-right: 15px;--%>
        <%--}--%>

        <%--#fine-uploader-manual-trigger .buttons {--%>
            <%--width: 36%;--%>
        <%--}--%>

        <%--#fine-uploader-manual-trigger .qq-uploader .qq-total-progress-bar-container {--%>
            <%--width: 60%;--%>
        <%--}--%>
    <%--</style>--%>
    <%--<jsp:include page="/scripts/fine-uploader-5.11.8/templates/fine-uploader-template-manual-trigger.jsp" flush="false" ></jsp:include>--%>
    <%--<div id="fine-uploader-manual-trigger"></div>--%>
    <%--<p></p>--%>
</div>


</body>
</html>

<script>

    var uploader = new qq.FineUploader({
        element: document.getElementById("fine-uploader-gallery"),
        template: 'qq-template',
        chunking: {
            enabled: false,
            concurrent: {
                enabled: true
            }
        },
        resume: {
            enabled: false
        },
        retry: {
            enableAuto: false,
            showButton: false
        },
        failedUploadTextDisplay: {
            mode: 'custom'
        },
        callbacks: {
            onAllComplete: function (succeed) {
                var self = this;
                $.each(succeed, function (k, v) {
                    $('.qq-upload-download-selector', self.getItemByFileId(v)).toggleClass('qq-hide', false);
                });
            }
        },
        request: {
            endpoint: rootPath + '/FineUploaderServlet',
            params: {
                bussinessId:"1"
            }
        },
        session:{
            endpoint: rootPath + '/demo/fine-uploader/testFileList.jsp',
            params: {
                bussinessId:"1"
            }
        },
        deleteFile: {
            enabled: true,
            endpoint: rootPath + "/demo/fine-uploader/testFileDelete.jsp",
            method:"POST",
            params: {
                bussinessId:"1"
            }
        },
        validation: {
            acceptFiles: ['.jpeg', '.jpg', '.gif', '.png'],
            allowedExtensions: ['jpeg', 'jpg', 'gif', 'png'],
            itemLimit: 3
        },
        debug: true
    });
    $("#fine-uploader-gallery").on('click', '.qq-upload-download-selector', function () {
        var uuid = uploader.getUuid($(this.closest('li')).attr('qq-file-id'));
        window.location.href = rootPath+'/FineDownloaderServlet?bussinessId=1&qquuid=' + uuid;
    });

//
//    var manualUploader = new qq.FineUploader({
//        element: document.getElementById('fine-uploader-manual-trigger'),
//        template: 'qq-template-manual-trigger',
//        request: {
//            endpoint: '/fileUploaderServlet'
//        },
//        autoUpload: false,
//        debug: true
//    });
//    qq(document.getElementById("trigger-upload")).attach("click", function() {
//        manualUploader.uploadStoredFiles();
//    });

</script>
