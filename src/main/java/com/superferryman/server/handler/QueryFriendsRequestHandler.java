package com.superferryman.server.handler;

import com.superferryman.dao.impl.FriendDAOImpl;
import com.superferryman.pojo.User;
import com.superferryman.protocol.request.QueryFriendsRequestPacket;
import com.superferryman.protocol.response.QueryFriendsResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/10 8:46
 */
@ChannelHandler.Sharable
public class QueryFriendsRequestHandler extends SimpleChannelInboundHandler<QueryFriendsRequestPacket> {

    public static final QueryFriendsRequestHandler INSTANCE = new QueryFriendsRequestHandler();

    private QueryFriendsRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QueryFriendsRequestPacket requestPacket) throws Exception {
        String userId = requestPacket.getUserId();
        // 根据用户 id 获取所有好友
        List<User> users = FriendDAOImpl.INSTANCE.findAll(userId);
        QueryFriendsResponsePacket responsePacket = new QueryFriendsResponsePacket();
        responsePacket.setFriends(users);
        ctx.writeAndFlush(responsePacket);
    }
}
