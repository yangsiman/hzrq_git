package com.hzvanke.report.dao;

import com.hzvanke.report.pojo.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
 */
@Repository
public interface SysRoleDao {

    /**
     * 查询所有角色
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @return 实体类集合
     */
    public List<SysRole> findAll(String value);

    /**
     * 根据ID获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param id 主键ID
     * @return 实体类
     */
    public SysRole findById(@Param("id") String id);

    /**
     * 根据编号获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param rolecode 角色编号
     * @return 实体类
     */
    public SysRole findByCode(@Param("roleCode") String rolecode);

    /**
     * 新增角色
     * 作者：莫宁辉   版本：v1.1
     * @prarm [hkRole]
     * @date  2019/8/26
     * 修改信息：   修改人：   时间：
     * @return
     */
    public int add(SysRole hkRole);

    /**
     * 修改一条记录
     * 作者：莫宁辉，开发时间：2019/8/26，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param hkRole 实体类
     * @return 成功返回1，失败返回0
     */
    public int update(SysRole hkRole);

    /**
     * 根据主键id删除一个角色
     * 作者：莫宁辉   版本：v1.1
     * @prarm [id]
     * @date  2019/8/26
     * 修改信息：   修改人：   时间：
     * @return
     */
    public int delete(String id);

    /**
     * 获取最大排序号，默认为0
     * 作者：莫宁辉，开发时间：2019/8/26，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @return 最大排序号
     */
    public int findMaxSort();

    /**
     * 根据角色名称获取一条数据
     * 作者：莫宁辉   版本：v1.1
     * @prarm [roleName]
     * @date  2019/5/20
     * 修改信息：   修改人：   时间：
     * @return
     */
    public SysRole findByName(String roleName);

}