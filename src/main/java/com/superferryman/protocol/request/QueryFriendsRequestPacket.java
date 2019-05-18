package com.superferryman.protocol.request;

import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;

/**
 * @Author superferryman
 * @Date 2019/5/10 8:44
 */
public class QueryFriendsRequestPacket extends Packet {

    /**
     * 用户 id
     */
    private String userId;

    public QueryFriendsRequestPacket() {
    }

    public QueryFriendsRequestPacket(String userId) {
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
        return Command.QUERY_FRIENDS_REQUEST;
    }
}
