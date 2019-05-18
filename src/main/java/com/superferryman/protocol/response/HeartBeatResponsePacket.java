package com.superferryman.protocol.response;

import com.superferryman.protocol.Packet;

import static com.superferryman.protocol.command.Command.HEARTBEAT_RESPONSE;

/**
 * @Author superferryman
 * @Date 2019/4/24 20:05
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
