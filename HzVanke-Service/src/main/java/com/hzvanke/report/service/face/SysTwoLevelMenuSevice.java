package com.hzvanke.report.service.face;

import com.hzvanke.report.pojo.SysTwolevelmenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 系统二级菜单数据操作接口定义
 * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
 */
public interface SysTwoLevelMenuSevice {

    /**
     * 新增
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysTwolevelmenu 实体类
     * @return 成功：1，失败：0
     */
    public int add(SysTwolevelmenu sysTwolevelmenu);

    /**
     * 修改
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysTwolevelmenu 实体类
     * @return成功：1，失败：0
     */
    public int update(SysTwolevelmenu sysTwolevelmenu);

    /**
     * 修改排序号
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param map 使用键值对进行批量更新操作，键为ID，值为Sort
     * @return 成功：true，失败：false
     */
    public boolean updateSort(Map<String, Integer> map);

    /**
     * 根据ID集合批量删除
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param listKeyid 主键ID集合
     * @return 成功：受影响的记录数，失败：0
     */
    public int del(List<String> listKeyid);

    /**
     * 根据ID获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param id 主键ID
     * @return 实体类
     */
    public SysTwolevelmenu findById(String id);

    /**
     * 根据编号获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param menucode 编号
     * @return 实体类
     */
    public SysTwolevelmenu findByCode(String menucode);

    /**
     * 获取最大排序号
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param onelevelmenuid 一级菜单ID
     * @return 数据库中的最大排序号
     */
    public int findMaxSort(String onelevelmenuid);

    /**
     * 根据一级菜单ID获取所属的二级菜单
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param onelevelmenuid 一级菜单ID
     * @return 实体类集合
     */
    public List<SysTwolevelmenu> findByOneLevelMenuId(String onelevelmenuid);

    /**
     * 获取指定编号的记录数（验证编号是否存在时使用）
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param menucode 菜单编号
     * @param id       主键ID，不为空时则指定的数据不参与运算，编辑时使用
     * @return 记录数
     */
    public int countByCode(@Param("menucode") String menucode, @Param("id") String id);
}
