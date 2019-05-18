package com.superferryman.server.handler;

import com.superferryman.protocol.request.HeartBeatRequestPacket;
import com.superferryman.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author superferryman
 * @Date 2019/4/24 20:06
 */
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

    protected HeartBeatRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket requestPacket) throws Exception {
        ctx.writeAndFlush(new HeartBeatResponsePacket());
    }
}
