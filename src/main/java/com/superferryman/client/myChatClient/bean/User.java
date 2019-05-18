package com.superferryman.client.myChatClient.bean;

public class User {
    private String id;
    private String username;
    private Integer avator;
    public String getId() {
        return id;
    }

    public User(String id, String username, Integer avator) {
        this.id = id;
        this.username = username;
        this.avator = avator;
    }

    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", avator=" + avator +
                '}';
    }

    public Integer getAvator() {
        return avator;
    }

    public void setAvator(Integer avator) {
        this.avator = avator;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
