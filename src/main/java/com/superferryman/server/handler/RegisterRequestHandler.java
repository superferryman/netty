package com.superferryman.server.handler;

import com.superferryman.dao.impl.UserDAOImpl;
import com.superferryman.pojo.User;
import com.superferryman.protocol.request.RegisterRequestPacket;
import com.superferryman.protocol.response.RegisterResponsePacket;
import com.superferryman.util.IDUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author superferryman
 * @Date 2019/5/9 22:46
 */
@ChannelHandler.Sharable
public class RegisterRequestHandler extends SimpleChannelInboundHandler<RegisterRequestPacket> {

    public static final RegisterRequestHandler INSTANCE = new RegisterRequestHandler();

    private RegisterRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterRequestPacket registerRequestPacket) throws Exception {
        RegisterResponsePacket responsePacket = new RegisterResponsePacket();
        // 通过传入的 username 查询用户
        User user = UserDAOImpl.INSTANCE.findByUsername(registerRequestPacket.getUser().getUsername());
        // 该 username 未被使用则允许注册，否则不允许注册
        if (user == null) {
            // 创建一个注册用户
            User registerUser = registerRequestPacket.getUser();
            String userId = IDUtil.randomId();
            registerUser.setUserId(userId);
            // 添加一个新用户
            if (UserDAOImpl.INSTANCE.add(registerUser)) {
                responsePacket.setSuccess(true);
                responsePacket.setUserId(userId);
                System.out.println("[" + registerUser.getUsername() + "]用户注册成功！");
            } else {
                responsePacket.setSuccess(false);
                responsePacket.setMessage("操作失败，请重试");
            }
        } else {
            responsePacket.setSuccess(false);
            responsePacket.setMessage("用户名已存在，请重新注册");
        }
        ctx.writeAndFlush(responsePacket);
    }
}
