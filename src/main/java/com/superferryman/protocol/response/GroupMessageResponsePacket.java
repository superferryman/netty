package com.superferryman.protocol.response;

import com.superferryman.protocol.Packet;
import com.superferryman.session.Session;

import static com.superferryman.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

/**
 * @Author superferryman
 * @Date 2019/4/23 19:44
 */
public class GroupMessageResponsePacket extends Packet {

    /**
     * 来源群组标识
     */
    private String fromGroupId;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 来源用户标识
     */
    private Session fromUser;

    /**
     * 来源用户头像
     */
    private Integer userAvator;

    public String getFromGroupId() {
        return fromGroupId;
    }

    public void setFromGroupId(String fromGroupId) {
        this.fromGroupId = fromGroupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Session getFromUser() {
        return fromUser;
    }

    public void setFromUser(Session fromUser) {
        this.fromUser = fromUser;
    }

    public Integer getUserAvator() {
        return userAvator;
    }

    public void setUserAvator(Integer userAvator) {
        this.userAvator = userAvator;
    }

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}
