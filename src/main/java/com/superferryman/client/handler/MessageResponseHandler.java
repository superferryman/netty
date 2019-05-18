package com.superferryman.client.handler;

import com.superferryman.client.myChatClient.api.AcceptAPI;
import com.superferryman.protocol.common.StringConst;
import com.superferryman.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author superferryman
 * @Date 2019/4/22 20:19
 */
@ChannelHandler.Sharable
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    public static final MessageResponseHandler INSTANCE = new MessageResponseHandler();

    private MessageResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        String fromUserId = messageResponsePacket.getFromUserId();
        String fromUserName = messageResponsePacket.getFromUsername();
        if (messageResponsePacket.isSuccess()) {
            System.out.println(fromUserId + ":" + fromUserName + " -> " + messageResponsePacket.getMessage());
            // 调用 UI 接口显示消息
            new AcceptAPI().chatMessageHandle(fromUserId, messageResponsePacket.getMessage());
        } else {
            System.out.println(fromUserId + ":" + fromUserName + " -> " + messageResponsePacket.getMessage());
            if (messageResponsePacket.getMessage().equals(StringConst.ERROR_NOT_LOGIN)) {
                new AcceptAPI().friendOfflineMessage(fromUserId);
            } else if (messageResponsePacket.getMessage().equals(StringConst.ERROR_NOT_FRIEND)) {
                new AcceptAPI().youHaveBeiLaHei(fromUserId);
            }
        }
    }
}
