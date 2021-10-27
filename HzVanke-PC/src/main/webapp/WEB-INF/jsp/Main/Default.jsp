<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/31
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>杭州万科大屋顶信息管理系统V3.1.1</title>
    <%@ include file="../IncludeFile/CssAndJs.jspf" %>
    <style type="text/css">
        body .order-class .layui-layer-title {
            background: #009688;
            color: #fff;
            border: none;
        }

        body .order-class .layui-layer-content span {
            font-size: 14px;
            font-family: '微软雅黑';
            padding-left: 70px;
            line-height: 150px;
            cursor: pointer;
            color: #009688
        }
    </style>
    <script type="text/javascript">
        /**
         * 切换一级菜单的点击事件
         * @param {"一级菜单的编号"} index
         */
        function Rq_Menu_Change(index) {
            var _directionId = "menu_item_direction_" + index;
            var _contentId = "menu_item_content_" + index;

            var _heightBox = $("#hzrq_box_menu").height();//总高度
            var _heightItem = 0;
            $("div[arrow='true']").each(function () {
                _heightItem += $(this).height();
            });
            //设置显示区域高度
            $("#" + _contentId).height(_heightBox - _heightItem);

            $(".rq_left_menu_content").not("#" + _contentId).each(function () {
                $(this).hide();
            });
            $("div[arrow='true']").not("#" + _directionId).each(function () {
                $(this).attr("direction", "close");
                $(this).attr("class", "rq_left_menu_direction_up");
            });

            if ($("#" + _directionId).attr("direction") == "open") {
                $("#" + _directionId).attr("direction", "close");
                $("#" + _directionId).attr("class", "rq_left_menu_direction_up");
            }
            else {
                $("#" + _directionId).attr("direction", "open");
                $("#" + _directionId).attr("class", "rq_left_menu_direction_down");
            }
            $("#" + _contentId).toggle();
        }

        /**
         * 小菜单点击事件处理
         * @param id
         */
        function Rq_Menu_ChangeItem(id) {
            $("a[mylink='true']").each(function () {
                var _controlid = "menu_small_item_" + id;
                if ($(this).attr("id") == _controlid) {
                    $(this).css("backgroundColor", "#009688");//阿里 00c1de
                } else {
                    $(this).css("backgroundColor", "");
                }
            });
        }

        /**
         * 显示固定右边栏的提示信息
         * @param {"层ID"} id
         */
        function Rq_Show_Absolute_Info(id) {
            $("#" + id).fadeToggle();
        }

        /**
         * 返回首页
         */
        function Rq_Redirect_Home() {
            $("#mainFrame").attr("src", "${path}/Main/UserMainPage");
        }

        /**
         * 编辑信息
         */
        function Rq_Edit_Current() {
            $("#mainFrame").attr("src", "${path}/SysUser/EditCurrentUser");
        }

        /**
         * 退出登陆
         */
        function Rq_QuitLogin() {
            if (confirm("你确定要退出系统吗？")) {
                location.href = "${path}/Login/login_out";
            }
        }

        /**
         * 刷新当前页面
         */
        function Rq_Reffresh_NowPage() {
            document.getElementById('mainFrame').contentWindow.location.reload(true);
        }
    </script>
</head>
<body>
<!-- 页头部分/start -->
<div class="rq_box_head">
    <div class="rq_head_f">
        <div class="rq_head_f_item_logo"></div>
        <div class="rq_head_f_item_sp"></div>
        <div class="rq_head_f_item rq_icon_global" title="返回首页" onclick="Rq_Redirect_Home();">首页</div>
        <div class="rq_head_f_item rq_icon_refresh" title="刷新" onclick="Rq_Reffresh_NowPage();">刷新</div>
        <div class="rq_head_f_item_empty">
            &nbsp;
        </div>
        <div onclick="Rq_Edit_Current();" class="rq_head_f_item rq_icon_user" title="当前登陆用户">
            ${ currentUserName }
        </div>
        <div onclick="Rq_QuitLogin();" class="rq_head_f_item rq_icon_exit" title="退出系统">
            退出
        </div>
        <div class="rq_head_f_item rq_icon_systeminfo" title="系统信息" onclick="Rq_Show_Absolute_Info('info_system');">
            &nbsp;
        </div>
    </div>
    <%--声音播放功能--%>
</div>
<!-- 页头部分/end -->

<!-- 页菜单部分/start -->
<div id="hzrq_box_menu" class="rq_box_menu">
    ${ systemMenuString }
</div>
<!-- 页菜单部分/end -->

<!-- 页内部部分/start -->
<div id="hzrq_box_cont" class="rq_box_cont">
    <!-- 主操作界面/start -->
    <iframe onload="changeFrameHeight()" allowfullscreen id="mainFrame" name="mainFrame" frameborder="no"
            scrolling="auto" hidefocus src="${path}/Main/UserMainPage"
            style="width:100%;"></iframe>
    <script type="text/javascript">
        /**
         * 改变iframe的高度
         */
        function changeFrameHeight() {
            var ifm = document.getElementById("mainFrame");
            ifm.height = $("#hzrq_box_cont").height();

        }

        //windows的窗口变化事件
        window.onresize = function () {
            changeFrameHeight();
        }
    </script>
    <!-- 主操作界面/end -->
</div>
<!-- 页内部部分/end -->

<!-- 特殊提示部分/start -->
<div onclick="Rq_Show_Absolute_Info(this.id);" id="info_system" class="rq_info_absolute_r">
    <div class="rq_info_title">版本信息</div>
    <div class="rq_info_cont_box">
        <div>当前版本：2019_V3.1.1</div>
        <div>基于框架：JDK 8.0</div>
        <br/>
    </div>
    <div class="rq_info_title">关于版权</div>
    <div class="rq_info_cont_box">
        <div class="rq_info_cont_backbox">Copyright &copy;2019-2025 杭州万科 版权所有，系统由杭州瑞祁广智信息技术有限公司提供技术支持</div>
    </div>
</div>
<!-- 特殊提示部分/end -->
</body>
</html>
