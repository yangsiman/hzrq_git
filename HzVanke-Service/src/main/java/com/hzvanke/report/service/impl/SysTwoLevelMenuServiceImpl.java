package com.hzvanke.report.service.impl;

import com.hzvanke.report.dao.SysTwolevelmenuDao;
import com.hzvanke.report.pojo.SysTwolevelmenu;
import com.hzvanke.report.service.face.SysTwoLevelMenuSevice;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 系统二级菜单数据操作接口实现类
 * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
 */
@Service
public class SysTwoLevelMenuServiceImpl implements SysTwoLevelMenuSevice {

    @Autowired
    private SysTwolevelmenuDao sysTwolevelmenuDao;


    /**
     * 新增
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysTwolevelmenu 实体类
     * @return 成功：1，失败：0
     */
    @Override
    public int add(SysTwolevelmenu sysTwolevelmenu) {
        return sysTwolevelmenuDao.add(sysTwolevelmenu);
    }

    /**
     * 修改
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param sysTwolevelmenu 实体类
     * @return成功：1，失败：0
     */
    @Override
    public int update(SysTwolevelmenu sysTwolevelmenu) {
        return sysTwolevelmenuDao.update(sysTwolevelmenu);
    }

    /**
     * 修改排序号
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param map 使用键值对进行批量更新操作，键为ID，值为Sort
     * @return 成功：true，失败：false
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,readOnly = false,value = "myTxManager")
    public boolean updateSort(Map<String,Integer> map) {
        boolean result = false;
        try {
            Set<String> set = map.keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String id = iterator.next();
                int sort = map.get(id);
                sysTwolevelmenuDao.updateSort(id, sort);
            }
            result = true;//设置返回值
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 根据ID集合批量删除
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param listKeyid 主键ID集合
     * @return 成功：受影响的记录数，失败：0
     */
    @Override
    public int del(List<String> listKeyid) {
        return sysTwolevelmenuDao.del(listKeyid);
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
    public SysTwolevelmenu findById(String id) {
        return sysTwolevelmenuDao.findById(id);
    }

    /**
     * 根据编号获取一条记录
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param menucode 编号
     * @return 实体类
     */
    @Override
    public SysTwolevelmenu findByCode(String menucode) {
        return sysTwolevelmenuDao.findByCode(menucode);
    }

    /**
     * 获取最大排序号
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param onelevelmenuid 一级菜单ID
     * @return 数据库中的最大排序号
     */
    @Override
    public int findMaxSort(String onelevelmenuid) {
        return sysTwolevelmenuDao.findMaxSort(onelevelmenuid);
    }

    /**
     * 根据一级菜单ID获取所属的二级菜单
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param onelevelmenuid 一级菜单ID
     * @return 实体类集合
     */
    @Override
    public List<SysTwolevelmenu> findByOneLevelMenuId(String onelevelmenuid) {
        return sysTwolevelmenuDao.findByOneLevelMenuId(onelevelmenuid);
    }

    /**
     * 获取指定编号的记录数（验证编号是否存在时使用）
     * 作者：莫宁辉，开发时间：2019-08-20，版本号：1.1
     * 最后编辑：，编辑时间：，版本号：
     *
     * @param menucode 菜单编号
     * @param id       主键ID，不为空时则指定的数据不参与运算，编辑时使用
     * @return 记录数
     */
    @Override
    public int countByCode(@Param("menucode") String menucode, @Param("id") String id){
        return sysTwolevelmenuDao.countByCode(menucode,id);
    }
}
