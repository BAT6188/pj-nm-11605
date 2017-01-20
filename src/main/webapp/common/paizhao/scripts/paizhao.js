/**
 * Created by Administrator on 2017/1/6.
 */
var folder='C:\\Users';
var suffix = ".jpg";
var picNum=3;
var width =2592;
var height=1944;
// var width =3264;
// var height=2448;
var base64StrArray=[];
var imageIds=[];
var captureCount=0;

//开启摄像头
function startVideo()
{
    // var restr = "3264*2448";
    // var pos = restr.lastIndexOf("*");
    // var width = parseInt(restr.substring(0, pos));
    // var height =parseInt(restr.substring(pos + 1, restr.length));
    // var i = axCam_Ocx.CAM_Open(CamID, 1, width, height);

    var i = axCam_Ocx.CAM_Open(0, 1, width, height);
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

    for (var i=1;i<=base64StrArray.length;i++){
        $("#img"+i).attr("src","");
    }
    base64StrArray=[];
    captureCount=0;

})

var otherDialogs=$('.modal').not("#paizhaoDialog");
otherDialogs.on('hide.bs.modal', function () {
    imageIds=[];
})
otherDialogs.on('shown.bs.modal', function () {
    if (userAgentType()=='Chrome'){

    }else {
        $(".paizhao").css("display","");//引用了<%@include file="/common/paizhao/paizhao.jsp"%>并且不是ie浏览器 才显示拍照按钮
    }

})

//抓图拍照
function Capture() {
    captureCount=captureCount+1;
    if(captureCount<=picNum){
        var path = folder + "\\img_" + captureCount + suffix;
        axCam_Ocx.CaptureImage(path);
        var base64Str = axCam_Ocx.GetBase64FromFile(path);
        base64StrArray.push(base64Str);
        // base64Str = axCam_Ocx.GetBase64String();
        var src_base64="data:image/jpg;base64,"+base64Str;
        $("#img"+captureCount).attr("src",src_base64);
        var info="拍照成功\n" + "位置:" + path;
        showInfo(info);
    }
}

function showInfo(info) {
    // console.log(info)
}

/**
 * 获取附件列表ids
 * @returns {*}
 */
function getAttachmentIds() {
    var attachments = uploader.getUploads();
    $.each(imageIds,function (i,v) {
        attachments.push({uuid:v.uuid})
    })

    //TODO 什么时候清空
    imageIds=[];
    if (attachments && attachments.length) {
        var ids = [];
        for (var i = 0 ; i < attachments.length; i++){
            ids.push(attachments[i].uuid);
        }
        return ids.join(",");
    }
    return "";
}

function UpdataFile() {
    // var ret=axCam_Ocx.UpdataFile("127.0.0.1", 8080, "/dshbcbp/imageUpload", path);
    // $(".qq-uploader",window.parent.document).append('<img src="/dshbcbp/common/images/user.jpg" alt="">')
    if (base64StrArray.length>0){
        $.each(base64StrArray,function (i,v) {
            $.ajax({
                url: "http://127.0.0.1:8080/dshbcbp/imageUpload",
                type:"post",
                async:false,
                data:{"base64Str":v},
                success:function (msg) {
                    // console.log(msg)
                    var msg = JSON.parse(msg);
                    $(".qq-uploader").append('<img src="data:image/jpg;base64,'+v+'" style="width: 50px;height: 44px;margin-right: 6px;" alt="">')
                    imageIds.push({"uuid":msg.id})
                    // console.log(666)
                }
            });
        })
        alert(base64StrArray.length+"张照片上传成功")
    }else {
        alert("请先拍照再上传")
    }


    /*var blob = dataURItoBlob(src_base64); // 上一步中的函数
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
    });*/
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
