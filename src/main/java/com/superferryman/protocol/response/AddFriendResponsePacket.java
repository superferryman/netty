package com.superferryman.protocol.response;

import com.superferryman.pojo.User;
import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;

/**
 * @Author superferryman
 * @Date 2019/5/10 9:37
 */
public class AddFriendResponsePacket extends Packet {

    /**
     * 好友信息
     */
    private User friend;

    /**
     * 标识添加好友是否成功
     */
    private boolean success;

    /**
     * 添加好友失败时的错误信息
     */
    private String message;

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
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
        return Command.ADD_FRIEND_RESPONSE;
    }
}
