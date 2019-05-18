package com.superferryman.dao;

import com.superferryman.pojo.User;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/4/27 10:34
 *
 * 用户 DAO 接口
 *
 */
public interface UserDAO extends BaseDAO<User, String> {
    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 对应的用户
     * @throws Exception 处理过程中出现任何错误均抛出至业务层处理
     */
    User findByUsername(String username) throws Exception;

    /**
     * 根据关键字查找用户，用于模糊搜索用户
     * @param keyword 关键字
     * @return 对应的用户列表(关键字可能会查到多个相同用户)
     * @throws Exception 处理过程中出现任何错误均抛出至业务层处理
     */
    List<User> findByKeyword(String keyword) throws Exception;

    /**
     * 根据用户 Id 和用户密码查询用户
     * @param userId 用户 Id
     * @param password 用户密码
     * @return 符合条件的用户
     * @throws Exception 处理过程中出现任何错误均抛出至业务层处理
     */
    User findByUserIdAndPassword(String userId, String password) throws Exception;
}
