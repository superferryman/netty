package com.superferryman.client.handler.command.impl;

import com.superferryman.client.handler.command.ConsoleCommand;
import com.superferryman.protocol.request.DeleteFriendRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author superferryman
 * @Date 2019/5/10 10:55
 */
public class DeleteFriendConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入 userId 和 friendId 删除好友:");
        String userId = scanner.next();
        String friendId = scanner.next();
        channel.writeAndFlush(new DeleteFriendRequestPacket(userId, friendId));
    }
}
