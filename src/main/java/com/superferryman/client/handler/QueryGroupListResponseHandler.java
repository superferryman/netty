package com.superferryman.client.handler;

import com.superferryman.client.myChatClient.api.AcceptAPI;
import com.superferryman.client.myChatClient.bean.User;
import com.superferryman.pojo.Group;
import com.superferryman.protocol.response.QueryGroupListResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author superferryman
 * @Date 2019/5/11 11:23
 */
@ChannelHandler.Sharable
public class QueryGroupListResponseHandler extends SimpleChannelInboundHandler<QueryGroupListResponsePacket> {

    public static final QueryGroupListResponseHandler INSTANCE = new QueryGroupListResponseHandler();

    private QueryGroupListResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QueryGroupListResponsePacket responsePacket) throws Exception {
        List<Group> groups = responsePacket.getGroupList();
        if (groups != null) {
            List<User> list = new ArrayList<>();
            for (Group group : groups) {
                list.add(new User(String.valueOf(group.getGroupId()), group.getGroupName(), group.getAvator()));
            }
            new AcceptAPI().loginRequestGroupListHandle(list);
        }
    }
}
