package com.superferryman.client.handler;

import com.superferryman.client.myChatClient.api.AcceptAPI;
import com.superferryman.protocol.response.DeleteFriendResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author superferryman
 * @Date 2019/5/10 10:51
 */
@ChannelHandler.Sharable
public class DeleteFriendResponseHandler extends SimpleChannelInboundHandler<DeleteFriendResponsePacket> {

    public static final DeleteFriendResponseHandler INSTANCE = new DeleteFriendResponseHandler();

    private DeleteFriendResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DeleteFriendResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            System.out.println("好友删除成功");
            new AcceptAPI().deleteFriendHandle(responsePacket.getFriendId());
        } else {
            if ("好友删除异常".equals(responsePacket.getMessage())) {
                new AcceptAPI().deleteFriendHandle(responsePacket.getFriendId());
            }
            System.out.println(responsePacket.getMessage());
        }
    }
}
