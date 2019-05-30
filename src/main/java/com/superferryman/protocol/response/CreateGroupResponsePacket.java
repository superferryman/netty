package com.superferryman.protocol.response;

import com.superferryman.protocol.Packet;

import java.util.List;

import static com.superferryman.protocol.command.Command.CREATE_GROUP_RESPONSE;

/**
 * @Author superferryman
 * @Date 2019/4/23 16:30
 */
public class CreateGroupResponsePacket extends Packet {

    /**
     * 标识群组是否创建成功
     */
    private boolean success;

    /**
     * 群组唯一标识
     */
    private Integer groupId;

    /**
     * 群名称
     */
    private String groupName;

    /**
     * 创建失败时的错误信息
     */
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }



    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}
