package com.superferryman.client.myChatClient.utils;

import com.superferryman.client.myChatClient.api.SendAPI;
import com.superferryman.client.myChatClient.bean.TalkItem;
import com.superferryman.client.myChatClient.bean.UserInfo;
import com.superferryman.client.myChatClient.component.MessageItemFactory;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.File;

public class MessagePrepare {
    public void sendWordMessage(String message, VBox vBox, int isFile, File file){
        //取得当前聊天对象id
        String targetId = GlobalState.userManager.getCurrentUser().getUserInfo().getId();
        //根据对象id获得userInfo
        UserInfo userInfo = GlobalState.userManager.getCurrentUser().getUserInfo();
        //是否群聊
        boolean isGroup = GlobalState.userManager.getCurrentUser().getUserInfo().isGroup();
        //取得自己id
        UserInfo myInfo = GlobalState.userManager.getMyInfo();
        String myId = myInfo.getId();
        String avatorURL = myInfo.getAvatorURL();
        String fileURL = "";
        //发送信息
        if(isFile == 0){
            if(isGroup){
                new SendAPI().sendGroupChatMessage(myId,targetId,message);
            }else{
                new SendAPI().sendWordMessage(myId,targetId,message);
            }
        }else if(isFile == 1){
            fileURL = file.getAbsolutePath();
            if(!isGroup){
                new SendAPI().sendFileMessage(myId,targetId,file);
            }
            if(file.getAbsolutePath().endsWith(".jpg") || file.getAbsolutePath().endsWith(".png")){
                isFile = 3;
                fileURL = "file:"+fileURL;
            }
        }
        //封装信息
        TalkItem talkItem = new TalkItem(isFile,myId,myId,message,avatorURL,fileURL);
        //添加到目标聊天列表
        userInfo.getMessages().add(talkItem);
        //追加到聊天框
        vBox.getChildren().add(new MessageItemFactory().getItem(talkItem));
        //追加到最新消息
        Label label = (Label)GlobalState.userManager.getCurrentUser().getUserInfoPane().getChildren().get(2);
        //列表项上移
        userInfo.setPriority(GlobalState.cnt++);
        GlobalState.userManager.renderFriendList(null,null);
        label.setText(message);
    }
}
