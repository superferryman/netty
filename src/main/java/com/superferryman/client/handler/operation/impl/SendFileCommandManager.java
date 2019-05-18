package com.superferryman.client.handler.operation.impl;

import com.superferryman.client.handler.operation.Command;
import com.superferryman.protocol.Packet;
import com.superferryman.protocol.common.FileUploadFile;
import com.superferryman.protocol.request.FileUploadRequestPacket;
import com.superferryman.util.SessionUtil;
import io.netty.channel.Channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Author superferryman
 * @Date 2019/5/10 22:39
 */
public class SendFileCommandManager implements Command {
    @Override
    public void exec(Packet packet, Channel channel) {
        FileUploadFile uploadFile = ((FileUploadRequestPacket) packet).getUploadFile();
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(uploadFile.getFile(), "r");
            randomAccessFile.seek(uploadFile.getStartPosition());
            byte[] bytes;
            if (randomAccessFile.length() < 10240) {
                bytes = new byte[(int) randomAccessFile.length()];
            } else {
                bytes = new byte[10240];
            }
            int byteRead = 0;
            if ((byteRead = randomAccessFile.read(bytes)) != -1) {
                uploadFile.setEndPosition(byteRead);
                uploadFile.setBytes(bytes);
                uploadFile.setLength(randomAccessFile.length());
                channel.writeAndFlush(new FileUploadRequestPacket(uploadFile, ((FileUploadRequestPacket) packet).getFriendId(), SessionUtil.getSession(channel).getUserId()));
            }
            System.out.println("文件已开始传输");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
