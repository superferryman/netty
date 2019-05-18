package com.superferryman.protocol.request;

import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;

/**
 * @Author superferryman
 * @Date 2019/5/10 10:31
 */
public class DeleteFriendRequestPacket extends Packet {

    /**
     * 用户 id
     */
    private String userId;

    /**
     * 待删除好友 id
     */
    private String friendId;

    public DeleteFriendRequestPacket() {
    }

    public DeleteFriendRequestPacket(String userId, String friendId) {
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
        return Command.DELETE_FRIEND_REQUEST;
    }
}
