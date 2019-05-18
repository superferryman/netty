package com.superferryman.server.handler;

import com.superferryman.dao.impl.MessageDAOImpl;
import com.superferryman.pojo.Message;
import com.superferryman.protocol.request.ListAllMessagesRequestPacket;
import com.superferryman.protocol.response.ListAllMessagesResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/12 18:00
 */
@ChannelHandler.Sharable
public class ListAllMessagesRequestHandler extends SimpleChannelInboundHandler<ListAllMessagesRequestPacket> {

    public static final ListAllMessagesRequestHandler INSTANCE = new ListAllMessagesRequestHandler();

    private ListAllMessagesRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListAllMessagesRequestPacket requestPacket) throws Exception {
        String userId = requestPacket.getUserId();
        List<Message> messages = MessageDAOImpl.INSTANCE.findAllMessage(userId);
        if (messages.size() > 0) {
            ListAllMessagesResponsePacket responsePacket = new ListAllMessagesResponsePacket();
            responsePacket.setMessages(messages);
            ctx.writeAndFlush(responsePacket);
        }
    }
}
