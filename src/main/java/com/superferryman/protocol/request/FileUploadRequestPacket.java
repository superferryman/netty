package com.superferryman.protocol.request;

import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;
import com.superferryman.protocol.common.FileUploadFile;

/**
 * @Author superferryman
 * @Date 2019/5/10 15:58
 */
public class FileUploadRequestPacket extends Packet {

    /**
     * 自定义传输文件
     */
    private FileUploadFile uploadFile;

    /**
     * 接收方的 Id
     */
    private String receiverId;

    /**
     * 发送方 Id
     */
    private String userId;

    /**
     * 消息类型
     */
    private int type;

    public FileUploadRequestPacket() {
    }

    public FileUploadRequestPacket(FileUploadFile uploadFile, String receiverId, String userId, int type) {
        this.uploadFile = uploadFile;
        this.receiverId = receiverId;
        this.userId = userId;
        this.type = type;
    }

    public FileUploadFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(FileUploadFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public Byte getCommand() {
        return Command.FILE_UPLOAD_REQUEST;
    }
}
