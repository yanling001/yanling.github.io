package DubboRpc.Customs;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class ClientHandler extends ChannelInboundHandlerAdapter implements Callable {
    ChannelHandlerContext context;
    String result;
    String param;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx);
        this.context=ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
         result=(String) msg;
         //返回后唤醒
         notify();
    }

    @Override
    public synchronized Object call() throws Exception {
        System.out.println("远程已被调用");

        System.out.println(context);
        //调用远程接口
        context.writeAndFlush(param);
        //等待回调
        wait();
        return result;
    }
}
