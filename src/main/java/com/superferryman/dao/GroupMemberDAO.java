package com.superferryman.dao;

import com.superferryman.pojo.Group;
import com.superferryman.pojo.GroupMember;
import com.superferryman.pojo.User;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/12 0:38
 */
public interface GroupMemberDAO {
    /**
     * 添加群成员
     * @param groupId 群id
     * @param user 用户对象
     * @return 添加是否成功
     * @throws Exception 处理过程中发生错误均抛出异常到业务层处理
     */
    boolean add(Integer groupId, User user) throws Exception;

    /**
     * 删除群成员
     * @param groupId 群id
     * @param userId 用户对象
     * @return 删除是否成功
     * @throws Exception 处理过程中发生错误均抛出异常到业务层处理
     */
    boolean delete(Integer groupId, String userId) throws Exception;

    /**
     * 更新成员昵称
     * @param groupId 群id
     * @param user 群成员对象
     * @param memberNickname 新昵称
     * @return 是否更新成功
     * @throws Exception 处理过程中发生错误均抛出异常到业务层处理
     */
    boolean update(Integer groupId, User user, String memberNickname) throws Exception;

    /**
     * 获取群成员列表
     * @param groupId 群id
     * @return 群成员列表
     * @throws Exception 处理过程中发生错误均抛出异常到业务层处理
     */
    List<GroupMember> listMembers(Integer groupId) throws Exception;

    /**
     * 获取该用户加入的群列表
     * @param userId 用户 id
     * @return 群组对象列表
     * @throws Exception 处理过程中发生错误均抛出异常到业务层处理
     */
    List<Group> listGroups(String userId) throws Exception;
}
