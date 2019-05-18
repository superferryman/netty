package com.superferryman.protocol.response;

import com.superferryman.protocol.Packet;

import static com.superferryman.protocol.command.Command.QUIT_GROUP_RESPONSE;

/**
 * @Author superferryman
 * @Date 2019/4/23 18:33
 */
public class QuitGroupResponsePacket extends Packet {

    /**
     * 群组唯一标识
     */
    private String groupId;

    /**
     * 用于标识退出群组是否成功
     */
    private boolean success;

    /**
     * 当退出群组错误时回送的信息
     */
    private String msg;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_RESPONSE;
    }
}
