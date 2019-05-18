package com.superferryman.client.handler;

import com.superferryman.client.myChatClient.api.AcceptAPI;
import com.superferryman.client.myChatClient.bean.User;
import com.superferryman.pojo.Group;
import com.superferryman.protocol.response.QueryGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author superferryman
 * @Date 2019/5/11 10:56
 */
@ChannelHandler.Sharable
public class QueryGroupResponseHandler extends SimpleChannelInboundHandler<QueryGroupResponsePacket> {

    public static final QueryGroupResponseHandler INSTANCE = new QueryGroupResponseHandler();

    private QueryGroupResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QueryGroupResponsePacket responsePacket) throws Exception {
        List<Group> groups = responsePacket.getGroupList();
        List<User> list = new ArrayList<>();
        if (groups != null && groups.size() > 0) {
            for (Group group : groups) {
                list.add(new User(
                        String.valueOf(group.getGroupId()), group.getGroupName(), group.getAvator()
                ));
            }
        }
        new AcceptAPI().queryGroupHandle(list);
    }
}
