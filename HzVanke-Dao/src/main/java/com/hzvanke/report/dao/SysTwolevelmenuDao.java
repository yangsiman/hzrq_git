package com.hzvanke.report.dao;

import com.hzvanke.report.pojo.SysTwolevelmenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
 */
@Repository
public interface SysTwolevelmenuDao {
    /**
     * 新增
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysTwolevelmenu 实体类
     * @return 成功返回1，失败返回0
     */
    public int add(SysTwolevelmenu sysTwolevelmenu);

    /**
     * 修改
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysTwolevelmenu 实体类
     * @return 成功返回1，失败返回0
     */
    public int update(SysTwolevelmenu sysTwolevelmenu);

    /**
     * 修改排序号
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param id   主键ID
     * @param sort 排序号
     * @return 成功返回1，失败返回0
     */
    public int updateSort(@Param("id") String id, @Param("sort") int sort);

    /**
     * 根据ID集合批量删除
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param listKeyid 主键ID集合
     * @return 成功返回受影响的记录数，失败返回0
     */
    public int del(@Param("listKeyid") List<String> listKeyid);

    /**
     * 根据ID获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param id 主键ID
     * @return 实体类
     */
    public SysTwolevelmenu findById(@Param("id") String id);

    /**
     * 根据编号获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param menucode 编号
     * @return 实体类
     */
    public SysTwolevelmenu findByCode(@Param("menucode") String menucode);

    /**
     * 获取最大排序号
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param onelevelmenuid 一级菜单主键ID
     * @return 数据库中最大的排序号
     */
    public int findMaxSort(@Param("onelevelmenuid") String onelevelmenuid);

    /**
     * 根据一级菜单ID获取所属的二级菜单
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param onelevelmenuid 一级菜单主键ID
     * @return 实体类
     */
    public List<SysTwolevelmenu> findByOneLevelMenuId(@Param("onelevelmenuid") String onelevelmenuid);

    /**
     * 获取指定编号的记录数（验证编号是否存在时使用）
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param menucode 菜单编号
     * @param id       主键ID，不为空时则指定的数据不参与运算，编辑时使用
     * @return 返回记录数
     */
    public int countByCode(@Param("menucode") String menucode, @Param("id") String id);
}