package com.superferryman.server.handler;

import com.superferryman.dao.impl.GroupMemberDAOImpl;
import com.superferryman.pojo.Group;
import com.superferryman.protocol.request.QueryGroupListRequestPacket;
import com.superferryman.protocol.response.QueryGroupListResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/11 11:17
 */
@ChannelHandler.Sharable
public class QueryGroupListRequestHandler extends SimpleChannelInboundHandler<QueryGroupListRequestPacket> {

    public static final QueryGroupListRequestHandler INSTANCE = new QueryGroupListRequestHandler();

    private QueryGroupListRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QueryGroupListRequestPacket requestPacket) throws Exception {
        QueryGroupListResponsePacket responsePacket = new QueryGroupListResponsePacket();
        String userId = requestPacket.getUserId();
        List<Group> groups = GroupMemberDAOImpl.INSTANCE.listGroups(userId);
        if (groups == null || groups.size() <= 0) {
            responsePacket.setGroupList(null);
        } else {
            responsePacket.setGroupList(groups);
        }
        ctx.writeAndFlush(responsePacket);
    }
}
