package com.superferryman.client.handler;

import com.superferryman.client.myChatClient.api.AcceptAPI;
import com.superferryman.pojo.User;
import com.superferryman.protocol.response.QueryFriendsResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/10 8:55
 */
@ChannelHandler.Sharable
public class QueryFriendsResponseHandler extends SimpleChannelInboundHandler<QueryFriendsResponsePacket> {

    public static final QueryFriendsResponseHandler INSTANCE = new QueryFriendsResponseHandler();

    private QueryFriendsResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QueryFriendsResponsePacket responsePacket) throws Exception {
        List<User> friends = responsePacket.getFriends();
        List<com.superferryman.client.myChatClient.bean.User> userList = new ArrayList<>();
        if (friends != null && friends.size() > 0) {
            for (User user : friends) {
                userList.add(new com.superferryman.client.myChatClient.bean.User(
                        user.getUserId(), user.getUsername(), user.getAvator()
                ));
            }
            new AcceptAPI().loginRequestFriendListHandle(userList);
        } else {
            System.out.println("目前您还没有好友或当前输入用户Id为不存在");
        }
    }
}
