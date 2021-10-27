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
    <title>系统菜单管理</title>
    <%@include file="../IncludeFile/CssAndJs.jspf" %>
    <script type="text/javascript">
        /**
         * 编辑一级菜单
         */
        function fEditOneMenu() {
            var _focusMenuId = $("#hdFocusMenuId").val();
            if (_focusMenuId == "") {
                layer.msg("请选择一级菜单后再执行此操作。");
            } else {
                location.href = "${path}/SysOneLevelMenu/EditMenu/" + _focusMenuId;
            }
        }

        /**
         * 删除一级菜单
         */
        function fDelOneMenu() {
            var _focusMenuId = $("#hdFocusMenuId").val();
            if (_focusMenuId == "") {
                layer.msg("请选择一级菜单后再执行此操作。");
            } else {
                if (confirm("你确定要删除此菜单吗？（请谨慎使用）")) {
                    var index = layer.load(2, {shade: true, shade: 0.1}); //0代表加载的风格，支持0-2
                    $.get("${path}/SysOneLevelMenu/DelMenu/" + _focusMenuId, function (result) {
                        layer.msg(result.msg);
                        if (result.code == "1") {
                            location.href = location.href;
                        }
                        layer.close(index);
                    });
                }
            }
        }

        /**
         * 删除二级菜单
         */
        function fDelTwoMenu() {
            var postUrl = "${path}/SysTwoLevelMenu/DelMenu";
            var options = {
                beforeSubmit: function(){
                    return fExistSelect("你确定要删除指定的二级菜单吗？(请谨慎使用)");
                },
                url: postUrl,
                success: function (json) {
                    layer.msg(json.msg);
                    layer.closeAll();
                    if (json.code == 1){
                        location.href=location.href;
                    }
                },//提交后的回调函数
                dataType: 'json', timeout: 6000
            };
            $("#form1").ajaxSubmit(options);
            return false;//阻止表单默认提交
        }

        /**
         * 更新二级菜单的排序号
         */
        function fUpdateTwoMenuSort() {
            var postUrl = "${path}/SysTwoLevelMenu/SaveMenuSort";
            var options = {
                beforeSubmit: function(){
                    return confirm("你确定要更新所有二级菜单的排序号吗？");
                },
                url: postUrl,
                success: function (json) {
                    layer.msg(json.msg);
                    layer.closeAll();
                    if (json.code == 1){
                        location.href=location.href;
                    }
                },//提交后的回调函数
                dataType: 'json', timeout: 6000
            };
            $("#form1").ajaxSubmit(options);
            return false;//阻止表单默认提交
        }

        /**
         * 验证是否有选择二级菜单
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
                layer.msg("你未选择二级菜单，不能执行此操作。");
                return false;
            }
        }
    </script>
</head>
<body class="body_main_page">
    <input type="hidden" id="hdFocusMenuId" value="${pageFocusMenuId}" />
    <div class="rq_page_frame_t">
        <div class="rq_page_navigation">
            <div class="rq_page_navigation_l">
                <a href="${path}/Main/UserMainPage">主页</a> / 系统配置 / 系统菜单管理
            </div>
            <div class="rq_page_navigation_r">
                <div class="rq_button_box_nopadding">
                    <input type="button" class="rq_button_btm_l" value="新增菜单" onclick="location.href='${path}/SysTwoLevelMenu/AddMenu'" />
                    <input type="button" name="btnUpdateSort" value="更新排序号"
                           onclick="fUpdateTwoMenuSort()" id="btnUpdateSort"
                           title="更新列表中所有的二级菜单排序号" class="rq_button_btm_c"/>
                    <input type="button" name="btnDelPermissions" value="删除菜单" onclick="fDelTwoMenu();"
                           id="btnDelPermissions" title="删除指定的二级菜单" class="rq_button_btm_r"/>
                </div>
            </div>
        </div>
    </div>

    <div class="rq_page_frame_l_h_perm">
        系统一级菜单
        <br />
        <a class="rq_hlink_1" href="${path}/SysOneLevelMenu/AddMenu">新增</a>
        <span class="rq_hlink_spline">|</span>
        <a onclick="fEditOneMenu();" href="javascript:void(0);" class="rq_hlink_1">修改</a>
        <span class="rq_hlink_spline">|</span>
        <a onclick="fDelOneMenu();" href="javascript:void(0);" class="rq_hlink_1">删除 </a>
    </div>

    <div class="rq_page_frame_l_sysmenu">
        <c:forEach items="${listSysOneLevelMenu}" var="temp" varStatus="index">
            <c:choose>
                <c:when test="${pageFocusMenuId.equals(temp.id)}">
                    <a title='查看二级菜单' id='role${temp.id}' href='${path}/SysOneLevelMenu/ManageList?menuid=${temp.id}' class='rq_page_sysmenu_item_focus'>
                            ${temp.managename}(${temp.sort})
                    </a>
                </c:when>
                <c:otherwise>
                    <a title='查看二级菜单' id='role${temp.id}' href='${path}/SysOneLevelMenu/ManageList?menuid=${temp.id}' class='rq_page_sysmenu_item'>
                            ${temp.managename}(${temp.sort})
                    </a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>

    <div class="rq_page_frame_r" style="left:200px;">
        <div class="rq_page_cont_box">
            <!-- 数据列表/start -->
            <form id="form1" method="post" action="${path}/SysTwoLevelMenu/SaveEditSort">
                <table border="0" class="page_table_list">
                    <tr>
                        <td class="header" style="width:30px;">
                            <input id="cboxSelectAll" type="checkbox" onclick="newCommon.pfSelectAll(this.id);"/>
                        </td>
                        <td class="header" style="width:70px;">排序号</td>
                        <td class="header" style="width:150px;">菜单名称</td>
                        <td class="header_left">&nbsp;链接地址</td>
                        <td class="header" style="width:70px;">编号代码</td>
                        <td class="header" style="width:40px;">操作</td>
                    </tr>
                    <c:forEach items="${listSysTwolevelmenu}" var="temp">
                        <tr class="itemtr">
                            <td><input name="menuId" type="checkbox" value="${temp.id}"/></td>
                            <td>
                                <input name="menuSortId" type="hidden" value="${temp.id}">
                                <input name="menuSort" type="text" value="${temp.sort}" class="pub_input_all_sort" style="width:50px;"/>
                            </td>
                            <td>${temp.menuName}</td>
                            <td class="td_left">&nbsp;${temp.pageUrl}</td>
                            <td>${temp.menuCode}</td>
                            <td><a class="rq_hlink_1"
                                   href="${path}/SysTwoLevelMenu/EditMenu/${temp.id}">编辑</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </form>
            <!-- 数据列表/end -->
        </div>
    </div>
</body>
</html>
