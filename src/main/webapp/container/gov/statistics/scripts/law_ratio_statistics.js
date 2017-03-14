/**
 * Created by Administrator on 2016/10/13.
 */
//@ sourceURL=law_ratio_statistics.js
$(function(){

    var highchart = $("#container");
    var highchart1 = $("#container1");
    var highchart2 = $("#container2");
    //初始化日期组件
    $('.form_datetime1').datetimepicker({
        language:   'zh-CN',
        autoclose: 1,
        startView: 3,//月视图
        minView: 3
    });

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

    //默认获取上半年时间，和去年上半年时间
    var year = new Date().getFullYear();
    var startXdate = year-1 + '-'+ '01' + '-'+ '01';
    var lastXdate = year-1 + '-'+ '06' + '-'+ '30';
    var startSdate = year +　'-'+'01' + '-'+'01';
    var lastSdate = year + '-'+ '06' + '-'+ '30';

    
    //执行初始化
    initPage();
    var valueChart = '1';

    function initPage(){
        $('#columnBtn').css('background','#0099FF');
        // getColumnRatio();
        search(valueChart,'','',startXdate,lastXdate,startSdate,lastSdate);
    }
    
    var name;
    //查询按钮
    $("#search").bind('click',function(){
        name = $("#s_name").val();
        var lawType = $("#lawType").val();
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
        search(valueChart,name,lawType,startXdate,lastXdate,startSdate,lastSdate);
       
        
    });

    function search(valueChart,name,lawType,startXdate,lastXdate,startSdate,lastSdate){
        if(valueChart == '2'){
            highchart.hide();
            highchart1.show();
            highchart2.show();
            getPieRatio1(name,lawType,startXdate,lastXdate,startSdate,lastSdate);
            getPieRatio2(name,lawType,startXdate,lastXdate,startSdate,lastSdate);
        }else if(valueChart == '3'){
            highchart1.hide();
            highchart2.hide();
            highchart.show();
            getLineRatio(name,lawType,startXdate,lastXdate,startSdate,lastSdate);
        }else{
            highchart1.hide();
            highchart2.hide();
            highchart.show();
            getColumnRatio(name,lawType,startXdate,lastXdate,startSdate,lastSdate);
        }


    }

    //同期对比上半年按钮
    $("#sbYear").bind('click',function(){
        var year = $("#startTime").val();
        var startXdate = year-1 + '-'+ '01' + '-'+ '01';
        var lastXdate = year-1 + '-'+ '06' + '-'+ '30';
        var startSdate = year +　'-'+'01' + '-'+'01';
        var lastSdate = year + '-'+ '06' + '-'+ '30';
        search(valueChart,'','',startXdate,lastXdate,startSdate,lastSdate);
    });

    //同期对比下半年按钮查询
    $("#xbYear").bind('click',function(){
        var year = $("#startTime").val();
        var startXdate = year-1 + '-'+ '07' + '-'+ '01';
        var lastXdate = year-1 + '-'+ '12' + '-'+ '31';
        var startSdate = year +　'-'+'07' + '-'+'01';
        var lastSdate = year + '-'+ '12' + '-'+ '31';
        search(valueChart,'','',startXdate,lastXdate,startSdate,lastSdate);
    });


    //柱状图按钮
    $("#columnBtn").bind('click',function(){
        valueChart = $("#columnBtn").attr("data-checked");
        $('#columnBtn').css('background','#0099FF');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#fff');
        search(valueChart,'','',startXdate,lastXdate,startSdate,lastSdate);
    });


    //饼状图按钮
    $("#pieBtn").bind('click',function(){
        valueChart = $("#pieBtn").attr("data-checked");
        $("#pieBtn").css('background','#0099FF');
        $('#columnBtn').css('background-color','#fff');
        $("#lineBtn").css('background','#fff');
        search(valueChart,'','',startXdate,lastXdate,startSdate,lastSdate);

    });

    //线状图按钮
    $("#lineBtn").bind('click',function(){
        valueChart = $("#lineBtn").attr("data-checked");
        $('#columnBtn').css('background','#fff');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#0099FF');
        search(valueChart,'','',startXdate,lastXdate,startSdate,lastSdate);
    });

    // function getColumnRatio(){
    //     var categories = ["1月","2月","3月","4月","5月","6月"];
    //     var series = [];
    //
    //     var tYear = {name: "2016年上半年", data: [1,2,3,3,4,3.55]};
    //     var yYear = {name: "2015年上半年", data: [2,3,1.2,4.5,3.7,2.8]};
    //     series.push(tYear);
    //     series.push(yYear);
    // }
    
    
    //柱状图highchart获取后台数据
    function getColumnRatio(name,lawType,startXdate,lastXdate,startSdate,lastSdate){
        $.ajax({
            url: rootPath + "/action/S_dispatch_DispatchTask_getColumnRatio.action",
            type: 'post',
            data: {name:name,lawType:lawType,startXdate:startXdate,lastXdate:lastXdate,startSdate:startSdate,lastSdate:lastSdate},
            dataType: 'json',
            success:function(data){
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

                var series1 = {name: "上一年同时期执法次数", color: 'rgb(124, 181, 236)', data: preValue1};
                var series2 = {name: "当前日期执法次数", color: '#FF8800', data: preValue2};
                series.push(series1);
                series.push(series2);
                loadColumnChart(preMonth, series,startSdate,lastSdate);
            }
        });
    }

    //饼状图1获取数据·
    function getPieRatio1(name,lawType,startXdate,lastXdate,startSdate,lastSdate){
        // var categories = ["1月","2月","3月","4月","5月","6月"];
        // var series1 = [];
        // var series2 = [];
        //
        // var tYear = {name: "2015年上半年", data: [1,2,3,3,4,3.55]};
        // var yYear = {name: "2016年上半年", data: [2,3,1.2,4.5,3.7,2.8]};
        // series1.push(tYear);
        // series2.push(yYear);
        // loadPieChart1(categories, series1);
        // loadPieChart2(categories, series2);
        $.ajax({
            url: rootPath + "/action/S_dispatch_DispatchTask_getColumnRatio.action",
            type: 'post',
            data: {name:name,lawType:lawType,startXdate:startXdate,lastXdate:lastXdate,startSdate:startSdate,lastSdate:lastSdate},
            dataType: 'json',
            success:function(data){
                var categories = data['x'];
                var series1 = data['y1'];
                var series = [{
                    name:"执法次数:(次)",
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
                loadPieChart1(series,startXdate,lastXdate);
            }
        });

    }

    //饼状图2获取数据
    function getPieRatio2(name,lawType,startXdate,lastXdate,startSdate,lastSdate){
        $.ajax({
            url: rootPath + "/action/S_dispatch_DispatchTask_getColumnRatio.action",
            type: 'post',
            data: {name:name,lawType:lawType,startXdate:startXdate,lastXdate:lastXdate,startSdate:startSdate,lastSdate:lastSdate},
            dataType: 'json',
            success:function(data){
                var categories = data['x'];
                var series1 = data['y2'];
                var series = [{
                    name:"执法次数:(次)",
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

    //highchart折线图获取后台数据
    function getLineRatio(name,lawType,startXdate,lastXdate,startSdate,lastSdate){
        // var categories = ["1月","2月","3月","4月","5月","6月"];
        // var series = [];
        // var tYear = {name: "2015年上半年", data: [1,2,3,3,4,3.55]};
        // var yYear = {name: "2016年上半年", data: [2,3,1.2,4.5,3.7,2.8]};
        // series.push(tYear);
        // series.push(yYear);
        // loadLineChart(categories, series);
        $.ajax({
            url: rootPath + "/action/S_dispatch_DispatchTask_getColumnRatio.action",
            type:'post',
            dataType:'json',
            data:{name:name,lawType:lawType,startXdate:startXdate,lastXdate:lastXdate,startSdate:startSdate,lastSdate:lastSdate},
            success:function(data){
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
                var series1 = {name: "上一年同时期执法次数", color: 'rgb(124, 181, 236)', data: preValue1};
                var series2 = {name: "当前日期执法次数", color: '#FF8800', data: preValue2};
                series.push(series1);
                series.push(series2);
                loadLineChart(preMonth, series,startSdate,lastSdate);
            }
        });
    }


    //柱状图highchart
    function loadColumnChart(categories,series,startSdate,lastSdate){
        if(startSdate == '2016-01-01'){
            titleSub = '2015年上半年与2016年上半年执法统计对比分析'
        }else{
            titleSub = startSdate+'月至'+lastSdate+'月同期执法统计对比分析';
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
                    dataLabels: {
                        enabled: true
                    }
                },
                series : {
                    cursor: 'pointer',
                    events : {
                        click: function(e) {
                            console.log(e.point.category);
                            $("#lawRatioListForm").modal('show');
                            var year = startSdate.substr(0,4);
                            var month = parseInt(e.point.category);
                            if(month < 10){
                                month =  "0"+month;
                            }else{
                                month;
                            }

                            var firstTime = year + "-" + month + "-" + "01";
                            var d=new Date(year,month,0);
                            var lastTime = year + "-" + month + "-"+d.getDate();
                            var year2 = year - 1;
                            var lastStartTime = year2 + "-" + month + "-" + "01";
                            var d=new Date(year2,month,0);
                            var lastEndTime = year2 + "-" + month + "-"+d.getDate();
                            initlLawRatioTable(lastStartTime,lastEndTime);
                            initcLawRatioTable(firstTime,lastTime);
                            //initlawTable(lastStartTime,lastEndTime,firstTime,lastTime);
                        }
                    }
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
            titleSub = '2015年上半年执法统计对比分析'
        }else{
            titleSub = startXdate+'至'+lastXdate+'同期执法统计对比分析';
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
                },
                series : {
                    cursor: 'pointer',
                    events : {
                        click: function(e) {
                            console.log(e.point.name);
                            $("#lawRatioListForm2").modal('show');
                            var year = startSdate.substr(0,4);
                            var month = parseInt(e.point.name);
                            if(month < 10){
                                month =  "0"+month;
                            }else{
                                month;
                            }

                           
                            var year2 = year - 1;
                            var firstTime = year2 + "-" + month + "-" + "01";
                            var d=new Date(year2,month,0);
                            var lastTime = year2 + "-" + month + "-"+d.getDate();
                            initlawTable2(firstTime,lastTime);
                        }
                    }
                }

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
            series:  series
        });

    }

    //饼状图2
    function loadPieChart2(series,startSdate,lastSdate){
        if(startSdate == '2016-01-01'){
            titleSub = '2016年上半年执法统计对比分析'
        }else{
            titleSub = startSdate+'至'+lastSdate+'同期执法统计对比分析';
        }
        highchart2.highcharts({
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
                },
                series : {
                    cursor: 'pointer',
                    events : {
                        click: function(e) {
                            console.log(e.point.name);
                            $("#lawRatioListForm2").modal('show');
                            var year = startSdate.substr(0,4);
                            var month = parseInt(e.point.name);
                            if(month < 10){
                                month =  "0"+month;
                            }else{
                                month;
                            }

                            var firstTime = year + "-" + month + "-" + "01";
                            var d=new Date(year,month,0);
                            var lastTime = year + "-" + month + "-"+d.getDate();
                            initlawTable2(firstTime,lastTime);
                        }
                    }
                }
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
            series:  series
        });

    }


    //线状图highchart
    function loadLineChart(categories, series,startSdate,lastSdate){
        if(startSdate == '2016-01-01'){
            titleSub = '2015年上半年与2016年上半年执法统计对比分析'
        }else{
            titleSub = startSdate+'月至'+lastSdate+'月同期执法统计对比分析';
        }

        $('#container').highcharts({
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
            lang:{
                downloadJPEG: "下载JPEG 图片",
                downloadPDF: "下载PDF文档",
                downloadPNG: "下载PNG 图片",
                downloadSVG: "下载SVG 矢量图",
                exportButtonTitle: "导出图片"
            },
            title: {
                text: titleSub
            },
            // subtitle: {
            //     text: 'Notice the difference between a 0 value and a null point'
            // },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    }
                },
                series : {
                    cursor: 'pointer',
                    events : {
                        click: function(e) {
                            console.log(e.point.category);
                            $("#lawRatioListForm").modal('show');
                            var year = startSdate.substr(0,4);
                            var month = parseInt(e.point.category);
                            if(month < 10){
                                month =  "0"+month;
                            }else{
                                month;
                            }

                            var firstTime = year + "-" + month + "-" + "01";
                            var d=new Date(year,month,0);
                            var lastTime = year + "-" + month + "-"+d.getDate();
                            var year2 = year - 1;
                            var lastStartTime = year2 + "-" + month + "-" + "01";
                            var d=new Date(year2,month,0);
                            var lastEndTime = year2 + "-" + month + "-"+d.getDate();
                            initlLawRatioTable(lastStartTime,lastEndTime);
                            initcLawRatioTable(firstTime,lastTime);
                            //initlawTable(lastStartTime,lastEndTime,firstTime,lastTime);
                        }
                    }
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
                    text: '执法次数(次)'
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

    /******************** 查询执法管理同期对比列表（柱状图，线状图） ********************/
    var lLawRatioTable = $('#lastYearLawRatioTable'),isLoadlLawRatioTable=false;
    var cLawRatioTable = $('#currentYearLawRatioTable'),isLoadcLawRatioTable=false;
    function initlLawRatioTable(firstTime,lastTime) {
        $('#lastYearTableTitle').html(firstTime+"至"+lastTime);
        lLawRatioTable.firstTime = firstTime;
        lLawRatioTable.lastTime = lastTime;
        if(isLoadlLawRatioTable){
            lLawRatioTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:10});
        }else{
            isLoadlLawRatioTable = true;
            lLawRatioTable.bootstrapTable({
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                sidePagination:"server",
                url: rootPath+"/action/S_dispatch_DispatchTask_list.action",
                method:'post',
                pagination:true,
                clickToSelect:true,//单击行时checkbox选中
                queryParams:function(params){
                    var localParams = {};
                    //分页参数
                    localParams.take = params.limit;
                    localParams.skip = params.offset;
                    if(params.offset){
                        localParams.page = params.offset / params.limit + 1;
                    }else{
                        localParams.page = 1;
                    }
                    localParams.pageSize = params.limit;
                    localParams.firstTime = lLawRatioTable.firstTime;
                    localParams.lastTime = lLawRatioTable.lastTime;
                    return localParams;
                },
                columns: [
                    {
                        title:"全选",
                        checkbox: true,
                        align: 'center',
                        radio:false,  //  true 单选， false多选
                        valign: 'middle'
                    },
                    {
                        title: 'ID',
                        field: 'id',
                        align: 'center',
                        valign: 'middle',
                        sortable: false,
                        visible:false
                    },
                    {
                        title: '事件时间',
                        field: 'eventTime',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        formatter:function (value, row, index) {
                            return pageUtils.sub16(value);
                        }
                    },
                    {
                        title: '接电人',
                        field: 'answer',
                        editable: false,
                        sortable: false,
                        align: 'center',
                        visible:false
                    },

                    {
                        title: '企业名称',
                        field: 'enterpriseName',
                        editable: false,
                        sortable: false,
                        align: 'center'
                    },

                    {
                        title: '信息来源',
                        field: 'source',
                        editable: false,
                        sortable: false,
                        align: 'center',
                        formatter:function (value, row, index) {
                            if(1==value){
                                value="12369"
                            }else if (2==value){
                                value="区长热线"
                            }else if (3==value){
                                value="市长热线"
                            }else if (4==value){
                                value="现场监察"
                            }else if (0==value){
                                value="监控中心"
                            }
                            return value;
                        }
                    },
                    {
                        title: '所属网格',
                        field: 'blockName',
                        editable: false,
                        sortable: false,
                        align: 'center'
                    },
                    {
                        field: 'reason',
                        title: '原因',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        formatter:function (value, row, index) {
                            if(1==value){
                                value="异常"
                            }else if(2==value){
                                value="超标"
                            }
                            return value;
                        }
                    },
                    {
                        field: 'senderName',
                        title: '发送人',
                        sortable: false,
                        align: 'center',
                        editable: false
                    },

                    {
                        field: 'monitorCaseId',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        visible:false
                    },
                    {
                        field: 'overValue',
                        title: '超标值',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        visible:false
                    },
                    {
                        field: 'thrValue',
                        title: '超标阀值',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        visible:false
                    },
                    {
                        field: 'sendRemark',
                        title: '备注',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        visible:false
                    }
                ]
            });
            // sometimes footer render error.
            setTimeout(function () {
                lLawRatioTable.bootstrapTable('resetView');
            }, 200);
            $(window).resize(function () {
                // 重新设置表的高度
                lLawRatioTable.bootstrapTable('resetView', {
                    height: pageUtils.getTableHeight()
                });
            });
        }
    }

    function initcLawRatioTable(firstTime,lastTime) {
        $('#currentYearTableTitle').html(firstTime+"至"+lastTime);
        cLawRatioTable.firstTime = firstTime;
        cLawRatioTable.lastTime = lastTime;
        if(isLoadcLawRatioTable){
            cLawRatioTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:10});
        }else{
            isLoadcLawRatioTable = true;
            cLawRatioTable.bootstrapTable({
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                sidePagination:"server",
                url: rootPath+"/action/S_dispatch_DispatchTask_list.action",
                method:'post',
                pagination:true,
                clickToSelect:true,//单击行时checkbox选中
                queryParams:function(params){
                    var localParams = {};
                    //分页参数
                    localParams.take = params.limit;
                    localParams.skip = params.offset;
                    if(params.offset){
                        localParams.page = params.offset / params.limit + 1;
                    }else{
                        localParams.page = 1;
                    }
                    localParams.pageSize = params.limit;
                    localParams.firstTime = cLawRatioTable.firstTime;
                    localParams.lastTime = cLawRatioTable.lastTime;
                    return localParams;
                },
                columns: [
                    {
                        title:"全选",
                        checkbox: true,
                        align: 'center',
                        radio:false,  //  true 单选， false多选
                        valign: 'middle'
                    },
                    {
                        title: 'ID',
                        field: 'id',
                        align: 'center',
                        valign: 'middle',
                        sortable: false,
                        visible:false
                    },
                    {
                        title: '事件时间',
                        field: 'eventTime',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        formatter:function (value, row, index) {
                            return pageUtils.sub16(value);
                        }
                    },
                    {
                        title: '接电人',
                        field: 'answer',
                        editable: false,
                        sortable: false,
                        align: 'center',
                        visible:false
                    },

                    {
                        title: '企业名称',
                        field: 'enterpriseName',
                        editable: false,
                        sortable: false,
                        align: 'center'
                    },

                    {
                        title: '信息来源',
                        field: 'source',
                        editable: false,
                        sortable: false,
                        align: 'center',
                        formatter:function (value, row, index) {
                            if(1==value){
                                value="12369"
                            }else if (2==value){
                                value="区长热线"
                            }else if (3==value){
                                value="市长热线"
                            }else if (4==value){
                                value="现场监察"
                            }else if (0==value){
                                value="监控中心"
                            }
                            return value;
                        }
                    },
                    {
                        title: '所属网格',
                        field: 'blockName',
                        editable: false,
                        sortable: false,
                        align: 'center'
                    },
                    {
                        field: 'reason',
                        title: '原因',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        formatter:function (value, row, index) {
                            if(1==value){
                                value="异常"
                            }else if(2==value){
                                value="超标"
                            }
                            return value;
                        }
                    },
                    {
                        field: 'senderName',
                        title: '发送人',
                        sortable: false,
                        align: 'center',
                        editable: false
                    },

                    {
                        field: 'monitorCaseId',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        visible:false
                    },
                    {
                        field: 'overValue',
                        title: '超标值',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        visible:false
                    },
                    {
                        field: 'thrValue',
                        title: '超标阀值',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        visible:false
                    },
                    {
                        field: 'sendRemark',
                        title: '备注',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        visible:false
                    }
                ]
            });
            // sometimes footer render error.
            setTimeout(function () {
                cLawRatioTable.bootstrapTable('resetView');
            }, 200);
            $(window).resize(function () {
                // 重新设置表的高度
                cLawRatioTable.bootstrapTable('resetView', {
                    height: pageUtils.getTableHeight()
                });
            });
        }
    }

    /******************** 查询执法管理同期对比列表(饼状图) ********************/
    var lawRatioTable2 = $('#lawRatioTable2'),isLoadlawRatioTable2=false;
    function initlawTable2(firstTime,lastTime) {
        lawRatioTable2.firstTime = firstTime;
        lawRatioTable2.lastTime = lastTime;
        if(isLoadlawRatioTable2){
            lawRatioTable2.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:10});
        }else{
            isLoadlawRatioTable2 = true;
            lawRatioTable2.bootstrapTable({
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                sidePagination:"server",
                url: rootPath+"/action/S_dispatch_DispatchTask_list.action",
                method:'post',
                pagination:true,
                clickToSelect:true,//单击行时checkbox选中
                queryParams:function(params){
                    var localParams = {};
                    //分页参数
                    localParams.take = params.limit;
                    localParams.skip = params.offset;
                    if(params.offset){
                        localParams.page = params.offset / params.limit + 1;
                    }else{
                        localParams.page = 1;
                    }
                    localParams.pageSize = params.limit;
                    localParams.firstTime = lawRatioTable2.firstTime;
                    localParams.lastTime = lawRatioTable2.lastTime;
                    return localParams;
                },
                columns: [
                    {
                        title:"全选",
                        checkbox: true,
                        align: 'center',
                        radio:false,  //  true 单选， false多选
                        valign: 'middle'
                    },
                    {
                        title: 'ID',
                        field: 'id',
                        align: 'center',
                        valign: 'middle',
                        sortable: false,
                        visible:false
                    },
                    {
                        title: '事件时间',
                        field: 'eventTime',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        formatter:function (value, row, index) {
                            return pageUtils.sub16(value);
                        }
                    },
                    {
                        title: '接电人',
                        field: 'answer',
                        editable: false,
                        sortable: false,
                        align: 'center',
                        visible:false
                    },

                    {
                        title: '企业名称',
                        field: 'enterpriseName',
                        editable: false,
                        sortable: false,
                        align: 'center'
                    },

                    {
                        title: '信息来源',
                        field: 'source',
                        editable: false,
                        sortable: false,
                        align: 'center',
                        formatter:function (value, row, index) {
                            if(1==value){
                                value="12369"
                            }else if (2==value){
                                value="区长热线"
                            }else if (3==value){
                                value="市长热线"
                            }else if (4==value){
                                value="现场监察"
                            }else if (0==value){
                                value="监控中心"
                            }
                            return value;
                        }
                    },
                    {
                        title: '所属网格',
                        field: 'blockName',
                        editable: false,
                        sortable: false,
                        align: 'center'
                    },
                    {
                        field: 'reason',
                        title: '原因',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        formatter:function (value, row, index) {
                            if(1==value){
                                value="异常"
                            }else if(2==value){
                                value="超标"
                            }
                            return value;
                        }
                    },
                    {
                        field: 'senderName',
                        title: '发送人',
                        sortable: false,
                        align: 'center',
                        editable: false
                    },

                    {
                        field: 'monitorCaseId',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        visible:false
                    },
                    {
                        field: 'overValue',
                        title: '超标值',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        visible:false
                    },
                    {
                        field: 'thrValue',
                        title: '超标阀值',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        visible:false
                    },
                    {
                        field: 'sendRemark',
                        title: '备注',
                        sortable: false,
                        align: 'center',
                        editable: false,
                        visible:false
                    }
                ]
            });
            // sometimes footer render error.
            setTimeout(function () {
                lawRatioTable2.bootstrapTable('resetView');
            }, 200);
            $(window).resize(function () {
                // 重新设置表的高度
                lawRatioTable2.bootstrapTable('resetView', {
                    height: pageUtils.getTableHeight()
                });
            });
        }

    }




});