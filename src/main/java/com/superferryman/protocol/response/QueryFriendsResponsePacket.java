package com.superferryman.protocol.response;

import com.superferryman.pojo.User;
import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/10 8:50
 */
public class QueryFriendsResponsePacket extends Packet {

    /**
     * 好友列表
     */
    private List<User> friends;

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    @Override
    public Byte getCommand() {
        return Command.QUERY_FRIENDS_RESPONSE;
    }
}
