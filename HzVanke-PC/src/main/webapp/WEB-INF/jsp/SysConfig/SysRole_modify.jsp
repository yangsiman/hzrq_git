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
    <title>编辑系统角色信息</title>
    <%@include file="../IncludeFile/CssAndJs.jspf" %>
    <script type="text/javascript">
        function fCheckInput() {
            if ($("#roleName").val() == "") {
                layer.tips('请输入角色名称。', '#roleName', { tips: [2, '#ff4401'] });
                return false;
            }

            if ($("#roleCode").val() == "") {
                layer.tips('请输入角色编码（只能为正整数）。', '#roleCode', { tips: [2, '#ff4401'] });
                return false;
            }

            if (!_numberreg.test($("#sort").val())) {
                layer.tips('请输入排序号（只能为正整数）。', '#sort', { tips: [2, '#ff4401'] });
                return false;
            }

            if (confirm("你确定要保存此角色信息吗？")) {
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
<form method="post" action="${path}/SysRole/SaveModifyRole" id="form1">
    <div class="rq_page_navigation_inpage">
        <a href="${path}/Main/UserMainPage">主页</a> / 系统配置 / <a href="${path}/SysRole/ManageList">角色权限配置</a> / 编辑系统角色
    </div>
    <div class="rq_from">
        <div class="rq_from_back">
            <!-- 功能实现区/start -->
            <input type="hidden" name="id" value="${sysRole.id}" />
            <div class="rq_form_box">
                <div class="rq_form_item_box">
                    <div class="rq_form_col_1">角色名称</div>
                    <div class="rq_form_col_r">
                        <input name="roleName" value="${sysRole.roleName}" type="text" id="roleName" readonly class="pub_input1" />
                        <span class="rq_color_red">*</span><span class="rq_color_grey">角色的名称</span>
                    </div>
                </div>

                <div class="rq_form_item_box">
                    <div class="rq_form_col_1">角色编码</div>
                    <div class="rq_form_col_r">
                        <input name="roleCode" type="number" value="${sysRole.roleCode}" id="roleCode" class="pub_input1" />
                        <span class="rq_color_red">*</span><span class="rq_color_grey">只能输入数字且为整数</span>
                    </div>
                </div>

                <div class="rq_form_item_box">
                    <div class="rq_form_col_1">排序号</div>
                    <div class="rq_form_col_r">
                        <input name="sort" type="number" value="${sysRole.sort}" id="sort" class="pub_input1" />
                        <span class="rq_color_red">*</span><span class="rq_color_grey">只能输入数字且为整数</span>
                    </div>
                </div>

                <div class="rq_form_item_box">
                    <div class="rq_form_col_1">角色备注说明</div>
                    <div class="rq_form_col_r">
                        <input name="description" type="text" value="${sysRole.description}" id="description" class="pub_input1" style="width:50%;" />
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
