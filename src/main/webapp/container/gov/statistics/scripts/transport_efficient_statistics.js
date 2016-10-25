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

    initPage();//执行页面初始化

    function initPage(){
        transportChart();
    }

    function transportChart(){
        // $.ajax({
        //
        // });

        var categories = ['1月', '2月', '3月', '4月', '5月','6月','7月','8月','9月','10月','11月','12月'];
        var series = [];

        var chuanshul = {name: '传输有效率', color: 'rgb(124, 181, 236)', data: [98.8, 97.8, 96.5, 95.7, 98.7,99,100,100,98,99,100,99]};
        series.push(chuanshul);
        colMchart(categories,series);

    }


    function colMchart(categories,series){
        highchart.highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '2016年各企业传输有效率平均值统计'
            },
            // subtitle: {
            //     text: 'Source: WorldClimate.com'
            // },
            xAxis: {
                categories: categories,
                crosshair: true
            },
            yAxis: {
                labels: {
                    formatter: function() { //格式化标签名称
                        return this.value + '%';
                    },
                    style: {
                        color: '#89A54E' //设置标签颜色
                    }
                },
                min: 0,
                title: {
                    text: 'Rainfall (mm)'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} %</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
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