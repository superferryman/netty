package com.superferryman.client.handler.operation.impl;

import com.superferryman.client.handler.operation.Command;
import com.superferryman.protocol.Packet;
import com.superferryman.protocol.request.QueryGroupListRequestPacket;
import io.netty.channel.Channel;

/**
 * @Author superferryman
 * @Date 2019/5/11 13:44
 */
public class QueryGroupListCommandManager implements Command {
    @Override
    public void exec(Packet packet, Channel channel) {
        channel.writeAndFlush((QueryGroupListRequestPacket) packet);
    }
}
