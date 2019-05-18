package com.superferryman.client.handler;

import com.superferryman.client.myChatClient.api.AcceptAPI;
import com.superferryman.pojo.User;
import com.superferryman.protocol.response.QueryUserResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/11 10:11
 */
@ChannelHandler.Sharable
public class QueryUserResponseHandler extends SimpleChannelInboundHandler<QueryUserResponsePacket> {

    public static final QueryUserResponseHandler INSTANCE = new QueryUserResponseHandler();

    private QueryUserResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QueryUserResponsePacket responsePacket) throws Exception {
        List<User> users = responsePacket.getUsers();
        List<com.superferryman.client.myChatClient.bean.User> list = new ArrayList<>();
        if (users != null) {
            for (User user : users) {
                list.add(new com.superferryman.client.myChatClient.bean.User(
                        user.getUserId(), user.getUsername(), user.getAvator()
                ));
            }
        }
        new AcceptAPI().queryUserHandle(list);
    }
}
