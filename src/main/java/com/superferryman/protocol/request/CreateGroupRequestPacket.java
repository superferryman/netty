package com.superferryman.protocol.request;

import com.superferryman.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.superferryman.protocol.command.Command.CREATE_GROUP_REQUEST;

/**
 * @Author superferryman
 * @Date 2019/4/23 16:27
 */
public class CreateGroupRequestPacket extends Packet {

    /**
     * 群名
     */
    private String groupName;

    /**
     * 群描述
     */
    private String groupDesc;

    /**
     * 群主 id
     */
    private String creatorId;

    /**
     * 用户 id 列表，用于存储当前群聊的用户
     */
    private List<String> userIdList;

    public CreateGroupRequestPacket() {
    }

    public CreateGroupRequestPacket(String groupName, String groupDesc, String creatorId, List<String> userIdList) {
        this.groupName = groupName;
        this.groupDesc = groupDesc;
        this.creatorId = creatorId;
        this.userIdList = userIdList;
    }

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
