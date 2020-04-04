package DubboRpc.Provider;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ProviderServer {
    public static void main(String[] args) {
        startServer(8090);
    }
    public  static void startServer(int port)  {
        EventLoopGroup bossgroup=new NioEventLoopGroup();
        EventLoopGroup workgroup = new NioEventLoopGroup();
        try{
            //ServerBootstrap负责建立服务端
            //你可以直接使用Channel去建立服务端，但是大多数情况下你无需做这种乏味的事情
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossgroup,workgroup)
                    //指定使用NioServerSocketChannel产生一个Channel用来接收连接
                    .channel(NioServerSocketChannel.class)
                    //ChannelInitializer用于配置一个新的Channel
                    //用于向你的Channel当中添加ChannelInboundHandler的实现
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel cx) throws Exception {
                            ChannelPipeline pipeline = cx.pipeline();
                            pipeline.addLast(new StringDecoder());//解码
                            pipeline.addLast(new StringEncoder());//编码
                            pipeline.addLast(new ServerHandler());//配置自己的handler

                        }
                    });
            ChannelFuture f=serverBootstrap.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            //资源优雅释放
            bossgroup.shutdownGracefully();
            workgroup.shutdownGracefully();
        }

    }
}
