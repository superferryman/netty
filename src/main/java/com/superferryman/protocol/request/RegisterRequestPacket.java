package com.superferryman.protocol.request;

import com.superferryman.pojo.User;
import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;

/**
 * @Author superferryman
 * @Date 2019/5/9 22:37
 */
public class RegisterRequestPacket extends Packet {

    /**
     * 注册的 User 对象
     */
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Byte getCommand() {
        return Command.REGISTER_REQUEST;
    }
}
