package com.superferryman.client.handler;

import com.superferryman.client.myChatClient.api.AcceptAPI;
import com.superferryman.client.myChatClient.bean.User;
import com.superferryman.pojo.Group;
import com.superferryman.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @Author superferryman
 * @Date 2019/4/23 18:50
 */
@ChannelHandler.Sharable
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

    public static final JoinGroupResponseHandler INSTANCE = new JoinGroupResponseHandler();

    private JoinGroupResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            Group group = responsePacket.getGroup();
            new AcceptAPI().acceptGroupHandle(new User(
                    String.valueOf(group.getGroupId()), group.getGroupName(), group.getAvator()
            ));
            System.out.println("加入群[" + group.getGroupId() + "]成功!");
        } else {
            System.err.println("加入群失败，原因为：" + responsePacket.getMsg());
        }
    }
}
