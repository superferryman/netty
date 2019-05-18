package com.superferryman.dao.impl;

import com.superferryman.dao.UserDAO;
import com.superferryman.pojo.User;
import com.superferryman.util.DatabaseUtil;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/11 22:51
 */
public class UserDAOImpl implements UserDAO {

    public static final UserDAOImpl INSTANCE = new UserDAOImpl();

    private UserDAOImpl() {}

    private static final String ALL_COLUMNS = "userId, username, password, avator";

    @SuppressWarnings("unchecked")
    @Override
    public User findByUsername(String username) throws Exception {
        String sql = "select " + ALL_COLUMNS + " from user where username = ?";
        List<User> list = (List<User>) DatabaseUtil.execQuery(sql, User.class, username);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findByKeyword(String keyword) throws Exception {
        String sql = "select " + ALL_COLUMNS + " from user where userId = ? or username like ?";
        return (List<User>) DatabaseUtil.execQuery(sql, User.class, keyword, "%" + keyword + "%");
    }

    @SuppressWarnings("unchecked")
    @Override
    public User findByUserIdAndPassword(String userId, String password) throws Exception {
        String sql = "select " + ALL_COLUMNS + " from user where userId = ? and password = ?";
        List<User> list = (List<User>) DatabaseUtil.execQuery(sql, User.class, userId, password);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public boolean add(User user) throws Exception {
        String sql = "insert into user values(?, ?, ?, ?)";
        int row = 0;
        try {
            row = DatabaseUtil.execUpdate(sql, user.getUserId(), user.getUsername(), user.getPassword(), user.getAvator());
        } catch (Exception e) {
            return false;
        }
        return row > 0;
    }

    @Override
    public boolean delete(User user) throws Exception {
        String sql = "delete from user where userId = ?";
        int row = DatabaseUtil.execUpdate(sql, user.getUserId());
        return row > 0;
    }

    @Override
    public boolean update(User user) throws Exception {
        String sql = "update user set username = ?, password = ?, avator = ? where userId = ?";
        int row = DatabaseUtil.execUpdate(sql, user.getUsername(), user.getPassword(), user.getAvator(), user.getUserId());
        return row > 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAll() throws Exception {
        String sql = "select " + ALL_COLUMNS + " from user";
        return (List<User>) DatabaseUtil.execQuery(sql, User.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public User findById(String userId) throws Exception {
        String sql = "select " + ALL_COLUMNS + " from user where userId = ?";
        List<User> list = (List<User>) DatabaseUtil.execQuery(sql, User.class, userId);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
