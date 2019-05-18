package com.superferryman.client.myChatClient.controller;

import com.superferryman.client.myChatClient.bean.TalkItem;
import com.superferryman.client.myChatClient.bean.UserInfo;
import com.superferryman.client.myChatClient.bean.UserInfoVo;
import com.superferryman.client.myChatClient.component.MessageItemFactory;
import com.superferryman.client.myChatClient.utils.GlobalState;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserManager {
    //好友列表
    private List<UserInfoVo> userList = new ArrayList<>();
    //当前好友对象
    private UserInfoVo currentUser = new UserInfoVo();
    //聊天面板
    private VBox chatVBox;
    //前端好友列表模块
    private VBox friendsVBox;
    //本人信息
    private UserInfo myInfo;
    //聊天框名
    private Label chatNameLabel;
    //左侧个人图片
    private ImageView myImage;
    /**
     * 根据当前点击好友渲染聊天列表
    * */
    public void renderChatList()
    {
        //清空当前列表
        chatVBox.getChildren().remove(0,chatVBox.getChildren().size());
        chatNameLabel.setText("");
        //如果当前点击好友不为空
        if(currentUser.getUserInfo() != null){
            MessageItemFactory messageItemFactory = new MessageItemFactory();
            //设置聊天对象名
            chatNameLabel.setText(currentUser.getUserInfo().getUsername());
            //获取当前聊天list
            List<TalkItem> chatList = currentUser.getUserInfo().getMessages();
            for(TalkItem s : chatList){
                chatVBox.getChildren().add(messageItemFactory.getItem(s));
            }
            //刷新滚动条
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200);
                        GlobalState.scrollerPane.setVvalue(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /**
     * 渲染当前好友列表
     * **/
    public void renderFriendList(String id,String message)
    {
        //好友按优先级排序
        userList.sort(new Comparator<UserInfoVo>() {
            @Override
            public int compare(UserInfoVo o1, UserInfoVo o2) {
                return  o2.getUserInfo().getPriority() - o1.getUserInfo().getPriority();
            }
        });

        //把最新的list渲染到页面
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //更新JavaFX的主线程的代码放在此处
                //清空当前列表
                friendsVBox.getChildren().remove(0,friendsVBox.getChildren().size());
                //渲染新列表
                for(UserInfoVo u : userList)
                {
                    friendsVBox.getChildren().add(u.getUserInfoPane());
                    if(id != null && message != null && u.getUserInfo().getId().equals(id))
                    {
                        //追加最新消息
                        Label label = (Label)u.getUserInfoPane().getChildren().get(2);
                        label.setText(message);
                        //若当前没有聊天对象或聊天对象不是此id，则追加消息提示
                        if(currentUser.getUserInfo() == null || !currentUser.getUserInfo().getId().equals(id)) {
                            u.getUserInfoPane().getChildren().get(3).setStyle("visibility: true");
                        }
                    }
                }
            }
        });
    }
    /***
     * 根据id获取userInfo
     * **/
    public UserInfo getUserInfoById(String id)
    {
        for(UserInfoVo u : userList)
        {
            if(u.getUserInfo().getId().equals(id))
            {
                return u.getUserInfo();
            }
        }
        return null;
    }

    //根据id判断是否已是好友
    public boolean judgeIsFriend(String id){
        for(UserInfoVo userInfoVo : userList){
            if(id.equals(userInfoVo.getUserInfo().getId())){
                return true;
            }
        }
        if(id.equals(myInfo.getId())) return true;
        return false;
    }

    //删除好友
    public void deleteUser(String id){
        if(id == null) return;
        System.out.println(id+userList.size());
        for(UserInfoVo userInfoVo : userList){
            if(userInfoVo.getUserInfo().getId().equals(id)){
                userList.remove(userInfoVo);
                //如果被删除的是当前点击的好友
                UserInfo current = currentUser.getUserInfo();
                if(current != null && id.equals(current.getId())){
                    currentUser.setUserInfo(null);
                    renderChatList();
                }
                //刷新好友列表
                renderFriendList(null,null);
                break;
            }
        }
    }

  /**
     * getter & setter
     * **/
    public UserInfo getMyInfo() {
        return myInfo;
    }

    public void setMyInfo(UserInfo myInfo) {
        this.myInfo = myInfo;
        if(GlobalState.myAvator != null){
            GlobalState.myAvator.setImage(new Image(myInfo.getAvatorURL()));
        }
    }

    public void setCurrentUser(UserInfoVo currentUser) {
        this.currentUser = currentUser;
    }

    public VBox getChatVBox() {
        return chatVBox;
    }

    public void setChatVBox(VBox chatVBox) {
        this.chatVBox = chatVBox;
    }

    public VBox getFriendsVBox() {
        return friendsVBox;
    }

    public void setFriendsVBox(VBox friendsVBox) {
        this.friendsVBox = friendsVBox;
    }

    public List<UserInfoVo> getUserList() {
        return userList;
    }

    public void setUserList(List<UserInfoVo> userList) {
        this.userList = userList;
    }

    public UserInfoVo getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String id) {
        for(UserInfoVo u : userList)
        {
            if(u.getUserInfo().getId().equals(id))
            {
                currentUser = u;
                break;
            }
        }
        renderChatList();
    }

    public ImageView getMyImage() {
        return myImage;
    }

    public void setMyImage(ImageView myImage) {
        this.myImage = myImage;
    }

    public void setChatNameLabel(Label chatNameLabel) {
        this.chatNameLabel = chatNameLabel;
    }
    public Label getChatNameLabel()
    {
        return chatNameLabel;
    }
}
