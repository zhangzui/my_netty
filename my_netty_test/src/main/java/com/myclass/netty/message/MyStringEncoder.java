package com.myclass.netty.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

import java.nio.CharBuffer;

public class MyStringEncoder extends MessageToByteEncoder<String> {
  @Override
  protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {

    //out.add(ByteBufUtil.encodeString(ctx.alloc(), CharBuffer.wrap(msg), this.charset));
    out.writeBytes(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
    ctx.flush();
  }
}
