package com.superferryman.dao;

import com.superferryman.pojo.Friend;
import com.superferryman.pojo.User;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/7 10:44
 */
public interface FriendDAO {
    /**
     * 添加好友
     * @param userId 用户 id
     * @param friendId 好友 id
     * @return 添加是否成功
     * @throws Exception 处理过程中发生错误均抛出异常到业务层处理
     */
    boolean add(String userId, String friendId) throws Exception;

    /**
     * 删除好友
     * @param userId 用户 id
     * @param friendId 好友 id
     * @return 删除是否成功
     * @throws Exception 处理过程中发生错误均抛出异常到业务层处理
     */
    boolean delete(String userId, String friendId) throws Exception;

    /**
     * 获取好友列表
     * @param userId 用户 id
     * @return 好友列表
     * @throws Exception 处理过程中发生错误均抛出异常到业务层处理
     */
    List<User> findAll(String userId) throws Exception;

    /**
     * 获取某个好友信息
     * @param userId 用户 id
     * @param friendId 好友 id
     * @return 对应的好友
     * @throws Exception 处理过程中发生错误均抛出异常到业务层处理
     */
    Friend findFriend(String userId, String friendId) throws Exception;
}
