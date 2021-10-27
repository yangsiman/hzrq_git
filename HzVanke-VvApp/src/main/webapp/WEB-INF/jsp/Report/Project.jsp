<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2021/10/14
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes"/>
    <%@ include file="../IncludeFile/CssAndJs.jspf" %>
    <title>项目满意度</title>
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

<div class="wrapper1" style="background-color: white">
    <div class="title">
        <div style="display: flex;width: 100px;height: 40px;margin-left: 10px;line-height: 40px;">
            <div style="left: 0;width: 8px;background-color: #1E9FFF;height: 22px;margin-top: 11px;"></div>
            <div style="left: 10px;">各项目满意度</div>
        </div>
    </div>
    <div class="wrapper-chart" style="overflow-y: auto;">
        <table border="0" class="page_table_list" style="width: auto;">
            <tr>
                <td class="header">项目</td>
                <td class="header">当月累计得分</td>
                <td class="header">涨幅</td>
                <td class="header">当月样本量</td>
                <td class="header">样本增量</td>
                <td class="header">当月累计参与</td>
                <td class="header">参与增量</td>
                <td class="header">当月累计不满意</td>
                <td class="header">不满意增量</td>
                <td class="header">年度累计得分</td>
            </tr>
            <tr class="itemtr">
                <td><a href="${path}/Main/ProjectPopu?projectId">项目1</a></td>
                <td>9021</td>
                <td>0.2</td>
                <td>11</td>
                <td>2</td>
                <td>11</td>
                <td>2</td>
                <td>5</td>
                <td>3</td>
                <td>92.89</td>
            </tr>
            <tr class="itemtr">
                <td><a href="#">项目2</a></td>
                <td>9021</td>
                <td>0.2</td>
                <td>11</td>
                <td>2</td>
                <td>11</td>
                <td>2</td>
                <td>5</td>
                <td>3</td>
                <td>92.89</td>
            </tr>
            <tr class="itemtr">
                <td><a href="#">项目3</a></td>
                <td>9021</td>
                <td>0.2</td>
                <td>11</td>
                <td>2</td>
                <td>11</td>
                <td>2</td>
                <td>5</td>
                <td>3</td>
                <td>92.89</td>
            </tr>
            <tr class="itemtr">
                <td><a href="#">项目4</a></td>
                <td>9021</td>
                <td>0.2</td>
                <td>11</td>
                <td>2</td>
                <td>11</td>
                <td>2</td>
                <td>5</td>
                <td>3</td>
                <td>92.89</td>
            </tr>
            <tr class="itemtr">
                <td><a href="#">项目5</a></td>
                <td>9021</td>
                <td>0.2</td>
                <td>11</td>
                <td>2</td>
                <td>11</td>
                <td>2</td>
                <td>5</td>
                <td>3</td>
                <td>92.89</td>
            </tr>
        </table>
    </div>
</div>

<script>
    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //常规用法
        laydate.render({
            elem: '#test1'
        });
    });
</script>
</body>
</html>
