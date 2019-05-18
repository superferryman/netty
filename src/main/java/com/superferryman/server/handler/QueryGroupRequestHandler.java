package com.superferryman.server.handler;

import com.superferryman.dao.impl.GroupDAOImpl;
import com.superferryman.pojo.Group;
import com.superferryman.protocol.request.QueryGroupRequestPacket;
import com.superferryman.protocol.response.QueryGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/11 10:53
 */
@ChannelHandler.Sharable
public class QueryGroupRequestHandler extends SimpleChannelInboundHandler<QueryGroupRequestPacket> {

    public static final QueryGroupRequestHandler INSTANCE = new QueryGroupRequestHandler();

    private QueryGroupRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QueryGroupRequestPacket requestPacket) throws Exception {
        String keyword = requestPacket.getKeyword();
        List<Group> groups = GroupDAOImpl.INSTANCE.findByKeyword(keyword);
        if (groups == null || groups.size() <= 0) {
            ctx.writeAndFlush(new QueryGroupResponsePacket(null));
        } else {
            ctx.writeAndFlush(new QueryGroupResponsePacket(groups));
        }
    }
}
