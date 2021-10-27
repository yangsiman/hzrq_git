package com.hzvanke.report.dao;

import com.hzvanke.report.pojo.SysOnelevelmenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
 */
@Repository
public interface SysOnelevelmenuDao {
    /**
     * 获取所有一级菜单数据
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @return 实体类集合
     */
    public List<SysOnelevelmenu> findAll();

    /**
     * 根据ID获取一条信息
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param id 主键ID
     * @return 实体类集合
     */
    public SysOnelevelmenu findById(@Param("id") String id);

    /**
     * 新增
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysOnelevelmenu 实体类
     * @return 成功返回1，失败返回0
     */
    public int add(SysOnelevelmenu sysOnelevelmenu);

    /**
     * 修改
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysOnelevelmenu 实体类
     * @return 成功返回1，失败返回0
     */
    public int update(SysOnelevelmenu sysOnelevelmenu);

    /**
     * 根据ID删除一条记录
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param id 主键ID
     * @return 成功返回1，失败返回0
     */
    public int del(@Param("id") String id);

    /**
     * 获取最大排序号，默认为0
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @return 最大排序号
     */
    public int findMaxSort();
}