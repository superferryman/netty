package com.superferryman.client.myChatClient.utils;

import com.superferryman.client.myChatClient.api.SendAPI;
import com.superferryman.client.myChatClient.bean.TalkItem;
import com.superferryman.client.myChatClient.bean.User;
import com.superferryman.client.myChatClient.bean.UserInfo;
import com.superferryman.client.myChatClient.bean.UserInfoVo;
import com.superferryman.client.myChatClient.component.AlertItem;
import com.superferryman.client.myChatClient.component.InfoPage;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import com.superferryman.client.myChatClient.Main;
import com.superferryman.client.myChatClient.component.FriendItem;
import com.superferryman.client.myChatClient.component.MessageItemFactory;
import javafx.scene.text.Text;

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
     * @param paneId    发送方id，若为单聊，为发送方id；若是群聊，为群组id
     * @param fromId    发送方id，若为单聊，为发送方id；若是群聊，为发送者id
     * @param type      消息类型，0表示文字消息，1表示文件
     * @param content   消息内容
     * @param fileURL   若为文字消息，传入空串即可；若为文件消息，需传入文件路径
     * **/
    public void chatHandle(String paneId,String fromId, int type,String content,String fileURL)
    {
        //找到目标对象
        UserInfo targetUserInfo = userManager.getUserInfoById(paneId);
        UserInfo targetUserInfo2 = userManager.getUserInfoById(fromId);
        String avatorURL = targetUserInfo2.getAvatorURL();
        //追加信息到聊天list
        TalkItem talkItem = new  TalkItem(type,paneId,fromId,content,avatorURL,fileURL);
        targetUserInfo.getMessages().add(talkItem);
        targetUserInfo.setPriority(GlobalState.cnt++);
        //重新渲染好友列表
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //更新JavaFX的主线程的代码放在此处
                userManager.renderFriendList(paneId,content);
            }
        });
        //如果是当前聊天对象，追加到聊天框
        UserInfo currentUserInfo = userManager.getCurrentUser().getUserInfo();
        if(currentUserInfo != null && currentUserInfo.getId().equals(paneId))
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
                queryAdd(list);
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
                queryAdd(list);
            }
        });
    }

    public void queryAdd(List<User> list){
        if(GlobalState.addPageStage != null) {
            ScrollPane scrollPane = (ScrollPane) ((AnchorPane) (GlobalState.addPageStage.getScene().getRoot())).getChildren().get(2);
            FlowPane flowPane = (FlowPane) scrollPane.getContent();
            flowPane.getChildren().remove(0, flowPane.getChildren().size());
            if (list != null && list.size() != 0){
                GlobalState.findNull.setVisible(false);
                for (User user : list) {
                    flowPane.getChildren().add(new FriendItem().getAddFriendItem(user.getUsername(), user.getId(), GlobalState.getImgUrl(user.getAvator())));
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
                ((Label)((Pane)GlobalState.registSuccess.getChildren().get(1)).getChildren().get(2)).setText("账号为："+userId);
                Main.stage.getScene().setRoot(GlobalState.registSuccess);
            }
        });
    }
}
