package com.superferryman.client.myChatClient.component;

import com.superferryman.client.myChatClient.bean.TalkItem;
import com.superferryman.client.myChatClient.utils.GlobalState;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class MessageItemFactory{
    private FileItem fileItem;
    private ChatItem chatItem;
    public MessageItemFactory()
    {
        fileItem = new FileItem();
        chatItem = new ChatItem();
    }
    /**
     * 根据传入的类型渲染对应聊天项
     * type：信息类型，0为一般信息，1为文件信息
     * avatorUrl：发送方头像
     * message：信息内容
     * flag：对方还是自己
     * **/
    public Node getItem(TalkItem talkItem)
    {
        String paneId = talkItem.getPaneId();
        String fromId = talkItem.getFromId();
        String myId = GlobalState.userManager.getMyInfo().getId();
        String name = "";
        boolean flag = false;
        if(paneId.equals(myId) && fromId.equals(myId))
        {
            flag = true;
        }
        if(!paneId.equals(fromId))
        {
            name = talkItem.getName();
        }
        if(talkItem.getType() == 0) {
            return chatItem.getChatItem(name, talkItem.getAvatorURL(), talkItem.getContent(), flag);
        } else if(talkItem.getType() == 1) {
            return fileItem.getFileItem(name, talkItem.getAvatorURL(), talkItem.getContent(), flag,talkItem.getFileURL());
        }else if(talkItem.getType() == 2){
            return new AlertItem().getInfoLabel(200,30,250,20,talkItem.getContent());
        }else if(talkItem.getType() == 3){
            return  chatItem.getChatImgItem(name,talkItem.getAvatorURL(),talkItem.getFileURL(),flag);
        }
        return null;
    }
}
