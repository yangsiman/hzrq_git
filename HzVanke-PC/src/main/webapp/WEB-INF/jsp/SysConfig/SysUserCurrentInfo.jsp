<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/1
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑系统用户</title>
    <%@ include file="../IncludeFile/CssAndJs.jspf" %>
    <script type="text/javascript">
        function fChangeTab(index) {
            for (var i = 1; i <= 2; i++) {
                var _tab_item = "tab_item_" + i.toString();
                var _tab_content = "tab_content_" + i.toString();
                if (index == i) {
                    $("#" + _tab_item).attr("class", "rq_tab_item_focus_2");
                    $("#" + _tab_content).show();
                }
                else {
                    $("#" + _tab_item).attr("class", "rq_tab_item_2");
                    $("#" + _tab_content).hide();
                }
            }
        }

        function fCheckInput() {
            if ($("#txtName").val() == "") {
                fChangeTab(1);
                layer.tips('请输入用户姓名。', '#txtName', { tips: [2, '#ff4401'] });
                return false;
            }

            if ($("#txtMobile").val() != "" && !_mobilereg.test($("#txtMobile").val())) {
                fChangeTab(1);
                layer.tips('请输入正确的手机号码。', '#txtMobile', { tips: [2, '#ff4401'] });
                return false;
            }

            if ($("#txtEmail").val() != "" && !_emailreg.test($("#txtEmail").val())) {
                fChangeTab(1);
                layer.tips('联系邮箱不正确，请确认。', '#txtEmail', { tips: [2, '#ff4401'] });
                return false;
            }


            var postUrl = "${path}/SysUser/SaveEditCurrentUser";
            var options = {
                beforeSubmit: function(){
                    var _submit =  confirm("你确定要保存用户信息吗？");
                    if(_submit){
                        var index = layer.load(2, { shade: true, shade: 0.1 }); //0代表加载的风格，支持0-2
                    }
                    return _submit;
                },
                url: postUrl,
                success: function (json) {
                    layer.msg(json.msg);
                    layer.closeAll();
                    if (json.code == 1){
                        top.location.href="${path}/Login/login_out";
                    }
                },//提交后的回调函数
                dataType: 'json', timeout: 6000
            };
            $("#form1").ajaxSubmit(options);
            return false;//阻止表单默认提交
        }
    </script>
</head>
<body>
<div class="rq_page_navigation_inpage">
    <a href="${path}/Main/UserMainPage">主页</a> / 编辑登陆用户信息
</div>
<div class="rq_from">
    <form id="form1" action="${path}/SysUser/SaveEditCurrentUser" method="post">
        <div class="rq_from_back">
            <!-- 功能实现区/start -->
            <div id="tab_content_1">
                <div class="rq_form_box">
                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">用户姓名</div>
                        <div class="rq_form_col_r">
                            <input value="${sysUser.userName}" name="username" type="text" id="txtName" class="pub_input1" />
                            <span class="rq_color_red">*</span>
                        </div>
                    </div>

                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">登陆帐号</div>
                        <div class="rq_form_col_r">
                            <input value="${sysUser.userCode}" readonly="readonly" name="usercode" type="text" id="txtUserCode" class="pub_input1" />
                            <span class="rq_color_red">//此项不能修改</span>
                        </div>
                    </div>

                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">登陆密码</div>
                        <div class="rq_form_col_r">
                            <input name="password" type="password" id="txtPassword" class="pub_input1" />
                            <span class="rq_color_red">//为空则不修改密码</span>
                        </div>
                    </div>

                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">手机号码</div>
                        <div class="rq_form_col_r">
                            <input value="${sysUser.mobile}" name="mobile" type="text" id="txtMobile" class="pub_input1" />
                        </div>
                    </div>

                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">联系邮箱</div>
                        <div class="rq_form_col_r">
                            <input value="${sysUser.email}" name="email" type="text" id="txtEmail" class="pub_input1" />
                        </div>
                    </div>
                </div>
            </div>

            <div class="rq_pub_line60"></div>
            <!-- 功能实现区/end -->


            <!-- 按钮区/start -->
            <div class="rq_button_box_flexed">
                <input type="button" name="btnSave" value="确定保存" onclick="return fCheckInput();" id="btnSave" class="rq_button_l" />
                <input id="Reset1" type="reset" value="重新输入" class="rq_button_r" />
            </div>
            <!-- 按钮区/end -->
        </div>
    </form>
</div>
</body>
</html>
