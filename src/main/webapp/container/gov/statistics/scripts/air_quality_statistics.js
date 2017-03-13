/**
 * Created by Administrator on 2016/10/17.
 */
//@ sourceURL=air_quality_statistics.js
$(function(){
    var highchart = $("#container");

    var valueChart = '1';
    
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
    var airType = '';
    
    
    //初始化页面
    initPage();

    function initPage(){
        $('#columnBtn').css('background','#0099FF');
        search(valueChart,startYdate,lastYdate,airType);

    }

    $("#search").bind('click',function(){
        var start_createTime = $("#start_createTime").val();
        var end_createTime = $("#end_createTime").val();
        if(start_createTime && start_createTime!=""){
            startYdate = start_createTime+"-"+"01";
        }
        if(end_createTime && end_createTime!=""){
            var edStr = end_createTime.split("-");
            var day = new Date(parseInt(edStr[0]),parseInt(edStr[1]),0);
            var dayCount = day.getDate();
            lastYdate = end_createTime+"-"+dayCount;
        }
        airType = $("#airType").val();
        search(valueChart,startYdate,lastYdate,airType);
    });

    function search(valueChart,startYdate,lastYdate,airType){
        if(valueChart == '2'){
            getAirPie(startYdate,lastYdate,airType);
        }else if(valueChart == '3'){
            getAirLine(startYdate,lastYdate,airType);
        }else{
            getAirColumn(startYdate,lastYdate,airType);
        }
    }

    //柱状图按钮
    $("#columnBtn").bind('click',function(){
        valueChart = $("#columnBtn").attr("data-checked");
        $('#columnBtn').css('background','#0099FF');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#fff');

        search(valueChart,startYdate,lastYdate,airType);

    });

    //饼状图按钮
    $("#pieBtn").bind('click',function(){
        valueChart = $("#pieBtn").attr("data-checked");
        $("#pieBtn").css('background','#0099FF');
        $('#columnBtn').css('background','#fff');
        $("#lineBtn").css('background','#fff');
        search(valueChart,startYdate,lastYdate,airType);
    });

    //线状图按钮
    $("#lineBtn").bind('click',function(){
        valueChart = $("#lineBtn").attr("data-checked");
        $('#columnBtn').css('background','#fff');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#0099FF');
        search(valueChart,startYdate,lastYdate,airType);
    });
    
    //柱状图获取后台数据
    function getAirColumn(startYdate,lastYdate,airType){
        // var categories = ["优","良","轻度污染","中度污染","重度污染","严重污染"];
        // var series = [];
        //
        // var gongye = {name: "空气质量", data: [9,2,0,2,2,2]};
        // series.push(gongye);
        //
        // colMchart(categories, series);
        $.ajax({
            url: rootPath + "/action/S_port_AirQuality_getColumnHighChart.action",
            dataType:'json',
            type:'post',
            data:{startYdate:startYdate,lastYdate:lastYdate,airType:airType},
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y;
                var ylist = [];
                for(var i = 0; i<list.length;i++){
                    ylist.push(parseInt(list[i]));
                }
                var series1 = {name: "空气质量", data:ylist};
                series.push(series1);
                colMchart(categories,series,startYdate,lastYdate);

            }
        });
    }

    //饼状图获取后台数据
    function getAirPie(startYdate,lastYdate,airType){
        $.ajax({
            url: rootPath + "/action/S_port_AirQuality_getColumnHighChart.action",
            dataType:'json',
            type:'post',
            data:{startYdate:startYdate,lastYdate:lastYdate,airType:airType},
            success:function(data){
                var categories = data['x'];
                var series1 = data['y'];
                var series = [{
                    name:"空气质量:(天)",
                    data:[]
                }];
                if(!series1){
                    return " ";
                }
                for (var i = 0; i < series1.length; i++) {

                    series[0].data.push({name:categories[i],y: parseInt(series1[i])});
                }
                pieMchart(series,startYdate,lastYdate);

            }
        });
    }

    //线状图获取后台数据
    function getAirLine(startYdate,lastYdate,airType){
        $.ajax({
            url: rootPath + "/action/S_port_AirQuality_getColumnHighChart.action",
            dataType:'json',
            type:'post',
            data:{startYdate:startYdate,lastYdate:lastYdate,airType:airType},
            success:function(data){
                var categories = data.x;
                var series = [];
                var list = data.y;
                var ylist = [];
                for(var i = 0; i<list.length;i++){
                    ylist.push(parseInt(list[i]));
                }
                var series1 = {name: "空气质量", data:ylist};
                series.push(series1);
                lineMchart(categories,series,startYdate,lastYdate);

            }
        });


    }

    var minValue;
    var maxValue;
    //柱状图highchart
    function colMchart(categories, series,startYdate,lastYdate){
        if(startYdate == '2016-01-01'){
            titleSub= '2016上半年空气质量(AQI)统计'
        }else{
            titleSub = startYdate+'月至'+lastYdate+'月空气质量(AQI)统计';
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
                    text:'污染级别'
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: '天数'
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

                            $("#airListForm").modal('show');
                            var strValue = e.point.category;
                            if(strValue == '优'){
                                minValue = 0;
                                maxValue = 50;
                            }else if(strValue == '良'){
                                minValue = 50;
                                maxValue = 100

                            }else if(strValue == '轻微污染'){
                                minValue = 100;
                                maxValue = 150;

                            }else if(strValue == '轻度污染'){
                                minValue = 150;
                                maxValue = 200;

                            }else if(strValue == '中度污染'){
                                minValue = 200;
                                maxValue = 300

                            }else if(strValue == '重度污染'){
                                minValue = 299;
                                maxValue = 1000
                            }
                            var startYdates = startYdate ;
                            var lastYdates = lastYdate ;
                            initTable(startYdates,lastYdates,minValue,maxValue);
                        }
                    }
                }
            },
            legend: {
                enabled: true
            },

            tooltip: {
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}</small><table>',
                pointFormat: '<tr>' +
                '<td style="text-align: right"><b>:{point.y} 天</b></td></tr>',
                footerFormat: '</table>',
                valueDecimals: 0
            },
            exporting: {
                enabled:false
            },
            series: series
        });

    }
    
    //饼状图
    function pieMchart(series,startYdate,lastYdate){
        if(startYdate == '2016-01-01'){
            titleSub= '2016上半年空气质量(AQI)统计'
        }else{
            titleSub = startYdate+'月至'+lastYdate+'月空气质量(AQI)统计';
        }

        highchart.highcharts({
            chart: {
                type: 'pie'
            },
            title: {
                text: titleSub
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
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
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
                            $("#airListForm").modal('show');
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
                            var startYdates = startYdate + "-"+"01";
                            var lastYdates = lastYdate + "-"+"31";
                            initTable(startYdates,lastYdates,minValue,maxValue);
                        }
                    }
                }
            },
            tooltip: {
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}</small><table>',
                pointFormat: '<tr><td style="color: {series.color}">{series.name}: </td>' +
                '<td style="text-align: right"><b>{point.y} 天</b></td></tr>',
                footerFormat: '</table>',
                valueDecimals: 0
            },
            exporting: {
                enabled:false
            },
            series: series
        });
    }


    //线状图highChart
    function lineMchart(categories,series,startYdate,lastYdate){
        if(startYdate == '2016-01-01'){
            titleSub= '2016上半年空气质量(AQI)统计'
        }else{
            titleSub = startYdate+'月至'+lastYdate+'月空气质量(AQI)统计';
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
                categories: categories,
                title:{
                    text:'空气质量'
                }
            },
            yAxis: {
                title: {
                    text: '天数'
                }
            },
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
                            $("#airListForm").modal('show');
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
                            var startYdates = startYdate + "-"+"01";
                            var lastYdates = lastYdate + "-"+"31";
                            initTable(startYdates,lastYdates,minValue,maxValue);
                        }
                    }
                }
            },
            legend: {
                enabled: true
            },
            tooltip: {
                shared: true,
                useHTML: true,
                headerFormat: '<small>{point.key}</small><table>',
                pointFormat: '<tr><td style="color: {series.color}">{series.name}: </td>' +
                '<td style="text-align: right"><b>{point.y} 天</b></td></tr>',
                footerFormat: '</table>',
                valueDecimals: 0
            },
            exporting: {
                enabled:false
            },
            series: series

        });
    }

    /********************  查询空气质量列表  ********************/
    var airTable = $('#airTable'),isLoadAirTable = false;
    function initTable(firstTime,lastTime,minValue,maxValue) {
        airTable.firstTime = firstTime;
        airTable.lastTime = lastTime;
        airTable.minValue = minValue;
        airTable.maxValue = maxValue;
        if(isLoadAirTable){
            airTable.bootstrapTable('refreshOptions',{pageNumber:1,pageSize:10});
        }else{
            isLoadAirTable = true;
            airTable.bootstrapTable({
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                sidePagination:"server",
                url: rootPath+"/action/S_port_CityDayAqiPublish_list.action",
                method:'post',
                pagination:true,
                clickToSelect:true,//单击行时checkbox选中
                queryParams:function(params){
                    var localParams = {};
                    //分页参数
                    localParams.take = params.limit;
                    localParams.skip = params.offset;
                    if(params.offset){
                        localParams.page = params.offset / params.limit + 1;
                    }else{
                        localParams.page = 1;
                    }
                    localParams.pageSize = params.limit;
                    localParams.firstTime = airTable.firstTime;
                    localParams.lastTime = airTable.lastTime;
                    localParams.minValue = airTable.minValue;
                    localParams.maxValue = airTable.maxValue;
                    return localParams;
                },
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
                        field: 'timePoint',
                        editable: false,
                        sortable: false,
                        align: 'center',
                        formatter:function(value, row, index) {
                            return pageUtils.sub10(value);
                        }
                    },
                    {
                        title: '空气AQI值',
                        field: 'aQI',
                        editable: false,
                        sortable: false,
                        align: 'center'
                    },
                    {
                        title: '首要污染物',
                        field: 'primaryPollutant',
                        editable: false,
                        sortable: false,
                        align: 'center'
                    }
                ]
            });
            // sometimes footer render error.
            setTimeout(function () {
                airTable.bootstrapTable('resetView');
            }, 200);

            $(window).resize(function () {
                // 重新设置表的高度
                airTable.bootstrapTable('resetView', {
                    height: pageUtils.getTableHeight()
                });
            });
        }
    }






});

