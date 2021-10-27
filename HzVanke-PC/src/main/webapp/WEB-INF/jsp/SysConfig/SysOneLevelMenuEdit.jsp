<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/1
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增一级菜单</title>
    <%@include file="../IncludeFile/CssAndJs.jspf" %>
    <script type="text/javascript">
        function fCheckInput() {
            if ($("#txtName").val() == "") {
                layer.tips('请输入菜单管理名称。', '#txtName', { tips: [2, '#ff4401'] });
                return false;
            }

            if ($("#txtMenuName").val() == "") {
                layer.tips('请输入菜单显示名称。', '#txtMenuName', { tips: [2, '#ff4401'] });
                return false;
            }

            if (!_numberreg.test($("#txtSort").val())) {
                layer.tips('请输入排序号（只能为正整数）。', '#txtSort', { tips: [2, '#ff4401'] });
                return false;
            }

            if ($("#txtClassName").val() == "") {
                layer.tips('请输入小图标样式名称。', '#txtClassName', { tips: [2, '#ff4401'] });
                return false;
            }

            if (confirm("你确定要保存此菜单信息吗？")) {
                var index = layer.load(2, { shade: true, shade: 0.1 }); //0代表加载的风格，支持0-2
                return true;
            }
            else {
                return false;
            }
        }
    </script>
</head>
<body>
<form method="post" action="${path}/SysOneLevelMenu/SaveEditMenu" id="form1">
    <div class="rq_page_navigation_inpage">
        <a href="${path}/Main/UserMainPage">主页</a> / 系统配置 / <a href="${path}/SysOneLevelMenu/ManageList">系统菜单管理</a> / 编辑一级菜单信息
    </div>
    <div class="rq_from">
        <div class="rq_from_back">
            <!-- 功能实现区/start -->
            <input type="hidden" name="id" value="${sysOnelevelmenu.id}" />
            <input type="hidden" name="status" value="${sysOnelevelmenu.status}" />

            <div class="rq_form_box">
                <div class="rq_form_item_box">
                    <div class="rq_form_col_1">菜单管理名称</div>
                    <div class="rq_form_col_r">
                        <input value="${sysOnelevelmenu.managename}" name="managename" type="text" id="txtName" class="pub_input1" />
                        <span class="rq_color_red">*</span><span class="rq_color_grey">后台管理列表里显示的名称</span>
                    </div>
                </div>

                <div class="rq_form_item_box">
                    <div class="rq_form_col_1">菜单显示名称</div>
                    <div class="rq_form_col_r">
                        <input value="${sysOnelevelmenu.menuname}" name="menuname" type="text" id="txtMenuName" class="pub_input1" />
                        <span class="rq_color_red">*</span><span class="rq_color_grey">左侧菜单栏中显示的名称</span>
                    </div>
                </div>

                <div class="rq_form_item_box">
                    <div class="rq_form_col_1">排序号</div>
                    <div class="rq_form_col_r">
                        <input name="sort" type="number" value="${sysOnelevelmenu.sort}" id="txtSort" class="pub_input1" />
                        <span class="rq_color_red">*</span><span class="rq_color_grey">只能输入数字且为整数</span>
                    </div>
                </div>

                <div class="rq_form_item_box">
                    <div class="rq_form_col_1">小图标样式名称</div>
                    <div class="rq_form_col_r">
                        <input value="${sysOnelevelmenu.classname}" name="classname" type="text" id="txtClassName" class="pub_input1" style="width:50%;" />
                        <span class="rq_color_red">*</span>
                    </div>
                </div>
            </div>
            <!-- 功能实现区/end -->

            <!-- 按钮区/start -->
            <div class="rq_button_box_flexed">
                <input type="submit" name="btnSave" value="确定保存" onclick="return fCheckInput();" id="btnSave" class="rq_button_l" />
                <input id="Reset1" type="reset" value="重新输入" class="rq_button_c" />
                <a href="javascript:history.back(-1)" class="rq_link_r">返回列表</a>
            </div>
            <!-- 按钮区/end -->
        </div>
    </div>
</form>
</body>
</html>
