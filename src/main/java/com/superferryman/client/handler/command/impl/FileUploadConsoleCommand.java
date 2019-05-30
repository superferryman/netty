package com.superferryman.client.handler.command.impl;

import com.superferryman.client.handler.command.ConsoleCommand;
import com.superferryman.pojo.Message;
import com.superferryman.protocol.common.FileUploadFile;
import com.superferryman.protocol.request.FileUploadRequestPacket;
import com.superferryman.util.SessionUtil;
import io.netty.channel.Channel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * @Author superferryman
 * @Date 2019/5/10 19:36
 */
public class FileUploadConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入好友id开始传输文件:");
        String friendId = scanner.next();
        FileUploadFile uploadFile = new FileUploadFile();
        File file = new File("D:\\用户目录\\Desktop\\20190420135532_IMG_9410.JPG");
        uploadFile.setFile(file);
        uploadFile.setStartPosition(0);
        uploadFile.setFileMd5(file.getName());
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(uploadFile.getFile(), "r");
            randomAccessFile.seek(uploadFile.getStartPosition());
            byte[] bytes = new byte[10240];
            int byteRead = 0;
            if ((byteRead = randomAccessFile.read(bytes)) != -1) {
                uploadFile.setEndPosition(byteRead);
                uploadFile.setBytes(bytes);
                channel.writeAndFlush(new FileUploadRequestPacket(uploadFile, friendId, SessionUtil.getSession(channel).getUserId(), Message.TYPE_FRIEND));
            }
            System.out.println("文件已开始传输");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
