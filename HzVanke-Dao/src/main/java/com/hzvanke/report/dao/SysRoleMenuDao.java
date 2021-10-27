package com.hzvanke.report.dao;

import com.hzvanke.report.pojo.SysOnelevelmenu;
import com.hzvanke.report.pojo.SysRoleMenu;
import com.hzvanke.report.pojo.SysTwolevelmenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 作者：莫宁辉，开发时间：2019-08-23，版本号：1.1
 */
@Repository
public interface SysRoleMenuDao {

    public int add(SysRoleMenu sysRoleMenu);

    public int del(@Param("roleid") String roleid);

    public List<SysTwolevelmenu> findByRoleid(@Param("roleid") String roleid);

    public List<SysOnelevelmenu> findOneLevelMenuByRoleid(@Param("roleid") String roleid);
}