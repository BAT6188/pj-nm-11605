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
    
    //获取2016上半年时间
    var year = new Date().getFullYear();
    var startYdate = year +　'-'+'01' + '-'+'01';
    var lastYdate = year + '-'+ '06' + '-'+ '30';

    intoPage();//执行初始化

    function intoPage(){

        $('#columnBtn').css('background','#0099FF');
        getColumnHighChartData(startYdate,lastYdate);

    }

    //柱状图按钮
    $("#columnBtn").bind('click',function(){
        $('#columnBtn').css('background','#0099FF');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#fff');
        getColumnHighChartData(startYdate,lastYdate);
    });



    //饼状图按钮
    $("#pieBtn").bind('click',function(){
        $("#pieBtn").css('background','#0099FF');
        $('#columnBtn').css('background','#fff');
        $("#lineBtn").css('background','#fff');
        getPieHighChartData(startYdate,lastYdate);

    });



    //线状图按钮
    $("#lineBtn").bind('click',function(){
        $('#columnBtn').css('background','#fff');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#0099FF');
        getLineHighData(startYdate,lastYdate);
    });

    //柱状图获取后台数据
    function getColumnHighChartData(startYdate,lastYdate){
        // var categories = ["1月","2月","3月","4月","5月","6月"];
        // var series = [];
        //
        // var gongye = {name: "月份", data: [1,2,3,3,4,3.55]};
        // series.push(gongye);
        //
        // colMchart(categories, series);
        $.ajax({
            url: rootPath + "/action/S_dispatch_DispathTask_getColumnHighChart.action",
            type:'post',
            data:{startYdate:startYdate,lastYdate:lastYdate},
            dataType:'json',
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y;
                var ylist = [];
                if(list && list.length>0){
                    for (var i=0; i<list.length; i++) {
                        ylist.push(parseInt(list[i]));
                    }
                }
                var preMonth = [];//定义查询月份的数组
                var preValue = [];//定义对应月份为0的一组数据
                var startMonth= startYdate.substring(5,7);
                if(startMonth < 10){
                    var sMonth = startMonth.substring(1)
                }else{
                    sMonth = startMonth;
                }
                var endMonth= lastYdate.substring(5,7);
                if(endMonth <10){
                    var lasMonth = endMonth.substring(1);
                }else{
                    lasMonth = endMonth
                }

                for(var i = sMonth; i <= lasMonth; i++){
                    preMonth.push(i);
                    preValue.push(0);
                }
                console.log(preMonth);
                console.log(preValue);
                var month = categories;//后台取出的2组数据
                var value = ylist;
                if(month && month.length >0){
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

                var series1 = {name: "月份", data:preValue};
                series.push(series1);
                colMchart(preMonth,series);
            }
        });
    }

    //饼状图获取后台数据
    function getPieHighChartData(startYdate,lastYdate){
        $.ajax({
            url: rootPath + "/action/S_dispatch_DispathTask_getColumnHighChart.action",
            type: 'post',
            data: {startYdate: startYdate, lastYdate: lastYdate},
            dataType: 'json',
            success:function(data){
                var categories = data['x'];
                var series1 = data['y'];
                var series = [{
                    name:"超标次数:(次)",
                    data:[]
                }];
                var preMonth = [];//定义查询月份的数组
                var preValue = [];//定义对应月份为0的一组数据
                var startMonth= startYdate.substring(5,7);
                if(startMonth < 10){
                    var sMonth = startMonth.substring(1)
                }else{
                    sMonth = startMonth;
                }
                var endMonth= lastYdate.substring(5,7);
                if(endMonth <10){
                    var lasMonth = endMonth.substring(1);
                }else{
                    lasMonth = endMonth
                }

                for(var i = sMonth; i <= lasMonth; i++){
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
                pieMchart(series);
            }
        });

    }
    
    //线状图获取后台数据
    function getLineHighData(startYdate,lastYdate){
        $.ajax({
            url: rootPath + "/action/S_dispatch_DispathTask_getColumnHighChart.action",
            type:"post",
            data:{name:name,startYdate:startYdate,lastYdate:lastYdate},
            dataType:"json",
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y;
                var ylist = [];
                if(list && list.length>0){
                    for (var i=0; i<list.length; i++) {
                        ylist.push(parseInt(list[i]));
                    }
                }

                var preMonth = [];
                var preValue = [];
                var startMonth= startYdate.substring(5,7);
                var endMonth= lastYdate.substring(5,7);
                for(var i = startMonth; i <= endMonth; i++){
                    preMonth.push(i);
                    preValue.push(0);
                }
                console.log(preMonth);
                console.log(preValue);
                var month = categories;
                var values = ylist;
                if(month && month.length>0){
                    for(var i=0;i< month.length;i++){
                        var m = month[i];
                        for(var j=0; j<preMonth.length;j++){
                            if(m==preMonth[j]){
                                preValue[j] = values[i];
                            }
                        }
                    }
                }
                console.log(preMonth);
                console.log(preValue);


                var series1 = {name: "月份", data:preValue};
                series.push(series1);
                lineMchart(preMonth,series);

            }
        })
    }


    //柱状图highchart
    function colMchart(preMonth, series){
        highchart.highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '2016上半年执法统计'
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
                pointFormat: '执法次数: <b>{point.y:.1f} 次</b>'
            },
            series: series
        });

    }

    //饼状图highChart
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
    

    //线状图highChart
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
                    dataLabels: {
                        enabled: true
                    },
                    enableMouseTracking: false
                }
            },
            legend: {
                enabled: true
            },
            tooltip: {
                pointFormat: '超标次数: <b>{point.y:.1f} 次</b>'
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
