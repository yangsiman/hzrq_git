package com.hzvanke.report.service.impl;

import com.hzvanke.report.dao.SysOnelevelmenuDao;
import com.hzvanke.report.pojo.SysOnelevelmenu;
import com.hzvanke.report.service.face.SysOneLevelMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统一级菜单数据操作接口实现类
 * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
 */
@Service
public class SysOneLevelMenuServiceImpl implements SysOneLevelMenuService {


    @Autowired
    private SysOnelevelmenuDao sysOnelevelmenuDao;

    /**
     * 获取所有一级菜单数据
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @return 实体类集合
     */
    @Override
    public List<SysOnelevelmenu> findAll() {
        return sysOnelevelmenuDao.findAll();
    }

    /**
     * 根据ID获取一条信息
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param id 主键ID
     * @return 实体类
     */
    @Override
    public SysOnelevelmenu findById(String id) {
        return sysOnelevelmenuDao.findById(id);
    }

    /**
     * 新增
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysOnelevelmenu 实体类
     * @return 成功：1，失败：0
     */
    @Override
    public int add(SysOnelevelmenu sysOnelevelmenu) {
        return sysOnelevelmenuDao.add(sysOnelevelmenu);
    }

    /**
     * 修改
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysOnelevelmenu 实体类
     * @return 成功：1，失败：0
     */
    @Override
    public int update(SysOnelevelmenu sysOnelevelmenu) {
        return sysOnelevelmenuDao.update(sysOnelevelmenu);
    }

    /**
     * 根据ID删除一条记录
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param id 主键ID
     * @return 成功：1，失败：0
     */
    @Override
    public int del(String id) {
        return sysOnelevelmenuDao.del(id);
    }

    /**
     * 获取最大排序号，默认为0
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @return 数据库中的最大排序号
     */
    @Override
    public int findMaxSort() {
        return sysOnelevelmenuDao.findMaxSort();
    }
}
