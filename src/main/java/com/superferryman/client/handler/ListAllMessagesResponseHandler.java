package com.superferryman.client.handler;

import com.superferryman.client.myChatClient.api.AcceptAPI;
import com.superferryman.pojo.Message;
import com.superferryman.protocol.response.ListAllMessagesResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/12 18:10
 */
@ChannelHandler.Sharable
public class ListAllMessagesResponseHandler extends SimpleChannelInboundHandler<ListAllMessagesResponsePacket> {

    public static final ListAllMessagesResponseHandler INSTANCE = new ListAllMessagesResponseHandler();

    private ListAllMessagesResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListAllMessagesResponsePacket responsePacket) throws Exception {
        new AcceptAPI().myAllMessageHandle(responsePacket.getMessages());
    }
}
