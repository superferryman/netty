package com.superferryman.client.handler.command.impl;

import com.superferryman.client.handler.command.ConsoleCommand;
import com.superferryman.pojo.User;
import com.superferryman.protocol.request.RegisterRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author superferryman
 * @Date 2019/5/9 23:45
 */
public class RegisterConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        RegisterRequestPacket requestPacket = new RegisterRequestPacket();
        System.out.print("请输入用户id、用户名、密码、头像编号进行注册：");
        String userId = scanner.next();
        String username = scanner.next();
        String password = scanner.next();
        String avator = scanner.next();
        User user = new User(userId, username, password, Integer.valueOf(avator));
        requestPacket.setUser(user);
        channel.writeAndFlush(requestPacket);
    }
}
