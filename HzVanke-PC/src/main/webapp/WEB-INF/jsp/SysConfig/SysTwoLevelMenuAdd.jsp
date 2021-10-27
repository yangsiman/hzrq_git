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
    <title>新增二级菜单</title>
    <%@include file="../IncludeFile/CssAndJs.jspf" %>
    <script type="text/javascript">
        function fCheckInput() {
            if ($("#ddlPermissionsType").val() == "") {
                layer.tips('请选择所属一级菜单。', '#ddlPermissionsType', { tips: [2, '#ff4401'] });
                return false;
            }

            if ($("#txtName").val() == "") {
                layer.tips('请输入菜单名称。', '#txtName', { tips: [2, '#ff4401'] });
                return false;
            }

            if ($("#txtCode").val() == "") {
                layer.tips('请输入编号代码。', '#txtCode', { tips: [2, '#ff4401'] });
                return false;
            }

            if (!_numberreg.test($("#txtSort").val())) {
                layer.tips('请输入排序号（只能为正整数）。', '#txtSort', { tips: [2, '#ff4401'] });
                return false;
            }

            if ($("#txtTarget").val() == "") {
                layer.tips('请输入打开方式。', '#txtTarget', { tips: [2, '#ff4401'] });
                return false;
            }

            if ($("#txtClassName").val() == "") {
                layer.tips('请输入小图标样式名称。', '#txtClassName', { tips: [2, '#ff4401'] });
                return false;
            }

            if ($("#txtPageUrl").val() == "") {
                layer.tips('请输入链接地址。', '#txtPageUrl', { tips: [2, '#ff4401'] });
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

        function fGetMaxSort(onelevelmenuid) {
            if (onelevelmenuid != "") {
                //获取最大排序号
                $.get("${path}/SysTwoLevelMenu/GetMaxSort", { "onelevelmenuid": onelevelmenuid, "rand": newCommon.newGuid() }, function (result) {
                    if(result.code=='1') {
                        $("#txtSort").val(result.msg);
                    }
                    else{
                        layer.msg(result.msg);
                    }
                });
            }
            else {
                $("#txtSort").val("");
            }
        }
    </script>
</head>
<body>
    <form id="form1" action="/SysTwoLevelMenu/SaveAddMenu" method="post">
        <div class="rq_page_navigation_inpage">
            <a href="${path}/Main/UserMainPage">主页</a> / 系统配置 / <a href="${path}/SysOneLevelMenu/ManageList">系统菜单管理</a> / 新增二级菜单
        </div>
        <div class="rq_from">
            <div class="rq_from_back">
                <!-- 功能实现区/start -->
                <div class="rq_form_box">
                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">所属一级菜单</div>
                        <div class="rq_form_col_r">
                            <select name="oneLevelMenuid" id="ddlPermissionsType" onchange="fGetMaxSort(this.value);">
                                <option value=""></option>
                                <c:forEach items="${listSysOneLevelmenu}" var="temp" varStatus="index">
                                    <option value="${temp.id}">${temp.managename}</option>
                                </c:forEach>
                            </select>
                            <span class="rq_color_red">*</span>
                        </div>
                    </div>

                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">菜单名称</div>
                        <div class="rq_form_col_r">
                            <input name="menuName" type="text" id="txtName" class="pub_input1" />
                            <span class="rq_color_red">*</span>
                        </div>
                    </div>

                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">编号代码</div>
                        <div class="rq_form_col_r">
                            <input name="menuCode" type="text" maxlength="6" id="txtCode" class="pub_input1" />
                            <span class="rq_color_red">*</span><span class="rq_color_grey">代码只能为6位数字</span>
                        </div>
                    </div>

                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">排序号</div>
                        <div class="rq_form_col_r">
                            <input name="sort" type="number" id="txtSort" class="pub_input1" />
                            <span class="rq_color_red">*</span><span class="rq_color_grey">只能输入数字且为整数</span>
                        </div>
                    </div>

                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">打开方式</div>
                        <div class="rq_form_col_r">
                            <input name="target" type="text" id="txtTarget" class="pub_input1" />
                            <span class="rq_color_red">*</span><span class="rq_color_grey">如：_blank、iframe等</span>
                        </div>
                    </div>

                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">小图标样式名称</div>
                        <div class="rq_form_col_r">
                            <input name="className" type="text" id="txtClassName" class="pub_input1" style="width:90%;" />
                            <span class="rq_color_red">*</span>
                        </div>
                    </div>

                    <div class="rq_form_item_box">
                        <div class="rq_form_col_1">链接地址</div>
                        <div class="rq_form_col_r">
                            <input name="pageUrl" type="text" id="txtPageUrl" class="pub_input1" style="width:90%;" />
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
