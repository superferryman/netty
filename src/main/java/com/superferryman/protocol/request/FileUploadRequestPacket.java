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
    private String friendId;

    /**
     * 发送方 Id
     */
    private String userId;

    public FileUploadRequestPacket() {
    }

    public FileUploadRequestPacket(FileUploadFile uploadFile, String friendId, String userId) {
        this.uploadFile = uploadFile;
        this.friendId = friendId;
        this.userId = userId;
    }

    public FileUploadFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(FileUploadFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Byte getCommand() {
        return Command.FILE_UPLOAD_REQUEST;
    }
}
