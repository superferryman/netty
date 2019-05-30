package com.superferryman.server.handler;

import com.superferryman.dao.impl.GroupMemberDAOImpl;
import com.superferryman.dao.impl.MessageDAOImpl;
import com.superferryman.pojo.Message;
import com.superferryman.pojo.User;
import com.superferryman.protocol.request.QuitGroupRequestPacket;
import com.superferryman.protocol.response.QuitGroupResponsePacket;
import com.superferryman.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Author superferryman
 * @Date 2019/4/23 19:00
 */
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    private QuitGroupRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket requestPacket) throws Exception {
        // 构造退群响应数据包
        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();
        Integer groupId = Integer.valueOf(requestPacket.getGroupId());
        String userId = requestPacket.getUserId();
        boolean flag = GroupMemberDAOImpl.INSTANCE.delete(groupId, userId);
        if (!flag) {
            responsePacket.setSuccess(false);
            responsePacket.setMsg("没有加入该群或操作异常");
            ctx.writeAndFlush(responsePacket);
            return;
        }
        // 获取群组对应的 channelGroup，然后将当前用户的 channel 从 channelGroup 中移除
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());
        responsePacket.setGroupId(String.valueOf(groupId));
        responsePacket.setSuccess(true);
        ctx.writeAndFlush(responsePacket);
    }
}
