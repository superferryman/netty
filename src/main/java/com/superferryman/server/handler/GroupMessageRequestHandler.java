package com.superferryman.server.handler;

import com.superferryman.dao.impl.MessageDAOImpl;
import com.superferryman.pojo.Message;
import com.superferryman.protocol.request.GroupMessageRequestPacket;
import com.superferryman.protocol.response.GroupMessageResponsePacket;
import com.superferryman.session.Session;
import com.superferryman.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Author superferryman
 * @Date 2019/4/23 20:03
 */
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    protected GroupMessageRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) throws Exception {
        // 获取 groupId 并构造群聊消息响应
        String groupId = requestPacket.getToGroupId();
        Session session = SessionUtil.getSession(ctx.channel());
        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        responsePacket.setFromGroupId(groupId);
        responsePacket.setFromUser(session);
        responsePacket.setMessage(requestPacket.getMessage());

        // 根据群聊的 channelGroup 发送给每个客户端
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(Integer.valueOf(groupId));

        if (channelGroup != null) {
            // 创建消息实体并添加到数据库中
            Message message = new Message();
            message.setContent(requestPacket.getMessage());
            message.setMessageType(Message.TYPE_GROUP);
            message.setReceiverId(requestPacket.getToGroupId());
            message.setSenderId(session.getUserId());
            MessageDAOImpl.INSTANCE.add(message);

            for (Channel channel : channelGroup) {
                if (channel != ctx.channel()) {
                    channel.writeAndFlush(responsePacket);
                }
            }
        }
    }
}
