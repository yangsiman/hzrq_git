package com.hzvanke.report.service.face;


import com.hzvanke.report.commons.PageUtil;
import com.hzvanke.report.pojo.SysUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 系统用户数据操作接口定义
 * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
 */
public interface SysUserService {

     boolean add(SysUser sysUser);

    /**
     * 根据ID获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param id 主键ID
     * @return 实体类
     */
    public SysUser findById(String id);

    /**
     * 根据用户登陆帐号和密码获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param UserCode 用户登陆帐号
     * @param Password 密码（需要进行MD5加密）
     * @return 实体类
     */
    SysUser findByCodeAndPwd(String UserCode, String Password);

    /**
     * 根据用户登陆帐号获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param UserCode 用户登陆帐号
     * @return 实体类
     */
    public SysUser findByCode(String UserCode);


    /**
     * 修改一条记录的基础信息
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysUser 实体类
     * @return 成功：1，失败：0
     */
    public boolean update(SysUser sysUser);

    /**
     * 根据主键ID集合批量更新状态
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param listKeyid 主键ID集合
     * @param status    用户状态值
     * @return 成功：影响的记录数，失败：0
     */
    public int updateStatus(List<String> listKeyid, String status);

    /**
     * 根据主键ID集合批量删除
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param listKeyid 主键ID集合
     * @return
     */
    public int del(List<String> listKeyid);


    /**
     * 根据条件获取对应的用户数据（用户管理页使用）返回视图（实现分页）
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *

     * @return 数据（List中为ViewSysUserRole的集合List）
     */
    public PageUtil<SysUser> findListByManage(HttpServletRequest request, int pageSize, SysUser sysUser, String roleName);



    public SysUser findByOpenId(String OpenId);



    /**
     * 查询所有角色为中介得用户
     * 作者：高鹏   版本：v1.1
     * @prarm []
     * @date  2019/3/18
     * 修改信息：   修改人：   时间：
     * @return
     */
    public List<SysUser> findByRoleId(String roleid);
}
