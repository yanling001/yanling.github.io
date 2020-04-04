package DubboRpc.Provider;

import DubboRpc.Provider.Pinterface.UserServerImp;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    public static String result;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //根据msg判断调用那个接口的方法
        System.out.println("msg "+ msg);
        if (msg.toString().startsWith("userService#getUserinfo#")){
            String s =(String) msg;
           result = new UserServerImp().getUserinfo(Integer.parseInt(s.substring(s.lastIndexOf("#")+1)));
          //回写客户端
            ctx.writeAndFlush(result);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
