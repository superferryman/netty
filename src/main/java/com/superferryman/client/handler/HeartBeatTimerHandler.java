package com.superferryman.client.handler;

import com.superferryman.protocol.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @Author superferryman
 * @Date 2019/4/24 19:53
 */
@ChannelHandler.Sharable
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    public static final HeartBeatTimerHandler INSTANCE = new HeartBeatTimerHandler();

    private HeartBeatTimerHandler() {}

    /**
     * 发送心跳包的间隔时长(单位 s )
     */
    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);
        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
