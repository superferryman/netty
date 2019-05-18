package com.superferryman.protocol.response;

import com.superferryman.protocol.Packet;

import static com.superferryman.protocol.command.Command.LOGOUT_RESPONSE;

/**
 * @Author superferryman
 * @Date 2019/4/23 16:31
 */
public class LogoutResponsePacket extends Packet {

    /**
     * 用于判断用户登出是否成功
     */
    private boolean success;

    /**
     * 当用户登出失败时的错误信息
     */
    private String msg;

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
        return LOGOUT_RESPONSE;
    }
}
