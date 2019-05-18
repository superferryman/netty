package com.superferryman.protocol.code;

import com.superferryman.protocol.Packet;
import com.superferryman.protocol.PacketCode;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author superferryman
 * @Date 2019/4/22 20:08
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCode.INSTANCE.encode(byteBuf, packet);
    }
}
