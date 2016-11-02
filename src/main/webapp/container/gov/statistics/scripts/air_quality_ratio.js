/**
 * Created by Administrator on 2016/10/17.
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

    $('.form_datetimes').datetimepicker({
        language:   'zh-CN',
        autoclose: 1,
        startView: 4,//月视图
        minView: 4
    });

    initPage();
    function initPage(){
        getAirColumn();

    }


    function getAirColumn(){
        var categories = ["优","良","轻度污染","中度污染","重度污染","严重污染"];
        var series = [];

        var gongye = {name: "空气质量", data: [9,2,0,2,2,2]};
        var gong = {name: "空气", data: [9,2,0,2,2,2]};
        series.push(gongye);
        series.push(gong);

        colMchart(categories, series);
        // $.ajax({
        //     url: rootPath + "/action/S_port_AirQuality_getColumnHighChart.action",
        //     dataType:'json',
        //     type:'post',
        //     data:{},
        //     success:function(data){
        //         var categories = data.x;
        //         var series = [];
        //         var list = data.y;
        //         var ylist = [];
        //         for(var i = 0; i<list.length;i++){
        //             ylist.push(parseInt(list[i]));
        //         }
        //         var series1 = {name: "空气质量", data:ylist};
        //         series.push(series1);
        //         colMchart(categories,series);
        //
        //     }
        // });

    }

    //柱状图highchart
    function colMchart(categories, series){
        highchart.highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '2016上半年空气质量(AQI)统计'
            },
            xAxis: {
                categories: categories,
                title:{
                    text:'污染级别'
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
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}</small><table>',
                pointFormat: '<tr>' +
                '<td style="text-align: right"><b>:{point.y} 次</b></td></tr>',
                footerFormat: '</table>',
                valueDecimals: 0
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
    
    




});

