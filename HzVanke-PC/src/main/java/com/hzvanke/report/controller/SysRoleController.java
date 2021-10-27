package com.hzvanke.report.controller;

import com.hzvanke.report.commons.PageUtil;
import com.hzvanke.report.commons.Result;
import com.hzvanke.report.commons.Utils;
import com.hzvanke.report.pojo.*;
import com.hzvanke.report.service.face.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/SysRole/")
public class SysRoleController extends BaseController{
    // 日志打印
    private static Logger logger = Logger.getLogger(SysRoleController.class.getName());

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysOneLevelMenuService sysOneLevelMenuService;
    @Autowired
    private SysTwoLevelMenuSevice sysTwoLevelMenuSevice;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    //region 角色菜单配置入口页

    /**
     * 获得所有的角色信息
     *
     * @return
     */
    @RequestMapping("ManageList")
    public ModelAndView pageManageList(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("SysConfig/RoleList");
        try {
            //获取所有角色数据
            List<SysRole> listSysRole = sysRoleService.findAll(this.getCurrentUserRole().getRoleName());

            //获取当前选中的角色ID，如果为空默认为第一个
            String strFocusRoleId = request.getParameter("roleid");
            if (StringUtils.isEmpty(strFocusRoleId)) {
                if (listSysRole.size() > 0) {
                    strFocusRoleId = listSysRole.get(0).getId();
                }
            }
            //获取角色对应的人员信息，并处理分页
            PageUtil pageData = null;
            String pageUrlString = "";
            if (!StringUtils.isEmpty(strFocusRoleId)) {
                SysUser sysUserSearch=new SysUser();
                SysRole role=new SysRole();
                role.setId(strFocusRoleId);
                sysUserSearch.setRole(role);
                pageData = sysUserService.findListByManage(request, 50, sysUserSearch,this.getCurrentUserRole().getRoleName());
            }

            //设置页面数据
            view.addObject("pageFocusRoleId", strFocusRoleId);//当前选中的角色ID
            view.addObject("listSysRole", listSysRole);//所有角色数据
            view.addObject("pageData", pageData);//角色所属人员数据
        } catch (Exception ex) {
            logger.error("处理数据异常",ex);

        }
        return view;
    }
    //endregion

    //region 角色菜单配置页入口

    /**
     * 角色菜单配置页入口
     *
     * @return
     */
    @RequestMapping("ConfigRoleMenu")
    public ModelAndView pageConfigRoleMenu(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("SysConfig/RoleMenuConfig");
        //所有一级和二级菜单数据分类存入map，供前台使用
        Map<String, List<SysTwolevelmenu>> mapData = new LinkedHashMap<>();
        List<SysOnelevelmenu> listSysOnelevelmenu = sysOneLevelMenuService.findAll();
        for (SysOnelevelmenu sysOnelevelmenu : listSysOnelevelmenu) {
            List<SysTwolevelmenu> listTwolevelmenu = sysTwoLevelMenuSevice.findByOneLevelMenuId(sysOnelevelmenu.getId());
            if (listTwolevelmenu != null && listTwolevelmenu.size() > 0)
                mapData.put(sysOnelevelmenu.getManagename(), listTwolevelmenu);
        }
        //获取当前角色所拥有的二级菜单权限
        String strRoleId = request.getParameter("roleid");
        List<SysTwolevelmenu> listMySysTwolevelmenu = sysRoleMenuService.findByRoleid(strRoleId);
        //获取当前角色信息
        SysRole sysRole = sysRoleService.findById(strRoleId);

        //设置数据
        view.addObject("mapData", mapData);
        view.addObject("listMySysTwolevelmenu", listMySysTwolevelmenu);
        view.addObject("sysRole", sysRole);

        return view;
    }
    //endregion

    //region 保存菜单配置项
    @RequestMapping("SaveConfigRoleMenu")
    @ResponseBody
    public Result saveConfigRoleMenu(HttpServletRequest request) {
        Result result = new Result();
        try {
            String strRoleId = request.getParameter("roleid");//角色ID
            String strTwoLevelMenuIds = request.getParameter("twolevelmenuids");
            //2.重新配置选中的信息。
            String[] arrayIds = strTwoLevelMenuIds.split(",");
            List<SysRoleMenu> listSysRoleMenu = new ArrayList<>();
            for (String strTwoLevelMenuId : arrayIds) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setId(Utils.CreateNewKeyid());
                sysRoleMenu.setRoleId(strRoleId);
                sysRoleMenu.setMenuId(strTwoLevelMenuId);
                listSysRoleMenu.add(sysRoleMenu);
            }
            if (sysRoleMenuService.add(listSysRoleMenu, strRoleId)) {
                result.setCode(1);
                result.setMsg("角色权限已配置成功。");
            } else {
                result.setCode(2);
                result.setMsg("配置权限时发生错误，操作失败。");
            }
        } catch (Exception ex) {
            logger.error("处理数据异常",ex);

            result.setCode(3);
            result.setMsg("接收数据时发生错误，操作失败。");
        }
        return result;
    }
    //endregion
    //endregion

    /**
     * 新增角色入口
     * 作者：高鹏   版本：v1.1
     *
     * @return
     * @prarm []
     * @date 2019/4/18
     * 修改信息：   修改人：   时间：
     */
    @RequestMapping("AddRole")
    public ModelAndView AddRole() {
        ModelAndView view = new ModelAndView("SysConfig/SysRole_add");

        int maxSort = sysRoleService.findMaxSort() + 1;
        view.addObject("maxSort", maxSort);
        return view;
    }

    /**
     * 新增系统角色
     * 作者：高鹏   版本：v1.1
     *
     * @return
     * @prarm [hkRole, response]
     * @date 2019/4/18
     * 修改信息：   修改人：   时间：
     */
    @RequestMapping("SaveAddRole")
    public void SaveAddRole(SysRole sysRole, HttpServletResponse response) {

        try {
            sysRole.setId(Utils.CreateNewKeyid());
            if (sysRoleService.add(sysRole) > 0) {
                Utils.alertAndForword(response, "数据已保存，操作成功。", "/SysRole/ManageList?roleid=" + sysRole.getId());
            } else {
                Utils.alertAndForword(response, "保存数据时发生错误，操作失败。", "/SysRole/AddRole");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            Utils.alertAndForword(response, "保存数据时发生错误，操作失败。", "/SysRole/AddRole");
        }
    }

    /**
     * 修改角色信息入口
     * 作者：高鹏   版本：v1.1
     *
     * @return
     * @prarm [id]
     * @date 2019/4/18
     * 修改信息：   修改人：   时间：
     */
    @RequestMapping("ModifyRole/{id}")
    public ModelAndView ModifyRole(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("SysConfig/SysRole_modify");
        SysRole sysRole = sysRoleService.findById(id);
        view.addObject("sysRole", sysRole);

        return view;
    }

    /**
     * 修改角色信息
     * 作者：高鹏   版本：v1.1
     *
     * @return
     * @prarm [hkRole, response]
     * @date 2019/4/18
     * 修改信息：   修改人：   时间：
     */
    @RequestMapping("SaveModifyRole")
    public void SaveModifyRole(SysRole hkRole, HttpServletResponse response) {
        try {
            sysRoleService.update(hkRole);
            Utils.alertAndForword(response, "数据已保存，操作成功。", "/SysRole/ManageList?roleid=" + hkRole.getId());
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            Utils.alertAndForword(response, "保存数据时发生错误，操作失败。", "/SysRole/ModifyRole");
        }
    }

    @RequestMapping("DelRole/{RoleId}")
    @ResponseBody
    public Result SaveDelRole(@PathVariable("RoleId") String roleid) {
        Result result = new Result();
        try {
            if (sysRoleService.delete(roleid) > 0) {
                result.setCode(1);
                result.setMsg("角色删除成功。");
            } else {
                result.setCode(2);
                result.setMsg("删除数据时发生错误，操作失败。");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            result.setCode(2);
            result.setMsg("删除数据时发生错误，操作失败。");
        }
        return result;
    }

}
