/**
 * Created by Administrator on 2016/11/3.
 */

/**
 * 切换共用的一个 uploader实例
 * @param selector
 */
function uploaderToggle(selector) {
    $(".uploaderToggle").attr("id","")
    $(selector).attr("id","fine-uploader-gallery")
}

/**
 * 绑定下载按钮事件
 */
function bindDownloadSelector() {
    $("#fine-uploader-gallery").on('click', '.qq-upload-download-selector', function () {
        var uuid = uploader.getUuid($(this.closest('li')).attr('qq-file-id'));
        window.location.href = rootPath+"/action/S_attachment_Attachment_download.action?id=" + uuid;
    });
}