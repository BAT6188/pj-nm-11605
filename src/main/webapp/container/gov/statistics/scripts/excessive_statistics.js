/**
 * Created by Administrator on 2016/10/10.
 */
$(function(){
    // $.ajax({
    //     url:rootPath + "/action/S_port_PortStatusHistory_.action",
    //     type:"post",
    //     dataType:"json",
    //     success:function (excessiveValue) {
    //         initMtchart(seriesData);
    //     }
    // });

    colMchart();
    function colMchart() {
        $('#container').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '2016年上半年超标统计'
            },
            xAxis: {
                categories: ['1月', '2月', '3月', '4月', '5月', '6月']
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
            // series: seriesData
            series: [{
                data: [5, 3, 4, 7, 2,6]
            }]
        });
    }

    // pieMchart();
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

    // lineMchart();
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


