<%@ page import="com.harmonywisdom.dshbcbp.enterprise.bean.Enterprise" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/12
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String handleType=request.getParameter("handleType");
        Enterprise enterprise = (Enterprise) request.getSession().getAttribute("session");
    %>
    <title>企业台账</title>
    <script type="text/javascript">
        var handleType='look';
        var id="<%=enterprise != null ? enterprise.getId():""%>";
        var enterpriseData;
        $(function(){
            $.ajax({
                url: rootPath + "/action/S_enterprise_Enterprise_getEnterpriseInfo.action",
                type:"post",
                async:false,
                data:{"id":id},//阻止深度序列化，向后台传递数组
                dataType:"json",
                success:function(data){
                    enterpriseData = data;
                }
            });
        })

        /**
         * 平面图标注
         */
        function makePlaneMap(){
            var planeMapMarkDate = $('#planeMapMark').val();
            var data = (planeMapMarkDate=="")?"":JSON.parse(planeMapMarkDate);
            PlottingDialog.dialog({
                show:true,
                mode:"marker",
                data:data,
                attachments:pageUtils.findAttachment(enterpriseId,"planeMap"),
                callback:function (marker) {
                    var str = JSON.stringify(marker);
                    form.find('#planeMapMark').val(str);
                    if($('#planeMapMark').val()){
                        setPlaneMapMarkTypeCheckType(true);
                        $('#editPlaneMapMark').html("重新标注");
                    }
                }
            });
        }
        /**
         * 查看平面图
         */
        function lookPlaneMap(){
            var planeMapMarkDate = $('#planeMapMark').val();
            var data = (planeMapMarkDate=="")?"":JSON.parse(planeMapMarkDate);
            PlottingDialog.dialog({
                show:true,
                mode:"view",
                data:data,
                attachments:pageUtils.findAttachment(enterpriseId,"planeMap")
            });
        }
        function setPlaneMarkBtn(type){
            $('#planeMapMarkType').attr('disabled',false);
            switch (type){
                case 'add':
                    setPlaneMapMarkTypeCheckType(false);
                    $('#editPlaneMapMark').html("平面图标注");
                    break;
                case 'edit':
                    setPlaneMapMarkTypeCheckType(true);
                    $('#editPlaneMapMark').html("重新标注");
                    break;
                case 'look':
                    setPlaneMapMarkTypeCheckType(true);
                    $('#lookPlaneMapMark').show();
                    break;
                case 'lookNull':
                    setPlaneMapMarkTypeCheckType(false);
                    $('#lookPlaneMapMark').hide();
                    break;
                default:

                    $('#editPlaneMapMark').html("平面图标注");
            }
        }
        function setPlaneMapMarkTypeCheckType(isMarked){
            if(isMarked){
                /*$('#planeMapMarkType').attr('class','btn-success textSpan');
                 $('#planeMapMarkType').html('已标注');*/
                $('#planeMapMarkType').attr("checked",true);
                $('#planeMapMarkType').attr('disabled',true);
            }else{
                /*$('#planeMapMarkType').attr('class','btn-danger textSpan');
                 $('#planeMapMarkType').html('未标注');*/
                $('#planeMapMarkType').attr("checked",false);
                $('#planeMapMarkType').attr('disabled',true);
            }
        }
    </script>
    <style>
        .menuDiv h3{
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="wrap">
    <div class="menu-left left">
        <div class="menuDiv">
            <h3><a href="javascript:;">基础信息</a></h3>
            <ul>
                <li class="curLi"><a href="javascript:loadPageInEnterprise('companyAccount/enterpriseInfo.jsp?handleType=<%=handleType%>')">基本信息</a></li>
                <li><a href="javascript:loadPageInEnterprise('basicInfo/grasPortList.jsp')">废气排口</a></li>
                <li><a href="javascript:loadPageInEnterprise('basicInfo/waterPortList.jsp')">废水排口</a></li>
                <li><a href="javascript:loadPageInEnterprise('basicInfo/noisePortList.jsp')">噪声源</a></li>
                <li><a href="javascript:loadPageInEnterprise('basicInfo/enterpriseVideo.jsp')">摄像头</a></li>
                <li><a href="javascript:loadPageInEnterprise('basicInfo/fumesPortList.jsp')">油烟排口(适用于酒店)</a></li>
                <li><a href="javascript:loadPageInEnterprise('basicInfo/mainProductList.jsp')">主要产品及规模</a></li>
            </ul>
        </div>
        <div class="menuDiv">
            <h3><a href="javascript:;">企业阀值管理</a></h3>
            <ul>
                <li><a href="javascript:loadPageInEnterprise('portThreshold/wasteWaterPT.jsp')">废水阈值管理</a></li>
                <%--<li><a href="javascript:loadPageInEnterprise('portThreshold/hepWasteGasPT.jsp')">火电厂废气阈值管理</a></li>--%>
                <li><a href="javascript:loadPageInEnterprise('portThreshold/wasteGasPT.jsp')">废气阈值管理</a></li>
                <li><a href="javascript:loadPageInEnterprise('portThreshold/flueGasPT.jsp')">油烟阈值管理</a></li>
            </ul>
        </div>
        <div class="menuDiv">
            <h3><a href="javascript:;">生产设备信息</a></h3>
            <ul>
                <li><a href="javascript:loadPageInEnterprise('proDeviceInfo/boilerList.jsp')">燃煤锅炉信息</a></li>
                <li><a href="javascript:loadPageInEnterprise('proDeviceInfo/kilnList.jsp')">窑炉信息</a></li>
                <li><a href="javascript:loadPageInEnterprise('proDeviceInfo/otherProductList.jsp')">其他生产设备信息</a></li>
            </ul>
        </div>
        <div class="menuDiv">
            <h3 onclick="loadPageInEnterprise('portStatusHistory/portStatusHistory.jsp')"><a href="javascript:void(0)">超标记录</a></h3>
            <ul>
                <li class="curLi"><a href="javascript:loadPageInEnterprise('portStatusHistory/portStatusHistory.jsp')">超标记录</a></li>
            </ul>
        </div>
        <div class="menuDiv">
            <h3><a href="javascript:;">污染防治设施建设和运营信息</a></h3>
            <ul>
                <li><a href="javascript:loadPageInEnterprise('/pollutionInstallation/watercontrolfacility.jsp')">水污染治理设施建设和运营情况</a></li>
                <li><a href="javascript:loadPageInEnterprise('/pollutionInstallation/gascontrolfacility.jsp')">大气污染治理设施建设和运营情况</a></li>
                <li><a href="javascript:loadPageInEnterprise('/pollutionInstallation/solid_control_facility.jsp')">固体废物贮存及治理设施建设和运营情况</a></li>
                <li><a href="javascript:loadPageInEnterprise('/pollutionInstallation/sound_control_facility.jsp')">噪声污染治理设施建设和运营情况</a></li>
            </ul>
        </div>
        <div class="menuDiv">
            <h3><a href="javascript:;">建设项目环评及其他许可情况</a></h3>
            <ul>
                <li><a href="javascript:loadPageInEnterprise('/eiaAcceptance/buildproject.jsp?id='+id)">建设项目环评及验收信息</a></li>
                <li><a href="javascript:loadPageInEnterprise('/eiaAcceptance/pollutantlicense.jsp?id='+id)">排污许可证信息</a></li>
                <li><a href="javascript:loadPageInEnterprise('/eiaAcceptance/cleanLicense.jsp?id='+id)">清洁生产审核</a></li>
            </ul>
        </div>
        <div class="menuDiv">
            <h3><a href="javascript:;">突发环境事件应急预案</a></h3>
            <ul>
                <li><a href="javascript:loadPageInEnterprise('/emergencyPlan/enterpriseplan.jsp')">突发环境事件应急预案</a></li>
            </ul>
        </div>
        <div class="menuDiv">
            <h3><a href="javascript:;">环境监管信息</a></h3>
            <ul>
                <li><a href="javascript:loadPageInEnterprise('/environmentSupervision/letterSue.jsp?id='+id);">信访投诉</a></li>
                <li><a href="javascript:loadPageInEnterprise('/environmentSupervision/check.jsp?id='+id);">现场检查（勘察）笔录</a></li>
                <li><a href="javascript:loadPageInEnterprise('/environmentSupervision/punish.jsp?id='+id);">行政处罚</a></li>
                <li><a href="javascript:loadPageInEnterprise('/environmentSupervision/problemCorrect.jsp?id='+id);">存在的问题及整改情况</a></li>
                <li><a href="javascript:loadPageInEnterprise('/environmentSupervision/pollutantPayment.jsp?id='+id);">排污收费</a></li>
            </ul>
        </div>
        <div class="menuDiv">
            <h3><a href="javascript:;">其他环境信息</a></h3>
            <ul>
                <li><a href="javascript:loadPageInEnterprise('/otherEnvironmental/EnvTestProgram.jsp');">环境自行监测方案</a></li>
                <li><a href="javascript:loadPageInEnterprise('/otherEnvironmental/otherEnv.jsp');">其他环境信息</a></li>
            </ul>
        </div>
        <%--<div class="menuDiv">--%>
            <%--<h3><a href="javascript:;">自查自报</a></h3>--%>
            <%--<ul>--%>
                <%--<li><a href="javascript:;">自查自报</a></li>--%>
            <%--</ul>--%>
        <%--</div>--%>
    </div>
    <div class="main-right right level3MenuContent">
        <%--<jsp:include page="enterpriseInfo.jsp"></jsp:include>--%>
    </div>
</div>
<%@include file="/container/gov/composite/enterprise_plotting.jsp"%>
<script src="<%=request.getContextPath()%>/container/gov/enterprise/scripts/pageset.js"></script>
<script src="<%=request.getContextPath()%>/container/gov/enterprise/scripts/mainEnterprise.js"></script>
<script type="text/javascript">
    var mSwitch = new MenuSwitch("menuDiv");
    mSwitch.setDefault(0);
    mSwitch.setPrevious(false);
    mSwitch.init();
    $(function(){
        loadPageInEnterprise('companyAccount/enterpriseInfo.jsp?handleType='+handleType+'&id='+id);
    });
    function loadPageInEnterprise(url){
        var headUrl = rootPath +"/container/company/";
        //$(".main-right").load(url);
        $('.level3MenuContent').html(pageUtils.loading()); // 设置页面加载时的loading图片
        $('.level3MenuContent').load(headUrl+url); // ajax加载页面
    }
</script>
</body>
</html>
