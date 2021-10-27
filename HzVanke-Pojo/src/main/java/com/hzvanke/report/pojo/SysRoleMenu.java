package com.hzvanke.report.pojo;


import lombok.Data;

/**
 * 角色拥有的菜单表

 */
@Data
public class SysRoleMenu extends BasePojo{

    private static final long serialVersionUID = 8644762737936788908L;
    private String roleId;//所属角色ID：RoleId

    private String menuId;//二级菜单ID: MenuId

}