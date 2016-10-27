/**
 * Created by Administrator on 2016/10/13.
 */
//@ sourceURL=excessive_ratio.js
$(function(){

    var highchart = $("#container");

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

    //执行初始化
    initPage();
    var valueChart = '1';

    function initPage(){
        $('#columnBtn').css('background','#0099FF');
        // getColumnRatio();
        search(valueChart,'',startXdate,lastXdate,startSdate,lastSdate);

    }

    var name;
    //查询按钮
    $("#search").bind('click',function(){
        name = $("#s_name").val();
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

        var dateLtr = lastSdate;
        var arr1 = dateLtr.split("-");
        var lastDate1 = new Date(parseInt(arr1[0])-1, parseInt(arr1[1])-1);
        var lastMonth1 = lastDate1.getMonth()+1;
        if (lastMonth1 < 10) {
            lastMonth1 = "0" + lastMonth;
        }
        var lastXdate = lastDate.getFullYear() + "-" + lastMonth1;

        search(valueChart,name,startXdate,lastXdate,startSdate,lastSdate);

    });

    function search(valueChart,name,startXdate,lastXdate,startSdate,lastSdate){
        if(valueChart == '2'){
            getPieRatio(name,startXdate,lastXdate,startSdate,lastSdate)
        }else if(valueChart == '3'){
            getLineRatio(name,startXdate,lastXdate,startSdate,lastSdate);
        }else{
            getColumnRatio(name,startXdate,lastXdate,startSdate,lastSdate);
        }

    }
    

    //柱状图按钮
    $("#columnBtn").bind('click',function(){
        valueChart = $("#columnBtn").attr("data-checked");
        $('#columnBtn').css('background','#0099FF');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#fff');
        search(valueChart,startXdate,lastXdate,startSdate,lastSdate);
    });


    //饼状图按钮
    $("#pieBtn").bind('click',function(){
        valueChart = $("#pieBtn").attr("data-checked");
        $("#pieBtn").css('background','#0099FF');
        $('#columnBtn').css('background-color','#fff');
        $("#lineBtn").css('background','#fff');
        search(valueChart,startXdate,lastXdate,startSdate,lastSdate);

    });

    //线状图按钮
    $("#lineBtn").bind('click',function(){
        valueChart = $("#lineBtn").attr("data-checked");
        $('#columnBtn').css('background','#fff');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#0099FF');
        search(valueChart,startXdate,lastXdate,startSdate,lastSdate);
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
        loadColumnChart(categories, series);
        $.ajax({
            url:rootPath + "/action/S_port_PortStatusHistory_getColumnRatio.action",
            type:'post',
            data:{name:name,startXdate:startXdate,lastXdate:lastXdate,startSdate:startSdate,lastSdate:lastSdate},
            dataType:'json',
            success:function(data){
        
            }
        });
    }
    //饼状图获取后台数据
    function getPieRatio(){
        var categories = ["1月","2月","3月","4月","5月","6月"];
        var series1 = [];
        var series2 = [];

        var tYear = {name: "2015年上半年", data: [1,2,3,3,4,3.55]};
        var yYear = {name: "2016年上半年", data: [2,3,1.2,4.5,3.7,2.8]};
        series1.push(tYear);
        series2.push(yYear);

        loadPieChart1(categories, series1);
        loadPieChart2(categories, series2);

    }

    //线状图获取后台数据
    function getLineRatio(){
        var categories = ["1月","2月","3月","4月","5月","6月"];
        var series = [];

        var tYear = {name: "20165年上半年", data: [1,2,3,3,4,3.55]};
        var yYear = {name: "2016年上半年", data: [2,3,1.2,4.5,3.7,2.8]};
        series.push(tYear);
        series.push(yYear);

        loadLineChart(categories, series);

    }


    //柱状图highchart
    function loadColumnChart(categories,series){
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
            lang:{
                downloadJPEG: "下载JPEG 图片",
                downloadPDF: "下载PDF文档",
                downloadPNG: "下载PNG 图片",
                downloadSVG: "下载SVG 矢量图",
                exportButtonTitle: "导出图片"
            },
            title: {
                text: '2015年上半年与2016年上半年超标统计对比分析'
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
                categories: categories
            },
            yAxis: {
                allowDecimals:false,//是否允许为小数
                min: 0,
                title: {
                    text: '超标次数(次)'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px"></span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} </b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            series:  series
        });

    }

    //饼状图highchart
    function loadPieChart1(categories, series1){
        highchart.highcharts({
            chart: {
                type: 'pie',
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
                text: '2015年上半年与2016年上半年超标统计对比分析'
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
                categories: categories
            },
            yAxis: {
                allowDecimals:false,//是否允许为小数
                min: 0,
                title: {
                    text: '超标次数(次)'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px"></span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} </b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            series:  series1
        });

    }

    function loadPieChart1(categories, series1){
        highchart.highcharts({
            chart: {
                type: 'pie',
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
                text: '2015年上半年与2016年上半年超标统计对比分析'
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
                categories: categories
            },
            yAxis: {
                allowDecimals:false,//是否允许为小数
                min: 0,
                title: {
                    text: '超标次数(次)'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px"></span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} </b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            series:  series1
        });

    }

    function loadPieChart2(categories, series2){
        highchart.highcharts({
            chart: {
                type: 'pie',
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
                text: '2016年上半年与2015年上半年超标统计对比分析'
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
                categories: categories
            },
            yAxis: {
                allowDecimals:false,//是否允许为小数
                min: 0,
                title: {
                    text: '超标次数(次)'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px"></span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} </b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            series:  series2
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
                text: '2016年上半年与2015年上半年超标统计对比分析'
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
                categories: categories
            },
            yAxis: {
                allowDecimals:false,//是否允许为小数
                min: 0,
                title: {
                    text: '超标次数(次)'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px"></span><table>',
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