/**
 * Created by Administrator on 2016/10/13.
 */
//@ sourceURL=excessive_ratio.js
$(function(){

    var highchart = $("#container");
    var highchart1 = $("#container1");
    var highchart2 = $("#container2");

    //默认获取上半年时间，和去年上半年时间
    var year = new Date().getFullYear();
    var startXdate = year-1 + '-'+ '01' + '-'+ '01';
    var lastXdate = year-1 + '-'+ '06' + '-'+ '30';
    var startSdate = year +　'-'+'01' + '-'+'01';
    var lastSdate = year + '-'+ '06' + '-'+ '30';

    //初始化日期组件
    $('.form_datetime1').datetimepicker({
        language:   'zh-CN',
        autoclose: 1,
        startView: 3,//月视图
        minView: 3,
        weekStart: 1,
        autoclose: true,
        todayBtn: 'linked'
    });
    /**
     * 设定日期选项在同一年
     */
    $("#start_createTime").bind("change",function () {
        var startTime = $(this).val();
        if (!startTime) {
            return;
        }
        var year = startTime.substr(0, 4);
        var years = parseInt(year) +1;
        var month = parseInt(startTime.substr(5, 2));
        var endTimeStartMonth = month;
        if (month == 12) {
            endTimeStartMonth = month;
        }else{
            endTimeStartMonth = month+3
        }
        $('.form_datetime2').datetimepicker('setStartDate', year+"-"+endTimeStartMonth);
        $('.form_datetime2').datetimepicker('setEndDate', years+"-"+"01");
    });

    $("#startTime").bind('change',function(){
        var nowYear = $("#startTime").val();
        $("#endtime").val(nowYear-1);

    });
 
    $('.form_datetime2').datetimepicker({
        language:   'zh-CN',
        startView: 3,//月视图
        minView: 3,
        weekStart: 1,
        autoclose: true,
        todayBtn: 'linked'
    });



    $('.form_datetimes').datetimepicker({
        language:   'zh-CN',
        autoclose: 1,
        startView: 4,//月视图
        minView: 4

    });


    //执行初始化
    initPage();
    var valueChart = '1';

    function initPage(){
        $('#columnBtn').css('background','#0099FF');
        // getColumnRatio();
        search(valueChart,'',startXdate,lastXdate,startSdate,lastSdate);

    }


    //查询按钮
    $("#search").bind('click',function(){
        var name = $("#s_name").val();
        var startSdate = $("#start_createTime").val();
        var lastSdate = $("#end_createTime").val();
        var dateStr = startSdate;
        var arr = dateStr.split("-");
        var lastDate = new Date(parseInt(arr[0])-1, parseInt(arr[1])-1);
        var lastMonth = lastDate.getMonth()+1;
        if (lastMonth < 10) {
            lastMonth = "0" + lastMonth;
        }
        var startXdate = lastDate.getFullYear() + "-" + lastMonth;

        var dateLtr2 = lastSdate;
        var arr2 = dateLtr2.split("-");
        var lastDate2 = new Date(parseInt(arr2[0])-1, parseInt(arr2[1])-1);
        var lastMonth2 = lastDate2.getMonth()+1;
        if (lastMonth2 < 10) {
            lastMonth2 = "0" + lastMonth2;
        }
        var lastXdate = lastDate2.getFullYear() + "-" + lastMonth2;

        search(valueChart,name,startXdate,lastXdate,startSdate,lastSdate);

    });


    function search(valueChart,name,startXdate,lastXdate,startSdate,lastSdate){
        if(valueChart == '2'){
            highchart.hide();
            highchart1.show();
            highchart2.show();
            getPieRatio1(name,startXdate,lastXdate,startSdate,lastSdate);
            getPieRatio2(name,startXdate,lastXdate,startSdate,lastSdate);
        }else if(valueChart == '3'){
            highchart1.hide();
            highchart2.hide();
            highchart.show();
            getLineRatio(name,startXdate,lastXdate,startSdate,lastSdate);
        }else{
            highchart1.hide();
            highchart2.hide();
            highchart.show();
            getColumnRatio(name,lastXdate,lastSdate,startSdate,lastSdate);
        }

    }

    //同期对比上半年按钮
    $("#sbYear").bind('click',function(){
       var year = $("#startTime").val();
        var startXdate = year-1 + '-'+ '01' + '-'+ '01';
        var lastXdate = year-1 + '-'+ '06' + '-'+ '30';
        var startSdate = year +　'-'+'01' + '-'+'01';
        var lastSdate = year + '-'+ '06' + '-'+ '30';
        search(valueChart,'',startXdate,lastXdate,startSdate,lastSdate);
    });

    //同期对比下半年按钮查询
    $("#xbYear").bind('click',function(){
        var year = $("#startTime").val();
        var startXdate = year-1 + '-'+ '07' + '-'+ '01';
        var lastXdate = year-1 + '-'+ '12' + '-'+ '31';
        var startSdate = year +　'-'+'07' + '-'+'01';
        var lastSdate = year + '-'+ '12' + '-'+ '31';
        search(valueChart,'',startXdate,lastXdate,startSdate,lastSdate);
    });


    
    //柱状图按钮
    $("#columnBtn").bind('click',function(){
        valueChart = $("#columnBtn").attr("data-checked");
        $('#columnBtn').css('background','#0099FF');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#fff');
        search(valueChart,'',startXdate,lastXdate,startSdate,lastSdate);
    });


    //饼状图按钮
    $("#pieBtn").bind('click',function(){
        valueChart = $("#pieBtn").attr("data-checked");
        $("#pieBtn").css('background','#0099FF');
        $('#columnBtn').css('background-color','#fff');
        $("#lineBtn").css('background','#fff');
        search(valueChart,'',startXdate,lastXdate,startSdate,lastSdate);

    });

    //线状图按钮
    $("#lineBtn").bind('click',function(){
        valueChart = $("#lineBtn").attr("data-checked");
        $('#columnBtn').css('background','#fff');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#0099FF');
        search(valueChart,'',startXdate,lastXdate,startSdate,lastSdate);
    });
    //柱状图获取后台数据
    function getColumnRatio(name,startXdate,lastXdate,startSdate,lastSdate){
        // var categories = ["1月","2月","3月","4月","5月","6月"];
        // var series = [];
        //
        // var tYear = {name: "2015年上半年", data: [1,2,3,3,4,3.55]};
        // var yYear = {name: "2016年上半年", data: [2,3,1.2,4.5,3.7,2.8]};
        // series.push(tYear);
        // series.push(yYear);
        // loadColumnChart(categories, series);
        $.ajax({
            url:rootPath + "/action/S_port_PortStatusHistory_getColumnRatio.action",
            type:'post',
            data:{name:name,startSdate:startSdate,lastSdate:lastSdate},
            dataType:'json',
            success:function(data) {
                var categories = data.x;
                var series = [];
                var list = data.y1;
                var ylist = [];
                var list2 = data.y2;
                var ylist2 = [];
                if (list && list.length > 0) {
                    for (var i = 0; i < list.length; i++) {
                        ylist.push(parseInt(list[i]));
                    }
                }
                if (list2 && list.length > 0) {
                    for (var i = 0; i < list2.length; i++) {
                        ylist2.push(parseInt(list2[i]));
                    }
                }
                var preMonth = [];//定义查询月份的数组
                var preValue1 = [];//定义对应月份为0的一组数据
                var preValue2 = [];//定义对应月份为0的一组数据

                // var startMonth= startXdate.substring(0,7);
                // var strStartMonth = startMonth.replace('-','');
                //
                // var endMonth= lastXdate.substring(0,7);
                // var strEndMonth= endMonth.replace('-','');
                //
                // var startYear = startXdate.substring(0,4);
                // var endYear = lastXdate.substring(0,4);
                // if(startYear == endYear){
                //     for(var i = strStartMonth; i <= strEndMonth; i++){
                //         i = i + "";
                //         var k = i.substr(0, 4)+"-"+i.substr(4,2);
                //         var j = i.substr(0,4);
                //         var n = parseInt(j) + 1;
                //         var m = n + "-"+i.substr(4,2);
                //         var h = "(" + k + ")" + "-" + "(" + m + ")";
                //         preMonth.push(h);
                //         preValue1.push(0);
                //         preValue2.push(0);
                //     }
                // }else{
                //     var startTime = startYear + '12';
                //     var firstTime = endYear +'01';
                //     for(var i=strStartMonth;i<=startTime;i++){
                //         // i = i + "";
                //         // var k = i.substr(0, 4)+"-"+i.substr(4,2);
                //         // preMonth.push(k);
                //         i = i + "";
                //         var k = i.substr(0, 4)+"-"+i.substr(4,2);
                //         var j = i.substr(0,4);
                //         var n = parseInt(j) + 1;
                //         var m = n + "-"+i.substr(4,2);
                //         var h = "(" + k + ")" + "-" + "(" + m + ")";
                //         preMonth.push(h);
                //         preValue1.push(0);
                //         preValue2.push(0);
                //     }
                //     for(var i=firstTime; i<=strEndMonth; i++){
                //         // i = i + "";
                //         // var k = i.substr(0, 4)+"-"+i.substr(4,2);
                //         // preMonth.push(k);
                //         i = i + "";
                //         var k = i.substr(0, 4)+"-"+i.substr(4,2);
                //         var j = i.substr(0,4);
                //         var n = parseInt(j) + 1;
                //         var m = n + "-"+i.substr(4,2);
                //         var h = "(" + k + ")" + "-" + "(" + m + ")";
                //         preMonth.push(h);
                //         preValue1.push(0);
                //         preValue2.push(0);
                //     }
                // }
                var startMonth = startSdate.substring(5, 7);
                if (startMonth < 10) {
                    var sMonth = startMonth.substring(1)
                } else {
                    sMonth = startMonth;
                }
                var endMonth = lastSdate.substring(5, 7);
                if (endMonth < 10) {
                    var lasMonth = endMonth.substring(1);
                } else {
                    lasMonth = endMonth
                }
                for (var i = parseInt(sMonth); i <= parseInt(lasMonth); i++) {
                    preMonth.push(i);
                    preValue1.push(0);
                    preValue2.push(0);
                }
                var month = categories;//后台取出的2组数据
                var value = ylist;
                var arr_value = ylist2;
                if (month && month.length > 0) {
                    for (var i = 0; i < month.length; i++) {
                        var m = month[i];
                        for (var j = 0; j < preMonth.length; j++) {
                            if (m == preMonth[j]) {
                                preValue1[j] = value[i];
                            }
                        }
                        for (var k = 0; k < preMonth.length; k++) {
                            if (m == preMonth[k]) {
                                preValue2[k] = arr_value[i];
                            }
                        }

                    }
                }
                var series1 = {name: "上一年同期超标次数", color: 'rgb(124, 181, 236)', data: preValue1};
                var series2 = {name: "当前年份超标次数", color: '#FF8800', data: preValue2};
                series.push(series1);
                series.push(series2);
                loadColumnChart(preMonth, series,startSdate,lastSdate);
            }
        });
    }
    //饼状图获取后台数据
    function getPieRatio1(name,startXdate,lastXdate,startSdate,lastSdate){
       $.ajax({
           url:rootPath + "/action/S_port_PortStatusHistory_getColumnRatio.action",
           type:'post',
           data:{name:name,startSdate:startSdate,lastSdate:lastSdate},
           dataType:'json',
           success:function(data) {
               var categories = data['x'];
               var series1 = data['y1'];
               var series = [{
                   name:"超标次数:(次)",
                   data:[]
               }];
               var preMonth = [];//定义查询月份的数组
               var preValue = [];//定义对应月份为0的一组数据

               // var startMonth= startSdate.substring(0,7);
               // var strStartMonth = startMonth.replace('-','');
               //
               // var endMonth= lastSdate.substring(0,7);
               // var strEndMonth= endMonth.replace('-','');
               //
               // var startYear = startSdate.substring(0,4);
               // var endYear = lastSdate.substring(0,4);
               // if(startYear == endYear){
               //     for(var i = strStartMonth; i <= strEndMonth; i++){
               //         i = i + "";
               //         var k = i.substr(0, 4)+"-"+i.substr(4,2);
               //         preMonth.push(k);
               //         preValue.push(0);
               //     }
               // }else{
               //     var startTime = startYear + '12';
               //     var firstTime = endYear +'01';
               //     for(var i=strStartMonth;i<=startTime;i++){
               //         i = i + "";
               //         var k = i.substr(0, 4)+"-"+i.substr(4,2);
               //         preMonth.push(k);
               //         preValue.push(0);
               //     }
               //     for(var i=firstTime; i<=strEndMonth; i++){
               //         i = i + "";
               //         var k = i.substr(0, 4)+"-"+i.substr(4,2);
               //         preMonth.push(k);
               //         preValue.push(0);
               //     }
               // }
               // console.log(preMonth);
               // console.log(preValue);
               // var month = categories;//后台取出的2组数据
               // var value = series1;
               // if(month && month.length>0){
               //     for(var i = 0; i < month.length;i++){
               //         var n = month[i];
               //         var m = n.substr(11,7);
               //         for (var j = 0; j < preMonth.length; j++){
               //             if (m == preMonth[j]) {
               //                 preValue[j] = value[i];
               //             }
               //         }
               //     }
               // }
               var startMonth= startSdate.substring(5,7);
               if(startMonth < 10){
                   var sMonth = startMonth.substring(1)
               }else{
                   sMonth = startMonth;
               }
               var endMonth= lastSdate.substring(5,7);
               if(endMonth <10){
                   var lasMonth = endMonth.substring(1);
               }else{
                   lasMonth = endMonth
               }

               for(var i =  parseInt(sMonth); i <=  parseInt(lasMonth); i++){
                   preMonth.push(i);
                   preValue.push(0);
               }
               console.log(preMonth);
               console.log(preValue);
               var month = categories;//后台取出的2组数据
               var value = series1;
               if(month && month.length>0){
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

               for (var i = 0; i < preValue.length; i++) {

                   series[0].data.push({name:preMonth[i],y: parseInt(preValue[i])});
               }
               loadPieChart1(series,startXdate,lastXdate);
           }
       });

    }

    function getPieRatio2(name,startXdate,lastXdate,startSdate,lastSdate){
        $.ajax({
            url:rootPath + "/action/S_port_PortStatusHistory_getColumnRatio.action",
            type:'post',
            data:{name:name,startSdate:startSdate,lastSdate:lastSdate},
            dataType:'json',
            success:function(data) {
                var categories = data['x'];
                var series1 = data['y2'];
                var series = [{
                    name:"超标次数:(次)",
                    data:[]
                }];

                var preMonth = [];//定义查询月份的数组
                var preValue = [];//定义对应月份为0的一组数据
                var startMonth= startSdate.substring(5,7);
                if(startMonth < 10){
                    var sMonth = startMonth.substring(1)
                }else{
                    sMonth = startMonth;
                }
                var endMonth= lastSdate.substring(5,7);
                if(endMonth <10){
                    var lasMonth = endMonth.substring(1);
                }else{
                    lasMonth = endMonth
                }

                for(var i =  parseInt(sMonth); i <=  parseInt(lasMonth); i++){
                    preMonth.push(i);
                    preValue.push(0);
                }
                console.log(preMonth);
                console.log(preValue);
                var month = categories;//后台取出的2组数据
                var value = series1;
                if(month && month.length>0){
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

                for (var i = 0; i < preValue.length; i++) {

                    series[0].data.push({name:preMonth[i],y: parseInt(preValue[i])});
                }
                loadPieChart2(series,startSdate,lastSdate);
            }
        });


    }


    //线状图获取后台数据
    function  getLineRatio(name,startXdate,lastXdate,startSdate,lastSdate){
        // var categories = ["1月","2月","3月","4月","5月","6月"];
        // var series = [];
        //
        // var tYear = {name: "20165年上半年", data: [1,2,3,3,4,3.55]};
        // var yYear = {name: "2016年上半年", data: [2,3,1.2,4.5,3.7,2.8]};
        // series.push(tYear);
        // series.push(yYear);
        $.ajax({
            url:rootPath + "/action/S_port_PortStatusHistory_getColumnRatio.action",
            type:'post',
            data:{name:name,startSdate:startSdate,lastSdate:lastSdate},
            dataType:'json',
            success:function(data) {
                var categories = data.x;
                var series = [];
                var list = data.y1;
                var ylist = [];
                var list2 = data.y2;
                var ylist2 = [];
                if (list && list.length > 0) {
                    for (var i = 0; i < list.length; i++) {
                        ylist.push(parseInt(list[i]));
                    }
                }
                if (list2 && list.length > 0) {
                    for (var i = 0; i < list2.length; i++) {
                        ylist2.push(parseInt(list2[i]));
                    }
                }
                var preMonth = [];//定义查询月份的数组
                var preValue1 = [];//定义对应月份为0的一组数据
                var preValue2 = [];//定义对应月份为0的一组数据
                var startMonth = startSdate.substring(5, 7);
                if (startMonth < 10) {
                    var sMonth = startMonth.substring(1)
                } else {
                    sMonth = startMonth;
                }
                var endMonth = lastSdate.substring(5, 7);
                if (endMonth < 10) {
                    var lasMonth = endMonth.substring(1);
                } else {
                    lasMonth = endMonth
                }
                for (var i =  parseInt(sMonth); i <=  parseInt(lasMonth); i++) {
                    preMonth.push(i);
                    preValue1.push(0);
                    preValue2.push(0);
                }
                var month = categories;//后台取出的2组数据
                var value = ylist;
                var arr_value = ylist2;
                if (month && month.length > 0) {
                    for (var i = 0; i < month.length; i++) {
                        var m = month[i];
                        for (var j = 0; j < preMonth.length; j++) {
                            if (m == preMonth[j]) {
                                preValue1[j] = value[i];
                            }
                        }
                        for (var k = 0; k < preMonth.length; k++) {
                            if (m == preMonth[k]) {
                                preValue2[k] = arr_value[i];
                            }
                        }

                    }
                }
                var series1 = {name: "上一年同期超标次数", color: 'rgb(124, 181, 236)', data: preValue1};
                var series2 = {name: "当前年份超标次数", color: '#FF8800', data: preValue2};
                series.push(series1);
                series.push(series2);
                loadLineChart(preMonth, series,startSdate,lastSdate);
            }
            
        });
        

    }


    //柱状图highchart
    function loadColumnChart(categories,series,startSdate,lastSdate){
        if(startSdate == '2016-01-01'){
            titleSub = '2015年上半年与2016年上半年超标统计对比分析'
        }else{
            titleSub = startSdate+'至'+lastSdate+'同期超标统计对比分析';
        }
        highchart.highcharts({
            chart: {
                type: 'column',
                margin: 75,
                options3d: {
//                        enabled: true,
                    enabled: false,
                    alpha: 10,
                    beta: 25,
                    depth: 70
                }
            },
            title: {
                text: titleSub
            },
            // subtitle: {
            //     text: 'Notice the difference between a 0 value and a null point'
            // },
            plotOptions: {
                column: {
                    depth: 25
                }
            },
            xAxis: {
                categories: categories,
                title: {
                    text: '月份'
                }
            },
            yAxis: {
                allowDecimals:false,//是否允许为小数
                min: 0,
                title: {
                    text: '超标次数(次)'
                }
            },
            exporting: {
                enabled:false
            },
            tooltip: {
                headerFormat: '<small>{point.key}月</small><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} </b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            series:  series
        });

    }

    //饼状图1highchart
    function loadPieChart1(series,startXdate,lastXdate){
        if(startXdate == '2015-01-01'){
            titleSub = '2015年上半年超标统计对比分析'
        }else{
            titleSub = startXdate+'至'+lastXdate+'同期超标统计对比分析';
        }
        highchart1.highcharts({
            chart: {
                type: 'pie'
            },
            title: {
                text: titleSub
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}月</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    },
                    showInLegend: true
                }
            },
            exporting: {
                enabled:false
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
            series:  series
        });

    }
    //饼状图2
    function loadPieChart2(series,startSdate,lastSdate){
        if(startSdate == '2016-01-01'){
            titleSub = '2016年上半年超标统计对比分析'
        }else{
            titleSub = startSdate+'至'+lastSdate+'同期超标统计对比分析';
        }
        highchart2.highcharts({
            chart: {
                type: 'pie'
            },
            title: {
                text: titleSub
            },
            // subtitle: {
            //     text: 'Notice the difference between a 0 value and a null point'
            // },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}月</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    },
                    showInLegend: true
                }
            },
            exporting: {
                enabled:false
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

            series:  series
        });

    }

    

    
    //线状图highchart
    function loadLineChart(categories, series,startSdate,lastSdate){
        if(startSdate == '2016-01-01'){
            titleSub = '2015年上半年与2016年上半年超标统计对比分析'
        }else{
            titleSub = startSdate+'至'+lastSdate+'同期超标统计对比分析';
        }
        highchart.highcharts({
            chart: {
                type: 'line',
                margin: 75,
                options3d: {
                    enabled: false,
                    alpha: 10,
                    beta: 25,
                    depth: 70
                }
            },
            title: {
                text: titleSub
            },
            // subtitle: {
            //     text: 'Notice the difference between a 0 value and a null point'
            // },
            // plotOptions: {
            //     column: {
            //         depth: 25
            //     }
            // },
            xAxis: {
                categories: categories,
                title: {
                    text: '月份'
                }
            },
            yAxis: {
                allowDecimals:false,//是否允许为小数
                min: 0,
                title: {
                    text: '超标次数(次)'
                }
            },
            exporting: {
                enabled:false
            },
            tooltip: {
                headerFormat: '<small>{point.key}月</small><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} </b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            series:  series
        });

    }

});