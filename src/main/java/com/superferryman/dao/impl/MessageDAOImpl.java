package com.superferryman.dao.impl;

import com.superferryman.dao.MessageDAO;
import com.superferryman.pojo.Message;
import com.superferryman.util.DatabaseUtil;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/12 12:17
 */
public class MessageDAOImpl implements MessageDAO {

    public static final MessageDAOImpl INSTANCE = new MessageDAOImpl();

    private MessageDAOImpl() {}

    private static final String ALL_COLUMNS = "id, messageType, senderId, receiverId, content";

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> findByType(int type) throws Exception {
        String sql = "select " + ALL_COLUMNS + " from message where messageType = ?";
        return (List<Message>) DatabaseUtil.execQuery(sql, Message.class, type);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> findBySender(String senderId) throws Exception {
        String sql = "select " + ALL_COLUMNS + " from message where senderId = ?";
        return (List<Message>) DatabaseUtil.execQuery(sql, Message.class, senderId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> findByReceiver(int messageType, String receiverId) throws Exception {
        String sql = "select " + ALL_COLUMNS + " from message where messageType = ? and receiverId = ?";
        return (List<Message>) DatabaseUtil.execQuery(sql, Message.class, messageType, receiverId);
    }

    @Override
    public List<Message> findAllMessage(String userId) throws Exception {
        List<Message> list = findGroupMessageWithoutSender(userId);
        List<Message> friendMessageList = findFriendMessage(userId);
        List<Message> senderMessageList = findBySender(userId);

        // 去重
        list.removeAll(friendMessageList);
        list.addAll(friendMessageList);
        list.removeAll(senderMessageList);
        list.addAll(senderMessageList);

        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> findBySenderAndReceiver(String senderId, String receiverId, int type) throws Exception {
        String sql = "select " + ALL_COLUMNS + " from message where senderId = ? and messageType = ? and receiverId = ?";
        return (List<Message>) DatabaseUtil.execQuery(sql, Message.class, senderId, type, receiverId);
    }

    @Override
    public boolean add(Message message) throws Exception {
        String sql = "insert into message values(null, ?, ?, ?, ?)";
        int row = DatabaseUtil.execUpdate(sql, message.getMessageType(), message.getSenderId(),
                message.getReceiverId(), message.getContent());
        return row > 0;
    }

    @Override
    public boolean delete(Message message) throws Exception {
        String sql = "delete from message where id = ?";
        int row = DatabaseUtil.execUpdate(sql, message.getId());
        return row > 0;
    }

    @Override
    public boolean update(Message obj) throws Exception {
        // 聊天记录莫得更新得到
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Message> findAll() throws Exception {
        String sql = "select " + ALL_COLUMNS + " from message";
        return (List<Message>) DatabaseUtil.execQuery(sql, Message.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Message findById(Long id) throws Exception {
        String sql = "select " + ALL_COLUMNS + " from message where id = ?";
        List<Message> list = (List<Message>) DatabaseUtil.execQuery(sql, Message.class, id);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private List<Message> findGroupMessageWithoutSender(String userId) throws Exception {
        String sql = "select " + ALL_COLUMNS + " from message, groupmember where " +
                "receiverId = groupId and messageType = ? and userId = ? and senderId <> ?";
        return (List<Message>) DatabaseUtil.execQuery(sql, Message.class, Message.TYPE_GROUP, userId, userId);
    }

    @SuppressWarnings("unchecked")
    private List<Message> findFriendMessage(String userId) throws Exception {
        String sql = "select " + ALL_COLUMNS + " from message where messageType = ? and receiverId = ?";
        return (List<Message>) DatabaseUtil.execQuery(sql, Message.class, Message.TYPE_FRIEND, userId);
    }
}
