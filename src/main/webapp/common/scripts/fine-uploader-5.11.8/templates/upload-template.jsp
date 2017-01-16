<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/template" id="qq-template">
    <div class="qq-uploader-selector qq-uploader" qq-drop-area-text="拖放文件到这里进行上传...">
        <div class="qq-total-progress-bar-container-selector qq-total-progress-bar-container">
            <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"
                 class="qq-total-progress-bar-selector qq-progress-bar qq-total-progress-bar"></div>
        </div>
        <div class="qq-upload-drop-area-selector qq-upload-drop-area" qq-hide-dropzone>
            <span class="qq-upload-drop-area-text-selector"></span>
        </div>
        <div class="qq-upload-button-selector qq-upload-button">
            <div>选择文件</div>
        </div>
        <div class="qq-upload-button paizhao" style="margin-left: 11px;cursor: pointer;display: none;">
            <div class="">拍照</div>
        </div>
        <span class="qq-drop-processing-selector qq-drop-processing">
                <span>正在上传...</span>
                <span class="qq-drop-processing-spinner-selector qq-drop-processing-spinner"></span>
            </span>
        <ul class="qq-upload-list-selector qq-upload-list" aria-live="polite" aria-relevant="additions removals">
            <li>
                <div class="qq-progress-bar-container-selector">
                    <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"
                         class="qq-progress-bar-selector qq-progress-bar"></div>
                </div>
                <span class="qq-upload-spinner-selector qq-upload-spinner"></span>
                <span class="qq-upload-file-selector qq-upload-file"></span>
                <span class="qq-edit-filename-icon-selector qq-edit-filename-icon"
                      aria-label="Edit filename"></span>
                <input class="qq-edit-filename-selector qq-edit-filename" tabindex="0" type="hidden">
                <span class="qq-upload-size-selector qq-upload-size"></span>
                <button type="button" class="qq-btn qq-upload-cancel-selector qq-upload-cancel">取消</button>
                <button type="button" class="qq-btn qq-upload-retry-selector qq-upload-retry">重试</button>
                <button type="button" class="qq-btn qq-upload-delete-selector qq-upload-delete">删除</button>
                <button type="button" class="qq-btn qq-upload-download-selector qq-upload-pause qq-hide">下载</button>
                <span role="status" class="qq-upload-status-text-selector qq-upload-status-text"></span>
            </li>
        </ul>

        <dialog class="qq-alert-dialog-selector">
            <div class="qq-dialog-message-selector"></div>
            <div class="qq-dialog-buttons">
                <button type="button" class="qq-cancel-button-selector">关闭</button>
            </div>
        </dialog>

        <dialog class="qq-confirm-dialog-selector">
            <div class="qq-dialog-message-selector"></div>
            <div class="qq-dialog-buttons">
                <button type="button" class="qq-cancel-button-selector">取消</button>
                <button type="button" class="qq-ok-button-selector">确定</button>
            </div>
        </dialog>

        <dialog class="qq-prompt-dialog-selector">
            <div class="qq-dialog-message-selector"></div>
            <input type="text">
            <div class="qq-dialog-buttons">
                <button type="button" class="qq-cancel-button-selector">取消</button>
                <button type="button" class="qq-ok-button-selector">确定</button>
            </div>
        </dialog>
    </div>
</script>

<script>
    $(document).on("click",'.paizhao',{msg: "param"},function(data){
        if (userAgentType()=='Chrome'){

        }else {
            console.log(data.data.msg)
            $("#paizhaoDialog").modal('show');
        }



//        var url = rootPath+"/common/paizhao/paizhao.jsp";
//        var params = data.data.msg;
//        var dialogWidth = "1000px";
//        var dialogHeight = "600px";
//        if (window.showModelessDialog) {
//            window.showModelessDialog(url, params, "dialogWidth=" + dialogWidth + ";dialogHeight=" + dialogHeight + ";");
//        }else if (window.showModalDialog) {
//            window.showModalDialog(url, params, "dialogWidth=" + dialogWidth + ";dialogHeight=" + dialogHeight + ";");
//        }else{
//            window.open(url,"拍照");
//        }
//        window.open(url,"拍照");
    })

</script>
