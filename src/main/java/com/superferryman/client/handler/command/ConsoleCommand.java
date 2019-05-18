package com.superferryman.client.handler.command;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author superferryman
 * @Date 2019/4/23 12:59
 */
public interface ConsoleCommand {
    /**
     * 对于用户的不同输入执行不同的操作
     * @param scanner 监控控制台的输入
     * @param channel 对应的 channel
     */
    void exec(Scanner scanner, Channel channel);
}
