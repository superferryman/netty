package com.superferryman.server.handler;

import com.superferryman.protocol.common.StringConst;
import com.superferryman.protocol.common.FileUploadFile;
import com.superferryman.protocol.request.FileUploadRequestPacket;
import com.superferryman.protocol.response.FileDownloadResponsePacket;
import com.superferryman.protocol.response.FileUploadResponsePacket;
import com.superferryman.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Author superferryman
 * @Date 2019/5/10 16:09
 */
@ChannelHandler.Sharable
public class FileUploadRequestHandler extends SimpleChannelInboundHandler<FileUploadRequestPacket> {

    public static final FileUploadRequestHandler INSTANCE = new FileUploadRequestHandler();

    private FileUploadRequestHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FileUploadRequestPacket requestPacket) throws Exception {
        // 获取文件写出路径
        String fileLocation = StringConst.FILE_UPLOAD_DIR;
        // 根据数据包获取自定义文件类型
        FileUploadFile uploadFile = requestPacket.getUploadFile();
        // 获取传送过来的数据
        byte[] bytes = uploadFile.getBytes();
        // 获取读起始位置
        int start = uploadFile.getStartPosition();
        // 获取已读位置
        int byteRead = uploadFile.getEndPosition();
        // 获取文件名称
        String fileMd5 = uploadFile.getFileMd5();
        // 创建写出路径
        String path = fileLocation + File.separator + fileMd5;
        File file = new File(path);
        // 以只读模式创建一个 RandomAccessFile 对象
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        // 移动文件记录指针到 start 位置
        randomAccessFile.seek(start);
        // 以当前位置为起始位置，开始写入数据
        randomAccessFile.write(bytes);
        // 更新起始位置
        start = start + byteRead;
        if (byteRead > 0) {
            ctx.writeAndFlush(new FileUploadResponsePacket(start, uploadFile, requestPacket.getFriendId(), requestPacket.getUserId()));
            randomAccessFile.close();
            // 如果当前为文件结束
            if (byteRead < 1024 * 10) {
                Thread.sleep(1000);
                sendFile(SessionUtil.getChannel(requestPacket.getFriendId()), file, requestPacket.getUserId(), uploadFile.getLength());
            }
        }
        System.out.println("服务器处理客户端文件完毕，文件路径:" + path + "," + byteRead);
    }

    private void sendFile(Channel channel, File file, String fromId, long length) {
        FileUploadFile downloadFile = new FileUploadFile();
        downloadFile.setFile(file);
        downloadFile.setStartPosition(0);
        downloadFile.setFileMd5(file.getName());
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(downloadFile.getFile(), "r");
            randomAccessFile.seek(downloadFile.getStartPosition());
            byte[] bytes;
            if (length - downloadFile.getStartPosition() < 10240) {
                bytes = new byte[(int) (length - downloadFile.getStartPosition())];
            } else {
                bytes = new byte[1024 * 10];
            }
            int byteRead = 0;
            if ((byteRead = randomAccessFile.read(bytes)) != -1) {
                if (length - downloadFile.getStartPosition() < 10240) {
                    downloadFile.setEndPosition((int) (length - downloadFile.getStartPosition()));
                } else {
                    downloadFile.setEndPosition(byteRead);
                }
                downloadFile.setBytes(bytes);
                downloadFile.setLength(length);
                channel.writeAndFlush(new FileDownloadResponsePacket(downloadFile, fromId));
            }
            System.out.println("文件已开始传输");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
