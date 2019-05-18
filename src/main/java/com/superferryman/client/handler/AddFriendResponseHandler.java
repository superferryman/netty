package com.superferryman.client.handler;

import com.superferryman.client.myChatClient.api.AcceptAPI;
import com.superferryman.pojo.User;
import com.superferryman.protocol.response.AddFriendResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author superferryman
 * @Date 2019/5/10 9:43
 */
@ChannelHandler.Sharable
public class AddFriendResponseHandler extends SimpleChannelInboundHandler<AddFriendResponsePacket> {

    public static final AddFriendResponseHandler INSTANCE = new AddFriendResponseHandler();

    private AddFriendResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddFriendResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            System.out.println(responsePacket.getFriend());
            User friend = responsePacket.getFriend();
            new AcceptAPI().addFriendHandle(new com.superferryman.client.myChatClient.bean.User(
                    friend.getUserId(), friend.getUsername(), friend.getAvator()
            ));
        } else {
            System.out.println(responsePacket.getMessage());
        }
    }
}
