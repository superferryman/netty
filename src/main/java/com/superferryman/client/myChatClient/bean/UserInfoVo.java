package com.superferryman.client.myChatClient.bean;

import javafx.scene.layout.Pane;

import java.awt.*;

public class UserInfoVo {
    //用户信息
    private UserInfo userInfo;
    //好友控件
    private Pane UserInfoPane;
    //群聊左侧好友控件
    private Pane leftUserInfoPane;
    //群聊右侧好友控件
    private Pane rightUserInfoPane;

    @Override
    public String toString() {
        return "UserInfoVo{" +
                "userInfo=" + userInfo +
                ", UserInfoPane=" + UserInfoPane +
                ", leftUserInfoPane=" + leftUserInfoPane +
                ", rightUserInfoPane=" + rightUserInfoPane +
                '}';
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Pane getUserInfoPane() {
        return UserInfoPane;
    }

    public void setUserInfoPane(Pane userInfoPane) {
        UserInfoPane = userInfoPane;
    }

    public Pane getLeftUserInfoPane() {
        return leftUserInfoPane;
    }

    public void setLeftUserInfoPane(Pane leftUserInfoPane) {
        this.leftUserInfoPane = leftUserInfoPane;
    }

    public Pane getRightUserInfoPane() {
        return rightUserInfoPane;
    }

    public void setRightUserInfoPane(Pane rightUserInfoPane) {
        this.rightUserInfoPane = rightUserInfoPane;
    }
}
