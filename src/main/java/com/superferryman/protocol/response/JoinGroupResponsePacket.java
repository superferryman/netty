package com.superferryman.protocol.response;

import com.superferryman.pojo.Group;
import com.superferryman.protocol.Packet;

import static com.superferryman.protocol.command.Command.JOIN_GROUP_RESPONSE;

/**
 * @Author superferryman
 * @Date 2019/4/23 18:33
 */
public class JoinGroupResponsePacket extends Packet {

    /**
     * 群组对象
     */
    private Group group;

    /**
     * 标识是否加入群组成功
     */
    private boolean success;

    /**
     * 当加入群组失败时的回送信息
     */
    private String msg;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
        return JOIN_GROUP_RESPONSE;
    }
}
