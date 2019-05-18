package com.superferryman.dao;

import com.superferryman.pojo.Group;
import com.superferryman.pojo.User;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/7 10:53
 */
public interface GroupDAO extends BaseDAO<Group, Integer> {
    /**
     * 根据群组名查询群组
     * @param groupName 群组名
     * @return 对应的群组对象
     * @throws Exception 处理过程中出现任何错误均抛出至业务层处理
     */
    Group findByName(String groupName) throws Exception;

    /**
     * 根据群组 id 获取群组成员列表
     * @param groupId 群组 id
     * @return 对应的群组列表
     * @throws Exception 处理过程中出现任何错误均抛出至业务层处理
     */
    List<User> listMembers(Integer groupId) throws Exception;

    /**
     * 根据关键词查询群组列表
     * @param keyword 关键词
     * @return 对应的群组列表
     * @throws Exception 处理过程中出现任何错误均抛出至业务层处理
     */
    List<Group> findByKeyword(String keyword) throws Exception;
}
