package com.myclass.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhangzuizui
 * @date 2018/8/22 15:35
 */
public class SimpleChatServerHandler extends SimpleChannelInboundHandler {

    AtomicLong msgIdGenerator = new AtomicLong();
    static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    static AttributeKey<AtomicLong> msgIdGeneratorKey = AttributeKey.valueOf("msgIdGenerator");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        channels.add(ctx.channel());

        ctx.channel().attr(msgIdGeneratorKey).set(msgIdGenerator);
        // 把ConnectionInfo写到Attribute


        super.channelActive(ctx);
    }
}
