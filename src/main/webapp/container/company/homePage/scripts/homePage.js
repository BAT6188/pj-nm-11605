/**
 * Created by Administrator on 2016/11/10.
 */
//@ sourceURL=homePage.js

$(function(){

    loadPageInEnterprise('homePage/realMonitoring.jsp');

    function loadPageInEnterprise(url){
        var headUrl = rootPath +"/container/company/";
        //$(".main-right").load(url);
        $('.level3MenuContent').html(pageUtils.loading()); // 设置页面加载时的loading图片
        $('.level3MenuContent').load(headUrl+url); // ajax加载页面
    }

    /**
     * 信息公告
     */
    InformationNotice();
    function InformationNotice() {
        $.ajax({
            url: rootPath + "/action/action/S_office_PubInfo_powerList.action",
            dataType: 'json',
            type: 'post',
            async: false,
            data: {},
            success: function (result) {
                //清除原有列表数据
                for (var i = 0; i < result.length; i++) {
                    var trHtml = "";
                    trHtml = "<tr>"
                        + "<td><i class='panelList-icon mail-icon'>" + "</i></td>"
                        + "<td ><span>" + (result[i].title == null ? "" : result[i].title) + "</span></td>"
                        + "<td ><span>" + (result[i].pubTime == null ? "" : result[i].pubTime) + "</span></td>"
                        + "<td ><span>" + (result[i].pubOrgName == null ? "" : result[i].pubOrgName) + "</span></td>"
                        + "<td ><span>" + '<button type="button" class="btn btn-md btn-warning view information" id="select" >详情</button>' + "</span></td>"
                        + "</tr>";
                    var $tr = $(trHtml);
                    $("#tablegrid").append($tr);
                }
                $(".information").bind('click', function () {
                    // $("#myModal").modal("show");
                    // window.location.href="<%=request.getContextPath()%>/container/gov/office/pubinfo.jsp";
                    var url = rootPath+"/container/company/dangerInspection/dangerInspection.jsp";
                    toUrl(url);
                });

            }
        });
    }

    /**
     * 超标异常信息
     */
    excessiveAbnormal();
    function excessiveAbnormal(){

        $.ajax({
            url: rootPath + "/action/action/S_port_PortStatusHistory_excessiveInformation.action",
            dataType: 'json',
            type: 'post',
            async: false,
            data: {},
            success: function (result) {
                //清除原有列表数据
                for (var i = 0; i < result.length; i++) {
                    var trHtml = "";
                    trHtml = "<tr>"
                        + "<td><i class='panelList-icon error-icon'>" + "</i></td>"
                        + "<td ><span>" + (result[i].res_title == null ? "" : result[i].res_title) + "</span></td>"
                        + "<td ><span>" + (result[i].startTime == null ? "" : result[i].startTime) + "</span></td>"
                        + "<td ><span>" + (result[i].equipmentPosition == null ? "" : result[i].equipmentPosition)+"</span></td>"
                        + "<td ><span>" + (result[i].realtimeData == null ? "" : result[i].realtimeData) + "</span></td>"
                        + "<td ><span>" + (result[i].maxValue == null ? "" : result[i].maxValue) + "</span></td>"
                        + "<td ><span>" + '<button type="button" class="btn btn-md btn-warning view excess" id="viewBtn" >详情</button>' + "</span></td>"
                        + "</tr>";
                    var $tr = $(trHtml);
                    $("#excessTable").append($tr);
                }

            }
        });
        $(".excess").bind('click', function () {
            // $("#myModal").modal("show");
            // window.location.href="<%=request.getContextPath()%>/container/gov/office/pubinfo.jsp";
            var url = rootPath+"/container/company/warningExcessive/resPortStatusHistory.jsp";
            toUrl(url);
        });



    }

    $("#scrollContent").slimScroll({
        height:"100%",
        railOpacity:.9,
        alwaysVisible:!1
    });
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "code",
                pIdKey: "parentCode",
                rootPId: -1
            }
        },
        height:500,
        width:200,
        view: {
            showLine: false
        },
        async: {
            enable: true,
            url:rootPath + "/action/S_enterprise_Enterprise_getEnterprisePortZtree.action",
            autoParam:["code"],
            otherParam:{"code":enterpriseId},
            dataFilter: null
        },
        callback: {
            onClick: function(event, treeId, treeNode){
                loadPortStatusHistory(treeNode.parentCode,treeNode.code,treeNode.name);
            },
            onAsyncSuccess: function(event, treeId, treeNode, msg) {
                var node = treeObj.getNodeByTId("enterpriseZTree_3");
                if(node){
                    treeObj.expandAll(true);
                    treeObj.selectNode(node,false);
                    loadPortStatusHistory(node.parentCode,node.code,node.name);
                }else{
                    Ewin.alert({message:"没有查询到与该企业相关的排口信息!<br/>稍后将自动返回...",hideTimes:1500}).on(function(e){
                        $('#level2content').html(pageUtils.loading()); // 设置页面加载时的loading图片
                        $('#level2content').load(rootPath+"/container/company/homePage/realMonitoring.jsp"); // ajax加载页面
                    });
                }
            }
        }
    };
    function loadPortStatusHistory(parentCode,code,name){
        switch(parentCode){
            case "gasPort":
                pageUtils.loadPageOfContent("#level3MenuContent",rootPath+"/container/company/homePage/gasPortStatusHistory.jsp?id="+code);
                break;
            case "waterPort":
                pageUtils.loadPageOfContent("#level3MenuContent",rootPath+"/container/company/homePage/waterPortStatusHistory.jsp?id="+code);
                break;
            case "noisePort":
                pageUtils.loadPageOfContent("#level3MenuContent",rootPath+"/container/company/homePage/noisePortStatusHistory.jsp?id="+code);
                break;
            case "fumesPort":
                pageUtils.loadPageOfContent("#level3MenuContent",rootPath+"/container/company/homePage/fumesPortStatusHistory.jsp?id="+code);
                break;
            default:
                break;
        }
    }
    var treeObj = $.fn.zTree.init($("#enterpriseZTree"), setting);





});






