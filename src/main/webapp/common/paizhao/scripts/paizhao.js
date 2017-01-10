/**
 * Created by Administrator on 2017/1/6.
 */
var folder='C:\\Users';
var suffix = ".jpg";
var path;
var base64Str;
var src_base64;
var captureCount=0;
var picNum=3;

//开启摄像头
function startVideo()
{
    // var restr = "3264*2448";
    // var pos = restr.lastIndexOf("*");
    // var width = parseInt(restr.substring(0, pos));
    // var height =parseInt(restr.substring(pos + 1, restr.length));
    // var i = axCam_Ocx.CAM_Open(CamID, 1, width, height);

    var i = axCam_Ocx.CAM_Open(0, 1, 3264, 2448);
    if (i == 0) showInfo("开启设备成功");
    else showInfo("开启设备失败");
}

//关闭摄像头
function closeVideo()
{
    axCam_Ocx.CAM_Close()
    showInfo("关闭设备");
}

$('#paizhaoDialog').on('shown.bs.modal', function () {
    startVideo()
})

$('#paizhaoDialog').on('hide.bs.modal', function () {
    closeVideo()
})

//抓图拍照
function Capture() {
    captureCount=captureCount+1;
    if(captureCount<=picNum){
        path = folder + "\\img_" + captureCount + suffix;
        axCam_Ocx.CaptureImage(path);
        base64Str = axCam_Ocx.GetBase64FromFile(path);
        // base64Str = axCam_Ocx.GetBase64String();
        src_base64="data:image/jpg;base64,"+base64Str;
        $("#img"+captureCount).attr("src",src_base64);
        $("#imageabc12").attr("src",src_base64);
        var info="拍照成功\n" + "位置:" + path;
        showInfo(info);
    }
}

function showInfo(info) {
    // console.log(info)
}

function dataURItoBlob(base64Data) {
    var byteString;
    if (base64Data.split(',')[0].indexOf('base64') >= 0)
        byteString = atob(base64Data.split(',')[1]);
    else
        byteString = unescape(base64Data.split(',')[1]);
    var mimeString = base64Data.split(',')[0].split(':')[1].split(';')[0];
    var ia = new Uint8Array(byteString.length);
    for (var i = 0; i < byteString.length; i++) {
        ia[i] = byteString.charCodeAt(i);
    }
    return new Blob([ia], {type:mimeString});
}



function UpdataFile() {
    // var ret=axCam_Ocx.UpdataFile("127.0.0.1", 8080, "/dshbcbp/imageUpload", path);
    // $(".qq-uploader",window.parent.document).append('<img src="/dshbcbp/common/images/user.jpg" alt="">')
   /* console.log(base64Str);
    $.ajax({
        url: "http://127.0.0.1:8080/dshbcbp/action/S_attachment_Attachment_uploadImagessss.action",
        //contentType: "application/json;charset=utf-8",
        type:"post",
        data:{"beasdImage":base64Str},
        // dataType:"json",
        // success:callback
    });*/
    var blob = dataURItoBlob(src_base64); // 上一步中的函数
    var canvas = document.createElement('canvas');
    var dataURL = canvas.toDataURL('image/jpeg', 0.5);
    var fd = new FormData(document.forms[0]);
    fd.append("the_file", blob, 'image.jpg');

    $.ajax({
        url: 'http://127.0.0.1:8080/dshbcbp/Upload',
        method: 'POST',
        processData: false, // 必须
        contentType: false, // 必须
        dataType: 'json',
        data: fd,
        success:function(data) {
            console.log(data);
        }
    });
}

