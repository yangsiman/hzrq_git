package com.hzvanke.report.commons;



import com.hzvanke.report.sysenum.EnumSysRole;

import java.util.HashMap;
import java.util.Map;

/**
 * 存放系统的静态变量
 */
public class AppCommon {
    /**
     * 角色对应的首页地址
     */
    public static Map<EnumSysRole.RoleCode, String> UserMainPage_URL;

    /**
     角色编码：
     1：系统管理员
     2：平台总控，可以设计模板
     3：项目采购管理，分项目权限
     4：项目设计管理，分项目权限
     5：项目成本管理，分项目权限
     6：项目管理，分项目权限。
     */
    static {
        UserMainPage_URL = new HashMap<>();
        //用户管理
        UserMainPage_URL.put(EnumSysRole.RoleCode.系统管理员, "/SysUser/ManageList");
        //平台总控
        UserMainPage_URL.put(EnumSysRole.RoleCode.平台总控, "/compfyzxbz/list");
        //项目管理
        UserMainPage_URL.put(EnumSysRole.RoleCode.项目管理, "/projectFyConfig/list");
        //项目采购
        UserMainPage_URL.put(EnumSysRole.RoleCode.项目采购管理, "/projectFyConfig/list");
        //项目设计
        UserMainPage_URL.put(EnumSysRole.RoleCode.项目设计管理, "/projectFyConfig/list");
        //项目成本
        UserMainPage_URL.put(EnumSysRole.RoleCode.项目成本管理, "/projectFyConfig/list");
    }
}
