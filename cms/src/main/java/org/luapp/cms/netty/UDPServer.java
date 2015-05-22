package org.luapp.cms.netty;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;

/**
 * Created by lum on 2015/5/22.
 */
public class UDPServer implements NettyServer {

    private ChannelType channelType;
    private InetSocketAddress localAddress;
    private Channel acceptorChannel;

    private ServerUDPHandler serverUDPHandler;

    public ServerUDPHandler getServerUDPHandler() {
        return serverUDPHandler;
    }

    public void setServerUDPHandler(ServerUDPHandler serverUDPHandler) {
        this.serverUDPHandler = serverUDPHandler;
    }

    public UDPServer(ChannelType channelType, int port, ServerUDPHandler serverUDPHandler) {
        this.channelType = channelType;
        this.localAddress = new InetSocketAddress(port);
        this.serverUDPHandler = serverUDPHandler;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void restart() {
        stop();
        start();
    }
}
