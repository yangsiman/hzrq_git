<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="path"
       scope="page"/>
<!DOCTYPE HTML>
<html>
<head>
    <title>杭州万科大屋顶信息管理系统V3.1.1</title>
    <link href="${path}/SystemCss/Login/login.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${path}/SystemJs/jquery.v3.3.1.min.js"></script>
    <script type="text/javascript" src="${path}/SystemJs/jquery.form.js"></script>
    <script type="text/javascript" src="${path}/SystemJs/newCommon.js"></script>
    <script type="text/javascript" src="${path}/SystemJs/layer/layer.js"></script>
    <script type="text/javascript">
        function fCheckInput() {
            var options = {
                beforeSubmit: showRequest,//提交前的回调函数
                success: showResponse,//提交后的回调函数
                dataType: 'json',
                timeout: 6000
            };
            $("#loginform").ajaxSubmit(options);
            return false;//阻止表单默认提交
        }

        function showRequest(formData, jqForm, options) {
            var formElement = jqForm[0]; //将jqForm转换为DOM对象
            if (formElement.usercode.value == "") {
                layer.tips('请输入登陆帐号。', '#txtUsercode', {tips: [2, '#e60012']});
                $("#txtUsercode").focus();
                return false;
            }
            if (formElement.password.value == "") {
                layer.tips('请输入登陆密码。', '#txtPassword', {tips: [2, '#e60012']});
                $("#txtPassword").focus();
                return false;
            }
            if (formElement.code.value == "" || formElement.code.value.length != 4) {
                layer.tips('请输入验证码。', '#txtCode', {
                    tips: [2, '#e60012']
                });
                $("#txtCode").focus();
                return false;
            }

            layer.load(2, {shade: true, shade: 0.1}); //0代表加载的风格，支持0-2
            return true; //只要不返回false，表单都会提交,在这里可以对表单元素进行验证
        }

        function showResponse(responseText) {
            layer.closeAll();
            let json = responseText;
            if (json.code == 1) {
                //登陆成功，转向
                location.href = "${path}/Main/Default";
            } else {
                fChangeCode();
                layer.msg(json.msg);
            }
        }

        function fChangeCode() {
            $("#imgCode").attr("src",
                "${path}/Login/authCode?rand=" + newCommon.newGuid());
        }

        $(function () {
            $(document).keyup(function (e) {//捕获文档对象的按键弹起事件
                if (e.keyCode == 13) {//按键信息对象以参数的形式传递进来了
                    //此处编写用户敲回车后的代码
                    fCheckInput();
                }
            });
        });
    </script>
</head>

<body>
<div class="page_head_box">
    <div class="page_head_logo"></div>
</div>
<div class="page_content_frame">
    <div class="page_login_frame">
        <div class="pub_line20"></div>
        <div class="page_login_title">
            欢迎登陆
        </div>
        <div class="pub_line10"></div>

        <form id="loginform" action="${path}/Login/login_do" method="post">
            <div class="page_login_control_item">
                <input id="txtUsercode" name="usercode" type="text"
                       class="page_login_input page_login_input_usercode"
                       placeholder="请输入登录帐号"/>
            </div>
            <div class="page_login_control_item">
                <input id="txtPassword" name="password" type="password"
                       class="page_login_input page_login_input_password"
                       placeholder="请输入登录密码"/>
            </div>
            <div class="page_login_control_item">
                <div class="page_login_code">
                    <input id="txtCode" name="code" type="text"
                           class="page_login_input page_login_input_code"
                           placeholder="请输入验证码" style="width: 110px" maxlength="4"/>
                </div>
                <div class="page_login_code_img">
                    <img id="imgCode" onclick="fChangeCode();" alt="验证码"
                         title="看不清换一个" src="${path}/Login/authCode">
                </div>
            </div>
            <div class="page_login_control_item">
                <input type="button" value="登 录" onclick="return fCheckInput();"
                       class="page_login_button" onfocus="this.blur();"/>
            </div>
        </form>
        <div class="pub_line20"></div>
       <%-- <div class="page_login_control_item pub_color1">
            支持服务电话：0571-86908830
        </div>--%>
    </div>
</div>
<div class="page_foot_frame" style="background: transparent;color: #eaeaea;border: 0;">Copyright &copy; 2018-2025
    杭州万科版权所有，系统由杭州瑞祁广智信息技术有限公司提供技术支持 <a href="http://www.beian.miit.gov.cn"
                      style="color: #eaeaea;"   target="_blank">备案号:浙ICP备17009903号-1</a>
</div>
</body>
</html>
