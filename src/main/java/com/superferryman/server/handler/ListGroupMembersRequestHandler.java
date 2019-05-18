package com.superferryman.server.handler;

import com.superferryman.dao.impl.GroupMemberDAOImpl;
import com.superferryman.pojo.GroupMember;
import com.superferryman.protocol.request.ListGroupMembersRequestPacket;
import com.superferryman.protocol.response.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/4/23 19:00
 */
@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

    private ListGroupMembersRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket requestPacket) throws Exception {
        // 构造获取成员列表响应数据包
        ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();
        // 获取当前群组的 channelGroup
        Integer groupId = Integer.valueOf(requestPacket.getGroupId());
        List<GroupMember> userList = GroupMemberDAOImpl.INSTANCE.listMembers(groupId);
        if (userList.size() <= 0) {
            responsePacket.setSuccess(false);
            responsePacket.setMessage("操作异常");
            ctx.writeAndFlush(responsePacket);
            return;
        }
        responsePacket.setGroupId(requestPacket.getGroupId());
        responsePacket.setUserList(userList);
        responsePacket.setSuccess(true);
        ctx.writeAndFlush(responsePacket);
    }
}
