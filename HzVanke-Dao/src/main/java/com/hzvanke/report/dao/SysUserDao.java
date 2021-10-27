package com.hzvanke.report.dao;

import com.hzvanke.report.commons.PageUtil;
import com.hzvanke.report.pojo.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserDao {

    List<SysUser> findByObj(@Param("key") String key, @Param("value") String value);

    /**
     * 根据ID获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param id 主键ID
     * @return 实体类
     */
    public SysUser findById(@Param("id") String id);

    /**
     * 根据用户登陆帐号和密码获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param UserCode 用户登陆帐号
     * @param Password 密码（需要进行MD5加密）
     * @return 实体类
     */
    public SysUser findByCodeAndPwd(@Param("UserCode") String UserCode, @Param("Password") String Password);



    /**

     * 新增用户
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysUser 实体类
     * @return 成功返回1，失败返回0
     */
    public int add(SysUser sysUser);

    /**
     * 修改一条记录
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysUser 实体类
     * @return 成功返回1，失败返回0
     */
    public int update(SysUser sysUser);

    /**
     * 根据主键ID集合批量更新状态
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param listKeyid 主键ID集合
     * @param status    用户状态值
     * @return 成功返回影响的条数，失败返回0
     */
    public int updateStatus(@Param("listKeyid") List<String> listKeyid, @Param("status") String status);

    /**
     * 根据主键ID集合批量删除
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param listKeyid 主键ID集合
     * @return 成功返回影响的条数，失败返回0
     */
    public int del(@Param("listKeyid") List<String> listKeyid);

    /**
     * 根据条件获取对应的用户数据（用户管理页使用）返回视图
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysUserSearch 查询参数，详见mapper
     * @return 实图集合
     */
    List<SysUser> findListByManage(@Param("sysUserSearch") SysUser sysUserSearch, @Param("pageUtil") PageUtil pageUtil, @Param("roleName") String roleName);

    /**
     * 根据条件获取用户对应的总记录数（用户管理页使用）
     * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysUserSearch 查询参数 查询参数，详见mapper
     * @return 总记录数
     */
    public int findCountByManage(@Param("sysUserSearch") SysUser sysUserSearch, @Param("roleName") String roleName);

}