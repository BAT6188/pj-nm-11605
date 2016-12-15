//接收公安视频参数
var videoDevice;
var gWndId = 0;
var bLogin = 0;
var bIVS =1;
var nOper=-1;
var nDirect=-1;
if (window.dialogArguments) {
    videoDevice = window.dialogArguments
}else if (window.opener && window.opener.videoDevice) {
    videoDevice = window.opener.videoDevice
}else{
    videoDevice = {"addr":"长安街7号","channalId":"2","deviceId":"1002","id":"2","latitude":"39.77978779325209","longitude":"110.10313785803981","type":"1","unit":"公安局"};
}
if (videoDevice){
    // console.log(JSON.stringify(videoDevice));  //{"addr":"长安街7号","channalId":"2","deviceId":"1002","id":"2","latitude":"39.77978779325209","longitude":"110.10313785803981","type":"1","unit":"公安局"}
    init(videoDevice.channalId);
    $(".videoPause").remove();

}else{
    alert("没有视频信息");
}

function ShowCallRetInfo(nRet, strInfo)
{
    var str = "";
    if(nRet == 0)
    {
        str = strInfo + " 成功！";
    }
    else
    {
        str = strInfo + "失败！错误码：" + nRet;
    }
    // console.log(str);
}

//设置窗口数量
function ButtonCreateWnd_onclick()
{
    var obj = document.getElementById("DPSDK_OCX");
    var nWndCount = "1";
    obj.DPSDK_SetWndCount(gWndId, nWndCount);
    obj.DPSDK_SetSelWnd(gWndId, 0);
}

//登录
function ButtonLogin_onclick()
{
    var obj = document.getElementById("DPSDK_OCX");

    var szIp = "15.113.112.13";
    var nPort = "9000";
    var szUsername = "hbj";
    var szPassword = "12345678";

    var nRet = obj.DPSDK_Login(szIp, nPort, szUsername, szPassword);
    ShowCallRetInfo(nRet, "登录");
}

//播放视频
/**
 *
 * @param szCameraId 通道id
 * @constructor
 */
function ButtonStartRealplayByWndNo_onclick(szCameraId)
{
    var obj = document.getElementById("DPSDK_OCX");

    var nStreamType = "1";
    var nMediaType = "1";
    var nTransType = "1";

    var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    var nRet = obj.DPSDK_StartRealplayByWndNo(gWndId, nWndNo, szCameraId, nStreamType, nMediaType, nTransType);
    ShowCallRetInfo(nRet, "播放视频");
    if(nRet == 0)
    {
        ShowCallRetInfo(obj.DPSDK_SetIvsShowFlagByWndNo(gWndId, nWndNo, 1, bIVS),"规则线显示");//打开规则线显示
        ShowCallRetInfo(obj.DPSDK_SetIvsShowFlagByWndNo(gWndId, nWndNo, 2, bIVS),"目标框显示");//打开目标框显示
        ShowCallRetInfo(obj.DPSDK_SetIvsShowFlagByWndNo(gWndId, nWndNo, 3, bIVS),"轨迹线显示");//打开轨迹线显示
    }
}

//页面初始化
function init(channalId){
    ButtonLogin_onclick();

    var obj = document.getElementById("DPSDK_OCX");
    gWndId = obj.DPSDK_CreateSmartWnd(0, 0, 100, 100);

    ButtonCreateWnd_onclick();
    // obj.DPSDK_SetLog(2, "D:\\DPSDK_OCX_log", false, false); //初始化后设置日志路径
    //var obj = document.getElementById("DPSDK_OCX");
    //ShowCallRetInfo(obj.DPSDK_Login("172.7.123.123", 9000, "1", "1"), "登录");
    //ShowCallRetInfo(obj.DPSDK_AsyncLoadDGroupInfo(), "异步加载组织结构");
    //var nWndNo = obj.DPSDK_GetSelWnd(gWndId);
    //ShowCallRetInfo(obj.DPSDK_DirectRealplayByWndNo(gWndId, nWndNo, "1000001$1$0$0", 1, 1, 1), "直接实时播放");
    for(var i=1;i<=4;i++)
        obj.DPSDK_SetToolBtnVisible(i, false);
    obj.DPSDK_SetToolBtnVisible(7, false);
    obj.DPSDK_SetToolBtnVisible(9, false);
    obj.DPSDK_SetControlButtonShowMode(1, 0);
    obj.DPSDK_SetControlButtonShowMode(2, 0);

    ButtonStartRealplayByWndNo_onclick(channalId);
}

function ButtonLogout_onclick()
{
    var obj = document.getElementById("DPSDK_OCX");
    if( bLogin == 1)
    {
        ShowCallRetInfo(obj.DPSDK_Logout(), "登出");
        bLogin = 0;
    }
}


//----------- 云台控制js ---------------//
//方向控制
function ButtonPtzDirection_onclick(nDirects)
{
    var obj = document.getElementById("DPSDK_OCX");
    var szCameraId = videoDevice.channalId;
    nDirect = nDirects;
    var nStep = 3;//步长
    ShowCallRetInfo(obj.DPSDK_PtzDirection(szCameraId, nDirect, nStep, 0), "方向控制");
}

//镜头控制
function ButtonPtzCameraOperation_onclick(nOpers)
{
    var obj = document.getElementById("DPSDK_OCX");
    var szCameraId = videoDevice.channalId;
    nOper = nOpers;
    var nStep = 3; //倍速
    ShowCallRetInfo(obj.DPSDK_PtzCameraOperation(szCameraId, nOper, nStep, 0), "镜头控制");
}