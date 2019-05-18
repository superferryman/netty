package com.superferryman.server.handler;

import com.superferryman.dao.impl.FriendDAOImpl;
import com.superferryman.protocol.request.DeleteFriendRequestPacket;
import com.superferryman.protocol.response.DeleteFriendResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author superferryman
 * @Date 2019/5/10 10:34
 */
@ChannelHandler.Sharable
public class DeleteFriendRequestHandler extends SimpleChannelInboundHandler<DeleteFriendRequestPacket> {

    public static final DeleteFriendRequestHandler INSTANCE = new DeleteFriendRequestHandler();

    private DeleteFriendRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DeleteFriendRequestPacket requestPacket) throws Exception {
        DeleteFriendResponsePacket responsePacket = new DeleteFriendResponsePacket();
        String userId = requestPacket.getUserId();
        String friendId = requestPacket.getFriendId();
        // 避免出现 id 不存在
        if (userId != null && friendId != null) {
            // 不允许添加自己
            if (userId.equals(friendId)) {
                responsePacket.setSuccess(false);
                responsePacket.setMessage("无法删除自身");
            } else {
                if (FriendDAOImpl.INSTANCE.delete(userId, friendId)) {
                    responsePacket.setSuccess(true);
                    responsePacket.setFriendId(requestPacket.getFriendId());
                } else {
                    responsePacket.setSuccess(false);
                    responsePacket.setMessage("好友删除异常");
                }
            }
        } else {
            responsePacket.setSuccess(false);
            responsePacket.setMessage("该好友不存在");
        }
        ctx.writeAndFlush(responsePacket);
    }
}
