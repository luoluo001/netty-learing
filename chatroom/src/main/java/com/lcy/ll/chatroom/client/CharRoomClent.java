package com.lcy.ll.chatroom.client;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by luo on 2019/12/14.
 */
public class CharRoomClent {
    private final String host;
    private final int port;
    public CharRoomClent(String host,int port){
        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();//事件循环组 默认有nio和oio的实现循环
        try {
            Bootstrap bs = new Bootstrap();//启动项
            bs.group(group).channel(NioSocketChannel.class)//定义使用哪一类的channel
                    .remoteAddress( new InetSocketAddress(host,port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ChatRoomClientHandler());//添加handler监听
                        }
                    });

            ChannelFuture future = bs.connect().sync();//阻塞直到连接完成
            future.channel().closeFuture().sync();//阻塞直到channel关闭
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully().sync();//最终关闭掉group
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String host = args[0];
        String port = args[1];
        new CharRoomClent(host,Integer.valueOf(port)).start();



    }
}
