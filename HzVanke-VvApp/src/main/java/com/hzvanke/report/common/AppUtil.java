package com.hzvanke.report.common;


import com.hzvanke.report.commons.AppCommon;
import com.hzvanke.report.sysenum.EnumSysRole;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class AppUtil extends AppCommon {

    /**
     * 角色对应的首页地址
     */
    public static Map<EnumSysRole.RoleCode, String> UserMainPage_URL;

    static {
        UserMainPage_URL = new HashMap<>();
        //用户管理
        UserMainPage_URL.put(EnumSysRole.RoleCode.系统管理员, "/SysUser/ManageList");
    }

    //session存放图片验证码
    public static final String SESSION_CODE = "com.hzvanke.report..code";
    //session存放当前登录用户
    public static final String REPORT_SYSUSER = "com.hzvanke.report..sysuser";
    //session存放用户的角色
    public static final String REPORT_ROLE = "com.hzvanke.report..menbercenterpc.role";
    //session存放二级目录
    public static final String SESSION_TWO_MENU = "com.hzvanke.report..twolevelmenu";
    //session存放用户对应的项目
    public static final String SESSION_USER_PROJECT = "com.hzvanke.report..userproject";

    public static final String reidsKey="com.hzvanke.report.user_pc";


}
