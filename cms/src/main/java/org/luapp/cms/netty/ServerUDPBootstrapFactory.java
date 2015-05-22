package org.luapp.cms.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.oio.OioDatagramChannel;

/**
 * Created by lum on 2015/5/22.
 */
public class ServerUDPBootstrapFactory {

    private ServerUDPBootstrapFactory() {
        throw new RuntimeException("factory class,can not initialize");
    }

    public static Bootstrap create(final ChannelType channelType) {
        Bootstrap serverBootstrap = new Bootstrap();
        switch (channelType) {
            case NIO:
                serverBootstrap.group(new NioEventLoopGroup());
                serverBootstrap.channel(NioDatagramChannel.class);
                return serverBootstrap;
            case OIO:
                serverBootstrap.group(new OioEventLoopGroup());
                serverBootstrap.channel(OioDatagramChannel.class);
                return serverBootstrap;
            default:
                throw new UnsupportedOperationException("Not support!");
        }
    }
}
