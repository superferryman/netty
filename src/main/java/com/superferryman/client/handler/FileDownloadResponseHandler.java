package com.superferryman.client.handler;

import com.superferryman.client.myChatClient.api.AcceptAPI;
import com.superferryman.pojo.Message;
import com.superferryman.protocol.common.StringConst;
import com.superferryman.protocol.common.FileUploadFile;
import com.superferryman.protocol.request.FileDownloadRequestPacket;
import com.superferryman.protocol.response.FileDownloadResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @Author superferryman
 * @Date 2019/5/10 21:29
 */
@ChannelHandler.Sharable
public class FileDownloadResponseHandler extends SimpleChannelInboundHandler<FileDownloadResponsePacket> {

    public static final FileDownloadResponseHandler INSTANCE = new FileDownloadResponseHandler();

    private FileDownloadResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FileDownloadResponsePacket responsePacket) throws Exception {
        System.out.println("收到服务器发来的文件，正在处理......");
        // 获取文件写出路径
        String fileLocation = StringConst.FILE_DOWNLOAD_DIR;
        // 根据数据包获取自定义文件类型
        FileUploadFile uploadFile = responsePacket.getFile();
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
            int type = responsePacket.getType();
            if (type == Message.TYPE_FRIEND) {
                ctx.writeAndFlush(new FileDownloadRequestPacket(start, uploadFile,
                        responsePacket.getFromId(), type));
            } else if (type == Message.TYPE_GROUP) {
                ctx.writeAndFlush(new FileDownloadRequestPacket(start, uploadFile, responsePacket.getFromId(),
                        type, responsePacket.getGroupId(), responsePacket.getUsername(), responsePacket.getUserAvator()));
            }
            randomAccessFile.close();
            // 如果当前为文件结束
            if (byteRead < 10240) {
                Thread.sleep(1000);
                if (type == Message.TYPE_FRIEND) {
                    new AcceptAPI().chatFileHandle(responsePacket.getFromId(), file);
                } else if (type == Message.TYPE_GROUP) {
                    new AcceptAPI().groupChatFileHandle(String.valueOf(responsePacket.getGroupId()),
                            responsePacket.getFromId(), responsePacket.getUsername(),
                            responsePacket.getUserAvator(), file);
                }
                System.out.println("文件接收成功");
            }
        }
        System.out.println("处理完毕，文件路径:" + path + "," + byteRead);
    }
}
