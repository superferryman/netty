package com.superferryman.client.myChatClient.bean;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.superferryman.client.myChatClient.enums.MessageSender;
import com.superferryman.client.myChatClient.enums.MessageType;

public class TalkItem {
    //信息类型，0文字，1文件，2错误信息
    private int type;
    //发送者,0自己，1对方
    private String paneId;
    private String fromId;
    //内容
    private String content;
    //文件路径
    private String fileURL;
    //头像路径
    private String avatorURL;

    public TalkItem(int type, String paneId, String fromId, String content, String avatorURL,String fileURL) {
        this.type = type;
        this.paneId = paneId;
        this.fromId = fromId;
        this.content = content;
        this.fileURL = fileURL;
        this.avatorURL = avatorURL;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPaneId() {
        return paneId;
    }

    public void setPaneId(String paneId) {
        this.paneId = paneId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public String getAvatorURL() {
        return avatorURL;
    }

    public void setAvatorURL(String avatorURL) {
        this.avatorURL = avatorURL;
    }
}
