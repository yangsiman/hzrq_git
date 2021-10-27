package com.hzvanke.report.service.face;


import com.hzvanke.report.pojo.SysOnelevelmenu;

import java.util.List;

/**
 * 系统一级菜单数据操作接口定义
 * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
 */
public interface SysOneLevelMenuService {
    /**
     * 获取所有一级菜单数据
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @return 实体类集合
     */
    public List<SysOnelevelmenu> findAll();

    /**
     * 根据ID获取一条信息
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param id 主键ID
     * @return 实体类
     */
    public SysOnelevelmenu findById(String id);

    /**
     * 新增
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysOnelevelmenu 实体类
     * @return 成功：1，失败：0
     */
    public int add(SysOnelevelmenu sysOnelevelmenu);

    /**
     * 修改
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysOnelevelmenu 实体类
     * @return 成功：1，失败：0
     */
    public int update(SysOnelevelmenu sysOnelevelmenu);

    /**
     * 根据ID删除一条记录
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param id 主键ID
     * @return 成功：1，失败：0
     */
    public int del(String id);

    /**
     * 获取最大排序号，默认为0
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @return 数据库中的最大排序号
     */
    public int findMaxSort();
}
