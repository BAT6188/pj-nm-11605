/**
 * Created by Administrator on 2017/1/6.
 */
// var paizhaoDialog=$("#paizhaoDialog")
// $(document).on("click",'.paizhao',{msg: "You just clicked me!"},function(e){
//     console.log(e.data.msg)
//     paizhaoDialog.modal("show")
// })

var pathq;
var CamID  = 0;  //此例程默认以第一个摄像头打开
var SupportFormat;    //设备支持的视频格式代号：1->MJPG; 2->YUY2
var OpenFormat;      //打开格式：0->YUY；1->MJPG
var isOpen = false;
var isWiseCapture =false;

var nFileCount = 0;
var bAdjustMode = 0;
var bCropArea = 0;
var bSeriesCapture = 0;
var bReadBarCode=0;
var bSetDenoise = 0;
var Width = 0;
var Height = 0;
var strFile;
var index = 0;
var strCardFile;
var cardIndex = 0;
var captureCount=0;



//开启摄像头
function StartVideo()
{
    // var restr = "3264*2448";
    // var pos = restr.lastIndexOf("*");
    // var width = parseInt(restr.substring(0, pos));
    // var height =parseInt(restr.substring(pos + 1, restr.length));

    // var i = axCam_Ocx.CAM_Open(CamID, 1, width, height);
    var i = axCam_Ocx.CAM_Open(0, 1, 640, 480);
    if (i == 0) ShowInfo("开启设备成功");
    else ShowInfo("开启设备失败");

//    var i = axCam_Ocx2.CAM_Open(1, 0, 640, 480);

}

//抓图拍照
function Capture() {

    captureCount=captureCount+1;
    var folder='d:\\Image';
    var path = folder + "\\Img_" + captureCount;
    var suffix = ".jpg";
    path = path + suffix;
    axCam_Ocx.CaptureImage(path);

    pathq=path;

    var info="拍照成功\n" + "位置:" + path;
    ShowInfo(info);
}

function ShowInfo(op) {
    alert(op)
}

StartVideo();
