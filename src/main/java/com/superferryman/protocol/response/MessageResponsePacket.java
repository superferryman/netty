package com.superferryman.protocol.response;

import com.superferryman.protocol.Packet;
import lombok.Data;

import static com.superferryman.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @Author superferryman
 * @Date 2019/4/22 12:35
 */
public class MessageResponsePacket extends Packet {
    /**
     * 来源用户标识符
     */
    private String fromUserId;

    /**
     * 来源用户名
     */
    private String fromUsername;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 标识信息是否发送成功
     */
    private boolean success;

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
