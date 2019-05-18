package com.superferryman.protocol.code;

import com.superferryman.protocol.Packet;
import com.superferryman.protocol.PacketCode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @Author superferryman
 * @Date 2019/4/23 21:34
 */
@ChannelHandler.Sharable
public class PacketCodeHandler extends MessageToMessageCodec<ByteBuf, Packet> {

    public static final PacketCodeHandler INSTANCE = new PacketCodeHandler();

    protected PacketCodeHandler() {}

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, List<Object> list) throws Exception {
        ByteBuf byteBuf = channelHandlerContext.channel().alloc().ioBuffer();
        list.add(PacketCode.INSTANCE.encode(byteBuf, packet));
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(PacketCode.INSTANCE.decode(byteBuf));
    }
}
