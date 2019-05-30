package com.superferryman.protocol.response;

import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;
import com.superferryman.protocol.common.FileUploadFile;

/**
 * @Author superferryman
 * @Date 2019/5/10 16:06
 */
public class FileUploadResponsePacket extends Packet {

    /**
     * 文件读取的开始位置标识
     */
    private int start;

    /**
     * 自定义文件实体
     */
    private FileUploadFile uploadFile;

    /**
     * 接收方 Id
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

    public FileUploadResponsePacket() {
    }

    public FileUploadResponsePacket(int start, FileUploadFile uploadFile, String receiverId, String userId, int type) {
        this.start = start;
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

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Byte getCommand() {
        return Command.FILE_UPLOAD_RESPONSE;
    }
}
