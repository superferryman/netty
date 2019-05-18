package com.superferryman.protocol.response;

import com.superferryman.pojo.GroupMember;
import com.superferryman.pojo.User;
import com.superferryman.protocol.Packet;
import com.superferryman.session.Session;

import java.util.List;

import static com.superferryman.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

/**
 * @Author superferryman
 * @Date 2019/4/23 18:33
 */
public class ListGroupMembersResponsePacket extends Packet {

    /**
     * 群组唯一标识
     */
    private String groupId;

    /**
     * 会话信息列表
     */
    private List<GroupMember> userList;

    /**
     * 标识查找是否成功
     */
    private boolean success;

    /**
     * 失败时的原因
     */
    private String message;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<GroupMember> getUserList() {
        return userList;
    }

    public void setUserList(List<GroupMember> userList) {
        this.userList = userList;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
