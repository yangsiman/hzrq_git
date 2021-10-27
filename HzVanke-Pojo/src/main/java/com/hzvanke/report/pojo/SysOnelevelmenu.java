package com.hzvanke.report.pojo;

/**
 * 系统一级菜单表
 * 字段：主键ID
 * 菜单名称，显示给用户时的名称: MenuName
 * 菜单名称：管理时显示的名称，用于区别多角色授权时后台管理区分: ManageName
 * 排序号，从小到大进行排序: Sort
 * 样式名称：引用图标样式名称: ClassName
 * 展开状态：1展开，2收起: Status
 */
public class SysOnelevelmenu extends BasePojo{

    private static final long serialVersionUID = 4385570684077613256L;
    private String menuname;

    private String managename;

    private Integer sort;

    private String classname;

    private String status;

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public String getManagename() {
        return managename;
    }

    public void setManagename(String managename) {
        this.managename = managename == null ? null : managename.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}