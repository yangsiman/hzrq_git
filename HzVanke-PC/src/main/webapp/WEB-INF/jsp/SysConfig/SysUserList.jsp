<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/1
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.hzvanke.report.sysenum.EnumSysUser" %>
<html>
<head>
    <title>系统用户管理</title>
    <%@ include file="../IncludeFile/CssAndJs.jspf" %>
    <script type="text/javascript">
        /**
         * 验证是否有选择数据
         * @param msg 操作提示
         * @returns {boolean}
         */
        function fExistSelect(msg) {
            if (newCommon.pfValidDel("cboxSelectAll")) {
                if (confirm(msg)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                layer.msg("你未选择数据，不能执行此操作。");
                return false;
            }
        }

        /**
         * 更新用户状态
         * @param status 1允许登陆，2禁止登陆
         */
        function fUpdateUserStatus(status) {
            var postUrl = "${path}/SysUser/UpdateUserStatus?status=" + status;
            var options = {
                beforeSubmit: function () {
                    var msg = "";
                    if (status == "1") {
                        msg = "你确定要允许选中的用户登陆系统吗？";
                    } else {
                        msg = "你确定要禁止选中的用户登陆系统吗？";
                    }
                    return fExistSelect(msg);
                },
                url: postUrl,
                success: function (json) {
                    layer.closeAll();
                    layer.msg(json.msg, {time: 2000}, function () {
                        if (json.code == 1) {
                            location.href = location.href;
                        }
                    });
                },//提交后的回调函数
                dataType: 'json', timeout: 6000
            };
            $("#dataForm").ajaxSubmit(options);
            return false;//阻止表单默认提交
        }

        function fDelUser() {
            console.log("dsas")
            var postUrl = "${path}/SysUser/DelUser";
            var options = {
                beforeSubmit: function () {
                    return fExistSelect("你确定要删除选中的用户吗？（请谨慎使用）");
                },
                url: postUrl,
                success: function (json) {
                    layer.closeAll();
                    layer.msg(json.msg, {time: 2000}, function () {
                        if (json.code == 1) {
                            location.href = location.href;
                        }
                    });
                },//提交后的回调函数
                dataType: 'json', timeout: 6000
            };
            $("#dataForm").ajaxSubmit(options);
            return false;//阻止表单默认提交
        }
        function updateUntying(id, name) {
            if (confirm("确认解除该用户(" + name + ")的微信绑定吗？")) {
                $.ajax({
                    url: "${path}/SysUser/UpdateOpenid",
                    data: {
                        "id": id
                    },
                    type: "post",
                    success: function (data) {
                        if (data.code == 0) {
                            layer.msg("操作成功！", {time: 2000}, function () {
                                location.reload();
                            })
                        }else{
                            layer.msg("操作失败！")
                        }
                    }
                })
            }
        }

    </script>
</head>
<body>
<div class="rq_page_navigation_inpage">
    <a href="${path}/Main/UserMainPage">主页</a> / 系统配置 / 系统用户管理
</div>
<div class="rq_from">
    <!-- 查询框/start -->
    <div class="rq_page_search_box">
        <form id="searchForm" action="${path}/SysUser/ManageList" method="post">
            <span>登陆帐号：</span>
            <input value="${sysUserSearch.userCode}" name="userCode" type="text" id="txtUserCode"
                   class="pub_input_search"/>
            <span>用户姓名</span>
            <input value="${sysUserSearch.userName}" name="userName" type="text" id="txtName" class="pub_input_search"/>
            <span>系统角色</span>
            <select name="role.id" id="ddlRole" style="height:28px;width:120px;">
                <option value=""></option>
                <c:forEach items="${listSysRole}" var="sysrole" varStatus="index">
                    <option value="${sysrole.id}"
                            <c:if test="${sysrole.id.equals(sysUserSearch.role.id)}">selected="selected"</c:if> >
                            ${sysrole.roleName}
                    </option>
                </c:forEach>
            </select>
            <input type="submit" name="btnSearch" value="搜索" id="btnSearch" class="rq_button_search"/>
            <a class="rq_link_search_gray" href="${path}/SysUser/AddUser">注册用户</a>
            <a class="rq_link_search_gray" href="${path}/SysUser/copy">测试</a>
        </form>
    </div>
    <!-- 查询框/end -->
    <div class="rq_pub_line10"></div>
    <!-- 数据显示/start  -->
    <form id="dataForm" method="post">
        <table border="0" class="page_table_list">
            <tr>
                <td class="header" style="width:30px;">序号</td>
                <td class="header" style="width:30px;">
                    <input name="cboxSelectAll" id="cboxSelectAll" type="checkbox"
                           onclick="newCommon.pfSelectAll(this.id);"/>
                </td>
                <td class="header_left">&nbsp;用户姓名</td>
                <td class="header" style="width:100px;">登陆帐号</td>
                <td class="header" style="width:100px;">手机号码</td>
                <td class="header" style="width:100px;">用户角色</td>
                <td class="header" style="width:70px;">用户状态</td>
                <td class="header" style="width:80px;">操作</td>
            </tr>
            <c:forEach items="${page.list}" var="sysuser" varStatus="index">
                <c:choose>
                    <c:when test="${index.index%2==0}"><tr class="itemtr"></c:when>
                    <c:otherwise><tr class="itemsptr"></c:otherwise>
                </c:choose>
                <td>${index.index+1}</td>
                <td>
                    <c:if test="${sysuser.userCode!='admin'}">
                        <input name="sysuserid" type="checkbox" value="${sysuser.id}"/>
                    </c:if>
                </td>
                <td class="td_left">&nbsp;${sysuser.userName}</td>
                <td>${sysuser.userCode}</td>
                <td>${sysuser.mobile}</td>
                <td>${sysuser.role.roleName}</td>
                <td>${EnumSysUser.getStatusFont(sysuser.status)}</td>
                <td>
                    <a class="rq_hlink_1" href="${path}/SysUser/EditUser/${sysuser.id}">编辑</a>
                    <c:if test="${sysuser.wxOpenid!=null and sysuser.wxOpenid!=''}">
                        <a class="rq_hlink_1" onclick="updateUntying('${sysuser.id}','${sysuser.userName}')"
                           style="color: orangered;cursor: pointer;">解绑</a>
                    </c:if>
                </td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <div class="rq_pub_line80"></div>
    <!-- 数据显示/end  -->

    <!-- 功能按钮及分页区域/start -->
    <div class="rq_page_paging_flexed">
        <div class="rq_page_paging_flexed_l">
            <input type="button" name="btnLock" value="锁定" onclick="return fUpdateUserStatus('2');" id="btnLock"
                   class="rq_button_btm_l"/>
            <input type="button" name="btnNormal" value="开放" onclick="return fUpdateUserStatus('1');" id="btnNormal"
                   class="rq_button_btm_c"/>
            <input type="button" name="btnDelUser" value="删除用户" onclick="return fDelUser();" id="btnDelUser"
                   class="rq_button_btm_r"/>
        </div>
        <div class="rq_page_paging_flexed_r">
            ${page.pageUrl}
        </div>
    </div>
    <!-- 功能按钮及分页区域/end -->
</div>
</body>
</html>
