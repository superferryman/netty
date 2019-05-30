package com.superferryman.protocol.response;

import com.superferryman.pojo.User;
import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;
import com.superferryman.protocol.common.FileUploadFile;

/**
 * @Author superferryman
 * @Date 2019/5/10 21:25
 */
public class FileDownloadResponsePacket extends Packet {

    private FileUploadFile file;

    private String fromId;

    private int type;

    private Integer groupId;

    private String username;

    private Integer userAvator;

    public FileDownloadResponsePacket() {
    }

    public FileDownloadResponsePacket(FileUploadFile file, String fromId, int type) {
        this.file = file;
        this.fromId = fromId;
        this.type = type;
    }

    public FileDownloadResponsePacket(FileUploadFile file, String fromId, int type, Integer groupId, String username, Integer userAvator) {
        this.file = file;
        this.fromId = fromId;
        this.type = type;
        this.groupId = groupId;
        this.username = username;
        this.userAvator = userAvator;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public FileUploadFile getFile() {
        return file;
    }

    public void setFile(FileUploadFile file) {
        this.file = file;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserAvator() {
        return userAvator;
    }

    public void setUserAvator(Integer userAvator) {
        this.userAvator = userAvator;
    }

    @Override
    public Byte getCommand() {
        return Command.FILE_DOWNLOAD_RESPONSE;
    }
}
