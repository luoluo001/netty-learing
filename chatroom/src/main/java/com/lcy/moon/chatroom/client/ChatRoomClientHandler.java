package com.lcy.moon.chatroom.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Created by luo on 2019/12/14.
 */
public class ChatRoomClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String str) throws Exception {
        //读取事件监听
        System.out.println("client receive :" + str);
        System.out.println(">");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, CharsetUtil.UTF_8));
        String line = reader.readLine();
        ctx.writeAndFlush(line);
    }



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //首次连接之后发送的内容
        System.out.println("client连接上了");
        ctx.writeAndFlush("connetion server first");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //出异常之后关闭连接通道
        cause.printStackTrace();
        ctx.close();
    }
}
