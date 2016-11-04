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