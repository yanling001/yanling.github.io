package DubboRpc.Customs;

import DubboRpc.Provider.Pinterface.UserServcie;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomClient {
   static ExecutorService executor= Executors.newFixedThreadPool(8);
     static   ClientHandler clientHandler=new ClientHandler();
    public static Object getBean(final Class<?> serverclass, final String param){
        return Proxy.newProxyInstance(UserServcie.class.getClassLoader(), new Class<?>[]{serverclass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            clientHandler.param=param+args[0];

                return executor.submit(clientHandler).get();
            }
        });

    }
    public static void startClient(int port) throws InterruptedException {
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new StringEncoder());
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(clientHandler);//my handle;
                    }
                });
        ChannelFuture connect = bootstrap.connect("127.0.0.1", port).sync();

       // connect.channel().closeFuture().sync();
    }
}
