package com.superferryman.client.handler;

import com.superferryman.client.myChatClient.api.AcceptAPI;
import com.superferryman.pojo.User;
import com.superferryman.protocol.response.LoginResponsePacket;
import com.superferryman.session.Session;
import com.superferryman.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author superferryman
 * @Date 2019/4/22 20:03
 */
@ChannelHandler.Sharable
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    public static final LoginResponseHandler INSTANCE = new LoginResponseHandler();

    private LoginResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket responsePacket) throws Exception {
        User user = responsePacket.getUser();

        if (responsePacket.isSuccess()) {
            System.out.println("[" + user.getUsername() + "]登录成功，userId 为: " + user.getUserId());
            // 登录成功绑定当前 channel
            SessionUtil.bindSession(new Session(user.getUserId(), user.getUsername()), ctx.channel());
            new AcceptAPI().loginSuccessHandle(new com.superferryman.client.myChatClient.bean.User(
                    user.getUserId(), user.getUsername(), user.getAvator()
            ));
        } else {
            System.out.println("登录失败，原因：" + responsePacket.getMsg());
            new AcceptAPI().loginFailHandle();
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }
}
