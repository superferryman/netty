package com.superferryman.protocol.response;

import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;

/**
 * @Author superferryman
 * @Date 2019/5/10 10:37
 */
public class DeleteFriendResponsePacket extends Packet {

    /**
     * 标识是否删除好友成功
     */
    private boolean success;

    /**
     * 删除好友失败时的错误信息
     */
    private String message;

    /**
     * 删除好友的 id
     */
    private String friendId;

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.DELETE_FRIEND_RESPONSE;
    }
}
