package com.superferryman.protocol.response;

import com.superferryman.pojo.Message;
import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/12 18:08
 */
public class ListAllMessagesResponsePacket extends Packet {

    /**
     * 获取的消息列表
     */
    private List<Message> messages;

    public ListAllMessagesResponsePacket() {
    }

    public ListAllMessagesResponsePacket(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public Byte getCommand() {
        return Command.LIST_MESSAGES_RESPONSE;
    }
}
