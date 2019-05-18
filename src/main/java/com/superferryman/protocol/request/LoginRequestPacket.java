package com.superferryman.protocol.request;

import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;
import lombok.Data;

/**
 * @Author superferryman
 * @Date 2019/4/22 10:46
 */
public class LoginRequestPacket extends Packet {
    /**
     * 密码
     */
    private String password;

    /**
     * 用户 ID
     */
    private String userId;

    public LoginRequestPacket() {
    }

    public LoginRequestPacket(String password, String userId) {
        this.password = password;
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
