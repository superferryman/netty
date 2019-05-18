package com.superferryman.client.handler;

import com.superferryman.client.myChatClient.api.AcceptAPI;
import com.superferryman.client.myChatClient.bean.User;
import com.superferryman.pojo.GroupMember;
import com.superferryman.protocol.response.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/4/23 18:50
 */
@ChannelHandler.Sharable
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {

    public static final ListGroupMembersResponseHandler INSTANCE = new ListGroupMembersResponseHandler();

    private ListGroupMembersResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            System.out.println("群[" + responsePacket.getGroupId() + "]中的人包括：" + responsePacket.getUserList());
            List<User> users = new ArrayList<>();
            for (GroupMember member : responsePacket.getUserList()) {
                users.add(new User(
                        member.getUserId(), member.getMemberNickname(), member.getAvator()
                ));
            }
            new AcceptAPI().groupUserHandle(responsePacket.getGroupId(), users);
        } else {
            System.out.println("操作失败，原因为:" + responsePacket.getMessage());
        }
    }
}
