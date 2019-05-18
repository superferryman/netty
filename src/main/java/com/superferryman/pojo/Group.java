package com.superferryman.pojo;

/**
 * @Author superferryman
 * @Date 2019/5/11 17:04
 */
public class Group {
    /**
     * 群组 id
     */
    private Integer groupId;

    /**
     * 群组名称
     */
    private String groupName;

    /**
     * 群组描述
     */
    private String groupDesc;

    /**
     * 群组头像编号
     */
    private Integer avator;

    /**
     * 群组内所有用户 id
     */
    private String creatorId;

    public Group() {
    }

    public Group(Integer groupId, String groupName, Integer avator, String creatorId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.avator = avator;
        this.creatorId = creatorId;
    }

    public Group(Integer groupId, String groupName, String groupDesc, Integer avator, String creatorId) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupDesc = groupDesc;
        this.avator = avator;
        this.creatorId = creatorId;
    }

    public Group(String groupName, String groupDesc, Integer avator, String creatorId) {
        this.groupName = groupName;
        this.groupDesc = groupDesc;
        this.avator = avator;
        this.creatorId = creatorId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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

    public Integer getAvator() {
        return avator;
    }

    public void setAvator(Integer avator) {
        this.avator = avator;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", groupDesc='" + groupDesc + '\'' +
                ", avator=" + avator +
                ", userId='" + creatorId + '\'' +
                '}';
    }
}
