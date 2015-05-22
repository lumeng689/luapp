package org.luapp.cms.netty;

/**
 * Created by lum on 2015/5/22.
 */
public interface NettyServer {
    void start();

    void stop();

    void restart();
}
