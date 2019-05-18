package com.superferryman.protocol.response;

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

    public FileDownloadResponsePacket() {
    }

    public FileDownloadResponsePacket(FileUploadFile file, String fromId) {
        this.file = file;
        this.fromId = fromId;
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

    @Override
    public Byte getCommand() {
        return Command.FILE_DOWNLOAD_RESPONSE;
    }
}
