package org.luapp.practise.netty;

import org.luapp.practise.netty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lum on 2015/5/27.
 */
public class ServerRunner {

    private final static Logger logger = LoggerFactory.getLogger(ServerRunner.class);

    public static void main(String[] args) {
        logger.info("start server......");
        final Server s = new Server();

        if (!s.start()) {
            logger.error("start server failed!...");
            return;
        }

        // 关闭程序时停止服务
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                s.stop();
            }
        });
    }
}
