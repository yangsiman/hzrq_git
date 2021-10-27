package com.hzvanke.report.service.impl;

import com.hzvanke.report.commons.PageUtil;
import com.hzvanke.report.commons.StringUtil;
import com.hzvanke.report.dao.SysUserDao;
import com.hzvanke.report.pojo.SysUser;
import com.hzvanke.report.service.face.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 系统用户数据操作接口实现类
 * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public boolean add(SysUser sysUser) {
        return sysUserDao.add(sysUser) == 1;
    }

    /**
     * 根据ID获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param id 主键ID
     * @return 实体类
     */
    @Override
    public SysUser findById(String id) {
        List<SysUser> list = sysUserDao.findByObj("id", id);
        if (StringUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 根据用户登陆帐号和密码获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param UserCode 用户登陆帐号
     * @param Password 密码（需要进行MD5加密）
     * @return 实体类
     */
    @Override
    public SysUser findByCodeAndPwd(String UserCode, String Password) {
        return sysUserDao.findByCodeAndPwd(UserCode, Password);
    }

    /**
     * 根据用户登陆帐号获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param UserCode 用户登陆帐号
     * @return 实体类
     */
    @Override
    public SysUser findByCode(String UserCode) {
        List<SysUser> list = sysUserDao.findByObj("UserCode", UserCode);
        if (StringUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 修改一条记录的基础信息
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysUser 实体类
     * @return 成功：1，失败：0
     */
    @Override
    public boolean update(SysUser sysUser) {
        return sysUserDao.update(sysUser) > 0;
    }

    /**
     * 根据主键ID集合批量更新状态
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param listKeyid 主键ID集合
     * @param status    用户状态值
     * @return 成功：影响的记录数，失败：0
     */
    @Override
    public int updateStatus(List<String> listKeyid, String status) {
        return sysUserDao.updateStatus(listKeyid, status);
    }

    /**
     * 根据主键ID集合批量删除
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param listKeyid 主键ID集合
     * @return
     */
    @Override
    public int del(List<String> listKeyid) {
        return sysUserDao.del(listKeyid);
    }

    @Override
    public PageUtil<SysUser> findListByManage(HttpServletRequest request, int pageSize, SysUser sysUser,String roleName) {
        //处理分页信息
        PageUtil<SysUser> page = new PageUtil<>(pageSize,request);
        //设置获取条件
        if (roleName.contains("系统管理员")){
            page.setList(sysUserDao.findListByManage(sysUser,page,null));//设置返回值
            page.setTotalNum(sysUserDao.findCountByManage(sysUser,null));
        }else{
            page.setList(sysUserDao.findListByManage(sysUser,page,roleName));//设置返回值
            page.setTotalNum(sysUserDao.findCountByManage(sysUser,roleName));
        }
        page.setPageUrl(request);
        return page;
    }

    @Override
    public SysUser findByOpenId(String OpenId) {
        List<SysUser> list = sysUserDao.findByObj("wxOpenid", OpenId);
        if (StringUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 查询所有角色为中介得用户
     * 作者：高鹏   版本：v1.1
     *
     * @return
     * @prarm []
     * @date 2019/3/18
     * 修改信息：   修改人：   时间：
     */
    @Override
    public List<SysUser> findByRoleId(String roleid) {
        return sysUserDao.findByObj("roleid", roleid);
    }
}
