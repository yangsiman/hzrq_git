package com.hzvanke.report.pojo;

import lombok.Data;

/**
 * 系统二级菜单表
 * 字段：主键ID
 * 所属一级菜单主键ID：OneLevelMenuId
 * 菜单名称: MenuName
 * 菜单唯一编码：使用6位数字，唯一值，可以作为权限控制，由系统指定编码（管理员录入时进行分配）: MenuCode
 * 排序号，由小到大进行排序: Sort
 * 菜单对应的链接地址: PageUrl
 * 样式名称,引用图标样式名称: ClassName
 * 访问方式,如_blank，mainFrame等: Target
 */
@Data
public class SysTwolevelmenu extends BasePojo{

    private static final long serialVersionUID = -5384673626557402833L;
    private String oneLevelMenuid;

    private String menuName;

    private String menuCode;

    private String pageUrl;

    private String className;

    private String target;

}