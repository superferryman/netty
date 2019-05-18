package com.superferryman.client.handler.command.impl;

import com.superferryman.client.handler.command.ConsoleCommand;
import com.superferryman.protocol.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author superferryman
 * @Date 2019/4/23 18:24
 *
 * 通过群组 id 加入对应群聊
 *
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入 groupId 及 userId，加入群聊：");
        String groupId = scanner.next();
        String userId = scanner.next();
        channel.writeAndFlush(new JoinGroupRequestPacket(groupId, userId));
    }
}
