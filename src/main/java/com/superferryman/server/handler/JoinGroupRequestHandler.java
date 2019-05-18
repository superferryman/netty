package com.superferryman.server.handler;

import com.superferryman.dao.impl.GroupDAOImpl;
import com.superferryman.dao.impl.GroupMemberDAOImpl;
import com.superferryman.dao.impl.UserDAOImpl;
import com.superferryman.pojo.Group;
import com.superferryman.pojo.User;
import com.superferryman.protocol.request.JoinGroupRequestPacket;
import com.superferryman.protocol.response.JoinGroupResponsePacket;
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
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    protected JoinGroupRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket requestPacket) throws Exception {
        // 构造加入群组响应数据包
        JoinGroupResponsePacket resonsePacket = new JoinGroupResponsePacket();
        // 获取包信息并以此获取群组及用户对象
        Integer groupId = Integer.valueOf(requestPacket.getGroupId());
        String userId = requestPacket.getUserId();
        Group group = GroupDAOImpl.INSTANCE.findById(groupId);
        if (group == null) {
            resonsePacket.setSuccess(false);
            resonsePacket.setMsg("群组不存在");
            ctx.writeAndFlush(resonsePacket);
            return;
        }
        User user = UserDAOImpl.INSTANCE.findById(userId);
        if (user == null) {
            resonsePacket.setSuccess(false);
            resonsePacket.setMsg("操作异常");
            ctx.writeAndFlush(resonsePacket);
            return;
        }
        boolean flag = GroupMemberDAOImpl.INSTANCE.add(group.getGroupId(), user);
        if (!flag) {
            resonsePacket.setSuccess(false);
            resonsePacket.setMsg("已加入该群");
            ctx.writeAndFlush(resonsePacket);
            return;
        }
        // 获取群对应的 channelGroup，然后将当前用户的 channel 添加进去
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());
        // 构造成功信息
        resonsePacket.setSuccess(true);
        resonsePacket.setGroup(group);
        ctx.writeAndFlush(resonsePacket);
    }
}
