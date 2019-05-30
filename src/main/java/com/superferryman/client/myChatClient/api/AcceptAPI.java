package com.superferryman.client.myChatClient.api;

import com.superferryman.client.myChatClient.bean.User;
import com.superferryman.client.myChatClient.utils.GlobalState;
import com.superferryman.client.myChatClient.utils.MessageHandler;
import com.superferryman.pojo.Message;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AcceptAPI {
    private MessageHandler messageHandler = new MessageHandler();

    /************************************接收聊天信息********************************************/

    //TODO done 接收单聊文字信息处理
    /**@param fromId    发送方id
     * @param content   发送内容
     * */
    public void chatMessageHandle(String fromId,String content){
        messageHandler.personChatHandle(fromId,content);
    }
    //TODO done 接收对方不在线信息
    /**@param fromId 发送方
     * **/
    public void friendOfflineMessage(String fromId){
        messageHandler.deletedMessageHandle(fromId,2,"对方暂时不在线噢！");
    }
    //TODO  done 接收你已被拉黑信息
    /**@param fromId 发送方
     * */
    public void youHaveBeiLaHei(String fromId){
        messageHandler.deletedMessageHandle(fromId,2,"对方已把你删除！");
    }

    //TODO done 接收单聊文件处理
    /**@param fromId  发送方id
     * @param file   发送的文件
     * */
    public void chatFileHandle(String fromId, File file){
        int type = 1;
        String path = file.getAbsolutePath();
        boolean isImg = false;
        if(file.getAbsolutePath().toLowerCase().endsWith(".jpg") || file.getAbsolutePath().toLowerCase().endsWith(".png") || file.getAbsolutePath().toLowerCase().endsWith(".jpeg") || file.getAbsolutePath().toLowerCase().endsWith(".gif") || file.getAbsolutePath().toLowerCase().endsWith(".bmp")){
            type = 3;
            path = "file:"+path;
            isImg = true;
        }
        messageHandler.personFileHandle(fromId,type,file.getName(),path,isImg);
    }

    //TODO 接收群聊文件信息
    public void groupChatFileHandle(String groupId,String senderId,String senderName,int senderAvator,File file){
        int type = 1;
        String path = file.getAbsolutePath();
        boolean isImg = false;
        if(file.getAbsolutePath().toLowerCase().endsWith(".jpg") || file.getAbsolutePath().toLowerCase().endsWith(".png") || file.getAbsolutePath().toLowerCase().endsWith(".jpeg") || file.getAbsolutePath().toLowerCase().endsWith(".gif") || file.getAbsolutePath().toLowerCase().endsWith(".bmp")){
            type = 3;
            path = "file:"+path;
            isImg = true;
        }
        messageHandler.groupFileHandle(groupId,senderId,senderName,senderAvator,file.getName(),path,type);
    }

    //TODO done 接收群聊文字信息处理
    /**@param groupId    群id
     * @param fromId    发送方Id
     * @param fromName  发送方名字
     * @param fromURL 发送方头像
     * @param content   发送内容
     * */
    public void groupChatMessageHandle(String groupId,String fromId,String fromName,int fromURL,String content){
        messageHandler.groupChatHandle(new Message(fromId,groupId,content,fromName,fromURL));
    }

    //TODO done 群创建消息处理
    /**@param user    群id
     * */
    public void addGroupHandle(User user){
        List<User> list = new ArrayList<>();
        list.add(user);
        messageHandler.userHandler(list,true);
    }

    /************************************处理添加相关信息********************************************/

    //TODO DONE 加一个双向添加 接收添加好友信息
    /**@param   user    添加的好友,包括id,username,avator
     * */
    public void addFriendHandle(User user){
        new MessageHandler().addFriendHandle(user);
    }

    //TODO done 接收添加群信息
    /**@param group   添加的群,包括id,username,avator
     * */
    public void acceptGroupHandle(User group){
        new MessageHandler().addGroupHandle(group);
    }

    /************************************处理登录相关信息********************************************/

    //TODO done 登录成功后接收返回信息处理
    /**@param   user    本用户息，包括id,username,avator
     * */
    public void loginSuccessHandle(User user){
       messageHandler.loginHandle(user);
    }

    //TODO done 登录失败
    /*****/
    public void loginFailHandle(){
        messageHandler.loginFailHandle();
    }

    //TODO done 接收登录成功后请求返回的好友列表（不包括群聊）
    /**@param list  本用户的好友list，user中包含id,name,avator
     * **/
    public void loginRequestFriendListHandle(List<User> list){
        messageHandler.userHandler(list,false);
        new SendAPI().sendLoginRequestGroupList(GlobalState.userManager.getMyInfo().getId());
        new SendAPI().getAllMessage(GlobalState.userManager.getMyInfo().getId());
    }

    //TODO done 接收登录成功后请求返回的群列表（不包括好友）
    /**@param list  本用户的群list,user中包含groupId,avator
     * **/
    public void loginRequestGroupListHandle(List<User> list){
        messageHandler.userHandler(list,true);
    }

    /************************************处理查询相关信息********************************************/

    //TODO done 接收按id查询用户返回信息
    /**@param list    按id查询到的用户,包括id,username,avator
     * */
    public void queryUserHandle(List<User> list){
        new MessageHandler().queryUserHandle(list);
    }
    //TODO done 接收按id查询群返回信息
    /**@param list  按id查询到的群,包括id,username,avator
     * */
    public void queryGroupHandle(List<User> list){
        new MessageHandler().queryGroupHandle(list);
    }

    /************************************处理删除相关信息********************************************/

    //TODO done 接收删除好友信息
    /**@param deleteId  删除的好友id
     * */
    public void deleteFriendHandle(String deleteId){
        new MessageHandler().deleteUserHandle(deleteId);
    }

    //TODO done 接收退出群成功信息
    /**
     * **/
    public void deleteGroupHandle(String groupId){
        new MessageHandler().deleteGroupHandle(groupId);
    }

    /************************************其他********************************************/

    //TODO done 接收请求群成员列表的返回信息
    /**@param  groupId  list所属群id
     * @param list  本群群成员的list，user包括id,username,avator三个信息
     * **/
    public void groupUserHandle(String groupId,List<User> list){
        messageHandler.grouplistHandle(groupId,list);
    }

    //TODO done  返送注册成功账号
    /**@id  用户账号
     * **/
    public void registerSuccessHandle(String id){
        messageHandler.RegisterSuccessHandle(id);
    }

    //TODO done 处理离线信息
    public void myAllMessageHandle(List<Message> messageList){
        messageList.sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                if(o1.getId() < o2.getId()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        messageHandler.messagesHandle(messageList);
    }

}
