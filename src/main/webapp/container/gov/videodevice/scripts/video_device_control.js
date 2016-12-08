//接收公安视频参数
var videoDevice;
if (window.dialogArguments) {
    videoDevice = window.dialogArguments
}else if (window.opener && window.opener.videoDevice) {
    videoDevice = window.opener.videoDevice
}else{
    videoDevice = null;
}
if (videoDevice){
    alert(videoDevice.id);
}else{
    //alert("没有视频信息");
}