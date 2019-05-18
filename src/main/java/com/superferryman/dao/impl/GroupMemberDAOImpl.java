package com.superferryman.dao.impl;

import com.superferryman.dao.GroupMemberDAO;
import com.superferryman.pojo.Group;
import com.superferryman.pojo.GroupMember;
import com.superferryman.pojo.User;
import com.superferryman.util.DatabaseUtil;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/12 8:36
 */
public class GroupMemberDAOImpl implements GroupMemberDAO {

    public static final GroupMemberDAOImpl INSTANCE = new GroupMemberDAOImpl();

    private GroupMemberDAOImpl() {}

    @Override
    public boolean add(Integer groupId, User user) throws Exception {
        String sql = "insert into groupmember values(?, ?, ?, ?)";
        int row = 0;
        try {
            row = DatabaseUtil.execUpdate(sql, groupId, user.getUserId(), user.getUsername(), user.getAvator());
        } catch (Exception e) {
            return false;
        }
        return row > 0;
    }

    @Override
    public boolean delete(Integer groupId, String userId) throws Exception {
        String sql = "delete from groupmember where groupId = ? and userId = ?";
        int row = DatabaseUtil.execUpdate(sql, groupId, userId);
        return row > 0;
    }

    @Override
    public boolean update(Integer groupId, User user, String memberNickname) throws Exception {
        String sql = "update groupmember set memberNickname = ? where groupId = ? and userId = ?";
        int row = DatabaseUtil.execUpdate(sql, memberNickname, groupId, user.getUserId());
        return row > 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<GroupMember> listMembers(Integer groupId) throws Exception {
        String sql = "select userId, memberNickname, avator from groupmember where groupId = ?";
        return (List<GroupMember>) DatabaseUtil.execQuery(sql, GroupMember.class, groupId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Group> listGroups(String userId) throws Exception {
        String sql = "select group.groupId, groupName, groupDesc, creatorId, group.avator from groupmember, `group` where groupmember.groupId = group.groupId and userId = ?";
        return (List<Group>) DatabaseUtil.execQuery(sql, Group.class, userId);
    }
}
