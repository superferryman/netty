package com.superferryman.protocol.request;

import com.superferryman.protocol.Packet;
import com.superferryman.protocol.command.Command;
import com.superferryman.protocol.common.FileUploadFile;

/**
 * @Author superferryman
 * @Date 2019/5/10 21:23
 */
public class FileDownloadRequestPacket extends Packet {

    private int start;

    private FileUploadFile downloadFile;

    private String fromId;

    private int type;

    private Integer groupId;

    private String username;

    private Integer userAvator;

    public FileDownloadRequestPacket() {
    }

    public FileDownloadRequestPacket(int start, FileUploadFile downloadFile, String fromId, int type) {
        this.start = start;
        this.downloadFile = downloadFile;
        this.fromId = fromId;
        this.type = type;
    }

    public FileDownloadRequestPacket(int start, FileUploadFile downloadFile, String fromId, int type, Integer groupId, String username, Integer userAvator) {
        this.start = start;
        this.downloadFile = downloadFile;
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

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public FileUploadFile getDownloadFile() {
        return downloadFile;
    }

    public void setDownloadFile(FileUploadFile downloadFile) {
        this.downloadFile = downloadFile;
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
        return Command.FILE_DOWNLOAD_REQUEST;
    }
}
