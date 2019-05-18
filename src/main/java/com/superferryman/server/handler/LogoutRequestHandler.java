package com.superferryman.server.handler;

import com.superferryman.dao.impl.UserDAOImpl;
import com.superferryman.pojo.User;
import com.superferryman.protocol.request.LogoutRequestPacket;
import com.superferryman.protocol.response.LogoutResponsePacket;
import com.superferryman.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author superferryman
 * @Date 2019/4/23 16:55
 */
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    protected LogoutRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket logoutRequestPacket) throws Exception {
        String userId = SessionUtil.getSession(ctx.channel()).getUserId();
        User user = UserDAOImpl.INSTANCE.findById(userId);
        if (user != null) {
            SessionUtil.unBindLoginUser(user);
        }
        // 登出时需要取消 channel 的绑定
        SessionUtil.unBindSession(ctx.channel());
        // 回送登出成功响应
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.writeAndFlush(logoutResponsePacket);
        ctx.channel().close();
    }
}
