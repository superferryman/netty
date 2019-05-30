package com.superferryman.server.handler;

import com.superferryman.dao.impl.GroupDAOImpl;
import com.superferryman.dao.impl.GroupMemberDAOImpl;
import com.superferryman.dao.impl.UserDAOImpl;
import com.superferryman.pojo.Group;
import com.superferryman.pojo.User;
import com.superferryman.protocol.request.CreateGroupRequestPacket;
import com.superferryman.protocol.response.CreateGroupResponsePacket;
import com.superferryman.util.IDUtil;
import com.superferryman.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author superferryman
 * @Date 2019/4/23 16:26
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    private CreateGroupRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket requestPacket) throws Exception {
        // 创建群聊创建的响应包
        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();

        // 获取当前群组的所有用户 id
        List<String> userIdList = requestPacket.getUserIdList();
        // 创建群组对象
        Group group = new Group(requestPacket.getGroupName(), requestPacket.getGroupDesc(),
                requestPacket.getAvator(), requestPacket.getCreatorId());
        // 创建群
        boolean row = GroupDAOImpl.INSTANCE.add(group);
        if (!row) {
            // 创建失败或者已有该群
            responsePacket.setSuccess(false);
            responsePacket.setMessage("创建失败或已有该群");
            ctx.writeAndFlush(responsePacket);
        } else {
            // 获取群号
            Integer groupId = GroupDAOImpl.INSTANCE.findByName(requestPacket.getGroupName()).getGroupId();
            // 给所有用户执行加群操作
            for (String userId : userIdList) {
                User user = UserDAOImpl.INSTANCE.findById(userId);
                GroupMemberDAOImpl.INSTANCE.add(groupId, user);
            }

            // 创建一个 channel 分组
            ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
            // 根据 userId 筛选出待加入群聊的用户的 channel
            for (String userId : userIdList) {
                Channel channel = SessionUtil.getChannel(userId);
                if (channel != null) {
                    channelGroup.add(channel);
                }
            }

            responsePacket.setSuccess(true);
            responsePacket.setGroupId(groupId);
            responsePacket.setGroupName(requestPacket.getGroupName());

            // 给每个客户端发送拉群通知
            channelGroup.writeAndFlush(responsePacket);

            System.out.print("群创建成功，id 为[" + responsePacket.getGroupId() + "], ");
            // 保存群组相关信息
            SessionUtil.bindChannelGroup(groupId, channelGroup);
        }

    }
}
