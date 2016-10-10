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

    initMtchart();
    function initMtchart() {
        $('#container').highcharts({
            chart: {
                type: 'line'
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



});


