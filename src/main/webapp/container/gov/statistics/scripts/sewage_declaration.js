/**
 * Created by Administrator on 2016/10/12.
 */
$(function(){
    var highchart = $("#container");

    var year = new Date().getFullYear();
    var startYdate = year +　'-'+'01' + '-'+'01';
    var lastYdate = year + '-'+ '06' + '-'+ '30';
    
    //初始化日期组件
    $('.form_datetime').datetimepicker({
        language:   'zh-CN',
        autoclose: 1,
        startView: 3,//月视图
        minView: 3
    });
    
    initPage();//页面初始化

    function initPage(){
        $('#columnBtn').css('background','#0099FF');
        sewageColumnCahrt(startYdate,lastYdate);
    }

    //柱状图按钮
    $("#columnBtn").bind('click',function(){
        $('#columnBtn').css('background','#0099FF');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#fff');
        sewageColumnCahrt(startYdate,lastYdate);
    });

    //饼状图按钮
    $("#pieBtn").bind('click',function(){
        $("#pieBtn").css('background','#0099FF');
        $('#columnBtn').css('background-color','#fff');
        $("#lineBtn").css('background','#fff');
        sewagePieChart(startYdate,lastYdate);

    });




    //线状图按钮
    $("#lineBtn").bind('click',function(){
        $('#columnBtn').css('background','#fff');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#0099FF');
        sewageLineCahrt(startYdate,lastYdate);

    });

    //柱状图获取后台数据
    function sewageColumnCahrt(startYdate,lastYdate){
        $.ajax({
            url:rootPath + "/action/S_exelaw_PollutantPayment_getSewageColumn.action",
            type:'post',
            dataType:'json',
            data:{startYdate:startYdate,lastYdate:lastYdate},
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y1;
                var ylist = [];
                var list2 = data.y2;
                var ylist2 = [];
                if(list && list.length>0){
                    for (var i=0; i<list.length; i++) {
                        ylist.push(parseInt(list[i]));
                    }
                }
                if(list2 && list.length>0){
                    for (var i=0; i<list2.length; i++) {
                        ylist2.push(parseInt(list2[i]));
                    }
                }
                var preMonth = [];//定义查询月份的数组
                var preValue1 = [];//定义对应月份为0的一组数据
                var preValue2 = [];//定义对应月份为0的一组数据
                var startMonth= startYdate.substring(5,7);
                var endMonth= lastYdate.substring(5,7);
                for(var i = startMonth; i <= endMonth; i++){
                    preMonth.push(i);
                    preValue1.push(0);
                    preValue2.push(0);
                }
                var month = categories;//后台取出的2组数据
                var value = ylist;
                var arr_value = ylist2;
                if(month && month.length>0){
                    for(var i = 0; i < month.length;i++){
                        var m = month[i];
                        for (var j = 0; j < preMonth.length; j++){
                            if (m == preMonth[j]) {
                                preValue1[j] = value[i];
                            }
                        }
                        for (var k = 0; k < preMonth.length; k++){
                            if (m == preMonth[k]) {
                                preValue2[k] = arr_value[i];
                            }
                        }

                    }
                }
                

                var series1 = {name: "已缴费",color: 'rgb(124, 181, 236)', data:preValue1};
                var series2 = {name: "未缴费",color:'#FF8800', data:preValue2};
                series.push(series1);
                series.push(series2);
                colMchart(preMonth,series);
            }
        });
        // var categories = ['1月', '2月', '3月', '4月', '5月','6月'];
        // var series = [];
        //
        // var yijiaofei = {name: '已缴费企业', color: 'rgb(124, 181, 236)', data: [40, 36, 20, 40, 30,43]};
        // var weijiaofei = {name: '未缴费企业', color:'#FF8800', data: [3, 7, 14, 3, 13,2]};
        // series.push(yijiaofei);
        // series.push(weijiaofei);
        // colMchart(categories,series);

    }
    
    
    //饼状图获取后台数据
    function sewagePieChart(startYdate,lastYdate){
        $.ajax({
            url: rootPath + "/action/S_exelaw_PollutantPayment_getSewagePie.action",
            type:'post',
            dataType:'json',
            data:{startYdate:startYdate,lastYdate:lastYdate},
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

                var preMonth = [];//定义查询月份的数组
                var preValue = [];//定义对应月份为0的一组数据
                var startMonth= startYdate.substring(5,7);
                var endMonth= lastYdate.substring(5,7);
                for(var i = startMonth; i <= endMonth; i++){
                    preMonth.push(i);
                    preValue.push(0);
                }
                console.log(preMonth);
                console.log(preValue);
                var month = categories;//后台取出的2组数据
                var value = series1;
                if(month && month.length>0){
                    for(var i = 0; i < month.length;i++){
                        var m = month[i];
                        for (var j = 0; j < preMonth.length; j++){
                            if (m == preMonth[j]) {
                                preValue[j] = value[i];
                            }
                        }

                    }
                }
                console.log(preMonth);
                console.log(preValue);

                for (var i = 0; i < preValue.length; i++) {

                    series[0].data.push({name:preMonth[i],y: parseInt(preValue[i])});
                }
                pieMchart(series)
            }
        });
    }

    
    
    
    //线状图获取后台数据
    function sewageLineCahrt(startYdate,lastYdate){
        $.ajax({
            url:rootPath + "/action/S_exelaw_PollutantPayment_getSewageColumn.action",
            type:'post',
            dataType:'json',
            data:{startYdate:startYdate,lastYdate:lastYdate},
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y1;
                var ylist = [];
                var list2 = data.y2;
                var ylist2 = [];
                if(list && list.length>0){
                    for (var i=0; i<list.length; i++) {
                        ylist.push(parseInt(list[i]));
                    }
                }
                if(list2 && list.length>0){
                    for (var i=0; i<list2.length; i++) {
                        ylist2.push(parseInt(list2[i]));
                    }
                }
                var preMonth = [];//定义查询月份的数组
                var preValue1 = [];//定义对应月份为0的一组数据
                var preValue2 = [];//定义对应月份为0的一组数据
                var startMonth= startYdate.substring(5,7);
                var endMonth= lastYdate.substring(5,7);
                for(var i = startMonth; i <= endMonth; i++){
                    preMonth.push(i);
                    preValue1.push(0);
                    preValue2.push(0);
                }
                var month = categories;//后台取出的2组数据
                var value = ylist;
                var arr_value = ylist2;
                if(month && month.length>0){
                    for(var i = 0; i < month.length;i++){
                        var m = month[i];
                        for (var j = 0; j < preMonth.length; j++){
                            if (m == preMonth[j]) {
                                preValue1[j] = value[i];
                            }
                        }
                        for (var k = 0; k < preMonth.length; k++){
                            if (m == preMonth[k]) {
                                preValue2[k] = arr_value[i];
                            }
                        }

                    }
                }


                var series1 = {name: "已缴费",color: 'rgb(124, 181, 236)', data:preValue1};
                var series2 = {name: "未缴费",color:'#FF8800', data:preValue2};
                series.push(series1);
                series.push(series2);
                lineMchart(preMonth,series);


            }
        });


    }
    
    
    
    //柱状图highchart
    function colMchart(preMonth,series){
        highchart.highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '2016年上半年企业排污申报费用统计'
            },
            xAxis: {
                categories: preMonth,
                title:{
                    text:'月份'
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: '企业数量'
                    // align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: '家'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
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
    function lineMchart(preMonth,series){
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
                categories: preMonth,
                title:{
                    text:'月份'
                }
            },
            yAxis: {
                title: {
                    text: '数量'
                }
            },
            plotOptions: {
                line: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.y:.1f} 家',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    },
                    showInLegend: true
                }
            },
            legend: {
                enabled: true
            },
            tooltip: {
                pointFormat: '企业数量: <b>{point.y:.1f} 家</b>'
            },
            lang: {
                printChart:"打印图表",
                downloadJPEG: "下载JPEG 图片" ,
                downloadPDF: "下载PDF文档" ,
                downloadPNG: "下载PNG 图片"  ,
                downloadSVG: "下载SVG 矢量图" ,
                exportButtonTitle: "导出图片"
            },
            series: series

        });
    }




    
});