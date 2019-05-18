package com.superferryman.client.handler.operation;

import com.superferryman.protocol.Packet;
import io.netty.channel.Channel;

/**
 * @Author superferryman
 * @Date 2019/5/6 12:43
 */
public interface Command {
    /**
     * 对于用户的不同输入执行不同的操作
     * @param channel 对应的 channel
     */
    void exec(Packet packet, Channel channel);
}
