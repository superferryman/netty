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

    public FileDownloadRequestPacket() {
    }

    public FileDownloadRequestPacket(int start, FileUploadFile downloadFile, String fromId) {
        this.start = start;
        this.downloadFile = downloadFile;
        this.fromId = fromId;
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

    @Override
    public Byte getCommand() {
        return Command.FILE_DOWNLOAD_REQUEST;
    }
}
