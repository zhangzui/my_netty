package com.myclass.netty.service;

import com.myclass.netty.message.MyStringDecoder;
import com.myclass.netty.message.MyStringEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author zhangzuizui
 * @date 2018/8/22 15:34
 * 用来增加多个的处理类到ChannelPipeline上:包括编码,解码,SimpleChatServerHandler
 */
public class SimpleChatServerInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        //消息转换
        pipeline.addLast(new LineBasedFrameDecoder(1024));
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());

        /*
        pipeline.addLast(new MessageDecoder());
        pipeline.addLast(new MessageEncoder());*/
        pipeline.addLast("handler", new SimpleChatServerHandler());
        System.out.println("SimpleChatClient:" + channel.remoteAddress()+"连接上");
    }
}
