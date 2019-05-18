package com.superferryman.protocol.request;

import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;

/**
 * @Author superferryman
 * @Date 2019/5/12 17:55
 */
public class ListAllMessagesRequestPacket extends Packet {

    /**
     * 用户 id
     */
    private String userId;

    public ListAllMessagesRequestPacket() {
    }

    public ListAllMessagesRequestPacket(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Byte getCommand() {
        return Command.LIST_MESSAGES_REQUEST;
    }
}
