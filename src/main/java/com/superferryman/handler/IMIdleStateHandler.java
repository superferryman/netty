package com.superferryman.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Author superferryman
 * @Date 2019/4/24 19:53
 */
public class IMIdleStateHandler extends IdleStateHandler {

    /**
     * 读空闲时间(单位 s )
     */
    private static final int READER_IDLE_TIME = 15;

    public IMIdleStateHandler() {
        /*
        * agr0 : 读空闲时间，指在这段时间内如果没有数据读则视为“假死”状态
        * arg1 : 写空闲时间，指的是在这段时间内没有写数据则视为“假死”状态
        * arg2 : 读写空闲时间，标识这段时间内没有进行读写数据则视为“假死”状态
        * arg3 : 时间单位标识
        * */
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
        ctx.channel().close();
    }
}
