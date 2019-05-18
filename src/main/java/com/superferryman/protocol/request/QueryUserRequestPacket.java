package com.superferryman.protocol.request;

import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;

/**
 * @Author superferryman
 * @Date 2019/5/11 10:02
 */
public class QueryUserRequestPacket extends Packet {

    /**
     * 查询用户的关键词
     */
    private String keyword;

    public QueryUserRequestPacket() {
    }

    public QueryUserRequestPacket(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public Byte getCommand() {
        return Command.QUERY_USER_REQUEST;
    }
}
