package org.luapp.cms.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.DatagramChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * Created by lum on 2015/5/22.
 */
public final class ServerUDPChannelFactory {
    private final static Logger logger = LoggerFactory.getLogger(ServerUDPChannelFactory.class);

    public static Channel createAcceptorChannel(final ChannelType channelType,
                                                final InetSocketAddress localAddress,
                                                final ServerUDPHandler serverUDPHandler) {
        final Bootstrap serverBootstrap = ServerUDPBootstrapFactory.create(channelType);
        serverBootstrap.option(ChannelOption.SO_REUSEADDR, false)
                .handler(new ChannelInitializer<DatagramChannel>() {

                    @Override
                    protected void initChannel(DatagramChannel ch) throws Exception {
                        final ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("readTimeoutHandler", new ReadTimeoutHandler(60));
                        pipeline.addLast("handler", serverUDPHandler);
                    }
                });

        logger.info("Create UDP......");

        try {
            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(localAddress.getPort())).sync();
            channelFuture.awaitUninterruptibly();
            if (channelFuture.isSuccess()) {
                return channelFuture.channel();
            } else {
                logger.warn("failed to open socket! cannot bind to port:{}!", localAddress.getPort());
            }
        } catch (InterruptedException e) {
            logger.error("Error detail.", e);
        }
        return null;
    }
}
