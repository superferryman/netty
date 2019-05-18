package com.superferryman.client.handler;

import com.superferryman.client.myChatClient.api.AcceptAPI;
import com.superferryman.protocol.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author superferryman
 * @Date 2019/4/23 18:51
 */
@ChannelHandler.Sharable
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    public static final QuitGroupResponseHandler INSTANCE = new QuitGroupResponseHandler();

    private QuitGroupResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            System.out.println("退出群聊[" + responsePacket.getGroupId() + "]成功！");
            new AcceptAPI().deleteGroupHandle(responsePacket.getGroupId());

        } else {
            System.out.println("退出群聊[" + responsePacket.getGroupId() + "]失败！");
        }
    }
}
