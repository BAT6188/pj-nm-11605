/**
 * Created by Administrator on 2016/10/13.
 */
$(function(){

    var highchart = $("#container");

    //初始化日期组件
    $('.form_datetime').datetimepicker({
        language:   'zh-CN',
        autoclose: 1,
        startView: 3,//月视图
        minView: 3
    });

    //执行初始化
    initPage();

    function initPage(){
        $('#columnBtn').css('background','#0099FF');
        getColumnRatio();

    }

    //柱状图按钮
    $("#columnBtn").bind('click',function(){
        $('#columnBtn').css('background','#0099FF');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#fff');
        getColumnRatio();
    });


    //饼状图按钮
    $("#pieBtn").bind('click',function(){
        $("#pieBtn").css('background','#0099FF');
        $('#columnBtn').css('background-color','#fff');
        $("#lineBtn").css('background','#fff');
        getPieRatio();

    });

    //线状图按钮
    $("#lineBtn").bind('click',function(){
        $('#columnBtn').css('background','#fff');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#0099FF');
        getLineRatio();
    });

    function getColumnRatio(){
        var categories = ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"];
        var series = [];

        var tYear = {name: "已环评", data: [1,2,3,3,4,3.55,2,3,1.2,4.5,3.7,2.8]};
        var yYear = {name: "已验收", data: [2,3,1.2,4.5,3.7,2.8,1,2,3,3,4,3.55]};
        series.push(tYear);
        series.push(yYear);

        loadColumnChart(categories, series);
        // $.ajax({
        //     url:rootPath + "/action/S_port_PortStatusHistory_getColumnRatio.action",
        //     type:'post',
        //     data:{},
        //     dataType:'json',
        //     success:function(){
        //
        //     }
        // });
    }

    function getPieRatio(){
        var categories = ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"];
        var series1 = [];
        var series2 = [];

        var tYear = {name: "已环评", data: [1,2,3,3,4,3.55,2,3,1.2,4.5,3.7,2.8]};
        var yYear = {name: "已验收", data: [2,3,1.2,4.5,3.7,2.8,1,2,3,3,4,3.55]};
        series1.push(tYear);
        series2.push(yYear);

        loadPieChart1(categories, series1);
        loadPieChart2(categories, series2);

    }


    function getLineRatio(){
        var categories = ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"];
        var series = [];

        var tYear = {name: "已环评", data: [1,2,3,3,4,3.55,2,3,1.2,4.5,3.7,2.8]};
        var yYear = {name: "已验收", data: [2,3,1.2,4.5,3.7,2.8,1,2,3,3,4,3.55]};
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
                text: '2016各企业环评验收情况统计'
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
                text: '2016各企业环评验收情况统计'
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
                text: '2016各企业环评验收情况统计'
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
