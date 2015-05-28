package org.luapp.practise.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.luapp.practise.proto.ResponseProto;

/**
 * Created by lum on 2015/5/26.
 */
public class LdapServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // Echo back the received object to the client.
        ResponseProto.Response.Builder rb = ResponseProto.Response.newBuilder();
        ResponseProto.Response r= rb.setRet(0).setMsg("ok").build();
        ctx.write(r);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}