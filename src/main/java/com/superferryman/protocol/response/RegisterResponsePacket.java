package com.superferryman.protocol.response;

import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;

/**
 * @Author superferryman
 * @Date 2019/5/9 23:30
 */
public class RegisterResponsePacket extends Packet {

    /**
     * 返回登录凭证
     */
    private String userId;

    /**
     * 标识注册是否成功
     */
    private boolean success;

    /**
     * 登录失败时的失败信息
     */
    private String message;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        return Command.REGISTER_RESPONSE;
    }
}
