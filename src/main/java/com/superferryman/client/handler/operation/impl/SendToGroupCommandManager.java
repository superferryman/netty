package com.superferryman.client.handler.operation.impl;

import com.superferryman.client.handler.operation.Command;
import com.superferryman.protocol.Packet;
import com.superferryman.protocol.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;

/**
 * @Author superferryman
 * @Date 2019/5/6 13:00
 */
public class SendToGroupCommandManager implements Command {
    @Override
    public void exec(Packet packet, Channel channel) {
        System.out.print("发送消息给某个某个群组：");

        channel.writeAndFlush((GroupMessageRequestPacket) packet);
    }
}
