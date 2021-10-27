<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/31
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>项目报表</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes"/>
    <%@ include file="../IncludeFile/CssAndJs.jspf" %>
</head>
<body style="background-color: #9999992b;">
<div style="width: 100%;height: 40px;line-height: 40px;text-align: left;background-color: white;">
    <i class="left" onclick="window.location.href='${path}/Main/Default'"></i>
</div>
<div style="width: 100%;background-color: white;">
    <div style="width: 100%;height: 50px;font-size: 16px;display: flex;">
        <!--左边时间下来-->
        <div style="flex: 1;height: 100%;">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-inline" style="margin-top: 2px;margin-left: 10px;!important;">
                        <input type="text" class="layui-input" id="test1" placeholder="yyyy-MM-dd">
                    </div>
                </div>
            </div>
        </div>
        <div style="flex: 1;font-size: 16px;color: #999999;height: 40px;line-height: 40px;">
            数据更新时间2021-10-8
        </div>
    </div>
</div>

<div class="wrapper1" style="">
    <div class="title">
        <div style="display: flex;width: 100px;height: 40px;margin-left: 30px;line-height: 40px;font-weight: 700;">
            <div style="left: 0;width: 8px;background-color: #1E9FFF;height: 22px;margin-top: 11px;"></div>
            <div style="left: 10px;">总体满意度</div>
        </div>
    </div>
    <div class="wrapper-chart" style="width: 100%;overflow-y: auto;box-shadow: 0 -10px 20px #88888833;">
        <div class="chart-top">
            <button type="button" class="layui-btn layui-btn-normal active" id="ri">日趋势</button><button type="button" class="layui-btn layui-btn-normal" id="yue">月趋势</button>
        </div>
        <div class="chart">
            <div id="main" class="main" ></div>
        </div>
    </div>
</div>


<script>

    var option = {

        tooltip: {
            show: true,
            trigger:"axis",
            confine: true
        },

        legend: {
            top: 10,
            left: 10,
            orient: 'horizontal',
            align: "auto",
            itemGap: 10,
            data:[
                {name:"当月累计得分"},
                {name:"当月累计公司排名"},
                {name:"调访户数"}]
        },

        grid:{
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },

        xAxis: {
            data: [<c:forEach items="${maps}" var="item">
                ${item.get("period_month")},
                </c:forEach>
            ]
        },

        yAxis: {
            type: "log",
        },
        dataZoom: [
            {
                type: "slider",
                show: true,
                xAxisIndex: [0],
                start: 0,
                end: 49,
                textStyle:{
                    color:"#ccd7d7"
                }
            },
        ],

        series: [
            {name: '当月累计得分', type: 'line', data: [
                    <c:forEach items="${maps}" var="item">
                    ${item.get("ljdf")},
                    </c:forEach>]
            },
            {name:'当月累计公司排名', type:'line', data:[
                    <c:forEach items="${maps}" var="item">
                    ${item.get("ljjt_pm")},
                    </c:forEach>
                ]},
            {name:'调访户数', type:'line', data:[
                    <c:forEach items="${maps}" var="item">
                    ${item.get("ljqty_ybs")},
                    </c:forEach>
                ]}
        ]
    };

    var option2 = {

        tooltip: {
            show: true,
            trigger:"axis"
        },

        legend: {
            top: 10,
            left: 10,
            orient: 'horizontal',
            align: "auto",
            itemGap: 10,
            data:[
                {name:"当月累计得分"},
                {name:"当月累计公司排名"},
                {name:"年度累计得分"},
                {name:"年度累计公司排名"},
                {name:"调访户数"}]
        },
        dataZoom: [
            {
                type: "slider",
                show: true,
                xAxisIndex: [0],
                start: 0,
                end: 49,
                textStyle:{
                    color:"#ccd7d7"
                }
            },
        ],
        grid:{
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },

        xAxis: {
            data: ["8/12","8/13","8/14","8/15"]
        },

        yAxis: {
            type: "log",
        },

        series: [
            {name: '当月累计得分', type: 'line', data: [10, 30,50, 70]},
            {name: '当月累计公司排名', type: 'line', data: [20, 40,60, 80]},
            {name: '年度累计得分', type: 'line', data: [20, 40,60, 80]},
            {name: '年度累计公司排名', type: 'line', data: [20, 40,60, 80]},
            {name:'调访户数', type:'line', data:[60,70,80,90]}
        ]
    };


    var myChart = echarts.init(document.getElementById('main'));
    myChart.setOption(option);
    activeClass();
    function activeClass(){
        $(".wrapper-chart>.chart-top>button").click(function () {
            $(this).addClass("active");
            if(this.id=="ri"){
                myChart.clear();
                myChart.setOption(option);
                $("#yue").removeClass("active");
            }
            if(this.id=="yue"){
                myChart.clear();
                myChart.setOption(option2);
                $("#ri").removeClass("active");
            }
        })
    }

</script>
<script>
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //常规用法
        laydate.render({
            elem: '#test1',
            done: function (value) {
                console.log(value);
            }
        });
    });
</script>
</body>
</html>
