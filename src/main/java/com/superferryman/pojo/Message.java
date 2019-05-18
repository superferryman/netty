package com.superferryman.pojo;

/**
 * @Author superferryman
 * @Date 2019/5/12 12:05
 */
public class Message {

    /**
     * 消息类型
     * 0：单聊
     * 1：群聊
     */
    public static final int TYPE_FRIEND = 0;
    public static final int TYPE_GROUP = 1;

    /**
     * 消息 id
     */
    private Long id;

    /**
     * 消息类型
     */
    private int messageType;

    /**
     * 发送方 id
     */
    private String senderId;

    /**
     * 接收方 id
     */
    private String receiverId;

    /**
     * 消息内容
     */
    private String content;

    public Message() {
    }

    public Message(int messageType, String senderId, String receiverId, String content) {
        this.messageType = messageType;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", messageType=" + messageType +
                ", senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
