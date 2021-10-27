package com.hzvanke.report.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公共DAO层
 * @Author: linjiasheng
 * @Date: 2019.8.16 17:45
 * @return
 **/
public interface CareDao<T> {


    /**
     * 根据ID获取单条记录
     * @Author: linjiasheng
     * @param id
     * @Date: 2019.8.16 18:34
     * @return
     **/
    T findById(String id);

    /**
     * 根据查询信息获取LIST
     * @Author: linjiasheng
     * @param entity
     * @Date: 2019.8.16 18:35
     * @return
     **/
    List<T> findList(T entity);

    /**
     * 保存
     * @Author: linjiasheng
     * @param entity
     * @Date: 2019.8.16 18:35
     * @return
     **/
    void add(T entity);

    /**
     * 修改
     * @Author: linjiasheng
     * @param entity
     * @Date: 2019.8.16 18:35
     * @return
     **/
    void update(T entity);

    /**
     * 删除
     * @Author: linjiasheng
     * @param id
     * @Date: 2019.8.16 18:35
     * @return
     **/
    void delete(String id);

    void deleteObj(@Param("sql") String sql) ;

    /**
     * 根据数据库字段带入相应值查询
     * @Author: linjiasheng
     *
     * ${key} = #{value}
     * @param key 数据库字段
     * @param value 值
     * @Date: 2019.8.16 18:36
     * @return
     **/
    T findObject(@Param("key") String key, @Param("value") String value);

    /**
     * 根据数据库字段带入相应值查询(List)
     * @Author: linjiasheng
     * @param key
     * @param value
     * @Date: 2019.8.26 11:11
     * @return
     **/
    List<T> findObjectList(@Param("key") String key, @Param("value") String value);

    /**
     * 查询最大Sort
     * @Author: linjiasheng
     * @Date: 2019.20.29 9:31
     * @return
     **/
    int maxSort();
}
