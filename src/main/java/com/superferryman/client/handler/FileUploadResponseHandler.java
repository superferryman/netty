package com.superferryman.client.handler;

import com.superferryman.protocol.common.FileUploadFile;
import com.superferryman.protocol.request.FileUploadRequestPacket;
import com.superferryman.protocol.response.FileUploadResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.RandomAccessFile;

/**
 * @Author superferryman
 * @Date 2019/5/10 19:28
 */
@ChannelHandler.Sharable
public class FileUploadResponseHandler extends SimpleChannelInboundHandler<FileUploadResponsePacket> {

    /**
     * 假定每次读取的大小
     */
    private volatile int lastLength = 1024 * 10;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FileUploadResponsePacket responsePacket) throws Exception {
        FileUploadFile fileUploadFile = responsePacket.getUploadFile();
        // 已读标识
        int byteRead = 0;
        // 读起始位置标识
        int start = responsePacket.getStart();
        if (start != -1) {
            // 以只读模式打开文件
            RandomAccessFile randomAccessFile = new RandomAccessFile(fileUploadFile.getFile(), "r");
            // 将指针移动到读起始位置
            randomAccessFile.seek(start);
            // 剩余长度
            int leftLength = (int) (randomAccessFile.length() - start);
            // 若当前剩余长度小于假定每次读取长度，则意味着这是最后一段数据
            if (leftLength < lastLength) {
                lastLength = leftLength;
            }
            byte[] bytes = new byte[lastLength];
            if ((byteRead = randomAccessFile.read(bytes)) != -1 && (randomAccessFile.length() - start) > 0) {
                // 设置下一次需要的参数
                fileUploadFile.setEndPosition(byteRead);
                fileUploadFile.setBytes(bytes);
                fileUploadFile.setStartPosition(start);
                ctx.writeAndFlush(new FileUploadRequestPacket(fileUploadFile, responsePacket.getReceiverId(), responsePacket.getUserId(), responsePacket.getType()));
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
