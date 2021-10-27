package com.hzvanke.report.service.impl;

import com.hzvanke.report.dao.SysRoleDao;
import com.hzvanke.report.pojo.SysRole;
import com.hzvanke.report.service.face.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统角色数据操作接口实现类
 * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    public SysRoleDao getSysRoleDao() {
        return sysRoleDao;
    }

    public void setSysRoleDao(SysRoleDao sysRoleDao) {
        this.sysRoleDao = sysRoleDao;
    }

    /**
     * 获取所有的系统角色
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @return 实体类集合
     */
    @Override
    public List<SysRole> findAll(String value) {
        if (value.contains("系统管理员"))
            return sysRoleDao.findAll(null);
        return sysRoleDao.findAll(value);
    }

    /**
     * 根据主键ID获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param id 主键ID
     * @return 实体类
     */
    @Override
    public SysRole findById(String id) {
        return sysRoleDao.findById(id);
    }

    /**
     * 根据编号获取一条记录
     * 作者：蒋斌 时间：2019-1-9 版本号：v1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param roleCode 角色编号
     * @return 实体类
     */
    @Override
    public SysRole findByCode(String roleCode) {
        return sysRoleDao.findByCode(roleCode);
    }

    @Override
    public int add(SysRole sysRole) {
        return sysRoleDao.add(sysRole);
    }

    /**
     * 修改一条记录
     * 作者：莫宁辉，开发时间：2019/8/26，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysRole 实体类
     * @return 成功返回1，失败返回0
     */
    @Override
    public int update(SysRole sysRole) {
        return sysRoleDao.update(sysRole);
    }

    /**
     * 根据主键id删除一个角色
     * 作者：莫宁辉   版本：v1.1
     *
     * @return
     * @prarm [id]
     * @date 2019/8/26
     * 修改信息：   修改人：   时间：
     */
    @Override
    public int delete(String id) {
        return sysRoleDao.delete(id);
    }

    /**
     * 获取最大排序号，默认为0
     * 作者：莫宁辉，开发时间：2019/8/26，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @return 最大排序号
     */
    @Override
    public int findMaxSort() {
        return sysRoleDao.findMaxSort();
    }

    /**
     * 根据角色名称获取一条数据
     * 作者：莫宁辉   版本：v1.1
     *
     * @return
     * @prarm [roleName]
     * @date 2019/5/20
     * 修改信息：   修改人：   时间：
     */
    @Override
    public SysRole findByName(String roleName) {
        return sysRoleDao.findByName(roleName);
    }
}
