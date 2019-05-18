package com.superferryman.pojo;

/**
 * @Author superferryman
 * @Date 2019/5/11 22:40
 */
public class Friend {

    /**
     * 用户 id
     */
    private String userId;

    /**
     * 好友对象
     */
    private String friendId;

    public Friend() {
    }

    public Friend(String userId, String friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "userId='" + userId + '\'' +
                ", friendId='" + friendId + '\'' +
                '}';
    }
}
