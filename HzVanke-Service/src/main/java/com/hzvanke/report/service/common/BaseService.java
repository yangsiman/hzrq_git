package com.hzvanke.report.service.common;


import com.hzvanke.report.commons.PageUtil;

import java.util.List;

public interface BaseService<T>{

    T findById(String id);

    List<T> findList(T entity);

    void add(T entity);

    void update(T entity);

    void delete(String id);

    T findObject(String key, String value);

    PageUtil<T> pageList(PageUtil pageUtil, T entity);

    List<T> findObjectList(String key, String value);

    /**
     * 选择最大的排序号
     * @return
     */
    int maxSort();

    public void deleteObj(String sql) ;
}
