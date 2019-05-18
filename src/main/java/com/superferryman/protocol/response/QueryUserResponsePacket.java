package com.superferryman.protocol.response;

import com.superferryman.pojo.User;
import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/5/11 10:07
 */
public class QueryUserResponsePacket extends Packet {

    /**
     * 查询获取的 User 对象
     */
    private List<User> users;

    public QueryUserResponsePacket() {
    }

    public QueryUserResponsePacket(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public Byte getCommand() {
        return Command.QUERY_USER_RESPONSE;
    }
}
