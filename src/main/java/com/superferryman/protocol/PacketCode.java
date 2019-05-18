package com.superferryman.protocol;

import com.superferryman.protocol.command.Command;
import com.superferryman.protocol.request.*;
import com.superferryman.protocol.response.*;
import com.superferryman.seialize.Serializer;
import com.superferryman.seialize.impl.JsonSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author superferryman
 * @Date 2019/4/22 10:59
 */
public class PacketCode {

    public static final PacketCode INSTANCE = new PacketCode();

    public static final int MAGIC_NUMBER = 0x12345678;
    private static final Map<Byte, Class<? extends Packet>> PACKETMAP;
    private static final Map<Byte, Serializer> SERIALIZER_MAP;

    static {
        PACKETMAP = new HashMap<Byte, Class<? extends Packet>>();
        PACKETMAP.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        PACKETMAP.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        PACKETMAP.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        PACKETMAP.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
        PACKETMAP.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        PACKETMAP.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        PACKETMAP.put(Command.LOGOUT_REQUEST, LogoutRequestPacket.class);
        PACKETMAP.put(Command.LOGOUT_RESPONSE, LogoutResponsePacket.class);
        PACKETMAP.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        PACKETMAP.put(Command.JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        PACKETMAP.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        PACKETMAP.put(Command.QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        PACKETMAP.put(Command.LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
        PACKETMAP.put(Command.LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
        PACKETMAP.put(Command.GROUP_MESSAGE_REQUEST, GroupMessageRequestPacket.class);
        PACKETMAP.put(Command.GROUP_MESSAGE_RESPONSE, GroupMessageResponsePacket.class);
        PACKETMAP.put(Command.HEARTBEAT_REQUEST, HeartBeatRequestPacket.class);
        PACKETMAP.put(Command.HEARTBEAT_RESPONSE, HeartBeatResponsePacket.class);
        PACKETMAP.put(Command.REGISTER_REQUEST, RegisterRequestPacket.class);
        PACKETMAP.put(Command.REGISTER_RESPONSE, RegisterResponsePacket.class);
        PACKETMAP.put(Command.QUERY_FRIENDS_REQUEST, QueryFriendsRequestPacket.class);
        PACKETMAP.put(Command.QUERY_FRIENDS_RESPONSE, QueryFriendsResponsePacket.class);
        PACKETMAP.put(Command.ADD_FRIEND_REQUEST, AddFriendRequestPacket.class);
        PACKETMAP.put(Command.ADD_FRIEND_RESPONSE, AddFriendResponsePacket.class);
        PACKETMAP.put(Command.DELETE_FRIEND_REQUEST, DeleteFriendRequestPacket.class);
        PACKETMAP.put(Command.DELETE_FRIEND_RESPONSE, DeleteFriendResponsePacket.class);
        PACKETMAP.put(Command.FILE_UPLOAD_REQUEST, FileUploadRequestPacket.class);
        PACKETMAP.put(Command.FILE_UPLOAD_RESPONSE, FileUploadResponsePacket.class);
        PACKETMAP.put(Command.FILE_DOWNLOAD_REQUEST, FileDownloadRequestPacket.class);
        PACKETMAP.put(Command.FILE_DOWNLOAD_RESPONSE, FileDownloadResponsePacket.class);
        PACKETMAP.put(Command.QUERY_USER_REQUEST, QueryUserRequestPacket.class);
        PACKETMAP.put(Command.QUERY_USER_RESPONSE, QueryUserResponsePacket.class);
        PACKETMAP.put(Command.QUERY_GROUP_REQUEST, QueryGroupRequestPacket.class);
        PACKETMAP.put(Command.QUERY_GROUP_RESPONSE, QueryGroupResponsePacket.class);
        PACKETMAP.put(Command.QUERY_GROUP_LIST_REQUEST, QueryGroupListRequestPacket.class);
        PACKETMAP.put(Command.QUERY_GROUP_LIST_RESPONSE, QueryGroupListResponsePacket.class);
        PACKETMAP.put(Command.LIST_MESSAGES_REQUEST, ListAllMessagesRequestPacket.class);
        PACKETMAP.put(Command.LIST_MESSAGES_RESPONSE, ListAllMessagesResponsePacket.class);

        SERIALIZER_MAP = new HashMap<Byte, Serializer>();
        Serializer serializer = new JsonSerializer();
        SERIALIZER_MAP.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
        // 创建 ByteBuf 对象
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        // 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 编码
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public ByteBuf encode(ByteBuf byteBuf, Packet packet) {
        // 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 编码
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        // TODO: 实际需要校验，暂时视为合法来源，即数据包是符合协议逻辑的
        byteBuf.skipBytes(4);
        // 跳过 version
        byteBuf.skipBytes(1);
        // 获取算法标识
        byte serializerAlgorithm =  byteBuf.readByte();
        // 获取 command
        byte command = byteBuf.readByte();
        // 获取数据长度
        int length = byteBuf.readInt();
        // 根据长度获取所有数据
        byte[] data = new byte[length];
        byteBuf.readBytes(data);
        // 根据协议内容获取需要解码的对象类和解码所需的算法
        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializerAlgorithm);
        if (requestType != null && serializer != null) {
            // 根据内容解码
            return serializer.deserialize(requestType, data);
        }
        return null;
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return PACKETMAP.get(command);
    }

    private Serializer getSerializer(byte serializerAlgorithm) {
        return SERIALIZER_MAP.get(serializerAlgorithm);
    }
}
