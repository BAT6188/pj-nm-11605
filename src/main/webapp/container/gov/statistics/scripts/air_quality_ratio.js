/**
 * Created by Administrator on 2016/10/17.
 */
$(function(){

    var highchart = $("#container");
    var highchart1 = $("#container1");
    var highchart2 = $("#container2");

    var valueChart = '1';
    
    //初始化日期组件
    $('.form_datetime1').datetimepicker({
        language:   'zh-CN',
        autoclose: 1,
        startView: 3,//月视图
        minView: 3
    });
    /**
     * 设定日期在同一年内
     */
    $("#start_createTime").bind("change",function () {
        var startTime = $(this).val();
        if (!startTime) {
            return;
        }
        var year = startTime.substr(0, 4);
        var years = parseInt(year) +1;
        var month = parseInt(startTime.substr(5, 2));
        var endTimeStartMonth = month;
        if (month == 12) {
            endTimeStartMonth = month;
        }else{
            endTimeStartMonth = month+3
        }
        $('.form_datetime2').datetimepicker('setStartDate', year+"-"+endTimeStartMonth);
        $('.form_datetime2').datetimepicker('setEndDate', years+"-"+"01");
    });

    $("#startTime").bind('change',function(){
        var nowYear = $("#startTime").val();
        $("#endtime").val(nowYear-1);

    });
    
    $('.form_datetime2').datetimepicker({
        language:   'zh-CN',
        startView: 3,//月视图
        minView: 3,
        weekStart: 1,
        autoclose: true,
        todayBtn: 'linked'
    });

    $('.form_datetimes').datetimepicker({
        language:   'zh-CN',
        autoclose: 1,
        startView: 4,//月视图
        minView: 4
    });


    //默认获取上半年时间，和去年上半年时间
    var year = new Date().getFullYear();
    var startXdate = year-1 + '-'+ '01' + '-'+ '01';
    var lastXdate = year-1 + '-'+ '06' + '-'+ '30';
    var startSdate = year +　'-'+'01' + '-'+'01';
    var lastSdate = year + '-'+ '06' + '-'+ '30';


    initPage();
    function initPage(){
        $('#columnBtn').css('background','#0099FF');
        // getColumnRatio();
        search(valueChart,startXdate,lastXdate,startSdate,lastSdate,'');

    }

    //查询按钮
    $("#search").bind('click',function(){
        var startSdate = $("#start_createTime").val() + "-" + "01";
        var lastSdate = $("#end_createTime").val() + "-" + "31";
        var dateStr = startSdate;
        var arr = dateStr.split("-");
        var lastDate = new Date(parseInt(arr[0])-1, parseInt(arr[1])-1);
        var lastMonth = lastDate.getMonth()+1;
        if (lastMonth < 10) {
            lastMonth = "0" + lastMonth;
        }
        var startXdate = lastDate.getFullYear() + "-" + lastMonth +"-"+ "01";

        var dateLtr2 = lastSdate;
        var arr2 = dateLtr2.split("-");
        var lastDate2 = new Date(parseInt(arr2[0])-1, parseInt(arr2[1])-1);
        var lastMonth2 = lastDate2.getMonth()+1;
        if (lastMonth2 < 10) {
            lastMonth2 = "0" + lastMonth2;
        }
        var lastXdate = lastDate2.getFullYear() + "-" + lastMonth2+"-"+31;
        var airType = $("#airType").val();
        search(valueChart,startXdate,lastXdate,startSdate,lastSdate,airType);

    });

    function search(valueChart,startXdate,lastXdate,startSdate,lastSdate,airType){
        if(valueChart == '2'){
            highchart.hide();
            highchart1.show();
            highchart2.show();
            getAirPieRatio1(startXdate,lastXdate,startSdate,lastSdate,airType);
            getAirPieRatio2(startXdate,lastXdate,startSdate,lastSdate,airType);
        }else if(valueChart == '3'){
            highchart1.hide();
            highchart2.hide();
            highchart.show();
            getAirLineRatio(startXdate,lastXdate,startSdate,lastSdate,airType);
        }else{
            highchart1.hide();
            highchart2.hide();
            highchart.show();
            getAirColumnRatio(startXdate,lastXdate,startSdate,lastSdate,airType);
        }

    }

    //同期对比上半年按钮
    $("#sbYear").bind('click',function(){
        var year = $("#startTime").val();
        var startXdate = year-1 + '-'+ '01' + '-'+ '01';
        var lastXdate = year-1 + '-'+ '06' + '-'+ '30';
        var startSdate = year +　'-'+'01' + '-'+'01';
        var lastSdate = year + '-'+ '06' + '-'+ '30';
        search(valueChart,startXdate,lastXdate,startSdate,lastSdate,'');
    });

    //同期对比下半年按钮查询
    $("#xbYear").bind('click',function(){
        var year = $("#startTime").val();
        var startXdate = year-1 + '-'+ '07' + '-'+ '01';
        var lastXdate = year-1 + '-'+ '12' + '-'+ '31';
        var startSdate = year +　'-'+'07' + '-'+'01';
        var lastSdate = year + '-'+ '12' + '-'+ '31';
        search(valueChart,startXdate,lastXdate,startSdate,lastSdate,'');
    });


    //柱状图按钮
    $("#columnBtn").bind('click',function(){
        valueChart = $("#columnBtn").attr("data-checked");
        $('#columnBtn').css('background','#0099FF');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#fff');
        search(valueChart,startXdate,lastXdate,startSdate,lastSdate,'');
    });

    //饼状图按钮
    $("#pieBtn").bind('click',function(){
        valueChart = $("#pieBtn").attr("data-checked");
        $("#pieBtn").css('background','#0099FF');
        $('#columnBtn').css('background-color','#fff');
        $("#lineBtn").css('background','#fff');
        search(valueChart,startXdate,lastXdate,startSdate,lastSdate,'');

    });

    //线状图按钮
    $("#lineBtn").bind('click',function(){
        valueChart = $("#lineBtn").attr("data-checked");
        $('#columnBtn').css('background','#fff');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#0099FF');
        search(valueChart,startXdate,lastXdate,startSdate,lastSdate,'');
    });

    //柱状图获取后台数据
    function getAirColumnRatio(startXdate,lastXdate,startSdate,lastSdate,airType){
        // var categories = ["优","良","轻度污染","中度污染","重度污染","严重污染"];
        // var series = [];
        //
        // var gongye = {name: "空气质量", data: [9,2,0,2,2,2]};
        // var gong = {name: "空气", data: [9,2,0,2,2,2]};
        // series.push(gongye);
        // series.push(gong);

        // colMchart(categories, series);
        $.ajax({
            url: rootPath + "/action/S_port_AirQuality_getColumnAirRatio.action",
            dataType:'json',
            type:'post',
            data:{startXdate:startXdate,lastXdate:lastXdate,startSdate:startSdate,lastSdate:lastSdate,airType:airType},
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y1;
                var ylist = [];
                var list2 = data.y2;
                var zlist = [];
                if(list && list.length>0){
                    for(var i = 0; i<list.length;i++){
                        ylist.push(parseInt(list[i]));
                    }
                }
                if(list2 && list2.length>0){
                    for(var i = 0;i<list2.length;i++){
                        zlist.push(parseInt(list2[i]));
                    }
                }
                var series1 = {name: "同期空气质量",color: 'rgb(124, 181, 236)', data:ylist};
                var series2 = {name: "现期空气质量",color: '#FF8800', data:zlist};
                series.push(series1);
                series.push(series2);
                colMchart(categories,series,startXdate,lastXdate,startSdate,lastSdate);

            }
        });

    }

    //饼状图1获取后台数据
    function getAirPieRatio1(startXdate,lastXdate,startSdate,lastSdate,airType){
        $.ajax({
            url: rootPath + "/action/S_port_AirQuality_getColumnAirRatio.action",
            dataType:'json',
            type:'post',
            data:{startXdate:startXdate,lastXdate:lastXdate,startSdate:startSdate,lastSdate:lastSdate,airType:airType},
            success:function(data){
                var categories = data['x'];
                var series1 = data['y1'];
                var series = [{
                    name:"空气质量天数:(天)",
                    data:[]
                }];
                if(series1 && series1.length > 0){
                    for (var i = 0; i < series1.length; i++) {
                        series[0].data.push({name:categories[i],y: parseInt(series1[i])});
                    }
                }
                loadPieChart1(series,startXdate,lastXdate);
            }
        });


    }


    //饼状图2获取后台数据
    function getAirPieRatio2(startXdate,lastXdate,startSdate,lastSdate,airType){
        $.ajax({
            url: rootPath + "/action/S_port_AirQuality_getColumnAirRatio.action",
            dataType:'json',
            type:'post',
            data:{startXdate:startXdate,lastXdate:lastXdate,startSdate:startSdate,lastSdate:lastSdate,airType:airType},
            success:function(data){
                var categories = data['x'];
                var series1 = data['y2'];
                var series = [{
                    name:"空气质量天数:(天)",
                    data:[]
                }];
                if(series1 && series1.length > 0){
                    for (var i = 0; i < series1.length; i++) {

                        series[0].data.push({name:categories[i],y: parseInt(series1[i])});
                    }
                }
                loadPieChart2(series,startSdate,lastSdate);
            }
        });

    }


    //线状图获取后台数据
    function getAirLineRatio(startXdate,lastXdate,startSdate,lastSdate,airType){
        $.ajax({
            url: rootPath + "/action/S_port_AirQuality_getColumnAirRatio.action",
            dataType:'json',
            type:'post',
            data:{startXdate:startXdate,lastXdate:lastXdate,startSdate:startSdate,lastSdate:lastSdate,airType:airType},
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y1;
                var ylist = [];
                var list2 = data.y2;
                var zlist = [];
                if(list && list.length>0){
                    for(var i = 0; i<list.length;i++){
                        ylist.push(parseInt(list[i]));
                    }
                }
                if(list2 && list2.length>0){
                    for(var i = 0;i<list2.length;i++){
                        zlist.push(parseInt(list2[i]));
                    }
                }
                var series1 = {name: "同期空气质量",color: 'rgb(124, 181, 236)', data:ylist};
                var series2 = {name: "现期空气质量",color: '#FF8800', data:zlist};
                series.push(series1);
                series.push(series2);
                loadLineChart(categories,series,startXdate,lastXdate,startSdate,lastSdate);

            }
        });
    }

    var minValue;
    var maxValue;

    //柱状图highchart
    function colMchart(categories, series,startXdate,lastXdate,startSdate,lastSdate){
        if(startSdate == '2016-01-01'){
            titleSub = '2015上半年与2016上半年空气质量(AQI)统计对比分析'
        }else{
            titleSub = startSdate+'月至'+lastSdate+'月同期超标统计对比分析';
        }
        highchart.highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: titleSub
            },
            xAxis: {
                categories: categories,
                title:{
                    text:'空气质量级别'
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
                    dataLabels: {
                        enabled: true
                    }
                },
                series : {
                    cursor: 'pointer',
                    events : {
                        click: function(e) {
                            console.log(e.point.category);
                            $("#airRatioListForm").modal('show');
                            var strValue = e.point.category;
                            if(strValue == '优'){
                                minValue = 1;
                                maxValue = 50
                            }else if(strValue == '良'){
                                minValue = 50;
                                maxValue = 100

                            }else if(strValue == '轻微污染'){
                                minValue = 100;
                                maxValue = 150

                            }else if(strValue == '轻度污染'){
                                minValue = 150;
                                maxValue = 200

                            }else if(strValue == '中度污染'){
                                minValue = 201;
                                maxValue = 300

                            }else if(strValue == '重度污染'){
                                minValue = 300;
                                maxValue = 1000
                            }

                            initTable(startXdate,lastXdate,startSdate,lastSdate,minValue,maxValue);
                        }
                    }
                }
            },
            legend: {
                enabled: true
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}等级</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} 天</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            exporting: {
                enabled:false
            },
            series: series
        });

    }

    //饼状图1highchart
    function loadPieChart1(series,startXdate,lastXdate){
        if(startXdate == '2015-01-01'){
            titleSub = '2015年上半年空气质量统计对比分析'
        }else{
            titleSub = startXdate+'月至'+lastXdate+'月空气质量统计对比分析';
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
                        format: '<b>{point.name}等级</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    },
                    showInLegend: true
                },
                series : {
                    cursor: 'pointer',
                    events : {
                        click: function(e) {
                            console.log(e.point.name);
                            $("#airRatioListForm2").modal('show');
                            var strValue = e.point.name;
                            if(strValue == '优'){
                                minValue = 1;
                                maxValue = 50
                            }else if(strValue == '良'){
                                minValue = 50;
                                maxValue = 100

                            }else if(strValue == '轻微污染'){
                                minValue = 100;
                                maxValue = 150

                            }else if(strValue == '轻度污染'){
                                minValue = 150;
                                maxValue = 200

                            }else if(strValue == '中度污染'){
                                minValue = 201;
                                maxValue = 300

                            }else if(strValue == '重度污染'){
                                minValue = 300;
                                maxValue = 1000
                            }

                            initTable2(startXdate,lastXdate,minValue,maxValue);
                        }
                    }
                }
            },
            tooltip: {
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}等级</small><table>',
                pointFormat: '<tr><td style="color: {series.color}">{series.name}: </td>' +
                '<td style="text-align: right"><b>{point.y} 天</b></td></tr>',
                footerFormat: '</table>',
                valueDecimals: 0
            },
            exporting: {
                enabled:false
            },
            series:  series
        });

    }

    //饼状图2highchart
    function loadPieChart2(series,startSdate,lastSdate){
        if(startSdate == '2016-01-01'){
            titleSub = '2016年空气质量统计对比分析'
        }else{
            titleSub = startSdate+'月至'+lastSdate+'月同期空气质量统计对比分析';
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
                        format: '<b>{point.name}等级</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    },
                    showInLegend: true
                },
                series : {
                    cursor: 'pointer',
                    events : {
                        click: function(e) {
                            console.log(e.point.name);
                            $("#airRatioListForm2").modal('show');
                            var strValue = e.point.name;
                            if(strValue == '优'){
                                minValue = 1;
                                maxValue = 50
                            }else if(strValue == '良'){
                                minValue = 50;
                                maxValue = 100

                            }else if(strValue == '轻微污染'){
                                minValue = 100;
                                maxValue = 150

                            }else if(strValue == '轻度污染'){
                                minValue = 150;
                                maxValue = 200

                            }else if(strValue == '中度污染'){
                                minValue = 201;
                                maxValue = 300

                            }else if(strValue == '重度污染'){
                                minValue = 300;
                                maxValue = 1000
                            }

                            initTable2(startSdate,lastSdate,minValue,maxValue);
                        }
                    }
                }
            },
            tooltip: {
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}等级</small><table>',
                pointFormat: '<tr><td style="color: {series.color}">{series.name}: </td>' +
                '<td style="text-align: right"><b>{point.y} 天</b></td></tr>',
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
    function loadLineChart(categories, series,startXdate,lastXdate,startSdate,lastSdate){
        if(startSdate == '2016-01-01'){
            titleSub = '2015上半年与2016上半年空气质量(AQI)统计对比分析'
        }else{
            titleSub = startSdate+'月至'+lastSdate+'月同期空气质量(AQI)统计对比分析';
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
                line: {
                    dataLabels: {
                        enabled: true
                    }
                },
                series : {
                    cursor: 'pointer',
                    events : {
                        click: function(e) {
                            console.log(e.point.category);
                            $("#airRatioListForm").modal('show');
                            var strValue = e.point.category;
                            if(strValue == '优'){
                                minValue = 0;
                                maxValue = 50
                            }else if(strValue == '良'){
                                minValue = 50;
                                maxValue = 100

                            }else if(strValue == '轻微污染'){
                                minValue = 100;
                                maxValue = 150

                            }else if(strValue == '轻度污染'){
                                minValue = 150;
                                maxValue = 200

                            }else if(strValue == '中度污染'){
                                minValue = 200;
                                maxValue = 300

                            }else if(strValue == '重度污染'){
                                minValue = 300;
                                maxValue = 1000
                            }

                            initTable(startXdate,lastXdate,startSdate,lastSdate,minValue,maxValue);
                        }
                    }
                }
            },
            xAxis: {
                categories: categories,
                title: {
                    text: '空气质量级别'
                }
            },
            yAxis: {
                allowDecimals:false,//是否允许为小数
                min: 0,
                title: {
                    text: '空气质量天数(天)'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}等级</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} 天</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            series:  series
        });

    }


    /********************  查询空气质量同期对比列表 (线状图)(柱状图) ********************/
    var airRatioTable = $('#airRatioTable');
    function initTable(startXdate,lastXdate,startSdate,lastSdate,minValue,maxValue) {
        airRatioTable.bootstrapTable('destroy');
        airRatioTable.bootstrapTable({
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            sidePagination:"server",
            url: rootPath+"/action/S_port_AirQuality_airRatiolist.action?startXdate="+startXdate+"&lastXdate="+lastXdate+"&startSdate="+startSdate+"&lastSdate="+lastSdate+"&minValue="+minValue+"&maxValue="+maxValue,
            method:'post',
            pagination:true,
            clickToSelect:true,//单击行时checkbox选中
            queryParams:pageUtils.localParams,
            columns: [
                {
                    title:"全选",
                    checkbox: true,
                    align: 'center',
                    radio:false,  //  true 单选， false多选
                    valign: 'middle'
                }, {
                    title: 'ID',
                    field: 'id',
                    align: 'center',
                    valign: 'middle',
                    sortable: false,
                    visible: false
                },
                {
                    title: '更新时间',
                    field: 'rec_Time',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '空气AQI值',
                    field: 'airValue',
                    editable: false,
                    sortable: false,
                    align: 'center'
                }

            ]
        });
        // sometimes footer render error.
        setTimeout(function () {
            airRatioTable.bootstrapTable('resetView');
        }, 200);

        $(window).resize(function () {
            // 重新设置表的高度
            airRatioTable.bootstrapTable('resetView', {
                height: pageUtils.getTableHeight()
            });
        });
    }

    /********************  查询空气质量同期对比列表(饼状图)  ********************/
    var airRatioTable2 = $('#airRatioTable2');
    function initTable2(firstTime,lastTime,minValue,maxValue) {
        airRatioTable2.bootstrapTable('destroy');
        airRatioTable2.bootstrapTable({
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            sidePagination:"server",
            url: rootPath+"/action/S_port_AirQuality_list.action?firstTime="+firstTime+"&lastTime="+lastTime+"&minValue="+minValue+"&maxValue="+maxValue,
            method:'post',
            pagination:true,
            clickToSelect:true,//单击行时checkbox选中
            queryParams:pageUtils.localParams,
            columns: [
                {
                    title:"全选",
                    checkbox: true,
                    align: 'center',
                    radio:false,  //  true 单选， false多选
                    valign: 'middle'
                }, {
                    title: 'ID',
                    field: 'id',
                    align: 'center',
                    valign: 'middle',
                    sortable: false,
                    visible: false
                },
                {
                    title: '更新时间',
                    field: 'rec_Time',
                    editable: false,
                    sortable: false,
                    align: 'center'
                },
                {
                    title: '空气AQI值',
                    field: 'airValue',
                    editable: false,
                    sortable: false,
                    align: 'center'
                }

            ]
        });
        // sometimes footer render error.
        setTimeout(function () {
            airRatioTable2.bootstrapTable('resetView');
        }, 200);

        $(window).resize(function () {
            // 重新设置表的高度
            airRatioTable2.bootstrapTable('resetView', {
                height: pageUtils.getTableHeight()
            });
        });
    }



});

