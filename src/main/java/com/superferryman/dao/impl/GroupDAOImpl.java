package com.superferryman.dao.impl;

import com.superferryman.dao.GroupDAO;
import com.superferryman.pojo.Group;
import com.superferryman.pojo.User;
import com.superferryman.util.DatabaseUtil;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/11 23:23
 */
public class GroupDAOImpl implements GroupDAO {

    public static final GroupDAOImpl INSTANCE = new GroupDAOImpl();

    private GroupDAOImpl() {}

    private static final String ALL_COLUMNS = "groupId, groupName, groupDesc, creatorId, avator";

    @SuppressWarnings("unchecked")
    @Override
    public Group findByName(String groupName) throws Exception {
        String sql = "select " + ALL_COLUMNS + " from `group` where groupName like ?";
        List<Group> list = (List<Group>) DatabaseUtil.execQuery(sql, Group.class, "%" + groupName + "%");
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> listMembers(Integer groupId) throws Exception {
        String sql = "select user.userId, username, password, user.avator from groupmember, user where groupId = ? and groupmember.userId = user.userId";
        return (List<User>) DatabaseUtil.execQuery(sql, User.class, groupId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Group> findByKeyword(String keyword) throws Exception {
        String sql = "select " + ALL_COLUMNS + " from `group` where groupName like ? or groupDesc like ?";
        return (List<Group>) DatabaseUtil.execQuery(sql, Group.class, "%" + keyword + "%", "%" + keyword + "%");
    }

    @Override
    public boolean add(Group group) throws Exception {
        String sql = "insert into `group` values(null, ?, ?, ?, ?)";
        int row = 0;
        try {
            row = DatabaseUtil.execUpdate(sql, group.getGroupName(),
                    group.getGroupDesc(), group.getCreatorId(), group.getAvator());
        } catch (Exception e) {
            return false;
        }
        return row > 0;
    }

    @Override
    public boolean delete(Group group) throws Exception {
        String sql = "delete from `group` where groupId = ?";
        int row = DatabaseUtil.execUpdate(sql, group.getGroupId());
        return row > 0;
    }

    @Override
    public boolean update(Group group) throws Exception {
        String sql = "update `group` set groupName = ?, groupDesc = ?, creatorId = ?, avator = ? where groupId = ?";
        int row = DatabaseUtil.execUpdate(sql, group.getGroupName(), group.getGroupDesc(),
                group.getCreatorId(), group.getAvator(), group.getGroupId());
        return row > 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Group> findAll() throws Exception {
        String sql = "select " + ALL_COLUMNS + " from `group`";
        return (List<Group>) DatabaseUtil.execQuery(sql, Group.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Group findById(Integer groupId) throws Exception {
        String sql = "select " + ALL_COLUMNS + " from `group` where groupId = ?";
        List<Group> list = (List<Group>) DatabaseUtil.execQuery(sql, Group.class, groupId);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
