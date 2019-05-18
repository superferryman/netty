package com.superferryman.client.handler;

import com.superferryman.client.myChatClient.api.AcceptAPI;
import com.superferryman.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author superferryman
 * @Date 2019/4/23 17:13
 *
 * 用于当群聊创建成功时的回显
 *
 */
@ChannelHandler.Sharable
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    public static final CreateGroupResponseHandler INSTANCE = new CreateGroupResponseHandler();

    private CreateGroupResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket responsePacket) throws Exception {
        System.out.print("群创建成功，id 为[" + responsePacket.getGroupId() + "], ");

        // 调用 UI 显示加入的群组
        new AcceptAPI().addGroupHandle(String.valueOf(responsePacket.getGroupId()));
    }
}
