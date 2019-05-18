package com.superferryman.client.handler.command.impl;

import com.superferryman.client.handler.command.ConsoleCommand;
import com.superferryman.protocol.request.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author superferryman
 * @Date 2019/4/23 18:44
 *
 * 根据输入的群组 id 获取群成员列表
 *
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();

        System.out.print("输入 groupId，获取群成员列表：");
        String groupId = scanner.next();

        listGroupMembersRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(listGroupMembersRequestPacket);
    }
}
