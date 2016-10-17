/**
 * Created by Administrator on 2016/10/10.
 */
$(function(){

    var highchart = $("#container");
    var year = new Date().getFullYear();
    var startYdate = year +　'-'+'01' + '-'+'01';
    var lastYdate = year + '-'+ '12' + '-'+ '31';



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

        $('#columnBtn').css('background','#0099FF');
        getColumnHighChartData(startYdate,lastYdate);
    }

    //查询按钮
    $("#serachModel").bind('click',function(){
        var name = $("#s_name").val();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        search(name,startTime,endTime);
    });

    //柱状图按钮
    $("#columnBtn").bind('click',function(){
        $('#columnBtn').css('background','#0099FF');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#fff');
        getColumnHighChartData('',startYdate,lastYdate);
    });

    $("#lineBtn").bind('click',function(){
        $('#columnBtn').css('background','#fff');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#0099FF');
        getLineHighData('',startYdate,lastYdate);
    });

    //饼状图按钮
    $("#pieBtn").bind('click',function(){
        $("#pieBtn").css('background','#0099FF');
        $('#columnBtn').css('background-color','#fff');
        $("#lineBtn").css('background','#fff');
        getPieHighChartData('',startYdate,lastYdate);

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

    //获取饼状图数据
    function getPieHighChartData(name,startYdate,lastYdate){
        $.ajax({
            url: rootPath + "/action/S_port_PortStatusHistory_getColumnHighChart.action",
            type:"post",
            data:{name:name,startYdate:startYdate,lastYdate:lastYdate},
            dataType:"json",
            success:function(data){
                var categories = data['x'];
                var series1 = data['y'];
                var series = [{
                    name:"超标次数:(次)",
                    data:[]
                }];
                if(!series1){
                    return " ";
                }
                for (var i = 0; i < series1.length; i++) {

                    series[0].data.push({name:categories[i],y: parseInt(series1[i])});
                }
                pieMchart(series);
            }
        });
    }

    //获取线状图数据
    function getLineHighData(name,startYdate,lastYdate){
        $.ajax({
            url: rootPath + "/action/S_port_PortStatusHistory_getColumnHighChart.action",
            type:"post",
            data:{name:name,startYdate:startYdate,lastYdate:lastYdate},
            dataType:"json",
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y;
                var ylist = [];
                for (var i=0; i<list.length; i++) {
                    ylist.push(parseInt(list[i]));
                }
                var series1 = {name: "超标次数", data:ylist};
                series.push(series1);
                lineMchart(categories,series);

            }
        })
    }

    /**
     * 柱状图highchart
     * @param categories
     * @param series
     */
    function colMchart(categories,series) {
        highchart.highcharts({
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
    function pieMchart(series){
        highchart.highcharts({
            chart: {
                type: 'pie'
            },
            title: {
                text: '2016年上半年超标统计'
            },
            // tooltip: {
            //     pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            // },
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
     * 折线图highchart
     */
    function lineMchart(categories,series){
        highchart.highcharts({
            chart: {
                type: 'line'
            },
            title: {
                text: '2016年上半年超标统计'
            },
            // subtitle: {
            //     text: 'Source: WorldClimate.com'
            // },
            xAxis: {
                categories: categories
            },
            yAxis: {
                title: {
                    text: '数量'
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


