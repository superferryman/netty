package com.superferryman.dao;

import com.superferryman.pojo.Message;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/12 12:08
 */
public interface MessageDAO extends BaseDAO<Message, Long> {
    /**
     * 根据消息类型查找消息
     * @param type 消息类型
     * @return 对应的消息列表
     * @throws Exception 处理过程中发生错误均抛出异常到业务层处理
     */
    List<Message> findByType(int type) throws Exception;

    /**
     * 根据发送者查找消息
     * @param senderId 发送者 id
     * @return 对应的消息列表
     * @throws Exception 处理过程中发生错误均抛出异常到业务层处理
     */
    List<Message> findBySender(String senderId) throws Exception;

    /**
     * 根据接收者查找消息
     * @param messageType 消息类型
     * @param receiverId 接收者 id
     * @return 对应的消息列表
     * @throws Exception 处理过程中发生错误均抛出异常到业务层处理
     */
    List<Message> findByReceiver(int messageType, String receiverId) throws Exception;

    /**
     * 根据用户 id 查找用户所有消息
     * @param userId 用户 id
     * @return 对应的消息列表
     * @throws Exception 处理过程中发生错误均抛出异常到业务层处理
     */
    List<Message> findAllMessage(String userId) throws Exception;

    /**
     * 根据发送者和接收者查找指定类型的消息
     * @param senderId 发送者 id
     * @param receiverId 接收者 id
     * @param type 消息类型
     * @return 对应的消息列表
     * @throws Exception 处理过程中发生错误均抛出异常到业务层处理
     */
    List<Message> findBySenderAndReceiver(String senderId, String receiverId, int type) throws Exception;
}
