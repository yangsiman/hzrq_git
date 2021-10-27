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
    <title>首页</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes"/>
    <%@ include file="../IncludeFile/CssAndJs.jspf" %>
</head>
<style>
    .title{
        height: 40px;
        position: relative;
    }

    .wrapper1 {
        margin-top: 16px;
        background-color: white;
    }

    .box-show {
        box-shadow: -2px 0 5px 1px #0000000d,0 -2px 5px 1px #0000000d,2px 0 5px 1px #0000000d,0 2px 5px 1px #0000000d;
        border-radius: 5px;
    }
    .box-show_div{
        flex: 1;
        margin-left: 10px;
    }

    .div_img {
        height: 60%;
        margin-top: 5px;
        text-align: center;
    }

    .div_img img {
        height: 100%;
        margin: auto;
    }

    .div_span {
        font-size: 14px;
        margin: auto;
        text-align: center;
        margin-top: 10px;
    }
</style>
<body style="background-color: white;">
<div style="width: 90%;margin: auto;margin-top: 50px;">
    <img style="width: 100%;" src="${path}/SystemCss/Default/image/home_top.png" />
</div>
<div class="wrapper1" style="">
    <div class="title">
        <div style="display: flex;width: 100px;height: 40px;margin-left: 30px;line-height: 40px;">
            <div style="left: 0;width: 8px;background-color: #1E9FFF;height: 22px;margin-top: 11px;"></div>
            <div style="left: 10px;">主要指标</div>
        </div>
    </div>
</div>
<div class="box-show" style="width: 90%;margin: auto;height: 80px;display: flex;">
    <div class="box-show_div" onclick="window.location.href='${path}/Main/popu'">
        <div class="div_img">
            <img src="${path}/SystemCss/Default/image/population.png" />
        </div>
        <div class="div_span">
            总体满意度
        </div>
    </div>
    <div class="box-show_div" onclick="window.location.href='${path}/Main/contact'">
        <div  class="div_img">
            <img src="${path}/SystemCss/Default/image/contact.png" />
        </div>
        <div class="div_span">
            触点满意度
        </div>
    </div>
    <div class="box-show_div" onclick="window.location.href='${path}/Main/Project'">
        <div  class="div_img">
            <img src="${path}/SystemCss/Default/image/project.png" />
        </div>
        <div class="div_span">
            各项目满意度
        </div>
    </div>
    <div></div>
</div>
</body>
</html>
