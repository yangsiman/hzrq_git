package com.hzvanke.report.controller;

import com.hzvanke.report.common.AppUtil;
import com.hzvanke.report.commons.Result;
import com.hzvanke.report.commons.Utils;
import com.hzvanke.report.pojo.SysRole;
import com.hzvanke.report.pojo.SysTwolevelmenu;
import com.hzvanke.report.pojo.SysUser;
import com.hzvanke.report.service.face.SysRoleMenuService;
import com.hzvanke.report.service.face.SysUserService;
import com.hzvanke.report.sysenum.EnumSysUser;
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
import java.util.Random;

@Controller
@RequestMapping("/Login")
public class LoginController extends BaseController {

    //日志打印
    private static Logger logger = Logger.getLogger(LoginController.class.getName());

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    //region 用户登录验证的方法

    /**
     * 用户登录验证的方法，成功返回1，用户名或密码错误返回2，验证码错误返回3，处理数据出错4，5限制登陆, 2019.3.20 新增：签约专员不可进入
     */
    @RequestMapping(value = "/login_do", method = RequestMethod.POST)
    @ResponseBody
    public Result Login(String usercode, String password, String code) throws Exception {
        Result result = new Result();
        try {
            //region 处理登陆业务逻辑
            if (session.getAttribute(AppUtil.SESSION_CODE) == null) {
                //验证码不正确返回
                result.setCode(3);
                result.setMsg("登陆验证码失效，有效期三分钟。");
            } else if (!code.equals(session.getAttribute(AppUtil.SESSION_CODE).toString())) {
                //验证码不正确返回
                result.setCode(3);
                result.setMsg("登陆验证码错误，请确认。");
            } else {
                //移除验证码session
                session.removeAttribute(AppUtil.SESSION_CODE);
                SysUser sysUser = sysUserService.findByCodeAndPwd(usercode, Utils.md5(password));
                if (sysUser == null || StringUtils.isEmpty(sysUser.getId())) {
                    result.setCode(2);
                    result.setMsg("用户名或密码错误，请确认。");
                } else {
                    //判断用户是否可以登陆
                    if (EnumSysUser.Status.valueOf(Integer.parseInt(sysUser.getStatus())) == EnumSysUser.Status.正常登陆) {
                        //用户角色信息
                        SysRole sysRole = sysUser.getRole();
                        //用户信息
                        //用户菜单，获取二级菜单即可
                        java.util.List<SysTwolevelmenu> listSysTwoLevelmenu = sysRoleMenuService.findByRoleid(sysRole.getId());
                        result.setCode(1);
                        result.setMsg("登陆成功。");
                        this.setCurrentUserRole(sysRole);
                        this.setCurrentUserTwoLevelMenu(listSysTwoLevelmenu);
                        this.setCurrentUser(sysUser);
                    } else {
                        result.setCode(5);
                        result.setMsg("此用户已被限制使用本系统，请联系管理员处理。");
                    }
                }
            }
            //endregion
        } catch (Exception ex) {
            logger.error("处理数据异常", ex);

            result.setCode(4);
            result.setMsg("处理数据时发生错误，登陆失败。");
        }
        return result;
    }
    //endregion

    //region 退出登陆
    @RequestMapping("/login_out")
    public ModelAndView LoginOut() {
        if (session.getAttribute(AppUtil.SESSION_SYSUSER) != null) {
            session.removeAttribute(AppUtil.SESSION_SYSUSER);
        }
        if (session.getAttribute(AppUtil.SESSION_ROLE) != null) {
            session.removeAttribute(AppUtil.SESSION_ROLE);
        }
        if (session.getAttribute(AppUtil.SESSION_TWO_MENU) != null) {
            session.removeAttribute(AppUtil.SESSION_TWO_MENU);
        }
        if (session.getAttribute(AppUtil.SESSION_USER_PROJECT) != null) {
            session.removeAttribute(AppUtil.SESSION_USER_PROJECT);
        }

        ModelAndView mav = new ModelAndView("redirect:/index.jsp");
        return mav;
    }
    //endregion

    //region 生成验证码操作

    /**
     * 生成登陆验证码 RequestMapping("authCode")
     *
     * @param request
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping(value = "/authCode", method = RequestMethod.GET)
    public void getAuthCode(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws IOException {
        int width = 80;
        int height = 40;
        Random random = new Random();
        // 设置response头信息
        // 禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        // 产生image类的Graphics用于绘制操作
        Graphics g = image.getGraphics();
        // Graphics类的样式
        g.setColor(this.getRandColor(200, 250));
        g.setFont(new Font("Times New Roman", 0, 28));
        g.fillRect(0, 0, width, height);
        // 绘制干扰线
        for (int i = 0; i < 40; i++) {
            g.setColor(this.getRandColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }
        // 绘制字符
        String strCode = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            strCode = strCode + rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 28);
        }
        // 将字符保存到session中用于前端的验证
        session.setAttribute(AppUtil.SESSION_CODE, strCode);
        g.dispose();

        ImageIO.write(image, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();
    }

    /**
     * 生成颜色
     *
     * @param fc
     * @param bc
     * @return
     */
    Color getRandColor(int fc, int bc) {
        Random random = new Random();
        fc = fc > 255 ? 255 : fc;
        bc = bc > 255 ? 255 : bc;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    //endregion
}
