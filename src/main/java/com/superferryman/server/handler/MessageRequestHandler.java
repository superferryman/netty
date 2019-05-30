package com.superferryman.server.handler;

import com.superferryman.dao.impl.FriendDAOImpl;
import com.superferryman.dao.impl.MessageDAOImpl;
import com.superferryman.dao.impl.UserDAOImpl;
import com.superferryman.pojo.Message;
import com.superferryman.pojo.User;
import com.superferryman.protocol.common.StringConst;
import com.superferryman.protocol.request.MessageRequestPacket;
import com.superferryman.protocol.response.MessageResponsePacket;
import com.superferryman.session.Session;
import com.superferryman.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author superferryman
 * @Date 2019/4/22 20:21
 */
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    private MessageRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket requestPacket) throws Exception {
        // 获取发送方会话信息
        Session session = SessionUtil.getSession(ctx.channel());

        // 如果非好友则直接返回非好友提示并且此消息不保存
        if (FriendDAOImpl.INSTANCE.findFriend(session.getUserId(), requestPacket.getToUserId()) == null) {
            MessageResponsePacket responsePacket = new MessageResponsePacket();
            responsePacket.setMessage(StringConst.ERROR_NOT_FRIEND);
            responsePacket.setFromUserId(requestPacket.getToUserId());
            responsePacket.setFromUsername("系统");
            responsePacket.setSuccess(false);
            ctx.writeAndFlush(responsePacket);
            return;
        }

        // 通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUsername(session.getUsername());
        messageResponsePacket.setMessage(requestPacket.getMessage());

        User user = UserDAOImpl.INSTANCE.findById(session.getUserId());
        // 创建消息实体并添加到数据库中
        Message message = new Message();
        message.setContent(requestPacket.getMessage());
        message.setMessageType(Message.TYPE_FRIEND);
        message.setReceiverId(requestPacket.getToUserId());
        message.setSenderId(user.getUserId());
        message.setSenderName(user.getUsername());
        message.setSenderAvator(user.getAvator());
        MessageDAOImpl.INSTANCE.add(message);

        // 根据用户 id 获取消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(requestPacket.getToUserId());

        // 发送消息
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            messageResponsePacket.setSuccess(true);
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            MessageResponsePacket responsePacket = new MessageResponsePacket();
            responsePacket.setMessage(StringConst.ERROR_NOT_LOGIN);
            responsePacket.setFromUserId(requestPacket.getToUserId());
            responsePacket.setFromUsername("系统");
            responsePacket.setSuccess(false);
            ctx.writeAndFlush(responsePacket);
            System.err.println("[" + requestPacket.getToUserId() + "] 不在线，发送失败!");
        }
    }
}
