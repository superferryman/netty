package com.superferryman.client.handler;

import com.superferryman.protocol.Packet;
import com.superferryman.protocol.response.HeartBeatResponsePacket;
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

        // 查询好友列表响应处理器
        handlerMap.put(QUERY_FRIENDS_RESPONSE, QueryFriendsResponseHandler.INSTANCE);
        // 添加好友响应处理器
        handlerMap.put(ADD_FRIEND_RESPONSE, AddFriendResponseHandler.INSTANCE);
        // 单聊响应处理器
        handlerMap.put(MESSAGE_RESPONSE, MessageResponseHandler.INSTANCE);
        // 群聊响应处理器
        handlerMap.put(GROUP_MESSAGE_RESPONSE, GroupMessageResponseHandler.INSTANCE);
        // 创建群组响应处理器
        handlerMap.put(CREATE_GROUP_RESPONSE, CreateGroupResponseHandler.INSTANCE);
        // 加入群组响应处理器
        handlerMap.put(JOIN_GROUP_RESPONSE, JoinGroupResponseHandler.INSTANCE);
        // 退群响应处理器
        handlerMap.put(QUIT_GROUP_RESPONSE, QuitGroupResponseHandler.INSTANCE);
        // 获取群成员响应处理器
        handlerMap.put(LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponseHandler.INSTANCE);
        // 文件传输响应处理器
        handlerMap.put(FILE_UPLOAD_RESPONSE, new FileUploadResponseHandler());
        // 查找用户响应处理器
        handlerMap.put(QUERY_USER_RESPONSE, QueryUserResponseHandler.INSTANCE);
        // 查找消息记录响应处理器
        handlerMap.put(LIST_MESSAGES_RESPONSE, ListAllMessagesResponseHandler.INSTANCE);
        // 查找群组响应处理器
        handlerMap.put(QUERY_GROUP_RESPONSE, QueryGroupResponseHandler.INSTANCE);
        // 获取群组列表响应处理器
        handlerMap.put(QUERY_GROUP_LIST_RESPONSE, QueryGroupListResponseHandler.INSTANCE);
        // 删除好友响应处理器
        handlerMap.put(DELETE_FRIEND_RESPONSE, DeleteFriendResponseHandler.INSTANCE);
        // 登出响应处理器
        handlerMap.put(LOGOUT_RESPONSE, LogoutResponseHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        if (packet instanceof HeartBeatResponsePacket) {
            return ;
        }
        handlerMap.get(packet.getCommand()).channelRead(channelHandlerContext, packet);
    }
}
