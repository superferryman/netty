package com.superferryman.util;

import com.superferryman.attribute.Attributes;
import com.superferryman.client.NettyClient;
import com.superferryman.pojo.User;
import com.superferryman.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author superferryman
 * @Date 2019/4/23 10:15
 */
public class SessionUtil {
    /**
     * 用户 TCP 标识
     * key : userId
     * value : 用户的 channel
     */
    private static final Map<String, Channel> USER_ID_CHANNEL_MAP = new ConcurrentHashMap<>();

    /**
     * 群组 TCP 标识
     */
    private static final Map<Integer, ChannelGroup> GROUP_ID_CHANNEL_GROUP_MAP = new ConcurrentHashMap<>();

    /**
     * 当前连接 channel
     */
    private static Channel currentChannel;

    /**
     * 当前所有登录用户
     */
    private static final List<User> LOGIN_USER = new CopyOnWriteArrayList<>();

    /**
     * 为传入的 session 绑定其对应的 channel 并存起当前 session 方便获取会话信息
     * @param session 当前会话的 session
     * @param channel 当前用户的 channel
     */
    public static void bindSession(Session session, Channel channel) {
        USER_ID_CHANNEL_MAP.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    /**
     * 当某个用户断开连接时调用，移出该用户的 channel
     * @param channel 当前用户的 channel
     */
    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            Session session = getSession(channel);
            USER_ID_CHANNEL_MAP.remove(session.getUserId());
            channel.attr(Attributes.SESSION).set(null);
            System.out.println(session + " 退出登录!");
        }
    }

    /**
     * 检查当前 channel 中是否拥有 SESSION
     * @param channel 当前连接的 channel
     * @return 是否已登录
     */
    public static boolean hasLogin(Channel channel) {
        return getSession(channel) != null;
    }

    /**
     * 根据当前 channel 获取会话信息
     * @param channel 当前 channel
     * @return 对应的会话信息
     */
    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    /**
     * 根据 id 获取对应的用户 channel
     * @param userId 用户 id
     * @return 对应的 channel
     */
    public static Channel getChannel(String userId) {
        return USER_ID_CHANNEL_MAP.get(userId);
    }

    /**
     * 将当前的群组 id 与群组中所有客户端对应的 channel 绑定
     * @param groupId 群组 id
     * @param channelGroup 群组中所有客户端的 channel
     */
    public static void bindChannelGroup(Integer groupId, ChannelGroup channelGroup) {
        GROUP_ID_CHANNEL_GROUP_MAP.put(groupId, channelGroup);
    }

    /**
     * 根据群组 id 获取该群组所有的客户端 channel
     * @param groupId 群组 id
     * @return 返回该群组所有客户端的 channel
     */
    public static ChannelGroup getChannelGroup(Integer groupId) {
        return GROUP_ID_CHANNEL_GROUP_MAP.get(groupId);
    }

    /**
     * 记录当前连接的Channel
     * @param channel 当前的 channel
     */
    public static void setCurrentChannel(Channel channel) {
        currentChannel = channel;
    }

    /**
     * 获取当前绑定的 channel
     * @return 当前绑定的 channel
     */
    public static Channel getCurrentChannel() {
        return currentChannel;
    }

    /**
     * 绑定登录用户
     * @param user 登录用户
     */
    public static void bindLoginUser(User user) {
        LOGIN_USER.add(user);
    }

    /**
     * 接触绑定
     * @param user 解绑的用户
     */
    public static void unBindLoginUser(User user) {
        for (User u : LOGIN_USER) {
            if (u.getUserId().equals(user.getUserId())) {
                LOGIN_USER.remove(u);
            }
        }
    }

    /**
     * 获取绑定的登录用户
     * @return 登录用户
     */
    public static List<User> getLoginUser() {
        return LOGIN_USER;
    }
}
