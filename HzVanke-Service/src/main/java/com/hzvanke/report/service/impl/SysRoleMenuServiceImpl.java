package com.hzvanke.report.service.impl;

import com.hzvanke.report.dao.SysRoleMenuDao;
import com.hzvanke.report.pojo.SysOnelevelmenu;
import com.hzvanke.report.pojo.SysRoleMenu;
import com.hzvanke.report.pojo.SysTwolevelmenu;
import com.hzvanke.report.service.face.SysRoleMenuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色菜单权限数据操作接口实现类
 * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    /**
     * 批量添加
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param listSysRoleMenu 需要保存的实体集合
     * @param roleid          角色ID
     * @return 成功：true，失败：false
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,readOnly = false,value = "myTxManager")
    public boolean add(List<SysRoleMenu> listSysRoleMenu, String roleid) {
        boolean result = false;
        try{
            sysRoleMenuDao.del(roleid);
            for(SysRoleMenu sysRoleMenu : listSysRoleMenu){
                sysRoleMenuDao.add(sysRoleMenu);
            }
            result = true;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 根据角色ID获取角色对应的所有二级菜单信息
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param roleid 角色ID
     * @return 实体类集合
     */
    @Override
    public List<SysTwolevelmenu> findByRoleid(String roleid) {
        return sysRoleMenuDao.findByRoleid(roleid);
    }

    /**
     * 根据角色ID获取角色对应的所有一级菜单信息
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param roleid 角色ID
     * @return 实体类集合
     */
    @Override
    public List<SysOnelevelmenu> findOneLevelMenuByRoleid(@Param("roleid") String roleid){
        return sysRoleMenuDao.findOneLevelMenuByRoleid(roleid);
    }
}
