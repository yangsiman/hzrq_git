package com.hzvanke.report.service.common;

import com.github.pagehelper.util.StringUtil;
import com.hzvanke.report.commons.PageUtil;
import com.hzvanke.report.commons.Utils;
import com.hzvanke.report.dao.CareDao;
import com.hzvanke.report.pojo.BasePojo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;


public class BaseServiceImpl<T extends BasePojo> implements BaseService<T> {

    @Autowired
    CareDao<T> dao;

    @Override
    public T findById(String id) {
        return dao.findById(id);
    }

    @Override
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    @Override
    public void add(T entity) {
        if (StringUtil.isEmpty(entity.getId())){
            entity.setId(Utils.CreateNewKeyid());
        }
        entity.setCreateTime(new Date());
        dao.add(entity);
    }

    @Override
    public void update(T entity) {
         dao.update(entity);
    }

    @Override
    public void delete(String id) {
         dao.delete(id);
    }

    @Override
    public void deleteObj(String sql) {
         dao.deleteObj(sql);
    }

    @Override
    public T findObject(String key, String value) {
        return dao.findObject(key,value);
    }

    @Override
    public PageUtil<T> pageList(PageUtil pageUtil, T entity) {
        entity.setPageUtil(pageUtil);
        pageUtil.setList(dao.findList(entity));
        entity.setPageUtil(null);
        pageUtil.setTotalNum(dao.findList(entity).size());
        return pageUtil;
    }

    @Override
    public List<T> findObjectList(String key, String value) {
        return dao.findObjectList(key,value);
    }

    public int maxSort(){
        return dao.maxSort();
    }
}
