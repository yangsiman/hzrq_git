package com.hzvanke.report.controller;

import com.hzvanke.report.commons.*;
import com.hzvanke.report.pojo.*;
import com.hzvanke.report.service.face.SysRoleService;
import com.hzvanke.report.service.face.SysUserService;
import com.hzvanke.report.service.face.VCSHzMydCdService;
import com.hzvanke.report.service.face.VCSHzMydService;
import com.hzvanke.report.sysenum.EnumSysUser;
import org.apache.ibatis.annotations.Param;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统用户操作控制器 作者：作者：莫宁辉，开发日期：2019-08-19，版本：v1.1
 */
@Controller
@RequestMapping("/SysUser/")
public class SysUserController extends BaseController {

    // 日志打印
    private static Logger logger = Logger.getLogger(SysUserController.class.getName());
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private VCSHzMydService vcsHzMydService;
    @Autowired
    private VCSHzMydCdService cdService;

    //region 系统用户管理主页面入口

    /**
     * 系统用户管理主页面入口 作者：作者：莫宁辉，开发日期：2019-08-19，版本：v1.1 最后编辑：，开发日期：，版本：
     *
     * @param sysUserSearch 查询实体类，具体参数见前台
     * @param request       request
     * @return
     */
    @RequestMapping("ManageList")
    public ModelAndView pageManageList(SysUser sysUserSearch, HttpServletRequest request) {
        ModelAndView view = new ModelAndView("SysConfig/SysUserList");
        //获取页面数据
        /**
         * 1.判断当前角色是不是系统管理员
         * 2.判断当前角色的区域
         */
        PageUtil<SysUser> pageData = sysUserService.findListByManage(request, 50, sysUserSearch, this.getCurrentUserRole().getRoleName());
        //获取全部角色信息
        List<SysRole> listSysRole = sysRoleService.findAll(this.getCurrentUserRole().getRoleName());

        //配置页面需要使用的数据
        view.addObject("sysUserSearch", sysUserSearch);
        //所有角色（如果不是系统管理员，不显示系统管理员）
        view.addObject("listSysRole", listSysRole);
        view.addObject("page", pageData);
        return view;
    }
    //endregion

    //region 锁定或解锁用户使用状态

    /**
     * 锁定或解锁用户使用状态
     *
     * @param request
     * @return
     */
    @RequestMapping("UpdateUserStatus")
    @ResponseBody
    public Result saveUpdateUserStatus(HttpServletRequest request) {
        Result result = new Result();
        try {
            String[] arrayUserIds = request.getParameterValues("sysuserid");
            List<String> listUserIds = Arrays.asList(arrayUserIds);
            String strUserStatus = request.getParameter("status");
            if (sysUserService.updateStatus(listUserIds, strUserStatus) > 0) {
                result.setCode(1);
                result.setMsg("用户状态已保存成功。");
            }
        } catch (Exception ex) {
            logger.error("处理数据异常", ex);

            result.setCode(2);
            result.setMsg("保存数据时发生错误，操作失败。");
        }
        return result;
    }
    //endregion

    //region 批量删除用户（不删除当前登陆的用户）

    /**
     * 批量删除用户（不删除当前登陆的用户）
     *
     * @param request
     * @return
     */
    @RequestMapping("DelUser")
    @ResponseBody
    public Result saveDelUser(HttpServletRequest request) {
        Result result = new Result();
        try {
            String[] arrayUserIds = request.getParameterValues("sysuserid");
            //排除当前登陆的用户
            List<String> listUserIds = Arrays.asList(arrayUserIds).stream().filter(data -> !data.equals(this.getCurrentUser().getId())).collect(Collectors.toList());
            if (listUserIds.size() > 0) {
                if (sysUserService.del(listUserIds) > 0) {
                    result.setCode(1);
                    result.setMsg("选中的用户已被删除，操作成功。");
                } else {
                    result.setCode(2);
                    result.setMsg("保存数据时发生错误，操作失败。");
                }
            } else {
                result.setCode(3);
                result.setMsg("未选择用户或只选择了当前登陆用户，操作失败。");
            }
        } catch (Exception ex) {
            logger.error("处理数据异常", ex);

            result.setCode(2);
            result.setMsg("保存数据时发生错误，操作失败。");
        }
        return result;
    }
    //endregion

    //region 编辑当前用户的信息

    /**
     * 编辑当前用户的信息
     *
     * @return
     */
    @RequestMapping("EditCurrentUser")
    public ModelAndView pageEditCurrentUser() {
        ModelAndView view = new ModelAndView("SysConfig/SysUserCurrentInfo");
        SysUser sysUser = this.getCurrentUser();
        view.addObject("sysUser", sysUser);
        return view;
    }
    //endregion

    //region 保存编辑当前用户的信息

    /**
     * 保存编辑当前用户的信息
     *
     * @param request
     * @return
     */
    @RequestMapping("SaveEditCurrentUser")
    @ResponseBody
    public Result saveEditCurrentUser(HttpServletRequest request) {
        Result result = new Result();
        try {
            SysUser sysUser = this.getCurrentUser();
            sysUser.setUserName(request.getParameter("username"));
            sysUser.setMobile(request.getParameter("mobile"));
            sysUser.setEmail(request.getParameter("email"));
            String password = request.getParameter("password");
            if (!StringUtils.isEmpty(password)) {
                sysUser.setPassword(Utils.md5(password));
            }

            if (sysUserService.update(sysUser)) {
                result.setCode(1);
                result.setMsg("用户信息已保存成功，请重新登陆。");
            } else {
                result.setCode(2);
                result.setMsg("处理数据时发生错误，操作失败。");
            }
        } catch (Exception ex) {
            logger.error("处理数据异常", ex);

            result.setCode(2);
            result.setMsg("处理数据时发生错误，操作失败。");
        }
        return result;
    }
    //endregion


    /**
     * 新增用户页面入口 作者：莫宁辉，开发日期：2019-08-19，版本：v1.1
     *
     * @return
     */
    @RequestMapping("AddUser")
    public ModelAndView pageAddUser() {
        ModelAndView view = new ModelAndView("SysConfig/SysUserAdd");
        //获取所有角色的数据
        List<SysRole> listSysRole = sysRoleService.findAll(this.getCurrentUserRole().getRoleName());
        view.addObject("listSysRole", listSysRole);
        return view;
    }

    @RequestMapping("EditUser/{id}")
    public ModelAndView pageEditUser(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("SysConfig/SysUserEdit");
        //初始化用户信息
        SysUser sysUser = sysUserService.findById(id);
        //获取所有角色的数据
        List<SysRole> listSysRole = sysRoleService.findAll(this.getCurrentUserRole().getRoleName());
        view.addObject("listSysRole", listSysRole);
        view.addObject("sysUser", sysUser);
        return view;
    }
    //endregion

    //region 新增用户保存操作

    /**
     * 新增用户保存操作
     *
     * @param sysUser 实体类
     */
    @RequestMapping("SaveAddUser")
    @ResponseBody
    public Result saveAddUser(SysUser sysUser) {
        try {
            if (sysUserService.findByCode(sysUser.getUserCode()) != null) {
                return ResultUtil.error("系统已存在该登录账号");
            }
            //处理角色信息
            sysUser.setId(Utils.CreateNewKeyid());
            sysUser.setCreateTime(new Date());
            sysUser.setPassword(Utils.md5(sysUser.getPassword()));
            sysUser.setUserType("2");//全部设为本系统用户
            sysUser.setStatus(String.valueOf(EnumSysUser.Status.正常登陆.value()));//默认为正常登陆状态
            sysUser.setCreateTime(new Date());
            sysUserService.add(sysUser);
            return ResultUtil.success("用户信息已保存成功");

        } catch (Exception ex) {
            logger.error("处理数据异常", ex);

            return ResultUtil.error("保存数据时发生错误，操作失败");
        }
    }
    //endregion

    //region 编辑用户信息保存操作

    /**
     * 编辑用户保存操作 作者：作者：莫宁辉，开发日期：2019-08-19，版本：v1.1
     *
     * @param sysUser  实体类
     * @param request
     * @param response
     */
    @RequestMapping("SaveEditUser")
    public void saveEditUser(SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
        try {
            String newPassword = request.getParameter("passwordChange");
            if (!StringUtils.isEmpty(newPassword)) {
                sysUser.setPassword(Utils.md5(newPassword));
            }
            sysUserService.update(sysUser);
            Utils.alertAndForword(response, "用户信息已保存成功。", "/SysUser/ManageList");
        } catch (Exception ex) {
            logger.error("处理数据异常", ex);

            Utils.alertAndForword(response, "处理数据时发生错误，操作失败。", "/SysUser/ManageList");
        }
    }
    //endregion

    /**
     * 解绑微信Openid 作者：作者：莫宁辉，开发日期：2019-09-03，版本：v1.1
     *
     * @param id
     */
    @RequestMapping("UpdateOpenid")
    @ResponseBody
    public Result Untying(@Param("id") String id) {
        try {
            SysUser user = sysUserService.findById(id);
            user.setWxOpenid("");
            sysUserService.update(user);
            return ResultUtil.success();
        } catch (Exception e) {

            logger.error(e.getMessage(), e);
            return ResultUtil.error();
        }
    }


}
