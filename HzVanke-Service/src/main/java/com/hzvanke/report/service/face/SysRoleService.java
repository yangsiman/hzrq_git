package com.hzvanke.report.service.face;


import com.hzvanke.report.pojo.SysRole;

import java.util.List;

/**
 * 系统角色数据操作接口定义
 * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
 */
public interface SysRoleService {

    /**
     * 获取所有的系统角色
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @return 实体类集合
     */
    public List<SysRole> findAll(String value);

    /**
     * 根据主键ID获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param id 主键ID
     * @return 实体类
     */
    public SysRole findById(String id);

    /**
     * 根据编号获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param roleCode 角色编号
     * @return 实体类
     */
    public SysRole findByCode(String roleCode);

    public int add(SysRole hkRole);

    /**
     * 修改一条记录
     * 作者：高鹏，开发时间：2019/4/17，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysRole 实体类
     * @return 成功返回1，失败返回0
     */
    public int update(SysRole sysRole);

    /**
     * 根据主键id删除一个角色
     * 作者：高鹏   版本：v1.1
     * @prarm [id]
     * @date  2019/4/17
     * 修改信息：   修改人：   时间：
     * @return
     */
    public int delete(String id);

    /**
     * 获取最大排序号，默认为0
     * 作者：高鹏，开发时间：2019/4/17，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @return 最大排序号
     */
    public int findMaxSort();

    /**
     * 根据角色名称获取一条数据
     * 作者：高鹏   版本：v1.1
     * @prarm [roleName]
     * @date  2019/5/20
     * 修改信息：   修改人：   时间：
     * @return
     */
    public SysRole findByName(String roleName);
}
