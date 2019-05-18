package com.superferryman.client.handler.command.impl;

import com.superferryman.client.handler.command.ConsoleCommand;
import com.superferryman.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author superferryman
 * @Date 2019/4/23 13:00
 */
public class ConsoleCommandManager implements ConsoleCommand {
    /**
     * 指令 map，用于存储指令
     */
    private Map<String, ConsoleCommand> consoleCommandMap;

    /**
     * 初始化指令 map
     */
    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
        consoleCommandMap.put("quitGroup", new QuitGroupConsoleCommand());
        consoleCommandMap.put("listGroupMembers", new ListGroupMembersConsoleCommand());
        consoleCommandMap.put("sendToGroup", new SendToGroupConsoleCommand());
        consoleCommandMap.put("register", new RegisterConsoleCommand());
        consoleCommandMap.put("queryFriends", new QueryFriendsConsoleCommand());
        consoleCommandMap.put("addFriend", new AddFriendConsoleCommand());
        consoleCommandMap.put("deleteFriend", new DeleteFriendConsoleCommand());
        consoleCommandMap.put("sendFile", new FileUploadConsoleCommand());
        consoleCommandMap.put("listMessages", new ListAllMessagesConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        // 获取指令
        String command = scanner.next();

        // 先检查当前用户连接是否经过登录
        if (!SessionUtil.hasLogin(channel)) {
            return ;
        }

        // 通过指令获取指令对象
        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            System.err.println("无法识别[" + command + "]指令，请重新输入!");
        }
    }
}
