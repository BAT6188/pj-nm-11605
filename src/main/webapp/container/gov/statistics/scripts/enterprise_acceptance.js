/**
 * Created by Administrator on 2016/10/13.
 */
$(function(){

    var highchart = $("#container");
    var highchart1 = $("#container1");
    var highchart2 = $("#container2");

    //初始化日期组件
    $('.form_datetime').datetimepicker({
        language:   'zh-CN',
        autoclose: 1,
        startView: 3,//月视图
        minView: 3
    });

    var year = new Date().getFullYear();
    var startdate = year + '-'+ '01' + '-'+ '01';
    var lastdate = year + '-'+ '12' + '-'+ '31';


    //执行初始化
    initPage();
    var valueChart = '1';

    // var enterpriseId = $("#enterpriseId").val();
    // console.log(enterpriseId);

    function initPage(){
        $('#columnBtn').css('background','#0099FF');
        // getColumnRatio(valueChart,startdate,lastdate,'');
        search(valueChart,startdate,lastdate,'');
    }
    
    //查询按钮
    $("#search").bind('click',function(){
        var startdate = $("#start_createTime").val();
        var lastdate = $("#end_createTime").val();
        var enterpriseId = $("#enterpriseId").val();
        console.log(enterpriseId);
        search(valueChart,startdate,lastdate,enterpriseId);
        
    });
    
    function search(valueChart,startdate,lastdate,enterpriseId){
        if(valueChart == '2'){
            highchart.hide();
            highchart1.show();
            highchart2.show();
            getPieRatio1(startdate,lastdate,enterpriseId);
            getPieRatio2(startdate,lastdate,enterpriseId);
        }else if(valueChart == '3'){
            highchart1.hide();
            highchart2.hide();
            highchart.show();
            getLineRatio(startdate,lastdate,enterpriseId);
        }else{
            highchart1.hide();
            highchart2.hide();
            highchart.show();
            getColumnRatio(startdate,lastdate,enterpriseId);
        }
        
        
    }

    //柱状图按钮
    $("#columnBtn").bind('click',function(){
        valueChart = $("#columnBtn").attr("data-checked");
        $('#columnBtn').css('background','#0099FF');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#fff');
        // getColumnRatio(startdate,lastdate,'');
        search(valueChart,startdate,lastdate,'');
    });


    //饼状图按钮
    $("#pieBtn").bind('click',function(){
        valueChart = $("#pieBtn").attr("data-checked");
        $("#pieBtn").css('background','#0099FF');
        $('#columnBtn').css('background-color','#fff');
        $("#lineBtn").css('background','#fff');

        search(valueChart,startdate,lastdate,'');
        // getPieRatio(startdate,lastdate,'');

    });

    //线状图按钮
    $("#lineBtn").bind('click',function(){
        valueChart = $("#lineBtn").attr("data-checked");
        $('#columnBtn').css('background','#fff');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#0099FF');
        search(valueChart,startdate,lastdate,'');
        
        // getLineRatio(startdate,lastdate,'');
    });

    //柱状图获取后台数据
    function getColumnRatio(startdate,lastdate,enterpriseId){
        // var categories = ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"];
        // var series = [];
        //
        // var tYear = {name: "已环评", data: [1,2,3,3,4,3.55,2,3,1.2,4.5,3.7,2.8]};
        // var yYear = {name: "已验收", data: [2,3,1.2,4.5,3.7,2.8,1,2,3,3,4,3.55]};
        // series.push(tYear);
        // series.push(yYear);
        //
        // loadColumnChart(categories, series);
        $.ajax({
            url:rootPath + "/action/S_composite_ProjectEIA_getColRatio.action",
            type:'post',
            data:{startdate:startdate,lastdate:lastdate,enterpriseId:enterpriseId},
            dataType:'json',
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y1;
                var ylist = [];
                var list2 = data.y2;
                var ylist2 = [];
                if (list && list.length > 0) {
                    for (var i = 0; i < list.length; i++) {
                        ylist.push(parseInt(list[i]));
                    }
                }
                if (list2 && list.length > 0) {
                    for (var i = 0; i < list2.length; i++) {
                        ylist2.push(parseInt(list2[i]));
                    }
                }
                var preMonth = [];//定义查询月份的数组
                var preValue1 = [];//定义对应月份为0的一组数据
                var preValue2 = [];//定义对应月份为0的一组数据

                var startMonth= startdate.substring(0,7);
                var strStartMonth = startMonth.replace('-','');

                var endMonth= lastdate.substring(0,7);
                var strEndMonth= endMonth.replace('-','');

                var startYear = startdate.substring(0,4);
                var endYear = lastdate.substring(0,4);
                if(startYear == endYear){
                    for(var i = strStartMonth; i <= strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue1.push(0);
                        preValue2.push(0);
                    }
                }else{
                    var startTime = startYear + '12';
                    var firstTime = endYear +'01';
                    for(var i=strStartMonth;i<=startTime;i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue1.push(0);
                        preValue2.push(0);
                    }
                    for(var i=firstTime; i<=strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue1.push(0);
                        preValue2.push(0);
                    }
                }
                var month = categories;//后台取出的2组数据
                var value = ylist;
                var arr_value = ylist2;
                if (month && month.length > 0) {
                    for (var i = 0; i < month.length; i++) {
                        var m = month[i];
                        for (var j = 0; j < preMonth.length; j++) {
                            if (m == preMonth[j]) {
                                preValue1[j] = value[i];
                            }
                        }
                        for (var k = 0; k < preMonth.length; k++) {
                            if (m == preMonth[k]) {
                                preValue2[k] = arr_value[i];
                            }
                        }

                    }
                }

                var series1 = {name: "已环评", color: 'rgb(124, 181, 236)', data: preValue1};
                var series2 = {name: "已验收", color: '#FF8800', data: preValue2};
                series.push(series1);
                series.push(series2);
                loadColumnChart(preMonth, series,startdate,lastdate);
        
            }
        });
    }
    //(环评)饼状图获取后台数据
    function getPieRatio1(startdate,lastdate,enterpriseId){
        // var categories = ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"];
        // var series1 = [];
        // var series2 = [];
        //
        // var tYear = {name: "已环评", data: [1,2,3,3,4,3.55,2,3,1.2,4.5,3.7,2.8]};
        // var yYear = {name: "已验收", data: [2,3,1.2,4.5,3.7,2.8,1,2,3,3,4,3.55]};
        // series1.push(tYear);
        // series2.push(yYear);
        //
        // loadPieChart1(categories, series1);
        // loadPieChart2(categories, series2);
        $.ajax({
            url:rootPath + "/action/S_composite_ProjectEIA_getColRatio.action",
            type:'post',
            data:{startdate:startdate,lastdate:lastdate,enterpriseId:enterpriseId},
            dataType:'json',
            success:function(data){
                var categories = data['x'];
                var series1 = data['y1'];
                var series = [{
                    name:"已环评企业:(家)",
                    data:[]
                }];

                var preMonth = [];//定义查询月份的数组
                var preValue = [];//定义对应月份为0的一组数据
                
                var startMonth= startdate.substring(0,7);
                var strStartMonth = startMonth.replace('-','');

                var endMonth= lastdate.substring(0,7);
                var strEndMonth= endMonth.replace('-','');

                var startYear = startdate.substring(0,4);
                var endYear = lastdate.substring(0,4);
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
                
                for (var i = 0; i < preValue.length; i++) {

                    series[0].data.push({name:preMonth[i],y: parseInt(preValue[i])});
                }
                loadPieChart1(series,startdate,lastdate);

            }


        });

    }

    //(验收)饼状图获取后台数据
    function getPieRatio2(startdate,lastdate,enterpriseId){
        $.ajax({
            url:rootPath + "/action/S_composite_ProjectEIA_getColRatio.action",
            type:'post',
            data:{startdate:startdate,lastdate:lastdate,enterpriseId:enterpriseId},
            dataType:'json',
            success:function(data){
                var categories = data['x'];
                var series1 = data['y2'];
                var series = [{
                    name:"已验收企业:(家)",
                    data:[]
                }];

                var preMonth = [];//定义查询月份的数组
                var preValue = [];//定义对应月份为0的一组数据

                var startMonth= startdate.substring(0,7);
                var strStartMonth = startMonth.replace('-','');

                var endMonth= lastdate.substring(0,7);
                var strEndMonth= endMonth.replace('-','');

                var startYear = startdate.substring(0,4);
                var endYear = lastdate.substring(0,4);
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
                loadPieChart2(series,startdate,lastdate);
            }
        });

    }


    //折线图highchart获取后台数据
    function getLineRatio(startdate,lastdate,enterpriseId){
        $.ajax({
            url:rootPath + "/action/S_composite_ProjectEIA_getColRatio.action",
            type:'post',
            data:{startdate:startdate,lastdate:lastdate,enterpriseId:enterpriseId},
            dataType:'json',
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y1;
                var ylist = [];
                var list2 = data.y2;
                var ylist2 = [];
                if (list && list.length > 0) {
                    for (var i = 0; i < list.length; i++) {
                        ylist.push(parseInt(list[i]));
                    }
                }
                if (list2 && list.length > 0) {
                    for (var i = 0; i < list2.length; i++) {
                        ylist2.push(parseInt(list2[i]));
                    }
                }
                var preMonth = [];//定义查询月份的数组
                var preValue1 = [];//定义对应月份为0的一组数据
                var preValue2 = [];//定义对应月份为0的一组数据

                var startMonth= startdate.substring(0,7);
                var strStartMonth = startMonth.replace('-','');

                var endMonth= lastdate.substring(0,7);
                var strEndMonth= endMonth.replace('-','');

                var startYear = startdate.substring(0,4);
                var endYear = lastdate.substring(0,4);
                if(startYear == endYear){
                    for(var i = strStartMonth; i <= strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue1.push(0);
                        preValue2.push(0);
                    }
                }else{
                    var startTime = startYear + '12';
                    var firstTime = endYear +'01';
                    for(var i=strStartMonth;i<=startTime;i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue1.push(0);
                        preValue2.push(0);
                    }
                    for(var i=firstTime; i<=strEndMonth; i++){
                        i = i + "";
                        var k = i.substr(0, 4)+"-"+i.substr(4,2);
                        preMonth.push(k);
                        preValue1.push(0);
                        preValue2.push(0);
                    }
                }

                var month = categories;//后台取出的2组数据
                var value = ylist;
                var arr_value = ylist2;
                if (month && month.length > 0) {
                    for (var i = 0; i < month.length; i++) {
                        var m = month[i];
                        for (var j = 0; j < preMonth.length; j++) {
                            if (m == preMonth[j]) {
                                preValue1[j] = value[i];
                            }
                        }
                        for (var k = 0; k < preMonth.length; k++) {
                            if (m == preMonth[k]) {
                                preValue2[k] = arr_value[i];
                            }
                        }

                    }
                }

                var series1 = {name: "已环评", color: 'rgb(124, 181, 236)', data: preValue1};
                var series2 = {name: "已验收", color: '#FF8800', data: preValue2};
                series.push(series1);
                series.push(series2);
                loadLineChart(preMonth, series,startdate,lastdate);

            }
        });
    }


    //柱状图highchart
    function loadColumnChart(preMonth, series,startdate,lastdate){
        if(startdate == '2016-01-01'){
            titleSub= '2016年各企业环评验收情况统计'
        }else{
            titleSub = startdate+'月至'+lastdate+'月各企业环评验收情况统计';
        }
        highchart.highcharts({
            chart: {
                type: 'column',
                margin: 75,
                options3d: {
//                        enabled: true,
                    enabled: false,
                    alpha: 10,
                    beta: 25,
                    depth: 70
                }
            },
            exporting: {
                enabled:false
            },
            title: {
                text: titleSub
            },
            // subtitle: {
            //     text: 'Notice the difference between a 0 value and a null point'
            // },
            plotOptions: {
                column: {
                    depth: 25
                }
            },
            xAxis: {
                categories: preMonth,
                title: {
                    text: '月份'
                }
            },
            yAxis: {
                allowDecimals:false,//是否允许为小数
                min: 0,
                title: {
                    text: '已环评验收企业数量'
                }
            },
            tooltip: {
                headerFormat: '<small>{point.key}月</small><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} 家</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            series:  series
        });

    }

    //饼状图1highchart
    function loadPieChart1(series,startdate,lastdate){
        if(startdate == '2016-01-01'){
            titleSub= '2016年各企业已环评情况统计'
        }else{
            titleSub = startdate+'月至'+lastdate+'月各企业已环评情况统计';
        }
        highchart1.highcharts({
            chart: {
                type: 'pie'
            },
            title: {
                text: titleSub
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
            tooltip: {
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}月</small><table>',
                pointFormat: '<tr><td style="color: {series.color}">{series.name}: </td>' +
                '<td style="text-align: right"><b>{point.y} 家</b></td></tr>',
                footerFormat: '</table>',
                valueDecimals: 0
            },
            exporting: {
                enabled:false
            },
            series:  series
        });

    }

    //饼状图2
    function loadPieChart2(series,startdate,lastdate){
        if(startdate == '2016-01-01'){
            titleSub= '2016年各企业已验收情况统计'
        }else{
            titleSub = startdate+'月至'+lastdate+'月各企业已验收情况统计';
        }
        highchart2.highcharts({
            chart: {
                type: 'pie'
            },
            title: {
                text: titleSub
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
            tooltip: {
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}月</small><table>',
                pointFormat: '<tr><td style="color: {series.color}">{series.name}: </td>' +
                '<td style="text-align: right"><b>{point.y} 家</b></td></tr>',
                footerFormat: '</table>',
                valueDecimals: 0
            },
            exporting: {
                enabled:false
            },
            series:  series
        });

    }


    //线状图highchart
    function loadLineChart(categories, series,startdate,lastdate){
        if(startdate == '2016-01-01'){
            titleSub= '2016年各企业环评验收情况统计'
        }else{
            titleSub = startdate+'月至'+lastdate+'月各企业环评验收情况统计';
        }

        $('#container').highcharts({
            chart: {
                type: 'line',
                margin: 75,
                options3d: {
                    enabled: false,
                    alpha: 10,
                    beta: 25,
                    depth: 70
                }
            },
            exporting: {
                enabled:false
            },
            title: {
                text: titleSub
            },
            // subtitle: {
            //     text: 'Notice the difference between a 0 value and a null point'
            // },
            plotOptions: {
                column: {
                    depth: 25
                }
            },
            xAxis: {
                categories: categories,
                title: {
                    text: '月份'
                }
            },
            yAxis: {
                allowDecimals:false,//是否允许为小数
                min: 0,
                title: {
                    text: '已环评验收企业数量'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px"></span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} 家</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            series:  series
        });

    }




});
