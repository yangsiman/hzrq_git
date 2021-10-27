<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/31
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色菜单权限配置</title>
    <%@include file="../IncludeFile/CssAndJs.jspf" %>
</head>
<body>
<form method="post" action="${path}/Role/configRolePerms?roleId=${sysRole.id}" id="form1">
    <div class="rq_page_navigation_inpage">
        <a href="${path}/Main/UserMainPage">主页</a> / 系统配置 / <a href="${path}/SysRole/ManageList">系统角色信息</a> / 角色权限配置 /
        <span class="rq_color_red">${sysRole.roleName}</span>
    </div>
    <div class="rq_from">
        <div class="rq_from_back">
            <!-- 功能实现区/start -->
            <div class="rq_form_box">
                <table border="0" class="page_table_list">
                    <c:forEach items="${mapData}" var="oneLevelMenu" varStatus="index">
                        <tr>
                            <td style="background-color:#fcfcfc;font-weight:bold;text-align:left">
                                &nbsp;&nbsp;${oneLevelMenu.key}
                            </td>
                        </tr>
                        <tr class="itemtr">
                            <td>
                                <div class="page_radio_frame">
                                    <table id="rpPermList_cboxPerm_0">
                                        <tr>
                                            <c:forEach items="${oneLevelMenu.value}" var="p" varStatus="pi">
                                            <td>
                                                <c:set var="isChecked" value="false"></c:set>
                                                <c:forEach items="${listMySysTwolevelmenu}" var="rp" varStatus="rpi">
                                                    <c:if test="${p.id == rp.id}">
                                                        <c:set var="isChecked" value="true"></c:set>
                                                    </c:if>
                                                </c:forEach>
                                                <c:choose>
                                                    <c:when test="${isChecked eq true}">
                                                        <input id="perm${p.id}" type="checkbox" name="boxSelect"
                                                               checked="true" value="${p.id}"/><label
                                                            for="perm${p.id}">${p.menuName}</label>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input id="perm${p.id}" type="checkbox" name="boxSelect"
                                                               value="${p.id}"/><label
                                                            for="perm${p.id}">${p.menuName}</label>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <c:if test="${(pi.index+1) % 8==0}">
                                        </tr>
                                        <tr>
                                            </c:if>
                                            </c:forEach>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <!-- 功能实现区/end -->

            <!-- 按钮区/start -->
            <div class="rq_button_box_flexed">
                <input type="button" name="btnSaveConfig" value="保存配置" onclick="return saveConfig('${sysRole.id}');"
                       id="btnSaveConfig" class="rq_button_l"/>
                <input id="Reset1" type="reset" value="重新输入" class="rq_button_c"/>
                <a href="${path}/SysRole/ManageList?roleid=${sysRole.id}" class="rq_link_r">返回列表</a>
            </div>
            <script type="text/javascript">
                function saveConfig(roleId) {
                    var kk = $("input[type='checkbox']:checked");
                    if (kk.length == 0) {
                        layer.msg("你未配置用户菜单，不能执行此操作。");
                        return;
                    }
                    var codes = "";
                    for (var i = 0; i < kk.length; i++) {
                        codes += "," + kk[i].value;
                    }
                    codes = codes.substring(1);
                    if (confirm("你确定要保存此角色的配置信息吗？")) {
                        var index = layer.load(2, {shade: true, shade: 0.1}); //0代表加载的风格，支持0-2
                        $.post("${path}/SysRole/SaveConfigRoleMenu", {
                            "roleid": roleId,
                            "twolevelmenuids": codes
                        }, function (result) {
                            layer.close(index);
                            if (result.code == 1) {
                                layer.msg(result.msg, function () {
                                    location.href = "${path}/SysRole/ManageList?roleid=" + roleId;
                                });
                            } else {
                                layer.msg(result.msg);
                            }

                        });
                    }
                }
            </script>
            <!-- 按钮区/end -->
        </div>
    </div>
</form>
</body>
</html>
