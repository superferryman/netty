package com.superferryman.protocol.request;

import com.superferryman.protocol.Packet;

import static com.superferryman.protocol.command.Command.HEARTBEAT_REQUEST;

/**
 * @Author superferryman
 * @Date 2019/4/24 20:03
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
