package com.superferryman.client.handler.command.impl;

import com.superferryman.client.handler.command.ConsoleCommand;
import com.superferryman.protocol.request.QueryFriendsRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author superferryman
 * @Date 2019/5/10 9:00
 */
public class QueryFriendsConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入用户 Id 查询好友：");
        String userId = scanner.next();
        channel.writeAndFlush(new QueryFriendsRequestPacket(userId));
    }
}
