package com.superferryman.protocol.command;

/**
 * @Author superferryman
 * @Date 2019/4/22 10:45
 */
public interface Command {
    /**
     * 登录相关
     */
    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;

    /**
     * 消息相关
     */
    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;

    Byte LIST_MESSAGES_REQUEST = 37;
    Byte LIST_MESSAGES_RESPONSE = 38;

    /**
     * 群聊相关
     */
    Byte CREATE_GROUP_REQUEST = 5;
    Byte CREATE_GROUP_RESPONSE = 6;

    Byte JOIN_GROUP_REQUEST = 9;
    Byte JOIN_GROUP_RESPONSE = 10;

    Byte LIST_GROUP_MEMBERS_REQUEST = 11;
    Byte LIST_GROUP_MEMBERS_RESPONSE = 12;

    Byte QUIT_GROUP_REQUEST = 13;
    Byte QUIT_GROUP_RESPONSE = 14;

    Byte GROUP_MESSAGE_REQUEST = 15;
    Byte GROUP_MESSAGE_RESPONSE = 16;

    /**
     * 登出相关
     */
    Byte LOGOUT_REQUEST = 7;
    Byte LOGOUT_RESPONSE = 8;

    /**
     * 心跳包相关
     */
    Byte HEARTBEAT_REQUEST = 17;
    Byte HEARTBEAT_RESPONSE = 18;

    /**
     * 好友相关
     */
    Byte ADD_FRIEND_REQUEST = 19;
    Byte ADD_FRIEND_RESPONSE = 20;

    Byte DELETE_FRIEND_REQUEST = 21;
    Byte DELETE_FRIEND_RESPONSE = 22;

    Byte QUERY_FRIENDS_REQUEST = 23;
    Byte QUERY_FRIENDS_RESPONSE = 24;

    /**
     * 注册相关
     */
    Byte REGISTER_REQUEST = 25;
    Byte REGISTER_RESPONSE = 26;

    /**
     * 文件相关
     */
    Byte FILE_UPLOAD_REQUEST = 27;
    Byte FILE_UPLOAD_RESPONSE = 28;

    Byte FILE_DOWNLOAD_REQUEST = 29;
    Byte FILE_DOWNLOAD_RESPONSE = 30;

    /**
     * 查询相关
     */
    Byte QUERY_USER_REQUEST = 31;
    Byte QUERY_USER_RESPONSE = 32;

    Byte QUERY_GROUP_REQUEST = 35;
    Byte QUERY_GROUP_RESPONSE = 36;

    Byte QUERY_GROUP_LIST_REQUEST = 33;
    Byte QUERY_GROUP_LIST_RESPONSE = 34;
}
