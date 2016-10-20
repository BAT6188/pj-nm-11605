/**
 * Created by Administrator on 2016/10/12.
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

    initPage();//页面初始化

    function initPage(){
        $('#columnBtn').css('background','#0099FF');
        sewageColumnCahrt();
    }

    //柱状图按钮
    $("#columnBtn").bind('click',function(){
        $('#columnBtn').css('background','#0099FF');
        $("#pieBtn").css('background','#fff');
        $("#lineBtn").css('background','#fff');
        sewageColumnCahrt();
    });

    //柱状图获取后台数据
    function sewageColumnCahrt(){
        // $.ajax({
        //     url:rootPath + "/action/S_exelaw_PollutantPayment_getSewageColumn.action",
        //     type:'post',
        //     dataType:'json',
        //     success:function(data){
        //
        //
        //     }
        //
        //
        // });


        var categories = ['1月', '2月', '3月', '4月', '5月','6月'];
        var series = [];

        var yijiaofei = {name: '已缴费企业', color: 'rgb(124, 181, 236)', data: [40, 36, 20, 40, 30,43]};
        var weijiaofei = {name: '未缴费企业', color:'#FF8800', data: [3, 7, 14, 3, 13,2]};
        series.push(yijiaofei);
        series.push(weijiaofei);
        colMchart(categories,series);

    }

    function colMchart(categories,series){
        highchart.highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '2016年上半年企业排污申报费用统计'
            },
            xAxis: {
                categories: categories,
                title: {
                    text: null
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





    
});