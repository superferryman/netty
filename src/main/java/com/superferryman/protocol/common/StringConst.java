package com.superferryman.protocol.common;

/**
 * @Author superferryman
 * @Date 2019/4/21 22:52
 */
public class StringConst {
    public static final int MAX_RETRY = 5;

    public static final int PORT = 60000;

    public static final String HOST = "127.0.0.1";

    public static final String FILE_UPLOAD_DIR = ".\\upload";

    public static final String FILE_DOWNLOAD_DIR = ".\\download";

    public static final String ERROR_NOT_FRIEND = "你和对方还不是好友";

    public static final String ERROR_NOT_LOGIN = "对方未登录";

    public static final String CRAZYCHAT_SERVER = "Chatting Server";
    public static final String SERVER_STATUS = "服务器状态";
    public static final String ONLINE_USERS = "在线用户";
    public static final String ALL_USERS = "所有用户";
    public static final String SERVER_LOGS = "服务器日志";

    /*
     * 服务状态面板用到的
     */
    public static final String STARTUP_SERVER = "启动服务";
    public static final String SHUTDOWN_SERVER = "停止服务";

    public static final String SERVER_STOP = "服务未运行";
    public static final String SERVER_STARTING = "服务启动中";
    public static final String SERVER_RUNNING = "服务正在运行";

    /*
     * 在线用户面板用到的
     */
    public static final String CURRENT_ONLINE_USERS = "当前在线用户";

    /*
     * 日志面板用到的
     */
    public static final String EXPORT_LOGS = "导出日志";

    /*
     * 登陆业务相关
     */
    public static final String ERROR_USER_NOT_FOUND = "该用户不存在";
    public static final String ERROR_WRONG_PASSWORD = "用户密码错误";
    public static final String ERROR_USER_IS_ALREADY_LOGINED = "该用户已经登录，不能重复登录";

    /*
     * 用户业务
     */
    public static final String NO_USER_HAS_BEEN_FOUND = "没有找到用户";
    public static final String GET_USER_PROFILE_SUCCESS = "获取用户资料成功";
    public static final String GET_USER_PROFILE_FAILED = "获取用户资料失败";
    public static final String UPDATE_USER_PROFILE_SUCCESS = "修改个人资料成功";
    public static final String UPDATE_USER_PROFILE_FAILED = "修改个人资料失败";
}
