package com.hzvanke.report.controller;

import com.hzvanke.report.common.AppUtil;
import com.hzvanke.report.commons.Result;
import com.hzvanke.report.commons.ResultUtil;
import com.hzvanke.report.commons.Utils;
import com.hzvanke.report.pojo.SysRole;
import com.hzvanke.report.pojo.SysTwolevelmenu;
import com.hzvanke.report.pojo.SysUser;
import com.hzvanke.report.service.face.SysRoleMenuService;
import com.hzvanke.report.service.face.SysUserService;
import com.hzvanke.report.sysenum.EnumSysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/Login")
public class LoginController extends BaseController {

    //日志打印
    private static Logger logger = Logger.getLogger(LoginController.class.getName());

    @Autowired
    private SysUserService sysUserService;

    //region 用户登录验证的方法

    /**
     * 用户登录验证的方法，成功返回1，用户名或密码错误返回2，验证码错误返回3，处理数据出错4，5限制登陆, 2019.3.20 新增：签约专员不可进入
     */
    @RequestMapping("login_do")
    @ResponseBody
    public Result login(@Param("email") String email, @Param("rand") String rand, HttpServletResponse response) {
        try {
            Map<String, Object> map = new HashMap<>();
            if (!StringUtils.isEmpty(email)) {
                email = email.replace("@vanke.com", "");
                SysUser sysuser = sysUserService.findByCode(email);
                if (StringUtils.isEmpty(sysuser)) {
                    return ResultUtil.error(110, "用户不存在");
                }
                map.put("user", sysuser);
                setCurrentUser(sysuser);
                return ResultUtil.success("登录成功");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ResultUtil.error(110,"登录错误");
    }
}
