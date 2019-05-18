package com.superferryman.dao;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/7 10:15
 *
 * 数据库基础访问接口
 * 抽象出基础访问接口以免后续需要增添新功能而需要写大量重复方法
 *
 */
public interface BaseDAO<T, U> {

    /**
     * 添加对象
     * @param obj 需要添加的对象
     * @return 是否添加成功
     * @throws Exception 处理过程中出现任何错误均抛出至业务层处理
     */
    boolean add(T obj) throws Exception;

    /**
     * 删除对象
     * @param obj 需要删除的对象
     * @return 是否删除成功
     * @throws Exception 处理过程中出现任何错误均抛出至业务层处理
     */
    boolean delete(T obj) throws Exception;

    /**
     * 更新对象
     * @param obj 需要修改的对象
     * @return 是否修改成功
     * @throws Exception 处理过程中出现任何错误均抛出至业务层处理
     */
    boolean update(T obj) throws Exception;

    /**
     * 查找对象的所有数据
     * @return 该对象所有数据的列表
     * @throws Exception 处理过程中出现任何错误均抛出至业务层处理
     */
    List<T> findAll() throws Exception;

    /**
     * 根据 Id 查找对象
     * @param id 对象的 Id
     * @return 对应的对象
     * @throws Exception 处理过程中出现任何错误均抛出至业务层处理
     */
    T findById(U id) throws Exception;
}
