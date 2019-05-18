package com.superferryman.server.handler;

import com.superferryman.dao.impl.GroupDAOImpl;
import com.superferryman.dao.impl.UserDAOImpl;
import com.superferryman.pojo.Group;
import com.superferryman.pojo.User;
import com.superferryman.protocol.request.LoginRequestPacket;
import com.superferryman.protocol.response.LoginResponsePacket;
import com.superferryman.session.Session;
import com.superferryman.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.Date;
import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/4/22 20:20
 *
 * 该注解用于显式告诉 Netty 该 handler 是支持多个 channel 共享的
 *
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    protected LoginRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());

        User user = login(loginRequestPacket);

        // 登录校验
        if (user == null) {
            loginResponsePacket.setMsg("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败！");
        } else if (SessionUtil.getChannel(user.getUserId()) != null) {
            loginResponsePacket.setMsg("该用户已登录");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败！");
        }else {
            loginResponsePacket.setUser(user);
            loginResponsePacket.setSuccess(true);
            System.out.println("[" + user.getUsername() + "]登录成功");
            // 绑定当前 channel 与依据当前客户端信息创建的 session
            SessionUtil.bindSession(new Session(user.getUserId(), user.getUsername()), ctx.channel());
            SessionUtil.bindLoginUser(user);
            bindGroupChannel(user.getUserId(), ctx);
        }
        ctx.writeAndFlush(loginResponsePacket);
    }

    private User login(LoginRequestPacket packet) {
        try {
            User user = UserDAOImpl.INSTANCE.findByUserIdAndPassword(packet.getUserId(), packet.getPassword());
            if (user != null) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 用户断开连接时取消绑定
        SessionUtil.unBindSession(ctx.channel());
    }

    private void bindGroupChannel(String userId, ChannelHandlerContext ctx) {
        try {
            List<Group> groups = GroupDAOImpl.INSTANCE.findAll();
            if (groups.size() > 0) {
                for (Group group : groups) {
                    ChannelGroup channelGroup = SessionUtil.getChannelGroup(group.getGroupId());
                    if (channelGroup == null) {
                        channelGroup = new DefaultChannelGroup(ctx.executor());
                        SessionUtil.bindChannelGroup(group.getGroupId(), channelGroup);
                    }
                    channelGroup.add(ctx.channel());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
