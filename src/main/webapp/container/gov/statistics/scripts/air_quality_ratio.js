/**
 * Created by Administrator on 2016/10/17.
 */
$(function(){

    var highchart = $("#container");
    var highchart1 = $("#container1");
    var highchart2 = $("#container2");

    var valueChart = '1';
    
    //初始化日期组件
    $('.form_datetime').datetimepicker({
        language:   'zh-CN',
        autoclose: 1,
        startView: 3,//月视图
        minView: 3
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


    initPage();
    function initPage(){
        $('#columnBtn').css('background','#0099FF');
        // getColumnRatio();
        search(valueChart,startXdate,lastXdate,startSdate,lastSdate,'');

    }

    //查询按钮
    $("#search").bind('click',function(){
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
        var airType = $("#airType").val();
        search(valueChart,startXdate,lastXdate,startSdate,lastSdate,airType);

    });

    function search(valueChart,startXdate,lastXdate,startSdate,lastSdate,airType){
        if(valueChart == '2'){
            highchart.hide();
            highchart1.show();
            highchart2.show();
            getAirPieRatio1(startXdate,lastXdate,startSdate,lastSdate,airType);
            getAirPieRatio2(startXdate,lastXdate,startSdate,lastSdate,airType);
        }else if(valueChart == '3'){
            highchart1.hide();
            highchart2.hide();
            highchart.show();
            getAirLineRatio(startXdate,lastXdate,startSdate,lastSdate,airType);
        }else{
            highchart1.hide();
            highchart2.hide();
            highchart.show();
            getAirColumnRatio(startXdate,lastXdate,startSdate,lastSdate,airType);
        }

    }

    //柱状图按钮
    $("#columnBtn").bind('click',function(){
        valueChart = $("#columnBtn").attr("data-checked");
        $('#columnBtn').css('background','#0099FF');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#fff');
        search(valueChart,startXdate,lastXdate,startSdate,lastSdate,'');
    });

    //饼状图按钮
    $("#pieBtn").bind('click',function(){
        valueChart = $("#pieBtn").attr("data-checked");
        $("#pieBtn").css('background','#0099FF');
        $('#columnBtn').css('background-color','#fff');
        $("#lineBtn").css('background','#fff');
        search(valueChart,startXdate,lastXdate,startSdate,lastSdate,'');

    });

    //线状图按钮
    $("#lineBtn").bind('click',function(){
        valueChart = $("#lineBtn").attr("data-checked");
        $('#columnBtn').css('background','#fff');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#0099FF');
        search(valueChart,startXdate,lastXdate,startSdate,lastSdate,'');
    });

    //柱状图获取后台数据
    function getAirColumnRatio(startXdate,lastXdate,startSdate,lastSdate,airType){
        // var categories = ["优","良","轻度污染","中度污染","重度污染","严重污染"];
        // var series = [];
        //
        // var gongye = {name: "空气质量", data: [9,2,0,2,2,2]};
        // var gong = {name: "空气", data: [9,2,0,2,2,2]};
        // series.push(gongye);
        // series.push(gong);

        // colMchart(categories, series);
        $.ajax({
            url: rootPath + "/action/S_port_AirQuality_getColumnAirRatio.action",
            dataType:'json',
            type:'post',
            data:{startXdate:startXdate,lastXdate:lastXdate,startSdate:startSdate,lastSdate:lastSdate,airType:airType},
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y1;
                var ylist = [];
                var list2 = data.y2;
                var zlist = [];
                if(list && list.length>0){
                    for(var i = 0; i<list.length;i++){
                        ylist.push(parseInt(list[i]));
                    }
                }
                if(list2 && list2.length>0){
                    for(var i = 0;i<list2.length;i++){
                        zlist.push(parseInt(list2[i]));
                    }
                }
                var series1 = {name: "同期空气质量",color: 'rgb(124, 181, 236)', data:ylist};
                var series2 = {name: "现期空气质量",color: '#FF8800', data:zlist};
                series.push(series1);
                series.push(series2);
                colMchart(categories,series);

            }
        });

    }

    //饼状图1获取后台数据
    function getAirPieRatio1(startXdate,lastXdate,startSdate,lastSdate,airType){
        $.ajax({
            url: rootPath + "/action/S_port_AirQuality_getColumnAirRatio.action",
            dataType:'json',
            type:'post',
            data:{startXdate:startXdate,lastXdate:lastXdate,startSdate:startSdate,lastSdate:lastSdate,airType:airType},
            success:function(data){
                var categories = data['x'];
                var series1 = data['y1'];
                var series = [{
                    name:"空气质量天数:(天)",
                    data:[]
                }];
                if(series1 && series1.length > 0){
                    for (var i = 0; i < series1.length; i++) {
                        series[0].data.push({name:categories[i],y: parseInt(series1[i])});
                    }
                }
                loadPieChart1(series);
            }
        });


    }


    //饼状图2获取后台数据
    function getAirPieRatio2(startXdate,lastXdate,startSdate,lastSdate,airType){
        $.ajax({
            url: rootPath + "/action/S_port_AirQuality_getColumnAirRatio.action",
            dataType:'json',
            type:'post',
            data:{startXdate:startXdate,lastXdate:lastXdate,startSdate:startSdate,lastSdate:lastSdate,airType:airType},
            success:function(data){
                var categories = data['x'];
                var series1 = data['y2'];
                var series = [{
                    name:"空气质量天数:(天)",
                    data:[]
                }];
                if(series1 && series1.length > 0){
                    for (var i = 0; i < series1.length; i++) {

                        series[0].data.push({name:categories[i],y: parseInt(series1[i])});
                    }
                }
                loadPieChart2(series);
            }
        });

    }


    //线状图获取后台数据
    function getAirLineRatio(startXdate,lastXdate,startSdate,lastSdate,airType){
        $.ajax({
            url: rootPath + "/action/S_port_AirQuality_getColumnAirRatio.action",
            dataType:'json',
            type:'post',
            data:{startXdate:startXdate,lastXdate:lastXdate,startSdate:startSdate,lastSdate:lastSdate,airType:airType},
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y1;
                var ylist = [];
                var list2 = data.y2;
                var zlist = [];
                if(list && list.length>0){
                    for(var i = 0; i<list.length;i++){
                        ylist.push(parseInt(list[i]));
                    }
                }
                if(list2 && list2.length>0){
                    for(var i = 0;i<list2.length;i++){
                        zlist.push(parseInt(list2[i]));
                    }
                }
                var series1 = {name: "同期空气质量",color: 'rgb(124, 181, 236)', data:ylist};
                var series2 = {name: "现期空气质量",color: '#FF8800', data:zlist};
                series.push(series1);
                series.push(series2);
                loadLineChart(categories,series);

            }
        });
    }


    //柱状图highchart
    function colMchart(categories, series){
        highchart.highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '2015上半年与2016上半年空气质量(AQI)统计分析'
            },
            xAxis: {
                categories: categories,
                title:{
                    text:'空气质量级别'
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
                    pointPadding: 0.1,
                    borderWidth: 0
                }
            },
            legend: {
                enabled: true
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}等级</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} 天</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            lang: {
                printChart:"打印图表",
                downloadJPEG: "下载JPEG 图片" ,
                downloadPDF: "下载PDF文档"  ,
                downloadPNG: "下载PNG 图片"  ,
                downloadSVG: "下载SVG 矢量图" ,
                exportButtonTitle: "导出图片"
            },
            series: series
        });

    }

    //饼状图1highchart
    function loadPieChart1(series){
        highchart1.highcharts({
            chart: {
                type: 'pie'
            },
            title: {
                text: '2015年上半年空气质量统计'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}等级</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    },
                    showInLegend: true
                }
            },
            tooltip: {
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}等级</small><table>',
                pointFormat: '<tr><td style="color: {series.color}">{series.name}: </td>' +
                '<td style="text-align: right"><b>{point.y} 天</b></td></tr>',
                footerFormat: '</table>',
                valueDecimals: 0
            },
            lang:{
                downloadJPEG: "下载JPEG 图片",
                downloadPDF: "下载PDF文档",
                downloadPNG: "下载PNG 图片",
                downloadSVG: "下载SVG 矢量图",
                exportButtonTitle: "导出图片"
            },
            series:  series
        });

    }

    //饼状图2highchart
    function loadPieChart2(series){
        highchart2.highcharts({
            chart: {
                type: 'pie'
            },
            title: {
                text: '2016年上半年空气质量统计'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}等级</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    },
                    showInLegend: true
                }
            },
            tooltip: {
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}等级</small><table>',
                pointFormat: '<tr><td style="color: {series.color}">{series.name}: </td>' +
                '<td style="text-align: right"><b>{point.y} 天</b></td></tr>',
                footerFormat: '</table>',
                valueDecimals: 0
            },
            lang:{
                downloadJPEG: "下载JPEG 图片",
                downloadPDF: "下载PDF文档",
                downloadPNG: "下载PNG 图片",
                downloadSVG: "下载SVG 矢量图",
                exportButtonTitle: "导出图片"
            },
            series:  series
        });

    }

    //线状图highchart
    function loadLineChart(categories, series){
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
                text: '2015上半年与2016上半年空气质量(AQI)统计分析'
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
                    text: '空气质量级别'
                }
            },
            yAxis: {
                allowDecimals:false,//是否允许为小数
                min: 0,
                title: {
                    text: '空气质量天数(天)'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}等级</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} 天</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            series:  series
        });

    }

});

