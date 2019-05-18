package com.superferryman.protocol.request;

import com.superferryman.protocol.Packet;

import static com.superferryman.protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;

/**
 * @Author superferryman
 * @Date 2019/4/23 18:26
 */
public class ListGroupMembersRequestPacket extends Packet {

    /**
     * 群组唯一标识
     */
    private String groupId;

    public ListGroupMembersRequestPacket() {
    }

    public ListGroupMembersRequestPacket(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
