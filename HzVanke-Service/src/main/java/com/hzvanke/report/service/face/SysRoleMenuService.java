package com.hzvanke.report.service.face;

import com.hzvanke.report.pojo.SysOnelevelmenu;
import com.hzvanke.report.pojo.SysRoleMenu;
import com.hzvanke.report.pojo.SysTwolevelmenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色菜单权限数据操作接口定义
 * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
 */
public interface SysRoleMenuService {
    /**
     * 批量添加
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param listSysRoleMenu 需要保存的实体集合
     * @param roleid          角色ID
     * @return 成功：true，失败：false
     */
    public boolean add(List<SysRoleMenu> listSysRoleMenu, String roleid);


    /**
     * 根据角色ID获取角色对应的所有二级菜单信息
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param roleid 角色ID
     * @return 实体类集合
     */
    public List<SysTwolevelmenu> findByRoleid(String roleid);

    /**
     * 根据角色ID获取角色对应的所有一级菜单信息
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param roleid 角色ID
     * @return 实体类集合
     */
    public List<SysOnelevelmenu> findOneLevelMenuByRoleid(@Param("roleid") String roleid);
}
