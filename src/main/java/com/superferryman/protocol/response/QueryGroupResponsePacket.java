package com.superferryman.protocol.response;

import com.superferryman.pojo.Group;
import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/11 10:51
 */
public class QueryGroupResponsePacket extends Packet {

    /**
     * 查询获得的群列表
     */
    private List<Group> groupList;

    public QueryGroupResponsePacket() {
    }

    public QueryGroupResponsePacket(List<Group> groupList) {
        this.groupList = groupList;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    @Override
    public Byte getCommand() {
        return Command.QUERY_GROUP_RESPONSE;
    }
}
