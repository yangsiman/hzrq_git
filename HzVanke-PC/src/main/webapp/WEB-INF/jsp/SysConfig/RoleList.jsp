<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/31
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.hzvanke.report.sysenum.EnumSysUser" %>
<html>
<head>
    <title>角色及所属人员信息查看</title>
    <%@include file="../IncludeFile/CssAndJs.jspf" %>
    <script type="text/javascript">
        /**
         * 编辑角色信息
         */
        function fEditRole() {
            var _focusRoleId = $("#hdFocusRoleId").val();
            if (_focusRoleId == "") {
                layer.msg("请选择角色后再执行此操作。");
            } else {
                location.href = "${path}/SysRole/ModifyRole/" + _focusRoleId;
            }
        }

        /**
         * 删除系统角色
         */
        function fDelRole() {
            var _focusRoleId = $("#hdFocusRoleId").val();
            if (_focusRoleId == "") {
                layer.msg("请选择角色后再执行此操作。");
            } else if(_focusRoleId=='role100001'){
                layer.msg("系统管理员不允许删除！");
            }else {
                if (confirm("你确定要删除此角色吗？")) {
                    var index = layer.load(2, {shade: true, shade: 0.1}); //0代表加载的风格，支持0-2
                    $.get("${path}/SysRole/DelRole/" + _focusRoleId, function (result) {
                        layer.msg(result.msg);
                        if (result.code == "1") {
                            location.href = location.href;
                        }
                        layer.close(index);
                    });
                }
            }
        }

    </script>
</head>
<body class="body_main_page">
<input type="hidden" id="hdFocusRoleId" value="${pageFocusRoleId}"/>
<div class="rq_page_frame_t">
    <div class="rq_page_navigation">
        <div class="rq_page_navigation_l">
            <a href="${path}/Main/UserMainPage">主页</a> / 系统配置 /角色权限配置
        </div>
        <div class="rq_page_navigation_r">

        </div>
    </div>
</div>
<div class="rq_page_frame_l_h_perm">系统角色信息</div>
<div class="rq_page_frame_l_h_perm">
    系统角色信息
    <br />
    <a class="rq_hlink_1" href="${path}/SysRole/AddRole">新增</a>
    <span class="rq_hlink_spline">|</span>
    <a onclick="fEditRole();" href="javascript:void(0);" class="rq_hlink_1">修改</a>
    <span class="rq_hlink_spline">|</span>
    <a onclick="fDelRole();" href="javascript:void(0);" class="rq_hlink_1">删除 </a>
</div>
<div class="rq_page_frame_l_sysmenu">
    <c:forEach items="${listSysRole}" var="temp" varStatus="index">
        <c:choose>
            <c:when test="${pageFocusRoleId.equals(temp.id)}">
                <a title='查看角色下属人员' id='role${temp.id}' href='${path}/SysRole/ManageList?roleid=${temp.id}'
                   class='rq_page_sysmenu_item_focus'>
                        ${temp.roleName}
                </a>
            </c:when>
            <c:otherwise>
                <a title='查看角色下属人员' id='role${temp.id}' href='${path}/SysRole/ManageList?roleid=${temp.id}'
                   class='rq_page_sysmenu_item'>
                        ${temp.roleName}
                </a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <br/>
    <div class="rq_button_box_nopadding" style="text-align:center;">
        <c:if test="${!pageFocusRoleId.equals('')}">
            <a href='${path}/SysRole/ConfigRoleMenu?roleid=${pageFocusRoleId}' class='rq_link' title='配置所选角色的系统权限'>配置角色权限</a>
        </c:if>
    </div>
</div>

<div class="rq_page_frame_r" style="left:200px;">
    <div class="rq_page_cont_box">
        <!-- 数据列表/start -->
        <table border="0" class="page_table_list">
            <tr>
                <td class="header" style="width:40px;">序号</td>
                <td class="header_left">&nbsp;用户姓名</td>
                <td class="header" style="width:120px;">用户角色</td>
                <td class="header" style="width:120px;">登陆帐号</td>
                <td class="header" style="width:100px;">用户状态</td>
                <td class="header" style="width:200px;">电子邮箱</td>
            </tr>
            <c:forEach items="${pageData.list}" var="temp" varStatus="index">
                <c:choose>
                    <c:when test="${index.index%2==0}">
                        <tr class="itemtr">
                    </c:when>
                    <c:otherwise>
                        <tr class="itemsptr">
                    </c:otherwise>
                </c:choose>
                <td>${pageData.start+1+index.index}</td>
                <td class="td_left">&nbsp;${temp.userName}</td>
                <td>${temp.role.roleName}</td>
                <td>${temp.userCode}</td>
                <td>${EnumSysUser.getStatusFont(temp.status)}</td>
                <td>${temp.email}</td>
                </tr>
            </c:forEach>
        </table>
        <div class="rq_pub_line80"></div>
        <!-- 数据列表/end -->
    </div>
    <div class="rq_page_paging_flexed_role">
        <div class="rq_page_paging_flexed_r">
            ${pageData.pageUrl}
        </div>
    </div>
</div>
</body>
</html>
