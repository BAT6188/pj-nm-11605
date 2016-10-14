/**
 * Created by Administrator on 2016/10/10.
 */
$(function(){

    var columnHighchart = $("#container");

    
    initPage();//执行初始化

    function initPage(){

        $('#datetimepicker').datetimepicker({
            language:   'zh-CN',
            autoclose: 1,
            minView: 2
        });
        $('#datetimepicker2').datetimepicker({
            language:   'zh-CN',
            autoclose: 1,
            minView: 2
        });

        var year = new Date().getFullYear();
        var startYdate = year +　'-'+'01' + '-'+'01';
        var lastYdate = year + '-'+ '12' + '-'+ '31';
        getColumnHighChartData(startYdate,lastYdate);
    }

    //查询按钮
    $("#serachModel").bind('click',function(){
        var name = $("#s_name").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        search(name,startTime,endTime);
    });

    /**
     * 柱状图获取数据
     */
    function getColumnHighChartData(startYdate,lastYdate){
        $.ajax({
            url:rootPath + "/action/S_port_PortStatusHistory_getColumnHighChart.action",
            type:"post",
            data:{startYdate:startYdate,lastYdate:lastYdate},
            dataType:"json",
            success:function (data) {
                var categories = data.x;
                var series = [];
                var list = data.y;
                var ylist = [];
                for (var i=0; i<list.length; i++) {
                    ylist.push(parseInt(list[i]));
                }
                var series1 = {name: "超标次数", data:ylist};
                series.push(series1);
                colMchart(categories,series);
            }
        });
    }

    /**
     * 柱状图highchart
     * @param categories
     * @param series
     */
    function colMchart(categories,series) {
        columnHighchart.highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '2016年上半年超标统计'
            },
            xAxis: {
                categories: categories
            },
            yAxis: {
                allowDecimals:false,//是否允许为小数
                min: 0,
                title: {
                    text: '数量'
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
            plotOptions: {
                column: {
                    pointPadding: 0.1,
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

    /**
     * 饼状图highchart
     */
    function pieMchart() {
        $('#container').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: '2016年上半年超标统计'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                data: [
                    ['1月', 45.0],
                    ['2月', 26.8],
                    {
                        name: '3月',
                        y: 12.8,
                        sliced: true,
                        selected: true
                    },
                    ['4月', 8.5],
                    ['5月', 6.2],
                    ['6月', 0.7]
                ]
            }]
        });
    }

    /**
     * 折线图highchart
     */
    function lineMchart(){
        $('#container').highcharts({
            chart: {
                type: 'line'
            },
            title: {
                text: '2016年上半年超标统计'
            },
            subtitle: {
                text: 'Source: WorldClimate.com'
            },
            xAxis: {
                categories: ['1月', '2月', '3月', '4月', '5月', '6月']
            },
            yAxis: {
                title: {
                    text: 'Temperature (°C)'
                }
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: false
                }
            },
            series: [{
                data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
            }]

        });
    }


});


