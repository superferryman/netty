package com.superferryman.server.handler;

import com.superferryman.dao.impl.FriendDAOImpl;
import com.superferryman.dao.impl.UserDAOImpl;
import com.superferryman.protocol.request.AddFriendRequestPacket;
import com.superferryman.protocol.response.AddFriendResponsePacket;
import com.superferryman.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author superferryman
 * @Date 2019/5/10 9:22
 */
@ChannelHandler.Sharable
public class AddFriendRequestHandler extends SimpleChannelInboundHandler<AddFriendRequestPacket> {

    public static final AddFriendRequestHandler INSTANCE = new AddFriendRequestHandler();

    private AddFriendRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AddFriendRequestPacket requestPacket) throws Exception {
        AddFriendResponsePacket responsePacket = new AddFriendResponsePacket();
        String userId = requestPacket.getUserId();
        String friendId = requestPacket.getFriendId();
        // 避免出现 id 不存在
        if (userId != null && friendId != null) {
            // 不允许添加自己
            if (userId.equals(friendId)) {
                responsePacket.setSuccess(false);
                responsePacket.setMessage("无法添加自身");
            } else {
                if (FriendDAOImpl.INSTANCE.add(userId, friendId)) {
                    responsePacket.setSuccess(true);
                    responsePacket.setFriend(UserDAOImpl.INSTANCE.findById(friendId));
                } else {
                    responsePacket.setSuccess(false);
                    responsePacket.setMessage("好友添加异常");
                }
            }
        } else {
            responsePacket.setSuccess(false);
            responsePacket.setMessage("该用户不存在");
        }
        Channel channel = SessionUtil.getChannel(requestPacket.getFriendId());
        if (channel != null) {
            AddFriendResponsePacket newResponsePacket = new AddFriendResponsePacket();
            newResponsePacket.setFriend(UserDAOImpl.INSTANCE.findById(userId));
            newResponsePacket.setSuccess(true);
            channel.writeAndFlush(newResponsePacket);
        }
        ctx.writeAndFlush(responsePacket);
    }
}
