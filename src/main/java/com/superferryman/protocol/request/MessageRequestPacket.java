package com.superferryman.protocol.request;

import com.superferryman.protocol.Packet;

import static com.superferryman.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @Author superferryman
 * @Date 2019/4/22 12:33
 */
public class MessageRequestPacket extends Packet {
    /**
     * 目标标识符
     */
    private String toUserId;

    /**
     * 信息内容
     */
    private String message;

    public MessageRequestPacket() {}

    public MessageRequestPacket(String message) {
        this.message = message;
    }

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
