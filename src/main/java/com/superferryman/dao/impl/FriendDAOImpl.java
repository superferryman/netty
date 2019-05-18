package com.superferryman.dao.impl;

import com.superferryman.dao.FriendDAO;
import com.superferryman.pojo.Friend;
import com.superferryman.pojo.User;
import com.superferryman.util.DatabaseUtil;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/12 0:05
 */
public class FriendDAOImpl implements FriendDAO {

    public static final FriendDAOImpl INSTANCE = new FriendDAOImpl();

    private FriendDAOImpl() {}

    @Override
    public boolean add(String userId, String friendId) throws Exception {
        int row = 0;
        int row2 = 0;
        if (findFriend(userId, friendId) == null && findFriend(friendId, userId) == null) {
            String sql = "insert into friend values(?, ?)";
            // 双向添加
            row = DatabaseUtil.execUpdate(sql, userId, friendId);
            row2 = DatabaseUtil.execUpdate(sql, friendId, userId);
        }
        return row > 0 && row2 > 0;
    }

    @Override
    public boolean delete(String userId, String friendId) throws Exception {
        String sql = "delete from friend where userId = ? and friendId = ?";
        // 双向删除
        int row = DatabaseUtil.execUpdate(sql, userId, friendId);
        int row2 = DatabaseUtil.execUpdate(sql, friendId, userId);
        return row > 0 && row2 > 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAll(String userId) throws Exception {
        String sql = "select user.userId, username, password, avator from friend, user where friend.userId = ? and friendId = user.userId";
        return (List<User>) DatabaseUtil.execQuery(sql, User.class, userId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Friend findFriend(String userId, String friendId) throws Exception {
        User friend = UserDAOImpl.INSTANCE.findById(friendId);
        if (friend == null) {
            return null;
        }
        String sql = "select userId, friendId from friend where userId = ? and friendId = ?";
        List<Friend> friendList = (List<Friend>) DatabaseUtil.execQuery(sql, Friend.class, userId, friendId);
        if (friendList.size() <= 0) {
            return null;
        }
        return friendList.get(0);
    }
}
