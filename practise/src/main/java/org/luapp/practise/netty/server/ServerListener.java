package org.luapp.practise.netty.server;

/**
 * Created by lum on 2015/5/27.
 */
public interface ServerListener<T> {
    void messageReceived(ServerHandler serverHandler, T msg);

    void connectionOpen(ServerHandler serverHandler);
}
