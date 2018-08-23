package com.myclass.netty.service;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * @author zhangzuizui
 * @date 2018/8/22 15:34
 * 用来增加多个的处理类到ChannelPipeline上:包括编码,解码,SimpleChatServerHandler
 */
public class SimpleChatServerInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

//        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
//        pipeline.addLast("decoder", new StringDecoder());
//        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", new SimpleChatServerHandler());
        System.out.println("SimpleChatClient:" + channel.remoteAddress()+"连接上");
    }
}
