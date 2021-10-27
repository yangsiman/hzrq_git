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
        $(function () {
            changeRole();
        });

        function fCheckInput() {
            if ($("#txtName").val() == "") {
                layer.tips('请输入用户姓名。', '#txtName', {tips: [2, '#ff4401']});
                return false;
            }

            if ($("#ddlRole").val() == "") {
                layer.tips('请选择用户所属角色。', '#ddlRole', {tips: [2, '#ff4401']});
                return false;
            }
            if ($("#txtMobile").val() != "" && !_mobilereg.test($("#txtMobile").val())) {
                layer.tips('请输入正确的手机号码。', '#txtMobile', {tips: [2, '#ff4401']});
                return false;
            }

            if ($("#txtEmail").val() != "" && !_emailreg.test($("#txtEmail").val())) {
                layer.tips('联系邮箱不正确，请确认。', '#txtEmail', {tips: [2, '#ff4401']});
                return false;
            }
            if ($("#txtPassword").val() != "" && !newCommon.checkPass($("#txtPassword").val())) {
                layer.tips('密码不符合规范，请重新输入。', '#txtPassword', {tips: [2, '#ff4401']});
                return false;
            }

            if (confirm("你确定要保存此用户信息吗？")) {
                var index = layer.load(2, {shade: true, shade: 0.1}); //0代表加载的风格，支持0-2
                return true;
            }
            else {
                return false;
            }
        }
    </script>
</head>
<body>
<div class="rq_page_navigation_inpage">
    <a href="${path}/Main/UserMainPage">主页</a> / 系统配置 / <a href="${path}/SysUser/ManageList">系统用户管理</a> / 编辑系统用户
</div>
<div class="rq_from">
    <form id="form1" action="${path}/SysUser/SaveEditUser" method="post">
        <input name="id" value="${sysUser.id}" type="hidden"/>
        <div class="rq_from_back">
            <div class="rq_tab_frame_2">
                <div class="rq_tab_box_2">
                    <div onclick="fChangeTab(1);" id="tab_item_1" class="rq_tab_item_2 rq_tab_item_focus_2"><span>基本信息</span></div>
                    <div class="rq_tab_item_flex"></div>
                </div>
            </div>
            <div class="rq_pub_line15"></div>

            <!-- 功能实现区/start -->
            <div id="tab_content_1">
                <div class="rq_form_box">
                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">用户姓名</div>
                        <div class="rq_form_col_r">
                            <input value="${sysUser.userName}" name="userName" type="text" id="txtName"
                                   class="pub_input1"/>
                            <span class="rq_color_red">*</span>
                        </div>
                    </div>

                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">用户角色</div>
                        <div class="rq_form_col_r">
                            <select name="role.id" id="ddlRole" onchange="changeRole()">
                                <option value=""></option>
                                <c:forEach var="role" varStatus="index" items="${listSysRole}">
                                    <option value="${role.id}"
                                            <c:if test="${role.id.equals(sysUser.role.id)}">selected="selected"</c:if>>${role.roleName}</option>
                                </c:forEach>
                            </select>
                            <span class="rq_color_red">*</span>
                        </div>
                    </div>
                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">登陆帐号</div>
                        <div class="rq_form_col_r">
                            <input value="${sysUser.userCode}" readonly="readonly" name="userCode" type="text"
                                   id="txtUserCode" class="pub_input1"/>
                            <span class="rq_color_red">//此项不能修改</span>
                        </div>
                    </div>

                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">登陆密码</div>
                        <div class="rq_form_col_r">
                            <input value="${sysUser.password}" type="hidden" name="password"/>
                            <input name="passwordChange" type="password" id="txtPassword" class="pub_input1"/>
                            <span class="rq_color_red">//为空则不修改密码，若修改密码必须是大写字母，小写字母，数字，特殊字符中任意三个组合且长度大于8位</span>
                        </div>
                    </div>

                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">手机号码</div>
                        <div class="rq_form_col_r">
                            <input value="${sysUser.mobile}" name="mobile" type="text" id="txtMobile"
                                   class="pub_input1"/>
                        </div>
                    </div>

                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">联系邮箱</div>
                        <div class="rq_form_col_r">
                            <input value="${sysUser.email}" name="email" type="text" id="txtEmail" class="pub_input1"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="rq_pub_line60"></div>
            <!-- 功能实现区/end -->


            <!-- 按钮区/start -->
            <div class="rq_button_box_flexed">
                <input type="submit" name="btnSave" value="确定保存" onclick="return fCheckInput();" id="btnSave"
                       class="rq_button_l"/>
                <input id="Reset1" type="reset" value="重新输入" class="rq_button_c"/>
                <a href="javascript:history.back(-1)" class="rq_link_r">返回列表</a>
            </div>
            <!-- 按钮区/end -->
        </div>
    </form>
</div>
</body>
</html>
