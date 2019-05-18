package com.superferryman.client.handler;

import com.superferryman.client.myChatClient.api.AcceptAPI;
import com.superferryman.protocol.response.RegisterResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author superferryman
 * @Date 2019/5/9 23:40
 */
@ChannelHandler.Sharable
public class RegisterResponseHandler extends SimpleChannelInboundHandler<RegisterResponsePacket> {

    public static final RegisterResponseHandler INSTANCE = new RegisterResponseHandler();

    private RegisterResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RegisterResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            System.out.println("注册成功， userId 为:" + responsePacket.getUserId());
            new AcceptAPI().registerSuccessHandle(responsePacket.getUserId());
        } else {
            System.out.println("注册失败，原因：" + responsePacket.getMessage());
        }
    }
}
