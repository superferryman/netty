package com.superferryman.server.handler;

import com.superferryman.pojo.Message;
import com.superferryman.protocol.common.FileUploadFile;
import com.superferryman.protocol.request.FileDownloadRequestPacket;
import com.superferryman.protocol.response.FileDownloadResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.RandomAccessFile;

/**
 * @Author superferryman
 * @Date 2019/5/10 21:34
 */
@ChannelHandler.Sharable
public class FileDownloadRequestHandler extends SimpleChannelInboundHandler<FileDownloadRequestPacket> {

    /**
     * 假定每次读取的大小
     */
    private volatile int lastLength = 1024 * 10;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FileDownloadRequestPacket requestPacket) throws Exception {
        FileUploadFile downloadFile = requestPacket.getDownloadFile();
        // 已读标识
        int byteRead = 0;
        // 读起始位置标识
        int start = requestPacket.getStart();
        if (start != -1) {
            // 以只读模式打开文件
            RandomAccessFile randomAccessFile = new RandomAccessFile(downloadFile.getFile(), "r");
            // 将指针移动到读起始位置
            randomAccessFile.seek(start);
            // 剩余长度
            int leftLength = (int) (downloadFile.getLength() - start);
            // 若当前剩余长度小于假定每次读取长度，则意味着这是最后一段数据
            if (leftLength < lastLength) {
                lastLength = leftLength;
            }
            byte[] bytes = new byte[lastLength];
            if ((byteRead = randomAccessFile.read(bytes)) != -1 && leftLength > 0) {
                // 设置下一次需要的参数
                downloadFile.setEndPosition(byteRead);
                downloadFile.setBytes(bytes);
                downloadFile.setStartPosition(start);
                int type = requestPacket.getType();
                if (type == Message.TYPE_FRIEND) {
                    ctx.writeAndFlush(new FileDownloadResponsePacket(downloadFile,
                            requestPacket.getFromId(), requestPacket.getType()));
                } else if (type == Message.TYPE_GROUP) {
                    ctx.writeAndFlush(new FileDownloadResponsePacket(downloadFile, requestPacket.getFromId(),
                            requestPacket.getType(), requestPacket.getGroupId(), requestPacket.getUsername(),
                            requestPacket.getUserAvator()));
                }
                // 防止因特殊情况而导致的文件被占用
                randomAccessFile.close();
            } else {
                randomAccessFile.close();
                System.out.println("文件已经读完   " + byteRead);
                lastLength = 1024 * 10;
            }
        }
    }
}
