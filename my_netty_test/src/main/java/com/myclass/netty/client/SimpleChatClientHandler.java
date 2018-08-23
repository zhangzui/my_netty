package com.myclass.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

/**
 * @author zhangzuizui
 * @date 2018/8/22 15:35
 */
public class SimpleChatClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("客户端-接收消息："+msg);
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        ctx.writeAndFlush(read);
    }
}
