package com.superferryman.client.handler.command.impl;

import com.superferryman.client.handler.command.ConsoleCommand;
import com.superferryman.protocol.request.ListAllMessagesRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author superferryman
 * @Date 2019/5/12 18:19
 */
public class ListAllMessagesConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入用户 id 查找消息记录:");
        String userId = scanner.next();
        channel.writeAndFlush(new ListAllMessagesRequestPacket(userId));
    }
}
