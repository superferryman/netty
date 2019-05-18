package com.superferryman.session;

/**
 * @Author superferryman
 * @Date 2019/4/23 10:13
 */
public class Session {
    /**
     * 用户唯一标识
      */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    public Session() {}

    public Session(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return userId + ":" + username;
    }
}
