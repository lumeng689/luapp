package org.luapp.practise.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.luapp.practise.proto.RequestProto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lum on 2015/5/26.
 */
public class LdapClientHandler extends ChannelInboundHandlerAdapter {

    private final List<Integer> firstMessage;

    /**
     * Creates a client-side handler.
     */
    public LdapClientHandler() {
        firstMessage = new ArrayList<Integer>(LdapClient.SIZE);
        for (int i = 0; i < LdapClient.SIZE; i ++) {
            firstMessage.add(Integer.valueOf(i));
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // Send the first message if this handler is a client-side handler.
        RequestProto.Request.Builder rb = RequestProto.Request.newBuilder();
        RequestProto.Request r = rb.setCmdType(1).build();

        ctx.writeAndFlush(r);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // Echo back the received object to the server.
        ctx.write(msg);
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
