package com.superferryman.client.handler.operation.impl;

import com.superferryman.client.handler.operation.Command;
import com.superferryman.protocol.Packet;
import com.superferryman.protocol.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

/**
 * @Author superferryman
 * @Date 2019/5/10 23:14
 */
public class QuitGroupCommandManager implements Command {
    @Override
    public void exec(Packet packet, Channel channel) {
        channel.writeAndFlush((QuitGroupRequestPacket) packet);
    }
}
