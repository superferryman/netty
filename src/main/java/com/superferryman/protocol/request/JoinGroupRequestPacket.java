package com.superferryman.protocol.request;

import com.superferryman.protocol.Packet;

import static com.superferryman.protocol.command.Command.JOIN_GROUP_REQUEST;

/**
 * @Author superferryman
 * @Date 2019/4/23 18:25
 */
public class JoinGroupRequestPacket extends Packet {

    /**
     * 群组唯一标识
     */
    private String groupId;

    /**
     * 用户 id
     */
    private String userId;

    public JoinGroupRequestPacket() {
    }

    public JoinGroupRequestPacket(String groupId, String userId) {
        this.groupId = groupId;
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_REQUEST;
    }
}
