package com.superferryman.client.handler;

import com.superferryman.protocol.response.LogoutResponsePacket;
import com.superferryman.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author superferryman
 * @Date 2019/4/23 17:12
 *
 * 登出操作相当于取绑
 *
 */
@ChannelHandler.Sharable
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    public static final LogoutResponseHandler INSTANCE = new LogoutResponseHandler();

    private LogoutResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) throws Exception {
        System.out.println(SessionUtil.getSession(ctx.channel()).getUsername() + "您已退出登录");
        SessionUtil.unBindSession(ctx.channel());
        ctx.channel().close();
        Thread.sleep(1000);
        System.exit(0);
    }
}
