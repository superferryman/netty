package com.superferryman.protocol.request;

import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;

/**
 * @Author superferryman
 * @Date 2019/5/11 11:13
 */
public class QueryGroupListRequestPacket extends Packet {

    /**
     * 用户 id
     */
    private String userId;

    public QueryGroupListRequestPacket() {
    }

    public QueryGroupListRequestPacket(String userId) {
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
        return Command.QUERY_GROUP_LIST_REQUEST;
    }
}
