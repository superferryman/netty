package com.superferryman.client.handler.operation.impl;

import com.superferryman.client.handler.operation.Command;
import com.superferryman.protocol.Packet;
import com.superferryman.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

/**
 * @Author superferryman
 * @Date 2019/5/6 13:03
 */
public class CreateGroupCommandManager implements Command {

    @Override
    public void exec(Packet packet, Channel channel) {
        channel.writeAndFlush((CreateGroupRequestPacket) packet);
    }
}
