package org.luapp.core.thrift;

import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Protocol;

/**
 * Created by lum on 2015/5/19.
 */
public class ThriftHelper {
    private static Logger logger = LoggerFactory.getLogger(ThriftHelper.class);

    private ThriftHelper() {
        throw new RuntimeException("initialize failed!");
    }

    /**
     * 关闭thrift客户端
     *
     * @param client
     */
    public static void closeClient(TServiceClient client) {
        if (client == null) {
            logger.info("client alreay closed!");
            return;
        }
        logger.info("Closing input protocol...");
        closeProtocol(client.getInputProtocol());
        logger.info("Closing output protocol...");
        closeProtocol(client.getOutputProtocol());
    }

    private static void closeProtocol(TProtocol proto) {
        if (proto != null) {
            try {
                proto.getTransport().close();
            } catch (Exception e) {
                logger.error("close proto exception :", e);
            }
        }

        logger.info("protocol closed successfully!");
    }
}
