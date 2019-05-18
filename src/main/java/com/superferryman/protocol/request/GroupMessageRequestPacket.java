package com.superferryman.protocol.request;

import com.superferryman.protocol.Packet;

import static com.superferryman.protocol.command.Command.GROUP_MESSAGE_REQUEST;

/**
 * @Author superferryman
 * @Date 2019/4/23 19:44
 */
public class GroupMessageRequestPacket extends Packet {

    /**
     * 消息内容
     */
    private String message;

    /**
     * 目标群组标识
     */
    private String toGroupId;

    public GroupMessageRequestPacket() { }

    public GroupMessageRequestPacket(String message, String toGroupId) {
        this.message = message;
        this.toGroupId = toGroupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToGroupId() {
        return toGroupId;
    }

    public void setToGroupId(String toGroupId) {
        this.toGroupId = toGroupId;
    }

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }
}
