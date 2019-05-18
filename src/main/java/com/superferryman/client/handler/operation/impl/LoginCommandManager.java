package com.superferryman.client.handler.operation.impl;

import com.superferryman.client.handler.operation.Command;
import com.superferryman.protocol.Packet;
import com.superferryman.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

/**
 * @Author superferryman
 * @Date 2019/5/10 22:49
 */
public class LoginCommandManager implements Command {

    @Override
    public void exec(Packet packet, Channel channel) {
        channel.writeAndFlush((LoginRequestPacket) packet);
    }
}
