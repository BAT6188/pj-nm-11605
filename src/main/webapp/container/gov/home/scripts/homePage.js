//@ sourceURL=homePage.js
$(function(){

    var highchart = $("#container");

    /**
     * 加载一张图
     */
    $("#links").load(rootPath+"/container/gov/composite/one_image.jsp");
    /**
     * 获取最近三个月的时间
     * @type {Date}
     */
    var date=new Date;
    var year=date.getFullYear();
    var month=date.getMonth()+1;
    var month2 = month - 2;
    month =(month<10 ? "0"+month:month);
    month2 =(month2<10 ? "0"+month2:month2);
    var startTime = (year.toString()+"-"+month2.toString()+"-"+"01");
    var  endTime= (year.toString()+"-"+month.toString() + "-" + "31");

    getColumnHighChartData('','',startTime,endTime);


    function getColumnHighChartData(name,lawType,startYdate,lastYdate){
        // var categories = ["1月","2月","3月","4月","5月","6月"];
        // var series = [];
        //
        // var gongye = {name: "月份", data: [1,2,3,3,4,3.55]};
        // series.push(gongye);
        //
        // colMchart(categories, series);
        $.ajax({
            url: rootPath + "/action/S_dispatch_DispatchTask_getColumnHighChart.action",
            type:'post',
            data:{name:name,lawType:lawType,startYdate:startYdate,lastYdate:lastYdate},
            dataType:'json',
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y;
                var ylist = [];
                if(list && list.length>0){
                    for (var i=0; i<list.length; i++) {
                        ylist.push(parseInt(list[i]));
                    }
                }
                var preMonth = [];//定义查询月份的数组
                var preValue = [];//定义对应月份为0的一组数据
                var startMonth= startYdate.substring(0,7);
                var strStartMonth = startMonth.replace('-','');

                var endMonth= lastYdate.substring(0,7);
                var strEndMonth= endMonth.replace('-','');

                var startYear = startYdate.substring(0,4);
                var endYear = lastYdate.substring(0,4);
                if(startYear == endYear){
                    for(var i = strStartMonth; i <= strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                }else{
                    var startTime = startYear + '12';
                    var firstTime = endYear +'01';
                    for(var i=strStartMonth;i<=startTime;i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                    for(var i=firstTime; i<=strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                }
                console.log(preMonth);
                console.log(preValue);
                var month = categories;//后台取出的2组数据
                var value = ylist;
                if(month && month.length >0){
                    for(var i = 0; i < month.length;i++){
                        var m = month[i];
                        for (var j = 0; j < preMonth.length; j++){
                            if (m == preMonth[j]) {
                                preValue[j] = value[i];
                            }
                        }

                    }
                }
                console.log(preMonth);
                console.log(preValue);

                var series1 = {name: "执法次数", data:preValue};
                series.push(series1);
                colMchart(preMonth,series,startYdate,lastYdate);
            }
        });
    }


    function colMchart(preMonth, series,startYdate,lastYdate){

        highchart.highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '最近三个月的执法统计次数'
            },
            xAxis: {
                categories: preMonth,
                title:{
                    text:'月份'
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: '数量'
                }
            },
            plotOptions: {
                column: {
                    dataLabels: {
                        enabled: true
                    }
                }

            },
            legend: {
                enabled: true
            },

            tooltip: {
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}月</small><table>',
                pointFormat: '<tr><td style="color: {series.color}">{series.name}: </td>' +
                '<td style="text-align: right"><b>{point.y} 次</b></td></tr>',
                footerFormat: '</table>',
                valueDecimals: 0
            },
            exporting: {
                enabled:false
            },
            series: series
        });

    }

    /**
     * 实时数据监测
     */
    //废气排口
    exhaustPort();
    function exhaustPort() {
        $.ajax({
            url: rootPath + "/action/action/S_port_GasPort_realTimeExhaustPort.action",
            dataType: 'json',
            type: 'post',
            async: false,
            data: {},
            success: function (result) {
                for (var i = 0; i < result.length; i++) {
                //清除原有列表数据
                    var trHtml = "";
                    trHtml = "<tr>"
                        + "<td><i class='panelList-icon mail-icon'>" + "</i></td>"
                        + "<td ><span>" + (result[i].name == null ? "" : result[i].name) + "</span></td>"
                        + "<td ><span>" + (result[i].monitorTime == null ? "" : result[i].monitorTime) + "</span></td>"
                        + "<td ><span>" + (result[i].nitrogen == null ? "" : result[i].nitrogen) + "</span></td>"
                        + "<td ><span>" + (result[i].sulfur == null ? "" : result[i].sulfur) + "</span></td>"
                        + "<td ><span>" + (result[i].gasFlow == null ? "" : result[i].gasFlow) + "</span></td>"
                        + "<td ><span>" + (result[i].dust == null ? "" : result[i].dust) + "</span></td>"
                        + "<td ><span>" + (result[i].oxygen == null ? "" : result[i].oxygen) + "</span></td>"
                        + "</tr>";
                    var $tr = $(trHtml);
                    $("#gasTable").append($tr);
                }
            }
        });
    }

    //废水排口
    WasteWaterPort();
    function WasteWaterPort() {
        $.ajax({
            url: rootPath + "/action/action/S_port_WaterPort_realTimeWaterPort.action",
            dataType: 'json',
            type: 'post',
            async: false,
            data: {},
            success: function (result) {
                for (var i = 0; i < result.length; i++) {
                    //清除原有列表数据
                    var trHtml = "";
                    trHtml = "<tr>"
                        + "<td><i class='panelList-icon mail-icon'>" + "</i></td>"
                        + "<td ><span>" + (result[i].name == null ? "" : result[i].name) + "</span></td>"
                        + "<td ><span>" + (result[i].monitorTime == null ? "" : result[i].monitorTime) + "</span></td>"
                        + "<td ><span>" + (result[i].flow == null ? "" : result[i].flow) + "</span></td>"
                        + "<td ><span>" + (result[i].oxygen == null ? "" : result[i].oxygen) + "</span></td>"
                        + "<td ><span>" + (result[i].nitrogen == null ? "" : result[i].nitrogen) + "</span></td>"
                        + "<td ><span>" + (result[i].ph == null ? "" : result[i].ph) + "</span></td>"
                        + "</tr>";
                    var $tr = $(trHtml);
                    $("#waterTable").append($tr);
                }
            }
        });
    }


    //噪声源排口
    noisesPort();
    function noisesPort() {
        $.ajax({
            url: rootPath + "/action/action/S_port_NoisePort_realTimeNoisesPort.action",
            dataType: 'json',
            type: 'post',
            async: false,
            data: {},
            success: function (result) {
                for (var i = 0; i < result.length; i++) {
                    //清除原有列表数据
                    var trHtml = "";
                    trHtml = "<tr>"
                        + "<td><i class='panelList-icon mail-icon'>" + "</i></td>"
                        + "<td ><span>" + (result[i].name == null ? "" : result[i].name) + "</span></td>"
                        + "<td ><span>" + (result[i].monitorTime == null ? "" : result[i].monitorTime) + "</span></td>"
                        + "<td ><span>" + (result[i].leqdb == null ? "" : result[i].leqdb) + "</span></td>"
                        + "<td ><span>" + (result[i].lmax == null ? "" : result[i].lmax) + "</span></td>"
                        + "<td ><span>" + (result[i].lmin == null ? "" : result[i].lmin) + "</span></td>"
                        + "</tr>";
                    var $tr = $(trHtml);
                    $("#noiseTable").append($tr);
                }
            }
        });
    }


    //油烟排口
    fumesPort();
    function fumesPort() {
        $.ajax({
            url: rootPath + "/action/action/S_port_FumesPort_realTimeFumesPort.action",
            dataType: 'json',
            type: 'post',
            async: false,
            data: {},
            success: function (result) {
                for (var i = 0; i < result.length; i++) {
                    //清除原有列表数据
                    var trHtml = "";
                    trHtml = "<tr>"
                        + "<td><i class='panelList-icon mail-icon'>" + "</i></td>"
                        + "<td ><span>" + (result[i].name == null ? "" : result[i].name) + "</span></td>"
                        + "<td ><span>" + (result[i].monitorTime == null ? "" : result[i].monitorTime) + "</span></td>"
                        + "<td ><span>" + (result[i].fumes == null ? "" : result[i].fumes) + "</span></td>"
                        + "<td ><span>" + (result[i].temperature == null ? "" : result[i].temperature) + "</span></td>"
                        + "<td ><span>" + (result[i].humidity == null ? "" : result[i].humidity) + "</span></td>"
                        + "<td ><span>" + (result[i].ph == null ? "" : result[i].ph) + "</span></td>"
                        + "</tr>";
                    var $tr = $(trHtml);
                    $("#fumesTable").append($tr);
                }
            }
        });
    }



});