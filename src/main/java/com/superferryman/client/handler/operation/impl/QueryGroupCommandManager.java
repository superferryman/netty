package com.superferryman.client.handler.operation.impl;

import com.superferryman.client.handler.operation.Command;
import com.superferryman.protocol.Packet;
import com.superferryman.protocol.request.QueryGroupRequestPacket;
import io.netty.channel.Channel;

/**
 * @Author superferryman
 * @Date 2019/5/11 10:48
 */
public class QueryGroupCommandManager implements Command {
    @Override
    public void exec(Packet packet, Channel channel) {
        channel.writeAndFlush((QueryGroupRequestPacket) packet);
    }
}
