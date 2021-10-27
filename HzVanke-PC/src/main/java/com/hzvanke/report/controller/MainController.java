package com.hzvanke.report.controller;

import com.hzvanke.report.pojo.SysOnelevelmenu;
import com.hzvanke.report.pojo.SysTwolevelmenu;
import com.hzvanke.report.service.face.SysRoleMenuService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * 系统主入口
 */
@Controller
@RequestMapping("/Main/")
public class MainController extends BaseController {

    // 日志打印
    private static Logger logger = Logger.getLogger(MainController.class.getName());
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    //region 系统主界面入口

    /**
     * 登录之后的页面 需要查询当前用户的角色查找权限进行菜单布置
     *
     * @return
     */
    @RequestMapping("Default")
    public ModelAndView defaultJsp() {
        ModelAndView view = new ModelAndView();
        view.setViewName("Main/Default");
        view.addObject("systemMenuString", this.GetUserMenuString());
        view.addObject("currentUserName", this.getCurrentUser().getUserName());
        return view;
    }

    /**
     * 获取当前用户的菜单信息
     *
     * @return
     */
    private String GetUserMenuString() {
        StringBuilder _strMenuString = new StringBuilder();
        int _nMenuIndex = 1;
        //获取一级菜单信息
        java.util.List<SysOnelevelmenu> _listSysOneLevelMenu = sysRoleMenuService.findOneLevelMenuByRoleid(this.getCurrentUserRole().getId());
        java.util.List<SysTwolevelmenu> _listSysTwoLevelMenu = this.getCurrentUserTwoLevelMenu();
        Collections.sort(_listSysTwoLevelMenu, new Comparator<SysTwolevelmenu>() {
            /**
             * 排序
             * @param p1
             * @param p2
             * @return
             */
            public int compare(SysTwolevelmenu p1, SysTwolevelmenu p2) {
                if (p1.getSort() > p2.getSort()) {
                    return 1;
                }
                if (p1.getSort() == p2.getSort()) {
                    return 0;
                }
                return -1;
            }
        });

        _strMenuString.append("<ul id='rq_left_menu'>");
        for (int i = 0; i < _listSysOneLevelMenu.size(); i++) {
            //输出一级
            _strMenuString.append("<li onclick='Rq_Menu_Change(&quot;" + _nMenuIndex + "&quot;);'>");
            _strMenuString.append("<div class='rq_left_menu_title_box " + _listSysOneLevelMenu.get(i).getClassname() + "'>");
            _strMenuString.append("<div class='rq_left_menu_title'>" + _listSysOneLevelMenu.get(i).getMenuname() + "</div>");
            if (i == 0) {
                _strMenuString.append("<div arrow='true' direction='open' id='menu_item_direction_" + _nMenuIndex + "' class='rq_left_menu_direction_down'></div>");
            } else {
                _strMenuString.append("<div arrow='true' direction='close' id='menu_item_direction_" + _nMenuIndex + "' class='rq_left_menu_direction_up'></div>");
            }
            _strMenuString.append("</div>");
            _strMenuString.append("</li>");
            //计算输出二级
            String _strTypeId = _listSysOneLevelMenu.get(i).getId();
            java.util.List<SysTwolevelmenu> _listMySysTwoLevelMenu = _listSysTwoLevelMenu.stream().filter(data -> data.getOneLevelMenuid().equals(_strTypeId)).collect(Collectors.toList());
            if (i == 0) {
                _strMenuString.append("<li id='menu_item_content_" + _nMenuIndex + "' class='rq_left_menu_content'>");
            } else {
                _strMenuString.append("<li id='menu_item_content_" + _nMenuIndex + "' class='rq_left_menu_content' style='display:none;'>");
            }
            for (SysTwolevelmenu _mSysTwoLevelMenu : _listMySysTwoLevelMenu) {
                if (_mSysTwoLevelMenu.getPageUrl().indexOf("?") == -1) {
                    _mSysTwoLevelMenu.setPageUrl(_mSysTwoLevelMenu.getPageUrl() + "?rand=" + java.util.UUID.randomUUID().toString().replaceAll("-", ""));
                } else {
                    _mSysTwoLevelMenu.setPageUrl(_mSysTwoLevelMenu.getPageUrl() + "&rand=" + java.util.UUID.randomUUID().toString().replaceAll("-", ""));
                }
                _strMenuString.append("<a mylink='true' id='menu_small_item_" + _mSysTwoLevelMenu.getId() + "' class='" + _mSysTwoLevelMenu.getClassName() + "' target='" + _mSysTwoLevelMenu.getTarget() + "' href='" + _mSysTwoLevelMenu.getPageUrl() + "' onclick='Rq_Menu_ChangeItem(&quot;" + _mSysTwoLevelMenu.getId() + "&quot;);'>" + _mSysTwoLevelMenu.getMenuName() + "</a>");
            }
            _strMenuString.append("</li>");
            _nMenuIndex += 1;
        }
        _strMenuString.append("</ul>");
        return _strMenuString.toString();
    }
    //endregion

    //region 用户首页（根据权限进行重定向）
    @RequestMapping("UserMainPage")
    public ModelAndView userMainPage(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        //根据当前用户的角色转到不同的页面上
        java.util.List<SysTwolevelmenu> _listHkTwoLevelMenu = this.getCurrentUserTwoLevelMenu();
        String pageIFrameUrl = request.getServletContext().getContextPath() + _listHkTwoLevelMenu.get(0).getPageUrl();
        view.setViewName("redirect:" + pageIFrameUrl);
        return view;
    }
    //endregion


}
