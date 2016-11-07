/**
 * Created by Administrator on 2016/10/13.
 */
$(function(){

    var highchart = $("#container");

    var valueChart = '1';

    //获取2016年时间
    var year = new Date().getFullYear();
    var startYdate = year +　'-'+'01' + '-'+'01';
    var lastYdate = year + '-'+ '12' + '-'+ '31';

    //初始化日期组件
    $('.form_datetime').datetimepicker({
        language:   'zh-CN',
        autoclose: 1,
        startView: 3,//月视图
        minView: 3
    });

    initPage();//执行页面初始化

    function initPage(){

        $('#columnBtn').css('background','#0099FF');
        search(valueChart,'',startYdate,lastYdate);
    }

    var name;
    //查询按钮
    $("#search").bind('click',function(){
        name = $("#s_name").val();
        var startYdate = $("#start_createTime").val();
        var lastYdate = $("#end_createTime").val();
        search(valueChart,name,startYdate,lastYdate);
    });

    function search(valueChart,name,startYdate,lastYdate){
        if(valueChart == '2'){
            transportPie(name,startYdate,lastYdate);
        }else if(valueChart == '3'){
            transportLine(name,startYdate,lastYdate);
        }else{
            transportColumn(name,startYdate,lastYdate);
        }
    }

    //柱状图按钮
    $("#columnBtn").bind('click',function(){
        valueChart = $("#columnBtn").attr("data-checked");
        $('#columnBtn').css('background','#0099FF');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#fff');
        search(valueChart,'',startYdate,lastYdate);
    });


    //饼状图按钮
    $("#pieBtn").bind('click',function(){
        valueChart = $("#pieBtn").attr("data-checked");
        $("#pieBtn").css('background','#0099FF');
        $('#columnBtn').css('background','#fff');
        $("#lineBtn").css('background','#fff');
        search(valueChart,'',startYdate,lastYdate);
    });


    //线状图按钮
    $("#lineBtn").bind('click',function(){
        valueChart = $("#lineBtn").attr("data-checked");
        $('#columnBtn').css('background','#fff');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#0099FF');
        // getLineHighData(startYdate,lastYdate);
        search(valueChart,'',startYdate,lastYdate);
    });

    /**
     * 获取柱状图数据
     * @param startYdate
     * @param lastYdate
     */
    function transportColumn(name,startYdate,lastYdate){
        $.ajax({
            url: rootPath + "/action/S_port_TransportEfficient_getPortChart.action",
            type:'post',
            dataType:'json',
            data:{name:name,startYdate:startYdate,lastYdate:lastYdate},
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
                var startMonth= startYdate.substring(0,7);
                var strStartMonth = startMonth.replace('-','');

                var endMonth= lastYdate.substring(0,7);
                var strEndMonth= endMonth.replace('-','');

                var startYear = startYdate.substring(0,4);
                var endYear = lastYdate.substring(0,4);
                if(startYear == endYear){
                    for(var i = strStartMonth; i <= strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                }else{
                    var startTime = startYear + '12';
                    var firstTime = endYear +'01';
                    for(var i=strStartMonth;i<=startTime;i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                    for(var i=firstTime; i<=strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
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

                var series1 = {name: '传输有效率', color: 'rgb(124, 181, 236)', data: preValue};
                series.push(series1);
                colMchart(preMonth,series,startYdate,lastYdate);
            }

        });

        // var categories = ['1月', '2月', '3月', '4月', '5月','6月','7月','8月','9月','10月','11月','12月'];
        // var series = [];
        //
        // var chuanshul = {name: '传输有效率', color: 'rgb(124, 181, 236)', data: [98.8, 97.8, 96.5, 95.7, 98.7,99,100,100,98,99,100,99]};
        // series.push(chuanshul);
        // colMchart(categories,series);
    }

    /**
     * 获取饼状图状数据
     */
    function transportPie(name,startYdate,lastYdate){
        $.ajax({
            url: rootPath + "/action/S_port_TransportEfficient_getPortChart.action",
            type: 'post',
            data: {name:name,startYdate: startYdate, lastYdate: lastYdate},
            dataType: 'json',
            success:function(data){
                var categories = data['x'];
                var series1 = data['y'];
                var series = [{
                    name:"传输有效率",
                    data:[]
                }];
                var preMonth = [];//定义查询月份的数组
                var preValue = [];//定义对应月份为0的一组数据
                var startMonth= startYdate.substring(0,7);
                var strStartMonth = startMonth.replace('-','');

                var endMonth= lastYdate.substring(0,7);
                var strEndMonth= endMonth.replace('-','');

                var startYear = startYdate.substring(0,4);
                var endYear = lastYdate.substring(0,4);
                if(startYear == endYear){
                    for(var i = strStartMonth; i <= strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                }else{
                    var startTime = startYear + '12';
                    var firstTime = endYear +'01';
                    for(var i=strStartMonth;i<=startTime;i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                    for(var i=firstTime; i<=strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
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
                pieMchart(series,startYdate,lastYdate);
            }
        });
    }

    /**
     * 获取线状图数据
     */
    function transportLine(name,startYdate,lastYdate){
        $.ajax({
            url: rootPath + "/action/S_port_TransportEfficient_getPortChart.action",
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
                var startMonth= startYdate.substring(0,7);
                var strStartMonth = startMonth.replace('-','');

                var endMonth= lastYdate.substring(0,7);
                var strEndMonth= endMonth.replace('-','');

                var startYear = startYdate.substring(0,4);
                var endYear = lastYdate.substring(0,4);
                if(startYear == endYear){
                    for(var i = strStartMonth; i <= strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                }else{
                    var startTime = startYear + '12';
                    var firstTime = endYear +'01';
                    for(var i=strStartMonth;i<=startTime;i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
                    for(var i=firstTime; i<=strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue.push(0);
                    }
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


                var series1 = {name: "传输有效率", data:preValue};
                series.push(series1);
                lineMchart(preMonth,series,startYdate,lastYdate);

            }
        })
    }

    /**
     * 柱状图highChart
     */
    function colMchart(preMonth,series,startYdate,lastYdate){
        if(startYdate == '2016-01-01'){
            titleSub= '2016年各企业传输有效率平均值统计'
        }else{
            titleSub = startYdate+'月至'+lastYdate+'月各企业传输有效率平均值统计';
        }

        highchart.highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: titleSub
            },
            // subtitle: {
            //     text: 'Source: WorldClimate.com'
            // },
            xAxis: {
                categories: preMonth,
                crosshair: true,
                title:{
                    text:'月份'
                }
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
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}月</small><table>',
                pointFormat: '<tr><td style="color: {series.color}">{series.name}: </td>' +
                '<td style="text-align: right"><b>{point.y} %</b></td></tr>',
                footerFormat: '</table>',
                valueDecimals: 0
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

    //饼状图highChart
    function pieMchart(series,startYdate,lastYdate){
        if(startYdate == '2016-01-01'){
            titleSub= '2016年各企业传输有效率平均值统计'
        }else{
            titleSub = startYdate+'月至'+lastYdate+'月各企业传输有效率平均值统计';
        }
        highchart.highcharts({
            chart: {
                type: 'pie'
            },
            title: {
                text: titleSub
            },
            tooltip: {
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}月</small><table>',
                pointFormat: '<tr><td style="color: {series.color}">{series.name}: </td>' +
                '<td style="text-align: right"><b>{point.y} %</b></td></tr>',
                footerFormat: '</table>',
                valueDecimals: 0
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

    //线状图highChart
    function lineMchart(preMonth,series,startYdate,lastYdate){
        if(startYdate == '2016-01-01'){
            titleSub= '2016年各企业传输有效率平均值统计'
        }else{
            titleSub = startYdate+'月至'+lastYdate+'月各企业传输有效率平均值统计';
        }
        highchart.highcharts({
            chart: {
                type: 'line'
            },
            title: {
                text: titleSub
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
            tooltip: {
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}月</small><table>',
                pointFormat: '<tr><td style="color: {series.color}">{series.name}: </td>' +
                '<td style="text-align: right"><b>{point.y} %</b></td></tr>',
                footerFormat: '</table>',
                valueDecimals: 0
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    },
                }
            },
            legend: {
                enabled: true
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