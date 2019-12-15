package com.lcy.moon.chatroom.client;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

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
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                            pipeline.addLast("decoder", new StringDecoder());
                            pipeline.addLast("encoder", new StringEncoder());
                            pipeline.addLast(new ChatRoomClientHandler());//添加handler监听

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
//        String host = args[0];
//        String port = args[1];
        new CharRoomClent("127.0.0.1",8080).start();



    }
}
