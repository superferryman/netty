package com.superferryman.client.handler.command.impl;

import com.superferryman.client.handler.command.ConsoleCommand;
import com.superferryman.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author superferryman
 * @Date 2019/4/23 13:04
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {
    private static final String USER_ID_SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("【拉人群聊】输入群名、群主id及成员id列表,成员id之间英文逗号隔开：");
        String groupName = scanner.next();
        String creatorId = scanner.next();
        String userIds = scanner.next();
        CreateGroupRequestPacket requestPacket = new CreateGroupRequestPacket(groupName, "",
                creatorId, Arrays.asList(userIds.split(USER_ID_SPLITER)));
        // 利用 Arrays.asList() 将字符串转为数组
        channel.writeAndFlush(requestPacket);
    }
}
