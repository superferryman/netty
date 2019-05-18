package com.superferryman.protocol.response;

import com.superferryman.pojo.User;
import com.superferryman.protocol.Packet;
import lombok.Data;

import static com.superferryman.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @Author superferryman
 * @Date 2019/4/22 12:20
 */
public class LoginResponsePacket extends Packet {
    /**
     * User 对象
     */
    private User user;

    /**
     * 登录成功标识
     */
    private boolean success;

    /**
     * 登录失败时回送的信息
     */
    private String msg;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
