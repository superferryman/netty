package com.superferryman.server.handler;

import com.superferryman.protocol.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

import static com.superferryman.protocol.command.Command.*;

/**
 * @Author superferryman
 * @Date 2019/4/23 21:26
 */
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {

    public static final IMHandler INSTANCE = new IMHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private IMHandler() {
        handlerMap = new HashMap<>();

        // 查询好友列表请求处理器
        handlerMap.put(QUERY_FRIENDS_REQUEST, QueryFriendsRequestHandler.INSTANCE);
        // 单聊信息请求处理器
        handlerMap.put(MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        // 加好友请求处理器
        handlerMap.put(ADD_FRIEND_REQUEST, AddFriendRequestHandler.INSTANCE);
        // 创建群组请求处理器
        handlerMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        // 加入群组请求处理器
        handlerMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        // 退出群组请求处理器
        handlerMap.put(QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        // 获取群组成员请求处理器
        handlerMap.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestHandler.INSTANCE);
        // 群聊信息请求处理器
        handlerMap.put(GROUP_MESSAGE_REQUEST, GroupMessageRequestHandler.INSTANCE);
        // 查找用户请求处理器
        handlerMap.put(QUERY_USER_REQUEST, QueryUserRequestHandler.INSTANCE);
        // 查找消息记录请求处理器
        handlerMap.put(LIST_MESSAGES_REQUEST, ListAllMessagesRequestHandler.INSTANCE);
        // 查找群组请求处理器
        handlerMap.put(QUERY_GROUP_REQUEST, QueryGroupRequestHandler.INSTANCE);
        // 获取群组列表请求处理器
        handlerMap.put(QUERY_GROUP_LIST_REQUEST, QueryGroupListRequestHandler.INSTANCE);
        // 文件传输请求处理器
        handlerMap.put(FILE_UPLOAD_REQUEST, FileUploadRequestHandler.INSTANCE);
        // 删除好友请求处理器
        handlerMap.put(DELETE_FRIEND_REQUEST, DeleteFriendRequestHandler.INSTANCE);
        // 登出请求处理器
        handlerMap.put(LOGOUT_REQUEST, LogoutRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        handlerMap.get(packet.getCommand()).channelRead(channelHandlerContext, packet);
    }
}
