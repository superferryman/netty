package com.superferryman.pojo;

/**
 * @Author superferryman
 * @Date 2019/4/27 10:14
 */
public class User {

    /**
     * 用户 id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 头像编号
     */
    private Integer avator;

    public User() {
    }

    public User(String username, String password, Integer avator) {
        this.username = username;
        this.password = password;
        this.avator = avator;
    }

    public User(String userId, String username, String password, Integer avator) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.avator = avator;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getAvator() {
        return avator;
    }

    public void setAvator(Integer avator) {
        this.avator = avator;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avator=" + avator +
                '}';
    }
}
