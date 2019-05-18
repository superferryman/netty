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
    private String friendId;

    /**
     * 发送方 Id
     */
    private String userId;

    public FileUploadResponsePacket() {
    }

    public FileUploadResponsePacket(int start, FileUploadFile fileUploadFile, String friendId, String userId) {
        this.start = start;
        this.uploadFile = fileUploadFile;
        this.friendId = friendId;
        this.userId = userId;
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
        return Command.FILE_UPLOAD_RESPONSE;
    }
}
