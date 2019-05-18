package com.superferryman.protocol.request;

import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;

/**
 * @Author superferryman
 * @Date 2019/5/10 9:19
 */
public class AddFriendRequestPacket extends Packet {

    /**
     * 用户 id
     */
    private String userId;

    /**
     * 要添加的用户 id
     */
    private String friendId;

    public AddFriendRequestPacket() {
    }

    public AddFriendRequestPacket(String userId, String friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    @Override
    public Byte getCommand() {
        return Command.ADD_FRIEND_REQUEST;
    }
}
