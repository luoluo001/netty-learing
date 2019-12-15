package com.lcy.moon.chatroom.sever;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Created by luo on 2019/12/15.
 */
public class CharRoomServerHanler2 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("远程client"+ channel.remoteAddress()+ "连接上了");

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("敌方英雄：" + buf.toString(CharsetUtil.UTF_8));
        System.out.println(">");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, CharsetUtil.UTF_8));
        String line = reader.readLine();
        ctx.writeAndFlush(Unpooled.copiedBuffer(line,CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush("").addListener(ChannelFutureListener.CLOSE);
        System.out.println("读取完成了");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //出异常之后关闭连接通道
        cause.printStackTrace();
        ctx.close();
    }
}
