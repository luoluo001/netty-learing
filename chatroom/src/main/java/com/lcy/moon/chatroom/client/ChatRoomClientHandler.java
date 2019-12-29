package com.lcy.moon.chatroom.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by luo on 2019/12/14.
 */
public class ChatRoomClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String str) throws Exception {
        //读取事件监听
        System.out.println("client receive :" + str);
    }



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //首次连接之后发送的内容
        System.out.println("client连接上了");
        ctx.writeAndFlush("connetion server first");
        System.out.println("success");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //出异常之后关闭连接通道
        cause.printStackTrace();
        ctx.close();
    }
}
