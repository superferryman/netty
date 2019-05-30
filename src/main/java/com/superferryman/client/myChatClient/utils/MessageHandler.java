package com.superferryman.client.myChatClient.utils;

import com.superferryman.client.myChatClient.api.SendAPI;
import com.superferryman.client.myChatClient.bean.TalkItem;
import com.superferryman.client.myChatClient.bean.User;
import com.superferryman.client.myChatClient.bean.UserInfo;
import com.superferryman.client.myChatClient.bean.UserInfoVo;
import com.superferryman.client.myChatClient.component.AlertItem;
import com.superferryman.client.myChatClient.component.InfoPage;
import com.superferryman.pojo.Message;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import com.superferryman.client.myChatClient.Main;
import com.superferryman.client.myChatClient.component.FriendItem;
import com.superferryman.client.myChatClient.component.MessageItemFactory;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.superferryman.client.myChatClient.utils.GlobalState.userManager;

public class MessageHandler {

    /**
     * 新增好友信息
     * List<User> list：待新增的好友列表
     * **/
    public void userHandler(List<User> list, boolean isGroup)
    {
        //取得当前列表
        List<UserInfoVo> userList = userManager.getUserList();
        FriendItem friendItem = new FriendItem();
        if(list == null || list.size() == 0) return;
        //根据待增列表，创建userInfoVo并加入当前list
        for(User u : list)
        {
            if(userManager.getUserInfoById(u.getId()) != null){
                continue;
            }
            UserInfoVo tmp = new UserInfoVo();
            UserInfo userInfo = new UserInfo(u.getId(),u.getUsername(),new ArrayList<TalkItem>(),GlobalState.cnt++,isGroup,GlobalState.getImgUrl(u.getAvator()));
            Pane leftPane = friendItem.getSimpleFriendItem(GlobalState.getImgUrl(u.getAvator()),u.getUsername(),u.getId(),true);
            Pane rightPane = friendItem.getSimpleFriendItem(GlobalState.getImgUrl(u.getAvator()),u.getUsername(),u.getId(),false);
            Pane pane = friendItem.getChatFriendItem(GlobalState.getImgUrl(u.getAvator()),u.getUsername(),"",u.getId());
            tmp.setRightUserInfoPane(rightPane);
            tmp.setLeftUserInfoPane(leftPane);
            tmp.setUserInfoPane(pane);
            tmp.setUserInfo(userInfo);
            userList.add(tmp);
        }
        userManager.renderFriendList(null,null);
    }

    /**
     * 处理聊天信息
     * @param targetUserInfo    该条信息所属的好友列表中的某一项
     * @param senderId    发送方id，若为单聊，为发送方id；若是群聊，为发送者id
     * @param senderName    发送方名字
     * @param avatorURL 发送方头像
     * @param type      消息类型，0表示文字消息，1表示文件
     * @param content   消息内容
     * @param fileURL   若为文字消息，传入空串即可；若为文件消息，需传入文件路径
     * **/
    public void chatHandle(UserInfo targetUserInfo,String senderId,String senderName,String avatorURL, int type,String content,String fileURL)
    {
        //追加信息到聊天list
        TalkItem talkItem = new  TalkItem(type,targetUserInfo.getId(),senderId,content,avatorURL,fileURL,senderName);
        targetUserInfo.getMessages().add(talkItem);
        targetUserInfo.setPriority(GlobalState.cnt++);
        //重新渲染好友列表
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //更新JavaFX的主线程的代码放在此处
                userManager.renderFriendList(targetUserInfo.getId(),content);
            }
        });
        //如果是当前聊天对象，追加到聊天框
        UserInfo currentUserInfo = userManager.getCurrentUser().getUserInfo();
        if(currentUserInfo != null && currentUserInfo.getId().equals(targetUserInfo.getId()))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //更新JavaFX的主线程的代码放在此处
                    Node node = new MessageItemFactory().getItem(talkItem);
                    if(talkItem.getType() == 2){
                        GlobalState.chatVBox.getChildren().add((Label)node);
                    }else{
                        GlobalState.chatVBox.getChildren().add((HBox)node);
                    }
                }
            });
            //刷新滚动条
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    GlobalState.scrollerPane.setVvalue(1);
                }
            }).start();
        }
    }

    /**处理群聊信息**/
    public void groupChatHandle(Message message){
        if(message != null){
            UserInfo paneInfo = userManager.getUserInfoById(message.getReceiverId());
            chatHandle(paneInfo,message.getSenderId(),message.getSenderName(),GlobalState.getImgUrl(message.getSenderAvator()),0,message.getContent(),"");
        }
    }
    /*处理群聊文件信息**/
    public void groupFileHandle(String groupId, String senderId, String senderName, int senderAvator, String fileName,String filePath,int type){
        UserInfo paneInfo = userManager.getUserInfoById(groupId);
        if(paneInfo != null){
            chatHandle(paneInfo,senderId,senderName,GlobalState.getImgUrl(senderAvator),type,fileName,filePath);
        }
    }

    /***处理单聊信息**/
    public void personChatHandle(String senderId,String content){
        if(senderId != null && content != null && senderId.trim().length() > 0 && content.trim().length() > 0){
            UserInfo paneInfo = userManager.getUserInfoById(senderId);
            if(paneInfo != null){
                chatHandle(paneInfo,paneInfo.getId(),paneInfo.getUsername(),paneInfo.getAvatorURL(),0,content,"");
            }
        }
    }

    /**处理单聊文件信息**/
    public void personFileHandle(String senderId,int type,String fileName,String filePath,Boolean isImg){
        UserInfo paneInfo = userManager.getUserInfoById(senderId);
        if(paneInfo != null){
            if(isImg){
                chatHandle(paneInfo,paneInfo.getId(),paneInfo.getUsername(),paneInfo.getAvatorURL(),3,fileName,filePath);
            }
            else{
                chatHandle(paneInfo,paneInfo.getId(),paneInfo.getUsername(),paneInfo.getAvatorURL(),1,fileName,filePath);
            }
        }
    }




    /**处理拉黑信息**/
    public void deletedMessageHandle(String fromId,int type,String content){
        UserInfo userInfo = userManager.getUserInfoById(fromId);
        chatHandle(userInfo,fromId,userInfo.getUsername(),userInfo.getAvatorURL(),type,content,"");
    }
    /***
     * 登录成功调用处理函数
     * @param  user 本用户信息，包括id,username,avator
     * */
    public void loginHandle(User user)
    {
        //添加此用户信息
        userManager.setMyInfo(new UserInfo(user.getId(),user.getUsername(),null,0,false,GlobalState.getImgUrl(user.getAvator())));
        //调出主界面
        Main.stage.getScene().setRoot(Main.root);
        Main.stage.setWidth(928);
        Main.stage.setHeight(604);
        new SendAPI().sendLoginRequestFriendList(user.getId());
    }

    /**登录失败
     * */
    public void loginFailHandle(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //更新JavaFX的主线程的代码放在此处
                new AlertItem().getInfoAlert("账号密码错误或账号已登录",50,80).show();
            }
        });
    }

    /**处理返回的群成员列表信息
     * @param groupId   list所属群id
     * @param list      该群的群成员列表
     * **/
    public void grouplistHandle(String groupId, List<User> list) {
        UserInfo userInfo = userManager.getCurrentUser().getUserInfo();
        if(userInfo != null && userInfo.getId().equals(groupId) && Main.stage.getWidth() > 1000){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //更新JavaFX的主线程的代码放在此处
                    new InfoPage().addGroupList(list);
                }
            });
        }
    }

    /**处理按id查询用户的返回结果
     * @param list  查询得到的单个user
     * */
    public void queryUserHandle(List<User> list){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //更新JavaFX的主线程的代码放在此处
                queryAdd(list,"");
            }
        });
    }

    /**处理按id查询群的返回结果
     * @param list 查询到的单个user
     * */
    public void queryGroupHandle(List<User> list){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //更新JavaFX的主线程的代码放在此处
                queryAdd(list,"群");
            }
        });
    }

    public void queryAdd(List<User> list,String tip){
        if(GlobalState.addPageStage != null) {
            ScrollPane scrollPane = (ScrollPane) ((AnchorPane) (GlobalState.addPageStage.getScene().getRoot())).getChildren().get(2);
            FlowPane flowPane = (FlowPane) scrollPane.getContent();
            flowPane.getChildren().remove(0, flowPane.getChildren().size());
            if (list != null && list.size() != 0){
                GlobalState.findNull.setVisible(false);
                for (User user : list) {
                    flowPane.getChildren().add(new FriendItem().getAddFriendItem(user.getUsername(), user.getId(), GlobalState.getImgUrl(user.getAvator()),tip));
                }
            }
            else if (GlobalState.addPageStage != null) {
                GlobalState.findNull.setVisible(true);
            }
        }
    }
    /**处理添加好友
     * @param user 待添加好友
     * **/
    public void addFriendHandle(User user) {
        List<User> list = new ArrayList<>();
        list.add(user);
        userHandler(list,false);
    }

    /**处理添加群
     * @param group 待添加群
     * */
    public void addGroupHandle(User group){
        List<User> list = new ArrayList<>();
        list.add(group);
        userHandler(list,true);
    }

    /**删除好友
     * @param deleteId 待删除id
     * **/
    public void deleteUserHandle(String deleteId){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //更新JavaFX的主线程的代码放在此处
                userManager.deleteUser(deleteId);
            }
        });
    }

    /**退出群
     * @param groupId 群id
     * */
    public void deleteGroupHandle(String groupId){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //更新JavaFX的主线程的代码放在此处
                userManager.deleteUser(groupId);
            }
        });
    }

    /**注册成功*/
    public void RegisterSuccessHandle(String userId){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //更新JavaFX的主线程的代码放在此处
                ((TextField)((Pane)GlobalState.registSuccess.getChildren().get(1)).getChildren().get(2)).setText("账号为："+userId);
                Main.stage.getScene().setRoot(GlobalState.registSuccess);
            }
        });
    }

    /**个人所有聊天记录处理**/
    public void messagesHandle(List<Message> messageList){
        UserInfo myUserInfo = userManager.getMyInfo();
        for(Message message : messageList){
            System.out.println(message);
            UserInfo paneInfo = null;
            String paneId = "",fromId = "",content = "",avatorURL = "",fileURL = "",name = "";
            int type = 0;
            if(message.getMessageType() == 0) {
                if(message.getSenderId().equals(myUserInfo.getId())){
                    paneId = fromId = myUserInfo.getId();
                    avatorURL = myUserInfo.getAvatorURL();
                    paneInfo = userManager.getUserInfoById(message.getReceiverId());
                }
                else{
                    paneId = fromId = message.getSenderId();
                    avatorURL = GlobalState.getImgUrl(message.getSenderAvator());
                    name = message.getSenderName();
                    paneInfo = userManager.getUserInfoById(message.getSenderId());
                }
            }
            else if(message.getMessageType() == 1){
                if(message.getSenderId().equals(myUserInfo.getId())){
                    paneId = fromId = myUserInfo.getId();
                    avatorURL = myUserInfo.getAvatorURL();
                }
                else{
                    paneId = message.getReceiverId();
                    fromId = message.getSenderId();
                    avatorURL = GlobalState.getImgUrl(message.getSenderAvator());
                    name = message.getSenderName();
                }
                paneInfo = userManager.getUserInfoById(message.getReceiverId());
            }
            content = message.getContent();
            if(paneInfo == null){
                return;
            }

            paneInfo.getMessages().add(new TalkItem(type,paneId,fromId,content,avatorURL,"",name));
            paneInfo.setPriority(GlobalState.cnt++);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for(UserInfoVo u : userManager.getUserList()){
                    //追加最新消息
                    Label label = (Label)u.getUserInfoPane().getChildren().get(2);
                    int size = u.getUserInfo().getMessages().size();
                    if(size > 0){
                        label.setText(u.getUserInfo().getMessages().get(size-1).getContent());
                    }
                }
                userManager.renderFriendList("","");
            }
        });
    }
}
