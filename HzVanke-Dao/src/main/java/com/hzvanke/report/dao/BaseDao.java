package com.hzvanke.report.dao;

import com.hzvanke.report.commons.PageUtil;
import com.hzvanke.report.pojo.BasePojo;
import com.sun.istack.internal.NotNull;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @描述:
 * @作者: 张召
 * @创建时间: 2019-09-24 10:40
 * @版本: 1.0
 **/
public interface BaseDao {

    /**
     * 根据ID获取单条记录
     *
     * @param id
     * @return
     * @Author: linjiasheng
     * @Date: 2019.8.16 18:34
     **/
    BasePojo findById(@NotNull String id);

    /**
     * 根据查询信息获取LIST
     *
     * @param entity
     * @return
     * @Author: linjiasheng
     * @Date: 2019.8.16 18:35
     **/
    List<BasePojo> findList(@NotNull BasePojo entity, PageUtil pageUtil);

    /**
     * 根据查询信息获取LIST的个数用于分页
     *
     * @param entity
     * @return
     * @Author: linjiasheng
     * @Date: 2019.8.16 18:35
     **/
    int findCount(@NotNull BasePojo entity);

    /**
     * 根据数据库字段带入相应值查询(List)
     *
     * @param key
     * @param value
     * @return
     * @Date: 2019.8.26 11:11
     **/
    List<BasePojo> findObjectList(@Param("key") String key, @Param("value") String value);

    /**
     * 根据某个查询条件查询最大排序号
     *
     * @param str 查询条件，可为空
     * @date 2019/10/30 14:28
     * 版本: v1.1
     */
    int maxSort(String str);

    /**
     * 保存
     *
     * @param entity
     * @return
     * @Author: linjiasheng
     * @Date: 2019.8.16 18:35
     **/
    void add(@NotNull BasePojo entity);

    /**
     * 修改
     *
     * @param entity
     * @return
     * @Author: linjiasheng
     * @Date: 2019.8.16 18:35
     **/
    void update(@NotNull BasePojo entity);

    /**
     * 删除
     *
     * @param id
     * @return
     * @Author: linjiasheng
     * @Date: 2019.8.16 18:35
     **/
    void delete(@NotNull String id);

    /**
     * 数组删除
     *
     * @author 作者: 张召
     * @date 2019/10/30 14:23
     */
    void deleteByArray(@NotNull String[] ids);
}
