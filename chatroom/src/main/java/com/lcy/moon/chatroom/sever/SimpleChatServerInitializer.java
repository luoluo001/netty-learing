package com.lcy.moon.chatroom.sever;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class SimpleChatServerInitializer extends
        ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //分隔符解码器
        pipeline.addLast(new ChatRoomServerHandler());
        System.out.println("SimpleChatClient:"+ch.remoteAddress() +"连接上");
    }

}
