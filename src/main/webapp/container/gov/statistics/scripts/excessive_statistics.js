/**
 * Created by Administrator on 2016/10/10.
 */
$(function(){
    
    initPage();//执行初始化

    function initPage(){
        getColumnHighChartData();
    }

    //初始化日期组件
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

    /**
     * 柱状图获取数据
     */
    function getColumnHighChartData(){
        // var data = {};
        var categories = ["1月","2月","3月","4月","5月","6月"];
        var series = [];
        
        var gongsi1 = {name: "gongsi1", data: [1,2,3,3,4,3.55]};
        var gongsi2 = {name: "gongsi2", data: [2,3,1.2,4.5,3.7,2.8]};
        var gongsi3 = {name: "gongsi2", data: [4,5,5.2,5.5,5.7,3.8]};
        series.push(gongsi1);
        series.push(gongsi2);
        series.push(gongsi3);
        // $.ajax({
        //     url:rootPath + "/action/S_port_PortStatusHistory_getColumnHighChart.action",
        //     type:"post",
        //     dataType:"json",
        //     success:function (data) {
        //             colMchart(categories, series);
        //     }
        // });

        colMchart(categories, series);
    }

    /**
     * 柱状图highchart
     * @param categories
     * @param series
     */
    function colMchart(categories,series) {
        $('#container').highcharts({
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


