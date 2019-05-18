package com.superferryman.server.handler;

import com.superferryman.dao.impl.UserDAOImpl;
import com.superferryman.pojo.User;
import com.superferryman.protocol.request.QueryUserRequestPacket;
import com.superferryman.protocol.response.QueryUserResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/11 10:08
 */
@ChannelHandler.Sharable
public class QueryUserRequestHandler extends SimpleChannelInboundHandler<QueryUserRequestPacket> {

    public static final QueryUserRequestHandler INSTANCE = new QueryUserRequestHandler();

    private QueryUserRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QueryUserRequestPacket requestPacket) throws Exception {
        String keyword = requestPacket.getKeyword();
        List<User> users = UserDAOImpl.INSTANCE.findByKeyword(keyword);
        if (users == null || users.size() <= 0) {
            ctx.writeAndFlush(new QueryUserResponsePacket(null));
        } else {
            ctx.writeAndFlush(new QueryUserResponsePacket(users));
        }
    }
}
