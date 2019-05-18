package com.superferryman.client.handler.command.impl;

import com.superferryman.client.handler.command.ConsoleCommand;
import com.superferryman.protocol.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author superferryman
 * @Date 2019/4/23 18:44
 *
 * 通过输入群组 id 退出群聊
 *
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();

        System.out.print("输入 groupId 及 userId，退出群聊：");
        String groupId = scanner.next();
        String userId = scanner.next();

        quitGroupRequestPacket.setGroupId(groupId);
        quitGroupRequestPacket.setUserId(userId);
        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
