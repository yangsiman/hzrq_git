package com.hzvanke.report.controller;

import com.hzvanke.report.common.AppUtil;
import com.hzvanke.report.commons.DateUtils2;
import com.hzvanke.report.pojo.SysRole;
import com.hzvanke.report.pojo.SysTwolevelmenu;
import com.hzvanke.report.pojo.SysUser;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

public class BaseController {

    @Resource
    HttpSession session;

    //region 当前登陆用户信息
    /**
     * 当前登陆用户信息
     */
    private SysUser currentUser;

    public void setOtherThing(String OtherThing) {
        session.setAttribute(OtherThing, OtherThing);
    }

    public String getOtherThing() {
        Object value = session.getAttribute(AppUtil.REPORT_SYSUSER + "OtherThing");
        if (value != null) {
            return (String) value;
        }
        return "";
    }

    /**
     * 获取当前登陆用户信息
     *
     * @return
     */
    public SysUser getCurrentUser() {
        if (session.getAttribute(AppUtil.REPORT_SYSUSER) != null) {
            currentUser = (SysUser) session.getAttribute(AppUtil.REPORT_SYSUSER);
        }
        return currentUser;
    }

    /**
     * 设置当前用户登陆信息
     *
     * @param currentUser
     */
    public void setCurrentUser(SysUser currentUser) {
        session.setAttribute(AppUtil.REPORT_SYSUSER, currentUser);
        this.currentUser = currentUser;
    }
    //endregion

    //region 当前用户所有的二级菜单信息
    /**
     * 当前用户所有的二级菜单信息
     */
    private List<SysTwolevelmenu> currentUserTwoLevelMenu;

    //region 当前登陆用户角色信息
    /**
     * 当前登陆用户角色信息
     */
    private SysRole currentUserRole;

    /**
     * 获取当前所对应的角色
     *
     * @return
     */
    public SysRole getCurrentUserRole() {
        if (session.getAttribute(AppUtil.REPORT_SYSUSER) != null) {
            currentUserRole = (SysRole) session.getAttribute(AppUtil.REPORT_SYSUSER);
        } else if (this.getCurrentUser() != null) {
            currentUserRole = this.getCurrentUser().getRole();
        }
        return currentUserRole;
    }

    /**
     * 设置当前用户所对应的角色
     *
     * @param currentUserRole
     */
    public void setCurrentUserRole(SysRole currentUserRole) {
        session.setAttribute(AppUtil.REPORT_SYSUSER, currentUserRole);
        this.currentUserRole = currentUserRole;
    }
    //endregion

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }

            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }
        });
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils2.parseDate(text));
            }

            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? DateUtils2.formatDateTime((Date) value) : "";
            }
        });
    }

}
