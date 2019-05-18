package com.superferryman.client.myChatClient.api;


import com.superferryman.client.handler.operation.impl.*;
import com.superferryman.pojo.User;
import com.superferryman.protocol.common.FileUploadFile;
import com.superferryman.protocol.request.*;
import com.superferryman.util.SessionUtil;

import java.io.File;
import java.util.Arrays;

public class SendAPI {
    /************************************发送聊天相关信息********************************************/

    //TODO done 发送单聊文字信息
    /**@param from    发送方id
     * @param to    接收方id
     * @param content   发送内容
     * */
    public void sendWordMessage(String from,String to,String content){
        new SendToUserCommandManager().exec(new MessageRequestPacket(to, content), SessionUtil.getCurrentChannel());
    }

    //TODO done 发送群聊信息
    /**@param from    发送方id
     * @param groupId    群id
     * @param content   发送内容
     * */
    public void sendGroupChatMessage(String from,String groupId,String content){
        new SendToGroupCommandManager().exec(new GroupMessageRequestPacket(content, groupId), SessionUtil.getCurrentChannel());
    }

    //TODO done 发送创建群信息
    /**@param ids   群成员id列表，以逗号分隔
     * */
    public void sendCreateGroupMessage(String from, String ids){
        // 构造创建群聊数据包
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        createGroupRequestPacket.setUserIdList(Arrays.asList(ids.split(",")));
        createGroupRequestPacket.setCreatorId(from);
        createGroupRequestPacket.setGroupName("群聊");
        createGroupRequestPacket.setGroupDesc("");
        new CreateGroupCommandManager().exec(createGroupRequestPacket, SessionUtil.getCurrentChannel());
    }

    //TODO done 发送文件信息
    /**@param fromId    发送方id
     *@param  to   接收方id
     *@param  file 发送的文件
     * **/
    public void sendFileMessage(String fromId, String to, File file){
        FileUploadFile uploadFile = new FileUploadFile();
        uploadFile.setFile(file);
        uploadFile.setStartPosition(0);
        uploadFile.setFileMd5(file.getName());
        FileUploadRequestPacket requestPacket = new FileUploadRequestPacket(uploadFile, to, fromId);
        new SendFileCommandManager().exec(requestPacket, SessionUtil.getCurrentChannel());
    }

    /************************************发送登录相关信息********************************************/

    //TODO done  发送登录信息
    /**@param fromId    用户id
     * @param pwd   密码
     * */
    public void sendLoginMessage(String fromId,String pwd){
        new LoginCommandManager().exec(new LoginRequestPacket(pwd, fromId), SessionUtil.getCurrentChannel());
    }

    //TODO done  登录后请求好友列表（不包括群聊）
    /** @param   fromId 发送方id
     * **/
    public void sendLoginRequestFriendList(String fromId){
        new QueryFriendsCommandManager().exec(new QueryFriendsRequestPacket(fromId), SessionUtil.getCurrentChannel());
    }

    //TODO done 登录后请求群列表（不包括好友）
    /**@param   fromId 发送方id
     * **/
    public void sendLoginRequestGroupList(String fromId){
        new QueryGroupListCommandManager().exec(new QueryGroupListRequestPacket(fromId), SessionUtil.getCurrentChannel());
    }

    /************************************发送查询相关信息********************************************/

    //TODO done 查询用户
    /**@param fromId  发送方id
     * @param keyword      查询id
     * */
    public void sendQueryUserByIdMessage(String fromId,String keyword){
        new QueryUserCommandManager().exec(new QueryUserRequestPacket(keyword), SessionUtil.getCurrentChannel());
    }

    //TODO done 查询群组
    /**@param fromId    发送方id
     * @param keyword   查询id
     * **/
    public void sendQueryGroupByIdMessage(String fromId,String keyword){
        new QueryGroupCommandManager().exec(new QueryGroupRequestPacket(keyword), SessionUtil.getCurrentChannel());
    }

    //TODO done 请求群成员列表
    /**@param fromId    发送方id
     *@param  groupId   请求的列表的群id
     * **/
    public void sendGroupUsersListMessage(String fromId,String groupId){
        new ListGroupMembersCommandManager().exec(new ListGroupMembersRequestPacket(groupId), SessionUtil.getCurrentChannel());
    }

    /************************************发送添加相关信息********************************************/

    //TODO done 发送添加好友信息
    /**@param fromId    发送方id
     * @param friendId  待添加好友的id
     * */
    public void sendAddFriendMessage(String fromId,String friendId){
        new AddFriendCommandManager().exec(new AddFriendRequestPacket(fromId, friendId), SessionUtil.getCurrentChannel());
    }

    //TODO done 发送添加群信息  待测
    /**@param fromId    发送方id
     * @param groupId   待添加群id
     * */
    public void sendAddGroupMessage(String fromId,String groupId){
        new JoinGroupCommandManager().exec(new JoinGroupRequestPacket(groupId, fromId), SessionUtil.getCurrentChannel());
    }

    /************************************发送删除相关信息********************************************/

    //TODO done 发送删除好友信息
    /**@param fromId 发送方id
     * @param deleteId 删除的id
     * **/
    public void sendDeleteFriendMessage(String fromId,String deleteId){
        new DeleteFriendCommandManager().exec(new DeleteFriendRequestPacket(fromId, deleteId), SessionUtil.getCurrentChannel());
    }
    //TODO done 发送退出群信息
    /**@param fromId 发送方id
     * @param groupId 群Id
     * **/
    public void sendDeleteGroupId(String fromId,String groupId){
        new QuitGroupCommandManager().exec(new QuitGroupRequestPacket(groupId, fromId), SessionUtil.getCurrentChannel());
    }

    /************************************其他********************************************/

    /**关闭界面后改方法被调用
     * **/
    public void closeAll(){
        new LogoutCommandManager().exec(new LogoutRequestPacket(), SessionUtil.getCurrentChannel());
    }

    //TODO  发送注册信息
    /**@name 用户名
     * @password    密码
     * @avator      头像
     * */
    public void sendRegisterMessage(String name,String password,int avator){
        RegisterRequestPacket requestPacket = new RegisterRequestPacket();
        requestPacket.setUser(new User(name, password, avator));
        new RegisterCommandManager().exec(requestPacket, SessionUtil.getCurrentChannel());
    }
}
