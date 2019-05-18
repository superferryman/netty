package com.superferryman.client;

import com.superferryman.client.handler.*;
import com.superferryman.client.myChatClient.Main;
import com.superferryman.handler.IMIdleStateHandler;
import com.superferryman.protocol.common.StringConst;
import com.superferryman.protocol.code.PacketCodeHandler;
import com.superferryman.protocol.code.Spliter;
import com.superferryman.client.handler.command.impl.ConsoleCommandManager;
import com.superferryman.client.handler.command.impl.LoginConsoleCommand;
import com.superferryman.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


/**
 * @Author superferryman
 * @Date 2019/4/21 22:34
 */
public class NettyClient {
    public static void main(String[] args) {
        // 定义线程模型
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        // 定义客户端引导
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 指定线程模型
                .group(workerGroup)
                // 指定 IO 模型
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                // 完成 IO 逻辑处理
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel socketChannel) throws Exception {
                        // 空闲检测处理器
                        socketChannel.pipeline().addLast(new IMIdleStateHandler());
                        // 拆包处理器
                        socketChannel.pipeline().addLast(new Spliter());
                        // 编码译码处理器
                        socketChannel.pipeline().addLast(PacketCodeHandler.INSTANCE);
                        // 注册响应处理器
                        socketChannel.pipeline().addLast(RegisterResponseHandler.INSTANCE);
                        // 登录响应处理器
                        socketChannel.pipeline().addLast(LoginResponseHandler.INSTANCE);
                        // 文件响应处理器
                        socketChannel.pipeline().addLast(FileDownloadResponseHandler.INSTANCE);
                        // 心跳包处理器
                        socketChannel.pipeline().addLast(HeartBeatTimerHandler.INSTANCE);
                        // 通信请求处理器
                        socketChannel.pipeline().addLast(IMHandler.INSTANCE);
                    }
                });
        connect(bootstrap, StringConst.HOST, StringConst.PORT, StringConst.MAX_RETRY);
    }

    /**
     * 通常情况下，连接建立失败都不会立即进行重连，而是通过一个指数退避的方式，
     * 以2的幂次来建立连接，达到一定次数之后将会放弃重连，以下重写该逻辑，默认最多重连5次。
     */
    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        // 建立连接
        bootstrap.connect(StringConst.HOST, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功，启动控制台线程......");
                // 获取当前的 channel
                Channel channel = ((ChannelFuture) future).channel();
                // 启动控制台监听线程
                // startConsoleThread(channel);
                // 启动客户端UI
                startUIThread(channel);
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int times = (StringConst.MAX_RETRY - retry) + 1;
                // 本次重连的需要的时间间隔
                int delay = 1 << times;
                // 输出重连信息，例如第几次重连等
                System.err.println(new Date() + " : 连接失败，第" + times + "次重连......");
                // 定时任务
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startUIThread(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();

        new Thread(() -> {
            if (!SessionUtil.hasLogin(channel)) {
                /*loginConsoleCommand.exec(scanner, channel);*/
                SessionUtil.setCurrentChannel(channel);
                new Thread(() -> {
                    new Main().go(null);
                }).start();
            }
            while (!Thread.interrupted()) {
                if (SessionUtil.hasLogin(channel)) {
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        }).start();
    }

/*    private static void startConsoleThread(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();

        new Thread(() -> {
                while (!Thread.interrupted()) {
                    if (!SessionUtil.hasLogin(channel)) {
                        loginConsoleCommand.exec(scanner, channel);
                    } else {
                        consoleCommandManager.exec(scanner, channel);
                    }
                }
        }).start();
    }*/

    /*
    private static void connect(Bootstrap bootstrap, String host, int port) {
        // 建立连接
        bootstrap.connect("127.0.0.1", 8000).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
            } else {
                System.out.println("连接失败");
                connect(bootstrap, host, port);
            }
        });
    }
    */
}
