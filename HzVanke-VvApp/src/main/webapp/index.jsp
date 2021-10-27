<%--
  Created by IntelliJ IDEA.
  User: E480
  Date: 2020/4/29
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=yes"/>
    <%@ include file="WEB-INF/jsp/IncludeFile/CssAndJs.jspf"%>
    <script type="text/javascript">
        $(function () {

            $.ajax({
                url: '${path}/Login/login_do?email=@vanke.comadmin',
                type: "POST",
                dataType: "json",
                success: function (data) {
                    if(data.code=='0'){
                        location.href = "${path}/Main/Default"
                    }else{
                        layer.msg('登录失败', {
                            icon: 2,
                            time: 2000
                        }, function(){
                            location.href = "http://hzvvfy.vanke.com/vv/index2.jsp"
                        });
                    }
                }
            })

            <%--var index = layer.load(2, {--%>
                <%--shade: 0.1, content: '加载中...',--%>
                <%--success: function (layero) {--%>
                    <%--layero.find('.layui-layer-content').css({--%>
                        <%--'padding-top': '39px',--%>
                        <%--'width': '60px',--%>
                        <%--'font-size':'13px'--%>
                    <%--});--%>
                <%--}--%>
            <%--}); //0代表加载的风格，支持0-2--%>

            <%--// //此处为测试代码--%>
            <%--// location.href = "Login.aspx?email=h-jiangb01@vanke.com&rand=" + newCommon.newGuid();--%>


            <%--// 在 qing.config 执行前，所有其它的 ydk api 都不会真正被调用--%>
            <%--// 这里是一个简单的配置，其它 API list 请参考详细文档--%>
            <%--qing.config({--%>
                <%--debug: false,--%>
                <%--// 声明需要调用的API--%>
                <%--jsApiList: ['checkJsApi', 'share']--%>
            <%--});--%>

            <%--// 获取个人信息--%>
            <%--qing.call('getPersonInfo', {--%>
                <%--success: function (result) {--%>
                    <%--if (result.success) {--%>
                        <%--var email = result.data.email;--%>
                        <%--//获取到万科邮箱，需要针对邮箱地址进行字符串处理--%>
                        <%--//替换掉@vanke.com，得到用户名，可以在后台进行处理--%>
                        <%--$.ajax({--%>
                            <%--url: '${path}/Login/login_do?email=@vanke.comadmin',--%>
                            <%--type: "POST",--%>
                            <%--dataType: "json",--%>
                            <%--success: function (data) {--%>
                                <%--if(data.code=='0'){--%>
                                    <%--location.href = "${path}/compfyzxbz/findList"--%>
                                <%--}else{--%>
                                    <%--layer.msg('登录失败', {--%>
                                        <%--icon: 2,--%>
                                        <%--time: 2000--%>
                                    <%--}, function(){--%>
                                        <%--location.href = "http://hzvvfy.vanke.com/vv/index2.jsp"--%>
                                    <%--});--%>
                                <%--}--%>
                            <%--}--%>
                        <%--})--%>
                    <%--}--%>
                    <%--else {--%>
                        <%--//转到错误提示页--%>
                        <%--location.href = "Error_NoUserCode.aspx";--%>
                    <%--}--%>
                <%--}--%>
            <%--});--%>
        });
    </script>
</head>
<body style="background-color: white">
<div style="width: 100%;height: 600px;display: flex;justify-content: center;align-items: center;">
    登录中...
</div>
</body>
</html>
