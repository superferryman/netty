package com.superferryman.protocol.request;

import com.superferryman.protocol.Packet;

import static com.superferryman.protocol.command.Command.LOGOUT_REQUEST;

/**
 * @Author superferryman
 * @Date 2019/4/23 16:31
 */
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
