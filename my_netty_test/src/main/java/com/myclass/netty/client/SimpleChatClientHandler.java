package com.myclass.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.Date;
import java.util.Scanner;

/**
 * @author zhangzuizui
 * @date 2018/8/22 15:35
 */
public class SimpleChatClientHandler extends SimpleChannelInboundHandler<String> {


    /**
     * /*
     * channelRead
     *
     * channel 通道
     * Read    读
     *
     * 简而言之就是从通道中读取数据，也就是服务端接收客户端发来的数据
     * 但是这个数据在不进行解码时它是ByteBuf类型的后面例子我们在介绍
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("客户端-接收消息："+msg);
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        ctx.writeAndFlush(read);
    }

    /**
     * channelAction
     *
     * channel 通道
     * action  活跃的
     *
     * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     *
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //System.out.println(ctx.channel().localAddress().toString()+" channelActive");

        //通知您已经链接上客户端
        //String str = "您已经开启与服务端链接"+" "+new Date()+" "+ctx.channel().localAddress();
       // ctx.writeAndFlush(str);

    }

    /**
     * channelInactive
     *
     * channel 	通道
     * Inactive 不活跃的
     *
     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     *
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        //System.out.println(ctx.channel().localAddress().toString()+" channelInactive");

    }
}
