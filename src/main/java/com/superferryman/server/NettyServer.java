package com.superferryman.server;

import com.superferryman.handler.IMIdleStateHandler;
import com.superferryman.protocol.common.StringConst;
import com.superferryman.protocol.code.PacketCodeHandler;
import com.superferryman.protocol.code.Spliter;
import com.superferryman.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Date;

/**
 * @Author superferryman
 * @Date 2019/4/21 20:13
 */
public class NettyServer {

    public static void startServer() {
        main(null);
    }

    public static void main(String[] args) {
        // 创建线程模型
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        // 服务器引导
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                // 指定线程模型
                .group(bossGroup, workerGroup)
                // 指定 IO 类型
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                // 实现 IO 处理逻辑
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        // 空闲检测处理器
                        nioSocketChannel.pipeline().addLast(new IMIdleStateHandler());
                        // 拆包处理器
                        nioSocketChannel.pipeline().addLast(new Spliter());
                        // 编码译码处理器
                        nioSocketChannel.pipeline().addLast(PacketCodeHandler.INSTANCE);
                        // 注册请求处理器
                        nioSocketChannel.pipeline().addLast(RegisterRequestHandler.INSTANCE);
                        // 登录请求处理器
                        nioSocketChannel.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        // 心跳包检测处理器
                        nioSocketChannel.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                        // 文件请求处理器
                        nioSocketChannel.pipeline().addLast(new FileDownloadRequestHandler());
                        // 验证处理器
                        nioSocketChannel.pipeline().addLast(AuthHandler.INSTANCE);
                        // 通信请求处理器
                        nioSocketChannel.pipeline().addLast(IMHandler.INSTANCE);
                    }
                });
        // 绑定对应端口
        bind(serverBootstrap, StringConst.PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println(new Date() + "： 端口[" + port + "]绑定成功!");
                } else {
                    System.out.println("端口[" + port + "]绑定失败!");
                }
            }
        });
    }

    public static void stopServer() {
        System.exit(0);
    }
}
