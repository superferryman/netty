package com.superferryman.pojo;

/**
 * @Author superferryman
 * @Date 2019/5/11 22:42
 */
public class GroupMember {

    /**
     * 群组 id
     */
    private Integer groupId;

    /**
     * 群组内用户 id
     */
    private String userId;

    /**
     * 群用户昵称
     */
    private String memberNickname;

    /**
     * 群用户头像编号
     */
    private int avator;

    public GroupMember() {
    }

    public GroupMember(Integer groupId, String userId, String memberNickname, int avator) {
        this.groupId = groupId;
        this.userId = userId;
        this.memberNickname = memberNickname;
        this.avator = avator;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public int getAvator() {
        return avator;
    }

    public void setAvator(int avator) {
        this.avator = avator;
    }

    @Override
    public String toString() {
        return "GroupMember{" +
                "groupId=" + groupId +
                ", userId='" + userId + '\'' +
                ", memberNickname='" + memberNickname + '\'' +
                ", avator=" + avator +
                '}';
    }
}
